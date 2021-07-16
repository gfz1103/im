   
package com.buit.cis.dctwork.response;

import java.math.BigDecimal;
import java.sql.Timestamp;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


/**
 * 类名称：NisTj02Resp<br> 
 * 类描述：<br>
 * @author GONGFANGZHOU
 */
@ApiModel(value="病区_提交明细退回_DrugInfoResp")
public class NisTj02DrugInfoResp {

	@ApiModelProperty(value="记录序号")
    private Integer jlxh;
	
	@ApiModelProperty(value="提交序号")
    private Integer tjxh;
	
	@ApiModelProperty(value="医嘱序号")
    private Integer yzxh;
	
	@ApiModelProperty(value="药品序号")
    private Integer ypxh;
	
	@ApiModelProperty(value="药品产地")
    private Integer ypcd;
	
	@ApiModelProperty(value="药品名称")
    private String ypmc;
	
	@ApiModelProperty(value="开嘱时间")
    private Timestamp kssj;
	
	@ApiModelProperty(value="一次数量")
    private BigDecimal ycsl;
	
	@ApiModelProperty(value="药房规格")
    private String yfgg;
	
	@ApiModelProperty(value="药房单位")
    private String yfdw;

	@ApiModelProperty(value="药品单价")
    private BigDecimal ypdj;

	@ApiModelProperty(value="金额")
    private BigDecimal fyje;
	
	@ApiModelProperty(value="产地名称")
    private String cdmc;
	
	@ApiModelProperty(value="确认日期")
    private Timestamp qrrq;
	
	@ApiModelProperty(value="药房识别")
    private Integer yfsb;
	
	@ApiModelProperty(value="发药方式")
    private Integer fyfs;
	
	@ApiModelProperty(value="医嘱组号")
    private Integer yzzh;

	@ApiModelProperty(value="临时医嘱")
    private Integer lsyz;

	@ApiModelProperty(value="药品类型")
    private Integer yplx;
	
	@ApiModelProperty(value="组套标志")
    private Integer ztbz;
	
	@ApiModelProperty(value="组套记录序号")
    private Integer ztyzjlxh;

	public Integer getJlxh() {
		return jlxh;
	}

	public void setJlxh(Integer jlxh) {
		this.jlxh = jlxh;
	}

	public Integer getTjxh() {
		return tjxh;
	}

	public void setTjxh(Integer tjxh) {
		this.tjxh = tjxh;
	}

	public Integer getYzxh() {
		return yzxh;
	}

	public void setYzxh(Integer yzxh) {
		this.yzxh = yzxh;
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

	public String getYpmc() {
		return ypmc;
	}

	public void setYpmc(String ypmc) {
		this.ypmc = ypmc;
	}

	public Timestamp getKssj() {
		return kssj;
	}

	public void setKssj(Timestamp kssj) {
		this.kssj = kssj;
	}

	public BigDecimal getYcsl() {
		return ycsl;
	}

	public void setYcsl(BigDecimal ycsl) {
		this.ycsl = ycsl;
	}

	public String getYfgg() {
		return yfgg;
	}

	public void setYfgg(String yfgg) {
		this.yfgg = yfgg;
	}

	public String getYfdw() {
		return yfdw;
	}

	public void setYfdw(String yfdw) {
		this.yfdw = yfdw;
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

	public String getCdmc() {
		return cdmc;
	}

	public void setCdmc(String cdmc) {
		this.cdmc = cdmc;
	}

	public Timestamp getQrrq() {
		return qrrq;
	}

	public void setQrrq(Timestamp qrrq) {
		this.qrrq = qrrq;
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

	public Integer getYzzh() {
		return yzzh;
	}

	public void setYzzh(Integer yzzh) {
		this.yzzh = yzzh;
	}

	public Integer getLsyz() {
		return lsyz;
	}

	public void setLsyz(Integer lsyz) {
		this.lsyz = lsyz;
	}
	
	public Integer getYplx() {
		return yplx;
	}

	public void setYplx(Integer yplx) {
		this.yplx = yplx;
	}

	public Integer getZtbz() {
		return ztbz;
	}

	public void setZtbz(Integer ztbz) {
		this.ztbz = ztbz;
	}

	public Integer getZtyzjlxh() {
		return ztyzjlxh;
	}

	public void setZtyzjlxh(Integer ztyzjlxh) {
		this.ztyzjlxh = ztyzjlxh;
	}
		
}
