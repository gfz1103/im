
package com.buit.cis.ims.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.buit.cis.ims.service.ImFeeFymxYjSer;
import com.buit.commons.BaseSpringController;

import io.swagger.annotations.Api;

/**
 * 费用明细表<br>
 * @author GONGFANGZHOU
 */
@Api(tags="费用明细表")
@Controller
@RequestMapping("/imfeefymxyj")
public class ImFeeFymxYjCtr extends BaseSpringController{
    
    static final Logger logger = LoggerFactory.getLogger(ImFeeFymxYjCtr.class);
    
    @Autowired
    private ImFeeFymxYjSer imFeeFymxYjSer;
    
//    @RequestMapping("/queryPage")
//    @ResponseBody
//    @ApiOperation(value="按条件分页查询" ,httpMethod="POST")
//    public ReturnEntity<PageInfo<ImFeeFymxYjResp>> queryPage(ImFeeFymxYjReq imfeefymxyj,PageQuery page){
//        PageInfo<ImFeeFymxYj> pageInfo = PageHelper.startPage(
//            page.getPageNum(), page.getPageSize()).doSelectPageInfo(
//                    () -> imFeeFymxYjSer.findByEntity(imfeefymxyj)
//            );
//        return ReturnEntityUtil.success(pageInfo);
//    }
//    
//    @RequestMapping("/findList")
//    @ResponseBody
//    @ApiOperation(value="按条件查询-返回列表" ,httpMethod="POST")
//    public ReturnEntity<List<ImFeeFymxYjResp>> getByEntity( ImFeeFymxYjReq imfeefymxyj){//@RequestBody 
//        return ReturnEntityUtil.success(imFeeFymxYjSer.findByEntity(imfeefymxyj));    
//    }
//    
//    @RequestMapping("/getOneByEntity")
//    @ResponseBody
//    @ApiOperation(value="按条件查询-返回唯一值" ,httpMethod="POST")
//    public ReturnEntity<ImFeeFymxYjResp> getOneByEntity(ImFeeFymxYjReq imfeefymxyj){
//        List<ImFeeFymxYj> list=imFeeFymxYjSer.findByEntity(imfeefymxyj);
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
//    public ReturnEntity<ImFeeFymxYjResp> add(ImFeeFymxYjReq imFeeFymxYj) {
//        imFeeFymxYjSer.insert(imFeeFymxYj);        
//        return ReturnEntityUtil.success(imFeeFymxYj);            
//    }
//    /** 编辑 */
//    @RequestMapping("/edit")
//    @ResponseBody
//    @ApiOperation(value="编辑" ,httpMethod="POST")
//    public ReturnEntity<ImFeeFymxYjResp> edit(ImFeeFymxYjReq imFeeFymxYj)  {
//        imFeeFymxYjSer.update(imFeeFymxYj);        
//        return ReturnEntityUtil.success(imFeeFymxYj);            
//    }
//    
//    /** 删除 */
//    @RequestMapping("/delete")
//    @ResponseBody
//    @ApiOperation(value="按条件查询" ,httpMethod="POST")
//    public ReturnEntity<ImFeeFymxYjResp> delete(ImFeeFymxYjReq imFeeFymxYj) {
//        imFeeFymxYjSer.removeByEntity(imFeeFymxYj);        
//        return ReturnEntityUtil.success(imFeeFymxYj);            
//    }
    
}

