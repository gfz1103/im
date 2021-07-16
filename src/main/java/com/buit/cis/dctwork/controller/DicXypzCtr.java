
package com.buit.cis.dctwork.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.buit.cis.dctwork.model.DicXypz;
import com.buit.cis.dctwork.service.DicXypzSer;
import com.buit.commons.BaseSpringController;
import com.buit.commons.PageQuery;
import com.buit.system.utill.ObjectToTypes;
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
@Api(tags="血液品种表")
@Controller
@RequestMapping("/dicXypz")
public class DicXypzCtr extends BaseSpringController{
    
    static final Logger logger = LoggerFactory.getLogger(DicXypzCtr.class);
    
    @Autowired
    private DicXypzSer dicXypzSer;
    
    @RequestMapping("/queryPage")
    @ResponseBody
    @ApiOperation(value="血液品种表查询-分页" ,httpMethod="POST")
    public ReturnEntity<PageInfo<DicXypz>> queryPage(@ApiParam(name = "sxpzmc", value = "血液品种名称", required = false) 
	@RequestParam(value = "sxpzmc", required = false ) String sxpzmc, PageQuery page){
    	DicXypz dicXypz = new DicXypz();
    	dicXypz.setSxpzmc(sxpzmc);
    	dicXypz.setYljgd(ObjectToTypes.parseString(this.getUser().getHospitalId()));
        PageInfo<DicXypz> pageInfo = PageHelper.startPage(
            page.getPageNum(), page.getPageSize()).doSelectPageInfo(
                    () -> dicXypzSer.findByEntity(dicXypz)
            );
        return ReturnEntityUtil.success(pageInfo);
    }
//    
//    @RequestMapping("/findList")
//    @ResponseBody
//    @ApiOperation(value="按条件查询-返回列表" ,httpMethod="POST")
//    public ReturnEntity<List<DicXypzResp>> getByEntity( DicXypzReq DicXypz){//@RequestBody 
//        return ReturnEntityUtil.success(DicXypzSer.findByEntity(DicXypz));    
//    }
//    
//    @RequestMapping("/getOneByEntity")
//    @ResponseBody
//    @ApiOperation(value="按条件查询-返回唯一值" ,httpMethod="POST")
//    public ReturnEntity<DicXypzResp> getOneByEntity(DicXypzReq DicXypz){
//        List<DicXypz> list=DicXypzSer.findByEntity(DicXypz);
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
//    public ReturnEntity<DicXypzResp> add(DicXypzReq DicXypz) {
//        DicXypzSer.insert(DicXypz);        
//        return ReturnEntityUtil.success(DicXypz);            
//    }
//    /** 编辑 */
//    @RequestMapping("/edit")
//    @ResponseBody
//    @ApiOperation(value="编辑" ,httpMethod="POST")
//    public ReturnEntity<DicXypzResp> edit(DicXypzReq DicXypz)  {
//        DicXypzSer.update(DicXypz);        
//        return ReturnEntityUtil.success(DicXypz);            
//    }
//    
//    /** 删除 */
//    @RequestMapping("/delete")
//    @ResponseBody
//    @ApiOperation(value="按条件查询" ,httpMethod="POST")
//    public ReturnEntity<DicXypzResp> delete(DicXypzReq DicXypz) {
//        DicXypzSer.removeByEntity(DicXypz);        
//        return ReturnEntityUtil.success(DicXypz);            
//    }
    
}

