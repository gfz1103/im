
package com.buit.cis.nurse.controller;

import cn.hutool.core.util.StrUtil;
import com.buit.cis.nurse.model.NisHljl;
import com.buit.cis.nurse.request.NisHljlPrintReq;
import com.buit.cis.nurse.request.NisHljlReq;
import com.buit.cis.nurse.service.NisHljlSer;
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
 * 一般护理记录单<br>
 * @author GONGFANGZHOU
 */
@Api(tags="一般护理记录单")
@Controller
@RequestMapping("/nishljl")
public class NisHljlCtr extends BaseSpringController{
    
    static final Logger logger = LoggerFactory.getLogger(NisHljlCtr.class);
    
    @Autowired
    private NisHljlSer nisHljlSer;
    
    @Autowired
    private RedisFactory redisFactory;
    
    @DubboReference
    private ExportFileSer exportFileSer;
    
    @RequestMapping("/queryHljlByDate")
    @ResponseBody
    @ApiOperation(value="根据日期查询一般护理记录单" ,httpMethod="POST")
    public ReturnEntity<List<NisHljl>> queryHljlByDate(@ApiParam(name = "zyh", value = "住院号", required = true)
    @RequestParam Integer zyh, @ApiParam(name = "queryDate", value = "查询时间(M-d)", required = false)
    @RequestParam(value="queryDate", required = false) String queryDate){
        return ReturnEntityUtil.success(nisHljlSer.getEntityMapper().queryHljlByDate(zyh, 
        		queryDate, this.getUser().getHospitalId()));
    }
    
    @RequestMapping("/saveHljl")
    @ResponseBody
    @ApiOperation(value="新增修改保存一般护理记录单" ,httpMethod="POST")
    public ReturnEntity saveHljl(NisHljlReq nisHljlReq){
    	nisHljlReq.setJgid(this.getUser().getHospitalId());
    	if(StrUtil.isBlankIfStr(nisHljlReq.getJlxh())) {
    		nisHljlReq.setJlxh(redisFactory.getTableKey(TableName.DB_NAME, TableName.NIS_HLJL));
    		nisHljlSer.insert(nisHljlReq);
    	}else {
    		nisHljlSer.update(nisHljlReq);
    	}
        return ReturnEntityUtil.success();    
    }
    
    @RequestMapping("/deleteByJlxh")
    @ResponseBody
    @ApiOperation(value="删除一般护理记录单" ,httpMethod="POST")
    public ReturnEntity deleteByJlxh(@ApiParam(name = "jlxh", value = "记录序号", required = true)
    @RequestParam Integer jlxh){
    	nisHljlSer.removeById(jlxh);
        return ReturnEntityUtil.success();
    }
    
    @RequestMapping("/nursingPrint")
    @ResponseBody
    @ApiOperation(value="一般护理记录单打印" ,httpMethod="POST")
    public ReturnEntity<String> nursingPrint(@RequestBody List<NisHljlPrintReq> list) {
        List<Map<String, Object>> resList = BUHISUtil.ListObjToMap(list);
        String jasperName = "NursingRecordPrint.jasper";
        return ReturnEntityUtil.success(exportFileSer.reportHtml(resList, null, jasperName));
    }
    
    @RequestMapping("/nursingPrintColumnHead")
    @ResponseBody
    @ApiOperation(value="一般护理记录单列头打印" ,httpMethod="POST")
    public ReturnEntity<String> nursingPrintColumnHead(){
		String jasperName = "NursingRecordPrintColumnHead.jasper";
        return ReturnEntityUtil.success(exportFileSer.reportHtml(null, null, jasperName));    
    }  

//    /** 编辑 */
//    @RequestMapping("/edit")
//    @ResponseBody
//    @ApiOperation(value="编辑" ,httpMethod="POST")
//    public ReturnEntity<NisHljlResp> edit(NisHljlReq nisHljl)  {
//        nisHljlSer.update(nisHljl);        
//        return ReturnEntityUtil.success(nisHljl);            
//    }
//    
//    /** 删除 */
//    @RequestMapping("/delete")
//    @ResponseBody
//    @ApiOperation(value="按条件查询" ,httpMethod="POST")
//    public ReturnEntity<NisHljlResp> delete(NisHljlReq nisHljl) {
//        nisHljlSer.removeByEntity(nisHljl);        
//        return ReturnEntityUtil.success(nisHljl);            
//    }
    
}

