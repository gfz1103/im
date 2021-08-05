
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

import com.buit.cis.nurse.model.NisZspdl;
import com.buit.cis.nurse.request.NisHlQueryReq;
import com.buit.cis.nurse.request.NisZspdlReq;
import com.buit.cis.nurse.response.NisZspdlResp;
import com.buit.cis.nurse.service.NisHzmbSer;
import com.buit.cis.nurse.service.NisZspdlSer;
import com.buit.commons.BaseSpringController;
import com.buit.constans.TableName;
import com.buit.file.IReportExportFileSer;
import com.buit.utill.RedisFactory;
import com.buit.utill.ReturnEntity;
import com.buit.utill.ReturnEntityUtil;

import cn.hutool.core.util.StrUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * Barthel指数平定量表<br>
 * @author GONGFANGZHOU
 */
@Api(tags="Barthel指数平定量表")
@Controller
@RequestMapping("/niszspdl")
public class NisZspdlCtr extends BaseSpringController{
    
    static final Logger logger = LoggerFactory.getLogger(NisZspdlCtr.class);
    
    @Autowired
    private NisZspdlSer nisZspdlSer;
    
    @Autowired
    private RedisFactory redisFactory;
    
    @Autowired
    private IReportExportFileSer iReportExportFileSer;
    
    @Autowired
    private NisHzmbSer nisHzmbSer;
    
    @RequestMapping("/queryZspdlByDate")
    @ResponseBody
    @ApiOperation(value="根据日期查询Barthel指数评定量表" ,httpMethod="POST")
    public ReturnEntity<List<NisZspdlResp>> queryZspdlByDate(NisHlQueryReq nisHlQueryReq){
    	nisHlQueryReq.setJgid(this.getUser().getHospitalId());
        return ReturnEntityUtil.success(nisZspdlSer.getEntityMapper().queryZspdlByDate(nisHlQueryReq));
    }
    
    @RequestMapping("/saveZspdl")
    @ResponseBody
    @ApiOperation(value="新增修改保存Barthel指数评定量表" ,httpMethod="POST")
    public ReturnEntity<Integer> saveZspdl(NisZspdlReq nisZspdlReq){
    	nisZspdlReq.setJgid(this.getUser().getHospitalId());
    	Integer jlxh = null;
    	if(StrUtil.isBlankIfStr(nisZspdlReq.getJlxh())) {
    		jlxh = redisFactory.getTableKey(TableName.DB_NAME, TableName.NIS_ZSPDL);
    		nisZspdlReq.setJlxh(jlxh);
    		nisZspdlSer.insert(nisZspdlReq);
    	}else {
    		jlxh = nisZspdlReq.getJlxh();
    		nisZspdlSer.update(nisZspdlReq);
    	}
        return ReturnEntityUtil.success(jlxh);                                                                                      
    }
 
    @RequestMapping("/deleteZspdlByJlxh")
    @ResponseBody
    @ApiOperation(value="删除Barthel指数评定量表" ,httpMethod="POST")
    public ReturnEntity deleteZspdlByJlxh(@ApiParam(name = "jlxh", value = "记录序号", required = true)
    @RequestParam Integer jlxh){
    	nisZspdlSer.removeById(jlxh);
        return ReturnEntityUtil.success();
    }
    
    @GetMapping("/indexRatingScalePrint")
    @ApiOperation(value="Barthel指数评定量表打印")
    public void indexRatingScalePrint(NisHlQueryReq nisHlQueryReq,
    		HttpServletResponse response){
    	nisHlQueryReq.setJgid(this.getUser().getHospitalId());
    	List<Map<String, Object>> list = nisZspdlSer.getEntityMapper().queryPrintZspdlByDate(nisHlQueryReq);
        String jasperName = "jrxml/IndexRatingScalePrint.jasper";
        nisHzmbSer.ComplementEmptyline(list, 18);
        iReportExportFileSer.reportHtmlForStream(list, null, jasperName, response);
    }
    
    @GetMapping("/indexRatingScaleColumnHead")
    @ApiOperation(value="Barthel指数评定量表列头打印")
    public void indexRatingScaleColumnHead(HttpServletResponse response){
		String jasperName = "jrxml/IndexRatingScaleColumnHead.jasper";
		iReportExportFileSer.reportHtmlForStream(null, jasperName, response);  
    }
    
    @RequestMapping("/queryZspdlByJlxh")
    @ResponseBody
    @ApiOperation(value="查询Barthel指数评定量表" ,httpMethod="POST")
    public ReturnEntity<NisZspdl> queryZspdlByJlxh(@ApiParam(name = "jlxh", value = "记录序号", required = true)
    @RequestParam Integer jlxh){
        return ReturnEntityUtil.success(nisZspdlSer.getById(jlxh));
    }
    
}

