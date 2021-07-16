   
package com.buit.cis.nurse.request;

import java.util.List;

import com.buit.commons.PageQuery;
import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


/**
 * 类名称：NisYlxsscbd<br> 
 * 类描述：压力性损伤预报、传报单<br>
 * @author GONGFANGZHOU
 */
@ApiModel(value="压力性损伤预报、传报单_PrintReq")
public class NisYlxsscbdPrintReq  extends  PageQuery{
    /**
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
	*/
	private static final long serialVersionUID = 1L;
	@ApiModelProperty(value="翻身情况1")
    private String xm;
	@ApiModelProperty(value="翻身情况1")
    private String csrq;
	@ApiModelProperty(value="翻身情况1")
    private String xb;
	@ApiModelProperty(value="翻身情况1")
    private String nl;
	@ApiModelProperty(value="翻身情况1")
    private String ks;
	@ApiModelProperty(value="翻身情况1")
    private String bq;
	@ApiModelProperty(value="翻身情况1")
    private String ch;
	@ApiModelProperty(value="翻身情况1")
    private String zyhm;
	@ApiModelProperty(value="翻身情况1")
    private String zd;
	@ApiModelProperty(value="翻身情况1")
    private String ryrq;
    @ApiModelProperty(value="翻身情况1")
    private String fsqk1;
    @ApiModelProperty(value="翻身情况2")
    private String fsqk2;
    @ApiModelProperty(value="翻身情况3")
    private String fsqk3;    
    @ApiModelProperty(value="BMI1")
    private String bmi1;
    @ApiModelProperty(value="BMI2")
    private String bmi2;
    @ApiModelProperty(value="BMI3")
    private String bmi3;
    @ApiModelProperty(value="BMI4")
    private String bmi4;
    @ApiModelProperty(value="BMI5")
    private String bmi5;
    @ApiModelProperty(value="BMI6")
    private String bmi6;
    @ApiModelProperty(value="BMI7")
    private String bmi7;
    @ApiModelProperty(value="低蛋白血症1")
    private String ddbxz1;
    @ApiModelProperty(value="低蛋白血症2")
    private String ddbxz2;
    @ApiModelProperty(value="进食1")
    private String js1;
    @ApiModelProperty(value="进食2")
    private String js2;
    @ApiModelProperty(value="糖尿病1")
    private String tnb1;
    @ApiModelProperty(value="糖尿病2")
    private String tnb2;
    @ApiModelProperty(value="评估者")
    private String pgz;
    @ApiModelProperty(value="日期")
    private String rq;
    @JsonIgnore
    @ApiModelProperty(value="机构id")
    private Integer jgid;
    @ApiModelProperty(value="报告日期")
    private String bgsj;
    @ApiModelProperty(value="手术时间1")
    private String sssj1;
    @ApiModelProperty(value="手术时间2")
    private String sssj2;
    @ApiModelProperty(value="手术中舒张压")
    private String ssszy1;
    @ApiModelProperty(value="手术中舒张压")
    private String ssszy2;
    @ApiModelProperty(value="监控随访集合")
    List<NisYlxssjksfPrintReq> nisYlxssjksfList;
    @ApiModelProperty(value="转归情况集合")
    List<NisYlxsszgPrintReq> nisYlxsszgList;
    
	public String getXm() {
		return xm;
	}
	public void setXm(String xm) {
		this.xm = xm;
	}
	public String getCsrq() {
		return csrq;
	}
	public void setCsrq(String csrq) {
		this.csrq = csrq;
	}
	public String getXb() {
		return xb;
	}
	public void setXb(String xb) {
		this.xb = xb;
	}
	public String getNl() {
		return nl;
	}
	public void setNl(String nl) {
		this.nl = nl;
	}
	public String getKs() {
		return ks;
	}
	public void setKs(String ks) {
		this.ks = ks;
	}
	public String getBq() {
		return bq;
	}
	public void setBq(String bq) {
		this.bq = bq;
	}
	public String getCh() {
		return ch;
	}
	public void setCh(String ch) {
		this.ch = ch;
	}
	public String getZyhm() {
		return zyhm;
	}
	public void setZyhm(String zyhm) {
		this.zyhm = zyhm;
	}
	public String getZd() {
		return zd;
	}
	public void setZd(String zd) {
		this.zd = zd;
	}
	public String getRyrq() {
		return ryrq;
	}
	public void setRyrq(String ryrq) {
		this.ryrq = ryrq;
	}
	public String getFsqk1() {
		return fsqk1;
	}
	public void setFsqk1(String fsqk1) {
		this.fsqk1 = fsqk1;
	}
	public String getFsqk2() {
		return fsqk2;
	}
	public void setFsqk2(String fsqk2) {
		this.fsqk2 = fsqk2;
	}
	public String getFsqk3() {
		return fsqk3;
	}
	public void setFsqk3(String fsqk3) {
		this.fsqk3 = fsqk3;
	}
	public String getBmi1() {
		return bmi1;
	}
	public void setBmi1(String bmi1) {
		this.bmi1 = bmi1;
	}
	public String getBmi2() {
		return bmi2;
	}
	public void setBmi2(String bmi2) {
		this.bmi2 = bmi2;
	}
	public String getBmi3() {
		return bmi3;
	}
	public void setBmi3(String bmi3) {
		this.bmi3 = bmi3;
	}
	public String getBmi4() {
		return bmi4;
	}
	public void setBmi4(String bmi4) {
		this.bmi4 = bmi4;
	}
	public String getBmi5() {
		return bmi5;
	}
	public void setBmi5(String bmi5) {
		this.bmi5 = bmi5;
	}
	public String getBmi6() {
		return bmi6;
	}
	public void setBmi6(String bmi6) {
		this.bmi6 = bmi6;
	}
	public String getBmi7() {
		return bmi7;
	}
	public void setBmi7(String bmi7) {
		this.bmi7 = bmi7;
	}
	public String getDdbxz1() {
		return ddbxz1;
	}
	public void setDdbxz1(String ddbxz1) {
		this.ddbxz1 = ddbxz1;
	}
	public String getDdbxz2() {
		return ddbxz2;
	}
	public void setDdbxz2(String ddbxz2) {
		this.ddbxz2 = ddbxz2;
	}
	public String getJs1() {
		return js1;
	}
	public void setJs1(String js1) {
		this.js1 = js1;
	}
	public String getJs2() {
		return js2;
	}
	public void setJs2(String js2) {
		this.js2 = js2;
	}
	public String getTnb1() {
		return tnb1;
	}
	public void setTnb1(String tnb1) {
		this.tnb1 = tnb1;
	}
	public String getTnb2() {
		return tnb2;
	}
	public void setTnb2(String tnb2) {
		this.tnb2 = tnb2;
	}
	public String getPgz() {
		return pgz;
	}
	public void setPgz(String pgz) {
		this.pgz = pgz;
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
	public String getBgsj() {
		return bgsj;
	}
	public void setBgsj(String bgsj) {
		this.bgsj = bgsj;
	}
	public String getSssj1() {
		return sssj1;
	}
	public void setSssj1(String sssj1) {
		this.sssj1 = sssj1;
	}
	public String getSssj2() {
		return sssj2;
	}
	public void setSssj2(String sssj2) {
		this.sssj2 = sssj2;
	}
	public String getSsszy1() {
		return ssszy1;
	}
	public void setSsszy1(String ssszy1) {
		this.ssszy1 = ssszy1;
	}
	public String getSsszy2() {
		return ssszy2;
	}
	public void setSsszy2(String ssszy2) {
		this.ssszy2 = ssszy2;
	}
	public List<NisYlxssjksfPrintReq> getNisYlxssjksfList() {
		return nisYlxssjksfList;
	}
	public void setNisYlxssjksfList(List<NisYlxssjksfPrintReq> nisYlxssjksfList) {
		this.nisYlxssjksfList = nisYlxssjksfList;
	}
	public List<NisYlxsszgPrintReq> getNisYlxsszgList() {
		return nisYlxsszgList;
	}
	public void setNisYlxsszgList(List<NisYlxsszgPrintReq> nisYlxsszgList) {
		this.nisYlxsszgList = nisYlxsszgList;
	}
    
	
    
}