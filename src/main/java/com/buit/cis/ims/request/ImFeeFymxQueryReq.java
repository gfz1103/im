package com.buit.cis.ims.request;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


/**
 * 类名称：ImFeeFymxQueryReq<br> 
 * 类描述：费用明细表<br>
 * @author GONGFANGZHOU
 */
@ApiModel(value="费用明细表_queryReq")
public class ImFeeFymxQueryReq  {

	@ApiModelProperty(value="费用序号")
	private Integer fyxh;
	
	@ApiModelProperty(value="住院号")
	private Integer zyh;
	
	@ApiModelProperty(value="费用单价")
	private BigDecimal fydj;
	
	@ApiModelProperty(value="费用日期")
	private String fyrq;
	
	@ApiModelProperty(value="费用数量")
	private BigDecimal fysl;

	public Integer getFyxh() {
		return fyxh;
	}

	public void setFyxh(Integer fyxh) {
		this.fyxh = fyxh;
	}

	public Integer getZyh() {
		return zyh;
	}

	public void setZyh(Integer zyh) {
		this.zyh = zyh;
	}

	public BigDecimal getFydj() {
		return fydj;
	}

	public void setFydj(BigDecimal fydj) {
		this.fydj = fydj;
	}

	public String getFyrq() {
		return fyrq;
	}

	public void setFyrq(String fyrq) {
		this.fyrq = fyrq;
	}

	public BigDecimal getFysl() {
		return fysl;
	}

	public void setFysl(BigDecimal fysl) {
		this.fysl = fysl;
	}
	
	
    
}
