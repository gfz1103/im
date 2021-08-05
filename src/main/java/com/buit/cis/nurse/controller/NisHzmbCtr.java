
package com.buit.cis.nurse.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.buit.cis.nurse.request.NisHzmbReq;
import com.buit.cis.nurse.response.NisHzmbDetailResp;
import com.buit.cis.nurse.response.NisHzmbResp;
import com.buit.cis.nurse.service.NisHzmbSer;
import com.buit.commons.BaseSpringController;
import com.buit.utill.ReturnEntity;
import com.buit.utill.ReturnEntityUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * 患者护理记录模板表<br>
 * @author GONGFANGZHOU
 */
@Api(tags="患者护理记录模板表")
@Controller
@RequestMapping("/nishzmb")
public class NisHzmbCtr extends BaseSpringController{
    
    static final Logger logger = LoggerFactory.getLogger(NisHzmbCtr.class);
    
    @Autowired
    private NisHzmbSer nisHzmbSer;
    
    
    @RequestMapping("/saveHzmb")
    @ResponseBody
    @ApiOperation(value="新增患者模板" ,httpMethod="POST")
    public ReturnEntity saveHzmb(NisHzmbReq nisHzmbReq) {       
	    nisHzmbSer.saveHzmb(nisHzmbReq, this.getUser().getHospitalId());
	    return ReturnEntityUtil.success();            
    }
    
    @RequestMapping("/queryHzmbTree")
    @ResponseBody
    @ApiOperation(value="查询患者模板树形结构" ,httpMethod="POST")
    public ReturnEntity<List<NisHzmbResp>> queryHzmbTree(@ApiParam(name = "zyh", value = "住院号", required = true)
    @RequestParam Integer zyh){   
        return ReturnEntityUtil.success(nisHzmbSer.queryHzmbTree(zyh, this.getUser()));
    }
    
 
    @RequestMapping("/deleteByZyhAndMblx")
    @ResponseBody
    @ApiOperation(value="根据住院号和模板类型删除模板" ,httpMethod="POST")
    public ReturnEntity deleteByZyhAndMblx(@ApiParam(name = "zyh", value = "住院号", required = true)
    @RequestParam Integer zyh, @ApiParam(name = "mblx", value = "模板类型", required = true)
    @RequestParam String mblx){
    	nisHzmbSer.deleteByZyhAndMblx(zyh, mblx, this.getUser().getHospitalId());
        return ReturnEntityUtil.success();    
    }

    @RequestMapping("/queryHzmbTreeDetail")
    @ResponseBody
    @ApiOperation(value="查询患者模板树形结构明细" ,httpMethod="POST")
    public ReturnEntity<List<NisHzmbDetailResp>> queryHzmbTreeDetail(@ApiParam(name = "zyh", value = "住院号", required = true)
    @RequestParam Integer zyh, @ApiParam(name = "mblx", value = "模板类型", required = true)
    @RequestParam String mblx, @ApiParam(name = "yearMonth", value = "查询时间(年月Y-m)", required = true)
    @RequestParam String yearMonth){   
        return ReturnEntityUtil.success(nisHzmbSer.queryHzmbTreeDetail(zyh, mblx, yearMonth, this.getUser()));
    }
}

