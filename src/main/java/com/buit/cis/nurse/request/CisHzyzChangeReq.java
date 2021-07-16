package com.buit.cis.nurse.request;

import java.sql.Timestamp;

import com.buit.commons.PageQuery;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 类名称：CisHzyzChangeResp<br> 
 * 类描述：住院_病区变动医嘱<br>
 * @author GONGFANGZHOU
 */
@ApiModel(value="住院_病区变动医嘱_ChangeReq")
public class CisHzyzChangeReq {
	
	@ApiModelProperty(value="开始时间")
	private Timestamp beginDate;
	
	@ApiModelProperty(value="结束时间")
	private Timestamp endDate;
	
	@ApiModelProperty(value="医嘱类型(1:当天所有变动医嘱2:当天临时医嘱3:当天开始长期医嘱4:当天结束长期医嘱)")
	private Integer yzlx;
	
	@ApiModelProperty(value="发药方式")
	private Integer fyfs;
	
	@ApiModelProperty(value="病人病区")
	private Integer brbq;
	
	@ApiModelProperty(value="病人科室")
	private Integer brks;
	
	@ApiModelProperty(value="机构id", hidden = true)
	private Integer jgid;

	public Timestamp getBeginDate() {
		return beginDate;
	}

	public void setBeginDate(Timestamp beginDate) {
		this.beginDate = beginDate;
	}

	public Timestamp getEndDate() {
		return endDate;
	}

	public void setEndDate(Timestamp endDate) {
		this.endDate = endDate;
	}

	public Integer getYzlx() {
		return yzlx;
	}

	public void setYzlx(Integer yzlx) {
		this.yzlx = yzlx;
	}

	public Integer getFyfs() {
		return fyfs;
	}

	public void setFyfs(Integer fyfs) {
		this.fyfs = fyfs;
	}

	public Integer getBrbq() {
		return brbq;
	}

	public void setBrbq(Integer brbq) {
		this.brbq = brbq;
	}

	public Integer getBrks() {
		return brks;
	}

	public void setBrks(Integer brks) {
		this.brks = brks;
	}

	public Integer getJgid() {
		return jgid;
	}

	public void setJgid(Integer jgid) {
		this.jgid = jgid;
	}
	
	
	
}
