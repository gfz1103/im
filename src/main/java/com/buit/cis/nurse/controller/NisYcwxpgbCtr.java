
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

import com.buit.cis.nurse.model.NisYcwxpgb;
import com.buit.cis.nurse.request.NisYcwxpgbReq;
import com.buit.cis.nurse.response.NisYcwxpgbResp;
import com.buit.cis.nurse.service.NisHzmbSer;
import com.buit.cis.nurse.service.NisYcwxpgbSer;
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
 * 住院患者压疮危险因素评估表<br>
 * @author GONGFANGZHOU
 */
@Api(tags="住院患者压疮危险因素评估表")
@Controller
@RequestMapping("/nisycwxpgb")
public class NisYcwxpgbCtr extends BaseSpringController{
    
    static final Logger logger = LoggerFactory.getLogger(NisYcwxpgbCtr.class);
    
    @Autowired
    private NisYcwxpgbSer nisYcwxpgbSer;
    
    @Autowired
    private RedisFactory redisFactory;
    
    @Autowired
    private IReportExportFileSer iReportExportFileSer;
    
    @Autowired
    private NisHzmbSer nisHzmbSer;
    
    @RequestMapping("/queryYcwxpgbByDate")
    @ResponseBody
    @ApiOperation(value="根据日期查询住院患者压疮危险因素评估表" ,httpMethod="POST")
    public ReturnEntity<List<NisYcwxpgbResp>> queryYcwxpgbByDate(@ApiParam(name = "zyh", value = "住院号", required = true)
    @RequestParam Integer zyh, @ApiParam(name = "queryDate", value = "查询时间(M-d)", required = false)
    @RequestParam(value="queryDate", required = false) String queryDate){
        return ReturnEntityUtil.success(nisYcwxpgbSer.getEntityMapper().queryYcwxpgbByDate(zyh, 
        		queryDate, this.getUser().getHospitalId()));
    }
    
    
    @RequestMapping("/saveYcwxpgb")
    @ResponseBody
    @ApiOperation(value="新增修改保存住院患者压疮危险因素评估表" ,httpMethod="POST")
    public ReturnEntity<Integer> saveYcwxpgb(NisYcwxpgbReq nisYcwxpgbReq){
    	Integer jlxh = null;
    	nisYcwxpgbReq.setJgid(this.getUser().getHospitalId());
    	if(StrUtil.isBlankIfStr(nisYcwxpgbReq.getJlxh())) {
    		jlxh = redisFactory.getTableKey(TableName.DB_NAME, TableName.NIS_YCWXPGB);
    		nisYcwxpgbReq.setJlxh(jlxh);
    		nisYcwxpgbSer.insert(nisYcwxpgbReq);
    	}else {
    		jlxh = nisYcwxpgbReq.getJlxh();
    		nisYcwxpgbSer.update(nisYcwxpgbReq);
    	}
        return ReturnEntityUtil.success(jlxh);    
    }
   
    @RequestMapping("/deleteYcwxpgbByJlxh")
    @ResponseBody
    @ApiOperation(value="删除住院患者压疮危险因素评估表" ,httpMethod="POST")
    public ReturnEntity deleteYcwxpgbByJlxh(@ApiParam(name = "jlxh", value = "记录序号", required = true)
    @RequestParam Integer jlxh){
    	nisYcwxpgbSer.removeById(jlxh);
        return ReturnEntityUtil.success();
    }
   
    @GetMapping("/riskFactorsPrint")
    @ApiOperation(value="住院患者压疮危险因素评估表打印")
    public void postpartumNursingRecordPrint(@RequestParam(value="zyh") Integer zyh, @RequestParam(value="queryDate", required = false) String queryDate,
    		HttpServletResponse response){
    	List<Map<String, Object>> list = nisYcwxpgbSer.getEntityMapper().queryPrintYcwxpgbByDate(zyh, 
        		queryDate, this.getUser().getHospitalId());
        String jasperName = "jrxml/RiskFactorsPrint.jasper";
        nisHzmbSer.ComplementEmptyline(list, 17);
        iReportExportFileSer.reportHtmlForStream(list, null, jasperName, response);
    }
    
    @GetMapping("/riskFactorsColumnHead")
    @ApiOperation(value="住院患者压疮危险因素评估表列头打印")
    public void postpartumNursingRecordColumnHead(HttpServletResponse response){
		String jasperName = "jrxml/RiskFactorsColumnHead.jasper";
		iReportExportFileSer.reportHtmlForStream(null, jasperName, response);  
    }  
    
    @RequestMapping("/queryYcwxpgbByJlxh")
    @ResponseBody
    @ApiOperation(value="根据记录序号查询住院患者压疮危险因素评估表" ,httpMethod="POST")
    public ReturnEntity<NisYcwxpgb> queryYcwxpgbByJlxh(@ApiParam(name = "jlxh", value = "记录序号", required = true)
    @RequestParam Integer jlxh){
        return ReturnEntityUtil.success(nisYcwxpgbSer.getById(jlxh));
    }
  
    
}

