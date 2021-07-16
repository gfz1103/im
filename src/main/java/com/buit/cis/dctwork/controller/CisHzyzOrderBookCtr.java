
package com.buit.cis.dctwork.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.CollectionUtils;
import org.apache.dubbo.config.annotation.DubboReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.buit.cis.dctwork.request.CisHzyzBookPrintReq;
import com.buit.cis.dctwork.response.CisHzyzBookPrintResp;
import com.buit.cis.dctwork.response.CisHzyzOrderBookResp;
import com.buit.cis.dctwork.response.CisHzyzPrintRecordResp;
import com.buit.cis.dctwork.service.CisHzyzOrderBookSer;
import com.buit.cis.ims.model.ImHzry;
import com.buit.cis.ims.request.ImHzryMedicalBookReq;
import com.buit.cis.ims.service.ImHzrySer;
import com.buit.commons.BaseSpringController;
import com.buit.constans.SysXtcsCsmcCts;
import com.buit.file.IReportExportFileSer;
import com.buit.system.response.SysXtcs;
import com.buit.system.service.SysXtcsCacheSer;
import com.buit.utill.ReturnEntity;
import com.buit.utill.ReturnEntityUtil;

import cn.hutool.json.JSONUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * 医嘱本打印<br>
 * @author GONGFANGZHOU
 */
@Api(tags="医嘱本打印")
@Controller
@RequestMapping("/cisHzyzOrderBook")
public class CisHzyzOrderBookCtr extends BaseSpringController{
    
    static final Logger logger = LoggerFactory.getLogger(CisHzyzOrderBookCtr.class);
    
    @Autowired
    private CisHzyzOrderBookSer cisHzyzOrderBookSer;
   
    @Autowired
    private ImHzrySer imHzrySer;
    
    @DubboReference
    private SysXtcsCacheSer sysXtcsCacheSer;
    
    @Autowired
    private IReportExportFileSer iReportExportFileSer;
	
	@RequestMapping("/queryMedicalBookInfo")
    @ResponseBody
    @ApiOperation(value="医嘱本打印病人信息" ,httpMethod="POST")
    public ReturnEntity<List<ImHzry>> queryMedicalBookInfo(ImHzryMedicalBookReq imHzryMedicalBookReq){
		imHzryMedicalBookReq.setJgid(this.getUser().getHospitalId());
        return ReturnEntityUtil.success(imHzrySer.getEntityMapper().queryMedicalBookInfo(imHzryMedicalBookReq));    
    }
	
	@RequestMapping("/queryYz")
    @ResponseBody
    @ApiOperation(value="医嘱本打印病人医嘱信息" ,httpMethod="POST")
    public ReturnEntity<List<CisHzyzOrderBookResp>> queryYz(@ApiParam(name = "zyh", value = "住院号", required = true)
    @RequestParam Integer zyh, @ApiParam(name = "yzzt", value = "医嘱状态(1:开嘱2:停嘱)", required = true)
    @RequestParam Integer yzzt, @ApiParam(name = "yzlx", value = "医嘱类型(0:长期1:临时)", required = true)
    @RequestParam Integer yzlx){
        return ReturnEntityUtil.success(cisHzyzOrderBookSer.queryYz(zyh, yzzt, yzlx, this.getUser()));    
    }
	
	@RequestMapping("/saveWardDoctorPrint")
    @ResponseBody
    @ApiOperation(value="医嘱本打印保存" ,httpMethod="POST")
    public ReturnEntity<CisHzyzBookPrintResp> saveWardDoctorPrint(CisHzyzBookPrintReq cisHzyzBookPrintReq) throws Exception{
        return ReturnEntityUtil.success(cisHzyzOrderBookSer.saveWardDoctorPrint(cisHzyzBookPrintReq, this.getUser()));    
    }
	
	@GetMapping("/wardDoctorPrintFile")
    @ApiOperation(value="医嘱本打印")
    public void wardDoctorPrintFile(@RequestParam("reqStr") String reqStr, HttpServletResponse response){
		CisHzyzBookPrintReq cisHzyzBookPrintReq = JSONUtil.toBean(reqStr, CisHzyzBookPrintReq.class);
		Map<String, Object> map = cisHzyzOrderBookSer.getWardDoctorPrintParameters(cisHzyzBookPrintReq, this.getUser());
		List<Map<String, Object>> list = cisHzyzOrderBookSer.getWardDoctorPrintFields(cisHzyzBookPrintReq, this.getUser());
		SysXtcs sysXtcs = sysXtcsCacheSer.getByJGIdAndCsmc(this.getUser().getHospitalId(), SysXtcsCsmcCts.YZBDYSFTD);
		String sftd = sysXtcs == null ? "0" : sysXtcs.getCsz();
		String jasperName = "";
		if("1".equals(sftd)) {
			jasperName = cisHzyzBookPrintReq.getYzlx() == 0 ? "jrxml/WardDoctorPrint_TD.jasper" : "jrxml/WardDoctorPrint_ls_TD.jasper";
		}else {
			jasperName = cisHzyzBookPrintReq.getYzlx() == 0 ? "jrxml/WardDoctorPrint.jasper" : "jrxml/WardDoctorPrint_ls.jasper";
		}
		if(CollectionUtils.isNotEmpty(list)) {
			iReportExportFileSer.reportHtmlForStream(list, map, jasperName, response);
		}
    }
	
	@RequestMapping("/queryDyjl")
    @ResponseBody
    @ApiOperation(value="指定行查询打印记录" ,httpMethod="POST")
    public ReturnEntity<List<CisHzyzPrintRecordResp>> queryDyjl(@ApiParam(name = "zyh", value = "住院号", required = true)
    @RequestParam Integer zyh, @ApiParam(name = "yzlx", value = "医嘱类型(0:长期1:临时)", required = true)
    @RequestParam Integer yzlx){
        return ReturnEntityUtil.success(cisHzyzOrderBookSer.queryDyjl(zyh, yzlx));    
    }
	
	@GetMapping("/wardAllOrdersPrintFile")
    @ApiOperation(value="全部医嘱本打印")
    public void wardAllOrdersPrintFile(@RequestParam("reqStr") String reqStr, HttpServletResponse response) throws Exception{
		CisHzyzBookPrintReq cisHzyzBookPrintReq = JSONUtil.toBean(reqStr, CisHzyzBookPrintReq.class);
		Map<String, Object> map = cisHzyzOrderBookSer.getWardDoctorPrintParameters(cisHzyzBookPrintReq, this.getUser());
		List<Map<String, Object>> list = cisHzyzOrderBookSer.getAllOrdersPrint(cisHzyzBookPrintReq, this.getUser());
		String jasperName = cisHzyzBookPrintReq.getYzlx() == 0 ? "jrxml/WardAllOrdersPrint.jasper" : "jrxml/ShortWardAllOrdersPrint.jasper";
		if(CollectionUtils.isNotEmpty(list)) {
			iReportExportFileSer.reportHtmlForStream(list, map, jasperName, response);
		}
    }

}

