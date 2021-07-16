
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
import com.buit.cis.dctwork.service.NisTj01Ser;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 病区_提交记录 | 记录病区医嘱提交的记录，与提交明细表BQ_TJMX通过TJXH关联<br>
 * @author GONGFANGZHOU
 */
@Api(tags="病区_提交记录 | 记录病区医嘱提交的记录，与提交明细表BQ_TJMX通过TJXH关联")
@Controller
@RequestMapping("/nistj01")
public class NisTj01Ctr extends BaseSpringController{
    
    static final Logger logger = LoggerFactory.getLogger(NisTj01Ctr.class);
    
    @Autowired
    private NisTj01Ser nisTj01Ser;
    
//    @RequestMapping("/queryPage")
//    @ResponseBody
//    @ApiOperation(value="按条件分页查询" ,httpMethod="POST")
//    public ReturnEntity<PageInfo<NisTj01Resp>> queryPage(NisTj01Req nistj01,PageQuery page){
//        PageInfo<NisTj01> pageInfo = PageHelper.startPage(
//            page.getPageNum(), page.getPageSize()).doSelectPageInfo(
//                    () -> nisTj01Ser.findByEntity(nistj01)
//            );
//        return ReturnEntityUtil.success(pageInfo);
//    }
//    
//    @RequestMapping("/findList")
//    @ResponseBody
//    @ApiOperation(value="按条件查询-返回列表" ,httpMethod="POST")
//    public ReturnEntity<List<NisTj01Resp>> getByEntity( NisTj01Req nistj01){//@RequestBody 
//        return ReturnEntityUtil.success(nisTj01Ser.findByEntity(nistj01));    
//    }
//    
//    @RequestMapping("/getOneByEntity")
//    @ResponseBody
//    @ApiOperation(value="按条件查询-返回唯一值" ,httpMethod="POST")
//    public ReturnEntity<NisTj01Resp> getOneByEntity(NisTj01Req nistj01){
//        List<NisTj01> list=nisTj01Ser.findByEntity(nistj01);
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
//    public ReturnEntity<NisTj01Resp> add(NisTj01Req nisTj01) {
//        nisTj01Ser.insert(nisTj01);        
//        return ReturnEntityUtil.success(nisTj01);            
//    }
//    /** 编辑 */
//    @RequestMapping("/edit")
//    @ResponseBody
//    @ApiOperation(value="编辑" ,httpMethod="POST")
//    public ReturnEntity<NisTj01Resp> edit(NisTj01Req nisTj01)  {
//        nisTj01Ser.update(nisTj01);        
//        return ReturnEntityUtil.success(nisTj01);            
//    }
//    
//    /** 删除 */
//    @RequestMapping("/delete")
//    @ResponseBody
//    @ApiOperation(value="按条件查询" ,httpMethod="POST")
//    public ReturnEntity<NisTj01Resp> delete(NisTj01Req nisTj01) {
//        nisTj01Ser.removeByEntity(nisTj01);        
//        return ReturnEntityUtil.success(nisTj01);            
//    }
    
}

