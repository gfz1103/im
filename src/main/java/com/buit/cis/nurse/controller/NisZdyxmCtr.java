
package com.buit.cis.nurse.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.buit.cis.nurse.model.NisZdyxm;
import com.buit.cis.nurse.model.NisZdyxmnr;
import com.buit.cis.nurse.request.NisZdyxmReq;
import com.buit.cis.nurse.response.NisZdyxmResp;
import com.buit.cis.nurse.service.NisZdyxmSer;
import com.buit.cis.nurse.service.NisZdyxmnrSer;
import com.buit.commons.BaseSpringController;
import com.buit.commons.PageQuery;
import com.buit.utill.ReturnEntity;
import com.buit.utill.ReturnEntityUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import cn.hutool.core.bean.BeanUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * 护理记录单自定义项目<br>
 * @author GONGFANGZHOU
 */
@Api(tags="护理记录单自定义项目")
@Controller
@RequestMapping("/niszdyxm")
public class NisZdyxmCtr extends BaseSpringController{
    
	static final Logger logger = LoggerFactory.getLogger(NisZdyxmCtr.class);
	    
    @Autowired
    private NisZdyxmSer nisZdyxmSer;
    
    @Autowired
    private NisZdyxmnrSer nisZdyxmnrSer;
    
    /** 新增 */
    @RequestMapping("/saveOrUpdate")
    @ResponseBody
    @ApiOperation(value="新增或修改" ,httpMethod="POST")
    public ReturnEntity saveOrUpdate(@RequestBody NisZdyxmReq nisZdyxmReq) {  	
    	nisZdyxmSer.saveOrUpdate(nisZdyxmReq, this.getUser());
    	return ReturnEntityUtil.success();            
    }
  
    @RequestMapping("/queryPage")
    @ResponseBody
    @ApiOperation(value="专科护理自定义项目分页查询" ,httpMethod="POST")
    public ReturnEntity<PageInfo<NisZdyxm>> queryPage(NisZdyxmReq niszdyxm,PageQuery page){
    	niszdyxm.setJgid(this.getUser().getHospitalId());
        PageInfo<NisZdyxm> pageInfo = PageHelper.startPage(
            page.getPageNum(), page.getPageSize()).doSelectPageInfo(
                    () -> nisZdyxmSer.findByEntity(niszdyxm)
            );
        return ReturnEntityUtil.success(pageInfo);
    }
   
    /** 删除 */
    @RequestMapping("/delete")
    @ResponseBody
    @ApiOperation(value="专科护理自定义项目删除" ,httpMethod="POST")
    public ReturnEntity delete(@ApiParam(name = "xmdm", value = "项目代码", required = true)
	@RequestParam Integer xmdm) {
      nisZdyxmSer.deleteById(xmdm, this.getUser().getHospitalId());
      return ReturnEntityUtil.success();            
    }	
  
    @RequestMapping("/getById")
    @ResponseBody
    @ApiOperation(value="根据项目代码查询专科护理自定义项目" ,httpMethod="POST")
    public ReturnEntity<NisZdyxmResp> getById(@ApiParam(name = "xmdm", value = "项目代码", required = true)
	@RequestParam Integer xmdm){
    	NisZdyxmResp resp = BeanUtil.toBean(nisZdyxmSer.getById(xmdm), NisZdyxmResp.class);
    	NisZdyxmnr nisZdyxmnr = new NisZdyxmnr();
    	nisZdyxmnr.setXmdm(xmdm);
    	nisZdyxmnr.setJgid(this.getUser().getHospitalId());
    	resp.setNisZdyxmnrList(nisZdyxmnrSer.findByEntity(nisZdyxmnr));
        return ReturnEntityUtil.success(resp);
    }

    /** 编辑 */
    @RequestMapping("/invalid")
    @ResponseBody
    @ApiOperation(value="专科护理自定义项目作废或取消作废" ,httpMethod="POST")
    public ReturnEntity invalid(@ApiParam(name = "xmdm", value = "项目代码", required = true)
	@RequestParam Integer xmdm,@ApiParam(name = "zfpb", value = "作废判别(作废:1,取消:0)", required = true)
	@RequestParam Integer zfpb)  {
        nisZdyxmSer.invalidXm(xmdm, zfpb, this.getUser().getHospitalId());       
        return ReturnEntityUtil.success();            
    }
    
}

