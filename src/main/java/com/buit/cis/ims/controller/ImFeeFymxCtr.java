
package com.buit.cis.ims.controller;

import cn.hutool.core.bean.BeanUtil;
import com.buit.cis.ims.enums.ExpensesPrintTemplateEnum;
import com.buit.cis.ims.model.ImFeeFymx;
import com.buit.cis.ims.model.ImHzry;
import com.buit.cis.ims.request.CardPatientCostReq;
import com.buit.cis.ims.request.ExpensesPrintReq;
import com.buit.cis.ims.response.CardPatientCostBaseInfoResp;
import com.buit.cis.ims.response.ExpensesPrintDataResp;
import com.buit.cis.ims.response.ExpensesPrintPatientResp;
import com.buit.cis.ims.service.ImFeeFymxSer;
import com.buit.cis.ims.service.ImHzrySer;
import com.buit.commons.BaseSpringController;
import com.buit.commons.PageQuery;
import com.buit.file.IReportExportFileSer;
import com.buit.system.response.DicYljgOut;
import com.buit.system.response.PubBrxzOut;
import com.buit.system.service.DicKszdOutSer;
import com.buit.system.service.DicYljgOutSer;
import com.buit.system.service.PubBrxzOutSer;
import com.buit.utill.BUHISUtil;
import com.buit.utill.DateUtils;
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
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 费用明细表<br>
 *
 * @author ZHOUHAISHENG
 */
@Api(tags = "费用明细表")
@Controller
@RequestMapping("/imfeefymx")
public class ImFeeFymxCtr extends BaseSpringController {

    static final Logger logger = LoggerFactory.getLogger(ImFeeFymxCtr.class);
    @Autowired
    private IReportExportFileSer iReportExportFileSer;
    @Autowired
    private ImFeeFymxSer imFeeFymxSer;
    @Autowired
    private ImHzrySer imHzrySer;
    @DubboReference
    private DicKszdOutSer dicKszdOutSer;
    @DubboReference
    private PubBrxzOutSer pubBrxzOutSer;
    @DubboReference
    private DicYljgOutSer dicYljgOutSer;

    @RequestMapping("/queryPageByJlxh")
    @ResponseBody
    @ApiOperation(value = "根据病区医嘱记录序号查询费用明细—分页", httpMethod = "POST")
    public ReturnEntity<PageInfo<ImFeeFymx>> queryPageByJlxh(@ApiParam(name = "jlxh", value = "病区医嘱jlxh")
                                                         @RequestParam(value = "jlxh") Integer jlxh, PageQuery page) {
        return ReturnEntityUtil.success(PageHelper.startPage(
                page.getPageNum(), page.getPageSize()).doSelectPageInfo(
                () -> imFeeFymxSer.getEntityMapper().queryFeeDetails(jlxh)));
    }

    @RequestMapping("/expensesPrint")
    @ResponseBody
    @ApiOperationSupport(author = "jiangwei")
    @ApiOperation(value = "费用清单-打印", httpMethod = "POST")
    public void expensesDetailListPrint(ExpensesPrintReq req, HttpServletResponse response) {
        Map<String, Object> map = new HashMap<>();
        DicYljgOut dicYljg = dicYljgOutSer.getById(getUser().getHospitalId());
        map.put("title", dicYljg.getAffiliatedName() + req.getStartDate().substring(0, 10) + " 至 " + req.getEndDate().substring(0, 10) + "病人住院费用清单");
        map.put("jgid", dicYljg.getAffiliatedName());

        //入院信息
        ImHzry imHzry = imHzrySer.getById(req.getZyh());
        ExpensesPrintPatientResp header = new ExpensesPrintPatientResp();

        //计算住院天数
        //header.setZyts(BUHISUtil.loadDischargeDays(imHzry.getRyrq().toString(), imHzry.getCyrq() == null ? DateUtils.getCurrentDateStr() : imHzry.getCyrq().toString()));
        //计算结算天数
        String ryrq = DateUtils.formatToDateTime(imHzry.getRyrq());
        if (req.getStartDate().compareTo(ryrq) < 0) {
            req.setStartDate(ryrq);
        }
        if (imHzry.getCyrq() != null) {
            String cyrq = DateUtils.formatToDateTime(imHzry.getCyrq());
            if (req.getEndDate().compareTo(cyrq) > 0) {
                req.setEndDate(cyrq);
            }
        }
        CardPatientCostReq costReq = new CardPatientCostReq();
        BeanUtils.copyProperties(req, costReq);

        CardPatientCostBaseInfoResp baseInfo = imHzrySer.queryCardPatientCostBaseInfo(costReq, getUser().getHospitalId());
        BeanUtils.copyProperties(baseInfo, header);

        header.setBrks(dicKszdOutSer.getNameById(imHzry.getBrks()));
        PubBrxzOut brxz = pubBrxzOutSer.getBrmcPubBrxz(baseInfo.getBrxz());
        header.setBrxz(brxz.getXzmc());

        header.setKsrq(req.getStartDate());
        header.setZzrq(req.getEndDate());

        //结算天数计算(费用清单结算天数按住院天数计算逻辑计算)
        header.setJsts(BUHISUtil.loadDischargeDays(req.getStartDate(), req.getEndDate()));

        map.put("header", header);

        List<Map<String, Object>> list = new ArrayList<>();
        List<ExpensesPrintDataResp> data = imFeeFymxSer.findExpensesListByTemplate(req);
        for (ExpensesPrintDataResp resp : data) {
            list.add(BeanUtil.beanToMap(resp));
        }
        String jasperName = "jrxml/" + ExpensesPrintTemplateEnum.getTemplateUrl(req.getTemplate());
        iReportExportFileSer.reportHtmlForStream(list, map, jasperName, response);
    }


//    @RequestMapping("/queryPage")
//    @ResponseBody
//    @ApiOperation(value="按条件分页查询" ,httpMethod="POST")
//    public ReturnEntity<PageInfo<ImFeeFymxResp>> queryPage(ImFeeFymxReq imfeefymx,PageQuery page){
//        PageInfo<ImFeeFymx> pageInfo = PageHelper.startPage(
//            page.getPageNum(), page.getPageSize()).doSelectPageInfo(
//                    () -> imFeeFymxSer.findByEntity(imfeefymx)
//            );
//        return ReturnEntityUtil.success(pageInfo);
//    }
//    

//    
//    @RequestMapping("/getOneByEntity")
//    @ResponseBody
//    @ApiOperation(value="按条件查询-返回唯一值" ,httpMethod="POST")
//    public ReturnEntity<ImFeeFymxResp> getOneByEntity(ImFeeFymxReq imfeefymx){
//        List<ImFeeFymx> list=imFeeFymxSer.findByEntity(imfeefymx);
//        if(list.size()>0){
//           return ReturnEntityUtil.success(list.get(0));    
//        }
//        return ReturnEntityUtil.success();
//    }
//    
//    /** 新增 */
//    @RequestMapping("/add")
//    @ResponseBody
//    @ApiOperation(value="新增" ,httpMethod="POST")
//    public ReturnEntity<ImFeeFymxResp> add(ImFeeFymxReq imFeeFymx) {
//        imFeeFymxSer.insert(imFeeFymx);        
//        return ReturnEntityUtil.success(imFeeFymx);            
//    }
//    /** 编辑 */
//    @RequestMapping("/edit")
//    @ResponseBody
//    @ApiOperation(value="编辑" ,httpMethod="POST")
//    public ReturnEntity<ImFeeFymxResp> edit(ImFeeFymxReq imFeeFymx)  {
//        imFeeFymxSer.update(imFeeFymx);        
//        return ReturnEntityUtil.success(imFeeFymx);            
//    }
//    
//    /** 删除 */
//    @RequestMapping("/delete")
//    @ResponseBody
//    @ApiOperation(value="按条件查询" ,httpMethod="POST")
//    public ReturnEntity<ImFeeFymxResp> delete(ImFeeFymxReq imFeeFymx) {
//        imFeeFymxSer.removeByEntity(imFeeFymx);        
//        return ReturnEntityUtil.success(imFeeFymx);            
//    }

}

