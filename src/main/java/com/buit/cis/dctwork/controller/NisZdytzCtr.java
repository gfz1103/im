
package com.buit.cis.dctwork.controller;

import java.util.List;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.Page;
import com.buit.utill.ReturnEntity;
import com.buit.utill.ReturnEntityUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

import com.buit.commons.BaseSpringController;
import com.buit.cis.dctwork.model.NisZdytz;
import com.buit.cis.dctwork.service.NisZdytzSer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 体温单自定义表<br>
 * @author GONGFANGZHOU
 */
@Api(tags="体温单自定义表")
@Controller
@RequestMapping("/niszdytz")
public class NisZdytzCtr extends BaseSpringController{
    
    static final Logger logger = LoggerFactory.getLogger(NisZdytzCtr.class);
    
    @Autowired
    private NisZdytzSer nisZdytzSer;
    
    @RequestMapping("/queryZdytzByCurrentWeek")
    @ResponseBody
    @ApiOperation(value="根据当前周查询自定义体征列" ,httpMethod="POST")
    public ReturnEntity<List<NisZdytz>> queryZdytzByCurrentWeek(@ApiParam(name = "zyh", value = "住院号", required = true) 
	@RequestParam Integer zyh, @ApiParam(name = "currentWeek", value = "周数", required = true) 
	@RequestParam Integer currentWeek){
    	NisZdytz nisZdytz = new NisZdytz(); 
    	nisZdytz.setZyh(zyh);
    	nisZdytz.setDqzs(currentWeek);
    	nisZdytz.setJgid(this.getUser().getHospitalId());
    	nisZdytz.setSortColumns("ZDYID ASC");
        return ReturnEntityUtil.success(nisZdytzSer.findByEntity(nisZdytz));
    }
   
//    @RequestMapping("/findList")
//    @ResponseBody
//    @ApiOperation(value="按条件查询-返回列表" ,httpMethod="POST")
//    public ReturnEntity<List<NisZdytzResp>> getByEntity( NisZdytzReq niszdytz){//@RequestBody 
//        return ReturnEntityUtil.success(nisZdytzSer.findByEntity(niszdytz));    
//    }
//    
//    @RequestMapping("/getOneByEntity")
//    @ResponseBody
//    @ApiOperation(value="按条件查询-返回唯一值" ,httpMethod="POST")
//    public ReturnEntity<NisZdytzResp> getOneByEntity(NisZdytzReq niszdytz){
//        List<NisZdytz> list=nisZdytzSer.findByEntity(niszdytz);
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
//    public ReturnEntity<NisZdytzResp> add(NisZdytzReq nisZdytz) {
//        nisZdytzSer.insert(nisZdytz);        
//        return ReturnEntityUtil.success(nisZdytz);            
//    }
//    /** 编辑 */
//    @RequestMapping("/edit")
//    @ResponseBody
//    @ApiOperation(value="编辑" ,httpMethod="POST")
//    public ReturnEntity<NisZdytzResp> edit(NisZdytzReq nisZdytz)  {
//        nisZdytzSer.update(nisZdytz);        
//        return ReturnEntityUtil.success(nisZdytz);            
//    }
//    
//    /** 删除 */
//    @RequestMapping("/delete")
//    @ResponseBody
//    @ApiOperation(value="按条件查询" ,httpMethod="POST")
//    public ReturnEntity<NisZdytzResp> delete(NisZdytzReq nisZdytz) {
//        nisZdytzSer.removeByEntity(nisZdytz);        
//        return ReturnEntityUtil.success(nisZdytz);            
//    }
    
}

