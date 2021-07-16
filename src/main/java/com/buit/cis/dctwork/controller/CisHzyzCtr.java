
package com.buit.cis.dctwork.controller;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import com.buit.cis.dctwork.request.*;
import org.apache.commons.collections.CollectionUtils;
import org.apache.dubbo.config.annotation.DubboReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.buit.apply.request.CisJcsq01QueryReq;
import com.buit.apply.response.CisJcsq01QueryResp;
import com.buit.apply.service.Cisjcsqd01Service;
import com.buit.cis.dctwork.model.CisHzyz;
import com.buit.cis.dctwork.model.CisHzyzZt;
import com.buit.cis.dctwork.response.CisHzyzChooseProjectResp;
import com.buit.cis.dctwork.response.CisHzyzCommonResp;
import com.buit.cis.dctwork.response.CisHzyzHerbResp;
import com.buit.cis.dctwork.response.CisHzyzProjectResp;
import com.buit.cis.dctwork.response.CisHzyzQueryResp;
import com.buit.cis.dctwork.response.CisHzyzYflbResp;
import com.buit.cis.dctwork.response.CisHzyzYpSubmitResp;
import com.buit.cis.dctwork.response.CisHzyzZtQueryResp;
import com.buit.cis.dctwork.response.CisJcsq01ZlxmResp;
import com.buit.cis.dctwork.response.NisTj02Resp;
import com.buit.cis.dctwork.response.NisTjMsgResp;
import com.buit.cis.dctwork.response.NisTjSureResp;
import com.buit.cis.dctwork.service.CisHzyzSer;
import com.buit.cis.dctwork.service.CisHzyzZtSer;
import com.buit.cis.ims.response.ImHzryYpSumbitResp;
import com.buit.cis.ims.service.ImHzrySer;
import com.buit.cis.nurse.request.CisHzyzJySqdReq;
import com.buit.commons.BaseSpringController;
import com.buit.commons.PageQuery;
import com.buit.file.IReportExportFileSer;
import com.buit.mms.cmo.response.IOptSssqResp;
import com.buit.mms.cmo.service.OptSssqService;
//bjhzs import com.buit.mms.cmo.response.IOptSssqResp;
//import com.buit.mms.cmo.service.OptSssqService;
import com.buit.system.response.DiccLdxmglApiResp;
import com.buit.utill.ReturnEntity;
import com.buit.utill.ReturnEntityUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import cn.hutool.json.JSONUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * 住院_病区医嘱<br>
 * @author GONGFANGZHOU
 */
@Api(tags="住院_病区医嘱")
@Controller
@RequestMapping("/cishzyz")
public class CisHzyzCtr extends BaseSpringController{
    
    static final Logger logger = LoggerFactory.getLogger(CisHzyzCtr.class);
    
    @Autowired
    private CisHzyzSer cisHzyzSer;
    
    @Autowired
    private CisHzyzZtSer cisHzyzZtSer;
    
    @DubboReference
    private OptSssqService optSssqService;
    
    @DubboReference
    private Cisjcsqd01Service cisjcsqd01Service;
    
    @Autowired
    private IReportExportFileSer iReportExportFileSer;
    
    @Autowired
    private ImHzrySer imHzrySer;
     
    @RequestMapping("/getDoctorAdviceBrQuery")
    @ResponseBody
    @ApiOperation(value="药品医嘱提交左边病人列表" ,httpMethod="POST")
    public ReturnEntity<List<ImHzryYpSumbitResp>> getDoctorAdviceBrQuery(@RequestBody CisHzyzYpSubmitReq cisHzyzYpSubmitReq){
        return ReturnEntityUtil.success(cisHzyzSer.getDoctorAdviceBrQuery(cisHzyzYpSubmitReq, this.getUser()));    
    }
    
    @RequestMapping("/getDoctorAdviceSubmitQuery")
    @ResponseBody
    @ApiOperation(value="药品医嘱提交右边药品信息列表" ,httpMethod="POST")
    public ReturnEntity<List<CisHzyzYpSubmitResp>> getDoctorAdviceSubmitQuery(@RequestBody CisHzyzYpSubmitReq cisHzyzYpSubmitReq){
        return ReturnEntityUtil.success(cisHzyzSer.doGetDoctorAdviceSubmitQuery(cisHzyzYpSubmitReq, this.getUser()));    
    }
    
    @RequestMapping("/doctorAdviceQueryVerification")
    @ResponseBody
    @ApiOperation(value="药品医嘱提交查询前间隔天数校验" ,httpMethod="POST")
    public ReturnEntity doctorAdviceQueryVerification(@ApiParam(name = "lyrq", value = "领药日期", required = true)
    @RequestParam Date lyrq, @ApiParam(name = "brbq", value = "病区代码", required = true)
    @RequestParam Integer brbq){
    	cisHzyzSer.doDoctorAdviceQueryVerification(brbq, lyrq, this.getUser());
        return ReturnEntityUtil.success();    
    }
    
    @RequestMapping("/checkInventoryInpatientPharmacy")
    @ResponseBody
    @ApiOperation(value="药品医嘱提交确认前校验库存" ,httpMethod="POST")
    public ReturnEntity<String> checkInventoryInpatientPharmacy(@RequestBody 
    		List<NisTjSureReq> nisTjSureReqList){
        return ReturnEntityUtil.success(cisHzyzSer.doCheckInventoryInpatientPharmacy(nisTjSureReqList));    
    }
    
    @RequestMapping("/saveDoctorAdviceSubmit")
    @ResponseBody
    @ApiOperation(value="药品医嘱提交确认" ,httpMethod="POST")
    public ReturnEntity<NisTjMsgResp> saveDoctorAdviceSubmit(@RequestBody 
    		CisHzyzYpSubmitReq cisHzyzYpSubmitReq){
        return ReturnEntityUtil.success(cisHzyzSer.doSaveDoctorAdviceSubmit(cisHzyzYpSubmitReq, this.getUser()));    
    }
    
    @RequestMapping("/sssThqr")
    @ResponseBody
    @ApiOperation(value="药品医嘱退回确认" ,httpMethod="POST")
    public ReturnEntity sssThqr(@ApiParam(name = "jlxh", value = "记录序号", required = true)
    @RequestParam Integer jlxh, @ApiParam(name = "fymxjlxh", value = "fymx记录序号", required = true)
    @RequestParam Integer fymxjlxh){
    	cisHzyzSer.doSssThqr(jlxh, fymxjlxh, this.getUser());
        return ReturnEntityUtil.success();    
    }
    	
	@RequestMapping("/loadMedicineInfo")
    @ResponseBody
    @ApiOperation(value="药品项目助手调入常用药或全部药品" ,httpMethod="POST")
    public ReturnEntity<CisHzyzCommonResp> loadMedicineInfo(CisHzyzCommonReq cisHzyzCommonReq){
        return ReturnEntityUtil.success(cisHzyzSer.doLoadMedicineInfo(cisHzyzCommonReq, this.getUser()));    
    }
	
	@RequestMapping("/loadAdviceSet")
    @ResponseBody
    @ApiOperation(value="药品医嘱组套调入" ,httpMethod="POST")
    public ReturnEntity<List<CisHzyzCommonResp>> loadAdviceSet(CisHzyzStackReq cisHzyzStackReq){
        return ReturnEntityUtil.success(cisHzyzSer.doLoadAdviceSet(cisHzyzStackReq, this.getUser()));    
    }
	
	@RequestMapping("/loadClinicInfo")
    @ResponseBody
    @ApiOperation(value="项目常用项和全部项目调入" ,httpMethod="POST")
    public ReturnEntity<CisHzyzProjectResp> loadClinicInfo(CisHzyzProjectReq cisHzyzProjectReq){
        return ReturnEntityUtil.success(cisHzyzSer.doLoadClinicInfo(cisHzyzProjectReq, this.getUser()));    
    }
	
	@RequestMapping("/saveWarDisposalSet")
    @ResponseBody
    @ApiOperation(value="项目组套调入保存" ,httpMethod="POST")
    public ReturnEntity<Integer> saveWarDisposalSet(@RequestBody CisHzyzXmImportReq cisHzyzXmImportReq){	
        return ReturnEntityUtil.success(cisHzyzSer.doSaveWarDisposalSet(cisHzyzXmImportReq, this.getUser()));    
    }
	
	@RequestMapping("/loadFromPersonalSet")
    @ResponseBody
    @ApiOperation(value="项目组套全部调入查询" ,httpMethod="POST")
    public ReturnEntity<CisHzyzZt> loadFromPersonalSet(@ApiParam(name = "jlxh", value = "记录序号", required = true)
    @RequestParam Integer jlxh){	
        return ReturnEntityUtil.success(cisHzyzZtSer.getEntityMapper().getById(jlxh));    
    }
	
	
	@RequestMapping("/loadFromUSLTPersonalSet")
    @ResponseBody
    @ApiOperation(value="项目组套多条调入后查询" ,httpMethod="POST")
    public ReturnEntity<List<CisHzyzChooseProjectResp>> loadFromUSLTPersonalSet(@RequestBody 
    		CisHzyzChooseProjectReq cisHzyzChooseProjectReq){	
        return ReturnEntityUtil.success(cisHzyzSer.doLoadFromUsltPersonalSet(cisHzyzChooseProjectReq,
        		this.getUser()));    
    }
	
	@RequestMapping("/saveZySqd")
    @ResponseBody
    @ApiOperation(value="住院申请单保存" ,httpMethod="POST")
    public ReturnEntity<Integer> saveZySqd(@RequestBody 
    		CisHzyzJcxmReq cisHzyzJcxmReq){	
        return ReturnEntityUtil.success(cisHzyzSer.saveZySqd(cisHzyzJcxmReq,
        		this.getUser()));    
    }

	@RequestMapping("/loadAmqcCount")
    @ResponseBody
    @ApiOperation(value="查询当天使用药品数量总和" ,httpMethod="POST")
    public ReturnEntity<BigDecimal> loadAmqcCount(@ApiParam(name = "zyh", value = "住院号", required = true)
    @RequestParam Integer zyh, @ApiParam(name = "ypxh", value = "药品序号", required = true)
    @RequestParam Integer ypxh, @ApiParam(name = "kssj", value = "开始时间(y-m-d)", required = true)
    @RequestParam String kssj){	
        return ReturnEntityUtil.success(cisHzyzSer.doLoadAmqcCount(zyh, ypxh, kssj));    
    }
	
	@RequestMapping("/getAllYfs")
    @ResponseBody
    @ApiOperation(value="获取当前机构所有的药房和病区的发药药房" ,httpMethod="POST")
    public ReturnEntity<CisHzyzYflbResp> getAllYfs(@ApiParam(name = "bqdm", value = "病区代码", required = true)
    @RequestParam Integer bqdm){	
        return ReturnEntityUtil.success(cisHzyzSer.doGetAllYfs(bqdm, this.getUser()));    
    }
	
	@RequestMapping("/getDrugReturn")
	@ResponseBody
	@ApiOperation(value="药品提交退回-分页" ,httpMethod="POST")
	public ReturnEntity<PageInfo<NisTj02Resp>> getDrugReturn(NisTj02Req nisTj02Req, PageQuery page){
		PageInfo<NisTj02Resp> pageInfo = PageHelper.startPage(
				page.getPageNum(), page.getPageSize()).doSelectPageInfo(
						() -> cisHzyzSer.getDrugReturn(nisTj02Req)
				);
	    return ReturnEntityUtil.success(pageInfo);
	}
	
	@RequestMapping("/saveZyJySqd")
    @ResponseBody
    @ApiOperation(value="住院保存检验申请单" ,httpMethod="POST")
    public ReturnEntity saveZyJySqd(@RequestBody CisHzyzJySqdReq cisHzyzJySqdReq){
    	cisHzyzSer.saveZyJySqd(cisHzyzJySqdReq, this.getUser());
        return ReturnEntityUtil.success();    
    }
	
	@RequestMapping("/queryAdditionalAdvice")
    @ResponseBody
    @ApiOperation(value="根据医嘱组号查询对应附加项目" ,httpMethod="POST")
    public ReturnEntity<List<CisHzyz>> queryAdditionalAdvice(@ApiParam(name = "lsyz", value = "临时医嘱", required = true)
    @RequestParam Integer lsyz, @ApiParam(name = "zyh", value = "住院号", required = true)
    @RequestParam Integer zyh){	
		CisHzyz cisHzyz = new CisHzyz();
		cisHzyz.setLsyz(lsyz);
		cisHzyz.setZyh(zyh);
		cisHzyz.setJgid(this.getUser().getHospitalId());
        return ReturnEntityUtil.success(cisHzyzSer.getEntityMapper().queryAdditionalAdvice(cisHzyz));    
    }
	
	@RequestMapping("/loadAppendAdvice")
    @ResponseBody
    @ApiOperation(value="查询项目对应的附加项目信息" ,httpMethod="POST")
    public ReturnEntity<List<DiccLdxmglApiResp>> doLoadAppendAdvice(@ApiParam(name = "fyxh", value = "费用序号", required = true)
    @RequestParam Integer fyxh, @ApiParam(name = "ksdm", value = "科室代码", required = true)
    @RequestParam Integer ksdm, @ApiParam(name = "mzsy", value = "是否门诊使用", required = false)
    @RequestParam(value="mzsy", required = false) String mzsy){	
        return ReturnEntityUtil.success(cisHzyzSer.doLoadAppendAdvice(fyxh, ksdm, mzsy, this.getUser()));    
    }
	
	
	@RequestMapping("/queryZyJySqdList")
    @ResponseBody
    @ApiOperation(value="住院检验申请查询-分页" ,httpMethod="POST")
    public ReturnEntity<PageInfo<CisHzyzZtQueryResp>> queryZyJySqdList(CisHzyzZtQueryReq cisHzyzZtQueryReq, PageQuery page){
		cisHzyzZtQueryReq.setYzlx(1);
    	PageInfo<CisHzyzZtQueryResp> pageInfo = PageHelper.startPage(
        		page.getPageNum(), page.getPageSize()).doSelectPageInfo(
                () -> cisHzyzZtSer.getEntityMapper().queryZyJySqdList(cisHzyzZtQueryReq)
        );   	
    	return ReturnEntityUtil.success(pageInfo);
    }
	
	@RequestMapping("/updateSkinTest")
    @ResponseBody
    @ApiOperation(value="更新皮试结果状态" ,httpMethod="POST")
    public ReturnEntity<Long> updateSkinTest(@ApiParam(name = "jlxh", value = "记录序号", required = true)
    @RequestParam Integer jlxh, @ApiParam(name = "psjg", value = "皮试结果", required = true)
    @RequestParam Integer psjg){
        return ReturnEntityUtil.success(cisHzyzSer.getEntityMapper().updateSkinTestByJlxh(psjg, jlxh, this.getUser().getHospitalId()));    
    }
	
	@RequestMapping("/queryDicZlxmInfo")
    @ResponseBody
    @ApiOperation(value="根据申请id查询检查项目信息" ,httpMethod="POST")
    public ReturnEntity<CisJcsq01ZlxmResp> queryDicZlxmInfo(@ApiParam(name = "sqid", value = "申请id", required = true)
    @RequestParam Integer sqid){
        return ReturnEntityUtil.success(cisHzyzSer.queryDicZlxmInfo(sqid, this.getUser()));    
    }
	

	@RequestMapping("/checkYpZtKc")
    @ResponseBody
    @ApiOperation(value="药品组套调入校验库存" ,httpMethod="POST")
    public ReturnEntity<List<String>> checkYpZtKc(@RequestBody CisHzyzCheckKcReq cisHzyzCheckKcReq){
        return ReturnEntityUtil.success(cisHzyzSer.checkYpZtKc(cisHzyzCheckKcReq, this.getUser()));    
    }
	
	@RequestMapping("/querySssqInfo")
    @ResponseBody
    @ApiOperation(value="跳转查询手术申请单" ,httpMethod="POST")
    public ReturnEntity<IOptSssqResp> querySssqInfo(@ApiParam(name = "sqid", value = "申请id", required = true)
    @RequestParam Integer sqid){
        return ReturnEntityUtil.success(optSssqService.getBySqdh(sqid));
    }
	
	@RequestMapping("/queryHerbInfo")
    @ResponseBody
    @ApiOperation(value="查询草药方医嘱信息" ,httpMethod="POST")
    public ReturnEntity<List<CisHzyzHerbResp>> queryHerbInfo(@ApiParam(name = "zyh", value = "住院号", required = true)
    @RequestParam Integer zyh, @ApiParam(name = "yzzh", value = "医嘱组号", required = true)
    @RequestParam Integer yzzh){
        return ReturnEntityUtil.success(cisHzyzSer.getEntityMapper().queryHerbInfo(zyh, yzzh));    
    }
	
	
	@GetMapping("/doctorAdviceSubmitFile")
    @ApiOperation(value="药品医嘱提交药房打印")
    public void doctorAdviceSubmitFile(@RequestParam("reqStr") String reqStr, HttpServletResponse response){
		CisHzyzYpSubmitReq cisHzyzYpSubmitReq = JSONUtil.toBean(reqStr, CisHzyzYpSubmitReq.class);
		Map<String, Object> map = cisHzyzSer.getAdviceSubmitParameters(cisHzyzYpSubmitReq.getLsyz(), this.getUser());
		List<Map<String, Object>> list = cisHzyzSer.getAdviceSubmitFields(cisHzyzYpSubmitReq, this.getUser());
		String jasperName = "jrxml/DoctorAdviceSubmit.jasper";
		if(CollectionUtils.isNotEmpty(list)) {
			iReportExportFileSer.reportHtmlForStream(list, map, jasperName, response);
		}
    }
	
	@RequestMapping("/updateAntibioticsStatus")
    @ResponseBody
    @ApiOperation(value="更新医嘱抗菌药物状态" ,httpMethod="POST")
    public ReturnEntity updateAntibioticsStatus(@RequestBody List<CisHzyzAntibioticsReq> list){
		cisHzyzSer.updateAntibioticsStatus(list);
        return ReturnEntityUtil.success();    
    }
	
	@RequestMapping("/checkAntimicrobialRights")
    @ResponseBody
    @ApiOperation(value="抗菌药物上级授权登录和权限校验" ,httpMethod="POST")
    public ReturnEntity<String> checkAntimicrobialRights(@RequestBody CisHzyzCheckAntimicrobialReq req){
        return ReturnEntityUtil.success(cisHzyzSer.checkAntimicrobialRights(req));    
    }
	
	@RequestMapping("/queryZyJcSqdList")
    @ResponseBody
    @ApiOperation(value="住院检查申请单查询-分页" ,httpMethod="POST")
    public ReturnEntity<PageInfo<CisJcsq01QueryResp>> queryZyJcSqdList(CisJcsq01QueryReq cisJcsq01QueryReq, PageQuery page){
        return ReturnEntityUtil.success(cisjcsqd01Service.queryZyJcSqdList(cisJcsq01QueryReq, page));    
    }

	@RequestMapping("/queryAllergicDrugsCode")
    @ResponseBody
    @ApiOperation(value="查询皮试结果类别代码" ,httpMethod="POST")
    public ReturnEntity<Integer> queryAllergicDrugsCode(@ApiParam(name = "zyh", value = "住院号", required = true)
    @RequestParam Integer zyh){
        return ReturnEntityUtil.success(cisHzyzSer.queryAllergicDrugsCode(zyh));    
    }
	
	@GetMapping("/hosCheckFormPrintFile")
    @ApiOperation(value="检查申请单打印")
    public void hosCheckFormPrintFile(@RequestParam("jcxh") Integer jcxh, HttpServletResponse response){
		String jasperName = "jrxml/HosCheckForm.jasper";
		Map<String, Object> map = imHzrySer.getEntityMapper()
        		.queryPatientJcsqd(this.getUser().getHospitalId(), jcxh);
		map.put("TITLE", this.getUser().getHospitalName() + "检查申请单");
		iReportExportFileSer.reportHtmlForStream(map, jasperName, response); 
    }
	
	@RequestMapping("/queryMedicalAdviceInfoByZyh")
    @ResponseBody
    @ApiOperation(value="查询患者历史医嘱信息" ,httpMethod="POST")
    public ReturnEntity<List<CisHzyz>> queryMedicalAdviceInfoByZyh(@ApiParam(name = "zyh", value = "住院号", required = true)
    @RequestParam Integer zyh){
        return ReturnEntityUtil.success(cisHzyzSer.queryMedicalAdviceInfoByZyh(zyh, this.getUser()));    
    }
	
	@RequestMapping("/copyMedicalAdviceInfoHistoryToNew")
    @ResponseBody
    @ApiOperation(value="复制患者历史医嘱信息" ,httpMethod="POST")
    public ReturnEntity copyMedicalAdviceInfoHistoryToNew(@RequestBody CisHzyzCopyReq req){
		cisHzyzSer.copyMedicalAdviceInfoHistoryToNew(req, this.getUser());
        return ReturnEntityUtil.success();    
    }
	
	@RequestMapping("/queryBatchJcxhByZyh")
    @ResponseBody
    @ApiOperation(value="根据住院批量查询检查申请单序号" ,httpMethod="POST")
    public ReturnEntity<List<CisJcsq01QueryResp>> queryBatchJcxhByZyh(@ApiParam(name = "zyh", value = "住院号", required = true)
    @RequestParam Integer zyh){
        return ReturnEntityUtil.success(cisHzyzSer.queryBatchJcxhByZyh(zyh));    
    }
	
	@RequestMapping("/queryWrittenOrdersMaxXh")
    @ResponseBody
    @ApiOperation(value="查询文字医嘱最大序号" ,httpMethod="POST")
    public ReturnEntity<Long> queryWrittenOrdersMaxXh(){
        return ReturnEntityUtil.success(cisHzyzSer.getEntityMapper().queryWrittenOrdersMaxXh(this.getUser().getHospitalId()));    
    }
	
	@RequestMapping("/queryOrderSetList")
    @ResponseBody
    @ApiOperation(value="另存为组套医嘱查询列表" ,httpMethod="POST")
    public ReturnEntity<List<CisHzyzQueryResp>> queryOrderSetList(CisHzyzOrderSetReq cisHzyzOrderSetReq){
		cisHzyzOrderSetReq.setJgid(this.getUser().getHospitalId());
        return ReturnEntityUtil.success(cisHzyzSer.getEntityMapper().queryOrderSetList(cisHzyzOrderSetReq));    
    }

    @RequestMapping("/stopOrdersAndCheckAuthority")
    @ResponseBody
    @ApiOperation(value="停医嘱同时校验医生权限" ,httpMethod="POST")
    public ReturnEntity<String> stopOrdersAndCheckAuthority(@RequestBody List<CisHzyzStopCheckReq> list){
        return ReturnEntityUtil.success(cisHzyzSer.stopOrdersAndCheckAuthority(list, this.getUser()));
    }

    @RequestMapping("/cancelStopOrdersAndCheckAuthority")
    @ResponseBody
    @ApiOperation(value="取消停医嘱同时校验医生权限" ,httpMethod="POST")
    public ReturnEntity<String> cancelStopOrdersAndCheckAuthority(@Valid @RequestBody List<CisHzyzStopCheckReq> list){
        return ReturnEntityUtil.success(cisHzyzSer.cancelStopOrdersAndCheckAuthority(list, this.getUser()));
    }

    @RequestMapping("/deleteOrdersByJlxhList")
    @ResponseBody
    @ApiOperation(value="批量删除医嘱" ,httpMethod="POST")
    public ReturnEntity deleteOrdersByJlxhList(@Valid @RequestBody List<CisHzyzDeteleReq> list){
        cisHzyzSer.deleteOrdersByJlxhList(list);
        return ReturnEntityUtil.success();
    }

}

