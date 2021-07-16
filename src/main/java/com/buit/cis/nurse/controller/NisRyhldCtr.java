
package com.buit.cis.nurse.controller;

import java.util.List;

import com.buit.cis.nurse.model.NisRyhld;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.buit.cis.nurse.request.NisRyhldReq;
import com.buit.cis.nurse.service.NisRyhldSer;
import com.buit.commons.BaseSpringController;
import com.buit.constans.TableName;
import com.buit.utill.RedisFactory;
import com.buit.utill.ReturnEntity;
import com.buit.utill.ReturnEntityUtil;

import cn.hutool.core.util.StrUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * 入院护理评估单<br>
 * @author GONGFANGZHOU
 */
@Api(tags="入院护理评估单")
@Controller
@RequestMapping("/nisryhld")
public class NisRyhldCtr extends BaseSpringController{
    
    static final Logger logger = LoggerFactory.getLogger(NisRyhldCtr.class);
    
    @Autowired
    private NisRyhldSer nisRyhldSer;
    
    @Autowired
    private RedisFactory redisFactory;
    
    @RequestMapping("/queryRyhldByDate")
    @ResponseBody
    @ApiOperation(value="根据日期查询入院护理单" ,httpMethod="POST")
    public ReturnEntity<List<NisRyhld>> queryRyhldByDate(@ApiParam(name = "zyh", value = "住院号", required = true)
    @RequestParam Integer zyh, @ApiParam(name = "queryDate", value = "查询时间(M-d H:i)", required = false)
    @RequestParam(value="queryDate", required = false) String queryDate){
        return ReturnEntityUtil.success(nisRyhldSer.getEntityMapper().queryRyhldByDate(zyh, 
        		queryDate, this.getUser().getHospitalId()));
    }
  
    @RequestMapping("/saveRyhld")
    @ResponseBody
    @ApiOperation(value="新增修改保存入院护理单" ,httpMethod="POST")
    public ReturnEntity saveRyhld(NisRyhldReq nisRyhldReq){
    	nisRyhldReq.setJgid(this.getUser().getHospitalId());
    	if(StrUtil.isBlankIfStr(nisRyhldReq.getJlxh())) {
    		nisRyhldReq.setJlxh(redisFactory.getTableKey(TableName.DB_NAME, TableName.NIS_RYHLD));
    		nisRyhldSer.insert(nisRyhldReq);
    	}else {
    		nisRyhldSer.update(nisRyhldReq);
    	}
        return ReturnEntityUtil.success();    
    }
   
    @RequestMapping("/deleteRyhldByJlxhDate")
    @ResponseBody
    @ApiOperation(value="根据日期删除入院护理单" ,httpMethod="POST")
    public ReturnEntity deleteRyhldByJlxhDate(@ApiParam(name = "zyh", value = "住院号", required = true)
    @RequestParam Integer zyh, @ApiParam(name = "queryDate", value = "查询时间(M-d H:i)", required = true)
    @RequestParam String queryDate){
    	List<NisRyhld> list = nisRyhldSer.getEntityMapper().queryRyhldByDate(zyh, 
        		queryDate, this.getUser().getHospitalId());
    	for(NisRyhld nisRyhld : list) {
    		nisRyhldSer.removeById(nisRyhld.getJlxh());
    	}
        return ReturnEntityUtil.success();
    }
    
}

