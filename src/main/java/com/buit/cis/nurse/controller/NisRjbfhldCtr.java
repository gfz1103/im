
package com.buit.cis.nurse.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.buit.cis.nurse.model.NisRjbfhld;
import com.buit.cis.nurse.request.NisRjbfhldReq;
import com.buit.cis.nurse.service.NisRjbfhldSer;
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
 * 日间病房护理记录单<br>
 * @author GONGFANGZHOU
 */
@Api(tags="日间病房护理记录单")
@Controller
@RequestMapping("/nisrjbfhld")
public class NisRjbfhldCtr extends BaseSpringController{
    
    static final Logger logger = LoggerFactory.getLogger(NisRjbfhldCtr.class);
    
    @Autowired
    private NisRjbfhldSer nisRjbfhldSer;
    
    @Autowired
    private RedisFactory redisFactory;
    
    @RequestMapping("/queryRjbfhldByDate")
    @ResponseBody
    @ApiOperation(value="根据日期查询日间病房护理单" ,httpMethod="POST")
    public ReturnEntity<List<NisRjbfhld>> queryRjbfhldByDate(@ApiParam(name = "zyh", value = "住院号", required = true)
    @RequestParam Integer zyh, @ApiParam(name = "queryDate", value = "查询时间(M-d H:i)", required = false)
    @RequestParam(value="queryDate", required = false) String queryDate){
        return ReturnEntityUtil.success(nisRjbfhldSer.getEntityMapper().queryRjbfhldByDate(zyh, 
        		queryDate, this.getUser().getHospitalId()));
    }
  
    @RequestMapping("/saveRjbfhld")
    @ResponseBody
    @ApiOperation(value="新增修改保存日间病房护理单" ,httpMethod="POST")
    public ReturnEntity saveRjbfhld(NisRjbfhldReq nisRjbfhldReq){
    	nisRjbfhldReq.setJgid(this.getUser().getHospitalId());
    	if(StrUtil.isBlankIfStr(nisRjbfhldReq.getJlxh())) {
    		nisRjbfhldReq.setJlxh(redisFactory.getTableKey(TableName.DB_NAME, TableName.NIS_RJBFHLD));
    		nisRjbfhldSer.insert(nisRjbfhldReq);
    	}else {
    		nisRjbfhldSer.update(nisRjbfhldReq);
    	}
        return ReturnEntityUtil.success();    
    }
   
    @RequestMapping("/deleteRjbfhldByJlxhDate")
    @ResponseBody
    @ApiOperation(value="根据日期删除日间病房护理单" ,httpMethod="POST")
    public ReturnEntity deleteRjbfhldByJlxhDate(@ApiParam(name = "zyh", value = "住院号", required = true)
    @RequestParam Integer zyh, @ApiParam(name = "queryDate", value = "查询时间(M-d H:i)", required = true)
    @RequestParam String queryDate){
    	List<NisRjbfhld> list = nisRjbfhldSer.getEntityMapper().queryRjbfhldByDate(zyh, 
        		queryDate, this.getUser().getHospitalId());
    	for(NisRjbfhld nisRjbfhld : list) {
    		nisRjbfhldSer.removeById(nisRjbfhld.getJlxh());
    	}
        return ReturnEntityUtil.success();
    }
    
}

