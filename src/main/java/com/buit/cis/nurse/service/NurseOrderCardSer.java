package com.buit.cis.nurse.service;

import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import org.apache.commons.collections.CollectionUtils;
import org.apache.dubbo.config.annotation.DubboReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.buit.cis.dctwork.dao.CisHzyzDao;
import com.buit.cis.dctwork.dao.SkinPsjlDao;
import com.buit.cis.dctwork.model.CisHzyz;
import com.buit.cis.dctwork.request.SkinPsjlReq;
import com.buit.cis.dctwork.response.SkinPsjlResp;
import com.buit.cis.ims.dao.ImHzryDao;
import com.buit.cis.ims.model.ImHzry;
import com.buit.cis.nurse.dao.CisKfdDao;
import com.buit.cis.nurse.dao.CisKpbzmxDao;
import com.buit.cis.nurse.dao.CisKpkfbzDao;
import com.buit.cis.nurse.dao.CisYzkpdyjlDao;
import com.buit.cis.nurse.model.CisKfd;
import com.buit.cis.nurse.model.CisKpbzmx;
import com.buit.cis.nurse.model.CisKpkfbz;
import com.buit.cis.nurse.model.CisYzkpdyjl;
import com.buit.cis.nurse.request.CisHzyzCardCancelReq;
import com.buit.cis.nurse.request.CisHzyzCardReq;
import com.buit.commons.BaseManagerImp;
import com.buit.commons.SysUser;
import com.buit.constans.SysXtcsCsmcCts;
import com.buit.constans.TableName;
import com.buit.file.IReportExportFileSer;
import com.buit.system.response.DicSypcModel;
import com.buit.system.response.SysXtcs;
import com.buit.system.service.DicKszdOutSer;
import com.buit.system.service.DicSypcService;
import com.buit.system.service.SysXtcsCacheSer;
import com.buit.system.utill.ObjectToTypes;
import com.buit.utill.BUHISUtil;
import com.buit.utill.BytesUtil;
import com.buit.utill.RedisFactory;

import cn.hutool.core.date.DateField;
import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUnit;
import cn.hutool.core.date.DateUtil;

/**
 * <br>护士医嘱卡片打印
 * @author GONGFANGZHOU
 */
@Service
public class NurseOrderCardSer extends BaseManagerImp<CisHzyz,Integer> {
	
	static final Logger logger = LoggerFactory.getLogger(NurseOrderCardSer.class);
    
    @Autowired
    private CisHzyzDao cisHzyzDao;
    
    @Override
    public CisHzyzDao getEntityMapper(){        
        return cisHzyzDao;
    }
    
    @Autowired
    private RedisFactory redisFactory;
    
    @Autowired
    private CisKpbzmxDao cisKpbzmxDao;
    
    @Autowired 
    private CisKpkfbzDao cisKpkfbzDao;
    
    @DubboReference
    private SysXtcsCacheSer sysXtcsCacheSer;
    
    @DubboReference
    private DicSypcService dicSypcService;
    
    @Autowired
    private CisKfdDao cisKfdDao;
    
    @Autowired
    private CisYzkpdyjlDao cisYzkpdyjlDao;
    
    @Autowired
    private IReportExportFileSer iReportExportFileSer;
    
    @Autowired
    private ImHzryDao imHzryDao;
    
    @DubboReference
    private DicKszdOutSer dicKszdOutSer;
    
    @Autowired
    private SkinPsjlDao skinPsjlDao;
    
    /**
     * 获取口服卡固定卡片打印返回集合
     * @Title: getOralCardFields
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @param @param cisHzyzCardReq
     * @param @param user
     * @param @return    设定文件
     * @return List<Map<String,Object>>    返回类型
     * @author 龚方舟
     * @throws
     */
	public List<Map<String, Object>> getOralCardFields(CisHzyzCardReq cisHzyzCardReq, SysUser user) {
    	List<Map<String, Object>> resList = new ArrayList<Map<String,Object>>();
		Integer dybz = cisHzyzCardReq.getDybz();
		Date kpyysj = setCardDateTime(cisHzyzCardReq.getTypeDate());
		int nowYear = DateUtil.year(kpyysj);
		int nowMouth = DateUtil.month(kpyysj) + 1;
		int nowDay =  DateUtil.dayOfMonth(kpyysj);
		List<Map<String, Object>> list = getOralCardList(cisHzyzCardReq, kpyysj, user, 0);
		List<String> zhList = new ArrayList<String>();
		Map<String, List<Map<String, Object>>> zhMap = orderGrouping(list, zhList, kpyysj);
		// 取出每个组的医嘱 并分别打印到前台页面
		for(int k = 0 ; k < zhList.size() ; k ++ ){
			List<Map<String, Object>> yzList = zhMap.get(zhList.get(k));
			if(!yzList.isEmpty()) {
				Map<String, Object> map = yzList.get(0);
				Date csny = DateUtil.parse(map.get("CSNY") + "");
				// 出生日期
				int borthYear = DateUtil.year(csny);
				int borthMouth = DateUtil.month(csny) + 1;
				int borthDay =  DateUtil.dayOfMonth(csny);
				// 计算年龄
				int age = nowYear - borthYear - 1;
				boolean dateFlag = borthMouth < nowMouth || borthMouth == nowMouth
						&& borthDay <= nowDay;
				if (dateFlag) {
					age++;
				}
	
				String yzzxsj = ObjectToTypes.parseString(map.get("YZZXSJ"));
				
				Map<String, Object> o = new HashMap<String, Object>(16);
				if(cisHzyzCardReq.getLsyz() == 0 && ObjectToTypes.parseInt(yzzxsj.substring(0,2)) >= 16) {
					o.put("DYRQ", DateUtil.offset(kpyysj, DateField.DAY_OF_MONTH, -1).toSqlDate());
				}else {
					o.put("DYRQ", kpyysj);
				}
				o.put("TITLE", user.getHospitalName() + "口服贴" + (cisHzyzCardReq.getLsyz() == 0 ? "(长期)" : "(临时)"));
				o.put("BRXB", "1".equals(ObjectToTypes.parseString(map.get("BRXB"))) ? "男" : "女");
				o.put("BRNL", age);// 病人年龄
				o.put("BQMC", cisHzyzCardReq.getBqdmName());// 病区名称
				o.put("TIME", yzzxsj);// 医嘱执行时间
				o.put("BRXM", map.get("BRXM"));
				o.put("BRCH", map.get("BRCH"));
				o.put("ZYHM", map.get("ZYHM"));
				o.put("PCMC", iReportExportFileSer.conVerDataSource(yzList));
				if(dybz == 1){
					saveKfInfo(yzList, nowYear, nowMouth, nowDay, user, kpyysj);
					saveYzkpdyjl(yzList, DateUtil.date(), kpyysj);
				}
				resList.add(o);
			}
		}
		return resList;
	}
    
    /**
     * 获取医嘱卡片打印信息
     * @Title: getOralCardList
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @param @param cisHzyzCardReq
     * @param @param kpyysj
     * @param @param user
     * @param @return
     * @param @throws PrintException    设定文件
     * @return List<Map<String,Object>>    返回类型
     * @author 龚方舟
     * @throws
     */
    public List<Map<String, Object>> getOralCardList(CisHzyzCardReq cisHzyzCardReq, Date kpyysj,
    		SysUser user, Integer xmlb) {
    	List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
		Integer jgid = user.getHospitalId();
			// 获取表单参数
		List<Integer> zyhList = cisHzyzCardReq.getZyhList();
		if (zyhList.size() > 0) {
			// 获得系统参数中关于 医嘱符合标识 的设置
			// "FHYZHJF","1","医嘱不复核是否可进行后续业务FHYZHJF，0:可以进行，1:不可以进行"
			SysXtcs sysXtcs = sysXtcsCacheSer.getByJGIdAndCsmc(jgid, SysXtcsCsmcCts.FHYZHJF);
			String fhbz = sysXtcs == null ? "0" : sysXtcs.getCsz();

			Map<String, Object> parameters = new HashMap<String, Object>(16);
			parameters.put("bqdm", cisHzyzCardReq.getBqdm());// 当前病区
			Integer yzlb = cisHzyzCardReq.getYzlb();
			if(yzlb == 7) {
				yzlb = 2;
			}
			parameters.put("yzlb", yzlb);// 卡片类型
			parameters.put("jgid", jgid);// 机构ID
			parameters.put("fhbz", ObjectToTypes.parseInt(fhbz));// 系统配置参数中 符合标识
			parameters.put("yysj", DateUtil.format(kpyysj, DatePattern.NORM_DATE_PATTERN));//=======新加卡片打印的时间========
			parameters.put("zyhList", zyhList);
			if(cisHzyzCardReq.getLsyz() != null) {
				parameters.put("lsyz", cisHzyzCardReq.getLsyz());
			}
			if(xmlb == 0) {
				parameters.put("xmlb", xmlb);
			}
			if(cisHzyzCardReq.getYzlb() == 2) {
				sysXtcs = sysXtcsCacheSer.getByJGIdAndCsmc(jgid, SysXtcsCsmcCts.YYKYPXH);
				List<String> yykList = Arrays.asList(sysXtcs.getCsz().split(","));
				if(!yykList.isEmpty()) {
					parameters.put("sfyyk", cisHzyzCardReq.getSfyyk());
					parameters.put("yykList", yykList);
				}
				list = cisHzyzDao.queryOralCardInfo(parameters);
			}else {				
				list = cisHzyzDao.queryMedicalCardInfo(parameters);
			}
			
			Iterator<Map<String, Object>> iter = list.iterator();
			while (iter.hasNext()) {
				Map<String, Object> rec = iter.next();
				String sypc = ObjectToTypes.parseString(rec.get("SYPC"));
				Date kssj = DateUtil.parse(rec.get("KSSJ") + "");
				int zxbz = validOrders(sypc, kssj, kpyysj);
				if (zxbz != 1) {
					iter.remove();
				}
			}
		}	
		return list;
	}
    
    /**
     * 判断医嘱有效性
     * @Title: validOrders
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @param @param sypc
     * @param @param kssj
     * @param @param nowDate
     * @param @return    设定文件
     * @return int 1:需执行 0:不需执行 -1:有错误发生
     * @author 龚方舟
     * @throws
     */
	public int validOrders(String sypc, Date kssj, Date nowDate) {
		DicSypcModel dicSypc = dicSypcService.getById(sypc);
		if(dicSypc != null) {
			Integer zxbz = 0;
			Integer zxzq = dicSypc.getZxzq();
			Integer rlz = dicSypc.getRlz();
			String rzxzq = dicSypc.getRzxzq();
			if (zxzq == 1) {
				return 1;// 最小周期为1,则必定执行
			}
			if (rzxzq.length() != zxzq) {
				return -1;// 最小周期和日执行周期中字符个数不符
			}
			if (rlz == 1) {
				Calendar c = Calendar.getInstance();
				c.setTime(nowDate);
				int zqbz = c.get(Calendar.DAY_OF_WEEK);
				if (rzxzq.length() == zqbz) {
					zxbz = ObjectToTypes.parseInt(rzxzq.substring(zqbz - 1));
				} else {
					zxbz = ObjectToTypes.parseInt(rzxzq.substring(zqbz - 1, zqbz));
				}
			} else {
				int days = (int) DateUtil.between(DateUtil.parse(DateUtil.format(kssj, DatePattern.NORM_DATE_PATTERN), DatePattern.NORM_DATE_PATTERN), 
						DateUtil.parse(DateUtil.format(nowDate, DatePattern.NORM_DATE_PATTERN), DatePattern.NORM_DATE_PATTERN), DateUnit.DAY);
				days = days % zxzq;
				if (days == 0) {
					days = zxzq;
				}
				int rzlen = rzxzq.length();
				if (rzlen <= days) {
					zxbz = 1;
				}else{
					zxbz = ObjectToTypes.parseInt(rzxzq.substring(days, days + 1));// 0或1
				}
			}
			return zxbz;

		} else {
			return -1;
		}
		
	}
	
	
	/**
	 * 获取口服卡子报表路径
	 * @Title: getOralCardParameters
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @return    设定文件
	 * @return Map<String,Object>    返回类型
	 * @author 龚方舟
	 * @throws
	 */
	public Map<String, Object> getOralCardParameters(){
		Map<String, Object> responseMap = new HashMap<String, Object>(16);
		responseMap.put("SUBREPORT_DIR", this.getClass().getClassLoader().getResource("jrxml/"));
		return responseMap;
	}
	
	/**
	 * 获取口服、静推、肌肉注射、皮下执行单医嘱返回集合
	 * @Title: getOralCardZxdFields
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @param cisHzyzCardReq
	 * @param @param user
	 * @param @return    设定文件
	 * @return List<Map<String,Object>>    返回类型
	 * @author 龚方舟
	 * @throws
	 */
	public List<Map<String, Object>> getOralCardZxdFields(CisHzyzCardReq cisHzyzCardReq, SysUser user){
		List<Map<String, Object>> resList = new ArrayList<Map<String,Object>>();
		DecimalFormat df = new DecimalFormat("##########.##########");
		Date kpyysj = setCardDateTime(cisHzyzCardReq.getTypeDate());
		List<Map<String, Object>> list = getOralCardZxList(cisHzyzCardReq, user, kpyysj);
		Map<String, List<Map<String, Object>>> zhMap = new HashMap<String, List<Map<String, Object>>>(16);
		List<String> zhList = list.stream().map(o -> ObjectToTypes.parseString(o.get("ZYHM"))).distinct().collect(Collectors.toList());
		for(String str : zhList) {
			zhMap.put(str, new ArrayList<Map<String,Object>>());
		}

		// 将同组的数据分别放到各自所属集合中
		for (int j = 0; j < list.size(); j++) {
			Map<String, Object> map = list.get(j);
			map.put("YCJL", df.format(ObjectToTypes.parseBigDecimal(map.get("YCJL"))));
			if("0".equals(ObjectToTypes.parseString(map.get("LSYZ")))) {
				if (map.get("TZSJ") != null) {
	                Timestamp tzsj = DateUtil.parse(map.get("TZSJ") + "").toTimestamp();
	                //判断停嘱日期和当前日期
	                int d = BUHISUtil.dateCompare(new Date(tzsj.getTime()), kpyysj);
	                if (d < 0) {
	                    continue;
	                }
	            }
			}
			
			String zyhm = ObjectToTypes.parseString(map.get("ZYHM"));
			//同一组医嘱添加标识符
			Map<String, Object> jlMap = list.get(j);
			map.put("ZH", " ");
			//实现相同日期只显示第一条和最后一条,同组加符号		
			if(j != 0){
				if(ObjectToTypes.parseInt(jlMap.get("YZZH")) == ObjectToTypes.parseInt(list.get(j-1).get("YZZH"))){
					if((j+1) <list.size()){
						if(ObjectToTypes.parseInt(jlMap.get("YZZH")) == ObjectToTypes.parseInt(list.get(j+1).get("YZZH"))){
							map.put("ZH", "┃");
						}else{
							map.put("ZH", "┛");
						}
					}else{
						map.put("ZH", "┛");
					}
				}else{
					if((j+1) < list.size()){
						if(ObjectToTypes.parseInt(jlMap.get("YZZH")) == ObjectToTypes.parseInt(list.get(j+1).get("YZZH"))){
							map.put("ZH", "┓");
						}
					}
				}
			}
			if(j == 0){
				if((j+1) < list.size()){
					if(ObjectToTypes.parseInt(jlMap.get("YZZH")) == ObjectToTypes.parseInt(list.get(j+1).get("YZZH"))){
						map.put("ZH", "┓");
					}
				}
			}
			zhMap.get(zyhm).add(map);
		}
		// 取出每个组的医嘱 并分别打印到前台页面
		for(int k = 0 ; k < zhList.size() ; k ++ ){
			List<Map<String, Object>> yzList = zhMap.get(zhList.get(k));
			if(!yzList.isEmpty()) {
				Map<String, Object> map = yzList.get(0);
				Map<String, Object> o = new HashMap<String, Object>(16);
				o.put("BRCH", map.get("BRCH"));
				o.put("BRXM", map.get("BRXM"));
				o.put("CSRQ", map.get("CSNY"));
				o.put("ZYHM", map.get("ZYHM"));
				o.put("KSMC", map.get("KSMC"));
				o.put("BQMC", map.get("BQMC"));
				o.put("DYRQ", DateUtil.format(kpyysj, "MM.dd"));
				o.put("XB", map.get("XB"));
				o.put("RYNL", map.get("RYNL"));
				o.put("CHILD", iReportExportFileSer.conVerDataSource(yzList));
				resList.add(o);
			}
			
		}
		return resList;
	}
	
	/**
	 * 查询口服、静推、肌肉注射、皮下执行单医嘱信息
	 * @Title: getOralCardZxList
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @param cisHzyzCardReq
	 * @param @param user
	 * @param @return    设定文件
	 * @return List<Map<String,Object>>    返回类型
	 * @author 龚方舟
	 * @throws
	 */
	public List<Map<String, Object>> getOralCardZxList(CisHzyzCardReq cisHzyzCardReq, SysUser user,
			Date kpyysj) {
		Integer jgid = user.getHospitalId();
		List<Map<String, Object>> body = new ArrayList<Map<String, Object>>();
		//获取表单参数
		//获得选中的医嘱病人住院号的集合
		List<Integer> zyhList = cisHzyzCardReq.getZyhList();
		if (zyhList.size() > 0 ) {
			Integer yzlb = cisHzyzCardReq.getYzlb();
			// 获得系统参数中关于 医嘱符合标识 的设置
			// "FHYZHJF","1","医嘱不复核是否可进行后续业务FHYZHJF，0:可以进行，1:不可以进行"
			SysXtcs sysXtcs = sysXtcsCacheSer.getByJGIdAndCsmc(jgid, SysXtcsCsmcCts.FHYZHJF);
			String fhbz = sysXtcs == null ? "0" : sysXtcs.getCsz();
			
			Map<String, Object> parameters = new HashMap<String, Object>(16); 
			parameters.put("zyhList", zyhList);
			parameters.put("bqdm", cisHzyzCardReq.getBqdm());// 当前病区
			parameters.put("jgid", jgid);// 机构ID
			parameters.put("fhbz", fhbz);// 系统配置参数中 符合标识
			parameters.put("lsyz", cisHzyzCardReq.getLsyz());
			
			//巡视卡
			if(yzlb == 4) {
				parameters.put("xmlb", 0);
			}else if(yzlb == 6) {
				parameters.put("xmlb", 1);
			}else {
				parameters.put("yzlb", yzlb);
			}

			if(yzlb == 16) {
				sysXtcs = sysXtcsCacheSer.getByJGIdAndCsmc(jgid, SysXtcsCsmcCts.ZXKSDM);
				List<String> zxksList = Arrays.asList(sysXtcs.getCsz().split(","));
				parameters.put("zxksList", zxksList);
				body = cisHzyzDao.queryTreatmentZxInfo(parameters);
			}else {	
				parameters.put("yysj", DateUtil.format(kpyysj, DatePattern.NORM_DATE_PATTERN));
				if(yzlb == 2) {
					sysXtcs = sysXtcsCacheSer.getByJGIdAndCsmc(jgid, SysXtcsCsmcCts.YYKYPXH);
					List<String> yykList = Arrays.asList(sysXtcs.getCsz().split(","));
					if(!yykList.isEmpty()) {
						parameters.put("sfyyk", cisHzyzCardReq.getSfyyk());
						parameters.put("yykList", yykList);
					}
				}
				body = cisHzyzDao.queryOralCardZxInfo(parameters);
			}
			Iterator<Map<String, Object>> iter = body.iterator();
			while (iter.hasNext()) {
				Map<String, Object> rec = iter.next();
				String sypc = ObjectToTypes.parseString(rec.get("SYPC"));
				Date kssj = DateUtil.parse(rec.get("KSSJ") + "");
				int zxbz = validOrders(sypc, kssj, kpyysj);
				if (zxbz != 1) {
					iter.remove();
				}
			}
		}
		return body;
	}
	
	/**
	 * 获取口服、静推、肌肉注射、皮下执行单医嘱返回路径和信息等
	 * @Title: getOralCardZxParameters
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @param cisHzyzCardReq
	 * @param @return    设定文件
	 * @return Map<String,Object>    返回类型
	 * @author 龚方舟
	 * @throws
	 */
	public Map<String, Object> getOralCardZxdParameters(CisHzyzCardReq cisHzyzCardReq, SysUser user) {
		
		Map<String, Object> responseMap = new HashMap<String, Object>(16);
		String yzlb  = ObjectToTypes.parseString(cisHzyzCardReq.getYzlb());
		responseMap.put("TITLE", user.getHospitalName());
		String djmc = "";
		if("2".equals(yzlb)){
			djmc = cisHzyzCardReq.getLsyz() == 0 ? "服药单(长期)" : "服药单(临时)";
		}else if("3".equals(yzlb)){
			djmc = cisHzyzCardReq.getLsyz() == 0 ? "注射单(长期)" : "注射单(临时)";
		}else if("4".equals(yzlb)){
			djmc = cisHzyzCardReq.getLsyz() == 0 ? "输液单(长期)" : "输液单(临时)";
		}else if("16".equals(yzlb)){
			djmc = user.getHospitalName() + (cisHzyzCardReq.getLsyz() == 0 ? "治疗单(长期)" : "治疗单(临时)" );
		}
		responseMap.put("DJMC", djmc);
		responseMap.put("BQMC", cisHzyzCardReq.getBqdmName());
		Date dyrq = setCardDateTime(cisHzyzCardReq.getTypeDate());
		responseMap.put("DYRQ", DateUtil.format(dyrq, DatePattern.NORM_DATE_PATTERN));
		responseMap.put("SUBREPORT_DIR", this.getClass().getClassLoader().getResource("jrxml/"));
		return responseMap;
	}
	
	
	
	/**
	 * 静推卡固定卡片打印返回数据
	 * @Title: getInjectionCardFields
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @param cisHzyzCardReq
	 * @param @param user    设定文件
	 * @return void    返回类型
	 * @author 龚方舟
	 * @throws
	 */
	public List<Map<String, Object>> getInjectionCardFields(CisHzyzCardReq cisHzyzCardReq, SysUser user) {
		List<Map<String, Object>> resList = new ArrayList<Map<String,Object>>();
		Integer dybz = cisHzyzCardReq.getDybz();
		try {
			Date kpyysj = setCardDateTime(cisHzyzCardReq.getTypeDate());
			List<Map<String, Object>> list = getOralCardList(cisHzyzCardReq, kpyysj, user, 1);
			Map<String, List<Map<String, Object>>> zhMap = list.stream().
        			collect(Collectors.groupingBy(o -> ObjectToTypes.parseString(o.get("YZZH"))));
			String username = user.getUserName();
			//取出每个组的医嘱 并分别打印到前台页面
			for (Map.Entry<String, List<Map<String, Object>>> entry : zhMap.entrySet()){
				List<Map<String, Object>> yzList = entry.getValue();
				Map<String, Object> map = yzList.get(0);
				Date csny = (Date) map.get("CSNY");// 出生日期
				// 出生日期
				Calendar cs = Calendar.getInstance();
				cs.setTime(csny);
				int borthYear = cs.get(Calendar.YEAR);
				int borthMouth = cs.get(Calendar.MONTH) + 1;
				int borthDay = cs.get(Calendar.DATE);
				// 当前时间
				Date nowDate = new Date();
				Calendar c = Calendar.getInstance();// 可以对每个时间域单独修改
				c.setTime(nowDate);

				int nowYear = c.get(Calendar.YEAR);
				int nowMouth = c.get(Calendar.MONTH) + 1;
				int nowDay = c.get(Calendar.DATE);
				// 计算年龄
				int age = nowYear - borthYear - 1;
				boolean dateFlag = borthMouth < nowMouth || borthMouth == nowMouth
						&& borthDay <= nowDay;
				if (dateFlag) {
					age++;
				}
				//========================打印时间========================
				String yzzxsj = map.get("YZZXSJ") + "";
				int srcs = ObjectToTypes.parseInt(map.get("SRCS"));
				String[] zxsjStr = yzzxsj.split("-");

				Timestamp kssj = DateUtil.parse(map.get("KSSJ") + "").toTimestamp();
				Calendar ksCal = Calendar.getInstance();
				ksCal.setTime(kssj);
				int ksMonth = ksCal.get(Calendar.MONTH) + 1;
				int ksYear = ksCal.get(Calendar.YEAR);
				int ksDay = ksCal.get(Calendar.DATE);

				SimpleDateFormat sdf = new SimpleDateFormat(
						"yyyy-MM-dd HH:mm:ss");

				if (zxsjStr.length != 0) {
					int j = 0;// 用于首日次数的打印限制
					int x = zxsjStr.length - srcs;
					for (int i = 0; i < zxsjStr.length; i++) {
						Map<String, Object> o = new HashMap<String, Object>(16);

						if (zxsjStr[i].length() == 2) {
							zxsjStr[i] = zxsjStr[i] + ":00";
						} else if (zxsjStr[i].length() == 1) {
							zxsjStr[i] = "0" + zxsjStr[i] + ":00";
						}
						Date zxDate = sdf.parse(nowYear + "-" + nowMouth + "-"
								+ nowDay + " " + zxsjStr[i] + ":00");

						if (ksYear == nowYear && ksMonth == nowMouth
								&& ksDay == nowDay) {// 如果开嘱时间与今天是同一天
							// 则判断今天医嘱从什么时间开始打印
							if (j < x) {
								j++;
								continue;
							}
						}

						if (map.get("TZSJ") != null) {
                            Timestamp tzsj = DateUtil.parse(map.get("TZSJ") + "").toTimestamp();
                            Calendar tzCal = Calendar.getInstance();
                            tzCal.setTime(tzsj);
                            int tzMonth = tzCal.get(Calendar.MONTH) + 1;
                            int tzYear = tzCal.get(Calendar.YEAR);
                            int tzDay = tzCal.get(Calendar.DATE);
                            //判断停嘱日期和当前日期
                            int d = BUHISUtil.dateCompare(new Date(tzsj.getTime()), new Date());
                            if (d < 0) {
                                continue;
                            }
                            if (tzYear == nowYear && tzMonth == nowMouth && tzDay == nowDay) {
                                //如果停嘱时间与今天是同一天
                                //则判断今天医嘱从什么时间开始打印
                                if (map.get("LSYZ").toString().equals("1")) {

                                } else {
                                    if (tzsj.getTime() - zxDate.getTime() <= 0) {
										continue;
									}
								}
							}
						}

						o.put("Date", nowYear + "." + nowMouth + "." + nowDay);// 日期
						o.put("Time", kpyysj);// 时间
						o.put("User", username);
						o.put("BRXM", map.get("BRXM"));
						o.put("BRCH", map.get("BRCH"));
						if("1".equals(map.get("BRXB") + "")){
							o.put("BRXB", "男");
						}else{
							o.put("BRXB", "女");
						}
						o.put("PCMC", iReportExportFileSer.conVerDataSource(yzList));
						resList.add(o);
						if(dybz == 1){
							saveYzkpdyjl(yzList, DateUtil.date(), kpyysj);
						}
					}
				}
		    }

//						if(dybz == 1){//在取消打印时，要把输液单的数据删除.待开发
//							SimpleDateFormat syrq_sdf = new SimpleDateFormat(
//									"yyyy-MM-dd HH:mm:ss");
//							//ZXSJ[i]
//							Long SYDH= 0l;
//							Map<String, Object> SYDMap = new HashMap<String, Object>();
//							SYDMap.put("ZYH",  yzList.get(0).get("ZYH"));//住院号
//							SYDMap.put("BRBQ", yzList.get(0).get("BRBQ"));//not-null="1"病人病区
//							SYDMap.put("BRCH", yzList.get(0).get("BRCH"));//not-null="1"病人床号
//							SYDMap.put("TMBH", "  ");//not-null="1"条码编号
//							SYDMap.put("YPYF", yzList.get(0).get("YPYF"));//药品用法111111
//							SYDMap.put("SYPC", yzList.get(0).get("SYPC"));//使用频次11111111111
//							SYDMap.put("YZZH", yzList.get(0).get("YZZH"));//not-null="1"医嘱组号
//							//SYDMap.put("SYRQ", kpyysj);//输液日期
//							SYDMap.put("SYRQ", syrq_sdf.parse(nowYear + "-" + nowMouth + "-"+ nowDay + " 00:00:00 "));//输液日期
//							SYDMap.put("SYSJ", zxDate);//not-null="1"输液时间
//							SYDMap.put("SJBH", null);//时间编号
//							SYDMap.put("SJMC",  yzList.get(0).get("SYPC"));//时间名称
//							SYDMap.put("SYSD", 0);//not-null="1"输液时点
//							SYDMap.put("PYBZ", 0);//not-null="1"配液标志
//							SYDMap.put("PYSJ", null);//配液时间
//							SYDMap.put("PYGH", null);//配液工号
//							SYDMap.put("PYLY", 1);//not-null="1"配液来源
//							SYDMap.put("LYBS", "  ");//来源标识-0.系统内部:同输液单号1.静配中心:静配中心的输液单Key值 
//							SYDMap.put("DYCS", 0);//not-null="1"打印次数
//							SYDMap.put("DYSJ", null);//打印时间
//							SYDMap.put("DYGH", null);//打印工号
//							SYDMap.put("SYZT", 0);//输液状态
//							SYDMap.put("KSSJ", null);//开始时间
//							SYDMap.put("KSGH", null);//开始工号
//							SYDMap.put("JSSJ", null);//结束时间
//							SYDMap.put("JSGH", null);//结束工号
//							SYDMap.put("ZXDH", null);//执行单号
//							SYDMap.put("SPBZ", null);//首瓶标志
//							SYDMap.put("MPBZ", null);//末瓶标志
//							SYDMap.put("PJDS", null);//平均滴速
//							SYDMap.put("KSHDSJ", null);//开始核对时间
//							SYDMap.put("KSHDGH", null);//开始核对工号
//							SYDMap.put("JSHDSJ", null);//结束核对时间
//							SYDMap.put("JSHDGH", null);//结束核对工号
//							SYDMap.put("JYHDBZ", 0);//加药核对标志
//							SYDMap.put("JYHDGH", null);//加药核对工号
//							SYDMap.put("JYHDSJ", null);//加药核对时间
//							SYDMap.put("BYHDBZ", 0);//摆药核对标志
//							SYDMap.put("BYHDGH", null);//摆药核对工号
//							SYDMap.put("BYHDSJ", null);//摆药核对时间
//							SYDMap.put("JGID", user.getManageUnitId());//机构ID
//							SYDMap.put("ZYHM", yzList.get(0).get("ZYHM"));//住院号码11111111
//							Map<String, Object> keyGen = dao.doSave("create",
//										 "phis.application.war.schemas.ENR_SYD", SYDMap, false);
//								 SYDH = (Long) keyGen.get("SYDH");
//						   dao.doSqlUpdate("UPDATE ENR_SYD SET TMBH = 'AS"+SYDH +"' ,LYBS ="+SYDH+"  WHERE SYDH = "+SYDH, null);	
//						   //ZXSJ[i]
//							for (int v =0 ; v<yzList.size(); v++ ){
//								//if(saveSydList.contains(yzList.get(v).get("JLXH").toString()) == false){   
//									Map<String, Object> SYMXMap = new HashMap<String, Object>();
//									SYMXMap.put("SYDH", SYDH);//
//									SYMXMap.put("YZXH",  yzList.get(v).get("JLXH"));//not-null="1" 
//									SYMXMap.put("YPXH",  yzList.get(v).get("YPXH"));//
//									SYMXMap.put("YZZX",  yzList.get(v).get("YJZX"));// not-null="1" 
//									SYMXMap.put("YCJL",  yzList.get(v).get("YCJL"));//  not-null="1" 
//									SYMXMap.put("JLDW",  yzList.get(v).get("JLDW"));//
//									SYMXMap.put("YCSL",  yzList.get(v).get("YCSL"));// not-null="1" 
//									SYMXMap.put("SLDW",  yzList.get(v).get("SLDW"));//
//									Map<String, Object> keyGenSYMX = dao.doSave("create",
//											 "phis.application.war.schemas.ENR_SYMX", SYMXMap, false);
//							}	
//							//生成二维码
//							String text= "AS"+SYDH+"";
//							int width = 100;   
//						    int height = 100;  
//							Hashtable<EncodeHintType,Object>  hints= new Hashtable();   
//							hints.put(EncodeHintType.CHARACTER_SET, "utf-8"); 
//							hints.put(EncodeHintType.MARGIN,1); 
//							
//							BitMatrix bitMatrix = new MultiFormatWriter().encode(text, BarcodeFormat.QR_CODE,width,height,hints);
//							BufferedImage image  = MatrixToImageWriter.toBufferedImage(bitMatrix);
//							ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
//					        image.flush();
//							outputStream.flush();
//				            ImageIO.write(image, "jpg", outputStream);
//				            String base64Img =Base64.base64Encode(outputStream.toByteArray());
//						    o.put("EWM", "data:image/jpg;base64,"+base64Img);
//						}			
						
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return resList;
	}
	
	
	/**
	 * 长期临时静滴卡打印返回数据集合
	 * @Title: getStillDripCardFields
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @param cisHzyzCardReq
	 * @param @param user    设定文件
	 * @return void    返回类型
	 * @author 龚方舟
	 * @throws
	 */
	public List<Map<String, Object>> getStillDripCardFields(CisHzyzCardReq cisHzyzCardReq, SysUser user){
		DecimalFormat dft = new DecimalFormat("##########.##########");
		List<Map<String, Object>> resList = new ArrayList<Map<String,Object>>();
		Integer yzlb = cisHzyzCardReq.getYzlb();
		Integer dybz = cisHzyzCardReq.getDybz();
		Date dyrq =  setCardDateTime(cisHzyzCardReq.getTypeDate());
		int nowMouth = DateUtil.month(dyrq) + 1;
		int nowYear = DateUtil.year(dyrq);
		int nowDay = DateUtil.dayOfMonth(dyrq);
		// yzlb = 4 长期 yzlb =11 临时 yzlb =12 长期明日
		if(yzlb == 11) {
			cisHzyzCardReq.setYzlb(4);
		}
		List<Map<String, Object>> list = getOralCardList(cisHzyzCardReq, dyrq, user, 1);
		
		Map<String, List<Map<String, Object>>> zhMap = list.stream().
    			collect(Collectors.groupingBy(o -> ObjectToTypes.parseString(o.get("YZZH")), LinkedHashMap::new, Collectors.toList()));
		//取出每个组的医嘱 并分别打印到前台页面
		for (Entry<String, List<Map<String, Object>>> entry : zhMap.entrySet()){
			List<Map<String, Object>> yzList = entry.getValue();
			for(Map<String, Object> yzMap : yzList) {
				yzMap.put("YCJL", dft.format(ObjectToTypes.parseBigDecimal(yzMap.get("YCJL"))));
			}
			Map<String, Object> map = yzList.get(0);
			Date csny = DateUtil.parse(map.get("CSNY") + "");
			// 出生日期
			int borthYear = DateUtil.year(csny);
			int borthMouth = DateUtil.month(csny) + 1;
			int borthDay =  DateUtil.dayOfMonth(csny);
			// 计算年龄
			int age = nowYear - borthYear - 1;
			boolean dateFlag = borthMouth < nowMouth || borthMouth == nowMouth
					&& borthDay <= nowDay;
			if (dateFlag) {
				age++;
			}
			
			
			String[] zxsjStr = ObjectToTypes.parseString(map.get("YZZXSJ")).split("-");
			int srcs = ObjectToTypes.parseInt(map.get("SRCS"));
			Timestamp kssj = DateUtil.parse(map.get("KSSJ") + "").toTimestamp();
			int ksMonth = DateUtil.month(kssj) + 1;
			int ksYear = DateUtil.year(kssj);
			int ksDay = DateUtil.dayOfMonth(kssj);
		
			if (zxsjStr.length != 0) {
				int j = 0;// 用于首日次数的打印限制
				int x = zxsjStr.length - srcs;
				for (int i = 0; i < zxsjStr.length; i++) {
					Map<String, Object> o = new HashMap<String, Object>(16);
					if (zxsjStr[i].length() == 2) {
						zxsjStr[i] = zxsjStr[i] + ":00";
					} else if (zxsjStr[i].length() == 1) {
						zxsjStr[i] = "0" + zxsjStr[i] + ":00";
					}
					
					Date zxDate = DateUtil.parse(nowYear + "-" + nowMouth + "-" + nowDay + " " + zxsjStr[i] + ":00",
							DatePattern.NORM_DATETIME_PATTERN);
					if("0".equals(ObjectToTypes.parseString(map.get("LSYZ")))) {
						if (ksYear == nowYear && ksMonth == nowMouth && ksDay == nowDay) {// 如果开嘱时间与今天是同一天
							// 则判断今天医嘱从什么时间开始打印
							if (j < x) {
								j++;
								continue;
							}
						}
						if (map.get("TZSJ") != null) {
	                        Timestamp tzsj = DateUtil.parse(map.get("TZSJ") + "").toTimestamp();
	                        int tzMonth = DateUtil.month(tzsj) + 1;
	        				int tzYear = DateUtil.year(tzsj);
	        				int tzDay = DateUtil.year(tzsj);
	                        //判断停嘱日期和当前日期
	                        int d = BUHISUtil.dateCompare(new Date(tzsj.getTime()), dyrq);
	                        if (d < 0) {
	                            continue;
	                        }
	                        if (tzYear == nowYear && tzMonth == nowMouth && tzDay == nowDay) {// 如果停嘱时间与今天是同一天
	
	                    		if (tzsj.getTime() - zxDate.getTime() <= 0) {
	                                continue;
								}
	  
							}
						}
					}
					o.put("BRNL", age);
					o.put("BRXM", map.get("BRXM"));
					o.put("BQMC", cisHzyzCardReq.getBqdmName());
					o.put("BRCH", map.get("BRCH"));
					o.put("BRXB", "1".equals(ObjectToTypes.parseString(map.get("BRXB"))) ? "男" : "女");
					o.put("DYRQ", dyrq);
					o.put("TIME", zxsjStr[i]);
					o.put("ZYHM", map.get("ZYHM"));
					o.put("CSRQ", map.get("CSNY"));
					if(yzlb == 3) {
						o.put("TITLE", user.getHospitalName() + "注射贴" + (cisHzyzCardReq.getLsyz() == 0 ? "(长期)" : "(临时)"));
					}else if(yzlb == 4) {
						o.put("TITLE", user.getHospitalName() + "输液贴" + (cisHzyzCardReq.getLsyz() == 0 ? "(长期)" : "(临时)"));
					}else if(yzlb == 99) {
						o.put("TITLE", user.getHospitalName() + (cisHzyzCardReq.getLsyz() == 0 ? "(长期)" : "(临时)"));
					}
					o.put("PCMC", iReportExportFileSer.conVerDataSource(yzList));
					resList.add(o);
					//点击的是打印按钮，才进入这里，如果是生成打印页面的则不进入
					if(dybz == 1){	
						saveYzkpdyjl(yzList, DateUtil.date(), dyrq);
					}
				}
			}
		}

		return resList;
	}

	
	/**
	 * 医嘱分组
	 * @Title: orderGrouping
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @param list
	 * @param @param zhList
	 * @param @return    设定文件
	 * @return Map<String,List<Map<String,Object>>>    返回类型
	 * @author 龚方舟
	 * @throws
	 */
	public Map<String, List<Map<String, Object>>> orderGrouping(List<Map<String, Object>> list, List<String> zhList,
			Date kpyysj) {
		int nowYear = DateUtil.year(kpyysj);
		int nowMouth = DateUtil.month(kpyysj) + 1;
		int nowDay =  DateUtil.dayOfMonth(kpyysj);
		DecimalFormat dft = new DecimalFormat("##########.##########");
		Map<String, List<Map<String, Object>>> zhMap = new HashMap<String, List<Map<String, Object>>>(16);
		List<Map<String, Object>> newList = new ArrayList<Map<String,Object>>();
		// 用来判断有多少组医嘱
		for (int i = 0; i < list.size(); i++) {		
			Map<String, Object> map = list.get(i);
			String[] yzzxsjStr = ObjectToTypes.parseString(map.get("YZZXSJ")).split("-");
			for(String s : yzzxsjStr) {
				Map<String, Object> newMap = new HashMap<String, Object>(16);
				map.put("YZZXSJ", s);
				newMap.putAll(map);
				newList.add(newMap);
			}
			
		}
		for(int i = 0; i < newList.size(); i++) {
			//频次
			Map<String, Object> map = newList.get(i);
			String yzzxsj = ObjectToTypes.parseString(map.get("YZZXSJ"));
			String zyhm = ObjectToTypes.parseString(map.get("ZYHM"));
			zhMap.put(yzzxsj + zyhm, new ArrayList<Map<String,Object>>());
			if(!zhList.contains(yzzxsj + zyhm)){
				zhList.add(yzzxsj + zyhm);
			}
		}

		// 将同组的数据分别放到各自所属集合中
		for (int j = 0; j < newList.size(); j++) {
			Map<String, Object> map = newList.get(j);
			String yzzxsj = ObjectToTypes.parseString(map.get("YZZXSJ"));
			int srcs = ObjectToTypes.parseInt(map.get("SRCS"));
			map.put("YCJL", dft.format(ObjectToTypes.parseBigDecimal(map.get("YCJL"))));
			Date zxDate = DateUtil.parse(nowYear + "-" + nowMouth + "-" + nowDay + " " + yzzxsj + ":00",
					DatePattern.NORM_DATETIME_PATTERN);
			Timestamp kssj  = DateUtil.parse(map.get("KSSJ") + "").toTimestamp();
			int ksYear = DateUtil.year(kssj);
			int ksMonth = DateUtil.month(kssj) + 1;
			int ksDay =  DateUtil.dayOfMonth(kssj);
			if("0".equals(ObjectToTypes.parseString(map.get("LSYZ")))){
				if (ksYear == nowYear && ksMonth == nowMouth && ksDay == nowDay) {
					//如果开嘱时间与今天是同一天,则判断今天医嘱从什么时间开始打印
					if(srcs == 0){
						continue;
					}
				}
				if (map.get("TZSJ") != null) {
	                Timestamp tzsj = DateUtil.parse(map.get("TZSJ") + "").toTimestamp();
	                int tzMonth = DateUtil.month(tzsj) + 1;
					int tzYear = DateUtil.year(tzsj);
					int tzDay = DateUtil.dayOfMonth(tzsj);
	                //判断停嘱日期和当前日期
	                int d = BUHISUtil.dateCompare(new Date(tzsj.getTime()), kpyysj);
	                if (d < 0) {
	                    continue;
	                }
	                if (tzYear == nowYear && tzMonth == nowMouth && tzDay == nowDay) {// 如果停嘱时间与今天是同一天
	                    // 则判断今天医嘱从什么时间开始打印
                		if (tzsj.getTime() - zxDate.getTime() <= 0) {
                            continue;
						}
					}
				}
			}
			zhMap.get(ObjectToTypes.parseString(map.get("YZZXSJ")) + 
					ObjectToTypes.parseString(map.get("ZYHM"))).add(map);
		}
		return zhMap;
	}
	
	
	/**
	 * 保存口服卡信息
	 * @Title: saveKfInfo
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @param yzList
	 * @param @param nowYear
	 * @param @param nowMouth
	 * @param @param nowDay
	 * @param @param user
	 * @param @param zxDate    设定文件
	 * @return void    返回类型
	 * @author 龚方舟
	 * @throws
	 */
	public void saveKfInfo(List<Map<String, Object>> yzList, int nowYear, int nowMouth, 
			int nowDay, SysUser user, Date zxDate) {
		Integer kfdh = redisFactory.getTableKey(TableName.DB_NAME, TableName.CIS_KFD);
		CisKfd cisKfd = new CisKfd();
		cisKfd.setKfdh(kfdh);
		cisKfd.setZyh(ObjectToTypes.parseInt(yzList.get(0).get("ZYH")));
		cisKfd.setBrbq(ObjectToTypes.parseInt(yzList.get(0).get("BRBQ")));
		cisKfd.setBrch(ObjectToTypes.parseString(yzList.get(0).get("BRCH")));
		cisKfd.setYpyf(ObjectToTypes.parseInt(yzList.get(0).get("YPYF")));
		cisKfd.setKfrq(DateUtil.parse(nowYear + "-" + nowMouth + "-"+ nowDay + " 00:00:00 ", 
				DatePattern.NORM_DATETIME_PATTERN).toTimestamp());
		cisKfd.setKfsj(DateUtil.date(zxDate).toTimestamp());
		cisKfd.setKfsd(0);
		cisKfd.setByly(1);
		cisKfd.setLybs(ObjectToTypes.parseString(kfdh));
		cisKfd.setJgid(user.getHospitalId());
		cisKfd.setZyhm(ObjectToTypes.parseString(yzList.get(0).get("ZYHM")));
		cisKfdDao.insert(cisKfd);
		
		CisKpkfbz cisKpkfbz = new CisKpkfbz();
		Integer kfmx = redisFactory.getTableKey(TableName.DB_NAME, TableName.CIS_KPKFBZ);
		cisKpkfbz.setKfmxid(kfmx);
		cisKpkfbz.setKfdh(kfdh);
		cisKpkfbz.setTmbh("KS" + kfdh);
		cisKpkfbz.setZfbz(0);
		cisKpkfbz.setLybs("1");
		cisKpkfbz.setKfzt(0);
		cisKpkfbz.setDycs(0);
		cisKpkfbzDao.insert(cisKpkfbz);
       
		for (int v =0 ; v < yzList.size(); v++ ){   
			CisKpbzmx cisKpbzmx = new CisKpbzmx();
			cisKpbzmx.setBzmxid(redisFactory.getTableKey(TableName.DB_NAME, TableName.CIS_KPBZMX));
			cisKpbzmx.setKfmx(kfmx);
			cisKpbzmx.setKfdh(kfdh);
			cisKpbzmx.setYzxh(ObjectToTypes.parseInt(yzList.get(v).get("JLXH")));
			cisKpbzmx.setYpxh(ObjectToTypes.parseInt(yzList.get(v).get("YPXH")));
			cisKpbzmx.setBzjl(ObjectToTypes.parseBigDecimal(yzList.get(v).get("YCJL")));
			cisKpbzmx.setJldw(ObjectToTypes.parseString(yzList.get(v).get("JLDW")));
			cisKpbzmx.setBzsl(ObjectToTypes.parseBigDecimal(yzList.get(v).get("YCSL")));
			cisKpbzmx.setSldw(ObjectToTypes.parseString(yzList.get(v).get("SLDW")));
			cisKpbzmxDao.insert(cisKpbzmx);
		}
	}

	/**
	 * 保存医嘱卡片打印记录
	 * @Title: saveYzkpdyjl
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @param yzList
	 * @param @param kpdysj
	 * @param @param kpyysj    设定文件
	 * @return void    返回类型
	 * @author 龚方舟
	 * @throws
	 */
	public void saveYzkpdyjl(List<Map<String, Object>> yzList, Date kpdysj, Date kpyysj) {
		List<String> dyList = new ArrayList<String>();
		for (int v = 0 ; v < yzList.size(); v++ ){
			if(dyList.contains(yzList.get(v).get("JLXH").toString()) == false){
				CisYzkpdyjl cisYzkpdyjl = new CisYzkpdyjl();
				cisYzkpdyjl.setZyh(ObjectToTypes.parseInt(yzList.get(v).get("ZYH")));
				cisYzkpdyjl.setYzxh(ObjectToTypes.parseInt(yzList.get(v).get("JLXH")));
				cisYzkpdyjl.setYzzh(ObjectToTypes.parseInt(yzList.get(v).get("YZZH")));
				cisYzkpdyjl.setDybz(1);
				cisYzkpdyjl.setId(redisFactory.getTableKey(TableName.DB_NAME, TableName.CIS_YZKPDYJL));
				cisYzkpdyjl.setDysj(DateUtil.date(kpdysj).toTimestamp());
				cisYzkpdyjl.setYysj(DateUtil.date(kpyysj).toTimestamp());
				cisYzkpdyjlDao.insert(cisYzkpdyjl);
				dyList.add(yzList.get(v).get("JLXH").toString());
			}
		}
	}
	
	/**
	 * 取消打印
	 * @Title: doCancelKpdy
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @param list    设定文件
	 * @return void    返回类型
	 * @author 龚方舟
	 * @throws
	 */
	public void doCancelKpdy(List<CisHzyzCardCancelReq> list){
		for(CisHzyzCardCancelReq cisHzyzCardCancelReq : list) {
			cisYzkpdyjlDao.cancelKpdy(cisHzyzCardCancelReq.getYzzh(), cisHzyzCardCancelReq.getYysj());
		}
	}


	/**
	 * 给药记录单参数集合
	 * @Title: getAdministrationRecordFields
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @param cisHzyzCardReq
	 * @param @param user
	 * @param @return    设定文件
	 * @return List<Map<String,Object>>    返回类型
	 * @author 龚方舟
	 * @throws
	 */
	public List<Map<String, Object>> getAdministrationRecordFields(CisHzyzCardReq cisHzyzCardReq, SysUser user) {
		DecimalFormat df = new DecimalFormat("##########.##########");
		Date zxtime = setCardDateTime(cisHzyzCardReq.getTypeDate());
		List<Map<String, Object>> list = imHzryDao.queryAdministrationPatientInfo(user.getHospitalId(), cisHzyzCardReq.getBqdm(),
				cisHzyzCardReq.getZyhList(), cisHzyzCardReq.getLsyz());
		for(Map<String, Object> map : list) {
			map.put("title", cisHzyzCardReq.getLsyz()!= null && cisHzyzCardReq.getLsyz() == 0 ? "给药记录单(长期)" : "给药记录单(临时)");
			map.put("zxtime", DateUtil.format(zxtime, DatePattern.NORM_DATE_PATTERN));
			List<Map<String, Object>> chlidList = cisHzyzDao.queryYpyfByZyh(ObjectToTypes.parseInt(map.get("zyh")), 
					cisHzyzCardReq.getLsyz(), user.getHospitalId());
			map.put("child", iReportExportFileSer.conVerDataSource(chlidList));
			if(CollectionUtils.isNotEmpty(chlidList)) {
				for(Map<String, Object> chlidMap : chlidList) {
					List<Map<String, Object>> ypList = cisHzyzDao.queryAdministrationRecord(ObjectToTypes.parseInt(map.get("zyh")), cisHzyzCardReq.getLsyz(), 
							ObjectToTypes.parseInt(chlidMap.get("ypyf")), user.getHospitalId());
					List<Map<String, Object>> newList = new ArrayList<Map<String,Object>>();
					if(CollectionUtils.isNotEmpty(ypList)) {
						Map<String, Object> jlMap = new HashMap<String, Object>();
						for(Map<String, Object> resMap : ypList) {
							if(jlMap.containsKey(ObjectToTypes.parseString(resMap.get("yzzh")))) {
								continue;
							}
							jlMap.put(ObjectToTypes.parseString(resMap.get("yzzh")), resMap);
							String[] strArray = ObjectToTypes.parseString(resMap.get("yzzxsj")).split("-");
							Integer yzzh = ObjectToTypes.parseInt(resMap.get("yzzh"));
							for(String str : strArray) {
								for(int i = 0; i < ypList.size(); i++) {
									Map<String, Object> newMap = new HashMap<String, Object>(16);
									Map<String, Object> ypMap = ypList.get(i);
									ypMap.put("jldw", df.format(ObjectToTypes.parseBigDecimal(ypMap.get("ycjl"))) + 
											ypMap.get("yfdw"));
									if(yzzh == ObjectToTypes.parseInt(ypMap.get("yzzh"))) {
										ypMap.put("yzzxsj", str);
										ypMap.put("zh", " ");
										//实现相同日期只显示第一条和最后一条,同组加符号		
										if(i != 0){
											if(ObjectToTypes.parseInt(ypMap.get("yzzh")) == ObjectToTypes.parseInt(ypList.get(i-1).get("yzzh"))){
												if((i+1) < ypList.size()){
													if(ObjectToTypes.parseInt(ypMap.get("yzzh")) == ObjectToTypes.parseInt(ypList.get(i+1).get("yzzh"))){
														ypMap.put("zh", "┃");
													}else{
														ypMap.put("zh", "┛");
													}
												}else{
													ypMap.put("zh", "┛");
												}
											}else{
												if((i+1) < ypList.size()){
													if(ObjectToTypes.parseInt(ypMap.get("yzzh")) == ObjectToTypes.parseInt(ypList.get(i+1).get("yzzh"))){
														ypMap.put("zh", "┓");
													}
												}
											}
										}
										if(i == 0){
											if((i+1) < ypList.size()){
												if(ObjectToTypes.parseInt(ypMap.get("yzzh")) == ObjectToTypes.parseInt(ypList.get(i+1).get("yzzh"))){
													ypMap.put("zh", "┓");
												}
											}
										}
										newMap.putAll(ypMap);
										newList.add(newMap);
									}
								}
							}
						}
					}	
					chlidMap.put("ypyf", iReportExportFileSer.conVerDataSource(newList));
				}
			}
		}
		
		return list;
	}

	/**
	 * 饮食单参数
	 * @Title: getDietListParameters
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @param cisHzyzCardReq
	 * @param @param user
	 * @param @return    设定文件
	 * @return Map<String,Object>    返回类型
	 * @author 龚方舟
	 * @throws
	 */
	public Map<String, Object> getDietListParameters(CisHzyzCardReq cisHzyzCardReq, SysUser user) {
		Map<String, Object> responseMap = new HashMap<String, Object>(16);
		String djmc = cisHzyzCardReq.getLsyz() == 0 ? "饮食单(长期)" : "饮食单(临时)";
		responseMap.put("title", user.getHospitalName() + djmc);
		responseMap.put("bqmc", cisHzyzCardReq.getBqdmName());
		Date dyrq = setCardDateTime(cisHzyzCardReq.getTypeDate());
		responseMap.put("dyrq", DateUtil.format(dyrq, DatePattern.NORM_DATETIME_PATTERN));
		return responseMap;
	}

	/**
	 * 汇总治疗单
	 * @Title: getSummaryTreatmentParameters
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @param cisHzyzCardReq
	 * @param @param user
	 * @param @return    设定文件
	 * @return Map<String,Object>    返回类型
	 * @author 龚方舟
	 * @throws
	 */
	public Map<String, Object> getSummaryTreatmentParameters(CisHzyzCardReq cisHzyzCardReq, SysUser user) {
		Map<String, Object> responseMap = new HashMap<String, Object>(16);
		String djmc = cisHzyzCardReq.getLsyz() == 0 ? "长期" : "临时";
		responseMap.put("title", user.getHospitalName() + djmc + "医嘱汇总治疗单");
		responseMap.put("bqmc", cisHzyzCardReq.getBqdmName());
		responseMap.put("ksmc", cisHzyzCardReq.getKsdmName());
		Date zxrq = setCardDateTime(cisHzyzCardReq.getTypeDate());
		responseMap.put("zxrq", DateUtil.format(zxrq, DatePattern.NORM_DATE_PATTERN));
		responseMap.put("SUBREPORT_DIR", this.getClass().getClassLoader().getResource("jrxml/"));
		return responseMap;
	}
	
	/**
	 * @throws Exception 
	 * @throws Exception 
	 * 汇总治疗单返回参数
	 * @Title: getSummaryTreatmentFields
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @param cisHzyzCardReq
	 * @param @param user
	 * @param @return    设定文件
	 * @return List<Map<String,Object>>    返回类型
	 * @author 龚方舟
	 * @throws
	 */
	public List<Map<String, Object>> getSummaryTreatmentFields(CisHzyzCardReq cisHzyzCardReq, SysUser user) throws Exception  {
		List<Map<String, Object>> respList = new ArrayList<Map<String,Object>>();
		List<Integer> list = cisHzyzCardReq.getZyhList();
		Integer jgid = user.getHospitalId(); 
		SysXtcs sysXtcs = sysXtcsCacheSer.getByJGIdAndCsmc(jgid, SysXtcsCsmcCts.FHYZHJF);
		String fhbz = sysXtcs == null ? "0" : sysXtcs.getCsz();
		DecimalFormat df = new DecimalFormat("##########.##########");
		Date zxrq = setCardDateTime(cisHzyzCardReq.getTypeDate());
		for(Integer zyh : list) {
			List<Map<String, Object>> zzList = new ArrayList<Map<String,Object>>();
			Map<String, Object> responseMap = new HashMap<String, Object>(16);
			ImHzry hzry = imHzryDao.getById(zyh);
			Map<String, Object> parameters = new HashMap<String, Object>(16);
			parameters.put("zyh", zyh);
			parameters.put("bqdm", cisHzyzCardReq.getBqdm());// 当前病区
			parameters.put("jgid", jgid);// 机构ID
			parameters.put("fhbz", fhbz);// 系统配置参数中 符合标识
			parameters.put("lsyz", cisHzyzCardReq.getLsyz());
			parameters.put("yzlb", 2);
			//口服
			List<Map<String, Object>> kfList = cisHzyzDao.queryOralCardZxInfo(parameters);
			//输液
			parameters.remove("yzlb");
			parameters.put("xmlb", 1);
			List<Map<String, Object>> syList = cisHzyzDao.queryOralCardZxInfo(parameters);
			if(CollectionUtils.isNotEmpty(syList)) {
				for(int j = 0; j < syList.size(); j ++) {
					Map<String, Object> syMap = syList.get(j);
					String yzmc = ObjectToTypes.parseString(syMap.get("YZMC"));
					if(j != 0){
						if(ObjectToTypes.parseInt(syMap.get("YZZH")) == ObjectToTypes.parseInt(syList.get(j-1).get("YZZH"))){
							if((j+1) < syList.size()){
								if(ObjectToTypes.parseInt(syMap.get("YZZH")) == ObjectToTypes.parseInt(syList.get(j+1).get("YZZH"))){
									yzmc = "┃" + yzmc;
								}else{
									yzmc = "┛" + yzmc;
								}
							}else{
								yzmc = "┛" +  yzmc;
							}
						}else{
							if((j+1) < syList.size()){
								if(ObjectToTypes.parseInt(syMap.get("YZZH")) == ObjectToTypes.parseInt(syList.get(j+1).get("YZZH"))){
									yzmc = "┓" +  yzmc;
								}
							}
						}
					}
					if(j == 0){
						if((j+1) < syList.size()){
							if(ObjectToTypes.parseInt(syMap.get("YZZH")) == ObjectToTypes.parseInt(syList.get(j+1).get("YZZH"))){
								yzmc = "┓" +  yzmc;
							}
						}
					}
					syMap.put("YZMC", yzmc);
				}
			}
			parameters.remove("xmlb");
			//注射
			parameters.put("yzlb", 3);
			List<Map<String, Object>> zsList = cisHzyzDao.queryOralCardZxInfo(parameters);
			//治疗
			parameters.put("yzlb", 16);
			sysXtcs = sysXtcsCacheSer.getByJGIdAndCsmc(jgid, SysXtcsCsmcCts.ZXKSDM);
			List<String> zxksList = Arrays.asList(sysXtcs.getCsz().split(","));
			parameters.put("zxksList", zxksList);
			List<Map<String, Object>> zlList = cisHzyzDao.queryTreatmentZxInfo(parameters);
			//过敏
			SkinPsjlReq skinPsjlReq = new SkinPsjlReq();
			skinPsjlReq.setJgid(jgid);
			skinPsjlReq.setBrid(hzry.getBrid());
			List<SkinPsjlResp> gmList = skinPsjlDao.getAllergicDrugsByEntity(skinPsjlReq);
			//护理
			parameters.remove("zxksList");
			List<Map<String, Object>> hlList = cisHzyzDao.queryCareInfo(parameters);
			//饮食
			List<Map<String, Object>> ysList = cisHzyzDao.queryDietList(jgid, null, 
					cisHzyzCardReq.getLsyz(), zyh);
			List<String> newkfList = new ArrayList<String>();
			List<String> newsyList = new ArrayList<String>();
			List<String> newzsList = new ArrayList<String>();
			List<String> newzlList = new ArrayList<String>();
			List<String> newgmList = new ArrayList<String>();
			List<String> newhlList = new ArrayList<String>();
			List<String> newysList = new ArrayList<String>(); 
			
			//处理换行
			for(Map<String, Object> kfMap : kfList) {
				if(kfMap.get("TZSJ") != null) {
					Date tzsj = DateUtil.parse(kfMap.get("TZSJ") + "", DatePattern.NORM_DATETIME_PATTERN);
					if(zxrq.after(tzsj)) {
						continue;
					}
				}
				String kf = kfMap.get("YZMC") + " " + df.format(ObjectToTypes.parseBigDecimal(kfMap.get("YCJL"))) + 
						kfMap.get("JLDW") + " " + kfMap.get("XMMC") + " " + 
						kfMap.get("SYPC");
				newkfList.addAll(BytesUtil.getListStringBytes(kf, 32));
				newkfList.add("");
			}
			
			for(Map<String, Object> syMap : syList) {
				if(syMap.get("TZSJ") != null) {
					Date tzsj = DateUtil.parse(syMap.get("TZSJ") + "", DatePattern.NORM_DATETIME_PATTERN);
					if(zxrq.after(tzsj)) {
						continue;
					}
				}
				String sy = syMap.get("YZMC") + " " + df.format(ObjectToTypes.parseBigDecimal(syMap.get("YCJL"))) + 
						syMap.get("JLDW") + " " + syMap.get("XMMC") + " " + 
						syMap.get("SYPC");
				newsyList.addAll(BytesUtil.getListStringBytes(sy, 33));
				newsyList.add("");
			}
			
			for(Map<String, Object> zsMap : zsList) {
				if(zsMap.get("TZSJ") != null) {
					Date tzsj = DateUtil.parse(zsMap.get("TZSJ") + "", DatePattern.NORM_DATETIME_PATTERN);
					if(zxrq.after(tzsj)) {
						continue;
					}
				}
				String zs = zsMap.get("YZMC") + " " + df.format(ObjectToTypes.parseBigDecimal(zsMap.get("YCJL"))) + 
						zsMap.get("JLDW") + " " + zsMap.get("XMMC") + " " + 
						zsMap.get("SYPC");
				newzsList.addAll(BytesUtil.getListStringBytes(zs, 34));
				newzsList.add("");
			}
			
			for(Map<String, Object> zlMap : zlList) {
				if(zlMap.get("TZSJ") != null) {
					Date tzsj = DateUtil.parse(zlMap.get("TZSJ") + "", DatePattern.NORM_DATETIME_PATTERN);
					if(zxrq.after(tzsj)) {
						continue;
					}
				}
				String zl = zlMap.get("YZMC") + " " + df.format(ObjectToTypes.parseBigDecimal(zlMap.get("YCSL"))) + 
						(zlMap.get("YFDW") == null? "" : zlMap.get("YFDW")) + " " +
						zlMap.get("SYPC");
				newzlList.addAll(BytesUtil.getListStringBytes(zl, 32));
				newzlList.add("");
			}
			
			for(SkinPsjlResp gmResp : gmList) {
				String gm = gmResp.getYpmc();
				newgmList.addAll(BytesUtil.getListStringBytes(gm, 16));
				newgmList.add("");
			}
			
			for(Map<String, Object> hlMap : hlList) {
				if(hlMap.get("tzsj") != null) {
					Date tzsj = DateUtil.parse(hlMap.get("tzsj") + "", DatePattern.NORM_DATETIME_PATTERN);
					if(zxrq.after(tzsj)) {
						continue;
					}
				}
				String hl = ObjectToTypes.parseString(hlMap.get("yzmc"));
				newhlList.addAll(BytesUtil.getListStringBytes(hl, 14));
				newhlList.add("");
			}
			
			for(Map<String, Object> ysMap : ysList) {
				if(ysMap.get("tzsj") != null) {
					Date tzsj = DateUtil.parse(ysMap.get("tzsj") + "", DatePattern.NORM_DATETIME_PATTERN);
					if(zxrq.after(tzsj)) {
						continue;
					}
				}
				String ys = ObjectToTypes.parseString(ysMap.get("yzmc"));
				newysList.addAll(BytesUtil.getListStringBytes(ys, 14));
				newysList.add("");
			}

			int biggest = Math.max(newkfList.size(), newsyList.size());
			biggest = Math.max(biggest, newzsList.size());
			biggest = Math.max(biggest, newzlList.size());
			biggest = Math.max(biggest, newgmList.size());
			biggest = Math.max(biggest, newhlList.size());
			biggest = Math.max(biggest, newysList.size());
			
			for(int i = 0; i < biggest; i++) {
				Map<String, Object> map = new HashMap<String, Object>(16);
				if(i == 0) {
					map.put("brch", hzry.getBrch());
					map.put("zyhm", hzry.getZyhm());
					map.put("xmsr", hzry.getBrxm());
				}
				if(i == 1) {
					map.put("xmsr", DateUtil.format(hzry.getCsny(), DatePattern.PURE_DATE_PATTERN));
				}
				
				if(newkfList.size() > i) {
					map.put("kflx", newkfList.get(i).trim());
				}
				
				if(newsyList.size() > i) {
					map.put("sylx", newsyList.get(i).trim());
				}
				
				if(newzsList.size() > i) {
					map.put("zslx", newzsList.get(i).trim());
				}
				
				if(newzlList.size() > i) {
					map.put("zllx", newzlList.get(i).trim());
				}
				
				if(newgmList.size() > i) {
					map.put("gmlx", newgmList.get(i).trim());
				}
				
				if(newhlList.size() > i) {
					map.put("hllx", newhlList.get(i).trim());
				}
				
				if(newysList.size() > i) {
					map.put("yslx", newysList.get(i).trim());
				}
				zzList.add(map);
				responseMap.put("child", iReportExportFileSer.conVerDataSource(zzList));
			}
			if(responseMap.get("child") != null) {	
				respList.add(responseMap);
			}
		}
		
		return respList;
	}
	
	
	
	/**
	 * 补液巡回记录单数据集合返回
	 * @Title: getRehydrationPrintFields
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @param cisHzyzCardReq
	 * @param @param user
	 * @param @return    设定文件
	 * @return List<Map<String,Object>>    返回类型
	 * @author 龚方舟
	 * @throws
	 */
	public List<Map<String, Object>> getRehydrationPrintFields(CisHzyzCardReq cisHzyzCardReq, SysUser user){
		DecimalFormat df = new DecimalFormat("##########.##########");
		List<Map<String, Object>> resList = new ArrayList<Map<String,Object>>();
		Date kpyysj = setCardDateTime(cisHzyzCardReq.getTypeDate());
		List<Map<String, Object>> list = getOralCardZxList(cisHzyzCardReq, user, kpyysj);

		Map<String, List<Map<String, Object>>> zhMap = new HashMap<String, List<Map<String, Object>>>(16);
		List<String> zhList = list.stream().map(o -> ObjectToTypes.parseString(o.get("ZYHM"))).distinct().collect(Collectors.toList());
		for(String str : zhList) {
			zhMap.put(str, new ArrayList<Map<String,Object>>());
		}
		Map<String, Object> transfMap = new HashMap<String, Object>(16);
		for(Map<String, Object> resMap : list) {
			if(transfMap.containsKey(ObjectToTypes.parseString(resMap.get("YZZH")))) {
				continue;
			}
			transfMap.put(ObjectToTypes.parseString(resMap.get("YZZH")), resMap);
			String[] strArray = ObjectToTypes.parseString(resMap.get("YZZXSJ")).split("-");
			Integer yzzh = ObjectToTypes.parseInt(resMap.get("YZZH"));
			for(String str : strArray) {
				for(int j = 0; j < list.size(); j++) {
					Map<String, Object> newMap = new HashMap<String, Object>(16);
					Map<String, Object> map = list.get(j);
					String zyhm = ObjectToTypes.parseString(map.get("ZYHM"));
					map.put("YCJL", df.format(ObjectToTypes.parseBigDecimal(map.get("YCJL"))));
					if("0".equals(ObjectToTypes.parseString(map.get("LSYZ")))) {
						if (map.get("TZSJ") != null) {
							Timestamp tzsj = DateUtil.parse(map.get("TZSJ") + "").toTimestamp();
							// 判断停嘱日期和当前日期
							int d = BUHISUtil.dateCompare(new Date(tzsj.getTime()), kpyysj);
							if (d < 0) {
								continue;
							}
						}
					}
					if(yzzh == ObjectToTypes.parseInt(map.get("YZZH"))) {
						map.put("ZH", " ");
						map.put("YZZXSJ", str);	
						if(j != 0){
							if(ObjectToTypes.parseInt(map.get("YZZH")) == ObjectToTypes.parseInt(list.get(j-1).get("YZZH"))){
								if((j+1) <list.size()){
									if(ObjectToTypes.parseInt(map.get("YZZH")) == ObjectToTypes.parseInt(list.get(j+1).get("YZZH"))){
										map.put("ZH", "┃");
									}else{
										map.put("ZH", "┛");
									}
								}else{
									map.put("ZH", "┛");
								}
							}else{
								if((j+1) < list.size()){
									if(ObjectToTypes.parseInt(map.get("YZZH")) == ObjectToTypes.parseInt(list.get(j+1).get("YZZH"))){
										map.put("ZH", "┓");
									}
								}
							}
						}
						if(j == 0){
							if((j+1) < list.size()){
								if(ObjectToTypes.parseInt(map.get("YZZH")) == ObjectToTypes.parseInt(list.get(j+1).get("YZZH"))){
									map.put("ZH", "┓");
								}
							}
						}
						newMap.putAll(map);
						zhMap.get(zyhm).add(newMap);
					}
				}
			}
		}
		// 取出每个组的医嘱 并分别打印到前台页面
		for(int k = 0 ; k < zhList.size() ; k ++ ){
			List<Map<String, Object>> yzList = zhMap.get(zhList.get(k));
			if(!yzList.isEmpty()) {
				Map<String, Object> map = yzList.get(0);
				Map<String, Object> o = new HashMap<String, Object>(16);
				o.put("TITLE", "补液巡回记录单");
				o.put("BRCH", map.get("BRCH"));
				o.put("BRXM", map.get("BRXM"));
				o.put("CSRQ", map.get("CSNY"));
				o.put("ZYHM", map.get("ZYHM"));
				o.put("KSMC", map.get("KSMC"));
				o.put("BQMC", map.get("BQMC"));
				o.put("DYRQ", DateUtil.format(kpyysj, "MM.dd"));
				o.put("XB", map.get("XB"));
				o.put("RYNL", map.get("RYNL"));
				o.put("CHILD", iReportExportFileSer.conVerDataSource(yzList));
				resList.add(o);
			}
		}
		return resList;
	}
	
	/**
	 * 设置卡片时间
	 * @Title: setCardDateTime
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @param typeDate
	 * @param @return    设定文件
	 * @return Date    返回类型
	 * @author 龚方舟
	 * @throws
	 */
	public Date setCardDateTime(Integer typeDate) {
		Date date = DateUtil.date();
		if(typeDate == 5){
			date = DateUtil.tomorrow();
		}else if(typeDate == 6){
			date = DateUtil.offset(date, DateField.DAY_OF_MONTH, 2);
		}
		return date;
	}
	
}
