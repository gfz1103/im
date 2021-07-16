
package com.buit.cis.dctwork.controller;

import java.util.List;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.Page;
import com.buit.utill.ReturnEntity;
import com.buit.utill.ReturnEntityUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import com.buit.cis.dctwork.request.SkinPsjlReq;
import com.buit.cis.dctwork.response.SkinPsjlResp;
import com.buit.commons.BaseSpringController;
import com.buit.cis.dctwork.service.SkinPsjlSer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 公用_病人皮试记录<br>
 * @author GONGFANGZHOU
 */
@Api(tags="公用_病人皮试记录")
@Controller
@RequestMapping("/skinpsjl")
public class SkinPsjlCtr extends BaseSpringController{
    
    static final Logger logger = LoggerFactory.getLogger(SkinPsjlCtr.class);
    
    @Autowired
    private SkinPsjlSer skinPsjlSer;
    
//    @RequestMapping("/queryPage")
//    @ResponseBody
//    @ApiOperation(value="按条件分页查询" ,httpMethod="POST")
//    public ReturnEntity<PageInfo<SkinPsjlResp>> queryPage(SkinPsjlReq skinpsjl,PageQuery page){
//        PageInfo<SkinPsjl> pageInfo = PageHelper.startPage(
//            page.getPageNum(), page.getPageSize()).doSelectPageInfo(
//                    () -> skinPsjlSer.findByEntity(skinpsjl)
//            );
//        return ReturnEntityUtil.success(pageInfo);
//    }
//    
//    @RequestMapping("/findList")
//    @ResponseBody
//    @ApiOperation(value="按条件查询-返回列表" ,httpMethod="POST")
//    public ReturnEntity<List<SkinPsjlResp>> getByEntity( SkinPsjlReq skinpsjl){//@RequestBody 
//        return ReturnEntityUtil.success(skinPsjlSer.findByEntity(skinpsjl));    
//    }
//    
//    @RequestMapping("/getOneByEntity")
//    @ResponseBody
//    @ApiOperation(value="按条件查询-返回唯一值" ,httpMethod="POST")
//    public ReturnEntity<SkinPsjlResp> getOneByEntity(SkinPsjlReq skinpsjl){
//        List<SkinPsjl> list=skinPsjlSer.findByEntity(skinpsjl);
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
//    public ReturnEntity<SkinPsjlResp> add(SkinPsjlReq skinPsjl) {
//        skinPsjlSer.insert(skinPsjl);        
//        return ReturnEntityUtil.success(skinPsjl);            
//    }
//    /** 编辑 */
//    @RequestMapping("/edit")
//    @ResponseBody
//    @ApiOperation(value="编辑" ,httpMethod="POST")
//    public ReturnEntity<SkinPsjlResp> edit(SkinPsjlReq skinPsjl)  {
//        skinPsjlSer.update(skinPsjl);        
//        return ReturnEntityUtil.success(skinPsjl);            
//    }
//    
//    /** 删除 */
//    @RequestMapping("/delete")
//    @ResponseBody
//    @ApiOperation(value="按条件查询" ,httpMethod="POST")
//    public ReturnEntity<SkinPsjlResp> delete(SkinPsjlReq skinPsjl) {
//        skinPsjlSer.removeByEntity(skinPsjl);        
//        return ReturnEntityUtil.success(skinPsjl);            
//    }
    
}

