   
package com.buit.cis.dctwork.response;

import java.math.BigDecimal;
import java.sql.Timestamp;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


/**
 * 类名称：CisHzyzOrderBookResp<br> 
 * 类描述：住院_病区医嘱_医嘱本打印<br>
 * @author GONGFANGZHOU
 */
@ApiModel(value="住院_病区医嘱_orderbookResp")
public class CisHzyzOrderBookResp {

    @ApiModelProperty(value="记录序号")
    private Integer jlxh;
    
    @ApiModelProperty(value="组套标志")
    private Integer ztbz;
    
    @ApiModelProperty(value="组套记录序号")
    private Integer ztyzjlxh;
    
    @ApiModelProperty(value="组套记录序号")
    private Timestamp kssj;
    
    @ApiModelProperty(value="药嘱名称")
    private String yzmc;
    
    @ApiModelProperty(value="执行工号")
    private Integer zxgh;
    
    @ApiModelProperty(value="开嘱医生")
    private Integer ysgh;
    
    @ApiModelProperty(value="停嘱时间")
    private Timestamp tzsj;

    @ApiModelProperty(value="停嘱医生")
    private Integer tzys;
    
    @ApiModelProperty(value="复核工号")
    private Integer fhgh;
    
    @ApiModelProperty(value="复核时间")
    private Timestamp fhsj;
    
    @ApiModelProperty(value="住院号")
    private Integer zyh;
    
    @ApiModelProperty(value="医嘱组号")
    private Integer yzzh;
    
    @ApiModelProperty(value="项目类型")
    private Integer xmlx;
    
    @ApiModelProperty(value="剂量单位")
    private String jldw;
    
    @ApiModelProperty(value="约定类别")
    private String ydlb;
    
    @ApiModelProperty(value="临时医嘱")
    private Integer lsyz;
    
    @ApiModelProperty(value="药品类型")
    private Integer yplx;
    
    @ApiModelProperty(value="一次剂量")
    private BigDecimal ycjl;
    
    @ApiModelProperty(value="转方药品")
    private Integer zfyp;
    
    @ApiModelProperty(value="计费标志")
    private Integer jfbz;
    
    @ApiModelProperty(value="备注信息")
    private String bzxx;
    
    @ApiModelProperty(value="处方贴数")
    private Integer cfts;
    
    @ApiModelProperty(value="一次数量")
    private BigDecimal ycsl;
    
    @ApiModelProperty(value="药房单位")
    private String yfdw;
    
    @ApiModelProperty(value="医技费用归并")
    private Integer yjfygb;
    
    @ApiModelProperty(value="药品用法名称")
    private String ypyfmc;
    
    @ApiModelProperty(value="使用频次名称")
    private String sypcmc;
    
    @ApiModelProperty(value="计费医嘱")
    private Integer jfyz;
    
    @ApiModelProperty(value="医技费用单位")
    private String yjfydw;
    
    @ApiModelProperty(value="医嘱类型")
    private Integer yzlx;
    
    @ApiModelProperty(value="执行护士签名")
    private String zxfhghmc;
    
    @ApiModelProperty(value="医生签名")
    private String ysghmc;
    
    @ApiModelProperty(value="核对护士签名")
    private String fhghmc;
    
    @ApiModelProperty(value="停嘱医生签名")
    private String tzysmc;
    
    @ApiModelProperty(value="停嘱护士签名")
    private String tzfhrmc;
    
    @ApiModelProperty(value="作废标志")
    private Integer zfbz;

	public Integer getJfbz() {
		return jfbz;
	}

	public void setJfbz(Integer jfbz) {
		this.jfbz = jfbz;
	}

	public Integer getJlxh() {
		return jlxh;
	}

	public void setJlxh(Integer jlxh) {
		this.jlxh = jlxh;
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

	public Integer getZxgh() {
		return zxgh;
	}

	public void setZxgh(Integer zxgh) {
		this.zxgh = zxgh;
	}

	public Integer getYsgh() {
		return ysgh;
	}

	public void setYsgh(Integer ysgh) {
		this.ysgh = ysgh;
	}

	public Timestamp getTzsj() {
		return tzsj;
	}

	public void setTzsj(Timestamp tzsj) {
		this.tzsj = tzsj;
	}

	public Integer getTzys() {
		return tzys;
	}

	public void setTzys(Integer tzys) {
		this.tzys = tzys;
	}

	public Integer getFhgh() {
		return fhgh;
	}

	public void setFhgh(Integer fhgh) {
		this.fhgh = fhgh;
	}

	public Timestamp getFhsj() {
		return fhsj;
	}

	public void setFhsj(Timestamp fhsj) {
		this.fhsj = fhsj;
	}

	public Integer getZyh() {
		return zyh;
	}

	public void setZyh(Integer zyh) {
		this.zyh = zyh;
	}

	public Integer getYzzh() {
		return yzzh;
	}

	public void setYzzh(Integer yzzh) {
		this.yzzh = yzzh;
	}

	public Integer getXmlx() {
		return xmlx;
	}

	public void setXmlx(Integer xmlx) {
		this.xmlx = xmlx;
	}

	public String getJldw() {
		return jldw;
	}

	public void setJldw(String jldw) {
		this.jldw = jldw;
	}

	public String getYdlb() {
		return ydlb;
	}

	public void setYdlb(String ydlb) {
		this.ydlb = ydlb;
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

	public BigDecimal getYcjl() {
		return ycjl;
	}

	public void setYcjl(BigDecimal ycjl) {
		this.ycjl = ycjl;
	}

	public Integer getZfyp() {
		return zfyp;
	}

	public void setZfyp(Integer zfyp) {
		this.zfyp = zfyp;
	}

	public String getBzxx() {
		return bzxx;
	}

	public void setBzxx(String bzxx) {
		this.bzxx = bzxx;
	}

	public Integer getCfts() {
		return cfts;
	}

	public void setCfts(Integer cfts) {
		this.cfts = cfts;
	}
	
	public BigDecimal getYcsl() {
		return ycsl;
	}

	public void setYcsl(BigDecimal ycsl) {
		this.ycsl = ycsl;
	}

	public String getYfdw() {
		return yfdw;
	}

	public void setYfdw(String yfdw) {
		this.yfdw = yfdw;
	}

	public Integer getYjfygb() {
		return yjfygb;
	}

	public void setYjfygb(Integer yjfygb) {
		this.yjfygb = yjfygb;
	}

	public String getYpyfmc() {
		return ypyfmc;
	}

	public void setYpyfmc(String ypyfmc) {
		this.ypyfmc = ypyfmc;
	}

	public String getSypcmc() {
		return sypcmc;
	}

	public void setSypcmc(String sypcmc) {
		this.sypcmc = sypcmc;
	}

	public Integer getJfyz() {
		return jfyz;
	}

	public void setJfyz(Integer jfyz) {
		this.jfyz = jfyz;
	}

	public String getYjfydw() {
		return yjfydw;
	}

	public void setYjfydw(String yjfydw) {
		this.yjfydw = yjfydw;
	}

	public Integer getYzlx() {
		return yzlx;
	}

	public void setYzlx(Integer yzlx) {
		this.yzlx = yzlx;
	}

	public String getZxfhghmc() {
		return zxfhghmc;
	}

	public void setZxfhghmc(String zxfhghmc) {
		this.zxfhghmc = zxfhghmc;
	}

	public String getYsghmc() {
		return ysghmc;
	}

	public void setYsghmc(String ysghmc) {
		this.ysghmc = ysghmc;
	}

	public String getFhghmc() {
		return fhghmc;
	}

	public void setFhghmc(String fhghmc) {
		this.fhghmc = fhghmc;
	}

	public String getTzysmc() {
		return tzysmc;
	}

	public void setTzysmc(String tzysmc) {
		this.tzysmc = tzysmc;
	}

	public String getTzfhrmc() {
		return tzfhrmc;
	}

	public void setTzfhrmc(String tzfhrmc) {
		this.tzfhrmc = tzfhrmc;
	}

	public Integer getZfbz() {
		return zfbz;
	}

	public void setZfbz(Integer zfbz) {
		this.zfbz = zfbz;
	}
	
}
