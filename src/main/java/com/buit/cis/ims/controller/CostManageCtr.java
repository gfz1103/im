package com.buit.cis.ims.controller;

import com.buit.cis.ims.model.ImHzry;
import com.buit.cis.ims.model.ImTbkk;
import com.buit.cis.ims.preService.YjkPreService;
import com.buit.cis.ims.request.ImTbkkSaveReq;
import com.buit.cis.ims.request.PatientPayPageReq;
import com.buit.cis.ims.request.QueryCostPatientListByBrxmReq;
import com.buit.cis.ims.request.QueryPatientCostReq;
import com.buit.cis.ims.response.CostPatientCollectResp;
import com.buit.cis.ims.response.PatientPayPageResp;
import com.buit.cis.ims.response.QueryPatientCostResp;
import com.buit.cis.ims.service.ImHzrySer;
import com.buit.cis.ims.service.ImTbkkSer;
import com.buit.commons.BaseSpringController;
import com.buit.commons.SysUser;
import com.buit.utill.ReturnEntity;
import com.buit.utill.ReturnEntityUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.math.BigDecimal;
import java.util.List;

/**
 * 住院管理-缴费管理<br>
 *
 * @author zhouhaisheng
 */
@Api(tags = "住院管理-缴费管理")
@Controller
@RequestMapping("/costManage")
public class CostManageCtr extends BaseSpringController {

    static final Logger logger = LoggerFactory.getLogger(CostManageCtr.class);

    @Autowired
    private ImTbkkSer imTbkkSer;
    @Autowired
    private YjkPreService preService;
    @Autowired
    private ImHzrySer imHzrySer;

    /**
     * 缴费管理-缴费处理分页查询
     *
     * @param patientPayPageReq
     * @return
     */
    @RequestMapping("/queryPatientPayPage")
    @ResponseBody
    @ApiOperationSupport(author = "zhouhaisheng")
    @ApiOperation(value = "缴费管理-缴费处理分页查询", httpMethod = "POST")
    public ReturnEntity<PageInfo<PatientPayPageResp>> queryPatientPayPage(PatientPayPageReq patientPayPageReq) {
        patientPayPageReq.setJgid(getUser().getHospitalId());
//        patientPayPageReq.setZfpb(0);
        PageInfo<PatientPayPageResp> pageInfo = PageHelper.startPage(
                patientPayPageReq.getPageNum(), patientPayPageReq.getPageSize()).doSelectPageInfo(
                () -> imTbkkSer.queryPatientPayPage(patientPayPageReq)
        );
        return ReturnEntityUtil.success(pageInfo);
    }

    /**
     * 缴费管理-缴费处理-注销
     *
     * @param sjhm
     * @return
     */
    @RequestMapping("/patientPayCancel")
    @ResponseBody
    @ApiOperationSupport(author = "zhouhaisheng")
    @ApiOperation(value = "缴费管理-缴费处理-注销", httpMethod = "POST")
    public ReturnEntity<String> patientPayCancel(String sjhm) {
        //需要注销的缴费记录
        SysUser sysUser = getUser();
        ImTbkk tbkk = preService.queryBySjhm(sjhm, sysUser);
        return imTbkkSer.patientPayCancel(tbkk, sysUser);
    }

    /**
     * 缴费管理-缴费查询
     *
     * @param queryPatientCostReq
     * @return
     */
    @RequestMapping("/queryPatientCost")
    @ResponseBody
    @ApiOperationSupport(author = "zhouhaisheng")
    @ApiOperation(value = "缴费管理-缴费查询", httpMethod = "POST")
    public ReturnEntity<PageInfo<QueryPatientCostResp>> queryPatientCost(QueryPatientCostReq queryPatientCostReq) {
        queryPatientCostReq.setJgid(getUser().getHospitalId());
        PageInfo<QueryPatientCostResp> pageInfo = PageHelper.startPage(
                queryPatientCostReq.getPageNum(), queryPatientCostReq.getPageSize()).doSelectPageInfo(
                () -> imTbkkSer.queryPatientCost(queryPatientCostReq)
        );
        return ReturnEntityUtil.success(pageInfo);
    }

    /**
     * 缴费管理-缴费查询(总金额)
     *
     * @param queryPatientCostReq
     * @return
     */
    @RequestMapping("/queryPatientCostTotal")
    @ResponseBody
    @ApiOperationSupport(author = "zhouhaisheng")
    @ApiOperation(value = "缴费管理-缴费查询(总金额)", httpMethod = "POST")
    public ReturnEntity<BigDecimal> queryPatientCostTotal(QueryPatientCostReq queryPatientCostReq) {
        queryPatientCostReq.setJgid(getUser().getHospitalId());
        List<QueryPatientCostResp> list = imTbkkSer.queryPatientCost(queryPatientCostReq);
        BigDecimal total = new BigDecimal(0);
        for (QueryPatientCostResp q : list) {
            total = total.add(q.getJkje());
        }
        return ReturnEntityUtil.success(total);
    }

    /**
     * 缴费管理-缴费处理-住院号码查询预缴医药收据
     *
     * @param zyhm
     * @return
     */
    @RequestMapping("/queryCostPatientCollectByZyh")
    @ResponseBody
    @ApiOperationSupport(author = "zhouhaisheng")
    @ApiOperation(value = "缴费管理-缴费处理-住院号码查询预缴医药收据", httpMethod = "POST")
    public ReturnEntity<CostPatientCollectResp> queryCostPatientCollectByZyh(String zyhm) {
        return imTbkkSer.queryCostPatientCollectByZyhm(zyhm, getUser());
    }

    /**
     * 缴费管理-缴费处理-就诊卡号询预缴医药收据
     *
     * @param jzkh
     * @return
     */
    @RequestMapping("/queryCostPatientCollectByJzkh")
    @ResponseBody
    @ApiOperationSupport(author = "zhouhaisheng")
    @ApiOperation(value = "缴费管理-缴费处理-就诊卡号询预缴医药收据", httpMethod = "POST")
    public ReturnEntity<CostPatientCollectResp> queryCostPatientCollectByJzkh(String jzkh) {
        return imTbkkSer.queryCostPatientCollectByJzkh(jzkh, getUser());
    }

    /**
     * 缴费管理-缴费处理-病人床号查询预缴医药收据
     *
     * @param brch
     * @return
     */
    @RequestMapping("/queryCostPatientCollectByBrch")
    @ResponseBody
    @ApiOperationSupport(author = "zhouhaisheng")
    @ApiOperation(value = "缴费管理-缴费处理-病人床号查询预缴医药收据", httpMethod = "POST")
    public ReturnEntity<CostPatientCollectResp> queryCostPatientCollectByBrch(String brch) {
        return imTbkkSer.queryCostPatientCollectByBrch(brch, getUser());
    }

    /**
     * 缴费管理-缴费处理-病人姓名模糊查询住院病人列表
     *
     * @param queryCostPatientListByBrxmReq
     * @return
     */
    @RequestMapping("/queryCostPatientListByBrxm")
    @ResponseBody
    @ApiOperationSupport(author = "zhouhaisheng")
    @ApiOperation(value = "缴费管理-缴费处理-病人姓名模糊查询住院病人列表", httpMethod = "POST")
    public ReturnEntity<PageInfo<ImHzry>> queryCostPatientListByBrxm(QueryCostPatientListByBrxmReq queryCostPatientListByBrxmReq) {
        queryCostPatientListByBrxmReq.setJgid(getUser().getHospitalId());
        PageInfo<ImHzry> pageInfo = PageHelper.startPage(
                queryCostPatientListByBrxmReq.getPageNum(), queryCostPatientListByBrxmReq.getPageSize()).doSelectPageInfo(
                () -> imHzrySer.queryCostPatientListByBrxm(queryCostPatientListByBrxmReq)
        );
        return ReturnEntityUtil.success(pageInfo);

    }


    /**
     * 缴费管理-病人预缴费保存
     *
     * @param imTbkkSaveReq
     * @return
     */
    @RequestMapping("/patientCostInsert")
    @ResponseBody
    @ApiOperationSupport(author = "zhouhaisheng")
    @ApiOperation(value = "缴费管理-病人预缴费保存", httpMethod = "POST")
    public ReturnEntity<String> patientCostInsert(ImTbkkSaveReq imTbkkSaveReq) {

        return imTbkkSer.patientCostInsert(imTbkkSaveReq, getUser(), getIpAddress());
    }

    /**
     * 缴费管理-获取当前的收据号码
     *
     * @param
     * @return
     */
    @RequestMapping("/getCurrentBill")
    @ResponseBody
    @ApiOperationSupport(author = "zhouhaisheng")
    @ApiOperation(value = "缴费管理-获取当前的收据号码", httpMethod = "POST")
    public ReturnEntity<String> getCurrentBill() {

        return imTbkkSer.getCurrentBill(getUser());
    }

    /**
     * 缴费管理-获取当前的发票号码
     *
     * @param
     * @return
     */
    @RequestMapping("/getCurrentInvoice")
    @ResponseBody
    @ApiOperationSupport(author = "zhouhaisheng")
    @ApiOperation(value = "缴费管理-获取当前的发票号码", httpMethod = "POST")
    public ReturnEntity<String> getCurrentInvoice() {

        return imTbkkSer.getCurrentInvoice(getUser());
    }

    /**
     * 缴费管理-获取当前用户的医疗机构名称
     *
     * @param
     * @return
     */
    @RequestMapping("/getHospitalName")
    @ResponseBody
    @ApiOperationSupport(author = "zhouhaisheng")
    @ApiOperation(value = "缴费管理-获取当前用户的医疗机构名称", httpMethod = "POST")
    public ReturnEntity<String> getHospitalName() {

        return ReturnEntityUtil.success(getUser().getHospitalName());
    }


}
