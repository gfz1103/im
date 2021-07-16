package com.buit.cis.dctwork.response;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class CisHzyzKcdjResp {

	private Integer jgid;
	private Integer ywlb;//1:门诊，2：住院
	private Integer jlxh;//取值：op_cf02主键sbxh或nis_tj02主键记录序号
	private Integer ypxh;
	private Integer ypcd;
	private BigDecimal ypsl;
	private Timestamp djsj;
	private Integer yfsb;
	private Integer yzzh;
	private String yzmc;
	private Integer ztbz;
	private Integer ztyzjlxh;
	private Integer yplx;
	
	public Integer getJgid() {
		return jgid;
	}
	public void setJgid(Integer jgid) {
		this.jgid = jgid;
	}
	public Integer getYwlb() {
		return ywlb;
	}
	public void setYwlb(Integer ywlb) {
		this.ywlb = ywlb;
	}
	public Integer getJlxh() {
		return jlxh;
	}
	public void setJlxh(Integer jlxh) {
		this.jlxh = jlxh;
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
	public BigDecimal getYpsl() {
		return ypsl;
	}
	public void setYpsl(BigDecimal ypsl) {
		this.ypsl = ypsl;
	}
	public Timestamp getDjsj() {
		return djsj;
	}
	public void setDjsj(Timestamp djsj) {
		this.djsj = djsj;
	}
	public Integer getYfsb() {
		return yfsb;
	}
	public void setYfsb(Integer yfsb) {
		this.yfsb = yfsb;
	}
	public Integer getYzzh() {
		return yzzh;
	}
	public void setYzzh(Integer yzzh) {
		this.yzzh = yzzh;
	}
	public String getYzmc() {
		return yzmc;
	}
	public void setYzmc(String yzmc) {
		this.yzmc = yzmc;
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
	public Integer getYplx() {
		return yplx;
	}
	public void setYplx(Integer yplx) {
		this.yplx = yplx;
	}
      
}
