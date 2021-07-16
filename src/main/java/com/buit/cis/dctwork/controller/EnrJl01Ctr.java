
package com.buit.cis.dctwork.controller;

import java.util.List;

import com.buit.utill.ReturnEntity;
import com.buit.utill.ReturnEntityUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

import com.buit.commons.BaseSpringController;
import com.buit.cis.dctwork.request.EnrJlSaveReq;
import com.buit.cis.dctwork.response.EnrJg02Resp;
import com.buit.cis.dctwork.response.EnrJl02Resp;
import com.buit.cis.dctwork.service.EnrJl01Ser;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * <br>
 * @author GONGFANGZHOU
 */
@Api(tags="护理记录")
@Controller
@RequestMapping("/enrjl01")
public class EnrJl01Ctr extends BaseSpringController{
    
    static final Logger logger = LoggerFactory.getLogger(EnrJl01Ctr.class);
    
    @Autowired
    private EnrJl01Ser enrJl01Ser;
    
    @RequestMapping("/queryRecordMete")
    @ResponseBody
    @ApiOperation(value = "查询护理结构表单", httpMethod = "POST")
    public ReturnEntity<List<EnrJg02Resp>> queryRecordMete(){
        return ReturnEntityUtil.success(enrJl01Ser.queryRecordMete());
    }
    
    @RequestMapping("/queryEnrJl02ByJlbh")
    @ResponseBody
    @ApiOperation(value = "根据记录编号获取护理明细表单", httpMethod = "POST")
    public ReturnEntity<List<EnrJl02Resp>> queryEnrJl02ByJlbh(@ApiParam(name = "jlbh", value = "记录编号", required = true) 
	@RequestParam Integer jlbh){
        return ReturnEntityUtil.success(enrJl01Ser.queryEnrJl02ByJlbh(jlbh, this.getUser()));
    }
     
    @RequestMapping("/saveRecordsOfNursing")
    @ResponseBody
    @ApiOperation(value = "病人管理护理记录新增或修改保存", httpMethod = "POST")
    public ReturnEntity saveRecordsOfNursing(@RequestBody EnrJlSaveReq enrJlSaveReq){
    	enrJl01Ser.doSaveNursingRecords(enrJlSaveReq, this.getUser());
        return ReturnEntityUtil.success();
    }
    
    
    @RequestMapping("/deleteEnrjl01")
    @ResponseBody
    @ApiOperation(value = "护理记录单删除", httpMethod = "POST")
    public ReturnEntity deleteEnrjl01(@ApiParam(name = "jlbh", value = "记录编号", required = true) 
	@RequestParam Integer jlbh){
    	enrJl01Ser.deleteEnrjl01(jlbh);
        return ReturnEntityUtil.success();
    }
    
}

