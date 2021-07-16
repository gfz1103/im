
package com.buit.cis.ims.controller;

import cn.hutool.core.bean.BeanUtil;

import com.buit.cis.dctwork.request.CisHzyzOrderSetReq;
import com.buit.cis.dctwork.response.CisHzyzQueryResp;
import com.buit.cis.ims.model.ImCwsz;
import com.buit.cis.ims.request.ImCwszAddReq;
import com.buit.cis.ims.request.ImCwszReq;
import com.buit.cis.ims.response.ImCwszResp;
import com.buit.cis.ims.response.ImCwszUseResp;
import com.buit.cis.ims.service.ImCwszSer;
import com.buit.commons.BaseSpringController;
import com.buit.utill.ReturnEntity;
import com.buit.utill.ReturnEntityUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 住院_床位设置<br>
 * @author zhouhaisheng
 */
@Api(tags="住院_床位设置")
@Controller
@RequestMapping("/imcwsz")
public class ImCwszCtr extends BaseSpringController {
    
    static final Logger logger = LoggerFactory.getLogger(ImCwszCtr.class);
    
    @Autowired
    private ImCwszSer imCwszSer;
    
    @RequestMapping("/queryPage")
    @ResponseBody
    @ApiOperationSupport(author = "zhouhaisheng")
    @ApiOperation(value="按条件分页查询" ,httpMethod="POST")
    public ReturnEntity<PageInfo<ImCwszResp>> queryPage(ImCwszReq imcwsz){
        imcwsz.setJgid(getUser().getHospitalId());
        PageInfo<ImCwszResp> pageInfo = PageHelper.startPage(
                imcwsz.getPageNum(), imcwsz.getPageSize()).doSelectPageInfo(
                    () -> imCwszSer.queryPage(imcwsz)
            );

        return ReturnEntityUtil.success(pageInfo);
    }

    /**
     * 获取床位设置详情
     * @param brch
     * @return
     */
    @RequestMapping("/getOneByEntity")
    @ResponseBody
    @ApiOperationSupport(author = "zhouhaisheng")
    @ApiOperation(value="获取床位设置详情" ,httpMethod="POST")
    public ReturnEntity<ImCwszResp> getOneByEntity(String  brch){
        ImCwsz imCwsz=new ImCwsz();
        imCwsz.setJgid(getUser().getHospitalId());
        imCwsz.setBrch(brch);
        List<ImCwsz> list=imCwszSer.findByEntity(imCwsz);
        if(list.size()>0){
            ImCwszResp resp= new ImCwszResp();
            BeanUtil.copyProperties(list.get(0),resp);
           return ReturnEntityUtil.success(resp);
        }
        return ReturnEntityUtil.success();
    }

    /**
     * 床位设置新增
     * @param imCwsz
     * @return
     */
    @RequestMapping("/add")
    @ResponseBody
    @ApiOperationSupport(author = "zhouhaisheng")
    @ApiOperation(value="床位设置新增" ,httpMethod="POST")
    public ReturnEntity<String> add(ImCwszAddReq imCwsz) {
        return imCwszSer.add(imCwsz,getUser());
    }

    /**
     * 床位设置修改
     * @param imCwsz
     * @return
     */
    @RequestMapping("/edit")
    @ResponseBody
    @ApiOperationSupport(author = "zhouhaisheng")
    @ApiOperation(value="床位设置修改" ,httpMethod="POST")
    public ReturnEntity<String> edit(ImCwszAddReq imCwsz)  {

        return imCwszSer.edit(imCwsz,getUser());
    }

    /**
     * 床位设置删除
     * @param brch
     * @return
     */
    @RequestMapping("/delete")
    @ResponseBody
    @ApiOperationSupport(author = "zhouhaisheng")
    @ApiOperation(value="床位设置删除" ,httpMethod="POST")
    public ReturnEntity<String> delete(@RequestParam(value = "brch")  String  brch, @RequestParam(value = "ksdm") Integer ksdm) {

        return imCwszSer.delete(brch, ksdm, getUser());
    }

    @RequestMapping("/queryUseOrNotCwCount")
    @ResponseBody
    @ApiOperation(value="查询病区床位使用数和未使用数" ,httpMethod="POST")
    public ReturnEntity<ImCwszUseResp> queryUseOrNotCwCount(@ApiParam(name = "bqdm", value = "病区代码", required = true)
    @RequestParam Integer bqdm){
    	ImCwszUseResp resp = null;
    	List<ImCwszUseResp> list = imCwszSer.getEntityMapper().queryUseOrNotCwCount(this.getUser().getHospitalId(), bqdm);
        if(CollectionUtils.isNotEmpty(list)) {
        	 resp = list.get(0);
        }
    	return ReturnEntityUtil.success(resp);    
    }


}

