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
 * ???????????????<br>
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
	 * ???????????????????????????
	 * @Title: queryYz
	 * @Description: TODO(?????????????????????????????????????????????)
	 * @param @param zyh
	 * @param @param yzzt
	 * @param @param yzlx
	 * @param @param user
	 * @param @return    ????????????
	 * @return List<CisHzyzOrderBookResp>    ????????????
	 * @author ?????????
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
		//?????????????????????,1??????????????????,2??????????????????,?????????2
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
					//??????????????????????????????
					NisYzdy nisYzdy = new NisYzdy();
					nisYzdy.setYzbxh(cis.getZtyzjlxh());
					long count = nisYzdyDao.findByEntityCount(nisYzdy);
					if (count > 0) {
						continue;
					}
					//??????????????????
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
	 * ?????????????????????
	 * @Title: saveWardDoctorPrint
	 * @Description: TODO(?????????????????????????????????????????????)
	 * @param @param cisHzyzBookPrintReq
	 * @param @param user
	 * @param @return    ????????????
	 * @return CisHzyzBookPrintResp    ????????????
	 * @author ?????????
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
		if("jxdy".equals(type) || "qbdy".equals(type)){//????????????,??????????????????????????????????????? ??????????????????????????????
			List<CisHzyzOrderBookResp> list = queryYz(zyh, cisHzyzBookPrintReq.getYzzt(), yzlx, user);
			if(CollectionUtils.isEmpty(list)){
				return null;
			}
			
			SysXtcs sysXtcs = sysXtcsCacheSer.getByJGIdAndCsmc(jgid, SysXtcsCsmcCts.YZTDMYTS);
			//??????????????????
			Integer yztdmyts = sysXtcs == null ? 20 : ObjectToTypes.parseInt(sysXtcs.getCsz());
//			????????????????????????,??????????????????????????????????????????????????????, ??????????????????????????????????????????
//			if(yztdmyts > 34){//?????????????????????????????????????????????,??????????????????35???
//				ret.put("code", 9000);
//				ret.put("msg", "??????????????????????????????????????????????????????,???????????????,??????34?????????");
//				return ret;
//			}
			sysXtcs = sysXtcsCacheSer.getByJGIdAndCsmc(jgid, SysXtcsCsmcCts.ZKCZHHYDY);
			Integer zkczhhydy = sysXtcs == null ? 0 : ObjectToTypes.parseInt(sysXtcs.getCsz());	

			//??????????????????????????????
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


				//????????????????????????
				String ydlb = cis.getYdlb();
				if("302".equals(ydlb)){
					if(zkczhhydy == 1){
						if(dyhh != 1){
							int xh= yztdmyts - dyhh;
							for(int x = 0; x <= xh; x++){
							 	NisYzdy nisYzdySave = new NisYzdy();
							 	//-1??????????????????????????????????????????????????????
							 	nisYzdySave.setYzbxh(-1);
							 	nisYzdySave.setZyh(zyh);
							 	nisYzdySave.setYzqx(yzlx);
							 	nisYzdySave.setDyrq(DateUtil.date().toTimestamp());
							 	nisYzdySave.setDynr("??????");
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
				 	nisYzdySave.setDynr("???????????????");
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
				 	nisYzdySave.setDynr("????????????");
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
				///??????????????????????????????
				if(cis.getLsyz().intValue() != 1 && !StrUtil.isBlankIfStr(cis.getTzsj())){
					tzjlxhList.add(cis.getJlxh());
				}else{
					jlxhList.add(cis.getJlxh());
				}
				
				Integer dyxh = null;

				//????????????????????????????????????????????????????????????
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
					//????????????
					if(yzlx == 1){
						//???????????? <> 0   ?????? 
						if(!"0".equals(ObjectToTypes.parseString(cis.getYplx()))){
							if("3".equals(ObjectToTypes.parseString(cis.getYplx()))){
								//??????
								dynr += " " + (cis.getYcjl().compareTo(BigDecimal.ZERO) == 0 ? "": cis.getYcjl()) + jldw + " ";	
								dynr += " ??" + cis.getCfts() + " , ";
								dynr += ypyfmc + " " + sypcmc;
							}else{
								//??????
								dynr += (cis.getYcsl().compareTo(BigDecimal.ZERO) == 0 ? "" : "/  ??" + dft.format(cis.getYcsl()) + cis.getYfdw()) + " ,";
								dynr += " "+ (cis.getYcjl().compareTo(BigDecimal.ZERO) == 0  ? "": cis.getYcjl()) + jldw + " ";	
								dynr += ypyfmc + " " + sypcmc;
							}
							
						}else{
							//?????????????????????
							if(!StrUtil.isBlankIfStr(cis.getYjfygb())){
								//??????
								dynr += " " + sypcmc;
								dynr += (cis.getYcsl().compareTo(BigDecimal.ZERO) == 0 ? "" : " ??" + dft.format(cis.getYcsl()) )+ "";
							}else{ 
								if(!StrUtil.isBlankIfStr(cis.getYzlx()) && cis.getYzlx() == 4) {
									dynr += ypyfmc;
								}else {
									//???????????????				
									dynr += ypyfmc + " " + sypcmc;
								}
							}
						}
					}else{
						dynr += " " + (cis.getYcjl().compareTo(BigDecimal.ZERO) == 0 ? "" : dft.format(cis.getYcjl())) + jldw + " ";	
						dynr += ypyfmc + " " + sypcmc;
					}
					
					if(cis.getZfyp() != null && cis.getZfyp().intValue() == 1){
						dynr ="(??????)" + dynr;
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
			//????????????,??????????????????
			Integer yzzt = cisHzyzBookPrintReq.getYzzt();
			
			Set<Integer> set = new HashSet<Integer>();
			if(jlxhList.size() > 0){
				for(int i = 0;i < jlxhList.size(); i++){
					set.add(jlxhList.get(i));
				}
				//?????????????????????????????????
				//??????????????????????????????
				cisHzyzDao.updateDyztFilter(yzzt, set);
			}
			if(tzjlxhList.size() > 0){
				//??????????????????????????????(??????)
				cisHzyzDao.updateDyzt(tzjlxhList);
			}
		}else if("aydy".equals(type)){
			//????????????,?????????????????????????????????????????????
			long l = nisYzdyDao.queryIsExistPrintPage(cisHzyzBookPrintReq);
			if(l == 0){
				throw BaseException.create("ERROR_BOOK_PRINT_0003");
			}
		}else if("czdy".equals(type)){
			//????????????
			//???????????????????????????????????????????????????

			//???????????????????????????

			//?????????????????????,1??????????????????,2??????????????????,?????????2
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
			//??????????????????
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
				 	nisYzdySave.setDynr("??????");
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
		 	nisYzdySave.setDynr("????????????");
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
				//??????????????????????????????
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
				if(yzlx == 1){//????????????
					//???????????? <> 0   ?????? 
					if(!"0".equals(ObjectToTypes.parseString(cis.getYplx()))){
						if("3".equals(ObjectToTypes.parseString(cis.getYplx()))){
							//??????
							dynr += " "+ (cis.getYcjl().compareTo(BigDecimal.ZERO) == 0 ? "": cis.getYcjl()) + jldw +" ";	
							dynr += " ??" + cis.getCfts() + " , ";
							dynr += ypyfmc + " " + sypcmc;
						}else{
							//??????
							dynr += (cis.getYcsl().compareTo(BigDecimal.ZERO) == 0 ? "" : "/  ??" + dft.format(cis.getYcsl()) + cis.getYfdw()) + " ,";
							dynr += " " + (cis.getYcjl().compareTo(BigDecimal.ZERO) == 0 ? "" : cis.getYcjl()) + jldw + " ";	
							dynr += ypyfmc + " " + sypcmc;
						}
						
					}else{
						//?????????????????????
						if(!StrUtil.isBlankIfStr(cis.getYjfygb())){
							//??????
							dynr += " " + sypcmc;
							dynr += (cis.getYcsl().compareTo(BigDecimal.ZERO) == 0 ? "" : " ??" + dft.format(cis.getYcsl()) )+ "";
						}else{ 
							//???????????????	
							dynr += ypyfmc + " " + sypcmc;
							
						}
					}
				}else{
					dynr += " " + (cis.getYcjl().compareTo(BigDecimal.ZERO) == 0 ? "" : cis.getYcjl()) + cis.getJldw() + " ";	
					dynr += ypyfmc + " " + sypcmc;
				}
				
				if(cis.getZfyp() != null && cis.getZfyp().intValue() == 1){
					dynr="(??????)" + dynr;
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
	 * ????????????????????????
	 * @Title: getMaxDyjl
	 * @Description: TODO(?????????????????????????????????????????????)
	 * @param @param list
	 * @param @return    ????????????
	 * @return Map<String,Object>    ????????????
	 * @author ?????????
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
	 * ???????????????????????????
	 * @Title: getWardDoctorPrintParameters
	 * @Description: TODO(?????????????????????????????????????????????)
	 * @param @param zyh
	 * @param @param user
	 * @param @param dylx
	 * @param @param zyxhs
	 * @param @param tzyzs
	 * @param @param dyhh
	 * @param @return    ????????????
	 * @return Map<String,Object>    ????????????
	 * @author ?????????
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
		//??????????????????
		Integer yztdmyts = sysXtcs == null ? 20 : ObjectToTypes.parseInt(sysXtcs.getCsz());
		ImHzry imHzry = imHzryDao.getById(zyh);
		String brxb = "";
		if(imHzry.getBrxb() != null){
			brxb = ObjectToTypes.parseString(imHzry.getBrxb());
			if("0".equals(brxb)) {
				brxb = "???????????????";
			}else if("1".equals(brxb)) {
				brxb = "???";
			}else if("2".equals(brxb)) {
				brxb = "???";
			}else {
				brxb = "??????????????????";
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
			responseMap.put("TITLE", user.getHospitalName() + "???????????????" + (cisHzyzBookPrintReq.getYzlx() == 0? "(??????)" : "(??????)"));
		}else if(cisHzyzBookPrintReq.getYzlx() == 0) {
			responseMap.put("TITLE", user.getHospitalName() + "???????????????");
		}else if(cisHzyzBookPrintReq.getYzlx() == 1) {
			responseMap.put("TITLE", user.getHospitalName() + "???????????????");
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
		//????????????,?????????????????????
		String dylx = cisHzyzBookPrintReq.getType();
		//????????????,1???????????????2???????????????
		Integer yzlx = cisHzyzBookPrintReq.getYzlx();
		Map<String,Object> parameters = new HashMap<String,Object>(16);
		//????????????????????????????????????????????????????????????
		List<Integer> tzdyxhList = new ArrayList<Integer>();
		SysXtcs sysXtcs = sysXtcsCacheSer.getByJGIdAndCsmc(jgid, SysXtcsCsmcCts.YZBDYSFTD);
		//??????????????????
		Integer sftd = sysXtcs == null ? 0 : ObjectToTypes.parseInt(sysXtcs.getCsz());
		List<Map<String, Object>> listMap = null;
		try {
			if("jxdy".equals(dylx)){//????????????
				String zyxhs = cisHzyzBookPrintReq.getDyyzs();
				if("".equals(zyxhs)){
					return null;
				}
				String[] dyyzs = zyxhs.split(",");
				List<Integer> yzxhList = new ArrayList<Integer>();//?????????????????????
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
				//????????????
				//???????????????
				Integer pageTo = cisHzyzBookPrintReq.getPageTo();
				//?????????
				parameters.put("zyh", cisHzyzBookPrintReq.getZyh());
				//????????????,0???????????????1???????????????
				parameters.put("yzlx", yzlx);
				//???????????????
				parameters.put("pageFrom", cisHzyzBookPrintReq.getPageFrom());
				if(pageTo != 0){
					parameters.put("pageTo", pageTo);
				}
				listMap = nisYzdyDao.queryPagePrint(parameters);
			}else if("zdhdy".equals(dylx)){
				//?????????
				//?????????
				parameters.put("zyh", cisHzyzBookPrintReq.getZyh());
				//????????????,0???????????????1???????????????
				parameters.put("yzlx", yzlx);
				//??????
				parameters.put("dyym", cisHzyzBookPrintReq.getDyym());
				//??????
				parameters.put("dyhh", cisHzyzBookPrintReq.getDyhh());
				parameters.put("type", 1);
				listMap = nisYzdyDao.querySpecifyLinePrint(parameters);
			}else if("czdy".equals(dylx)){//??????
				//?????????
				parameters.put("zyh", cisHzyzBookPrintReq.getZyh());
				//????????????,0???????????????1???????????????
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
			//?????????JLXH???null????????????????????????????????????????????????
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
			//????????????
			for(int i = 1; i < firsthh; i++){
				Map<String,Object> dyMap = new HashMap<String,Object>(16);
				dyMap.put("KH", "???");
				responseList.add(dyMap);
			}
			
			sysXtcs = sysXtcsCacheSer.getByJGIdAndCsmc(jgid, SysXtcsCsmcCts.YZTDMYTS);
			//??????????????????
			Integer yztdmyts = sysXtcs == null ? 20 : ObjectToTypes.parseInt(sysXtcs.getCsz());
			for(int i = 0; i < listMap.size(); i++){
				if(i != 0){
					//???????????????????????????,?????????????????????????????????????????????
					int syhDyym = ObjectToTypes.parseInt(listMap.get(i-1).get("DYYM"));//??????????????????????????????
					int syhDyhh = ObjectToTypes.parseInt(listMap.get(i-1).get("DYHH"));//??????????????????????????????
					int dyym = ObjectToTypes.parseInt(listMap.get(i).get("DYYM"));//??????????????????????????????
					int dyhh = ObjectToTypes.parseInt(listMap.get(i).get("DYHH"));//??????????????????????????????
					if(syhDyym == dyym && dyhh - syhDyhh>1){
						//???????????????
						for(int j = 1; j < dyhh - syhDyhh; j++){
							Map<String,Object> dyMap = new HashMap<String,Object>(16);
							dyMap.put("KH", "???");
							dyMap.put("DYYM", dyym);
							responseList.add(dyMap);
						}
					}else if(syhDyym<dyym){
						//??????
						for(int j = 0; j < yztdmyts - syhDyhh; j++){
							//????????????????????????
							Map<String,Object> dyMap=new HashMap<String,Object>(16);
							dyMap.put("KH", "???");
							dyMap.put("DYYM", dyym);
							responseList.add(dyMap);
						}
						//?????????????????????,?????????2????????????????????????2????????????,???????????????????????????????????????
						if(syhDyym > dyym - 1){
							//??????????????????????????????,????????????????????????????????????
							for(int x= 0; x < (dyym - syhDyym - 1) * yztdmyts; x++){
								Map<String,Object> dyMap = new HashMap<String,Object>(16);
								dyMap.put("KH", "???");
								dyMap.put("DYYM", dyym);
								responseList.add(dyMap);
							}
						}
						for(int k=1;k<dyhh;k++){
							//?????????????????????
							Map<String,Object> dyMap = new HashMap<String,Object>(16);
							dyMap.put("DYYM", dyym);
							responseList.add(dyMap);
						}
					}
					
				}
				Map<String,Object> jlMap = listMap.get(i);
				int dyym = ObjectToTypes.parseInt(jlMap.get("DYYM"));//??????????????????????????????
				Map<String,Object> dyMap = new HashMap<String,Object>(16);
				dyMap.put("ZXSJ", "???");
				dyMap.put("DYYM", dyym);
				if(jlMap.get("KSSJ") == null){
					dyMap.put("KZRQ", "???");
					dyMap.put("KZSJ", "???");
				}else{
					dyMap.put("KZRQ", sdfo.format(sdf.parse(jlMap.get("KSSJ")+"")));
					dyMap.put("KZSJ", sdft.format(sdf.parse(jlMap.get("KSSJ")+"")));
				}
				String yzmc = ObjectToTypes.parseString(jlMap.get("YZMC"));
				if(ObjectToTypes.parseInt(jlMap.get("YZZF"))==1){
					//????????????????????????
					yzmc="<font style='font-size:9px; text-decoration: line-through;'>" + yzmc + "</font>";
				}
//				if(getBytesStrLength(yzmc) > 40){
//					dyMap.put("YZMC","<font style='font-size:8px'>"+yzmc+"</font>");
//				}else{
					dyMap.put("YZMC","<font style='font-size:9px;'>"+yzmc+"</font>");
//				}
				dyMap.put("HDQM","".equals(ObjectToTypes.parseString(jlMap.get("FHGHMC"))) ? "???" : ObjectToTypes.parseString(jlMap.get("FHGHMC")));
				dyMap.put("YSQM", "".equals(ObjectToTypes.parseString(jlMap.get("YSGHMC"))) ? "???" : ObjectToTypes.parseString(jlMap.get("YSGHMC")));
				dyMap.put("HSQM", "".equals(ObjectToTypes.parseString(jlMap.get("ZXFHGHMC"))) ? "???": ObjectToTypes.parseString(jlMap.get("ZXFHGHMC")));
				if(jlMap.get("TZSJ")==null){
					dyMap.put("TZRQ", "???");
					dyMap.put("TZSJ", "???");
				}else{
					dyMap.put("TZRQ", "".equals(sdfo.format(sdf.parse(jlMap.get("TZSJ")+"")))?"???":sdfo.format(sdf.parse(jlMap.get("TZSJ")+"")));
					dyMap.put("TZSJ", "".equals(sdft.format(sdf.parse(jlMap.get("TZSJ")+"")))?"???":sdft.format(sdf.parse(jlMap.get("TZSJ")+"")));
				}
				dyMap.put("TZYS", "".equals(ObjectToTypes.parseString(jlMap.get("TZYSMC")))?"???":ObjectToTypes.parseString(jlMap.get("TZYSMC")));
				dyMap.put("TZHS", "".equals(ObjectToTypes.parseString(jlMap.get("TZFHRMC")))?"???":ObjectToTypes.parseString(jlMap.get("TZFHRMC")));
				dyMap.put("ZXSJ", jlMap.get("ZXSJ") != null ? jlMap.get("ZXSJ") : "???");
				if("????????????".equals(yzmc) || "???????????????".equals(yzmc) || "????????????".equals(yzmc)){
					dyMap.put("HSQM", "???");
					if(ObjectToTypes.parseInt(jlMap.get("DYHH")) == 1){
						dyMap.put("CZBZ1", "1");
					}else{
						dyMap.put("CZBZ", "1");
					}
					if("????????????".equals(yzmc)){
						dyMap.put("KZRQ", jlMap.get("RQ")+"");
						dyMap.put("KZSJ", jlMap.get("SJ")+"");
					}
					//?????????????????? ??????????????????  ????????????????????????????????????
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
				//??????????????????
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
				dyMap.put("ZH", "???");
				long count = 0L;
				if(jlMap.get("YZZH") != null) {
					CisHzyz cisHzyz = new CisHzyz();
					cisHzyz.setYzzh(ObjectToTypes.parseInt(jlMap.get("YZZH")));
					count = cisHzyzDao.findByEntityCount(cisHzyz);
				}

				if(i > 0) {
					if(ObjectToTypes.parseInt(jlMap.get("YZBXH")) == ObjectToTypes.parseInt(listMap.get(i - 1).get("YZBXH"))) {
						dyMap.put("KZRQ", "???");
						dyMap.put("KZSJ", "???");
					}
				}
				if(count > 1) {
					if(i!=0){//???????????????????????????????????????????????????,???????????????
						//?????????????????????????????????????????????
						if(jlMap.get("KSSJ")!=null){
							if(listMap.get(i-1).get("KSSJ") != null && !"???????????????".equals(ObjectToTypes.parseString(jlMap.get("YZMC")))
									&& !"????????????".equals(ObjectToTypes.parseString(jlMap.get("YZMC")))){
								//????????????????????????????????????
								if(sdfo.format(sdf.parse(jlMap.get("KSSJ")+"")).equals(sdfo.format(sdf.parse(listMap.get(i - 1).get("KSSJ")+"")))){
									//???????????????????????????
									if(i < listMap.size()- 1){
										//??????????????????????????????
										if(ObjectToTypes.parseInt(jlMap.get("YZZH")) != ObjectToTypes.parseInt(listMap.get(i - 1).get("YZZH"))){
											dyMap.put("KZRQ", "???");
										}else{
											if(listMap.get(i+1).get("KSSJ")!=null && !"???????????????".equals(ObjectToTypes.parseString(jlMap.get("YZMC")))
													&& !"????????????".equals(ObjectToTypes.parseString(jlMap.get("YZMC")))){
												//????????????????????????????????????
												if(sdfo.format(sdf.parse(jlMap.get("KSSJ")+"")).equals(sdfo.format(sdf.parse(listMap.get(i + 1).get("KSSJ")+"")))){
													//???????????????????????????
													if(ObjectToTypes.parseInt(jlMap.get("YZZH")) == ObjectToTypes.parseInt(listMap.get(i + 1).get("YZZH"))){
														dyMap.put("KZRQ", "???");
														dyMap.put("KZSJ", "???");
													}else{
														dyMap.put("KZRQ", "???");
													}
												}else{
													//???????????????????????????
													if(ObjectToTypes.parseInt(jlMap.get("YZZH")) == ObjectToTypes.parseInt(listMap.get(i - 1).get("YZZH"))) {	
														dyMap.put("KZRQ", "???");
														dyMap.put("KZSJ", "???");
													}else {
														dyMap.put("KZRQ", "???");
													}												
												}
											}
										}
									}else {
										if(ObjectToTypes.parseInt(jlMap.get("YZZH")) == ObjectToTypes.parseInt(listMap.get(i - 1).get("YZZH"))) {	
											dyMap.put("KZRQ", "???");
											dyMap.put("KZSJ", "???");
										}else {
											if(sdfo.format(sdf.parse(jlMap.get("KSSJ")+"")).equals(sdfo.format(sdf.parse(listMap.get(i - 1).get("KSSJ")+"")))) {
												dyMap.put("KZRQ", "???");
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
									dyMap.put("ZH", "???");
								}else{
									dyMap.put("ZH", "???");
								}
							}else{
								dyMap.put("ZH", "???");
							}
						}else{
							if((i + 1) < listMap.size()){
								if(ObjectToTypes.parseInt(jlMap.get("YZZH")) == ObjectToTypes.parseInt(listMap.get(i+1).get("YZZH")) 
										&& ObjectToTypes.parseInt(jlMap.get("YZBXH")) != -1){
									dyMap.put("ZH", "???");
								}
							}
						}
					}
					if(i==0){
						if((i+1)<listMap.size()){
							if(ObjectToTypes.parseInt(jlMap.get("YZZH")) == ObjectToTypes.parseInt(listMap.get(i+1).get("YZZH"))
									&& ObjectToTypes.parseInt(jlMap.get("YZBXH"))!=-1&&ObjectToTypes.parseInt(jlMap.get("YZBXH")) != 0){
								dyMap.put("ZH", "???");
							}
						}
					}
				}
				//????????????????????? ???????????????????????? ????????????????????????????????????
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
						//??????????????????,?????????????????????
						if(ObjectToTypes.parseInt(firstMap.get("DYHH") )> 1 && 
								ObjectToTypes.parseInt(firstMap.get("DYYM")) == ObjectToTypes.parseInt(jlMap.get("DYYM"))){
							//?????????????????????????????? ??????????????????????????????
							if(sftd != 1){
								dyMap.put("KH", "???");
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
					//?????????????????????????????? ??????????????????????????????
					if(ObjectToTypes.parseInt(firstMap.get("DYHH")) > 1 && 
							ObjectToTypes.parseInt(firstMap.get("DYYM")) == ObjectToTypes.parseInt(jlMap.get("DYYM"))){
						if(sftd != 1){
							dyMap.put("KH", "???");
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
			//???????????????????????????
			//????????????????????????????????????????????????,???????????????????????????????????????
			if((ObjectToTypes.parseInt(firstMap.get("DYYM")) != ObjectToTypes.parseInt(listMap.get(listMap.size()-1).get("DYYM"))
					|| ObjectToTypes.parseInt(firstMap.get("DYHH")) == 1) && 
					responseList.get(responseList.size()-1).containsKey("YZMC")){
				//?????????
				int zhh = ObjectToTypes.parseInt(listMap.get(listMap.size()-1).get("DYHH"));
				for(int i = zhh; i < yztdmyts; i++){
					Map<String,Object> dyMap = new HashMap<String,Object>(16);
					dyMap.put("KZRQ", "???");
					dyMap.put("KZSJ", "???");
					dyMap.put("YZMC", "???");
					dyMap.put("YSQM", "???");
					dyMap.put("HDQM", "???");
					dyMap.put("HSQM", "???");
					dyMap.put("ZH", "???");
					dyMap.put("TZRQ", "???");
					dyMap.put("TZSJ", "???");
					dyMap.put("TZYS", "???");
					dyMap.put("TZHS", "???");
					dyMap.put("ZXSJ", "???");
					dyMap.put("DYYM", responseList.get(responseList.size() - 1).get("DYYM"));				
					responseList.add(dyMap);
				}
			}else{
				//?????????
				int zhh=ObjectToTypes.parseInt(listMap.get(listMap.size() - 1).get("DYHH"));
				for(int i = zhh ; i < yztdmyts; i++){
					Map<String,Object> dyMap = new HashMap<String,Object>(16);						
					dyMap.put("KH", "???");
					responseList.add(dyMap);
				}
			}
		} catch (ParseException e) {
			throw BaseException.create("ERROR_BOOK_PRINT_0005");
		}
		return responseList;
	}
	
	
	/**
	 * ????????????
	 * @Title: getSftd
	 * @Description: TODO(?????????????????????????????????????????????)
	 * @param @param dylx
	 * @param @param zyxhs
	 * @param @param tzyzs
	 * @param @param dyhh
	 * @param @return    ????????????
	 * @return int    ????????????
	 * @author ?????????
	 * @throws
	 */
	public int getSftd(String dylx, String zyxhs, String tzyzs, Integer dyhh) {
		if("jxdy".equals(dylx)){
			if("".equals(zyxhs)){
				return 0;
			}
			String[] dyyzs = zyxhs.split(",");
			List<Integer> dyxhList = new ArrayList<Integer>();//?????????????????????
			for(String dyyz : dyyzs){
				dyxhList.add(ObjectToTypes.parseInt(dyyz));
			}
			List<Map<String,Object>> dyjlList = nisYzdyDao.queryDyInfoByDyxh(dyxhList);
			if(CollectionUtils.isEmpty(dyjlList)){
				return 0;
			}
			Map<String,Object> dyjlMap = dyjlList.get(0);
			if (StrUtil.isNotBlank(tzyzs)) {//?????????,??????????????????????????????,???????????????????????????,??????????????????????????????????????? ????????????
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
	 * ???????????????????????????
	 * @Title: queryDyjl
	 * @Description: TODO(?????????????????????????????????????????????)
	 * @param @param zyh
	 * @param @param yzlx
	 * @param @return    ????????????
	 * @return List<CisHzyzPrintRecordResp>    ????????????
	 * @author ?????????
	 * @throws
	 */
	public List<CisHzyzPrintRecordResp> queryDyjl(Integer zyh, Integer yzlx){
		Map<String, Object> parameters = new HashMap<String, Object>(16);
		parameters.put("zyh", zyh);
		//????????????,0???????????????1???????????????
		parameters.put("yzlx", yzlx);
		List<Map<String, Object>> listMap = nisYzdyDao.querySpecifyLinePrint(parameters);
			 
		// ?????????JLXH???null????????????????????????????????????????????????
		for (Map<String, Object> map : listMap) {
			if (map.get("JLXHS") == null || map.get("JLXHS").toString().length() == 0) {
				Map<String,Object> ztjlMap = cisHzyzDao.queryZtOrderPrintInfo(ObjectToTypes.parseInt(map.get("YZBXH")));
				if(ztjlMap != null){
					map.putAll(ztjlMap);
				}
			}
		}

		for (int i = 0; i < listMap.size(); i++) {
			if ("????????????".equals(ObjectToTypes.parseString(listMap.get(i).get("YZMC")))) {
				listMap.get(i).put("KSSJ", listMap.get(i).get("DYRQ"));
				listMap.get(i).put("YSGHMC", listMap.get(i).get("CZYS"));
				listMap.get(i).put("FHGHMC", listMap.get(i).get("CZHS"));
			}
		}

		return BUHISUtil.ListToResultSet(listMap, new CisHzyzPrintRecordResp());
	}
	
	/**
	 * @throws Exception 
	 * ??????????????????
	 * @Title: getAllOrdersPrint
	 * @Description: TODO(?????????????????????????????????????????????)
	 * @param @param req
	 * @param @param user
	 * @param @return    ????????????
	 * @return List<Map<String,Object>>    ????????????
	 * @author ?????????
	 * @throws
	 */
	public List<Map<String, Object>> getAllOrdersPrint(CisHzyzBookPrintReq req, SysUser user) throws Exception{
		Integer jgid = user.getHospitalId();
		//?????????????????????,1??????????????????,2??????????????????,?????????2
		SysXtcs sysXtcs = sysXtcsCacheSer.getByJGIdAndCsmc(jgid, SysXtcsCsmcCts.YZBDYSJ);
		Integer yzbdysj = sysXtcs == null ? 2 : ObjectToTypes.parseInt(sysXtcs.getCsz());
		//??????????????????
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
					//??????????????????
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
				
				//????????????
				if(yzlx == 1){
					//???????????? <> 0   ?????? 
					if(!"0".equals(ObjectToTypes.parseString(cis.getYplx()))){
						if("3".equals(ObjectToTypes.parseString(cis.getYplx()))){
							//??????
							yzmc += " " + (cis.getYcjl().compareTo(BigDecimal.ZERO) == 0 ? "": cis.getYcjl()) + jldw + " ";	
							yzmc += " ??" + cis.getCfts() + " , ";
							yzmc += ypyfmc + " " + sypcmc;
						}else{
							//??????
							yzmc += (cis.getYcsl().compareTo(BigDecimal.ZERO) == 0 ? "" : "/  ??" + dft.format(cis.getYcsl()) + cis.getYfdw()) + " ,";
							yzmc += " "+ (cis.getYcjl().compareTo(BigDecimal.ZERO) == 0  ? "": cis.getYcjl()) + jldw + " ";	
							yzmc += ypyfmc + " " + sypcmc;
						}
						
					}else{
						//?????????????????????
						if(!StrUtil.isBlankIfStr(cis.getYjfygb())){
							//??????
							yzmc += " " + sypcmc;
							yzmc += (cis.getYcsl().compareTo(BigDecimal.ZERO) == 0 ? "" : " ??" + dft.format(cis.getYcsl()) )+ "";
						}else{ 
							if(!StrUtil.isBlankIfStr(cis.getYzlx()) && cis.getYzlx() == 4) {
								yzmc += ypyfmc;
							}else {
								//???????????????				
								yzmc += ypyfmc + " " + sypcmc;
							}
						}
					}
				}else{
					yzmc += " " + (cis.getYcjl().compareTo(BigDecimal.ZERO) == 0 ? "" : dft.format(cis.getYcjl())) + jldw + " ";	
					yzmc += ypyfmc + " " + sypcmc;
				}
				
				if(cis.getZfyp() != null && cis.getZfyp().intValue() == 1){
					yzmc ="(??????)" + yzmc;
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
							+ "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+"???????????????"+"</font>");
					respList.add(resMap);
					continue;
				}
				resMap.put("KZRQ", resp.getKssj() != null ? DateUtil.format(resp.getKssj(), "yy-MM-dd") : "???");
				resMap.put("KZSJ", resp.getKssj() != null ? DateUtil.format(resp.getKssj(), "HH:mm") : "???");
				resMap.put("YZMC", "<font style='font-size:9px;'>" + yzmc + "</font>");
				resMap.put("ZH", "???");
				resMap.put("YSQM", StrUtil.isNotBlank(resp.getYsghmc()) ? resp.getYsghmc() : "???");
				resMap.put("HDQM", StrUtil.isNotBlank(resp.getFhghmc()) ? resp.getFhghmc() : "???");
				resMap.put("HSQM", "???");
				resMap.put("TZRQ", resp.getTzsj() != null ? DateUtil.format(resp.getTzsj(), "yy-MM-dd")  : "???");
				resMap.put("TZSJ", resp.getTzsj() != null ? DateUtil.format(resp.getTzsj(), "HH:mm") : "???");
				resMap.put("TZYS", StrUtil.isNotBlank(resp.getTzysmc()) ? resp.getTzysmc() : "???");
				resMap.put("TZHS", "???");
				if(resp.getYzlx() != null && resp.getYzlx() == 3) {
					respList.add(resMap);
					resMap = new HashMap<String, Object>(16);
					resMap.put("YZMC", "<font style='font-size:9px;'>&nbsp;&nbsp;"
							+ "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+"????????????"+"</font>");
					respList.add(resMap);
					continue;
				}
				if("1".equals(ObjectToTypes.parseString(resp.getZfbz()))){
					//????????????????????????
					resMap.put("YZMC", "<font style='font-size:9px; text-decoration: line-through;'>" + yzmc + "</font>");
				}
				if(i > 0) {
					if(resp.getJlxh().intValue() == reslist.get(i - 1).getJlxh().intValue()) {
						resMap.put("KZRQ", "???");
						resMap.put("KZSJ", "???");
					}
				}
				for (Map.Entry<Integer, List<CisHzyzOrderBookResp>> entry : groups.entrySet()){
			       if(yzzh.intValue() == entry.getKey().intValue() && entry.getValue().size() > 1) {
						if(i != 0){//???????????????????????????????????????????????????,???????????????
							//?????????????????????????????????????????????
							if(resp.getKssj() != null){
								if(reslist.get(i-1).getKssj() != null && !"302".equals(resp.getYdlb())){
									//????????????????????????????????????
									if(DateUtil.format(resp.getKssj(), DatePattern.NORM_DATE_PATTERN).toString().equals(
											DateUtil.format(reslist.get(i-1).getKssj(), DatePattern.NORM_DATE_PATTERN).toString())){
										//???????????????????????????
										if(i < reslist.size()- 1){
											//??????????????????????????????
											if(resp.getYzzh().intValue() != reslist.get(i-1).getYzzh().intValue()){
												resMap.put("KZRQ", "???");
											}else{
											    if(reslist.get(i+1).getKssj() != null && !"302".equals(resp.getYdlb())){
													//????????????????????????????????????
													if(DateUtil.format(resp.getKssj(), DatePattern.NORM_DATE_PATTERN).toString().equals(
															DateUtil.format(reslist.get(i+1).getKssj(), DatePattern.NORM_DATE_PATTERN).toString())){
														//???????????????????????????
														if(resp.getYzzh().intValue() == reslist.get(i+1).getYzzh().intValue()){
															resMap.put("KZRQ", "???");
															resMap.put("KZSJ", "???");
														}else{
															resMap.put("KZRQ", "???");
														}
													}else{
														//???????????????????????????
														if(resp.getYzzh().intValue() == reslist.get(i-1).getYzzh().intValue()) {	
															resMap.put("KZRQ", "???");
															resMap.put("KZSJ", "???");
														}else {
															resMap.put("KZRQ", "???");
														}												
													}
												}
											}
										}else {
											if(resp.getYzzh().intValue() == reslist.get(i-1).getYzzh().intValue()) {	
												resMap.put("KZRQ", "???");
												resMap.put("KZSJ", "???");
											}else {
												if(DateUtil.format(resp.getKssj(), DatePattern.NORM_DATE_PATTERN).toString().equals(
														DateUtil.format(reslist.get(i-1).getKssj(), DatePattern.NORM_DATE_PATTERN).toString())) {
													resMap.put("KZRQ", "???");
												}
											}												
										}
									}
								}
							}
							if(resp.getYzzh().intValue() == reslist.get(i-1).getYzzh().intValue()){
								if((i+1) < reslist.size()){
									if(resp.getYzzh().intValue() == reslist.get(i+1).getYzzh().intValue()){
										resMap.put("ZH", "???");
									}else{
										resMap.put("ZH", "???");
									}
								}else{
									resMap.put("ZH", "???");
								}
							}else{
								if((i+1) < reslist.size()){
									if(resp.getYzzh().intValue() == reslist.get(i+1).getYzzh().intValue()){
										resMap.put("ZH", "???");
									}
								}
							}
						}
						if(i == 0){
							if((i+1) < reslist.size()){
								if(resp.getYzzh().intValue() == reslist.get(i+1).getYzzh().intValue()){
									resMap.put("ZH", "???");
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
