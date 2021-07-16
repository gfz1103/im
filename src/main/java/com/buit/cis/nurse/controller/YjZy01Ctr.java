
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
import com.buit.cis.nurse.service.YjZy01Ser;

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
@RequestMapping("/yjzy01")
public class YjZy01Ctr extends BaseSpringController{
    
    static final Logger logger = LoggerFactory.getLogger(YjZy01Ctr.class);
    
    @Autowired
    private YjZy01Ser yjZy01Ser;
    
//    @RequestMapping("/queryPage")
//    @ResponseBody
//    @ApiOperation(value="按条件分页查询" ,httpMethod="POST")
//    public ReturnEntity<PageInfo<YjZy01Resp>> queryPage(YjZy01Req yjzy01,PageQuery page){
//        PageInfo<YjZy01> pageInfo = PageHelper.startPage(
//            page.getPageNum(), page.getPageSize()).doSelectPageInfo(
//                    () -> yjZy01Ser.findByEntity(yjzy01)
//            );
//        return ReturnEntityUtil.success(pageInfo);
//    }
//    
//    @RequestMapping("/findList")
//    @ResponseBody
//    @ApiOperation(value="按条件查询-返回列表" ,httpMethod="POST")
//    public ReturnEntity<List<YjZy01Resp>> getByEntity( YjZy01Req yjzy01){//@RequestBody 
//        return ReturnEntityUtil.success(yjZy01Ser.findByEntity(yjzy01));    
//    }
//    
//    @RequestMapping("/getOneByEntity")
//    @ResponseBody
//    @ApiOperation(value="按条件查询-返回唯一值" ,httpMethod="POST")
//    public ReturnEntity<YjZy01Resp> getOneByEntity(YjZy01Req yjzy01){
//        List<YjZy01> list=yjZy01Ser.findByEntity(yjzy01);
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
//    public ReturnEntity<YjZy01Resp> add(YjZy01Req yjZy01) {
//        yjZy01Ser.insert(yjZy01);        
//        return ReturnEntityUtil.success(yjZy01);            
//    }
//    /** 编辑 */
//    @RequestMapping("/edit")
//    @ResponseBody
//    @ApiOperation(value="编辑" ,httpMethod="POST")
//    public ReturnEntity<YjZy01Resp> edit(YjZy01Req yjZy01)  {
//        yjZy01Ser.update(yjZy01);        
//        return ReturnEntityUtil.success(yjZy01);            
//    }
//    
//    /** 删除 */
//    @RequestMapping("/delete")
//    @ResponseBody
//    @ApiOperation(value="按条件查询" ,httpMethod="POST")
//    public ReturnEntity<YjZy01Resp> delete(YjZy01Req yjZy01) {
//        yjZy01Ser.removeByEntity(yjZy01);        
//        return ReturnEntityUtil.success(yjZy01);            
//    }
    
}

