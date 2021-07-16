
package com.buit.cis.ims.controller;

import com.buit.cis.ims.model.YjZdjg;
import com.buit.cis.ims.request.YjZdjgReq;
import com.buit.cis.ims.request.YjZdjgUpdateReq;
import com.buit.cis.ims.response.YjZdjgResp;
import com.buit.cis.ims.service.YjZdjgSer;
import com.buit.commons.BaseSpringController;
import com.buit.commons.PageQuery;
import com.buit.utill.ReturnEntity;
import com.buit.utill.ReturnEntityUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 医技信息维护 -诊断结果维护
 * <br>
 * @author ZHOUHAISHENG
 */
@Api(tags="医技信息维护 -诊断结果维护")
@Controller
@RequestMapping("/yjzdjg")
public class YjZdjgCtr extends BaseSpringController {
    
    static final Logger logger = LoggerFactory.getLogger(YjZdjgCtr.class);
    
    @Autowired
    private YjZdjgSer yjZdjgSer;

    /**
     * 医技诊断结果列表查询
      * @param page
     * @return
     */
    @RequestMapping("/queryPage")
    @ResponseBody
    @ApiOperationSupport(author = "zhouhaisheng")
    @ApiOperation(value="医技诊断结果列表查询" ,httpMethod="POST")
    public ReturnEntity<PageInfo<YjZdjgResp>> queryPage(PageQuery page){
        YjZdjg yjzdjg=new YjZdjg();
        yjzdjg.setJgid(getUser().getHospitalId());
        PageInfo<YjZdjgResp> pageInfo = PageHelper.startPage(
                page.getPageNum(), page.getPageSize()).doSelectPageInfo(
                    () -> yjZdjgSer.findByEntity(yjzdjg)
            );
        return ReturnEntityUtil.success(pageInfo);
    }

//    @RequestMapping("/findList")
//    @ResponseBody
//    @ApiOperation(value="按条件查询-返回列表" ,httpMethod="POST")
//    public ReturnEntity<List<YjZdjgResp>> getByEntity( YjZdjgReq yjzdjg){//@RequestBody 
//        return ReturnEntityUtil.success(yjZdjgSer.findByEntity(yjzdjg));    
//    }
//    
//    @RequestMapping("/getOneByEntity")
//    @ResponseBody
//    @ApiOperation(value="按条件查询-返回唯一值" ,httpMethod="POST")
//    public ReturnEntity<YjZdjgResp> getOneByEntity(YjZdjgReq yjzdjg){
//        List<YjZdjg> list=yjZdjgSer.findByEntity(yjzdjg);
//        if(list.size()>0){
//           return ReturnEntityUtil.success(list.get(0));    
//        }
//        return ReturnEntityUtil.success();
//    }
//    

    /**
     * 医技诊断结果新增
     * @param yjZdjg
     * @return
     */
    @RequestMapping("/add")
    @ResponseBody
    @ApiOperationSupport(author = "zhouhaisheng")
    @ApiOperation(value="医技诊断结果新增" ,httpMethod="POST")
    public ReturnEntity<String> add(YjZdjgReq yjZdjg) {
        yjZdjgSer.insert(yjZdjg,getUser());
        return ReturnEntityUtil.success();
    }

    /**
     * 医技诊断结果修改
     * @param yjZdjgUpdateReq
     * @return
     */
    @RequestMapping("/edit")
    @ResponseBody
    @ApiOperationSupport(author = "zhouhaisheng")
    @ApiOperation(value="医技诊断结果修改" ,httpMethod="POST")
    public ReturnEntity<YjZdjgResp> edit(YjZdjgUpdateReq yjZdjgUpdateReq)  {
        yjZdjgSer.update(yjZdjgUpdateReq);
        return ReturnEntityUtil.success();
    }

    /**
     * 医技诊断结果删除
     * @param zdid
     * @return
     */
    @RequestMapping("/delete")
    @ResponseBody
    @ApiOperationSupport(author = "zhouhaisheng")
    @ApiOperation(value="医技诊断结果删除" ,httpMethod="POST")
    public ReturnEntity<String> delete(Integer zdid) {
        yjZdjgSer.removeById(zdid);
        return ReturnEntityUtil.success();
    }
    
}

