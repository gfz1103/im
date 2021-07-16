package com.buit.cis.ims.response;

import java.sql.Timestamp;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 类名称：ImZkjlHomeResp<br> 
 * 类描述：住院_转科记录
 * @author GONGFANGZHOU 
 * @ApiModel(value="住院_转科记录")
 */
@ApiModel(value="住院_转科记录_HomeResp")
public class ImZkjlHomeResp {
	
	@ApiModelProperty(value="记录序号")
	private Integer jlxh;
	
	@ApiModelProperty(value="病人床号")
	private String hqch;
	
	@ApiModelProperty(value="病人姓名")
	private String brxm;
	
	@ApiModelProperty(value="病区转出状态")
	private Integer bqzt;
	
	@ApiModelProperty(value="科室转出状态")
	private Integer kszt;
	
	@ApiModelProperty(value="所在科室")
	private Integer hqks;
	
	@ApiModelProperty(value="管床医生")
	private Integer hqys;
	
	@ApiModelProperty(value="所在病区")
	private Integer hqbq;
	
	@ApiModelProperty(value="新科室")
	private Integer hhks;
	
	@ApiModelProperty(value="新病区")
	private Integer hhbq;
	
	@ApiModelProperty(value="新管床医生")
	private Integer hhys;
	
	@ApiModelProperty(value="转科时间")
	private Timestamp yssqrq;

	public Integer getJlxh() {
		return jlxh;
	}

	public void setJlxh(Integer jlxh) {
		this.jlxh = jlxh;
	}

	public String getHqch() {
		return hqch;
	}

	public void setHqch(String hqch) {
		this.hqch = hqch;
	}

	public String getBrxm() {
		return brxm;
	}

	public void setBrxm(String brxm) {
		this.brxm = brxm;
	}

	public Integer getBqzt() {
		return bqzt;
	}

	public void setBqzt(Integer bqzt) {
		this.bqzt = bqzt;
	}

	public Integer getKszt() {
		return kszt;
	}

	public void setKszt(Integer kszt) {
		this.kszt = kszt;
	}

	public Integer getHqks() {
		return hqks;
	}

	public void setHqks(Integer hqks) {
		this.hqks = hqks;
	}

	public Integer getHqys() {
		return hqys;
	}

	public void setHqys(Integer hqys) {
		this.hqys = hqys;
	}

	public Integer getHqbq() {
		return hqbq;
	}

	public void setHqbq(Integer hqbq) {
		this.hqbq = hqbq;
	}

	public Integer getHhks() {
		return hhks;
	}

	public void setHhks(Integer hhks) {
		this.hhks = hhks;
	}

	public Integer getHhbq() {
		return hhbq;
	}

	public void setHhbq(Integer hhbq) {
		this.hhbq = hhbq;
	}

	public Integer getHhys() {
		return hhys;
	}

	public void setHhys(Integer hhys) {
		this.hhys = hhys;
	}

	public Timestamp getYssqrq() {
		return yssqrq;
	}

	public void setYssqrq(Timestamp yssqrq) {
		this.yssqrq = yssqrq;
	}


	
}
