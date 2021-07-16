
package com.buit.cis.nurse.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.buit.cis.nurse.service.NisYlxssjksfSer;
import com.buit.commons.BaseSpringController;

import io.swagger.annotations.Api;

/**
 * 压力性损伤预报、传报单(监控随访)<br>
 * @author GONGFANGZHOU
 */
@Api(tags="压力性损伤预报、传报单(监控随访)")
@Controller
@RequestMapping("/nisylxssjksf")
public class NisYlxssjksfCtr extends BaseSpringController{
    
    static final Logger logger = LoggerFactory.getLogger(NisYlxssjksfCtr.class);
    
    @Autowired
    private NisYlxssjksfSer nisYlxssjksfSer;
    
//    @RequestMapping("/queryPage")
//    @ResponseBody
//    @ApiOperation(value="按条件分页查询" ,httpMethod="POST")
//    public ReturnEntity<PageInfo<NisYlxssjksfResp>> queryPage(NisYlxssjksfReq nisylxssjksf,PageQuery page){
//        PageInfo<NisYlxssjksf> pageInfo = PageHelper.startPage(
//            page.getPageNum(), page.getPageSize()).doSelectPageInfo(
//                    () -> nisYlxssjksfSer.findByEntity(nisylxssjksf)
//            );
//        return ReturnEntityUtil.success(pageInfo);
//    }
//    
//    @RequestMapping("/findList")
//    @ResponseBody
//    @ApiOperation(value="按条件查询-返回列表" ,httpMethod="POST")
//    public ReturnEntity<List<NisYlxssjksfResp>> getByEntity( NisYlxssjksfReq nisylxssjksf){//@RequestBody 
//        return ReturnEntityUtil.success(nisYlxssjksfSer.findByEntity(nisylxssjksf));    
//    }
//    
//    @RequestMapping("/getOneByEntity")
//    @ResponseBody
//    @ApiOperation(value="按条件查询-返回唯一值" ,httpMethod="POST")
//    public ReturnEntity<NisYlxssjksfResp> getOneByEntity(NisYlxssjksfReq nisylxssjksf){
//        List<NisYlxssjksf> list=nisYlxssjksfSer.findByEntity(nisylxssjksf);
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
//    public ReturnEntity<NisYlxssjksfResp> add(NisYlxssjksfReq nisYlxssjksf) {
//        nisYlxssjksfSer.insert(nisYlxssjksf);        
//        return ReturnEntityUtil.success(nisYlxssjksf);            
//    }
//    /** 编辑 */
//    @RequestMapping("/edit")
//    @ResponseBody
//    @ApiOperation(value="编辑" ,httpMethod="POST")
//    public ReturnEntity<NisYlxssjksfResp> edit(NisYlxssjksfReq nisYlxssjksf)  {
//        nisYlxssjksfSer.update(nisYlxssjksf);        
//        return ReturnEntityUtil.success(nisYlxssjksf);            
//    }
//    
//    /** 删除 */
//    @RequestMapping("/delete")
//    @ResponseBody
//    @ApiOperation(value="按条件查询" ,httpMethod="POST")
//    public ReturnEntity<NisYlxssjksfResp> delete(NisYlxssjksfReq nisYlxssjksf) {
//        nisYlxssjksfSer.removeByEntity(nisYlxssjksf);        
//        return ReturnEntityUtil.success(nisYlxssjksf);            
//    }
    
}

