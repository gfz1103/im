
package com.buit.cis.nurse.controller;

import java.util.List;

import com.buit.cis.nurse.model.NisAllzxd;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.buit.cis.nurse.request.NisAllzxdReq;
import com.buit.cis.nurse.service.NisAllzxdSer;
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
 * 所有护理执行单<br>
 * @author GONGFANGZHOU
 */
@Api(tags="所有护理执行单")
@Controller
@RequestMapping("/nisallzxd")
public class NisAllzxdCtr extends BaseSpringController{
    
    static final Logger logger = LoggerFactory.getLogger(NisAllzxdCtr.class);
    
    @Autowired
    private NisAllzxdSer nisAllzxdSer;
    
    @Autowired
    private RedisFactory redisFactory;
    
    @RequestMapping("/queryZxdByDate")
    @ResponseBody
    @ApiOperation(value="根据日期查询模板类型对应执行单" ,httpMethod="POST")
    public ReturnEntity<List<NisAllzxd>> queryZxdByDate(@ApiParam(name = "zyh", value = "住院号", required = true)
    @RequestParam Integer zyh, @ApiParam(name = "queryDate", value = "查询时间(M-d H:i)", required = false)
    @RequestParam(value="queryDate", required = false) String queryDate, @ApiParam(name = "mblx", value = "模板类型", required = true)
    @RequestParam String mblx){
        return ReturnEntityUtil.success(nisAllzxdSer.getEntityMapper().queryZxdByDate(zyh, 
        		queryDate, this.getUser().getHospitalId(), mblx));
    }
  
    @RequestMapping("/saveCorrespondingZxd")
    @ResponseBody
    @ApiOperation(value="新增修改保存对应执行单" ,httpMethod="POST")
    public ReturnEntity saveCorrespondingZxd(@RequestBody List<NisAllzxdReq> list){
    	Integer zh = redisFactory.getTableKey(TableName.DB_NAME, TableName.NIS_ALLZXD + ".zh");
    	for(NisAllzxdReq nisAllzxdReq : list) {
    		nisAllzxdReq.setJgid(this.getUser().getHospitalId());
        	if(StrUtil.isBlankIfStr(nisAllzxdReq.getJlxh())) {
        		if(StrUtil.isBlankIfStr(nisAllzxdReq.getZh())) {
        			nisAllzxdReq.setZh(zh);
        		}
        		nisAllzxdReq.setJlxh(redisFactory.getTableKey(TableName.DB_NAME, TableName.NIS_ALLZXD));
        		nisAllzxdSer.insert(nisAllzxdReq);
        	}else {
        		nisAllzxdSer.update(nisAllzxdReq);
        	}
    	}
    	
        return ReturnEntityUtil.success();    
    }
   
    @RequestMapping("/deleteCorrespondingZxdByJlxh")
    @ResponseBody
    @ApiOperation(value="删除模板类型对应执行单" ,httpMethod="POST")
    public ReturnEntity deleteCorrespondingZxdByJlxh(@ApiParam(name = "zh", value = "组号", required = true)
    @RequestParam Integer zh){
    	nisAllzxdSer.getEntityMapper().deleteByZh(zh);;
        return ReturnEntityUtil.success();
    }
    
}

