package com.buit.cis.ims.response;

import com.buit.commons.PageQuery;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;
import java.sql.Timestamp;


/**
 * 类名称：ImFeeFymxYj<br>
 * 类描述：医技费用明细表<br>
 * @author ZHOUHAISHENG
 */
@ApiModel(value="医技费用明细表_JyResp")
public class ImFeeFymxYjJyResp  extends PageQuery {

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
    @ApiModelProperty(value="开嘱时间")
    private Timestamp kssj;
    @ApiModelProperty(value="记录序号")
    private Integer jlxh;
    @ApiModelProperty(value="项目名称")
    private String yzmc;
    @ApiModelProperty(value="执行状态(0:未执行5:已执行)")
    private Integer zxzt;
    @ApiModelProperty(value="执行次数")
    private Integer zxcs;
    @ApiModelProperty(value="开嘱医生")
    private Integer ysgh;
    @ApiModelProperty(value="复核护士")
    private Integer fhgh;
    @ApiModelProperty(value="数量")
    private BigDecimal ycsl;
    @ApiModelProperty(value="单价")
    private BigDecimal ypdj;
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
	public Integer getZxzt() {
		return zxzt;
	}
	public void setZxzt(Integer zxzt) {
		this.zxzt = zxzt;
	}
	public Integer getZxcs() {
		return zxcs;
	}
	public void setZxcs(Integer zxcs) {
		this.zxcs = zxcs;
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
