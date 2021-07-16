
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
import com.buit.cis.dctwork.service.CisZyHzyqdxSer;

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
@RequestMapping("/ciszyhzyqdx")
public class CisZyHzyqdxCtr extends BaseSpringController{
    
    static final Logger logger = LoggerFactory.getLogger(CisZyHzyqdxCtr.class);
    
    @Autowired
    private CisZyHzyqdxSer cisZyHzyqdxSer;
    
//    @RequestMapping("/queryPage")
//    @ResponseBody
//    @ApiOperation(value="按条件分页查询" ,httpMethod="POST")
//    public ReturnEntity<PageInfo<CisZyHzyqdxResp>> queryPage(CisZyHzyqdxReq ciszyhzyqdx,PageQuery page){
//        PageInfo<CisZyHzyqdx> pageInfo = PageHelper.startPage(
//            page.getPageNum(), page.getPageSize()).doSelectPageInfo(
//                    () -> cisZyHzyqdxSer.findByEntity(ciszyhzyqdx)
//            );
//        return ReturnEntityUtil.success(pageInfo);
//    }
//    
//    @RequestMapping("/findList")
//    @ResponseBody
//    @ApiOperation(value="按条件查询-返回列表" ,httpMethod="POST")
//    public ReturnEntity<List<CisZyHzyqdxResp>> getByEntity( CisZyHzyqdxReq ciszyhzyqdx){//@RequestBody 
//        return ReturnEntityUtil.success(cisZyHzyqdxSer.findByEntity(ciszyhzyqdx));    
//    }
//    
//    @RequestMapping("/getOneByEntity")
//    @ResponseBody
//    @ApiOperation(value="按条件查询-返回唯一值" ,httpMethod="POST")
//    public ReturnEntity<CisZyHzyqdxResp> getOneByEntity(CisZyHzyqdxReq ciszyhzyqdx){
//        List<CisZyHzyqdx> list=cisZyHzyqdxSer.findByEntity(ciszyhzyqdx);
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
//    public ReturnEntity<CisZyHzyqdxResp> add(CisZyHzyqdxReq cisZyHzyqdx) {
//        cisZyHzyqdxSer.insert(cisZyHzyqdx);        
//        return ReturnEntityUtil.success(cisZyHzyqdx);            
//    }
//    /** 编辑 */
//    @RequestMapping("/edit")
//    @ResponseBody
//    @ApiOperation(value="编辑" ,httpMethod="POST")
//    public ReturnEntity<CisZyHzyqdxResp> edit(CisZyHzyqdxReq cisZyHzyqdx)  {
//        cisZyHzyqdxSer.update(cisZyHzyqdx);        
//        return ReturnEntityUtil.success(cisZyHzyqdx);            
//    }
//    
//    /** 删除 */
//    @RequestMapping("/delete")
//    @ResponseBody
//    @ApiOperation(value="按条件查询" ,httpMethod="POST")
//    public ReturnEntity<CisZyHzyqdxResp> delete(CisZyHzyqdxReq cisZyHzyqdx) {
//        cisZyHzyqdxSer.removeByEntity(cisZyHzyqdx);        
//        return ReturnEntityUtil.success(cisZyHzyqdx);            
//    }
    
}

