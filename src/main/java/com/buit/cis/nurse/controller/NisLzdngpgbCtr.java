
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

import com.buit.cis.nurse.request.NisLzdngpgbReq;
import com.buit.cis.nurse.response.NisLzdngpgbResp;
import com.buit.cis.nurse.service.NisHzmbSer;
import com.buit.cis.nurse.service.NisLzdngpgbSer;
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
 * 留置导尿管感染风险因素评估表<br>
 * @author GONGFANGZHOU
 */
@Api(tags="留置导尿管感染风险因素评估表")
@Controller
@RequestMapping("/nislzdngpgb")
public class NisLzdngpgbCtr extends BaseSpringController{
    
    static final Logger logger = LoggerFactory.getLogger(NisLzdngpgbCtr.class);
    
    @Autowired
    private NisLzdngpgbSer nisLzdngpgbSer;
    
    @Autowired
    private RedisFactory redisFactory;
    
    @Autowired
    private IReportExportFileSer iReportExportFileSer;

    @Autowired
    private NisHzmbSer nisHzmbSer;
    
    @RequestMapping("/queryLzdngByDate")
    @ResponseBody
    @ApiOperation(value="根据日期查询留置导尿管感染风险因素评估表" ,httpMethod="POST")
    public ReturnEntity<List<NisLzdngpgbResp>> queryLzdngByDate(@ApiParam(name = "zyh", value = "住院号", required = true)
    @RequestParam Integer zyh, @ApiParam(name = "queryDate", value = "查询时间(M-d)", required = false)
    @RequestParam(value="queryDate", required = false) String queryDate){
        return ReturnEntityUtil.success(nisLzdngpgbSer.getEntityMapper().queryLzdngByDate(zyh, 
        		queryDate, this.getUser().getHospitalId()));
    }
  
    @RequestMapping("/saveLzdng")
    @ResponseBody
    @ApiOperation(value="新增修改保存患者留置导尿管感染风险因素评估表" ,httpMethod="POST")
    public ReturnEntity saveLzdng(NisLzdngpgbReq nisLzdngpgbReq){
    	nisLzdngpgbReq.setJgid(this.getUser().getHospitalId());
    	if(StrUtil.isBlankIfStr(nisLzdngpgbReq.getJlxh())) {
    		nisLzdngpgbReq.setJlxh(redisFactory.getTableKey(TableName.DB_NAME, TableName.NIS_LZDNGPGB));
    		nisLzdngpgbSer.insert(nisLzdngpgbReq);
    	}else {
    		nisLzdngpgbSer.update(nisLzdngpgbReq);
    	}
        return ReturnEntityUtil.success();    
    }
   
    @RequestMapping("/deleteLzdngByJlxh")
    @ResponseBody
    @ApiOperation(value="删除留置导尿管感染风险因素评估表" ,httpMethod="POST")
    public ReturnEntity deleteLzdngByJlxh(@ApiParam(name = "jlxh", value = "记录序号", required = true)
    @RequestParam Integer jlxh){
    	nisLzdngpgbSer.removeById(jlxh);
        return ReturnEntityUtil.success();
    }
    
    @GetMapping("/catheterInfectionPrint")
    @ApiOperation(value="留置导尿管感染风险因素评估表打印")
    public void catheterInfectionPrint(@RequestParam(value="zyh") Integer zyh, @RequestParam(value="queryDate", required = false) String queryDate,
    		HttpServletResponse response){
    	List<Map<String, Object>> list = nisLzdngpgbSer.getEntityMapper().queryPrintLzdngByDate(zyh, 
        		queryDate, this.getUser().getHospitalId());
        String jasperName = "jrxml/CatheterInfectionPrint.jasper";
        nisHzmbSer.ComplementEmptyline(list, 16);
        iReportExportFileSer.reportHtmlForStream(list, null, jasperName, response);
    }
    
    @GetMapping("/catheterInfectionColumnHead")
    @ApiOperation(value="留置导尿管感染风险因素评估表列头打印")
    public void catheterInfectionColumnHead(HttpServletResponse response){
		String jasperName = "jrxml/CatheterInfectionColumnHead.jasper";
		iReportExportFileSer.reportHtmlForStream(null, jasperName, response);  
    }  
    
}

