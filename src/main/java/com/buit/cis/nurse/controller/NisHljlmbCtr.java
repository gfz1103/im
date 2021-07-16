
package com.buit.cis.nurse.controller;

import java.util.List;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.Page;
import com.buit.utill.ReturnEntity;
import com.buit.utill.ReturnEntityUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import com.buit.commons.BaseSpringController;
import com.buit.cis.nurse.service.NisHljlmbSer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 护理记录模板<br>
 * @author GONGFANGZHOU
 */
@Api(tags="护理记录模板")
@Controller
@RequestMapping("/nishljlmb")
public class NisHljlmbCtr extends BaseSpringController{
    
    static final Logger logger = LoggerFactory.getLogger(NisHljlmbCtr.class);
    
    @Autowired
    private NisHljlmbSer nisHljlmbSer;
    
//    @RequestMapping("/queryPage")
//    @ResponseBody
//    @ApiOperation(value="按条件分页查询" ,httpMethod="POST")
//    public ReturnEntity<PageInfo<NisHljlmbResp>> queryPage(NisHljlmbReq nishljlmb,PageQuery page){
//        PageInfo<NisHljlmb> pageInfo = PageHelper.startPage(
//            page.getPageNum(), page.getPageSize()).doSelectPageInfo(
//                    () -> nisHljlmbSer.findByEntity(nishljlmb)
//            );
//        return ReturnEntityUtil.success(pageInfo);
//    }
//    
//    @RequestMapping("/findList")
//    @ResponseBody
//    @ApiOperation(value="按条件查询-返回列表" ,httpMethod="POST")
//    public ReturnEntity<List<NisHljlmbResp>> getByEntity( NisHljlmbReq nishljlmb){//@RequestBody 
//        return ReturnEntityUtil.success(nisHljlmbSer.findByEntity(nishljlmb));    
//    }
//    
//    @RequestMapping("/getOneByEntity")
//    @ResponseBody
//    @ApiOperation(value="按条件查询-返回唯一值" ,httpMethod="POST")
//    public ReturnEntity<NisHljlmbResp> getOneByEntity(NisHljlmbReq nishljlmb){
//        List<NisHljlmb> list=nisHljlmbSer.findByEntity(nishljlmb);
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
//    public ReturnEntity<NisHljlmbResp> add(NisHljlmbReq nisHljlmb) {
//        nisHljlmbSer.insert(nisHljlmb);        
//        return ReturnEntityUtil.success(nisHljlmb);            
//    }
//    /** 编辑 */
//    @RequestMapping("/edit")
//    @ResponseBody
//    @ApiOperation(value="编辑" ,httpMethod="POST")
//    public ReturnEntity<NisHljlmbResp> edit(NisHljlmbReq nisHljlmb)  {
//        nisHljlmbSer.update(nisHljlmb);        
//        return ReturnEntityUtil.success(nisHljlmb);            
//    }
//    
//    /** 删除 */
//    @RequestMapping("/delete")
//    @ResponseBody
//    @ApiOperation(value="按条件查询" ,httpMethod="POST")
//    public ReturnEntity<NisHljlmbResp> delete(NisHljlmbReq nisHljlmb) {
//        nisHljlmbSer.removeByEntity(nisHljlmb);        
//        return ReturnEntityUtil.success(nisHljlmb);            
//    }
    
}

