
package com.buit.cis.ims.controller;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.buit.system.service.ExportFileSer;
import org.apache.dubbo.config.annotation.DubboReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.buit.commons.BaseSpringController;
import com.buit.utill.ReturnEntity;
import com.buit.utill.ReturnEntityUtil;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;

import cn.hutool.core.date.DateUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * 系统用户表<br>
 * 
 * @author All
 */
@Api(tags = "报表测试")
@Controller
@RequestMapping("/report")
public class ReportCtr extends BaseSpringController {

	static final Logger logger = LoggerFactory.getLogger(ReportCtr.class);
	@DubboReference
	private ExportFileSer exportFileSer;


	public List<Map<String, Object>> getProductList() {
		Map<String, Object> parmap = new HashMap<>();
		String dateStart = "2015-03-01 22:33:23";
		Timestamp startTime = DateUtil.parse(dateStart).toTimestamp();

		String dateEnd = "2019-03-01 22:33:23";
		Timestamp endTime = DateUtil.parse(dateEnd).toTimestamp();

		parmap.put("startTime", startTime);
		parmap.put("endTime", endTime);
//		List<Map<String, Object>> retmap = sysUserSer.getEntityMapper().getReport(parmap);
		List<Map<String, Object>> retmap = null;
//	   for (Map<String, Object> map : retmap) {
//		   Iterator<Map.Entry<String,Object>> iterator = map.entrySet().iterator();
//		   System.out.println("---------------------------------------");
//	       while (iterator.hasNext()){
//	           Map.Entry<String,Object> entry = iterator.next();
//	           System.out.println(entry.getKey()+"="+entry.getValue()+" type="+entry.getValue().getClass());
//	       }
//	       System.out.println("---------------------------------------");
//	   }
		return retmap;
	}

	@RequestMapping(path = "/htmlPreview")
	@ResponseBody
	@ApiOperationSupport(author = "神算子")
	@ApiOperation(value = "html预览", httpMethod = "POST", notes = "返回htmlURL路径")
	public ReturnEntity<String> reportHtml(HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> params = new HashMap<>();
		params.put("adt_start", "20170606");
		params.put("adt_end", "20200606");
		String url = exportFileSer.reportHtml(getProductList(), params, "GhfkstjView.jasper");
		return ReturnEntityUtil.success(url);
	}
	@ApiOperationSupport(author = "神算子")
	@ApiOperation(value = "pdf导出", httpMethod = "POST")
	@RequestMapping(path = "/pdfExport")
	public void pdfExport(HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> params = new HashMap<>();
		params.put("adt_start", "20170606");
		params.put("adt_end", "20200606");
		exportFileSer.pdfExport(getProductList(), params, "GhfkstjView.jasper", response);
	}
	@ApiOperationSupport(author = "神算子")
	@ApiOperation(value = "pdf导出返回URL", httpMethod = "POST")
	@ResponseBody
	@RequestMapping(path = "/pdfExportToUrl")
	public ReturnEntity<String> pdfExportToUrl(HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> params = new HashMap<>();
		params.put("adt_start", "20170606");
		params.put("adt_end", "20200606");
		String url =exportFileSer.pdfExportToUrl(getProductList(), params, "GhfkstjView.jasper");
		return ReturnEntityUtil.success(url);
	}

	// 预览并浏览器自动弹出打印
	@RequestMapping(path = "/previewPrint")
	@ApiOperationSupport(author = "神算子")
	@ApiOperation(value = "pdf预览", httpMethod = "POST",notes = "pdf预览并浏览器自动弹出打印")
	public void previewPrint(HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> params = new HashMap<>();
		params.put("adt_start", "20170606");
		params.put("adt_end", "20200606");
		exportFileSer.pdfPreviewPrint(getProductList(), params, "GhfkstjView.jasper", response);
	}

//   //连接打印机打印
//   @RequestMapping(path = "/print1")
//   public void print1(HttpServletRequest request, HttpServletResponse response) throws Exception {
//	   Map<String,Object> params = new HashMap<>();
//       params.put("Parameter1", "hello 你好");
//	   exportFileSer.print1(getProductList(), params, "GhfkstjView.jasper",response);
//   }

	// 导出excel文件
	@RequestMapping(path = "/exportExcel")
	@ApiOperationSupport(author = "神算子")
	@ResponseBody
	@ApiOperation(value = "导出excel到URL", httpMethod = "POST",notes = "返回URL路径")
	public ReturnEntity<String> exportExcel(HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> params = new HashMap<>();
		params.put("adt_start", "20170606");
		params.put("adt_end", "20200606");
		String url = exportFileSer.exportExcel(getProductList(), params, "GhfkstjView.jasper");
		return ReturnEntityUtil.success(url);
	}

	// 直接下载excel文件
	@ApiOperationSupport(author = "神算子")
	@ApiOperation(value = "下载excel文件", httpMethod = "POST")
	@RequestMapping(path = "/exportDownload")
	public void exportDownload(HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> params = new HashMap<>();
		params.put("adt_start", "20170606");
		params.put("adt_end", "20200606");
		exportFileSer.exportDownload(getProductList(), params, "GhfkstjView.jasper", response);
	}
	
	
	@ApiOperationSupport(author = "神算子")
	@ApiOperation(value = "动态列报表", httpMethod = "POST")
	@ResponseBody
	@RequestMapping(path = "/dynamicCol")
	public ReturnEntity<String> dynamicCol(@ApiParam(name = "expType", value = "1:html 2:excel 3:pdf",required = true) @RequestParam(value = "expType")  int expType) {
		Map<String, Object> params = new HashMap<>();
		params.put("adt_start", "20170606");
		params.put("adt_end", "20200606");
		
		LinkedHashMap<String,String> headMap=new  LinkedHashMap<>();
		headMap.put("col1", "colValue1");
		headMap.put("col2", "colValue2");
		headMap.put("col3", "colValue3");
		List<Map<String,Object>> detailList=new ArrayList<>();
		
		Map<String,Object> detailMap=new  HashMap<>();
		detailMap.put("col1", "colValue11");
		detailMap.put("col2", "colValue22");
		detailMap.put("col3", "colValue33");
		detailList.add(detailMap);
		String rer=exportFileSer.dynamicCol(detailList, params, headMap, "template.jrxml", 5, expType);
		return ReturnEntityUtil.success(rer);
	}
}
