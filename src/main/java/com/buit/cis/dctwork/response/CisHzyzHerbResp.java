   
package com.buit.cis.dctwork.response;

import java.math.BigDecimal;
import java.sql.Timestamp;

import com.buit.commons.PageQuery;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


/**
 * 类名称：CisHzyzHerbResp<br> 
 * 类描述：住院_病区医嘱_草药<br>
 * @author GONGFANGZHOU
 */
@ApiModel(value="住院_病区医嘱_herbResp")
public class CisHzyzHerbResp {

    @ApiModelProperty(value="记录序号")
    private Integer jlxh;
    
    @ApiModelProperty(value="药品序号")
    private Integer ypxh;
    
    @ApiModelProperty(value="药嘱名称")
    private String yzmc;
    
    @ApiModelProperty(value="一次剂量")
    private BigDecimal ycjl;
    
    @ApiModelProperty(value="脚注")
    private Integer jz;
    
    @ApiModelProperty(value="一次数量")
    private BigDecimal ycsl;

    @ApiModelProperty(value="药房单位")
    private String yfdw;
    
    @ApiModelProperty(value="药房包装")
    private Integer yfbz;
    
    @ApiModelProperty(value="药品单价")
    private BigDecimal ypdj;
    
    @ApiModelProperty(value="药品类型")
    private Integer yplx;
    
    @ApiModelProperty(value="药品产地")
    private Integer ypcd;
    
    @ApiModelProperty(value="每日次数")
    private Integer mrcs;
    
    @ApiModelProperty(value="医嘱执行时间")
    private String yzzxsj;
    
    @ApiModelProperty(value="首日次数")
    private Integer srcs;
    
    @ApiModelProperty(value="发药方式")
    private Integer fyfs;
    
    @ApiModelProperty(value="使用频次")
    private String sypc;
    
    @ApiModelProperty(value="项目类型")
    private Integer xmlx;
    
    @ApiModelProperty(value="药房识别")
    private Integer yfsb;
    
    @ApiModelProperty(value="剂量单位")
    private String jldw;
    
    @ApiModelProperty(value="药品剂量")
    private BigDecimal ypjl;
    
    @ApiModelProperty(value="住院号")
    private Integer zyh;

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

	public Integer getJz() {
		return jz;
	}

	public void setJz(Integer jz) {
		this.jz = jz;
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

	public Integer getYfbz() {
		return yfbz;
	}

	public void setYfbz(Integer yfbz) {
		this.yfbz = yfbz;
	}

	public BigDecimal getYpdj() {
		return ypdj;
	}

	public void setYpdj(BigDecimal ypdj) {
		this.ypdj = ypdj;
	}

	public Integer getYplx() {
		return yplx;
	}

	public void setYplx(Integer yplx) {
		this.yplx = yplx;
	}

	public Integer getYpcd() {
		return ypcd;
	}

	public void setYpcd(Integer ypcd) {
		this.ypcd = ypcd;
	}

	public Integer getMrcs() {
		return mrcs;
	}

	public void setMrcs(Integer mrcs) {
		this.mrcs = mrcs;
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

	public Integer getFyfs() {
		return fyfs;
	}

	public void setFyfs(Integer fyfs) {
		this.fyfs = fyfs;
	}

	public String getSypc() {
		return sypc;
	}

	public void setSypc(String sypc) {
		this.sypc = sypc;
	}

	public Integer getXmlx() {
		return xmlx;
	}

	public void setXmlx(Integer xmlx) {
		this.xmlx = xmlx;
	}

	public Integer getYfsb() {
		return yfsb;
	}

	public void setYfsb(Integer yfsb) {
		this.yfsb = yfsb;
	}

	public String getJldw() {
		return jldw;
	}

	public void setJldw(String jldw) {
		this.jldw = jldw;
	}

	public BigDecimal getYpjl() {
		return ypjl;
	}

	public void setYpjl(BigDecimal ypjl) {
		this.ypjl = ypjl;
	}

	public Integer getZyh() {
		return zyh;
	}

	public void setZyh(Integer zyh) {
		this.zyh = zyh;
	}
    
	
}
