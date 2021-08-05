package com.buit.cis.dctwork.service;


import java.lang.reflect.Method;
import java.sql.Timestamp;
import java.util.*;
import java.util.Map.Entry;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.commons.collections.CollectionUtils;
import org.apache.dubbo.config.annotation.DubboReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.buit.cis.dctwork.dao.NisSmtzDao;
import com.buit.cis.dctwork.dao.NisTzxmDao;
import com.buit.cis.dctwork.dao.NisZdytzDao;
import com.buit.cis.dctwork.enums.OperationTypeEnum;
import com.buit.cis.dctwork.model.NisSmtz;
import com.buit.cis.dctwork.model.NisTzxm;
import com.buit.cis.dctwork.model.NisZdytz;
import com.buit.cis.dctwork.request.NisSmtzBatchQueryReq;
import com.buit.cis.dctwork.request.NisSmtzBatchSaveReq;
import com.buit.cis.dctwork.request.NisSmtzBatchSaveReq.NisSmtzBatch;
import com.buit.cis.dctwork.request.NisZdytzReq;
import com.buit.cis.dctwork.request.NistzxmSaveReq;
import com.buit.cis.dctwork.response.NisSmtzBatchListResp;
import com.buit.cis.dctwork.response.NisSmtzListResp;
import com.buit.cis.dctwork.response.NisSmtzResp;
import com.buit.cis.dctwork.response.TableDataResp;
import com.buit.cis.dctwork.response.TableDataResp.CustomSummary;
import com.buit.cis.dctwork.response.TableDataResp.StatisticsSummary;
import com.buit.cis.dctwork.response.TemperatureChartResp;
import com.buit.cis.ims.dao.ImHzryDao;
import com.buit.cis.ims.dao.ImTwdnrDao;
import com.buit.cis.ims.enums.ZFPBEnum;
import com.buit.cis.ims.model.ImHzry;
import com.buit.cis.ims.model.ImTwdnr;
import com.buit.cis.ims.request.ImTwdReq;
import com.buit.cis.ims.request.ImTwdnrReq;
import com.buit.cis.ims.response.ExpensesPrintDataResp;
import com.buit.commons.BaseException;
import com.buit.commons.BaseManagerImp;
import com.buit.commons.SysUser;
import com.buit.constans.SysXtcsCsmcCts;
import com.buit.constans.TableName;
import com.buit.system.response.DictDto;
import com.buit.system.response.SysXtcs;
import com.buit.system.service.SysFlagDataValueOutSer;
import com.buit.system.service.SysXtcsCacheSer;
import com.buit.system.utill.ObjectToTypes;
import com.buit.utill.BUHISUtil;
import com.buit.utill.DigitalConversionUtil;
import com.buit.utill.PinyinUtils;
import com.buit.utill.RedisFactory;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUnit;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;

/**
 * <br>
 * @author GONGFANGZHOU
 */
@Service
public class ImTwdnrSer extends BaseManagerImp<ImTwdnr,Integer> {
    
    static final Logger logger = LoggerFactory.getLogger(ImTwdnrSer.class);
    
    @Autowired
    private ImTwdnrDao imTwdnrDao;

    @Autowired
    private RedisFactory redisFactory;

    
    @Override
    public ImTwdnrDao getEntityMapper(){        
        return imTwdnrDao;
    }
    
    @Autowired
    private ImHzryDao imHzryDao;
    
    @Autowired
    private NisTzxmDao nisTzxmDao;
    
    @Autowired
    private NisSmtzDao nisSmtzDao;
    
    @DubboReference
    private SysFlagDataValueOutSer sysFlagDataValueOutSer;
    
    @DubboReference
    private SysXtcsCacheSer sysXtcsCacheSer;
    
    @Autowired
    private NisZdytzDao nisZdytzDao;

    /**
     * 新增住院体温单内容
     * @param req
     */
    public void add(ImTwdnrReq req){
        ImTwdnr imtwdnr = BeanUtil.toBean(req, ImTwdnr.class);
        List<ImTwdnr> ret = imTwdnrDao.findByEntity(imtwdnr);
        if(!ret.isEmpty()){
            throw BaseException.create("ERROR_DCTWORK_ZYTWDNR_0001");
        }
        imtwdnr.setId(redisFactory.getTableKey(TableName.DB_NAME, TableName.IM_TWDNR));
        imTwdnrDao.insert(imtwdnr);
    }

    /**
     * 作废住院体温单内容
     * @param id
     */
    public void invalid(Integer id){
        ImTwdnr imTwdnr =  new ImTwdnr();
        imTwdnr.setId(id);
        imTwdnr.setZfbz(ZFPBEnum.code_1.getCode());
        imTwdnrDao.invalid(imTwdnr);
    }
    
    /**
    * @Title: queryTwdPatientInfo
    * @Description: TODO 查询体温单未出院病人列表
    * @param @param brbq
    * @param @return    设定文件
    * @return List<ImHzry>    返回类型
    * @author 龚方舟
    * @throws
     */
    public List<ImHzry> queryTwdPatientInfo(Integer brbq){	
    	return imHzryDao.queryTwdPatientInfo(brbq);
    }
    
    /**
    * @Title: saveSMTZ
    * @Description: TODO 体温单生命体征保存
    * @param @param imTwdReq
    * @param @param user    设定文件
    * @return void    返回类型
    * @author 龚方舟
    * @throws
     */
    @Transactional(rollbackFor = Exception.class)
    public void saveSmtz(ImTwdReq imTwdReq, SysUser user) {
    	NistzxmSaveReq nistzxmSaveReq = imTwdReq.getNistzxmSaveReq();
        Map<String, Object> smtz = BUHISUtil.caseInsensitiveMap(nistzxmSaveReq);
        Integer jgid = user.getHospitalId();
        Integer zyh = imTwdReq.getZyh();// 住院号
        Map<String, Object> parMap = new HashMap<String, Object>(16);
        // 自定义体征内容
        List<NisZdytzReq> nisZdytzList = imTwdReq.getNisZdytzReqList();

    	int ryTime = DateUtil.hour(imTwdReq.getCjsj(), true);
    	ryTime = (0 <= ryTime && ryTime < 6) ? 2 : (6 <= ryTime && ryTime < 10) ? 6 : 
				(10 <= ryTime && ryTime < 14) ? 10 : (14 <= ryTime && ryTime < 18) ? 14 :
				(18 <= ryTime && ryTime < 22) ? 18 : 22;	
        List<NisTzxm> nisTzxmList = nisTzxmDao.findByEntity(null);
        ImHzry imHzry = imHzryDao.getById(zyh);
        Integer cjzh = redisFactory.getTableKey(TableName.DB_NAME, TableName.NIS_SMTZ + ".cjzh");
        NisSmtz req = new NisSmtz();
        req.setCjzh(cjzh);
        req.setZyh(zyh);
        req.setJhbz(1);
        req.setBrks(imHzry.getBrks());
        req.setBrbq(imHzry.getBrbq());
        req.setBrch(imHzry.getBrch());
        req.setFcbz(0);
        req.setTwdxs(0);
        req.setJlsj(DateUtil.date().toTimestamp());
        req.setJlgh(ObjectToTypes.parseString(user.getUserId()));
        req.setZfbz(0);
        req.setYcbz(1);
        req.setCjsj(imTwdReq.getCjsj());
        req.setJgid(jgid);
        req.setSjd(ryTime);      	
		if(imTwdReq.getCjh() != null){
        	parMap.put("ZYH", zyh);
        	parMap.put("CJSJ", imTwdReq.getCjsj());
        	List<Map<String, Object>> tzxmParlist = nisSmtzDao.querySmtzAndBqtzInfo(parMap);
        	for (int i = 0; i < tzxmParlist.size(); i++) {
        		Map<String, Object> tzMap =  tzxmParlist.get(i);
        		if (smtz.containsKey(tzMap.get("PYDM"))) {
        			NisSmtz updateReq = new NisSmtz();
        			updateReq.setCjh(ObjectToTypes.parseInt(tzMap.get("CJH")));
        			if(tzMap.get("XMH") != null){
        				if ("1".equals(ObjectToTypes.parseString(tzMap.get("XMH")))) {// 体温
//	        					//项目下标（体温类型等）
        					updateReq.setXmxb(imTwdReq.getXmxb());
        				}
        			}
        			if (tzMap.get("PYDM") != null) {
        				if(StrUtil.isNotBlank(ObjectToTypes.parseString(smtz.get(tzMap.get("PYDM"))))) {
        					updateReq.setTznr(ObjectToTypes.parseString(smtz.get(tzMap.get("PYDM"))));
        				}else {
        					nisSmtzDao.deleteById(ObjectToTypes.parseInt(tzMap.get("CJH")));
        				}
        			}
        			updateReq.setJgid(jgid);
        			nisSmtzDao.update(updateReq);
        			smtz.remove(tzMap.get("PYDM").toString().toLowerCase());
        		}
			}
        }
		for (NisTzxm tzxm : nisTzxmList) {
    		String pydm = tzxm.getPydm();
    		if (smtz.containsKey(pydm) && smtz.get(pydm) != null && !"".equals(smtz.get(pydm))) {
    			req.setXmh(ObjectToTypes.parseInt(tzxm.getXmh()));
    			if (ObjectToTypes.parseInt(tzxm.getXmh()) == 1) {// 体温
    				req.setXmxb(imTwdReq.getXmxb());
    			}
    			req.setTznr(ObjectToTypes.parseString(smtz.get(pydm)));
    			req.setCjh(redisFactory.getTableKey(TableName.DB_NAME, TableName.NIS_SMTZ));
    			nisSmtzDao.insert(req);
    		}
    	} 	
		//自定义体征内容新增或修改
		for(NisZdytzReq nisZdytz : nisZdytzList) {
			nisZdytz.setZyh(zyh);
			nisZdytz.setJgid(jgid);
			nisZdytz.setDqzs(imTwdReq.getCurrentWeek());
			setPydmCode(nisZdytz, nisZdytz.getZdymc());
			if(nisZdytz.getZdyid() != null) {
				nisZdytzDao.update(nisZdytz);
				NisSmtz nisSmtz = new NisSmtz();
				nisSmtz.setZyh(zyh);
				nisSmtz.setCjsj(imTwdReq.getCjsj());
				nisSmtz.setZdyxh(nisZdytz.getZdyid());
				nisSmtz.setJgid(jgid);
				List<NisSmtz> smtzList = nisSmtzDao.findByEntity(nisSmtz);
				//生命体征表是否存在
				if(CollectionUtils.isNotEmpty(smtzList)) {
					//生命体征表是否如果没有体征内容则删除
					if(StrUtil.isNotBlank(nisZdytz.getZdynr())) {
						nisSmtz.setCjh(smtzList.get(0).getCjh());
						nisSmtz.setTznr(nisZdytz.getZdynr());
						nisSmtz.setSjd(ryTime);
						nisSmtzDao.update(nisSmtz);
					}else {
						nisSmtzDao.deleteById(smtzList.get(0).getCjh());
					}
				}else {
					if(StrUtil.isNotBlank(nisZdytz.getZdynr())) {
						nisSmtz.setCjzh(cjzh);
						nisSmtz.setJhbz(1);
						nisSmtz.setBrks(imHzry.getBrks());
						nisSmtz.setBrbq(imHzry.getBrbq());
						nisSmtz.setBrch(imHzry.getBrch());
						nisSmtz.setFcbz(0);
						nisSmtz.setTwdxs(0);
						nisSmtz.setJlsj(DateUtil.date().toTimestamp());
						nisSmtz.setJlgh(ObjectToTypes.parseString(user.getUserId()));
						nisSmtz.setZfbz(0);
						nisSmtz.setYcbz(1);
						nisSmtz.setTznr(nisZdytz.getZdynr());
						nisSmtz.setSjd(ryTime);
						nisSmtz.setCjh(redisFactory.getTableKey(TableName.DB_NAME, TableName.NIS_SMTZ));
						nisSmtzDao.insert(nisSmtz);
					}
				}
			}else {
				Integer zdyid = redisFactory.getTableKey(TableName.DB_NAME, TableName.NIS_ZDYTZ);
				nisZdytz.setZdyid(zdyid);
				nisZdytzDao.insert(nisZdytz);
				if(StrUtil.isNotBlank(nisZdytz.getZdynr())) {
					NisSmtz nisSmtz = new NisSmtz();
					nisSmtz.setZyh(zyh);
					nisSmtz.setCjsj(imTwdReq.getCjsj());
					nisSmtz.setZdyxh(nisZdytz.getZdyid());
					nisSmtz.setJgid(jgid);
					nisSmtz.setCjzh(cjzh);
					nisSmtz.setJhbz(1);
					nisSmtz.setBrks(imHzry.getBrks());
					nisSmtz.setBrbq(imHzry.getBrbq());
					nisSmtz.setBrch(imHzry.getBrch());
					nisSmtz.setFcbz(0);
					nisSmtz.setTwdxs(0);
					nisSmtz.setJlsj(DateUtil.date().toTimestamp());
					nisSmtz.setJlgh(ObjectToTypes.parseString(user.getUserId()));
					nisSmtz.setZfbz(0);
					nisSmtz.setYcbz(1);
					nisSmtz.setTznr(nisZdytz.getZdynr());
					nisSmtz.setSjd(ryTime);
					nisSmtz.setCjh(redisFactory.getTableKey(TableName.DB_NAME, TableName.NIS_SMTZ));
					nisSmtzDao.insert(nisSmtz);
				}					
			}			
		} 
	}
    
    /**
    * @Title: deleteSMTZ
    * @Description: TODO 删除生命体征
    * @param @param cjh    设定文件
    * @return void    返回类型
    * @author 龚方舟
    * @throws
     */
    public void deleteSmtz(List<Integer> cjhList) {
    	if(cjhList.isEmpty()) {
    		return;
    	}
    	nisSmtzDao.batchRemoveSmtz(cjhList);
    }
    
    /**
    * @Title: querySmtzInfoByZyh
    * @Description: TODO 根据住院号查询病人生命体征
    * @param @param zyh
    * @param @return    设定文件
    * @return List<NisSmtzListResp>    返回类型
    * @author 龚方舟
    * @throws
     */
    public List<NisSmtzListResp> querySmtzInfoByZyh(Integer zyh){
    	List<NisSmtzListResp> nisSmtzList = new ArrayList<NisSmtzListResp>();
    	List<NisSmtzResp> list = nisSmtzDao.querySmtzInfoByZyh(zyh);
    	if(CollectionUtils.isNotEmpty(list)) {
    		Map<Timestamp, List<NisSmtzResp>> groups = list.stream().
        			collect(Collectors.groupingBy(NisSmtzResp::getCjsj));
        	for(Entry<Timestamp, List<NisSmtzResp>> vo : groups.entrySet()){
        		NisSmtzListResp nisSmtz = new NisSmtzListResp();
        		nisSmtz.setCjsj(vo.getKey());
        		nisSmtz.setNisSmtzList(vo.getValue());
        		nisSmtzList.add(nisSmtz);
    	    }
        	Collections.sort(nisSmtzList);
    	}  	
    	return nisSmtzList;
    }
    
    /**
     * 通过住院号码查询住院号
     * @Title: doGetZYH
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @param @param zyhm
     * @param @return    设定文件
     * @return ImHzry    返回类型
     * @author 龚方舟
     * @throws
     */
	public ImHzry doGetZyh(String zyhm) {
		ImHzry imHzry = new ImHzry();
		imHzry.setZyhm(zyhm);
		imHzry.setCypb(0);
		List<ImHzry> imHzryList = imHzryDao.findByEntity(imHzry);
		if(CollectionUtils.isNotEmpty(imHzryList) && imHzryList.size() == 1) {
			imHzry = imHzryList.get(0);
		}
		return imHzry;
	}
	
	/**
	 * 查询体温单表格数据
	 * @Title: loadTemperatureChart
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @param zyh
	 * @param @param currentWeek
	 * @param @return    设定文件
	 * @return TemperatureChartResp    返回类型
	 * @author 龚方舟
	 * @throws
	 */
	public TemperatureChartResp loadTemperatureChart(Integer zyh, Integer currentWeek, SysUser user) {
		TemperatureChartResp temperatureChartResp = new TemperatureChartResp();
		TableDataResp tableDataResp = new TableDataResp();
		//表格坐标
		List<Map<String, Object>> chartListMap = new ArrayList<Map<String,Object>>();
		for(int i = 35 ; i < 43; i ++) {
			Map<String, Object> initMap = new HashMap<String, Object>(16);
			initMap.put("cellMin", 34);
			initMap.put("cellSplit", 0.2);
			initMap.put("y", i);
			initMap.put("type", "baseline");
			initMap.put("color", "#000");
			initMap.put("shape", null);
			chartListMap.add(initMap);
		}
		
		List<Map<String, Object>> painChartList = new ArrayList<Map<String,Object>>();
		for(int i = 0 ; i < 11; i ++) {
			Map<String, Object> initPainMap = new HashMap<String, Object>(16);
			initPainMap.put("cellMin", 0);
			initPainMap.put("cellSplit", 1);
            initPainMap.put("y", i);
            initPainMap.put("type", "baseline");
            initPainMap.put("color", "#000");
            initPainMap.put("shape", null);
            painChartList.add(initPainMap);
        }

        Map<String, Object> map = imHzryDao.queryBasicInfo(zyh);
        tableDataResp.setName(ObjectToTypes.parseString(map.get("BRXM")));
        tableDataResp.setSex(ObjectToTypes.parseString(map.get("SEXNAME")));
        Map<String, Object> ageMap = BUHISUtil.getPersonAge(DateUtil.parse(map.get("CSNY") + "",
                DatePattern.NORM_DATE_PATTERN), null);
        tableDataResp.setAge(ObjectToTypes.parseString(ageMap.get("age") + "岁"));
        tableDataResp.setDepartment(ObjectToTypes.parseString(map.get("OFFICENAME")));
        tableDataResp.setInpatientArea(ObjectToTypes.parseString(map.get("INPATIENTNAME")));
        tableDataResp.setBed(ObjectToTypes.parseString(map.get("BRCH")));
        tableDataResp.setPageNumber(currentWeek);
        tableDataResp.setHospitalized(ObjectToTypes.parseString(map.get("ZYHM")));
        //日期
        List<java.sql.Date> dateList = new ArrayList<java.sql.Date>();
        //患病日数
		List<Integer> daysAfterSick = new ArrayList<Integer>();
		//术后日数
		List<String> daysAfterOperation = new ArrayList<String>();

        Date ryrq = (Date) map.get("RYRQ");
        Date cyrq = (Date) map.get("CYRQ");
        if (cyrq == null) {
            cyrq = new Date();
        }
        int dateStart = currentWeek * 7;
//		int weeks = BUHISUtil.getWeeksForTem(ryrq, cyrq) - 1;
//		int dateEnd = dateStart + 6;
//		if (19 == weeks) {
//			dateEnd = dateStart
//					+ BUHISUtil.getPeriod(
//							BUHISUtil.getDateAfter(ryrq, dateStart), cyrq);
//		}
        //温度
        Map<String, Object> tempMap = new HashMap<String, Object>(16);
        List<Map<String, Object>> wdListMap = new ArrayList<Map<String, Object>>();


        //心率脉搏
        Map<String, Object> pulseMap = new HashMap<String, Object>(16);
        List<Map<String, Object>> pulseListMap = new ArrayList<Map<String, Object>>();
		
		//呼吸
		Map<String, Object> breathMap = new HashMap<String, Object>(16);
		List<Map<String, Object>> breathListMap = new ArrayList<Map<String,Object>>();
		
		//温度
		Map<String, Object> painMap = new HashMap<String, Object>(16);
		List<Map<String, Object>> painListMap = new ArrayList<Map<String,Object>>();
		
		List<List<StatisticsSummary>> statisticsSummaryList = new ArrayList<List<StatisticsSummary>>();
		List<StatisticsSummary> list = new ArrayList<TableDataResp.StatisticsSummary>();
//		StatisticsSummary nl = new StatisticsSummary();
		StatisticsSummary cl = new StatisticsSummary(); 
		StatisticsSummary rl = new StatisticsSummary(); 
		StatisticsSummary db = new StatisticsSummary(); 
//		nl.setXmmc("尿量(ml)");
//		cl.setXmmc("出量(ml)");
//		rl.setXmmc("入量(ml)");
		db.setXmmc("大便(次/日)");
		
		//出量,入量,尿量
		List<String> nllist = new ArrayList<String>();
		List<String> cllist = new ArrayList<String>();
		List<String> rllist = new ArrayList<String>();
		//体重
		List<String> weightList = new ArrayList<String>();
		//身高
		List<String> heightList = new ArrayList<String>();
		//大便
		List<String> shitTimesList = new ArrayList<String>();
		//过敏药物
		List<String> skinTestRstList = new ArrayList<String>();
		//血压
		List<List<String>> bloodPressureList = new ArrayList<List<String>>();
		//出入量
		List<String> inAndOutList = new ArrayList<String>();
		//自定义列
		List<List<CustomSummary>> customSummaryList = new ArrayList<List<CustomSummary>>();
		List<CustomSummary> customlist = new ArrayList<TableDataResp.CustomSummary>();

		SysXtcs sysXtcs = sysXtcsCacheSer.getByJGIdAndCsmc(user.getHospitalId(), SysXtcsCsmcCts.TWDXSFS);
		int type = sysXtcs == null ? 2 : ObjectToTypes.parseInt(sysXtcs.getCsz());
		int xmhs[] = {1,3,7,8,11,31,32,33,108,145,502};	

		for (int i = dateStart; i <= dateStart + 6; i++) {
            java.sql.Date date = DateUtil.date(BUHISUtil
                    .getDateAfter(ryrq, i)).toSqlDate();
            dateList.add(date);

            long betweenDay = DateUtil.between(ryrq, date, DateUnit.DAY);
            if (betweenDay == 0) {
                //入院日期
                int ryTime = DateUtil.hour(ryrq, true);
                if (type == 2) {
                    ryTime = (0 <= ryTime && ryTime < 6) ? 2 : (6 <= ryTime && ryTime < 10) ? 6 :
                            (10 <= ryTime && ryTime < 14) ? 10 : (14 <= ryTime && ryTime < 18) ? 14 :
						(18 <= ryTime && ryTime < 22) ? 18 : 22;
				}	
				Map<String, Object> ryMap = new HashMap<String, Object>(16);
				ryMap.put("time", ryTime);
				ryMap.put("text", "入\n院\n丨\n" + DigitalConversionUtil.wrapStringLoop(DigitalConversionUtil.numberToChinese(
						DateUtil.hour(ryrq, true))) + "时\n" + DigitalConversionUtil.wrapStringLoop(
								DigitalConversionUtil.numberToChinese(DateUtil.minute(ryrq))) + "分");
				ryMap.put("position", 42);
				ryMap.put("cellMin", 34);
				ryMap.put("cellSplit", 0.2);
				ryMap.put("type", "text");
				ryMap.put("color", "red");
				ryMap.put("shape", null);
				chartListMap.add(ryMap);
			}
			daysAfterSick.add(ObjectToTypes.parseInt(betweenDay) + 1);
			
			ImTwdnr imTwdnr = new ImTwdnr(); 
			imTwdnr.setZyh(zyh);
			imTwdnr.setZfbz(0);
			imTwdnr.setSortColumns("CZSJ");
			List<ImTwdnr> imTwdnrList = imTwdnrDao.findByEntity(imTwdnr);
			if(CollectionUtils.isEmpty(imTwdnrList)) {
				daysAfterOperation.add("");
			}else {
				StringBuffer operation = new StringBuffer();
				for(ImTwdnr twdnr :imTwdnrList) {	
					String beginDate = DateUtil.format(date, DatePattern.NORM_DATE_PATTERN);
					String endDate = DateUtil.format(DateUtil.date(twdnr.getCzsj()).toSqlDate(), DatePattern.NORM_DATE_PATTERN);
					String czsjDate = DateUtil.format(twdnr.getCzsj(), DatePattern.NORM_DATE_PATTERN);
					long days = DateUtil.between(DateUtil.parse(beginDate, DatePattern.NORM_DATE_PATTERN), DateUtil.parse(endDate, DatePattern.NORM_DATE_PATTERN), DateUnit.DAY);			
					//手术分娩都算术后日期
					if(twdnr.getCzlx().intValue() == 1 || twdnr.getCzlx().intValue() == 2) {
						if(DateUtil.parse(beginDate, DatePattern.NORM_DATE_PATTERN).compareTo(DateUtil.parse(czsjDate, DatePattern.NORM_DATE_PATTERN)) == 1) {
							operation.append("/").append(days);
						}else if(DateUtil.parse(beginDate, DatePattern.NORM_DATE_PATTERN).compareTo(DateUtil.parse(czsjDate, DatePattern.NORM_DATE_PATTERN)) == 0) {
							operation.append("/0"); 
						}
					}
					
					if(days == 0) {
						String text = "";
						Map<String, Object> ssMap = new HashMap<String, Object>(16);
						int hourTime = DateUtil.hour(twdnr.getCzsj(), true);
						if(type == 2) {
							hourTime = (0 <= hourTime && hourTime < 6) ? 2 : (6 <= hourTime && hourTime < 10) ? 6 : 
								(10 <= hourTime && hourTime < 14) ? 10 : (14 <= hourTime && hourTime < 18) ? 14 :
								(18 <= hourTime && hourTime < 22) ? 18 : 22;
						}	
						ssMap.put("time", hourTime + ((i-dateStart) * 24));
						if(twdnr.getCzlx().intValue() == 1) {
							text = "手\n术\n";	
						}else if(twdnr.getCzlx().intValue() == 2) {
							text = "分\n娩\n";
						}else if(twdnr.getCzlx().intValue() == 3) {
							text = "死\n亡\n";
						}else if(twdnr.getCzlx().intValue() == 4) {
							String cznr = twdnr.getCznr();
							char[] strArr = cznr.toCharArray(); 
							for (int j = 0; j < strArr.length; j++) { 
								text+=strArr[j] + "\n";
							}; 
						}
						ssMap.put("text", text + "丨\n" + DigitalConversionUtil.wrapStringLoop(DigitalConversionUtil.numberToChinese(
								DateUtil.hour(twdnr.getCzsj(), true))) + "时\n" + DigitalConversionUtil.wrapStringLoop(
										DigitalConversionUtil.numberToChinese(DateUtil.minute(twdnr.getCzsj()))) + "分");
						ssMap.put("position", 42);
						ssMap.put("cellMin", 29);
						ssMap.put("cellSplit", 0.2);
						ssMap.put("type", "text");
						ssMap.put("color", "red");
						ssMap.put("shape", null);
						chartListMap.add(ssMap);
					}	
					
				}
				if(operation.toString().startsWith("/")) {
					daysAfterOperation.add(operation.toString().substring(1));
				}else {
					daysAfterOperation.add(operation.toString());
				}
				
			}
			//1:温度2:脉搏3:呼吸4:心率5:收缩6:舒张7:体重8:身高31:大便32:尿量33:出量108:过敏药物145:入量502:疼痛评分
			for(int xmh : xmhs) {
				List<Map<String, Object>> listMap = nisSmtzDao.querySmtzInfoByZyhAndDate(zyh, date, xmh, type, null);
				if(xmh == 1) {
					for(Map<String, Object> twMap : listMap) {
						if(!StrUtil.isBlankIfStr(twMap.get("XMXB"))) {
							if(twMap.get("XMXB").toString().indexOf("不升") > -1) {
								Map<String, Object> bsMap = new HashMap<String, Object>(16);
								bsMap.put("time", ObjectToTypes.parseInt(twMap.get("HOUR")) 
										+ ((i-dateStart) * 24));
								bsMap.put("text", "不\n升\n");
								bsMap.put("position", 34.9);
								bsMap.put("cellMin", 34);
								bsMap.put("cellSplit", 0.2);
								bsMap.put("type", "text");
								bsMap.put("color", "blue");
								bsMap.put("shape", null);
								bsMap.put("isNoRise", 1);
								chartListMap.add(bsMap);
							}else {
								Map<String, Object> wdMap = new HashMap<String, Object>(16);
								wdMap.put("time", ObjectToTypes.parseInt(twMap.get("HOUR")) 
										+ ((i-dateStart) * 24));
								wdMap.put("tips", twMap.get("XMXB") + "" + twMap.get("TZNR"));
								wdMap.put("value", twMap.get("TZNR"));
								wdMap.put("shape", "x");
								wdMap.put("Break", "false");
								wdMap.put("type", "temperature");
								wdMap.put("xmxb", twMap.get("XMXB"));
								wdMap.put("others", new ArrayList<Object>());			
								List<Map<String, Object>> jwlist = nisSmtzDao.queryJwSmtzInfo(zyh, 
										DateUtil.parse(twMap.get("CJSJ") + "").toTimestamp(), 9, type);
								//降温
								List<Map<String, Object>> jwListMap = new ArrayList<Map<String,Object>>();
								if(CollectionUtils.isNotEmpty(jwlist)) {
									Map<String, Object> jwMap = new HashMap<String, Object>(16);
									jwMap.put("extra", jwlist.get(0).get("TZNR"));
									jwMap.put("extraColor", "red");
									jwMap.put("extraTips", "降温");
									jwListMap.add(jwMap);
								}
								wdMap.put("extraArr", jwListMap);
								wdListMap.add(wdMap);
							}	
						}	
					}
				}else if(xmh == 7) {
					weightList.add(CollectionUtils.isEmpty(listMap) ? "" : ObjectToTypes.parseString(listMap.get(0).get("TZNR")));
				}else if( xmh == 8 ) {
					heightList.add(CollectionUtils.isEmpty(listMap) ? "" : ObjectToTypes.parseString(listMap.get(0).get("TZNR")));
				}else if( xmh == 31 ) {
					shitTimesList.add(CollectionUtils.isEmpty(listMap) ? "" : ObjectToTypes.parseString(listMap.get(0).get("TZNR")));
				}else if( xmh == 32 ) {
					nllist.add(CollectionUtils.isEmpty(listMap) ? "" : ObjectToTypes.parseString(listMap.get(0).get("TZNR")));
				}else if( xmh == 108 ) {
					if(CollectionUtils.isEmpty(listMap)) {
						skinTestRstList.add("");
					}else {
						DictDto dictDto = sysFlagDataValueOutSer.findFlagValueByDataIdAndValueCode("TW000001", ObjectToTypes.parseString(listMap.get(0).get("TZNR")));
						skinTestRstList.add(dictDto == null ? "" : dictDto.getName());
					}					
				}else if( xmh == 502 ) {
					if(CollectionUtils.isNotEmpty(listMap)) {
						Map<String, Object> ttMap = new HashMap<String, Object>(16);
						ttMap.put("time", ObjectToTypes.parseInt(listMap.get(0).get("HOUR")) 
								+ ((i-dateStart) * 24));
						ttMap.put("tips", "疼痛" + listMap.get(0).get("TZNR"));
						ttMap.put("value", ObjectToTypes.parseDouble(listMap.get(0).get("TZNR"))/2);
						ttMap.put("shape", "P");
						ttMap.put("Break", "false");
						ttMap.put("type", "pain");
						ttMap.put("extraArr", new ArrayList<Object>());
						ttMap.put("others", new ArrayList<Object>());
						painListMap.add(ttMap);
					}
				}		
				
			}
			
			//33:出量145:入量
			List<Map<String, Object>> cllistMap = nisSmtzDao.querySmtzInfoByZyhAndDate(zyh, date, 33, type, null);
			List<Map<String, Object>> rllistMap = nisSmtzDao.querySmtzInfoByZyhAndDate(zyh, date, 145, type, null);
			if(CollectionUtils.isNotEmpty(cllistMap) && CollectionUtils.isNotEmpty(rllistMap)) {
				inAndOutList.add(ObjectToTypes.parseString(rllistMap.get(0).get("TZNR") + "/" + cllistMap.get(0).get("TZNR")));
			}else if(CollectionUtils.isNotEmpty(cllistMap) && CollectionUtils.isEmpty(rllistMap)) {
				inAndOutList.add(ObjectToTypes.parseString("/" + cllistMap.get(0).get("TZNR")));
			}else if(CollectionUtils.isEmpty(cllistMap) && CollectionUtils.isNotEmpty(rllistMap)) {
				inAndOutList.add(ObjectToTypes.parseString(rllistMap.get(0).get("TZNR") + "/"));
			}else {
				inAndOutList.add("");
			}
			
			List<Integer> intList = new ArrayList<Integer>();
			intList.add(3);
			intList.add(11);
			List<Map<String, Object>> hxlistMap = nisSmtzDao.querySmtzInfoByZyhAndDate(zyh, date, null, type, intList);
			for(Map<String, Object> hxfzMap : hxlistMap) {
				int xmh = ObjectToTypes.parseInt(hxfzMap.get("XMH"));
				Map<String, Object> hxMap = new HashMap<String, Object>(16);
				hxMap.put("time", ObjectToTypes.parseInt(hxfzMap.get("HOUR")) 
						+ ((i-dateStart) * 24));
				if(xmh == 3) {
					hxMap.put("tips", "呼吸" + hxfzMap.get("TZNR"));
					hxMap.put("shape", "empty-circle");
				}else {
					hxMap.put("tips", "辅助呼吸" + hxfzMap.get("TZNR"));
					hxMap.put("shape", "R");
				}
				hxMap.put("value", hxfzMap.get("TZNR"));
				hxMap.put("Break", "false");
				breathListMap.add(hxMap);
			}
			

			
			//脉搏心率
			List<Map<String, Object>> mblistMap = nisSmtzDao.queryPulsePacemakerHeartRate(zyh, date, type);
			for(Map<String, Object> mbMap : mblistMap) {
				Map<String, Object> mbxlMap = new HashMap<String, Object>(16);
				mbxlMap.put("time", ObjectToTypes.parseInt(mbMap.get("HOUR")) 
						+ ((i-dateStart) * 24));
				mbxlMap.put("Break", "false");
				if(mbMap.get("MB") != null) {
					mbxlMap.put("v2", mbMap.get("MB"));
					mbxlMap.put("v2Tips", "脉搏" + mbMap.get("MB"));
				}
				if(mbMap.get("XL") != null) {			
					mbxlMap.put("v1", mbMap.get("XL"));
					mbxlMap.put("v1Tips", "心率" + mbMap.get("XL"));
				}
				if(mbMap.get("QBQ") != null) {			
					mbxlMap.put("v2", mbMap.get("QBQ"));
					mbxlMap.put("v2Tips", "起搏器" + mbMap.get("QBQ"));
					mbxlMap.put("isPacemaker", 1);
				}
				pulseListMap.add(mbxlMap);
			}
		
			//血压
			List<String> strList = new ArrayList<String>();
			Map<String, Object> sfMap = new HashMap<String, Object>(16);
			List<Map<String, Object>> sflistMap = nisSmtzDao.querySmtzInfoByZyhAndDate(zyh, date, 5, type, null);
			if(CollectionUtils.isNotEmpty(sflistMap)) {
				if(sflistMap.size() == 1) {
					if(ObjectToTypes.parseInt(sflistMap.get(0).get("HOUR")) < 12 ) {						
						sfMap.put("1", sflistMap.get(0) == null ? "" : sflistMap.get(0).get("TZNR"));
					}else {						
						sfMap.put("2", sflistMap.get(0) == null ? "" : sflistMap.get(0).get("TZNR"));
					}
				}else if(sflistMap.size() >= 2) {
					sfMap.put("2", sflistMap.get(0) == null ? "" : sflistMap.get(0).get("TZNR"));
					sfMap.put("1", sflistMap.get(1) == null ? "" : sflistMap.get(1).get("TZNR"));
				}		
			}
			
			Map<String, Object> szMap = new HashMap<String, Object>(16);
			List<Map<String, Object>> szlistMap = nisSmtzDao.querySmtzInfoByZyhAndDate(zyh, date, 6, type, null);
			if(CollectionUtils.isNotEmpty(szlistMap)) {
				if(szlistMap.size() == 1) {
					if(ObjectToTypes.parseInt(szlistMap.get(0).get("HOUR")) < 12 ) {						
						szMap.put("1", szlistMap.get(0) == null ? "" : "/" + szlistMap.get(0).get("TZNR"));
					}else {						
						szMap.put("2", szlistMap.get(0) == null ? "" : "/" + szlistMap.get(0).get("TZNR"));
					}	
				}else if(szlistMap.size() >= 2) {
					szMap.put("2", szlistMap.get(0) == null ? "" : "/" + szlistMap.get(0).get("TZNR"));
					szMap.put("1", szlistMap.get(1) == null ? "" : "/" + szlistMap.get(1).get("TZNR"));
				}	
			}
			strList.add(ObjectToTypes.parseString(sfMap.get("1")) + ObjectToTypes.parseString(szMap.get("1")));
			strList.add(ObjectToTypes.parseString(sfMap.get("2")) + ObjectToTypes.parseString(szMap.get("2")));
			bloodPressureList.add(strList);	
			
		}
		//自定义
		NisZdytz nisZdytz = new NisZdytz();
		nisZdytz.setZyh(zyh);
		nisZdytz.setDqzs(currentWeek);
		nisZdytz.setJgid(user.getHospitalId());
		nisZdytz.setSortColumns("ZDYID ASC");
		List<NisZdytz> nisZdytzList = nisZdytzDao.findByEntity(nisZdytz);
		for(NisZdytz zdyObj : nisZdytzList) {
			CustomSummary customSummary = new CustomSummary();
			customSummary.setZdymc(zdyObj.getZdymc());
			List<String> zdyList = new ArrayList<String>();
			for (int j = dateStart; j <= dateStart + 6; j++) {
                java.sql.Date date = DateUtil.date(BUHISUtil
                        .getDateAfter(ryrq, j)).toSqlDate();
                List<Map<String, Object>> zdyListMap = nisSmtzDao.querySmtzInfoByZyhAndZdyxh(zyh, date, zdyObj.getZdyid());
                zdyList.add(CollectionUtils.isEmpty(zdyListMap) ? "" : ObjectToTypes.parseString(zdyListMap.get(0).get("TZNR")));
            }
			customSummary.setZdydata(zdyList);
			customlist.add(customSummary);
		}
		
		Collections.sort(wdListMap, new Comparator<Map<String,Object>>() {
			@Override
			public int compare(Map<String, Object> o1, Map<String, Object> o2) {
			//进行判断
			return ((Integer)o1.get("time")).compareTo((Integer)o2.get("time"));
			}
		});
		tempMap.put("cellMin", 34);
		tempMap.put("cellSplit", 0.2);
		tempMap.put("array", wdListMap);
		tempMap.put("type", "line");
		tempMap.put("color", "blue");
		tempMap.put("shape", "x-circle");
		chartListMap.add(tempMap);	
		
		Collections.sort(breathListMap, new Comparator<Map<String,Object>>() {
			@Override
			public int compare(Map<String, Object> o1, Map<String, Object> o2) {
			//进行判断
			return ((Integer)o1.get("time")).compareTo((Integer)o2.get("time"));
			}
		});
		breathMap.put("cellMin", 10);
		breathMap.put("cellSplit", 1);
		breathMap.put("array", breathListMap);
		breathMap.put("type", "line");
		breathMap.put("color", "black");
		breathMap.put("shape", "empty-circle");
		chartListMap.add(breathMap);


		pulseMap.put("bgColor", "rgba(255,0,0,0.7)");
		pulseMap.put("cellMin", 40);
		pulseMap.put("cellSplit", 4);
		pulseMap.put("array", pulseListMap);
		pulseMap.put("type", "area");
		pulseMap.put("color", "red");
		pulseMap.put("shape", null);
		chartListMap.add(pulseMap);
		
		Map<String, Object> chartMap = new HashMap<String, Object>(16);
		chartMap.put("text", null);
		chartMap.put("y", "28");
		chartMap.put("cellMin", -10);
		chartMap.put("cellSplit", 2);
		chartMap.put("array", new ArrayList<>());
		chartMap.put("type", "tag");
		chartMap.put("color", "black");
		chartMap.put("shape", "null");
		chartListMap.add(pulseMap);		
		chartMap.put("cellMin", 30);
		chartListMap.add(pulseMap);
		
		painMap.put("cellMin", 0);
		painMap.put("cellSplit", 1);
		painMap.put("array", painListMap);
		painMap.put("type", "line");
		painMap.put("color", "blue");
		painMap.put("shape", "null");
		painChartList.add(painMap);	
		
//		nl.setData(nllist);
//		cl.setData(cllist);
//		rl.setData(rllist);
		db.setData(shitTimesList);
	
//		list.add(rl);
//		list.add(cl);
		list.add(db);
//		list.add(nl);
		statisticsSummaryList.add(list);
		customSummaryList.add(customlist);
		tableDataResp.setDate(dateList);
		tableDataResp.setDaysAfterSick(daysAfterSick);
		tableDataResp.setDaysAfterOperation(daysAfterOperation);
		tableDataResp.setStatisticsSummary(statisticsSummaryList);
		tableDataResp.setCustomSummary(customSummaryList);
		tableDataResp.setWeight(weightList);
		tableDataResp.setHeight(heightList);
//		tableDataResp.setShitTimes(shitTimesList);
		tableDataResp.setSkinTestRst(skinTestRstList);
		tableDataResp.setBloodPressure(bloodPressureList);
		tableDataResp.setInAndOutList(inAndOutList);
		temperatureChartResp.setTableData(tableDataResp);
		temperatureChartResp.setChartData(chartListMap);
		temperatureChartResp.setPainChartData(painChartList);
		return temperatureChartResp;
	}
	
	/**
	 * 批量保存新增或修改住院体温单内容
	 * @Title: saveBatchTwdnr
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @param imTwdnrReqList    设定文件
	 * @return void    返回类型
	 * @author 龚方舟
	 * @throws
	 */
	public void saveBatchTwdnr(List<ImTwdnrReq> imTwdnrReqList) {
		for(ImTwdnrReq imTwdnrReq : imTwdnrReqList) {
			if(StrUtil.isNotBlank(ObjectToTypes.parseString(imTwdnrReq.getId()))) {
				ImTwdnr imtwdnr = imTwdnrDao.getById(imTwdnrReq.getId());
				BeanUtil.copyProperties(imTwdnrReq, imtwdnr, 
						CopyOptions.create().setIgnoreNullValue(true).setIgnoreError(true));
				imTwdnrDao.update(imtwdnr);
			}else {
				ImTwdnr imtwdnr = BeanUtil.toBean(imTwdnrReq, ImTwdnr.class);
				List<ImTwdnr> ret = imTwdnrDao.findByEntity(imtwdnr);
		        if(CollectionUtils.isNotEmpty(ret)){
		            throw BaseException.create("ERROR_DCTWORK_ZYTWDNR_0001");
		        }
				imtwdnr.setId(redisFactory.getTableKey(TableName.DB_NAME, TableName.IM_TWDNR));
		        imTwdnrDao.insert(imtwdnr);
			}
		}
	}
	
	
	/**
	 * 设置对象拼音代码
	 * @Title: setPydmCode
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @param obj
	 * @param @param name    设定文件
	 * @return void    返回类型
	 * @author 龚方舟
	 * @throws
	 */
    public static void setPydmCode(Object obj, String name) {
        try {
        	String personNamePinyin = PinyinUtils.getSimplePinYin(name);
			Method pyMethod = obj.getClass().getMethod("setPydm", String.class);
			pyMethod.invoke(obj, personNamePinyin);
		} catch (Exception e) {
			throw BaseException.create("ERROR_USER_0012!");
		}
    }

    /**
     * 批量保存体温单生命体征
     * @Title: saveBatchTwdSmtzInfo
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @param @param zyTwdReqList
     * @param @param user    设定文件
     * @return void    返回类型
     * @author 龚方舟
     * @throws
     */
    @Transactional(rollbackFor = Exception.class)
	public void saveBatchTwdSmtzInfo(NisSmtzBatchSaveReq nisSmtzBatchSaveReq, SysUser user) {
		Integer jgid = user.getHospitalId();
		Integer hour = nisSmtzBatchSaveReq.getHour();
		String queryDate = nisSmtzBatchSaveReq.getQueryDate();
		List<NisSmtzBatch> list = nisSmtzBatchSaveReq.getNisSmtzBatchList();
		for(NisSmtzBatch req : list) {
            Integer zyh = req.getZyh();
            ImHzry imHzry = imHzryDao.getById(zyh);
            Timestamp ryrq = imHzry.getRyrq();
            Double currentWeek = Math.floor(Math.floor((DateUtil.parse(queryDate).getTime() - ryrq.getTime()) / 1000 / 60 / 60 / 24) / 7);
            Map<String, Object> smtz = BUHISUtil.caseInsensitiveMap(req);
            Map<String, Object> parMap = new HashMap<String, Object>(16);
            List<NisTzxm> nisTzxmList = nisTzxmDao.findByEntity(null);
            Integer cjzh = redisFactory.getTableKey(TableName.DB_NAME, TableName.NIS_SMTZ + ".cjzh");
            NisSmtz smtzSave = new NisSmtz();
            smtzSave.setCjzh(cjzh);
            smtzSave.setZyh(zyh);
            smtzSave.setJhbz(1);
            smtzSave.setBrks(imHzry.getBrks());
            smtzSave.setBrbq(imHzry.getBrbq());
            smtzSave.setBrch(imHzry.getBrch());
            smtzSave.setFcbz(0);
            smtzSave.setTwdxs(0);
            smtzSave.setJlsj(DateUtil.date().toTimestamp());
            smtzSave.setJlgh(ObjectToTypes.parseString(user.getUserId()));
            smtzSave.setZfbz(0);
            smtzSave.setYcbz(1);
            smtzSave.setCjsj(req.getCjsj());
            smtzSave.setJgid(jgid);
            smtzSave.setSjd(hour);      
			if(OperationTypeEnum.update.getCode().equals(req.getOpStatus())){
				parMap.put("queryDate", queryDate);
	        	parMap.put("zyh", zyh);
	        	parMap.put("hour", hour);
	        	List<Map<String, Object>> tzxmParlist = nisSmtzDao.queryBatchSmtzAndBqtzInfo(parMap);
	        	for (int i = 0; i < tzxmParlist.size(); i++) {
	        		Map<String, Object> tzMap =  tzxmParlist.get(i);
	        		if (smtz.containsKey(tzMap.get("pydm"))) {	
	        			NisSmtz updateReq = new NisSmtz();
	        			updateReq.setCjh(ObjectToTypes.parseInt(tzMap.get("cjh")));
	        			if(tzMap.get("xmh") != null){
	        				if ("1".equals(ObjectToTypes.parseString(tzMap.get("xmh")))) {// 体温
	        					//项目下标（体温类型等）
	        					updateReq.setXmxb(req.getXmxb());
	        				}
	        			}
	        			if (tzMap.get("pydm") != null) {
	        				if(StrUtil.isNotBlank(ObjectToTypes.parseString(smtz.get(tzMap.get("pydm"))))) {
	        					updateReq.setTznr(ObjectToTypes.parseString(smtz.get(tzMap.get("pydm"))));
	        				}else {
	        					nisSmtzDao.deleteById(ObjectToTypes.parseInt(tzMap.get("cjh")));
	        				}
	        			}
	        			updateReq.setJgid(jgid);
	        			nisSmtzDao.update(updateReq);
	        			smtz.remove(tzMap.get("pydm").toString().toLowerCase());
	        		}
				}
	        	for (NisTzxm tzxm : nisTzxmList) {
	        		String pydm = tzxm.getPydm();
	        		if (smtz.containsKey(pydm) && smtz.get(pydm) != null && !"".equals(smtz.get(pydm))) {
	        			smtzSave.setXmh(ObjectToTypes.parseInt(tzxm.getXmh()));
	        			if (ObjectToTypes.parseInt(tzxm.getXmh()) == 1) {// 体温
	        				req.setXmxb(req.getXmxb());
	        			}
	        			smtzSave.setTznr(ObjectToTypes.parseString(smtz.get(pydm)));
	        			smtzSave.setCjh(redisFactory.getTableKey(TableName.DB_NAME, TableName.NIS_SMTZ));
	        			nisSmtzDao.insert(smtzSave);
	        		}
	        	} 	
	        	if(StrUtil.isBlankIfStr(req.getZdyid1())) {
	        		saveBatchZdynr(req.getZdymc1(), req.getZdynr1(), req.getXmdm1(), zyh, jgid, 
	        				currentWeek.intValue(), smtzSave);
	        	}else {
	        		updateBatchZdynr(req.getZdymc1(), req.getZdynr1(), req.getXmdm1(), req.getZdyid1(), 
	        				zyh, jgid, currentWeek.intValue(), queryDate, hour);
	        	}
	        	
	        	if(StrUtil.isBlankIfStr(req.getZdyid2())) {
	        		saveBatchZdynr(req.getZdymc2(), req.getZdynr2(), req.getXmdm2(), zyh, jgid, 
	        				currentWeek.intValue(), smtzSave);
	        	}else {
	        		updateBatchZdynr(req.getZdymc2(), req.getZdynr2(), req.getXmdm2(), req.getZdyid2(), 
	        				zyh, jgid, currentWeek.intValue(), queryDate, hour);
	        	}
	        	
	        	if(StrUtil.isBlankIfStr(req.getZdyid3())) {
	        		saveBatchZdynr(req.getZdymc3(), req.getZdynr3(), req.getXmdm3(), zyh, jgid, 
	        				currentWeek.intValue(), smtzSave);
	        	}else {
	        		updateBatchZdynr(req.getZdymc3(), req.getZdynr3(), req.getXmdm3(), req.getZdyid1(), 
	        				zyh, jgid, currentWeek.intValue(), queryDate, hour);
	        	}
	        }else {	
	        	for (NisTzxm tzxm : nisTzxmList) {
	        		String pydm = tzxm.getPydm();
	        		if (smtz.containsKey(pydm) && smtz.get(pydm) != null && !"".equals(smtz.get(pydm))) {
	        			smtzSave.setXmh(ObjectToTypes.parseInt(tzxm.getXmh()));
	        			if (ObjectToTypes.parseInt(tzxm.getXmh()) == 1) {// 体温
	        				req.setXmxb(req.getXmxb());
	        			}
	        			smtzSave.setTznr(ObjectToTypes.parseString(smtz.get(pydm)));
	        			smtzSave.setCjh(redisFactory.getTableKey(TableName.DB_NAME, TableName.NIS_SMTZ));
	        			nisSmtzDao.insert(smtzSave);
	        		}
	        	}       	
	        	saveBatchZdynr(req.getZdymc1(), req.getZdynr1(), req.getXmdm1(), zyh, jgid, 
	        			currentWeek.intValue(), smtzSave);
	        	saveBatchZdynr(req.getZdymc2(), req.getZdynr2(), req.getXmdm1(), zyh, jgid, 
	        			currentWeek.intValue(), smtzSave);
	        	saveBatchZdynr(req.getZdymc3(), req.getZdynr3(), req.getXmdm1(), zyh, jgid, 
	        			currentWeek.intValue(), smtzSave);
			}
		}	
	}
	
    /**
     * 批量录入查询
     * @Title: queryTemperatureHzInfo
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @param @param queryDate
     * @param @param hour
     * @param @param user
     * @param @return    设定文件
     * @return List<NisSmtzBatchListResp>    返回类型
     * @author 龚方舟
     * @throws
     */
    public List<NisSmtzBatchListResp> queryTemperatureHzInfo(NisSmtzBatchQueryReq req, SysUser user) {
    	String queryDate = req.getQueryDate();
    	Integer hour = req.getHour();
    	Integer bqdm = req.getBqdm();
    	Set<Integer> zyhList = new HashSet<Integer>();
    	if(!req.isQbFlag()) {
	    	boolean xrFlag = req.isXrFlag();
	    	boolean ssFlag = req.isSsFlag();
	    	boolean yjFlag = req.isYjhlFlag();
	    	boolean frFlag = req.isFrFlag();
	    	boolean bwFlag = req.isBwFlag();
	    	boolean bzFlag = req.isBzFlag();
	    	boolean tjFlag = req.isTjFlag();
	    	//新入患者(每日2次*3天)
	    	if(xrFlag && (hour == 6 || hour == 14)) {
	    		zyhList.addAll(imHzryDao.queryNewAdmissionPatient(bqdm, user.getHospitalId(), 1, queryDate));
	    	}
	    	//手术患者
	    	if(ssFlag) {
	    		//术前一天2次
	    		if(hour == 14 || hour == 18) {
	    			zyhList.addAll(imHzryDao.querySsPatient(bqdm, user.getHospitalId(), 1, queryDate));
	    		}
	    		//术日晨1次
	    		if(hour == 6) {
	    			zyhList.addAll(imHzryDao.querySsPatient(bqdm, user.getHospitalId(), 2, queryDate));
	    		}
	    		//术后每天4次*3天
	    		if(hour == 6 || hour == 10 || hour == 14 || hour == 18) {
	    			zyhList.addAll(imHzryDao.querySsPatient(bqdm, user.getHospitalId(), 3, queryDate));
	    		}
	    	}
	    	//一级护理患者
	    	if(yjFlag && (hour == 6 || hour == 10 || hour == 14 || hour == 18)) {
	    		zyhList.addAll(imHzryDao.queryNewAdmissionPatient(bqdm, user.getHospitalId(), 2, queryDate));
	    	}
	    	//发热患者
	    	if(frFlag) {
	    		String datetime = queryDate + " " + (hour + 4) + ":00:00";
	    		if(hour == 22){
	    			datetime = datetime + "23:59:59";
	    		}
	    		if(hour == 6 || hour == 14) {
	    			zyhList.addAll(imHzryDao.queryFeverPatient(bqdm, user.getHospitalId(), 1, 
	    					DateUtil.parse(datetime, DatePattern.NORM_DATETIME_PATTERN).toTimestamp()));
	    		}
	    		if(hour == 6 || hour == 10 || hour == 14 || hour == 18) {
	    			zyhList.addAll(imHzryDao.queryFeverPatient(bqdm, user.getHospitalId(), 2, 
	    					DateUtil.parse(datetime, DatePattern.NORM_DATETIME_PATTERN).toTimestamp()));
	    		}
	    		if(hour == 2 || hour == 6 || hour == 10 || hour == 14 || hour == 18 || hour == 22) {
	    			zyhList.addAll(imHzryDao.queryFeverPatient(bqdm, user.getHospitalId(), 2, 
	    					DateUtil.parse(datetime, DatePattern.NORM_DATETIME_PATTERN).toTimestamp()));
	    		}
	    	}
	    	if(bwFlag) {
	    		zyhList.addAll(imHzryDao.queryNewAdmissionPatient(bqdm, user.getHospitalId(), 3, queryDate));
	    	}
	    	if(bzFlag) {
	    		zyhList.addAll(imHzryDao.queryNewAdmissionPatient(bqdm, user.getHospitalId(), 4, queryDate));
	    	}
	    	if(tjFlag) {
	    		zyhList.addAll(imHzryDao.queryNewAdmissionPatient(bqdm, user.getHospitalId(), 5, queryDate));
	    	}
    	}
    	List<Map<String, Object>> list = imHzryDao.queryTemperatureHzInfo(hour, queryDate, 
    			user.getHospitalId(), bqdm);
    	Map<Integer, List<Map<String, Object>>> groups = list.stream().collect(Collectors.groupingBy(
    			o -> ObjectToTypes.parseInt(o.get("zyh")), LinkedHashMap::new, Collectors.toList()));
		list = groups.entrySet().stream().map(o -> o.getValue().stream().map(e -> {
			Map<String, Object> x = new HashMap<String, Object>(16);
			x.put("zyh", e.get("zyh"));
			x.put("brxm", e.get("brxm"));
			x.put("brks", e.get("brks"));
			x.put("brch", e.get("brch"));
			x.put("cjsj", e.get("cjsj"));
			if(e.get("xmh") != null) {
				if("1".equals(ObjectToTypes.parseString(e.get("xmh")))) {
					x.put("tw", e.get("tznr"));
					x.put("xmxb", e.get("xmxb"));
				}else if("2".equals(ObjectToTypes.parseString(e.get("xmh")))) {
					x.put("mb", e.get("tznr"));
				}else if("3".equals(ObjectToTypes.parseString(e.get("xmh")))) {
					x.put("hx", e.get("tznr"));
				}else if("4".equals(ObjectToTypes.parseString(e.get("xmh")))) {
					x.put("xl", e.get("tznr"));
				}else if("5".equals(ObjectToTypes.parseString(e.get("xmh")))) {
					x.put("ssy", e.get("tznr"));
				}else if("6".equals(ObjectToTypes.parseString(e.get("xmh")))) {
					x.put("szy", e.get("tznr"));
				}else if("7".equals(ObjectToTypes.parseString(e.get("xmh")))) {
					x.put("tz", e.get("tznr"));
				}else if("8".equals(ObjectToTypes.parseString(e.get("xmh")))) {
					x.put("sg", e.get("tznr"));
				}else if("9".equals(ObjectToTypes.parseString(e.get("xmh")))) {
					x.put("jw", e.get("tznr"));
				}else if("10".equals(ObjectToTypes.parseString(e.get("xmh")))) {
					x.put("qbq", e.get("tznr"));
				}else if("11".equals(ObjectToTypes.parseString(e.get("xmh")))) {
					x.put("fzhx", e.get("tznr"));
				}else if("31".equals(ObjectToTypes.parseString(e.get("xmh")))) {
					x.put("db", e.get("tznr"));
				}else if("32".equals(ObjectToTypes.parseString(e.get("xmh")))) {
					x.put("nl", e.get("tznr"));
				}else if("33".equals(ObjectToTypes.parseString(e.get("xmh")))) {
					x.put("qtcl", e.get("tznr"));
				}else if("108".equals(ObjectToTypes.parseString(e.get("xmh")))) {
					x.put("ps", e.get("tznr"));
				}else if("502".equals(ObjectToTypes.parseString(e.get("xmh")))) {
					x.put("ttpf", e.get("tznr"));
				}
			}
			return x;
		}).reduce((sum ,y) -> {sum.putAll(y);
			return sum;
		}).get()).collect(Collectors.toList());
    	if(CollectionUtils.isNotEmpty(zyhList)) {
    		list = list.stream().filter(o -> zyhList.contains(o.get("zyh"))).collect(Collectors.toList());
    	}else if(!req.isQbFlag() && zyhList.isEmpty()){
    		return null;
    	}
    	for(Map<String, Object> map : list) {
    		List<Map<String, Object>> smtzList = nisSmtzDao.queryZdySmtz(ObjectToTypes.parseInt(map.get("zyh")), 
    				queryDate, hour, user.getHospitalId());
            if (CollectionUtils.isNotEmpty(smtzList)) {
                for (int i = 0; i < smtzList.size(); i++) {
                    map.put("xmdm" + (i + 1), smtzList.get(i).get("xmdm"));
                    map.put("zdymc" + (i + 1), smtzList.get(i).get("zdymc"));
                    map.put("zdynr" + (i + 1), smtzList.get(i).get("tznr"));
                    map.put("zdyid" + (i + 1), smtzList.get(i).get("zdyid"));
                }
            }
        }
        return BUHISUtil.ListToResultSet(list, new NisSmtzBatchListResp());
    }
   
    /**
     * 批量保存自定义生命体征
     * @Title: saveBatchZdynr
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @param @param zdymc
     * @param @param zdynr
     * @param @param xmdm
     * @param @param zyh
     * @param @param jgid
     * @param @param currentWeek
     * @param @param smtzObj    设定文件
     * @return void    返回类型
     * @author 龚方舟
     * @throws
     */
    public void saveBatchZdynr(String zdymc, String zdynr, Integer xmdm, Integer zyh, Integer jgid, 
    		Integer currentWeek, NisSmtz smtz) {
    	if(StrUtil.isNotBlank(zdymc) && StrUtil.isNotBlank(zdynr)) {
    		NisZdytz nisZdytz = new NisZdytz();
    		nisZdytz.setZyh(zyh);
			nisZdytz.setJgid(jgid);
			nisZdytz.setDqzs(currentWeek);
			setPydmCode(nisZdytz, zdymc);
			nisZdytz.setXmdm(xmdm);
			nisZdytz.setZdymc(zdymc);
			Integer zdyid = redisFactory.getTableKey(TableName.DB_NAME, TableName.NIS_ZDYTZ);
			nisZdytz.setZdyid(zdyid);
			nisZdytzDao.insert(nisZdytz);
			
			smtz.setXmh(null);
			smtz.setTznr(zdynr);
			smtz.setZdyxh(zdyid);
			smtz.setCjh(redisFactory.getTableKey(TableName.DB_NAME, TableName.NIS_SMTZ));
			nisSmtzDao.insert(smtz);
    	}
    }
    
    /**
     * 批量修改自定义生命体征
     * @Title: updateBatchZdynr
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @param @param zdymc
     * @param @param zdynr
     * @param @param xmdm
     * @param @param zdyid
     * @param @param zyh
     * @param @param jgid
     * @param @param currentWeek
     * @param @param queryDate
     * @param @param hour    设定文件
     * @return void    返回类型
     * @author 龚方舟
     * @throws
     */
    public void updateBatchZdynr(String zdymc, String zdynr, Integer xmdm, Integer zdyid, Integer zyh, Integer jgid, 
    		Integer currentWeek, String queryDate, Integer hour) {
    	if(StrUtil.isNotBlank(zdymc) && StrUtil.isNotBlank(zdynr)) {
	    	NisZdytz nisZdytzUpdate = new NisZdytz();
			nisZdytzUpdate.setZyh(zyh);
			nisZdytzUpdate.setJgid(jgid);
			nisZdytzUpdate.setDqzs(currentWeek);
			setPydmCode(nisZdytzUpdate, zdymc);
			nisZdytzUpdate.setXmdm(xmdm);
			nisZdytzUpdate.setZdymc(zdymc);
			nisZdytzUpdate.setZdyid(zdyid);
			nisZdytzDao.update(nisZdytzUpdate);
			
			List<Map<String, Object>> listMap = nisSmtzDao.queryZdySmtzByHour(zyh, queryDate, hour, zdyid, jgid);
			if(CollectionUtils.isNotEmpty(listMap)) {
				NisSmtz nisSmtz = new NisSmtz();
				nisSmtz.setCjh(ObjectToTypes.parseInt(listMap.get(0).get("cjh")));
				nisSmtz.setJgid(jgid);
				nisSmtz.setTznr(zdynr);
				nisSmtzDao.update(nisSmtz);
			}
    	}
    }
    
}
