
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
import com.buit.cis.dctwork.service.NisTymxSer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 病区_退药明细<br>
 * @author GONGFANGZHOU
 */
@Api(tags="病区_退药明细")
@Controller
@RequestMapping("/nistymx")
public class NisTymxCtr extends BaseSpringController{
    
    static final Logger logger = LoggerFactory.getLogger(NisTymxCtr.class);
    
    @Autowired
    private NisTymxSer nisTymxSer;
    
//    @RequestMapping("/queryPage")
//    @ResponseBody
//    @ApiOperation(value="按条件分页查询" ,httpMethod="POST")
//    public ReturnEntity<PageInfo<NisTymxResp>> queryPage(NisTymxReq nistymx,PageQuery page){
//        PageInfo<NisTymx> pageInfo = PageHelper.startPage(
//            page.getPageNum(), page.getPageSize()).doSelectPageInfo(
//                    () -> nisTymxSer.findByEntity(nistymx)
//            );
//        return ReturnEntityUtil.success(pageInfo);
//    }
//    
//    @RequestMapping("/findList")
//    @ResponseBody
//    @ApiOperation(value="按条件查询-返回列表" ,httpMethod="POST")
//    public ReturnEntity<List<NisTymxResp>> getByEntity( NisTymxReq nistymx){//@RequestBody 
//        return ReturnEntityUtil.success(nisTymxSer.findByEntity(nistymx));    
//    }
//    
//    @RequestMapping("/getOneByEntity")
//    @ResponseBody
//    @ApiOperation(value="按条件查询-返回唯一值" ,httpMethod="POST")
//    public ReturnEntity<NisTymxResp> getOneByEntity(NisTymxReq nistymx){
//        List<NisTymx> list=nisTymxSer.findByEntity(nistymx);
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
//    public ReturnEntity<NisTymxResp> add(NisTymxReq nisTymx) {
//        nisTymxSer.insert(nisTymx);        
//        return ReturnEntityUtil.success(nisTymx);            
//    }
//    /** 编辑 */
//    @RequestMapping("/edit")
//    @ResponseBody
//    @ApiOperation(value="编辑" ,httpMethod="POST")
//    public ReturnEntity<NisTymxResp> edit(NisTymxReq nisTymx)  {
//        nisTymxSer.update(nisTymx);        
//        return ReturnEntityUtil.success(nisTymx);            
//    }
//    
//    /** 删除 */
//    @RequestMapping("/delete")
//    @ResponseBody
//    @ApiOperation(value="按条件查询" ,httpMethod="POST")
//    public ReturnEntity<NisTymxResp> delete(NisTymxReq nisTymx) {
//        nisTymxSer.removeByEntity(nisTymx);        
//        return ReturnEntityUtil.success(nisTymx);            
//    }
    
}

