package com.buit.cis.ims.controller;

import com.buit.cis.ims.request.DebtInventoryReportReq;
import com.buit.cis.ims.request.DebtInventoryReq;
import com.buit.cis.ims.response.DebtInventoryPageResp;
import com.buit.cis.ims.response.DebtInventoryResp;
import com.buit.cis.ims.service.ImHzrySer;
import com.buit.cis.ims.service.ZyReportSer;
import com.buit.commons.BaseSpringController;
import com.buit.file.IReportExportFileSer;
import com.buit.utill.ReturnEntity;
import com.buit.utill.ReturnEntityUtil;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 住院管理-缴费管理<br>
 *
 * @author zhouhaisheng
 */
@Api(tags = "催款管理")
@Controller
@RequestMapping("/debtManage")
public class DebtManageCtr extends BaseSpringController {
    static final Logger logger = LoggerFactory.getLogger(DebtManageCtr.class);
    @Autowired
    private ImHzrySer imHzrySer;
    @Autowired
    private ZyReportSer zyReportSer;
    @Autowired
    private IReportExportFileSer iReportExportFileSer;

    /**
     * 住院管理-缴费管理(分页查询)
     *
     * @param debtInventoryReq
     * @return
     */
    @RequestMapping("/queryDebtInventoryPage")
    @ResponseBody
    @ApiOperationSupport(author = "zhouhaisheng")
    @ApiOperation(value = "住院管理-缴费管理(分页查询)", httpMethod = "POST")
    public ReturnEntity<DebtInventoryPageResp> queryDebtInventoryPage(DebtInventoryReq debtInventoryReq) {
        debtInventoryReq.setJgid(getUser().getHospitalId());
        return ReturnEntityUtil.success(imHzrySer.queryDebtInventoryPage(debtInventoryReq));
    }

    /**
     * 住院管理-缴费管理(获取系统默认的催款金额)
     *
     * @param
     * @return
     */
    @RequestMapping("/queryDefaultDebtInventory")
    @ResponseBody
    @ApiOperationSupport(author = "zhouhaisheng")
    @ApiOperation(value = "住院管理-缴费管理(获取系统默认的催款金额)", httpMethod = "POST")
    public ReturnEntity<String> queryDefaultDebtInventory() {

        return ReturnEntityUtil.success(imHzrySer.queryDefaultDebtInventory(getUser()));
    }

    /**
     * 催款管理-催款清单报表
     *
     * @param debtInventoryReportReq
     * @return
     */
    @GetMapping(path = "/debtInventoryPrintPreview")
    @ApiOperationSupport(author = "zhouhaisheng")
    @ApiOperation(value = "催款管理-催款清单报表", httpMethod = "GET", notes = "返回报表数据流")
    public void debtInventoryPrintPreview(DebtInventoryReportReq debtInventoryReportReq, HttpServletResponse response) {
        String jasperName = "jrxml/HospitalPromptManagement_new.jasper";
        List<DebtInventoryResp> ret = zyReportSer.doSetDebtInventoryParameters(debtInventoryReportReq, getUser());
        iReportExportFileSer.reportHtmlForStream(zyReportSer.getDebtInventoryFields(ret, getUser()),
                null,
                jasperName, response);
    }


}
