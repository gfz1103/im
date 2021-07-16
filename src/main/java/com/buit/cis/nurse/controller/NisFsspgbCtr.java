
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

import com.buit.cis.nurse.request.NisFsspgbReq;
import com.buit.cis.nurse.response.NisFsspgbResp;
import com.buit.cis.nurse.service.NisFsspgbSer;
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
 * 深静脉血栓风险评估表<br>
 * @author GONGFANGZHOU
 */
@Api(tags="深静脉血栓风险评估表")
@Controller
@RequestMapping("/nisfsspgb")
public class NisFsspgbCtr extends BaseSpringController{
    
    static final Logger logger = LoggerFactory.getLogger(NisFsspgbCtr.class);
    
    @Autowired
    private NisFsspgbSer nisFsspgbSer;
    
    @Autowired
    private RedisFactory redisFactory;
    
    @Autowired
    private IReportExportFileSer iReportExportFileSer;
    
    @Autowired
    private NisHzmbSer nisHzmbSer;
    
    
    @RequestMapping("/queryFsspgbByDate")
    @ResponseBody
    @ApiOperation(value="根据日期查询深静脉血栓风险评估表" ,httpMethod="POST")
    public ReturnEntity<List<NisFsspgbResp>> queryFsspgbByDate(@ApiParam(name = "zyh", value = "住院号", required = true)
    @RequestParam Integer zyh, @ApiParam(name = "queryDate", value = "查询时间(M-d)", required = false)
    @RequestParam(value="queryDate", required = false) String queryDate, @ApiParam(name = "mblx", value = "模板类型", required = true)
    @RequestParam String mblx){
        return ReturnEntityUtil.success(nisFsspgbSer.getEntityMapper().queryFsspgbByDate(zyh, 
        		queryDate, this.getUser().getHospitalId(), mblx));
    }
  
    @RequestMapping("/saveFsspgb")
    @ResponseBody
    @ApiOperation(value="新增修改保存深静脉血栓风险评估表" ,httpMethod="POST")
    public ReturnEntity saveFsspgb(NisFsspgbReq nisFsspgbReq){
    	nisFsspgbReq.setJgid(this.getUser().getHospitalId());
    	if(StrUtil.isBlankIfStr(nisFsspgbReq.getJlxh())) {
    		nisFsspgbReq.setJlxh(redisFactory.getTableKey(TableName.DB_NAME, TableName.NIS_FSSPGB));
    		nisFsspgbSer.insert(nisFsspgbReq);
    	}else {
    		nisFsspgbSer.update(nisFsspgbReq);
    	}
        return ReturnEntityUtil.success();    
    }
   
    @RequestMapping("/deleteFsspgbByJlxh")
    @ResponseBody
    @ApiOperation(value="删除深静脉血栓风险评估表" ,httpMethod="POST")
    public ReturnEntity deleteFsspgbByJlxh(@ApiParam(name = "jlxh", value = "记录序号", required = true)
    @RequestParam Integer jlxh){
    	nisFsspgbSer.removeById(jlxh);
        return ReturnEntityUtil.success();
    }
    
    @GetMapping("/deepVeinThrombosisPrint")
    @ApiOperation(value="深静脉血栓风险评估表打印")
    public void deepVeinThrombosisPrint(@RequestParam(value="zyh") Integer zyh, @RequestParam(value="queryDate", required = false) String queryDate,
    		@RequestParam(value="mblx") String mblx, HttpServletResponse response){
    	List<Map<String, Object>> list = nisFsspgbSer.getEntityMapper().queryPrintFsspgbByDate(zyh, 
        		queryDate, this.getUser().getHospitalId(), mblx);
        String jasperName = "jrxml/DeepVeinThrombosisPrint.jasper";
        nisHzmbSer.ComplementEmptyline(list, 16);
        iReportExportFileSer.reportHtmlForStream(list, null, jasperName, response);
    }
    
    @GetMapping("/deepVeinThrombosisColumnHead")
    @ApiOperation(value="深静脉血栓风险评估表列头打印")
    public void deepVeinThrombosisColumnHead(HttpServletResponse response){
		String jasperName = "jrxml/DeepVeinThrombosisColumnHead.jasper";
		iReportExportFileSer.reportHtmlForStream(null, jasperName, response);  
    }  
    
}

