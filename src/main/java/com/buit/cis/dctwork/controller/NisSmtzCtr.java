
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
import com.buit.cis.dctwork.service.NisSmtzSer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 生命体征<br>
 * @author GONGFANGZHOU
 */
@Api(tags="生命体征")
@Controller
@RequestMapping("/nissmtz")
public class NisSmtzCtr extends BaseSpringController{
    
    static final Logger logger = LoggerFactory.getLogger(NisSmtzCtr.class);
    
    @Autowired
    private NisSmtzSer nisSmtzSer;
    
//    @RequestMapping("/queryPage")
//    @ResponseBody
//    @ApiOperation(value="按条件分页查询" ,httpMethod="POST")
//    public ReturnEntity<PageInfo<NisSmtzResp>> queryPage(NisSmtzReq nissmtz,PageQuery page){
//        PageInfo<NisSmtz> pageInfo = PageHelper.startPage(
//            page.getPageNum(), page.getPageSize()).doSelectPageInfo(
//                    () -> nisSmtzSer.findByEntity(nissmtz)
//            );
//        return ReturnEntityUtil.success(pageInfo);
//    }
//    
//    @RequestMapping("/findList")
//    @ResponseBody
//    @ApiOperation(value="按条件查询-返回列表" ,httpMethod="POST")
//    public ReturnEntity<List<NisSmtzResp>> getByEntity( NisSmtzReq nissmtz){//@RequestBody 
//        return ReturnEntityUtil.success(nisSmtzSer.findByEntity(nissmtz));    
//    }
//    
//    @RequestMapping("/getOneByEntity")
//    @ResponseBody
//    @ApiOperation(value="按条件查询-返回唯一值" ,httpMethod="POST")
//    public ReturnEntity<NisSmtzResp> getOneByEntity(NisSmtzReq nissmtz){
//        List<NisSmtz> list=nisSmtzSer.findByEntity(nissmtz);
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
//    public ReturnEntity<NisSmtzResp> add(NisSmtzReq nisSmtz) {
//        nisSmtzSer.insert(nisSmtz);        
//        return ReturnEntityUtil.success(nisSmtz);            
//    }
//    /** 编辑 */
//    @RequestMapping("/edit")
//    @ResponseBody
//    @ApiOperation(value="编辑" ,httpMethod="POST")
//    public ReturnEntity<NisSmtzResp> edit(NisSmtzReq nisSmtz)  {
//        nisSmtzSer.update(nisSmtz);        
//        return ReturnEntityUtil.success(nisSmtz);            
//    }
//    
//    /** 删除 */
//    @RequestMapping("/delete")
//    @ResponseBody
//    @ApiOperation(value="按条件查询" ,httpMethod="POST")
//    public ReturnEntity<NisSmtzResp> delete(NisSmtzReq nisSmtz) {
//        nisSmtzSer.removeByEntity(nisSmtz);        
//        return ReturnEntityUtil.success(nisSmtz);            
//    }
    
}

