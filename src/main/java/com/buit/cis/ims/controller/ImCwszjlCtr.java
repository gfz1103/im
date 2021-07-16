
package com.buit.cis.ims.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.buit.cis.ims.service.ImCwszjlSer;
import com.buit.commons.BaseSpringController;

import io.swagger.annotations.Api;

/**
 * 床位使用记录表<br>
 * @author GONGFANGZHOU
 */
@Api(tags="床位使用记录表")
@Controller
@RequestMapping("/imcwszjl")
public class ImCwszjlCtr extends BaseSpringController{
    
    static final Logger logger = LoggerFactory.getLogger(ImCwszjlCtr.class);
    
    @Autowired
    private ImCwszjlSer imCwszjlSer;
    
//    @RequestMapping("/queryPage")
//    @ResponseBody
//    @ApiOperation(value="按条件分页查询" ,httpMethod="POST")
//    public ReturnEntity<PageInfo<ImCwszjlResp>> queryPage(ImCwszjlReq imcwszjl,PageQuery page){
//        PageInfo<ImCwszjl> pageInfo = PageHelper.startPage(
//            page.getPageNum(), page.getPageSize()).doSelectPageInfo(
//                    () -> imCwszjlSer.findByEntity(imcwszjl)
//            );
//        return ReturnEntityUtil.success(pageInfo);
//    }
//    
//    @RequestMapping("/findList")
//    @ResponseBody
//    @ApiOperation(value="按条件查询-返回列表" ,httpMethod="POST")
//    public ReturnEntity<List<ImCwszjlResp>> getByEntity( ImCwszjlReq imcwszjl){//@RequestBody 
//        return ReturnEntityUtil.success(imCwszjlSer.findByEntity(imcwszjl));    
//    }
//    
//    @RequestMapping("/getOneByEntity")
//    @ResponseBody
//    @ApiOperation(value="按条件查询-返回唯一值" ,httpMethod="POST")
//    public ReturnEntity<ImCwszjlResp> getOneByEntity(ImCwszjlReq imcwszjl){
//        List<ImCwszjl> list=imCwszjlSer.findByEntity(imcwszjl);
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
//    public ReturnEntity<ImCwszjlResp> add(ImCwszjlReq imCwszjl) {
//        imCwszjlSer.insert(imCwszjl);        
//        return ReturnEntityUtil.success(imCwszjl);            
//    }
//    /** 编辑 */
//    @RequestMapping("/edit")
//    @ResponseBody
//    @ApiOperation(value="编辑" ,httpMethod="POST")
//    public ReturnEntity<ImCwszjlResp> edit(ImCwszjlReq imCwszjl)  {
//        imCwszjlSer.update(imCwszjl);        
//        return ReturnEntityUtil.success(imCwszjl);            
//    }
//    
//    /** 删除 */
//    @RequestMapping("/delete")
//    @ResponseBody
//    @ApiOperation(value="按条件查询" ,httpMethod="POST")
//    public ReturnEntity<ImCwszjlResp> delete(ImCwszjlReq imCwszjl) {
//        imCwszjlSer.removeByEntity(imCwszjl);        
//        return ReturnEntityUtil.success(imCwszjl);            
//    }
    
}

