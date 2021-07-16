package com.buit.cis.ims.response;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;

import com.buit.commons.PageQuery;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


/**
 * 类名称：ImFeeFymxAccountingResp<br>
 * 类描述：费用明细表<br>
 * @author GONGFANGZHOU
 */
@ApiModel(value="费用明细表_CostResp")
public class ImFeeFymxCostResp extends PageQuery {
    @ApiModelProperty(value="费用日期")
	private Timestamp fyrq;
	
	@ApiModelProperty(value="费用名称")
	private String fymc;
	
	@ApiModelProperty(value="费用数量")
	private BigDecimal fysl;
	
	@ApiModelProperty(value="总计金额")
	private BigDecimal zjje;
    
	@ApiModelProperty(value="费用病区")
	private Integer fybq;
	
	@ApiModelProperty(value="计费日期")
	private Timestamp jfrq;
	
	@ApiModelProperty(value="费用科室")
	private Integer fyks;
	
	@ApiModelProperty(value="医生工号")
	private String ysgh;

	public Timestamp getFyrq() {
		return fyrq;
	}

	public void setFyrq(Timestamp fyrq) {
		this.fyrq = fyrq;
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

	public BigDecimal getZjje() {
		return zjje;
	}

	public void setZjje(BigDecimal zjje) {
		this.zjje = zjje;
	}

	public Integer getFybq() {
		return fybq;
	}

	public void setFybq(Integer fybq) {
		this.fybq = fybq;
	}

	public Timestamp getJfrq() {
		return jfrq;
	}

	public void setJfrq(Timestamp jfrq) {
		this.jfrq = jfrq;
	}

	public Integer getFyks() {
		return fyks;
	}

	public void setFyks(Integer fyks) {
		this.fyks = fyks;
	}

	public String getYsgh() {
		return ysgh;
	}

	public void setYsgh(String ysgh) {
		this.ysgh = ysgh;
	}
	
	
}
