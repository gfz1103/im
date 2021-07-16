
package com.buit.cis.ims.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.buit.cis.ims.service.ImZyjsSer;
import com.buit.commons.BaseSpringController;

import io.swagger.annotations.Api;

/**
 * 住院_住院结算<br>
 * @author GONGFANGZHOU
 */
@Api(tags="住院_住院结算")
@Controller
@RequestMapping("/imzyjs")
public class ImZyjsCtr extends BaseSpringController{
    
    static final Logger logger = LoggerFactory.getLogger(ImZyjsCtr.class);
    
    @Autowired
    private ImZyjsSer imZyjsSer;
    
//    @RequestMapping("/queryPage")
//    @ResponseBody
//    @ApiOperation(value="按条件分页查询" ,httpMethod="POST")
//    public ReturnEntity<PageInfo<ImZyjsResp>> queryPage(ImZyjsReq imzyjs,PageQuery page){
//        PageInfo<ImZyjs> pageInfo = PageHelper.startPage(
//            page.getPageNum(), page.getPageSize()).doSelectPageInfo(
//                    () -> imZyjsSer.findByEntity(imzyjs)
//            );
//        return ReturnEntityUtil.success(pageInfo);
//    }
//    
//    @RequestMapping("/findList")
//    @ResponseBody
//    @ApiOperation(value="按条件查询-返回列表" ,httpMethod="POST")
//    public ReturnEntity<List<ImZyjsResp>> getByEntity( ImZyjsReq imzyjs){//@RequestBody 
//        return ReturnEntityUtil.success(imZyjsSer.findByEntity(imzyjs));    
//    }
//    
//    @RequestMapping("/getOneByEntity")
//    @ResponseBody
//    @ApiOperation(value="按条件查询-返回唯一值" ,httpMethod="POST")
//    public ReturnEntity<ImZyjsResp> getOneByEntity(ImZyjsReq imzyjs){
//        List<ImZyjs> list=imZyjsSer.findByEntity(imzyjs);
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
//    public ReturnEntity<ImZyjsResp> add(ImZyjsReq imZyjs) {
//        imZyjsSer.insert(imZyjs);        
//        return ReturnEntityUtil.success(imZyjs);            
//    }
//    /** 编辑 */
//    @RequestMapping("/edit")
//    @ResponseBody
//    @ApiOperation(value="编辑" ,httpMethod="POST")
//    public ReturnEntity<ImZyjsResp> edit(ImZyjsReq imZyjs)  {
//        imZyjsSer.update(imZyjs);        
//        return ReturnEntityUtil.success(imZyjs);            
//    }
//    
//    /** 删除 */
//    @RequestMapping("/delete")
//    @ResponseBody
//    @ApiOperation(value="按条件查询" ,httpMethod="POST")
//    public ReturnEntity<ImZyjsResp> delete(ImZyjsReq imZyjs) {
//        imZyjsSer.removeByEntity(imZyjs);        
//        return ReturnEntityUtil.success(imZyjs);            
//    }
    
}

