   
package com.buit.cis.dctwork.response;

import java.math.BigDecimal;
import java.sql.Timestamp;

import com.buit.commons.PageQuery;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


/**
 * 类名称：CisHzyz<br> 
 * 类描述：住院_病区医嘱_药品提交返回数据集<br>
 * @author GONGFANGZHOU
 */
@ApiModel(value="住院_病区医嘱_ypsubmitResp")
public class CisHzyzYpSubmitResp {

    @ApiModelProperty(value="记录序号")
    private Integer jlxh;
    
    @ApiModelProperty(value="住院号")
    private Integer zyh;
    
    @ApiModelProperty(value="病人姓名")
    private String brxm;
    
    @ApiModelProperty(value="病人床号")
    private String brch;
    
    @ApiModelProperty(value="医嘱组号")
    private Integer yzzh;
    
    @ApiModelProperty(value="药嘱名称")
    private String yzmc;
    
    @ApiModelProperty(value="药品用法")
    private Integer ypyf;
    
    @ApiModelProperty(value="一次数量")
    private BigDecimal ycsl;
    
    @ApiModelProperty(value="费用次数")
    private Integer fycs;
    
    @ApiModelProperty(value="使用频次")
    private String sypc;
    
    @ApiModelProperty(value="金额")
    private BigDecimal je;
    
    @ApiModelProperty(value="开始时间")
    private Timestamp kssj;
    
    @ApiModelProperty(value="停止时间")
    private Timestamp tzsj;
    
    @ApiModelProperty(value="备注")
    private String bzxx;
    
    @ApiModelProperty(value="药房识别")
    private Integer yfsb;
    
    @ApiModelProperty(value="药品序号")
    private Integer ypxh;
    
    @ApiModelProperty(value="药品产地")
    private Integer ypcd;
    
    @ApiModelProperty(value="药房单位")
    private String yfdw;
    
    @ApiModelProperty(value="是否缺药")
    private boolean sfqy;
    
    @ApiModelProperty(value="临时医嘱")
    private Integer lsyz;

	public Integer getJlxh() {
		return jlxh;
	}

	public void setJlxh(Integer jlxh) {
		this.jlxh = jlxh;
	}

	public Integer getZyh() {
		return zyh;
	}

	public void setZyh(Integer zyh) {
		this.zyh = zyh;
	}

	public String getBrxm() {
		return brxm;
	}

	public void setBrxm(String brxm) {
		this.brxm = brxm;
	}

	public String getBrch() {
		return brch;
	}

	public void setBrch(String brch) {
		this.brch = brch;
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

	public Integer getYpyf() {
		return ypyf;
	}

	public void setYpyf(Integer ypyf) {
		this.ypyf = ypyf;
	}

	public BigDecimal getYcsl() {
		return ycsl;
	}

	public void setYcsl(BigDecimal ycsl) {
		this.ycsl = ycsl;
	}

	public Integer getFycs() {
		return fycs;
	}

	public void setFycs(Integer fycs) {
		this.fycs = fycs;
	}

	public String getSypc() {
		return sypc;
	}

	public void setSypc(String sypc) {
		this.sypc = sypc;
	}

	public BigDecimal getJe() {
		return je;
	}

	public void setJe(BigDecimal je) {
		this.je = je;
	}

	public Timestamp getKssj() {
		return kssj;
	}

	public void setKssj(Timestamp kssj) {
		this.kssj = kssj;
	}

	public Timestamp getTzsj() {
		return tzsj;
	}

	public void setTzsj(Timestamp tzsj) {
		this.tzsj = tzsj;
	}

	public String getBzxx() {
		return bzxx;
	}

	public void setBzxx(String bzxx) {
		this.bzxx = bzxx;
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

	public String getYfdw() {
		return yfdw;
	}

	public void setYfdw(String yfdw) {
		this.yfdw = yfdw;
	}

	public boolean isSfqy() {
		return sfqy;
	}

	public void setSfqy(boolean sfqy) {
		this.sfqy = sfqy;
	}

	public Integer getLsyz() {
		return lsyz;
	}

	public void setLsyz(Integer lsyz) {
		this.lsyz = lsyz;
	}
    
	
}
