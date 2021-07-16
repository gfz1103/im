package com.buit.cis.dctwork.response;

import java.util.List;
import java.util.Map;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 类名称：TemperatureChartResp<br> 
 * 类描述：体温单图表<br>
 * @author GONGFANGZHOU
 */
@ApiModel(value="体温单图表_Resp")
public class TemperatureChartResp {
	
	@ApiModelProperty(value="图表折线")
	private List<Map<String, Object>> chartData;
	
	@ApiModelProperty(value="图表单元格")
	private TableDataResp tableData;
	
	@ApiModelProperty(value="疼痛图表折线")
	private List<Map<String, Object>> painChartData;

	
	public List<Map<String, Object>> getChartData() {
		return chartData;
	}

	public void setChartData(List<Map<String, Object>> chartData) {
		this.chartData = chartData;
	}

	public TableDataResp getTableData() {
		return tableData;
	}

	public void setTableData(TableDataResp tableData) {
		this.tableData = tableData;
	}

	public List<Map<String, Object>> getPainChartData() {
		return painChartData;
	}

	public void setPainChartData(List<Map<String, Object>> painChartData) {
		this.painChartData = painChartData;
	}
	
	
	
}
