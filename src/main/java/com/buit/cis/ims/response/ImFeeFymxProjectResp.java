package com.buit.cis.ims.response;

import com.buit.commons.PageQuery;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;
import java.sql.Date;



/**
 * 类名称：ImFeeFymxProjectResp<br>
 * 类描述：费用明细表<br>
 * @author GONGFANGZHOU
 */
@ApiModel(value="费用明细表_projectResp")
public class ImFeeFymxProjectResp  extends PageQuery {
	
	@ApiModelProperty(value="住院号")
	private Integer zyh;
	
	@ApiModelProperty(value="费用日期")
	private Date fyrq;
	
	@ApiModelProperty(value="费用序号")
	private Integer fyxh;
	
	@ApiModelProperty(value="费用名称")
	private String fymc;
	
	@ApiModelProperty(value="费用数量")
	private BigDecimal fysl;
	
	@ApiModelProperty(value="费用单价")
	private BigDecimal fydj;

	@ApiModelProperty(value="医生工号")
	private String ysgh;
	
	@ApiModelProperty(value="医生姓名")
	private String ysxm;

	public Integer getZyh() {
		return zyh;
	}

	public void setZyh(Integer zyh) {
		this.zyh = zyh;
	}

	public Date getFyrq() {
		return fyrq;
	}

	public void setFyrq(Date fyrq) {
		this.fyrq = fyrq;
	}

	public Integer getFyxh() {
		return fyxh;
	}

	public void setFyxh(Integer fyxh) {
		this.fyxh = fyxh;
	}

	public String getFymc() {
		return fymc;
	}

	public void setFymc(String fymc) {
		this.fymc = fymc;
	}

	public BigDecimal getFysl() {
		return fysl;
	}

	public void setFysl(BigDecimal fysl) {
		this.fysl = fysl;
	}

	public BigDecimal getFydj() {
		return fydj;
	}

	public void setFydj(BigDecimal fydj) {
		this.fydj = fydj;
	}

	public String getYsgh() {
		return ysgh;
	}

	public void setYsgh(String ysgh) {
		this.ysgh = ysgh;
	}

	public String getYsxm() {
		return ysxm;
	}

	public void setYsxm(String ysxm) {
		this.ysxm = ysxm;
	}
	
	
}
