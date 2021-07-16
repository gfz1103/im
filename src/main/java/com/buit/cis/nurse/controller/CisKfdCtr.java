
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
import com.buit.cis.nurse.service.CisKfdSer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 口服单<br>
 * @author GONGFANGZHOU
 */
@Api(tags="口服单")
@Controller
@RequestMapping("/ciskfd")
public class CisKfdCtr extends BaseSpringController{
    
    static final Logger logger = LoggerFactory.getLogger(CisKfdCtr.class);
    
    @Autowired
    private CisKfdSer cisKfdSer;
    
//    @RequestMapping("/queryPage")
//    @ResponseBody
//    @ApiOperation(value="按条件分页查询" ,httpMethod="POST")
//    public ReturnEntity<PageInfo<CisKfdResp>> queryPage(CisKfdReq ciskfd,PageQuery page){
//        PageInfo<CisKfd> pageInfo = PageHelper.startPage(
//            page.getPageNum(), page.getPageSize()).doSelectPageInfo(
//                    () -> cisKfdSer.findByEntity(ciskfd)
//            );
//        return ReturnEntityUtil.success(pageInfo);
//    }
//    
//    @RequestMapping("/findList")
//    @ResponseBody
//    @ApiOperation(value="按条件查询-返回列表" ,httpMethod="POST")
//    public ReturnEntity<List<CisKfdResp>> getByEntity( CisKfdReq ciskfd){//@RequestBody 
//        return ReturnEntityUtil.success(cisKfdSer.findByEntity(ciskfd));    
//    }
//    
//    @RequestMapping("/getOneByEntity")
//    @ResponseBody
//    @ApiOperation(value="按条件查询-返回唯一值" ,httpMethod="POST")
//    public ReturnEntity<CisKfdResp> getOneByEntity(CisKfdReq ciskfd){
//        List<CisKfd> list=cisKfdSer.findByEntity(ciskfd);
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
//    public ReturnEntity<CisKfdResp> add(CisKfdReq cisKfd) {
//        cisKfdSer.insert(cisKfd);        
//        return ReturnEntityUtil.success(cisKfd);            
//    }
//    /** 编辑 */
//    @RequestMapping("/edit")
//    @ResponseBody
//    @ApiOperation(value="编辑" ,httpMethod="POST")
//    public ReturnEntity<CisKfdResp> edit(CisKfdReq cisKfd)  {
//        cisKfdSer.update(cisKfd);        
//        return ReturnEntityUtil.success(cisKfd);            
//    }
//    
//    /** 删除 */
//    @RequestMapping("/delete")
//    @ResponseBody
//    @ApiOperation(value="按条件查询" ,httpMethod="POST")
//    public ReturnEntity<CisKfdResp> delete(CisKfdReq cisKfd) {
//        cisKfdSer.removeByEntity(cisKfd);        
//        return ReturnEntityUtil.success(cisKfd);            
//    }
    
}

