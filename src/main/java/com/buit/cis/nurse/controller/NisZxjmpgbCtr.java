
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

import com.buit.cis.nurse.request.NisHlQueryReq;
import com.buit.cis.nurse.request.NisZxjmpgbReq;
import com.buit.cis.nurse.response.NisZxjmpgbResp;
import com.buit.cis.nurse.service.NisHzmbSer;
import com.buit.cis.nurse.service.NisZxjmpgbSer;
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
 * 中心静脉导管相关性感染风险因素评估表<br>
 * @author GONGFANGZHOU
 */
@Api(tags="中心静脉导管相关性感染风险因素评估表")
@Controller
@RequestMapping("/niszxjmpgb")
public class NisZxjmpgbCtr extends BaseSpringController{
    
    static final Logger logger = LoggerFactory.getLogger(NisZxjmpgbCtr.class);
    
    @Autowired
    private NisZxjmpgbSer nisZxjmpgbSer;
    
    @Autowired
    private RedisFactory redisFactory;
    
    @Autowired
    private IReportExportFileSer iReportExportFileSer;
    
    @Autowired
    private NisHzmbSer nisHzmbSer;
    
    @RequestMapping("/queryZxjmpgbByDate")
    @ResponseBody
    @ApiOperation(value="根据日期查询中心静脉导管相关性感染风险因素评估表" ,httpMethod="POST")
    public ReturnEntity<List<NisZxjmpgbResp>> queryZxjmpgbByDate(NisHlQueryReq nisHlQueryReq){
    	nisHlQueryReq.setJgid(this.getUser().getHospitalId());
        return ReturnEntityUtil.success(nisZxjmpgbSer.getEntityMapper().queryZxjmpgbByDate(nisHlQueryReq));
    }
  
    @RequestMapping("/saveZxjmpgb")
    @ResponseBody
    @ApiOperation(value="新增修改保存中心静脉导管相关性感染风险因素评估表" ,httpMethod="POST")
    public ReturnEntity saveZxjmpgb(NisZxjmpgbReq nisZxjmpgbReq){
    	nisZxjmpgbReq.setJgid(this.getUser().getHospitalId());
    	if(StrUtil.isBlankIfStr(nisZxjmpgbReq.getJlxh())) {
    		nisZxjmpgbReq.setJlxh(redisFactory.getTableKey(TableName.DB_NAME, TableName.NIS_ZXJMPGB));
    		nisZxjmpgbSer.insert(nisZxjmpgbReq);
    	}else {
    		nisZxjmpgbSer.update(nisZxjmpgbReq);
    	}
        return ReturnEntityUtil.success();    
    }
   
    @RequestMapping("/deleteZxjmpgbByJlxh")
    @ResponseBody
    @ApiOperation(value="删除中心静脉导管相关性感染风险因素评估表" ,httpMethod="POST")
    public ReturnEntity deleteZxjmpgbByJlxh(@ApiParam(name = "jlxh", value = "记录序号", required = true)
    @RequestParam Integer jlxh){
    	nisZxjmpgbSer.removeById(jlxh);
        return ReturnEntityUtil.success();
    }
    
    @GetMapping("/centralVenousCatheterPrint")
    @ApiOperation(value="中心静脉导管相关性感染风险因素评估表打印")
    public void centralVenousCatheterPrint(NisHlQueryReq nisHlQueryReq,
    		HttpServletResponse response) {
    	nisHlQueryReq.setJgid(this.getUser().getHospitalId());
    	List<Map<String, Object>> list = nisZxjmpgbSer.getEntityMapper().queryPrintZxjmpgbByDate(nisHlQueryReq);
        String jasperName = "jrxml/CentralVenousCatheterPrint.jasper";
        nisHzmbSer.ComplementEmptyline(list, 18);
        iReportExportFileSer.reportHtmlForStream(list, null, jasperName, response);
    }
    
    @GetMapping("/centralVenousCatheterColumnHead")
    @ApiOperation(value="中心静脉导管相关性感染风险因素评估表列头打印")
    public void centralVenousCatheterColumnHead(HttpServletResponse response){
		String jasperName = "jrxml/CentralVenousCatheterColumnHead.jasper";
		iReportExportFileSer.reportHtmlForStream(null, jasperName, response);      
    }  
}

