
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
import com.buit.cis.dctwork.service.EnrJg02Ser;

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
@RequestMapping("/enrjg02")
public class EnrJg02Ctr extends BaseSpringController{
    
    static final Logger logger = LoggerFactory.getLogger(EnrJg02Ctr.class);
    
    @Autowired
    private EnrJg02Ser enrJg02Ser;
    
//    @RequestMapping("/queryPage")
//    @ResponseBody
//    @ApiOperation(value="按条件分页查询" ,httpMethod="POST")
//    public ReturnEntity<PageInfo<EnrJg02Resp>> queryPage(EnrJg02Req enrjg02,PageQuery page){
//        PageInfo<EnrJg02> pageInfo = PageHelper.startPage(
//            page.getPageNum(), page.getPageSize()).doSelectPageInfo(
//                    () -> enrJg02Ser.findByEntity(enrjg02)
//            );
//        return ReturnEntityUtil.success(pageInfo);
//    }
//    
//    @RequestMapping("/findList")
//    @ResponseBody
//    @ApiOperation(value="按条件查询-返回列表" ,httpMethod="POST")
//    public ReturnEntity<List<EnrJg02Resp>> getByEntity( EnrJg02Req enrjg02){//@RequestBody 
//        return ReturnEntityUtil.success(enrJg02Ser.findByEntity(enrjg02));    
//    }
//    
//    @RequestMapping("/getOneByEntity")
//    @ResponseBody
//    @ApiOperation(value="按条件查询-返回唯一值" ,httpMethod="POST")
//    public ReturnEntity<EnrJg02Resp> getOneByEntity(EnrJg02Req enrjg02){
//        List<EnrJg02> list=enrJg02Ser.findByEntity(enrjg02);
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
//    public ReturnEntity<EnrJg02Resp> add(EnrJg02Req enrJg02) {
//        enrJg02Ser.insert(enrJg02);        
//        return ReturnEntityUtil.success(enrJg02);            
//    }
//    /** 编辑 */
//    @RequestMapping("/edit")
//    @ResponseBody
//    @ApiOperation(value="编辑" ,httpMethod="POST")
//    public ReturnEntity<EnrJg02Resp> edit(EnrJg02Req enrJg02)  {
//        enrJg02Ser.update(enrJg02);        
//        return ReturnEntityUtil.success(enrJg02);            
//    }
//    
//    /** 删除 */
//    @RequestMapping("/delete")
//    @ResponseBody
//    @ApiOperation(value="按条件查询" ,httpMethod="POST")
//    public ReturnEntity<EnrJg02Resp> delete(EnrJg02Req enrJg02) {
//        enrJg02Ser.removeByEntity(enrJg02);        
//        return ReturnEntityUtil.success(enrJg02);            
//    }
    
}

