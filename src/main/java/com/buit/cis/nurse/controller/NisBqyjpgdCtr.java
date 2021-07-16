
package com.buit.cis.nurse.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.buit.cis.nurse.request.NisBqyjpgdReq;
import com.buit.cis.nurse.response.NisBqyjpgdResp;
import com.buit.cis.nurse.service.NisBqyjpgdSer;
import com.buit.cis.nurse.service.NisHzmbSer;
import com.buit.commons.BaseSpringController;
import com.buit.constans.TableName;
import com.buit.file.IReportExportFileSer;
import com.buit.utill.BUHISUtil;
import com.buit.utill.RedisFactory;
import com.buit.utill.ReturnEntity;
import com.buit.utill.ReturnEntityUtil;

import cn.hutool.core.util.StrUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * <br>患者病情预警评估单
 * @author GONGFANGZHOU
 */
@Api(tags="患者病情预警评估单")
@Controller
@RequestMapping("/nisbqyjpgd")
public class NisBqyjpgdCtr extends BaseSpringController{
    
    static final Logger logger = LoggerFactory.getLogger(NisBqyjpgdCtr.class);
    
    @Autowired
    private NisBqyjpgdSer nisBqyjpgdSer;
    
    @Autowired
    private RedisFactory redisFactory;
    
    @Autowired
    private IReportExportFileSer iReportExportFileSer;
    
    @Autowired
    private NisHzmbSer nisHzmbSer;
    
    @RequestMapping("/queryBqyjpgdByDate")
    @ResponseBody
    @ApiOperation(value="根据日期查询患者病情预警评估单" ,httpMethod="POST")
    public ReturnEntity<List<NisBqyjpgdResp>> queryBqyjpgdByDate(@ApiParam(name = "zyh", value = "住院号", required = true)
    @RequestParam Integer zyh, @ApiParam(name = "queryDate", value = "查询时间(M-d)", required = false)
    @RequestParam(value="queryDate", required = false) String queryDate){
        return ReturnEntityUtil.success(nisBqyjpgdSer.getEntityMapper().queryBqyjpgdByDate(zyh, 
        		queryDate, this.getUser().getHospitalId()));
    }
  
    @RequestMapping("/saveBqyjpgd")
    @ResponseBody
    @ApiOperation(value="新增修改保存患者病情预警评估单" ,httpMethod="POST")
    public ReturnEntity saveBqyjpgd(NisBqyjpgdReq nisBqyjpgdReq){
    	nisBqyjpgdReq.setJgid(this.getUser().getHospitalId());
    	if(StrUtil.isBlankIfStr(nisBqyjpgdReq.getJlxh())) {
    		nisBqyjpgdReq.setJlxh(redisFactory.getTableKey(TableName.DB_NAME, TableName.NIS_BQYJPGD));
    		nisBqyjpgdSer.insert(nisBqyjpgdReq);
    	}else {
    		nisBqyjpgdSer.update(nisBqyjpgdReq);
    	}
        return ReturnEntityUtil.success();    
    }
   
    @RequestMapping("/deleteBqyjpgdByJlxh")
    @ResponseBody
    @ApiOperation(value="删除患者病情预警评估单" ,httpMethod="POST")
    public ReturnEntity deleteBqyjpgdByJlxh(@ApiParam(name = "jlxh", value = "记录序号", required = true)
    @RequestParam Integer jlxh){
    	nisBqyjpgdSer.removeById(jlxh);
        return ReturnEntityUtil.success();
    }

    @GetMapping("/patientConditionWarningPrint")
    @ApiOperation(value="患者病情预警评估单打印")
    public void patientConditionWarningPrint(@RequestParam(value="zyh") Integer zyh, @RequestParam(value="queryDate", required = false) String queryDate,
    		HttpServletResponse response) {
    	List<Map<String, Object>> list = nisBqyjpgdSer.getEntityMapper().queryPrintBqyjpgdByDate(zyh, 
        		queryDate, this.getUser().getHospitalId());
        String jasperName = "jrxml/PatientConditionWarningPrint.jasper";
        nisHzmbSer.ComplementEmptyline(list, 18);
		iReportExportFileSer.reportHtmlForStream(list, null, jasperName, response);
    }
    
    @GetMapping("/patientConditionWarningColumnHead")
    @ApiOperation(value="患者病情预警评估单列头打印")
    public void patientConditionWarningColumnHead(HttpServletResponse response){
		String jasperName = "jrxml/PatientConditionWarningColumnHead.jasper";
		iReportExportFileSer.reportHtmlForStream(null,jasperName, response);
    }  

    
}

