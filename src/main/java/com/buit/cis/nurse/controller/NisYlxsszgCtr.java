
package com.buit.cis.nurse.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.buit.cis.nurse.service.NisYlxsszgSer;
import com.buit.commons.BaseSpringController;

import io.swagger.annotations.Api;

/**
 * 压力性损伤预报、传报单(转归情况)<br>
 * @author GONGFANGZHOU
 */
@Api(tags="压力性损伤预报、传报单(转归情况)")
@Controller
@RequestMapping("/nisylxsszg")
public class NisYlxsszgCtr extends BaseSpringController{
    
    static final Logger logger = LoggerFactory.getLogger(NisYlxsszgCtr.class);
    
    @Autowired
    private NisYlxsszgSer nisYlxsszgSer;
    
//    @RequestMapping("/queryPage")
//    @ResponseBody
//    @ApiOperation(value="按条件分页查询" ,httpMethod="POST")
//    public ReturnEntity<PageInfo<NisYlxsszgResp>> queryPage(NisYlxsszgReq nisylxsszg,PageQuery page){
//        PageInfo<NisYlxsszg> pageInfo = PageHelper.startPage(
//            page.getPageNum(), page.getPageSize()).doSelectPageInfo(
//                    () -> nisYlxsszgSer.findByEntity(nisylxsszg)
//            );
//        return ReturnEntityUtil.success(pageInfo);
//    }
//    
//    @RequestMapping("/findList")
//    @ResponseBody
//    @ApiOperation(value="按条件查询-返回列表" ,httpMethod="POST")
//    public ReturnEntity<List<NisYlxsszgResp>> getByEntity( NisYlxsszgReq nisylxsszg){//@RequestBody 
//        return ReturnEntityUtil.success(nisYlxsszgSer.findByEntity(nisylxsszg));    
//    }
//    
//    @RequestMapping("/getOneByEntity")
//    @ResponseBody
//    @ApiOperation(value="按条件查询-返回唯一值" ,httpMethod="POST")
//    public ReturnEntity<NisYlxsszgResp> getOneByEntity(NisYlxsszgReq nisylxsszg){
//        List<NisYlxsszg> list=nisYlxsszgSer.findByEntity(nisylxsszg);
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
//    public ReturnEntity<NisYlxsszgResp> add(NisYlxsszgReq nisYlxsszg) {
//        nisYlxsszgSer.insert(nisYlxsszg);        
//        return ReturnEntityUtil.success(nisYlxsszg);            
//    }
//    /** 编辑 */
//    @RequestMapping("/edit")
//    @ResponseBody
//    @ApiOperation(value="编辑" ,httpMethod="POST")
//    public ReturnEntity<NisYlxsszgResp> edit(NisYlxsszgReq nisYlxsszg)  {
//        nisYlxsszgSer.update(nisYlxsszg);        
//        return ReturnEntityUtil.success(nisYlxsszg);            
//    }
//    
//    /** 删除 */
//    @RequestMapping("/delete")
//    @ResponseBody
//    @ApiOperation(value="按条件查询" ,httpMethod="POST")
//    public ReturnEntity<NisYlxsszgResp> delete(NisYlxsszgReq nisYlxsszg) {
//        nisYlxsszgSer.removeByEntity(nisYlxsszg);        
//        return ReturnEntityUtil.success(nisYlxsszg);            
//    }
    
}

