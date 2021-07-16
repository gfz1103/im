
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

import com.buit.cis.nurse.request.NisWzhldReq;
import com.buit.cis.nurse.response.NisWzhldResp;
import com.buit.cis.nurse.service.NisHzmbSer;
import com.buit.cis.nurse.service.NisWzhldSer;
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
 * 危重患者护理单<br>
 * @author GONGFANGZHOU
 */
@Api(tags="危重患者护理单")
@Controller
@RequestMapping("/niswzhld")
public class NisWzhldCtr extends BaseSpringController{
    
    static final Logger logger = LoggerFactory.getLogger(NisWzhldCtr.class);
    
    @Autowired
    private NisWzhldSer nisWzhldSer;
    
    @Autowired
    private RedisFactory redisFactory;
    
    @Autowired
    private IReportExportFileSer iReportExportFileSer;
    
    @Autowired
    private NisHzmbSer nisHzmbSer;
    
    @RequestMapping("/queryNisWzhldByDate")
    @ResponseBody
    @ApiOperation(value="根据日期查询危重患者记录单" ,httpMethod="POST")
    public ReturnEntity<List<NisWzhldResp>> queryHljlByDate(@ApiParam(name = "zyh", value = "住院号", required = true)
    @RequestParam Integer zyh, @ApiParam(name = "queryDate", value = "查询时间(M-d)", required = false)
    @RequestParam(value="queryDate", required = false) String queryDate){
        return ReturnEntityUtil.success(nisWzhldSer.getEntityMapper().queryWzhldByDate(zyh, 
        		queryDate, this.getUser().getHospitalId()));
    }
  
    @RequestMapping("/saveNisWzhld")
    @ResponseBody
    @ApiOperation(value="新增修改保存危重患者记录单" ,httpMethod="POST")
    public ReturnEntity saveNisWzhld(NisWzhldReq nisWzhldReq){
    	nisWzhldReq.setJgid(this.getUser().getHospitalId());
    	if(StrUtil.isBlankIfStr(nisWzhldReq.getJlxh())) {
    		nisWzhldReq.setJlxh(redisFactory.getTableKey(TableName.DB_NAME, TableName.NIS_WZHLD));
    		nisWzhldSer.insert(nisWzhldReq);
    	}else {
    		nisWzhldSer.update(nisWzhldReq);
    	}
        return ReturnEntityUtil.success();    
    }
 
    @RequestMapping("/deleteNisWzhldByJlxh")
    @ResponseBody
    @ApiOperation(value="删除危重患者记录单" ,httpMethod="POST")
    public ReturnEntity deleteNisWzhldByJlxh(@ApiParam(name = "jlxh", value = "记录序号", required = true)
    @RequestParam Integer jlxh){
    	nisWzhldSer.removeById(jlxh);
        return ReturnEntityUtil.success();
    }
    
    @GetMapping("/criticalCareRecordsPrint")
    @ApiOperation(value="危重护理记录单打印")
    public void criticalCareRecordsPrint(@RequestParam(value="zyh") Integer zyh, @RequestParam(value="queryDate", required = false) String queryDate,
    		HttpServletResponse response){
    	List<Map<String, Object>> list = nisWzhldSer.queryWzhldByDatePrintInfo(zyh, 
        		queryDate, this.getUser().getHospitalId());
        String jasperName = "jrxml/CriticalCareRecordsPrintHd.jasper";
        nisHzmbSer.ComplementEmptyline(list, 15);
        iReportExportFileSer.reportHtmlForStream(list, null, jasperName, response);
    }
    
    @GetMapping("/criticalCareRecordsPrintColumnHead")
    @ApiOperation(value="危重护理记录单列头打印")
    public void criticalCareRecordsPrintColumnHead(HttpServletResponse response){
		String jasperName = "jrxml/CriticalCareRecordsPrintColumnHeadHd.jasper";
		iReportExportFileSer.reportHtmlForStream(null, jasperName, response);  
    }
    
    
}

