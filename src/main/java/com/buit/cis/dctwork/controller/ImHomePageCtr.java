package com.buit.cis.dctwork.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.dubbo.config.annotation.DubboReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.buit.cis.dctwork.request.CisBxsqdHomeReq;
import com.buit.cis.dctwork.response.CisBxsqdHomeResp;
import com.buit.cis.dctwork.service.CisBxsqdSer;
import com.buit.cis.dctwork.service.CisHzyzSer;
import com.buit.cis.dctwork.service.CisHzyzZtSer;
import com.buit.cis.dctwork.service.ImHomePageSer;
import com.buit.cis.dctwork.service.ImZkjlSer;
import com.buit.cis.ims.request.ImHzryCriticalHomeReq;
import com.buit.cis.ims.request.ImHzryHomeReq;
import com.buit.cis.ims.request.ImZkjlHomeReq;
import com.buit.cis.ims.response.ImHzryConsultedResp;
import com.buit.cis.ims.response.ImHzryCriticalHomeResp;
import com.buit.cis.ims.response.ImHzryHandledResp;
import com.buit.cis.ims.response.ImHzryHomeResp;
import com.buit.cis.ims.response.ImHzryOperaResp;
import com.buit.cis.ims.response.ImHzryWardHomeResp;
import com.buit.cis.ims.response.ImZkjlHomeResp;
import com.buit.cis.ims.service.ImHzrySer;
import com.buit.commons.BaseSpringController;
import com.buit.mms.cmo.service.OptSssqService;
import com.buit.system.utill.ObjectToTypes;
import com.buit.utill.ReturnEntity;
import com.buit.utill.ReturnEntityUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Api(tags="住院医生护士首页")
@Controller
@RequestMapping("/imHomePage")
public class ImHomePageCtr extends BaseSpringController{

	  static final Logger logger = LoggerFactory.getLogger(ImHomePageCtr.class);
	  
	  @Autowired
	  private ImHzrySer imHzrySer;
	  
	  @Autowired
	  private CisBxsqdSer cisBxsqdSer;
	  
	  @Autowired
	  private ImZkjlSer imZkjlSer;
	  
	  @Autowired
	  private ImHomePageSer imHomePageSer;
	  
	  @Autowired
	  private CisHzyzSer cisHzyzSer;
	  
	  @Autowired
	  private CisHzyzZtSer cisHzyzZtSer;
	  
	  @DubboReference
	  private OptSssqService optSssqService;
	
	  @RequestMapping("/queryOperationHomePage")
	  @ResponseBody
	  @ApiOperation(value="查询首页今日手术" ,httpMethod="POST")
	  public ReturnEntity<List<ImHzryOperaResp>> queryOperationHomePage(@ApiParam(name = "bqdm", 
	  value = "病区代码", required = false) 
	  @RequestParam(value = "bqdm", required = false)  Integer bqdm,
	  @ApiParam(name = "ksdm", value = "科室代码", required = false) 
	  @RequestParam(value = "ksdm", required = false ) Integer ksdm){
	      return ReturnEntityUtil.success(imHzrySer.getEntityMapper().queryOperationHomePage(bqdm, ksdm));
	  }
	  
	  @RequestMapping("/queryBloodHomePage")
	  @ResponseBody
	  @ApiOperation(value="查询首页备血申请审批单" ,httpMethod="POST")
	  public ReturnEntity<List<CisBxsqdHomeResp>> queryBloodHomePage(CisBxsqdHomeReq cisBxsqdHomeReq){
		  cisBxsqdHomeReq.setYljgd(ObjectToTypes.parseString(this.getUser().getHospitalId()));
	      return ReturnEntityUtil.success(cisBxsqdSer.getEntityMapper().queryBloodHomePage(cisBxsqdHomeReq));
	  }
	  
	  @RequestMapping("/queryConsultedHomePage")
	  @ResponseBody
	  @ApiOperation(value="查询首页待会诊患者" ,httpMethod="POST")
	  public ReturnEntity<List<ImHzryConsultedResp>> queryConsultedHomePage(@ApiParam(name = "type", value = "类型(1:护士站,2:医生站)", required = true) 
	  @RequestParam  Integer type, @ApiParam(name = "bqdm", value = "病区代码", required = false) 
	  @RequestParam(value = "bqdm", required = false)  Integer bqdm,
	  @ApiParam(name = "ksdm", value = "科室代码", required = false) 
	  @RequestParam(value = "ksdm", required = false ) Integer ksdm){
		  List<ImHzryConsultedResp> list = new ArrayList<ImHzryConsultedResp>();
		  if(type == 1) {
			  list = imHzrySer.getEntityMapper().queryConsultedHomePage(bqdm, ksdm, 
					  this.getUser().getHospitalId());
		  }else {
			  list = imHzrySer.getEntityMapper().queryZyysConsultedHomePage(bqdm, ksdm, 
					  this.getUser().getHospitalId(), this.getUser().getUserId(), null);
		  }
	      return ReturnEntityUtil.success(list);
	  }
	  
	  @RequestMapping("/queryZkjlHomePage")
	  @ResponseBody
	  @ApiOperation(value="查询首页待转出入患者" ,httpMethod="POST")
	  public ReturnEntity<List<ImZkjlHomeResp>> queryZkjlHomePage(ImZkjlHomeReq imZkjlHomeReq){
		  imZkjlHomeReq.setJgid(this.getUser().getHospitalId());
		  return ReturnEntityUtil.success(imZkjlSer.getEntityMapper().queryZkjlHomePage(imZkjlHomeReq));
	  }
	  
	  @RequestMapping("/queryImHzryHomePage")
	  @ResponseBody
	  @ApiOperation(value="查询首页出入院患者" ,httpMethod="POST")
	  public ReturnEntity<List<ImHzryHomeResp>> queryImHzryHomePage(ImHzryHomeReq imHzryHomeReq){ 
		  imHzryHomeReq.setJgid(this.getUser().getHospitalId());
		  return ReturnEntityUtil.success(imHzrySer.getEntityMapper().queryImHzryHomePage(imHzryHomeReq));
	  }
	  
	  @RequestMapping("/queryWardAdviceHomePage")
	  @ResponseBody
	  @ApiOperation(value="查询首页病人问题医嘱" ,httpMethod="POST")
	  public ReturnEntity<List<ImHzryWardHomeResp>> queryWardAdviceHomePage(@ApiParam(name = "bqdm", 
	  value = "病区代码", required = false) 
	  @RequestParam(value = "bqdm", required = false)  Integer bqdm,
	  @ApiParam(name = "ksdm", value = "科室代码", required = false) 
	  @RequestParam(value = "ksdm", required = false ) Integer ksdm){
		  return ReturnEntityUtil.success(imHzrySer.getEntityMapper().queryWardAdviceHomePage(bqdm, ksdm, null));
	  }
	  
	  @RequestMapping("/queryCriticalHomePage")
	  @ResponseBody
	  @ApiOperation(value="查询首页危机值信息" ,httpMethod="POST")
	  public ReturnEntity<List<ImHzryCriticalHomeResp>> queryCriticalHomePage(ImHzryCriticalHomeReq imHzryCriticalHomeReq){
		  imHzryCriticalHomeReq.setJgid(this.getUser().getHospitalId());
		  return ReturnEntityUtil.success(imHzrySer.getEntityMapper().queryCriticalHomePage(imHzryCriticalHomeReq));
	  }
	  
	  @RequestMapping("/queryHandledCount")
	  @ResponseBody
	  @ApiOperation(value="查询首页是否处理数据" ,httpMethod="POST")
	  public ReturnEntity<ImHzryHandledResp> queryHandledCount(@ApiParam(name = "bqdm", 
	  value = "病区代码", required = false) 
	  @RequestParam(value = "bqdm", required = false)  Integer bqdm,
	  @ApiParam(name = "ksdm", value = "科室代码", required = false) 
	  @RequestParam(value = "ksdm", required = false ) Integer ksdm){
		  return ReturnEntityUtil.success(imHomePageSer.queryHandledCount(bqdm, ksdm, this.getUser()));
	  }
	  
	  @RequestMapping("/updateProblemStatus")
	  @ResponseBody
	  @ApiOperation(value="问题医嘱通知更新" ,httpMethod="POST")
	  public ReturnEntity updateProblemStatus(@ApiParam(name = "zyh", value = "住院号", required = true) 
	  @RequestParam Integer zyh){
		  cisHzyzSer.getEntityMapper().updateYzProblemStatus(this.getUser().getUserId(), zyh);
		  cisHzyzZtSer.getEntityMapper().updateZtProblemStatus(this.getUser().getUserId(), zyh);
		  return ReturnEntityUtil.success();
	  }
	  
	  @RequestMapping("/updateNoticeStatus")
	  @ResponseBody
	  @ApiOperation(value="病人转出转入等更新通知状态" ,httpMethod="POST")
	  public ReturnEntity updateNoticeStatus(@ApiParam(name = "zyh", value = "住院号", required = true) 
	  @RequestParam Integer zyh, @ApiParam(name = "tzzt", 
	  value = "通知状态1:出院通知,2:待出院通知,3:入院通知,4:待入院通知)", required = true) 
	  @RequestParam Integer tzzt){
		  imHzrySer.getEntityMapper().updateNoticeStatus(tzzt, this.getUser().getUserId(), zyh);
		  return ReturnEntityUtil.success();
	  }
	  
	  @RequestMapping("/updateZkNoticeStatus")
	  @ResponseBody
	  @ApiOperation(value="病人转科更新通知状态" ,httpMethod="POST")
	  public ReturnEntity updateZkNoticeStatus(@ApiParam(name = "zyh", value = "住院号", required = true) 
	  @RequestParam Integer jlxh, @ApiParam(name = "tzzt", value = "通知状态(1:转出通知,2:转入通知)", required = true) 
	  @RequestParam Integer tzzt){
		  imZkjlSer.getEntityMapper().updateZkNoticeStatus(tzzt, this.getUser().getUserId(), jlxh);
		  return ReturnEntityUtil.success();
	  }
	  
	  @RequestMapping("/updateSsNoticeStatus")
	  @ResponseBody
	  @ApiOperation(value="病人手术更新通知状态" ,httpMethod="POST")
	  public ReturnEntity updateSsNoticeStatus(@ApiParam(name = "sqdh", value = "申请单号", required = true) 
	  @RequestParam Integer sqdh){
		  optSssqService.updateTzzt(sqdh, this.getUser().getUserId());
		  return ReturnEntityUtil.success();
	  }
	  
	  
}
