   
package com.buit.cis.dctwork.response;

import java.math.BigDecimal;
import java.sql.Timestamp;

import com.buit.commons.PageQuery;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


/**
 * 类名称：CisHzyzProjectResp<br> 
 * 类描述：住院_病区项目医嘱<br>
 * @author GONGFANGZHOU
 */
@ApiModel(value="住院_病区项目医嘱_projectResp")
public class CisHzyzProjectResp {

    @ApiModelProperty(value="记录编号")
	private Integer jlbh;
	
	@ApiModelProperty(value="组套编号")
	private Integer ztbh;
	
	@ApiModelProperty(value="药品组号")
	private Integer ypzh;
	
	@ApiModelProperty(value="项目编号")
	private Integer xmbh;
	
	@ApiModelProperty(value="项目名称")
	private String xmmc;
	
	@ApiModelProperty(value="项目数量")
	private BigDecimal xmsl;
	
	@ApiModelProperty(value="一次剂量")
	private BigDecimal ycjl;
	
	@ApiModelProperty(value="使用频次")
	private String sypc;
	
	@ApiModelProperty(value="每日次数")
	private Integer mrcs;
	
	@ApiModelProperty(value="用药天数")
	private Integer yyts;
	
	@ApiModelProperty(value="给药途径")
	private Integer gytj;
	
	@ApiModelProperty(value="费用序号")
	private Integer fyxh;
	
	@ApiModelProperty(value="医嘱名称")
	private String yzmc;
	
	@ApiModelProperty(value="费用单位")
	private String fydw;
	
	@ApiModelProperty(value="标准价格")
	private BigDecimal bzjg;
	
	@ApiModelProperty(value="费用单价")
	private BigDecimal fydj;
	
	@ApiModelProperty(value="费用归并")
	private Integer fygb;
	
	@ApiModelProperty(value="费用科室")
	private Integer fyks;
	
	@ApiModelProperty(value="药品类型")
	private Integer yplx;
	
	@ApiModelProperty(value="医技使用")
	private Integer yjsy;
	
	@ApiModelProperty(value="项目类型")
	private Integer xmlx;

	public Integer getJlbh() {
		return jlbh;
	}

	public void setJlbh(Integer jlbh) {
		this.jlbh = jlbh;
	}

	public Integer getZtbh() {
		return ztbh;
	}

	public void setZtbh(Integer ztbh) {
		this.ztbh = ztbh;
	}

	public Integer getYpzh() {
		return ypzh;
	}

	public void setYpzh(Integer ypzh) {
		this.ypzh = ypzh;
	}

	public Integer getXmbh() {
		return xmbh;
	}

	public void setXmbh(Integer xmbh) {
		this.xmbh = xmbh;
	}

	public String getXmmc() {
		return xmmc;
	}

	public void setXmmc(String xmmc) {
		this.xmmc = xmmc;
	}

	public BigDecimal getXmsl() {
		return xmsl;
	}

	public void setXmsl(BigDecimal xmsl) {
		this.xmsl = xmsl;
	}

	public BigDecimal getYcjl() {
		return ycjl;
	}

	public void setYcjl(BigDecimal ycjl) {
		this.ycjl = ycjl;
	}

	public String getSypc() {
		return sypc;
	}

	public void setSypc(String sypc) {
		this.sypc = sypc;
	}

	public Integer getMrcs() {
		return mrcs;
	}

	public void setMrcs(Integer mrcs) {
		this.mrcs = mrcs;
	}

	public Integer getYyts() {
		return yyts;
	}

	public void setYyts(Integer yyts) {
		this.yyts = yyts;
	}

	public Integer getGytj() {
		return gytj;
	}

	public void setGytj(Integer gytj) {
		this.gytj = gytj;
	}

	public Integer getFyxh() {
		return fyxh;
	}

	public void setFyxh(Integer fyxh) {
		this.fyxh = fyxh;
	}

	public String getYzmc() {
		return yzmc;
	}

	public void setYzmc(String yzmc) {
		this.yzmc = yzmc;
	}

	public String getFydw() {
		return fydw;
	}

	public void setFydw(String fydw) {
		this.fydw = fydw;
	}

	public BigDecimal getBzjg() {
		return bzjg;
	}

	public void setBzjg(BigDecimal bzjg) {
		this.bzjg = bzjg;
	}

	public BigDecimal getFydj() {
		return fydj;
	}

	public void setFydj(BigDecimal fydj) {
		this.fydj = fydj;
	}

	public Integer getFygb() {
		return fygb;
	}

	public void setFygb(Integer fygb) {
		this.fygb = fygb;
	}

	public Integer getFyks() {
		return fyks;
	}

	public void setFyks(Integer fyks) {
		this.fyks = fyks;
	}

	public Integer getYplx() {
		return yplx;
	}

	public void setYplx(Integer yplx) {
		this.yplx = yplx;
	}

	public Integer getYjsy() {
		return yjsy;
	}

	public void setYjsy(Integer yjsy) {
		this.yjsy = yjsy;
	}

	public Integer getXmlx() {
		return xmlx;
	}

	public void setXmlx(Integer xmlx) {
		this.xmlx = xmlx;
	}

	
}
