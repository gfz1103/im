package com.buit.cis.nurse.service;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.dubbo.config.annotation.DubboReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.buit.aop.lock.Locked;
import com.buit.apply.response.OpZt02DetailResp;
import com.buit.apply.service.OpZt02Service;
import com.buit.cis.dctwork.dao.CisHzyzDao;
import com.buit.cis.dctwork.dao.CisHzyzZtDao;
import com.buit.cis.dctwork.dao.NisFyyfDao;
import com.buit.cis.dctwork.dao.NisTj01Dao;
import com.buit.cis.dctwork.dao.NisTj02Dao;
import com.buit.cis.dctwork.dao.NisTymxDao;
import com.buit.cis.dctwork.model.CisHzyz;
import com.buit.cis.dctwork.model.CisHzyzZt;
import com.buit.cis.dctwork.model.NisFyyf;
import com.buit.cis.dctwork.model.NisTj01;
import com.buit.cis.dctwork.model.NisTj02;
import com.buit.cis.dctwork.model.NisTymx;
import com.buit.cis.dctwork.request.NisTymxReq;
import com.buit.cis.dctwork.request.NisTymxReq.NisTymxDetail;
import com.buit.cis.dctwork.response.CisHzyzResp;
import com.buit.cis.dctwork.response.NisTj02BackDetailResp;
import com.buit.cis.dctwork.response.NisTjMsgResp;
import com.buit.cis.dctwork.service.CisHzyzSer;
import com.buit.cis.dctwork.service.ImHzryByDctworkSer;
import com.buit.cis.dctwork.service.ImZkjlSer;
import com.buit.cis.ims.dao.ImCwszDao;
import com.buit.cis.ims.dao.ImCyjlDao;
import com.buit.cis.ims.dao.ImFeeFymxDao;
import com.buit.cis.ims.dao.ImFeeFymxYjDao;
import com.buit.cis.ims.dao.ImHzryDao;
import com.buit.cis.ims.dao.ImRcjlDao;
import com.buit.cis.ims.dao.ImZyjsDao;
import com.buit.cis.ims.enums.FeeYlsfxmXmlxEnum;
import com.buit.cis.ims.enums.FymxYplxEnum;
import com.buit.cis.ims.model.ImCwsz;
import com.buit.cis.ims.model.ImCyjl;
import com.buit.cis.ims.model.ImFeeFymx;
import com.buit.cis.ims.model.ImFeeFymxYj;
import com.buit.cis.ims.model.ImHzry;
import com.buit.cis.ims.model.ImRcjl;
import com.buit.cis.ims.model.ImZyjs;
import com.buit.cis.ims.preService.ZyjsPreService;
import com.buit.cis.ims.request.ImFeeFymxQueryReq;
import com.buit.cis.ims.request.ImFeeFymxSaveReq;
import com.buit.cis.ims.request.ImFeeFymxVoidReq;
import com.buit.cis.ims.request.ImFeeFymxYjPrintReq;
import com.buit.cis.ims.response.ImHzryYpSumbitResp;
import com.buit.cis.nurse.dao.NisTypcDao;
import com.buit.cis.nurse.dao.NisYzdyDao;
import com.buit.cis.nurse.dao.YjZy01Dao;
import com.buit.cis.nurse.dao.ZyYbxmfzClDao;
import com.buit.cis.nurse.enums.FymxZfpbEnum;
import com.buit.cis.nurse.model.NisTypc;
import com.buit.cis.nurse.model.NisYzdy;
import com.buit.cis.nurse.model.ZyYbxmfzCl;
import com.buit.cis.nurse.preSevice.NurseWorkPreService;
import com.buit.cis.nurse.request.CisHzyzExcuteReq;
import com.buit.cis.nurse.request.CisHzyzFjReq;
import com.buit.cis.nurse.request.CisHzyzFjReq.FjBody;
import com.buit.cis.nurse.request.CisHzyzNurseExReq;
import com.buit.cis.nurse.request.CisHzyzNurseExReq.CisHzyzBody;
import com.buit.cis.nurse.request.CisHzyzNurseExReq.CisHzyzBodyZx;
import com.buit.cis.nurse.request.CisHzyzReviewBatchReq;
import com.buit.cis.nurse.request.CisHzyzReviewReq;
import com.buit.cis.nurse.request.CisHzyzReviewSaveReq;
import com.buit.cis.nurse.request.CisHzyzReviewSaveReq.BatchBody;
import com.buit.cis.nurse.request.CisHzyzReviewUpdateReq;
import com.buit.cis.nurse.request.CisHzyzSheetReq;
import com.buit.cis.nurse.request.CisHzyzStopReviewReq;
import com.buit.cis.nurse.request.CisHzyzStopReviewReq.CisHzyzStopReviewBody;
import com.buit.cis.nurse.request.CisHzyzStopUpdateReq;
import com.buit.cis.nurse.request.CisHzyzorztExcuteReq;
import com.buit.cis.nurse.request.NisFyyfSaveReq;
import com.buit.cis.nurse.request.NisFyyfSaveReq.NisFyyfSaveBody;
import com.buit.cis.nurse.request.NisTymxSaveReq;
import com.buit.cis.nurse.response.CisHzyzCostResp;
import com.buit.cis.nurse.response.CisHzyzOrztResp;
import com.buit.cis.nurse.response.CisHzyzReviewBatchResp;
import com.buit.cis.nurse.response.CisHzyzReviewInfoResp;
import com.buit.cis.nurse.response.CisHzyzReviewPatientResp;
import com.buit.cis.nurse.response.CisHzyzReviewResp;
import com.buit.cis.nurse.response.CisHzyzXmResp;
import com.buit.cis.nurse.response.NisFyyfCheckResp;
import com.buit.cis.nurse.response.NisTymxBackResp;
import com.buit.cis.nurse.response.NisTymxRecordResp;
import com.buit.common.drug.dto.PubPharUnfreezeDto;
import com.buit.common.drug.service.impl.PubDrugKcdjSerImpl;
import com.buit.commons.BaseException;
import com.buit.commons.BaseManagerImp;
import com.buit.commons.EntityDao;
import com.buit.commons.SysUser;
import com.buit.constans.SysXtcsCsmcCts;
import com.buit.constans.TableName;
import com.buit.drug.request.QueryGrantedDrugReq;
import com.buit.drug.response.CisHzyzFymxGrantedResp;
import com.buit.drug.response.DrugsTypkDetailResp;
import com.buit.drug.service.DrugLogService;
import com.buit.drug.service.DrugsTypkOutSer;
import com.buit.his.request.LisRequestCancelReq;
import com.buit.his.request.LisRequestUpdateReq;
import com.buit.his.request.RequestUpdateDetail;
import com.buit.his.request.RequestUpdateInfo;
import com.buit.his.response.LisResp;
import com.buit.his.service.MdiHrpService;
import com.buit.his.service.MdiLisService;
import com.buit.his.treatment.ResultsUtil;
import com.buit.his.treatment.model.ZlRwfpUpdateReq;
import com.buit.his.treatment.service.ZlRwfpService;
import com.buit.mdi.response.HighQualityConsumablesResp;
import com.buit.mdi.service.MdiVejkfBatchViewService;
import com.buit.mms.antimi.service.AmqcKjywsysqService;
import com.buit.system.model.DicYjlxModel;
import com.buit.system.model.DicZlxmModel;
import com.buit.system.model.FeeYlsfxmModel;
import com.buit.system.request.FeeYpxzApiReq;
import com.buit.system.request.PubFyxzApiReq;
import com.buit.system.response.DiccLdxmglApiResp;
import com.buit.system.response.FeeYlsfxmOutResp;
import com.buit.system.response.SysXtcs;
import com.buit.system.service.DicKszdOutSer;
import com.buit.system.service.DicYjlxService;
import com.buit.system.service.DicZlxmService;
import com.buit.system.service.DiccLdxmglService;
import com.buit.system.service.FeeSfdlzfblOutSer;
import com.buit.system.service.FeeYlsfxmOutSer;
import com.buit.system.service.FeeYpxzService;
import com.buit.system.service.HrPersonnelService;
import com.buit.system.service.PubFyxzService;
import com.buit.system.service.SysXtcsCacheSer;
import com.buit.system.utill.ObjectToTypes;
import com.buit.utill.BUHISUtil;
import com.buit.utill.DateUtils;
import com.buit.utill.RedisFactory;
import com.buit.utill.ReturnEntity;
import com.google.common.base.Joiner;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.date.DateField;
import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;

/**
 * ???????????????
 *
 * @author ?????????
 * @ClassName: NurseworkSer
 * @Description: TODO(??????????????????????????????????????????)
 * @date 2020???6???10??? ??????9:55:52
 */
@Service
public class NurseworkSer extends BaseManagerImp<ImHzry, Integer> {

    static final Logger logger = LoggerFactory.getLogger(NurseworkSer.class);

    @Override
    public EntityDao<ImHzry, Integer> getEntityMapper() {
        return null;
    }

    @Autowired
    private NisFyyfDao nisFyyfDao;

    @Autowired
    private NisYzdyDao nisYzdyDao;

    @Autowired
    private ImHzryByDctworkSer imHzryByDctworkSer;

    @DubboReference
    private SysXtcsCacheSer sysXtcsCacheSer;

    @Autowired
    private CisHzyzDao cisHzyzDao;

    @Autowired
    private CisHzyzZtDao cisHzyzZtDao;

    @DubboReference
    private FeeYlsfxmOutSer feeYlsfxmOutSer;

    @Autowired
    private ImHzryDao imHzryDao;

//	@Autowired
//	private FeeXmzxksDao feeXmzxksDao;

    @Autowired
    private RedisFactory redisFactory;

    @Autowired
    private ImFeeFymxYjDao imFeeFymxYjDao;

    @Autowired
    private ImFeeFymxDao imFeeFymxDao;

//	@Autowired
//	private WlKfdzDao wlKfdzDao;

    @DubboReference(timeout = 10000)
    private DrugLogService drugLogService;

    @Autowired
    private NisTymxDao nisTymxDao;

    @Autowired
    private ImRcjlDao imRcjlDao;

    @Autowired
    private YjZy01Dao yjZy01Dao;

    @Autowired
    private ImCyjlDao imCyjlDao;

    @Autowired
    private ImCwszDao imCwszDao;

    @Autowired
    private ZyYbxmfzClDao zyYbxmfzClDao;

    @Autowired
    private NisTj01Dao nisTj01Dao;

    @Autowired
    private NisTj02Dao nisTj02Dao;

    @DubboReference
    private DiccLdxmglService diccLdxmglService;

    @DubboReference
    private OpZt02Service opZt02Service;

    @Autowired
    private CisHzyzSer cisHzyzSer;

    @Autowired
    private ImZkjlSer imZkjlSer;

    @DubboReference
    private FeeSfdlzfblOutSer feeSfdlzfblOutSer;

    @DubboReference
    private PubFyxzService pubFyxzService;

    @DubboReference
    private FeeYpxzService feeYpxzService;

    @DubboReference
    private DicYjlxService dicYjlxService;

    @DubboReference
    private DicZlxmService dicZlxmService;

    @DubboReference
    private AmqcKjywsysqService amqcKjywsysqService;

    @Autowired
    private NurseWorkPreService preService;

    @DubboReference
    private DrugsTypkOutSer drugsTypkOutSer;

    @Autowired
    private NisTypcDao nisTypcDao;

    @DubboReference
    private ZlRwfpService zlRwfpService;

    @Autowired
    public ZyjsPreService zyjsPreService;

    @Autowired
    private HrPersonnelService hrPersonnelService;

    @Autowired
    private DicKszdOutSer dicKszdOutSer;

    @DubboReference
    private MdiLisService mdiLisService;

    @DubboReference
    private MdiHrpService mdiHrpService;

    @DubboReference
    private MdiVejkfBatchViewService mdiVejkfBatchViewService;
    
    @Autowired
    private ImZyjsDao imZyjsDao;
    
    @Autowired
    private PubDrugKcdjSerImpl pubDrugKcdjSerImpl;

    /**
     * ?????????????????????????????????????????????
     *
     * @param @param  req
     * @param @param  res
     * @param @param  user
     * @param @return ????????????
     * @return NisFyyfCheckResp    ????????????
     * @throws
     * @Title: doQueryJzyfsz
     * @Description: TODO(?????????????????????????????????????????????)
     * @author ?????????
     */
    public NisFyyfCheckResp doQueryJzyfsz(Integer brbq, SysUser user) {
        Integer manageUnit = user.getHospitalId();
        NisFyyfCheckResp nisFyyfCheckResp = new NisFyyfCheckResp();
        try {
            NisFyyf nisFyyf = new NisFyyf();
            nisFyyf.setBqdm(ObjectToTypes.parseString(brbq));
            nisFyyf.setJgid(manageUnit);
            nisFyyf.setZxpb(0);
            nisFyyf.setType(2);
            List<NisFyyf> yfsbjzList = nisFyyfDao.findByEntity(nisFyyf);
            if (yfsbjzList.size() < 3) {
                nisFyyfCheckResp.setJzyf(0);
            } else {
                nisFyyfCheckResp.setJzyf(1);
            }
            nisFyyf.setType(3);
            List<NisFyyf> yfsbdyList = nisFyyfDao.findByEntity(nisFyyf);
            if (yfsbdyList.size() < 3) {
                nisFyyfCheckResp.setCydy(0);
            } else {
                nisFyyfCheckResp.setCydy(1);
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw BaseException.create("ERROR_NURSEWORK_ZYBRRY_0001");
        }
        return nisFyyfCheckResp;
    }

    /**
     * ???????????????????????????????????????
     *
     * @param @param  zyh
     * @param @return ????????????
     * @return Integer    ????????????
     * @throws
     * @Title: checkYscz
     * @Description: TODO(?????????????????????????????????????????????)
     * @author ?????????
     */
    public NisYzdy checkYscz(Integer zyh) {
        NisYzdy nisYzdy = new NisYzdy();
        nisYzdy.setZyh(zyh);
        nisYzdy.setDynr("????????????");
        nisYzdy.setSortColumns("DYXH desc");
        List<NisYzdy> nisYzdyList = nisYzdyDao.findByEntity(nisYzdy);
        if (CollectionUtils.isNotEmpty(nisYzdyList)) {
            if (StrUtil.isNotBlank(nisYzdyList.get(0).getCzys()) &&
                    StrUtil.isNotBlank(nisYzdyList.get(0).getCzhs())) {
                return nisYzdyList.get(0);
            }
        }
        return null;
    }

    /**
     * ????????????????????????????????????
     *
     * @param @param dyxh
     * @param @param czhs    ????????????
     * @return void    ????????????
     * @throws
     * @Title: updateCzhs
     * @Description: TODO(?????????????????????????????????????????????)
     * @author ?????????
     */
    public void updateCzhs(Integer dyxh, String czhs) {
        nisYzdyDao.updateCzhsByDyxh(dyxh, czhs);
    }


    /**
     * ??????????????????
     *
     * @param @param  zyh
     * @param @param  lsyz
     * @param @param  user
     * @param @return ????????????
     * @return CisHzyzReviewResp    ????????????
     * @throws
     * @Title: doSaveReview
     * @Description: TODO(?????????????????????????????????????????????)
     * @author ?????????
     */
    @Transactional(rollbackFor = Exception.class)
    public CisHzyzReviewResp doSaveReview(List<CisHzyzReviewReq> reqList, SysUser user) {
    	//??????????????????????????????????????????
        SysXtcs sysXtcs = sysXtcsCacheSer.getByJGIdAndCsmc(user.getHospitalId(), SysXtcsCsmcCts.YZLRFHTYGH);
        String isfhsrSame = sysXtcs == null ? "1" : sysXtcs.getCsz();
        CisHzyzReviewResp cisHzyzReviewResp = new CisHzyzReviewResp();
        cisHzyzReviewResp.setMessageCount(0);
        List<Integer> ptList = reqList.stream().filter(o -> (o.getZtbz() == null || o.getZtbz() == 0))
				.map(CisHzyzReviewReq::getJlxh).collect(Collectors.toList());
        CisHzyzReviewUpdateReq req = new CisHzyzReviewUpdateReq();
        req.setJgid(user.getHospitalId());
        req.setFhsj(DateUtil.date().toTimestamp());
        req.setFhgh(ObjectToTypes.parseString(user.getUserId()));
        long ptCount = 0L;
        long ztCount = 0L;
        if(!ptList.isEmpty()) {

            //????????????????????????????????????????????????
            List<Map<String, Object>> kjywlistMap = cisHzyzDao.queryNotApprovedKjywBatch(user.getHospitalId(), null, ptList);
            
            List<ZlRwfpUpdateReq> yzjlxhList = cisHzyzDao.queryTreatmentProjectByJlxh(user.getHospitalId(), ptList, null, 0);
            
            boolean flag = false;
            List<Integer> list = new ArrayList<Integer>();
            if (CollectionUtils.isNotEmpty(kjywlistMap)) {
                flag = true;
                StringBuffer str = new StringBuffer();
                ReturnEntity<String> returnEntity = new ReturnEntity<String>();
                for (Map<String, Object> map : kjywlistMap) {
                    list.add(ObjectToTypes.parseInt(map.get("YZZH")));
                    str.append(ObjectToTypes.parseInt(map.get("YZMC"))).append(",");
                }
                str.deleteCharAt(str.length() - 1);
                returnEntity.setError("ERROR_NURSEWORK_ZYBRRY_00046", new String[]{
                        str.toString()
                });
                cisHzyzReviewResp.setYwwarningMessage(returnEntity.getMessage());
            }
            req.setJlxhList(ptList);
            if (flag) {
            	ptCount = cisHzyzDao.updateNotExtisSkinTestBatch(req, list);
            } else {
            	ptCount = cisHzyzDao.updateReviewStatusBatch(req);
            }

            if (CollectionUtils.isNotEmpty(yzjlxhList)) {
                cisHzyzDao.updateTreatmentProjectByJlxh(yzjlxhList.stream().map(ZlRwfpUpdateReq::getYzJlxh).collect(Collectors.toList()));
                zlRwfpService.add(yzjlxhList);
            }
        }
        List<Integer> ztList = reqList.stream().filter(o -> (o.getZtbz() != null && o.getZtbz() == 1))
				.map(CisHzyzReviewReq::getJlxh).collect(Collectors.toList());
        if(!ztList.isEmpty()) {
        	req.setJlxhList(ztList);
        	ztCount = cisHzyzZtDao.updateZtReviewStatusBatch(req);
        	req.setJlxhList(null);
        	req.setZtjlxhList(ztList);
        	cisHzyzDao.updateReviewStatusBatch(req);
        	
        }
//        if ("0".equals(isfhsrSame)) {
//            cisHzyz.setCzgh(ObjectToTypes.parseString(userId));
//            long count = cisHzyzDao.queryReviewByOther(cisHzyz);
//            if (count > 0) {
//                cisHzyzReviewResp.setMessageCount(1);
//            }
//            yzjlxhList = cisHzyzDao.queryTreatmentProject(cisHzyz);
//        }
        cisHzyzReviewResp.setUpdateCount(ptCount + ztCount);
        return cisHzyzReviewResp;
        
    }


    /**
     * ????????????
     *
     * @param @param  cisHzyzReviewReq
     * @param @param  user
     * @param @return ????????????
     * @return CisHzyzReviewResp    ????????????
     * @throws
     * @Title: doSaveAreview
     * @author ?????????
     */
//    @Transactional(rollbackFor = Exception.class)
//    public CisHzyzReviewResp doSaveAreview(CisHzyzReviewReq cisHzyzReviewReq, SysUser user) {
//        Integer manageUnit = user.getHospitalId();
//        Integer userId = user.getUserId();
//        CisHzyzReviewResp cisHzyzReviewResp = new CisHzyzReviewResp();
//        cisHzyzReviewResp.setMessageCount(0);
//        try {
//            //??????????????????????????????????????????
//            SysXtcs sysXtcs = sysXtcsCacheSer.getByJGIdAndCsmc(manageUnit, SysXtcsCsmcCts.YZLRFHTYGH);
//            String isfhsrSame = sysXtcs == null ? "1" : sysXtcs.getCsz();
//            CisHzyz cisHzyz = new CisHzyz();
//            cisHzyz.setJgid(manageUnit);
//            cisHzyz.setLsyz(cisHzyzReviewReq.getLsyz());
//
//            CisHzyz zlcisHzyz = new CisHzyz();
//            zlcisHzyz.setJgid(manageUnit);
//            zlcisHzyz.setLsyz(cisHzyzReviewReq.getLsyz());
//            zlcisHzyz.setZyh(cisHzyzReviewReq.getZyh());
//            //0?????????????????????
//            if ("0".equals(isfhsrSame)) {
//                cisHzyz.setCzgh(ObjectToTypes.parseString(userId));
//                zlcisHzyz.setCzgh(ObjectToTypes.parseString(userId));
//                //??????????????????
//                if ("1".equals(ObjectToTypes.parseString(cisHzyzReviewReq.getZtbz()))) {
//                    cisHzyz.setZtyzjlxh(cisHzyzReviewReq.getJlxh());
//                    long count = cisHzyzDao.queryReviewByOther(cisHzyz);
//                    cisHzyz.setFhgh(ObjectToTypes.parseString(userId));
//                    cisHzyz.setFhsj(DateUtil.date().toTimestamp());
//                    //????????????
//                    cisHzyzZtDao.updateZtReviewStatusByOther(cisHzyz);
//                    long rs = cisHzyzDao.updateReviewStatusByOther(cisHzyz);
//                    if (count > 0) {
//                        cisHzyzReviewResp.setMessageCount(1);
//                    }
//                    if(cisHzyzReviewReq.getLsyz() == 0) {
//                    	long tzrs = cisHzyzZtDao.updateZtStopOrderReview(cisHzyz);
//                    	if(rs == 0) {
//                    		rs = tzrs;
//                    	}
//                    }
//                    cisHzyzReviewResp.setUpdateCount(rs);
//                } else {
//                    cisHzyz.setYzzh(cisHzyzReviewReq.getYzzh());
//                    zlcisHzyz.setYzzh(cisHzyzReviewReq.getYzzh());
//                    //??????????????????
//                    List<ZlRwfpUpdateReq> list = cisHzyzDao.queryTreatmentProject(zlcisHzyz);
//                    if (CollectionUtils.isNotEmpty(list)) {
//                        cisHzyzDao.updateTreatmentProjectByJlxh(list.stream().map(ZlRwfpUpdateReq::getYzJlxh).collect(Collectors.toList()));
//                        zlRwfpService.add(list);
//                    }
//
//                    long count = cisHzyzDao.queryReviewByOther(cisHzyz);
//                    cisHzyz.setFhgh(ObjectToTypes.parseString(userId));
//                    cisHzyz.setFhsj(DateUtil.date().toTimestamp());
//                    long rs = cisHzyzDao.updateReviewStatusByOther(cisHzyz);
//                    if (count > 0) {
//                        cisHzyzReviewResp.setMessageCount(1);
//                    }  
//                    if(cisHzyzReviewReq.getLsyz() == 0) {
//                    	long tzrs = cisHzyzDao.updateStopOrderReview(cisHzyz);
//                    	if(rs == 0) {
//                    		rs = tzrs;
//                    	}
//                    }
//                    cisHzyzReviewResp.setUpdateCount(rs);
//                }
//            } else {
//                cisHzyz.setFhgh(ObjectToTypes.parseString(userId));
//                cisHzyz.setFhsj(DateUtil.date().toTimestamp());
//                //???????????????????????????????????????
//                if ("1".equals(ObjectToTypes.parseString(cisHzyzReviewReq.getZtbz()))) {
//                    cisHzyz.setJlxh(cisHzyzReviewReq.getJlxh());
//                    //????????????
//                    cisHzyzZtDao.updateZtReviewStatusByOther(cisHzyz);
//                    cisHzyz.setJlxh(null);
//                    cisHzyz.setZtyzjlxh(cisHzyzReviewReq.getJlxh());
//                    long rs = cisHzyzDao.updateReviewStatusByOther(cisHzyz);   
//                    if(cisHzyzReviewReq.getLsyz() == 0) {
//                    	long tzrs = cisHzyzZtDao.updateZtStopOrderReview(cisHzyz);
//                    	if(rs == 0) {
//                    		rs = tzrs;
//                    	}
//                    }
//                    cisHzyzReviewResp.setUpdateCount(rs);
//                } else {
//                    cisHzyz.setYzzh(cisHzyzReviewReq.getYzzh());
//                    zlcisHzyz.setYzzh(cisHzyzReviewReq.getYzzh());
//                    //??????????????????
//                    List<ZlRwfpUpdateReq> list = cisHzyzDao.queryTreatmentProject(zlcisHzyz);
//                    if (CollectionUtils.isNotEmpty(list)) {
//                        cisHzyzDao.updateTreatmentProjectByJlxh(list.stream().map(ZlRwfpUpdateReq::getYzJlxh).collect(Collectors.toList()));
//                        zlRwfpService.add(list);
//                    }
//
//                    long rs = cisHzyzDao.updateReviewStatusByOther(cisHzyz);
//                    if(cisHzyzReviewReq.getLsyz() == 0) {
//                    	long tzrs = cisHzyzDao.updateStopOrderReview(cisHzyz);
//                    	if(rs == 0) {
//                    		rs = tzrs;
//                    	}
//                    }
//                    cisHzyzReviewResp.setUpdateCount(rs);
//                }
//                
//            }
//            return cisHzyzReviewResp;
//        } catch (Exception e) {
//            logger.error(e.getMessage(), e);
//            throw BaseException.create("ERROR_NURSEWORK_ZYBRRY_0002");
//        }
//    }

    /**
     * ??????????????????
     * @Title: doSaveUnReview
     * @Description: TODO(?????????????????????????????????????????????)
     * @param @param list
     * @param @param hospitalId
     * @param @return    ????????????
     * @return Long    ????????????
     * @author ?????????
     * @throws
     */
    @Transactional(rollbackFor = Exception.class)
    public Long doSaveUnReview(List<CisHzyzReviewReq> list, Integer hospitalId) {
    	//?????????
    	List<Integer> ptList = list.stream().filter(o -> (o.getZtbz() == null || o.getZtbz() == 0))
				.map(CisHzyzReviewReq::getJlxh).collect(Collectors.toList());
        long ptCount = 0L;
        long ztCount = 0L;
        if(CollectionUtils.isNotEmpty(ptList)) {
        	List<ZlRwfpUpdateReq> zlList = cisHzyzDao.queryTreatmentProjectByJlxh(hospitalId, ptList, 6, 1);
            if (CollectionUtils.isNotEmpty(zlList)) {
                zlRwfpService.deleteByYzJlxh(zlList.stream().map(ZlRwfpUpdateReq::getYzJlxh).collect(Collectors.toList()));
            }
            ptCount = cisHzyzDao.cancelYzReviewStatus(hospitalId, ptList);
        }
        
        List<Integer> ztList = list.stream().filter(o -> (o.getZtbz() != null && o.getZtbz() == 1))
				.map(CisHzyzReviewReq::getJlxh).collect(Collectors.toList());
        if(CollectionUtils.isNotEmpty(ztList)) {
        	cisHzyzZtDao.cancelZtReviewStatus(hospitalId, ztList);
        	ztCount = cisHzyzDao.cancelYzZtReviewStatus(hospitalId, ztList);
        }
        return ptCount + ztCount;
    }

    /**
     * ????????????????????????
     *
     * @param @param  cisHzyzExcuteReq
     * @param @param  user
     * @param @return ????????????
     * @return Long    ????????????
     * @throws
     * @Title: doQueryFHSFXM
     * @Description: TODO(?????????????????????????????????????????????)
     * @author ?????????
     */
    public Long doQueryFhsfxm(CisHzyzExcuteReq cisHzyzExcuteReq, SysUser user) {
        Integer jgid = user.getHospitalId();
        //????????????
        Integer hsql = cisHzyzExcuteReq.getBrbq();
        long count = 0;
        if (hsql == null) {
            throw BaseException.create("ERROR_NURSEWORK_ZYBRRY_0004");
        }
        Integer zyh = 0;
        if (cisHzyzExcuteReq.getZyh() != null) {
            zyh = cisHzyzExcuteReq.getZyh();
        }
        try {
            SysXtcs sysXtcs = sysXtcsCacheSer.getByJGIdAndCsmc(jgid, SysXtcsCsmcCts.FHYZHJF);
            String xyf = sysXtcs == null ? "0" : sysXtcs.getCsz();
            if ("1".equals(xyf)) {
                cisHzyzExcuteReq.setFhbz(0);
                String date = BUHISUtil.getDate();
                Date qrsj = BUHISUtil.toDate(date);// ????????????
                cisHzyzExcuteReq.setJgid(jgid);
                cisHzyzExcuteReq.setQrsj(DateUtil.date(qrsj).toTimestamp());
                count = cisHzyzDao.queryBqyzExcutedAndReviewCount(cisHzyzExcuteReq);
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw BaseException.create("ERROR_NURSEWORK_ZYBRRY_0005");
        }
        return count;
    }


    /**
     * ??????????????????????????????????????????
     *
     * @param @param  cisHzyzorztExcuteReq
     * @param @param  user
     * @param @return ????????????
     * @return List<CisHzyzOrztResp>    ????????????
     * @throws
     * @Title: doDetailChargeQuery
     * @Description: TODO(?????????????????????????????????????????????)
     * @author ?????????
     */
    public List<CisHzyzOrztResp> doDetailChargeQuery(CisHzyzorztExcuteReq cisHzyzorztExcuteReq, SysUser user) {
        Integer jgid = user.getHospitalId();
        //????????????
        Integer hsql = cisHzyzorztExcuteReq.getBrbq();
        if (hsql == null) {
            throw BaseException.create("ERROR_NURSEWORK_ZYBRRY_0004");
        }
        long zyh = 0L;
        if (cisHzyzorztExcuteReq.getZyh() != null) {
            zyh = cisHzyzorztExcuteReq.getZyh();
        }

        List<Integer> ztyzypxhList = new ArrayList<Integer>();// ??????????????????
        List<Integer> xmxhs = new ArrayList<Integer>();
        if (CollectionUtils.isNotEmpty(cisHzyzorztExcuteReq.getXmxhs())) {
            List<Integer> l = cisHzyzorztExcuteReq.getXmxhs();
            List<Object> zts = cisHzyzorztExcuteReq.getZtbzs();
            for (int i = 0; i < l.size(); i++) {
                // ??????
                if (zts.get(i) == null) {
                    zts.set(i, 0);
                }
                if ("1".equals(ObjectToTypes.parseString(zts.get(i)))) {
                    ztyzypxhList.add(ObjectToTypes.parseInt(l.get(i)));
                } else {
                    // ?????????
                    xmxhs.add(ObjectToTypes.parseInt(l.get(i)));
                }
            }
//			parameters.put("xmxhs",xmxhs);
        }

        // ????????????????????????ypxh??????
        if (StrUtil.isNotBlank(cisHzyzorztExcuteReq.getPrintWay())
                && "XM".equals(cisHzyzorztExcuteReq.getPrintWay())) {
            cisHzyzorztExcuteReq.setOrderBy("t.YPXH");
        } else {
            cisHzyzorztExcuteReq.setOrderBy("t.ZYH");
        }
        /**
         * ????????????ADD_FHBZ???????????????????????????1??????????????????????????????????????????
         */
        if (cisHzyzorztExcuteReq.getAddfhbz() == null ||
                "1".equals(ObjectToTypes.parseString(cisHzyzorztExcuteReq.getAddfhbz()))) {
            SysXtcs sysXtcs = sysXtcsCacheSer.getByJGIdAndCsmc(jgid, SysXtcsCsmcCts.FHYZHJF);
            String xyf = sysXtcs == null ? "0" : sysXtcs.getCsz();
            if ("1".equals(xyf)) {
                cisHzyzorztExcuteReq.setFhbz(1);
            }
        }

        String date = BUHISUtil.getDate();
        //????????????
        Date qrsj = BUHISUtil.toDate(date);

        cisHzyzorztExcuteReq.setJgid(jgid);
        cisHzyzorztExcuteReq.setQrsj(new Timestamp(qrsj.getTime()));
        if(cisHzyzorztExcuteReq.getLsyz() != null && cisHzyzorztExcuteReq.getLsyz() == 2) {
        	cisHzyzorztExcuteReq.setLsyz(null);
        }
        try {
            // ??????list???????????????
            List<Map<String, Object>> inofList = new ArrayList<Map<String, Object>>();
            // ??????????????????????????????????????????????????????????????????
            boolean xmflag = CollectionUtils.isNotEmpty(cisHzyzorztExcuteReq.getXmxhs()) && xmxhs.size() == 0 || ztyzypxhList.size() > 0;
            if (xmflag) {
                cisHzyzorztExcuteReq.setXmxhs(ztyzypxhList);
                List<CisHzyzOrztResp> cisHzyzOrztRespList = cisHzyzZtDao.queryBqyzZtExcutedAndReview(cisHzyzorztExcuteReq);
                List<Map<String, Object>> inofList2 = BUHISUtil.ListObjToMap(cisHzyzOrztRespList);
                inofList.addAll(inofList2);
                if (xmxhs.size() == 0) {
                    //??????????????????????????????
                    int fjbz = 0;
                    if (inofList.size() > 0) {
                        List<Map<String, Object>> dicsypcList = cisHzyzSer.frequencyList();
                        calculateOrderExecution(dicsypcList, inofList, user);
                        for (int i = 0; i < inofList.size(); i++) {
                            double fycs = Double.parseDouble(inofList.get(i).get("FYCS") + "");
                            double ycsl = Double.parseDouble(inofList.get(i).get("YCSL") + "");
                            double ypdj = Double.parseDouble(inofList.get(i).get("YPDJ") + "");
                            int lsbz = Integer.parseInt(inofList.get(i).get("LSBZ") + "");
                            String qrsj1 = inofList.get(i).get("QRSJ") + "";
                            if (fycs == 0) {
                                inofList.remove(i);
                                i--;
                            } else {
                                inofList.get(i).put("FYCS", fycs);
                                inofList.get(i).put("LSBZ", lsbz);
                                inofList.get(i).put("QRSJ", qrsj1);
                                inofList.get(i).put("JE", (fycs * ycsl * ypdj));
                                inofList.get(0).put("FJBZ", fjbz);
                            }
                        }
                    }
                    return BUHISUtil.ListToResultSet(inofList, new CisHzyzOrztResp());
                }
            }

            List<CisHzyzOrztResp> cisHzyzOrztRespList = cisHzyzDao.queryBqyzExcutedAndReview(cisHzyzorztExcuteReq);
            List<Map<String, Object>> inofList0 = BUHISUtil.ListObjToMap(cisHzyzOrztRespList);
            //???CIS_HZYZ_zt????????????????????????????????????
            Map<String, Object> ztyzjlxhMap = new HashMap<String, Object>(16);
            for (Map<String, Object> record : inofList0) {
                if (record.get("ZTBZ") != null &&
                        "1".equals(ObjectToTypes.parseString(record.get("ZTBZ")))) {
                    if (ztyzjlxhMap.containsKey(ObjectToTypes.parseString(record.get("ZTYZJLXH")))) {
                        continue;
                    }
                    // ????????????????????????????????????????????????
                    long zyhm = ObjectToTypes.parseLong(record.get("ZYHM"));
                    String brxm = ObjectToTypes.parseString(record.get("BRXM"));
                    CisHzyzZt cisHzyzZt = cisHzyzZtDao.getById(ObjectToTypes.parseInt(record.get("ZTYZJLXH")));
                    record = BUHISUtil.caseInsensitiveMap(cisHzyzZt);
                    record.put("ZYHM", zyhm);
                    record.put("BRXM", brxm);
                    ztyzjlxhMap.put(ObjectToTypes.parseString(record.get("JLXH")), record);
                }
                inofList.add(record);
            }
            //??????????????????????????????
            int fjbz = 0;
            if (inofList.size() > 0) {
                List<Map<String, Object>> dicsypcList = cisHzyzSer.frequencyList();
                calculateOrderExecution(dicsypcList, inofList, user);
                for (int i = 0; i < inofList.size(); i++) {
                    double fycs = Double.parseDouble(inofList.get(i).get("FYCS") + "");
                    double ycsl = Double.parseDouble(inofList.get(i).get("YCSL") + "");
                    double ypdj = Double.parseDouble(inofList.get(i).get("YPDJ") + "");
                    int lsbz = Integer.parseInt(inofList.get(i).get("LSBZ") + "");
                    String qrsj1 = inofList.get(i).get("QRSJ") + "";
                    if (fycs == 0) {
                        inofList.remove(i);
                        i--;
                    } else {
                        inofList.get(i).put("FYCS", fycs);
                        inofList.get(i).put("LSBZ", lsbz);
                        inofList.get(i).put("QRSJ", qrsj1);
                        inofList.get(i).put("JE", (fycs * ycsl * ypdj));
                        inofList.get(0).put("FJBZ", fjbz);
                    }
                }
            }
            return BUHISUtil.ListToResultSet(inofList, new CisHzyzOrztResp());
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw BaseException.create("ERROR_NURSEWORK_ZYBRRY_0005");
        }
    }


    /**
     * ???????????????????????????---????????????
     *
     * @param @param  cisHzyzSheetReq
     * @param @param  res
     * @param @param  user
     * @param @return ????????????
     * @return List<CisHzyzOrztResp>    ????????????
     * @throws
     * @Title: doAdditionProjectsFeeQuery
     * @Description: TODO(?????????????????????????????????????????????)
     * @author ?????????
     */
    public List<CisHzyzOrztResp> doAdditionProjectsFeeQuery(CisHzyzSheetReq cisHzyzSheetReq, SysUser user) {
        Integer jgid = user.getHospitalId();
        //????????????
        Integer hsql = cisHzyzSheetReq.getBrbq();
        if (hsql == null) {
            throw BaseException.create("ERROR_NURSEWORK_ZYBRRY_0004");
        }
        CisHzyzorztExcuteReq cisHzyzorztExcuteReq = new CisHzyzorztExcuteReq();

        List<Integer> yzxhList = new ArrayList<Integer>();
        if (CollectionUtils.isNotEmpty(cisHzyzSheetReq.getYzxhList())) {
            yzxhList = cisHzyzSheetReq.getYzxhList();
        }

        List<Map<String, Object>> allYzzhList = new ArrayList<Map<String, Object>>();
        // ???????????????????????????????????????????????????????????????
        cisHzyzorztExcuteReq.setAddfhbz(0);
        cisHzyzorztExcuteReq.setBrbq(hsql);
        cisHzyzorztExcuteReq.setZyh(cisHzyzSheetReq.getZyh());
        List<CisHzyzOrztResp> cisHzyzOrztRespList = doDetailChargeQuery(cisHzyzorztExcuteReq, user);
        if (CollectionUtils.isNotEmpty(cisHzyzOrztRespList)) {
            allYzzhList = BUHISUtil.ListObjToMap(cisHzyzOrztRespList);
        }
        Map<String, Object> parameters = new HashMap<String, Object>(16);
        try {
            if (cisHzyzSheetReq.getZyh() != null) {
                parameters.put("ZYH", cisHzyzSheetReq.getZyh());
            }
            parameters.put("JGID", jgid);
            parameters.put("SRKS", hsql);
            String date = BUHISUtil.getDate();
            parameters.put("ADTODAY", BUHISUtil.toDate(date));
            // ???????????????????????????
            List<Map<String, Object>> inofList = cisHzyzDao.queryBqyzBillingSheet(parameters);
            // ???????????????????????? ??????????????????
            int fjbz = 1; // ????????????
            if (inofList.size() > 0) {
                //??????????????????
                inofList.get(0).put("FJBZ", fjbz);
                List<Map<String, Object>> dicsypcList = cisHzyzSer.frequencyList();
                calculateOrderExecution(dicsypcList, inofList, user);
                // long ZYH = 0;
                for (int i = 0; i < inofList.size(); i++) {
                    double fycs = Double.parseDouble(inofList.get(i).get("FYCS") + "");
                    double ycsl = Double.parseDouble(inofList.get(i).get("YCSL") + "");
                    double ypdj = Double.parseDouble(inofList.get(i).get("YPDJ") + "");
                    inofList.get(i).put("FYCS", fycs);
                    inofList.get(i).put("JE", (fycs * ycsl * ypdj));
                    if (fycs <= 0) {
                        inofList.remove(i);
                        i--;
                    }
                }
            }
            List<Map<String, Object>> yjList = new ArrayList<Map<String, Object>>();// ?????????????????????????????????????????????????????????????????????????????????????????????????????????
            List<Map<String, Object>> xmList = new ArrayList<Map<String, Object>>();// ??????????????????
            String yzzh = "";
            boolean flag = true;
            /**
             * ???????????????????????????????????????????????????????????????????????????????????????
             * ??????????????????????????????????????????????????????????????????????????????????????????YZZH??????????????????????????? ?????????????????????
             */
            for (Map<String, Object> map : inofList) {
                flag = true;
                yzzh = String.valueOf(map.get("YZZH"));
                for (Map<String, Object> map2 : allYzzhList) {
                    // ?????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????
                    if (yzzh.equals(String.valueOf(map2.get("YZZH")))) {
                        // ????????????????????????????????????????????????????????????????????????????????????
                        for (Integer yzxh : yzxhList) {
                            if (yzzh.equals(ObjectToTypes.parseString(yzxh))) {
                                xmList.add(map);
                            }
                        }
                        flag = false;
                        break;
                    }
                }
                if (flag) {
                    yjList.add(map);
                }
            }
            inofList.clear();
            inofList.addAll(yjList);
            inofList.addAll(xmList);
            return BUHISUtil.ListToResultSet(inofList, new CisHzyzOrztResp());
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw BaseException.create("ERROR_NURSEWORK_ZYBRRY_0006");
        }
    }

    /**
     * ????????????????????????
     *
     * @param @param  cisHzyzNurseExReq
     * @param @param  user
     * @param @return ????????????
     * @return BqTjMsgResp    ????????????
     * @throws
     * @Title: doSaveConfirm
     * @Description: TODO(?????????????????????????????????????????????)
     * @author ?????????
     */
    @Transactional(rollbackFor = Exception.class)
    @Locked(value = {"im_nurse_doSaveConfirm_#[cisHzyzNurseExReq.*cisHzyzBody.jlxh]"})
    public NisTjMsgResp doSaveConfirm(CisHzyzNurseExReq cisHzyzNurseExReq, SysUser user) {
        List<CisHzyzBody> cisHzyzBodyList = cisHzyzNurseExReq.getCisHzyzBody();
        List<CisHzyzBodyZx> cisHzyzBodyZxList = cisHzyzNurseExReq.getCisHzyzBodyZx();            
        List<Map<String, Object>> body = BUHISUtil.ListObjToMap(cisHzyzBodyList);
        List<Map<String, Object>> bodyForZx = BUHISUtil.ListObjToMap(cisHzyzBodyZxList);
        List<Integer> intList = new ArrayList<Integer>();
        NisTjMsgResp nisTjMsgResp = new NisTjMsgResp();
        try {
            CisHzyzorztExcuteReq cisHzyzorztExcuteReq = new CisHzyzorztExcuteReq();
            Integer jgid = user.getHospitalId();
            //????????????
            Integer hsql = cisHzyzNurseExReq.getBrbq();
            if (hsql == null) {
                throw BaseException.create("ERROR_NURSEWORK_ZYBRRY_0004");
            }
            HashSet<Integer> zyhs = new HashSet<Integer>();
            for (int i = 0; i < body.size(); i++) {
                FeeYlsfxmModel feeYlsfxm = new FeeYlsfxmModel();
                feeYlsfxm.setFyxh(ObjectToTypes.parseInt(body.get(i).get("FYXH")));
                feeYlsfxm.setZfpb(1);
                long count = feeYlsfxmOutSer.findByEntityCount(feeYlsfxm);
                if (count > 0) {
                    throw BaseException.create("ERROR_NURSEWORK_ZYBRRY_0007", new String[]{
                            ObjectToTypes.parseString(body.get(i).get("FYMC"))
                    });
                }
                zyhs.add(ObjectToTypes.parseInt(body.get(i).get("ZYH")));
            }

            cisHzyzorztExcuteReq.setZyhs(new ArrayList<>(zyhs));

            SysXtcs sysXtcs = sysXtcsCacheSer.getByJGIdAndCsmc(jgid, SysXtcsCsmcCts.FHYZHJF);
            String xyf = sysXtcs == null ? "0" : sysXtcs.getCsz();
            if ("1".equals(xyf)) {
                cisHzyzorztExcuteReq.setFhbz(1);
            }
            //????????????
            String date = BUHISUtil.getDate();
            Date qrsjNew = BUHISUtil.toDate(date);

            cisHzyzorztExcuteReq.setBrbq(hsql);
            cisHzyzorztExcuteReq.setJgid(jgid);
            cisHzyzorztExcuteReq.setQrsj(new Timestamp(qrsjNew.getTime()));
            if(cisHzyzNurseExReq.getLsyz() != null && cisHzyzNurseExReq.getLsyz() != 2) {
            	cisHzyzorztExcuteReq.setLsyz(cisHzyzNurseExReq.getLsyz());
            }

            List<CisHzyzOrztResp> cisHzyzOrztRespList = cisHzyzDao.queryBqyzExcutedAndReview(cisHzyzorztExcuteReq);
            List<Map<String, Object>> inofList = BUHISUtil.ListObjToMap(cisHzyzOrztRespList);
            //??????????????????????????????
            int fjbz = 0;
            if (inofList.size() > 0) {
                List<Map<String, Object>> dicsypcList = cisHzyzSer.frequencyList();
                calculateOrderExecution(dicsypcList, inofList, user);
                for (int i = 0; i < inofList.size(); i++) {
                    double fycs = Double.parseDouble(inofList.get(i).get("FYCS") + "");
                    double ycsl = Double.parseDouble(inofList.get(i).get("YCSL") + "");
                    double ypdj = Double.parseDouble(inofList.get(i).get("YPDJ") + "");

                    int lsbz = Integer.parseInt(inofList.get(i).get("LSBZ") + "");
                    String qrsj1 = inofList.get(i).get("QRSJ") + "";
                    if (fycs == 0) {
                        inofList.remove(i);
                        i--;
                    } else {
                        inofList.get(i).put("FYCS", fycs);
                        inofList.get(i).put("LSBZ", lsbz);
                        inofList.get(i).put("QRSJ", qrsj1);
                        inofList.get(i).put("JE", (fycs * ycsl * ypdj));
                        inofList.get(0).put("FJBZ", fjbz);
                    }
                }
            }
            /**??????????????????????????????????????????list??????????????????????????????????????? **/
            // ??????????????????
            List<Map<String, Object>> tmpList = new ArrayList<Map<String, Object>>();
            tmpList.addAll(body);
            tmpList.addAll(bodyForZx);

            boolean flag = cisHzyzSer.ArrearsPatientsQuery(tmpList, user, nisTjMsgResp);
            //????????????body??????,infoList????????????????????????
            if (flag) {// ??????????????????????????????????????????????????????(?????????????????????)
                boolean f = true;
                Map<String, Object> tmpMap = null;
                for (int i = 0; i < body.size(); i++) {
                    tmpMap = body.get(i);
                    f = true;
                    for (Map<String, Object> map : tmpList) {
                        if (tmpMap.get("ZYH").equals(map.get("ZYH"))) {
                            f = false;
                            continue;
                        }
                    }
                    if (f) {
                        body.remove(i);
                        i--;
                    }
                }
                for (int i = 0; i < bodyForZx.size(); i++) {
                    tmpMap = bodyForZx.get(i);
                    f = true;
                    for (Map<String, Object> map : tmpList) {
                        if (tmpMap.get("ZYH").equals(map.get("ZYH"))) {
                            f = false;
                            continue;
                        }
                    }
                    if (f) {
                        bodyForZx.remove(i);
                        i--;
                    }
                }
                // ??????????????????????????????????????????
                boolean bodyFlag = (body == null || body.size() == 0)
                        && (bodyForZx == null || bodyForZx.size() == 0);
                if (bodyFlag) {
                    return nisTjMsgResp;
                }
            }
            List<Map<String, Object>> listForputFymx = new ArrayList<Map<String, Object>>();
            List<Map<String, Object>> listForputFymxforZx = new ArrayList<Map<String, Object>>();

            insertFymx(body, listForputFymx, user, cisHzyzNurseExReq.getBrbq());
            // ?????????????????????
            insertFymx(bodyForZx, listForputFymxforZx, user, cisHzyzNurseExReq.getBrbq());

            List<Map<String, Object>> xhmxList = new ArrayList<Map<String, Object>>();// ??????????????????
            //??????????????????
            sysXtcs = sysXtcsCacheSer.getByJGIdAndCsmc(jgid, SysXtcsCsmcCts.WPJFBZ);
            int wpjfbz = sysXtcs == null ? 0 : ObjectToTypes.parseInt(sysXtcs.getCsz());
            //??????????????????????????????
            sysXtcs = sysXtcsCacheSer.getByJGIdAndCsmc(jgid, SysXtcsCsmcCts.WZSFXMJGZY);
            int wzsfxmjgzy = sysXtcs == null ? 0 : ObjectToTypes.parseInt(sysXtcs.getCsz());
            Integer brid = 0;
            Integer ksdm = 0;
            String ksmc = "";
            for (int i = 0; i < body.size(); i++) {
                Map<String, Object> parametersForputFymx = listForputFymx.get(i);
                Map<String, Object> parametersUpdate = new HashMap<String, Object>(16);
                // ????????????????????????
                if (wpjfbz == 1 && wzsfxmjgzy == 0) {
                    //???????????????
                    if (Integer.parseInt(parametersForputFymx.get("YPLX") + "") == 0) {
//						FeeXmzxks feeXmzxks = new FeeXmzxks();
//						feeXmzxks.setFyxh(ObjectToTypes.parseInt(parametersForputFymx.get("FYXH")));
//						feeXmzxks.setJgid(jgid);
//						List<FeeXmzxks> fywzList = feeXmzxksDao.findByEntity(feeXmzxks);
//						if (fywzList != null && fywzList.size() > 0) {
//							ImHzry imHzry = imHzryDao.getById(ObjectToTypes.parseInt(parametersForputFymx.get("ZYH")));
//							for (FeeXmzxks fywz : fywzList) {
//								Map<String, Object> m = new HashMap<String, Object>(16);
//								brid = imHzry.getBrid();
//								ksdm = hsql;
//								ksmc = cisHzyzNurseExReq.getQxmc();
//								m.put("JGID", jgid);
//								m.put("KSDM", ksdm);
//								m.put("KSMC", cisHzyzNurseExReq.getQxmc());
//								m.put("BRID", brid);
//								m.put("BRHM", imHzry.getZyhm());
//								m.put("BRLY", "ZY");
//								m.put("BRXM", imHzry.getBrxm());
//								m.put("WZXH", fywz.getWzxh());
//								m.put("ZJJE", parametersForputFymx.get("ZJJE"));
//								m.put("WZSL", BUHISUtil.simpleMultiply(2, fywz.getWzsl(), parametersForputFymx.get("FYSL")));
//								xhmxList.add(m);
//							}
//						}
                    }
                }
                Map<String, Object> jlMap = new HashMap<String, Object>();
                for (int j = 0; j < inofList.size(); j++) {
                    long jlxh = Long.parseLong(inofList.get(j).get("JLXH") + "");
                    long jlxhl = Long.parseLong(body.get(i).get("JLXH") + "");
                    String qrsj = inofList.get(j).get("QRSJ") + "";
                    int lsbz = Integer.parseInt(inofList.get(j).get("LSBZ") + "");
                    parametersUpdate.put("JLXH", jlxh);
                    parametersUpdate.put("QRSJ", DateUtil.parse(qrsj).toTimestamp());
                    parametersUpdate.put("LSBZ", lsbz);
                    if (jlxh == jlxhl) {
                        cisHzyzDao.updateTimeAndLsByJlxh(parametersUpdate);
                        //???????????????????????????????????????????????????IM_FEE_FYMX_YJ??? ???????????????????????? ??????IM_FEE_FYMX
//                        FeeYlsfxmModel feeYlsfxm = new FeeYlsfxmModel();
//                        feeYlsfxm.setFyxh(ObjectToTypes.parseInt(parametersForputFymx.get("FYXH")));
//                        feeYlsfxm.setYjsy(1);
                        FeeYlsfxmOutResp resp = feeYlsfxmOutSer.getById(ObjectToTypes.parseInt(parametersForputFymx.get("FYXH")));
                        if (resp != null && "1".equals(ObjectToTypes.parseString(resp.getYjsy()))) {
                            ImFeeFymxYj imFeeFymxYj = BeanUtil.fillBeanWithMapIgnoreCase(parametersForputFymx, new ImFeeFymxYj(), true);
                            imFeeFymxYj.setJlxh(redisFactory.getTableKey(TableName.DB_NAME, TableName.IM_FEE_FYMX_YJ));
                            imFeeFymxYjDao.insert(imFeeFymxYj);
                        } else {
                            ImFeeFymx imFeeFymx = BeanUtil.fillBeanWithMapIgnoreCase(parametersForputFymx, new ImFeeFymx(), true);
                            Integer fyjlxh = redisFactory.getTableKey(TableName.DB_NAME, TableName.IM_FEE_FYMX);
                            imFeeFymx.setJlxh(fyjlxh);
                            imFeeFymxDao.insert(imFeeFymx);
                            if(resp != null && ("4".equals(ObjectToTypes.parseString(resp.getFygb()))
                            		|| "17".equals(ObjectToTypes.parseString(resp.getFygb())))) {
                            	intList.add(fyjlxh);
                            }
                        }

                        if (wpjfbz == 1 && wzsfxmjgzy == 1) {
//							updateXhmxZt(jlxh, "2", jgid);
                        }
                        // ????????????????????????????????????????????????????????????????????????CIS_HZYZ_ZT????????????
                        if ("1".equals(ObjectToTypes.parseString(inofList.get(j).get("ZTBZ")))) {
                            CisHzyz cisHzyz = new CisHzyz();
                            cisHzyz.setZtyzjlxh(ObjectToTypes.parseInt(inofList.get(j).get("ZTYZJLXH")));
                            long count = cisHzyzDao.findByEntityCount(cisHzyz);
                            long count2 = cisHzyzDao.queryCountByZtjlxh(ObjectToTypes.parseInt(inofList.get(j).get("ZTYZJLXH")));
                            if (count == count2) {
                                Map<String, Object> par = new HashMap<String, Object>(16);
                                par.putAll(parametersUpdate);
                                par.put("JLXH", ObjectToTypes.parseInt(inofList.get(j).get("ZTYZJLXH")));
                                cisHzyzZtDao.updateZtTimeAndLsByJlxh(par);
                            }
                        }
                    }
                }
            }
            for (int j = 0; j < bodyForZx.size(); j++) {
                Map<String, Object> parametersUpdate = new HashMap<String, Object>(16);
                long jlxh = Long.parseLong(bodyForZx.get(j).get("JLXH") + "");
                String qrsj = bodyForZx.get(j).get("QRSJ") + "";
                int lsbz = Integer.parseInt(bodyForZx.get(j).get("LSBZ") + "");
                parametersUpdate.put("JLXH", jlxh);
                parametersUpdate.put("QRSJ", DateUtil.parse(qrsj).toTimestamp());
                parametersUpdate.put("LSBZ", lsbz);
                cisHzyzDao.updateTimeAndLsByJlxh(parametersUpdate);
                //???????????????????????????????????????????????????IM_FEE_FYMX_YJ??? ???????????????????????? ??????IM_FEE_FYMX
                FeeYlsfxmModel feeYlsfxm = new FeeYlsfxmModel();
                feeYlsfxm.setFyxh(ObjectToTypes.parseInt(bodyForZx.get(j).get("FYXH")));
                feeYlsfxm.setYjsy(1);
                List<FeeYlsfxmModel> checkList = feeYlsfxmOutSer.findByEntity(feeYlsfxm);
                if (checkList.size() > 0) {
                    ImFeeFymxYj imFeeFymxYj = BeanUtil.fillBeanWithMapIgnoreCase(bodyForZx.get(j), new ImFeeFymxYj(), true);
                    imFeeFymxYj.setJlxh(redisFactory.getTableKey(TableName.DB_NAME, TableName.IM_FEE_FYMX_YJ));
                    imFeeFymxYjDao.insert(imFeeFymxYj);
                } else {
                    ImFeeFymx imFeeFymx = BeanUtil.fillBeanWithMapIgnoreCase(bodyForZx.get(j), new ImFeeFymx(), true);
                    imFeeFymx.setJlxh(redisFactory.getTableKey(TableName.DB_NAME, TableName.IM_FEE_FYMX));
                    imFeeFymxDao.insert(imFeeFymx);

                }
            }

            if (wpjfbz == 1 && wzsfxmjgzy == 0) {
//				String d = saveXhmx(xhmxList);
                if (xhmxList.size() > 0) {
//					Map<String, Object> retMap = new HashMap<String, Object>(16);
//					retMap.put("BRID", brid);
//					retMap.put("XHRQ", d);
//					retMap.put("KSMC", ksmc);
//					retMap.put("KSDM", ksdm);
//					res.put("body", retMap);
                }
            }
            nisTjMsgResp.setIntList(intList);
//            if(!intList.isEmpty()) {
//            	try {
//					mdiHrpService.hiscubebzejkfpush(intList, 1);
//				} catch (Exception e) {
//					logger.error(e.getMessage(), e);
//				}
//            }
        } catch (ParseException e) {
            logger.error(e.getMessage(), e);
            throw BaseException.create("ERROR_NURSEWORK_ZYBRRY_0008");
        }
        return nisTjMsgResp;
    }

    /**
     * ??????????????????????????????(????????????,????????????)
     * @Title: saveXhmx
     * @Description: TODO(?????????????????????????????????????????????)
     * @param @param list_fymx
     * @param @return    ????????????
     * @return String    ????????????
     * @author ?????????
     * @throws
     */
//	public String saveXhmx(List<Map<String, Object>> fymxList) {
//		Date d = new Date();
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//		try {
//			for (Map<String, Object> fymxMap : fymxList) {
//				Map<String, Object> xhmxMap = new HashMap<String, Object>(16);
//				xhmxMap.put("JGID", fymxMap.get("JGID"));
//				xhmxMap.put("KSDM", ObjectToTypes.parseInt(fymxMap.get("KSDM")));
//				xhmxMap.put("KSMC", fymxMap.get("KSMC"));
//				xhmxMap.put("BRID", ObjectToTypes.parseInt(fymxMap.get("BRID")));
//				xhmxMap.put("BRHM", fymxMap.get("BRHM"));
//				xhmxMap.put("BRLY", "2");
//				xhmxMap.put("BRXM", fymxMap.get("BRXM"));
//				xhmxMap.put("XHRQ", d);
//				xhmxMap.put("WZXH", ObjectToTypes.parseInt(fymxMap.get("WZXH")));
//				xhmxMap.put("WZSL", ObjectToTypes.parseDouble(fymxMap.get("WZSL")));
//				xhmxMap.put("ZTBZ", 0);
//				xhmxMap.put("XMJE", ObjectToTypes.parseDouble(fymxMap.get("ZJJE")));
//
//				WlKfdz wlKfdz = new WlKfdz();
//				wlKfdz.setKsdm(ObjectToTypes.parseInt(fymxMap.get("KSDM")));
//				List<WlKfdz> wlKfdzList = wlKfdzDao.findByEntity(wlKfdz);
//
//				if (wlKfdzList != null && wlKfdzList.size() > 0) {
//					xhmxMap.put("KFXH", wlKfdzList.get(wlKfdzList.size() - 1).getKfxh());
//				}
//				WlKfdz wlKfdzNew = BeanUtil.fillBeanWithMapIgnoreCase(xhmxMap, new WlKfdz(), true);
//				wlKfdzDao.insert(wlKfdzNew);
//			}
//			return sdf.format(d);
//		} catch (Exception e) {
//			logger.error(e.getMessage(), e);
//			throw BaseException.create("ERROR_NURSEWORK_ZYBRRY_0009");
//		}
//	}

    /**
     * ??????????????????
     *
     * @param @param  jlxh
     * @param @param  user
     * @param @return ????????????
     * @return List<CisHzyzResp>    ????????????
     * @throws
     * @Title: doQueryZtmx
     * @Description: TODO(?????????????????????????????????????????????)
     * @author ?????????
     */
    public List<CisHzyzResp> doQueryZtmx(Integer jlxh, SysUser user) {
        List<CisHzyzResp> cisHzyzRespList = cisHzyzDao.queryBqyzExecuted(jlxh);
        List<Map<String, Object>> ztDetail = BUHISUtil.ListObjToMap(cisHzyzRespList);
        //??????FYCS
        List<Map<String, Object>> dicsypcList = cisHzyzSer.frequencyList();
        calculateOrderExecution(dicsypcList, ztDetail, user);
        return BUHISUtil.ListToResultSet(ztDetail, new CisHzyzResp());
    }

    /**
     * ????????????????????????
     *
     * @param @param  jlxh
     * @param @param  ztbz
     * @param @return ????????????
     * @return Long    ????????????
     * @throws
     * @Title: doSaveGoback
     * @Description: TODO(?????????????????????????????????????????????)
     * @author ?????????
     */
    @Transactional(rollbackFor = Exception.class)
    public Long doSaveGoback(Integer jlxh, Integer ztbz, String bzxx, SysUser user) {
        try {
        	List<PubPharUnfreezeDto> unFreeList = new ArrayList<PubPharUnfreezeDto>();
            // ????????????????????????
            if ("1".equals(ObjectToTypes.parseString(ztbz))) {
                cisHzyzZtDao.cancelZtSubmitStatusByJlxh(2, jlxh, 1, bzxx);
                CisHzyz cisHzyz = new CisHzyz();
                cisHzyz.setYstj(1);
                cisHzyz.setZtyzjlxh(jlxh);
                List<CisHzyz> list = cisHzyzDao.findByEntity(cisHzyz);
                long count = cisHzyzDao.cancelSubmitStatusByZtxh(2, list.stream().distinct().
                		map(CisHzyz::getYzzh).collect(Collectors.toList()), bzxx);
                List<Integer> jlxhList =  list.stream().filter(o -> "3".equals(ObjectToTypes.parseString(o.getYplx())))
                		.map(CisHzyz::getJlxh).collect(Collectors.toList());
                for(Integer item : jlxhList) {
					PubPharUnfreezeDto unfreezeDto = new PubPharUnfreezeDto();
					unfreezeDto.setJgid(user.getHospitalId());
					unfreezeDto.setJlxh(item);
					unfreezeDto.setYwlb(3);
					unFreeList.add(unfreezeDto);
				}
                pubDrugKcdjSerImpl.unfreeze(unFreeList);
                return count;
            } else {
                CisHzyz cishzyz = cisHzyzDao.getById(jlxh);
                if (cishzyz.getKjywsp() != null && cishzyz.getKjywsp().intValue() == 0) {
                    amqcKjywsysqService.cancelDeleteKjyw(cishzyz.getSqid(), user);
                }
                long count = cisHzyzDao.cancelSubmitStatusByJlxh(2, jlxh, 1, bzxx);
                if(cishzyz.getLsyz() == 1 && cishzyz.getYplx() > 0) {
                	PubPharUnfreezeDto unfreezeDto = new PubPharUnfreezeDto();
					unfreezeDto.setJgid(user.getHospitalId());
					unfreezeDto.setJlxh(jlxh);
					unfreezeDto.setYwlb(3);
					unFreeList.add(unfreezeDto);
                }
                pubDrugKcdjSerImpl.unfreeze(unFreeList);
                return count;
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw BaseException.create("ERROR_NURSEWORK_ZYBRRY_00011");
        }
    }

    /**
     * ?????????????????????????????????
     *
     * @param @param  zyh
     * @param @param  lybq
     * @param @param  user
     * @param @return ????????????
     * @return List<CisHzyzFymxResp>    ????????????
     * @throws
     * @Title: queryDispensingRecords
     * @Description: TODO(?????????????????????????????????????????????)
     * @author ?????????
     */
    public List<CisHzyzFymxGrantedResp> queryDispensingRecords(Integer zyh, Integer lybq,
         SysUser user, String beginDate, String endDate, String pydm) {
        Integer jgid = user.getHospitalId();
        try {
            return drugLogService.queryDeliveredMedicine(zyh, lybq, jgid, beginDate, endDate, pydm);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw BaseException.create("ERROR_NURSEWORK_ZYBRRY_00012");
        }
    }


    /**
     * ??????????????????????????????
     *
     * @param @param  zyh
     * @param @return ????????????
     * @return List<NisTymxRecordResp>    ????????????
     * @throws
     * @Title: querytyRecords
     * @Description: TODO(?????????????????????????????????????????????)
     * @author ?????????
     */
    public List<NisTymxRecordResp> querytyRecords(Integer zyh, String beginDate, String endDate) {
        return nisTymxDao.queryTymxRecord(zyh, beginDate, endDate);
    }

    /**
     * ??????????????????
     *
     * @param @param  nisTymxRetrogressiveReq
     * @param @param  user
     * @param @return ????????????
     * @return Double    ????????????
     * @throws
     * @Title: queryTurningBackNumber
     * @Description: TODO(?????????????????????????????????????????????)
     * @author ?????????
     */
    public NisTymxBackResp queryTurningBackNumber(QueryGrantedDrugReq queryGrantedDrugReq, SysUser user) {
        NisTymxBackResp resp = new NisTymxBackResp();
        queryGrantedDrugReq.setJgid(user.getHospitalId());
        DrugsTypkDetailResp drugsTypkDetailResp = drugsTypkOutSer.getDrugsTypkById(queryGrantedDrugReq.getYpxh());
        //??????????????????????????????????????????(1:??? 0:???)
        SysXtcs sysXtcs = sysXtcsCacheSer.getByJGIdAndCsmc(user.getHospitalId(), SysXtcsCsmcCts.ZYCLYPSFNT);
        String sfnt = sysXtcs == null ? "0" : sysXtcs.getCsz();
        if ("0".equals(sfnt) && drugsTypkDetailResp.getBfbz() % drugsTypkDetailResp.getYfbz() != 0) {
            throw BaseException.create("ERROR_NURSEWORK_ZYBRRY_00013");
        }
        //??????????????????
        BigDecimal fysldec = drugLogService.queryFysl(queryGrantedDrugReq);
        if (fysldec == null || fysldec.compareTo(BigDecimal.ZERO) == 0) {
            fysldec = new BigDecimal(0);
        }
        queryGrantedDrugReq.setLybq(null);
        queryGrantedDrugReq.setYpjg(null);

        //??????????????????(???????????????????????????..??????????????????..)
//        Map<String, Object> tyslMap = imFeeFymxDao.queryTysl(queryGrantedDrugReq);
//        BigDecimal tysl = new BigDecimal(0);
//        if (tyslMap != null && tyslMap.get("YPSL") != null) {
//            tysl = ObjectToTypes.parseBigDecimal(tyslMap.get("YPSL"));
//        }

        //??????????????????????????????
        BigDecimal tjsl = nisTymxDao.queryTyslSubmitted(queryGrantedDrugReq);
        if(tjsl == null) {
        	tjsl = new BigDecimal(0);
        }
        resp.setKtysl(fysldec.subtract(tjsl));
        
//        BigDecimal ycsl = nisTj02Dao.queryOneTimeQuantity(queryGrantedDrugReq.getYzxh(), 
//        		queryGrantedDrugReq.getStart(), queryGrantedDrugReq.getEnd());
//        if(ycsl == null) {
//        	ycsl = new BigDecimal(0);
//        }
//        resp.setYcsl(ycsl);
        return resp;
    }


    /**
     * ????????????????????????
     *
     * @param @param nisTymxSaveReq
     * @param @param user    ????????????
     * @return void    ????????????
     * @throws
     * @Title: saveBackApplication
     * @Description: TODO(?????????????????????????????????????????????)
     * @author ?????????
     */
    @Transactional(rollbackFor = Exception.class)
    public void saveBackApplication(NisTymxSaveReq nisTymxSaveReq, SysUser user) {
        Integer zyh = nisTymxSaveReq.getZyh();
        Integer jgid = user.getHospitalId();
        Date zzrq = imZyjsDao.queryTerminationDate(zyh);
        List<NisTymxReq> nisTymxReqList = nisTymxSaveReq.getNisTymxReqList();
        nisTypcDao.deleteTypcByZyh(zyh, jgid);
        nisTymxDao.deleteTymxByZyhAndJgid(zyh, jgid);
        if (CollectionUtils.isNotEmpty(nisTymxReqList)) {
            for (NisTymxReq req : nisTymxReqList) {
                boolean sdflag = req.isSdflag();
                BigDecimal ycsl = req.getYcsl();
                List<NisTymxDetail> detailList = req.getDetailList();
                NisTymx nisTymx = BeanUtil.toBean(req, NisTymx.class);
                BigDecimal ypsl = nisTymx.getYpsl();
                CisHzyz cisHzyz = cisHzyzDao.getById(nisTymx.getYzid());
                nisTymx.setFyfs(cisHzyz.getFyfs());
                nisTymx.setTjbz(0);
                nisTymx.setThbz(0);
                nisTymx.setSqrq(DateUtil.date().toTimestamp());
                nisTymx.setCzgh(ObjectToTypes.parseString(user.getUserId()));
                nisTymx.setTylx(2);
                nisTymx.setYpsl(nisTymx.getYpsl().negate());
                Integer tyjlxh = redisFactory.getTableKey(TableName.DB_NAME, TableName.NIS_TYMX);
                nisTymx.setJlxh(tyjlxh);
                nisTymxDao.insert(nisTymx);
                //??????????????????,????????????????????????,????????????????????????????????????
                if (sdflag) {
                    for (NisTymxDetail detail : detailList) {
                    	if(zzrq != null && detail.getQrrq().before(zzrq)) {
                    		throw BaseException.create("ERROR_NURSEWORK_ZYBRRY_00060", new String[] {
                    				DateUtil.date(zzrq).toString()
                    		});
                    	}
                        NisTypc nisTypc = new NisTypc();
                        nisTypc.setTyxh(tyjlxh);
                        nisTypc.setTjxh(detail.getJlxh());
                        nisTypc.setZxsj(detail.getQrrq());
                        nisTypc.setId(ObjectToTypes.parseLong(
                                redisFactory.getTableKey(TableName.DB_NAME, TableName.NIS_TYPC)));
                        nisTypcDao.insert(nisTypc);
                    }
                } else {
                    List<NisTj02BackDetailResp> backList = nisTj02Dao.queryTurningBackDetail(nisTymx.getYzid(), jgid, 2,
                    		nisTymxSaveReq.getBeginDate(), nisTymxSaveReq.getEndDate());
                    if (CollectionUtils.isNotEmpty(backList)) {
                        for (int i = 0; i < ypsl.divide(ycsl).intValue(); i++) {
                        	if(zzrq != null && backList.get(i).getQrrq().before(zzrq)) {
                        		throw BaseException.create("ERROR_NURSEWORK_ZYBRRY_00060", new String[] {
                        				DateUtil.date(zzrq).toString()
                        		});
                        	}
                            NisTypc nisTypc = new NisTypc();
                            nisTypc.setTyxh(tyjlxh);
                            nisTypc.setTjxh(backList.get(i).getJlxh());
                            nisTypc.setZxsj(backList.get(i).getQrrq());
                            nisTypc.setId(ObjectToTypes.parseLong(
                                    redisFactory.getTableKey(TableName.DB_NAME, TableName.NIS_TYPC)));
                            nisTypcDao.insert(nisTypc);
                        }
                    }
                }
            }
        }
    }

    /**
     * ??????????????????
     *
     * @param @param zyh
     * @param @param user    ????????????
     * @return void    ????????????
     * @throws
     * @Title: saveCommitBackApplication
     * @Description: TODO(?????????????????????????????????????????????)
     * @author ?????????
     */
    public void saveCommitBackApplication(Integer zyh, SysUser user) {
        try {
            nisTymxDao.updateTymxByZyhAndJgid(zyh, user.getHospitalId());
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw BaseException.create("ERROR_NURSEWORK_ZYBRRY_00015");
        }
    }

    /**
     * ????????????
     *
     * @param @param  zyh
     * @param @param  jgid
     * @param @return ????????????
     * @return ErrorResp    ????????????
     * @throws
     * @Title: doCanLeaveHospital
     * @Description: TODO(?????????????????????????????????????????????)
     * @author ?????????
     */
    public String doCanLeaveHospital(Integer zyh, Timestamp cyrq, SysUser user) {
        StringBuffer expiredMsg = new StringBuffer();
        StringBuffer codeMsg = new StringBuffer();
        ReturnEntity<String> returnEntity = new ReturnEntity<>();
        Integer jgid = user.getHospitalId();
        //????????????????????????
        ImRcjl imRcjl = new ImRcjl();
        imRcjl.setZyh(zyh);
        imRcjl.setJgid(jgid);
        imRcjl.setBqpb(0);
        imRcjl.setCzlx(-1);
        long cyzCount = imRcjlDao.findByEntityCount(imRcjl);

        //????????????????????????????????????????????????
        List<Map<String, Object>> dicsypcList = cisHzyzSer.frequencyList();
        imZkjlSer.medicalAdviceToLs(dicsypcList, zyh);
        //?????????????????????????????????????????????
        long l = cisHzyzDao.queryYzXmNotSureOrExcuted(zyh, jgid, null);
        if (l > 0) {
            returnEntity.setError("ERROR_NURSEWORK_ZYBRRY_00040", null);
            expiredMsg.append(returnEntity.getMessage());
            codeMsg.append("ypyz|");
        }
        //?????????????????????????????????????????????
//        l = cisHzyzDao.queryYzXmNotSureOrExcuted(zyh, jgid, 0);
        //?????????????????????????????????????????????YJ_ZY01
//		long count = yjZy01Dao.queryYjNotExcuted(zyh, jgid);
//		if (l > 0 || count > 0) {
//			returnEntity.setError("ERROR_NURSEWORK_ZYBRRY_00041", null);
//			expiredMsg.append(returnEntity.getMessage());
//			codeMsg.append("xmyz|");
//		}
        //????????????????????????????????????
        l = cisHzyzDao.queryCostDetailCnt(zyh);
        if (l > 0) {
            returnEntity.setError("ERROR_NURSEWORK_ZYBRRY_00058", null);
            expiredMsg.append(returnEntity.getMessage());
            codeMsg.append("yjxm|");
        }

        //????????????????????????????????????
        ResultsUtil res = zlRwfpService.dischargeCheck(zyh);
        if(!res.isOk()) {
            returnEntity.setError("ERROR_NURSEWORK_ZYBRRY_00059", null);
            expiredMsg.append(returnEntity.getMessage());
            codeMsg.append("kfxm|");
        }

        //?????????????????????????????????????????????
        l = nisTymxDao.queryAntidoteNotSureOrExcuted(zyh, jgid);
        if (l > 0) {
            returnEntity.setError("ERROR_NURSEWORK_ZYBRRY_00042", null);
            expiredMsg.append(returnEntity.getMessage());
            codeMsg.append("tyd|");
        }

        //??????????????????????????????
        l = cisHzyzDao.queryOperationNotArrangeCount(zyh, jgid);
        if (l > 0) {
            returnEntity.setError("ERROR_NURSEWORK_ZYBRRY_00049", null);
            expiredMsg.append(returnEntity.getMessage());
            codeMsg.append("ssap|");
        }

        if (cyzCount <= 0) {
            returnEntity.setError("ERROR_NURSEWORK_ZYBRRY_00039", null);
            expiredMsg.append(returnEntity.getMessage());
            codeMsg.append("cyz|");
        }
        
        String yzmc = cisHzyzDao.queryNotReviewedDischargeOrder(zyh, jgid);
        if(StrUtil.isNotBlank(yzmc)) {
        	 returnEntity.setError("ERROR_NURSEWORK_ZYBRRY_00062", new String[] {
        			 yzmc
        	 });
             expiredMsg.append(returnEntity.getMessage());
             codeMsg.append("cyyz|");
        }
        
//        l = imFeeFymxDao.queryAfterCyIsExistCostCount(jgid, zyh, cyrq);
//        if(l > 0) {
//        	 returnEntity.setError("ERROR_NURSEWORK_ZYBRRY_00054", null);
//             expiredMsg.append(returnEntity.getMessage());
//             codeMsg.append("fy|");
//        }

        if (expiredMsg.length() > 0) {
            expiredMsg.append(",").append(codeMsg.substring(0, codeMsg.length() - 1));
        }
        return expiredMsg.toString();
    }

    /**
     * ??????????????????????????????
     *
     * @param @param zyh    ????????????
     * @return void    ????????????
     * @throws
     * @Title: doTzcfYJfyCheck
     * @Description: TODO(?????????????????????????????????????????????)
     * @author ?????????
     */
    public void doTzcfYjfyCheck(Integer zyh) {
        List<Map<String, Object>> list = cisHzyzDao.queryCostDetail(zyh);
        String yzmc = "";
        boolean flag = false;
        for (int i = 0; i < list.size(); i++) {
            if ("".equals(list.get(i).get("FYMX_JLXH") + "") || "null".equals(list.get(i).get("FYMX_JLXH") + "")) {
                yzmc = list.get(i).get("YZMC") + "(" + list.get(i).get("JLXH") + ")";
                flag = true;
                break;
            }
        }
        if (flag) {
            throw BaseException.create("ERROR_NURSEWORK_ZYBRRY_00018", new String[]{yzmc});
        }
        
        ResultsUtil res = zlRwfpService.dischargeCheck(zyh);
        if(!res.isOk()) {
        	 throw BaseException.create("ERROR_NURSEWORK_ZYBRRY_00055", new String[]{res.getString()});
        }
    }

    /**
     * ??????????????????
     *
     * @param @param zyh
     * @param @param user    ????????????
     * @return void    ????????????
     * @throws
     * @Title: doSaveLeaveHospital
     * @Description: TODO(?????????????????????????????????????????????)
     * @author ?????????
     */
    @Transactional(rollbackFor = Exception.class)
    public void doSaveLeaveHospital(Integer zyh, SysUser user) {
        try {
            ImRcjl imRcjl = new ImRcjl();
            imRcjl.setZyh(zyh);
            imRcjl.setJgid(user.getHospitalId());
            imRcjl.setCzlx(-1);
            imRcjl.setBqpb(0);
            List<ImRcjl> imRcjlList = imRcjlDao.findByEntity(imRcjl);
            if (CollectionUtils.isEmpty(imRcjlList)) {
                throw BaseException.create("ERROR_NURSEWORK_ZYBRRY_00019");
            }
            imRcjl = imRcjlList.get(0);
            long l = imHzryDao.queryDischarged(zyh, user.getHospitalId());
            if (l > 0) {
                throw BaseException.create("ERROR_NURSEWORK_ZYBRRY_00020");
            }
            ImCyjl imCyjl = new ImCyjl();
            imCyjl.setCzrq(DateUtil.date().toTimestamp());
            imCyjl.setCzr(user.getUserId());
            imCyjl.setCzlx(3);
            imCyjl.setCysj(imRcjl.getLcrq());
            imCyjl.setCyfs(imRcjl.getCyfs());
            imCyjl.setJgid(user.getHospitalId());
            imCyjl.setZyh(zyh);
            imCyjl.setJlxh(redisFactory.getTableKey(TableName.DB_NAME, TableName.IM_CYJL));
            imCyjlDao.insert(imCyjl);

            Map<String, Object> brry = new HashMap<String, Object>(16);
            brry.put("CYRQ", imRcjl.getLcrq());
            brry.put("CYFS", imRcjl.getCyfs());
            brry.put("ZYH", zyh);
            brry.put("JGID", user.getHospitalId());
            brry.put("CYPB", 1);
            imHzryDao.updateInformDischarge(brry);

            SysXtcs sysXtcs = sysXtcsCacheSer.getByJGIdAndCsmc(user.getHospitalId(), SysXtcsCsmcCts.SFTZCYSFCW);
            String sftzcysfcw = sysXtcs == null ? "0" : sysXtcs.getCsz();
            if ("1".equals(sftzcysfcw)) {
                //????????????
                cxcw(zyh, user.getHospitalId());
            }

        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw BaseException.create("ERROR_NURSEWORK_ZYBRRY_00021");
        }
    }


    /**
     * ???????????????????????????
     *
     * @param @param zyh
     * @param @param jgid    ????????????
     * @return void    ????????????
     * @throws
     * @Title: cxcw
     * @Description: TODO(?????????????????????????????????????????????)
     * @author ?????????
     */
    public void cxcw(Integer zyh, Integer jgid) {
        ImCwsz imCwsz = new ImCwsz();
        imCwsz.setZyh(zyh);
        imCwsz.setJgid(jgid);
        imCwszDao.updateTc(imCwsz);
    }

    /**
     * ??????????????????
     *
     * @param @param zyh
     * @param @param user    ????????????
     * @return void    ????????????
     * @throws
     * @Title: doSaveCancelLeaveHospital
     * @Description: TODO(?????????????????????????????????????????????)
     * @author ?????????
     */
    @Transactional(rollbackFor = Exception.class)
    public void doSaveCancelLeaveHospital(Integer zyh, SysUser user) {
        long l = imHzryDao.queryDischarged(zyh, user.getHospitalId());
        if (l > 0) {
            throw BaseException.create("ERROR_NURSEWORK_ZYBRRY_00022");
        }
        ImCyjl imCyjl = new ImCyjl();
        imCyjl.setCzrq(DateUtil.date().toTimestamp());
        imCyjl.setCzr(user.getUserId());
        imCyjl.setCzlx(4);
        imCyjl.setCysj(DateUtil.date().toTimestamp());
        imCyjl.setCyfs(0);
        imCyjl.setJgid(user.getHospitalId());
        imCyjl.setZyh(zyh);
        imCyjl.setJlxh(redisFactory.getTableKey(TableName.DB_NAME, TableName.IM_CYJL));
        imCyjlDao.insert(imCyjl);

        Map<String, Object> brry = new HashMap<String, Object>(16);
        brry.put("CYRQ", null);
        brry.put("CYFS", null);
        brry.put("ZYH", zyh);
        brry.put("JGID", user.getHospitalId());
        brry.put("CYPB", 0);
        imHzryDao.updateInformDischarge(brry);
    }


    /**
     * ?????????????????????????????????????????????????????????
     *
     * @param @param  cisHzyzorztExcuteReq
     * @param @param  user
     * @param @return ????????????
     * @return List<ImHzryYpSumbitResp>    ????????????
     * @throws
     * @Title: doPatientQuery
     * @Description: TODO(?????????????????????????????????????????????)
     * @author ?????????
     */
    public List<ImHzryYpSumbitResp> doPatientQuery(CisHzyzorztExcuteReq cisHzyzorztExcuteReq, SysUser user) {
        Integer jgid = user.getHospitalId();
        //????????????
        Integer hsql = cisHzyzorztExcuteReq.getBrbq();
        if (hsql == null) {
            throw BaseException.create("ERROR_NURSEWORK_ZYBRRY_0004");
        }
        String date = BUHISUtil.getDate();
        Date qrsj = BUHISUtil.toDate(date);// ????????????

        Integer zyh = 0;
        if (cisHzyzorztExcuteReq.getZyh() != null) {
            zyh = cisHzyzorztExcuteReq.getZyh();
        }

        SysXtcs sysXtcs = sysXtcsCacheSer.getByJGIdAndCsmc(jgid, SysXtcsCsmcCts.FHYZHJF);
        String xyf = sysXtcs == null ? "0" : sysXtcs.getCsz();
        if ("1".equals(xyf)) {
            cisHzyzorztExcuteReq.setFhbz(1);
        }
        try {
            cisHzyzorztExcuteReq.setJgid(jgid);
            cisHzyzorztExcuteReq.setQrsj(new Timestamp(qrsj.getTime()));
            if(cisHzyzorztExcuteReq.getLsyz() != null && cisHzyzorztExcuteReq.getLsyz() == 2) {
            	cisHzyzorztExcuteReq.setLsyz(null);
            }
            // ??????list???????????????

            List<Map<String, Object>> inofList = cisHzyzDao.queryPatientInfoByProject(cisHzyzorztExcuteReq);
            List<Map<String, Object>> reList = new ArrayList<Map<String, Object>>();
            if (inofList != null) {
                for (int i = 0; i < inofList.size(); i++) {
                    Map<String, Object> inof = inofList.get(i);
                    cisHzyzorztExcuteReq.setZyh(ObjectToTypes.parseInt(inof.get("ZYH")));
                    List<CisHzyzOrztResp> cisHzyzOrztRespList = doDetailChargeQuery(cisHzyzorztExcuteReq, user);
                    if (CollectionUtils.isNotEmpty(cisHzyzOrztRespList)) {
                        reList.add(inof);
                    }

                }
            }
            return BUHISUtil.ListToResultSet(reList, new ImHzryYpSumbitResp());
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw BaseException.create("ERROR_NURSEWORK_ZYBRRY_00024");
        }
    }


    /**
     * ?????????????????????????????????????????????????????????
     *
     * @param @param  user
     * @param @return ????????????
     * @return Set<CisHzyzXmResp>    ????????????
     * @throws
     * @Title: xmQuery
     * @Description: TODO(?????????????????????????????????????????????)
     * @author ?????????
     */
    public Set<CisHzyzXmResp> xmQuery(Integer brbq, Integer lsyz, SysUser user) {
        CisHzyzorztExcuteReq cisHzyzorztExcuteReq = new CisHzyzorztExcuteReq();
        cisHzyzorztExcuteReq.setBrbq(brbq);
        cisHzyzorztExcuteReq.setLsyz(lsyz);
        List<CisHzyzOrztResp> cisHzyzOrztRespList = doDetailChargeQuery(cisHzyzorztExcuteReq, user);
        Set<CisHzyzXmResp> ret = new HashSet<CisHzyzXmResp>();
        for (CisHzyzOrztResp cisHzyzOrztResp : cisHzyzOrztRespList) {
            CisHzyzXmResp cisHzyzXmResp = new CisHzyzXmResp();
            cisHzyzXmResp.setYpxh(cisHzyzOrztResp.getYpxh());
            cisHzyzXmResp.setYzmc(cisHzyzOrztResp.getYzmc());
            cisHzyzXmResp.setZtbz(cisHzyzOrztResp.getZtbz());
            ret.add(cisHzyzXmResp);
        }
        return ret;
    }


    /**
     * ????????????????????????????????????????????????
     *
     * @param @param  cisHzyzExcuteReq
     * @param @param  user
     * @param @return ????????????
     * @return List<ImHzryYpSumbitResp>    ????????????
     * @throws
     * @Title: additionProjectsBrQuery
     * @Description: TODO(?????????????????????????????????????????????)
     * @author ?????????
     */
    public List<ImHzryYpSumbitResp> additionProjectsBrQuery(CisHzyzExcuteReq cisHzyzExcuteReq, SysUser user) {
        List<CisHzyzOrztResp> cisHzyzOrztRespList = doAdditionProjectsQuery(cisHzyzExcuteReq, user);
        List<ImHzryYpSumbitResp> ret = new ArrayList<ImHzryYpSumbitResp>();
        for (CisHzyzOrztResp cisHzyzOrzt : cisHzyzOrztRespList) {
            int i = 0;
            for (ImHzryYpSumbitResp m : ret) {
                if (cisHzyzOrzt.getZyh().intValue() == m.getZyh().intValue()) {
                    i = 1;
                    break;
                }
            }
            if (i == 0) {
                ImHzryYpSumbitResp maptemp = new ImHzryYpSumbitResp();
                maptemp.setZyh(cisHzyzOrzt.getZyh());
                maptemp.setBrch(cisHzyzOrzt.getBrch());
                maptemp.setBrxm(cisHzyzOrzt.getBrxm());
                ret.add(maptemp);
            }
        }
        return ret;
    }


    /**
     * ????????????????????????????????????
     *
     * @param @param  cisHzyzExcuteReq
     * @param @param  user
     * @param @return ????????????
     * @return List<CisHzyzOrztResp>    ????????????
     * @throws
     * @Title: doAdditionProjectsQuery
     * @Description: TODO(?????????????????????????????????????????????)
     * @author ?????????
     */
    public List<CisHzyzOrztResp> doAdditionProjectsQuery(CisHzyzExcuteReq cisHzyzExcuteReq, SysUser user) {
        Integer jgid = user.getHospitalId();
        //????????????
        Integer hsql = cisHzyzExcuteReq.getBrbq();
        if (hsql == null) {
            throw BaseException.create("ERROR_NURSEWORK_ZYBRRY_0004");
        }

        try {
            cisHzyzExcuteReq.setJgid(jgid);
            // ???????????????????????????
            List<Map<String, Object>> inofList = cisHzyzDao.queryAdditionalPriced(cisHzyzExcuteReq);
            // ???????????????????????? ??????????????????
            int fjbz = 1; // ????????????
            if (inofList.size() > 0) {
                //??????????????????
                inofList.get(0).put("FJBZ", fjbz);
                List<Map<String, Object>> dicsypcList = cisHzyzSer.frequencyList();
                calculateOrderExecution(dicsypcList, inofList, user);
                //long ZYH = 0;
                for (int i = 0; i < inofList.size(); i++) {
                    double fycs = Double.parseDouble(inofList.get(i).get("FYCS") + "");
                    double ycsl = Double.parseDouble(inofList.get(i).get("YCSL") + "");
                    double ypdj = Double.parseDouble(inofList.get(i).get("YPDJ") + "");
                    if (fycs <= 0) {
                        inofList.remove(i);
                        i--;
                    } else {
                        inofList.get(i).put("FYCS", fycs);
                        inofList.get(i).put("JE", (fycs * ycsl * ypdj));
                    }
                }
            }

            return BUHISUtil.ListToResultSet(inofList, new CisHzyzOrztResp());
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw BaseException.create("ERROR_NURSEWORK_ZYBRRY_00025");
        }
    }

    /**
     * ???????????????????????????????????????????????????
     *
     * @param @param  cisHzyzExcuteReq
     * @param @param  user
     * @param @return ????????????
     * @return Set<CisHzyzXmResp>    ????????????
     * @throws
     * @Title: additionProjectsXmQuery
     * @Description: TODO(?????????????????????????????????????????????)
     * @author ?????????
     */
    public Set<CisHzyzXmResp> additionProjectsXmQuery(CisHzyzExcuteReq cisHzyzExcuteReq, SysUser user) {
        List<CisHzyzOrztResp> cisHzyzOrztRespList = doAdditionProjectsQuery(cisHzyzExcuteReq, user);
        Set<CisHzyzXmResp> ret = new HashSet<CisHzyzXmResp>();
        for (CisHzyzOrztResp cisHzyzOrztResp : cisHzyzOrztRespList) {
            CisHzyzXmResp cisHzyzXmResp = new CisHzyzXmResp();
            cisHzyzXmResp.setYpxh(cisHzyzOrztResp.getYpxh());
            cisHzyzXmResp.setYzmc(cisHzyzOrztResp.getYzmc());
            ret.add(cisHzyzXmResp);
        }
        return ret;
    }

    /**
     * ????????????????????????
     *
     * @param @param  cisHzyzNurseExReq
     * @param @param  user
     * @param @return ????????????
     * @return BqTjMsgResp    ????????????
     * @throws
     * @Title: doSaveConfirmAdditional
     * @Description: TODO(?????????????????????????????????????????????)
     * @author ?????????
     */
    @Transactional(rollbackFor = Exception.class)
    public NisTjMsgResp doSaveConfirmAdditional(CisHzyzNurseExReq cisHzyzNurseExReq, SysUser user) {
        List<CisHzyzBody> cisHzyzBodyList = cisHzyzNurseExReq.getCisHzyzBody();
        List<Map<String, Object>> listMap = BUHISUtil.ListObjToMap(cisHzyzBodyList);
        NisTjMsgResp bqTjMsgResp = new NisTjMsgResp();
        Integer jgid = user.getHospitalId();
        //????????????
        Integer hsql = cisHzyzNurseExReq.getBrbq();
        Integer yggh = user.getUserId();
        if (hsql == null) {
            throw BaseException.create("ERROR_NURSEWORK_ZYBRRY_0004");
        }

        try {
            CisHzyzExcuteReq cisHzyzExcuteReq = new CisHzyzExcuteReq();
            cisHzyzExcuteReq.setJgid(jgid);
            cisHzyzExcuteReq.setBrbq(hsql);
            // ??????list???????????????
            List<Map<String, Object>> inofList = cisHzyzDao.queryAdditionalPriced(cisHzyzExcuteReq);

            // ???????????????????????? ??????????????????
            List<Map<String, Object>> body = new ArrayList<Map<String, Object>>();
            int fjbz = 1; // ????????????
            if (inofList.size() > 0) {
                inofList.get(0).put("FJBZ", fjbz);// ??????????????????
                List<Map<String, Object>> dicsypcList = cisHzyzSer.frequencyList();
                calculateOrderExecution(dicsypcList, inofList, user);
                List<Map<String, Object>> tempList = new ArrayList<Map<String, Object>>();
                for (int i = 0; i < inofList.size(); i++) {
                    double fycs = Double.parseDouble(inofList.get(i).get("FYCS") + "");
                    double ypdj = Double.parseDouble(inofList.get(i).get("YPDJ") + "");
                    if (fycs <= 0) {
                        //inofList.remove(i);
                        //i--;
                    } else {
                        inofList.get(i).put("FYCS", fycs);
                        double qrsl = 0;
                        boolean x = false;
                        for (int j = 0; j < listMap.size(); j++) {
                            String jlxh = ObjectToTypes.parseString(listMap.get(j).get("JLXH"));
                            String jlxh2 = ObjectToTypes.parseString(inofList.get(i).get("JLXH"));
                            if (jlxh.equals(jlxh2)) {
                                qrsl = Double.parseDouble(listMap.get(j).get("FYSL") + "");
                                x = true;
                                break;
                            }

                        }
                        if (!x) {
                            continue;
                        }
                        inofList.get(i).put("JE", (fycs * qrsl * ypdj));
                        //???????????????????????????????????????Map
                        Map<String, Object> imfeefymxMap = (Map<String, Object>) inofList.get(i);
                        imfeefymxMap.put("YZXH", ObjectToTypes.parseInt(inofList.get(i).get("JLXH")));
                        imfeefymxMap.put("ZYH", ObjectToTypes.parseInt(inofList.get(i).get("ZYH")));
                        imfeefymxMap.put("FYXH", ObjectToTypes.parseInt(inofList.get(i).get("YPXH")));
                        imfeefymxMap.put("YPLX", ObjectToTypes.parseInt(inofList.get(i).get("YPLX")));
                        imfeefymxMap.put("FYMC", inofList.get(i).get("YZMC") + "");
                        imfeefymxMap.put("FYRQ", DateUtil.date().toTimestamp());
                        imfeefymxMap.put("YPCD", 0);
                        imfeefymxMap.put("DZBL", 0);
                        imfeefymxMap.put("JSCS", 0);
                        imfeefymxMap.put("ZJJE", 0);
                        imfeefymxMap.put("ZFJE", 0);
                        imfeefymxMap.put("ZLJE", 0);
                        imfeefymxMap.put("ZFBL", 0);
                        imfeefymxMap.put("JGID", jgid);
                        imfeefymxMap.put("QRGH", yggh);
                        imfeefymxMap.put("XMLX", 1);
                        imfeefymxMap.put("FYXM", 0);
                        imfeefymxMap.put("FYBQ", hsql);
                        imfeefymxMap.put("JFRQ", DateUtil.date().toTimestamp());
                        imfeefymxMap.put("YSGH", inofList.get(i).get("YSGH") + "");
                        imfeefymxMap.put("FYSL", qrsl);
                        imfeefymxMap.put("FYDJ", ObjectToTypes.parseDouble(inofList.get(i).get("YPDJ")));
                        imfeefymxMap.put("FYKS", ObjectToTypes.parseInt(inofList.get(i).get("BRKS")));
                        imfeefymxMap.put("BRXZ", ObjectToTypes.parseInt(inofList.get(i).get("BRXZ")));
                        // ????????????????????? ?????????????????????
                        Integer zxks = 0;
                        if (inofList.get(i).get("ZXKS") == null
                                || inofList.get(i).get("ZXKS") == "") {
                            zxks = ObjectToTypes.parseInt(inofList.get(i).get("BRKS"));
                        } else {
                            zxks = ObjectToTypes.parseInt(inofList.get(i).get("ZXKS"));
                        }
                        imfeefymxMap.put("ZXKS", zxks);

                        if (inofList.get(i).get("CZGH") != null) {
                            imfeefymxMap.put("SRGH", ObjectToTypes.parseInt(inofList.get(i).get("CZGH")));
                        }
                        if (inofList.get(i).get("ZLXZ") != null) {
                            imfeefymxMap.put("ZLXZ", ObjectToTypes.parseInt(inofList.get(i).get("ZLXZ")));
                        }
                        if (inofList.get(i).get("YEPB") != null) {
                            imfeefymxMap.put("YEPB", ObjectToTypes.parseInt(inofList.get(i).get("YEPB")));
                        }
                        /**
                         * ???FYCS??????????????????????????????????????????????????????????????????????????????* ??????
                         **/
                        imfeefymxMap.put("JE", (qrsl * ypdj));
                        body.add(imfeefymxMap);
                        tempList.add(inofList.get(i));
                    }
                }
                //??????????????????
                cisHzyzSer.ArrearsPatientsQuery(body, user, bqTjMsgResp);

                List<Map<String, Object>> listForputFymx = new ArrayList<Map<String, Object>>();
                insertFymx(body, listForputFymx, user, cisHzyzNurseExReq.getBrbq());
                List<Map<String, Object>> xhmxList = new ArrayList<Map<String, Object>>();// ??????????????????

                SysXtcs sysXtcs = sysXtcsCacheSer.getByJGIdAndCsmc(jgid, SysXtcsCsmcCts.WPJFBZ);
                Integer wpjfbz = sysXtcs == null ? 0 : ObjectToTypes.parseInt(sysXtcs.getCsz());
                sysXtcs = sysXtcsCacheSer.getByJGIdAndCsmc(jgid, SysXtcsCsmcCts.WZSFXMJGZY);
                int wzsfxmjg = sysXtcs == null ? 0 : ObjectToTypes.parseInt(sysXtcs.getCsz());
                long brid = 0;
                long ksdm = 0;
                String ksmc = "";
                for (int i = 0; i < listForputFymx.size(); i++) {
                    Map<String, Object> parametersForputFymx = listForputFymx.get(i);
                    Map<String, Object> parametersUpdate = new HashMap<String, Object>(16);
                    if (wpjfbz == 1 && wzsfxmjg == 0) {
                        //???????????????
                        if (ObjectToTypes.parseInt(parametersForputFymx.get("YPLX")) == 0) {
//							FeeXmzxks feeXmzxks = new FeeXmzxks();
//							feeXmzxks.setFyxh(ObjectToTypes.parseInt(parametersForputFymx.get("FYXH")));
//							feeXmzxks.setJgid(jgid);
//							List<FeeXmzxks> fywzList = feeXmzxksDao.findByEntity(feeXmzxks);
//							if (fywzList != null && fywzList.size() > 0) {
//								ImHzry imHzry = imHzryDao.getById(ObjectToTypes.parseInt(parametersForputFymx.get("ZYH")));
//								for (FeeXmzxks fywz : fywzList) {
//									Map<String, Object> m = new HashMap<String, Object>(16);
//									brid = imHzry.getBrid();
//									ksdm = hsql;
//									ksmc = cisHzyzNurseExReq.getQxmc();
//									m.put("JGID", jgid);
//									m.put("KSDM", hsql);
//									m.put("KSMC", cisHzyzNurseExReq.getQxmc());
//									m.put("BRID", imHzry.getBrid());
//									m.put("BRHM", imHzry.getZyhm());
//									m.put("BRLY", "ZY");
//									m.put("BRXM", imHzry.getBrxm());
//									m.put("WZXH", fywz.getWzxh());
//									m.put("ZJJE",parametersForputFymx.get("ZJJE"));
//									m.put("WZSL",BUHISUtil.simpleMultiply(2, fywz.getWzsl(), parametersForputFymx.get("FYSL")));
//									xhmxList.add(m);
//								}
//							}
                        }
                    }
                    for (int j = 0; j < tempList.size(); j++) {
                        long jlxh = ObjectToTypes.parseInt(tempList.get(j).get("JLXH"));
                        long jlxhl = ObjectToTypes.parseInt(listForputFymx.get(i).get("YZXH"));
                        if (jlxh == jlxhl) {
                            String qrsj = tempList.get(j).get("QRSJ") + "";
                            parametersUpdate.put("JLXH", jlxh);
                            parametersUpdate.put("QRSJ", new Timestamp(BUHISUtil.toDate(qrsj).getTime()));
                            cisHzyzDao.updateTimeAndLsByJlxh(parametersUpdate);
                            if (Double.parseDouble(parametersForputFymx.get("FYSL") + "") != 0) {
                                parametersForputFymx.put("SRGH", yggh);
                                ImFeeFymx imFeeFymx = BeanUtil.fillBeanWithMapIgnoreCase(parametersForputFymx, new ImFeeFymx(), true);
                                imFeeFymx.setJlxh(redisFactory.getTableKey(TableName.DB_NAME, TableName.IM_FEE_FYMX));
                                imFeeFymxDao.insert(imFeeFymx);
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            throw BaseException.create("ERROR_NURSEWORK_ZYBRRY_00026");
        }
        return bqTjMsgResp;
    }

    /**
     * ??????????????????????????????????????????????????????
     *
     * @param @param  cndValue
     * @param @param  cndName
     * @param @param  user
     * @param @return ????????????
     * @return ImHzry    ????????????
     * @throws
     * @Title: doQueryItemInfo
     * @Description: TODO(?????????????????????????????????????????????)
     * @author ?????????
     */
    public ImHzry doQueryItemInfo(String zyhm, Integer brbq, String brch, SysUser user) {
        Integer jgid = user.getHospitalId();
        ImHzry imHzry = new ImHzry();
        imHzry.setJgid(jgid);
        imHzry.setZyhm(zyhm);
        imHzry.setBrbq(brbq);
        imHzry.setBrch(brch);
        return imHzryDao.queryPatientInfoByZyhmOrBrch(imHzry);
    }

    /**
     * ????????????????????????
     *
     * @param @param  req
     * @param @param  res
     * @param @param  user
     * @param @return ????????????
     * @return List<ZybqyzCostResp>    ????????????
     * @throws
     * @Title: doSaveCostList
     * @Description: TODO(?????????????????????????????????????????????)
     * @author ?????????
     */
    @Transactional(rollbackFor = Exception.class)
    @Locked(value = {"saveAccountMoney_#[zyhs]"})
    public List<CisHzyzCostResp> doSaveCostList(Integer zyhs, ImFeeFymxSaveReq imFeeFymxSaveReq, SysUser user) {
            Timestamp now = DateUtils.getNow();
            List<Map<String, Object>> returnData = new ArrayList<Map<String, Object>>();
        Integer jgid = user.getHospitalId();
        Integer userId = user.getUserId();
        List<ImFeeFymxSaveReq.FymxBody> fymxBodyList = imFeeFymxSaveReq.getFymxBody();
        List<Map<String, Object>> saveDataList = BUHISUtil.ListObjToMap(fymxBodyList);
        List<Map<String, Object>> tj02mapList = new ArrayList<Map<String, Object>>();
        Map<String, Object> tj02map = new HashMap<String, Object>(16);
        //??????????????????
        //????????????bug
        Integer zyhcldb = ObjectToTypes.parseInt(saveDataList.get(0).get("ZYH"));
        Integer brxz = ObjectToTypes.parseInt(saveDataList.get(0).get("BRXZ"));

        String floor = sysXtcsCacheSer.getCsz(jgid, SysXtcsCsmcCts.JZKSTSXZ);//??????????????????????????????
        Timestamp min = DateUtils.dateAdd(now, 0 - Integer.parseInt(floor));
        String ceiling = sysXtcsCacheSer.getCsz(jgid, SysXtcsCsmcCts.JZJSTSXZ);//??????????????????????????????
        Timestamp max = DateUtils.dateAdd(now, Integer.parseInt(ceiling));

        ImHzry imHzry = imHzryDao.getById(zyhs);
        ImZyjs lastJs = zyjsPreService.getLastImZyjs(zyhs);//????????????????????????

        List<Integer> intList = new ArrayList<Integer>();
        for (Map<String, Object> body : saveDataList) {
            Timestamp ldtNow = DateUtils.getNow();
            Integer zyh = ObjectToTypes.parseInt(body.get("ZYH"));
            Integer yplx = ObjectToTypes.parseInt(body.get("YPLX"));
            Integer fyxh = ObjectToTypes.parseInt(body.get("FYXH"));
            String fymc = ObjectToTypes.parseString(body.get("fymc"));
            Integer fyxm = cisHzyzSer.getfygb(yplx, fyxh);
            Integer zxks = 0;


            if (body.containsKey("ZXKS")) {
                zxks = ObjectToTypes.parseInt(body.get("ZXKS"));
            }
            Integer fyks = ObjectToTypes.parseInt(body.get("FYKS"));
            double fysl = Double.parseDouble(body.get("FYSL") + "");
            double fydj = Double.parseDouble(body.get("FYDJ") + "");
                // ?????????????????????????????????????????????
                if (fysl > 0 && zxks == 0) {
                    zxks = fyks;
                }
                // ?????????????????????????????????????????????????????????????????????, ????????????????????????,????????????????????????????????????
                String lsysghkzys = ObjectToTypes.parseString(body.get("YSGH"));


            //??????????????????
            Timestamp fyrq = DateUtils.parseToDateTime(String.valueOf(body.get("fyrq")));


            if (fyrq.before(min)) {//??????????????????????????????
                throw BaseException.create("ERROR_NURSEWORK_ZYBRRY_00048");
            }

            if (lastJs == null && fyrq.before(imHzry.getRyrq())) {//??????????????????????????????
                throw BaseException.create("ERROR_NURSEWORK_ZYBRRY_00051", new String[]{imHzry.getRyrq().toString()});
            } else if (lastJs != null && fyrq.before(lastJs.getZzrq())) {//??????????????????????????????????????????
                throw BaseException.create("ERROR_NURSEWORK_ZYBRRY_00052", new String[]{lastJs.getZzrq().toString()});
            }

            if (fyrq.after(max)) {//??????????????????????????????
                throw BaseException.create("ERROR_NURSEWORK_ZYBRRY_00048");
            } else if (imHzry.getCyrq() != null && fyrq.after(imHzry.getCyrq())) {//??????????????????????????????
                throw BaseException.create("ERROR_NURSEWORK_ZYBRRY_00053", new String[]{imHzry.getCyrq().toString()});
            }

            //????????????????????????
            Double fyslCount = preService.getFyslCount(zyh, yplx, fyxh);
            if (fyslCount + fysl < 0) {
                throw BaseException.create("ERROR_NURSEWORK_ZYBRRY_00030", new String[]{String.valueOf(fyslCount)});
            }
            //??????????????????????????????
            if (yplx == FymxYplxEnum.NOT_MEDICINE.getValue()) {
                FeeYlsfxmOutResp feeYlsfxmOutResp = feeYlsfxmOutSer.getById(fyxh);
                if (FeeYlsfxmXmlxEnum.MATERIALS.equals(feeYlsfxmOutResp.getXmlx())) {
                        List<HighQualityConsumablesResp> list = mdiVejkfBatchViewService.queryHighQualityConsumables(Arrays.asList(new String[]{imHzry.getBrbq() + "-" + feeYlsfxmOutResp.getFyxh()}));
                    if (CollectionUtil.isEmpty(list) || StrUtil.isBlank(list.get(0).getAmount())) {
                        throw BaseException.create("ERROR_NURSEWORK_ZYBRRY_00056", new String[]{fymc});
                    }
                    Double amount = Double.parseDouble(list.get(0).getAmount());
                    if (amount < fysl) {
                        throw BaseException.create("ERROR_NURSEWORK_ZYBRRY_00057", new String[]{fymc + "[" + amount + "]"});
                    }
                }
            }


            Map<String, Object> parameters = new HashMap<String, Object>(16);
            parameters.put("ZYH", zyh);
            String ysgh = "";
            if (StrUtil.isNotBlank(lsysghkzys)) {
                ysgh = lsysghkzys;
            }
            if (ysgh.length() == 0 && imHzry != null) {
                    if (imHzry.getZsys() != null) {
                        ysgh = imHzry.getZsys();
                    }
                }

            Map<String, Object> fymxMap = new HashMap<String, Object>(16);
                fymxMap.put("fyxm", fyxm);
                double zlje = 0;
                if (yplx == 0) {// ????????????
                    /**************???????????????????????????*******************/
                    //??????????????????????????????:???????????? ????????????????????????>=1????????????????????????????????????ZFBL???????????????
                    Map<String, Object> reje = getPriceJe(yplx, brxz, fyxh, fyxm, fydj, fysl);
                    fymxMap.put("ZFBL", reje.get("ZFBL"));
                    fymxMap.put("ZJJE", reje.get("ZJJE"));
                    fymxMap.put("ZFJE", reje.get("ZFJE"));
                    fymxMap.put("ZLJE", reje.get("ZLJE"));
                } else {// ????????????
                    //1034??????????????????1017??????????????????,1103???????????????
                    String ksdm = saveDataList.get(0).get("FYKS").toString();
                    if ("1034".equals(ksdm) || "1017".equals(ksdm) || "1103".equals(ksdm)) {
                        tj02map = new HashMap<String, Object>(16);
                        tj02map.put("YZXH", 0);
                        tj02map.put("ZYH", zyh);
                        tj02map.put("YPXH", fyxh);
                        tj02map.put("YPCD", body.get("YPCD"));
                        tj02map.put("KSSJ", DateUtil.date().toTimestamp());
                        tj02map.put("YCSL", fysl);
                        tj02map.put("YTCS", 1);
                        tj02map.put("JFRQ", DateUtil.date().toTimestamp());
                        tj02map.put("SYPC", "st");
                        tj02map.put("YPDJ", fydj);
                        tj02map.put("YFDW", body.get("YFDW"));
                        tj02map.put("YFGG", body.get("YFGG"));
                        tj02map.put("YFBZ", 1);
                        tj02map.put("QRRQ", new Date());
                        tj02map.put("LSYZ", 1);
                        tj02map.put("FYJE", 0);
                        tj02map.put("FYBZ", 0);
                        tj02map.put("QZCL", 0);
                        tj02map.put("SJFYBZ", 0);
                        tj02map.put("FYSL", fysl);
                        tj02map.put("JGID", jgid);
                        tj02map.put("YEPB", 0); // ???????????? ?????????0
                        tj02map.put("FYKS", fyks);
                        tj02map.put("BQYZ", 1L);
                        tj02map.put("QRBZ", 0);
                    }
                    /**************???????????????????????????*******************/
                    //??????????????????????????????:???????????? ????????????????????????>=1????????????????????????????????????ZFBL???????????????
                    Map<String, Object> reje = getPriceJe(yplx,
                            brxz, fyxh, fyxm, fydj, fysl);
                    fyxm = ObjectToTypes.parseInt(reje.get("FYGB"));
                    fymxMap.put("FYXM", fyxm);
                    fymxMap.put("ZFBL", reje.get("ZFBL"));
                    fymxMap.put("ZJJE", reje.get("ZJJE"));
                    fymxMap.put("ZFJE", reje.get("ZFJE"));
                    fymxMap.put("ZLJE", reje.get("ZLJE"));
                }
                Date ldtFyrq = ldtNow;
                if (body.containsKey("FYRQ")) {
                    ldtFyrq = BUHISUtil.toDate(body.get("FYRQ") + "");
                }
                if (yplx == 0) {
                    fymxMap.put("YPCD", 0);
                } else {
                    fymxMap.put("YPCD", body.get("YPCD"));
                }
                if (body.get("YZXH") != null) {
                    fymxMap.put("YZXH", body.get("YZXH"));
                } else {
                    fymxMap.put("YZXH", 0);
                }

                fymxMap.put("ZYH", zyh);
                fymxMap.put("FYRQ", DateUtil.date(ldtFyrq).toTimestamp());
                fymxMap.put("FYXH", fyxh);
                fymxMap.put("FYXM", fyxm);
                fymxMap.put("FYMC", body.get("FYMC"));
                fymxMap.put("FYSL", fysl);
                fymxMap.put("FYDJ", fydj);
                fymxMap.put("SRGH", userId);
                fymxMap.put("QRGH", userId);
                fymxMap.put("FYKS", fyks);
                fymxMap.put("ZXKS", zxks);
                fymxMap.put("JFRQ", ldtNow);
                fymxMap.put("XMLX", 4);
                fymxMap.put("YPLX", yplx);
                fymxMap.put("YSGH", ysgh);
                fymxMap.put("FYBQ", body.get("FYBQ"));
                fymxMap.put("JSCS", 0);
                fymxMap.put("YEPB", 0);
                fymxMap.put("JGID", jgid);
                fymxMap.put("DZBL", 0);
                //??????????????????
                if (null != body.get("CLDYM")) {
                    fymxMap.put("CLDYM", body.get("CLDYM") + "");
                }

                ImFeeFymx imFeeFymx = BeanUtil.fillBeanWithMapIgnoreCase(fymxMap, new ImFeeFymx(), true);
                Integer fymxjlxh = redisFactory.getTableKey(TableName.DB_NAME, TableName.IM_FEE_FYMX);
                imFeeFymx.setJlxh(fymxjlxh);
                imFeeFymx.setJfrq(ldtNow);
                imFeeFymx.setZfpb(FymxZfpbEnum.NORMAL.getValue());
                imFeeFymxDao.insert(imFeeFymx);
                String fygb = ObjectToTypes.parseString(body.get("FYGB"));
                //???????????????????????????????????????mdi???????????????
                if("4".equals(fygb) || "17".equals(fygb)) {
                	intList.add(fymxjlxh);
                }

                Map<String, Object> map = new HashMap<String, Object>(16);
                map.put("FYXH", fyxh);
                map.put("JLXH", fymxjlxh);
                returnData.add(map);
                String ksdm = saveDataList.get(0).get("FYKS").toString();
                boolean dmFlag = yplx != 0 && ("1034".equals(ksdm) || "1017".equals(ksdm) || "1103".equals(ksdm));
                if (dmFlag) {
                    tj02map.put("FYMXJLXH", map.get("JLXH"));
                    tj02mapList.add(tj02map);
                }
            }


            /************************??????????????????*********************************/
            boolean cfdbflag = false;//???????????????????????????
            for (Map<String, Object> body : saveDataList) {
                if (null != body.get("CLDYM") && !"".equals(body.get("CLDYM") + "")) {
                    cfdbflag = true;
                    break;
                }
            }

            boolean brxzFlag = 19 == brxz || 28 == brxz || 32 == brxz || 33 == brxz || 34 == brxz ||
                    35 == brxz || 36 == brxz || 39 == brxz || 4001 == brxz || 6023 == brxz || 45 == brxz;
            if (brxzFlag) {
                if (cfdbflag) {
                    //????????????????????????????????????
                    DecimalFormat df = new DecimalFormat("#.00");
                    String fzbh = "";
                    double fzxj = 0;
                    ZyYbxmfzCl zyYbxmfzCl = new ZyYbxmfzCl();
                    zyYbxmfzCl.setSortColumns("FZBH");

                    List<ZyYbxmfzCl> zyYbxmfzClList = zyYbxmfzClDao.findByEntity(zyYbxmfzCl);
                    if (CollectionUtils.isNotEmpty(zyYbxmfzClList)) {
                        for (int i = 0; i < zyYbxmfzClList.size(); i++) {
                            ZyYbxmfzCl zyYbxmfzClEntity = zyYbxmfzClList.get(i);
                            fzbh = zyYbxmfzClEntity.getFzbh();
                            fzxj = ObjectToTypes.parseDouble(zyYbxmfzClEntity.getFzxj());

                            List<Map<String, Object>> cldbList = imFeeFymxDao.queryFymxByCldymAndZyh(zyhcldb, fzbh);
                            double cldbfy = 0;

                            for (int k = 0; k < cldbList.size(); k++) {
                                double zjje = Double.valueOf(cldbList.get(k).get("ZJJE") + "");
                                cldbfy += zjje;
                            }
                            if (cldbfy > fzxj) {
                                for (int j = 0; j < cldbList.size(); j++) {
                                    Map<String, Object> cldbMap = cldbList.get(j);
                                    String jlxh = cldbMap.get("JLXH") + "";
                                    double zjje = Double.valueOf(cldbMap.get("ZJJE") + "");
                                    double zfblcs = Double.valueOf(cldbMap.get("ZFBL") + "");
                                    double zfjecs = Double.valueOf(cldbMap.get("ZFJE") + "");
                                    logger.info("85508550:??????????????? ??????ZFJE = " + zfjecs + "ZFBL = " + zfblcs);
                                    double zfblcldb = BUHISUtil.getDouble((cldbfy - fzxj) / cldbfy, 3);

                                    double zfjecldb = Math.ceil((((cldbfy - fzxj) / cldbfy) * zjje) * 100) / 100;

                                    ImFeeFymx imFeeFymx = new ImFeeFymx();
                                    imFeeFymx.setJlxh(ObjectToTypes.parseInt(jlxh));
                                    imFeeFymx.setZfbl(BigDecimal.valueOf(zfblcldb));
                                    imFeeFymx.setZfje(BigDecimal.valueOf(zfjecldb));
                                    logger.info("85508550:??????????????? ??????ZFJE = " + zfjecldb + "ZFBL = " + zfblcldb);
                                }

                            }
                        }
                    }
                }
            }
            /*********************************************************/
            //????????????????????? NIS_TJ02 NIS_TJ01
            if (tj02mapList.size() > 0) {
                Map<String, Object> tj01map = new HashMap<String, Object>(16);
                tj01map.put("TJYF", 2L);
                tj01map.put("YZLX", 1);
                tj01map.put("TJSJ", DateUtil.date().toTimestamp());
                tj01map.put("TJBQ", Long.parseLong(saveDataList.get(0).get("FYKS") + ""));
                tj01map.put("TJGH", userId);
                tj01map.put("XMLX", 1);
                tj01map.put("FYBZ", 0);
                tj01map.put("FYFS", 1);
                tj01map.put("JGID", jgid);
                NisTj01 nisTj01 = BeanUtil.fillBeanWithMapIgnoreCase(tj01map, new NisTj01(), true);
                Integer tjxh = redisFactory.getTableKey(TableName.DB_NAME, TableName.NIS_TJ01);
                nisTj01.setTjxh(tjxh);
                nisTj01Dao.insert(nisTj01);
                for (Map<String, Object> map : tj02mapList) {
                    map.put("TJXH", tjxh);
                    NisTj02 nisTj02 = BeanUtil.fillBeanWithMapIgnoreCase(tj01map, new NisTj02(), true);
                    Integer tj02jlxh = redisFactory.getTableKey(TableName.DB_NAME, TableName.NIS_TJ02);
                    nisTj02.setJlxh(tj02jlxh);
                    nisTj02Dao.insert(nisTj02);
                }
            }
            
//            if(!intList.isEmpty()) {
//            	try {
//            		mdiHrpService.hiscubebzejkfpush(intList, 1);
//				} catch (Exception e) {
//					logger.error(e.getMessage(), e);
//				}
//
//            }

            return BUHISUtil.ListToResultSet(returnData, new CisHzyzCostResp());
    }


    /**
     * ???????????????
     *
     * @author ?????????
     */
    @Transactional(rollbackFor = Exception.class)
    @Locked(value = {"saveAccountMoney_#[imFeeFymxVoidReq.zyh]"})
    public List<Integer> doUpdateJzzf(ImFeeFymxVoidReq imFeeFymxVoidReq, SysUser user) {
        Timestamp now = DateUtils.getNow();
        //?????????????????????????????????????????????
        Integer userId = user.getUserId();
        ImFeeFymxVoidReq.BrxxBody brxxbody = imFeeFymxVoidReq.getBrxxbody();
        Map<String, Object> body = BUHISUtil.caseInsensitiveMap(imFeeFymxVoidReq);
        Integer zyh = ObjectToTypes.parseInt(body.get("ZYH"));
        Integer fyxh = ObjectToTypes.parseInt(body.get("FYXH"));
        Map<String, Object> brxxMap = BUHISUtil.caseInsensitiveMap(brxxbody);
        Integer brxz = ObjectToTypes.parseInt(brxxMap.get("BRXZ"));
        Integer jlxh = ObjectToTypes.parseInt(body.get("JLXH"));

        //?????????????????????
        ImFeeFymx oldRecord = new ImFeeFymx();
        oldRecord.setZyh(zyh);
        oldRecord.setJscs(0);
        oldRecord.setFyxh(fyxh);
        oldRecord.setJlxh(jlxh);
        oldRecord.setZfpb(FymxZfpbEnum.NORMAL.getValue());
        List<ImFeeFymx> imFeeFymxList = imFeeFymxDao.findByEntity(oldRecord);
        if (CollectionUtils.isEmpty(imFeeFymxList)) {
            throw BaseException.create("ERROR_NURSEWORK_ZYBRRY_00029");
        }
        oldRecord = imFeeFymxList.get(0);

        //??????????????????
        if (null != body.get("CLDYM") && !"".equals(body.get("CLDYM") + "")) {
            oldRecord.setCldym(String.valueOf(body.get("CLDYM")));
        }

        //??????????????????????????????????????????
        oldRecord.setZfgh(ObjectToTypes.parseString(userId));
        oldRecord.setZfpb(FymxZfpbEnum.CANCELED.getValue());
        oldRecord.setZfrq(now);
        imFeeFymxDao.invalidExpenseBookkeeping(oldRecord);

        //??????????????????
        ImHzry imHzry = imHzryDao.getById(oldRecord.getZyh());
        ImZyjs lastJs = zyjsPreService.getLastImZyjs(oldRecord.getZyh());//????????????????????????

        if (lastJs == null && oldRecord.getFyrq().before(imHzry.getRyrq())) {//??????????????????????????????
            throw BaseException.create("ERROR_NURSEWORK_ZYBRRY_00051", new String[]{imHzry.getRyrq().toString()});
        } else if (lastJs != null && oldRecord.getFyrq().before(lastJs.getZzrq())) {//??????????????????????????????????????????
            throw BaseException.create("ERROR_NURSEWORK_ZYBRRY_00052", new String[]{lastJs.getZzrq().toString()});
        }

        if (imHzry.getCyrq() != null && oldRecord.getFyrq().after(imHzry.getCyrq())) {//??????????????????????????????
            throw BaseException.create("ERROR_NURSEWORK_ZYBRRY_00053", new String[]{imHzry.getCyrq().toString()});
        }


        //????????????????????????
        Double fysl = oldRecord.getFysl().negate().doubleValue();
        Double fyslCount = preService.getFyslCount(zyh, oldRecord.getYplx(), fyxh);
        if (fyslCount + fysl < 0) {
            throw BaseException.create("ERROR_NURSEWORK_ZYBRRY_00030", new String[]{String.valueOf(fyslCount)});
        }

        //??????????????????????????????
        if (oldRecord.getYplx() == FymxYplxEnum.NOT_MEDICINE.getValue()) {
            FeeYlsfxmOutResp feeYlsfxmOutResp = feeYlsfxmOutSer.getById(fyxh);
            if (FeeYlsfxmXmlxEnum.MATERIALS.equals(feeYlsfxmOutResp.getXmlx())) {
                    List<HighQualityConsumablesResp> list = mdiVejkfBatchViewService.queryHighQualityConsumables(Arrays.asList(new String[]{imHzry.getBrbq() + "-" + feeYlsfxmOutResp.getFyxh()}));
                if (CollectionUtil.isEmpty(list) || StrUtil.isBlank(list.get(0).getAmount())) {
                    throw BaseException.create("ERROR_NURSEWORK_ZYBRRY_00056", new String[]{oldRecord.getFymc()});
                }
                Double amount = Double.parseDouble(list.get(0).getAmount());
                if (amount < fysl) {
                    throw BaseException.create("ERROR_NURSEWORK_ZYBRRY_00057", new String[]{oldRecord.getFymc() + "[" + amount + "]"});
                }
            }
        }

        //?????????????????????
        Integer negateJlxh = preService.addNewNegateRecord(oldRecord, userId);
        List<Integer> intList = new ArrayList<Integer>();
        FeeYlsfxmOutResp resp = feeYlsfxmOutSer.getById(fyxh);
        if (resp != null && resp.getFygb() != null && (resp.getFygb() == 4
                || resp.getFygb() == 17)) {
            intList.add(negateJlxh);
        }

        /************************??????????????????*********************************/
        //???????????????????????????
        boolean cfdbflag = false;
        if (null != body.get("CLDYM") && !"".equals(body.get("CLDYM") + "")) {
            cfdbflag = true;

        }

        boolean brxzFlag = 19 == brxz || 28 == brxz || 32 == brxz || 33 == brxz || 34 == brxz ||
                35 == brxz || 36 == brxz || 39 == brxz || 4001 == brxz || 6023 == brxz || 45 == brxz;
        if (brxzFlag) {
            if (cfdbflag) {
                DecimalFormat df = new DecimalFormat("#.00");// ????????????????????????????????????
                String fzbh = "";
                double fzxj = 0;

                ZyYbxmfzCl zyYbxmfzCl = new ZyYbxmfzCl();
                zyYbxmfzCl.setSortColumns("FZBH");

                List<ZyYbxmfzCl> zyYbxmfzClList = zyYbxmfzClDao.findByEntity(zyYbxmfzCl);

                if (CollectionUtils.isNotEmpty(zyYbxmfzClList)) {
                    for (int i = 0; i < zyYbxmfzClList.size(); i++) {
                        ZyYbxmfzCl zyYbxmfzClEntity = zyYbxmfzClList.get(i);
                        fzbh = zyYbxmfzClEntity.getFzbh();
                        fzxj = ObjectToTypes.parseDouble(zyYbxmfzClEntity.getFzxj());

                        List<Map<String, Object>> cldbList = imFeeFymxDao.queryFymxByCldymAndZyh(zyh, fzbh);
                        double cldbfy = 0;

                        for (int k = 0; k < cldbList.size(); k++) {
                            double zjje = Double.valueOf(cldbList.get(k).get("ZJJE") + "");
                            cldbfy += zjje;
                        }
                        if (cldbfy > fzxj) {
                            for (int j = 0; j < cldbList.size(); j++) {
                                Map<String, Object> cldbMap = cldbList.get(j);
                                String jlxhcypb = cldbMap.get("JLXH") + "";
                                double zjje = Double.valueOf(cldbMap.get("ZJJE") + "");
                                double zfblcs = Double.valueOf(cldbMap.get("ZFBL") + "");
                                double zfjecs = Double.valueOf(cldbMap.get("ZFJE") + "");
                                logger.info("85508550:??????????????? ??????ZFJE = " + zfjecs + "ZFBL = " + zfblcs);
                                double zfblcldb = BUHISUtil.getDouble((cldbfy - fzxj) / cldbfy, 3);
                                /***********/
                                double zfjecldb = Math.ceil((((cldbfy - fzxj) / cldbfy) * zjje) * 100) / 100;   //
                                /********/
                                ImFeeFymx imfeefymx = new ImFeeFymx();
                                imfeefymx.setJlxh(ObjectToTypes.parseInt(jlxhcypb));
                                imfeefymx.setZfbl(BigDecimal.valueOf(zfblcldb));
                                imfeefymx.setZfje(BigDecimal.valueOf(zfjecldb));
                                logger.info("85508550:??????????????? ??????ZFJE = " + zfjecldb + "ZFBL = " + zfblcldb);
                            }
                        } else {
                            for (int j = 0; j < cldbList.size(); j++) {
                                Map<String, Object> cldbMap = cldbList.get(j);
                                String jlxhcypb = cldbMap.get("JLXH") + "";
                                String fyxhcypb = cldbMap.get("FYXH") + "";
                                String fyxmcypb = cldbMap.get("FYXM") + "";

                                double zjje = Double.valueOf(cldbMap.get("ZJJE") + "");
                                double zfblcs = Double.valueOf(cldbMap.get("ZFBL") + "");
                                double zfjecs = Double.valueOf(cldbMap.get("ZFJE") + "");
                                logger.info("85508550:??????????????? ??????ZFJE = " + zfjecs + "ZFBL = " + zfblcs);

                                Map<String, Object> zfblmp = new HashMap<String, Object>(16);
                                zfblmp.put("BRXZ", brxz);
                                zfblmp.put("FYXH", fyxhcypb);
                                zfblmp.put("FYGB", fyxmcypb);
                                zfblmp.put("TYPE", 0);
                                zfblmp = cisHzyzSer.getPayProportion(zfblmp);
                                double zfblcldb = BUHISUtil.getDouble((zfblmp.get("ZFBL") + ""), 3);
                                /***********/
                                double zfjecldb = Double.valueOf(df.format(
                                        (ObjectToTypes.parseDouble(zfblmp.get("ZFBL") + "")) * zjje));
                                /********/
                                ImFeeFymx imfeefymx = new ImFeeFymx();
                                imfeefymx.setJlxh(ObjectToTypes.parseInt(jlxhcypb));
                                imfeefymx.setZfbl(BigDecimal.valueOf(zfblcldb));
                                imfeefymx.setZfje(BigDecimal.valueOf(zfjecldb));
                                logger.info("85508550:??????????????? ??????ZFJE = " + zfjecldb + "ZFBL = " + zfblcldb);
                            }
                        }
                    }
                }
            }
        }
        //????????????????????????????????????????????????????????????????????????
//        if(!intList.isEmpty()) {
//        	try {
//        		mdiHrpService.hiscubebzejkfpush(intList, 1);
//			} catch (Exception e) {
//				logger.error(e.getMessage(), e);
//			}
//        }
        return intList;
    }

    /**
     * ??????????????????????????????
     *
     * @param @param  yzbz
     * @param @param  zyhm
     * @param @param  user
     * @param @return ????????????
     * @return List<CisHzyzReviewInfoResp>    ????????????
     * @throws
     * @Title: doQueryDoctorReviewList
     * @Description: TODO(?????????????????????????????????????????????)
     * @author ?????????
     */
    public List<CisHzyzReviewInfoResp> doQueryDoctorReviewList(CisHzyzReviewBatchReq req) {
        List<CisHzyzReviewInfoResp> cisHzyzReviewInfoRespList = null;
        Integer yzbz = req.getYzbz();
        if (yzbz == 1) {
        	if(req.getLsyz() == 2) {
        		req.setLsyz(null);
        	}
        	cisHzyzReviewInfoRespList = cisHzyzDao.queryBatchReviewOpen(req);
        } else {
        	 if(req.getLsyz() == 1) {
        		 return new ArrayList<CisHzyzReviewInfoResp>();
        	 }
        	 if(req.getLsyz() == 2) {
             	req.setLsyz(0);
             }
             cisHzyzReviewInfoRespList = cisHzyzDao.queryBatchReviewStop(req);
        }
        return cisHzyzReviewInfoRespList;
    }

    /**
     * ???????????????????????????
     *
     * @param @param  yzbz
     * @param @param  brch
     * @param @param  user
     * @param @param  type
     * @param @return ????????????
     * @return Boolean    ????????????
     * @throws
     * @Title: doSaveBatchReview
     * @Description: TODO(?????????????????????????????????????????????)
     * @author ?????????
     */
    @Transactional(rollbackFor = Exception.class)
    public CisHzyzReviewBatchResp doSaveBatchReview(CisHzyzReviewSaveReq req, SysUser user) {
    	CisHzyzReviewBatchResp cisHzyzReviewBatchResp = new CisHzyzReviewBatchResp();
        boolean tsbz = false;
        Integer jgid = user.getHospitalId();
        Integer uid = user.getUserId();
        SysXtcs sysXtcs = sysXtcsCacheSer.getByJGIdAndCsmc(jgid, SysXtcsCsmcCts.YZLRFHTYGH);
        String isfhsrSame = sysXtcs == null ? "1" : sysXtcs.getCsz();
        Integer yzbz = req.getYzbz();
        if(req.getLsyz() == 2) {
        	req.setLsyz(null);
        }
        List<Map<String, Object>> list = null;
        List<Integer> jlxhList = new ArrayList<Integer>();
        List<BatchBody> batchList = req.getBatchBodyList();
        if(req.isCheckAll()) {
        	 if (yzbz == 1) {
                 list = cisHzyzDao.queryBeforeBatchReviewOpen(jgid, req.getSrks(), req.getLsyz(), req.getZyhm());
             } else {
                 list = cisHzyzDao.queryBeforeBatchReviewStop(jgid, req.getSrks(), req.getLsyz(), req.getZyhm());
             }
        	 batchList = list.stream().map(o -> new BatchBody(ObjectToTypes.parseInt(o.get("JLXH")), 
        			 ObjectToTypes.parseInt(o.get("SFZT")), ObjectToTypes.parseString(o.get("CZGH")))).collect(Collectors.toList());
        }
        
        jlxhList = batchList.stream().filter(o -> o.getZtbz() == 0).map(e -> e.getJlxh()).collect(Collectors.toList());
      
        //?????????????????????????????????
        if (yzbz == 1) {
        	if(!jlxhList.isEmpty()) {
        		List<ZlRwfpUpdateReq> yzjlxhList = cisHzyzDao.queryTreatmentProjectByJlxh(jgid, jlxhList, null, 0);
    	        if (CollectionUtils.isNotEmpty(yzjlxhList)) {
    	            cisHzyzDao.updateTreatmentProjectByJlxh(yzjlxhList.stream().map(ZlRwfpUpdateReq::getYzJlxh).collect(Collectors.toList()));
    	            zlRwfpService.add(yzjlxhList);
    	        }
        	} 
        }
  
        //???????????????????????????????????????(?????????????????????)
        List<Integer> listInt = new ArrayList<Integer>();

        if(!jlxhList.isEmpty()) {
             //???????????????????????????????????????????????????
             List<Map<String, Object>> kjywlistMap = cisHzyzDao.queryNotApprovedKjywBatch(jgid, req.getSrks(), jlxhList);
             if (CollectionUtils.isNotEmpty(kjywlistMap)) {
                 StringBuffer str = new StringBuffer();
                 ReturnEntity<String> returnEntity = new ReturnEntity<String>();
                 for (Map<String, Object> map : kjywlistMap) {
                     listInt.add(ObjectToTypes.parseInt(map.get("YZZH")));
                     str.append(ObjectToTypes.parseString(map.get("YZMC"))).append(",");
                 }
                 if (str.length() > 0) {
                     str.deleteCharAt(str.length() - 1);
                     returnEntity.setError("ERROR_NURSEWORK_ZYBRRY_00047", new String[]{
                             str.toString()
                     });
                     cisHzyzReviewBatchResp.setYwwarningMessage(returnEntity.getMessage());
                 }
             }
        }

        if (CollectionUtils.isNotEmpty(batchList)) {
            for (BatchBody body : batchList) {
                //??????type???2?????????????????????????????? ?????? type???1????????????????????????
                    //0:????????????????????? 1?????????????????????
                if (!"1".equals(isfhsrSame) && uid.equals(body.getCzgh())) {
                    tsbz = true;
                } else {
                    CisHzyz cisHzyz = new CisHzyz();
                    cisHzyz.setFhgh(ObjectToTypes.parseString(uid));
                    cisHzyz.setJgid(jgid);
                    cisHzyz.setFhsj(DateUtil.date().toTimestamp());
                    cisHzyz.setJlxh(body.getJlxh());
                    if (body.getZtbz() == 0) {
                        if (yzbz == 1) {
                            cisHzyzDao.updateReviewNotExtisSkinTest(cisHzyz, listInt);
                        } else if (yzbz == 2) {
                            cisHzyzDao.updateBqyzStopOrderReview(cisHzyz);
                        }
                    } else {
                        if (yzbz == 1) {
                            cisHzyzZtDao.updateZtReviewStatusByOther(cisHzyz);
                            cisHzyz.setJlxh(null);
                            cisHzyz.setZtyzjlxh(body.getJlxh());
                            cisHzyzDao.updateReviewStatusByOther(cisHzyz);
                        } else if (yzbz == 2) {
                            cisHzyzZtDao.updateBqyzZtStopOrderReview(cisHzyz);
                            cisHzyz.setJlxh(null);
                            cisHzyz.setZtyzjlxh(body.getJlxh());
                            cisHzyzDao.updateBqyzStopOrderReview(cisHzyz);
                        }
                    }
                }
            }
        }
        cisHzyzReviewBatchResp.setTsbz(tsbz);
        return cisHzyzReviewBatchResp;
    }


    /**
     * ????????????????????????
     *
     * @param @param  cisHzyzFjReq
     * @param @param  user
     * @param @return ????????????
     * @return List<DiccLdxmglResp>    ????????????
     * @throws
     * @Title: doLoadAppendAdviceTysq
     * @Description: TODO(?????????????????????????????????????????????)
     * @author ?????????
     */
    public List<DiccLdxmglApiResp> doLoadAppendAdviceTysq(
            CisHzyzFjReq cisHzyzFjReq, SysUser user) {
        Integer manageUnit = user.getHospitalId();
        List<FjBody> fjBodyList = cisHzyzFjReq.getFjBodyList();
        for (int i = 0; i < fjBodyList.size(); i++) {
            FjBody fjBody = fjBodyList.get(i);
            Integer ksdm = fjBody.getKsdm();
            Integer yzxh = fjBody.getYzxh();
            Map<String, Object> ypyfMap = cisHzyzDao.queryDrugUsage(yzxh);
            if (ypyfMap == null) {
                return null;
            }
            if (ypyfMap.get("FYXH") == null) {
                return null;
            }
            Integer fyxh = ObjectToTypes.parseInt(ypyfMap.get("FYXH"));
            Map<String, Object> params = new HashMap<String, Object>(16);
            params.put("JGID", manageUnit);
            params.put("XMXH", fyxh);
            params.put("KSDM", ksdm);
            String mzsy = fjBody.getMzsy();
            String sylb = "b.ZYSY";
            if (!"".equals(mzsy) && "1".equals(mzsy)) {
                sylb = "b.MZSY";
            }
            params.put("SYLB", sylb);
            List<DiccLdxmglApiResp> list = diccLdxmglService.queryProjectAssociationInfo(params);
            if (CollectionUtils.isNotEmpty(list)) {
                throw BaseException.create("ERROR_NURSEWORK_ZYBRRY_00043");
            }
        }
        return null;
    }

    /**
     * ??????????????????????????????
     *
     * @param @param nisFyyfSaveReq
     * @param @param user    ????????????
     * @return void    ????????????
     * @throws
     * @Title: doSaveCommit
     * @Description: TODO(?????????????????????????????????????????????)
     * @author ?????????
     */
    public void doSaveCommit(NisFyyfSaveReq nisFyyfSaveReq, SysUser user) {
        Integer jgid = user.getHospitalId();
        int gnfl = 4;
        List<NisFyyfSaveBody> nisFyyfSaveBodyList = nisFyyfSaveReq.getNisFyyfSaveBodyList();
        for (int i = 0; i < nisFyyfSaveBodyList.size(); i++) {
            NisFyyfSaveBody nisFyyfSaveBody = nisFyyfSaveBodyList.get(i);
            if (StrUtil.isEmpty(nisFyyfSaveBody.getYfsb())) {
                throw BaseException.create("ERROR_NURSEWORK_ZYBRRY_00034");
            }
            if (StrUtil.isEmpty(nisFyyfSaveBody.getBqdm())) {
                throw BaseException.create("ERROR_NURSEWORK_ZYBRRY_00035");
            }
            if (StrUtil.isEmpty(ObjectToTypes.parseString(nisFyyfSaveBody.getZxpb()))) {
                throw BaseException.create("ERROR_NURSEWORK_ZYBRRY_00036");
            }
            if (StrUtil.isEmpty(ObjectToTypes.parseString(nisFyyfSaveBody.getType()))) {
                throw BaseException.create("ERROR_NURSEWORK_ZYBRRY_00037");
            }
            if (StrUtil.isEmpty(nisFyyfSaveBody.getDmsb())) {
                throw BaseException.create("ERROR_NURSEWORK_ZYBRRY_00038");
            }
            nisFyyfSaveBody.setJgid(jgid);
            nisFyyfSaveBody.setGnfl(gnfl);
            if (StrUtil.isEmpty(ObjectToTypes.parseString(nisFyyfSaveBody.getJlxh()))) {
                nisFyyfSaveBody.setJlxh(redisFactory.getTableKey(TableName.DB_NAME, TableName.NIS_FYYF));
                nisFyyfDao.insert(nisFyyfSaveBody);
            } else {
                nisFyyfDao.update(nisFyyfSaveBody);
            }

        }
    }

    /**
     * ??????????????????????????????
     *
     * @param @param jlxh
     * @param @param zxpb    ????????????
     * @return void    ????????????
     * @throws
     * @Title: doUpdateStage
     * @Description: TODO(?????????????????????????????????????????????)
     * @author ?????????
     */
    public void doUpdateStage(Integer jlxh, Integer zxpb) {
        NisFyyf nisFyyf = nisFyyfDao.getById(ObjectToTypes.parseLong(jlxh));
        nisFyyf.setZxpb(zxpb);
        nisFyyfDao.update(nisFyyf);
    }


    /**
     * ??????????????????
     *
     * @param @param body
     * @param @param user    ????????????
     * @return void    ????????????
     * @throws
     * @Title: doSaveRefundClinic
     * @Description: TODO(?????????????????????????????????????????????)
     * @author ?????????
     */
    public List<Integer> doSaveRefundClinic(List<ImFeeFymxQueryReq> body, SysUser user) {
    	List<Integer> list = new ArrayList<Integer>();
        if (body != null && body.size() > 0) {
            Integer zyh = body.get(0).getZyh();
            ImHzry imHzry = imHzryDao.getById(zyh);
            ImZyjs lastJs = zyjsPreService.getLastImZyjs(zyh);//????????????????????????

            Timestamp now = DateUtils.getNow();

            for (ImFeeFymxQueryReq imFeeFymxQueryReq : body) {
                // ???FYXH FYDJ FYRQ?????????????????????
                List<ImFeeFymx> fymxList = imFeeFymxDao.queryExecuteRefundableProject(imFeeFymxQueryReq);
                if (CollectionUtils.isNotEmpty(fymxList)) {
                    ImFeeFymx imFeeFymx = fymxList.get(0);// ??????????????????????????????????????????????????????
                    Timestamp fyrq = imFeeFymx.getFyrq();// ????????????
                    BigDecimal fyslTotal = fymxList.stream().map(ImFeeFymx::getFysl).reduce(BigDecimal::add).get();// ??????????????????

                    if (lastJs == null && fyrq.before(imHzry.getRyrq())) {//??????????????????????????????
                        throw BaseException.create("ERROR_NURSEWORK_ZYBRRY_00051", new String[]{imHzry.getRyrq().toString()});
                    }
                    if (lastJs != null && fyrq.before(lastJs.getZzrq())) {//??????????????????????????????????????????
                        throw BaseException.create("ERROR_NURSEWORK_ZYBRRY_00052", new String[]{lastJs.getZzrq().toString()});
                    }
                    if (imHzry.getCyrq() != null && fyrq.after(imHzry.getCyrq())) {//??????????????????????????????
                        throw BaseException.create("ERROR_NURSEWORK_ZYBRRY_00053", new String[]{imHzry.getCyrq().toString()});
                    }

                    if (fyslTotal.compareTo(imFeeFymxQueryReq.getFysl()) < 0) {
                        throw BaseException.create("ERROR_NURSEWORK_ZYBRRY_00030", new String[]{String.valueOf(fyslTotal)});
                    }

                    Integer jlxh = redisFactory.getTableKey(TableName.DB_NAME, TableName.IM_FEE_FYMX);
                    imFeeFymx.setJlxh(jlxh);
                    imFeeFymx.setFysl(imFeeFymxQueryReq.getFysl().multiply(new BigDecimal(-1)));
                    imFeeFymx.setZjje(imFeeFymx.getFysl().multiply(imFeeFymx.getFydj()));
                    imFeeFymx.setZfje(imFeeFymx.getZjje().multiply(imFeeFymx.getZfbl()));
                    imFeeFymx.setZlje(imFeeFymx.getZjje().multiply(imFeeFymx.getZfbl()));
                    imFeeFymx.setJfrq(now);
                    imFeeFymx.setSrgh(ObjectToTypes.parseString(user.getUserId()));
                    imFeeFymxDao.insert(imFeeFymx);
                    FeeYlsfxmOutResp resp = feeYlsfxmOutSer.getById(imFeeFymx.getFyxh());
                    if(resp != null && resp.getFygb() != null && (
                    		resp.getFygb() == 4 || resp.getFygb() == 17)) {
                    	list.add(jlxh);
                    }
                }
            }
        }
        //???????????????????????????????????????????????????
//        if(!list.isEmpty()) {
//        	try {
//				mdiHrpService.hiscubebzejkfpush(list, 1);
//			} catch (Exception e) {
//				logger.error(e.getMessage(), e);
//			}
//        }
        return list;
    }

    /**
     * ???????????????????????????????????????
     *
     * @param @param  ztbh
     * @param @param  brxz
     * @param @return ????????????
     * @return List<OpZt02DetailResp>    ????????????
     * @throws
     * @Title: doLoadDetailByZtBH
     * @Description: TODO(?????????????????????????????????????????????)
     * @author ?????????
     */
    public List<OpZt02DetailResp> doLoadDetailByZtBH(Integer ztbh, Integer brxz) {
        List<OpZt02DetailResp> list = opZt02Service.queryDetailByZtbh(ztbh);
        if (CollectionUtils.isNotEmpty(list)) {
            for (OpZt02DetailResp fyxxMap : list) {
                // ????????????????????????
                //===============????????????=========================
                Integer ypxh = fyxxMap.getFyxh();// ????????????
                Integer fygb = cisHzyzSer.getfygb(0, ypxh);// ??????????????????
                Map<String, Object> params = new HashMap<String, Object>();
                params.put("BRXZ", brxz);// ????????????
                params.put("FYGB", fygb);// ????????????
                params.put("TYPE", 0);// ???????????? 0=??????
                params.put("FYXH", ypxh);// ????????????
                //=========================??????????????????????????????????????????????????????=============================
                Map<String, Object> zfblMap = cisHzyzSer.getPayProportion(params);
                BigDecimal zfbl = ObjectToTypes.parseBigDecimal(zfblMap.get("ZFBL"));
                fyxxMap.setZfbl(zfbl);
            }
        }
        return list;
    }

    /**
     * ??????????????????????????????
     *
     * @param @param  listMap
     * @param @param  user
     * @param @return ????????????
     * @return Map<String, Object>    ????????????
     * @throws
     * @Title: getCostAccountingDetailParameters
     * @Description: TODO(?????????????????????????????????????????????)
     * @author ?????????
     */
    public Map<String, Object> getCostAccountingDetailParameters(List<Map<String, Object>> listMap, SysUser user) {
        Map<String, Object> responseMap = new HashMap<String, Object>(16);
        double total = 0;
        double zfje = 0;
        for (int i = 0; i < listMap.size(); i++) {
            Map<String, Object> map = listMap.get(i);
            total += ObjectToTypes.parseDouble(map.get("ZJJE"));
            zfje += (ObjectToTypes.parseDouble(map.get("ZJJE")) * ObjectToTypes.parseDouble(listMap.get(i).get("ZFBL")));
        }
        responseMap.put("zj", BUHISUtil.getDouble(total, 2) + "");
        responseMap.put("zf", BUHISUtil.getDouble(zfje, 2) + "");
        return responseMap;
    }

    /**
     * ????????????
     *
     * @param @param  al_yplx
     * @param @param  al_brxz
     * @param @param  al_fyxh
     * @param @param  al_fygb
     * @param @param  ad_fydj
     * @param @param  ad_fysl
     * @param @return ????????????
     * @return Map<String, Object>    ????????????
     * @throws
     * @Title: getPriceJe(???getje)
     * @Description: TODO(?????????????????????????????????????????????)
     * @author ?????????
     */
    public Map<String, Object> getPriceJe(Integer al_yplx, Integer al_brxz, Integer al_fyxh, Integer al_fygb, double ad_fydj,
                                          double ad_fysl) {
        double ld_zfbl = 1;
        boolean adc_zfbl = false;
        double ld_cxbl = 1;
        double ld_zfje = 0;
        double ld_zlje = 0;
        double ld_fyxe = 0;
        Map<String, Object> parameters = new HashMap<String, Object>(16);
        parameters.put("BRXZ", al_brxz);
        try {
            if (al_yplx == 0) {
                PubFyxzApiReq pubFyxz = new PubFyxzApiReq();
                pubFyxz.setBrxz(al_brxz);
                pubFyxz.setFyxh(al_fyxh);
                // ????????????????????????
                Map<String, Object> pubfyxzMap = pubFyxzService.getFyjyInfo(pubFyxz);
                if (pubfyxzMap != null) {
                    if (pubfyxzMap.get("FYXE") != null) {
                        ld_fyxe = Double.parseDouble(pubfyxzMap.get("FYXE") + "");
                    }
                    if (pubfyxzMap.get("CXBL") != null) {
                        ld_cxbl = Double.parseDouble(pubfyxzMap.get("CXBL") + "");
                    }
                    if (pubfyxzMap.get("ZFBL") != null) {
                        ld_zfbl = Double.parseDouble(pubfyxzMap.get("ZFBL") + "");
                        adc_zfbl = true;
                    }
                }
                if (ld_fyxe > 0 && ad_fydj > ld_fyxe) {
                    ld_zfje = ad_fysl * ld_fyxe * ld_zfbl;
                    ld_zlje = ad_fysl * (ad_fydj - ld_fyxe) * ld_cxbl;
                    ld_zfje = ld_zfje + ld_zlje;
                } else {
                    ld_zfje = ad_fysl * ad_fydj * ld_zfbl;
                    ld_zlje = 0;
                }
            } else if (al_yplx == 1 || al_yplx == 2 || al_yplx == 3) {
                FeeYpxzApiReq feeYpxz = new FeeYpxzApiReq();
                feeYpxz.setBrxz(al_brxz);
                feeYpxz.setYpxh(al_fyxh);
                // ????????????????????????
                Map<String, Object> feeypxzMap = feeYpxzService.getYpjyInfo(feeYpxz);
                if (feeypxzMap != null) {
                    if (feeypxzMap.get("ZFBL") != null) {
                        ld_zfbl = Double.parseDouble(feeypxzMap.get("ZFBL") + "");
                        adc_zfbl = true;
                    }
                }
                ld_zfje = ad_fysl * ad_fydj * ld_zfbl;
                ld_zlje = 0;
            }
            if (!adc_zfbl) {
                al_fygb = cisHzyzSer.getfygb(al_yplx, al_fyxh);
                parameters.put("SFXM", al_fygb);

                List<Map<String, Object>> feesfdlzfblList = feeSfdlzfblOutSer.getZfblMap(parameters);
                if (CollectionUtils.isNotEmpty(feesfdlzfblList)) {
                    Map<String, Object> feesfdlzfblMap = feesfdlzfblList.get(0);
                    if (feesfdlzfblMap.get("ZFBL") != null) {
                        ld_zfbl = Double.parseDouble(feesfdlzfblMap.get("ZFBL") + "");
                    }
                }
                ld_zfje = ad_fysl * ad_fydj * ld_zfbl;
                ld_zlje = 0;
            }
            double ld_zjje = ad_fydj * ad_fysl;
            Map<String, Object> getjeMap = new HashMap<String, Object>(16);
            getjeMap.put("ZFBL", ld_zfbl);
            getjeMap.put("ZFJE", ld_zfje);
            getjeMap.put("ZLJE", ld_zlje);
            getjeMap.put("ZJJE", ld_zjje);
            getjeMap.put("FYGB", al_fygb);
            return getjeMap;
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw BaseException.create("ERROR_NURSEWORK_ZYBRRY_00010");
        }
    }


    /**
     * ????????????????????????
     *
     * @param @param  list_FYMX
     * @param @param  listForputFYMX
     * @param @param  user
     * @param @param  brbq
     * @param @return
     * @param @throws ParseException    ????????????
     * @return Boolean    ????????????
     * @throws
     * @Title: insertFymx
     * @Description: TODO(?????????????????????????????????????????????)
     * @author ?????????
     */
    public Boolean insertFymx(List<Map<String, Object>> list_FYMX, List<Map<String, Object>> listForputFYMX,
                              SysUser user, Integer brbq) throws ParseException {
        Integer JGID = user.getHospitalId();
        Integer bq = 0;
        if (brbq != null) {
            // ????????????
            bq = brbq;
        } else {
            if (list_FYMX != null && list_FYMX.size() > 0) {
                bq = ObjectToTypes.parseInt(list_FYMX.get(0).get("FYBQ"));
            }
        }
        // ?????????????????????
        Integer yggh = user.getUserId();
        for (int i = 0; i < list_FYMX.size(); i++) {
            long ZXKS = 0;
            double ZFBL = 0;
            double ZFJE = 0;
            double ZLJE = 0;
            double DZBL = 0;
            double ZJJE = 0;
            if (list_FYMX.get(i).get("ZFBL") != null) {
                ZFBL = Double.parseDouble(list_FYMX.get(i).get("ZFBL") + "");
            }
            if (list_FYMX.get(i).get("ZFJE") != null) {
                ZFJE = Double.parseDouble(list_FYMX.get(i).get("ZFJE") + "");
            }
            if (list_FYMX.get(i).get("ZLJE") != null) {
                ZLJE = Double.parseDouble(list_FYMX.get(i).get("ZLJE") + "");
            }
            if (list_FYMX.get(i).get("DZBL") != null) {
                DZBL = Double.parseDouble(list_FYMX.get(i).get("DZBL") + "");
            }
            if (list_FYMX.get(i).get("ZJJE") != null) {
                ZJJE = Double.parseDouble(list_FYMX.get(i).get("ZJJE") + "");
            }
            long ZYH = Long.parseLong(list_FYMX.get(i).get("ZYH") + "");
            long FYKS = Long.parseLong(list_FYMX.get(i).get("FYKS") + "");
            Integer YPLX = ObjectToTypes.parseInt(list_FYMX.get(i).get("YPLX"));
            Integer BRXZ = ObjectToTypes.parseInt(list_FYMX.get(i).get("BRXZ"));
            double FYDJ = Double.parseDouble(list_FYMX.get(i).get("FYDJ") + "");
            double FYSL = Double.parseDouble(list_FYMX.get(i).get("FYSL") + "");
            double YCSL = Double.parseDouble(list_FYMX.get(i).get("YCSL") + "");
            String YSGH = list_FYMX.get(i).get("YSGH") + "";
            long YPCD = Long.parseLong(list_FYMX.get(i).get("YPCD") + "");
            // ????????????????????? ?????????????????????
            if (list_FYMX.get(i).get("ZXKS") == null || list_FYMX.get(i).get("ZXKS") == ""
                    || "null".equals(list_FYMX.get(i).get("ZXKS"))) {
                ZXKS = FYKS;
            } else {
                ZXKS = Long.parseLong(list_FYMX.get(i).get("ZXKS") + "");
            }
            // ??????????????????????????????
            Map<String, Object> parameters = new HashMap<String, Object>(16);
            parameters.put("ZYH", ZYH);
            parameters.put("JGID", JGID);
            // ???????????????????????????????????????Map
            Map<String, Object> imfeefymx_map = (Map<String, Object>) list_FYMX.get(i);
            // ???????????? YPLX_c ?????????????????????
            Integer YPLX_c = ObjectToTypes.parseInt(list_FYMX.get(i).get("YPLX"));
            Integer FYXH = ObjectToTypes.parseInt(list_FYMX.get(i).get("FYXH"));
            Integer FYXM = cisHzyzSer.getfygb(YPLX_c, FYXH);
            imfeefymx_map.put("FYXM", FYXM);
            // YPLX ???0????????????
            if (YPLX == 0) {
                if (FYSL < 0) {
                    imfeefymx_map.put("ZFBL", ZFBL);
                    imfeefymx_map.put("ZJJE", ZJJE);
                    imfeefymx_map.put("ZFJE", ZFJE);
                    imfeefymx_map.put("ZLJE", ZLJE);
                } else {
                    if (ZFJE > 0) {
                        imfeefymx_map.put("ZFBL", ZFBL);
                        imfeefymx_map.put("ZJJE", ZJJE);
                        imfeefymx_map.put("ZFJE", ZFJE);
                        imfeefymx_map.put("ZLJE", ZLJE);
                    } else {
                        Map<String, Object> FYXX = getPriceJe(YPLX, BRXZ, FYXH, FYXM, FYDJ, FYSL);
                        imfeefymx_map.put("ZFBL", FYXX.get("ZFBL"));
                        imfeefymx_map.put("ZJJE", FYXX.get("ZJJE"));
                        imfeefymx_map.put("ZFJE", FYXX.get("ZFJE"));
                        imfeefymx_map.put("ZLJE", FYXX.get("ZLJE"));
                    }
                }
            } else {
                // ??????????????????
                if (FYSL < 0) {
                    imfeefymx_map.put("ZFBL", ZFBL);
                    imfeefymx_map.put("ZJJE", ZJJE);
                    imfeefymx_map.put("ZFJE", ZFJE);
                    imfeefymx_map.put("ZLJE", ZLJE);
                } else {
                    if (ZFJE > 0) {
                        imfeefymx_map.put("ZFBL", ZFBL);
                        imfeefymx_map.put("ZJJE", ZJJE);
                        imfeefymx_map.put("ZFJE", ZFJE);
                        imfeefymx_map.put("ZLJE", ZLJE);
                        imfeefymx_map.put("FYXM", FYXM);
                    } else {
                        Map<String, Object> FYXX = getPriceJe(YPLX, BRXZ, FYXH, FYXM, FYDJ, FYSL);
                        imfeefymx_map.put("ZFBL", FYXX.get("ZFBL"));
                        imfeefymx_map.put("ZJJE", FYXX.get("ZJJE"));
                        imfeefymx_map.put("ZFJE", FYXX.get("ZFJE"));
                        imfeefymx_map.put("ZLJE", FYXX.get("ZLJE"));
                        imfeefymx_map.put("FYXM", FYXX.get("FYGB"));
                    }
                }
            }
            // ??????????????????????????????
            if (list_FYMX.get(i).get("FYRQ") == null || list_FYMX.get(i).get("FYRQ") == "") {
                imfeefymx_map.put("FYRQ", DateUtil.date().toTimestamp());
            } else {
                String FYRQ = list_FYMX.get(i).get("FYRQ") + "";
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Date date = list_FYMX.get(i).get("FYRQ") instanceof Date ? (Date) list_FYMX.get(i).get("FYRQ")
                        : sdf.parse(FYRQ);
                imfeefymx_map.put("FYRQ", DateUtil.date(date).toTimestamp());
            }
            // ??????????????????????????????
            if (list_FYMX.get(i).get("JFRQ") == null || list_FYMX.get(i).get("JFRQ") == "") {
                imfeefymx_map.put("JFRQ", DateUtil.date().toTimestamp());
            } else {
                String JFRQ = list_FYMX.get(i).get("JFRQ") + "";
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Date date = list_FYMX.get(i).get("JFRQ") instanceof Date ? (Date) list_FYMX.get(i).get("JFRQ")
                        : sdf.parse(JFRQ);
                imfeefymx_map.put("JFRQ", DateUtil.date(date).toTimestamp());
            }
            // ??????????????????
            if (YPLX == 0) {
                imfeefymx_map.put("YPCD", 0);
            } else {
                imfeefymx_map.put("YPCD", YPCD);
            }
            if (list_FYMX.get(i).get("JLXH") != null) {
                // ????????????
                imfeefymx_map.put("YZXH", Long.parseLong(list_FYMX.get(i).get("JLXH") + ""));
            }
            imfeefymx_map.put("ZYH", ZYH);// ?????????
            imfeefymx_map.put("FYXH", FYXH);// ????????????
            imfeefymx_map.put("FYMC", list_FYMX.get(i).get("FYMC"));// ????????????
            imfeefymx_map.put("FYSL", FYSL);// ????????????
            imfeefymx_map.put("FYDJ", FYDJ);// ????????????
            imfeefymx_map.put("QRGH", yggh);// ?????????????????????
            imfeefymx_map.put("FYKS", FYKS);// ???????????? long
            imfeefymx_map.put("ZXKS", ZXKS);// ???????????? long
            imfeefymx_map.put("XMLX", 1);// ????????????// int
            imfeefymx_map.put("YPLX", YPLX);// ????????????
            imfeefymx_map.put("YSGH", YSGH);// ????????????
            imfeefymx_map.put("FYBQ", bq);// ???????????? long
            imfeefymx_map.put("DZBL", DZBL);
            imfeefymx_map.put("JSCS", 0);
            imfeefymx_map.put("YEPB", 0);
            imfeefymx_map.put("JGID", JGID);
            imfeefymx_map.put("XMSL", YCSL);
            imfeefymx_map.put("JFRQ", DateUtil.date().toTimestamp());
            listForputFYMX.add(i, imfeefymx_map);
        }
        return true;
    }


    /**
     * ??????????????????
     *
     * @param @param  dicsypcList
     * @param @param  projectList
     * @param @param  user
     * @param @return ????????????
     * @return Boolean    ????????????
     * @throws
     * @Title: calculateOrderExecution(???uf_comp_yzzx)
     * @Description: TODO(?????????????????????????????????????????????)
     * @author ?????????
     */
    public Boolean calculateOrderExecution(List<Map<String, Object>> dicsypcList, List<Map<String, Object>> projectList,
                                           SysUser user) {
        try {
            SimpleDateFormat sdfdatetime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            SimpleDateFormat sdfdate = new SimpleDateFormat("yyyy-MM-dd");
            long ll_yzzh_old = 0;
            int fysx = 0;
            int ll_jfbz = 0;
            Date currentDate = null;
            /**
             * ??????????????????????????????????????????????????????????????????9???????????????????????????????????????????????????????????? 1?????????ll_yjxh?????? 2??????sqlString?????????,
             * min(YJXH) as LL_YJXH 3?????????ll_yjxh =
             * Long.parseLong(list_fysx.get(0).get("LL_YJXH") + "");?????? 4???????????????if (ll_jfbz ==
             * 1) { ?????????if (ll_jfbz == 1 || (ll_yjxh > 0 && ll_jfbz == 9)) {
             * 5???????????????LL_JFBZ??????????????????(?????????)
             **/
            long ll_yjxh = 0;
            for (int i = 0; i < projectList.size(); i++) {
                Map<String, Object> parameters_lsbz = new HashMap<String, Object>();
                Map<String, Object> parameters_up_lsbz = new HashMap<String, Object>();
                Map<String, Object> parameters_cq = new HashMap<String, Object>(); // ??????uf_cacl_zxcs_cq
                // ????????????

                String QRSJ = null;
                if (projectList.get(i).get("QRSJ") != null && projectList.get(i).get("QRSJ") != "") {
                    QRSJ = projectList.get(i).get("QRSJ") + "";// ????????????
                }
                String TZSJ = null;
                if (projectList.get(i).get("TZSJ") != null && projectList.get(i).get("TZSJ") != "") {
                    TZSJ = projectList.get(i).get("TZSJ") + "";// ???????????????
                }
                String KSSJ = null;
                if (projectList.get(i).get("KSSJ") != null && projectList.get(i).get("KSSJ") != "") {
                    KSSJ = projectList.get(i).get("KSSJ") + "";// ????????????
                }
                String YZZXSJ = projectList.get(i).get("YZZXSJ") + "";// ??????????????????
                String SYPC = projectList.get(i).get("SYPC") + "";// ????????????
                long YZXH = Long.parseLong(projectList.get(i).get("JLXH") + "");// ????????????
                int LSYZ = Integer.parseInt(projectList.get(i).get("LSYZ") + "");// ????????????//
                // 1,????????????
                // 0

                int SRCS = 0;
                if (projectList.get(i).get("SRCS") != null) {
                    SRCS = Integer.parseInt(projectList.get(i).get("SRCS") + "");// ????????????
                }
                // ??????????????????
                int FJBZ = 0;
                if (projectList.get(0).get("FJBZ") != null) {
                    // ????????????
                    FJBZ = Integer.parseInt(projectList.get(0).get("FJBZ") + "");
                }
                // ????????????
                int ZXCS_TOTAL = 0;

                parameters_lsbz.put("ldt_kssj", KSSJ);
                parameters_lsbz.put("ldt_qrsj", QRSJ);
                parameters_lsbz.put("ldt_tzsj", TZSJ);
                parameters_lsbz.put("ls_sypc", SYPC);
                parameters_lsbz.put("ls_yzzxsj_str", YZZXSJ);
                parameters_lsbz.put("ll_lsyz", LSYZ);
                parameters_lsbz.put("al_ypbz", 0);
                parameters_lsbz.put("SRCS", SRCS);
                parameters_lsbz.put("FJBZ", FJBZ);
                // ??????????????????
                int ll_lsbz = BUHISUtil.uf_cacl_lsbz(dicsypcList, parameters_lsbz);

                if (ll_lsbz == 1) {// ????????????????????????????????????,????????????????????????
                    parameters_up_lsbz.put("ll_yzxh", YZXH);
                    cisHzyzSer.yzUpdateLsbz(parameters_up_lsbz, user); // ??????????????????
                    projectList.get(i).put("FYCS", 0);
                    continue;
                }
                // ?????????????????????????????????
               
                if (FJBZ == 1) {
                	if(LSYZ == 0) {
                		parameters_lsbz.put("ldt_kssj", DateUtil.offset(DateUtil.parse(KSSJ), 
                				DateField.DAY_OF_MONTH, 1).toSqlDate() + " 00:00:00");
                	}
                    long ll_yzzh = Long.parseLong(projectList.get(i).get("YZZH") + "");
                    if (ll_yzzh_old != ll_yzzh) {
                        ll_yjxh = 0;
                        Map<String, Object> map_FY = new HashMap<String, Object>();
                        System.out.println(projectList.get(i).get("YZZH"));
                        map_FY.put("YZZH", Long.parseLong(projectList.get(i).get("YZZH") + ""));
                        // ???????????????????????????????????????????????????????????????
                        List<Map<String, Object>> list_fysx = cisHzyzDao.queryFjBqyzInfo(map_FY);
                        if(list_fysx != null) {
                            fysx = Integer.parseInt(list_fysx.get(0).get("LL_FYSX") + "");
                            ll_yjxh = Long.parseLong(list_fysx.get(0).get("LL_YJXH") + "");
                            if (list_fysx.get(0).get("LL_JFBZ") != null) {
                                ll_jfbz = Integer.parseInt(list_fysx.get(0).get("LL_JFBZ") + "");
                            }
                            if (list_fysx.get(0).get("LDT_QRSJ") != null) {
                                currentDate = sdfdatetime.parse(list_fysx.get(0).get("LDT_QRSJ") + "");
                            } else {
                                currentDate = null;
                            }
                        } else {
                            currentDate = null;
                        }
                    }
                    ll_yzzh_old = Long.parseLong(projectList.get(i).get("YZZH") + "");
                    if (fysx == 2) {
                        projectList.get(i).put("FYCS", 0);
                        continue;
                    } else {
                        if (ll_jfbz == 1 || (ll_yjxh > 0 && ll_jfbz == 9)) {
                            if (currentDate != null) {
                                if (TZSJ != null && BUHISUtil.toDate(TZSJ).getTime() > currentDate.getTime()) {
                                    TZSJ = sdfdatetime.format(currentDate);
                                }
                                parameters_lsbz.put("ldt_tzsj", TZSJ);
                            } else {
                                projectList.get(i).put("FYCS", 0);
                                continue;
                            }
                        }
                    }
                }
                // ????????????
                if (LSYZ == 0) {
                    ZXCS_TOTAL = BUHISUtil.uf_cacl_zxcs_cq(dicsypcList, parameters_lsbz, parameters_cq);
                    double al_zxcs = 0;
                    if (parameters_cq.get("al_zxcs") != null) {
                        al_zxcs = Double.parseDouble(parameters_cq.get("al_zxcs") + "");
                    }
                    projectList.get(i).put("FYCS", al_zxcs);
                    if (ZXCS_TOTAL > 0) {
                        String currentTime = null;
                        if (parameters_cq.get("currentTime") != null) {
                            currentTime = sdfdatetime.format(parameters_cq.get("currentTime"));
                        }
                        // ??????????????????????????????
                        projectList.get(i).put("QRSJ", currentTime);
                        // ?????????????????????QRSJ?????? ???????????? ll_lsbz(????????????)
                        parameters_lsbz.put("ldt_qrsj", currentTime);
                        ll_lsbz = BUHISUtil.uf_cacl_lsbz(dicsypcList, parameters_lsbz);
                        if (ll_lsbz == 1) {
                            projectList.get(i).put("LSBZ", 1);
                        }
                    }
                } else { // ????????????
                    // ???????????????????????????
                    int count_MRCS = 0;
                    for (int j = 0; j < dicsypcList.size(); j++) {
                        // ??????????????????,??????????????????,??????MRCS???1
                        if (SYPC == null || "null".equals(SYPC)) {
                            count_MRCS = 1;
                            break;
                        }
                        if (SYPC.equals(dicsypcList.get(j).get("PCBM"))) {
                            count_MRCS = Integer.parseInt(dicsypcList.get(j).get("MRCS") + "");
                        }
                    }
                    if (count_MRCS > 0) {
                        projectList.get(i).put("QRSJ", KSSJ);
                        projectList.get(i).put("LSBZ", 1);
                    }
                    projectList.get(i).put("FYCS", count_MRCS);
                }
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw BaseException.create("ERROR_DCTWORK_ZYBQYZ_00021");
        }
        return true;
    }

    /**
     * ??????????????????
     * @Title: updateXhmxZt
     * @Description: TODO(?????????????????????????????????????????????)
     * @param @param sbxh
     * @param @param brly
     * @param @param jgid    ????????????
     * @return void    ????????????
     * @author ?????????
     * @throws
     */
//	public void updateXhmxZt(Long sbxh, String brly, Integer jgid) {
//		Map<String, Object> parametersxhmx = new HashMap<String, Object>(16);
//		parametersxhmx.put("JGID", jgid);
//		parametersxhmx.put("MZXH", sbxh);
//		parametersxhmx.put("BRLY", brly);
//		parametersxhmx.put("ZTBZ", 0);
//		wlXhmxDao.updateZtbz(parametersxhmx);
//	}

    /**
     * ????????????????????????
     *
     * @param @param  cisHzyzorztExcuteReq
     * @param @param  user
     * @param @return ????????????
     * @return List<Map < String, Object>>    ????????????
     * @throws
     * @Title: getDetailChargeQueryFiles
     * @Description: TODO(?????????????????????????????????????????????)
     * @author ?????????
     */
    public List<Map<String, Object>> getDetailChargeQueryFiles(CisHzyzorztExcuteReq cisHzyzorztExcuteReq, SysUser user) {
        List<CisHzyzOrztResp> list = doDetailChargeQuery(cisHzyzorztExcuteReq, user);
        List<Map<String, Object>> listMap = BUHISUtil.ListObjToMap(list);
        List<Map<String, Object>> resList = new ArrayList<Map<String, Object>>();
        if ("XM".equals(cisHzyzorztExcuteReq.getPrintWay())) {
            Integer ypxh = 0;
            String yzmc = "";
            boolean init = true;
            for (Map<String, Object> m : listMap) {
                // ?????????????????????
                if (m.get("YZLX") != null && ObjectToTypes.parseInt(m.get("YZLX")) == 0) {
                    // ???????????????????????? ????????????
                    DicZlxmModel dicZlxm = dicZlxmService.getById(ObjectToTypes.parseInt(m.get("YPXH")));
                    DicYjlxModel dicYjlxModel = new DicYjlxModel();
                    dicYjlxModel.setId(dicZlxm.getYjlx());
                    dicYjlxModel = dicYjlxService.queryFirstYjlx(dicYjlxModel);
                    m.put("YJLXNAME", dicYjlxModel.getName().replace("?????????", ""));
                    if (init) {
                        ypxh = ObjectToTypes.parseInt(m.get("YPXH"));
                        yzmc = ObjectToTypes.parseString(m.get("YZMC"));
                        m.put("YZMC", yzmc);
                        init = false;
                    } else {
                        if (m.get("YPXH") != null && ObjectToTypes.parseInt(m.get("YPXH")) == ypxh) {
                            m.put("YZMC", "");
                        } else {
                            ypxh = ObjectToTypes.parseInt(m.get("YPXH"));
                            yzmc = ObjectToTypes.parseString(m.get("YZMC"));
                            m.put("YZMC", yzmc);
                        }
                    }
                    m.put("ZJE", BUHISUtil.getDouble(ObjectToTypes.parseDouble(m.get("YCSL")) *
                            ObjectToTypes.parseDouble(m.get("YPDJ")), 2) + "*" +
                            String.format("%1$.0f", ObjectToTypes.parseDouble(m.get("FYCS"))));
                    resList.add(m);
                }
            }
        } else if ("BR".equals(cisHzyzorztExcuteReq.getPrintWay())) {
            String brch = null;
            String brxm = null;
            String zyhm = "0";
            boolean init = true;
            for (Map<String, Object> m : listMap) {
                // ?????????????????????
                if (m.get("YZLX") != null && ObjectToTypes.parseInt(m.get("YZLX")) == 0) {

                    if (init) {
                        brch = m.get("BRCH") + "";
                        brxm = m.get("BRXM") + "";
                        zyhm = ObjectToTypes.parseString(m.get("ZYHM"));
                        m.put("BRCH", brch);
                        m.put("BRXM", brxm);
                        m.put("ZYHM", zyhm);
                        init = false;
                    } else {
                        if (m.get("BRCH") != null && m.get("BRCH").toString().equals(brch)
                                && m.get("BRXM").toString().equals(brxm)) {
                            m.put("BRCH", "");
                            m.put("BRXM", "");
                            m.put("ZYHM", null);
                        } else {
                            brch = m.get("BRCH") + "";
                            brxm = m.get("BRXM") + "";
                            zyhm = ObjectToTypes.parseString(m.get("ZYHM"));
                            m.put("BRCH", brch);
                            m.put("BRXM", brxm);
                            m.put("ZYHM", zyhm);
                        }
                    }
                    m.put("ZJE", BUHISUtil.getDouble(ObjectToTypes.parseDouble(m.get("YCSL")) *
                            ObjectToTypes.parseDouble(m.get("YPDJ")), 2) + "*" +
                            String.format("%1$.0f", ObjectToTypes.parseDouble(m.get("FYCS"))));
                    resList.add(m);
                }
            }
        } else {
            for (Map<String, Object> m : listMap) {
                m.put("ZJE", BUHISUtil.getDouble(ObjectToTypes.parseDouble(m.get("YCSL")) *
                        ObjectToTypes.parseDouble(m.get("YPDJ")), 2) + "*" +
                        String.format("%1$.0f", ObjectToTypes.parseDouble(m.get("FYCS"))));
                resList.add(m);
            }
        }
        return resList;
    }

    /**
     * ??????????????????????????????
     *
     * @param @param yzxh    ????????????
     * @return void    ????????????
     * @throws
     * @Title: deleteBackApplication
     * @Description: TODO(?????????????????????????????????????????????)
     * @author ?????????
     */
    @Transactional(rollbackFor = Exception.class)
    public void deleteBackApplication(Integer jlxh) {
        nisTymxDao.deleteById(jlxh);
        nisTypcDao.deleteTypcByTyxh(jlxh);
    }


    /**
     * lis????????????
     *
     * @param @param list
     * @param @param user    ????????????
     * @return void    ????????????
     * @throws
     * @Title: uploadLisData
     * @Description: TODO(?????????????????????????????????????????????)
     * @author ?????????
     */
    public void uploadLisData(List<Integer> list, SysUser user) {
        SysXtcs sysXtcs = sysXtcsCacheSer.getByJGIdAndCsmc(user.getHospitalId(), SysXtcsCsmcCts.SFSCJYSJ);
        String sfsc = sysXtcs == null ? "1" : sysXtcs.getCsz();
        logger.error("uploadLisData:" + Joiner.on(",").join(list));
        if ("1".equals(sfsc)) {
            for (Integer jlxh : list) {
                CisHzyzZt cisHzyzZt = cisHzyzZtDao.getById(jlxh);
                //??????
                logger.error("lis??????:" + JSONUtil.toJsonStr(cisHzyzZt));
                if (cisHzyzZt.getYzlx() == 1) {
                    ImHzry imHzry = imHzryDao.getById(cisHzyzZt.getZyh());
                    LisRequestUpdateReq req = new LisRequestUpdateReq();
                    RequestUpdateInfo requsetInfo = new RequestUpdateInfo();
                    requsetInfo.setHospital_id(ObjectToTypes.parseString(user.getHospitalId()));
                    requsetInfo.setRequest_no(ObjectToTypes.parseString(jlxh));
                    requsetInfo.setPatient_id(imHzry.getZyhm());
                    requsetInfo.setCard_no(imHzry.getJzkh());
                    requsetInfo.setIdentity_card(imHzry.getSfzh());
                    requsetInfo.setPatient_Type("??????");
                    requsetInfo.setEmergency(cisHzyzZt.getHyxm() == null ? "0" : cisHzyzZt.getHyxm());
                    requsetInfo.setPatient_name(imHzry.getBrxm());
                    requsetInfo.setPhone(imHzry.getLxdh());
                    requsetInfo.setGender(imHzry.getBrxb() == 1 ? "???" : "???");
                    requsetInfo.setBirthday(DateUtil.format(imHzry.getCsny(), DatePattern.NORM_DATETIME_PATTERN));
                    requsetInfo.setWard(dicKszdOutSer.getNameById(cisHzyzZt.getBrbq()));
                    requsetInfo.setDepartment(dicKszdOutSer.getNameById(cisHzyzZt.getBrks()));
                    requsetInfo.setBed_no(imHzry.getBrch());
                    requsetInfo.setReq_date(DateUtil.format(cisHzyzZt.getKssj(), DatePattern.NORM_DATETIME_PATTERN));
                    requsetInfo.setReq_doctor(hrPersonnelService.getPersonnelById(Integer.valueOf(cisHzyzZt.getYsgh())).getPersonname());
                    if(StringUtils.isNotEmpty(imHzry.getMqzd())) {
                        if (imHzry.getMqzd().length() > 50) {
                            requsetInfo.setDiagnostic(imHzry.getMqzd().substring(0, 50));
                        } else {
                            requsetInfo.setDiagnostic(imHzry.getMqzd());
                        }
                    }
                    if(StringUtils.isNotEmpty(cisHzyzZt.getBzxx())){
                        if(cisHzyzZt.getBzxx().length() > 10){
                            requsetInfo.setBZ1(cisHzyzZt.getBzxx().substring(0,10));
                        }else{
                            requsetInfo.setBZ1(cisHzyzZt.getBzxx());
                        }
                    }
                    if (imHzry.getCsny() != null) {
                        Map<String, Object> agMap = BUHISUtil.getPersonAge(imHzry.getCsny(), null);
                        if (!agMap.isEmpty()) {
                            String ages = String.valueOf(agMap.get("ages"));
                            if (ages.contains("???")) {
                                requsetInfo.setAge_unit("???");
                                requsetInfo.setAge(Integer.parseInt(String.valueOf(agMap.get("age"))));
                            } else if (ages.contains("???")) {
                                requsetInfo.setAge_unit("???");
                                requsetInfo.setAge(Integer.parseInt((ages.split("???"))[0]));
                            } else if (ages.contains("???")) {
                                requsetInfo.setAge_unit("???");
                                requsetInfo.setAge(Integer.parseInt((ages.split("???"))[0]));
                            } else {
                                requsetInfo.setAge_unit("???");
                                requsetInfo.setAge(1);
                            }
                        }
                    }
                    req.setRequest_info(requsetInfo);
                    List<RequestUpdateDetail> detailUpload = new ArrayList<RequestUpdateDetail>();
//                    CisHzyz cisHzyz = new CisHzyz();
//                    cisHzyz.setJgid(user.getHospitalId());
//                    cisHzyz.setZtyzjlxh(jlxh);
//                    List<CisHzyz> detailList = cisHzyzDao.findByEntity(cisHzyz);
//                    for (CisHzyz cis : detailList) {
                        RequestUpdateDetail detail = new RequestUpdateDetail();
                        String ztmc = cisHzyzZtDao.getZt01Name(cisHzyzZt.getYpxh());
                        detail.setRequestdetail_no(ObjectToTypes.parseString(jlxh));
                        detail.setItem_code(ObjectToTypes.parseString(cisHzyzZt.getYpxh()));
                        detail.setItem_name(ztmc);
                        detail.setPrice(cisHzyzZt.getYpdj());
                        detailUpload.add(detail);
//                    }
                    req.setRequest_detail(detailUpload);
                    logger.error("lis??????????????????:" + JSONUtil.toJsonStr(req));
                    mdiLisService.lisRequestUpdate(req); 
                }
            }
        }
    }

    /**
     * ??????????????????
     *
     * @param @param list
     * @param @param user    ????????????
     * @return void    ????????????
     * @throws
     * @Title: cancelLisData
     * @Description: TODO(?????????????????????????????????????????????)
     * @author ?????????
     */
    public void cancelLisData(List<Integer> list, SysUser user) {
        SysXtcs sysXtcs = sysXtcsCacheSer.getByJGIdAndCsmc(user.getHospitalId(), SysXtcsCsmcCts.SFSCJYSJ);
        String sfsc = sysXtcs == null ? "1" : sysXtcs.getCsz();
        if ("1".equals(sfsc)) {
            for (Integer jlxh : list) {
                LisRequestCancelReq req = new LisRequestCancelReq();
                req.setRequest_no(ObjectToTypes.parseString(jlxh));
                req.setHospital_id(ObjectToTypes.parseString(user.getHospitalId()));
                LisResp resp = mdiLisService.lisRequestCancel(req);
                if(StrUtil.isNotBlank(resp.getCode()) && "0".equals(resp.getCode())) {
                	CisHzyz cisHzyz = new CisHzyz();
                	cisHzyz.setZtyzjlxh(jlxh);                	
                	List<CisHzyz> hzyzList = cisHzyzDao.findByEntity(cisHzyz);
                	if(CollectionUtils.isNotEmpty(hzyzList)) {
                		imFeeFymxYjDao.cancelLisZxzt(hzyzList.stream().map(CisHzyz::getJlxh).collect(Collectors.toList()));
                	}
                }
            }
        }
    }

    
	public List<Map<String, Object>> queryXmPrintFiles(ImFeeFymxYjPrintReq req, SysUser user) {
		List<Map<String, Object>> list = BUHISUtil.ListObjToMap(imFeeFymxYjDao.queryXmPrint(req));
		Map<String, Object> jlMap = new HashMap<String, Object>(16);
		Map<String, Object> xmMap = new HashMap<String, Object>(16);
		for(Map<String, Object> map : list) {
			map.put("fyrq", DateUtil.parse(map.get("fyrq") + "", DatePattern.NORM_DATETIME_PATTERN));
			if(jlMap.containsKey(ObjectToTypes.parseString(map.get("zyhm")))) {
				map.put("brch", "");
				map.put("zyhm", "");
				map.put("brxm", "");
			}else {
				jlMap.put(ObjectToTypes.parseString(map.get("zyhm")), map);
			}
			
			if(xmMap.containsKey(ObjectToTypes.parseString(map.get("zyh")) + "-" +
					map.get("fyrq") + "-"+ map.get("yzmc"))) {
				map.put("fyrq", "");
				map.put("yzmc", "");
			}else {
				xmMap.put(ObjectToTypes.parseString(map.get("zyh")) + "-" +
						map.get("fyrq") + "-"+ map.get("yzmc"), map);
			}
		}
		return list;
	}

	/**
     * @Description ????????????????????????????????????
     * @Title  queryDoctorReviewPatientInfo
     * @author  gfz
     * @Date  2021/6/9 21:07
     * @param req
     * @Return  java.util.List<com.buit.cis.nurse.response.CisHzyzReviewPatientResp>
     * @Exception
    */
    public List<CisHzyzReviewPatientResp> queryDoctorReviewPatientInfo(CisHzyzReviewBatchReq req) {
        List<CisHzyzReviewInfoResp> list = doQueryDoctorReviewList(req);
        Map<String, List<CisHzyzReviewInfoResp>> groups = list.stream().collect(
                Collectors.groupingBy(o -> String.format("%s-%s-%s", o.getZyh(), o.getBrch(), o.getBrxm()),
                        LinkedHashMap::new, Collectors.toList()));
        List<CisHzyzReviewPatientResp> ret = new ArrayList<CisHzyzReviewPatientResp>();
        groups.entrySet().stream().forEach(item -> {
            String[] keyArray = item.getKey().split("-");
            CisHzyzReviewPatientResp resp = new CisHzyzReviewPatientResp();
            resp.setZyh(ObjectToTypes.parseInt(keyArray[0]));
            resp.setBrch(keyArray[1]);
            resp.setBrxm(keyArray[2]);
            ret.add(resp);
        });
        return ret;
    }

    /**
     * ???????????????????????????????????????
     * @Title: stopOrderReview
     * @Description: TODO(?????????????????????????????????????????????)
     * @param @param req
     * @param @param user    ????????????
     * @return void    ????????????
     * @author ?????????
     * @throws
     */
	public void stopOrderReview(CisHzyzStopReviewReq req, SysUser user) {
		SysXtcs sysXtcs = sysXtcsCacheSer.getByJGIdAndCsmc(user.getHospitalId(), SysXtcsCsmcCts.YZLRFHTYGH);
        String isfhsrSame = sysXtcs == null ? "1" : sysXtcs.getCsz();
		List<CisHzyzStopReviewBody> list = req.getStopReviewBodyList();
		List<Integer> ptList = list.stream().filter(o -> (o.getZtbz() == null || o.getZtbz() == 0))
				.map(CisHzyzStopReviewBody::getJlxh).collect(Collectors.toList());
		CisHzyzStopUpdateReq updatReq = new CisHzyzStopUpdateReq();
		updatReq.setJlxhList(ptList);
		updatReq.setTzfhbz(req.getSftzfh());
		updatReq.setJgid(user.getHospitalId());
		if(req.getSftzfh() == 1) {
			updatReq.setFhgh(ObjectToTypes.parseString(user.getUserId()));
			updatReq.setFhsj(DateUtil.date().toTimestamp());
		}
		//??????????????????????????????????????????
		if("0".equals(isfhsrSame)) {
			updatReq.setCzgh(ObjectToTypes.parseString(user.getUserId()));
		}
		if(!ptList.isEmpty()) {			
			cisHzyzDao.updateStopOrderReview(updatReq);
		}
		
		List<Integer> ztList = list.stream().filter(o -> (o.getZtbz() != null && o.getZtbz() == 1))
				.map(CisHzyzStopReviewBody::getJlxh).collect(Collectors.toList());
		if(!ztList.isEmpty()) {
			cisHzyzZtDao.updateZtStopOrderReview(updatReq);
			updatReq.setJlxhList(null);
			updatReq.setZtjlxhList(ztList);
			cisHzyzDao.updateStopOrderReview(updatReq);
		}
         
	}
}
