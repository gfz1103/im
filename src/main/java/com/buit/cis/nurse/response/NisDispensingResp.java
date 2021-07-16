   
package com.buit.cis.nurse.response;

import java.math.BigDecimal;

import com.buit.commons.PageQuery;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


/**
 * 类名称：NisDispensingResp<br> 
 * 类描述：病区发药<br>
 * @author GONGFANGZHOU
 */
@ApiModel(value="病区发药_DispensingResp")
public class NisDispensingResp  extends  PageQuery{
    /**
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
	*/

	private static final long serialVersionUID = 1L;
	@ApiModelProperty(value="药品规格")
    private String ypgg;
    @ApiModelProperty(value="药房单位")
    private String yfdw;
    @ApiModelProperty(value="数量")
    private BigDecimal ycsl;
    @ApiModelProperty(value="药房识别")
    private Integer yfsb;
    @ApiModelProperty(value="药品序号")
    private Integer ypxh;
    @ApiModelProperty(value="药品产地")
    private Integer ypcd;
    @ApiModelProperty(value="产地名称")
    private String cdmc;
    @ApiModelProperty(value="药品名称")
    private String ypmc;
    @ApiModelProperty(value="药品单价")
    private BigDecimal ypdj;
    @ApiModelProperty(value="费用金额")
    private BigDecimal fyje;
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
	public BigDecimal getYcsl() {
		return ycsl;
	}
	public void setYcsl(BigDecimal ycsl) {
		this.ycsl = ycsl;
	}
	public Integer getYfsb() {
		return yfsb;
	}
	public void setYfsb(Integer yfsb) {
		this.yfsb = yfsb;
	}
	public Integer getYpxh() {
		return ypxh;
	}
	public void setYpxh(Integer ypxh) {
		this.ypxh = ypxh;
	}
	public Integer getYpcd() {
		return ypcd;
	}
	public void setYpcd(Integer ypcd) {
		this.ypcd = ypcd;
	}
	public String getCdmc() {
		return cdmc;
	}
	public void setCdmc(String cdmc) {
		this.cdmc = cdmc;
	}
	public String getYpmc() {
		return ypmc;
	}
	public void setYpmc(String ypmc) {
		this.ypmc = ypmc;
	}
	public BigDecimal getYpdj() {
		return ypdj;
	}
	public void setYpdj(BigDecimal ypdj) {
		this.ypdj = ypdj;
	}
	public BigDecimal getFyje() {
		return fyje;
	}
	public void setFyje(BigDecimal fyje) {
		this.fyje = fyje;
	}
    
   
}