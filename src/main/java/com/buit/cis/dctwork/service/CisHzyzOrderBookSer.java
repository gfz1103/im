package com.buit.cis.dctwork.service;


import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.commons.collections.CollectionUtils;
import org.apache.dubbo.config.annotation.DubboReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.buit.aop.lock.Locked;
import com.buit.cis.dctwork.dao.CisHzyzDao;
import com.buit.cis.dctwork.dao.CisHzyzZtDao;
import com.buit.cis.dctwork.model.CisHzyz;
import com.buit.cis.dctwork.model.CisHzyzZt;
import com.buit.cis.dctwork.request.CisHzyzBookPrintReq;
import com.buit.cis.dctwork.request.CisHzyzCopyReq.CopyBody;
import com.buit.cis.dctwork.response.CisHzyzBookPrintResp;
import com.buit.cis.dctwork.response.CisHzyzOrderBookResp;
import com.buit.cis.dctwork.response.CisHzyzPrintRecordResp;
import com.buit.cis.ims.dao.ImHzryDao;
import com.buit.cis.ims.model.ImHzry;
import com.buit.cis.nurse.dao.NisYzdyDao;
import com.buit.cis.nurse.model.NisYzdy;
import com.buit.commons.BaseException;
import com.buit.commons.BaseManagerImp;
import com.buit.commons.SysUser;
import com.buit.constans.SysXtcsCsmcCts;
import com.buit.constans.TableName;
import com.buit.system.response.SysXtcs;
import com.buit.system.service.DicKszdOutSer;
import com.buit.system.service.HrPersonnelService;
import com.buit.system.service.SysXtcsCacheSer;
import com.buit.system.utill.ObjectToTypes;
import com.buit.utill.BUHISUtil;
import com.buit.utill.BytesUtil;
import com.buit.utill.RedisFactory;
import com.buit.utill.ReturnEntity;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
/**
 * 医嘱本打印<br>
 * @author GONGFANGZHOU
 */
@Service
public class CisHzyzOrderBookSer extends BaseManagerImp<CisHzyz,Integer> {
    
    static final Logger logger = LoggerFactory.getLogger(CisHzyzOrderBookSer.class);
    
    @Autowired
    private CisHzyzDao cisHzyzDao;
    
    @Override
    public CisHzyzDao getEntityMapper(){        
        return cisHzyzDao;
    }
	
	@Autowired
	private CisHzyzZtDao cisHzyzZtDao;
	
	@Autowired
	private NisYzdyDao nisYzdyDao;
	
	@DubboReference
	private SysXtcsCacheSer sysXtcsCacheSer;

	@Autowired
	private RedisFactory redisFactory;
	
	@Autowired
	private ImHzryDao imHzryDao;
	
	@DubboReference
	private DicKszdOutSer dicKszdOutSer;
	
	@DubboReference
	private HrPersonnelService hrPersonnelService;
	
	/**
	 * 医嘱本打印查询医嘱
	 * @Title: queryYz
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @param zyh
	 * @param @param yzzt
	 * @param @param yzlx
	 * @param @param user
	 * @param @return    设定文件
	 * @return List<CisHzyzOrderBookResp>    返回类型
	 * @author 龚方舟
	 * @throws
	 */
	public List<CisHzyzOrderBookResp> queryYz(Integer zyh, Integer yzzt, Integer yzlx, SysUser user){
		Integer jgid = user.getHospitalId();
		Integer type = 0;
		if(yzzt != 2){
			NisYzdy nisYzdy = new NisYzdy();
			nisYzdy.setZyh(zyh);
			long count = nisYzdyDao.findByEntityCount(nisYzdy);
			if(count > 0){
				type = 1;
			}
		}
		//医嘱本打印时间,1是提交后打印,2是复核后打印,默认为2
		SysXtcs sysXtcs = sysXtcsCacheSer.getByJGIdAndCsmc(jgid, SysXtcsCsmcCts.YZBDYSJ);
		Integer yzbdysj = sysXtcs == null ? 2 : ObjectToTypes.parseInt(sysXtcs.getCsz());
		
		List<CisHzyzOrderBookResp> cisHzyzOrderBookRespList = cisHzyzDao.queryMedicalOrderInfo(yzlx, zyh,
		    		yzzt, type, jgid, yzbdysj);
		Map<String, Object> jlxhMap = new HashMap<String, Object>(16);
		List<CisHzyzOrderBookResp> reslist = new ArrayList<CisHzyzOrderBookResp>();
		if(CollectionUtils.isNotEmpty(cisHzyzOrderBookRespList)) {
			for(int i = 0; i < cisHzyzOrderBookRespList.size(); i++) {
				CisHzyzOrderBookResp cis = cisHzyzOrderBookRespList.get(i);
				if(StrUtil.isNotBlank(ObjectToTypes.parseString(cis.getZtbz())) && "1".equals(ObjectToTypes.parseString(cis.getZtbz()))) {
					//去除已打印的组套数据
					NisYzdy nisYzdy = new NisYzdy();
					nisYzdy.setYzbxh(cis.getZtyzjlxh());
					long count = nisYzdyDao.findByEntityCount(nisYzdy);
					if (count > 0) {
						continue;
					}
					//改变组套内容
					if (jlxhMap.containsKey(ObjectToTypes.parseString(cis.getZtyzjlxh()))) {
						continue;
					}
					CisHzyzZt cisHzyzZt = cisHzyzZtDao.getById(cis.getZtyzjlxh());
					Map<String, Object> record = BUHISUtil.caseInsensitiveMap(cisHzyzZt);
					cis = BeanUtil.fillBeanWithMapIgnoreCase(record, new CisHzyzOrderBookResp(), true);
					jlxhMap.put(ObjectToTypes.parseString(cis.getJlxh()), cis);

				}
				if("1".equals(ObjectToTypes.parseString(cis.getJfyz()))) {
					continue;
				}
				reslist.add(cis);
			}
		}

		return reslist;
	}
	
	/**
	 * @throws Exception 
	 * @throws Exception 
	 * 医嘱本打印保存
	 * @Title: saveWardDoctorPrint
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @param cisHzyzBookPrintReq
	 * @param @param user
	 * @param @return    设定文件
	 * @return CisHzyzBookPrintResp    返回类型
	 * @author 龚方舟
	 * @throws
	 */
	@Transactional(rollbackFor = Exception.class)
	@Locked(value = {"saveWardDoctorPrint_#[cisHzyzBookPrintReq.zyh]"})
	public CisHzyzBookPrintResp saveWardDoctorPrint(CisHzyzBookPrintReq cisHzyzBookPrintReq, SysUser user) throws Exception {
		Integer jgid = user.getHospitalId();
		String type = cisHzyzBookPrintReq.getType();
		Integer zyh = cisHzyzBookPrintReq.getZyh();
		Integer yzlx = cisHzyzBookPrintReq.getYzlx();
		StringBuffer dyyzs = new StringBuffer();
		StringBuffer tzyzs = new StringBuffer();
		DecimalFormat dft = new DecimalFormat("##########.##########");
		Integer zjs = yzlx == 0 ? 54 : 62;
		
		CisHzyzBookPrintResp printResp = new CisHzyzBookPrintResp();
		if("jxdy".equals(type) || "qbdy".equals(type)){//继续打印,将没打印的医嘱保存到打印表 并返回医嘱序号的集合
			List<CisHzyzOrderBookResp> list = queryYz(zyh, cisHzyzBookPrintReq.getYzzt(), yzlx, user);
			if(CollectionUtils.isEmpty(list)){
				return null;
			}
			
			SysXtcs sysXtcs = sysXtcsCacheSer.getByJGIdAndCsmc(jgid, SysXtcsCsmcCts.YZTDMYTS);
			//每页打印行数
			Integer yztdmyts = sysXtcs == null ? 20 : ObjectToTypes.parseInt(sysXtcs.getCsz());
//			暂时去掉最大限制,因为医院有数据同组超过这个没办法打印, 可以通过临时调整最大行数解决
//			if(yztdmyts > 34){//根据实际情况设置每页行数最大值,现在暂时定为35行
//				ret.put("code", 9000);
//				ret.put("msg", "设置的每页行数大于纸张能打的最大高度,请重新设置,小于34的整数");
//				return ret;
//			}
			sysXtcs = sysXtcsCacheSer.getByJGIdAndCsmc(jgid, SysXtcsCsmcCts.ZKCZHHYDY);
			Integer zkczhhydy = sysXtcs == null ? 0 : ObjectToTypes.parseInt(sysXtcs.getCsz());	

			//查出最后一条打印记录
			NisYzdy nisYzdy = new NisYzdy();
			nisYzdy.setZyh(zyh);
			nisYzdy.setYzqx(yzlx);
			List<NisYzdy> nisYzdyList = nisYzdyDao.findByEntity(nisYzdy);

			Integer dyym = 1;
			Integer dyhh = 1;
			if(CollectionUtils.isNotEmpty(nisYzdyList)){
				Map<String,Object> nisYzdyMap = getMaxDyjl(nisYzdyList);
				dyym = ObjectToTypes.parseInt(nisYzdyMap.get("dyym"));
				dyhh = ObjectToTypes.parseInt(nisYzdyMap.get("dyhh")) +1;
			}
			List<Integer> jlxhList = new ArrayList<Integer>();
			List<Integer> tzjlxhList = new ArrayList<Integer>();
			for(int i= 0; i < list.size(); i++){
				CisHzyzOrderBookResp cis = list.get(i);
				if (cis.getXmlx().intValue() >= 4) {
					cis.setJldw("");
				}
				int k = 1;
				for(int j = i + 1; j < list.size(); j++){
					if(cis.getYzzh().intValue() == list.get(j).getYzzh().intValue()){
						k++;
					}
				}


				//转科医嘱特殊判断
				String ydlb = cis.getYdlb();
				if("302".equals(ydlb)){
					if(zkczhhydy == 1){
						if(dyhh != 1){
							int xh= yztdmyts - dyhh;
							for(int x = 0; x <= xh; x++){
							 	NisYzdy nisYzdySave = new NisYzdy();
							 	//-1表示因为同组在不同页而用于填充的空行
							 	nisYzdySave.setYzbxh(-1);
							 	nisYzdySave.setZyh(zyh);
							 	nisYzdySave.setYzqx(yzlx);
							 	nisYzdySave.setDyrq(DateUtil.date().toTimestamp());
							 	nisYzdySave.setDynr("空行");
							 	nisYzdySave.setDyym(dyym);
							 	nisYzdySave.setDyhh(dyhh);
							 	nisYzdySave.setCzbz(0);
							 	nisYzdySave.setJgid(jgid);
							 	Integer dyxh = redisFactory.getTableKey(TableName.DB_NAME, TableName.NIS_YZDY);
							 	nisYzdySave.setDyxh(dyxh);
							 	nisYzdyDao.insert(nisYzdySave);						
								dyhh++;
								if(dyyzs.length() > 0){
									dyyzs.append(",").append(dyxh);
								}else{
									dyyzs.append(dyxh);
								}
							 }
							 dyym++;
							 dyhh = 1;
						}
					}
					NisYzdy nisYzdySave = new NisYzdy();
				 	nisYzdySave.setYzbxh(cis.getJlxh());
				 	nisYzdySave.setZyh(zyh);
				 	nisYzdySave.setYzqx(yzlx);
				 	nisYzdySave.setDyrq(DateUtil.date().toTimestamp());
				 	nisYzdySave.setDynr("转科后医嘱");
				 	nisYzdySave.setDyym(dyym);
				 	nisYzdySave.setDyhh(dyhh);
				 	nisYzdySave.setCzbz(0);
				 	nisYzdySave.setJgid(jgid);
				 	Integer dyxh = redisFactory.getTableKey(TableName.DB_NAME, TableName.NIS_YZDY);
				 	nisYzdySave.setDyxh(dyxh);
				 	nisYzdyDao.insert(nisYzdySave);			
					dyhh++;
					if(dyyzs.length() > 0){
						dyyzs.append(",").append(dyxh);
					}else{
						dyyzs.append(dyxh);
					}
					jlxhList.add(cis.getJlxh());
					continue;
				}else if(cis.getYzlx() != null && cis.getYzlx() == 3) {
					String yzmc = cis.getYzmc();
					if(yzmc.indexOf("(") == 0){
						yzmc = yzmc.substring(yzmc.indexOf(")")+1);
					}
					String ypyfmc = cis.getYpyfmc() == null ? "" : cis.getYpyfmc();
					String sypcmc = cis.getSypcmc() == null ? "" : cis.getSypcmc();
					yzmc += " " + (cis.getYcjl().compareTo(BigDecimal.ZERO) == 0 ? "" : dft.format(cis.getYcjl())) + 
							(cis.getJldw() == null ? "" : cis.getJldw()) + " ";	
					yzmc += ypyfmc + " " + sypcmc;
					NisYzdy nisYzdySave = new NisYzdy();
				 	nisYzdySave.setYzbxh(cis.getJlxh());
				 	nisYzdySave.setZyh(zyh);
				 	nisYzdySave.setYzqx(yzlx);
				 	nisYzdySave.setDyrq(DateUtil.date().toTimestamp());
				 	nisYzdySave.setDynr(yzmc);
				 	nisYzdySave.setDyym(dyym);
				 	nisYzdySave.setDyhh(dyhh);
				 	nisYzdySave.setCzbz(0);
				 	nisYzdySave.setJgid(jgid);
				 	Integer dyxh = redisFactory.getTableKey(TableName.DB_NAME, TableName.NIS_YZDY);
				 	nisYzdySave.setDyxh(dyxh);
				 	nisYzdyDao.insert(nisYzdySave);			
					dyhh++;
					if(dyyzs.length() > 0){
						dyyzs.append(",").append(dyxh);
					}else{
						dyyzs.append(dyxh);
					}
					jlxhList.add(cis.getJlxh());
					
					nisYzdySave.setYzbxh(0);
				 	nisYzdySave.setZyh(zyh);
				 	nisYzdySave.setYzqx(yzlx);
				 	nisYzdySave.setDyrq(DateUtil.date().toTimestamp());
				 	nisYzdySave.setDynr("术后医嘱");
				 	nisYzdySave.setDyym(dyym);
				 	nisYzdySave.setDyhh(dyhh);
				 	nisYzdySave.setCzbz(0);
				 	nisYzdySave.setJgid(jgid);
				 	dyxh = redisFactory.getTableKey(TableName.DB_NAME, TableName.NIS_YZDY);
				 	nisYzdySave.setDyxh(dyxh);
				 	nisYzdyDao.insert(nisYzdySave);			
					dyhh++;
					if(dyyzs.length() > 0){
						dyyzs.append(",").append(dyxh);
					}else{
						dyyzs.append(dyxh);
					}
					jlxhList.add(cis.getJlxh());
					continue;	
				}
				///转科医嘱特殊判断结束
				if(cis.getLsyz().intValue() != 1 && !StrUtil.isBlankIfStr(cis.getTzsj())){
					tzjlxhList.add(cis.getJlxh());
				}else{
					jlxhList.add(cis.getJlxh());
				}
				
				Integer dyxh = null;

				//停嘱医嘱继续打印要判断打印表是否有该记录
				NisYzdy yzdyResp = nisYzdyDao.queryDyxhByYzbxh(cis.getJlxh());
				if(yzdyResp == null){
					if(dyhh > yztdmyts){
						dyym++;
						dyhh = 1;
					}
					String yzmc = cis.getYzmc();
					if(yzmc.indexOf("(") == 0){
						yzmc = yzmc.substring(yzmc.indexOf(")")+1);
					}
					String dynr = yzmc; 
					String ypyfmc = cis.getYpyfmc() == null ? "" : cis.getYpyfmc();
					String sypcmc = cis.getSypcmc() == null ? "" : cis.getSypcmc();
					String jldw = cis.getJldw() == null ? "" : cis.getJldw();
					//临时医嘱
					if(yzlx == 1){
						//药品类型 <> 0   药品 
						if(!"0".equals(ObjectToTypes.parseString(cis.getYplx()))){
							if("3".equals(ObjectToTypes.parseString(cis.getYplx()))){
								//草药
								dynr += " " + (cis.getYcjl().compareTo(BigDecimal.ZERO) == 0 ? "": cis.getYcjl()) + jldw + " ";	
								dynr += " ×" + cis.getCfts() + " , ";
								dynr += ypyfmc + " " + sypcmc;
							}else{
								//西药
								dynr += (cis.getYcsl().compareTo(BigDecimal.ZERO) == 0 ? "" : "/  ×" + dft.format(cis.getYcsl()) + cis.getYfdw()) + " ,";
								dynr += " "+ (cis.getYcjl().compareTo(BigDecimal.ZERO) == 0  ? "": cis.getYcjl()) + jldw + " ";	
								dynr += ypyfmc + " " + sypcmc;
							}
							
						}else{
							//项目或文字医嘱
							if(!StrUtil.isBlankIfStr(cis.getYjfygb())){
								//项目
								dynr += " " + sypcmc;
								dynr += (cis.getYcsl().compareTo(BigDecimal.ZERO) == 0 ? "" : " ×" + dft.format(cis.getYcsl()) )+ "";
							}else{ 
								if(!StrUtil.isBlankIfStr(cis.getYzlx()) && cis.getYzlx() == 4) {
									dynr += ypyfmc;
								}else {
									//或文字医嘱				
									dynr += ypyfmc + " " + sypcmc;
								}
							}
						}
					}else{
						dynr += " " + (cis.getYcjl().compareTo(BigDecimal.ZERO) == 0 ? "" : dft.format(cis.getYcjl())) + jldw + " ";	
						dynr += ypyfmc + " " + sypcmc;
					}
					
					if(cis.getZfyp().intValue() == 1){
						dynr ="(自备)" + dynr;
					}
					if(cis.getJfbz() != null && cis.getJfbz().intValue() != 3 && cis.getBzxx() != null 
							&& cis.getBzxx().trim().length()>0) {
						dynr +=" (" + (cis.getBzxx()+ ")");
					}
					NisYzdy nisYzdySave = new NisYzdy();
				 	nisYzdySave.setYzbxh(cis.getJlxh());
				 	nisYzdySave.setZyh(zyh);
				 	nisYzdySave.setYzqx(yzlx);
				 	nisYzdySave.setDyrq(DateUtil.date().toTimestamp());
				 	nisYzdySave.setDynr(dynr);
				 	nisYzdySave.setDyym(dyym);	
				 	nisYzdySave.setCzbz(0);
				 	nisYzdySave.setJgid(jgid);
					List<String> dynnList = BytesUtil.getListStringBytes(dynr, zjs);
					if(dynnList.size() > 1) {
						for(String dynnStr : dynnList) {
							nisYzdySave.setDyhh(dyhh);
							dyxh = redisFactory.getTableKey(TableName.DB_NAME, TableName.NIS_YZDY);
						 	nisYzdySave.setDyxh(dyxh);
						 	nisYzdySave.setDynr(dynnStr);
							nisYzdyDao.insert(nisYzdySave);	
							dyhh++;
							if(dyyzs.length() > 0){
								dyyzs.append(",").append(yzdyResp == null ? dyxh : yzdyResp.getDyxh());
							}else{
								dyyzs.append(yzdyResp == null ? dyxh : yzdyResp.getDyxh());
							}
						}
					}else {
						nisYzdySave.setDyhh(dyhh);
						dyxh = redisFactory.getTableKey(TableName.DB_NAME, TableName.NIS_YZDY);
					 	nisYzdySave.setDyxh(dyxh);
						nisYzdyDao.insert(nisYzdySave);	
						dyhh++;
						if(dyyzs.length() > 0){
							dyyzs.append(",").append(yzdyResp == null ? dyxh : yzdyResp.getDyxh());
						}else{
							dyyzs.append(yzdyResp == null ? dyxh : yzdyResp.getDyxh());
						}
					}
//				 	if(getBytesStrLength(dynr) > dyhhs) {
//				 		while(getBytesStrLength(dynr) > dyhhs) {
//				 			dyxh = redisFactory.getTableKey(TableName.DB_NAME, TableName.NIS_YZDY);
//							String dynrSave = bytesSubstring(dynr, dyhhs);
//							dynr = dynr.substring(dynrSave.length());
//							nisYzdySave.setDynr(dynrSave);
//							nisYzdyDao.insert(nisYzdySave);	
//							dyhh++;
//						}
//				 	}else {
//				 		dyxh = redisFactory.getTableKey(TableName.DB_NAME, TableName.NIS_YZDY);
//						nisYzdySave.setDynr(dynr);
//						nisYzdyDao.insert(nisYzdySave);	
//						dyhh++;
//					}
				}else{
					if(tzyzs.length() > 0){
						tzyzs.append(",").append(yzdyResp.getDyxh());
					}else{
						tzyzs.append(yzdyResp.getDyxh());
					}
				}
				
			}
			printResp.setDyyzs(dyyzs.toString());
			printResp.setTzyzs(tzyzs.toString());
			//医嘱状态,停嘱或非停嘱
			Integer yzzt = cisHzyzBookPrintReq.getYzzt();
			
			Set<Integer> set = new HashSet<Integer>();
			if(jlxhList.size() > 0){
				for(int i = 0;i < jlxhList.size(); i++){
					set.add(jlxhList.get(i));
				}
				//过滤重复的住院医嘱数据
				//更新医嘱表的打印状态
				cisHzyzDao.updateDyztFilter(yzzt, set);
			}
			if(tzjlxhList.size() > 0){
				//更新医嘱表的打印状态(停嘱)
				cisHzyzDao.updateDyzt(tzjlxhList);
			}
		}else if("aydy".equals(type)){
			//按页打印,查询是否有当前输入页的打印数据
			long l = nisYzdyDao.queryIsExistPrintPage(cisHzyzBookPrintReq);
			if(l == 0){
				throw BaseException.create("ERROR_BOOK_PRINT_0003");
			}
		}else if("czdy".equals(type)){
			//重整打印
			//重整前判断开嘱医嘱是否已经全部打印

			//查询需要重整的医嘱

			//医嘱本打印时间,1是提交后打印,2是复核后打印,默认为2
			SysXtcs sysXtcs = sysXtcsCacheSer.getByJGIdAndCsmc(jgid, SysXtcsCsmcCts.YZBDYSJ);
			Integer yzbdysj = sysXtcs == null ? 2 : ObjectToTypes.parseInt(sysXtcs.getCsz());

			Map<String, Object> czMap = nisYzdyDao.queryDyrqByZyh(zyh);
			Integer sfcz = cisHzyzBookPrintReq.getSfcz();
			if(czMap != null && !czMap.isEmpty() && sfcz == 0) {
				if (BUHISUtil.dateCompare(BUHISUtil.toDate(czMap.get("DYRQ") + ""), new Date()) == 0) {
					ReturnEntity<String> returnEntity = new ReturnEntity<String>();
					returnEntity.setError("ERROR_BOOK_PRINT_0001", null);
					printResp.setMsg(returnEntity.getMessage());
				}
			}

			List<CisHzyzOrderBookResp> resList = cisHzyzDao.queryRenewOrderInfo(yzlx, zyh,
					jgid, yzbdysj);
			if(CollectionUtils.isEmpty(resList)){
				throw BaseException.create("ERROR_BOOK_PRINT_0004");
			}
			sysXtcs = sysXtcsCacheSer.getByJGIdAndCsmc(jgid, SysXtcsCsmcCts.YZTDMYTS);
			//每页打印行数
			Integer yztdmyts = sysXtcs == null ? 20 : ObjectToTypes.parseInt(sysXtcs.getCsz());

			NisYzdy nisYzdy = new NisYzdy();
			nisYzdy.setZyh(zyh);
			nisYzdy.setYzqx(yzlx);
			List<NisYzdy> nisYzdyList = nisYzdyDao.findByEntity(nisYzdy);
			int dyym = 1;
			int dyhh = 1;
			if(CollectionUtils.isNotEmpty(nisYzdyList)){
				Map<String,Object> yzdyMap = getMaxDyjl(nisYzdyList);
				dyym = ObjectToTypes.parseInt(yzdyMap.get("dyym"));
				dyhh = ObjectToTypes.parseInt(yzdyMap.get("dyhh")) + 1;
			}
			if(dyhh > yztdmyts){
				dyym++;
				dyhh = 1;
			}
			
			nisYzdyDao.updatePrintCzbz(zyh, yzlx);
			
			if(dyhh != 1){
				int xh= yztdmyts - dyhh;
				for(int x = 0; x <= xh; x++){
				 	NisYzdy nisYzdySave = new NisYzdy();
				 	nisYzdySave.setYzbxh(-1);
				 	nisYzdySave.setZyh(zyh);
				 	nisYzdySave.setYzqx(yzlx);
				 	nisYzdySave.setDyrq(DateUtil.date().toTimestamp());
				 	nisYzdySave.setDynr("空行");
				 	nisYzdySave.setDyym(dyym);
				 	nisYzdySave.setDyhh(dyhh);
				 	nisYzdySave.setCzbz(0);
				 	nisYzdySave.setJgid(jgid);
				 	Integer dyxh = redisFactory.getTableKey(TableName.DB_NAME, TableName.NIS_YZDY);
				 	nisYzdySave.setDyxh(dyxh);
				 	nisYzdyDao.insert(nisYzdySave);	
				
					dyhh++;
					if(dyyzs.length() > 0){
						dyyzs.append(",").append(dyxh);
					}else{
						dyyzs.append(dyxh);
					}
				}
				dyym++;
				dyhh = 1;
			}
			
			NisYzdy nisYzdySave = new NisYzdy();
		 	nisYzdySave.setYzbxh(0);
		 	nisYzdySave.setZyh(zyh);
		 	nisYzdySave.setYzqx(yzlx);
		 	nisYzdySave.setDyrq(DateUtil.date().toTimestamp());
		 	nisYzdySave.setDynr("重整医嘱");
		 	nisYzdySave.setDyym(dyym);
		 	nisYzdySave.setDyhh(dyhh);
		 	nisYzdySave.setCzbz(0);
		 	nisYzdySave.setJgid(jgid);
		 	nisYzdySave.setCzys(ObjectToTypes.parseString(cisHzyzBookPrintReq.getCzys()));
		 	Integer dyxh = redisFactory.getTableKey(TableName.DB_NAME, TableName.NIS_YZDY);
		 	nisYzdySave.setDyxh(dyxh);
		 	nisYzdyDao.insert(nisYzdySave);	
			dyhh++;
			for(int i = 0 ; i < resList.size(); i++){
				CisHzyzOrderBookResp cis = resList.get(i);
				int k = 1;
				if (cis.getXmlx().intValue() >= 4) {
					cis.setJldw("");
				}
				for(int j = i+ 1; j < resList.size(); j++){
					if(cis.getYzzh().intValue() == resList.get(j).getYzzh().intValue()){
						k++;
					}
				}
				if(k > yztdmyts){
					throw BaseException.create("ERROR_BOOK_PRINT_0002");
				}
				
				Integer dyzt = 1;
				if(cis.getTzsj() != null){
					dyzt = 2;
				}
				//更新医嘱表的打印状态
				cisHzyzDao.updateDyztByJlxh(dyzt, cis.getJlxh());
				if(dyhh > yztdmyts){
					dyym++;
					dyhh = 1;
				}

				String yzmc = cis.getYzmc();
				if(yzmc.indexOf("(") == 0){
					yzmc = yzmc.substring(yzmc.indexOf(")")+1);
				}

				String dynr = yzmc;
				String ypyfmc = cis.getYpyfmc() == null ? "" : cis.getYpyfmc();
				String sypcmc = cis.getSypcmc() == null ? "" : cis.getSypcmc();
				String jldw = cis.getJldw() == null ? "" : cis.getJldw();
				if(yzlx == 1){//临时医嘱
					//药品类型 <> 0   药品 
					if(!"0".equals(ObjectToTypes.parseString(cis.getYplx()))){
						if("3".equals(ObjectToTypes.parseString(cis.getYplx()))){
							//草药
							dynr += " "+ (cis.getYcjl().compareTo(BigDecimal.ZERO) == 0 ? "": cis.getYcjl()) + jldw +" ";	
							dynr += " ×" + cis.getCfts() + " , ";
							dynr += ypyfmc + " " + sypcmc;
						}else{
							//西药
							dynr += (cis.getYcsl().compareTo(BigDecimal.ZERO) == 0 ? "" : "/  ×" + dft.format(cis.getYcsl()) + cis.getYfdw()) + " ,";
							dynr += " " + (cis.getYcjl().compareTo(BigDecimal.ZERO) == 0 ? "" : cis.getYcjl()) + jldw + " ";	
							dynr += ypyfmc + " " + sypcmc;
						}
						
					}else{
						//项目或文字医嘱
						if(!StrUtil.isBlankIfStr(cis.getYjfygb())){
							//项目
							dynr += " " + sypcmc;
							dynr += (cis.getYcsl().compareTo(BigDecimal.ZERO) == 0 ? "" : " ×" + dft.format(cis.getYcsl()) )+ "";
						}else{ 
							//或文字医嘱	
							dynr += ypyfmc + " " + sypcmc;
							
						}
					}
				}else{
					dynr += " " + (cis.getYcjl().compareTo(BigDecimal.ZERO) == 0 ? "" : cis.getYcjl()) + cis.getJldw() + " ";	
					dynr += ypyfmc + " " + sypcmc;
				}
				
				if(cis.getZfyp().intValue() == 1){
					dynr="(自备)" + dynr;
				}
				if(cis.getJfbz() != null && cis.getJfbz().intValue() != 3 && cis.getBzxx() != null 
						&& cis.getBzxx().toString().trim().length() > 0) {
					dynr +=" ("+ (cis.getBzxx() +")");
				}	
				nisYzdySave = new NisYzdy();
			 	nisYzdySave.setYzbxh(cis.getJlxh());
			 	nisYzdySave.setZyh(zyh);
			 	nisYzdySave.setYzqx(yzlx);
			 	nisYzdySave.setDyrq(DateUtil.date().toTimestamp());
			 	nisYzdySave.setDynr(dynr);
			 	nisYzdySave.setDyym(dyym);
			 	nisYzdySave.setDyhh(dyhh);
			 	nisYzdySave.setCzbz(2);
			 	nisYzdySave.setJgid(jgid);
			 	nisYzdySave.setCzys(ObjectToTypes.parseString(cisHzyzBookPrintReq.getCzys()));
			 	dyxh = redisFactory.getTableKey(TableName.DB_NAME, TableName.NIS_YZDY);
			 	nisYzdySave.setDyxh(dyxh);
			 	nisYzdyDao.insert(nisYzdySave);	
				dyhh++;
			}
		}
		return printResp;
	}
	
	/**
	 * 获取最后打印记录
	 * @Title: getMaxDyjl
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @param list
	 * @param @return    设定文件
	 * @return Map<String,Object>    返回类型
	 * @author 龚方舟
	 * @throws
	 */
	private Map<String,Object> getMaxDyjl(List<NisYzdy> list) {
		List<Map<String, Object>> listMap = BUHISUtil.ListObjToMap(list);
		Map<String, Object> resMap = new HashMap<String, Object>(16);
		resMap.putAll(listMap.get(0));
		for (int i = 1; i < listMap.size(); i++) {
			if (ObjectToTypes.parseInt(listMap.get(i).get("dyym")) >
					ObjectToTypes.parseInt(resMap.get("dyym"))) {
				resMap.putAll(listMap.get(i));
			} else if (ObjectToTypes.parseInt(listMap.get(i).get("dyym")) ==
					ObjectToTypes.parseInt(resMap.get("dyym"))) {
				if (ObjectToTypes.parseInt(listMap.get(i).get("dyhh")) >
						ObjectToTypes.parseInt(resMap.get("dyhh"))) {
					resMap.putAll(listMap.get(i));
				}
			}
		}
		return resMap;
	}
	
	/**
	 * 医嘱本打印返回参数
	 * @Title: getWardDoctorPrintParameters
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @param zyh
	 * @param @param user
	 * @param @param dylx
	 * @param @param zyxhs
	 * @param @param tzyzs
	 * @param @param dyhh
	 * @param @return    设定文件
	 * @return Map<String,Object>    返回类型
	 * @author 龚方舟
	 * @throws
	 */
	public Map<String, Object> getWardDoctorPrintParameters(CisHzyzBookPrintReq cisHzyzBookPrintReq, SysUser user){
		Map<String, Object> responseMap = new HashMap<String, Object>(16);
		Integer jgid = user.getHospitalId();
		SysXtcs sysXtcs = sysXtcsCacheSer.getByJGIdAndCsmc(jgid, SysXtcsCsmcCts.YZTDMYTS);
		Integer zyh = cisHzyzBookPrintReq.getZyh();
		String dylx = cisHzyzBookPrintReq.getType();
		String zyxhs = cisHzyzBookPrintReq.getDyyzs();
		String tzyzs = cisHzyzBookPrintReq.getTzyzs();
		Integer dyhh = cisHzyzBookPrintReq.getDyhh();
		//每页打印行数
		Integer yztdmyts = sysXtcs == null ? 20 : ObjectToTypes.parseInt(sysXtcs.getCsz());
		ImHzry imHzry = imHzryDao.getById(zyh);
		String brxb = "";
		if(imHzry.getBrxb() != null){
			brxb = ObjectToTypes.parseString(imHzry.getBrxb());
			if("0".equals(brxb)) {
				brxb = "未知的性别";
			}else if("1".equals(brxb)) {
				brxb = "男";
			}else if("2".equals(brxb)) {
				brxb = "女";
			}else {
				brxb = "未说明的性别";
			}
		}
		String kb = "";
		if(imHzry.getBrks() !=null ){
			kb = dicKszdOutSer.getNameById(imHzry.getBrks());
		}
		
		String bq = "";
		if(imHzry.getBrbq() !=null ){
			bq = dicKszdOutSer.getNameById(imHzry.getBrbq());
		}
		if("qbdy".equals(cisHzyzBookPrintReq.getType())) {
			responseMap.put("TITLE", user.getHospitalName() + "全部医嘱单" + (cisHzyzBookPrintReq.getYzlx() == 0? "(长期)" : "(临时)"));
		}else if(cisHzyzBookPrintReq.getYzlx() == 0) {
			responseMap.put("TITLE", user.getHospitalName() + "长期医嘱单");
		}else if(cisHzyzBookPrintReq.getYzlx() == 1) {
			responseMap.put("TITLE", user.getHospitalName() + "临期医嘱单");
		}
		responseMap.put("BRXM", imHzry.getBrxm());
		responseMap.put("XB", brxb);
		responseMap.put("NL", imHzry.getRynl());
		responseMap.put("KB", kb);
		responseMap.put("BQ", bq);
		responseMap.put("CH", imHzry.getBrch());
		responseMap.put("ZYHM", imHzry.getZyhm());
		responseMap.put("MYHS", yztdmyts);
		responseMap.put("SFTD", getSftd(dylx, zyxhs, tzyzs, dyhh));
		responseMap.put("YEAR", DateUtil.year(imHzry.getCsny()));
		responseMap.put("MONTH", DateUtil.month(imHzry.getCsny()));
		responseMap.put("DAY", DateUtil.dayOfMonth(imHzry.getCsny()));
		return responseMap;
	}
	
	public List<Map<String, Object>> getWardDoctorPrintFields(CisHzyzBookPrintReq cisHzyzBookPrintReq, SysUser user){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		SimpleDateFormat sdfo = new SimpleDateFormat("yy-MM-dd");
		SimpleDateFormat sdft = new SimpleDateFormat("HH:mm");
		List<Map<String, Object>> responseList = new ArrayList<Map<String,Object>>();
		Integer jgid = user.getHospitalId();
		//打印类型,即哪个按钮打印
		String dylx = cisHzyzBookPrintReq.getType();
		//医嘱期效,1是长期医嘱2是临时医嘱
		Integer yzlx = cisHzyzBookPrintReq.getYzlx();
		Map<String,Object> parameters = new HashMap<String,Object>(16);
		//储存那些已经打印过的停嘱医嘱的打印表主键
		List<Integer> tzdyxhList = new ArrayList<Integer>();
		SysXtcs sysXtcs = sysXtcsCacheSer.getByJGIdAndCsmc(jgid, SysXtcsCsmcCts.YZBDYSFTD);
		//每页打印行数
		Integer sftd = sysXtcs == null ? 0 : ObjectToTypes.parseInt(sysXtcs.getCsz());
		List<Map<String, Object>> listMap = null;
		try {
			if("jxdy".equals(dylx)){//继续打印
				String zyxhs = cisHzyzBookPrintReq.getDyyzs();
				if("".equals(zyxhs)){
					return null;
				}
				String[] dyyzs = zyxhs.split(",");
				List<Integer> yzxhList = new ArrayList<Integer>();//打印的医嘱集合
				for(String dyyz : dyyzs){
					yzxhList.add(ObjectToTypes.parseInt(dyyz));
				}
				String tzyzs = cisHzyzBookPrintReq.getTzyzs();
				if(StrUtil.isNotBlank(tzyzs)){
					String[] tzyz = tzyzs.split(",");
					for(String tz : tzyz){
						tzdyxhList.add(ObjectToTypes.parseInt(tz));
					}
				}
				parameters.put("jlxhs", yzxhList);
				parameters.put("zyh", cisHzyzBookPrintReq.getZyh());
				parameters.put("yzlx", yzlx);
				listMap = nisYzdyDao.queryResumePrint(parameters);
			}else if("aydy".equals(dylx)){
				//按页打印
				//打印终止页
				Integer pageTo = cisHzyzBookPrintReq.getPageTo();
				//住院号
				parameters.put("zyh", cisHzyzBookPrintReq.getZyh());
				//医嘱期效,0是长期医嘱1是临时医嘱
				parameters.put("yzlx", yzlx);
				//打印起始页
				parameters.put("pageFrom", cisHzyzBookPrintReq.getPageFrom());
				if(pageTo != 0){
					parameters.put("pageTo", pageTo);
				}
				listMap = nisYzdyDao.queryPagePrint(parameters);
			}else if("zdhdy".equals(dylx)){
				//指定行
				//住院号
				parameters.put("zyh", cisHzyzBookPrintReq.getZyh());
				//医嘱期效,0是长期医嘱1是临时医嘱
				parameters.put("yzlx", yzlx);
				//页码
				parameters.put("dyym", cisHzyzBookPrintReq.getDyym());
				//行号
				parameters.put("dyhh", cisHzyzBookPrintReq.getDyhh());
				parameters.put("type", 1);
				listMap = nisYzdyDao.querySpecifyLinePrint(parameters);
			}else if("czdy".equals(dylx)){//重整
				//住院号
				parameters.put("zyh", cisHzyzBookPrintReq.getZyh());
				//医嘱期效,0是长期医嘱1是临时医嘱
				parameters.put("yzlx", yzlx);
				listMap = nisYzdyDao.queryRestructPrint(parameters);
			}
//			else if("qbdy".equals(dylx)) {
//				parameters.put("zyh", cisHzyzBookPrintReq.getZyh());
//				parameters.put("yzlx", yzlx);
//				listMap = nisYzdyDao.queryResumePrint(parameters);
//			}
			if(CollectionUtils.isEmpty(listMap)){
				return null;
			}
			
			
			Map<String, Object> yzbxhMap = new HashMap<String, Object>();
			//过滤出JLXH为null的数据，该数据为要打印的组套数据
			for(Map<String,Object> map : listMap){
				if(map.get("JLXH") == null || map.get("JLXH").toString().length() == 0){
//					if(yzbxhMap.containsKey(ObjectToTypes.parseString(map.get("YZBXH")))) {
//						continue;
//					}
					Map<String,Object> ztjlMap = cisHzyzDao.queryZtOrderPrintInfo(ObjectToTypes.parseInt(map.get("YZBXH")));
					if(ztjlMap != null){
						map.putAll(ztjlMap);
//						yzbxhMap.put(ObjectToTypes.parseString(map.get("YZBXH")), ztjlMap);
					}
				}
			}
			System.out.println("listMap" + listMap );
			Map<String,Object> firstMap = listMap.get(0);
			int firsthh = ObjectToTypes.parseInt(firstMap.get("DYHH"));
			//填充空行
			for(int i = 1; i < firsthh; i++){
				Map<String,Object> dyMap = new HashMap<String,Object>(16);
				dyMap.put("KH", "　");
				responseList.add(dyMap);
			}
			
			sysXtcs = sysXtcsCacheSer.getByJGIdAndCsmc(jgid, SysXtcsCsmcCts.YZTDMYTS);
			//每页打印行数
			Integer yztdmyts = sysXtcs == null ? 20 : ObjectToTypes.parseInt(sysXtcs.getCsz());
			for(int i = 0; i < listMap.size(); i++){
				if(i != 0){
					//如果不是第一条记录,与前一条记录判断中间的行数间隔
					int syhDyym = ObjectToTypes.parseInt(listMap.get(i-1).get("DYYM"));//上一行记录的打印页码
					int syhDyhh = ObjectToTypes.parseInt(listMap.get(i-1).get("DYHH"));//上一行记录的打印行号
					int dyym = ObjectToTypes.parseInt(listMap.get(i).get("DYYM"));//当前行记录的打印页码
					int dyhh = ObjectToTypes.parseInt(listMap.get(i).get("DYHH"));//当前行记录的打印行号
					if(syhDyym == dyym && dyhh - syhDyhh>1){
						//中间有空行
						for(int j = 1; j < dyhh - syhDyhh; j++){
							Map<String,Object> dyMap = new HashMap<String,Object>(16);
							dyMap.put("KH", "　");
							dyMap.put("DYYM", dyym);
							responseList.add(dyMap);
						}
					}else if(syhDyym<dyym){
						//差页
						for(int j = 0; j < yztdmyts - syhDyhh; j++){
							//填充上页剩余行数
							Map<String,Object> dyMap=new HashMap<String,Object>(16);
							dyMap.put("KH", "　");
							dyMap.put("DYYM", dyym);
							responseList.add(dyMap);
						}
						//这里看情况修改,当上下2条记录的页码相差2页以上时,中间完全空白的也要不要打印
						if(syhDyym > dyym - 1){
							//差多页时填充整页空白,根据实际情况修改是否需要
							for(int x= 0; x < (dyym - syhDyym - 1) * yztdmyts; x++){
								Map<String,Object> dyMap = new HashMap<String,Object>(16);
								dyMap.put("KH", "　");
								dyMap.put("DYYM", dyym);
								responseList.add(dyMap);
							}
						}
						for(int k=1;k<dyhh;k++){
							//填充当前页空行
							Map<String,Object> dyMap = new HashMap<String,Object>(16);
							dyMap.put("DYYM", dyym);
							responseList.add(dyMap);
						}
					}
					
				}
				Map<String,Object> jlMap = listMap.get(i);
				int dyym = ObjectToTypes.parseInt(jlMap.get("DYYM"));//当前行记录的打印页码
				Map<String,Object> dyMap = new HashMap<String,Object>(16);
				dyMap.put("ZXSJ", "　");
				dyMap.put("DYYM", dyym);
				if(jlMap.get("KSSJ") == null){
					dyMap.put("KZRQ", "　");
					dyMap.put("KZSJ", "　");
				}else{
					dyMap.put("KZRQ", sdfo.format(sdf.parse(jlMap.get("KSSJ")+"")));
					dyMap.put("KZSJ", sdft.format(sdf.parse(jlMap.get("KSSJ")+"")));
				}
				String yzmc = ObjectToTypes.parseString(jlMap.get("YZMC"));
				if(ObjectToTypes.parseInt(jlMap.get("YZZF"))==1){
					//作废的医嘱加横线
					yzmc="<font style='font-size:9px; text-decoration: line-through;'>" + yzmc + "</font>";
				}
//				if(getBytesStrLength(yzmc) > 40){
//					dyMap.put("YZMC","<font style='font-size:8px'>"+yzmc+"</font>");
//				}else{
					dyMap.put("YZMC","<font style='font-size:9px;'>"+yzmc+"</font>");
//				}
				dyMap.put("HDQM","".equals(ObjectToTypes.parseString(jlMap.get("FHGHMC"))) ? "　" : ObjectToTypes.parseString(jlMap.get("FHGHMC")));
				dyMap.put("YSQM", "".equals(ObjectToTypes.parseString(jlMap.get("YSGHMC"))) ? "　" : ObjectToTypes.parseString(jlMap.get("YSGHMC")));
				dyMap.put("HSQM", "".equals(ObjectToTypes.parseString(jlMap.get("ZXFHGHMC"))) ? "　": ObjectToTypes.parseString(jlMap.get("ZXFHGHMC")));
				if(jlMap.get("TZSJ")==null){
					dyMap.put("TZRQ", "　");
					dyMap.put("TZSJ", "　");
				}else{
					dyMap.put("TZRQ", "".equals(sdfo.format(sdf.parse(jlMap.get("TZSJ")+"")))?"　":sdfo.format(sdf.parse(jlMap.get("TZSJ")+"")));
					dyMap.put("TZSJ", "".equals(sdft.format(sdf.parse(jlMap.get("TZSJ")+"")))?"　":sdft.format(sdf.parse(jlMap.get("TZSJ")+"")));
				}
				dyMap.put("TZYS", "".equals(ObjectToTypes.parseString(jlMap.get("TZYSMC")))?"　":ObjectToTypes.parseString(jlMap.get("TZYSMC")));
				dyMap.put("TZHS", "".equals(ObjectToTypes.parseString(jlMap.get("TZFHRMC")))?"　":ObjectToTypes.parseString(jlMap.get("TZFHRMC")));
				dyMap.put("ZXSJ", jlMap.get("ZXSJ") != null ? jlMap.get("ZXSJ") : "　");
				if("重整医嘱".equals(yzmc) || "转科后医嘱".equals(yzmc) || "术后医嘱".equals(yzmc)){
					dyMap.put("HSQM", "　");
					if(ObjectToTypes.parseInt(jlMap.get("DYHH")) == 1){
						dyMap.put("CZBZ1", "1");
					}else{
						dyMap.put("CZBZ", "1");
					}
					if("重整医嘱".equals(yzmc)){
						dyMap.put("KZRQ", jlMap.get("RQ")+"");
						dyMap.put("KZSJ", jlMap.get("SJ")+"");
					}
					//增加这些代码 为了转科打印  转科记录不打印医生和护士
					if(jlMap.get("CZYS") != null){
						dyMap.put("YSQM", ObjectToTypes.parseString(jlMap.get("CZYS")));
					}
					if(jlMap.get("CZHS") != null){
						dyMap.put("HSQM", ObjectToTypes.parseString(jlMap.get("CZHS")));
					}
					dyMap.put("YZMC","<font style='font-size:9px;'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+yzmc+"</font>");
					if(responseList.size() > 0){							
						responseList.get(responseList.size() - 1).put("CZX", "true");
					}
				}
				//空行特殊处理
				if(ObjectToTypes.parseInt(jlMap.get("YZBXH")) == -1){
					dyMap.put("KZRQ", "- - - ");
					dyMap.put("KZSJ", "- - - ");
					dyMap.put("YZMC", "- - - - - - - - - - - - - - - - - ");
					dyMap.put("YSQM", "- - - ");
					dyMap.put("HDQM", "- - - ");
					dyMap.put("HSQM", "- - - ");
					dyMap.put("TZRQ", "- - - ");
					dyMap.put("TZSJ", "- - - ");
					dyMap.put("TZYS", "- - - ");
					dyMap.put("TZHS", "- - - ");
					dyMap.put("ZXSJ", "- - - ");
					
				}
				dyMap.put("ZH", "　");
				long count = 0L;
				if(jlMap.get("YZZH") != null) {
					CisHzyz cisHzyz = new CisHzyz();
					cisHzyz.setYzzh(ObjectToTypes.parseInt(jlMap.get("YZZH")));
					count = cisHzyzDao.findByEntityCount(cisHzyz);
				}

				if(i > 0) {
					if(ObjectToTypes.parseInt(jlMap.get("YZBXH")) == ObjectToTypes.parseInt(listMap.get(i - 1).get("YZBXH"))) {
						dyMap.put("KZRQ", "　");
						dyMap.put("KZSJ", "　");
					}
				}
				if(count > 1) {
					if(i!=0){//实现相同日期只显示第一条和最后一条,同组加符号
						//相同日期只显示第一条和最后一条
						if(jlMap.get("KSSJ")!=null){
							if(listMap.get(i-1).get("KSSJ") != null && !"转科后医嘱".equals(ObjectToTypes.parseString(jlMap.get("YZMC")))
									&& !"术后医嘱".equals(ObjectToTypes.parseString(jlMap.get("YZMC")))){
								//判断日期和上一条是否相同
								if(sdfo.format(sdf.parse(jlMap.get("KSSJ")+"")).equals(sdfo.format(sdf.parse(listMap.get(i - 1).get("KSSJ")+"")))){
									//判断是否还有下一条
									if(i < listMap.size()- 1){
										//如果和上一条不是同组
										if(ObjectToTypes.parseInt(jlMap.get("YZZH")) != ObjectToTypes.parseInt(listMap.get(i - 1).get("YZZH"))){
											dyMap.put("KZRQ", "　");
										}else{
											if(listMap.get(i+1).get("KSSJ")!=null && !"转科后医嘱".equals(ObjectToTypes.parseString(jlMap.get("YZMC")))
													&& !"术后医嘱".equals(ObjectToTypes.parseString(jlMap.get("YZMC")))){
												//判断是否和下一条日期相同
												if(sdfo.format(sdf.parse(jlMap.get("KSSJ")+"")).equals(sdfo.format(sdf.parse(listMap.get(i + 1).get("KSSJ")+"")))){
													//如果和下一条是同组
													if(ObjectToTypes.parseInt(jlMap.get("YZZH")) == ObjectToTypes.parseInt(listMap.get(i + 1).get("YZZH"))){
														dyMap.put("KZRQ", "　");
														dyMap.put("KZSJ", "　");
													}else{
														dyMap.put("KZRQ", "　");
													}
												}else{
													//如果和上一条是同组
													if(ObjectToTypes.parseInt(jlMap.get("YZZH")) == ObjectToTypes.parseInt(listMap.get(i - 1).get("YZZH"))) {	
														dyMap.put("KZRQ", "　");
														dyMap.put("KZSJ", "　");
													}else {
														dyMap.put("KZRQ", "　");
													}												
												}
											}
										}
									}else {
										if(ObjectToTypes.parseInt(jlMap.get("YZZH")) == ObjectToTypes.parseInt(listMap.get(i - 1).get("YZZH"))) {	
											dyMap.put("KZRQ", "　");
											dyMap.put("KZSJ", "　");
										}else {
											if(sdfo.format(sdf.parse(jlMap.get("KSSJ")+"")).equals(sdfo.format(sdf.parse(listMap.get(i - 1).get("KSSJ")+"")))) {
												dyMap.put("KZRQ", "　");
											}
										}												
									}
								}
							}
						}
						if(ObjectToTypes.parseInt(jlMap.get("YZZH")) == ObjectToTypes.parseInt(listMap.get(i - 1).get("YZZH"))
								&& ObjectToTypes.parseInt(jlMap.get("YZBXH")) != -1 && ObjectToTypes.parseInt(jlMap.get("YZBXH"))!=0){
							if((i + 1) < listMap.size()){
								if(ObjectToTypes.parseInt(jlMap.get("YZZH")) == ObjectToTypes.parseInt(listMap.get(i + 1).get("YZZH"))){
									dyMap.put("ZH", "┃");
								}else{
									dyMap.put("ZH", "┛");
								}
							}else{
								dyMap.put("ZH", "┛");
							}
						}else{
							if((i + 1) < listMap.size()){
								if(ObjectToTypes.parseInt(jlMap.get("YZZH")) == ObjectToTypes.parseInt(listMap.get(i+1).get("YZZH")) 
										&& ObjectToTypes.parseInt(jlMap.get("YZBXH")) != -1){
									dyMap.put("ZH", "┓");
								}
							}
						}
					}
					if(i==0){
						if((i+1)<listMap.size()){
							if(ObjectToTypes.parseInt(jlMap.get("YZZH")) == ObjectToTypes.parseInt(listMap.get(i+1).get("YZZH"))
									&& ObjectToTypes.parseInt(jlMap.get("YZBXH"))!=-1&&ObjectToTypes.parseInt(jlMap.get("YZBXH")) != 0){
								dyMap.put("ZH", "┓");
							}
						}
					}
				}
				//停嘱的继续打印 判断以后是否打过 如果打过则只打印停嘱部分
				if("jxdy".equals(dylx) && cisHzyzBookPrintReq.getYzzt().intValue() == 2){
					int sfdy=0;
					for(Integer tzdyxh : tzdyxhList){
						if(tzdyxh.intValue() == ObjectToTypes.parseInt(jlMap.get("DYXH"))){
							dyMap.put("KZRQ", "");
							dyMap.put("KZSJ", "");
							dyMap.put("YZMC", "");
							dyMap.put("YSQM", "");
							dyMap.put("HSQM", "");
							dyMap.put("ZH", "");
							if(sftd != 1){
								dyMap.remove("KZRQ");
								dyMap.remove("KZSJ");
								dyMap.remove("YZMC");
								dyMap.remove("YSQM");
								dyMap.remove("HSQM");
								dyMap.remove("HDQM");
								dyMap.remove("ZH");
								dyMap.put("TZRQ1", dyMap.get("TZRQ"));
								dyMap.remove("TZRQ");
								dyMap.put("TZSJ1", dyMap.get("TZSJ"));
								dyMap.remove("TZSJ");
								dyMap.put("TZYS1", dyMap.get("TZYS"));
								dyMap.remove("TZYS");
								dyMap.put("TZHS1", dyMap.get("TZHS"));
								dyMap.remove("TZHS");
							}
							sfdy = 1;
							break;
						}
					}
					if(sfdy == 0){
						//如果没打印过,则整条数据套打
						if(ObjectToTypes.parseInt(firstMap.get("DYHH") )> 1 && 
								ObjectToTypes.parseInt(firstMap.get("DYYM")) == ObjectToTypes.parseInt(jlMap.get("DYYM"))){
							//第一条记录不是第一行 当前页所有记录都套打
							if(sftd != 1){
								dyMap.put("KH", "　");
								dyMap.put("KZRQ1", dyMap.get("KZRQ"));
								dyMap.remove("KZRQ");
								dyMap.put("KZSJ1", dyMap.get("KZSJ"));
								dyMap.remove("KZSJ");
								dyMap.put("YZMC1", dyMap.get("YZMC"));
								dyMap.remove("YZMC");
								dyMap.put("YSQM1", dyMap.get("YSQM"));
								dyMap.remove("YSQM");
								dyMap.put("HSQM1", dyMap.get("HSQM"));
								dyMap.remove("HSQM");
								dyMap.put("HDQM1", dyMap.get("HDQM"));
								dyMap.remove("HDQM");
								dyMap.put("ZH1", dyMap.get("ZH"));
								dyMap.remove("ZH");
								dyMap.put("TZRQ1", dyMap.get("TZRQ"));
								dyMap.remove("TZRQ");
								dyMap.put("TZSJ1", dyMap.get("TZSJ"));
								dyMap.remove("TZSJ");
								dyMap.put("TZYS1", dyMap.get("TZYS"));
								dyMap.remove("TZYS");
								dyMap.put("TZHS1", dyMap.get("TZHS"));
								dyMap.remove("TZHS");
								dyMap.put("ZXSJ1", dyMap.get("ZXSJ"));
								dyMap.remove("ZXSJ");
							}
						}
					}
				}else{
					//第一条记录不是第一行 当前页所有记录都套打
					if(ObjectToTypes.parseInt(firstMap.get("DYHH")) > 1 && 
							ObjectToTypes.parseInt(firstMap.get("DYYM")) == ObjectToTypes.parseInt(jlMap.get("DYYM"))){
						if(sftd != 1){
							dyMap.put("KH", "　");
							dyMap.put("KZRQ1", dyMap.get("KZRQ"));
							dyMap.remove("KZRQ");
							dyMap.put("KZSJ1", dyMap.get("KZSJ"));
							dyMap.remove("KZSJ");
							dyMap.put("YZMC1", dyMap.get("YZMC"));
							dyMap.remove("YZMC");
							dyMap.put("YSQM1", dyMap.get("YSQM"));
							dyMap.remove("YSQM");
							dyMap.put("HSQM1", dyMap.get("HSQM"));
							dyMap.remove("HSQM");
							dyMap.put("HDQM1", dyMap.get("HDQM"));
							dyMap.remove("HDQM");
							dyMap.put("ZH1", dyMap.get("ZH"));
							dyMap.remove("ZH");
							dyMap.put("TZRQ1", dyMap.get("TZRQ"));
							dyMap.remove("TZRQ");
							dyMap.put("TZSJ1", dyMap.get("TZSJ"));
							dyMap.remove("TZSJ");
							dyMap.put("TZYS1", dyMap.get("TZYS"));
							dyMap.remove("TZYS");
							dyMap.put("TZHS1", dyMap.get("TZHS"));
							dyMap.remove("TZHS");
							dyMap.put("ZXSJ1", dyMap.get("ZXSJ"));
							dyMap.remove("ZXSJ");
						}
					}
				}
				responseList.add(dyMap);
			}
			//补全最后一页的格子
			//如果第一条记录是第一行或者有多页,那么补全最后一页的打印格子
			if((ObjectToTypes.parseInt(firstMap.get("DYYM")) != ObjectToTypes.parseInt(listMap.get(listMap.size()-1).get("DYYM"))
					|| ObjectToTypes.parseInt(firstMap.get("DYHH")) == 1) && 
					responseList.get(responseList.size()-1).containsKey("YZMC")){
				//最后行
				int zhh = ObjectToTypes.parseInt(listMap.get(listMap.size()-1).get("DYHH"));
				for(int i = zhh; i < yztdmyts; i++){
					Map<String,Object> dyMap = new HashMap<String,Object>(16);
					dyMap.put("KZRQ", "　");
					dyMap.put("KZSJ", "　");
					dyMap.put("YZMC", "　");
					dyMap.put("YSQM", "　");
					dyMap.put("HDQM", "　");
					dyMap.put("HSQM", "　");
					dyMap.put("ZH", "　");
					dyMap.put("TZRQ", "　");
					dyMap.put("TZSJ", "　");
					dyMap.put("TZYS", "　");
					dyMap.put("TZHS", "　");
					dyMap.put("ZXSJ", "　");
					dyMap.put("DYYM", responseList.get(responseList.size() - 1).get("DYYM"));				
					responseList.add(dyMap);
				}
			}else{
				//最后行
				int zhh=ObjectToTypes.parseInt(listMap.get(listMap.size() - 1).get("DYHH"));
				for(int i = zhh ; i < yztdmyts; i++){
					Map<String,Object> dyMap = new HashMap<String,Object>(16);						
					dyMap.put("KH", "　");
					responseList.add(dyMap);
				}
			}
		} catch (ParseException e) {
			throw BaseException.create("ERROR_BOOK_PRINT_0005");
		}
		return responseList;
	}
	
	
	/**
	 * 是否套打
	 * @Title: getSftd
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @param dylx
	 * @param @param zyxhs
	 * @param @param tzyzs
	 * @param @param dyhh
	 * @param @return    设定文件
	 * @return int    返回类型
	 * @author 龚方舟
	 * @throws
	 */
	public int getSftd(String dylx, String zyxhs, String tzyzs, Integer dyhh) {
		if("jxdy".equals(dylx)){
			if("".equals(zyxhs)){
				return 0;
			}
			String[] dyyzs = zyxhs.split(",");
			List<Integer> dyxhList = new ArrayList<Integer>();//打印的医嘱集合
			for(String dyyz : dyyzs){
				dyxhList.add(ObjectToTypes.parseInt(dyyz));
			}
			List<Map<String,Object>> dyjlList = nisYzdyDao.queryDyInfoByDyxh(dyxhList);
			if(CollectionUtils.isEmpty(dyjlList)){
				return 0;
			}
			Map<String,Object> dyjlMap = dyjlList.get(0);
			if (StrUtil.isNotBlank(tzyzs)) {//停嘱的,如果已经打印过的记录,哪怕第一行也要套打,如果非第一行并且已经打印过 则也套打
				String[] tzyz = tzyzs.split(",");
				int dyym = ObjectToTypes.parseInt(dyjlMap.get("DYYM"));
				int k = 0;
				for (String tz : tzyz) {
					if(ObjectToTypes.parseInt(tz) == ObjectToTypes.parseInt(dyjlMap.get("DYXH"))){
						k++;
					}
				}
				if(k==1){
					for(Map<String,Object> dyjl : dyjlList){
						if(ObjectToTypes.parseInt(dyjl.get("DYYM"))> dyym){
							for (String tz : tzyz) {
								if(ObjectToTypes.parseInt(tz) == ObjectToTypes.parseInt(dyjl.get("DYXH"))){
									k++;
									dyym = ObjectToTypes.parseInt(dyjl.get("DYYM"));
								}
							}
						}
					}
				}
				return k;
			}else{
				if(ObjectToTypes.parseInt(dyjlMap.get("DYHH")) != 1){
					return 1;
				}
			}
			return 0;
		}else if("aydy".equals(dylx)){
			return 0;
		}else if("zdhdy".equals(dylx)){
			if(dyhh.intValue() == 1){
				return 0;
			}
			return 1;
		}else if("czdy".equals(dylx)){
			return 0;
		}
		return 0;
	}
	
	
	
	/**
	 * 指定行查询打印记录
	 * @Title: queryDyjl
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @param zyh
	 * @param @param yzlx
	 * @param @return    设定文件
	 * @return List<CisHzyzPrintRecordResp>    返回类型
	 * @author 龚方舟
	 * @throws
	 */
	public List<CisHzyzPrintRecordResp> queryDyjl(Integer zyh, Integer yzlx){
		Map<String, Object> parameters = new HashMap<String, Object>(16);
		parameters.put("zyh", zyh);
		//医嘱期效,0是长期医嘱1是临时医嘱
		parameters.put("yzlx", yzlx);
		List<Map<String, Object>> listMap = nisYzdyDao.querySpecifyLinePrint(parameters);
			 
		// 过滤出JLXH为null的数据，该数据为要打印的组套数据
		for (Map<String, Object> map : listMap) {
			if (map.get("JLXHS") == null || map.get("JLXHS").toString().length() == 0) {
				Map<String,Object> ztjlMap = cisHzyzDao.queryZtOrderPrintInfo(ObjectToTypes.parseInt(map.get("YZBXH")));
				if(ztjlMap != null){
					map.putAll(ztjlMap);
				}
			}
		}

		for (int i = 0; i < listMap.size(); i++) {
			if ("重整医嘱".equals(ObjectToTypes.parseString(listMap.get(i).get("YZMC")))) {
				listMap.get(i).put("KSSJ", listMap.get(i).get("DYRQ"));
				listMap.get(i).put("YSGHMC", listMap.get(i).get("CZYS"));
				listMap.get(i).put("FHGHMC", listMap.get(i).get("CZHS"));
			}
		}

		return BUHISUtil.ListToResultSet(listMap, new CisHzyzPrintRecordResp());
	}
	
	/**
	 * @throws Exception 
	 * 全部医嘱打印
	 * @Title: getAllOrdersPrint
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @param req
	 * @param @param user
	 * @param @return    设定文件
	 * @return List<Map<String,Object>>    返回类型
	 * @author 龚方舟
	 * @throws
	 */
	public List<Map<String, Object>> getAllOrdersPrint(CisHzyzBookPrintReq req, SysUser user) throws Exception{
		Integer jgid = user.getHospitalId();
		//医嘱本打印时间,1是提交后打印,2是复核后打印,默认为2
		SysXtcs sysXtcs = sysXtcsCacheSer.getByJGIdAndCsmc(jgid, SysXtcsCsmcCts.YZBDYSJ);
		Integer yzbdysj = sysXtcs == null ? 2 : ObjectToTypes.parseInt(sysXtcs.getCsz());
		//每页打印行数
		sysXtcs = sysXtcsCacheSer.getByJGIdAndCsmc(jgid, SysXtcsCsmcCts.YZTDMYTS);
		Integer yztdmyts = sysXtcs == null ? 20 : ObjectToTypes.parseInt(sysXtcs.getCsz());
		Integer yzlx = req.getYzlx();
		Integer zjs = req.getYzlx() == 0 ? 54 : 62;
		DecimalFormat dft = new DecimalFormat("##########.##########");
		List<CisHzyzOrderBookResp> cisHzyzOrderBookRespList = cisHzyzDao.queryMedicalOrderInfo(yzlx, req.getZyh(),
		    		null, null, jgid, yzbdysj);
		Map<String, Object> jlxhMap = new HashMap<String, Object>(16);
		List<Map<String, Object>> respList = new ArrayList<Map<String,Object>>();
		List<CisHzyzOrderBookResp> reslist = new ArrayList<CisHzyzOrderBookResp>();
		if(CollectionUtils.isNotEmpty(cisHzyzOrderBookRespList)) {
			for(int i = 0; i < cisHzyzOrderBookRespList.size(); i++) {
				CisHzyzOrderBookResp cis = cisHzyzOrderBookRespList.get(i);
				if(StrUtil.isNotBlank(ObjectToTypes.parseString(cis.getZtbz())) && "1".equals(ObjectToTypes.parseString(cis.getZtbz()))) {
					//改变组套内容
					if (jlxhMap.containsKey(ObjectToTypes.parseString(cis.getZtyzjlxh()))) {
						continue;
					}
					CisHzyzZt cisHzyzZt = cisHzyzZtDao.getById(cis.getZtyzjlxh());
					String zxfhmc = cis.getZxfhghmc();
					Map<String, Object> record = BUHISUtil.caseInsensitiveMap(cisHzyzZt);
					cis = BeanUtil.fillBeanWithMapIgnoreCase(record, new CisHzyzOrderBookResp(), true);
					cis.setYsghmc(hrPersonnelService.getPersonnelById(cis.getYsgh()).getPersonname());
					cis.setFhghmc(hrPersonnelService.getPersonnelById(cis.getFhgh()).getPersonname());
					cis.setZxfhghmc(zxfhmc);
					jlxhMap.put(ObjectToTypes.parseString(cis.getJlxh()), cis);

				}
				if("1".equals(ObjectToTypes.parseString(cis.getJfyz()))) {
					continue;
				}
				
				if (cis.getXmlx().intValue() >= 4) {
					cis.setJldw("");
				}
				
				String yzmc = cis.getYzmc();
				String ypyfmc = cis.getYpyfmc() == null ? "" : cis.getYpyfmc();
				String sypcmc = cis.getSypcmc() == null ? "" : cis.getSypcmc();
				String jldw = cis.getJldw() == null ? "" : cis.getJldw();
				
				//临时医嘱
				if(yzlx == 1){
					//药品类型 <> 0   药品 
					if(!"0".equals(ObjectToTypes.parseString(cis.getYplx()))){
						if("3".equals(ObjectToTypes.parseString(cis.getYplx()))){
							//草药
							yzmc += " " + (cis.getYcjl().compareTo(BigDecimal.ZERO) == 0 ? "": cis.getYcjl()) + jldw + " ";	
							yzmc += " ×" + cis.getCfts() + " , ";
							yzmc += ypyfmc + " " + sypcmc;
						}else{
							//西药
							yzmc += (cis.getYcsl().compareTo(BigDecimal.ZERO) == 0 ? "" : "/  ×" + dft.format(cis.getYcsl()) + cis.getYfdw()) + " ,";
							yzmc += " "+ (cis.getYcjl().compareTo(BigDecimal.ZERO) == 0  ? "": cis.getYcjl()) + jldw + " ";	
							yzmc += ypyfmc + " " + sypcmc;
						}
						
					}else{
						//项目或文字医嘱
						if(!StrUtil.isBlankIfStr(cis.getYjfygb())){
							//项目
							yzmc += " " + sypcmc;
							yzmc += (cis.getYcsl().compareTo(BigDecimal.ZERO) == 0 ? "" : " ×" + dft.format(cis.getYcsl()) )+ "";
						}else{ 
							if(!StrUtil.isBlankIfStr(cis.getYzlx()) && cis.getYzlx() == 4) {
								yzmc += ypyfmc;
							}else {
								//或文字医嘱				
								yzmc += ypyfmc + " " + sypcmc;
							}
						}
					}
				}else{
					yzmc += " " + (cis.getYcjl().compareTo(BigDecimal.ZERO) == 0 ? "" : dft.format(cis.getYcjl())) + jldw + " ";	
					yzmc += ypyfmc + " " + sypcmc;
				}
				
				if(cis.getZfyp().intValue() == 1){
					yzmc ="(自备)" + yzmc;
				}
				if(cis.getJfbz() != null && cis.getJfbz().intValue() != 3 && cis.getBzxx() != null 
						&& cis.getBzxx().trim().length()>0) {
					yzmc +=" (" + (cis.getBzxx()+ ")");
				}
				cis.setYzmc(yzmc);
				reslist.add(cis);
			}
		}
		Map<Integer, List<CisHzyzOrderBookResp>> groups = reslist.stream().
				collect(Collectors.groupingBy(CisHzyzOrderBookResp::getYzzh));
		List<CisHzyzOrderBookResp> zhList = new ArrayList<CisHzyzOrderBookResp>();
		for(int i = 0; i < reslist.size(); i++) {
			CisHzyzOrderBookResp zhResp = reslist.get(i);
			String yzmc = zhResp.getYzmc();
			List<String> yzmcList = BytesUtil.getListStringBytes(yzmc, zjs);
			if(yzmcList.size() > 1) {
				for(int j = 0; j < yzmcList.size(); j++) {
					if(j == 0) {
						zhResp.setYzmc(yzmcList.get(j));
						zhList.add(zhResp);
					}else {
						CisHzyzOrderBookResp newResp = new CisHzyzOrderBookResp();
						BeanUtil.copyProperties(zhResp, newResp, 
								CopyOptions.create().setIgnoreNullValue(true).setIgnoreError(true));
						newResp.setYzmc(yzmcList.get(j));
//						newResp.setKssj(null);
						zhList.add(newResp);
					}
				}
			}else {
				zhList.add(zhResp);
			}
		}
		reslist = zhList;
		if(CollectionUtils.isNotEmpty(reslist)) {
			for(int i = 0; i < reslist.size(); i ++) {
				CisHzyzOrderBookResp resp = reslist.get(i);
				Map<String, Object> resMap = new HashMap<String, Object>(16);
				String yzmc = resp.getYzmc();
				Integer yzzh = resp.getYzzh();
				if("302".equals(resp.getYdlb())) {
					resMap.put("YZMC", "<font style='font-size:9px;'>&nbsp;&nbsp;"
							+ "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+"转科后医嘱"+"</font>");
					respList.add(resMap);
					continue;
				}
				resMap.put("KZRQ", resp.getKssj() != null ? DateUtil.format(resp.getKssj(), "yy-MM-dd") : "　");
				resMap.put("KZSJ", resp.getKssj() != null ? DateUtil.format(resp.getKssj(), "HH:mm") : "　");
				resMap.put("YZMC", "<font style='font-size:9px;'>" + yzmc + "</font>");
				resMap.put("ZH", "　");
				resMap.put("YSQM", StrUtil.isNotBlank(resp.getYsghmc()) ? resp.getYsghmc() : "　");
				resMap.put("HDQM", StrUtil.isNotBlank(resp.getFhghmc()) ? resp.getFhghmc() : "　");
				resMap.put("HSQM", "　");
				resMap.put("TZRQ", resp.getTzsj() != null ? DateUtil.format(resp.getTzsj(), "yy-MM-dd")  : "　");
				resMap.put("TZSJ", resp.getTzsj() != null ? DateUtil.format(resp.getTzsj(), "HH:mm") : "　");
				resMap.put("TZYS", StrUtil.isNotBlank(resp.getTzysmc()) ? resp.getTzysmc() : "　");
				resMap.put("TZHS", "　");
				if(resp.getYzlx() != null && resp.getYzlx() == 3) {
					respList.add(resMap);
					resMap = new HashMap<String, Object>(16);
					resMap.put("YZMC", "<font style='font-size:9px;'>&nbsp;&nbsp;"
							+ "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+"术后医嘱"+"</font>");
					respList.add(resMap);
					continue;
				}
				if("1".equals(ObjectToTypes.parseString(resp.getZfbz()))){
					//作废的医嘱加横线
					resMap.put("YZMC", "<font style='font-size:9px; text-decoration: line-through;'>" + yzmc + "</font>");
				}
				if(i > 0) {
					if(resp.getJlxh().intValue() == reslist.get(i - 1).getJlxh().intValue()) {
						resMap.put("KZRQ", "　");
						resMap.put("KZSJ", "　");
					}
				}
				for (Map.Entry<Integer, List<CisHzyzOrderBookResp>> entry : groups.entrySet()){
			       if(yzzh.intValue() == entry.getKey().intValue() && entry.getValue().size() > 1) {
						if(i != 0){//实现相同日期只显示第一条和最后一条,同组加符号
							//相同日期只显示第一条和最后一条
							if(resp.getKssj() != null){
								if(reslist.get(i-1).getKssj() != null && !"302".equals(resp.getYdlb())){
									//判断日期和上一条是否相同
									if(DateUtil.format(resp.getKssj(), DatePattern.NORM_DATE_PATTERN).toString().equals(
											DateUtil.format(reslist.get(i-1).getKssj(), DatePattern.NORM_DATE_PATTERN).toString())){
										//判断是否还有下一条
										if(i < reslist.size()- 1){
											//如果和上一条不是同组
											if(resp.getYzzh().intValue() != reslist.get(i-1).getYzzh().intValue()){
												resMap.put("KZRQ", "　");
											}else{
											    if(reslist.get(i+1).getKssj() != null && !"302".equals(resp.getYdlb())){
													//判断是否和下一条日期相同
													if(DateUtil.format(resp.getKssj(), DatePattern.NORM_DATE_PATTERN).toString().equals(
															DateUtil.format(reslist.get(i+1).getKssj(), DatePattern.NORM_DATE_PATTERN).toString())){
														//如果和下一条是同组
														if(resp.getYzzh().intValue() == reslist.get(i+1).getYzzh().intValue()){
															resMap.put("KZRQ", "　");
															resMap.put("KZSJ", "　");
														}else{
															resMap.put("KZRQ", "　");
														}
													}else{
														//如果和上一条是同组
														if(resp.getYzzh().intValue() == reslist.get(i-1).getYzzh().intValue()) {	
															resMap.put("KZRQ", "　");
															resMap.put("KZSJ", "　");
														}else {
															resMap.put("KZRQ", "　");
														}												
													}
												}
											}
										}else {
											if(resp.getYzzh().intValue() == reslist.get(i-1).getYzzh().intValue()) {	
												resMap.put("KZRQ", "　");
												resMap.put("KZSJ", "　");
											}else {
												if(DateUtil.format(resp.getKssj(), DatePattern.NORM_DATE_PATTERN).toString().equals(
														DateUtil.format(reslist.get(i-1).getKssj(), DatePattern.NORM_DATE_PATTERN).toString())) {
													resMap.put("KZRQ", "　");
												}
											}												
										}
									}
								}
							}
							if(resp.getYzzh().intValue() == reslist.get(i-1).getYzzh().intValue()){
								if((i+1) < reslist.size()){
									if(resp.getYzzh().intValue() == reslist.get(i+1).getYzzh().intValue()){
										resMap.put("ZH", "┃");
									}else{
										resMap.put("ZH", "┛");
									}
								}else{
									resMap.put("ZH", "┛");
								}
							}else{
								if((i+1) < reslist.size()){
									if(resp.getYzzh().intValue() == reslist.get(i+1).getYzzh().intValue()){
										resMap.put("ZH", "┓");
									}
								}
							}
						}
						if(i == 0){
							if((i+1) < reslist.size()){
								if(resp.getYzzh().intValue() == reslist.get(i+1).getYzzh().intValue()){
									resMap.put("ZH", "┓");
								}
							}
						}
			       }
			    }
				respList.add(resMap);
			}
		}
		int divNum = respList.size() % yztdmyts;
    	boolean flag = divNum == 0;
    	if(!flag) {
    		for(int i = 0; i < yztdmyts - divNum; i++ ) {
        		Map<String, Object> map = new HashMap<String, Object>(16);
        		map.put("KZRQ", "");
        		respList.add(map);
        	}
    	}
		return respList;
	}


}
