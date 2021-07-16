
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
import com.buit.cis.nurse.service.ZyYbxmfzClSer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 医保项目分组(材料)<br>
 * @author GONGFANGZHOU
 */
@Api(tags="医保项目分组(材料)")
@Controller
@RequestMapping("/zyybxmfzcl")
public class ZyYbxmfzClCtr extends BaseSpringController{
    
    static final Logger logger = LoggerFactory.getLogger(ZyYbxmfzClCtr.class);
    
    @Autowired
    private ZyYbxmfzClSer zyYbxmfzClSer;
    
//    @RequestMapping("/queryPage")
//    @ResponseBody
//    @ApiOperation(value="按条件分页查询" ,httpMethod="POST")
//    public ReturnEntity<PageInfo<ZyYbxmfzClResp>> queryPage(ZyYbxmfzClReq zyybxmfzcl,PageQuery page){
//        PageInfo<ZyYbxmfzCl> pageInfo = PageHelper.startPage(
//            page.getPageNum(), page.getPageSize()).doSelectPageInfo(
//                    () -> zyYbxmfzClSer.findByEntity(zyybxmfzcl)
//            );
//        return ReturnEntityUtil.success(pageInfo);
//    }
//    
//    @RequestMapping("/findList")
//    @ResponseBody
//    @ApiOperation(value="按条件查询-返回列表" ,httpMethod="POST")
//    public ReturnEntity<List<ZyYbxmfzClResp>> getByEntity( ZyYbxmfzClReq zyybxmfzcl){//@RequestBody 
//        return ReturnEntityUtil.success(zyYbxmfzClSer.findByEntity(zyybxmfzcl));    
//    }
//    
//    @RequestMapping("/getOneByEntity")
//    @ResponseBody
//    @ApiOperation(value="按条件查询-返回唯一值" ,httpMethod="POST")
//    public ReturnEntity<ZyYbxmfzClResp> getOneByEntity(ZyYbxmfzClReq zyybxmfzcl){
//        List<ZyYbxmfzCl> list=zyYbxmfzClSer.findByEntity(zyybxmfzcl);
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
//    public ReturnEntity<ZyYbxmfzClResp> add(ZyYbxmfzClReq zyYbxmfzCl) {
//        zyYbxmfzClSer.insert(zyYbxmfzCl);        
//        return ReturnEntityUtil.success(zyYbxmfzCl);            
//    }
//    /** 编辑 */
//    @RequestMapping("/edit")
//    @ResponseBody
//    @ApiOperation(value="编辑" ,httpMethod="POST")
//    public ReturnEntity<ZyYbxmfzClResp> edit(ZyYbxmfzClReq zyYbxmfzCl)  {
//        zyYbxmfzClSer.update(zyYbxmfzCl);        
//        return ReturnEntityUtil.success(zyYbxmfzCl);            
//    }
//    
//    /** 删除 */
//    @RequestMapping("/delete")
//    @ResponseBody
//    @ApiOperation(value="按条件查询" ,httpMethod="POST")
//    public ReturnEntity<ZyYbxmfzClResp> delete(ZyYbxmfzClReq zyYbxmfzCl) {
//        zyYbxmfzClSer.removeByEntity(zyYbxmfzCl);        
//        return ReturnEntityUtil.success(zyYbxmfzCl);            
//    }
    
}

