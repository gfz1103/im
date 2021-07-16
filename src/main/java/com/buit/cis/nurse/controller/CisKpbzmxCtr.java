
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
import com.buit.cis.nurse.service.CisKpbzmxSer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 包装明细<br>
 * @author GONGFANGZHOU
 */
@Api(tags="包装明细")
@Controller
@RequestMapping("/ciskpbzmx")
public class CisKpbzmxCtr extends BaseSpringController{
    
    static final Logger logger = LoggerFactory.getLogger(CisKpbzmxCtr.class);
    
    @Autowired
    private CisKpbzmxSer cisKpbzmxSer;
    
//    @RequestMapping("/queryPage")
//    @ResponseBody
//    @ApiOperation(value="按条件分页查询" ,httpMethod="POST")
//    public ReturnEntity<PageInfo<CisKpbzmxResp>> queryPage(CisKpbzmxReq ciskpbzmx,PageQuery page){
//        PageInfo<CisKpbzmx> pageInfo = PageHelper.startPage(
//            page.getPageNum(), page.getPageSize()).doSelectPageInfo(
//                    () -> cisKpbzmxSer.findByEntity(ciskpbzmx)
//            );
//        return ReturnEntityUtil.success(pageInfo);
//    }
//    
//    @RequestMapping("/findList")
//    @ResponseBody
//    @ApiOperation(value="按条件查询-返回列表" ,httpMethod="POST")
//    public ReturnEntity<List<CisKpbzmxResp>> getByEntity( CisKpbzmxReq ciskpbzmx){//@RequestBody 
//        return ReturnEntityUtil.success(cisKpbzmxSer.findByEntity(ciskpbzmx));    
//    }
//    
//    @RequestMapping("/getOneByEntity")
//    @ResponseBody
//    @ApiOperation(value="按条件查询-返回唯一值" ,httpMethod="POST")
//    public ReturnEntity<CisKpbzmxResp> getOneByEntity(CisKpbzmxReq ciskpbzmx){
//        List<CisKpbzmx> list=cisKpbzmxSer.findByEntity(ciskpbzmx);
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
//    public ReturnEntity<CisKpbzmxResp> add(CisKpbzmxReq cisKpbzmx) {
//        cisKpbzmxSer.insert(cisKpbzmx);        
//        return ReturnEntityUtil.success(cisKpbzmx);            
//    }
//    /** 编辑 */
//    @RequestMapping("/edit")
//    @ResponseBody
//    @ApiOperation(value="编辑" ,httpMethod="POST")
//    public ReturnEntity<CisKpbzmxResp> edit(CisKpbzmxReq cisKpbzmx)  {
//        cisKpbzmxSer.update(cisKpbzmx);        
//        return ReturnEntityUtil.success(cisKpbzmx);            
//    }
//    
//    /** 删除 */
//    @RequestMapping("/delete")
//    @ResponseBody
//    @ApiOperation(value="按条件查询" ,httpMethod="POST")
//    public ReturnEntity<CisKpbzmxResp> delete(CisKpbzmxReq cisKpbzmx) {
//        cisKpbzmxSer.removeByEntity(cisKpbzmx);        
//        return ReturnEntityUtil.success(cisKpbzmx);            
//    }
    
}

