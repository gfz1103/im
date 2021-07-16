   
package com.buit.cis.nurse.response;

import java.math.BigDecimal;
import java.sql.Timestamp;

import com.buit.commons.PageQuery;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


/**
 * 类名称：NisTymxRecordResp<br> 
 * 类描述：病区_退药明细_退药记录<br>
 * @author GONGFANGZHOU
 */
@ApiModel(value="病区_退药明细_recordResp")
public class NisTymxRecordResp  extends  PageQuery{
	
	@ApiModelProperty(value="退费日期")
    private Timestamp tyrq;
	
	@ApiModelProperty(value="药品名称")
    private String ypmc;
	
	@ApiModelProperty(value="药品规格")
    private String ypgg;
	
	@ApiModelProperty(value="药房单位")
    private String yfdw;
	
	@ApiModelProperty(value="药品数量")
    private BigDecimal ypsl;
	
	@ApiModelProperty(value="药品价格")
    private BigDecimal ypjg;
	
	@ApiModelProperty(value="产地名称")
	private String cdmc;
	
	@ApiModelProperty(value="申请日期")
    private Timestamp sqrq;
	
	@ApiModelProperty(value="退药原因")
	private String tyyy;

	public Timestamp getTyrq() {
		return tyrq;
	}

	public void setTyrq(Timestamp tyrq) {
		this.tyrq = tyrq;
	}

	public String getYpmc() {
		return ypmc;
	}

	public void setYpmc(String ypmc) {
		this.ypmc = ypmc;
	}

	public String getYpgg() {
		return ypgg;
	}

	public void setYpgg(String ypgg) {
		this.ypgg = ypgg;
	}

	public String getYfdw() {
		return yfdw;
	}

	public void setYfdw(String yfdw) {
		this.yfdw = yfdw;
	}

	public BigDecimal getYpsl() {
		return ypsl;
	}

	public void setYpsl(BigDecimal ypsl) {
		this.ypsl = ypsl;
	}

	public BigDecimal getYpjg() {
		return ypjg;
	}

	public void setYpjg(BigDecimal ypjg) {
		this.ypjg = ypjg;
	}

	public String getCdmc() {
		return cdmc;
	}

	public void setCdmc(String cdmc) {
		this.cdmc = cdmc;
	}

	public Timestamp getSqrq() {
		return sqrq;
	}

	public void setSqrq(Timestamp sqrq) {
		this.sqrq = sqrq;
	}

	public String getTyyy() {
		return tyyy;
	}

	public void setTyyy(String tyyy) {
		this.tyyy = tyyy;
	}
	
    
}
