   
package com.buit.cis.nurse.request;

import com.buit.commons.PageQuery;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


/**
 * 类名称：NisGdhtwxysb<br> 
 * 类描述：住院患者管道滑脱危险因素评估表<br>
 * @author GONGFANGZHOU
 */
@ApiModel(value="住院患者管道滑脱危险因素评估表_PrintReq")
public class NisGdhtwxysbPrintReq  extends  PageQuery{
	@ApiModelProperty(value="管道类型1")
    private String gdlx1;
	@ApiModelProperty(value="管道类型2")
    private String gdlx2;
	@ApiModelProperty(value="管道类型3")
    private String gdlx3;
	@ApiModelProperty(value="管道类型4")
    private String gdlx4;
	@ApiModelProperty(value="管道类型5")
    private String gdlx5;
	@ApiModelProperty(value="管道类型6")
    private String gdlx6;
	@ApiModelProperty(value="管道类型7")
    private String gdlx7;
	@ApiModelProperty(value="管道类型8")
    private String gdlx8;
	@ApiModelProperty(value="管道类型9")
    private String gdlx9;
	@ApiModelProperty(value="管道类型10")
    private String gdlx10;
	@ApiModelProperty(value="管道类型11")
    private String gdlx11;
	@ApiModelProperty(value="管道类型12")
    private String gdlx12;
	@ApiModelProperty(value="管道类型13")
    private String gdlx13;
	@ApiModelProperty(value="管道类型14")
    private String gdlx14;
    @ApiModelProperty(value="其他1")
    private String qt1;
    @ApiModelProperty(value="其他2")
    private String qt2;
    @ApiModelProperty(value="其他3")
    private String qt3;
    @ApiModelProperty(value="其他4")
    private String qt4;
    @ApiModelProperty(value="其他5")
    private String qt5;
    @ApiModelProperty(value="其他6")
    private String qt6;
    @ApiModelProperty(value="其他7")
    private String qt7;
    @ApiModelProperty(value="其他8")
    private String qt8;
    @ApiModelProperty(value="其他9")
    private String qt9;
    @ApiModelProperty(value="护理措施")
    private String hlcs;
    @ApiModelProperty(value="护士签名")
    private String hsqm;
    @ApiModelProperty(value="日期")
    private String rq;
    @ApiModelProperty(value="机构id", hidden = true)
    private Integer jgid;
    @ApiModelProperty(value="总分")
    private Integer zf;
    
	
	public String getGdlx1() {
		return gdlx1;
	}
	public void setGdlx1(String gdlx1) {
		this.gdlx1 = gdlx1;
	}
	public String getGdlx2() {
		return gdlx2;
	}
	public void setGdlx2(String gdlx2) {
		this.gdlx2 = gdlx2;
	}
	public String getGdlx3() {
		return gdlx3;
	}
	public void setGdlx3(String gdlx3) {
		this.gdlx3 = gdlx3;
	}
	public String getGdlx4() {
		return gdlx4;
	}
	public void setGdlx4(String gdlx4) {
		this.gdlx4 = gdlx4;
	}
	public String getGdlx5() {
		return gdlx5;
	}
	public void setGdlx5(String gdlx5) {
		this.gdlx5 = gdlx5;
	}
	public String getGdlx6() {
		return gdlx6;
	}
	public void setGdlx6(String gdlx6) {
		this.gdlx6 = gdlx6;
	}
	public String getGdlx7() {
		return gdlx7;
	}
	public void setGdlx7(String gdlx7) {
		this.gdlx7 = gdlx7;
	}
	public String getGdlx8() {
		return gdlx8;
	}
	public void setGdlx8(String gdlx8) {
		this.gdlx8 = gdlx8;
	}
	public String getGdlx9() {
		return gdlx9;
	}
	public void setGdlx9(String gdlx9) {
		this.gdlx9 = gdlx9;
	}
	public String getGdlx10() {
		return gdlx10;
	}
	public void setGdlx10(String gdlx10) {
		this.gdlx10 = gdlx10;
	}
	public String getGdlx11() {
		return gdlx11;
	}
	public void setGdlx11(String gdlx11) {
		this.gdlx11 = gdlx11;
	}
	public String getGdlx12() {
		return gdlx12;
	}
	public void setGdlx12(String gdlx12) {
		this.gdlx12 = gdlx12;
	}
	public String getGdlx13() {
		return gdlx13;
	}
	public void setGdlx13(String gdlx13) {
		this.gdlx13 = gdlx13;
	}
	public String getGdlx14() {
		return gdlx14;
	}
	public void setGdlx14(String gdlx14) {
		this.gdlx14 = gdlx14;
	}
	public String getQt1() {
		return qt1;
	}
	public void setQt1(String qt1) {
		this.qt1 = qt1;
	}
	public String getQt2() {
		return qt2;
	}
	public void setQt2(String qt2) {
		this.qt2 = qt2;
	}
	public String getQt3() {
		return qt3;
	}
	public void setQt3(String qt3) {
		this.qt3 = qt3;
	}
	public String getQt4() {
		return qt4;
	}
	public void setQt4(String qt4) {
		this.qt4 = qt4;
	}
	public String getQt5() {
		return qt5;
	}
	public void setQt5(String qt5) {
		this.qt5 = qt5;
	}
	public String getQt6() {
		return qt6;
	}
	public void setQt6(String qt6) {
		this.qt6 = qt6;
	}
	public String getQt7() {
		return qt7;
	}
	public void setQt7(String qt7) {
		this.qt7 = qt7;
	}
	public String getQt8() {
		return qt8;
	}
	public void setQt8(String qt8) {
		this.qt8 = qt8;
	}
	public String getQt9() {
		return qt9;
	}
	public void setQt9(String qt9) {
		this.qt9 = qt9;
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
	public Integer getJgid() {
		return jgid;
	}
	public void setJgid(Integer jgid) {
		this.jgid = jgid;
	}
	public Integer getZf() {
		return zf;
	}
	public void setZf(Integer zf) {
		this.zf = zf;
	}
   
}