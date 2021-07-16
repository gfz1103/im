
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
import com.buit.cis.dctwork.service.CisBxxypzSer;

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
@Api(tags="备血申请单血液品种表")
@Controller
@RequestMapping("/cisbxxypzCtr")
public class CisBxxypzCtr extends BaseSpringController{
    
    static final Logger logger = LoggerFactory.getLogger(CisBxxypzCtr.class);
    
    @Autowired
    private CisBxxypzSer CisBxxypzSer;
    
//    @RequestMapping("/queryPage")
//    @ResponseBody
//    @ApiOperation(value="按条件分页查询" ,httpMethod="POST")
//    public ReturnEntity<PageInfo<CisBxxypzResp>> queryPage(CisBxxypzReq CisBxxypz,PageQuery page){
//        PageInfo<CisBxxypz> pageInfo = PageHelper.startPage(
//            page.getPageNum(), page.getPageSize()).doSelectPageInfo(
//                    () -> CisBxxypzSer.findByEntity(CisBxxypz)
//            );
//        return ReturnEntityUtil.success(pageInfo);
//    }
//    
//    @RequestMapping("/findList")
//    @ResponseBody
//    @ApiOperation(value="按条件查询-返回列表" ,httpMethod="POST")
//    public ReturnEntity<List<CisBxxypzResp>> getByEntity( CisBxxypzReq CisBxxypz){//@RequestBody 
//        return ReturnEntityUtil.success(CisBxxypzSer.findByEntity(CisBxxypz));    
//    }
//    
//    @RequestMapping("/getOneByEntity")
//    @ResponseBody
//    @ApiOperation(value="按条件查询-返回唯一值" ,httpMethod="POST")
//    public ReturnEntity<CisBxxypzResp> getOneByEntity(CisBxxypzReq CisBxxypz){
//        List<CisBxxypz> list=CisBxxypzSer.findByEntity(CisBxxypz);
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
//    public ReturnEntity<CisBxxypzResp> add(CisBxxypzReq CisBxxypz) {
//        CisBxxypzSer.insert(CisBxxypz);        
//        return ReturnEntityUtil.success(CisBxxypz);            
//    }
//    /** 编辑 */
//    @RequestMapping("/edit")
//    @ResponseBody
//    @ApiOperation(value="编辑" ,httpMethod="POST")
//    public ReturnEntity<CisBxxypzResp> edit(CisBxxypzReq CisBxxypz)  {
//        CisBxxypzSer.update(CisBxxypz);        
//        return ReturnEntityUtil.success(CisBxxypz);            
//    }
//    
//    /** 删除 */
//    @RequestMapping("/delete")
//    @ResponseBody
//    @ApiOperation(value="按条件查询" ,httpMethod="POST")
//    public ReturnEntity<CisBxxypzResp> delete(CisBxxypzReq CisBxxypz) {
//        CisBxxypzSer.removeByEntity(CisBxxypz);        
//        return ReturnEntityUtil.success(CisBxxypz);            
//    }
    
}

