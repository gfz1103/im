
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
import com.buit.cis.dctwork.service.EnrJl02Ser;

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
@RequestMapping("/enrjl02")
public class EnrJl02Ctr extends BaseSpringController{
    
    static final Logger logger = LoggerFactory.getLogger(EnrJl02Ctr.class);
    
    @Autowired
    private EnrJl02Ser enrJl02Ser;
    
//    @RequestMapping("/queryPage")
//    @ResponseBody
//    @ApiOperation(value="按条件分页查询" ,httpMethod="POST")
//    public ReturnEntity<PageInfo<EnrJl02Resp>> queryPage(EnrJl02Req enrjl02,PageQuery page){
//        PageInfo<EnrJl02> pageInfo = PageHelper.startPage(
//            page.getPageNum(), page.getPageSize()).doSelectPageInfo(
//                    () -> enrJl02Ser.findByEntity(enrjl02)
//            );
//        return ReturnEntityUtil.success(pageInfo);
//    }
//    
//    @RequestMapping("/findList")
//    @ResponseBody
//    @ApiOperation(value="按条件查询-返回列表" ,httpMethod="POST")
//    public ReturnEntity<List<EnrJl02Resp>> getByEntity( EnrJl02Req enrjl02){//@RequestBody 
//        return ReturnEntityUtil.success(enrJl02Ser.findByEntity(enrjl02));    
//    }
//    
//    @RequestMapping("/getOneByEntity")
//    @ResponseBody
//    @ApiOperation(value="按条件查询-返回唯一值" ,httpMethod="POST")
//    public ReturnEntity<EnrJl02Resp> getOneByEntity(EnrJl02Req enrjl02){
//        List<EnrJl02> list=enrJl02Ser.findByEntity(enrjl02);
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
//    public ReturnEntity<EnrJl02Resp> add(EnrJl02Req enrJl02) {
//        enrJl02Ser.insert(enrJl02);        
//        return ReturnEntityUtil.success(enrJl02);            
//    }
//    /** 编辑 */
//    @RequestMapping("/edit")
//    @ResponseBody
//    @ApiOperation(value="编辑" ,httpMethod="POST")
//    public ReturnEntity<EnrJl02Resp> edit(EnrJl02Req enrJl02)  {
//        enrJl02Ser.update(enrJl02);        
//        return ReturnEntityUtil.success(enrJl02);            
//    }
//    
//    /** 删除 */
//    @RequestMapping("/delete")
//    @ResponseBody
//    @ApiOperation(value="按条件查询" ,httpMethod="POST")
//    public ReturnEntity<EnrJl02Resp> delete(EnrJl02Req enrJl02) {
//        enrJl02Ser.removeByEntity(enrJl02);        
//        return ReturnEntityUtil.success(enrJl02);            
//    }
    
}

