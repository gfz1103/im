
package com.buit.cis.nurse.controller;

import com.buit.cis.nurse.model.NisHljldzdy;
import com.buit.cis.nurse.response.NisHljldzdyResp;
import com.buit.cis.nurse.service.NisHljldzdySer;
import com.buit.commons.BaseSpringController;
import com.buit.utill.BUHISUtil;
import com.buit.utill.ReturnEntity;
import com.buit.utill.ReturnEntityUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

/**
 * 护理记录单自定义内容<br>
 * @author GONGFANGZHOU
 */
@Api(tags="护理记录单自定义内容")
@Controller
@RequestMapping("/nishljldzdy")
public class NisHljldzdyCtr extends BaseSpringController{
    
    static final Logger logger = LoggerFactory.getLogger(NisHljldzdyCtr.class);
    
    @Autowired
    private NisHljldzdySer nisHljldzdySer;
    
    @RequestMapping("/queryZdyTitle")
    @ResponseBody
    @ApiOperation(value="查询自定义内容列标题" ,httpMethod="POST")
    public ReturnEntity<List<NisHljldzdy>> queryZdyTitle(@ApiParam(name = "zyh", value = "住院号", required = true)
    @RequestParam Integer zyh,@ApiParam(name = "mblx", value = "模板类型", required = true)
    @RequestParam String mblx){
        return ReturnEntityUtil.success(nisHljldzdySer.getEntityMapper().queryZdyTitle(zyh, 
        		this.getUser().getHospitalId(), mblx));
    }
 
    @RequestMapping("/queryZdynrByZdyId")
    @ResponseBody
    @ApiOperation(value="查询自定义内容" ,httpMethod="POST")
    public ReturnEntity<List<NisHljldzdyResp>> queryZdynrByZdyId(@ApiParam(name = "jlxh", value = "记录序号", required = true)
    @RequestParam Integer jlxh, @ApiParam(name = "mblx", value = "模板类型", required = true)
    @RequestParam String mblx){
    	List<Map<String, Object>> zdyList = nisHljldzdySer.getEntityMapper().queryZdynrByZdyId(
    			jlxh, mblx, this.getUser().getHospitalId());
        return ReturnEntityUtil.success(BUHISUtil.ListToResultSet(zdyList, new NisHljldzdyResp()));
    }

//    @RequestMapping("/getOneByEntity")
//    @ResponseBody
//    @ApiOperation(value="按条件查询-返回唯一值" ,httpMethod="POST")
//    public ReturnEntity<NisHljldzdyResp> getOneByEntity(NisHljldzdyReq nishljldzdy){
//        List<NisHljldzdy> list=nisHljldzdySer.findByEntity(nishljldzdy);
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
//    public ReturnEntity<NisHljldzdyResp> add(NisHljldzdyReq nisHljldzdy) {
//        nisHljldzdySer.insert(nisHljldzdy);        
//        return ReturnEntityUtil.success(nisHljldzdy);            
//    }
//    /** 编辑 */
//    @RequestMapping("/edit")
//    @ResponseBody
//    @ApiOperation(value="编辑" ,httpMethod="POST")
//    public ReturnEntity<NisHljldzdyResp> edit(NisHljldzdyReq nisHljldzdy)  {
//        nisHljldzdySer.update(nisHljldzdy);        
//        return ReturnEntityUtil.success(nisHljldzdy);            
//    }
//    
//    /** 删除 */
//    @RequestMapping("/delete")
//    @ResponseBody
//    @ApiOperation(value="按条件查询" ,httpMethod="POST")
//    public ReturnEntity<NisHljldzdyResp> delete(NisHljldzdyReq nisHljldzdy) {
//        nisHljldzdySer.removeByEntity(nisHljldzdy);        
//        return ReturnEntityUtil.success(nisHljldzdy);            
//    }
    
}

