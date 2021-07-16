
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

import com.buit.cis.nurse.model.NisDdzcpgb;
import com.buit.cis.nurse.request.NisDdzcpgbReq;
import com.buit.cis.nurse.response.NisDdzcpgbResp;
import com.buit.cis.nurse.service.NisDdzcpgbSer;
import com.buit.cis.nurse.service.NisHzmbSer;
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
 * 住院患者跌倒、坠床危险因素评估表<br>
 * @author GONGFANGZHOU
 */
@Api(tags="住院患者跌倒、坠床危险因素评估表")
@Controller
@RequestMapping("/nisddzcpgb")
public class NisDdzcpgbCtr extends BaseSpringController{
    
    static final Logger logger = LoggerFactory.getLogger(NisDdzcpgbCtr.class);
    
    @Autowired
    private NisDdzcpgbSer nisDdzcpgbSer;
    
    @Autowired
    private RedisFactory redisFactory;
    
    @Autowired
    private IReportExportFileSer iReportExportFileSer;
    
    @Autowired
    private NisHzmbSer nisHzmbSer;
    
    @RequestMapping("/queryDdzcpgbByDate")
    @ResponseBody
    @ApiOperation(value="根据日期查询跌倒、坠床危险因素评估表" ,httpMethod="POST")
    public ReturnEntity<List<NisDdzcpgbResp>> queryDdzcpgbByDate(@ApiParam(name = "zyh", value = "住院号", required = true)
    @RequestParam Integer zyh, @ApiParam(name = "queryDate", value = "查询时间(M-d)", required = false)
    @RequestParam(value="queryDate", required = false) String queryDate){
        return ReturnEntityUtil.success(nisDdzcpgbSer.getEntityMapper().queryDdzcpgbByDate(zyh, 
        		queryDate, this.getUser().getHospitalId()));
    }
    
    @RequestMapping("/saveDdzcpgb")
    @ResponseBody
    @ApiOperation(value="新增修改保存跌倒、坠床危险因素评估表" ,httpMethod="POST")
    public ReturnEntity<Integer> saveDdzcpgb(NisDdzcpgbReq nisDdzcpgbReq){
    	Integer jlxh = null;
    	nisDdzcpgbReq.setJgid(this.getUser().getHospitalId());
    	if(StrUtil.isBlankIfStr(nisDdzcpgbReq.getJlxh())) {
    		jlxh = redisFactory.getTableKey(TableName.DB_NAME, TableName.NIS_DDZCPGB);
    		nisDdzcpgbReq.setJlxh(jlxh);
    		nisDdzcpgbSer.insert(nisDdzcpgbReq);
    	}else {
    		jlxh = nisDdzcpgbReq.getJlxh();
    		nisDdzcpgbSer.update(nisDdzcpgbReq);
    	}
        return ReturnEntityUtil.success(jlxh);    
    }
   
    @RequestMapping("/deleteDdzcpgbByJlxh")
    @ResponseBody
    @ApiOperation(value="删除跌倒、坠床危险因素评估表" ,httpMethod="POST")
    public ReturnEntity deleteDdzcpgbByJlxh(@ApiParam(name = "jlxh", value = "记录序号", required = true)
    @RequestParam Integer jlxh){
    	nisDdzcpgbSer.removeById(jlxh);
        return ReturnEntityUtil.success();
    }
    
    @GetMapping("/fallingBedPrint")
    @ApiOperation(value="跌倒、坠床危险因素评估表打印")
    public void fallingBedPrint(@RequestParam(value="zyh") Integer zyh, @RequestParam(value="queryDate", required = false) String queryDate,
    		HttpServletResponse response){
    	List<Map<String, Object>> list = nisDdzcpgbSer.getEntityMapper().queryPrintDdzcpgbByDate(zyh, 
        		queryDate, this.getUser().getHospitalId());
        nisHzmbSer.ComplementEmptyline(list, 16);
        String jasperName = "jrxml/FallingBedPrint.jasper";
        iReportExportFileSer.reportHtmlForStream(list, null, jasperName, response);
    }
    
    @GetMapping("/fallingBedColumnHead")
    @ApiOperation(value="跌倒、坠床危险因素评估表列头打印")
    public void fallingBedColumnHead(HttpServletResponse response){
		String jasperName = "jrxml/FallingBedColumnHead.jasper";
		iReportExportFileSer.reportHtmlForStream(null, jasperName, response);  
    }  
    
    @RequestMapping("/queryDdzcpgbByJlxh")
    @ResponseBody
    @ApiOperation(value="根据记录序号查询删除跌倒、坠床危险因素评估表" ,httpMethod="POST")
    public ReturnEntity<NisDdzcpgb> queryDdzcpgbByJlxh(@ApiParam(name = "jlxh", value = "记录序号", required = true)
    @RequestParam Integer jlxh){
        return ReturnEntityUtil.success(nisDdzcpgbSer.getById(jlxh));
    }
    
}

