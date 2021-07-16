
package com.buit.cis.dctwork.controller;

import com.buit.cis.dctwork.model.CisHzyz;
import com.buit.cis.dctwork.model.NisTfmx;
import com.buit.cis.dctwork.response.NisTymxResp;
import com.buit.cis.dctwork.service.ImZkjlSer;
import com.buit.cis.ims.model.ImCwsz;
import com.buit.cis.ims.request.ImZkjlBedReq;
import com.buit.cis.ims.request.ImZkjlReq;
import com.buit.cis.ims.request.ImZkjlVerReq;
import com.buit.cis.ims.response.ImZkZqHcjlResp;
import com.buit.cis.ims.response.ImZkjlResp;
import com.buit.commons.BaseSpringController;
import com.buit.commons.PageQuery;
import com.buit.utill.BeanUtil;
import com.buit.utill.ReturnEntity;
import com.buit.utill.ReturnEntityUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 住院_转科记录<br>
 * @author GONGFANGZHOU
 */
@Api(tags="住院_转科记录")
@Controller
@RequestMapping("/imzkjl")
public class ImZkjlCtr extends BaseSpringController{
    
    static final Logger logger = LoggerFactory.getLogger(ImZkjlCtr.class);
    
    @Autowired
    private ImZkjlSer imZkjlSer;
    
    @RequestMapping("/queryTransferVerification")
	@ResponseBody
	@ApiOperation(value="病人管理转科前信息查询校验" ,httpMethod="POST")
	public ReturnEntity<Long> queryTransferVerification(ImZkjlVerReq imZkjlVerReq) {
	    return ReturnEntityUtil.success(imZkjlSer.doQueryInfo(imZkjlVerReq, this.getUser()));            
	}
    
    @RequestMapping("/querySfzk")
	@ResponseBody
	@ApiOperation(value="病人管理查询是否转科以及转科内容" ,httpMethod="POST")
	public ReturnEntity<ImZkjlResp> querySfzk(@ApiParam(name = "zyh", value = "住院号", required = true) 
	@RequestParam Integer zyh) {
	    return ReturnEntityUtil.success(imZkjlSer.doQuerySfzk(zyh, this.getUser()));            
	}
    
    @RequestMapping("/queryUnvalidRecord")
	@ResponseBody
	@ApiOperation(value="查询不符合该病人的转科条件的记录" ,httpMethod="POST")
	public ReturnEntity<String> queryUnvalidRecord(@ApiParam(name = "zyh", value = "住院号", required = true) 
	@RequestParam Integer zyh) {
	    return ReturnEntityUtil.success(imZkjlSer.doQueryUnvalidRecord(zyh, this.getUser()));            
	}
    
    @RequestMapping("/queryNotSubmitOrNotSendYz")
	@ResponseBody
	@ApiOperation(value="药品医嘱未停未提交-分页查询列表" ,httpMethod="POST")
	public ReturnEntity<PageInfo<CisHzyz>> queryNotSubmitOrNotSendYz(@ApiParam(name = "zyh", value = "住院号", required = true) 
	@RequestParam Integer zyh, PageQuery page) {
    	PageInfo<CisHzyz> pageInfo = PageHelper.startPage(
                page.getPageNum(), page.getPageSize()).doSelectPageInfo(
                        () -> imZkjlSer.queryIsExistyp(zyh, this.getUser().getHospitalId())
                );
            PageInfo<CisHzyz> ret = BeanUtil.toPage(pageInfo, CisHzyz.class);
	    return ReturnEntityUtil.success(ret);            
	}
    
    @RequestMapping("/queryTyNotSubmitOrNotSure")
	@ResponseBody
	@ApiOperation(value="退药医嘱未提交或未确认-分页查询列表" ,httpMethod="POST")
	public ReturnEntity<PageInfo<NisTymxResp>> queryTyNotSubmitOrNotSure(@ApiParam(name = "zyh", value = "住院号", required = true) 
	@RequestParam Integer zyh, PageQuery page) {
    	PageInfo<CisHzyz> pageInfo = PageHelper.startPage(
                page.getPageNum(), page.getPageSize()).doSelectPageInfo(
                        () -> imZkjlSer.queryIsExisttyzy(zyh, this.getUser().getHospitalId())
                );
            PageInfo<NisTymxResp> ret = BeanUtil.toPage(pageInfo, NisTymxResp.class);
	    return ReturnEntityUtil.success(ret);            
	}
    
    
    @RequestMapping("/queryRefundNotSure")
	@ResponseBody
	@ApiOperation(value="退费单未确认-分页查询列表" ,httpMethod="POST")
	public ReturnEntity<PageInfo<NisTfmx>> queryRefundNotSure(@ApiParam(name = "zyh", value = "住院号", required = true) 
	@RequestParam Integer zyh, PageQuery page) {
    	PageInfo<CisHzyz> pageInfo = PageHelper.startPage(
                page.getPageNum(), page.getPageSize()).doSelectPageInfo(
                        () -> imZkjlSer.queryIsExistf(zyh, this.getUser().getHospitalId())
                );
            PageInfo<NisTfmx> ret = BeanUtil.toPage(pageInfo, NisTfmx.class);
	    return ReturnEntityUtil.success(ret);            
	}
    
    @RequestMapping("/queryNotSubmitOrNotExcuteXm")
	@ResponseBody
	@ApiOperation(value="项目医嘱未停未提交-分页查询列表" ,httpMethod="POST")
	public ReturnEntity<PageInfo<CisHzyz>> queryNotSubmitOrNotExcuteXm(@ApiParam(name = "zyh", value = "住院号", required = true) 
	@RequestParam Integer zyh, PageQuery page) {
    	PageInfo<CisHzyz> pageInfo = PageHelper.startPage(
                page.getPageNum(), page.getPageSize()).doSelectPageInfo(
                        () -> imZkjlSer.queryIsExistxm(zyh, this.getUser().getHospitalId())
                );
        PageInfo<CisHzyz> ret = BeanUtil.toPage(pageInfo, CisHzyz.class);
	    return ReturnEntityUtil.success(ret);            
	}
    
    @RequestMapping("/saveZyzkjl")
	@ResponseBody
	@ApiOperation(value="病人管理保存转科记录" ,httpMethod="POST")
	public ReturnEntity saveZyzkjl(ImZkjlReq imZkjlReq) {
    	imZkjlSer.doSaveorupdate(imZkjlReq, this.getUser());
	    return ReturnEntityUtil.success();            
	}
    
    @RequestMapping("/cancelsaveZyzkjl")
	@ResponseBody
	@ApiOperation(value="病人管理取消转科" ,httpMethod="POST")
	public ReturnEntity<Integer> cancelsaveZyzkjl(ImZkjlReq imZkjlReq) {
	    return ReturnEntityUtil.success(imZkjlSer.updateundoZk(imZkjlReq, this.getUser()));            
	}
    
    @RequestMapping("/queryInList")
	@ResponseBody
	@ApiOperation(value="提醒查询转科转入记录—分页查询" ,httpMethod="POST")
	public ReturnEntity<PageInfo<ImZkjlResp>> queryInList(@ApiParam(name = "hhbq", value = "当前病区", required = true) 
	@RequestParam Integer hhbq, @ApiParam(name = "type", value = "类型(1:换后未确认,2:换后未确认)", required = true) 
	@RequestParam Integer type, PageQuery page) {
    	PageInfo<ImZkjlResp> pageInfo = PageHelper.startPage(
                page.getPageNum(), page.getPageSize()).doSelectPageInfo(
                        () -> imZkjlSer.doQueryInList(type, hhbq, this.getUser())
                );
        PageInfo<ImZkjlResp> ret = BeanUtil.toPage(pageInfo, ImZkjlResp.class);
	    return ReturnEntityUtil.success(ret);            
	}

	@RequestMapping("/queryIsExistPatient")
	@ResponseBody
	@ApiOperation(value = "转入确认查询床号是否存在病人", httpMethod = "POST")
	public ReturnEntity<Long> queryIsExistPatient(
			@ApiParam(name = "fpcw", value = "床号", required = true) @RequestParam String fpcw,
			@ApiParam(name = "ksdm", value = "床位病区", required = true) @RequestParam Integer ksdm) {
		return ReturnEntityUtil.success(imZkjlSer.queryIsExistPatient(fpcw, this.getUser(), ksdm));
	}
    
    @RequestMapping("/checkBedSexLimit")
	@ResponseBody
	@ApiOperation(value="转入确认病人是否满足新床位性别限制" ,httpMethod="POST")
	public ReturnEntity<Boolean> checkBedSexLimit(@ApiParam(name = "zyh", value = "住院号", required = true) 
	@RequestParam Integer zyh, @ApiParam(name = "brch", value = "病人床号", required = true) 
	@RequestParam String brch, @ApiParam(name = "wardId", value = "当前病区", required = true) 
	@RequestParam Integer wardId, @ApiParam(name = "ksdm", value = "换前病区", required = true) 
	@RequestParam Integer ksdm) {
	    return ReturnEntityUtil.success(imZkjlSer.checkBedSexLimit(zyh, brch, wardId, ksdm, this.getUser()));            
	}
    
    @RequestMapping("/savecwglZccl")
	@ResponseBody
	@ApiOperation(value="提醒查询转入确定" ,httpMethod="POST")
	public ReturnEntity savecwglZccl(ImZkjlBedReq imZkjlBedReq) {
    	imZkjlSer.savecwglZccl(imZkjlBedReq, this.getUser());
	    return ReturnEntityUtil.success();            
	}
    
    @RequestMapping("/queryCwInfo")
	@ResponseBody
	@ApiOperation(value="查询符合条件空的床位号" ,httpMethod="POST")
	public ReturnEntity<List<ImCwsz>> queryCwInfo(@ApiParam(name = "zyh", value = "住院号", required = true) 
	@RequestParam Integer zyh, @ApiParam(name = "wardId", value = "当前病区", required = true) 
	@RequestParam Integer wardId, @ApiParam(name = "ksdm", value = "换后科室", required = true) 
	@RequestParam Integer ksdm) {
	    return ReturnEntityUtil.success(imZkjlSer.queryCwInfo(wardId, ksdm, zyh, this.getUser()));            
	}
    
    @RequestMapping("/queryZkZqHcInfo")
	@ResponseBody
	@ApiOperation(value="查询转科转病区换床信息" ,httpMethod="POST")
	public ReturnEntity<ImZkZqHcjlResp> queryZkZqHcInfo(@ApiParam(name = "zyh", value = "住院号", required = true) 
	@RequestParam Integer zyh, @ApiParam(name = "currentWeek", value = "周数", required = true) 
	@RequestParam Integer currentWeek) {
	    return ReturnEntityUtil.success(imZkjlSer.queryZkZqHcInfo(zyh, currentWeek, this.getUser()));            
	}

}

