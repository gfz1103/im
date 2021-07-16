
package com.buit.cis.nurse.controller;

import cn.hutool.core.util.StrUtil;
import com.buit.cis.nurse.model.NisCqhld;
import com.buit.cis.nurse.request.NisCqhldPrintReq;
import com.buit.cis.nurse.request.NisCqhldReq;
import com.buit.cis.nurse.service.NisCqhldSer;
import com.buit.commons.BaseSpringController;
import com.buit.constans.TableName;
import com.buit.system.service.ExportFileSer;
import com.buit.utill.BUHISUtil;
import com.buit.utill.RedisFactory;
import com.buit.utill.ReturnEntity;
import com.buit.utill.ReturnEntityUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.dubbo.config.annotation.DubboReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

/**
 * 产前护理记录单<br>
 * @author GONGFANGZHOU
 */
@Api(tags="产前护理记录单")
@Controller
@RequestMapping("/niscqhld")
public class NisCqhldCtr extends BaseSpringController{
    
    static final Logger logger = LoggerFactory.getLogger(NisCqhldCtr.class);
    
    @Autowired
    private NisCqhldSer nisCqhldSer;
    
    @Autowired
    private RedisFactory redisFactory;
    
    @DubboReference
    private ExportFileSer exportFileSer;
    
    @RequestMapping("/queryCqhldByDate")
    @ResponseBody
    @ApiOperation(value="根据日期查询产前护理记录单" ,httpMethod="POST")
    public ReturnEntity<List<NisCqhld>> queryCqhldByDate(@ApiParam(name = "zyh", value = "住院号", required = true)
    @RequestParam Integer zyh, @ApiParam(name = "queryDate", value = "查询时间(M-d)", required = false)
    @RequestParam(value="queryDate", required = false) String queryDate){
        return ReturnEntityUtil.success(nisCqhldSer.getEntityMapper().queryCqhldByDate(zyh, 
        		queryDate, this.getUser().getHospitalId()));
    }
    
    @RequestMapping("/saveCqhld")
    @ResponseBody
    @ApiOperation(value="新增修改保存产前护理记录单" ,httpMethod="POST")
    public ReturnEntity saveCqhld(NisCqhldReq nisCqhldReq){
    	nisCqhldReq.setJgid(this.getUser().getHospitalId());
    	if(StrUtil.isBlankIfStr(nisCqhldReq.getJlxh())) {
    		nisCqhldReq.setJlxh(redisFactory.getTableKey(TableName.DB_NAME, TableName.NIS_CQHLD));
    		nisCqhldSer.insert(nisCqhldReq);
    	}else {
    		nisCqhldSer.update(nisCqhldReq);
    	}
        return ReturnEntityUtil.success();    
    }
    
    @RequestMapping("/deleteCqhldByJlxh")
    @ResponseBody
    @ApiOperation(value="删除产前护理记录单" ,httpMethod="POST")
    public ReturnEntity deleteCqhldByJlxh(@ApiParam(name = "jlxh", value = "记录序号", required = true)
    @RequestParam Integer jlxh){
    	nisCqhldSer.removeById(jlxh);
        return ReturnEntityUtil.success();
    }
   
    @RequestMapping("/prenatalCareRecordPrint")
    @ResponseBody
    @ApiOperation(value="产前护理记录单打印" ,httpMethod="POST")
    public ReturnEntity<String> prenatalCareRecordPrint(@RequestBody List<NisCqhldPrintReq> list) {
        List<Map<String, Object>> resList = BUHISUtil.ListObjToMap(list);
        String jasperName = "PrenatalCareRecordPrint.jasper";
        return ReturnEntityUtil.success(exportFileSer.reportHtml(resList, null, jasperName));
    }
    
    @RequestMapping("/prenatalCareRecordColumnHead")
    @ResponseBody
    @ApiOperation(value="产前护理记录单列头打印" ,httpMethod="POST")
    public ReturnEntity<String> prenatalCareRecordColumnHead(){
		String jasperName = "PrenatalCareRecordColumnHead.jasper";
        return ReturnEntityUtil.success(exportFileSer.reportHtml(null, null, jasperName));    
    }  

//    /** 删除 */
//    @RequestMapping("/delete")
//    @ResponseBody
//    @ApiOperation(value="按条件查询" ,httpMethod="POST")
//    public ReturnEntity<NisCqhldResp> delete(NisCqhldReq nisCqhld) {
//        nisCqhldSer.removeByEntity(nisCqhld);        
//        return ReturnEntityUtil.success(nisCqhld);            
//    }
    
}

