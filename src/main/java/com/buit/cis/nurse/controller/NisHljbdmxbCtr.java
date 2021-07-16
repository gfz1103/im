
package com.buit.cis.nurse.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.buit.cis.nurse.model.NisHljbdmxb;
import com.buit.cis.nurse.request.NisHljbdmxbReq;
import com.buit.cis.nurse.service.NisHljbdmxbSer;
import com.buit.commons.BaseSpringController;
import com.buit.utill.ReturnEntity;
import com.buit.utill.ReturnEntityUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * 护理交班单明细表<br>
 * @author GONGFANGZHOU
 */
@Api(tags="护理交班单明细表")
@Controller
@RequestMapping("/nishljbdmxb")
public class NisHljbdmxbCtr extends BaseSpringController{
    
    static final Logger logger = LoggerFactory.getLogger(NisHljbdmxbCtr.class);
    
    @Autowired
    private NisHljbdmxbSer nisHljbdmxbSer;
    
    @RequestMapping("/saveOrUpdateNisHljbdmxb")
    @ResponseBody
    @ApiOperation(value="新增或修改交班单明细表" ,httpMethod="POST")
    public ReturnEntity saveOrUpdateNisHljbdmxb(@RequestBody List<NisHljbdmxbReq> list){
    	nisHljbdmxbSer.saveOrUpdateNisHljbdmxb(list, this.getUser().getHospitalId());
        return ReturnEntityUtil.success();
    }
    
    @RequestMapping("/getDetailByJlxh")
    @ResponseBody
    @ApiOperation(value="根据主键查询交班单明细" ,httpMethod="POST")
    public ReturnEntity<NisHljbdmxb> getDetailByJlxh(@ApiParam(name = "jlxh", value = "记录序号", required = true)
    @RequestParam Integer jlxh){
        return ReturnEntityUtil.success(nisHljbdmxbSer.getById(jlxh));    
    }
   
    @RequestMapping("/deleteById")
    @ResponseBody
    @ApiOperation(value="根据主键删除交班单明细" ,httpMethod="POST")
    public ReturnEntity deleteById(@ApiParam(name = "jlxh", value = "记录序号", required = true)
    @RequestParam Integer jlxh){
    	nisHljbdmxbSer.removeById(jlxh);
        return ReturnEntityUtil.success();    
    }
   
    
}

