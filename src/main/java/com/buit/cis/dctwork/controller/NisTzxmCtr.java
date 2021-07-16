
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
import com.buit.cis.dctwork.service.NisTzxmSer;

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
@RequestMapping("/nistzxm")
public class NisTzxmCtr extends BaseSpringController{
    
    static final Logger logger = LoggerFactory.getLogger(NisTzxmCtr.class);
    
    @Autowired
    private NisTzxmSer nisTzxmSer;
    
//    @RequestMapping("/queryPage")
//    @ResponseBody
//    @ApiOperation(value="按条件分页查询" ,httpMethod="POST")
//    public ReturnEntity<PageInfo<NisTzxmResp>> queryPage(NisTzxmReq nistzxm,PageQuery page){
//        PageInfo<NisTzxm> pageInfo = PageHelper.startPage(
//            page.getPageNum(), page.getPageSize()).doSelectPageInfo(
//                    () -> nisTzxmSer.findByEntity(nistzxm)
//            );
//        return ReturnEntityUtil.success(pageInfo);
//    }
//    
//    @RequestMapping("/findList")
//    @ResponseBody
//    @ApiOperation(value="按条件查询-返回列表" ,httpMethod="POST")
//    public ReturnEntity<List<NisTzxmResp>> getByEntity( NisTzxmReq nistzxm){//@RequestBody 
//        return ReturnEntityUtil.success(nisTzxmSer.findByEntity(nistzxm));    
//    }
//    
//    @RequestMapping("/getOneByEntity")
//    @ResponseBody
//    @ApiOperation(value="按条件查询-返回唯一值" ,httpMethod="POST")
//    public ReturnEntity<NisTzxmResp> getOneByEntity(NisTzxmReq nistzxm){
//        List<NisTzxm> list=nisTzxmSer.findByEntity(nistzxm);
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
//    public ReturnEntity<NisTzxmResp> add(NisTzxmReq nisTzxm) {
//        nisTzxmSer.insert(nisTzxm);        
//        return ReturnEntityUtil.success(nisTzxm);            
//    }
//    /** 编辑 */
//    @RequestMapping("/edit")
//    @ResponseBody
//    @ApiOperation(value="编辑" ,httpMethod="POST")
//    public ReturnEntity<NisTzxmResp> edit(NisTzxmReq nisTzxm)  {
//        nisTzxmSer.update(nisTzxm);        
//        return ReturnEntityUtil.success(nisTzxm);            
//    }
//    
//    /** 删除 */
//    @RequestMapping("/delete")
//    @ResponseBody
//    @ApiOperation(value="按条件查询" ,httpMethod="POST")
//    public ReturnEntity<NisTzxmResp> delete(NisTzxmReq nisTzxm) {
//        nisTzxmSer.removeByEntity(nisTzxm);        
//        return ReturnEntityUtil.success(nisTzxm);            
//    }
    
}

