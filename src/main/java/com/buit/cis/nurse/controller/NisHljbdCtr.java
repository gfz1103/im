
package com.buit.cis.nurse.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.buit.cis.nurse.model.NisHljbd;
import com.buit.cis.nurse.request.CisHzyzCardReq;
import com.buit.cis.nurse.request.NisHljbdQueryReq;
import com.buit.cis.nurse.request.NisHljbdReq;
import com.buit.cis.nurse.response.NisHljbdResp;
import com.buit.cis.nurse.service.NisHljbdSer;
import com.buit.commons.BaseSpringController;
import com.buit.commons.PageQuery;
import com.buit.file.IReportExportFileSer;
import com.buit.utill.ReturnEntity;
import com.buit.utill.ReturnEntityUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import cn.hutool.json.JSONUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * 护理交班单主表<br>
 * @author GONGFANGZHOU
 */
@Api(tags="护理交班单主表")
@Controller
@RequestMapping("/nishljbd")
public class NisHljbdCtr extends BaseSpringController{
    
    static final Logger logger = LoggerFactory.getLogger(NisHljbdCtr.class);
    
    @Autowired
    private NisHljbdSer nisHljbdSer;
    
    @Autowired
    private IReportExportFileSer iReportExportFileSer;
    
    @RequestMapping("/queryPageHljbd")
    @ResponseBody
    @ApiOperation(value="交班单-分页查询" ,httpMethod="POST")
    public ReturnEntity<PageInfo<NisHljbd>> queryPageHljbd(NisHljbdQueryReq nishljbd,PageQuery page){
    	nishljbd.setJgid(this.getUser().getHospitalId());
    	PageInfo<NisHljbd> pageInfo = PageHelper.startPage(
            page.getPageNum(), page.getPageSize()).doSelectPageInfo(
                    () -> nisHljbdSer.getEntityMapper().queryPageHljbd(nishljbd)
            );
        return ReturnEntityUtil.success(pageInfo);
    }
    
    @RequestMapping("/saveOrUpdateHljbd")
    @ResponseBody
    @ApiOperation(value="新增或修改护理交班单" ,httpMethod="POST")
    public ReturnEntity saveOrUpdateHljbd(NisHljbdReq nisHljbdReq){
    	nisHljbdSer.saveOrUpdateHljbd(nisHljbdReq, this.getUser().getHospitalId());
        return ReturnEntityUtil.success();    
    }
    
    @RequestMapping("/getByJlxh")
    @ResponseBody
    @ApiOperation(value="根据记录序号查询交班单信息" ,httpMethod="POST")
    public ReturnEntity<NisHljbdResp> getByJlxh(@ApiParam(name = "jlxh", value = "记录序号", required = true)
    @RequestParam Integer jlxh){
        return ReturnEntityUtil.success(nisHljbdSer.getByJlxh(jlxh, this.getUser().getHospitalId()));
    }
    
    /** 删除 */
    @RequestMapping("/deleteByJlxh")
    @ResponseBody
    @ApiOperation(value="根据记录序号删除交班单信息" ,httpMethod="POST")
    public ReturnEntity deleteByJlxh(@ApiParam(name = "jlxh", value = "记录序号", required = true)
    @RequestParam Integer jlxh) {
        nisHljbdSer.deleteByJlxh(jlxh);        
        return ReturnEntityUtil.success();            
    }
    
    @GetMapping("/nisJbdPrintFile")
	@ApiOperation(value="交班单打印")
	public void nisJbdPrintFile(@RequestParam("reqStr") String reqStr, HttpServletResponse response) throws Exception{
		Map<String, Object> map = nisHljbdSer.getParameters(reqStr);
		List<Map<String, Object>> list = nisHljbdSer.getFields(reqStr);
		String jasperName = "jrxml/ShiftHandoverForm.jasper";
		if(CollectionUtils.isNotEmpty(list)) {
			iReportExportFileSer.reportHtmlForStream(list, map, jasperName, response);
		}
	}

    
}

