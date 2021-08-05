
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

import com.buit.cis.nurse.request.NisFssriskReq;
import com.buit.cis.nurse.request.NisHlQueryReq;
import com.buit.cis.nurse.response.NisFssriskResp;
import com.buit.cis.nurse.service.NisFssriskSer;
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
 * 肺栓塞风险因素评估表<br>
 * @author GONGFANGZHOU
 */
@Api(tags="肺栓塞风险因素评估表")
@Controller
@RequestMapping("/nisfssrisk")
public class NisFssriskCtr extends BaseSpringController{
    
    static final Logger logger = LoggerFactory.getLogger(NisFssriskCtr.class);
    
    @Autowired
    private NisFssriskSer nisFssriskSer;
    
    @Autowired
    private RedisFactory redisFactory;
    
    @Autowired
    private IReportExportFileSer iReportExportFileSer;
    
    @Autowired
    private NisHzmbSer nisHzmbSer;
    
    @RequestMapping("/queryFssriskByDate")
    @ResponseBody
    @ApiOperation(value="根据日期查询肺栓塞风险因素评估表" ,httpMethod="POST")
    public ReturnEntity<List<NisFssriskResp>> queryFssriskByDate(NisHlQueryReq nisHlQueryReq){
    	nisHlQueryReq.setJgid(this.getUser().getHospitalId());
        return ReturnEntityUtil.success(nisFssriskSer.getEntityMapper().queryFssriskByDate(nisHlQueryReq));
    }
  
    @RequestMapping("/saveFssrisk")
    @ResponseBody
    @ApiOperation(value="新增修改保存肺栓塞风险因素评估表" ,httpMethod="POST")
    public ReturnEntity saveFssrisk(NisFssriskReq nisFssriskReq){
    	nisFssriskReq.setJgid(this.getUser().getHospitalId());
    	if(StrUtil.isBlankIfStr(nisFssriskReq.getJlxh())) {
    		nisFssriskReq.setJlxh(redisFactory.getTableKey(TableName.DB_NAME, TableName.NIS_FSSRISK));
    		nisFssriskSer.insert(nisFssriskReq);
    	}else {
    		nisFssriskSer.update(nisFssriskReq);
    	}
        return ReturnEntityUtil.success();    
    }
   
    @RequestMapping("/deleteFssriskByJlxh")
    @ResponseBody
    @ApiOperation(value="删除肺栓塞风险因素评估表" ,httpMethod="POST")
    public ReturnEntity deleteFssriskByJlxh(@ApiParam(name = "jlxh", value = "记录序号", required = true)
    @RequestParam Integer jlxh){
    	nisFssriskSer.removeById(jlxh);
        return ReturnEntityUtil.success();
    }
    
    @GetMapping("/pulmonaryEmbolismPrint")
    @ApiOperation(value="肺栓塞风险因素评估表打印")
    public void pulmonaryEmbolismPrint(NisHlQueryReq nisHlQueryReq,
    		HttpServletResponse response){
    	nisHlQueryReq.setJgid(this.getUser().getHospitalId());
    	List<Map<String, Object>> list = nisFssriskSer.getEntityMapper().queryPrintFssriskByDate(nisHlQueryReq);
        String jasperName = "jrxml/PulmonaryEmbolismPrint.jasper";
        nisHzmbSer.ComplementEmptyline(list, 18);
        iReportExportFileSer.reportHtmlForStream(list, null, jasperName, response);
    }
    
    @GetMapping("/pulmonaryEmbolismColumnHead")
    @ApiOperation(value="肺栓塞风险因素评估表列头打印")
    public void pulmonaryEmbolismColumnHead(HttpServletResponse response){
		String jasperName = "jrxml/PulmonaryEmbolismColumnHead.jasper";
		iReportExportFileSer.reportHtmlForStream(null, jasperName, response);  
    }  
    
}

