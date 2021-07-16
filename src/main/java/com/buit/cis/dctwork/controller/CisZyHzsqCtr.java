
package com.buit.cis.dctwork.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.dubbo.config.annotation.DubboReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.buit.cis.dctwork.request.CisZyHzsqReq;
import com.buit.cis.dctwork.response.CisZyHzsqAndYjResp;
import com.buit.cis.dctwork.response.CisZyHzsqResp;
import com.buit.cis.dctwork.response.CisZyHzyjResp;
import com.buit.cis.dctwork.service.CisZyHzsqSer;
import com.buit.commons.BaseSpringController;
import com.buit.commons.PageQuery;
import com.buit.file.IReportExportFileSer;
import com.buit.system.response.HrPersonnelModel;
import com.buit.system.service.HrPersonnelService;
import com.buit.system.utill.ObjectToTypes;
import com.buit.utill.ReturnEntity;
import com.buit.utill.ReturnEntityUtil;
import com.github.pagehelper.PageInfo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * <br>
 * @author GONGFANGZHOU
 */
@Api(tags="会诊申请")
@Controller
@RequestMapping("/ciszyhzsq")
public class CisZyHzsqCtr extends BaseSpringController{
    
    static final Logger logger = LoggerFactory.getLogger(CisZyHzsqCtr.class);
    
    @Autowired
    private CisZyHzsqSer cisZyHzsqSer;
    
    @DubboReference
    private HrPersonnelService hrPersonnelService;
    
    @Autowired
    private IReportExportFileSer iReportExportFileSer;
    
    @RequestMapping("/getHzList")
    @ResponseBody
    @ApiOperation(value="会诊申请分页查询" ,httpMethod="POST")
    public ReturnEntity<PageInfo<CisZyHzsqResp>> getHzList(@ApiParam(name = "zyh", value = "住院号", required = true) 
	@RequestParam Integer zyh, @ApiParam(name = "alias", value = "标志位(0,1,2)", required = true) 
	@RequestParam String alias, @ApiParam(name = "ksdm", value = "医生科室", required = true) 
	@RequestParam Integer ksdm, PageQuery page){
        return ReturnEntityUtil.success(cisZyHzsqSer.doGetHzList(zyh, alias, ksdm, this.getUser(), page));
    }

    @RequestMapping("/saveorupdate")
	@ResponseBody
	@ApiOperation(value="新增保存会诊申请" ,httpMethod="POST")
	public ReturnEntity saveorupdate(CisZyHzsqReq cisZyHzsqReq) {
		cisZyHzsqSer.doSaveorupdate(cisZyHzsqReq, this.getUser());        
	    return ReturnEntityUtil.success();            
	}
    
    @RequestMapping("/updateCisZyHzsq")
	@ResponseBody
	@ApiOperation(value="修改保存会诊申请" ,httpMethod="POST")
	public ReturnEntity updateCisZyHzsq(CisZyHzsqReq cisZyHzsqReq) {
		cisZyHzsqSer.updateCisZyHzsq(cisZyHzsqReq, this.getUser());        
	    return ReturnEntityUtil.success();            
	}   
    
    @RequestMapping("/submitCisZyHzsq")
	@ResponseBody
	@ApiOperation(value="提交会诊申请" ,httpMethod="POST")
	public ReturnEntity submitCisZyHzsq(@ApiParam(name = "sqxh", value = "申请序号", required = true) 
	@RequestParam Integer sqxh) {
		cisZyHzsqSer.submitCisZyHzsq(sqxh, this.getUser());        
	    return ReturnEntityUtil.success();            
	}    
      
    @RequestMapping("/stopCisZyHzsq")
	@ResponseBody
	@ApiOperation(value="会诊申请结束" ,httpMethod="POST")
	public ReturnEntity stopCisZyHzsq(@ApiParam(name = "sqxh", value = "申请序号", required = true) 
	@RequestParam Integer sqxh) {
		cisZyHzsqSer.stopCisZyHzsq(sqxh);        
	    return ReturnEntityUtil.success();            
	}     
    
    @RequestMapping("/queryCisZyHzyj")
	@ResponseBody
	@ApiOperation(value="查询会诊申请意见" ,httpMethod="POST")
	public ReturnEntity<List<CisZyHzyjResp>> queryCisZyHzyj(@ApiParam(name = "sqxh", value = "申请序号", required = true) 
	@RequestParam Integer sqxh) {     
	    return ReturnEntityUtil.success(cisZyHzsqSer.queryCisZyHzyj(sqxh));            
	}  
    
    @RequestMapping("/backCisZyHzsq")
	@ResponseBody
	@ApiOperation(value="退回会诊申请" ,httpMethod="POST")
	public ReturnEntity backCisZyHzsq(@ApiParam(name = "sqxh", value = "申请序号", required = true) 
	@RequestParam Integer sqxh) {
		cisZyHzsqSer.backCisZyHzsq(sqxh, this.getUser());        
	    return ReturnEntityUtil.success();            
	}  
    
    @RequestMapping("/removeCisZyHzsq")
	@ResponseBody
	@ApiOperation(value="删除会诊申请" ,httpMethod="POST")
	public ReturnEntity removeCisZyHzsq(@ApiParam(name = "sqxh", value = "申请序号", required = true) 
	@RequestParam Integer sqxh) {
		cisZyHzsqSer.removeCisZyHzsq(sqxh, this.getUser());        
	    return ReturnEntityUtil.success();            
	}  
    
    @RequestMapping("/queryHzxx")
	@ResponseBody
	@ApiOperation(value="查询会诊申请信息" ,httpMethod="POST")
	public ReturnEntity<CisZyHzsqAndYjResp> queryHzxx(@ApiParam(name = "sqxh", value = "申请序号", required = true) 
	@RequestParam Integer sqxh) { 
	    return ReturnEntityUtil.success(cisZyHzsqSer.queryHzxx(sqxh));            
	}  
    
    @RequestMapping("/loadInfo")
   	@ResponseBody
   	@ApiOperation(value="查询会诊申请详细信息" ,httpMethod="POST")
   	public ReturnEntity<CisZyHzsqResp> loadInfo(@ApiParam(name = "sqxh", value = "申请序号", required = true) 
   	@RequestParam Integer sqxh) { 
   	    return ReturnEntityUtil.success(cisZyHzsqSer.doLoadInfo(sqxh, this.getUser()));            
   	}
    
    @RequestMapping("/commitCisZyHzyj")
   	@ResponseBody
   	@ApiOperation(value="会诊意见确认保存" ,httpMethod="POST")
   	public ReturnEntity commitCisZyHzyj(@ApiParam(name = "sqxh", value = "申请序号", required = true) 
   	@RequestParam Integer sqxh, @ApiParam(name = "ssys", value = "所属医生工号", required = true) 
   	@RequestParam String ssys, @ApiParam(name = "dbks", value = "代表科室", required = true) 
   	@RequestParam Integer dbks, @ApiParam(name = "hzyj", value = "会诊意见", required = true) 
   	@RequestParam String hzyj) { 
		cisZyHzsqSer.commitCisZyHzyj(sqxh, ssys, dbks, hzyj, this.getUser());
   	    return ReturnEntityUtil.success();            
   	}  
    
    @RequestMapping("/queryYqysByKs")
   	@ResponseBody
   	@ApiOperation(value="根据拟邀会诊科室查询拟邀会诊医生" ,httpMethod="POST")
   	public ReturnEntity<List<HrPersonnelModel>> queryYqysByKs(@ApiParam(name = "nyqhzksList", value = "拟邀会诊科室", required = true) 
   	@RequestParam List<Integer> nyqhzksList, @ApiParam(name = "pydm", value = "拼音代码", required = false)
    @RequestParam(value="pydm", required = false) String pydm) { 
   	    return ReturnEntityUtil.success(hrPersonnelService.queryYqysByKs(nyqhzksList, pydm));            
   	}  
    
    @GetMapping("/consultationPrintFile")
	@ApiOperation(value="会诊申请单打印")
	public void consultationPrintFile(@RequestParam("sqxh") Integer sqxh, HttpServletResponse response){
    	Map<String, Object> map = cisZyHzsqSer.getConsultationPrintParameters(sqxh, this.getUser());
    	map.put("TITLE", this.getUser().getHospitalName() + "院内会诊申请单");
    	String jasperName = "jrxml/HosConsultationForm.jasper";
    	if("2".equals(ObjectToTypes.parseString(map.get("NHZYY")))) {
    		map.put("TITLE", this.getUser().getHospitalName() + "院外专家会诊申请单");
    		jasperName = "jrxml/OutHosConsultationForm.jasper";
    	}
    	iReportExportFileSer.reportHtmlForStream(map, jasperName, response);
	}


	@PostMapping("/consultationPrintInfo")
	@ResponseBody
	@ApiOperation(value="会诊申请单打印-反参形式", httpMethod="POST")
	public ReturnEntity<?> consultationPrintInfo(
			@ApiParam(name = "sqxh", value = "申请序号", required = true) @RequestParam("sqxh") Integer sqxh){
		Map<String, Object> map = cisZyHzsqSer.getConsultationPrintParameters(sqxh, this.getUser());
		map.put("TITLE", this.getUser().getHospitalName() + "院内会诊申请单");
		if("2".equals(ObjectToTypes.parseString(map.get("NHZYY")))) {
			map.put("TITLE", this.getUser().getHospitalName() + "院外专家会诊申请单");
		}
		return ReturnEntityUtil.success(map);
	}
}

