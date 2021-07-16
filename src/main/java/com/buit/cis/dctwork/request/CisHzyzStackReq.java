   
package com.buit.cis.dctwork.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


/**
 * 类名称：CisHzyzStackReq<br> 
 * 类描述：住院_病区医嘱药品组套调入<br>
 * @author GONGFANGZHOU
 */
@ApiModel(value="住院_病区医嘱_stackReq")
public class CisHzyzStackReq {
	
	@ApiModelProperty(value="病区代码")
	private Integer wardId;
	
	@ApiModelProperty(value="住院号")
	private Integer zyh;
	
	@ApiModelProperty(value="组套编号")
	private Integer ztbh;
	
	@ApiModelProperty(value="病人性质")
	private Integer	brxz;
	
	@ApiModelProperty(value="病人id")
	private Integer	brid;
	
	@ApiModelProperty(value="tab类型(长期:cq,临时:ls,急诊:jz,出院:cy)")
	private String adviceType;
	
	@ApiModelProperty(value="组套类别")
	private Integer ztlb;

	

	public Integer getWardId() {
		return wardId;
	}

	public void setWardId(Integer wardId) {
		this.wardId = wardId;
	}

	public Integer getZyh() {
		return zyh;
	}

	public void setZyh(Integer zyh) {
		this.zyh = zyh;
	}
	

	public Integer getZtbh() {
		return ztbh;
	}

	public void setZtbh(Integer ztbh) {
		this.ztbh = ztbh;
	}

	public Integer getBrxz() {
		return brxz;
	}

	public void setBrxz(Integer brxz) {
		this.brxz = brxz;
	}

	public Integer getBrid() {
		return brid;
	}

	public void setBrid(Integer brid) {
		this.brid = brid;
	}

	public String getAdviceType() {
		return adviceType;
	}

	public void setAdviceType(String adviceType) {
		this.adviceType = adviceType;
	}

	public Integer getZtlb() {
		return ztlb;
	}

	public void setZtlb(Integer ztlb) {
		this.ztlb = ztlb;
	}

	
}
