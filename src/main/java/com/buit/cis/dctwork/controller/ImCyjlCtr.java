
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
import com.buit.cis.dctwork.service.ImCyjlSer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 住院_住院记录<br>
 * @author GONGFANGZHOU
 */
@Api(tags="住院_住院记录")
@Controller
@RequestMapping("/imcyjl")
public class ImCyjlCtr extends BaseSpringController{
    
    static final Logger logger = LoggerFactory.getLogger(ImCyjlCtr.class);
    
    @Autowired
    private ImCyjlSer imCyjlSer;
    
//    @RequestMapping("/queryPage")
//    @ResponseBody
//    @ApiOperation(value="按条件分页查询" ,httpMethod="POST")
//    public ReturnEntity<PageInfo<ImCyjlResp>> queryPage(ImCyjlReq imcyjl,PageQuery page){
//        PageInfo<ImCyjl> pageInfo = PageHelper.startPage(
//            page.getPageNum(), page.getPageSize()).doSelectPageInfo(
//                    () -> imCyjlSer.findByEntity(imcyjl)
//            );
//        return ReturnEntityUtil.success(pageInfo);
//    }
//    
//    @RequestMapping("/findList")
//    @ResponseBody
//    @ApiOperation(value="按条件查询-返回列表" ,httpMethod="POST")
//    public ReturnEntity<List<ImCyjlResp>> getByEntity( ImCyjlReq imcyjl){//@RequestBody 
//        return ReturnEntityUtil.success(imCyjlSer.findByEntity(imcyjl));    
//    }
//    
//    @RequestMapping("/getOneByEntity")
//    @ResponseBody
//    @ApiOperation(value="按条件查询-返回唯一值" ,httpMethod="POST")
//    public ReturnEntity<ImCyjlResp> getOneByEntity(ImCyjlReq imcyjl){
//        List<ImCyjl> list=imCyjlSer.findByEntity(imcyjl);
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
//    public ReturnEntity<ImCyjlResp> add(ImCyjlReq imCyjl) {
//        imCyjlSer.insert(imCyjl);        
//        return ReturnEntityUtil.success(imCyjl);            
//    }
//    /** 编辑 */
//    @RequestMapping("/edit")
//    @ResponseBody
//    @ApiOperation(value="编辑" ,httpMethod="POST")
//    public ReturnEntity<ImCyjlResp> edit(ImCyjlReq imCyjl)  {
//        imCyjlSer.update(imCyjl);        
//        return ReturnEntityUtil.success(imCyjl);            
//    }
//    
//    /** 删除 */
//    @RequestMapping("/delete")
//    @ResponseBody
//    @ApiOperation(value="按条件查询" ,httpMethod="POST")
//    public ReturnEntity<ImCyjlResp> delete(ImCyjlReq imCyjl) {
//        imCyjlSer.removeByEntity(imCyjl);        
//        return ReturnEntityUtil.success(imCyjl);            
//    }
    
}

