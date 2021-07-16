
package com.buit.cis.dctwork.controller;

import java.sql.Timestamp;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.buit.cis.dctwork.request.CisBxsqdLeadBloodReq;
import com.buit.cis.dctwork.request.CisBxsqdQueryReq;
import com.buit.cis.dctwork.request.CisBxsqdReq;
import com.buit.cis.dctwork.response.CisBxsqdLeadBloodResp;
import com.buit.cis.dctwork.response.CisBxsqdQueryResp;
import com.buit.cis.dctwork.response.CisBxsqdResp;
import com.buit.cis.dctwork.service.CisBxsqdSer;
import com.buit.commons.BaseSpringController;
import com.buit.commons.PageQuery;
import com.buit.file.IReportExportFileSer;
import com.buit.system.utill.ObjectToTypes;
import com.buit.utill.ReturnEntity;
import com.buit.utill.ReturnEntityUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import cn.hutool.core.date.DateUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * 备血申请单<br>
 * @author GONGFANGZHOU
 */
@Api(tags="备血申请单")
@Controller
@RequestMapping("/cisbxsqd")
public class CisBxsqdCtr extends BaseSpringController{
    
    static final Logger logger = LoggerFactory.getLogger(CisBxsqdCtr.class);
    
    @Autowired
    private CisBxsqdSer cisBxsqdSer;
    
    @Autowired
    private IReportExportFileSer iReportExportFileSer;
    
    @RequestMapping("/saveZySxSqd")
    @ResponseBody
    @ApiOperation(value="保存备血申请单" ,httpMethod="POST")
    public ReturnEntity<String> saveZySxSqd(@RequestBody CisBxsqdReq cisBxsqdReq){
        return ReturnEntityUtil.success(cisBxsqdSer.saveZySxSqd(cisBxsqdReq, this.getUser()));
    }
   
    @RequestMapping("/queryZyBloodList")
    @ResponseBody
    @ApiOperation(value="备血申请单查询-分页" ,httpMethod="POST")
    public ReturnEntity<PageInfo<CisBxsqdResp>> queryZyBloodList(CisBxsqdQueryReq cisBxsqdQueryReq, PageQuery page){
    	cisBxsqdQueryReq.setYljgd(ObjectToTypes.parseString(this.getUser().getHospitalId()));
    	PageInfo<CisBxsqdResp> pageInfo = PageHelper.startPage(
              page.getPageNum(), page.getPageSize()).doSelectPageInfo(
                      () -> cisBxsqdSer.getEntityMapper().queryZyBloodList(cisBxsqdQueryReq)
              );
        return ReturnEntityUtil.success(pageInfo);    
    }
    
    @RequestMapping("/queryZySxSqdInfo")
    @ResponseBody
    @ApiOperation(value="查询备血申请单信息" ,httpMethod="POST")
    public ReturnEntity<CisBxsqdQueryResp> queryZySxSqdInfo(@ApiParam(name = "zyh", value = "住院号", required = true) 
	@RequestParam Integer zyh, @ApiParam(name = "sqid", value = "申请id", required = true) 
	@RequestParam Integer sqid){
        return ReturnEntityUtil.success(cisBxsqdSer.queryZySxSqdInfo(zyh, sqid, this.getUser()));
    }
    
    @GetMapping("/booldPrintFile")
	@ApiOperation(value="备血申请单打印")
	public void booldPrintFile(@RequestParam("sqdh") String sqdh, HttpServletResponse response){
		String jasperName = "jrxml/PrepareBloodForm.jasper";
		Map<String, Object> map = cisBxsqdSer.getEntityMapper().queryBloodPrint(sqdh, 
				this.getUser().getHospitalId());
		map.put("TITLE", this.getUser().getHospitalName() + "临床输血申请单");
		iReportExportFileSer.reportHtmlForStream(map, 
				jasperName, response);
	}
    
    @RequestMapping("/queryLeadBloodList")
    @ResponseBody
    @ApiOperation(value="领血申请单查询-分页" ,httpMethod="POST")
    public ReturnEntity<PageInfo<CisBxsqdLeadBloodResp>> queryLeadBloodList(CisBxsqdLeadBloodReq cisBxsqdLeadBloodReq){
    	cisBxsqdLeadBloodReq.setYljgd(ObjectToTypes.parseString(this.getUser().getHospitalId()));
    	PageInfo<CisBxsqdLeadBloodResp> pageInfo = PageHelper.startPage(
    			cisBxsqdLeadBloodReq.getPageNum(), cisBxsqdLeadBloodReq.getPageSize()).doSelectPageInfo(
                      () -> cisBxsqdSer.getEntityMapper().queryLeadBloodList(cisBxsqdLeadBloodReq)
              );
        return ReturnEntityUtil.success(pageInfo);
    }
    
    @RequestMapping("/updateLeadInfo")
    @ResponseBody
    @ApiOperation(value="更新领用信息" ,httpMethod="POST")
    public ReturnEntity updateLeadInfo(@ApiParam(name = "sqdh", value = "申请单号", required = true) 
	@RequestParam String sqdh, @ApiParam(name = "lxrdm", value = "领血人代码", required = true) 
	@RequestParam String lxrdm, @ApiParam(name = "lxsj", value = "领血时间", required = true) 
	@RequestParam Timestamp lxsj){
    	cisBxsqdSer.getEntityMapper().updateLeadInfo(sqdh, lxsj, lxrdm, 
    			ObjectToTypes.parseString(this.getUser().getUserId()), 
    			DateUtil.date().toTimestamp());
        return ReturnEntityUtil.success();
    }
    
    
}

