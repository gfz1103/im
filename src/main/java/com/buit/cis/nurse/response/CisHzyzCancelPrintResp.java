package com.buit.cis.nurse.response;

import java.math.BigDecimal;
import java.sql.Timestamp;

import com.buit.commons.PageQuery;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 类名称：CisHzyzCancelPrintResp<br> 
 * 类描述：住院_病区医嘱取消打印<br>
 * @author GONGFANGZHOU
 */
@ApiModel(value="住院_病区医嘱取消打印_CancelPrintResp")
public class CisHzyzCancelPrintResp extends PageQuery{
	
	/**
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
	*/
	private static final long serialVersionUID = 1L;

	@ApiModelProperty(value="住院号码")
	private String zyhm;
	
	@ApiModelProperty(value="病人姓名")
	private String brxm;
	
	@ApiModelProperty(value="记录序号")
	private Integer jlxh;
	
	@ApiModelProperty(value="开始时间")
	private Timestamp kssj;
	
	@ApiModelProperty(value="医嘱名称")
	private String yzmc;
	
	@ApiModelProperty(value="一次剂量")
	private BigDecimal ycjl;
	
	@ApiModelProperty(value="一次数量")
	private BigDecimal ycsl;
	
	@ApiModelProperty(value="使用频次")
	private String sypc;
	
	@ApiModelProperty(value="医嘱执行时间")
	private String yzzxsj;
	
	@ApiModelProperty(value="首日次数")
	private Integer srcs;
	
	@ApiModelProperty(value="药品用法")
	private Integer ypyf;
	
	@ApiModelProperty(value="药品单价")
	private BigDecimal ypdj;
	
	@ApiModelProperty(value="医生工号")
	private String ysgh;
	
	@ApiModelProperty(value="停嘱时间")
	private Timestamp tzsj;
	
	@ApiModelProperty(value="停嘱医生")
	private String tzys;
	
	@ApiModelProperty(value="操作工号")
	private String czgh;
	
	@ApiModelProperty(value="复核工号")
	private String fhgh;
	
	@ApiModelProperty(value="发药方式")
	private Integer fyfs;
	
	@ApiModelProperty(value="备注信息")
	private String bzxx;
	
	@ApiModelProperty(value="药品产地")
	private Integer ypcd;
	
	@ApiModelProperty(value="药房识别")
	private Integer yfsb;
	
	@ApiModelProperty(value="打印时间")
	private Timestamp dysj;
	
	@ApiModelProperty(value="用药时间")
	private Timestamp yysj;
	
	@ApiModelProperty(value="医嘱组号")
	private Integer yzzh;

	public String getZyhm() {
		return zyhm;
	}

	public void setZyhm(String zyhm) {
		this.zyhm = zyhm;
	}

	public String getBrxm() {
		return brxm;
	}

	public void setBrxm(String brxm) {
		this.brxm = brxm;
	}

	public Integer getJlxh() {
		return jlxh;
	}

	public void setJlxh(Integer jlxh) {
		this.jlxh = jlxh;
	}

	public Timestamp getKssj() {
		return kssj;
	}

	public void setKssj(Timestamp kssj) {
		this.kssj = kssj;
	}

	public String getYzmc() {
		return yzmc;
	}

	public void setYzmc(String yzmc) {
		this.yzmc = yzmc;
	}

	public BigDecimal getYcjl() {
		return ycjl;
	}

	public void setYcjl(BigDecimal ycjl) {
		this.ycjl = ycjl;
	}

	public BigDecimal getYcsl() {
		return ycsl;
	}

	public void setYcsl(BigDecimal ycsl) {
		this.ycsl = ycsl;
	}

	public String getSypc() {
		return sypc;
	}

	public void setSypc(String sypc) {
		this.sypc = sypc;
	}

	public String getYzzxsj() {
		return yzzxsj;
	}

	public void setYzzxsj(String yzzxsj) {
		this.yzzxsj = yzzxsj;
	}

	public Integer getSrcs() {
		return srcs;
	}

	public void setSrcs(Integer srcs) {
		this.srcs = srcs;
	}

	public Integer getYpyf() {
		return ypyf;
	}

	public void setYpyf(Integer ypyf) {
		this.ypyf = ypyf;
	}

	public BigDecimal getYpdj() {
		return ypdj;
	}

	public void setYpdj(BigDecimal ypdj) {
		this.ypdj = ypdj;
	}

	public String getYsgh() {
		return ysgh;
	}

	public void setYsgh(String ysgh) {
		this.ysgh = ysgh;
	}

	public Timestamp getTzsj() {
		return tzsj;
	}

	public void setTzsj(Timestamp tzsj) {
		this.tzsj = tzsj;
	}

	public String getTzys() {
		return tzys;
	}

	public void setTzys(String tzys) {
		this.tzys = tzys;
	}

	public String getCzgh() {
		return czgh;
	}

	public void setCzgh(String czgh) {
		this.czgh = czgh;
	}

	public String getFhgh() {
		return fhgh;
	}

	public void setFhgh(String fhgh) {
		this.fhgh = fhgh;
	}

	public Integer getFyfs() {
		return fyfs;
	}

	public void setFyfs(Integer fyfs) {
		this.fyfs = fyfs;
	}

	public String getBzxx() {
		return bzxx;
	}

	public void setBzxx(String bzxx) {
		this.bzxx = bzxx;
	}

	public Integer getYpcd() {
		return ypcd;
	}

	public void setYpcd(Integer ypcd) {
		this.ypcd = ypcd;
	}

	public Integer getYfsb() {
		return yfsb;
	}

	public void setYfsb(Integer yfsb) {
		this.yfsb = yfsb;
	}

	public Timestamp getDysj() {
		return dysj;
	}

	public void setDysj(Timestamp dysj) {
		this.dysj = dysj;
	}

	public Timestamp getYysj() {
		return yysj;
	}

	public void setYysj(Timestamp yysj) {
		this.yysj = yysj;
	}

	public Integer getYzzh() {
		return yzzh;
	}

	public void setYzzh(Integer yzzh) {
		this.yzzh = yzzh;
	}

	

}
