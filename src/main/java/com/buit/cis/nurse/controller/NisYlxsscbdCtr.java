
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

import com.buit.cis.nurse.model.NisYlxsscbd;
import com.buit.cis.nurse.request.NisYlxsscbdReq;
import com.buit.cis.nurse.response.NisYlxsscbdResp;
import com.buit.cis.nurse.response.NisYlxssjksfResp;
import com.buit.cis.nurse.response.NisYlxsszgResp;
import com.buit.cis.nurse.service.NisYlxsscbdSer;
import com.buit.cis.nurse.service.NisYlxssjksfSer;
import com.buit.cis.nurse.service.NisYlxsszgSer;
import com.buit.commons.BaseSpringController;
import com.buit.file.IReportExportFileSer;
import com.buit.utill.BeanUtil;
import com.buit.utill.ReturnEntity;
import com.buit.utill.ReturnEntityUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * 压力性损伤预报、传报单<br>
 * @author GONGFANGZHOU
 */
@Api(tags="压力性损伤预报、传报单")
@Controller
@RequestMapping("/nisylxsscbd")
public class NisYlxsscbdCtr extends BaseSpringController{
    
    static final Logger logger = LoggerFactory.getLogger(NisYlxsscbdCtr.class);
    
    @Autowired
    private NisYlxsscbdSer nisYlxsscbdSer;
    
    @Autowired
    private NisYlxssjksfSer nisYlxssjksfSer;
    
    @Autowired
    private NisYlxsszgSer nisYlxsszgSer;
    
    @Autowired
    private IReportExportFileSer iReportExportFileSer;
    
    @RequestMapping("/queryYlxsscbdByDate")
    @ResponseBody
    @ApiOperation(value="根据日期查询压力性损伤传报单" ,httpMethod="POST")
    public ReturnEntity<List<NisYlxsscbdResp>> queryYlxsscbdByDate(@ApiParam(name = "zyh", value = "住院号", required = true)
    @RequestParam Integer zyh, @ApiParam(name = "queryDate", value = "查询时间(M-d H:i)", required = false)
    @RequestParam(value="queryDate", required = false) String queryDate){
    	List<NisYlxsscbd> list = nisYlxsscbdSer.getEntityMapper().queryYlxsscbdByDate(zyh,
        		queryDate, this.getUser().getHospitalId());
    	List<NisYlxsscbdResp> respList = BeanUtil.toBeans(list, NisYlxsscbdResp.class);
    	for(NisYlxsscbdResp nisYlxsscbdResp : respList) {
    		List<NisYlxssjksfResp> jksfList = nisYlxssjksfSer.getEntityMapper().queryJksfByCbdjlxh(nisYlxsscbdResp.getJlxh());
    		if(CollectionUtils.isNotEmpty(jksfList)) {
    			nisYlxsscbdResp.setNisYlxssjksfList(jksfList);
    		}
    		List<NisYlxsszgResp> zgList = nisYlxsszgSer.getEntityMapper().queryZgByCbdjlxh(nisYlxsscbdResp.getJlxh());
    		if(CollectionUtils.isNotEmpty(zgList)) {
    			nisYlxsscbdResp.setNisYlxsszgList(zgList);
    		}
    	}
        return ReturnEntityUtil.success(respList);
    }

    @RequestMapping("/saveYlxsscbd")
    @ResponseBody
    @ApiOperation(value="新增修改保存压力性损伤传报单" ,httpMethod="POST")
    public ReturnEntity saveYlxsscbd(@RequestBody NisYlxsscbdReq nisYlxsscbdReq){
    	nisYlxsscbdSer.saveYlxsscbd(nisYlxsscbdReq, this.getUser());
        return ReturnEntityUtil.success();    
    }
    
    @RequestMapping("/deleteYlxsscbdByDate")
    @ResponseBody
    @ApiOperation(value="根据日期删除压力性损伤传报单" ,httpMethod="POST")
    public ReturnEntity deleteYlxsscbdByDate(@ApiParam(name = "zyh", value = "住院号", required = true)
    @RequestParam Integer zyh, @ApiParam(name = "queryDate", value = "查询时间(M-d H:i)", required = false)
    @RequestParam(value="queryDate", required = false) String queryDate){
    	nisYlxsscbdSer.deleteYlxsscbdByDate(zyh, queryDate, this.getUser());
        return ReturnEntityUtil.success();
    }
    
    @GetMapping("/stressInjuryPrint")
    @ApiOperation(value="压力性损伤传报单打印")
    public void stressInjuryPrint(@RequestParam(value="zyh") Integer zyh, @RequestParam(value="queryDate", required = false) String queryDate,
    		HttpServletResponse response){
    	List<Map<String, Object>> list = nisYlxsscbdSer.getEntityMapper().queryPrintYlxsscbdByDate(zyh,
        		queryDate, this.getUser().getHospitalId());
    	if(CollectionUtils.isNotEmpty(list)) {
        	Map<String, Object> params = list.get(0);
	        params.put("SUBREPORT_DIR", this.getClass().getClassLoader().getResource("jrxml/"));
	        String jasperName = "jrxml/StressInjuryPrint.jasper";
	        iReportExportFileSer.reportHtmlForStream(nisYlxsscbdSer.queryPrintInfo(list), params, jasperName, response);
    	}
       
        
    }
}

 