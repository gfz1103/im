package com.buit.cis.ims.request;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


/**
 * 类名称：ImFeeFymxQueryReq<br> 
 * 类描述：费用明细表<br>
 * @author GONGFANGZHOU
 */
@ApiModel(value="费用明细表_queryReq")
public class ImFeeFymxAccountingReq  {
	
	@ApiModelProperty(value="开始日期")
	private String beginDate;
	
	@ApiModelProperty(value="结束日期")
	private String endDate;
	
	@ApiModelProperty(value="输入工号")
	private Integer srgh;
	
	@ApiModelProperty(value="费用科室")
	private Integer fyks;

	@ApiModelProperty(value="住院号码")
	private String zyhm;
	
	@ApiModelProperty(value="机构id", hidden = true)
	private Integer jgid;

	public String getBeginDate() {
		return beginDate;
	}

	public void setBeginDate(String beginDate) {
		this.beginDate = beginDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public Integer getSrgh() {
		return srgh;
	}

	public void setSrgh(Integer srgh) {
		this.srgh = srgh;
	}

	public Integer getFyks() {
		return fyks;
	}

	public void setFyks(Integer fyks) {
		this.fyks = fyks;
	}

	public String getZyhm() {
		return zyhm;
	}

	public void setZyhm(String zyhm) {
		this.zyhm = zyhm;
	}

	public Integer getJgid() {
		return jgid;
	}

	public void setJgid(Integer jgid) {
		this.jgid = jgid;
	}

}
