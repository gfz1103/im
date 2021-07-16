
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

import com.buit.cis.nurse.model.NisHljhdcs;
import com.buit.cis.nurse.request.NisHljhdReq;
import com.buit.cis.nurse.response.NisHljhdResp;
import com.buit.cis.nurse.service.NisHljhdSer;
import com.buit.cis.nurse.service.NisHljhdcsSer;
import com.buit.commons.BaseSpringController;
import com.buit.file.IReportExportFileSer;
import com.buit.utill.ReturnEntity;
import com.buit.utill.ReturnEntityUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * 护理计划单主表<br>
 * @author GONGFANGZHOU
 */
@Api(tags="护理计划单主表")
@Controller
@RequestMapping("/nishljhd")
public class NisHljhdCtr extends BaseSpringController{
    
    static final Logger logger = LoggerFactory.getLogger(NisHljhdCtr.class);
    
    @Autowired
    private NisHljhdSer nisHljhdSer;
    
    @Autowired
    private NisHljhdcsSer nisHljhdcsSer;
    
    @Autowired
    private IReportExportFileSer iReportExportFileSer;
    
    @RequestMapping("/queryHljhdByDate")
    @ResponseBody
    @ApiOperation(value="根据日期查询护理计划单" ,httpMethod="POST")
    public ReturnEntity<List<NisHljhdResp>> queryCgHljlByDate(@ApiParam(name = "zyh", value = "住院号", required = true)
    @RequestParam Integer zyh, @ApiParam(name = "queryDate", value = "查询时间(M-d)", required = false)
    @RequestParam(value="queryDate", required = false) String queryDate) {
        List<NisHljhdResp> list = nisHljhdSer.getEntityMapper().queryHljhdByDate(zyh,
                queryDate, this.getUser().getHospitalId());
        for(NisHljhdResp resp : list) {
        	NisHljhdcs hlcs = new NisHljhdcs();
        	hlcs.setZxjlxh(resp.getJlxh());
        	resp.setHlcsList(nisHljhdcsSer.findByEntity(hlcs));
        }
        return ReturnEntityUtil.success(list);
    }
  
    @RequestMapping("/saveOrUpdateNisHljhd")
    @ResponseBody
    @ApiOperation(value="保存或修改护理计划单" ,httpMethod="POST")
    public ReturnEntity saveOrUpdateNisHljhd(@RequestBody List<NisHljhdReq> list){
    	nisHljhdSer.saveOrUpdateNisHljhd(list, this.getUser().getHospitalId());
        return ReturnEntityUtil.success();    
    }
    /** 删除 */
    @RequestMapping("/deleteByJlxh")
    @ResponseBody
    @ApiOperation(value="删除护理计划单" ,httpMethod="POST")
    public ReturnEntity deleteByJlxh(@ApiParam(name = "jlxh", value = "记录序号", required = true)
    @RequestParam Integer jlxh) {
    	nisHljhdSer.removeById(jlxh);
    	nisHljhdcsSer.getEntityMapper().deleteByZxjlxh(jlxh);
        return ReturnEntityUtil.success();            
    }
    
    @GetMapping("/nursingPlanPrint")
    @ApiOperation(value="护理计划单打印")
    @SuppressWarnings("unchecked")
    public void nursingPlanPrint(@RequestParam(value="zyh") Integer zyh, @RequestParam(value="queryDate", required = false) String queryDate,
    		HttpServletResponse response) {
    	Map<String, Object> map = nisHljhdSer.nursingPlanPrint(zyh,queryDate, this.getUser().getHospitalId());
        String jasperName = "jrxml/NursingPlanPrint.jasper";
		iReportExportFileSer.reportHtmlForStream((List<Map<String,Object>>)map.get("resList"), 
				(Map<String, Object>)map.get("resMap"), jasperName, response);
    }
    
    @RequestMapping("/queryHljhdDateTree")
    @ResponseBody
    @ApiOperation(value="查询计划单时间树" ,httpMethod="POST")
    public ReturnEntity<List<String>> queryHljhdDateTree(@ApiParam(name = "zyh", value = "住院号", required = true)
    @RequestParam Integer zyh) {
        return ReturnEntityUtil.success(nisHljhdSer.getEntityMapper().queryHljhdDateTree(zyh, 
        		this.getUser().getHospitalId()));            
    }
    
}

