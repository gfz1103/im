
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
import com.buit.cis.dctwork.service.EnrJbysSer;

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
@RequestMapping("/enrjbys")
public class EnrJbysCtr extends BaseSpringController{
    
    static final Logger logger = LoggerFactory.getLogger(EnrJbysCtr.class);
    
    @Autowired
    private EnrJbysSer enrJbysSer;
    
//    @RequestMapping("/queryPage")
//    @ResponseBody
//    @ApiOperation(value="按条件分页查询" ,httpMethod="POST")
//    public ReturnEntity<PageInfo<EnrJbysResp>> queryPage(EnrJbysReq enrjbys,PageQuery page){
//        PageInfo<EnrJbys> pageInfo = PageHelper.startPage(
//            page.getPageNum(), page.getPageSize()).doSelectPageInfo(
//                    () -> enrJbysSer.findByEntity(enrjbys)
//            );
//        return ReturnEntityUtil.success(pageInfo);
//    }
//    
//    @RequestMapping("/findList")
//    @ResponseBody
//    @ApiOperation(value="按条件查询-返回列表" ,httpMethod="POST")
//    public ReturnEntity<List<EnrJbysResp>> getByEntity( EnrJbysReq enrjbys){//@RequestBody 
//        return ReturnEntityUtil.success(enrJbysSer.findByEntity(enrjbys));    
//    }
//    
//    @RequestMapping("/getOneByEntity")
//    @ResponseBody
//    @ApiOperation(value="按条件查询-返回唯一值" ,httpMethod="POST")
//    public ReturnEntity<EnrJbysResp> getOneByEntity(EnrJbysReq enrjbys){
//        List<EnrJbys> list=enrJbysSer.findByEntity(enrjbys);
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
//    public ReturnEntity<EnrJbysResp> add(EnrJbysReq enrJbys) {
//        enrJbysSer.insert(enrJbys);        
//        return ReturnEntityUtil.success(enrJbys);            
//    }
//    /** 编辑 */
//    @RequestMapping("/edit")
//    @ResponseBody
//    @ApiOperation(value="编辑" ,httpMethod="POST")
//    public ReturnEntity<EnrJbysResp> edit(EnrJbysReq enrJbys)  {
//        enrJbysSer.update(enrJbys);        
//        return ReturnEntityUtil.success(enrJbys);            
//    }
//    
//    /** 删除 */
//    @RequestMapping("/delete")
//    @ResponseBody
//    @ApiOperation(value="按条件查询" ,httpMethod="POST")
//    public ReturnEntity<EnrJbysResp> delete(EnrJbysReq enrJbys) {
//        enrJbysSer.removeByEntity(enrJbys);        
//        return ReturnEntityUtil.success(enrJbys);            
//    }
    
}

