
package com.buit.cis.dctwork.controller;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson.JSONObject;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;
import java.util.Map;

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
import com.buit.apply.request.CisJcsq01QueryReq;
import com.buit.apply.response.CisJcsq01QueryResp;
import com.buit.apply.service.Cisjcsqd01Service;
import com.buit.cis.dctwork.model.CisHzyz;
import com.buit.cis.dctwork.model.CisHzyzZt;
import com.buit.system.model.DicKszd;
import com.buit.cis.dctwork.request.*;
import com.buit.cis.dctwork.response.*;
import com.buit.cis.dctwork.service.CisHzyzSer;
import com.buit.cis.dctwork.service.CisHzyzZtSer;
import com.buit.cis.ims.enums.OperateLogCategoryEnum;
import com.buit.cis.ims.enums.OperateLogDocPrintStatusEnum;
import com.buit.cis.ims.enums.OperateLogDocPrintTypeEnum;
import com.buit.cis.ims.model.ImHzry;
import com.buit.cis.ims.model.ImOperateLog;
import com.buit.cis.ims.response.ImHzryYpSumbitResp;
import com.buit.cis.ims.service.ImHzrySer;
import com.buit.cis.ims.service.ImOperateLogSer;
import com.buit.cis.ims.service.ImRyzdSer;
import com.buit.cis.nurse.request.CisHzyzJySqdReq;
import com.buit.commons.BaseSpringController;
import com.buit.commons.PageQuery;
import com.buit.commons.SysUser;
import com.buit.constans.TableName;
import com.buit.file.IReportExportFileSer;
import com.buit.mms.cmo.response.IOptSssqResp;
import com.buit.mms.cmo.service.OptSssqService;
import com.buit.system.response.DiccLdxmglApiResp;
import com.buit.system.service.DicKszdOutSer;
import com.buit.utill.DateUtils;
import com.buit.utill.RedisFactory;
import com.buit.utill.ReturnEntity;
import com.buit.utill.ReturnEntityUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.stream.Collectors;

/**
 * ??????_????????????<br>
 *
 * @author GONGFANGZHOU
 */
@Api(tags = "??????_????????????")
@Controller
@RequestMapping("/cishzyz")
public class CisHzyzCtr extends BaseSpringController {

    static final Logger logger = LoggerFactory.getLogger(CisHzyzCtr.class);

    @Autowired
    private CisHzyzSer cisHzyzSer;

    @Autowired
    private CisHzyzZtSer cisHzyzZtSer;

    @DubboReference
    private OptSssqService optSssqService;

    @DubboReference
    private Cisjcsqd01Service cisjcsqd01Service;

    @Autowired
    private IReportExportFileSer iReportExportFileSer;

    @Autowired
    private ImHzrySer imHzrySer;

    @Autowired
    private ImRyzdSer imRyzdSer;

    @DubboReference
    private DicKszdOutSer dicKszdOutSer;

    @Autowired
    private ImOperateLogSer imOperateLogSer;

    @Autowired
    private RedisFactory redisFactory;

    @RequestMapping("/getDoctorAdviceBrQuery")
    @ResponseBody
    @ApiOperation(value = "????????????????????????????????????", httpMethod = "POST")
    public ReturnEntity<List<ImHzryYpSumbitResp>> getDoctorAdviceBrQuery(@RequestBody CisHzyzYpSubmitReq cisHzyzYpSubmitReq) {
        return ReturnEntityUtil.success(cisHzyzSer.getDoctorAdviceBrQuery(cisHzyzYpSubmitReq, this.getUser()));
    }

    @RequestMapping("/getDoctorAdviceSubmitQuery")
    @ResponseBody
    @ApiOperation(value = "??????????????????????????????????????????", httpMethod = "POST")
    public ReturnEntity<List<CisHzyzYpSubmitResp>> getDoctorAdviceSubmitQuery(@RequestBody CisHzyzYpSubmitReq cisHzyzYpSubmitReq) {
        return ReturnEntityUtil.success(cisHzyzSer.doGetDoctorAdviceSubmitQuery(cisHzyzYpSubmitReq, this.getUser()));
    }

    @RequestMapping("/doctorAdviceQueryVerification")
    @ResponseBody
    @ApiOperation(value = "?????????????????????????????????????????????", httpMethod = "POST")
    public ReturnEntity doctorAdviceQueryVerification(@ApiParam(name = "lyrq", value = "????????????", required = true)
                                                      @RequestParam Date lyrq, @ApiParam(name = "brbq", value = "????????????", required = true)
                                                      @RequestParam Integer brbq) {
        cisHzyzSer.doDoctorAdviceQueryVerification(brbq, lyrq, this.getUser());
        return ReturnEntityUtil.success();
    }

    @RequestMapping("/checkInventoryInpatientPharmacy")
    @ResponseBody
    @ApiOperation(value = "???????????????????????????????????????", httpMethod = "POST")
    public ReturnEntity<String> checkInventoryInpatientPharmacy(@RequestBody
                                                                        List<NisTjSureReq> nisTjSureReqList) {
        return ReturnEntityUtil.success(cisHzyzSer.doCheckInventoryInpatientPharmacy(nisTjSureReqList));
    }

    @RequestMapping("/saveDoctorAdviceSubmit")
    @ResponseBody
    @ApiOperation(value = "????????????????????????", httpMethod = "POST")
    public ReturnEntity<NisTjMsgResp> saveDoctorAdviceSubmit(@RequestBody
                                                                     CisHzyzYpSubmitReq cisHzyzYpSubmitReq) {
        return ReturnEntityUtil.success(cisHzyzSer.doSaveDoctorAdviceSubmit(cisHzyzYpSubmitReq, this.getUser()));
    }

    @RequestMapping("/sssThqr")
    @ResponseBody
    @ApiOperation(value = "????????????????????????", httpMethod = "POST")
    public ReturnEntity sssThqr(@ApiParam(name = "jlxh", value = "????????????", required = true)
                                @RequestParam Integer jlxh, @ApiParam(name = "fymxjlxh", value = "fymx????????????", required = true)
                                @RequestParam Integer fymxjlxh) {
        cisHzyzSer.doSssThqr(jlxh, fymxjlxh, this.getUser());
        return ReturnEntityUtil.success();
    }

    @RequestMapping("/loadMedicineInfo")
    @ResponseBody
    @ApiOperation(value = "????????????????????????????????????????????????", httpMethod = "POST")
    public ReturnEntity<CisHzyzCommonResp> loadMedicineInfo(CisHzyzCommonReq cisHzyzCommonReq) {
        return ReturnEntityUtil.success(cisHzyzSer.doLoadMedicineInfo(cisHzyzCommonReq, this.getUser()));
    }

    @RequestMapping("/loadAdviceSet")
    @ResponseBody
    @ApiOperation(value = "????????????????????????", httpMethod = "POST")
    public ReturnEntity<List<CisHzyzCommonResp>> loadAdviceSet(CisHzyzStackReq cisHzyzStackReq) {
        return ReturnEntityUtil.success(cisHzyzSer.doLoadAdviceSet(cisHzyzStackReq, this.getUser()));
    }

    @RequestMapping("/loadClinicInfo")
    @ResponseBody
    @ApiOperation(value = "????????????????????????????????????", httpMethod = "POST")
    public ReturnEntity<CisHzyzProjectResp> loadClinicInfo(CisHzyzProjectReq cisHzyzProjectReq) {
        return ReturnEntityUtil.success(cisHzyzSer.doLoadClinicInfo(cisHzyzProjectReq, this.getUser()));
    }

    @RequestMapping("/saveWarDisposalSet")
    @ResponseBody
    @ApiOperation(value = "????????????????????????", httpMethod = "POST")
    public ReturnEntity<Integer> saveWarDisposalSet(@RequestBody CisHzyzXmImportReq cisHzyzXmImportReq) {
        return ReturnEntityUtil.success(cisHzyzSer.doSaveWarDisposalSet(cisHzyzXmImportReq, this.getUser()));
    }

    @RequestMapping("/loadFromPersonalSet")
    @ResponseBody
    @ApiOperation(value = "??????????????????????????????", httpMethod = "POST")
    public ReturnEntity<CisHzyzZt> loadFromPersonalSet(@ApiParam(name = "jlxh", value = "????????????", required = true)
                                                       @RequestParam Integer jlxh) {
        return ReturnEntityUtil.success(cisHzyzZtSer.getEntityMapper().getById(jlxh));
    }


    @RequestMapping("/loadFromUSLTPersonalSet")
    @ResponseBody
    @ApiOperation(value = "?????????????????????????????????", httpMethod = "POST")
    public ReturnEntity<List<CisHzyzChooseProjectResp>> loadFromUSLTPersonalSet(@RequestBody
                                                                                        CisHzyzChooseProjectReq cisHzyzChooseProjectReq) {
        return ReturnEntityUtil.success(cisHzyzSer.doLoadFromUsltPersonalSet(cisHzyzChooseProjectReq,
                this.getUser()));
    }

    @RequestMapping("/saveZySqd")
    @ResponseBody
    @ApiOperation(value = "?????????????????????", httpMethod = "POST")
    public ReturnEntity<Integer> saveZySqd(@RequestBody
                                                   CisHzyzJcxmReq cisHzyzJcxmReq) {
        return ReturnEntityUtil.success(cisHzyzSer.saveZySqd(cisHzyzJcxmReq,
                this.getUser()));
    }

    @RequestMapping("/loadAmqcCount")
    @ResponseBody
    @ApiOperation(value = "????????????????????????????????????", httpMethod = "POST")
    public ReturnEntity<BigDecimal> loadAmqcCount(@ApiParam(name = "zyh", value = "?????????", required = true)
                                                  @RequestParam Integer zyh, @ApiParam(name = "ypxh", value = "????????????", required = true)
                                                  @RequestParam Integer ypxh, @ApiParam(name = "kssj", value = "????????????(y-m-d)", required = true)
                                                  @RequestParam String kssj) {
        return ReturnEntityUtil.success(cisHzyzSer.doLoadAmqcCount(zyh, ypxh, kssj));
    }
    
	@RequestMapping("/updateSkinTest")
    @ResponseBody
    @ApiOperation(value="????????????????????????" ,httpMethod="POST")
    public ReturnEntity updateSkinTest(@Valid CisHzyzSkinReq req){
        cisHzyzSer.updateSkinTestByJlxh(req, this.getUser().getHospitalId());
        return ReturnEntityUtil.success();
    }

    @RequestMapping("/getAllYfs")
    @ResponseBody
    @ApiOperation(value = "?????????????????????????????????????????????????????????", httpMethod = "POST")
    public ReturnEntity<CisHzyzYflbResp> getAllYfs(@ApiParam(name = "bqdm", value = "????????????", required = true)
                                                   @RequestParam Integer bqdm) {
        return ReturnEntityUtil.success(cisHzyzSer.doGetAllYfs(bqdm, this.getUser()));
    }

    @RequestMapping("/getDrugReturn")
    @ResponseBody
    @ApiOperation(value = "??????????????????-??????", httpMethod = "POST")
    public ReturnEntity<PageInfo<NisTj02Resp>> getDrugReturn(NisTj02Req nisTj02Req, PageQuery page) {
        PageInfo<NisTj02Resp> pageInfo = PageHelper.startPage(
                page.getPageNum(), page.getPageSize()).doSelectPageInfo(
                () -> cisHzyzSer.getDrugReturn(nisTj02Req)
        );
        return ReturnEntityUtil.success(pageInfo);
    }

    @RequestMapping("/saveZyJySqd")
    @ResponseBody
    @ApiOperation(value = "???????????????????????????", httpMethod = "POST")
    public ReturnEntity saveZyJySqd(@RequestBody CisHzyzJySqdReq cisHzyzJySqdReq) {
        cisHzyzSer.saveZyJySqd(cisHzyzJySqdReq, this.getUser());
        return ReturnEntityUtil.success();
    }

    @RequestMapping("/queryAdditionalAdvice")
    @ResponseBody
    @ApiOperation(value = "??????????????????????????????????????????", httpMethod = "POST")
    public ReturnEntity<List<CisHzyz>> queryAdditionalAdvice(@ApiParam(name = "lsyz", value = "????????????", required = true)
                                                             @RequestParam Integer lsyz, @ApiParam(name = "zyh", value = "?????????", required = true)
                                                             @RequestParam Integer zyh) {
        CisHzyz cisHzyz = new CisHzyz();
        cisHzyz.setLsyz(lsyz);
        cisHzyz.setZyh(zyh);
        cisHzyz.setJgid(this.getUser().getHospitalId());
        return ReturnEntityUtil.success(cisHzyzSer.getEntityMapper().queryAdditionalAdvice(cisHzyz));
    }

    @RequestMapping("/loadAppendAdvice")
    @ResponseBody
    @ApiOperation(value = "???????????????????????????????????????", httpMethod = "POST")
    public ReturnEntity<List<DiccLdxmglApiResp>> doLoadAppendAdvice(@ApiParam(name = "fyxh", value = "????????????", required = true)
                                                                    @RequestParam Integer fyxh, @ApiParam(name = "ksdm", value = "????????????", required = true)
                                                                    @RequestParam Integer ksdm, @ApiParam(name = "mzsy", value = "??????????????????", required = false)
                                                                    @RequestParam(value = "mzsy", required = false) String mzsy) {
        return ReturnEntityUtil.success(cisHzyzSer.doLoadAppendAdvice(fyxh, ksdm, mzsy, this.getUser()));
    }


    @RequestMapping("/queryZyJySqdList")
    @ResponseBody
    @ApiOperation(value = "????????????????????????-??????", httpMethod = "POST")
    public ReturnEntity<PageInfo<CisHzyzZtQueryResp>> queryZyJySqdList(CisHzyzZtQueryReq cisHzyzZtQueryReq, PageQuery page) {
        cisHzyzZtQueryReq.setYzlx(1);
        PageInfo<CisHzyzZtQueryResp> pageInfo = PageHelper.startPage(
                page.getPageNum(), page.getPageSize()).doSelectPageInfo(
                () -> cisHzyzZtSer.getEntityMapper().queryZyJySqdList(cisHzyzZtQueryReq)
        );
        return ReturnEntityUtil.success(pageInfo);
    }

    @RequestMapping("/queryDicZlxmInfo")
    @ResponseBody
    @ApiOperation(value = "????????????id????????????????????????", httpMethod = "POST")
    public ReturnEntity<CisJcsq01ZlxmResp> queryDicZlxmInfo(@ApiParam(name = "sqid", value = "??????id", required = true)
                                                            @RequestParam Integer sqid) {
        return ReturnEntityUtil.success(cisHzyzSer.queryDicZlxmInfo(sqid, this.getUser()));
    }


    @RequestMapping("/checkYpZtKc")
    @ResponseBody
    @ApiOperation(value = "??????????????????????????????", httpMethod = "POST")
    public ReturnEntity<List<String>> checkYpZtKc(@RequestBody CisHzyzCheckKcReq cisHzyzCheckKcReq) {
        return ReturnEntityUtil.success(cisHzyzSer.checkYpZtKc(cisHzyzCheckKcReq, this.getUser()));
    }

    @RequestMapping("/querySssqInfo")
    @ResponseBody
    @ApiOperation(value = "???????????????????????????", httpMethod = "POST")
    public ReturnEntity<IOptSssqResp> querySssqInfo(@ApiParam(name = "sqid", value = "??????id", required = true)
                                                    @RequestParam Integer sqid) {
        return ReturnEntityUtil.success(optSssqService.getBySqdh(sqid));
    }

    @RequestMapping("/queryHerbInfo")
    @ResponseBody
    @ApiOperation(value = "???????????????????????????", httpMethod = "POST")
    public ReturnEntity<List<CisHzyzHerbResp>> queryHerbInfo(@ApiParam(name = "zyh", value = "?????????", required = true)
                                                             @RequestParam Integer zyh, @ApiParam(name = "yzzh", value = "????????????", required = true)
                                                             @RequestParam Integer yzzh) {
        return ReturnEntityUtil.success(cisHzyzSer.getEntityMapper().queryHerbInfo(zyh, yzzh));
    }


    @GetMapping("/doctorAdviceSubmitFile")
    @ApiOperation(value = "??????????????????????????????")
    public void doctorAdviceSubmitFile(@RequestParam("reqStr") String reqStr, HttpServletResponse response) {
        CisHzyzYpSubmitReq cisHzyzYpSubmitReq = JSONUtil.toBean(reqStr, CisHzyzYpSubmitReq.class);
        Map<String, Object> map = cisHzyzSer.getAdviceSubmitParameters(cisHzyzYpSubmitReq.getLsyz(), this.getUser());
        List<Map<String, Object>> list = cisHzyzSer.getAdviceSubmitFields(cisHzyzYpSubmitReq, this.getUser());
        String jasperName = "jrxml/DoctorAdviceSubmit.jasper";
        if (CollectionUtils.isNotEmpty(list)) {
            iReportExportFileSer.reportHtmlForStream(list, map, jasperName, response);
        }
    }

    @RequestMapping("/updateAntibioticsStatus")
    @ResponseBody
    @ApiOperation(value = "??????????????????????????????", httpMethod = "POST")
    public ReturnEntity updateAntibioticsStatus(@RequestBody List<CisHzyzAntibioticsReq> list) {
        cisHzyzSer.updateAntibioticsStatus(list);
        return ReturnEntityUtil.success();
    }

    @RequestMapping("/checkAntimicrobialRights")
    @ResponseBody
    @ApiOperation(value = "?????????????????????????????????????????????", httpMethod = "POST")
    public ReturnEntity<String> checkAntimicrobialRights(@RequestBody CisHzyzCheckAntimicrobialReq req) {
        return ReturnEntityUtil.success(cisHzyzSer.checkAntimicrobialRights(req));
    }

    @RequestMapping("/queryZyJcSqdList")
    @ResponseBody
    @ApiOperation(value = "???????????????????????????-??????", httpMethod = "POST")
    public ReturnEntity<PageInfo<CisJcsq01QueryResp>> queryZyJcSqdList(CisJcsq01QueryReq cisJcsq01QueryReq, PageQuery page) {
        PageInfo<CisJcsq01QueryResp> pageInfo = cisjcsqd01Service.queryZyJcSqdList(cisJcsq01QueryReq, page);
        List<CisJcsq01QueryResp> list = pageInfo.getList();
        if (CollectionUtil.isNotEmpty(list)) {
            for (CisJcsq01QueryResp resp : list) {
                resp.setDybz(imOperateLogSer.getLastPrintStatus(TableName.CIS_JCSQ01, resp.getJcxh()));
            }
        }
        pageInfo.setList(list);
        return ReturnEntityUtil.success(pageInfo);
    }

    @RequestMapping("/queryAllergicDrugsCode")
    @ResponseBody
    @ApiOperation(value = "??????????????????????????????", httpMethod = "POST")
    public ReturnEntity<Integer> queryAllergicDrugsCode(@ApiParam(name = "zyh", value = "?????????", required = true)
                                                        @RequestParam Integer zyh) {
        return ReturnEntityUtil.success(cisHzyzSer.queryAllergicDrugsCode(zyh));
    }

    @GetMapping("/hosCheckFormPrintFile")
    @ApiOperation(value = "?????????????????????")
    public void hosCheckFormPrintFile(@RequestParam("jcxh") Integer jcxh, HttpServletResponse response) {
        String jasperName = "jrxml/HosCheckForm.jasper";
        SysUser sysUser = this.getUser();
        Map<String, Object> map = imHzrySer.getEntityMapper()
                .queryPatientJcsqd(sysUser.getHospitalId(), jcxh);
        map.put("TITLE", this.getUser().getHospitalName() + "???????????????");
        iReportExportFileSer.reportHtmlForStream(map, jasperName, response);

        //??????????????????
        ImOperateLog log = new ImOperateLog();
        log.setJlxh(redisFactory.getTableKey(TableName.DB_NAME, TableName.IM_OPERATE_LOG));
        log.setCategory(OperateLogCategoryEnum.DOCUMENT_PRINT.getValue());
        log.setType(OperateLogDocPrintTypeEnum.EXAMINATION_ASSAY_PAPER.getValue());
        log.setStatus(OperateLogDocPrintStatusEnum.FIRST_PRINT.getValue());
        Integer zyh = (Integer) map.get("ZYH");
        log.setZyh(zyh);
        log.setDataTable(TableName.CIS_JCSQ01);
        log.setDataId(String.valueOf(jcxh));
        log.setOperateUser(sysUser.getUserId());
        log.setOperateTime(DateUtils.getNow());
        imOperateLogSer.insert(log);
    }

    @GetMapping("/hosCheckGuideFormPrintFile")
    @ApiOperation(value = "?????????????????????")
    public void hosCheckGuideFormPrintFile(@RequestParam("zyh") Integer zyh, @RequestParam("jcxhList") String jcxhList, HttpServletResponse response) {
        String jasperName = "jrxml/HosCheckGuideForm.jasper";
        ImHzry hzry = imHzrySer.getById(zyh);
        Map<String, Object> map = JSONObject.parseObject(JSONObject.toJSONString(hzry), Map.class);
        map.put("title", this.getUser().getHospitalName() + "???????????????");
        map.put("csny", DateUtils.formatToDate(hzry.getCsny()));
        if (1 == hzry.getBrxb()) {
            map.put("brxb", "???");
        } else if (2 == hzry.getBrxb()) {
            map.put("brxb", "???");
        } else {
            map.put("brxb", "??????");
        }
        DicKszd kszd = dicKszdOutSer.getById(hzry.getBrks());
        if (kszd != null) {
            map.put("brks", kszd.getOfficename());
        }
        DicKszd bqzd = dicKszdOutSer.getById(hzry.getBrbq());
        if (bqzd != null) {
            map.put("brbq", bqzd.getOfficename());
        }
//        List<ImRyzdModel> ryzdList = imRyzdSer.findListByZyhAndZxlb(zyh, 1);
//        if (CollectionUtil.isEmpty(ryzdList)) {
        map.put("cbzd", hzry.getMqzd());
//        }
//        map.put("cbzd", ryzdList.stream().map(ImRyzdModel::getZdmc).reduce("", (a, b) -> a + b));

        List<String> jcxhStrList = StrUtil.split(jcxhList, ',');
        List<Integer> ids = jcxhStrList.stream().map(r -> Integer.valueOf(r)).collect(Collectors.toList());
        List<CisJcsq01PrintResp> data = imHzrySer.findPatientCheckList(zyh, ids);
        List<Map<String, Object>> list = new ArrayList<>();
        if (CollectionUtil.isNotEmpty(data)) {
            for (CisJcsq01PrintResp resp : data) {
                list.add(BeanUtil.beanToMap(resp));
            }
        }
        iReportExportFileSer.reportHtmlForStream(list, map, jasperName, response);

        Integer userId = getUser().getUserId();
        Timestamp now = DateUtils.getNow();
        //??????????????????
        for (Integer jcxh : ids) {
            ImOperateLog log = new ImOperateLog();
            log.setJlxh(redisFactory.getTableKey(TableName.DB_NAME, TableName.IM_OPERATE_LOG));
            log.setCategory(OperateLogCategoryEnum.DOCUMENT_PRINT.getValue());
            log.setType(OperateLogDocPrintTypeEnum.EXAMINATION_ASSAY_PAPER.getValue());
            log.setStatus(OperateLogDocPrintStatusEnum.FIRST_PRINT.getValue());
            log.setZyh(zyh);
            log.setDataTable(TableName.CIS_JCSQ01);
            log.setDataId(String.valueOf(jcxh));
            log.setOperateUser(userId);
            log.setOperateTime(now);
            imOperateLogSer.insert(log);
        }
    }

    @RequestMapping("/queryMedicalAdviceInfoByZyh")
    @ResponseBody
    @ApiOperation(value = "??????????????????????????????", httpMethod = "POST")
    public ReturnEntity<List<CisHzyz>> queryMedicalAdviceInfoByZyh(@ApiParam(name = "zyh", value = "?????????", required = true)
                                                                   @RequestParam Integer zyh) {
        return ReturnEntityUtil.success(cisHzyzSer.queryMedicalAdviceInfoByZyh(zyh, this.getUser()));
    }

    @RequestMapping("/copyMedicalAdviceInfoHistoryToNew")
    @ResponseBody
    @ApiOperation(value = "??????????????????????????????", httpMethod = "POST")
    public ReturnEntity copyMedicalAdviceInfoHistoryToNew(@RequestBody CisHzyzCopyReq req) {
        cisHzyzSer.copyMedicalAdviceInfoHistoryToNew(req, this.getUser());
        return ReturnEntityUtil.success();
    }

    @RequestMapping("/queryBatchJcxhByZyh")
    @ResponseBody
    @ApiOperation(value = "?????????????????????????????????????????????", httpMethod = "POST")
    public ReturnEntity<List<CisJcsq01QueryResp>> queryBatchJcxhByZyh(@ApiParam(name = "zyh", value = "?????????", required = true)
                                                                      @RequestParam Integer zyh) {
        return ReturnEntityUtil.success(cisHzyzSer.queryBatchJcxhByZyh(zyh));
    }

    @RequestMapping("/queryWrittenOrdersMaxXh")
    @ResponseBody
    @ApiOperation(value = "??????????????????????????????", httpMethod = "POST")
    public ReturnEntity<Long> queryWrittenOrdersMaxXh() {
        return ReturnEntityUtil.success(cisHzyzSer.getEntityMapper().queryWrittenOrdersMaxXh(this.getUser().getHospitalId()));
    }

    @RequestMapping("/queryOrderSetList")
    @ResponseBody
    @ApiOperation(value = "?????????????????????????????????", httpMethod = "POST")
    public ReturnEntity<List<CisHzyzQueryResp>> queryOrderSetList(CisHzyzOrderSetReq cisHzyzOrderSetReq) {
        cisHzyzOrderSetReq.setJgid(this.getUser().getHospitalId());
        return ReturnEntityUtil.success(cisHzyzSer.getEntityMapper().queryOrderSetList(cisHzyzOrderSetReq));
    }

    @RequestMapping("/stopOrdersAndCheckAuthority")
    @ResponseBody
    @ApiOperation(value="?????????????????????????????????" ,httpMethod="POST")
    public ReturnEntity<String> stopOrdersAndCheckAuthority(@Valid @RequestBody List<CisHzyzStopCheckReq> list){
        return ReturnEntityUtil.success(cisHzyzSer.stopOrdersAndCheckAuthority(list, this.getUser()));
    }

    @RequestMapping("/cancelStopOrdersAndCheckAuthority")
    @ResponseBody
    @ApiOperation(value = "???????????????????????????????????????", httpMethod = "POST")
    public ReturnEntity<String> cancelStopOrdersAndCheckAuthority(@Valid @RequestBody List<CisHzyzStopCheckReq> list) {
        return ReturnEntityUtil.success(cisHzyzSer.cancelStopOrdersAndCheckAuthority(list, this.getUser()));
    }

    @RequestMapping("/deleteOrdersByJlxhList")
    @ResponseBody
    @ApiOperation(value = "??????????????????", httpMethod = "POST")
    public ReturnEntity deleteOrdersByJlxhList(@Valid @RequestBody List<CisHzyzDeteleReq> list) {
        cisHzyzSer.deleteOrdersByJlxhList(list);
        return ReturnEntityUtil.success();
    }

    
    @RequestMapping("/checkSkinTest")
    @ResponseBody
    @ApiOperation(value="??????????????????????????????" ,httpMethod="POST")
    public ReturnEntity<CisHzyzCheckSkinResp> checkSkinTest(@Valid CisHzyzCheckSkinReq req){
        return ReturnEntityUtil.success(cisHzyzSer.checkSkinTest(req, this.getUser().getHospitalId()));
    }

}

