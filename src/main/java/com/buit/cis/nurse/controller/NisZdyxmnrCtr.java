
package com.buit.cis.nurse.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.buit.cis.nurse.model.NisZdyxmnr;
import com.buit.cis.nurse.service.NisZdyxmnrSer;
import com.buit.commons.BaseSpringController;
import com.buit.utill.ReturnEntity;
import com.buit.utill.ReturnEntityUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * 自定义专科护理内容<br>
 * @author GONGFANGZHOU
 */
@Api(tags="自定义专科护理内容")
@Controller
@RequestMapping("/niszdyxmnr")
public class NisZdyxmnrCtr extends BaseSpringController{
    
    static final Logger logger = LoggerFactory.getLogger(NisZdyxmnrCtr.class);
    
    @Autowired
    private NisZdyxmnrSer nisZdyxmnrSer;
    
    @RequestMapping("/invalid")
    @ResponseBody
    @ApiOperation(value="专科护理自定义项目内容作废或取消作废" ,httpMethod="POST")
    public ReturnEntity invalid(@ApiParam(name = "xmnrdm", value = "项目内容代码", required = true)
	@RequestParam Integer xmnrdm,@ApiParam(name = "zfpb", value = "作废判别(作废:1,取消:0)", required = true)
	@RequestParam Integer zfpb)  {
    	nisZdyxmnrSer.getEntityMapper().invalid(xmnrdm, zfpb);   
        return ReturnEntityUtil.success();            
    }
   
    /** 删除 */
    @RequestMapping("/deleteById")
    @ResponseBody
    @ApiOperation(value="专科护理自定义项目内容删除" ,httpMethod="POST")
    public ReturnEntity deleteById(@ApiParam(name = "xmnrdm", value = "项目内容代码", required = true)
	@RequestParam Integer xmnrdm) {
        nisZdyxmnrSer.deleteXmnrById(xmnrdm, this.getUser().getHospitalId());        
        return ReturnEntityUtil.success();            
    }
    
    @RequestMapping("/queryXmnrDic")
    @ResponseBody
    @ApiOperation(value="根据项目代码查询字典信息" ,httpMethod="POST")
    public ReturnEntity<List<NisZdyxmnr>> queryXmnrDic(@ApiParam(name = "xmdm", value = "项目代码", required = true)
	@RequestParam Integer xmdm)  {
    	NisZdyxmnr nisZdyxmnr = new NisZdyxmnr();
    	nisZdyxmnr.setXmdm(xmdm);
    	nisZdyxmnr.setZfpb(0);
    	nisZdyxmnr.setJgid(this.getUser().getHospitalId());
        return ReturnEntityUtil.success(nisZdyxmnrSer.getEntityMapper().findByEntity(nisZdyxmnr));            
    }
    
}

