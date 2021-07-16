
package com.buit.cis.dctwork.controller;

import com.buit.system.utill.ObjectToTypes;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.buit.utill.BeanUtil;
import com.buit.utill.ReturnEntity;
import com.buit.utill.ReturnEntityUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

import com.buit.commons.BaseSpringController;
import com.buit.commons.PageQuery;
import com.buit.cis.dctwork.model.AmqcKjywsqd;
import com.buit.cis.dctwork.request.AmqcMedicineReq;
import com.buit.cis.dctwork.request.AmqcKjywsqdReq;
import com.buit.cis.dctwork.response.AmqcMedicineResp;
import com.buit.cis.dctwork.response.AmqcKjywsqdResp;
import com.buit.cis.dctwork.service.AmqcKjywsqdSer;
import com.buit.cis.dctwork.service.CisHzyzSer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * <br>
 * @author GONGFANGZHOU
 */
@Api(tags="抗菌药物申请")
@Controller
@RequestMapping("/amqckjywsqd")
public class AmqcKjywsqdCtr extends BaseSpringController{
    
    static final Logger logger = LoggerFactory.getLogger(AmqcKjywsqdCtr.class);
    
    @Autowired
    private AmqcKjywsqdSer amqcKjywsqdSer;
    
    @Autowired
    private CisHzyzSer cisHzyzSer;
    
    @RequestMapping("/queryAntibacterials")
    @ResponseBody
    @ApiOperation(value="病人抗菌药物申请列表分页查询" ,httpMethod="POST")
    public ReturnEntity<PageInfo<AmqcKjywsqdResp>> queryAntibacterials(@ApiParam(name = "jzxh", value = "传住院号", required = true) 
   	@RequestParam Integer jzxh,PageQuery page){
    	AmqcKjywsqd aAmqcKjywsqd = new AmqcKjywsqd();
    	aAmqcKjywsqd.setJzxh(ObjectToTypes.parseString(jzxh));
    	aAmqcKjywsqd.setZfbz(0);
    	aAmqcKjywsqd.setJzlx(1);
        PageInfo<AmqcKjywsqd> pageInfo = PageHelper.startPage(
            page.getPageNum(), page.getPageSize()).doSelectPageInfo(
                    () -> amqcKjywsqdSer.findByEntity(aAmqcKjywsqd)
            );
        PageInfo<AmqcKjywsqdResp> ret = BeanUtil.toPage(pageInfo, AmqcKjywsqdResp.class);
        return ReturnEntityUtil.success(ret);
    }
 
    @RequestMapping("/saveOrupdateAntibacterials")
    @ResponseBody
    @ApiOperation(value="抗菌药物保存(或提交)修改(或作废)审批" ,httpMethod="POST")
    public ReturnEntity add(AmqcKjywsqdReq amqcKjywsqd) {
        amqcKjywsqdSer.saveOrupdateAntibacterials(amqcKjywsqd);  
        return ReturnEntityUtil.success();            
    }    
 
    @RequestMapping("/deleteBySqdh")
    @ResponseBody
    @ApiOperation(value="删除抗菌药物申请" ,httpMethod="POST")
    public ReturnEntity<AmqcKjywsqdResp> deleteBySqdh(@ApiParam(name = "sqdh", value = "申请单号(主键)", required = true) 
   	@RequestParam Integer sqdh ){
        amqcKjywsqdSer.removeById(sqdh);   
        return ReturnEntityUtil.success();            
    }
    
    @RequestMapping("/queryAmqcAndMedicine")
    @ResponseBody
    @ApiOperation(value="抗菌药物审批列表-分页查询" ,httpMethod="POST")
    public ReturnEntity<PageInfo<AmqcMedicineResp>> queryAmqcAndMedicine(
    		AmqcMedicineReq amqcMedicineReq, PageQuery page){
    	 PageInfo<AmqcMedicineResp> pageInfo = PageHelper.startPage(
    	            page.getPageNum(), page.getPageSize()).doSelectPageInfo(
    	                    () -> amqcKjywsqdSer.queryAmqcAndMedicine(amqcMedicineReq, this.getUser())
    	            );
        PageInfo<AmqcMedicineResp> ret = BeanUtil.toPage(pageInfo, AmqcMedicineResp.class);
        return ReturnEntityUtil.success(ret);            
    }
    
    
    
}

