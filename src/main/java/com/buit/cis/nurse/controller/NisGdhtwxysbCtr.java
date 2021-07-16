
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

import com.buit.cis.nurse.model.NisGdhtwxysb;
import com.buit.cis.nurse.request.NisGdhtwxysbReq;
import com.buit.cis.nurse.response.NisGdhtwxysbResp;
import com.buit.cis.nurse.service.NisGdhtwxysbSer;
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
 * 住院患者管道滑脱危险因素评估表<br>
 * @author GONGFANGZHOU
 */
@Api(tags="住院患者管道滑脱危险因素评估表")
@Controller
@RequestMapping("/nisgdhtwxysb")
public class NisGdhtwxysbCtr extends BaseSpringController{
    
    static final Logger logger = LoggerFactory.getLogger(NisGdhtwxysbCtr.class);
    
    @Autowired
    private NisGdhtwxysbSer nisGdhtwxysbSer;
    
    @Autowired
    private RedisFactory redisFactory;
    
    @Autowired
    private IReportExportFileSer iReportExportFileSer;
    
    @Autowired
    private NisHzmbSer nisHzmbSer;
    
    @RequestMapping("/queryGdhtwByDate")
    @ResponseBody
    @ApiOperation(value="根据日期查询道滑脱危险因素表" ,httpMethod="POST")
    public ReturnEntity<List<NisGdhtwxysbResp>> queryGdhtwByDate(@ApiParam(name = "zyh", value = "住院号", required = true)
    @RequestParam Integer zyh, @ApiParam(name = "queryDate", value = "查询时间(M-d)", required = false)
    @RequestParam(value="queryDate", required = false) String queryDate){
        return ReturnEntityUtil.success(nisGdhtwxysbSer.getEntityMapper().queryGdhtwByDate(zyh, 
        		queryDate, this.getUser().getHospitalId()));
    }
    
    @RequestMapping("/saveGdhtw")
    @ResponseBody
    @ApiOperation(value="新增修改保存管道滑脱危险因素表" ,httpMethod="POST")
    public ReturnEntity<Integer> saveGdhtw(NisGdhtwxysbReq nisGdhtwxysbReq){
    	nisGdhtwxysbReq.setJgid(this.getUser().getHospitalId());
    	Integer jlxh = null;
    	if(StrUtil.isBlankIfStr(nisGdhtwxysbReq.getJlxh())) {
    		jlxh = redisFactory.getTableKey(TableName.DB_NAME, TableName.NIS_GDHTWXYSB);
    		nisGdhtwxysbReq.setJlxh(jlxh);
    		nisGdhtwxysbSer.insert(nisGdhtwxysbReq);
    	}else {
    		jlxh = nisGdhtwxysbReq.getJlxh();
    		nisGdhtwxysbSer.update(nisGdhtwxysbReq);
    	}
        return ReturnEntityUtil.success(jlxh);    
    }
    
    @RequestMapping("/deleteGdhtwByJlxh")
    @ResponseBody
    @ApiOperation(value="删除管道滑脱危险因素表" ,httpMethod="POST")
    public ReturnEntity deleteGdhtwByJlxh(@ApiParam(name = "jlxh", value = "记录序号", required = true)
    @RequestParam Integer jlxh){
    	nisGdhtwxysbSer.removeById(jlxh);
        return ReturnEntityUtil.success();
    }
  
    @GetMapping("/pipeSlippagePrint")
    @ApiOperation(value="管道滑脱危险因素表打印")
    public void pipeSlippagePrint(@RequestParam(value="zyh") Integer zyh, @RequestParam(value="queryDate", required = false) String queryDate,
    		HttpServletResponse response){
    	List<Map<String, Object>> list = nisGdhtwxysbSer.getEntityMapper().queryPrintGdhtwByDate(zyh, 
        		queryDate, this.getUser().getHospitalId());
        String jasperName = "jrxml/PipeSlippagePrint.jasper";
        nisHzmbSer.ComplementEmptyline(list, 16);
        iReportExportFileSer.reportHtmlForStream(list, null, jasperName, response);
    }
    
    @GetMapping("/pipeSlippageColumnHead")
    @ApiOperation(value="管道滑脱危险因素表列头打印")
    public void pipeSlippageColumnHead(HttpServletResponse response){
		String jasperName = "jrxml/PipeSlippageColumnHead.jasper";
		iReportExportFileSer.reportHtmlForStream(null, jasperName, response);   
    }  
    
    @RequestMapping("/queryGdhtwByJlxh")
    @ResponseBody
    @ApiOperation(value="根据记录序号查询管道滑脱危险因素表" ,httpMethod="POST")
    public ReturnEntity<NisGdhtwxysb> queryGdhtwByJlxh(@ApiParam(name = "jlxh", value = "记录序号", required = true)
    @RequestParam Integer jlxh){
        return ReturnEntityUtil.success(nisGdhtwxysbSer.getById(jlxh));
    }
    
}

