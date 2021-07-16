
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
import com.buit.cis.dctwork.service.PubCsrzSer;

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
@RequestMapping("/pubcsrz")
public class PubCsrzCtr extends BaseSpringController{
    
    static final Logger logger = LoggerFactory.getLogger(PubCsrzCtr.class);
    
    @Autowired
    private PubCsrzSer gyCsrzSer;
    
//    @RequestMapping("/queryPage")
//    @ResponseBody
//    @ApiOperation(value="按条件分页查询" ,httpMethod="POST")
//    public ReturnEntity<PageInfo<GyCsrzResp>> queryPage(GyCsrzReq gycsrz,PageQuery page){
//        PageInfo<GyCsrz> pageInfo = PageHelper.startPage(
//            page.getPageNum(), page.getPageSize()).doSelectPageInfo(
//                    () -> gyCsrzSer.findByEntity(gycsrz)
//            );
//        return ReturnEntityUtil.success(pageInfo);
//    }
//    
//    @RequestMapping("/findList")
//    @ResponseBody
//    @ApiOperation(value="按条件查询-返回列表" ,httpMethod="POST")
//    public ReturnEntity<List<GyCsrzResp>> getByEntity( GyCsrzReq gycsrz){//@RequestBody 
//        return ReturnEntityUtil.success(gyCsrzSer.findByEntity(gycsrz));    
//    }
//    
//    @RequestMapping("/getOneByEntity")
//    @ResponseBody
//    @ApiOperation(value="按条件查询-返回唯一值" ,httpMethod="POST")
//    public ReturnEntity<GyCsrzResp> getOneByEntity(GyCsrzReq gycsrz){
//        List<GyCsrz> list=gyCsrzSer.findByEntity(gycsrz);
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
//    public ReturnEntity<GyCsrzResp> add(GyCsrzReq gyCsrz) {
//        gyCsrzSer.insert(gyCsrz);        
//        return ReturnEntityUtil.success(gyCsrz);            
//    }
//    /** 编辑 */
//    @RequestMapping("/edit")
//    @ResponseBody
//    @ApiOperation(value="编辑" ,httpMethod="POST")
//    public ReturnEntity<GyCsrzResp> edit(GyCsrzReq gyCsrz)  {
//        gyCsrzSer.update(gyCsrz);        
//        return ReturnEntityUtil.success(gyCsrz);            
//    }
//    
//    /** 删除 */
//    @RequestMapping("/delete")
//    @ResponseBody
//    @ApiOperation(value="按条件查询" ,httpMethod="POST")
//    public ReturnEntity<GyCsrzResp> delete(GyCsrzReq gyCsrz) {
//        gyCsrzSer.removeByEntity(gyCsrz);        
//        return ReturnEntityUtil.success(gyCsrz);            
//    }
    
}

