
package com.buit.cis.nurse.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.buit.cis.nurse.service.NisHljhdcsSer;
import com.buit.commons.BaseSpringController;

import io.swagger.annotations.Api;

/**
 * 护理执行单子表<br>
 * @author GONGFANGZHOU
 */
@Api(tags="护理计划单子表")
@Controller
@RequestMapping("/nishljhdcs")
public class NisHljhdcsCtr extends BaseSpringController{
    
    static final Logger logger = LoggerFactory.getLogger(NisHljhdcsCtr.class);
    
    @Autowired
    private NisHljhdcsSer nisHljhdcsSer;
    
//    @RequestMapping("/queryPage")
//    @ResponseBody
//    @ApiOperation(value="按条件分页查询" ,httpMethod="POST")
//    public ReturnEntity<PageInfo<NisHljhdcsResp>> queryPage(NisHljhdcsReq nishljhdcs,PageQuery page){
//        PageInfo<NisHljhdcs> pageInfo = PageHelper.startPage(
//            page.getPageNum(), page.getPageSize()).doSelectPageInfo(
//                    () -> nisHljhdcsSer.findByEntity(nishljhdcs)
//            );
//        return ReturnEntityUtil.success(pageInfo);
//    }
//    
//    @RequestMapping("/findList")
//    @ResponseBody
//    @ApiOperation(value="按条件查询-返回列表" ,httpMethod="POST")
//    public ReturnEntity<List<NisHljhdcsResp>> getByEntity( NisHljhdcsReq nishljhdcs){//@RequestBody 
//        return ReturnEntityUtil.success(nisHljhdcsSer.findByEntity(nishljhdcs));    
//    }
//    
//    @RequestMapping("/getOneByEntity")
//    @ResponseBody
//    @ApiOperation(value="按条件查询-返回唯一值" ,httpMethod="POST")
//    public ReturnEntity<NisHljhdcsResp> getOneByEntity(NisHljhdcsReq nishljhdcs){
//        List<NisHljhdcs> list=nisHljhdcsSer.findByEntity(nishljhdcs);
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
//    public ReturnEntity<NisHljhdcsResp> add(NisHljhdcsReq nisHljhdcs) {
//        nisHljhdcsSer.insert(nisHljhdcs);        
//        return ReturnEntityUtil.success(nisHljhdcs);            
//    }
//    /** 编辑 */
//    @RequestMapping("/edit")
//    @ResponseBody
//    @ApiOperation(value="编辑" ,httpMethod="POST")
//    public ReturnEntity<NisHljhdcsResp> edit(NisHljhdcsReq nisHljhdcs)  {
//        nisHljhdcsSer.update(nisHljhdcs);        
//        return ReturnEntityUtil.success(nisHljhdcs);            
//    }
//    
//    /** 删除 */
//    @RequestMapping("/delete")
//    @ResponseBody
//    @ApiOperation(value="按条件查询" ,httpMethod="POST")
//    public ReturnEntity<NisHljhdcsResp> delete(NisHljhdcsReq nisHljhdcs) {
//        nisHljhdcsSer.removeByEntity(nisHljhdcs);        
//        return ReturnEntityUtil.success(nisHljhdcs);            
//    }
    
}

