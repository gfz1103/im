   
package com.buit.cis.ims.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


/**
 * 类名称：ImHzryQueryReq<br> 
 * 类描述：住院_病人入院_出院<br>
 * @author GONGFANGZHOU
 */
@ApiModel(value="住院_病人入院_queryReq")
public class ImHzryQueryReq  {
	
	@ApiModelProperty(value="开始时间")
	private String beginDate;
	
	@ApiModelProperty(value="结束时间")
	private String endDate;
	
	@ApiModelProperty(value="病人姓名或住院号码")
	private String comprehensive;
	
	@ApiModelProperty(value="机构id", hidden = true)
	private Integer jgid;
	
	@ApiModelProperty(value="病区代码")
	private Integer bqdm;
	
	@ApiModelProperty(value="科室代码")
	private Integer ksdm;

	@ApiModelProperty(value="会诊关联")
	private Integer hzgl;

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

	public String getComprehensive() {
		return comprehensive;
	}

	public void setComprehensive(String comprehensive) {
		this.comprehensive = comprehensive;
	}

	public Integer getJgid() {
		return jgid;
	}

	public void setJgid(Integer jgid) {
		this.jgid = jgid;
	}

	public Integer getBqdm() {
		return bqdm;
	}

	public void setBqdm(Integer bqdm) {
		this.bqdm = bqdm;
	}

	public Integer getKsdm() {
		return ksdm;
	}

	public void setKsdm(Integer ksdm) {
		this.ksdm = ksdm;
	}

	public Integer getHzgl() {
		return hzgl;
	}

	public void setHzgl(Integer hzgl) {
		this.hzgl = hzgl;
	}
}
