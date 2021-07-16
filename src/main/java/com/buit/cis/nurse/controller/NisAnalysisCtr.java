package com.buit.cis.nurse.controller;

import cn.hutool.json.JSONUtil;
import com.buit.cis.dctwork.service.CisHzyzSer;
import com.buit.cis.ims.model.ImHzry;
import com.buit.cis.ims.service.ImFeeFymxSer;
import com.buit.cis.ims.service.ImHzrySer;
import com.buit.cis.nurse.request.*;
import com.buit.cis.nurse.response.*;
import com.buit.cis.nurse.service.NisAnalysisSer;
import com.buit.commons.BaseSpringController;
import com.buit.commons.PageQuery;
import com.buit.file.IReportExportFileSer;
import com.buit.utill.BUHISUtil;
import com.buit.utill.PageUtil;
import com.buit.utill.ReturnEntity;
import com.buit.utill.ReturnEntityUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Api(tags="护士统计分析")
@Controller
@RequestMapping("/nisanalysis")
public class NisAnalysisCtr extends BaseSpringController{

	static final Logger logger = LoggerFactory.getLogger(NisAnalysisCtr.class);
	
	@Autowired
	private CisHzyzSer cisHzyzSer;
	
	@Autowired
	private ImHzrySer imHzrySer;
	
	@Autowired
	private NisAnalysisSer nisAnalysisSer;
	
	@Autowired
	private ImFeeFymxSer imFeeFymxSer;
	
	@Autowired
	private IReportExportFileSer iReportExportFileSer;
	
	@RequestMapping("/queryChangeOrders")
	@ResponseBody
	@ApiOperation(value="查询变动医嘱" ,httpMethod="POST")
	public ReturnEntity<PageInfo<CisHzyzChangeResp>> queryChangeOrders(CisHzyzChangeReq cisHzyzChangeReq, PageQuery page){
		cisHzyzChangeReq.setJgid(this.getUser().getHospitalId());
		PageInfo<CisHzyzChangeResp> pageInfo = PageHelper.startPage(
				page.getPageNum(), page.getPageSize()).doSelectPageInfo(
						() -> cisHzyzSer.getEntityMapper().queryChangeOrders(cisHzyzChangeReq)
				);
	    return ReturnEntityUtil.success(pageInfo);
	}
	
	@GetMapping("/changeOrdersPrintFile")
	@ApiOperation(value="变动医嘱打印")
	public void changeOrdersPrintFile(@RequestParam("reqStr") String reqStr, HttpServletResponse response){
		CisHzyzChangeReq cisHzyzChangeReq = JSONUtil.toBean(reqStr, CisHzyzChangeReq.class);
        cisHzyzChangeReq.setJgid(this.getUser().getHospitalId());
		List<CisHzyzChangeResp> list = cisHzyzSer.getEntityMapper().queryChangeOrders(cisHzyzChangeReq);
		Map<String, Object> map = new HashMap<String, Object>(16);
		map.put("title", this.getUser().getHospitalName());
		String jasperName = "jrxml/ChangedMedicalAdvice.jasper";
		if (CollectionUtils.isNotEmpty(list)) {
			iReportExportFileSer.reportHtmlForStream(BUHISUtil.ListObjToMap(list), map, jasperName, response);
		}
	}

	@RequestMapping("/querySummaryDispensing")
	@ResponseBody
	@ApiOperation(value = "查询病区发药汇总", httpMethod = "POST")
	public ReturnEntity<PageInfo<NisDispensingResp>> querySummaryDispensing(NisDispensingReq nisDispensingReq, PageQuery page) {
		nisDispensingReq.setJgid(this.getUser().getHospitalId());
		PageInfo<NisDispensingResp> pageInfo = PageHelper.startPage(
				page.getPageNum(), page.getPageSize()).doSelectPageInfo(
				() -> imHzrySer.getEntityMapper().querySummaryDispensing(nisDispensingReq)
		);
		return ReturnEntityUtil.success(pageInfo);
	}

	@RequestMapping("/querySummaryDispensingDetail")
	@ResponseBody
	@ApiOperation(value = "查询病区发药明细", httpMethod = "POST")
	public ReturnEntity<List<NisDispensingDetailResp>> querySummaryDispensingDetail(NisDispensingDetailReq req) {
		req.setJgid(this.getUser().getHospitalId());
		return ReturnEntityUtil.success(imHzrySer.getEntityMapper().querySummaryDispensingDetail(req));
	}

	@GetMapping("/summaryDispensingPrintFile")
	@ApiOperation(value = "病区发药汇总打印")
	public void summaryDispensingPrintFile(@RequestParam("reqStr") String reqStr, HttpServletResponse response) {
		NisDispensingReq nisDispensingReq = JSONUtil.toBean(reqStr, NisDispensingReq.class);
		nisDispensingReq.setJgid(this.getUser().getHospitalId());
		List<NisDispensingResp> list = imHzrySer.getEntityMapper().querySummaryDispensing(nisDispensingReq);
		Map<String, Object> map = nisAnalysisSer.getSummaryDispensingParameters(nisDispensingReq,
				this.getUser().getHospitalName());
		String jasperName = "jrxml/HospitalPharmacyMedicineHZ.jasper";
		if (CollectionUtils.isNotEmpty(list)) {
			iReportExportFileSer.reportHtmlForStream(BUHISUtil.ListObjToMap(list), map, jasperName, response);
		}
	}
	
	@RequestMapping("/queryHzDispensing")
	@ResponseBody
	@ApiOperation(value="查询病区已发药患者药品信息" ,httpMethod="POST")
	public ReturnEntity<PageInfo<NisHzDispensingResp>> queryHzDispensing(NisHzDispensingReq nisHzDispensingReq, PageQuery page){
		PageInfo<NisHzDispensingResp> pageInfo = PageHelper.startPage(
				page.getPageNum(), page.getPageSize()).doSelectPageInfo(
						() -> imHzrySer.getEntityMapper().queryHzDispensing(nisHzDispensingReq)
				);
	    return ReturnEntityUtil.success(pageInfo);
	}
	
	@RequestMapping("/queryPatientByDispensing")
	@ResponseBody
	@ApiOperation(value="病区发药按病人查询病人信息" ,httpMethod="POST")
	public ReturnEntity<PageInfo<ImHzry>> queryPatientByDispensing(NisDispensingReq nisDispensingReq, PageQuery page){
		nisDispensingReq.setJgid(this.getUser().getHospitalId());
		PageInfo<ImHzry> pageInfo = PageHelper.startPage(
				page.getPageNum(), page.getPageSize()).doSelectPageInfo(
						() -> imHzrySer.getEntityMapper().queryPatientByDispensing(nisDispensingReq)
				);
	    return ReturnEntityUtil.success(pageInfo);
	}
	
	@GetMapping("/revenueAccountingPrintFile")
	@ApiOperation(value="病区收入科室核算打印")
	public void revenueAccountingPrintFile(@RequestParam("beginDate") Timestamp beginDate, @RequestParam("endDate") Timestamp endDate, 
			@RequestParam("bqdm") Integer bqdm, HttpServletResponse response) {
        Map<String, Object> map = nisAnalysisSer.getRevenueAccountingParameters(beginDate, endDate, bqdm,
                this.getUser());
        List<Map<String, Object>> list = nisAnalysisSer.getRevenueAccountingFiles(beginDate, 
        		endDate, bqdm, this.getUser().getHospitalId());
        String jasperName = "jrxml/HospitalDepartmentChargesSummary.jasper";
        if(CollectionUtils.isNotEmpty(list)) {
			iReportExportFileSer.reportHtmlForStream(list, map, jasperName, response);
		}
    }
	
	@RequestMapping("/queryPatientByList")
    @ResponseBody
    @ApiOperation(value="病区一日清单病人信息" ,httpMethod="POST")
    public ReturnEntity<PageInfo<NisHzListResp>> queryPatientByList(@ApiParam(name = "bqdm", value = "病区代码", required = true)
    @RequestParam Integer bqdm, @ApiParam(name = "ksdm", value = "科室代码", required = false)
    @RequestParam(value="ksdm", required = false) Integer ksdm,@ApiParam(name = "zyhm", value = "住院号码", required = false)
    @RequestParam(value="zyhm", required = false) String zyhm,PageQuery page)  {
        return ReturnEntityUtil.success(PageUtil.getPageInfo(page.getPageNum(), page.getPageSize(), 
        		nisAnalysisSer.queryPatientByList(this.getUser().getHospitalId(), bqdm, ksdm, zyhm)));            
    }
	
	
	@GetMapping("/yzListCostPrintFile")
	@ApiOperation(value="病区一日清单医嘱格式打印")
	public void yzListCostPrintFile(@RequestParam("reqStr") String reqStr, HttpServletResponse response){
		NisYzCostReq  nisYzCostReq = JSONUtil.toBean(reqStr, NisYzCostReq.class);
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("zyh", nisYzCostReq.getZyh());
		parameters.put("beginDate", nisYzCostReq.getBeginDate());
		parameters.put("endDate", nisYzCostReq.getEndDate());
		List<Map<String, Object>> list = imFeeFymxSer.getEntityMapper().queryListByYz(parameters);
		Map<String, Object> map = nisAnalysisSer.getYzCostParameters(nisYzCostReq);
		String jasperName = "jrxml/HospitalCostYzgs.jasper";
		if(CollectionUtils.isNotEmpty(list)) {
			iReportExportFileSer.reportHtmlForStream(list, map, jasperName, response);
		}
	}

	
}
