   
package com.buit.cis.nurse.response;

import java.math.BigDecimal;
import java.sql.Timestamp;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


/**
 * 类名称：CisHzyzReviewInfoResp<br> 
 * 类描述：住院_病区医嘱复核列表<br>
 * @author GONGFANGZHOU
 */
@ApiModel(value="住院_病区组套医嘱_reviewinfoResp")
public class CisHzyzReviewInfoResp {
    
    @ApiModelProperty(value="住院号码")
    private String zyhm;
	
	@ApiModelProperty(value="病人姓名")
    private String brxm;
	
	@ApiModelProperty(value="病人床号")
    private String brch;
	
	@ApiModelProperty(value="药嘱名称")
    private String yzmc;
	
	@ApiModelProperty(value="一次数量")
    private BigDecimal ycsl;
	
	@ApiModelProperty(value="使用频次")
    private String sypc;
	
	@ApiModelProperty(value="药品单价")
    private BigDecimal ypdj;
	
    @ApiModelProperty(value="开嘱医生")
    private String ysgh;
    
    @ApiModelProperty(value="停止时间")
    private Timestamp tzsj;
    
    @ApiModelProperty(value="每周次数")
    private Integer mzcs;
    
    @ApiModelProperty(value="开始时间")
    private Timestamp kssj;
    
    @ApiModelProperty(value="每日次数")
    private Integer mrcs;
    
    @ApiModelProperty(value="操作工号")
    private String czgh;
    
    @ApiModelProperty(value="确认时间")
    private Timestamp qrsj;
    
    @ApiModelProperty(value="药品序号")
    private Integer ypxh;
    
    @ApiModelProperty(value="住院号")
    private Integer zyh;
    
    @ApiModelProperty(value="记录序号")
    private Integer jlxh;
    
    @ApiModelProperty(value="执行科室")
    private Integer zxks;
	
    @ApiModelProperty(value="药品用法")
    private Integer ypyf;
	
    @ApiModelProperty(value="医嘱组号")
    private Integer yzzh;
	
    @ApiModelProperty(value="婴儿判别")
    private Integer yepb;
    
    @ApiModelProperty(value="备注信息")
    private String bzxx;
    
    @ApiModelProperty(value="发药属性")
	private Integer fysx;
    
    @ApiModelProperty(value="临时医嘱")
    private Integer lsyz;
    
    @ApiModelProperty(value="医嘱判别")
    private Integer yzpb;
    
    @ApiModelProperty(value="历史标志")
    private Integer lsbz;
    
    @ApiModelProperty(value="药品类型")
    private Integer yplx;
    
    @ApiModelProperty(value="一次剂量")
    private BigDecimal ycjl;
    
    @ApiModelProperty(value="停嘱医生")
    private String tzys;

    @ApiModelProperty(value="医技主项")
    private Integer yjzx;
  
    @ApiModelProperty(value="复核工号")
    private String fhgh;

    @ApiModelProperty(value="药房识别")
    private Integer yfsb;
    
    @ApiModelProperty(value="计费标志")
    private Integer jfbz;
  
    @ApiModelProperty(value="剂量单位")
    private String jldw;
    
    @ApiModelProperty(value="组套标志")
    private Integer ztbz;
    
    @ApiModelProperty(value="处方帖数")
    private Integer cfts;
    
    @ApiModelProperty(value="药房单位")
    private String yfdw;

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

	public String getBrch() {
		return brch;
	}

	public void setBrch(String brch) {
		this.brch = brch;
	}

	public String getYzmc() {
		return yzmc;
	}

	public void setYzmc(String yzmc) {
		this.yzmc = yzmc;
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

	public Integer getMzcs() {
		return mzcs;
	}

	public void setMzcs(Integer mzcs) {
		this.mzcs = mzcs;
	}

	public Timestamp getKssj() {
		return kssj;
	}

	public void setKssj(Timestamp kssj) {
		this.kssj = kssj;
	}

	public Integer getMrcs() {
		return mrcs;
	}

	public void setMrcs(Integer mrcs) {
		this.mrcs = mrcs;
	}

	public String getCzgh() {
		return czgh;
	}

	public void setCzgh(String czgh) {
		this.czgh = czgh;
	}

	public Timestamp getQrsj() {
		return qrsj;
	}

	public void setQrsj(Timestamp qrsj) {
		this.qrsj = qrsj;
	}

	public Integer getYpxh() {
		return ypxh;
	}

	public void setYpxh(Integer ypxh) {
		this.ypxh = ypxh;
	}

	public Integer getZyh() {
		return zyh;
	}

	public void setZyh(Integer zyh) {
		this.zyh = zyh;
	}

	public Integer getJlxh() {
		return jlxh;
	}

	public void setJlxh(Integer jlxh) {
		this.jlxh = jlxh;
	}

	public Integer getZxks() {
		return zxks;
	}

	public void setZxks(Integer zxks) {
		this.zxks = zxks;
	}

	public Integer getYpyf() {
		return ypyf;
	}

	public void setYpyf(Integer ypyf) {
		this.ypyf = ypyf;
	}

	public Integer getYzzh() {
		return yzzh;
	}

	public void setYzzh(Integer yzzh) {
		this.yzzh = yzzh;
	}

	public Integer getYepb() {
		return yepb;
	}

	public void setYepb(Integer yepb) {
		this.yepb = yepb;
	}

	public String getBzxx() {
		return bzxx;
	}

	public void setBzxx(String bzxx) {
		this.bzxx = bzxx;
	}

	public Integer getFysx() {
		return fysx;
	}

	public void setFysx(Integer fysx) {
		this.fysx = fysx;
	}

	public Integer getLsyz() {
		return lsyz;
	}

	public void setLsyz(Integer lsyz) {
		this.lsyz = lsyz;
	}

	public Integer getYzpb() {
		return yzpb;
	}

	public void setYzpb(Integer yzpb) {
		this.yzpb = yzpb;
	}

	public Integer getLsbz() {
		return lsbz;
	}

	public void setLsbz(Integer lsbz) {
		this.lsbz = lsbz;
	}

	public Integer getYplx() {
		return yplx;
	}

	public void setYplx(Integer yplx) {
		this.yplx = yplx;
	}

	public BigDecimal getYcjl() {
		return ycjl;
	}

	public void setYcjl(BigDecimal ycjl) {
		this.ycjl = ycjl;
	}

	public String getTzys() {
		return tzys;
	}

	public void setTzys(String tzys) {
		this.tzys = tzys;
	}

	public Integer getYjzx() {
		return yjzx;
	}

	public void setYjzx(Integer yjzx) {
		this.yjzx = yjzx;
	}

	public String getFhgh() {
		return fhgh;
	}

	public void setFhgh(String fhgh) {
		this.fhgh = fhgh;
	}

	public Integer getYfsb() {
		return yfsb;
	}

	public void setYfsb(Integer yfsb) {
		this.yfsb = yfsb;
	}

	public Integer getJfbz() {
		return jfbz;
	}

	public void setJfbz(Integer jfbz) {
		this.jfbz = jfbz;
	}

	public String getJldw() {
		return jldw;
	}

	public void setJldw(String jldw) {
		this.jldw = jldw;
	}

	public Integer getZtbz() {
		return ztbz;
	}

	public void setZtbz(Integer ztbz) {
		this.ztbz = ztbz;
	}

	public Integer getCfts() {
		return cfts;
	}

	public void setCfts(Integer cfts) {
		this.cfts = cfts;
	}

	public String getYfdw() {
		return yfdw;
	}

	public void setYfdw(String yfdw) {
		this.yfdw = yfdw;
	}	
    
	
}
