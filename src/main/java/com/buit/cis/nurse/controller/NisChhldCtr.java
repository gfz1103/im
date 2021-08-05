
package com.buit.cis.nurse.controller;

import cn.hutool.core.util.StrUtil;
import com.buit.cis.nurse.model.NisChhld;
import com.buit.cis.nurse.request.NisChhldPrintReq;
import com.buit.cis.nurse.request.NisChhldReq;
import com.buit.cis.nurse.request.NisHlQueryReq;
import com.buit.cis.nurse.service.NisChhldSer;
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
 * 产后护理记录单<br>
 * @author GONGFANGZHOU
 */
@Api(tags="产后护理记录单")
@Controller
@RequestMapping("/nischhld")
public class NisChhldCtr extends BaseSpringController{
    
    static final Logger logger = LoggerFactory.getLogger(NisChhldCtr.class);
    
    @Autowired
    private NisChhldSer nisChhldSer;
    
    @Autowired
    private RedisFactory redisFactory;
    
    @DubboReference
    private ExportFileSer exportFileSer;
    
    @RequestMapping("/queryChhldByDate")
    @ResponseBody
    @ApiOperation(value="根据日期查询产后护理记录单" ,httpMethod="POST")
    public ReturnEntity<List<NisChhld>> queryChhldByDate(NisHlQueryReq nisHlQueryReq){
    	nisHlQueryReq.setJgid(this.getUser().getHospitalId());
        return ReturnEntityUtil.success(nisChhldSer.getEntityMapper().queryChhldByDate(nisHlQueryReq));
    }

    @RequestMapping("/saveChhld")
    @ResponseBody
    @ApiOperation(value="新增修改保存产后护理记录单" ,httpMethod="POST")
    public ReturnEntity saveChhld(NisChhldReq nisChhldReq){
    	nisChhldReq.setJgid(this.getUser().getHospitalId());
    	if(StrUtil.isBlankIfStr(nisChhldReq.getJlxh())) {
    		nisChhldReq.setJlxh(redisFactory.getTableKey(TableName.DB_NAME, TableName.NIS_CHHLD));
    		nisChhldSer.insert(nisChhldReq);
    	}else {
    		nisChhldSer.update(nisChhldReq);
    	}
        return ReturnEntityUtil.success();    
    }
  
    @RequestMapping("/deleteChhldByJlxh")
    @ResponseBody
    @ApiOperation(value="删除产后护理记录单" ,httpMethod="POST")
    public ReturnEntity deleteChhldByJlxh(@ApiParam(name = "jlxh", value = "记录序号", required = true)
    @RequestParam Integer jlxh){
    	nisChhldSer.removeById(jlxh);
        return ReturnEntityUtil.success();
    }
   
    @RequestMapping("/postpartumNursingRecordPrint")
    @ResponseBody
    @ApiOperation(value="产后护理记录单打印" ,httpMethod="POST")
    public ReturnEntity<String> postpartumNursingRecordPrint(@RequestBody List<NisChhldPrintReq> list) {
        List<Map<String, Object>> resList = BUHISUtil.ListObjToMap(list);
        String jasperName = "PostpartumNursingRecordPrint.jasper";
        return ReturnEntityUtil.success(exportFileSer.reportHtml(resList, null, jasperName));
    }
    
    @RequestMapping("/postpartumNursingRecordColumnHead")
    @ResponseBody
    @ApiOperation(value="产后护理记录单列头打印" ,httpMethod="POST")
    public ReturnEntity<String> postpartumNursingRecordColumnHead(){
		String jasperName = "PostpartumNursingRecordColumnHead.jasper";
        return ReturnEntityUtil.success(exportFileSer.reportHtml(null, null, jasperName));    
    }  
    
//    /** 删除 */
//    @RequestMapping("/delete")
//    @ResponseBody
//    @ApiOperation(value="按条件查询" ,httpMethod="POST")
//    public ReturnEntity<NisChhldResp> delete(NisChhldReq nisChhld) {
//        nisChhldSer.removeByEntity(nisChhld);        
//        return ReturnEntityUtil.success(nisChhld);            
//    }
    
}

