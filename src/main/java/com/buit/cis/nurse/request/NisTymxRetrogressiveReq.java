   
package com.buit.cis.nurse.request;

import java.math.BigDecimal;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


/**
 * 类名称：NisTymxRetrogressiveReq<br> 
 * 类描述：病区_退药明细_可退数量<br>
 * @author GONGFANGZHOU
 */
@ApiModel(value="病区_退药明细_retrogressiveReq")
public class NisTymxRetrogressiveReq {
	
	@ApiModelProperty(value="领用病区")
	private Integer lybq;
	
	@ApiModelProperty(value="药品产地")
    private Integer ypcd;
	
	@ApiModelProperty(value="药品单价")
	private BigDecimal ypjg;
	
	@ApiModelProperty(value="药品序号")
    private Integer ypxh;
	
	@ApiModelProperty(value="医嘱序号")
    private Integer yzxh;
	
	@ApiModelProperty(value="住院号")
    private Integer zyh;
	
	@ApiModelProperty(value="机构id")
	private Integer jgid;

	public Integer getLybq() {
		return lybq;
	}

	public void setLybq(Integer lybq) {
		this.lybq = lybq;
	}

	public Integer getYpcd() {
		return ypcd;
	}

	public void setYpcd(Integer ypcd) {
		this.ypcd = ypcd;
	}

	public BigDecimal getYpjg() {
		return ypjg;
	}

	public void setYpjg(BigDecimal ypjg) {
		this.ypjg = ypjg;
	}

	public Integer getYpxh() {
		return ypxh;
	}

	public void setYpxh(Integer ypxh) {
		this.ypxh = ypxh;
	}

	public Integer getYzxh() {
		return yzxh;
	}

	public void setYzxh(Integer yzxh) {
		this.yzxh = yzxh;
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
	
    
}
