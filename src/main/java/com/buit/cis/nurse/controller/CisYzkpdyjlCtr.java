
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
import com.buit.cis.nurse.service.CisYzkpdyjlSer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 住院医嘱医嘱卡片打印记录<br>
 * @author GONGFANGZHOU
 */
@Api(tags="住院医嘱医嘱卡片打印记录")
@Controller
@RequestMapping("/cisyzkpdyjl")
public class CisYzkpdyjlCtr extends BaseSpringController{
    
    static final Logger logger = LoggerFactory.getLogger(CisYzkpdyjlCtr.class);
    
    @Autowired
    private CisYzkpdyjlSer cisYzkpdyjlSer;
    
//    @RequestMapping("/queryPage")
//    @ResponseBody
//    @ApiOperation(value="按条件分页查询" ,httpMethod="POST")
//    public ReturnEntity<PageInfo<CisYzkpdyjlResp>> queryPage(CisYzkpdyjlReq cisyzkpdyjl,PageQuery page){
//        PageInfo<CisYzkpdyjl> pageInfo = PageHelper.startPage(
//            page.getPageNum(), page.getPageSize()).doSelectPageInfo(
//                    () -> cisYzkpdyjlSer.findByEntity(cisyzkpdyjl)
//            );
//        return ReturnEntityUtil.success(pageInfo);
//    }
//    
//    @RequestMapping("/findList")
//    @ResponseBody
//    @ApiOperation(value="按条件查询-返回列表" ,httpMethod="POST")
//    public ReturnEntity<List<CisYzkpdyjlResp>> getByEntity( CisYzkpdyjlReq cisyzkpdyjl){//@RequestBody 
//        return ReturnEntityUtil.success(cisYzkpdyjlSer.findByEntity(cisyzkpdyjl));    
//    }
//    
//    @RequestMapping("/getOneByEntity")
//    @ResponseBody
//    @ApiOperation(value="按条件查询-返回唯一值" ,httpMethod="POST")
//    public ReturnEntity<CisYzkpdyjlResp> getOneByEntity(CisYzkpdyjlReq cisyzkpdyjl){
//        List<CisYzkpdyjl> list=cisYzkpdyjlSer.findByEntity(cisyzkpdyjl);
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
//    public ReturnEntity<CisYzkpdyjlResp> add(CisYzkpdyjlReq cisYzkpdyjl) {
//        cisYzkpdyjlSer.insert(cisYzkpdyjl);        
//        return ReturnEntityUtil.success(cisYzkpdyjl);            
//    }
//    /** 编辑 */
//    @RequestMapping("/edit")
//    @ResponseBody
//    @ApiOperation(value="编辑" ,httpMethod="POST")
//    public ReturnEntity<CisYzkpdyjlResp> edit(CisYzkpdyjlReq cisYzkpdyjl)  {
//        cisYzkpdyjlSer.update(cisYzkpdyjl);        
//        return ReturnEntityUtil.success(cisYzkpdyjl);            
//    }
//    
//    /** 删除 */
//    @RequestMapping("/delete")
//    @ResponseBody
//    @ApiOperation(value="按条件查询" ,httpMethod="POST")
//    public ReturnEntity<CisYzkpdyjlResp> delete(CisYzkpdyjlReq cisYzkpdyjl) {
//        cisYzkpdyjlSer.removeByEntity(cisYzkpdyjl);        
//        return ReturnEntityUtil.success(cisYzkpdyjl);            
//    }
    
}

