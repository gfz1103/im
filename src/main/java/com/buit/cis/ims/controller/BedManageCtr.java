package com.buit.cis.ims.controller;

import cn.hutool.core.bean.BeanUtil;
import com.buit.cis.ims.enums.CwxbEnum;
import com.buit.cis.ims.request.*;
import com.buit.cis.ims.response.*;
import com.buit.cis.ims.service.ImCwszSer;
import com.buit.commons.BaseSpringController;
import com.buit.file.IReportExportFileSer;
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

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 床位管理<br>
 *
 * @author zhouhaisheng
 */
@Api(tags = "床位管理")
@Controller
@RequestMapping("/bedManage")
public class BedManageCtr extends BaseSpringController {
    static final Logger logger = LoggerFactory.getLogger(BedManageCtr.class);
    @Autowired
    private ImCwszSer imCwszSer;
    @Autowired
    private IReportExportFileSer iReportExportFileSer;

    /**
     * 床位管理-转床（保存）
     * @param doGetToBedInfoReq
     * @param
     * @return
     */
    @RequestMapping("/zyglCwszZc")
    @ResponseBody
    @ApiOperationSupport(author = "zhouhaisheng")
    @ApiOperation(value="床位管理-转床(保存)" ,httpMethod="POST")
    public ReturnEntity<String> zyglCwszZc(DoGetToBedInfoReq doGetToBedInfoReq){


        return imCwszSer.zyglCwszZc(doGetToBedInfoReq,getUser());
    }
    /**
     * 床位管理-转床（分页列表）
     * @param zcPatientPageInfoReq
     * @param
     * @return
     */
    @RequestMapping("/queryZcPatientPageInfo")
    @ResponseBody
    @ApiOperationSupport(author = "zhouhaisheng")
    @ApiOperation(value="床位管理-转床(分页列表)" ,httpMethod="POST")
    public ReturnEntity<PageInfo<ZcPatientPageInfoResp>> queryZcPatientPageInfo(ZcPatientPageInfoReq zcPatientPageInfoReq){
        zcPatientPageInfoReq.setJgid(getUser().getHospitalId());

        PageInfo<ZcPatientPageInfoResp> pageInfo = PageHelper.startPage(
                zcPatientPageInfoReq.getPageNum(), zcPatientPageInfoReq.getPageSize()).doSelectPageInfo(
                () -> imCwszSer.queryZcPatientPageInfo(zcPatientPageInfoReq)
        );
        return ReturnEntityUtil.success(pageInfo);

    }


    /**
     * 查询床位管理-床位分配(床位信息)
     * @param
     * @param
     * @return
     */
    @RequestMapping("/queryCwfpInfo")
    @ResponseBody
    @ApiOperationSupport(author = "zhouhaisheng")
    @ApiOperation(value="查询床位管理-床位分配(床位信息)" ,httpMethod="POST")
    public ReturnEntity<QueryCwfpInfoCwszResp> queryCwfpInfo(String brch, Integer brbq){

        return imCwszSer.queryCwfpInfoResp(brch, brbq, getUser());
    }


    /**
     * 床位管理-未分配床位病人分页信息
     * @param
     * @param
     * @return
     */
    @RequestMapping("/doGetToBedInfo")
    @ResponseBody
    @ApiOperationSupport(author = "zhouhaisheng")
    @ApiOperation(value="床位管理-床位分配(病人分页信息)" ,httpMethod="POST")
    public ReturnEntity<PageInfo<QueryCwfpInfoZybrryPageResp>> doGetToBedInfo(CwfpQueryReq cwfpQueryReq){
        PageInfo<QueryCwfpInfoZybrryPageResp> pageInfo = PageHelper.startPage(
                cwfpQueryReq.getPageNum(), cwfpQueryReq.getPageSize()).doSelectPageInfo(
                () -> imCwszSer.doGetToBedInfo(cwfpQueryReq,getUser())
        );

        return ReturnEntityUtil.success(pageInfo);
    }
    /**
     * 床位管理-住院病人床位列表分页查询
     * @param zybrCwszReq
     * @return
     */
    @RequestMapping("/queryZybrCwszPage")
    @ResponseBody
    @ApiOperationSupport(author = "zhouhaisheng")
    @ApiOperation(value="住院病人床位列表分页查询" ,httpMethod="POST")
    public ReturnEntity<PageInfo<ZybrCwszResp>> queryZybrCwszPage(ZybrCwszReq zybrCwszReq) {
        zybrCwszReq.setJgid(getUser().getHospitalId());
        PageInfo<ZybrCwszResp> pageInfo = PageHelper.startPage(
                zybrCwszReq.getPageNum(), zybrCwszReq.getPageSize()).doSelectPageInfo(
                () -> imCwszSer.queryZybrCwszPage(zybrCwszReq)
        );

        return ReturnEntityUtil.success(pageInfo);
    }

    /**
     * 床位管理-床位清单打印
     *
     * @param zybrCwszReq
     * @return
     */
    @RequestMapping("/bedListPrint")
    @ResponseBody
    @ApiOperationSupport(author = "jiangwei")
    @ApiOperation(value = "床位管理-床位清单打印", httpMethod = "POST")
    public void bedListPrint(ZybrCwszReq zybrCwszReq, HttpServletResponse response) {
        zybrCwszReq.setJgid(getUser().getHospitalId());
        Map<String, Object> map = new HashMap<>();
        map.put("title", "床位管理");

        List<Map<String, Object>> list = new ArrayList<>();
        List<ReportBedListResp> data = imCwszSer.queryReportBedList(zybrCwszReq);
        for (ReportBedListResp resp : data) {
            resp.setBrxb(CwxbEnum.getCwxb(resp.getBrxb()));
            resp.setCwxb(CwxbEnum.getCwxb(resp.getCwxb()));
            list.add(BeanUtil.beanToMap(resp));
        }
        String jasperName = "jrxml/BedList.jasper";
        iReportExportFileSer.reportHtmlForStream(list, map, jasperName, response);
    }

    /**
     * 床位管理-退床处理
     *
     * @param zyh
     * @param cwhm
     * @return
     */
    @RequestMapping("/zyglCwszTc")
    @ResponseBody
    @ApiOperationSupport(author = "zhouhaisheng")
    @ApiOperation(value="床位管理-退床处理" ,httpMethod="POST")
    public ReturnEntity<String> zyglCwszTc(Integer zyh, String cwhm, Integer brbq){
        return imCwszSer.zyglCwszTc(zyh,cwhm,getUser(),brbq);
    }

    /**
     * 床位管理-床位分配（保存）
     * @param cwfpReq
     * @return
     */
    @RequestMapping("/cwfp")
    @ResponseBody
    @ApiOperationSupport(author = "zhouhaisheng")
    @ApiOperation(value="床位管理-床位分配(保存)" ,httpMethod="POST")
    public ReturnEntity<String> cwfp(CwfpReq cwfpReq){
        return imCwszSer.cwfp(cwfpReq,getUser());
    }

}
