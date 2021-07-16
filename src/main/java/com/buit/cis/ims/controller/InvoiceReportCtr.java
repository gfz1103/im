package com.buit.cis.ims.controller;

import com.buit.cis.ims.request.InvoiceReportReq;
import com.buit.cis.ims.response.InvoiceReportResp;
import com.buit.cis.ims.service.InvoiceReportSer;
import com.buit.commons.BaseSpringController;
import com.buit.utill.DateUtils;
import com.buit.utill.PageUtil;
import com.buit.utill.ReturnEntity;
import com.buit.utill.ReturnEntityUtil;
import com.github.pagehelper.PageInfo;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 结算发票查询
 *
 * @author jiangwei
 */
@Api(tags = "住院_结算发票查询")
@Controller
@RequestMapping("/invoiceReport")
public class InvoiceReportCtr extends BaseSpringController {

    @Autowired
    InvoiceReportSer invoiceReportSer;


    @RequestMapping("/list")
    @ResponseBody
    @ApiOperationSupport(author = "jiangwei")
    @ApiOperation(value = "结算发票查询-结算发票查询列表", httpMethod = "POST")
    public ReturnEntity<PageInfo<InvoiceReportResp>> queryBalanceAccountsList(InvoiceReportReq req) {
        if(req.getKsrq() !=null){
            req.setKsrq(DateUtils.getDailyStartTime(req.getKsrq()));
        }
        if(req.getZzrq() !=null){
            req.setZzrq(DateUtils.getDailyEndTime(req.getZzrq()));
        }
        return ReturnEntityUtil.success(PageUtil.getPageInfo(req.getPageNum(), req.getPageSize(), invoiceReportSer.list(req)));
    }
}
