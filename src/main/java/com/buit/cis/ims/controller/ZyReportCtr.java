package com.buit.cis.ims.controller;

import cn.hutool.json.JSONUtil;
import com.buit.cis.ims.request.*;
import com.buit.cis.ims.response.BqPatientReportAllResp;
import com.buit.cis.ims.response.DischargedPatientReportAllResp;
import com.buit.cis.ims.response.DoIsreckonResp;
import com.buit.cis.ims.response.InHospitalPatientReportAllResp;
import com.buit.commons.BaseSpringController;
import com.buit.commons.PageQuery;
import com.buit.file.IReportExportFileSer;
import com.buit.system.service.ExportFileSer;
import com.buit.utill.BeanUtil;
import com.buit.utill.ReturnEntity;
import com.buit.utill.ReturnEntityUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.dubbo.config.annotation.DubboReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 住院管理-报表<br>
 *
 * @author ZHOUHAISHENG
 */
@Api(tags = "住院管理-报表")
@Controller
@RequestMapping("/zyReport")
public class ZyReportCtr extends BaseSpringController {

    static final Logger logger = LoggerFactory.getLogger(ZyReportCtr.class);

    @Autowired
    private com.buit.cis.ims.service.ZyReportSer zyReportSer;
    @DubboReference
    private ExportFileSer exportFileSer;
    @Autowired
    private IReportExportFileSer iReportExportFileSer;

    /**
     * 住院管理-日终结账（查询）
     *
     * @param jzrq
     * @return
     */
    @RequestMapping("/doCreateJzrb")
    @ResponseBody
    @ApiOperationSupport(author = "zhouhaisheng")
    @ApiOperation(value = "住院管理-日终结账（查询）", httpMethod = "POST")
    public ReturnEntity<Map<String, Object>> doCreateJzrb(String jzrq) {

        Map<String,Object> jzrb_map = zyReportSer.doCreateJzrbNew(jzrq,"2",getUser());
        return ReturnEntityUtil.success(jzrb_map);
    }

    /**
     * 住院管理-日终结账（判断本日是否已做过结帐）
     *
     * @param jzrq
     * @return
     */
    @RequestMapping("/doIsreckon")
    @ResponseBody
    @ApiOperationSupport(author = "zhouhaisheng")
    @ApiOperation(value = "住院管理-日终结账（判断本日是否已做过结帐）", httpMethod = "POST")
    public ReturnEntity<DoIsreckonResp> doIsreckon(String jzrq) {

        return zyReportSer.doIsreckon(jzrq, getUser());
    }

    /**
     * 住院管理-日终结账（检验是否可以结账）
     *
     * @param jzrq
     * @return
     */
    @RequestMapping("/doWfCheck")
    @ResponseBody
    @ApiOperationSupport(author = "zhouhaisheng")
    @ApiOperation(value = "住院管理-日终结账（检验是否可以结账）", httpMethod = "POST")
    public ReturnEntity doWfCheck(String jzrq) {

        return zyReportSer.doWfCheck(jzrq, getUser());
    }

    /**
     * 住院管理-日终结账（取消日报查询）
     *
     * @param jzrq
     * @return
     */
    @RequestMapping("/doQueryCancelCommit")
    @ResponseBody
    @ApiOperationSupport(author = "zhouhaisheng")
    @ApiOperation(value = "住院管理-日终结账（取消日报查询）", httpMethod = "POST")
    public ReturnEntity doQueryCancelCommit(String jzrq) {

        return zyReportSer.doQueryCancelCommit(jzrq, getUser());
    }

    /**
     * 住院管理-日终结账（日报查询）
     *
     * @return
     */
    @RequestMapping("/doQueryCommit")
    @ResponseBody
    @ApiOperationSupport(author = "宋宇")
    @ApiOperation(value = "住院管理-日终结账（日报查询）", httpMethod = "POST")
    public ReturnEntity<PageInfo<Map>> doQueryCommit(PageQuery page) {
        PageInfo<Map> pageInfo = PageHelper.startPage(

                page.getPageNum(), page.getPageSize()).doSelectPageInfo(
                        ()-> zyReportSer.doQueryCommit(getUser()));
        return ReturnEntityUtil.success(pageInfo);
    }


    /**
     * 住院管理-日终结账（取消日报）
     *
     * @param jzrq
     * @return
     */
    @RequestMapping("/doCancelCommit")
    @ResponseBody
    @ApiOperationSupport(author = "zhouhaisheng")
    @ApiOperation(value = "住院管理-日终结账（取消日报）", httpMethod = "POST")
    public ReturnEntity doCancelCommit(String jzrq) {

        return zyReportSer.doCancelCommit(jzrq, getUser());
    }

    /**
     * 住院管理-日终结账（结账）
     *
     * @param jzrq
     * @return
     */
    @RequestMapping("/doSaveJzrb")
    @ResponseBody
    @ApiOperationSupport(author = "zhouhaisheng")
    @ApiOperation(value = "住院管理-日终结账（结账）", httpMethod = "POST")
    public ReturnEntity doSaveJzrb(String jzrq, String jzrqStart, String jzrqEnd) {

        return zyReportSer.doSaveJzrb(jzrq, getUser(), jzrqStart, jzrqEnd);
    }

    /**
     * 住院管理-日终结账（明细查询前校验）
     *
     * @param
     * @return
     */
    @RequestMapping("/checkRecordCount")
    @ResponseBody
    @ApiOperationSupport(author = "zhouhaisheng")
    @ApiOperation(value = "住院管理-日终结账（明细查询前校验）", httpMethod = "POST")
    public ReturnEntity checkRecordCount(String jzrq) {

        return zyReportSer.checkRecordCount(jzrq, getUser());
    }

    /**
     * 住院管理-日终汇总（查询前校验）
     *
     * @param startDate
     * @param endDate
     * @return
     */
    @RequestMapping("/doQueryVerification")
    @ResponseBody
    @ApiOperationSupport(author = "zhouhaisheng")
    @ApiOperation(value = "住院管理-日终汇总（查询前校验）", httpMethod = "POST")
    public ReturnEntity<Map<String, Object>> doQueryVerification(String startDate, String endDate) {

        return zyReportSer.doQueryVerification(startDate, endDate, getUser());
    }


    /**
     * 住院管理-日终汇总（产生前校验）
     *
     * @param hzrq
     * @return
     */
    @RequestMapping("/doGenerateVerification")
    @ResponseBody
    @ApiOperationSupport(author = "zhouhaisheng")
    @ApiOperation(value = "住院管理-日终汇总（产生前校验）", httpMethod = "POST")
    public ReturnEntity<Map<String, Object>> doGenerateVerification(String hzrq) {

        return zyReportSer.doGenerateVerification(hzrq, getUser());
    }

    /**
     * 住院管理-日终汇总（产生后校验）
     *
     * @param hzrq
     * @return
     */
    @RequestMapping("/doGenerateAfterVerification")
    @ResponseBody
    @ApiOperationSupport(author = "zhouhaisheng")
    @ApiOperation(value = "住院管理-日终汇总（产生后校验）", httpMethod = "POST")
    public ReturnEntity<Map<String, Object>> doGenerateAfterVerification(String hzrq) {

        return zyReportSer.doGenerateAfterVerification(hzrq, getUser());
    }

    /**
     * 住院管理-日终汇总（汇总前验证）
     *
     * @param hzrq
     * @return
     */
    @RequestMapping("/doCollectVerification")
    @ResponseBody
    @ApiOperationSupport(author = "zhouhaisheng")
    @ApiOperation(value = "住院管理-日终汇总（汇总前验证）", httpMethod = "POST")
    public ReturnEntity<Map<String, Object>> doCollectVerification(String hzrq) {

        return zyReportSer.doCollectVerification(hzrq, getUser());
    }

    /**
     * 住院管理-日终汇总（汇总保存）
     *
     * @param hzrq
     * @return
     */
    @RequestMapping("/doSaveCollect")
    @ResponseBody
    @ApiOperationSupport(author = "zhouhaisheng")
    @ApiOperation(value = "住院管理-日终汇总（汇总保存）", httpMethod = "POST")
    public ReturnEntity<Map<String, Object>> doSaveCollect(String hzrq) {

        return zyReportSer.doSaveCollect(hzrq, getUser());
    }

    /**
     * 住院管理-日终汇总（取消汇总查询）
     *
     * @param hzrq
     * @return
     */
    @RequestMapping("/doQueryCancelCollectCommit")
    @ResponseBody
    @ApiOperationSupport(author = "zhouhaisheng")
    @ApiOperation(value = "住院管理-日终汇总（取消汇总查询）", httpMethod = "POST")
    public ReturnEntity<Map<String, Object>> doQueryCancelCollectCommit(String hzrq) {

        return zyReportSer.doQueryCancelCollectCommit(hzrq, getUser());
    }

    /**
     * 住院管理-日终汇总（取消汇总）
     *
     * @param hzrq
     * @return
     */
    @RequestMapping("/doCancelCollectCommit")
    @ResponseBody
    @ApiOperationSupport(author = "zhouhaisheng")
    @ApiOperation(value = "住院管理-日终汇总（取消汇总）", httpMethod = "POST")
    public ReturnEntity<Map<String, Object>> doCancelCollectCommit(String hzrq) {

        return zyReportSer.doCancelCollectCommit(hzrq, getUser());
    }


    /**
     * 住院管理-日终结账
     *
     * @param patientDepartmentChargesDailyPrintReq
     * @return
     * @throws Exception
     */
    @RequestMapping(path = "/patientDepartmentChargesDailyPrintPreviewOld")
    @ResponseBody
    @ApiOperationSupport(author = "zhouhaisheng")
    @ApiOperation(value = "住院管理-日终结账(查询生成报表)", httpMethod = "POST", notes = "返回htmlURL路径")
    public ReturnEntity<String> patientDepartmentChargesDailyPrintPreviewOld(PatientDepartmentChargesDailyPrintReq patientDepartmentChargesDailyPrintReq) throws Exception {
        Map<String, Object> params = new HashMap<>();
        params.put("jzrq", patientDepartmentChargesDailyPrintReq.getAdt_clrq_b());
        //  List<Map<String, Object>> list=new ArrayList<>();
        // list.add((Map<String, Object>) zyReportSer.doCreateJzrb(patientDepartmentChargesDailyPrintReq.getAdt_clrq_b(),getUser()).getData().get("jzxx"));
        Map<String, Object> request = BeanUtil.beanToMap(patientDepartmentChargesDailyPrintReq);
        Map<String, Object> respose = new HashMap<>();
        String url = "";
        if ("0".equals(patientDepartmentChargesDailyPrintReq.getFirst())) {
            url = exportFileSer.reportHtml(
                    null,
                    "PatientDepartmentChargesDaily.jasper");
            return ReturnEntityUtil.success(url);
        }
        url = exportFileSer.reportHtml(
                zyReportSer.getPatientDepartmentChargesDailyPrintParameters(request, respose, getUser()),
                "PatientDepartmentChargesDaily.jasper");
        return ReturnEntityUtil.success(url);
    }

    /**
     * 住院管理-日终结账(明细)
     *
     * @param mxType
     * @param jzrq
     * @param jzbs
     * @param hasRecordMore
     * @return
     * @throws Exception
     */
    @RequestMapping(path = "/jzMxPrintPreview")
    @ResponseBody
    @ApiOperationSupport(author = "zhouhaisheng")
    @ApiOperation(value = "住院管理-日终结账(明细)", httpMethod = "POST", notes = "返回htmlURL路径")
    public ReturnEntity<String> jzMxPrintPreview(@ApiParam(name = "mxType", value = "1:结算明细 2:缴款明细 3:退款明细", required = true) @RequestParam String mxType, @ApiParam(name = "jzrq", value = "结账日期", required = true) @RequestParam String jzrq, @ApiParam(name = "jzbs", value = "结账标识", required = true) @RequestParam String jzbs, @ApiParam(name = "hasRecordMore", value = "检验状态", required = true) @RequestParam Boolean hasRecordMore) throws Exception {
        Map<String, Object> params = new HashMap<>();
        List<Map<String, Object>> list = new ArrayList<>();
        String jasperName = "";
        //结算明细
        if ("1".equals(mxType)) {
            jasperName = "AccountsDetail.jasper";
            params = zyReportSer.getAccountsDetailParameters(jzrq, jzbs, hasRecordMore, getUser());
            list = zyReportSer.getAccountsDetailFields(jzrq, jzbs, hasRecordMore, getUser());

        }
        //缴款明细
        if ("2".equals(mxType)) {
            jasperName = "DeliveryDetail.jasper";
            params = zyReportSer.getDeliveryDetailParameters(jzrq, jzbs, hasRecordMore, getUser());
            list = zyReportSer.getDeliveryDetailFields(jzrq, jzbs, hasRecordMore, getUser());

        }
        //退款明细
        if ("3".equals(mxType)) {
            jasperName = "RefundDetail.jasper";
            params = zyReportSer.getRefundDetailParameters(jzrq, jzbs, hasRecordMore, getUser());
            list = zyReportSer.getRefundDetailFields(jzrq, jzbs, hasRecordMore, getUser());
        }

        String url = exportFileSer.reportHtml(list,
                params,
                jasperName);
        return ReturnEntityUtil.success(url);
    }

    /**
     * 住院管理-日终汇总报表(一)
     *
     * @param save
     * @param beginDate
     * @param endDate
     * @param hzrq
     * @return
     */
    @RequestMapping(path = "/hzPrintPreviewOne")
    @ResponseBody
    @ApiOperationSupport(author = "zhouhaisheng")
    @ApiOperation(value = "住院管理-日终汇总报表(一)", httpMethod = "POST", notes = "返回htmlURL路径")
    public ReturnEntity<String> hzPrintPreviewOne(@ApiParam(name = "save", value = "是否已产生 2已产生", required = false) @RequestParam String save,
                                                  @ApiParam(name = "beginDate", value = "开始时间", required = true) @RequestParam String beginDate,
                                                  @ApiParam(name = "endDate", value = "结束时间", required = true) @RequestParam String endDate,
                                                  @ApiParam(name = "hzrq", value = "汇总时间", required = false) @RequestParam(required = false) String hzrq) {
        List<Map<String, Object>> list = new ArrayList<>();


        String jasperName = "PatientDepartmentChargesSummary.jasper";
        Map<String, Object> ret = zyReportSer.getCollectParametersOne(save, beginDate, endDate, hzrq, getUser());
        list = zyReportSer.getCollectFieldsOne(save, (List<Map<String, Object>>) ret.get("list"), getUser());

        String url = exportFileSer.reportHtml(list,
                (Map<String, Object>) ret.get("params"),
                jasperName);
        return ReturnEntityUtil.success(url);
    }

    /**
     * 住院管理-日终汇总报表(二)
     *
     * @param save
     * @param beginDate
     * @param endDate
     * @param hzrq
     * @return
     */
    @RequestMapping(path = "/hzPrintPreviewTwo")
    @ResponseBody
    @ApiOperationSupport(author = "zhouhaisheng")
    @ApiOperation(value = "住院管理-日终汇总报表(二)", httpMethod = "POST", notes = "返回htmlURL路径")
    public ReturnEntity<String> hzPrintPreviewTwo(@ApiParam(name = "save", value = "是否已产生 2已产生", required = false) @RequestParam String save,
                                                  @ApiParam(name = "beginDate", value = "开始时间", required = true) @RequestParam String beginDate,
                                                  @ApiParam(name = "endDate", value = "结束时间", required = true) @RequestParam String endDate,
                                                  @ApiParam(name = "hzrq", value = "汇总时间", required = false) @RequestParam(required = false) String hzrq) {
        List<Map<String, Object>> list = new ArrayList<>();


        String jasperName = "Summaryofincome.jasper";
        Map<String, Object> ret = zyReportSer.doSetCollectParametersTwo(save, beginDate, endDate, getUser());


        String url = exportFileSer.reportHtml((List<Map<String, Object>>) ret.get("list"),
                (Map<String, Object>) ret.get("params"),
                jasperName);
        return ReturnEntityUtil.success(url);
    }

    /**
     * 住院管理-日终汇总报表(三)
     *
     * @param save
     * @param beginDate
     * @param endDate
     * @param hzrq
     * @return
     */
    @RequestMapping(path = "/hzPrintPreviewThree")
    @ResponseBody
    @ApiOperationSupport(author = "zhouhaisheng")
    @ApiOperation(value = "住院管理-日终汇总报表(三)", httpMethod = "POST", notes = "返回htmlURL路径")
    public ReturnEntity<String> hzPrintPreviewThree(@ApiParam(name = "save", value = "是否已产生 2已产生", required = false) @RequestParam String save,
                                                    @ApiParam(name = "beginDate", value = "开始时间", required = true) @RequestParam String beginDate,
                                                    @ApiParam(name = "endDate", value = "结束时间", required = true) @RequestParam String endDate,
                                                    @ApiParam(name = "hzrq", value = "汇总时间", required = false) @RequestParam(required = false) String hzrq) {
        List<Map<String, Object>> list = new ArrayList<>();


        String jasperName = "SummarycategoryHospital.jasper";
        Map<String, Object> ret = zyReportSer.doSetCollectParametersThree(save, beginDate, endDate, getUser());
        list = zyReportSer.getCollectFieldsThree(beginDate, endDate, hzrq, getUser());

        String url = exportFileSer.reportHtml(list,
                ret,
                jasperName);
        return ReturnEntityUtil.success(url);
    }

    /**
     * 住院管理-日终汇总报表(四)
     *
     * @param save
     * @param beginDate
     * @param endDate
     * @param hzrq
     * @return
     */
    @RequestMapping(path = "/hzPrintPreviewFour")
    @ResponseBody
    @ApiOperationSupport(author = "zhouhaisheng")
    @ApiOperation(value = "住院管理-日终汇总报表(四)", httpMethod = "POST", notes = "返回htmlURL路径")
    public ReturnEntity<String> hzPrintPreviewFour(@ApiParam(name = "save", value = "是否已产生 2已产生", required = false) @RequestParam String save,
                                                   @ApiParam(name = "beginDate", value = "开始时间", required = true) @RequestParam String beginDate,
                                                   @ApiParam(name = "endDate", value = "结束时间", required = true) @RequestParam String endDate,
                                                   @ApiParam(name = "hzrq", value = "汇总时间", required = false) @RequestParam(required = false) String hzrq) {
        List<Map<String, Object>> list = new ArrayList<>();


        String jasperName = "SummarycategoryHospitalFor.jasper";
        Map<String, Object> ret = zyReportSer.doSetCollectParametersFour(save, beginDate, endDate, hzrq, getUser());
        list = zyReportSer.getCollectFieldsFour(save, beginDate, endDate, hzrq, getUser());

        String url = exportFileSer.reportHtml(list,
                ret,
                jasperName);
        return ReturnEntityUtil.success(url);
    }

    /**
     * 住院管理-日终汇总报表(五)
     *
     * @param save
     * @param beginDate
     * @param endDate
     * @param hzrq
     * @return
     */
    @RequestMapping(path = "/hzPrintPreviewFive")
    @ResponseBody
    @ApiOperationSupport(author = "zhouhaisheng")
    @ApiOperation(value = "住院管理-日终汇总报表(五)", httpMethod = "POST", notes = "返回htmlURL路径")
    public ReturnEntity<String> hzPrintPreviewFive(@ApiParam(name = "save", value = "是否已产生 2已产生", required = false) @RequestParam String save,
                                                   @ApiParam(name = "beginDate", value = "开始时间", required = true) @RequestParam String beginDate,
                                                   @ApiParam(name = "endDate", value = "结束时间", required = true) @RequestParam String endDate,
                                                   @ApiParam(name = "hzrq", value = "汇总时间", required = false) @RequestParam(required = false) String hzrq) {
        List<Map<String, Object>> list = new ArrayList<>();


        String jasperName = "SummarycategoryHospital1.jasper";
        Map<String, Object> ret = zyReportSer.doSetCollectParametersFive(save, beginDate, endDate, 0, "null", getUser());

        String url = exportFileSer.reportHtml((List<Map<String, Object>>) ret.get("list"),
                (Map<String, Object>) ret.get("params"),
                jasperName);
        return ReturnEntityUtil.success(url);
    }

    /**
     * 住院管理-缴款收据打印
     *
     * @param jkxh
     * @return
     */
    @RequestMapping(path = "/jksjPrint")


    @ResponseBody
    @ApiOperationSupport(author = "zhouhaisheng")
    @ApiOperation(value = "住院管理-缴款收据打印", httpMethod = "POST", notes = "返回数据流")
    public void jksjPrint(@ApiParam(name = "jkxh", value = "缴款序号") @RequestParam Integer jkxh, HttpServletResponse response) {
        String jasperName = "jrxml/Payment.jasper";
        Map<String, Object> ret = zyReportSer.queryJksjPrintReportParams(jkxh);

        iReportExportFileSer.reportHtmlForStream(ret, jasperName, response);
    }


    /**
     * 在院病人报表
     *
     * @return
     */
    @RequestMapping(path = "/inHospitalPatientReport")
    @ResponseBody
    @ApiOperationSupport(author = "zhouhaisheng")
    @ApiOperation(value = "住院管理-在院病人报表", httpMethod = "POST", notes = "返回htmlURL路径")
    public ReturnEntity<InHospitalPatientReportAllResp> inHospitalPatientReport(InHospitalPatientReportReq inHospitalPatientReportReq) {
        InHospitalPatientReportAllResp reportAllResp = zyReportSer.InHospitalPatientReportParams(inHospitalPatientReportReq, getUser());

        return ReturnEntityUtil.success(reportAllResp);
    }

    /**
     * 出院病人报表
     *
     * @return
     */
    @RequestMapping(path = "/dischargedPatientReport")
    @ResponseBody
    @ApiOperationSupport(author = "zhouhaisheng")
    @ApiOperation(value = "出院病人报表", httpMethod = "POST", notes = "返回htmlURL路径")
    public ReturnEntity<DischargedPatientReportAllResp> dischargedPatientReport(DischargedPatientReportReq dischargedPatientReportReq) {
        DischargedPatientReportAllResp resp = zyReportSer.queryDischargedPatientReportParams(dischargedPatientReportReq, getUser());

        return ReturnEntityUtil.success(resp);
    }

    /**
     * 病区病人费用报表
     *
     * @return
     */
    @RequestMapping(path = "/bqPatientReport")
    @ResponseBody
    @ApiOperationSupport(author = "zhouhaisheng")
    @ApiOperation(value = "病区病人费用报表", httpMethod = "POST", notes = "返回htmlURL路径")
    public ReturnEntity<BqPatientReportAllResp> bqPatientReport(BqPatientReportReq bqPatientReportReq) {
        BqPatientReportAllResp resp = zyReportSer.queryBqPatientReportParams(bqPatientReportReq, getUser());

        return ReturnEntityUtil.success(resp);
    }


    /**
     * 患者账卡-费用清单明细格式打印
     *
     * @param cardPatientCostPageReqReq
     * @return
     */
    @RequestMapping(path = "/cardPatientCostDetailReport")
    @ResponseBody
    @ApiOperationSupport(author = "zhouhaisheng")
    @ApiOperation(value = "患者账卡-费用清单明细格式打印", httpMethod = "POST", notes = "返回htmlURL路径")
    public void cardPatientCostDetailReport(CardPatientCostPageReq cardPatientCostPageReqReq, HttpServletResponse response) {
        String jasperName = "jrxml/HospitalCostMxgs.jasper";
        Map<String, Object> retMap = zyReportSer.queryCardPatientCostDetailFields(cardPatientCostPageReqReq, getUser());
        Map<String, Object> params = (Map<String, Object>) retMap.get("params");
        List<Map<String, Object>> fields = (List<Map<String, Object>>) retMap.get("fields");
        iReportExportFileSer.reportHtmlForStream(fields,
                params,
                jasperName, response);
    }

    @GetMapping(path = "/patientDepartmentChargesDailyPrintPreview")
    @ApiOperationSupport(author = "宋宇")
    @ApiOperation(value = "住院管理-日终结账(查询生成报表)新")
    public void patientDepartmentChargesDailyPrintPreview(@RequestParam("reqStr") String reqStr,HttpServletResponse response){
        PatientDepartmentChargesDailyPrintReq patientDepartmentChargesDailyPrintReq = JSONUtil.toBean(reqStr, PatientDepartmentChargesDailyPrintReq.class);
        Map<String,Object> map =zyReportSer.getHospitalChargesDailyPrintParameters(patientDepartmentChargesDailyPrintReq,this.getUser());
        if (map.size()!= 0){
            List<Map<String,Object>> list = zyReportSer.getHospitalChargesDailyPrintFields(patientDepartmentChargesDailyPrintReq,this.getUser());
            iReportExportFileSer.reportHtmlForStream(list,map,"jrxml/HospitalChargesDaily.jasper",response);
        }
    }

    @GetMapping(path = "/patientDepartmentChargesDailyMxPrintPreview")
    @ApiOperationSupport(author = "宋宇")
    @ApiOperation(value = "住院管理-日报明细打印查询")
    public void patientDepartmentChargesDailyMxPrintPreview(@RequestParam("reqStr") String reqStr,HttpServletResponse response) {
        PatientDepartmentChargesDailyMxPrintReq mxPrintReq = JSONUtil.toBean(reqStr, PatientDepartmentChargesDailyMxPrintReq.class);
        List<Map<String,Object>> list = new ArrayList<>();
        Map<String,Object> map = new HashMap<>();
        String jasperName = "";
        Integer ty = mxPrintReq.getTy();
        Integer mxType = mxPrintReq.getMxType();
        if (mxType == 1){//结算明细
            list = zyReportSer.getJsmxPrintFields(ty,mxPrintReq.getJzrq(),this.getUser());
            jasperName = "jrxml/HospitalChargesDailyJsmx.jasper";
        }else if(mxType == 2){//交款明细
            list = zyReportSer.getTbkkPrintFields(ty,mxPrintReq.getJzrq(),this.getUser());
            jasperName = "jrxml/HospitalChargesDailyJkmx.jasper";
        }
        iReportExportFileSer.reportHtmlForStream(list,map,jasperName,response);
    }

    @GetMapping(path = "/HospitalChargesDailyPrint")
    @ApiOperationSupport(author = "宋宇")
    @ApiOperation(value = "住院管理-日终结账打印全部")
    public void hospitalChargesDailyPrint(@RequestParam("reqStr") String reqStr,HttpServletResponse response){
        PatientDepartmentChargesDailyPrintReq patientDepartmentChargesDailyPrintReq = JSONUtil.toBean(reqStr,PatientDepartmentChargesDailyPrintReq.class);
        Map<String,Object> map =zyReportSer.getHospitalChargesDailyPrintAllParameters(patientDepartmentChargesDailyPrintReq,this.getUser());
        List<Map<String,Object>> list = new ArrayList<>();
        list.add(new HashMap<>());
        iReportExportFileSer.reportHtmlForStream(list,map,"jrxml/HospitalChargesDailyPrintAll.jasper",response);

    }

    @GetMapping(path = "/HospitalChargesCollectPrint")
    @ApiOperationSupport(author = "宋宇")
    @ApiOperation(value = "住院日终结账汇总报表生成")
    public void hospitalChargesCollectPrint(@RequestParam("reqStr") String reqStr,HttpServletResponse response){
        HospitalChargesCollectReq hospitalChargesCollectReq = JSONUtil.toBean(reqStr,HospitalChargesCollectReq.class);
        Map<String,Object> map =zyReportSer.getHospitalChargesCollectPrintParameters(hospitalChargesCollectReq,this.getUser());
        List<Map<String,Object>> list = zyReportSer.getHospitalChargesCollectPrintFields(hospitalChargesCollectReq,this.getUser());
        iReportExportFileSer.reportHtmlForStream(list,map,"jrxml/Imrzjzhz.jasper",response);
    }

    /**
     * 生成汇总日报前校验是否有数据
     *
     * @return
     */
    @RequestMapping(path = "/ChcekCollect")
    @ResponseBody
    @ApiOperationSupport(author = "宋宇")
    @ApiOperation(value = "生成汇总日报前校验", httpMethod = "POST")
    public ReturnEntity checkCollect(@ApiParam(name = "hzjsrq", value = "汇总结束日期", required = true ) @RequestParam String hzjsrq) {

        return zyReportSer.doCheckCollect(hzjsrq,this.getUser());
    }

    /**
     * 获取最大汇总日期
     *
     * @return
     */
    @RequestMapping(path = "/GetMaxHzrq")
    @ResponseBody
    @ApiOperationSupport(author = "宋宇")
    @ApiOperation(value = "获取最大汇总日期", httpMethod = "POST")
    public ReturnEntity<String> getMaxHzrq() {

        return ReturnEntityUtil.success(zyReportSer.getMaxHzrq(this.getUser()));
    }

    /**
     * 汇总日报保存
     *
     * @return
     */
    @RequestMapping("/doSaveHz")
    @ResponseBody
    @ApiOperationSupport(author = "宋宇")
    @ApiOperation(value = "汇总日报保存", httpMethod = "POST")
    public ReturnEntity doSaveHz(@ApiParam(name = "hzjsrq", value = "汇总结束日期", required = true ) @RequestParam String hzjsrq) {

        return zyReportSer.doSaveHz(hzjsrq,this.getUser());
    }

    /**
     * 获取时间内汇总报表
     *
     * @return
     */
    @RequestMapping("/getHzrq")
    @ResponseBody
    @ApiOperationSupport(author = "宋宇")
    @ApiOperation(value = "获取时间内汇总报表", httpMethod = "POST")
    public ReturnEntity<List<Map<String,Object>>> getHzrq() {

        return ReturnEntityUtil.success(zyReportSer.getHzrq(this.getUser()));
    }

    @RequestMapping("/doCancelHz")
    @ResponseBody
    @ApiOperationSupport(author = "宋宇")
    @ApiOperation(value = "汇总日报取消", httpMethod = "POST")
    public ReturnEntity doCancelHz(@ApiParam(name = "hzjsrq", value = "汇总结束日期", required = true ) @RequestParam String hzjsrq) {

        return zyReportSer.doCancelHz(hzjsrq,this.getUser());
    }
}
