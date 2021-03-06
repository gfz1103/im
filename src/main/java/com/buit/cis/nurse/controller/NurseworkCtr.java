package com.buit.cis.nurse.controller;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.commons.collections.CollectionUtils;
import org.apache.dubbo.config.annotation.DubboReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.buit.apply.response.OpZt02DetailResp;
import com.buit.cis.dctwork.model.CisHzyz;
import com.buit.cis.dctwork.model.NisFyyf;
import com.buit.cis.dctwork.response.CisHzyzOperationResp;
import com.buit.cis.dctwork.response.CisHzyzResp;
import com.buit.cis.dctwork.response.NisTj02BackDetailResp;
import com.buit.cis.dctwork.response.NisTjMsgResp;
import com.buit.cis.dctwork.response.NisTymxResp;
import com.buit.cis.dctwork.service.CisHzyzSer;
import com.buit.cis.dctwork.service.NisFyyfSer;
import com.buit.cis.dctwork.service.NisTj02Ser;
import com.buit.cis.dctwork.service.NisTymxSer;
import com.buit.cis.ims.model.ImFeeFymx;
import com.buit.cis.ims.model.ImHzry;
import com.buit.cis.ims.request.ImCwszQueryReq;
import com.buit.cis.ims.request.ImFeeFymxAccountingReq;
import com.buit.cis.ims.request.ImFeeFymxQueryReq;
import com.buit.cis.ims.request.ImFeeFymxSaveReq;
import com.buit.cis.ims.request.ImFeeFymxVoidReq;
import com.buit.cis.ims.request.ImFeeFymxYjJyReq;
import com.buit.cis.ims.request.ImFeeFymxYjPrintReq;
import com.buit.cis.ims.response.ImFeeFymxAccountingResp;
import com.buit.cis.ims.response.ImFeeFymxCostResp;
import com.buit.cis.ims.response.ImFeeFymxProjectResp;
import com.buit.cis.ims.response.ImFeeFymxYjJyResp;
import com.buit.cis.ims.response.ImFeeFymxYjPrintResp;
import com.buit.cis.ims.response.ImHzryYpSumbitResp;
import com.buit.cis.ims.response.ZybrCwszResp;
import com.buit.cis.ims.service.ImCwszSer;
import com.buit.cis.ims.service.ImFeeFymxSer;
import com.buit.cis.ims.service.ImFeeFymxYjSer;
import com.buit.cis.nurse.enums.JzlxEnum;
import com.buit.cis.nurse.model.NisYzdy;
import com.buit.cis.nurse.request.CisHzyzExcuteReq;
import com.buit.cis.nurse.request.CisHzyzFjReq;
import com.buit.cis.nurse.request.CisHzyzNurseExReq;
import com.buit.cis.nurse.request.CisHzyzReviewBatchReq;
import com.buit.cis.nurse.request.CisHzyzReviewReq;
import com.buit.cis.nurse.request.CisHzyzReviewSaveReq;
import com.buit.cis.nurse.request.CisHzyzSheetReq;
import com.buit.cis.nurse.request.CisHzyzStopReviewReq;
import com.buit.cis.nurse.request.CisHzyzorztExcuteReq;
import com.buit.cis.nurse.request.NisFyyfSaveReq;
import com.buit.cis.nurse.request.NisRefundableProjectReq;
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
import com.buit.cis.nurse.service.NurseworkSer;
import com.buit.commons.BaseSpringController;
import com.buit.commons.PageQuery;
import com.buit.constans.SysXtcsCsmcCts;
import com.buit.drug.request.QueryGrantedDrugReq;
import com.buit.drug.response.CisHzyzFymxGrantedResp;
import com.buit.file.IReportExportFileSer;
import com.buit.his.service.MdiHrpService;
import com.buit.op.request.ValidWpjfbzReq;
import com.buit.op.service.OpMzxxService;
import com.buit.system.response.DiccLdxmglApiResp;
import com.buit.system.response.SysXtcs;
import com.buit.system.service.DicKszdOutSer;
import com.buit.system.service.SysXtcsCacheSer;
import com.buit.system.utill.ObjectToTypes;
import com.buit.utill.BUHISUtil;
import com.buit.utill.BeanUtil;
import com.buit.utill.ReturnEntity;
import com.buit.utill.ReturnEntityUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import cn.hutool.json.JSONUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * ???????????????
 *
 * @author ?????????
 * @ClassName: NurseworkCtr
 * @Description: TODO(??????????????????????????????????????????)
 * @date 2020???6???10??? ??????9:55:32
 */
@Api(tags = "???????????????????????????")
@Controller
@RequestMapping("/nursework")
public class NurseworkCtr extends BaseSpringController {

    static final Logger logger = LoggerFactory.getLogger(NurseworkCtr.class);

    @Autowired
    private NurseworkSer nurseworkSer;

    @DubboReference
    private OpMzxxService opMzxxService;

    @Autowired
    private ImCwszSer imCwszSer;

    @Autowired
    private CisHzyzSer cisHzyzSer;

    @Autowired
    private NisTymxSer nisTymxSer;

    @Autowired
    private ImFeeFymxSer imFeeFymxSer;

    @Autowired
    private NisFyyfSer nisFyyfSer;

    @DubboReference
    private SysXtcsCacheSer sysXtcsCacheSer;

    @DubboReference
    private DicKszdOutSer dicKszdOutSer;
	
	@Autowired
	private IReportExportFileSer iReportExportFileSer;
	
	@Autowired
	private ImFeeFymxYjSer imFeeFymxYjSer;
	
    @Autowired
    private NisTj02Ser nisTj02Ser;

    @DubboReference
    private MdiHrpService mdiHrpService;

    @RequestMapping("/queryJzyfsz")
    @ResponseBody
    @ApiOperation(value = "?????????????????????????????????????????????", httpMethod = "POST")
    public ReturnEntity<NisFyyfCheckResp> queryJzyfsz(@ApiParam(name = "brbq", value = "????????????")
                                                      @RequestParam(value = "brbq") Integer brbq) {
        return ReturnEntityUtil.success(nurseworkSer.doQueryJzyfsz(brbq, this.getUser()));
    }

    @RequestMapping("/checkYscz")
    @ResponseBody
    @ApiOperation(value = "???????????????????????????????????????", httpMethod = "POST")
    public ReturnEntity<NisYzdy> checkYscz(@ApiParam(name = "zyh", value = "?????????")
                                           @RequestParam(value = "zyh") Integer zyh) {
        return ReturnEntityUtil.success(nurseworkSer.checkYscz(zyh));
    }

    @RequestMapping("/updateCzhs")
    @ResponseBody
    @ApiOperation(value = "????????????????????????????????????", httpMethod = "POST")
    public ReturnEntity updateCzhs(@ApiParam(name = "dyxh", value = "????????????")
                                   @RequestParam(value = "dyxh") Integer dyxh, @ApiParam(name = "czhs", value = "????????????")
                                   @RequestParam(value = "czhs") String czhs) {
        nurseworkSer.updateCzhs(dyxh, czhs);
        return ReturnEntityUtil.success();
    }

    @RequestMapping("/saveReview")
    @ResponseBody
    @ApiOperation(value = "??????????????????", httpMethod = "POST")
    public ReturnEntity<CisHzyzReviewResp> saveReview(@Valid @RequestBody List<CisHzyzReviewReq> list) {
        return ReturnEntityUtil.success(nurseworkSer.doSaveReview(list, this.getUser()));
    }

//    @RequestMapping("/saveAreview")
//    @ResponseBody
//    @ApiOperation(value = "??????????????????", httpMethod = "POST")
//    public ReturnEntity<CisHzyzReviewResp> saveAreview(CisHzyzReviewReq cisHzyzReviewReq) {
//        return ReturnEntityUtil.success(nurseworkSer.doSaveAreview(cisHzyzReviewReq, this.getUser()));
//    }

    @RequestMapping("/saveUnReview")
    @ResponseBody
    @ApiOperation(value = "??????????????????", httpMethod = "POST")
    public ReturnEntity<Long> saveUnReview(@Valid @RequestBody List<CisHzyzReviewReq> list) {
        return ReturnEntityUtil.success(nurseworkSer.doSaveUnReview(list, this.getUser().getHospitalId()));
    }

    @RequestMapping("/queryFhsfxm")
    @ResponseBody
    @ApiOperation(value = "????????????????????????", httpMethod = "POST")
    public ReturnEntity<Long> queryFhsfxm(@RequestBody CisHzyzExcuteReq cisHzyzExcuteReq) {
        return ReturnEntityUtil.success(nurseworkSer.doQueryFhsfxm(cisHzyzExcuteReq, this.getUser()));
    }

    @RequestMapping("/detailChargeQuery")
    @ResponseBody
    @ApiOperation(value = "??????????????????????????????????????????", httpMethod = "POST")
    public ReturnEntity<List<CisHzyzOrztResp>> detailChargeQuery(@RequestBody CisHzyzorztExcuteReq cisHzyzorztExcuteReq) {
        return ReturnEntityUtil.success(nurseworkSer.doDetailChargeQuery(cisHzyzorztExcuteReq, this.getUser()));
    }

    @RequestMapping("/additionProjectsFeeQuery")
    @ResponseBody
    @ApiOperation(value = "???????????????????????????????????????", httpMethod = "POST")
    @SuppressWarnings("unchecked")
    public ReturnEntity<List<CisHzyzOrztResp>> additionProjectsFeeQuery(@RequestBody CisHzyzSheetReq cisHzyzSheetReq,
                                                                        PageQuery page) {
        return ReturnEntityUtil.success(nurseworkSer.doAdditionProjectsFeeQuery(cisHzyzSheetReq, this.getUser()));
    }

    @RequestMapping("/verificationWpjfbz")
    @ResponseBody
    @ApiOperation(value = "???????????????????????????????????????????????????????????????????????????", httpMethod = "POST")
    public ReturnEntity verificationWpjfbz(ValidWpjfbzReq validWpjfbzReq) {
        validWpjfbzReq.setJgid(getUser().getUserId());
        opMzxxService.doVerificationWpjfbz(validWpjfbzReq);
        return ReturnEntityUtil.success();
    }

    @RequestMapping("/saveConfirm")
    @ResponseBody
    @ApiOperation(value = "????????????????????????", httpMethod = "POST")
    public ReturnEntity<NisTjMsgResp> saveConfirm(@RequestBody CisHzyzNurseExReq cisHzyzNurseExReq) {
        NisTjMsgResp ret = nurseworkSer.doSaveConfirm(cisHzyzNurseExReq, this.getUser());
        List<Integer> intList = ret.getIntList();
        if(!intList.isEmpty()) {
            try {
                mdiHrpService.hiscubebzejkfpush(intList, 1);
            } catch (Exception e) {
                logger.error(e.getMessage(), e);
            }
        }
        return ReturnEntityUtil.success(ret);
    }

    @RequestMapping("/queryZtmx")
    @ResponseBody
    @ApiOperation(value = "??????????????????", httpMethod = "POST")
    public ReturnEntity<List<CisHzyzResp>> queryZtmx(@ApiParam(name = "jlxh", value = "????????????", required = true)
                                                     @RequestParam Integer jlxh) {
        return ReturnEntityUtil.success(nurseworkSer.doQueryZtmx(jlxh, this.getUser()));
    }

    @RequestMapping("/saveGoback")
    @ResponseBody
    @ApiOperation(value = "????????????????????????", httpMethod = "POST")
    public ReturnEntity<Long> saveGoback(@ApiParam(name = "jlxh", value = "????????????", required = true)
                                         @RequestParam Integer jlxh, @ApiParam(name = "bzxx", value = "????????????", required = true)
                                         @RequestParam String bzxx, @ApiParam(name = "ztbz", value = "????????????", required = false)
                                         @RequestParam(value = "ztbz", required = false) Integer ztbz) {
        return ReturnEntityUtil.success(nurseworkSer.doSaveGoback(jlxh, ztbz, bzxx, this.getUser()));
    }

    @RequestMapping("/queryDispensingRecords")
    @ResponseBody
    @ApiOperation(value = "?????????????????????????????????", httpMethod = "POST")
    public ReturnEntity<List<CisHzyzFymxGrantedResp>> queryDispensingRecords(@ApiParam(name = "zyh", value = "?????????", required = true)
     	@RequestParam Integer zyh, @ApiParam(name = "lybq", value = "????????????", required = true)
     	@RequestParam Integer lybq, @ApiParam(name = "beginDate", value = "????????????", required = true)
		@RequestParam String beginDate, @ApiParam(name = "endDate", value = "????????????", required = true)
		@RequestParam String endDate, @ApiParam(name = "pydm", value = "????????????", required = false)
    	@RequestParam(value = "pydm", required = false) String pydm) {
        return ReturnEntityUtil.success(nurseworkSer.queryDispensingRecords(zyh, lybq, 
        		this.getUser(), beginDate, endDate, pydm));
    }


    @RequestMapping("/querytyRecords")
    @ResponseBody
    @ApiOperation(value = "??????????????????????????????", httpMethod = "POST")
    public ReturnEntity<PageInfo<NisTymxRecordResp>> querytyRecords(@ApiParam(name = "zyh", value = "?????????", required = true)
        @RequestParam Integer zyh, @ApiParam(name = "beginDate", value = "????????????(??????)", required = true)
        @RequestParam String beginDate, @ApiParam(name = "endDate", value = "????????????(??????)", required = true)
        @RequestParam String endDate, PageQuery page) {
        PageInfo<NisTymxRecordResp> pageInfo = PageHelper.startPage(
                page.getPageNum(), page.getPageSize()).doSelectPageInfo(
                () -> nurseworkSer.querytyRecords(zyh, beginDate, endDate)
        );
        return ReturnEntityUtil.success(pageInfo);
    }

    @RequestMapping("/queryTurningBackNumber")
    @ResponseBody
    @ApiOperation(value = "??????????????????", httpMethod = "POST")
    public ReturnEntity<NisTymxBackResp> queryTurningBackNumber(
            QueryGrantedDrugReq queryGrantedDrugReq) {
        return ReturnEntityUtil.success(nurseworkSer.queryTurningBackNumber(queryGrantedDrugReq,
                this.getUser()));
    }

    @RequestMapping("/saveBackApplication")
    @ResponseBody
    @ApiOperation(value = "????????????????????????", httpMethod = "POST")
    public ReturnEntity saveBackApplication(@RequestBody NisTymxSaveReq nisTymxSaveReq) {
        nurseworkSer.saveBackApplication(nisTymxSaveReq, this.getUser());
        return ReturnEntityUtil.success();
    }

    @RequestMapping("/saveCommitBackApplication")
    @ResponseBody
    @ApiOperation(value = "??????????????????", httpMethod = "POST")
    public ReturnEntity saveCommitBackApplication(@ApiParam(name = "zyh", value = "?????????", required = true)
                                                  @RequestParam Integer zyh) {
        nurseworkSer.saveCommitBackApplication(zyh, this.getUser());
        return ReturnEntityUtil.success();
    }

    @RequestMapping("/canLeaveHospital")
    @ResponseBody
    @ApiOperation(value = "??????????????????????????????", httpMethod = "POST")
    public ReturnEntity<String> canLeaveHospital(@ApiParam(name = "zyh", value = "?????????", required = true)
    @RequestParam Integer zyh, @ApiParam(name = "cyrq", value = "????????????", required = false)
    @RequestParam(value = "cyrq", required = false) Timestamp cyrq) {
        return ReturnEntityUtil.success(nurseworkSer.doCanLeaveHospital(zyh, cyrq, this.getUser()));
    }


    @RequestMapping("/tzcfYjfyCheck")
    @ResponseBody
    @ApiOperation(value = "??????????????????????????????", httpMethod = "POST")
    public ReturnEntity tzcfYjfyCheck(@ApiParam(name = "zyh", value = "?????????", required = true)
                                      @RequestParam Integer zyh) {
        nurseworkSer.doTzcfYjfyCheck(zyh);
        return ReturnEntityUtil.success();
    }

    @RequestMapping("/saveLeaveHospital")
    @ResponseBody
    @ApiOperation(value = "??????????????????", httpMethod = "POST")
    public ReturnEntity saveLeaveHospital(@ApiParam(name = "zyh", value = "?????????", required = true)
                                          @RequestParam Integer zyh) {
        nurseworkSer.doSaveLeaveHospital(zyh, this.getUser());
        return ReturnEntityUtil.success();
    }

    @RequestMapping("/saveCancelLeaveHospital")
    @ResponseBody
    @ApiOperation(value = "??????????????????", httpMethod = "POST")
    public ReturnEntity saveCancelLeaveHospital(@ApiParam(name = "zyh", value = "?????????", required = true)
                                                @RequestParam Integer zyh) {
        nurseworkSer.doSaveCancelLeaveHospital(zyh, this.getUser());
        return ReturnEntityUtil.success();
    }

    @RequestMapping("/patientQuery")
    @ResponseBody
    @ApiOperation(value = "?????????????????????????????????????????????????????????", httpMethod = "POST")
    public ReturnEntity<List<ImHzryYpSumbitResp>> patientQuery(@RequestBody
                                                                       CisHzyzorztExcuteReq cisHzyzorztExcuteReq) {
        return ReturnEntityUtil.success(nurseworkSer.doPatientQuery(cisHzyzorztExcuteReq, this.getUser()));
    }

    @RequestMapping("/xmQuery")
    @ResponseBody
    @ApiOperation(value = "?????????????????????????????????????????????????????????", httpMethod = "POST")
    public ReturnEntity<Set<CisHzyzXmResp>> xmQuery(@ApiParam(name = "brbq", value = "????????????", required = true)
        @RequestParam Integer brbq, @ApiParam(name = "lsyz", value = "????????????(0:??????1:??????2:??????)", required = true)
    	@RequestParam Integer lsyz) {
        return ReturnEntityUtil.success(nurseworkSer.xmQuery(brbq, lsyz, this.getUser()));
    }

    @RequestMapping("/additionProjectsBrQuery")
    @ResponseBody
    @ApiOperation(value = "????????????????????????????????????????????????", httpMethod = "POST")
    public ReturnEntity<List<ImHzryYpSumbitResp>> additionProjectsBrQuery(@RequestBody
                                                                                  CisHzyzExcuteReq cisHzyzExcuteReq) {
        return ReturnEntityUtil.success(nurseworkSer.additionProjectsBrQuery(cisHzyzExcuteReq,
                this.getUser()));
    }

    @RequestMapping("/additionProjectsQuery")
    @ResponseBody
    @ApiOperation(value = "????????????????????????????????????", httpMethod = "POST")
    public ReturnEntity<List<CisHzyzOrztResp>> additionProjectsQuery(@RequestBody
                                                                             CisHzyzExcuteReq cisHzyzExcuteReq) {
        return ReturnEntityUtil.success(nurseworkSer.doAdditionProjectsQuery(cisHzyzExcuteReq,
                this.getUser()));
    }

    @RequestMapping("/additionProjectsXmQuery")
    @ResponseBody
    @ApiOperation(value = "???????????????????????????????????????????????????", httpMethod = "POST")
    public ReturnEntity<Set<CisHzyzXmResp>> additionProjectsXmQuery(@RequestBody
            CisHzyzExcuteReq cisHzyzExcuteReq) {
        return ReturnEntityUtil.success(nurseworkSer.additionProjectsXmQuery(cisHzyzExcuteReq, this.getUser()));
    }

    @RequestMapping("/saveConfirmAdditional")
    @ResponseBody
    @ApiOperation(value = "????????????????????????", httpMethod = "POST")
    public ReturnEntity<NisTjMsgResp> saveConfirmAdditional(@RequestBody
                                                                    CisHzyzNurseExReq cisHzyzNurseExReq) {
        return ReturnEntityUtil.success(nurseworkSer.doSaveConfirmAdditional(cisHzyzNurseExReq,
                this.getUser()));
    }

    @RequestMapping("/queryItemInfo")
    @ResponseBody
    @ApiOperation(value = "??????????????????????????????????????????????????????", httpMethod = "POST")
    public ReturnEntity<ImHzry> queryItemInfo(@ApiParam(name = "zyhm") @RequestParam(required = false) String zyhm,
                                              @ApiParam(name = "brbq") @RequestParam(required = false) Integer brbq,
                                              @ApiParam(name = "brch") @RequestParam(required = false) String brch) {
        return ReturnEntityUtil.success(nurseworkSer.doQueryItemInfo(zyhm, brbq, brch, this.getUser()));
    }

    @RequestMapping("/saveCostList")
    @ResponseBody
    @ApiOperation(value = "????????????????????????", httpMethod = "POST")
    public ReturnEntity<List<CisHzyzCostResp>> saveCostList(@RequestBody ImFeeFymxSaveReq imFeeFymxSaveReq) {
        Integer zyh = imFeeFymxSaveReq.getFymxBody().get(0).getZyh();
        List<CisHzyzCostResp> ret = nurseworkSer.doSaveCostList(zyh, imFeeFymxSaveReq,
                this.getUser());
        List<Integer> intList = new ArrayList<>();
        for(CisHzyzCostResp cost:ret){
            intList.add(cost.getJlxh());
        }
        if(!intList.isEmpty()) {
            try {
                mdiHrpService.hiscubebzejkfpush(intList, 1);
            } catch (Exception e) {
                logger.error(e.getMessage(), e);
            }
        }
        return ReturnEntityUtil.success(ret);
    }

    @RequestMapping("/updateJzzf")
    @ResponseBody
    @ApiOperation(value = "???????????????", httpMethod = "POST")
    public ReturnEntity updateJzzf(@RequestBody
                                           ImFeeFymxVoidReq imFeeFymxVoidReq) {
        List<Integer> intList = nurseworkSer.doUpdateJzzf(imFeeFymxVoidReq, this.getUser());
        if(!intList.isEmpty()) {
            try {
                mdiHrpService.hiscubebzejkfpush(intList, 1);
            } catch (Exception e) {
                logger.error(e.getMessage(), e);
            }
        }
        return ReturnEntityUtil.success();
    }


    @RequestMapping("/queryDoctorReviewList")
    @ResponseBody
    @ApiOperation(value = "??????????????????????????????", httpMethod = "POST")
    public ReturnEntity<List<CisHzyzReviewInfoResp>> queryDoctorReviewList(@RequestBody CisHzyzReviewBatchReq req) {
    	req.setJgid(this.getUser().getHospitalId());
    	return ReturnEntityUtil.success(nurseworkSer.doQueryDoctorReviewList(req));
    }

    @RequestMapping("/saveBatchReview")
    @ResponseBody
    @ApiOperation(value = "???????????????????????????", httpMethod = "POST")
    public ReturnEntity<CisHzyzReviewBatchResp> saveBatchReview(@RequestBody CisHzyzReviewSaveReq req) {
        return ReturnEntityUtil.success(nurseworkSer.doSaveBatchReview(req, this.getUser()));
    }

    @RequestMapping("/loadAppendAdviceTysq")
    @ResponseBody
    @ApiOperation(value = "???????????????????????????????????????", httpMethod = "POST")
    public ReturnEntity<List<DiccLdxmglApiResp>> loadAppendAdviceTysq(@RequestBody
                                                                              CisHzyzFjReq cisHzyzFjReq) {
        return ReturnEntityUtil.success(nurseworkSer.doLoadAppendAdviceTysq(cisHzyzFjReq,
                this.getUser()));
    }

    @RequestMapping("/saveNisFyyfCommit")
    @ResponseBody
    @ApiOperation(value = "??????????????????????????????", httpMethod = "POST")
    public ReturnEntity saveNisFyyfCommit(@RequestBody NisFyyfSaveReq nisFyyfSaveReq) {
        nurseworkSer.doSaveCommit(nisFyyfSaveReq, this.getUser());
        return ReturnEntityUtil.success();
    }

    @RequestMapping("/updateStage")
    @ResponseBody
    @ApiOperation(value = "??????????????????????????????", httpMethod = "POST")
    public ReturnEntity updateStage(@ApiParam(name = "jlxh", value = "????????????(??????)", required = true)
                                    @RequestParam Integer jlxh, @ApiParam(name = "zxpb", value = "????????????", required = true)
                                    @RequestParam Integer zxpb) {
        nurseworkSer.doUpdateStage(jlxh, zxpb);
        return ReturnEntityUtil.success();
    }


    @RequestMapping("/queryTransferableBeds")
    @ResponseBody
    @ApiOperation(value = "??????????????????-??????", httpMethod = "POST")
    public ReturnEntity<PageInfo<ZybrCwszResp>> queryTransferableBeds(ImCwszQueryReq imCwszQueryReq,
                                                                      PageQuery page) {
        imCwszQueryReq.setJgid(this.getUser().getHospitalId());
        PageInfo<ZybrCwszResp> pageInfo = PageHelper.startPage(
                page.getPageNum(), page.getPageSize()).doSelectPageInfo(
                () -> imCwszSer.getEntityMapper().queryTransferableBeds(imCwszQueryReq)
        );
        return ReturnEntityUtil.success(pageInfo);
    }

    @RequestMapping("/queryYzXmNotSureOrExcutedInfo")
    @ResponseBody
    @ApiOperation(value = "?????????????????????????????????????????????????????????????????????????????????????????????-??????", httpMethod = "POST")
    public ReturnEntity<PageInfo<CisHzyz>> queryYzXmNotSureOrExcutedInfo(@ApiParam(name = "yplx", value = "???????????????", required = false)
                                                                         @RequestParam(value = "yplx", required = false) Integer yplx, @ApiParam(name = "zyh", value = "?????????", required = true)
                                                                         @RequestParam Integer zyh, PageQuery page) {
        PageInfo<CisHzyz> pageInfo = PageHelper.startPage(
                page.getPageNum(), page.getPageSize()).doSelectPageInfo(
                () -> cisHzyzSer.getEntityMapper().queryYzXmNotSureOrExcutedInfo(zyh,
                        this.getUser().getHospitalId(), yplx)
        );
        return ReturnEntityUtil.success(pageInfo);
    }

    @RequestMapping("/queryAntidoteNotSureOrExcutedInfo")
    @ResponseBody
    @ApiOperation(value = "???????????????????????????????????????????????????-??????", httpMethod = "POST")
    public ReturnEntity<PageInfo<NisTymxResp>> queryAntidoteNotSureOrExcutedInfo(@ApiParam(name = "zyh", value = "?????????", required = true)
                                                                                 @RequestParam Integer zyh, PageQuery page) {
        PageInfo<NisTymxResp> pageInfo = PageHelper.startPage(
                page.getPageNum(), page.getPageSize()).doSelectPageInfo(
                () -> nisTymxSer.getEntityMapper().queryAntidoteNotSureOrExcutedInfo(zyh,
                        this.getUser().getHospitalId())
        );
        return ReturnEntityUtil.success(pageInfo);
    }

    @RequestMapping("/queryDrugWithdrawalApplicationList")
    @ResponseBody
    @ApiOperation(value = "?????????????????????????????????-??????", httpMethod = "POST")
    public ReturnEntity<PageInfo<NisTymxResp>> queryDrugWithdrawalApplicationList(@ApiParam(name = "zyh", value = "?????????", required = true)
        @RequestParam Integer zyh, @ApiParam(name = "beginDate", value = "????????????(??????)", required = true)
        @RequestParam String beginDate, @ApiParam(name = "endDate", value = "????????????(??????)", required = true)
        @RequestParam String endDate, PageQuery page) {
        PageInfo<NisTymxResp> pageInfo = PageHelper.startPage(
                page.getPageNum(), page.getPageSize()).doSelectPageInfo(
                () -> nisTymxSer.getEntityMapper().queryDrugWithdrawalApplicationList(zyh, beginDate, endDate)
        );
        return ReturnEntityUtil.success(pageInfo);
    }


    @RequestMapping("/queryRefundableProject")
    @ResponseBody
    @ApiOperation(value = "??????????????????", httpMethod = "POST")
    public ReturnEntity<List<ImFeeFymxProjectResp>> queryRefundableProject(@Valid @RequestBody NisRefundableProjectReq req) {
        return ReturnEntityUtil.success(imFeeFymxSer.getEntityMapper().queryRefundableProject(req));
    }

    @RequestMapping("/saveRefundClinic")
    @ResponseBody
    @ApiOperation(value = "??????????????????", httpMethod = "POST")
    public ReturnEntity saveRefundClinic(@RequestBody List<ImFeeFymxQueryReq> imFeeFymxQueryReqList) {
        List<Integer> intList = nurseworkSer.doSaveRefundClinic(imFeeFymxQueryReqList, this.getUser());
        if(!intList.isEmpty()) {
            try {
                mdiHrpService.hiscubebzejkfpush(intList, 1);
            } catch (Exception e) {
                logger.error(e.getMessage(), e);
            }
        }
        return ReturnEntityUtil.success();
    }

    @RequestMapping("/doLoadDetailByZtBH")
    @ResponseBody
    @ApiOperation(value = "???????????????????????????????????????", httpMethod = "POST")
    public ReturnEntity<List<OpZt02DetailResp>> loadDetailByZtBH(@ApiParam(name = "ztbh", value = "????????????", required = true)
                                                                 @RequestParam Integer ztbh, @ApiParam(name = "brxz", value = "????????????", required = true)
                                                                 @RequestParam Integer brxz) {
        return ReturnEntityUtil.success(nurseworkSer.doLoadDetailByZtBH(ztbh, brxz));
    }

    @RequestMapping("/queryExpenseAccountingFymx")
    @ResponseBody
    @ApiOperation(value = "???????????????????????????????????????", httpMethod = "POST")
    public ReturnEntity<PageInfo<ImFeeFymx>> queryExpenseAccountingFymx(@ApiParam(name = "zyh", value = "?????????", required = true)
                                                                        @RequestParam Integer zyh, PageQuery page) {
        ImFeeFymx imFeeFymx = new ImFeeFymx();
        //????????????????????????????????????
        imFeeFymx.setZyh(zyh);
        imFeeFymx.setXmlx(JzlxEnum.IN_HOSPITAL.getValue());
        imFeeFymx.setJscs(0);
        imFeeFymx.setSortColumns("JFRQ desc");

        //??????????????????????????????????????????
        SysXtcs sysXtcs = sysXtcsCacheSer.getByJGIdAndCsmc(this.getUser().getHospitalId(), SysXtcsCsmcCts.SFQYYPJZZF);
        String sfqy = sysXtcs == null ? "0" : sysXtcs.getCsz();
        if ("0".equals(sfqy)) {
            imFeeFymx.setYplx(0);
        }
        PageInfo<ImFeeFymx> pageInfo = PageHelper.startPage(
                page.getPageNum(), page.getPageSize()).doSelectPageInfo(
                () -> imFeeFymxSer.findByEntity(imFeeFymx)
        );
        return ReturnEntityUtil.success(pageInfo);
    }

/*    @RequestMapping("/queryVoidedList")
    @ResponseBody
    @ApiOperation(value = "??????????????????????????????", httpMethod = "POST")
    public ReturnEntity<PageInfo<ImFeeFymx>> queryVoidedList(@ApiParam(name = "zyh", value = "?????????", required = true)
                                                             @RequestParam Integer zyh, PageQuery page) {
        PageInfo<ImFeeFymx> pageInfo = PageHelper.startPage(
                page.getPageNum(), page.getPageSize()).doSelectPageInfo(
                () -> imFeeFymxSer.getEntityMapper().queryVoidedList(zyh,
                        this.getUser().getHospitalId())
        );
        return ReturnEntityUtil.success(pageInfo);
    }*/


    @RequestMapping("/inquiryBookkeeping")
    @ResponseBody
    @ApiOperation(value = "??????????????????-??????", httpMethod = "POST")
    public ReturnEntity<PageInfo<ImFeeFymxAccountingResp>> inquiryBookkeeping(ImFeeFymxAccountingReq imFeeFymxAccountingReq,
                                                                              PageQuery page) {
        imFeeFymxAccountingReq.setJgid(this.getUser().getHospitalId());
        PageInfo<ImFeeFymxAccountingResp> pageInfo = PageHelper.startPage(
                page.getPageNum(), page.getPageSize()).doSelectPageInfo(
                () -> imFeeFymxSer.getEntityMapper().inquiryBookkeeping(imFeeFymxAccountingReq)
        );
        return ReturnEntityUtil.success(pageInfo);
    }

    @RequestMapping("/queryNisFyyfInfo")
    @ResponseBody
    @ApiOperation(value = "????????????????????????", httpMethod = "POST")
    public ReturnEntity<List<NisFyyf>> queryNisFyyfInfo(@ApiParam(name = "bqdm", value = "????????????", required = true)
                                                        @RequestParam Integer bqdm, PageQuery page) {
        NisFyyf nisFyyf = new NisFyyf();
        nisFyyf.setJgid(getUser().getHospitalId());
        nisFyyf.setGnfl(4);
        nisFyyf.setBqdm(ObjectToTypes.parseString(bqdm));
        nisFyyf.setSortColumns("TYPE asc");
        return ReturnEntityUtil.success(nisFyyfSer.findByEntity(nisFyyf));
    }

    @GetMapping("/costAccountingDetailFile")
    @ApiOperation(value = "????????????????????????")
    public void costAccountingDetailFile(@RequestParam("reqStr") String reqStr, HttpServletResponse response) {
        ImFeeFymxAccountingReq imFeeFymxAccountingReq = JSONUtil.toBean(reqStr, ImFeeFymxAccountingReq.class);
        imFeeFymxAccountingReq.setJgid(this.getUser().getHospitalId());
        List<ImFeeFymxAccountingResp> respList = imFeeFymxSer.getEntityMapper().inquiryBookkeeping(imFeeFymxAccountingReq);
        List<Map<String, Object>> listMap = BUHISUtil.ListObjToMap(respList);
        for (Map<String, Object> map : listMap) {
            String ksmc = dicKszdOutSer.getNameById(ObjectToTypes.parseInt(map.get("FYKS")));
            map.put("KSMC", ksmc);
        }
        Map<String, Object> map = nurseworkSer.getCostAccountingDetailParameters(listMap, this.getUser());
        String jasperName = "jrxml/CostAccountingDetail.jasper";
        if (CollectionUtils.isNotEmpty(listMap)) {
            iReportExportFileSer.reportHtmlForStream(listMap, map, jasperName, response);
        }
    }

    @GetMapping("/detailChargeQueryPrintFile")
    @ApiOperation(value = "????????????????????????")
    public void detailChargeQueryPrintFile(@RequestParam("reqStr") String reqStr, HttpServletResponse response) {
        CisHzyzorztExcuteReq cisHzyzorztExcuteReq = JSONUtil.toBean(reqStr, CisHzyzorztExcuteReq.class);
        List<Map<String, Object>> list = nurseworkSer.getDetailChargeQueryFiles(cisHzyzorztExcuteReq, this.getUser());
        String jasperName = "jrxml/AdditionalProjects.jasper";
        Map<String, Object> map = new HashMap<String, Object>(16);
        map.put("TITLE", this.getUser().getHospitalName() + "?????????????????????");
        if ("XM".equals(cisHzyzorztExcuteReq.getPrintWay())) {
            jasperName = "jrxml/WardCheckSetXm.jasper";
        } else if ("BR".equals(cisHzyzorztExcuteReq.getPrintWay())) {
            jasperName = "jrxml/WardCheckSetBr.jasper";
        }
        iReportExportFileSer.reportHtmlForStream(list, map, jasperName, response);
    }

    @RequestMapping("/queryOperationNotArrange")
    @ResponseBody
    @ApiOperation(value = "???????????????????????????????????????-??????", httpMethod = "POST")
    public ReturnEntity<PageInfo<CisHzyzOperationResp>> queryOperationNotArrange(@ApiParam(name = "zyh", value = "?????????", required = true)
                                                                                 @RequestParam Integer zyh, PageQuery page) {
        PageInfo<CisHzyzOperationResp> pageInfo = PageHelper.startPage(
                page.getPageNum(), page.getPageSize()).doSelectPageInfo(
                () -> cisHzyzSer.getEntityMapper().queryOperationNotArrange(zyh,
                        this.getUser().getHospitalId())
        );
        return ReturnEntityUtil.success(pageInfo);
    }

    @RequestMapping("/queryTurningBackDetail")
    @ResponseBody
    @ApiOperation(value = "????????????????????????", httpMethod = "POST")
    public ReturnEntity<List<NisTj02BackDetailResp>> queryTurningBackDetail(@ApiParam(name = "yzxh", value = "????????????", required = true)
        @RequestParam Integer yzxh, @ApiParam(name = "beginDate", value = "????????????(??????)", required = true)
    @RequestParam String beginDate, @ApiParam(name = "endDate", value = "????????????(??????)", required = true)
    @RequestParam String endDate) {
        return ReturnEntityUtil.success(nisTj02Ser.getEntityMapper().queryTurningBackDetail(yzxh,
                this.getUser().getHospitalId(), null, beginDate, endDate));
    }

    @RequestMapping("/deleteBackApplication")
    @ResponseBody
    @ApiOperation(value = "??????????????????????????????", httpMethod = "POST")
    public ReturnEntity deleteBackApplication(@ApiParam(name = "jlxh", value = "????????????????????????", required = true)
                                              @RequestParam Integer jlxh) {
        nurseworkSer.deleteBackApplication(jlxh);
        return ReturnEntityUtil.success();
    }

    @RequestMapping("/uploadLisData")
    @ResponseBody
    @ApiOperation(value = "????????????????????????", httpMethod = "POST")
    public ReturnEntity uploadLisData(@RequestBody List<Integer> list) {
        nurseworkSer.uploadLisData(list, this.getUser());
        return ReturnEntityUtil.success();
    }
	
	@RequestMapping("/doQueryClinicalXm")
    @ResponseBody
    @ApiOperation(value = "???????????????????????????????????????", httpMethod = "POST")
    public ReturnEntity<PageInfo<ImFeeFymxYjJyResp>> doQueryClinicalXm(ImFeeFymxYjJyReq req) {
        req.setJgid(this.getUser().getHospitalId());
        req.setYzlx(1);
        PageInfo<ImFeeFymxYjJyResp> pageInfo = PageHelper.startPage(
        		req.getPageNum(), req.getPageSize()).doSelectPageInfo(
                () -> imFeeFymxYjSer.getEntityMapper().doQueryClinicalXm(req)
        );
        return ReturnEntityUtil.success(pageInfo);
    }
	
	@RequestMapping("/cancelLisData")
    @ResponseBody
    @ApiOperation(value = "??????????????????", httpMethod = "POST")
    public ReturnEntity cancelLisData(@RequestBody List<Integer> list) {
		nurseworkSer.cancelLisData(list, this.getUser());
        return ReturnEntityUtil.success();
    }
	
	@RequestMapping("/queryAfterCyIsExistCost")
    @ResponseBody
    @ApiOperation(value = "???????????????????????????-??????", httpMethod = "POST")
    public ReturnEntity<PageInfo<ImFeeFymxCostResp>> queryAfterCyIsExistCost(@ApiParam(name = "zyh", value = "?????????", required = true)
        @RequestParam Integer zyh, @ApiParam(name = "cyrq", value = "?????????", required = true)
        @RequestParam Timestamp cyrq, PageQuery page) {
        PageInfo<ImFeeFymxCostResp> pageInfo = PageHelper.startPage(
                page.getPageNum(), page.getPageSize()).doSelectPageInfo(
                () -> imFeeFymxSer.getEntityMapper().queryAfterCyIsExistCost(
                        this.getUser().getHospitalId(), zyh, cyrq)
        );
        return ReturnEntityUtil.success(pageInfo);
    }
	
	@RequestMapping("/queryXmPrint")
    @ResponseBody
    @ApiOperation(value = "?????????????????????", httpMethod = "POST")
    public ReturnEntity<List<ImFeeFymxYjPrintResp>> queryXmPrint(@RequestBody ImFeeFymxYjPrintReq req) {
        req.setJgid(this.getUser().getHospitalId());
        return ReturnEntityUtil.success(imFeeFymxYjSer.getEntityMapper().queryXmPrint(req));
    }
	
	@GetMapping("/queryXmPrintFile")
    @ApiOperation(value = "?????????????????????????????????")
    public void queryXmPrint(@RequestParam("reqStr") String reqStr, HttpServletResponse response) {
		ImFeeFymxYjPrintReq req = JSONUtil.toBean(reqStr, ImFeeFymxYjPrintReq.class);
		req.setJgid(this.getUser().getHospitalId());
        List<Map<String, Object>> list = nurseworkSer.queryXmPrintFiles(req, this.getUser());
        String jasperName = "jrxml/ExecutedProjectsPrint.jasper";
        Map<String, Object> map = new HashMap<String, Object>(16);
        map.put("TITLE", this.getUser().getHospitalName() + "???????????????");
        iReportExportFileSer.reportHtmlForStream(list, map, jasperName, response);
    }

    @RequestMapping("/queryCostDetailInfo")
    @ResponseBody
    @ApiOperation(value = "????????????????????????????????????", httpMethod = "POST")
    public ReturnEntity<PageInfo<CisHzyz>> queryCostDetailInfo( @ApiParam(name = "zyh", value = "?????????", required = true)
                                                                         @RequestParam Integer zyh, PageQuery page) {
        PageInfo<CisHzyz> pageInfo = PageHelper.startPage(
                page.getPageNum(), page.getPageSize()).doSelectPageInfo(
                () -> cisHzyzSer.getEntityMapper().queryCostDetailInfo(zyh)
        );
        return ReturnEntityUtil.success(pageInfo);
    }
    
    @RequestMapping("/queryDoctorReviewPatientInfo")
    @ResponseBody
    @ApiOperation(value = "????????????????????????????????????", httpMethod = "POST")
    public ReturnEntity<List<CisHzyzReviewPatientResp>> queryDoctorReviewPatientInfo(@RequestBody CisHzyzReviewBatchReq req) {
        req.setJgid(this.getUser().getHospitalId());
        return ReturnEntityUtil.success(nurseworkSer.queryDoctorReviewPatientInfo(req));
    }
    
    @RequestMapping("/stopOrderReview")
    @ResponseBody
    @ApiOperation(value = "???????????????????????????????????????", httpMethod = "POST")
    public ReturnEntity stopOrderReview(@Valid @RequestBody CisHzyzStopReviewReq req) {
    	nurseworkSer.stopOrderReview(req, this.getUser());
        return ReturnEntityUtil.success();
    }
}
