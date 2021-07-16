
package com.buit.cis.nurse.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.buit.cis.nurse.service.NisTypcSer;
import com.buit.commons.BaseSpringController;

import io.swagger.annotations.Api;

/**
 * <br>
 * @author GONGFANGZHOU
 */
@Api(tags="退药批次")
@Controller
@RequestMapping("/nistypc")
public class NisTypcCtr extends BaseSpringController{
    
    static final Logger logger = LoggerFactory.getLogger(NisTypcCtr.class);
    
    @Autowired
    private NisTypcSer nisTypcSer;
    
//    @RequestMapping("/queryPage")
//    @ResponseBody
//    @ApiOperation(value="按条件分页查询" ,httpMethod="POST")
//    public ReturnEntity<PageInfo<NisTypcResp>> queryPage(NisTypcReq nistypc,PageQuery page){
//        PageInfo<NisTypc> pageInfo = PageHelper.startPage(
//            page.getPageNum(), page.getPageSize()).doSelectPageInfo(
//                    () -> nisTypcSer.findByEntity(nistypc)
//            );
//        return ReturnEntityUtil.success(pageInfo);
//    }
//    
//    @RequestMapping("/findList")
//    @ResponseBody
//    @ApiOperation(value="按条件查询-返回列表" ,httpMethod="POST")
//    public ReturnEntity<List<NisTypcResp>> getByEntity( NisTypcReq nistypc){//@RequestBody 
//        return ReturnEntityUtil.success(nisTypcSer.findByEntity(nistypc));    
//    }
//    
//    @RequestMapping("/getOneByEntity")
//    @ResponseBody
//    @ApiOperation(value="按条件查询-返回唯一值" ,httpMethod="POST")
//    public ReturnEntity<NisTypcResp> getOneByEntity(NisTypcReq nistypc){
//        List<NisTypc> list=nisTypcSer.findByEntity(nistypc);
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
//    public ReturnEntity<NisTypcResp> add(NisTypcReq nisTypc) {
//        nisTypcSer.insert(nisTypc);        
//        return ReturnEntityUtil.success(nisTypc);            
//    }
//    /** 编辑 */
//    @RequestMapping("/edit")
//    @ResponseBody
//    @ApiOperation(value="编辑" ,httpMethod="POST")
//    public ReturnEntity<NisTypcResp> edit(NisTypcReq nisTypc)  {
//        nisTypcSer.update(nisTypc);        
//        return ReturnEntityUtil.success(nisTypc);            
//    }
//    
//    /** 删除 */
//    @RequestMapping("/delete")
//    @ResponseBody
//    @ApiOperation(value="按条件查询" ,httpMethod="POST")
//    public ReturnEntity<NisTypcResp> delete(NisTypcReq nisTypc) {
//        nisTypcSer.removeByEntity(nisTypc);        
//        return ReturnEntityUtil.success(nisTypc);            
//    }
    
}

