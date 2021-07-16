   
package com.buit.cis.nurse.request;

import java.sql.Timestamp;

import com.buit.commons.PageQuery;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


/**
 * 类名称：NisFssrisk<br> 
 * 类描述：肺栓塞风险因素评估表<br>
 * @author GONGFANGZHOU
 */
@ApiModel(value="肺栓塞风险因素评估表_PrintReq")
public class NisFssriskPrintReq  extends  PageQuery{

    @ApiModelProperty(value="PTE或DVT病史")
    private String bs;
    @ApiModelProperty(value="1月内手术或骨折")
    private String ssgz;
    @ApiModelProperty(value="活动性肿瘤")
    private String hdxzl;
    @ApiModelProperty(value="心率1")
    private String xl1;
    @ApiModelProperty(value="心率2")
    private String xl2;
    @ApiModelProperty(value="咯血")
    private String kx;
    @ApiModelProperty(value="单侧下肢疼痛")
    private String xztt;
    @ApiModelProperty(value="下肢深静脉触痛及单侧下肢水肿")
    private String xzsz;
    @ApiModelProperty(value="年龄＞65岁")
    private String nldy;
    @ApiModelProperty(value="血液高凝")
    private String xygn;
    @ApiModelProperty(value="心脏病")
    private String xzb;
    @ApiModelProperty(value="护理措施")
    private String hlcs;
    @ApiModelProperty(value="护士签名")
    private String hsqm;
    @ApiModelProperty(value="日期")
    private String rq;
    @ApiModelProperty(value="总分")
    private Integer zf;
    @ApiModelProperty(value="机构id", hidden = true)
    private Integer jgid;
	public String getBs() {
		return bs;
	}
	public void setBs(String bs) {
		this.bs = bs;
	}
	public String getSsgz() {
		return ssgz;
	}
	public void setSsgz(String ssgz) {
		this.ssgz = ssgz;
	}
	public String getHdxzl() {
		return hdxzl;
	}
	public void setHdxzl(String hdxzl) {
		this.hdxzl = hdxzl;
	}
	public String getXl1() {
		return xl1;
	}
	public void setXl1(String xl1) {
		this.xl1 = xl1;
	}
	public String getXl2() {
		return xl2;
	}
	public void setXl2(String xl2) {
		this.xl2 = xl2;
	}
	public String getKx() {
		return kx;
	}
	public void setKx(String kx) {
		this.kx = kx;
	}
	public String getXztt() {
		return xztt;
	}
	public void setXztt(String xztt) {
		this.xztt = xztt;
	}
	public String getXzsz() {
		return xzsz;
	}
	public void setXzsz(String xzsz) {
		this.xzsz = xzsz;
	}
	public String getNldy() {
		return nldy;
	}
	public void setNldy(String nldy) {
		this.nldy = nldy;
	}
	public String getXygn() {
		return xygn;
	}
	public void setXygn(String xygn) {
		this.xygn = xygn;
	}
	public String getXzb() {
		return xzb;
	}
	public void setXzb(String xzb) {
		this.xzb = xzb;
	}
	public String getHlcs() {
		return hlcs;
	}
	public void setHlcs(String hlcs) {
		this.hlcs = hlcs;
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
	public Integer getZf() {
		return zf;
	}
	public void setZf(Integer zf) {
		this.zf = zf;
	}
	public Integer getJgid() {
		return jgid;
	}
	public void setJgid(Integer jgid) {
		this.jgid = jgid;
	}
   
    
}