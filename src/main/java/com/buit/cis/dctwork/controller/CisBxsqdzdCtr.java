
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
import com.buit.cis.dctwork.service.CisBxsqdzdSer;

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
@Api(tags="备血申请单诊断")
@Controller
@RequestMapping("/cisBxsqdzd")
public class CisBxsqdzdCtr extends BaseSpringController{
    
    static final Logger logger = LoggerFactory.getLogger(CisBxsqdzdCtr.class);
    
    @Autowired
    private CisBxsqdzdSer cisBxsqdzdSer;
    
//    @RequestMapping("/queryPage")
//    @ResponseBody
//    @ApiOperation(value="按条件分页查询" ,httpMethod="POST")
//    public ReturnEntity<PageInfo<CisBxsqdzdResp>> queryPage(CisBxsqdzdReq cisBxsqdzd,PageQuery page){
//        PageInfo<CisBxsqdzd> pageInfo = PageHelper.startPage(
//            page.getPageNum(), page.getPageSize()).doSelectPageInfo(
//                    () -> cisBxsqdzdSer.findByEntity(cisBxsqdzd)
//            );
//        return ReturnEntityUtil.success(pageInfo);
//    }
//    
//    @RequestMapping("/findList")
//    @ResponseBody
//    @ApiOperation(value="按条件查询-返回列表" ,httpMethod="POST")
//    public ReturnEntity<List<CisBxsqdzdResp>> getByEntity( CisBxsqdzdReq cisBxsqdzd){//@RequestBody 
//        return ReturnEntityUtil.success(cisBxsqdzdSer.findByEntity(cisBxsqdzd));    
//    }
//    
//    @RequestMapping("/getOneByEntity")
//    @ResponseBody
//    @ApiOperation(value="按条件查询-返回唯一值" ,httpMethod="POST")
//    public ReturnEntity<CisBxsqdzdResp> getOneByEntity(CisBxsqdzdReq cisBxsqdzd){
//        List<CisBxsqdzd> list=cisBxsqdzdSer.findByEntity(cisBxsqdzd);
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
//    public ReturnEntity<CisBxsqdzdResp> add(CisBxsqdzdReq cisBxsqdzd) {
//        cisBxsqdzdSer.insert(cisBxsqdzd);        
//        return ReturnEntityUtil.success(cisBxsqdzd);            
//    }
//    /** 编辑 */
//    @RequestMapping("/edit")
//    @ResponseBody
//    @ApiOperation(value="编辑" ,httpMethod="POST")
//    public ReturnEntity<CisBxsqdzdResp> edit(CisBxsqdzdReq cisBxsqdzd)  {
//        cisBxsqdzdSer.update(cisBxsqdzd);        
//        return ReturnEntityUtil.success(cisBxsqdzd);            
//    }
//    
//    /** 删除 */
//    @RequestMapping("/delete")
//    @ResponseBody
//    @ApiOperation(value="按条件查询" ,httpMethod="POST")
//    public ReturnEntity<CisBxsqdzdResp> delete(CisBxsqdzdReq cisBxsqdzd) {
//        cisBxsqdzdSer.removeByEntity(cisBxsqdzd);        
//        return ReturnEntityUtil.success(cisBxsqdzd);            
//    }
    
}

