package com.buit.cis.dctwork.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import com.buit.cis.dctwork.request.*;
import com.buit.cis.dctwork.response.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.buit.cis.dctwork.model.CisHzyz;
import com.buit.cis.dctwork.service.CisHzyzSer;
import com.buit.cis.dctwork.request.CisHzyzDataReq;
import com.buit.cis.dctwork.request.CisHzyzQueryApiReq;
import com.buit.cis.dctwork.request.CisHzyzReq;
import com.buit.cis.dctwork.request.CisHzyzSaveReq;
import com.buit.cis.dctwork.request.CisHzyzSubmitReq;
import com.buit.cis.dctwork.request.CisHzyzXhListReq;
import com.buit.cis.dctwork.request.SkinPsjlCheckReq;
import com.buit.cis.dctwork.response.CisHzyzDocSubmitResp;
import com.buit.cis.dctwork.response.CisHzyzQueryResp;
import com.buit.cis.dctwork.response.CisHzyzResp;
import com.buit.cis.dctwork.response.NisFyyfQxResp;
import com.buit.cis.dctwork.response.SkinPsjlCheckResp;
import com.buit.cis.dctwork.response.SkinPsjlDrugsResp;
import com.buit.cis.dctwork.response.SkinPsjlQueryResp;
import com.buit.cis.dctwork.response.YsZyHzBgResp;
import com.buit.cis.dctwork.service.ImHzryByDctworkSer;
import com.buit.cis.dctwork.service.SkinPsjlSer;
import com.buit.cis.ims.model.ImHzry;
import com.buit.cis.ims.request.ImFeeFymxDbReq;
import com.buit.cis.ims.request.ImHzryDctworkReq;
import com.buit.cis.ims.request.ImHzryInfoReq;
import com.buit.cis.ims.request.ImHzryQueryReq;
import com.buit.cis.ims.request.ImRcjlCyReq;
import com.buit.cis.ims.request.ImRyzdReq;
import com.buit.cis.ims.response.ImFeeFymxXtResp;
import com.buit.cis.ims.response.ImHzryDctworkResp;
import com.buit.cis.ims.response.ImHzryLeaveHospitalResp;
import com.buit.cis.ims.response.ImRyzdResp;
import com.buit.cis.ims.service.ImHzrySer;
import com.buit.cis.ims.service.ZyDicSer;
import com.buit.commons.BaseSpringController;
import com.buit.commons.PageQuery;
import com.buit.file.IReportExportFileSer;
import com.buit.system.response.DicJbbmInfoResp;
import com.buit.utill.BeanUtil;
import com.buit.utill.ReturnEntity;
import com.buit.utill.ReturnEntityUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.zxing.WriterException;

import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * 
* @ClassName: ImHzryByDctworkCtr
* @Description: 住院医生站病人管理
* @author 龚方舟
* @date 2020年5月25日 下午2:28:23
*
 */
@Api(tags="住院医生站病人管理")
@Controller
@RequestMapping("/imhzrybydctwork")
public class ImHzryByDctworkCtr extends BaseSpringController {
	
	static final Logger logger = LoggerFactory.getLogger(ImHzryByDctworkCtr.class);
	
	@Autowired
	private ImHzryByDctworkSer imHzryByDctworkSer;
	
	@Autowired
	private ZyDicSer zyDicSer;	
	
	@Autowired
	private ImHzrySer imHzrySer;
	
	@Autowired
	private IReportExportFileSer iReportExportFileSer;
	
	@Autowired
	private CisHzyzSer cisHzyzSer;

	@Autowired
	private SkinPsjlSer skinPsjlSer;

	
	@RequestMapping("/queryPageByPatientInfo")
    @ResponseBody
    @ApiOperation(value="病区病人管理按条件分页查询-返回列表" ,httpMethod="POST")
    public ReturnEntity<PageInfo<ImHzryDctworkResp>> queryPageByPatientInfo(ImHzryDctworkReq req, PageQuery page){
        return ReturnEntityUtil.success(imHzryByDctworkSer.queryBedPatientInfo(req , this.getUser(), page));
    }
	
	@RequestMapping("/getPatientInfoById")
	@ResponseBody
	@ApiOperation(value="病区病人管理病人基本信息" ,httpMethod="POST")
	public ReturnEntity<ImHzry> getPatientInfoById(@ApiParam(name = "zyh", value = "主键zyh", required = true) @RequestParam Integer zyh){
		ImHzry imHzry = imHzryByDctworkSer.getById(zyh);
		return ReturnEntityUtil.success(imHzry);
	}
	  
	@RequestMapping("/getDiagnosisByEntity")
	@ResponseBody
	@ApiOperation(value="病区病人管理病人诊断信息" ,httpMethod="POST")
	public ReturnEntity<List<ImRyzdResp>> getDiagnosisById(ImRyzdReq imRyzdReq){
	    return ReturnEntityUtil.success(imHzryByDctworkSer.getDiagnosisByEntity(imRyzdReq));
	}
	  
	@RequestMapping("/getAllergicDrugsByEntity")
	@ResponseBody
	@ApiOperation(value="病区病人管理病人过敏药物" ,httpMethod="POST")
	public ReturnEntity<List<SkinPsjlDrugsResp>> getAllergicDrugsByEntity(@ApiParam(name = "brid", value = "病人id", required = true) 
	@RequestParam Integer brid){
	  	return ReturnEntityUtil.success(skinPsjlSer.getEntityMapper().
	  			queryAllergyDrugsInfo(brid, this.getUser().getHospitalId()));
  	}
  
	@RequestMapping("/deleteByCompositeKeys")
	@ResponseBody
	@ApiOperation(value="病区病人管理入院诊断删除" ,httpMethod="POST")
	public ReturnEntity deleteByCompositeKeys(@ApiParam(name = "zyh", value = "住院号", required = true) 
			@RequestParam Integer zyh, @ApiParam(name = "zdxh", value = "诊断序号", required = true) 
			@RequestParam Integer zdxh, @ApiParam(name = "zdlb", value = "诊断类别", required = true) 
			@RequestParam Integer zdlb ) {
		imHzryByDctworkSer.deleteByCompositeKeys(zyh, zdxh, zdlb);        
	    return ReturnEntityUtil.success();            
	}
	
	@RequestMapping("/deleteAllergicDrugsById")
	@ResponseBody
	@ApiOperation(value="病区病人管理过敏药物删除" ,httpMethod="POST")
	public ReturnEntity deleteAllergicDrugsById(@ApiParam(name = "jlxh", value = "记录序号", required = true) 
			@RequestParam Integer jlxh) {
		imHzryByDctworkSer.deleteAllergicDrugsById(jlxh);        
	    return ReturnEntityUtil.success();            
	}
	
	@RequestMapping("/savePatientInfo")
	@ResponseBody
	@ApiOperation(value="病区病人管理病人信息保存" ,httpMethod="POST")
	public ReturnEntity<DicJbbmInfoResp> savePatientInfo(@RequestBody ImHzryInfoReq imHzryInfoReq) {
		DicJbbmInfoResp dicJbbmInfoResp = imHzryByDctworkSer.savePatientInfo(imHzryInfoReq, this.getUser());        
	    return ReturnEntityUtil.success(dicJbbmInfoResp);            
	}
	
//	@RequestMapping("/saveCheck")
//    @ResponseBody
//    @ApiOperation(value="病区病人管理医嘱保存前校验" ,httpMethod="POST")
//    public ReturnEntity saveCheck(@RequestBody CheckProjectMaterialsReq opYjcf01) {
//        opYjcf01Ser.saveCheck(opYjcf01,getUser());
//        return ReturnEntityUtil.success();
//    }
	
	@RequestMapping("/saveWardPatientInfo")
	@ResponseBody
	@ApiOperation(value="病区病人管理医嘱保存" ,httpMethod="POST")
	public ReturnEntity saveWardPatientInfo(@Valid @RequestBody CisHzyzSaveReq cisHzyzSaveReq) {
		imHzryByDctworkSer.saveWardPatientInfo(cisHzyzSaveReq, this.getUser());        
	    return ReturnEntityUtil.success();            
	}
	
	@RequestMapping("/saveDocSubmit")
	@ResponseBody
	@ApiOperation(value="病区病人管理医嘱提交" ,httpMethod="POST")
	public ReturnEntity<CisHzyzDocSubmitResp> saveDocSubmit(CisHzyzSubmitReq cisHzyzSubmitReq) {
	    return ReturnEntityUtil.success(imHzryByDctworkSer.saveDocSubmit(cisHzyzSubmitReq, this.getUser()));            
	}
	
	@RequestMapping("/modifyLevelOfCare")
	@ResponseBody
	@ApiOperation(value="医嘱提交更新护理级别" ,httpMethod="POST")
	public ReturnEntity modifyLevelOfCare(@ApiParam(name = "zyh", value = "住院号", required = true) 
	@RequestParam Integer zyh, @ApiParam(name = "lsyz", value = "医嘱(长期:0,临时1)", required = true) 
	@RequestParam Integer lsyz) {
		imHzryByDctworkSer.modifyLevelOfCare(zyh, lsyz, this.getUser().getHospitalId());
	    return ReturnEntityUtil.success();            
	}
	
	@RequestMapping("/loadHospitalParams")
	@ResponseBody
	@ApiOperation(value="病区病人管理查询是否设置发药药房" ,httpMethod="POST")
	public ReturnEntity<NisFyyfQxResp> loadHospitalParams(@ApiParam(name = "brbq", value = "病人病区号", required = true) 
			@RequestParam Integer brbq) {
		NisFyyfQxResp nisFyyfQxResp = imHzryByDctworkSer.doLoadHospitalParams(brbq, this.getUser());        
	    return ReturnEntityUtil.success(nisFyyfQxResp);            
	}
	
	@RequestMapping("/cancelYz")
	@ResponseBody
	@ApiOperation(value="病区病人管理医嘱取消" ,httpMethod="POST")
	public ReturnEntity<String> cancelYz(@Valid @RequestBody CisHzyzCancelReq req) {
	    return ReturnEntityUtil.success(imHzryByDctworkSer.doCancelYz(req, this.getUser()));
	}
	
	@RequestMapping("/updateAdviceStatus")
	@ResponseBody
	@ApiOperation(value="病区病人管理取消医嘱停嘱" ,httpMethod="POST")
	public ReturnEntity updateAdviceStatus(@ApiParam(name = "isGroup", value = "是否同组", required = true) 
	@RequestParam Boolean isGroup, @ApiParam(name = "zyh", value = "住院号", required = true) 
	@RequestParam Integer zyh, @ApiParam(name = "yzzh", value = "医嘱组号", required = true) 
	@RequestParam Integer yzzh, @ApiParam(name = "jlxh", value = "记录序号", required = true) 
	@RequestParam Integer jlxh) {
		imHzryByDctworkSer.doUpdateAdviceStatus(isGroup, zyh, yzzh, jlxh);
	    return ReturnEntityUtil.success();            
	}
	
	@RequestMapping("/saveYzzf")
	@ResponseBody
	@ApiOperation(value="病区病人管理医嘱作废或取消作废" ,httpMethod="POST")
	public ReturnEntity<String> saveYzzf(@Valid @RequestBody CisHzyzInvalidReq req) {
	    return ReturnEntityUtil.success(imHzryByDctworkSer.saveYzzf(req, this.getUser()));            
	}
	
	@RequestMapping("/queryZtmx2")
	@ResponseBody
	@ApiOperation(value="病区病人管理查询已执行医嘱" ,httpMethod="POST")
	public ReturnEntity<List<CisHzyzResp>> queryZtmx2(@ApiParam(name = "jlxh", value = "记录序号", required = true) 
	@RequestParam Integer jlxh) {
	    return ReturnEntityUtil.success(imHzryByDctworkSer.doQueryZtmx2(jlxh));            
	}
	
	@RequestMapping("/yjIsPay")
	@ResponseBody
	@ApiOperation(value="病区病人管理取消已执行医嘱" ,httpMethod="POST")
	public ReturnEntity yjIsPay(@RequestBody CisHzyzXhListReq cisHzyzXhListReq) {
		imHzryByDctworkSer.doYjIsPay(cisHzyzXhListReq);
	    return ReturnEntityUtil.success();            
	}
	
	@RequestMapping("/zbyZx")
	@ResponseBody
	@ApiOperation(value="病区病人管理自备药停嘱" ,httpMethod="POST")
	public ReturnEntity zbyZx(@ApiParam(name = "zyh", value = "住院号", required = true) 
	@RequestParam Integer zyh) {
		imHzryByDctworkSer.doZbyZx(zyh, this.getUser());
	    return ReturnEntityUtil.success();            
	}
	
	@RequestMapping("/queryPatientVisitInfo")
	@ResponseBody
	@ApiOperation(value="病区病人管理病人基本信息和交款信息" ,httpMethod="POST")
	public ReturnEntity<ImHzryDctworkResp> queryPatientVisitInfo(@ApiParam(name = "zyh", value = "住院号", required = false) 
	@RequestParam(value="zyh", required = false) Integer zyh, @ApiParam(name = "zyhm", value = "住院号码", required = false) 
	@RequestParam(value="zyhm", required = false) String zyhm, @ApiParam(name = "brch", value = "病人床号", required = false) 
	@RequestParam(value="brch", required = false) String brch, @ApiParam(name = "type", value = "医嘱录入界面传", required = false) 
	@RequestParam(value="type", required = false) String type) {
	    return ReturnEntityUtil.success(imHzryByDctworkSer.queryPatientVisitInfo(zyh, zyhm, brch, type));            
	}
	
	
	@RequestMapping("/querydbjfFymx")
	@ResponseBody
	@ApiOperation(value="病区病人管理查询血透减负列表" ,httpMethod="POST")
	public ReturnEntity<List<ImFeeFymxXtResp>> querydbjfFymx(@ApiParam(name = "zyh", value = "住院号", required = true) 
	@RequestParam Integer zyh) {
	    return ReturnEntityUtil.success(imHzryByDctworkSer.doQuerydbjfFymx(zyh, this.getUser()));            
	}
	
	@RequestMapping("/saveDbjfFymx")
	@ResponseBody
	@ApiOperation(value="病区病人管理保存大病血透减负" ,httpMethod="POST")
	public ReturnEntity saveDbjfFymx(@RequestBody ImFeeFymxDbReq imFeeFymxDbReq) {
		imHzryByDctworkSer.doSaveDbjfFymx(imFeeFymxDbReq);
	    return ReturnEntityUtil.success();            
	}
	
	@RequestMapping("/loadDischargeDays")
	@ResponseBody
	@ApiOperation(value="病区病人管理计算住院天数" ,httpMethod="POST")
	public ReturnEntity<Double> loadDischargeDays(@ApiParam(name = "ryrq", value = "入院日期", required = true) 
	@RequestParam String ryrq, @ApiParam(name = "cyrq", value = "出院日期", required = true) 
	@RequestParam String cyrq) {
	    return ReturnEntityUtil.success(imHzryByDctworkSer.doLoadDischargeDays(ryrq, cyrq));            
	}
	
	@RequestMapping("/saveLeaveHospitalProve")
	@ResponseBody
	@ApiOperation(value="病区病人管理出院证保存或取消" ,httpMethod="POST")
	public ReturnEntity saveLeaveHospitalProve(ImRcjlCyReq imRcjlCyReq) {
		imHzryByDctworkSer.doSaveLeaveHospitalProve(imRcjlCyReq,this.getUser());
	    return ReturnEntityUtil.success();            
	}
	
	
	@RequestMapping("/loadPatientExInfo")
	@ResponseBody
	@ApiOperation(value="单机病人获取过敏药物等信息" ,httpMethod="POST")
	public ReturnEntity<SkinPsjlQueryResp> loadPatientExInfo(@ApiParam(name = "brid", value = "病人id", required = true) 
	@RequestParam Integer brid, @ApiParam(name = "zyh", value = "住院号", required = true) 
	@RequestParam Integer zyh, PageQuery page){
	  	return ReturnEntityUtil.success(imHzryByDctworkSer.doLoadPatientExInfo(zyh, brid, this.getUser()));
  	}
	
	
	@RequestMapping("/getHzbgList")
	@ResponseBody
	@ApiOperation(value="数据盒会诊报告分页查询" ,httpMethod="POST")
	public ReturnEntity<PageInfo<YsZyHzBgResp>> getHzbgList(@ApiParam(name = "zyhm", value = "住院号码", required = true) 
	@RequestParam String zyhm, PageQuery page){
		PageInfo<YsZyHzBgResp> pageInfo = PageHelper.startPage(
				page.getPageNum(), page.getPageSize()).doSelectPageInfo(
						() -> imHzryByDctworkSer.doGetHzbgList(zyhm)
	            );
	  	PageInfo<YsZyHzBgResp> ret = BeanUtil.toPage(pageInfo, YsZyHzBgResp.class);
	  	return ReturnEntityUtil.success(ret);
  	}
	
	@RequestMapping("/queryBqyzByDataBox")
	@ResponseBody
	@ApiOperation(value="数据盒临时长期变更医嘱分页查询" ,httpMethod="POST")
	public ReturnEntity<PageInfo<CisHzyz>> queryBqyzByDataBox(CisHzyzDataReq cisHzyzDataReq, PageQuery page){
		PageInfo<CisHzyz> pageInfo = PageHelper.startPage(
				page.getPageNum(), page.getPageSize()).doSelectPageInfo(
						() -> imHzryByDctworkSer.queryBqyzByDataBox(cisHzyzDataReq)
	            );
	  	PageInfo<CisHzyz> ret = BeanUtil.toPage(pageInfo, CisHzyz.class);
	  	return ReturnEntityUtil.success(ret);
  	}		
		
	@RequestMapping("/queryWardOrderList")
	@ResponseBody
	@ApiOperation(value="住院医生护士病人医嘱列表查询" ,httpMethod="POST")
	public ReturnEntity<List<CisHzyzQueryResp>> queryWardOrderList(CisHzyzQueryApiReq cisHzyzQueryReq){
	  	return ReturnEntityUtil.success(imHzryByDctworkSer.execute(cisHzyzQueryReq, this.getUser()));
  	}
	
	
	@RequestMapping("/loadDetailsInfo")
	@ResponseBody
	@ApiOperation(value="是否为过敏药物校验" ,httpMethod="POST")
	public ReturnEntity<SkinPsjlCheckResp> loadDetailsInfo(SkinPsjlCheckReq skinPsjlCheckReq){
	  	return ReturnEntityUtil.success(imHzryByDctworkSer.doLoadDetailsInfo(skinPsjlCheckReq));
  	}
	
	
	@RequestMapping("/loadPatientInfo")
	@ResponseBody
	@ApiOperation(value="查询当前科室全部在院病人" ,httpMethod="POST")
	public ReturnEntity<PageInfo<ImHzry>> loadPatientInfo(@ApiParam(name = "brks", value = "病人科室", required = true) 
	@RequestParam Integer brks, PageQuery page){
		ImHzry imHzry = new ImHzry();
		imHzry.setCypb(0);
		imHzry.setBrks(brks);
		PageInfo<ImHzry> pageInfo = PageHelper.startPage(
				page.getPageNum(), page.getPageSize()).doSelectPageInfo(
						() -> imHzryByDctworkSer.getEntityMapper().findByEntity(imHzry)
	            );
	  	PageInfo<ImHzry> ret = BeanUtil.toPage(pageInfo, ImHzry.class);
	  	return ReturnEntityUtil.success(ret);
  	}
	
	
	@RequestMapping("/loadLeaveHospitalInfo")
	@ResponseBody
	@ApiOperation(value="查询出院证病人信息" ,httpMethod="POST")
	public ReturnEntity<ImHzryLeaveHospitalResp> loadLeaveHospitalInfo(@ApiParam(name = "zyh", value = "住院号", required = true) 
	@RequestParam Integer zyh){
		ImHzry imHzry = new ImHzry();
		imHzry.setZyh(zyh);
		imHzry.setJgid(getUser().getHospitalId());
	  	return ReturnEntityUtil.success(imHzryByDctworkSer.getEntityMapper().
	  			queryDischargeCertificateInfo(imHzry));
  	}
	
	@RequestMapping("/queryDischargedPatients")
	@ResponseBody
	@ApiOperation(value="查询出院病人列表-分页" ,httpMethod="POST")
	public ReturnEntity<PageInfo<ImHzryDctworkResp>> queryDischargedPatients(ImHzryQueryReq imHzryQueryReq,														 PageQuery page){
		PageInfo<ImHzryDctworkResp> pageInfo = PageHelper.startPage(
				page.getPageNum(), page.getPageSize()).doSelectPageInfo(
						() -> imHzryByDctworkSer.queryDischargedPatients(imHzryQueryReq, this.getUser())
	            );
	  	return ReturnEntityUtil.success(pageInfo);
  	}
	
	
	@GetMapping("/bedsideOrWristbandPrintFile")
	@ApiOperation(value="床头或腕带打印")
	public void bedsideOrWristbandPrintFile(@RequestParam("zyh") Integer zyh, @RequestParam("type") Integer type,
			HttpServletResponse response) throws WriterException, IOException{
		ImHzry imHzry = imHzryByDctworkSer.getById(zyh);
		Map<String, Object> map = new HashMap<String, Object>(16);
		map.put("KSMC", zyDicSer.getBrbqDic(this.getUser().getHospitalId()).get(imHzry.getBrbq()));
		map.put("BRXM", imHzry.getBrxm());
		map.put("BRCH", imHzry.getBrch());
		map.put("ZYHM", imHzry.getZyhm());
		map.put("CSRQ", DateUtil.date(imHzry.getCsny()).toSqlDate());
		String jasperName = "jrxml/ctkprintHd.jasper";
		if(type == 1) {
			map.put("BRXB", imHzry.getBrxb() == 1 ? "男" : "女");
			map.put("AGE", imHzry.getRynl());
			map.put("RYRQ", DateUtil.format(imHzry.getRyrq(), DatePattern.CHINESE_DATE_PATTERN));
//			Map<EncodeHintType,Object> config = new HashMap<EncodeHintType, Object>();
//			config.put(EncodeHintType.CHARACTER_SET, BarcodeUtil.CHARACTER_SET); 
//			config.put(EncodeHintType.MARGIN,1); 
//			map.put("TM", "data:image/jpg;base64," + BarcodeUtil.createBarcode(imHzry.getZyhm(), 160, 30, config));
		}
		if(type == 2) {
			jasperName = "jrxml/WristletPrint.jasper";
			map.put("KSMC", this.getUser().getHospitalName() + 
					zyDicSer.getBrksDic(this.getUser().getHospitalId()).get(imHzry.getBrks()));
			map.put("BQMC", zyDicSer.getBrbqDic(this.getUser().getHospitalId()).get(imHzry.getBrbq()));
		}
		iReportExportFileSer.reportHtmlForStream(map, jasperName, response);
	}
	
	@GetMapping("/dischargeCertificatePrintFile")
	@ApiOperation(value="出院证打印")
	public void dischargeCertificatePrintFile(@RequestParam("zyh") Integer zyh,
			HttpServletResponse response){
		String jasperName = "jrxml/HospProve.jasper";
		iReportExportFileSer.reportHtmlForStream(imHzryByDctworkSer.getDischargeCertificatePrintParameters(zyh, this.getUser()), 
				jasperName, response);
	}
	
	@RequestMapping("/queryPatientByIdCard")
	@ResponseBody
	@ApiOperation(value="根据身份证查询历史患者记录" ,httpMethod="POST")
	public ReturnEntity<List<ImHzry>> queryPatientByIdCard(@ApiParam(name = "sfzh", value = "身份证号", required = true) 
	@RequestParam String sfzh, @ApiParam(name = "beginDate", value = "开始日期", required = false) 
	@RequestParam(value="beginDate", required = false) String beginDate, @ApiParam(name = "endDate", value = "结束日期", required = false) 
	@RequestParam(value="endDate", required = false) String endDate){
		return ReturnEntityUtil.success(imHzrySer.getEntityMapper().queryPatientByIdCard(this.getUser().getHospitalId(), sfzh,
				beginDate, endDate));
	}
	
	@RequestMapping("/saveOrModifyHerbalMedicine")
	@ResponseBody
	@ApiOperation(value="保存或修改中草药" ,httpMethod="POST")
	public ReturnEntity<Integer> saveOrModifyHerbalMedicine(@Valid @RequestBody CisHzyzHerbalReq cisHzyzHerbalReq) {      
	    return ReturnEntityUtil.success(imHzryByDctworkSer.saveOrModifyHerbalMedicine(
	    		cisHzyzHerbalReq, this.getUser()));            
	}
	
	@RequestMapping("/queryHerbalMedicineInfo")
	@ResponseBody
	@ApiOperation(value="查询中草药组套明细" ,httpMethod="POST")
	public ReturnEntity<List<CisHzyz>> queryHerbalMedicineInfo(@ApiParam(name = "ztjlxh", value = "组套记录序号", required = true) 
	@RequestParam Integer ztjlxh) {
		CisHzyz cisHzyz = new CisHzyz();    
		cisHzyz.setZtyzjlxh(ztjlxh);
		cisHzyz.setJgid(this.getUser().getHospitalId());
	    return ReturnEntityUtil.success(cisHzyzSer.findByEntity(cisHzyz));            
	}

}
