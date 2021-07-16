
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
import com.buit.cis.dctwork.service.NisTfmxSer;

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
@RequestMapping("/nistfmx")
public class NisTfmxCtr extends BaseSpringController{
    
    static final Logger logger = LoggerFactory.getLogger(NisTfmxCtr.class);
    
    @Autowired
    private NisTfmxSer nisTfmxSer;
    
//    @RequestMapping("/queryPage")
//    @ResponseBody
//    @ApiOperation(value="按条件分页查询" ,httpMethod="POST")
//    public ReturnEntity<PageInfo<NisTfmxResp>> queryPage(NisTfmxReq nistfmx,PageQuery page){
//        PageInfo<NisTfmx> pageInfo = PageHelper.startPage(
//            page.getPageNum(), page.getPageSize()).doSelectPageInfo(
//                    () -> nisTfmxSer.findByEntity(nistfmx)
//            );
//        return ReturnEntityUtil.success(pageInfo);
//    }
//    
//    @RequestMapping("/findList")
//    @ResponseBody
//    @ApiOperation(value="按条件查询-返回列表" ,httpMethod="POST")
//    public ReturnEntity<List<NisTfmxResp>> getByEntity( NisTfmxReq nistfmx){//@RequestBody 
//        return ReturnEntityUtil.success(nisTfmxSer.findByEntity(nistfmx));    
//    }
//    
//    @RequestMapping("/getOneByEntity")
//    @ResponseBody
//    @ApiOperation(value="按条件查询-返回唯一值" ,httpMethod="POST")
//    public ReturnEntity<NisTfmxResp> getOneByEntity(NisTfmxReq nistfmx){
//        List<NisTfmx> list=nisTfmxSer.findByEntity(nistfmx);
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
//    public ReturnEntity<NisTfmxResp> add(NisTfmxReq nisTfmx) {
//        nisTfmxSer.insert(nisTfmx);        
//        return ReturnEntityUtil.success(nisTfmx);            
//    }
//    /** 编辑 */
//    @RequestMapping("/edit")
//    @ResponseBody
//    @ApiOperation(value="编辑" ,httpMethod="POST")
//    public ReturnEntity<NisTfmxResp> edit(NisTfmxReq nisTfmx)  {
//        nisTfmxSer.update(nisTfmx);        
//        return ReturnEntityUtil.success(nisTfmx);            
//    }
//    
//    /** 删除 */
//    @RequestMapping("/delete")
//    @ResponseBody
//    @ApiOperation(value="按条件查询" ,httpMethod="POST")
//    public ReturnEntity<NisTfmxResp> delete(NisTfmxReq nisTfmx) {
//        nisTfmxSer.removeByEntity(nisTfmx);        
//        return ReturnEntityUtil.success(nisTfmx);            
//    }
    
}

