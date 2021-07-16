
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
import com.buit.cis.nurse.service.NisYzdySer;

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
@RequestMapping("/nisyzdy")
public class NisYzdyCtr extends BaseSpringController{
    
    static final Logger logger = LoggerFactory.getLogger(NisYzdyCtr.class);
    
    @Autowired
    private NisYzdySer nisYzdySer;
    
//    @RequestMapping("/queryPage")
//    @ResponseBody
//    @ApiOperation(value="按条件分页查询" ,httpMethod="POST")
//    public ReturnEntity<PageInfo<NisYzdyResp>> queryPage(NisYzdyReq nisyzdy,PageQuery page){
//        PageInfo<NisYzdy> pageInfo = PageHelper.startPage(
//            page.getPageNum(), page.getPageSize()).doSelectPageInfo(
//                    () -> nisYzdySer.findByEntity(nisyzdy)
//            );
//        return ReturnEntityUtil.success(pageInfo);
//    }
//    
//    @RequestMapping("/findList")
//    @ResponseBody
//    @ApiOperation(value="按条件查询-返回列表" ,httpMethod="POST")
//    public ReturnEntity<List<NisYzdyResp>> getByEntity( NisYzdyReq nisyzdy){//@RequestBody 
//        return ReturnEntityUtil.success(nisYzdySer.findByEntity(nisyzdy));    
//    }
//    
//    @RequestMapping("/getOneByEntity")
//    @ResponseBody
//    @ApiOperation(value="按条件查询-返回唯一值" ,httpMethod="POST")
//    public ReturnEntity<NisYzdyResp> getOneByEntity(NisYzdyReq nisyzdy){
//        List<NisYzdy> list=nisYzdySer.findByEntity(nisyzdy);
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
//    public ReturnEntity<NisYzdyResp> add(NisYzdyReq nisYzdy) {
//        nisYzdySer.insert(nisYzdy);        
//        return ReturnEntityUtil.success(nisYzdy);            
//    }
//    /** 编辑 */
//    @RequestMapping("/edit")
//    @ResponseBody
//    @ApiOperation(value="编辑" ,httpMethod="POST")
//    public ReturnEntity<NisYzdyResp> edit(NisYzdyReq nisYzdy)  {
//        nisYzdySer.update(nisYzdy);        
//        return ReturnEntityUtil.success(nisYzdy);            
//    }
//    
//    /** 删除 */
//    @RequestMapping("/delete")
//    @ResponseBody
//    @ApiOperation(value="按条件查询" ,httpMethod="POST")
//    public ReturnEntity<NisYzdyResp> delete(NisYzdyReq nisYzdy) {
//        nisYzdySer.removeByEntity(nisYzdy);        
//        return ReturnEntityUtil.success(nisYzdy);            
//    }
    
}

