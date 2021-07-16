
package com.buit.cis.nurse.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.sql.Timestamp;


/**
 * 类名称：NisDispensingReq<br>
 * 类描述：病区发药<br>
 *
 * @author GONGFANGZHOU
 */
@ApiModel(value = "病区发药_DispensingReq")
public class NisDispensingReq {

	@ApiModelProperty(value = "开始时间")
	private Timestamp beginDate;

	@ApiModelProperty(value = "结束时间")
	private Timestamp endDate;

	@ApiModelProperty(value = "病区代码")
	private Integer bqdm;

	@ApiModelProperty(value = "药房识别")
	private Integer yfsb;

	@ApiModelProperty(value = "发药方式")
	private Integer fyfs;

	@ApiModelProperty(value = "发药识别(1:发药2:退药)")
	private Integer fysb;

	@ApiModelProperty(value = "药品拼音编码")
	private String pydm;

	@ApiModelProperty(value = "住院号")
	private Integer zyh;

	@ApiModelProperty(value = "机构id", hidden = true)
	private Integer jgid;

	@ApiModelProperty(value = "住院号、床号、患者姓名")
	private String comprehensive;

	@ApiModelProperty(value = "出院判别 | 0：在院 8：出院")
	private Integer cypb;

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

	public Integer getBqdm() {
		return bqdm;
	}

	public void setBqdm(Integer bqdm) {
		this.bqdm = bqdm;
	}

	public Integer getYfsb() {
		return yfsb;
	}

	public void setYfsb(Integer yfsb) {
		this.yfsb = yfsb;
	}

	public Integer getFyfs() {
		return fyfs;
	}

	public void setFyfs(Integer fyfs) {
		this.fyfs = fyfs;
	}
	
	public Integer getFysb() {
		return fysb;
	}

	public void setFysb(Integer fysb) {
		this.fysb = fysb;
	}

	public Integer getZyh() {
		return zyh;
	}

	public void setZyh(Integer zyh) {
		this.zyh = zyh;
	}

	public Integer getJgid() {
		return jgid;
	}

	public void setJgid(Integer jgid) {
		this.jgid = jgid;
	}

	public String getComprehensive() {
		return comprehensive;
	}

	public void setComprehensive(String comprehensive) {
		this.comprehensive = comprehensive;
	}

	public String getPydm() {
		return pydm;
	}

	public void setPydm(String pydm) {
		this.pydm = pydm;
	}

	public Integer getCypb() {
		return cypb;
	}

	public void setCypb(Integer cypb) {
		this.cypb = cypb;
	}
}