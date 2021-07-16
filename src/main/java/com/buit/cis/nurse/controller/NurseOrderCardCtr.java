
package com.buit.cis.nurse.controller;



import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.buit.cis.dctwork.service.CisHzyzSer;
import com.buit.cis.ims.model.ImHzry;
import com.buit.cis.ims.service.ImHzrySer;
import com.buit.cis.nurse.request.CisHzyzCardCancelReq;
import com.buit.cis.nurse.request.CisHzyzCardQueryReq;
import com.buit.cis.nurse.request.CisHzyzCardReq;
import com.buit.cis.nurse.response.CisHzyzCancelPrintResp;
import com.buit.cis.nurse.response.CisHzyzCardKsResp;
import com.buit.cis.nurse.service.NurseOrderCardSer;
import com.buit.commons.BaseSpringController;
import com.buit.file.IReportExportFileSer;
import com.buit.utill.ReturnEntity;
import com.buit.utill.ReturnEntityUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import cn.hutool.core.date.DateUtil;
import cn.hutool.json.JSONUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * 护士工作站
 * @ClassName: NurseOrderCardCtr
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author 龚方舟
 * @date 2020年9月21日14:17:59
 *
 */
@Api(tags="护士医嘱卡片打印")
@Controller
@RequestMapping("/nurseOrderCard")
public class NurseOrderCardCtr extends BaseSpringController{

	static final Logger logger = LoggerFactory.getLogger(NurseOrderCardCtr.class);
	
	@Autowired
	private ImHzrySer imHzrySer;
	
	@Autowired
	private NurseOrderCardSer nurseOrderCardSer;
	
	@Autowired
	private CisHzyzSer cisHzyzSer;
	
	@Autowired
	private IReportExportFileSer iReportExportFileSer;
	
	@RequestMapping("/queryMedicalCardInfo")
    @ResponseBody
    @ApiOperation(value="医嘱卡片打印病人信息" ,httpMethod="POST")
    public ReturnEntity<List<ImHzry>> queryMedicalCardInfo(CisHzyzCardQueryReq req){
		req.setJgid(this.getUser().getHospitalId());
        return ReturnEntityUtil.success(imHzrySer.getEntityMapper().queryMedicalCardInfo(req));    
    }
	
	@RequestMapping("/queryCardBrksByBrbq")
    @ResponseBody
    @ApiOperation(value="医嘱卡片打印病人信息" ,httpMethod="POST")
    public ReturnEntity<List<CisHzyzCardKsResp>> queryCardBrksByBrbq(@ApiParam(name = "brbq", value = "病区代码", required = true)
    @RequestParam Integer brbq){
        return ReturnEntityUtil.success(imHzrySer.getEntityMapper().queryCardBrksByBrbq(brbq, 
        		this.getUser().getHospitalId()));    
    }
	
	@GetMapping("/oralCardPrintFile")
    @ApiOperation(value="医嘱口服卡固定卡片打印")
    public void oralCardPrintFile(@RequestParam("reqStr") String reqStr, HttpServletResponse response){
		CisHzyzCardReq cisHzyzCardReq = JSONUtil.toBean(reqStr, CisHzyzCardReq.class);
		Map<String, Object> map = nurseOrderCardSer.getOralCardParameters();
		List<Map<String, Object>> list = nurseOrderCardSer.getOralCardFields(cisHzyzCardReq, this.getUser());
		String jasperName = "jrxml/OralCardFileds.jasper";
		iReportExportFileSer.reportHtmlForStream(list, map, jasperName, response);
    }
	
	@GetMapping("/oralCardZxdPrintFile")
    @ApiOperation(value="医嘱口服执行单等打印")
    public void oralCardZxdPrintFile(@RequestParam("reqStr") String reqStr, HttpServletResponse response){
		CisHzyzCardReq cisHzyzCardReq = JSONUtil.toBean(reqStr, CisHzyzCardReq.class);
		Map<String, Object> map = nurseOrderCardSer.getOralCardZxdParameters(cisHzyzCardReq, this.getUser());
		List<Map<String, Object>> list = nurseOrderCardSer.getOralCardZxdFields(cisHzyzCardReq, this.getUser());
		String jasperName = "jrxml/OrderCardsMouthCardZX.jasper";
		Integer yzlb = cisHzyzCardReq.getYzlb();
		if(yzlb == 3 || yzlb == 4) {
			//注射卡、静滴卡
			jasperName = "jrxml/OrderCardsMouthCardZXAddYf.jasper";
		}else if(yzlb == 16) {
			//治疗单
			jasperName = "jrxml/TreatmentList.jasper";
		}
//		else if(yzlb == 10) {
//			//其他
//			jasperName = "OrderCardsMouthCardZX_zs.jasper";
//			sonjasperList = new ArrayList<String>();
//			sonjasperList.add("OrderCardsChildrenZX_zs.jasper");
//		}else if(yzlb == 14) {
//			//其他
//			jasperName = "OrderCardsOtherCardZX.jasper";
//			sonjasperList = new ArrayList<String>();
//			sonjasperList.add("OrderCardsChildrenZX.jasper");
//		}else if(yzlb == 15) {
//			//巡视卡
//			jasperName = "OrderCardsPatrolCard.jasper";
//			sonjasperList = new ArrayList<String>();
//			sonjasperList.add("OrderCardsChildrenSYXSKCQ.jasper");
//		}
		if(CollectionUtils.isNotEmpty(list)) {
			iReportExportFileSer.reportHtmlForStream(list, map, jasperName, response);
		}
    }
	
//	@GetMapping("/casualOralCardPrintFile")
//    @ApiOperation(value="临时医嘱口服卡固定卡片打印")
//    public void casualOralCardPrintFile(@RequestParam("reqStr") String reqStr, HttpServletResponse response){
//		CisHzyzCardReq cisHzyzCardReq = JSONUtil.toBean(reqStr, CisHzyzCardReq.class);
//		Map<String, Object> map = nurseOrderCardSer.getOralCardParameters();
//		List<Map<String, Object>> list = nurseOrderCardSer.getCasualOralCardFields(cisHzyzCardReq, this.getUser());
//		String jasperName = "jrxml/CasualOrderCardsMouthCard.jasper";
//		if(CollectionUtils.isNotEmpty(list)) {
//			iReportExportFileSer.reportHtmlForStream(list, map, jasperName, response);
//		}
//    }
	
	@GetMapping("/injectionCardPrintFile")
    @ApiOperation(value="静推固定卡片打印")
    public void injectionCardPrintFile(@RequestParam("reqStr") String reqStr, HttpServletResponse response){
		CisHzyzCardReq cisHzyzCardReq = JSONUtil.toBean(reqStr, CisHzyzCardReq.class);
		Map<String, Object> map = nurseOrderCardSer.getOralCardParameters();
		List<Map<String, Object>> list = nurseOrderCardSer.getInjectionCardFields(cisHzyzCardReq, this.getUser());
		String jasperName = "jrxml/OrderCardsInjectionCard.jasper";
		if(CollectionUtils.isNotEmpty(list)) {
			iReportExportFileSer.reportHtmlForStream(list, map, jasperName, response);
		}
    }
	
	@GetMapping("/stillDripCardPrintFile")
    @ApiOperation(value="长期临时静滴固定卡片打印")
    public void stillDripCardPrintFile(@RequestParam("reqStr") String reqStr, HttpServletResponse response){
		CisHzyzCardReq cisHzyzCardReq = JSONUtil.toBean(reqStr, CisHzyzCardReq.class);
		Map<String, Object> map = nurseOrderCardSer.getOralCardParameters();
		List<Map<String, Object>> list = nurseOrderCardSer.getStillDripCardFields(cisHzyzCardReq, this.getUser());
		String jasperName = "jrxml/OrderCardsStillDripCardLB.jasper";
//		if(yzlb == 8) {
//			//肌注卡
//			jasperName = "OrderCardsJZCard.jasper";
//		}else if(yzlb == 9) {
//			//皮下卡
//			jasperName = "OrderCardsPXCard.jasper";
//		}
		if(CollectionUtils.isNotEmpty(list)) {
			iReportExportFileSer.reportHtmlForStream(list, map, jasperName, response);
		}
    }
	
	@RequestMapping("/queryCancelPrintList")
    @ResponseBody
    @ApiOperation(value="医嘱卡片取消打印查询" ,httpMethod="POST")
    public ReturnEntity<PageInfo<CisHzyzCancelPrintResp>> queryCancelPrintList(@RequestBody CisHzyzCardReq cisHzyzCardReq){
		Map<String, Object> parameters = new HashMap<String, Object>(16);
		parameters.put("zyhList", cisHzyzCardReq.getZyhList());
		parameters.put("yysj", DateUtil.date().toSqlDate());
		PageInfo<CisHzyzCancelPrintResp> pageInfo = PageHelper.startPage(
				cisHzyzCardReq.getPageNum(), cisHzyzCardReq.getPageSize()).doSelectPageInfo(
						() -> cisHzyzSer.getEntityMapper().queryCancelPrintList(parameters)
				);
        return ReturnEntityUtil.success(pageInfo);    
    }
	
	@RequestMapping("/doCancelKpdy")
    @ResponseBody
    @ApiOperation(value="医嘱卡片取消打印" ,httpMethod="POST")
    public ReturnEntity doCancelKpdy(@RequestBody List<CisHzyzCardCancelReq> list){
		nurseOrderCardSer.doCancelKpdy(list);
        return ReturnEntityUtil.success();    
    }
	
	@GetMapping("/administrationRecordFile")
	@ApiOperation(value="给药记录单打印")
	public void administrationRecordFile(@RequestParam("reqStr") String reqStr, HttpServletResponse response){
		CisHzyzCardReq cisHzyzCardReq = JSONUtil.toBean(reqStr, CisHzyzCardReq.class);
		Map<String, Object> map = nurseOrderCardSer.getOralCardParameters();
		List<Map<String, Object>> list = nurseOrderCardSer.getAdministrationRecordFields(cisHzyzCardReq, this.getUser());
		String jasperName = "jrxml/AdministrationRecord.jasper";
		if(CollectionUtils.isNotEmpty(list)) {
			iReportExportFileSer.reportHtmlForStream(list, map, jasperName, response);
		}
	}

	@GetMapping("/dietListFile")
	@ApiOperation(value="饮食单打印")
	public void dietListFile(@RequestParam("reqStr") String reqStr, HttpServletResponse response){
		CisHzyzCardReq cisHzyzCardReq = JSONUtil.toBean(reqStr, CisHzyzCardReq.class);
		Map<String, Object> map = nurseOrderCardSer.getDietListParameters(cisHzyzCardReq, this.getUser());
		List<Map<String, Object>> list = cisHzyzSer.getEntityMapper().queryDietList(this.getUser().getHospitalId(),
				cisHzyzCardReq.getZyhList(),cisHzyzCardReq.getLsyz(), null);
		String jasperName = "jrxml/DietList.jasper";
		if(CollectionUtils.isNotEmpty(list)) {
			iReportExportFileSer.reportHtmlForStream(list, map, jasperName, response);
		}
	}
	
	@GetMapping("/summaryTreatmentListFile")
	@ApiOperation(value="汇总治疗单打印")
	public void summaryTreatmentListFile(@RequestParam("reqStr") String reqStr, HttpServletResponse response) throws Exception{
		CisHzyzCardReq cisHzyzCardReq = JSONUtil.toBean(reqStr, CisHzyzCardReq.class);
		Map<String, Object> map = nurseOrderCardSer.getSummaryTreatmentParameters(cisHzyzCardReq, this.getUser());
		List<Map<String, Object>> list = nurseOrderCardSer.getSummaryTreatmentFields(cisHzyzCardReq, this.getUser());
		String jasperName = "jrxml/SummaryTreatment.jasper";
		if(CollectionUtils.isNotEmpty(list)) {
			iReportExportFileSer.reportHtmlForStream(list, map, jasperName, response);
		}
	}
	
	@GetMapping("/rehydrationPrintFile")
    @ApiOperation(value="补液巡回执行单等打印")
    public void rehydrationPrintFile(@RequestParam("reqStr") String reqStr, HttpServletResponse response){
		CisHzyzCardReq cisHzyzCardReq = JSONUtil.toBean(reqStr, CisHzyzCardReq.class);
		Map<String, Object> map = nurseOrderCardSer.getOralCardZxdParameters(cisHzyzCardReq, this.getUser());
		List<Map<String, Object>> list = nurseOrderCardSer.getRehydrationPrintFields(cisHzyzCardReq, this.getUser());
		String jasperName = "jrxml/OrderCardsMouthCardSYXSKCQ.jasper";
		if(CollectionUtils.isNotEmpty(list)) {
			iReportExportFileSer.reportHtmlForStream(list, map, jasperName, response);
		}
    }
	
}
