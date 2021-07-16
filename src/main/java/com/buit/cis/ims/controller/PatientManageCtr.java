package com.buit.cis.ims.controller;

import com.buit.cis.im.response.ImHzryResp;
import com.buit.cis.ims.model.ImTbkk;
import com.buit.cis.ims.request.*;
import com.buit.cis.ims.response.*;
import com.buit.cis.ims.service.*;
import com.buit.commons.BaseException;
import com.buit.commons.BaseSpringController;
import com.buit.utill.DateUtils;
import com.buit.utill.ReturnEntity;
import com.buit.utill.ReturnEntityUtil;
import com.buit.utill.StrUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;


/**
 * 病人管理<br>
 * @author zhouhaisheng
 */
@Api(tags="病人管理")
@Controller
@RequestMapping("/patientManage")
public class PatientManageCtr extends BaseSpringController {
    static final Logger logger = LoggerFactory.getLogger(PatientManageCtr.class);
    @Autowired
    private ImCwszSer imCwszSer;
    @Autowired
    private ImHzrySer imHzrySer;
    @Autowired
    private ImTbkkSer imTbkkSer;
    @Autowired
    private ImFeeFymxSer imFeeFymxSer;
    @Autowired
    private ImDrugsOutRangeLogSer imDrugsOutRangeLogSer;

    /**
     * 病人管理-病人列表分页查询
     *
     * @param patientListReq
     * @return
     */
    @ApiOperationSupport(author = "zhouhaisheng")
    @RequestMapping("/queryPage")
    @ResponseBody
    @ApiOperation(value = "病人管理-病人列表分页查询", httpMethod = "POST")
    public ReturnEntity<PageInfo<PatientListResp>> doGetPatientList(PatientListReq patientListReq) {
        if (StrUtil.isNotBlank(patientListReq.getKsrq())) {
            patientListReq.setKsrq(DateUtils.getDailyStartTime(patientListReq.getKsrq()));
        }
        if (StrUtil.isNotBlank(patientListReq.getJsrq())) {
            patientListReq.setJsrq(DateUtils.getDailyEndTime(patientListReq.getJsrq()));
        }

        PageInfo<PatientListResp> pageInfo = PageHelper.startPage(
                patientListReq.getPageNum(), patientListReq.getPageSize()).doSelectPageInfo(
                () -> imHzrySer.findPatientList(patientListReq, getUser())
        );
        return ReturnEntityUtil.success(pageInfo);
    }

    /**
     * 病人管理form回填
     */
    @RequestMapping("/doLoadBrxx")
    @ResponseBody
    @ApiOperationSupport(author = "zhouhaisheng")
    @ApiOperation(value="病人管理form回填" ,httpMethod="POST")
    public ReturnEntity<ImHzryResp> doLoadBrxx(Integer zyh) {

        return  ReturnEntityUtil.success(imHzrySer.doLoadBrxx(zyh)) ;
    }

    /**
     * 病人管理-病人入院信息修改
     */
    @RequestMapping("/doUpdateBrry")
    @ResponseBody
    @ApiOperationSupport(author = "zhouhaisheng")
    @ApiOperation(value="病人管理-病人入院信息修改" ,httpMethod="POST")
    public ReturnEntity<String> doUpdateBrry(@RequestBody ImHzryReq imHzryReq) {

        return imHzrySer.doUpdateBrry(imHzryReq,getUser());
    }

    /**
     * 病人管理-转换(病人性质转换)
     */
    @RequestMapping("/doUpdateTransform")
    @ResponseBody
    @ApiOperationSupport(author = "zhouhaisheng")
    @ApiOperation(value="病人管理-转换(病人性质转换)" ,httpMethod="POST")
    public ReturnEntity<String> doUpdateTransform(Integer zyh, Integer brxz ,String cardno) {

        return imHzrySer.doUpdateTransform(zyh,brxz,getUser(),cardno,getIpAddress());
    }

    /**
     * 病人管理-判断上级性质是否是医保
     */
    @RequestMapping("/checkSjxz")
    @ResponseBody
    @ApiOperationSupport(author = "宋宇")
    @ApiOperation(value="病人管理-判断上级性质是否是医保" ,httpMethod="POST")
    public ReturnEntity<Boolean> checkSjxz(Integer oldBrxz,Integer newBrxz) {

        return imHzrySer.checkSjxz(oldBrxz,newBrxz);
    }

    /**
     * 病人管理-病人注销
     */
    @RequestMapping("/doDelBrry")
    @ResponseBody
    @ApiOperationSupport(author = "zhouhaisheng")
    @ApiOperation(value="病人管理-病人注销" ,httpMethod="POST")
    public ReturnEntity<String> doDelBrry(Integer zyh, String cardno) {

        return imHzrySer.doDelBrry(zyh,getUser(),getIpAddress(),cardno);
    }


    /**
     * 患者管理-帐卡-费用帐卡；结算管理-结算病人帐卡信息
     * @param cardPatientInfoReq 患者管理调用时 jslx=null; 结算管理调用时jslx!=null
     */
    @RequestMapping("/queryCardPatientInfo")
    @ResponseBody
    @ApiOperationSupport(author = "zhouhaisheng")
    @ApiOperation(value="病人管理-帐卡-费用帐卡(查询病人帐卡信息)" ,httpMethod="POST")
    public ReturnEntity<CardPatientInfoResp> queryCardPatientInfo(CardPatientInfoReq cardPatientInfoReq) {
        cardPatientInfoReq.setJgid(getUser().getHospitalId());
        imDrugsOutRangeLogSer.outRangeDrugBalance(cardPatientInfoReq.getZyh());
        return imHzrySer.queryCardPatientInfo(cardPatientInfoReq);
    }

    /**
     * 病人管理-帐卡-费用帐卡(帐卡费用列表)
     */
    @RequestMapping("/queryCardPatientCostInfoList")
    @ResponseBody
    @ApiOperationSupport(author = "zhouhaisheng")
    @ApiOperation(value="病人管理-帐卡-费用帐卡(帐卡费用列表)" ,httpMethod="POST")
    public ReturnEntity<List<CardPatientInfoCostResp>> queryCardPatientCostInfoList(CardPatientInfoCostReq cardPatientInfoCoseReq) {
        cardPatientInfoCoseReq.setJgid(getUser().getHospitalId());
        return imHzrySer.queryCardPatientCostInfoList(cardPatientInfoCoseReq);
    }

    /**
     * 病人管理-帐卡-费用帐卡(缴费记录)
     */
    @RequestMapping("/queryCardPatientCostRecord")
    @ResponseBody
    @ApiOperationSupport(author = "zhouhaisheng")
    @ApiOperation(value="病人管理-帐卡-费用帐卡(缴费记录)" ,httpMethod="POST")
    public ReturnEntity<List<CostImTbkkResp>> queryCardPatientCostRecord(Integer zyh) {
        ImTbkk imTbkkParam =new ImTbkk();
        imTbkkParam.setZyh(zyh);
        imTbkkParam.setJgid(getUser().getHospitalId());
        return ReturnEntityUtil.success(imTbkkSer.findJkjl(imTbkkParam));
    }

    /**
     * 病人管理-帐卡-费用帐卡(费用清单-病人费用统计信息)
     */
    @RequestMapping("/queryCardPatientCostBaseInfo")
    @ResponseBody
    @ApiOperationSupport(author = "zhouhaisheng")
    @ApiOperation(value="病人管理-帐卡-费用帐卡(费用清单-病人费用统计信息)" ,httpMethod="POST")
    public ReturnEntity<CardPatientCostBaseInfoResp> queryCardPatientCostBaseInfo(CardPatientCostReq cardPatientCostReq) {
        return ReturnEntityUtil.success(imHzrySer.queryCardPatientCostBaseInfo(cardPatientCostReq, getUser().getHospitalId()));
    }


    /**
     * 病人管理-帐卡-费用帐卡(费用清单-明细格式)
     */
    @RequestMapping("/queryCardPatientCostDetail")
    @ResponseBody
    @ApiOperationSupport(author = "zhouhaisheng")
    @ApiOperation(value="病人管理-帐卡-费用帐卡(费用清单-明细格式)" ,httpMethod="POST")
    public ReturnEntity<PageInfo<CardPatientCostDetailResp>> queryCardPatientCostDetail(CardPatientCostPageReq cardPatientCostPageReqReq) {
        cardPatientCostPageReqReq.setJgid(getUser().getHospitalId());

        PageInfo<CardPatientCostDetailResp> pageInfo = PageHelper.startPage(
                cardPatientCostPageReqReq.getPageNum(), cardPatientCostPageReqReq.getPageSize()).doSelectPageInfo(
                () -> imHzrySer.queryCardPatientCostDetail(cardPatientCostPageReqReq)
        );
        return ReturnEntityUtil.success(pageInfo);
    }

    /**
     * 病人管理-帐卡-费用帐卡(费用清单-汇总格式)
     */
    @RequestMapping("/queryCardPatientCostCollect")
    @ResponseBody
    @ApiOperationSupport(author = "zhouhaisheng")
    @ApiOperation(value="病人管理-帐卡-费用帐卡(费用清单-汇总格式)" ,httpMethod="POST")
    public ReturnEntity<PageInfo<CardPatientCostCollectResp>> queryCardPatientCostCollect(CardPatientCostPageReq cardPatientCostPageReqReq) {
        cardPatientCostPageReqReq.setJgid(getUser().getHospitalId());

        PageInfo<CardPatientCostCollectResp> pageInfo = PageHelper.startPage(
                cardPatientCostPageReqReq.getPageNum(), cardPatientCostPageReqReq.getPageSize()).doSelectPageInfo(
                () -> imHzrySer.queryCardPatientCostCollect(cardPatientCostPageReqReq)
        );
        return ReturnEntityUtil.success(pageInfo);
    }

    /**
     * 病人管理-帐卡-费用帐卡(费用清单-医嘱格式)
     */
    @RequestMapping("/queryCardPatientCostAdvice")
    @ResponseBody
    @ApiOperationSupport(author = "zhouhaisheng")
    @ApiOperation(value="病人管理-帐卡-费用帐卡(费用清单-医嘱格式)" ,httpMethod="POST")
    public ReturnEntity<PageInfo<CardPatientCostAdviceResp>> queryCardPatientCostAdvice(CardPatientCostPageReq cardPatientCostPageReqReq) {
        cardPatientCostPageReqReq.setJgid(getUser().getHospitalId());

        PageInfo<CardPatientCostAdviceResp> pageInfo = PageHelper.startPage(
                cardPatientCostPageReqReq.getPageNum(), cardPatientCostPageReqReq.getPageSize()).doSelectPageInfo(
                () -> imHzrySer.queryCardPatientCostAdvice(cardPatientCostPageReqReq)
        );
        return ReturnEntityUtil.success(pageInfo);
    }


    /**
     * 病人管理-帐卡-费用帐卡(费用清单-汇总新格式)
     */
    @RequestMapping("/queryCardPatientCostCollectNew")
    @ResponseBody
    @ApiOperationSupport(author = "zhouhaisheng")
    @ApiOperation(value="病人管理-帐卡-费用帐卡(费用清单-汇总新格式)" ,httpMethod="POST")
    public ReturnEntity<PageInfo<CardPatientCostCollectNewResp>> queryCardPatientCostCollectNew(CardPatientCostPageReq cardPatientCostPageReqReq) {
        cardPatientCostPageReqReq.setJgid(getUser().getHospitalId());

        PageInfo<CardPatientCostCollectNewResp> pageInfo = PageHelper.startPage(
                cardPatientCostPageReqReq.getPageNum(), cardPatientCostPageReqReq.getPageSize()).doSelectPageInfo(
                () -> imHzrySer.queryCardPatientCostCollectNew(cardPatientCostPageReqReq)
        );
        return ReturnEntityUtil.success(pageInfo);
    }

    /**
     * 病人管理-帐卡-明细项目(按日期)
     *
     * @param zyh
     * @return
     */
    @RequestMapping("/patientBalanceAccountsDetailDate")
    @ResponseBody
    @ApiOperationSupport(author = "zhouhaisheng")
    @ApiOperation(value = "病人管理-帐卡-明细项目(按日期))", httpMethod = "POST")
    public ReturnEntity<List<PatientBalanceAccountsDetailDateResp>> patientBalanceAccountsDetailDate(Integer zyh, Integer zygb, @RequestParam(required = false) String ksrq, @RequestParam(required = false) String zzrq) {
        return imFeeFymxSer.patientBalanceAccountsDetailDate(zyh, getUser(), zygb, ksrq, zzrq);
    }

    /**
     * 病人管理-帐卡-明细项目(按明细)
     *
     * @param zyh
     * @return
     */
    @RequestMapping("/patientBalanceAccountsDetailMx")
    @ResponseBody
    @ApiOperationSupport(author = "zhouhaisheng")
    @ApiOperation(value = "病人管理-帐卡-明细项目(按明细))", httpMethod = "POST")
    public ReturnEntity<List<PatientBalanceAccountsDetailMxResp>> patientBalanceAccountsDetailMx(Integer zyh, Integer zygb, @RequestParam(required = false) String ksrq, @RequestParam(required = false) String zzrq) {
        return imFeeFymxSer.patientBalanceAccountsDetailMx(zyh, getUser(), zygb, ksrq, zzrq);
    }

    /**
     * 病人管理-帐卡-明细项目(按明细)-修改折扣比例
     *
     * @param updateZkblListReq
     * @return
     */
    @RequestMapping("/updateZkbl")
    @ResponseBody
    @ApiOperationSupport(author = "zhouhaisheng")
    @ApiOperation(value = "病人管理-帐卡-明细项目(按明细)-修改折扣比例", httpMethod ="POST")
    public ReturnEntity<String> updateZkbl(@RequestBody UpdateZkblListReq updateZkblListReq){
        return imFeeFymxSer.updateZkbl(updateZkblListReq);
    }

    /**
     * 病人管理-帐卡-查询住院天数
     * @param ryrq
     * @param cyrq
     * @return
     */
    @RequestMapping("/queryZyts")
    @ResponseBody
    @ApiOperationSupport(author = "zhouhaisheng")
    @ApiOperation(value="病人管理-帐卡-查询住院天数" ,httpMethod="POST")
    public ReturnEntity<String> queryZyts(@ApiParam(name = "ryrq", value = "入院日期", required = true) @RequestParam  String ryrq,
                                          @ApiParam(name = "cyrq", value = "出院日期", required = true) @RequestParam String cyrq){
        return imFeeFymxSer.queryZyts(ryrq,cyrq);
    }

    /**
     * 病人管理-医保预结算
     * @param
     * @param
     * @return
     */
    @RequestMapping("/checkYbfy")
    @ResponseBody
    @ApiOperationSupport(author = "beijunhua")
    @ApiOperation(value="病人管理-医保预结算" ,httpMethod="POST")
    public ReturnEntity<BalanceAccountsSettleResp> checkYbfy(
                                          @ApiParam(name = "zyh", value = "住院号", required = true) @RequestParam int zyh,
                                          @ApiParam(name = "carddata", value = "卡号", required = true) @RequestParam String carddata,
                                          @ApiParam(name = "cardtype", value = "卡类型", required = true) @RequestParam String cardtype,
                                          @ApiParam(name = "accountattr", value = "医保账户标志", required = true) @RequestParam String accountattr,
                                          @ApiParam(name = "jscs", value = "结算次数", required = true) @RequestParam int jscs,
                                          @ApiParam(name = "jsksrq", value = "结算开始日期", required = true) @RequestParam String jsksrq,
                                          @ApiParam(name = "wjsts", value = "未结算天数", required = true) @RequestParam int wjsts
                                        ){
        return imFeeFymxSer.checkYbfy(zyh,carddata,cardtype,accountattr, jscs, jsksrq,wjsts,getIpAddress(), getUser());
    }


    @RequestMapping("/queryVerifyRecord")
    @ResponseBody
    @ApiOperation(value="费用帐卡汇总对账" ,httpMethod="POST")
    public ReturnEntity<PageInfo<VerifyRecordResp>> queryVerifyRecord(VerifyRecordReq verifyRecordReq) {
        verifyRecordReq.setJgid(getUser().getHospitalId());
        verifyRecordReq.setStartDate(DateUtils.getDailyStartTime(verifyRecordReq.getStartDate()));
        verifyRecordReq.setEndDate(DateUtils.getDailyEndTime(verifyRecordReq.getEndDate()));

        PageInfo<VerifyRecordResp> pageInfo = PageHelper.startPage(
                verifyRecordReq.getPageNum(), verifyRecordReq.getPageSize()).doSelectPageInfo(
                () -> imHzrySer.queryVerifyRecord(verifyRecordReq)
        );
        return ReturnEntityUtil.success(pageInfo);
    }

    /**
     * 病人管理-本地明细撤销
     * @param
     * @param
     * @return
     */
    @RequestMapping("/undoMxzd")
    @ResponseBody
    @ApiOperationSupport(author = "beijunhua")
    @ApiOperation(value="病人管理-本地明细撤销" ,httpMethod="POST")
    public ReturnEntity<?> checkYbfy( @ApiParam(name = "zyh", value = "住院号", required = true) @RequestParam int zyh ){
        imFeeFymxSer.undoMxzd(zyh);
        return ReturnEntityUtil.success();
    }

    @RequestMapping("/getInHospitalPatient")
    @ResponseBody
    @ApiOperationSupport(author = "jiangwei")
    @ApiOperation(value="病区费用对账查询在院病人" ,httpMethod="POST")
    public ReturnEntity<CardPatientCostBaseInfoResp> getInHospitalPatient(@ApiParam(name = "zyhm", value = "住院号码") @RequestParam(required = false) String zyhm,
                                                                          @ApiParam(name = "brch", value = "病人床号") @RequestParam(required = false) String brch){
        if(StrUtil.isBlank(zyhm) && StrUtil.isBlank(brch)){
            throw BaseException.create("ERROR_NURSEWORK_ZYBRRY_00061");
        }
        return ReturnEntityUtil.success(imHzrySer.getInHospitalPatient(zyhm, brch, getUser().getHospitalId()));
    }
}
