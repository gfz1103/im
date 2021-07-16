
package com.buit.cis.nurse.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.buit.cis.nurse.model.NisHljhdmb;
import com.buit.cis.nurse.request.NisHljhdmbReq;
import com.buit.cis.nurse.service.NisHljhdmbSer;
import com.buit.commons.BaseSpringController;
import com.buit.commons.PageQuery;
import com.buit.constans.TableName;
import com.buit.utill.RedisFactory;
import com.buit.utill.ReturnEntity;
import com.buit.utill.ReturnEntityUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import cn.hutool.core.util.StrUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * 护理执行单模板<br>
 * @author GONGFANGZHOU
 */
@Api(tags="护理计划单模板")
@Controller
@RequestMapping("/nishljhdmb")
public class NisHljhdmbCtr extends BaseSpringController{
    
    static final Logger logger = LoggerFactory.getLogger(NisHljhdmbCtr.class);
    
    @Autowired
    private NisHljhdmbSer nisHljhdmbSer;
    
    @Autowired
    private RedisFactory redisFactory;
    
    @RequestMapping("/queryPage")
    @ResponseBody
    @ApiOperation(value="护理计划单模板-按条件分页查询" ,httpMethod="POST")
    public ReturnEntity<PageInfo<NisHljhdmb>> queryPage(NisHljhdmbReq nishljhdmb,PageQuery page){
    	nishljhdmb.setJgid(this.getUser().getHospitalId());
    	PageInfo<NisHljhdmb> pageInfo = PageHelper.startPage(
            page.getPageNum(), page.getPageSize()).doSelectPageInfo(
                    () -> nisHljhdmbSer.findByEntity(nishljhdmb)
            );
        return ReturnEntityUtil.success(pageInfo);
    }
    
    @RequestMapping("/getById")
    @ResponseBody
    @ApiOperation(value="查询护理计划单模板信息" ,httpMethod="POST")
    public ReturnEntity<NisHljhdmb> getById(@ApiParam(name = "jlxh", value = "jlxh", required = true)
	@RequestParam Integer jlxh){
        return ReturnEntityUtil.success(nisHljhdmbSer.getById(jlxh));
    }

    /** 新增 */
    @RequestMapping("/saveOrUpdate")
    @ResponseBody
    @ApiOperation(value="护理计划单模板新增或修改" ,httpMethod="POST")
    public ReturnEntity saveOrUpdate(NisHljhdmbReq nisHljhdmb) {
    	nisHljhdmb.setJgid(this.getUser().getHospitalId());
    	if(StrUtil.isBlankIfStr(nisHljhdmb.getJlxh())) {
    		nisHljhdmb.setJlxh(redisFactory.getTableKey(TableName.DB_NAME, TableName.NIS_HLJHDMB));
    		nisHljhdmbSer.insert(nisHljhdmb);    
    	}else {
    		nisHljhdmbSer.update(nisHljhdmb);    
    	}     
        return ReturnEntityUtil.success();            
    }

    /** 删除 */
    @RequestMapping("/deleteByJlxh")
    @ResponseBody
    @ApiOperation(value="删除护理计划单模板" ,httpMethod="POST")
    public ReturnEntity deleteByJlxh(@ApiParam(name = "jlxh", value = "记录序号", required = true)
    @RequestParam Integer jlxh) {
        nisHljhdmbSer.removeById(jlxh);
        return ReturnEntityUtil.success();            
    }
    
    @RequestMapping("/invalid")
    @ResponseBody
    @ApiOperation(value="护理计划单模板作废或取消作废" ,httpMethod="POST")
    public ReturnEntity invalid(@ApiParam(name = "jlxh", value = "jlxh", required = true)
	@RequestParam Integer jlxh,@ApiParam(name = "zfpb", value = "作废判别(作废:1,取消:0)", required = true)
	@RequestParam Integer zfpb)  {
    	nisHljhdmbSer.getEntityMapper().invalid(jlxh, zfpb);   
        return ReturnEntityUtil.success();            
    }
    
}

