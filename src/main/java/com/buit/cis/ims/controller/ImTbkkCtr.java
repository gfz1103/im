
package com.buit.cis.ims.controller;

import com.buit.cis.ims.request.PatientImTbkkReq;
import com.buit.cis.ims.response.PatientImTbkkResp;
import com.buit.cis.ims.service.ImTbkkSer;
import com.buit.commons.BaseSpringController;
import com.buit.commons.SysUser;
import com.buit.utill.DateUtils;
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

/**
 * 住院_退补缴款<br>
 * @author ZHOUHAISHENG
 */
@Api(tags="住院_退补缴款")
@Controller
@RequestMapping("/imtbkk")
public class ImTbkkCtr extends BaseSpringController {
    
    static final Logger logger = LoggerFactory.getLogger(ImTbkkCtr.class);
    
    @Autowired
    private ImTbkkSer imTbkkSer;

    /**
     * 在院病人预缴款统计分页查询
     * @param patientImTbkkReq
     * @return
     */
    @RequestMapping("/queryPatientImTbkkPage")
    @ResponseBody
    @ApiOperationSupport(author = "zhouhaisheng")
    @ApiOperation(value="在院病人预缴款统计分页查询" ,httpMethod="POST")
    public ReturnEntity<PageInfo<PatientImTbkkResp>> queryPatientImTbkkPage(PatientImTbkkReq patientImTbkkReq){
        SysUser sysUser = getUser();
        patientImTbkkReq.setJgid(sysUser.getHospitalId());
        patientImTbkkReq.setCzgh(sysUser.getUserId());

        patientImTbkkReq.setStartDate(DateUtils.getDailyStartTime(patientImTbkkReq.getStartDate()));
        patientImTbkkReq.setEndDate(DateUtils.getDailyEndTime(patientImTbkkReq.getEndDate()));

        PageInfo<PatientImTbkkResp> pageInfo = PageHelper.startPage(
                patientImTbkkReq.getPageNum(), patientImTbkkReq.getPageSize()).doSelectPageInfo(
                () -> imTbkkSer.queryPatientImTbkkPage(patientImTbkkReq)
        );

        return ReturnEntityUtil.success(pageInfo);
    }


    /**
     * 在院病人预缴款统计总和
     * @param
     * @return
     */
    @RequestMapping("/queryPatientImTbkkSum")
    @ResponseBody
    @ApiOperationSupport(author = "zhouhaisheng")
    @ApiOperation(value="在院病人预缴款统计总和" ,httpMethod="POST")
    public ReturnEntity<BigDecimal> queryPatientImTbkkSum(PatientImTbkkReq patientImTbkkReq){
        patientImTbkkReq.setJgid(getUser().getHospitalId());

        patientImTbkkReq.setStartDate(DateUtils.getDailyStartTime(patientImTbkkReq.getStartDate()));
        patientImTbkkReq.setEndDate(DateUtils.getDailyEndTime(patientImTbkkReq.getEndDate()));

        return ReturnEntityUtil.success(imTbkkSer.queryPatientImTbkkSum(patientImTbkkReq));
    }
    /**
     * 住院号统计病人缴款总金额
     * @param
     * @return
     */
    @RequestMapping("/queryPatientImTbkkSumByZyh")
    @ResponseBody
    @ApiOperationSupport(author = "zhouhaisheng")
    @ApiOperation(value="住院号统计病人缴款总金额" ,httpMethod="POST")
    public ReturnEntity<BigDecimal> queryPatientImTbkkSumByZyh(Integer zyh){

        return ReturnEntityUtil.success(imTbkkSer.queryPatientImTbkkSumByZyh(zyh,getUser().getHospitalId()));
    }
}

