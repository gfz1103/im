package com.buit.cis.nurse.service;


import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUnit;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import com.buit.cis.dctwork.dao.CisHzyzDao;
import com.buit.cis.dctwork.model.CisHzyz;
import com.buit.cis.ims.dao.ImFeeFymxDao;
import com.buit.cis.ims.dao.ImHzryDao;
import com.buit.cis.ims.dao.ImRcjlDao;
import com.buit.cis.ims.model.ImRcjl;
import com.buit.cis.ims.service.ZyDicSer;
import com.buit.cis.nurse.request.NisDispensingReq;
import com.buit.cis.nurse.request.NisYzCostReq;
import com.buit.cis.nurse.response.NisHzListResp;
import com.buit.commons.BaseManagerImp;
import com.buit.commons.SysUser;
import com.buit.drug.response.IImFyfsResp;
import com.buit.drug.response.PharBaseInfoResp;
import com.buit.drug.service.DrugService;
import com.buit.drug.service.PharBaseConfigService;
import com.buit.system.model.DicKszd;
import com.buit.system.response.DicKszdModel;
import com.buit.system.service.DicKszdOutSer;
import com.buit.utill.BUHISUtil;
import org.apache.commons.collections.CollectionUtils;
import org.apache.dubbo.config.annotation.DubboReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * 统计分析<br>
 * @author GONGFANGZHOU
 */
@Service
public class NisAnalysisSer extends BaseManagerImp<CisHzyz,Integer> {
    
    static final Logger logger = LoggerFactory.getLogger(NisAnalysisSer.class);
    
    @Autowired
    private CisHzyzDao cisHzyzDao;
    
    @Override
    public CisHzyzDao getEntityMapper(){        
        return cisHzyzDao;
    }
    
    @DubboReference
    private DicKszdOutSer dicKszdOutSer;
    
    @DubboReference
    private PharBaseConfigService pharBaseConfigService;
    
    @DubboReference
	private DrugService drugService;
    
    @Autowired
    private ImFeeFymxDao imFeeFymxDao;
    
    @Autowired
    private ImHzryDao imHzryDao;
    
    @Autowired
    private ImRcjlDao imRcjlDao;
    
    @Autowired
    private ZyDicSer zyDicSer;
    
    /**
     * 病区发药汇总返回打印数据
     * @Title: getSummaryDispensingParameters
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @param @param nisDispensingReq
     * @param @param HospitalName
     * @param @return    设定文件
     * @return Map<String,Object>    返回类型
     * @author 龚方舟
     * @throws
     */
    public Map<String, Object> getSummaryDispensingParameters(NisDispensingReq nisDispensingReq, String hospitalName){
    	Map<String, Object> responseMap = new HashMap<String, Object>(16);
		responseMap.put("dateForm", DateUtil.format(nisDispensingReq.getBeginDate(), DatePattern.NORM_DATETIME_PATTERN));
		responseMap.put("dateTo", DateUtil.format(nisDispensingReq.getEndDate(), DatePattern.NORM_DATETIME_PATTERN));
		responseMap.put("TITLE", hospitalName + "病区发药明细清单汇总");
		DicKszd dicKszd = new DicKszd();
		dicKszd.setId(nisDispensingReq.getBqdm());
		dicKszd.setHospitalarea("1");
		List<DicKszdModel> kszdList = dicKszdOutSer.findByEntity(dicKszd);
		if(CollectionUtils.isNotEmpty(kszdList)) {
			responseMap.put("BQ",kszdList.get(0).getOfficename());
		}
		if(StrUtil.isBlankIfStr(nisDispensingReq.getYfsb())) {
			responseMap.put("YFMC", "全部药房");
		}else {
			PharBaseInfoResp yflbResp = pharBaseConfigService.getById(nisDispensingReq.getYfsb());
			responseMap.put("YFMC", yflbResp.getYfmc());
		}
		if(StrUtil.isBlankIfStr(nisDispensingReq.getFyfs())) {
			responseMap.put("FYFS", "全部发药方式");
		}else {
			IImFyfsResp fyfsResp = drugService.getFyfsById(nisDispensingReq.getFyfs());
			responseMap.put("FYFS", fyfsResp.getFsmc());
		}
		return responseMap;
    }

    /**
     * 病区收入核算
     * @Title: getRevenueAccountingFiles
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @param @param beginDate
     * @param @param endDate
     * @param @param bqdm
     * @param @return    设定文件
     * @return List<Map<String,Object>>    返回类型
     * @author 龚方舟
     * @throws
     */
	public List<Map<String, Object>> getRevenueAccountingFiles(Timestamp beginDate, Timestamp endDate, Integer bqdm, Integer jgid) {
		List<Map<String, Object>> resList = new ArrayList<Map<String,Object>>();
		Map<String, Object> parameters = new HashMap<String, Object>(16);
		parameters.put("jgid", jgid);
		parameters.put("beginDate", beginDate);
		parameters.put("endDate", endDate);
		parameters.put("bqdm", bqdm);
		List<Map<String, Object>> list = imFeeFymxDao.queryRevenueAccounting(parameters);
		BigDecimal hjSum = new BigDecimal(0);
		for (int i = 0; i < list.size(); i++) {
			Map<String, Object> map = new HashMap<String, Object>();
			hjSum = hjSum.add(new BigDecimal(list.get(i).get("ZJJE") + ""));
			map.put("XM0", list.get(i).get("SFMC").toString());
			map.put("JE0",
					String.format("%1$.2f", list.get(i).get("ZJJE")) + "");
			if (i < list.size() - 1) {
				i++;
				map.put("XM1", list.get(i).get("SFMC").toString());
				map.put("JE1",
						String.format("%1$.2f", list.get(i).get("ZJJE"))
								+ "");
				hjSum = hjSum.add(new BigDecimal(list.get(i).get("ZJJE") + ""));
			}
			if (i < list.size() - 1) {
				i++;
				map.put("XM2", list.get(i).get("SFMC").toString());
				map.put("JE2",
						String.format("%1$.2f", list.get(i).get("ZJJE"))
								+ "");
				hjSum = hjSum.add(new BigDecimal(list.get(i).get("ZJJE") + ""));
			}
			map.put("HJ", hjSum);
			resList.add(map);
		}
		return resList;
	}
	
	/**
	 * 病区收入核算返回参数
	 * @Title: getRevenueAccountingParameters
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @param beginDate
	 * @param @param endDate
	 * @param @param bqdm
	 * @param @param hospitalName
	 * @param @return    设定文件
	 * @return Map<String,Object>    返回类型
	 * @author 龚方舟
	 * @throws
	 */
	public Map<String, Object> getRevenueAccountingParameters(Timestamp beginDate, Timestamp endDate, Integer bqdm,
			SysUser user) {
		Map<String, Object> responseMap = new HashMap<String, Object>(16);
		responseMap.put("RYRQ", DateUtil.format(beginDate, DatePattern.NORM_DATETIME_PATTERN) + "至" 
		+ DateUtil.format(endDate, DatePattern.NORM_DATETIME_PATTERN));
		responseMap.put("TITLE", user.getHospitalName() + "病区科室收入核算");
		responseMap.put("CZY", user.getUserName());
		
		if(bqdm != null) {
			DicKszd dicKszd = new DicKszd();
			dicKszd.setId(bqdm);
			dicKszd.setHospitalarea("1");
			List<DicKszdModel> kszdList = dicKszdOutSer.findByEntity(dicKszd);
			if(CollectionUtils.isNotEmpty(kszdList)) {
				responseMap.put("KSMC",kszdList.get(0).getOfficename());
			}
		}else {
			responseMap.put("KSMC", "全部病区");
		}
		return responseMap;
	}
	
	/**
	 * 病区一日清单病人信息
	 * @Title: queryPatientByList
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @param jgid
	 * @param @return    设定文件
	 * @return List<NisHzListResp>    返回类型
	 * @author 龚方舟
	 * @throws
	 */
	public List<NisHzListResp> queryPatientByList(Integer jgid, Integer bqdm, Integer ksdm, String zyhm) {
		List<NisHzListResp> respList = imHzryDao.queryPatientByList(jgid, bqdm, ksdm, zyhm);
		for(NisHzListResp nisHzListResp : respList) {
			Integer cypb = nisHzListResp.getCypb();
			if(cypb == 1) {
				continue;
			}
			ImRcjl imRcjl = new ImRcjl();
			imRcjl.setZyh(nisHzListResp.getZyh());
			imRcjl.setCzlx(-1);
			imRcjl.setJgid(jgid);
			long count = imRcjlDao.findByEntityCount(imRcjl);
			if(count > 0) {
				nisHzListResp.setCypb(102);
			}
			Integer ts = 0;
			if(!StrUtil.isBlankIfStr(nisHzListResp.getRyrq())) {
                ts = BUHISUtil.getPeriod(nisHzListResp.getRyrq(), new Date());
                nisHzListResp.setWjsts(ts);
            }
		}
		return respList;
	} 
	
	/**
	 * 一日清单医嘱格式返回参数
	 * @Title: getYzCostParameters
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @param nisYzCostReq    设定文件
	 * @return Map<String, Object>    返回类型
	 * @author 龚方舟
	 * @throws
	 */
	public Map<String, Object> getYzCostParameters(NisYzCostReq nisYzCostReq) {
		Map<String, Object> responseMap = new HashMap<String, Object>(16);
		Map<String, Object> parameters = new HashMap<String, Object>(16);
		parameters.put("zyh", nisYzCostReq.getZyh());
		parameters.put("beginDate", nisYzCostReq.getBeginDate());
		parameters.put("endDate", nisYzCostReq.getEndDate());
		Map<String, Object> resMap = imFeeFymxDao.queryYzCost(parameters);
		responseMap.put("ZFHJ", resMap.get("ZJJE")==null?"0.00":resMap.get("ZJJE"));
		responseMap.put("FYHJ", resMap.get("ZFJE")==null?"0.00":resMap.get("ZFJE"));
			
		parameters.remove("beginDate");
		parameters.remove("endDate");
		resMap = imFeeFymxDao.queryYzCost(parameters);
		responseMap.put("ZFLJ", resMap.get("ZJJE")==null?"0.00":resMap.get("ZJJE"));
		responseMap.put("FYLJ", resMap.get("ZFJE")==null?"0.00":resMap.get("ZFJE"));
		responseMap.put("BRXM", nisYzCostReq.getBrxm());
		responseMap.put("BRXZ", zyDicSer.getBrxzDic().get(nisYzCostReq.getBrxz()));
		responseMap.put("BRKS", nisYzCostReq.getKsmc());
		responseMap.put("ZYHM", nisYzCostReq.getZyhm());
		responseMap.put("RYRQ", nisYzCostReq.getRyrq());
		if(!StrUtil.isBlankIfStr(nisYzCostReq.getCyrq())) {
			responseMap.put("CYRQ", nisYzCostReq.getCyrq());
			responseMap.put("DAYS", DateUtil.between(nisYzCostReq.getRyrq(), nisYzCostReq.getCyrq(), 
					DateUnit.DAY));
		}
		responseMap.put("BRCH", nisYzCostReq.getBrch());
		return responseMap;
	} 
    
}
