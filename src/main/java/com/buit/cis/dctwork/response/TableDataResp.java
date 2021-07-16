package com.buit.cis.dctwork.response;

import java.sql.Date;
import java.util.List;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 类名称：TableDataResp<br> 
 * 类描述：体温单图表<br>
 * @author GONGFANGZHOU
 */
@ApiModel(value="体温单图表_tabledataResp")
public class TableDataResp {
	
	@ApiModelProperty(value="姓名")
	private String name;
	
	@ApiModelProperty(value="性别")
	private String sex;
	
	@ApiModelProperty(value="年龄")
	private String age;
	
	@ApiModelProperty(value="科室")
	private String department;
	
	@ApiModelProperty(value="病区")
	private String inpatientArea;
	
	@ApiModelProperty(value="床号")
	private String bed;
	
	@ApiModelProperty(value="页码")
	private Integer pageNumber;
	
	@ApiModelProperty(value="住院号码")
	private String hospitalized;
	
	@ApiModelProperty(value="日期")
	private List<Date> date;
	
	@ApiModelProperty(value="患病日数")
	private List<Integer> daysAfterSick;
	
	@ApiModelProperty(value="术后日数")
	private List<String> daysAfterOperation;
	
	@ApiModelProperty(value="采集项目集合")
	private List<List<StatisticsSummary>> statisticsSummary;
	
	@ApiModelProperty(value="大便次数")
	private List<String> shitTimes;
	
	@ApiModelProperty(value="身高")
	private List<String> height;
	
	@ApiModelProperty(value="体重")
	private List<String> weight;
	
	@ApiModelProperty(value="血压")
	private List<List<String>> bloodPressure;
	
	@ApiModelProperty(value="疼痛")
	private List<Object> pain;
		
	@ApiModelProperty(value="过敏药物")
	private List<String> skinTestRst;
	
	@ApiModelProperty(value="自定义项目集合")
	private List<List<CustomSummary>> customSummary;
	
	@ApiModelProperty(value="入出量")
	private List<String> inAndOutList;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getInpatientArea() {
		return inpatientArea;
	}

	public void setInpatientArea(String inpatientArea) {
		this.inpatientArea = inpatientArea;
	}

	public String getBed() {
		return bed;
	}

	public void setBed(String bed) {
		this.bed = bed;
	}

	public Integer getPageNumber() {
		return pageNumber;
	}

	public void setPageNumber(Integer pageNumber) {
		this.pageNumber = pageNumber;
	}

	public String getHospitalized() {
		return hospitalized;
	}

	public void setHospitalized(String hospitalized) {
		this.hospitalized = hospitalized;
	}

	public List<Date> getDate() {
		return date;
	}

	public void setDate(List<Date> date) {
		this.date = date;
	}

	public List<Integer> getDaysAfterSick() {
		return daysAfterSick;
	}

	public void setDaysAfterSick(List<Integer> daysAfterSick) {
		this.daysAfterSick = daysAfterSick;
	}

	public List<String> getDaysAfterOperation() {
		return daysAfterOperation;
	}

	public void setDaysAfterOperation(List<String> daysAfterOperation) {
		this.daysAfterOperation = daysAfterOperation;
	}

	public List<List<StatisticsSummary>> getStatisticsSummary() {
		return statisticsSummary;
	}

	public void setStatisticsSummary(List<List<StatisticsSummary>> statisticsSummary) {
		this.statisticsSummary = statisticsSummary;
	}

	public List<String> getShitTimes() {
		return shitTimes;
	}

	public void setShitTimes(List<String> shitTimes) {
		this.shitTimes = shitTimes;
	}

	public List<String> getHeight() {
		return height;
	}

	public void setHeight(List<String> height) {
		this.height = height;
	}

	public List<String> getWeight() {
		return weight;
	}

	public void setWeight(List<String> weight) {
		this.weight = weight;
	}

	public List<List<String>> getBloodPressure() {
		return bloodPressure;
	}

	public void setBloodPressure(List<List<String>> bloodPressure) {
		this.bloodPressure = bloodPressure;
	}

	public List<Object> getPain() {
		return pain;
	}

	public void setPain(List<Object> pain) {
		this.pain = pain;
	}

	public List<String> getSkinTestRst() {
		return skinTestRst;
	}

	public void setSkinTestRst(List<String> skinTestRst) {
		this.skinTestRst = skinTestRst;
	}
	
	public List<List<CustomSummary>> getCustomSummary() {
		return customSummary;
	}

	public void setCustomSummary(List<List<CustomSummary>> customSummary) {
		this.customSummary = customSummary;
	}

	public List<String> getInAndOutList() {
		return inAndOutList;
	}

	public void setInAndOutList(List<String> inAndOutList) {
		this.inAndOutList = inAndOutList;
	}

	public static class StatisticsSummary {
		
		@ApiModelProperty(value="采集项目")
		private String xmmc;
		
		@ApiModelProperty(value="采集项目数值")
		private List<String> data;

		public String getXmmc() {
			return xmmc;
		}

		public void setXmmc(String xmmc) {
			this.xmmc = xmmc;
		}

		public List<String> getData() {
			return data;
		}

		public void setData(List<String> data) {
			this.data = data;
		}

	}
	
	public static class CustomSummary {
		
		@ApiModelProperty(value="自定义项目名称")
		private String zdymc;
		
		@ApiModelProperty(value="自定义项目数值")
		private List<String> zdydata;

		public String getZdymc() {
			return zdymc;
		}

		public void setZdymc(String zdymc) {
			this.zdymc = zdymc;
		}

		public List<String> getZdydata() {
			return zdydata;
		}

		public void setZdydata(List<String> zdydata) {
			this.zdydata = zdydata;
		}

	}
	
}
