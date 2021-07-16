
package com.buit.cis.dctwork.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.buit.cis.dctwork.request.NisFyyfReq;
import com.buit.cis.dctwork.response.NisFyyfResp;
import com.buit.cis.dctwork.service.NisFyyfSer;
import com.buit.commons.BaseSpringController;
import com.buit.utill.ReturnEntity;
import com.buit.utill.ReturnEntityUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 病区_发药药房<br>
 * @author zhouhaisheng
 */
@Api(tags="病区_发药药房")
@Controller
@RequestMapping("/nisfyyf")
public class NisFyyfCtr extends BaseSpringController {
    
    static final Logger logger = LoggerFactory.getLogger(NisFyyfCtr.class);
    
    @Autowired
    private NisFyyfSer nisFyyfSer;
    
    @RequestMapping("/queryPage")
    @ResponseBody
    @ApiOperationSupport(author = "zhouhaisheng")
    @ApiOperation(value="病区_发药药房分页查询" ,httpMethod="POST")
    public ReturnEntity<PageInfo<NisFyyfResp>> queryPage(NisFyyfReq nisfyyf){
        PageInfo<NisFyyfResp> pageInfo = PageHelper.startPage(
                nisfyyf.getPageNum(), nisfyyf.getPageSize()).doSelectPageInfo(
                    () -> nisFyyfSer.findByEntity(nisfyyf)
            );
        return ReturnEntityUtil.success(pageInfo);
    }



/*    @RequestMapping("/getOneByEntity")
    @ResponseBody
    @ApiOperationSupport(author = "zhouhaisheng")
    @ApiOperation(value="按条件查询-返回唯一值" ,httpMethod="POST")
    public ReturnEntity<NisFyyfResp> getOneByEntity(NisFyyfReq nisfyyf){
        List<NisFyyf> list=nisFyyfSer.findByEntity(nisfyyf);
        if(list.size()>0){


           return ReturnEntityUtil.success(list.get(0));
        }
        return ReturnEntityUtil.success();
    }*/

    /**
     * 新增病区_发药药房
     * @param nisFyyf
     * @return
     */
    @RequestMapping("/add")
    @ResponseBody
    @ApiOperationSupport(author = "zhouhaisheng")
    @ApiOperation(value="新增病区_发药药房" ,httpMethod="POST")
    public ReturnEntity<NisFyyfResp> add(NisFyyfReq nisFyyf) {

        return nisFyyfSer.add(nisFyyf,getUser());
    }

    /**
     * 编辑病区_发药药房
     * @param nisFyyf
     * @return
     */
    @RequestMapping("/edit")
    @ResponseBody
    @ApiOperationSupport(author = "zhouhaisheng")
    @ApiOperation(value="编辑病区_发药药房" ,httpMethod="POST")
    public ReturnEntity<NisFyyfResp> edit(NisFyyfReq nisFyyf)  {

        return nisFyyfSer.edit(nisFyyf,getUser());
    }

    /**
     * 注销病区_发药药房
     * @param jlxh
     * @return
     */
    @RequestMapping("/delete")
    @ResponseBody
    @ApiOperationSupport(author = "zhouhaisheng")
    @ApiOperation(value="注销病区_发药药房" ,httpMethod="POST")
    public ReturnEntity<String> delete(Integer jlxh) {

        return  nisFyyfSer.delete(jlxh,getUser());
    }
    
}

