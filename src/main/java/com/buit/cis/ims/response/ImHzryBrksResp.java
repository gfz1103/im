   
package com.buit.cis.ims.response;

import java.math.BigDecimal;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


/**
 * 类名称：CisHzyzZt<br> 
 * 类描述：住院_病区发药明细<br>
 * @author GONGFANGZHOU
 */
@ApiModel(value="住院_病区发药明细_fymxResp")
public class ImHzryBrksResp {

	@ApiModelProperty(value="机构id")
    private Integer jgid;
	
    @ApiModelProperty(value="药房识别")
	private Integer yfsb;
	
	@ApiModelProperty(value="药品序号")
	private Integer ypxh;
	
	@ApiModelProperty(value="药品名称")
	private String ypmc;
	
	@ApiModelProperty(value="药品产地")
	private Integer ypcd;
	
	@ApiModelProperty(value="产地名称")
	private String cdmc;
	
	@ApiModelProperty(value="药品单价")
	private BigDecimal ypdj;
	
	@ApiModelProperty(value="药品数量")
	private BigDecimal ypsl;
	
	@ApiModelProperty(value="药房名称")
	private String yfmc;
	
	@ApiModelProperty(value="药品规格")
	private String ypgg;
	
	@ApiModelProperty(value="药房单位")
	private String yfdw;
	
	@ApiModelProperty(value="药房标志")
	private Integer yfbz;
	
	@ApiModelProperty(value="领用病区")
	private Integer lybq;
	
	@ApiModelProperty(value="婴儿判别")
	private Integer yepb;
	
	@ApiModelProperty(value="自负比例")
	private BigDecimal zfbl;
	
	@ApiModelProperty(value="自负判别")
	private Integer zfpb;
	
	@ApiModelProperty(value="医嘱序号")
	private Integer yzxh;
	
	@ApiModelProperty(value="住院号")
	private Integer zyh;

	public Integer getJgid() {
		return jgid;
	}

	public void setJgid(Integer jgid) {
		this.jgid = jgid;
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

	public String getYpmc() {
		return ypmc;
	}

	public void setYpmc(String ypmc) {
		this.ypmc = ypmc;
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

	public BigDecimal getYpdj() {
		return ypdj;
	}

	public void setYpdj(BigDecimal ypdj) {
		this.ypdj = ypdj;
	}

	public BigDecimal getYpsl() {
		return ypsl;
	}

	public void setYpsl(BigDecimal ypsl) {
		this.ypsl = ypsl;
	}

	public String getYfmc() {
		return yfmc;
	}

	public void setYfmc(String yfmc) {
		this.yfmc = yfmc;
	}

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

	public Integer getYfbz() {
		return yfbz;
	}

	public void setYfbz(Integer yfbz) {
		this.yfbz = yfbz;
	}

	public Integer getLybq() {
		return lybq;
	}

	public void setLybq(Integer lybq) {
		this.lybq = lybq;
	}

	public Integer getYepb() {
		return yepb;
	}

	public void setYepb(Integer yepb) {
		this.yepb = yepb;
	}

	public BigDecimal getZfbl() {
		return zfbl;
	}

	public void setZfbl(BigDecimal zfbl) {
		this.zfbl = zfbl;
	}

	public Integer getZfpb() {
		return zfpb;
	}

	public void setZfpb(Integer zfpb) {
		this.zfpb = zfpb;
	}

	public Integer getYzxh() {
		return yzxh;
	}

	public void setYzxh(Integer yzxh) {
		this.yzxh = yzxh;
	}

	public Integer getZyh() {
		return zyh;
	}

	public void setZyh(Integer zyh) {
		this.zyh = zyh;
	}
	
}
