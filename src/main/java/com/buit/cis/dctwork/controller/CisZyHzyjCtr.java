
package com.buit.cis.dctwork.controller;

import java.util.List;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.Page;
import com.buit.utill.ReturnEntity;
import com.buit.utill.ReturnEntityUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import com.buit.commons.BaseSpringController;
import com.buit.cis.dctwork.service.CisZyHzyjSer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * <br>
 * @author GONGFANGZHOU
 */
@Api(tags="")
@Controller
@RequestMapping("/ciszyhzyj")
public class CisZyHzyjCtr extends BaseSpringController{
    
    static final Logger logger = LoggerFactory.getLogger(CisZyHzyjCtr.class);
    
    @Autowired
    private CisZyHzyjSer cisZyHzyjSer;
    
//    @RequestMapping("/queryPage")
//    @ResponseBody
//    @ApiOperation(value="按条件分页查询" ,httpMethod="POST")
//    public ReturnEntity<PageInfo<CisZyHzyjResp>> queryPage(CisZyHzyjReq ciszyhzyj,PageQuery page){
//        PageInfo<CisZyHzyj> pageInfo = PageHelper.startPage(
//            page.getPageNum(), page.getPageSize()).doSelectPageInfo(
//                    () -> cisZyHzyjSer.findByEntity(ciszyhzyj)
//            );
//        return ReturnEntityUtil.success(pageInfo);
//    }
//    
//    @RequestMapping("/findList")
//    @ResponseBody
//    @ApiOperation(value="按条件查询-返回列表" ,httpMethod="POST")
//    public ReturnEntity<List<CisZyHzyjResp>> getByEntity( CisZyHzyjReq ciszyhzyj){//@RequestBody 
//        return ReturnEntityUtil.success(cisZyHzyjSer.findByEntity(ciszyhzyj));    
//    }
//    
//    @RequestMapping("/getOneByEntity")
//    @ResponseBody
//    @ApiOperation(value="按条件查询-返回唯一值" ,httpMethod="POST")
//    public ReturnEntity<CisZyHzyjResp> getOneByEntity(CisZyHzyjReq ciszyhzyj){
//        List<CisZyHzyj> list=cisZyHzyjSer.findByEntity(ciszyhzyj);
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
//    public ReturnEntity<CisZyHzyjResp> add(CisZyHzyjReq cisZyHzyj) {
//        cisZyHzyjSer.insert(cisZyHzyj);        
//        return ReturnEntityUtil.success(cisZyHzyj);            
//    }
//    /** 编辑 */
//    @RequestMapping("/edit")
//    @ResponseBody
//    @ApiOperation(value="编辑" ,httpMethod="POST")
//    public ReturnEntity<CisZyHzyjResp> edit(CisZyHzyjReq cisZyHzyj)  {
//        cisZyHzyjSer.update(cisZyHzyj);        
//        return ReturnEntityUtil.success(cisZyHzyj);            
//    }
//    
//    /** 删除 */
//    @RequestMapping("/delete")
//    @ResponseBody
//    @ApiOperation(value="按条件查询" ,httpMethod="POST")
//    public ReturnEntity<CisZyHzyjResp> delete(CisZyHzyjReq cisZyHzyj) {
//        cisZyHzyjSer.removeByEntity(cisZyHzyj);        
//        return ReturnEntityUtil.success(cisZyHzyj);            
//    }
    
}

