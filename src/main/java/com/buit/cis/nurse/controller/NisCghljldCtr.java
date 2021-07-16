
package com.buit.cis.nurse.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.buit.cis.nurse.request.NisCghljldColumnHeadReq;
import com.buit.cis.nurse.request.NisCghljldReq;
import com.buit.cis.nurse.response.NisCghljldResp;
import com.buit.cis.nurse.service.NisCghljldSer;
import com.buit.cis.nurse.service.NisHljldzdySer;
import com.buit.cis.nurse.service.NisHzmbSer;
import com.buit.commons.BaseSpringController;
import com.buit.commons.PageQuery;
import com.buit.file.IReportExportFileSer;
import com.buit.utill.BUHISUtil;
import com.buit.utill.PageUtil;
import com.buit.utill.ReturnEntity;
import com.buit.utill.ReturnEntityUtil;
import com.github.pagehelper.PageInfo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * 常规护理记录单<br>
 * @author GONGFANGZHOU
 */
@Api(tags="常规护理记录单")
@Controller
@RequestMapping("/niscghljld")
public class NisCghljldCtr extends BaseSpringController{
    
    static final Logger logger = LoggerFactory.getLogger(NisCghljldCtr.class);
    
    @Autowired
    private NisCghljldSer nisCghljldSer;
    
    @Autowired 
    private NisHljldzdySer nisHljldzdySer;
    
    @Autowired
    private IReportExportFileSer iReportExportFileSer;
    
    @Autowired
    private NisHzmbSer nisHzmbSer;
    
    @RequestMapping("/queryCgHljlByDate")
    @ResponseBody
    @ApiOperation(value="根据日期查询护理记录单" ,httpMethod="POST")
    public ReturnEntity<PageInfo<NisCghljldResp>> queryCgHljlByDate(@ApiParam(name = "zyh", value = "住院号", required = true)
    @RequestParam Integer zyh, @ApiParam(name = "queryDate", value = "查询时间(M-d)", required = false)
    @RequestParam(value="queryDate", required = false) String queryDate, PageQuery page) {
    	List<Map<String, Object>> list = BUHISUtil.ListObjToMap(nisCghljldSer.queryNisCghljldByDateInfo(zyh, queryDate, 
				this.getUser().getHospitalId()));
    	nisHzmbSer.ComplementEmptyline(list, 23);
        return ReturnEntityUtil.success(PageUtil.getPageInfo(page.getPageNum(), 
        		page.getPageSize(), BUHISUtil.ListToResultSet(list, new NisCghljldResp())));
    }

    @RequestMapping("/saveCgHljl")
    @ResponseBody
    @ApiOperation(value="新增修改保存护理记录单" ,httpMethod="POST")
    public ReturnEntity saveCgHljl(@RequestBody NisCghljldReq nisCghljldReq){
    	nisCghljldSer.saveCgHljl(nisCghljldReq, this.getUser());
        return ReturnEntityUtil.success();    
    }
    
    @RequestMapping("/deleteCgHljlByJlxh")
    @ResponseBody
    @ApiOperation(value="删除护理记录单" ,httpMethod="POST")
    public ReturnEntity deleteCgHljlByJlxh(@ApiParam(name = "jlxh", value = "记录序号", required = true)
    @RequestParam Integer jlxh){
    	nisCghljldSer.removeByJlxh(jlxh, this.getUser().getHospitalId());
        return ReturnEntityUtil.success();
    }
   
    @GetMapping("/nurseRecordPrint")
    @ApiOperation(value="护理记录单打印")
    public void nurseRecordPrint(@RequestParam(value="zyh") Integer zyh, @RequestParam(value="queryDate", required = false) String queryDate,
    		HttpServletResponse response){
        List<Map<String, Object>> listMap = nisCghljldSer.queryNisCghljldByDatePrintInfo(zyh, queryDate, 
        		this.getUser().getHospitalId());
        nisHzmbSer.ComplementEmptyline(listMap, 23);
		String jasperName = "jrxml/NurseRecordCgPrintHd.jasper";
		iReportExportFileSer.reportHtmlForStream(listMap, null, jasperName, response);
    }

    @GetMapping("/nurseRecordColumnHead")
    @ApiOperation(value="护理记录单列头打印")
    public void nurseRecordColumnHead(NisCghljldColumnHeadReq req, HttpServletResponse response){
		String jasperName = "jrxml/NurseRecordColumnHeadHd.jasper";
		iReportExportFileSer.reportHtmlForStream(BUHISUtil.caseInsensitiveMap(req),
        		jasperName, response); 
    } 
    
    @RequestMapping("/getById")
    @ResponseBody
    @ApiOperation(value="根据记录序号查询护理记录单" ,httpMethod="POST")
    public ReturnEntity<NisCghljldResp> getById(@ApiParam(name = "jlxh", value = "记录序号", required = true)
    @RequestParam Integer jlxh){
        return ReturnEntityUtil.success(nisCghljldSer.getEntityMapper().getCgHljlByJlxh(jlxh));
    }
    
}

