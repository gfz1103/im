   
package com.buit.cis.nurse.request;

import java.sql.Timestamp;

import com.buit.commons.PageQuery;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


/**
 * 类名称：NisLzdngpgb<br> 
 * 类描述：留置导尿管感染风险因素评估表<br>
 * @author GONGFANGZHOU
 */
@ApiModel(value="留置导尿管感染风险因素评估表_PrintReq")
public class NisLzdngpgbPrintReq  extends  PageQuery{
    @ApiModelProperty(value="一般情况1")
    private String ybqk1;
    @ApiModelProperty(value="一般情况2")
    private String ybqk2;
    @ApiModelProperty(value="一般情况3")
    private String ybqk3;
    @ApiModelProperty(value="一般情况4")
    private String ybqk4;
    @ApiModelProperty(value="一般情况5")
    private String ybqk5;
    @ApiModelProperty(value="一般情况6")
    private String ybqk6;
    @ApiModelProperty(value="一般情况7")
    private String ybqk7;
    @ApiModelProperty(value="导尿管类型1")
    private String dnglx1;
    @ApiModelProperty(value="导尿管类型2")
    private String dnglx2;
    @ApiModelProperty(value="导尿管类型3")
    private String dnglx3;
    @ApiModelProperty(value="留置时间1")
    private String lzsj1;
    @ApiModelProperty(value="留置时间2")
    private String lzsj2;
    @ApiModelProperty(value="留置时间3")
    private String lzsj3;
    @ApiModelProperty(value="尿液性状1")
    private String nyxz1;
    @ApiModelProperty(value="尿液性状2")
    private String nyxz2;
    @ApiModelProperty(value="尿液性状3")
    private String nyxz3;
    @ApiModelProperty(value="膀胱冲洗")
    private String pgcx;
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
	public String getYbqk1() {
		return ybqk1;
	}
	public void setYbqk1(String ybqk1) {
		this.ybqk1 = ybqk1;
	}
	public String getYbqk2() {
		return ybqk2;
	}
	public void setYbqk2(String ybqk2) {
		this.ybqk2 = ybqk2;
	}
	public String getYbqk3() {
		return ybqk3;
	}
	public void setYbqk3(String ybqk3) {
		this.ybqk3 = ybqk3;
	}
	public String getYbqk4() {
		return ybqk4;
	}
	public void setYbqk4(String ybqk4) {
		this.ybqk4 = ybqk4;
	}
	public String getYbqk5() {
		return ybqk5;
	}
	public void setYbqk5(String ybqk5) {
		this.ybqk5 = ybqk5;
	}
	public String getYbqk6() {
		return ybqk6;
	}
	public void setYbqk6(String ybqk6) {
		this.ybqk6 = ybqk6;
	}
	public String getYbqk7() {
		return ybqk7;
	}
	public void setYbqk7(String ybqk7) {
		this.ybqk7 = ybqk7;
	}
	public String getDnglx1() {
		return dnglx1;
	}
	public void setDnglx1(String dnglx1) {
		this.dnglx1 = dnglx1;
	}
	public String getDnglx2() {
		return dnglx2;
	}
	public void setDnglx2(String dnglx2) {
		this.dnglx2 = dnglx2;
	}
	public String getDnglx3() {
		return dnglx3;
	}
	public void setDnglx3(String dnglx3) {
		this.dnglx3 = dnglx3;
	}
	public String getLzsj1() {
		return lzsj1;
	}
	public void setLzsj1(String lzsj1) {
		this.lzsj1 = lzsj1;
	}
	public String getLzsj2() {
		return lzsj2;
	}
	public void setLzsj2(String lzsj2) {
		this.lzsj2 = lzsj2;
	}
	public String getLzsj3() {
		return lzsj3;
	}
	public void setLzsj3(String lzsj3) {
		this.lzsj3 = lzsj3;
	}
	public String getNyxz1() {
		return nyxz1;
	}
	public void setNyxz1(String nyxz1) {
		this.nyxz1 = nyxz1;
	}
	public String getNyxz2() {
		return nyxz2;
	}
	public void setNyxz2(String nyxz2) {
		this.nyxz2 = nyxz2;
	}
	public String getNyxz3() {
		return nyxz3;
	}
	public void setNyxz3(String nyxz3) {
		this.nyxz3 = nyxz3;
	}
	public String getPgcx() {
		return pgcx;
	}
	public void setPgcx(String pgcx) {
		this.pgcx = pgcx;
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