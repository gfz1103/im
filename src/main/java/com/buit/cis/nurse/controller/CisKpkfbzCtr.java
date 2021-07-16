
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
import com.buit.cis.nurse.service.CisKpkfbzSer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 口服包装<br>
 * @author GONGFANGZHOU
 */
@Api(tags="口服包装")
@Controller
@RequestMapping("/ciskpkfbz")
public class CisKpkfbzCtr extends BaseSpringController{
    
    static final Logger logger = LoggerFactory.getLogger(CisKpkfbzCtr.class);
    
    @Autowired
    private CisKpkfbzSer cisKpkfbzSer;
    
//    @RequestMapping("/queryPage")
//    @ResponseBody
//    @ApiOperation(value="按条件分页查询" ,httpMethod="POST")
//    public ReturnEntity<PageInfo<CisKpkfbzResp>> queryPage(CisKpkfbzReq ciskpkfbz,PageQuery page){
//        PageInfo<CisKpkfbz> pageInfo = PageHelper.startPage(
//            page.getPageNum(), page.getPageSize()).doSelectPageInfo(
//                    () -> cisKpkfbzSer.findByEntity(ciskpkfbz)
//            );
//        return ReturnEntityUtil.success(pageInfo);
//    }
//    
//    @RequestMapping("/findList")
//    @ResponseBody
//    @ApiOperation(value="按条件查询-返回列表" ,httpMethod="POST")
//    public ReturnEntity<List<CisKpkfbzResp>> getByEntity( CisKpkfbzReq ciskpkfbz){//@RequestBody 
//        return ReturnEntityUtil.success(cisKpkfbzSer.findByEntity(ciskpkfbz));    
//    }
//    
//    @RequestMapping("/getOneByEntity")
//    @ResponseBody
//    @ApiOperation(value="按条件查询-返回唯一值" ,httpMethod="POST")
//    public ReturnEntity<CisKpkfbzResp> getOneByEntity(CisKpkfbzReq ciskpkfbz){
//        List<CisKpkfbz> list=cisKpkfbzSer.findByEntity(ciskpkfbz);
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
//    public ReturnEntity<CisKpkfbzResp> add(CisKpkfbzReq cisKpkfbz) {
//        cisKpkfbzSer.insert(cisKpkfbz);        
//        return ReturnEntityUtil.success(cisKpkfbz);            
//    }
//    /** 编辑 */
//    @RequestMapping("/edit")
//    @ResponseBody
//    @ApiOperation(value="编辑" ,httpMethod="POST")
//    public ReturnEntity<CisKpkfbzResp> edit(CisKpkfbzReq cisKpkfbz)  {
//        cisKpkfbzSer.update(cisKpkfbz);        
//        return ReturnEntityUtil.success(cisKpkfbz);            
//    }
//    
//    /** 删除 */
//    @RequestMapping("/delete")
//    @ResponseBody
//    @ApiOperation(value="按条件查询" ,httpMethod="POST")
//    public ReturnEntity<CisKpkfbzResp> delete(CisKpkfbzReq cisKpkfbz) {
//        cisKpkfbzSer.removeByEntity(cisKpkfbz);        
//        return ReturnEntityUtil.success(cisKpkfbz);            
//    }
    
}

