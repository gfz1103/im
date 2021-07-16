package com.buit.cis.ims.response;

import java.math.BigDecimal;
import java.sql.Timestamp;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


/**
 * 类名称：ImFeeFymxYj<br>
 * 类描述：医技费用明细表<br>
 * @author ZHOUHAISHENG
 */
@ApiModel(value="医技费用明细表_PrintResp")
public class ImFeeFymxYjPrintResp {
	
	@ApiModelProperty(value="住院号")
    private Integer zyh;
    @ApiModelProperty(value="患者姓名")
    private String brxm;
	@ApiModelProperty(value="住院号码")
    private String zyhm;
    @ApiModelProperty(value="病区")
    private Integer brbq;
    @ApiModelProperty(value="科室")
    private Integer brks;
    @ApiModelProperty(value="床号")
    private String brch;
    @ApiModelProperty(value="费用日期")
    private Timestamp fyrq;
    @ApiModelProperty(value="开嘱时间")
    private Timestamp kssj;
    @ApiModelProperty(value="记录序号")
    private Integer jlxh;
    @ApiModelProperty(value="项目名称")
    private String yzmc;
    @ApiModelProperty(value="明细项目名称")
    private String mxmc;
    @ApiModelProperty(value="开嘱医生")
    private Integer ysgh;
    @ApiModelProperty(value="复核护士")
    private Integer fhgh;
    @ApiModelProperty(value="数量")
    private BigDecimal ycsl;
    @ApiModelProperty(value="单价")
    private BigDecimal ypdj;
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
	public String getZyhm() {
		return zyhm;
	}
	public void setZyhm(String zyhm) {
		this.zyhm = zyhm;
	}
	public Integer getBrbq() {
		return brbq;
	}
	public void setBrbq(Integer brbq) {
		this.brbq = brbq;
	}
	public Integer getBrks() {
		return brks;
	}
	public void setBrks(Integer brks) {
		this.brks = brks;
	}
	public String getBrch() {
		return brch;
	}
	public void setBrch(String brch) {
		this.brch = brch;
	}
	public Timestamp getFyrq() {
		return fyrq;
	}
	public void setFyrq(Timestamp fyrq) {
		this.fyrq = fyrq;
	}
	public Timestamp getKssj() {
		return kssj;
	}
	public void setKssj(Timestamp kssj) {
		this.kssj = kssj;
	}
	public Integer getJlxh() {
		return jlxh;
	}
	public void setJlxh(Integer jlxh) {
		this.jlxh = jlxh;
	}
	public String getYzmc() {
		return yzmc;
	}
	public void setYzmc(String yzmc) {
		this.yzmc = yzmc;
	}
	public String getMxmc() {
		return mxmc;
	}
	public void setMxmc(String mxmc) {
		this.mxmc = mxmc;
	}
	public Integer getYsgh() {
		return ysgh;
	}
	public void setYsgh(Integer ysgh) {
		this.ysgh = ysgh;
	}
	public Integer getFhgh() {
		return fhgh;
	}
	public void setFhgh(Integer fhgh) {
		this.fhgh = fhgh;
	}
	public BigDecimal getYcsl() {
		return ycsl;
	}
	public void setYcsl(BigDecimal ycsl) {
		this.ycsl = ycsl;
	}
	public BigDecimal getYpdj() {
		return ypdj;
	}
	public void setYpdj(BigDecimal ypdj) {
		this.ypdj = ypdj;
	}
	
   
}
