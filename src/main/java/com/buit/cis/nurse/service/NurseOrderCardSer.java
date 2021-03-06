package com.buit.cis.nurse.service;

import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Comparator;
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
import com.buit.cis.nurse.request.CisHzyzCardReTypeReq;
import com.buit.cis.nurse.request.CisHzyzCardReq;
import com.buit.cis.nurse.response.CisHzyzCancelPrintResp;
import com.buit.cis.nurse.request.CisHzyzMenuReq;
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
 * <br>????????????????????????
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
     * ?????????????????????????????????????????????
     * @Title: getOralCardFields
     * @Description: TODO(?????????????????????????????????????????????)
     * @param @param cisHzyzCardReq
     * @param @param user
     * @param @return    ????????????
     * @return List<Map<String,Object>>    ????????????
     * @author ?????????
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
		// ???????????????????????? ??????????????????????????????
		for(int k = 0 ; k < zhList.size() ; k ++ ){
			List<Map<String, Object>> yzList = zhMap.get(zhList.get(k));
			if(!yzList.isEmpty()) {
				Map<String, Object> map = yzList.get(0);
				Date csny = DateUtil.parse(map.get("CSNY") + "");
				// ????????????
				int borthYear = DateUtil.year(csny);
				int borthMouth = DateUtil.month(csny) + 1;
				int borthDay =  DateUtil.dayOfMonth(csny);
				// ????????????
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
					o.put("DYRQ", DateUtil.date(kpyysj).toSqlDate());
				}
				o.put("TITLE", user.getHospitalName() + "?????????" + (cisHzyzCardReq.getLsyz() == 0 ? "(??????)" : "(??????)"));
				o.put("BRXB", "1".equals(ObjectToTypes.parseString(map.get("BRXB"))) ? "???" : "???");
				o.put("BRNL", age);// ????????????
				o.put("BQMC", cisHzyzCardReq.getBqdmName());// ????????????
				o.put("TIME", yzzxsj);// ??????????????????
				o.put("BRXM", map.get("BRXM"));
				o.put("BRCH", map.get("BRCH"));
				o.put("ZYHM", map.get("ZYHM"));
				o.put("PCMC", iReportExportFileSer.conVerDataSource(yzList));
				if(dybz == 1){
					saveKfInfo(yzList, nowYear, nowMouth, nowDay, user, kpyysj);
					saveYzkpdyjl(yzList, DateUtil.date(), kpyysj, cisHzyzCardReq.getYzlb(), yzzxsj);
				}
				resList.add(o);
			}
		}
		return resList;
	}
    
    /**
     * ??????????????????????????????
     * @Title: getOralCardList
     * @Description: TODO(?????????????????????????????????????????????)
     * @param @param cisHzyzCardReq
     * @param @param kpyysj
     * @param @param user
     * @param @return
     * @param @throws PrintException    ????????????
     * @return List<Map<String,Object>>    ????????????
     * @author ?????????
     * @throws
     */
    public List<Map<String, Object>> getOralCardList(CisHzyzCardReq cisHzyzCardReq, Date kpyysj,
    		SysUser user, Integer xmlb) {
    	List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
		Integer jgid = user.getHospitalId();
			// ??????????????????
		List<Integer> zyhList = cisHzyzCardReq.getZyhList();
		if (!zyhList.isEmpty()) {
			// ??????????????????????????? ?????????????????? ?????????
			// "FHYZHJF","1","??????????????????????????????????????????FHYZHJF???0:???????????????1:???????????????"
			SysXtcs sysXtcs = sysXtcsCacheSer.getByJGIdAndCsmc(jgid, SysXtcsCsmcCts.FHYZHJF);
			String fhbz = sysXtcs == null ? "0" : sysXtcs.getCsz();

			Map<String, Object> parameters = new HashMap<String, Object>(16);
			parameters.put("bqdm", cisHzyzCardReq.getBqdm());// ????????????
			Integer yzlb = cisHzyzCardReq.getYzlb();
			if(yzlb == 7) {
				yzlb = 2;
			}
			parameters.put("yzlb", yzlb);// ????????????
			parameters.put("jgid", jgid);// ??????ID
			parameters.put("fhbz", ObjectToTypes.parseInt(fhbz));// ????????????????????? ????????????
			parameters.put("yysj", DateUtil.format(kpyysj, DatePattern.NORM_DATE_PATTERN));//=======???????????????????????????========
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
     * ?????????????????????
     * @Title: validOrders
     * @Description: TODO(?????????????????????????????????????????????)
     * @param @param sypc
     * @param @param kssj
     * @param @param nowDate
     * @param @return    ????????????
     * @return int 1:????????? 0:???????????? -1:???????????????
     * @author ?????????
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
				return 1;// ???????????????1,???????????????
			}
			if (rzxzq.length() != zxzq) {
				return -1;// ???????????????????????????????????????????????????
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
					zxbz = ObjectToTypes.parseInt(rzxzq.substring(days, days + 1));// 0???1
				}
			}
			return zxbz;

		} else {
			return -1;
		}
		
	}
	
	
	/**
	 * ??????????????????????????????
	 * @Title: getOralCardParameters
	 * @Description: TODO(?????????????????????????????????????????????)
	 * @param @return    ????????????
	 * @return Map<String,Object>    ????????????
	 * @author ?????????
	 * @throws
	 */
	public Map<String, Object> getOralCardParameters(){
		Map<String, Object> responseMap = new HashMap<String, Object>(16);
		responseMap.put("SUBREPORT_DIR", this.getClass().getClassLoader().getResource("jrxml/"));
		return responseMap;
	}
	
	/**
	 * ????????????????????????????????????????????????????????????????????????
	 * @Title: getOralCardZxdFields
	 * @Description: TODO(?????????????????????????????????????????????)
	 * @param @param cisHzyzCardReq
	 * @param @param user
	 * @param @return    ????????????
	 * @return List<Map<String,Object>>    ????????????
	 * @author ?????????
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

		// ???????????????????????????????????????????????????
		for (int j = 0; j < list.size(); j++) {
			Map<String, Object> map = list.get(j);
			map.put("YCJL", df.format(ObjectToTypes.parseBigDecimal(map.get("YCJL"))));
			if("0".equals(ObjectToTypes.parseString(map.get("LSYZ")))) {
				if (map.get("TZSJ") != null) {
	                Timestamp tzsj = DateUtil.parse(map.get("TZSJ") + "").toTimestamp();
	                //?????????????????????????????????
	                int d = BUHISUtil.dateCompare(new Date(tzsj.getTime()), kpyysj);
	                if (d < 0) {
	                    continue;
	                }
	            }
			}
			
			String zyhm = ObjectToTypes.parseString(map.get("ZYHM"));
			//??????????????????????????????
			Map<String, Object> jlMap = list.get(j);
			map.put("ZH", " ");
			//???????????????????????????????????????????????????,???????????????		
			if(j != 0){
				if(ObjectToTypes.parseInt(jlMap.get("YZZH")) == ObjectToTypes.parseInt(list.get(j-1).get("YZZH"))){
					if((j+1) <list.size()){
						if(ObjectToTypes.parseInt(jlMap.get("YZZH")) == ObjectToTypes.parseInt(list.get(j+1).get("YZZH"))){
							map.put("ZH", "???");
						}else{
							map.put("ZH", "???");
						}
					}else{
						map.put("ZH", "???");
					}
				}else{
					if((j+1) < list.size()){
						if(ObjectToTypes.parseInt(jlMap.get("YZZH")) == ObjectToTypes.parseInt(list.get(j+1).get("YZZH"))){
							map.put("ZH", "???");
						}
					}
				}
			}
			if(j == 0){
				if((j+1) < list.size()){
					if(ObjectToTypes.parseInt(jlMap.get("YZZH")) == ObjectToTypes.parseInt(list.get(j+1).get("YZZH"))){
						map.put("ZH", "???");
					}
				}
			}
			zhMap.get(zyhm).add(map);
		}
		// ???????????????????????? ??????????????????????????????
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
	 * ??????????????????????????????????????????????????????????????????
	 * @Title: getOralCardZxList
	 * @Description: TODO(?????????????????????????????????????????????)
	 * @param @param cisHzyzCardReq
	 * @param @param user
	 * @param @return    ????????????
	 * @return List<Map<String,Object>>    ????????????
	 * @author ?????????
	 * @throws
	 */
	public List<Map<String, Object>> getOralCardZxList(CisHzyzCardReq cisHzyzCardReq, SysUser user,
			Date kpyysj) {
		Integer jgid = user.getHospitalId();
		List<Map<String, Object>> body = new ArrayList<Map<String, Object>>();
		//??????????????????
		//?????????????????????????????????????????????
		List<Integer> zyhList = cisHzyzCardReq.getZyhList();
		if (zyhList.size() > 0 ) {
			Integer yzlb = cisHzyzCardReq.getYzlb();
			// ??????????????????????????? ?????????????????? ?????????
			// "FHYZHJF","1","??????????????????????????????????????????FHYZHJF???0:???????????????1:???????????????"
			SysXtcs sysXtcs = sysXtcsCacheSer.getByJGIdAndCsmc(jgid, SysXtcsCsmcCts.FHYZHJF);
			String fhbz = sysXtcs == null ? "0" : sysXtcs.getCsz();
			
			Map<String, Object> parameters = new HashMap<String, Object>(16); 
			parameters.put("zyhList", zyhList);
			parameters.put("bqdm", cisHzyzCardReq.getBqdm());// ????????????
			parameters.put("jgid", jgid);// ??????ID
			parameters.put("fhbz", fhbz);// ????????????????????? ????????????
			parameters.put("lsyz", cisHzyzCardReq.getLsyz());
			
			//?????????
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
	 * ????????????????????????????????????????????????????????????????????????????????????
	 * @Title: getOralCardZxParameters
	 * @Description: TODO(?????????????????????????????????????????????)
	 * @param @param cisHzyzCardReq
	 * @param @return    ????????????
	 * @return Map<String,Object>    ????????????
	 * @author ?????????
	 * @throws
	 */
	public Map<String, Object> getOralCardZxdParameters(CisHzyzCardReq cisHzyzCardReq, SysUser user) {
		
		Map<String, Object> responseMap = new HashMap<String, Object>(16);
		String yzlb  = ObjectToTypes.parseString(cisHzyzCardReq.getYzlb());
		responseMap.put("TITLE", user.getHospitalName());
		String djmc = "";
		if("2".equals(yzlb)){
			djmc = cisHzyzCardReq.getLsyz() == 0 ? "?????????(??????)" : "?????????(??????)";
		}else if("3".equals(yzlb)){
			djmc = cisHzyzCardReq.getLsyz() == 0 ? "?????????(??????)" : "?????????(??????)";
		}else if("4".equals(yzlb)){
			djmc = cisHzyzCardReq.getLsyz() == 0 ? "?????????(??????)" : "?????????(??????)";
		}else if("16".equals(yzlb)){
			djmc = user.getHospitalName() + (cisHzyzCardReq.getLsyz() == 0 ? "?????????(??????)" : "?????????(??????)" );
		}
		responseMap.put("DJMC", djmc);
		responseMap.put("BQMC", cisHzyzCardReq.getBqdmName());
		Date dyrq = setCardDateTime(cisHzyzCardReq.getTypeDate());
		responseMap.put("DYRQ", DateUtil.format(dyrq, DatePattern.NORM_DATE_PATTERN));
		responseMap.put("SUBREPORT_DIR", this.getClass().getClassLoader().getResource("jrxml/"));
		return responseMap;
	}
	
	
	
	/**
	 * ???????????????????????????????????????
	 * @Title: getInjectionCardFields
	 * @Description: TODO(?????????????????????????????????????????????)
	 * @param @param cisHzyzCardReq
	 * @param @param user    ????????????
	 * @return void    ????????????
	 * @author ?????????
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
			//???????????????????????? ??????????????????????????????
			for (Map.Entry<String, List<Map<String, Object>>> entry : zhMap.entrySet()){
				List<Map<String, Object>> yzList = entry.getValue();
				Map<String, Object> map = yzList.get(0);
				Date csny = (Date) map.get("CSNY");// ????????????
				// ????????????
				Calendar cs = Calendar.getInstance();
				cs.setTime(csny);
				int borthYear = cs.get(Calendar.YEAR);
				int borthMouth = cs.get(Calendar.MONTH) + 1;
				int borthDay = cs.get(Calendar.DATE);
				// ????????????
				Date nowDate = new Date();
				Calendar c = Calendar.getInstance();// ????????????????????????????????????
				c.setTime(nowDate);

				int nowYear = c.get(Calendar.YEAR);
				int nowMouth = c.get(Calendar.MONTH) + 1;
				int nowDay = c.get(Calendar.DATE);
				// ????????????
				int age = nowYear - borthYear - 1;
				boolean dateFlag = borthMouth < nowMouth || borthMouth == nowMouth
						&& borthDay <= nowDay;
				if (dateFlag) {
					age++;
				}
				//========================????????????========================
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
					int j = 0;// ?????????????????????????????????
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
								&& ksDay == nowDay) {// ???????????????????????????????????????
							// ????????????????????????????????????????????????
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
                            //?????????????????????????????????
                            int d = BUHISUtil.dateCompare(new Date(tzsj.getTime()), new Date());
                            if (d < 0) {
                                continue;
                            }
                            if (tzYear == nowYear && tzMonth == nowMouth && tzDay == nowDay) {
                                //???????????????????????????????????????
                                //????????????????????????????????????????????????
                                if (map.get("LSYZ").toString().equals("1")) {

                                } else {
                                    if (tzsj.getTime() - zxDate.getTime() <= 0) {
										continue;
									}
								}
							}
						}

						o.put("Date", nowYear + "." + nowMouth + "." + nowDay);// ??????
						o.put("Time", kpyysj);// ??????
						o.put("User", username);
						o.put("BRXM", map.get("BRXM"));
						o.put("BRCH", map.get("BRCH"));
						if("1".equals(map.get("BRXB") + "")){
							o.put("BRXB", "???");
						}else{
							o.put("BRXB", "???");
						}
						o.put("PCMC", iReportExportFileSer.conVerDataSource(yzList));
						resList.add(o);
						if(dybz == 1){
							saveYzkpdyjl(yzList, DateUtil.date(), kpyysj, cisHzyzCardReq.getYzlb(), zxsjStr[i]);
						}
					}
				}
		    }

//						if(dybz == 1){//???????????????????????????????????????????????????.?????????
//							SimpleDateFormat syrq_sdf = new SimpleDateFormat(
//									"yyyy-MM-dd HH:mm:ss");
//							//ZXSJ[i]
//							Long SYDH= 0l;
//							Map<String, Object> SYDMap = new HashMap<String, Object>();
//							SYDMap.put("ZYH",  yzList.get(0).get("ZYH"));//?????????
//							SYDMap.put("BRBQ", yzList.get(0).get("BRBQ"));//not-null="1"????????????
//							SYDMap.put("BRCH", yzList.get(0).get("BRCH"));//not-null="1"????????????
//							SYDMap.put("TMBH", "  ");//not-null="1"????????????
//							SYDMap.put("YPYF", yzList.get(0).get("YPYF"));//????????????111111
//							SYDMap.put("SYPC", yzList.get(0).get("SYPC"));//????????????11111111111
//							SYDMap.put("YZZH", yzList.get(0).get("YZZH"));//not-null="1"????????????
//							//SYDMap.put("SYRQ", kpyysj);//????????????
//							SYDMap.put("SYRQ", syrq_sdf.parse(nowYear + "-" + nowMouth + "-"+ nowDay + " 00:00:00 "));//????????????
//							SYDMap.put("SYSJ", zxDate);//not-null="1"????????????
//							SYDMap.put("SJBH", null);//????????????
//							SYDMap.put("SJMC",  yzList.get(0).get("SYPC"));//????????????
//							SYDMap.put("SYSD", 0);//not-null="1"????????????
//							SYDMap.put("PYBZ", 0);//not-null="1"????????????
//							SYDMap.put("PYSJ", null);//????????????
//							SYDMap.put("PYGH", null);//????????????
//							SYDMap.put("PYLY", 1);//not-null="1"????????????
//							SYDMap.put("LYBS", "  ");//????????????-0.????????????:???????????????1.????????????:????????????????????????Key??? 
//							SYDMap.put("DYCS", 0);//not-null="1"????????????
//							SYDMap.put("DYSJ", null);//????????????
//							SYDMap.put("DYGH", null);//????????????
//							SYDMap.put("SYZT", 0);//????????????
//							SYDMap.put("KSSJ", null);//????????????
//							SYDMap.put("KSGH", null);//????????????
//							SYDMap.put("JSSJ", null);//????????????
//							SYDMap.put("JSGH", null);//????????????
//							SYDMap.put("ZXDH", null);//????????????
//							SYDMap.put("SPBZ", null);//????????????
//							SYDMap.put("MPBZ", null);//????????????
//							SYDMap.put("PJDS", null);//????????????
//							SYDMap.put("KSHDSJ", null);//??????????????????
//							SYDMap.put("KSHDGH", null);//??????????????????
//							SYDMap.put("JSHDSJ", null);//??????????????????
//							SYDMap.put("JSHDGH", null);//??????????????????
//							SYDMap.put("JYHDBZ", 0);//??????????????????
//							SYDMap.put("JYHDGH", null);//??????????????????
//							SYDMap.put("JYHDSJ", null);//??????????????????
//							SYDMap.put("BYHDBZ", 0);//??????????????????
//							SYDMap.put("BYHDGH", null);//??????????????????
//							SYDMap.put("BYHDSJ", null);//??????????????????
//							SYDMap.put("JGID", user.getManageUnitId());//??????ID
//							SYDMap.put("ZYHM", yzList.get(0).get("ZYHM"));//????????????11111111
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
//							//???????????????
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
	 * ?????????????????????????????????????????????
	 * @Title: getStillDripCardFields
	 * @Description: TODO(?????????????????????????????????????????????)
	 * @param @param cisHzyzCardReq
	 * @param @param user    ????????????
	 * @return void    ????????????
	 * @author ?????????
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
		// yzlb = 4 ?????? yzlb =11 ?????? yzlb =12 ????????????
		if(yzlb == 11) {
			cisHzyzCardReq.setYzlb(4);
		}
		List<Map<String, Object>> list = getOralCardList(cisHzyzCardReq, dyrq, user, 1);
		
		Map<String, List<Map<String, Object>>> zhMap = list.stream().
    			collect(Collectors.groupingBy(o -> ObjectToTypes.parseString(o.get("YZZH")), LinkedHashMap::new, Collectors.toList()));
		//???????????????????????? ??????????????????????????????
		for (Entry<String, List<Map<String, Object>>> entry : zhMap.entrySet()){
			List<Map<String, Object>> yzList = entry.getValue();
			for(Map<String, Object> yzMap : yzList) {
				yzMap.put("YCJL", dft.format(ObjectToTypes.parseBigDecimal(yzMap.get("YCJL"))));
			}
			Map<String, Object> map = yzList.get(0);
			Date csny = DateUtil.parse(map.get("CSNY") + "");
			// ????????????
			int borthYear = DateUtil.year(csny);
			int borthMouth = DateUtil.month(csny) + 1;
			int borthDay =  DateUtil.dayOfMonth(csny);
			// ????????????
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
				int j = 0;// ?????????????????????????????????
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
						if (ksYear == nowYear && ksMonth == nowMouth && ksDay == nowDay) {// ???????????????????????????????????????
							// ????????????????????????????????????????????????
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
	                        //?????????????????????????????????
	                        int d = BUHISUtil.dateCompare(new Date(tzsj.getTime()), dyrq);
	                        if (d < 0) {
	                            continue;
	                        }
	                        if (tzYear == nowYear && tzMonth == nowMouth && tzDay == nowDay) {// ???????????????????????????????????????
	
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
					o.put("BRXB", "1".equals(ObjectToTypes.parseString(map.get("BRXB"))) ? "???" : "???");
					o.put("DYRQ", dyrq);
					o.put("TIME", zxsjStr[i]);
					o.put("ZYHM", map.get("ZYHM"));
					o.put("CSRQ", map.get("CSNY"));
					if(yzlb == 3) {
						o.put("TITLE", user.getHospitalName() + "?????????" + (cisHzyzCardReq.getLsyz() == 0 ? "(??????)" : "(??????)"));
					}else if(yzlb == 4) {
						o.put("TITLE", user.getHospitalName() + "?????????" + (cisHzyzCardReq.getLsyz() == 0 ? "(??????)" : "(??????)"));
					}else if(yzlb == 99) {
						o.put("TITLE", user.getHospitalName() + (cisHzyzCardReq.getLsyz() == 0 ? "(??????)" : "(??????)"));
					}
					o.put("PCMC", iReportExportFileSer.conVerDataSource(yzList));
					resList.add(o);
					//???????????????????????????????????????????????????????????????????????????????????????
					if(dybz == 1){	
						saveYzkpdyjl(yzList, DateUtil.date(), dyrq, yzlb, zxsjStr[i]);
					}
				}
			}
		}

		return resList;
	}

	
	/**
	 * ????????????
	 * @Title: orderGrouping
	 * @Description: TODO(?????????????????????????????????????????????)
	 * @param @param list
	 * @param @param zhList
	 * @param @return    ????????????
	 * @return Map<String,List<Map<String,Object>>>    ????????????
	 * @author ?????????
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
		// ??????????????????????????????
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
			//??????
			Map<String, Object> map = newList.get(i);
			String yzzxsj = ObjectToTypes.parseString(map.get("YZZXSJ"));
			String zyhm = ObjectToTypes.parseString(map.get("ZYHM"));
			zhMap.put(yzzxsj + zyhm, new ArrayList<Map<String,Object>>());
			if(!zhList.contains(yzzxsj + zyhm)){
				zhList.add(yzzxsj + zyhm);
			}
		}

		// ???????????????????????????????????????????????????
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
					//???????????????????????????????????????,????????????????????????????????????????????????
					if(srcs == 0){
						continue;
					}
				}
				if (map.get("TZSJ") != null) {
	                Timestamp tzsj = DateUtil.parse(map.get("TZSJ") + "").toTimestamp();
	                int tzMonth = DateUtil.month(tzsj) + 1;
					int tzYear = DateUtil.year(tzsj);
					int tzDay = DateUtil.dayOfMonth(tzsj);
	                //?????????????????????????????????
	                int d = BUHISUtil.dateCompare(new Date(tzsj.getTime()), kpyysj);
	                if (d < 0) {
	                    continue;
	                }
	                if (tzYear == nowYear && tzMonth == nowMouth && tzDay == nowDay) {// ???????????????????????????????????????
	                    // ????????????????????????????????????????????????
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
	 * ?????????????????????
	 * @Title: saveKfInfo
	 * @Description: TODO(?????????????????????????????????????????????)
	 * @param @param yzList
	 * @param @param nowYear
	 * @param @param nowMouth
	 * @param @param nowDay
	 * @param @param user
	 * @param @param zxDate    ????????????
	 * @return void    ????????????
	 * @author ?????????
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
	 * ??????????????????????????????
	 * @Title: saveYzkpdyjl
	 * @Description: TODO(?????????????????????????????????????????????)
	 * @param @param yzList
	 * @param @param kpdysj
	 * @param @param kpyysj    ????????????
	 * @return void    ????????????
	 * @author ?????????
	 * @throws
	 */
	public void saveYzkpdyjl(List<Map<String, Object>> yzList, Date kpdysj, Date kpyysj, Integer yzlb, String zxsj) {
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
				cisYzkpdyjl.setYysj(DateUtil.parse(DateUtil.format(kpyysj, DatePattern.NORM_DATE_PATTERN) 
						+ " " + zxsj + ":00").toTimestamp());
				cisYzkpdyjl.setYzlb(yzlb);
				cisYzkpdyjlDao.insert(cisYzkpdyjl);
				dyList.add(yzList.get(v).get("JLXH").toString());
			}
		}
	}
	
	/**
	 * ????????????
	 * @Title: doCancelKpdy
	 * @Description: TODO(?????????????????????????????????????????????)
	 * @param @param list    ????????????
	 * @return void    ????????????
	 * @author ?????????
	 * @throws
	 */
	public void doCancelKpdy(List<CisHzyzCardCancelReq> list){
		for(CisHzyzCardCancelReq cisHzyzCardCancelReq : list) {
			cisYzkpdyjlDao.cancelKpdy(cisHzyzCardCancelReq.getYzzh(), cisHzyzCardCancelReq.getYysj());
		}
	}


	/**
	 * ???????????????????????????
	 * @Title: getAdministrationRecordFields
	 * @Description: TODO(?????????????????????????????????????????????)
	 * @param @param cisHzyzCardReq
	 * @param @param user
	 * @param @return    ????????????
	 * @return List<Map<String,Object>>    ????????????
	 * @author ?????????
	 * @throws
	 */
	public List<Map<String, Object>> getAdministrationRecordFields(CisHzyzCardReq cisHzyzCardReq, SysUser user) {
		DecimalFormat df = new DecimalFormat("##########.##########");
		Date zxtime = setCardDateTime(cisHzyzCardReq.getTypeDate());
		List<Map<String, Object>> list = imHzryDao.queryAdministrationPatientInfo(user.getHospitalId(), cisHzyzCardReq.getBqdm(),
				cisHzyzCardReq.getZyhList(), cisHzyzCardReq.getLsyz());
		for(Map<String, Object> map : list) {
			map.put("title", cisHzyzCardReq.getLsyz()!= null && cisHzyzCardReq.getLsyz() == 0 ? "???????????????(??????)" : "???????????????(??????)");
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
										//???????????????????????????????????????????????????,???????????????		
										if(i != 0){
											if(ObjectToTypes.parseInt(ypMap.get("yzzh")) == ObjectToTypes.parseInt(ypList.get(i-1).get("yzzh"))){
												if((i+1) < ypList.size()){
													if(ObjectToTypes.parseInt(ypMap.get("yzzh")) == ObjectToTypes.parseInt(ypList.get(i+1).get("yzzh"))){
														ypMap.put("zh", "???");
													}else{
														ypMap.put("zh", "???");
													}
												}else{
													ypMap.put("zh", "???");
												}
											}else{
												if((i+1) < ypList.size()){
													if(ObjectToTypes.parseInt(ypMap.get("yzzh")) == ObjectToTypes.parseInt(ypList.get(i+1).get("yzzh"))){
														ypMap.put("zh", "???");
													}
												}
											}
										}
										if(i == 0){
											if((i+1) < ypList.size()){
												if(ObjectToTypes.parseInt(ypMap.get("yzzh")) == ObjectToTypes.parseInt(ypList.get(i+1).get("yzzh"))){
													ypMap.put("zh", "???");
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
					chlidMap.put("ypyf", iReportExportFileSer.conVerDataSource(newList.stream().sorted(
							Comparator.comparing(o -> ObjectToTypes.parseInt(o.get("yzzh")))).sorted(
									Comparator.comparing(o -> ObjectToTypes.parseString(o.get("yzzxsj")))).collect(Collectors.toList())));
				}
			}
		}
		
		return list;
	}

	/**
	 * ???????????????
	 * @Title: getDietListParameters
	 * @Description: TODO(?????????????????????????????????????????????)
	 * @param @param cisHzyzCardReq
	 * @param @param user
	 * @param @return    ????????????
	 * @return Map<String,Object>    ????????????
	 * @author ?????????
	 * @throws
	 */
	public Map<String, Object> getDietListParameters(CisHzyzCardReq cisHzyzCardReq, SysUser user) {
		Map<String, Object> responseMap = new HashMap<String, Object>(16);
		String djmc = cisHzyzCardReq.getLsyz() == 0 ? "?????????(??????)" : "?????????(??????)";
		responseMap.put("title", user.getHospitalName() + djmc);
		responseMap.put("bqmc", cisHzyzCardReq.getBqdmName());
		Date dyrq = setCardDateTime(cisHzyzCardReq.getTypeDate());
		responseMap.put("dyrq", DateUtil.format(dyrq, DatePattern.NORM_DATETIME_PATTERN));
		return responseMap;
	}

	/**
	 * ???????????????
	 * @Title: getSummaryTreatmentParameters
	 * @Description: TODO(?????????????????????????????????????????????)
	 * @param @param cisHzyzCardReq
	 * @param @param user
	 * @param @return    ????????????
	 * @return Map<String,Object>    ????????????
	 * @author ?????????
	 * @throws
	 */
	public Map<String, Object> getSummaryTreatmentParameters(CisHzyzCardReq cisHzyzCardReq, SysUser user) {
		Map<String, Object> responseMap = new HashMap<String, Object>(16);
		String djmc = cisHzyzCardReq.getLsyz() == 0 ? "??????" : "??????";
		responseMap.put("title", user.getHospitalName() + djmc + "?????????????????????");
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
	 * ???????????????????????????
	 * @Title: getSummaryTreatmentFields
	 * @Description: TODO(?????????????????????????????????????????????)
	 * @param @param cisHzyzCardReq
	 * @param @param user
	 * @param @return    ????????????
	 * @return List<Map<String,Object>>    ????????????
	 * @author ?????????
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
			parameters.put("bqdm", cisHzyzCardReq.getBqdm());// ????????????
			parameters.put("jgid", jgid);// ??????ID
			parameters.put("fhbz", fhbz);// ????????????????????? ????????????
			parameters.put("lsyz", cisHzyzCardReq.getLsyz());
			parameters.put("yzlb", 2);
			//??????
			List<Map<String, Object>> kfList = cisHzyzDao.queryOralCardZxInfo(parameters);
			//??????
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
									yzmc = "???" + yzmc;
								}else{
									yzmc = "???" + yzmc;
								}
							}else{
								yzmc = "???" +  yzmc;
							}
						}else{
							if((j+1) < syList.size()){
								if(ObjectToTypes.parseInt(syMap.get("YZZH")) == ObjectToTypes.parseInt(syList.get(j+1).get("YZZH"))){
									yzmc = "???" +  yzmc;
								}
							}
						}
					}
					if(j == 0){
						if((j+1) < syList.size()){
							if(ObjectToTypes.parseInt(syMap.get("YZZH")) == ObjectToTypes.parseInt(syList.get(j+1).get("YZZH"))){
								yzmc = "???" +  yzmc;
							}
						}
					}
					syMap.put("YZMC", yzmc);
				}
			}
			parameters.remove("xmlb");
			//??????
			parameters.put("yzlb", 3);
			List<Map<String, Object>> zsList = cisHzyzDao.queryOralCardZxInfo(parameters);
			//??????
			parameters.put("yzlb", 16);
			sysXtcs = sysXtcsCacheSer.getByJGIdAndCsmc(jgid, SysXtcsCsmcCts.ZXKSDM);
			List<String> zxksList = Arrays.asList(sysXtcs.getCsz().split(","));
			parameters.put("zxksList", zxksList);
			List<Map<String, Object>> zlList = cisHzyzDao.queryTreatmentZxInfo(parameters);
			//??????
			SkinPsjlReq skinPsjlReq = new SkinPsjlReq();
			skinPsjlReq.setJgid(jgid);
			skinPsjlReq.setBrid(hzry.getBrid());
			List<SkinPsjlResp> gmList = skinPsjlDao.getAllergicDrugsByEntity(skinPsjlReq);
			//??????
			parameters.remove("zxksList");
			List<Map<String, Object>> hlList = cisHzyzDao.queryCareInfo(parameters);
			//??????
			List<Map<String, Object>> ysList = cisHzyzDao.queryDietList(jgid, null, 
					cisHzyzCardReq.getLsyz(), zyh);
			List<String> newkfList = new ArrayList<String>();
			List<String> newsyList = new ArrayList<String>();
			List<String> newzsList = new ArrayList<String>();
			List<String> newzlList = new ArrayList<String>();
			List<String> newgmList = new ArrayList<String>();
			List<String> newhlList = new ArrayList<String>();
			List<String> newysList = new ArrayList<String>(); 
			
			//????????????
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
	 * ???????????????????????????????????????
	 * @Title: getRehydrationPrintFields
	 * @Description: TODO(?????????????????????????????????????????????)
	 * @param @param cisHzyzCardReq
	 * @param @param user
	 * @param @return    ????????????
	 * @return List<Map<String,Object>>    ????????????
	 * @author ?????????
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
							// ?????????????????????????????????
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
										map.put("ZH", "???");
									}else{
										map.put("ZH", "???");
									}
								}else{
									map.put("ZH", "???");
								}
							}else{
								if((j+1) < list.size()){
									if(ObjectToTypes.parseInt(map.get("YZZH")) == ObjectToTypes.parseInt(list.get(j+1).get("YZZH"))){
										map.put("ZH", "???");
									}
								}
							}
						}
						if(j == 0){
							if((j+1) < list.size()){
								if(ObjectToTypes.parseInt(map.get("YZZH")) == ObjectToTypes.parseInt(list.get(j+1).get("YZZH"))){
									map.put("ZH", "???");
								}
							}
						}
						newMap.putAll(map);
						zhMap.get(zyhm).add(newMap);
					}
				}
			}
		}
		// ???????????????????????? ??????????????????????????????
		for(int k = 0 ; k < zhList.size() ; k ++ ){
			List<Map<String, Object>> yzList = zhMap.get(zhList.get(k));
			if(!yzList.isEmpty()) {
				Map<String, Object> map = yzList.get(0);
				Map<String, Object> o = new HashMap<String, Object>(16);
				o.put("TITLE", "?????????????????????");
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
	 * ??????????????????
	 * @Title: setCardDateTime
	 * @Description: TODO(?????????????????????????????????????????????)
	 * @param @param typeDate
	 * @param @return    ????????????
	 * @return Date    ????????????
	 * @author ?????????
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

	public Map<String, Object> getOrderMenuParameters(CisHzyzMenuReq cisHzyzMenuReq, SysUser user) {
		Map<String, Object> responseMap = new HashMap<String, Object>(16);
		if(cisHzyzMenuReq.getType() == 1) {
			responseMap.put("title", dicKszdOutSer.getNameById(cisHzyzMenuReq.getBqdm()) + "?????????");
		}else if(cisHzyzMenuReq.getType() == 3) {
			responseMap.put("SUBREPORT_DIR", this.getClass().getClassLoader().getResource("jrxml/"));
			responseMap.put("pzrq", DateUtil.format(DateUtil.date(), DatePattern.NORM_DATETIME_PATTERN));
			responseMap.put("title", user.getHospitalName() + "????????????");
		}
		
		return responseMap;
	}

	public List<Map<String, Object>> orderMenuPrintFileds(CisHzyzMenuReq cisHzyzMenuReq, SysUser user) {
		if(cisHzyzMenuReq.getType() == 1) {
			return cisHzyzDao.queryDietList(user.getHospitalId(), cisHzyzMenuReq.getZyhList(), null, null);
		}else if(cisHzyzMenuReq.getType() == 2) {
			List<Map<String, Object>> noonList = cisHzyzDao.queryDietList(user.getHospitalId(), cisHzyzMenuReq.getZyhList(), null, null);
			List<Map<String, Object>> afterList = new ArrayList<Map<String,Object>>();
			for(Map<String, Object> noonMap : noonList) {
				noonMap.put("kpmc", "??????");
				noonMap.put("dyrq", DateUtil.date().toSqlDate());
				Map<String, Object> afterMap = new HashMap<String, Object>(16);
				afterMap.putAll(noonMap);
				afterMap.put("kpmc", "??????");
				afterList.add(afterMap);
			}
			noonList.addAll(afterList);
			return noonList.stream().sorted(Comparator.comparing(o -> ObjectToTypes.parseString(
					o.get("brch")))).collect(Collectors.toList());
		}else if(cisHzyzMenuReq.getType() == 3) {
			List<Map<String, Object>> respList = new ArrayList<Map<String,Object>>();
			List<Map<String, Object>> list = cisHzyzDao.querySpecialMedicalDiet(user.getHospitalId(), cisHzyzMenuReq.getZyhList());
			Map<String, List<Map<String, Object>>> groups = list.stream().
        			collect(Collectors.groupingBy(o -> ObjectToTypes.parseString(o.get("zyhm")), LinkedHashMap::new, Collectors.toList()));
			for(Map.Entry<String, List<Map<String, Object>>> entry : groups.entrySet()) {
				Map<String, Object> map = new HashMap<String, Object>(16);
				map.put("brxm", entry.getValue().get(0).get("brxm"));
				map.put("birthday", entry.getValue().get(0).get("birthday"));
				map.put("zyhm", entry.getValue().get(0).get("zyhm"));
				map.put("bqmc", entry.getValue().get(0).get("bqmc"));
				map.put("brxb", entry.getValue().get(0).get("brxb"));
				map.put("brch", entry.getValue().get(0).get("brch"));
				map.put("ksmc", entry.getValue().get(0).get("ksmc"));
				map.put("child", iReportExportFileSer.conVerDataSource(entry.getValue()));
				respList.add(map);
			}
			return respList;
		}
		return null;
	}
	
	public List<CisHzyzCancelPrintResp> queryCancelPrintList(CisHzyzCardReq cisHzyzCardReq, Integer hospitalId) {
		if(cisHzyzCardReq.getYzlb() != null && cisHzyzCardReq.getYzlb() == 11) {
			cisHzyzCardReq.setYzlb(4);
		}
		Date kpyysj = setCardDateTime(cisHzyzCardReq.getTypeDate());
		Map<String, Object> parameters = new HashMap<String, Object>(16);
		parameters.put("zyhList", cisHzyzCardReq.getZyhList());
		parameters.put("yysj", DateUtil.date(kpyysj).toSqlDate());
		parameters.put("yzlb", cisHzyzCardReq.getYzlb());
		parameters.put("lsyz", cisHzyzCardReq.getLsyz());
		SysXtcs sysXtcs = sysXtcsCacheSer.getByJGIdAndCsmc(hospitalId, SysXtcsCsmcCts.YYKYPXH);
		List<String> yykList = Arrays.asList(sysXtcs.getCsz().split(","));
		if(!yykList.isEmpty()) {
			parameters.put("sfyyk", cisHzyzCardReq.getSfyyk());
			parameters.put("yykList", yykList);
		}
		return cisHzyzDao.queryCancelPrintList(parameters);
	}
	
	
	public List<Map<String, Object>> reTypeCardInfo(CisHzyzCardReTypeReq cisHzyzCardReTypeReq, SysUser user) {
		DecimalFormat df = new DecimalFormat("##########.##########");
		String bqmc = dicKszdOutSer.getNameById(cisHzyzCardReTypeReq.getBqdm());
		List<Map<String, Object>> resList = new ArrayList<Map<String,Object>>();
		Date kpyysj = setCardDateTime(cisHzyzCardReTypeReq.getTypeDate());
		Integer yzlb = cisHzyzCardReTypeReq.getYzlb();
		SysXtcs sysXtcs = sysXtcsCacheSer.getByJGIdAndCsmc(user.getHospitalId(), SysXtcsCsmcCts.FHYZHJF);
		String fhbz = sysXtcs == null ? "0" : sysXtcs.getCsz();
		Map<String, Object> parameters = new HashMap<String, Object>(16);
		parameters.put("idList", cisHzyzCardReTypeReq.getIdList());
		parameters.put("fhbz", fhbz);
		parameters.put("yzlb", yzlb);
		parameters.put("bqdm", cisHzyzCardReTypeReq.getBqdm());
		parameters.put("jgid", user.getHospitalId());
		if(yzlb == 2) {
			sysXtcs = sysXtcsCacheSer.getByJGIdAndCsmc(user.getHospitalId(), SysXtcsCsmcCts.YYKYPXH);
			List<String> yykList = Arrays.asList(sysXtcs.getCsz().split(","));
			if(!yykList.isEmpty()) {
				parameters.put("sfyyk", cisHzyzCardReTypeReq.getSfyyk());
				parameters.put("yykList", yykList);
			}
		}
		List<Map<String, Object>> list = cisYzkpdyjlDao.reTypeCardInfo(parameters);
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
		Map<String, List<Map<String, Object>>> groups = new HashMap<String, List<Map<String,Object>>>();
		if(yzlb == 2) {
			groups = list.stream().
	    			collect(Collectors.groupingBy(o -> String.format("%s-%s", ObjectToTypes.parseString(o.get("ZYHM")), 
	    					ObjectToTypes.parseString(o.get("YZZXSJ"))), LinkedHashMap::new, Collectors.toList()));
		}else {
			groups = list.stream().
	    			collect(Collectors.groupingBy(o -> String.format("%s-%s-%s", ObjectToTypes.parseString(o.get("ZYHM")), 
	    					ObjectToTypes.parseString(o.get("YZZXSJ")), ObjectToTypes.parseString(o.get("YZZH"))), LinkedHashMap::new, Collectors.toList()));
		}
 
		for (Map.Entry<String, List<Map<String, Object>>> entry : groups.entrySet()){
			List<Map<String, Object>> yzList = entry.getValue();
			Map<String, Object> map = yzList.get(0);
			for(Map<String, Object> yzMap : yzList) {
				yzMap.put("YCJL", df.format(ObjectToTypes.parseBigDecimal(yzMap.get("YCJL"))));
			}
			Date csny = DateUtil.parse(map.get("CSNY") + "");
			Calendar cs = Calendar.getInstance();
			cs.setTime(csny);
			int borthYear = cs.get(Calendar.YEAR);
			int borthMouth = cs.get(Calendar.MONTH) + 1;
			int borthDay = cs.get(Calendar.DATE);
			// ????????????
			Date nowDate = new Date();
			Calendar c = Calendar.getInstance();// ????????????????????????????????????
			c.setTime(nowDate);

			int nowYear = c.get(Calendar.YEAR);
			int nowMouth = c.get(Calendar.MONTH) + 1;
			int nowDay = c.get(Calendar.DATE);
			// ????????????
			int age = nowYear - borthYear - 1;
			boolean dateFlag = borthMouth < nowMouth || borthMouth == nowMouth
					&& borthDay <= nowDay;
			if (dateFlag) {
				age++;
			}
			String yzzxsj  = ObjectToTypes.parseString(map.get("YZZXSJ"));
			Map<String, Object> o = new HashMap<String, Object>(16);
			if(yzlb == 2 && cisHzyzCardReTypeReq.getLsyz() == 0 && ObjectToTypes.parseInt(yzzxsj.substring(0,2)) >= 16) {
				o.put("DYRQ", DateUtil.offset(kpyysj, DateField.DAY_OF_MONTH, -1).toSqlDate());
			}else {
				o.put("DYRQ", kpyysj);
			}
			
			o.put("BRXB", "1".equals(ObjectToTypes.parseString(map.get("BRXB"))) ? "???" : "???");
			o.put("BRNL", age);// ????????????
			o.put("BQMC", bqmc);// ????????????
			o.put("TIME", yzzxsj);// ??????????????????
			o.put("BRXM", map.get("BRXM"));
			o.put("BRCH", map.get("BRCH"));
			o.put("ZYHM", map.get("ZYHM"));
			o.put("PCMC", iReportExportFileSer.conVerDataSource(yzList));
			if(yzlb == 2) {
				o.put("TITLE", user.getHospitalName() + "?????????" + (cisHzyzCardReTypeReq.getLsyz() == 0 ? "(??????)" : "(??????)"));
			}else if(yzlb == 3) {
				o.put("TITLE", user.getHospitalName() + "?????????" + (cisHzyzCardReTypeReq.getLsyz() == 0 ? "(??????)" : "(??????)"));
			}else if(yzlb == 4) {
				o.put("TITLE", user.getHospitalName() + "?????????" + (cisHzyzCardReTypeReq.getLsyz() == 0 ? "(??????)" : "(??????)"));
			}else if(yzlb == 99) {
				o.put("TITLE", user.getHospitalName() + (cisHzyzCardReTypeReq.getLsyz() == 0 ? "(??????)" : "(??????)"));
			}
			o.put("PCMC", iReportExportFileSer.conVerDataSource(yzList));
			resList.add(o);
	    }
		return resList;
	}
}
