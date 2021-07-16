package com.buit.cis.ims.controller;

import com.buit.cis.ims.request.QueryBalanceAccountsPageReq;
import com.buit.cis.ims.request.SaveSettleAccountsReq;
import com.buit.cis.ims.request.UpdateSettleAccountsReq;
import com.buit.cis.ims.response.BalanceAccountsListResp;
import com.buit.cis.ims.response.BalanceAccountsSettleResp;
import com.buit.cis.ims.service.ImHzrySer;
import com.buit.cis.ims.service.ImZyjsSer;
import com.buit.commons.BaseSpringController;
import com.buit.his.shyb.source.entity.SE01Res;
import com.buit.his.shyb.source.entity.SM01Res;
import com.buit.utill.ReturnEntity;
import com.github.pagehelper.PageInfo;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.ParseException;

/**
 * 结算管理<br>
 * @author zhouhaisheng
 */
@Api(tags="结算管理")
@Controller
@RequestMapping("/balanceAccounts")
public class BalanceAccountsCtr extends BaseSpringController {
    static final Logger logger = LoggerFactory.getLogger(BalanceAccountsCtr.class);

    @Autowired
    private ImHzrySer imHzrySer;
    @Autowired
    private ImZyjsSer imZyjsSer;
    /**
     * 结算管理-根据结算类型查询病人结算列表
     * @param queryBalanceAccountsPageReq
     * @return
     */
    @RequestMapping("/queryBalanceAccountsList")
    @ResponseBody
    @ApiOperationSupport(author = "zhouhaisheng")
    @ApiOperation(value="结算管理-根据结算类型查询病人结算列表" ,httpMethod="POST")
    public ReturnEntity<PageInfo<BalanceAccountsListResp>> queryBalanceAccountsList(QueryBalanceAccountsPageReq queryBalanceAccountsPageReq) throws ParseException {

        return imHzrySer.queryBalanceAccountsList(queryBalanceAccountsPageReq,getUser());
    }

/*    *//**
     * 结算管理-病人结算基本汇总信息
     * @param zyh
     * @return
     *//*
    @RequestMapping("/queryPatientBalanceAccountsBaseInfo")
    @ResponseBody
    @ApiOperationSupport(author = "zhouhaisheng")
    @ApiOperation(value="结算管理-病人结算基本汇总信息" ,httpMethod="POST")
    public ReturnEntity<PatientBalanceAccountsBaseInfoResp> queryPatientBalanceAccountsBaseInfo(Integer zyh, String endDate){
        return imHzrySer.queryPatientBalanceAccountsBaseInfo(zyh,getUser().getHospitalId(),endDate);
    }*/


    /**
     * 结算处理页面的结算金额和补退金额汇总
     */
    @RequestMapping("/queryBalanceAccountsSettle")
    @ResponseBody
    @ApiOperationSupport(author = "zhouhaisheng")
    @ApiOperation(value = "结算处理页面的结算金额和补退金额汇总", httpMethod = "POST")
    public ReturnEntity<BalanceAccountsSettleResp> queryBalanceAccountsSettle(Integer zyh, String jslx, String jsrq, String carddata, String accountattr, String cardtype, String ksrq, String zzrq,String ybjsksrq,String gsrdh) throws ParseException {
        return imZyjsSer.queryBalanceAccountsSettle(zyh, jslx, jsrq, carddata, cardtype, accountattr,
                getIpAddress(), getUser(), ksrq, zzrq,ybjsksrq,gsrdh);
    }

    /**
     * 结算管理-住院结算保存
     * @param saveSettleAccountsReq
     * @return
     */

    //phis.hospitalPatientSelectionService.saveSettleAccounts
    @RequestMapping("/saveSettleAccounts")
    @ResponseBody
    @ApiOperationSupport(author = "zhouhaisheng")
    @ApiOperation(value="结算管理-住院结算保存" ,httpMethod="POST")
    public ReturnEntity<String> saveSettleAccounts(@RequestBody SaveSettleAccountsReq saveSettleAccountsReq){
        return imZyjsSer.saveSettleAccounts(saveSettleAccountsReq,getUser(),getIpAddress());
    }



    /**
     * 结算管理-病人结算作废(普通结算作废)
     * @param updateSettleAccountsReq
     * @return
     */
    @RequestMapping("/patientBalanceAccountsCancel")
    @ResponseBody
    @ApiOperationSupport(author = "zhouhaisheng")
    @ApiOperation(value="结算管理-病人结算作废(普通结算作废)" ,httpMethod="POST")
    public ReturnEntity<String> patientBalanceAccountsCancel(@RequestBody UpdateSettleAccountsReq updateSettleAccountsReq){
        return imZyjsSer.patientBalanceAccountsCancel(updateSettleAccountsReq,getUser(),getIpAddress());
    }


    /**
     * 结算管理-病人结算作废(高价药)
     * @param zyh
     * @return
     */
    @RequestMapping("/patientBalanceAccountsCancelGjy")
    @ResponseBody
    @ApiOperationSupport(author = "zhouhaisheng")
    @ApiOperation(value="结算管理-病人结算作废(高价药)" ,httpMethod="POST")
    public ReturnEntity<String> patientBalanceAccountsCancelGjy(Integer zyh){

        return imZyjsSer.patientBalanceAccountsCancelGjy(zyh,getUser(),getIpAddress());
    }

    /**
     * 结算管理-查询实际病人性质
     * @param brxz
     * @return
     */
    @RequestMapping("/querySjBrxz")
    @ResponseBody
    @ApiOperationSupport(author = "zhouhaisheng")
    @ApiOperation(value="结算管理-查询实际病人性质" ,httpMethod="POST")
    public ReturnEntity<Integer> querySjBrxz(Integer brxz){

        return imZyjsSer.querySjBrxz(brxz);
    }

    /**
     * 获取住院发票打印数据
     * @return
     */
    //hospitalPatientSelectionService.queryZyfpdy
    @RequestMapping("/doQueryZyfpdy")
    @ResponseBody
    @ApiOperationSupport(author = "zhouhaisheng")
    @ApiOperation(value="获取住院发票打印数据" ,httpMethod="POST")
    public ReturnEntity doQueryZyfpdy(String fphm){
        return imZyjsSer.doQueryZyfpdy(getUser(),fphm);
    }

    /**
     * 病人医保读卡
     * @return
     */
    @RequestMapping("/readPatientMedicareAccountInfo")
    @ResponseBody
    @ApiOperationSupport(author = "beijunhua")
    @ApiOperation(value="病人医保读卡" ,httpMethod="POST")
    public ReturnEntity<SM01Res> readPatientMedicareAccountInfo(){

        return imHzrySer.readPatientMedicareAccountInfo("",getIpAddress());
    }

    /**
     * 病人医保刷卡
     * @param carddata
     * @return
     */
    @RequestMapping("/queryPatientMedicareAccountInfo")
    @ResponseBody
    @ApiOperationSupport(author = "beijunhua")
    @ApiOperation(value="病人医保刷卡" ,httpMethod="POST")
    public ReturnEntity<SM01Res> queryPatientMedicareAccountInfo(String carddata){

        return imHzrySer.readPatientMedicareAccountInfo(carddata,getIpAddress());
    }

    /**
     * 病人电子凭证
     * @param ecQrCode
     * @return
     */
    @RequestMapping("/getEctoken")
    @ResponseBody
    @ApiOperationSupport(author = "beijunhua")
    @ApiOperation(value="解码" ,httpMethod="POST")
    public ReturnEntity<SE01Res> getEctoken(String ywlx, String ecQrCode){

        return imHzrySer.getEctoken(ywlx ,ecQrCode, getIpAddress(), getUser());
    }


}
