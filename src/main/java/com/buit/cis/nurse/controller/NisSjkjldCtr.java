
package com.buit.cis.nurse.controller;

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

import com.buit.cis.nurse.enums.NursingTypeEnum;
import com.buit.cis.nurse.request.NisCghljldColumnHeadReq;
import com.buit.cis.nurse.request.NisSjkjldReq;
import com.buit.cis.nurse.response.NisSjkjldResp;
import com.buit.cis.nurse.service.NisHljldzdySer;
import com.buit.cis.nurse.service.NisHzmbSer;
import com.buit.cis.nurse.service.NisSjkjldSer;
import com.buit.commons.BaseSpringController;
import com.buit.file.IReportExportFileSer;
import com.buit.system.utill.ObjectToTypes;
import com.buit.utill.BUHISUtil;
import com.buit.utill.ReturnEntity;
import com.buit.utill.ReturnEntityUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * 神经科记录单<br>
 * @author GONGFANGZHOU
 */
@Api(tags="神经科记录单")
@Controller
@RequestMapping("/nissjkjld")
public class NisSjkjldCtr extends BaseSpringController{
    
    static final Logger logger = LoggerFactory.getLogger(NisSjkjldCtr.class);
    
    @Autowired
    private NisSjkjldSer nisSjkjldSer;
    
    @Autowired
    private NisHljldzdySer nisHljldzdySer;
    
    @Autowired
    private IReportExportFileSer iReportExportFileSer;
    
    @Autowired
    private NisHzmbSer nisHzmbSer;
    
    @RequestMapping("/querySjkjldByDate")
    @ResponseBody
    @ApiOperation(value="根据日期查询神经科记录单" ,httpMethod="POST")
    public ReturnEntity<List<NisSjkjldResp>> querySjkjldByDate(@ApiParam(name = "zyh", value = "住院号", required = true)
    @RequestParam Integer zyh, @ApiParam(name = "queryDate", value = "查询时间(M-d)", required = false)
    @RequestParam(value="queryDate", required = false) String queryDate) {
        List<NisSjkjldResp> list = nisSjkjldSer.getEntityMapper().querySjkjldByDate(zyh,
                queryDate, this.getUser().getHospitalId());
        List<Map<String, Object>> listMap = BUHISUtil.ListObjToMap(list);
        for (Map<String, Object> map : listMap) {
            List<Map<String, Object>> zdyList = nisHljldzdySer.getEntityMapper().queryZdynrByZdyId(
                    ObjectToTypes.parseInt(map.get("jlxh")), NursingTypeEnum.neurologyRecords.getCode(),
                    this.getUser().getHospitalId());
            if (CollectionUtils.isNotEmpty(zdyList)) {
                for (int i = 0; i < zdyList.size(); i++) {
                    map.put("zdynr" + (i + 1), zdyList.get(i).get("zdynr"));
                }
            }
        }
        return ReturnEntityUtil.success(BUHISUtil.ListToResultSet(listMap, new NisSjkjldResp()));
    }
  
    @RequestMapping("/saveSjkjld")
    @ResponseBody
    @ApiOperation(value="新增修改保存神经科记录单" ,httpMethod="POST")
    public ReturnEntity saveSjkjld(@RequestBody NisSjkjldReq nisSjkjldReq){	
    	nisSjkjldSer.saveSjkjld(nisSjkjldReq, this.getUser());
        return ReturnEntityUtil.success();    
    }
   
    @RequestMapping("/deleteSjkjldByJlxh")
    @ResponseBody
    @ApiOperation(value="删除神经科记录单" ,httpMethod="POST")
    public ReturnEntity deleteSjkjldByJlxh(@ApiParam(name = "jlxh", value = "记录序号", required = true)
    @RequestParam Integer jlxh){
    	nisSjkjldSer.removeByJlxh(jlxh, this.getUser().getHospitalId());
        return ReturnEntityUtil.success();
    }
    
    @GetMapping("/neurologyRecordsPrint")
    @ApiOperation(value="神经科记录单打印")
    public void neurologyRecordsPrint(@RequestParam(value="zyh") Integer zyh, @RequestParam(value="queryDate", required = false) String queryDate,
    		HttpServletResponse response){
        String jasperName = "jrxml/NeurologyRecordsPrint.jasper";
        List<Map<String, Object>> listMap = nisSjkjldSer.querySjkjldByDatePrintInfo(zyh, queryDate, 
        		this.getUser().getHospitalId());
        nisHzmbSer.ComplementEmptyline(listMap, 23);
        iReportExportFileSer.reportHtmlForStream(listMap, null, jasperName, response);
    }
    
    @GetMapping("/neurologyRecordsColumnHead")
    @ApiOperation(value="神经科记录单列头打印")
    public void neurologyRecordsColumnHead(NisCghljldColumnHeadReq req, HttpServletResponse response){
		String jasperName = "jrxml/NeurologyRecordsColumnHead.jasper";
		iReportExportFileSer.reportHtmlForStream(BUHISUtil.caseInsensitiveMap(req),
        		jasperName, response); 
    } 
    
}

