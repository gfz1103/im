
package com.buit.cis.dctwork.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.buit.cis.dctwork.model.NisQtyz;
import com.buit.cis.dctwork.request.NisQtyzReq;
import com.buit.cis.dctwork.response.NisQtyzResp;
import com.buit.cis.dctwork.service.NisQtyzSer;
import com.buit.commons.BaseSpringController;
import com.buit.commons.PageQuery;
import com.buit.utill.BeanUtil;
import com.buit.utill.ReturnEntity;
import com.buit.utill.ReturnEntityUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * <br>
 * @author GONGFANGZHOU
 */
@Api(tags="病区_其他医嘱")
@Controller
@RequestMapping("/nisqtyz")
public class NisQtyzCtr extends BaseSpringController{
    
    static final Logger logger = LoggerFactory.getLogger(NisQtyzCtr.class);
    
    @Autowired
    private NisQtyzSer nisQtyzSer;
    
    @RequestMapping("/queryPage")
    @ResponseBody
    @ApiOperation(value="其他医嘱维护-按条件分页查询" ,httpMethod="POST")
    public ReturnEntity<PageInfo<NisQtyzResp>> queryPage(NisQtyzReq nisqtyz,PageQuery page){
        PageInfo<NisQtyz> pageInfo = PageHelper.startPage(
            page.getPageNum(), page.getPageSize()).doSelectPageInfo(
                    () -> nisQtyzSer.findByEntity(nisqtyz)
            );
        PageInfo<NisQtyzResp> ret = BeanUtil.toPage(pageInfo, NisQtyzResp.class);
        return ReturnEntityUtil.success(ret);
    }
    
    /** 新增 */
    @RequestMapping("/add")
    @ResponseBody
    @ApiOperation(value="其他医嘱新增或修改" ,httpMethod="POST")
    public ReturnEntity add(@ApiParam(name = "yzxh", value = "主键yzxh", required = false) 
    @RequestParam(value = "yzxh", required = false ) Integer yzxh, @ApiParam(name = "yzmc", value = "医嘱名称", required = true) 
    @RequestParam String yzmc, @ApiParam(name = "srdm", value = "输入代码", required = false) 
    @RequestParam(value = "srdm", required = false )  String srdm) {
        nisQtyzSer.addQtyz(yzxh, yzmc, srdm, this.getUser());        
        return ReturnEntityUtil.success();            
    }
    
    /** 删除 */
    @RequestMapping("/deleteByYzxh")
    @ResponseBody
    @ApiOperation(value="其他医嘱删除" ,httpMethod="POST")
    public ReturnEntity delete(@ApiParam(name = "yzxh", value = "主键yzxh", required = true) 
    @RequestParam Integer yzxh) {
        nisQtyzSer.removeById(yzxh);
        return ReturnEntityUtil.success();            
    }

//    @RequestMapping("/findList")
//    @ResponseBody
//    @ApiOperation(value="按条件查询-返回列表" ,httpMethod="POST")
//    public ReturnEntity<List<NisQtyzResp>> getByEntity( NisQtyzReq nisqtyz){//@RequestBody 
//        return ReturnEntityUtil.success(nisQtyzSer.findByEntity(nisqtyz));    
//    }
//    
//    @RequestMapping("/getOneByEntity")
//    @ResponseBody
//    @ApiOperation(value="按条件查询-返回唯一值" ,httpMethod="POST")
//    public ReturnEntity<NisQtyzResp> getOneByEntity(NisQtyzReq nisqtyz){
//        List<NisQtyz> list=nisQtyzSer.findByEntity(nisqtyz);
//        if(list.size()>0){
//           return ReturnEntityUtil.success(list.get(0));    
//        }
//        return ReturnEntityUtil.success();
//    }
//    
    
//    /** 编辑 */
//    @RequestMapping("/edit")
//    @ResponseBody
//    @ApiOperation(value="编辑" ,httpMethod="POST")
//    public ReturnEntity<NisQtyzResp> edit(NisQtyzReq nisQtyz)  {
//        nisQtyzSer.update(nisQtyz);        
//        return ReturnEntityUtil.success(nisQtyz);            
//    }
//    
    
    
}

