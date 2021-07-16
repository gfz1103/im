
package com.buit.cis.dctwork.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.buit.cis.dctwork.service.CisYzmessSer;
import com.buit.commons.BaseSpringController;
import com.buit.utill.ReturnEntity;
import com.buit.utill.ReturnEntityUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * 医嘱变动消息发送<br>
 * @author GONGFANGZHOU
 */
@Api(tags="医嘱变动消息发送")
@Controller
@RequestMapping("/cisyzmess")
public class CisYzmessCtr extends BaseSpringController{
    
    static final Logger logger = LoggerFactory.getLogger(CisYzmessCtr.class);
    
    @Autowired
    private CisYzmessSer cisYzmessSer;
    
    @RequestMapping("/updateZtByJlxh")
    @ResponseBody
    @ApiOperation(value="更新医嘱变动消息状态" ,httpMethod="POST")
    public ReturnEntity updateZtByJlxh(@ApiParam(name = "jlxh", value = "记录序号", required = true)
    @RequestParam Integer jlxh) {
        cisYzmessSer.updateZt(jlxh, this.getUser());
        return ReturnEntityUtil.success();            
    }
    
}

