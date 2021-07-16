
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

import com.buit.cis.nurse.request.NisFyfxpgdReq;
import com.buit.cis.nurse.response.NisFyfxpgdResp;
import com.buit.cis.nurse.service.NisFyfxpgdSer;
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
 * <br>医院获得性肺炎风险因素评估单
 * @author GONGFANGZHOU
 */
@Api(tags="医院获得性肺炎风险因素评估单")
@Controller
@RequestMapping("/nisfyfxpgd")
public class NisFyfxpgdCtr extends BaseSpringController{
    
    static final Logger logger = LoggerFactory.getLogger(NisFyfxpgdCtr.class);
    
    @Autowired
    private NisFyfxpgdSer nisFyfxpgdSer;
    
    @Autowired
    private RedisFactory redisFactory;
    
    @Autowired
    private IReportExportFileSer iReportExportFileSer;
    
    @Autowired
    private NisHzmbSer nisHzmbSer;

    @RequestMapping("/queryFyfxpgdByDate")
    @ResponseBody
    @ApiOperation(value="根据日期查询医院获得性肺炎风险因素评估单" ,httpMethod="POST")
    public ReturnEntity<List<NisFyfxpgdResp>> queryFyfxpgdByDate(@ApiParam(name = "zyh", value = "住院号", required = true)
    @RequestParam Integer zyh, @ApiParam(name = "queryDate", value = "查询时间(M-d)", required = false)
    @RequestParam(value="queryDate", required = false) String queryDate){
        return ReturnEntityUtil.success(nisFyfxpgdSer.getEntityMapper().queryFyfxpgdByDate(zyh, 
        		queryDate, this.getUser().getHospitalId()));
    }
 
    @RequestMapping("/saveFyfxpgd")
    @ResponseBody
    @ApiOperation(value="新增修改保存医院获得性肺炎风险因素评估单" ,httpMethod="POST")
    public ReturnEntity saveFyfxpgd(NisFyfxpgdReq nisFyfxpgdReq){ 
    	nisFyfxpgdReq.setJgid(this.getUser().getHospitalId());
    	if(StrUtil.isBlankIfStr(nisFyfxpgdReq.getJlxh())) {
    		nisFyfxpgdReq.setJlxh(redisFactory.getTableKey(TableName.DB_NAME, TableName.NIS_FYFXPGD));
    		nisFyfxpgdSer.insert(nisFyfxpgdReq);
    	}else {
    		nisFyfxpgdSer.update(nisFyfxpgdReq);
    	}
        return ReturnEntityUtil.success();    
    }
    
    @RequestMapping("/deleteFyfxpgdByJlxh")
    @ResponseBody
    @ApiOperation(value="删除医院获得性肺炎风险因素评估单" ,httpMethod="POST")
    public ReturnEntity deleteFyfxpgdByJlxh(@ApiParam(name = "jlxh", value = "记录序号", required = true)
    @RequestParam Integer jlxh){
    	nisFyfxpgdSer.removeById(jlxh);
        return ReturnEntityUtil.success();
    }
 
    @GetMapping("/acquiredPneumoniaPrint")
    @ApiOperation(value="医院获得性肺炎风险因素评估单打印")
    public void acquiredPneumoniaPrint(@RequestParam(value="zyh") Integer zyh, @RequestParam(value="queryDate", required = false) String queryDate,
    		HttpServletResponse response){
    	List<Map<String, Object>> list = nisFyfxpgdSer.getEntityMapper().queryPrintFyfxpgdByDate(zyh, 
        		queryDate, this.getUser().getHospitalId());
        String jasperName = "jrxml/AcquiredPneumoniaPrint.jasper";
        nisHzmbSer.ComplementEmptyline(list, 17);
        iReportExportFileSer.reportHtmlForStream(list, null, jasperName, response);
    }
    
    @GetMapping("/acquiredPneumoniaColumnHead")
    @ApiOperation(value="医院获得性肺炎风险因素评估单列头打印")
    public void acquiredPneumoniaColumnHead(HttpServletResponse response){
		String jasperName = "jrxml/AcquiredPneumoniaColumnHead.jasper";
		iReportExportFileSer.reportHtmlForStream(null, jasperName, response);  
    }  
   
    
}

