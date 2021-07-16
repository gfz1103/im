   
package com.buit.cis.nurse.request;

import java.sql.Timestamp;

import com.buit.commons.PageQuery;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


/**
 * 类名称：NisFyfxpgd<br> 
 * 类描述：<br>医院获得性肺炎风险因素评估单
 * @author GONGFANGZHOU
 */
@ApiModel(value="医院获得性肺炎风险因素评估单_printReq")
public class NisFyfxpgdPrintReq  extends  PageQuery{
    @ApiModelProperty(value="年龄1")
    private String nl1;
    @ApiModelProperty(value="年龄2")
    private String nl2;
    @ApiModelProperty(value="神志1")
    private String sz1;
    @ApiModelProperty(value="神志2")
    private String sz2;
    @ApiModelProperty(value="特殊风险1")
    private String tsfx1;
    @ApiModelProperty(value="特殊风险2")
    private String tsfx2;
    @ApiModelProperty(value="特殊风险3")
    private String tsfx3;
    @ApiModelProperty(value="特殊风险4")
    private String tsfx4;
    @ApiModelProperty(value="特殊风险5")
    private String tsfx5;
    @ApiModelProperty(value="特殊风险6")
    private String tsfx6;
    @ApiModelProperty(value="特殊风险7")
    private String tsfx7;
    @ApiModelProperty(value="特殊风险8")
    private String tsfx8;
    @ApiModelProperty(value="特殊风险9")
    private String tsfx9;
    @ApiModelProperty(value="特殊风险10")
    private String tsfx10;
    @ApiModelProperty(value="特殊风险11")
    private String tsfx11;
    @ApiModelProperty(value="特殊风险12")
    private String tsfx12;
    @ApiModelProperty(value="特殊风险13")
    private String tsfx13;
    @ApiModelProperty(value="特殊风险14")
    private String tsfx14;
    @ApiModelProperty(value="呼吸类型1")
    private String hxlx1;
    @ApiModelProperty(value="呼吸类型2")
    private String hxlx2;
    @ApiModelProperty(value="呼吸类型3")
    private String hxlx3;
    @ApiModelProperty(value="气道类型1")
    private String qdlx1;
    @ApiModelProperty(value="气道类型2")
    private String qdlx2;
    @ApiModelProperty(value="气道类型3")
    private String qdlx3;
    @ApiModelProperty(value="气道类型4")
    private String qdlx4;
    @ApiModelProperty(value="机械通气1")
    private String jxtq1;
    @ApiModelProperty(value="机械通气2")
    private String jxtq2;
    @ApiModelProperty(value="机械通气3")
    private String jxtq3;
    @ApiModelProperty(value="颅胸腹部手术")
    private String lxfb;
    @ApiModelProperty(value="插管全麻")
    private String cgqm;
    @ApiModelProperty(value="护士签名")
    private String hsqm;
    @ApiModelProperty(value="护理措施")
    private String hlcs;
    @ApiModelProperty(value="总分")
    private Integer zf;
    @ApiModelProperty(value="日期")
    private String rq;
    @ApiModelProperty(value="机构", hidden = true)
    private Integer jgid;
	public String getNl1() {
		return nl1;
	}
	public void setNl1(String nl1) {
		this.nl1 = nl1;
	}
	public String getNl2() {
		return nl2;
	}
	public void setNl2(String nl2) {
		this.nl2 = nl2;
	}
	public String getSz1() {
		return sz1;
	}
	public void setSz1(String sz1) {
		this.sz1 = sz1;
	}
	public String getSz2() {
		return sz2;
	}
	public void setSz2(String sz2) {
		this.sz2 = sz2;
	}
	public String getTsfx1() {
		return tsfx1;
	}
	public void setTsfx1(String tsfx1) {
		this.tsfx1 = tsfx1;
	}
	public String getTsfx2() {
		return tsfx2;
	}
	public void setTsfx2(String tsfx2) {
		this.tsfx2 = tsfx2;
	}
	public String getTsfx3() {
		return tsfx3;
	}
	public void setTsfx3(String tsfx3) {
		this.tsfx3 = tsfx3;
	}
	public String getTsfx4() {
		return tsfx4;
	}
	public void setTsfx4(String tsfx4) {
		this.tsfx4 = tsfx4;
	}
	public String getTsfx5() {
		return tsfx5;
	}
	public void setTsfx5(String tsfx5) {
		this.tsfx5 = tsfx5;
	}
	public String getTsfx6() {
		return tsfx6;
	}
	public void setTsfx6(String tsfx6) {
		this.tsfx6 = tsfx6;
	}
	public String getTsfx7() {
		return tsfx7;
	}
	public void setTsfx7(String tsfx7) {
		this.tsfx7 = tsfx7;
	}
	public String getTsfx8() {
		return tsfx8;
	}
	public void setTsfx8(String tsfx8) {
		this.tsfx8 = tsfx8;
	}
	public String getTsfx9() {
		return tsfx9;
	}
	public void setTsfx9(String tsfx9) {
		this.tsfx9 = tsfx9;
	}
	public String getTsfx10() {
		return tsfx10;
	}
	public void setTsfx10(String tsfx10) {
		this.tsfx10 = tsfx10;
	}
	public String getTsfx11() {
		return tsfx11;
	}
	public void setTsfx11(String tsfx11) {
		this.tsfx11 = tsfx11;
	}
	public String getTsfx12() {
		return tsfx12;
	}
	public void setTsfx12(String tsfx12) {
		this.tsfx12 = tsfx12;
	}
	public String getTsfx13() {
		return tsfx13;
	}
	public void setTsfx13(String tsfx13) {
		this.tsfx13 = tsfx13;
	}
	public String getTsfx14() {
		return tsfx14;
	}
	public void setTsfx14(String tsfx14) {
		this.tsfx14 = tsfx14;
	}
	public String getHxlx1() {
		return hxlx1;
	}
	public void setHxlx1(String hxlx1) {
		this.hxlx1 = hxlx1;
	}
	public String getHxlx2() {
		return hxlx2;
	}
	public void setHxlx2(String hxlx2) {
		this.hxlx2 = hxlx2;
	}
	public String getHxlx3() {
		return hxlx3;
	}
	public void setHxlx3(String hxlx3) {
		this.hxlx3 = hxlx3;
	}
	public String getQdlx1() {
		return qdlx1;
	}
	public void setQdlx1(String qdlx1) {
		this.qdlx1 = qdlx1;
	}
	public String getQdlx2() {
		return qdlx2;
	}
	public void setQdlx2(String qdlx2) {
		this.qdlx2 = qdlx2;
	}
	public String getQdlx3() {
		return qdlx3;
	}
	public void setQdlx3(String qdlx3) {
		this.qdlx3 = qdlx3;
	}
	public String getQdlx4() {
		return qdlx4;
	}
	public void setQdlx4(String qdlx4) {
		this.qdlx4 = qdlx4;
	}
	public String getJxtq1() {
		return jxtq1;
	}
	public void setJxtq1(String jxtq1) {
		this.jxtq1 = jxtq1;
	}
	public String getJxtq2() {
		return jxtq2;
	}
	public void setJxtq2(String jxtq2) {
		this.jxtq2 = jxtq2;
	}
	public String getJxtq3() {
		return jxtq3;
	}
	public void setJxtq3(String jxtq3) {
		this.jxtq3 = jxtq3;
	}
	public String getLxfb() {
		return lxfb;
	}
	public void setLxfb(String lxfb) {
		this.lxfb = lxfb;
	}
	public String getCgqm() {
		return cgqm;
	}
	public void setCgqm(String cgqm) {
		this.cgqm = cgqm;
	}
	public String getHsqm() {
		return hsqm;
	}
	public void setHsqm(String hsqm) {
		this.hsqm = hsqm;
	}
	public String getHlcs() {
		return hlcs;
	}
	public void setHlcs(String hlcs) {
		this.hlcs = hlcs;
	}
	public Integer getZf() {
		return zf;
	}
	public void setZf(Integer zf) {
		this.zf = zf;
	}
	public String getRq() {
		return rq;
	}
	public void setRq(String rq) {
		this.rq = rq;
	}
	public Integer getJgid() {
		return jgid;
	}
	public void setJgid(Integer jgid) {
		this.jgid = jgid;
	}
    
}