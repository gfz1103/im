   
package com.buit.cis.nurse.request;

import java.sql.Timestamp;

import com.buit.commons.PageQuery;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


/**
 * 类名称：NisYcwxpgb<br> 
 * 类描述：住院患者压疮危险因素评估表<br>
 * @author GONGFANGZHOU
 */
@ApiModel(value="住院患者压疮危险因素评估表_printReq")
public class NisYcwxpgbPrintReq  extends  PageQuery{
    @ApiModelProperty(value="感知1")
    private String gz1;
    @ApiModelProperty(value="感知2")
    private String gz2;
    @ApiModelProperty(value="感知3")
    private String gz3;
    @ApiModelProperty(value="感知4")
    private String gz4;
    @ApiModelProperty(value="潮湿1")
    private String cs1;
    @ApiModelProperty(value="潮湿2")
    private String cs2;
    @ApiModelProperty(value="潮湿3")
    private String cs3;
    @ApiModelProperty(value="潮湿4")
    private String cs4;
    @ApiModelProperty(value="活动1")
    private String hd1;
    @ApiModelProperty(value="活动2")
    private String hd2;
    @ApiModelProperty(value="活动3")
    private String hd3;
    @ApiModelProperty(value="活动4")
    private String hd4;
    @ApiModelProperty(value="移动1")
    private String yd1;
    @ApiModelProperty(value="移动2")
    private String yd2;
    @ApiModelProperty(value="移动3")
    private String yd3;
    @ApiModelProperty(value="移动4")
    private String yd4;
    @ApiModelProperty(value="营养1")
    private String yy1;
    @ApiModelProperty(value="营养2")
    private String yy2;
    @ApiModelProperty(value="营养3")
    private String yy3;
    @ApiModelProperty(value="营养4")
    private String yy4;
    @ApiModelProperty(value="摩擦力和剪切力1")
    private String mcjql1;
    @ApiModelProperty(value="摩擦力和剪切力2")
    private String mcjql2;
    @ApiModelProperty(value="摩擦力和剪切力3")
    private String mcjql3;
    @ApiModelProperty(value="是否院外带入")
    private String ywdr;
    @ApiModelProperty(value="总分")
    private Integer zf;
    @ApiModelProperty(value="护士签名")
    private String hsqm;
    @ApiModelProperty(value="日期")
    private String rq;
    @ApiModelProperty(value="护理措施")
    private String hlcs;
    @ApiModelProperty(value="机构id", hidden = true)
    private Integer jgid;
	public String getGz1() {
		return gz1;
	}
	public void setGz1(String gz1) {
		this.gz1 = gz1;
	}
	public String getGz2() {
		return gz2;
	}
	public void setGz2(String gz2) {
		this.gz2 = gz2;
	}
	public String getGz3() {
		return gz3;
	}
	public void setGz3(String gz3) {
		this.gz3 = gz3;
	}
	public String getGz4() {
		return gz4;
	}
	public void setGz4(String gz4) {
		this.gz4 = gz4;
	}
	public String getCs1() {
		return cs1;
	}
	public void setCs1(String cs1) {
		this.cs1 = cs1;
	}
	public String getCs2() {
		return cs2;
	}
	public void setCs2(String cs2) {
		this.cs2 = cs2;
	}
	public String getCs3() {
		return cs3;
	}
	public void setCs3(String cs3) {
		this.cs3 = cs3;
	}
	public String getCs4() {
		return cs4;
	}
	public void setCs4(String cs4) {
		this.cs4 = cs4;
	}
	public String getHd1() {
		return hd1;
	}
	public void setHd1(String hd1) {
		this.hd1 = hd1;
	}
	public String getHd2() {
		return hd2;
	}
	public void setHd2(String hd2) {
		this.hd2 = hd2;
	}
	public String getHd3() {
		return hd3;
	}
	public void setHd3(String hd3) {
		this.hd3 = hd3;
	}
	public String getHd4() {
		return hd4;
	}
	public void setHd4(String hd4) {
		this.hd4 = hd4;
	}
	public String getYd1() {
		return yd1;
	}
	public void setYd1(String yd1) {
		this.yd1 = yd1;
	}
	public String getYd2() {
		return yd2;
	}
	public void setYd2(String yd2) {
		this.yd2 = yd2;
	}
	public String getYd3() {
		return yd3;
	}
	public void setYd3(String yd3) {
		this.yd3 = yd3;
	}
	public String getYd4() {
		return yd4;
	}
	public void setYd4(String yd4) {
		this.yd4 = yd4;
	}
	public String getYy1() {
		return yy1;
	}
	public void setYy1(String yy1) {
		this.yy1 = yy1;
	}
	public String getYy2() {
		return yy2;
	}
	public void setYy2(String yy2) {
		this.yy2 = yy2;
	}
	public String getYy3() {
		return yy3;
	}
	public void setYy3(String yy3) {
		this.yy3 = yy3;
	}
	public String getYy4() {
		return yy4;
	}
	public void setYy4(String yy4) {
		this.yy4 = yy4;
	}
	public String getMcjql1() {
		return mcjql1;
	}
	public void setMcjql1(String mcjql1) {
		this.mcjql1 = mcjql1;
	}
	public String getMcjql2() {
		return mcjql2;
	}
	public void setMcjql2(String mcjql2) {
		this.mcjql2 = mcjql2;
	}
	public String getMcjql3() {
		return mcjql3;
	}
	public void setMcjql3(String mcjql3) {
		this.mcjql3 = mcjql3;
	}
	public String getYwdr() {
		return ywdr;
	}
	public void setYwdr(String ywdr) {
		this.ywdr = ywdr;
	}
	public Integer getZf() {
		return zf;
	}
	public void setZf(Integer zf) {
		this.zf = zf;
	}
	public String getHsqm() {
		return hsqm;
	}
	public void setHsqm(String hsqm) {
		this.hsqm = hsqm;
	}
	public String getRq() {
		return rq;
	}
	public void setRq(String rq) {
		this.rq = rq;
	}
	public String getHlcs() {
		return hlcs;
	}
	public void setHlcs(String hlcs) {
		this.hlcs = hlcs;
	}
	public Integer getJgid() {
		return jgid;
	}
	public void setJgid(Integer jgid) {
		this.jgid = jgid;
	}
   
}