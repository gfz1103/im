   
package com.buit.cis.dctwork.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.sql.Timestamp;

import javax.validation.constraints.NotNull;


/**
 * 类名称：CisHzyzDataReq<br> 
 * 类描述：住院_病区医嘱_数据盒<br>
 * @author GONGFANGZHOU
 */
@ApiModel(value="住院_病区医嘱_CheckSkinReq")
public class CisHzyzCheckSkinReq {

	@NotNull(message = "病人id不能为空")
	@ApiModelProperty(value="病人id")
    private Integer brid;

	@NotNull(message = "医嘱类型不能为空")
	@ApiModelProperty(value="医嘱类型(0:长期1:临时)")
    private Integer lsyz;
	
	@NotNull(message = "药品序号不能为空")
	@ApiModelProperty(value="药品序号")
    private Integer ypxh;

	@NotNull(message = "过敏药物类别不能为空")
	@ApiModelProperty(value="过敏药物类别")
    private Integer gmywlb;
	
	@NotNull(message = "药品名称不能为空")
	@ApiModelProperty(value="药品名称")
    private String yzmc;
	
	@NotNull(message = "开嘱时间不能为空")
	@ApiModelProperty(value="开嘱时间")
    private Timestamp kssj;
	
	public Integer getBrid() {
		return brid;
	}

	public void setBrid(Integer brid) {
		this.brid = brid;
	}

	public Integer getLsyz() {
		return lsyz;
	}

	public void setLsyz(Integer lsyz) {
		this.lsyz = lsyz;
	}

	public Integer getYpxh() {
		return ypxh;
	}

	public void setYpxh(Integer ypxh) {
		this.ypxh = ypxh;
	}

	public Integer getGmywlb() {
		return gmywlb;
	}

	public void setGmywlb(Integer gmywlb) {
		this.gmywlb = gmywlb;
	}

	public String getYzmc() {
		return yzmc;
	}

	public void setYzmc(String yzmc) {
		this.yzmc = yzmc;
	}

	public Timestamp getKssj() {
		return kssj;
	}

	public void setKssj(Timestamp kssj) {
		this.kssj = kssj;
	}
	
}
