   
package com.buit.cis.nurse.request;

import com.buit.commons.PageQuery;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


/**
 * 类名称：NisBqyjpgdPrintReq<br> 
 * 类描述：<br>患者病情预警评估单
 * @author GONGFANGZHOU
 */
@ApiModel(value="患者病情预警评估单_printReq")
public class NisBqyjpgdPrintReq  extends  PageQuery{
    @ApiModelProperty(value="心率1")
    private String xl1;
    @ApiModelProperty(value="心率2")
    private String xl2;
    @ApiModelProperty(value="心率3")
    private String xl3;
    @ApiModelProperty(value="心率4")
    private String xl4;
    @ApiModelProperty(value="心率5")
    private String xl5;
    @ApiModelProperty(value="心率6")
    private String xl6;
    @ApiModelProperty(value="收缩压1")
    private String ssy1;
    @ApiModelProperty(value="收缩压2")
    private String ssy2;
    @ApiModelProperty(value="收缩压3")
    private String ssy3;
    @ApiModelProperty(value="收缩压4")
    private String ssy4;
    @ApiModelProperty(value="收缩压5")
    private String ssy5;
    @ApiModelProperty(value="呼吸频率1")
    private String hx1;
    @ApiModelProperty(value="呼吸频率2")
    private String hx2;
    @ApiModelProperty(value="呼吸频率3")
    private String hx3;
    @ApiModelProperty(value="呼吸频率4")
    private String hx4;
    @ApiModelProperty(value="呼吸频率5")
    private String hx5;
    @ApiModelProperty(value="体温1")
    private String tw1;
    @ApiModelProperty(value="体温2")
    private String tw2;
    @ApiModelProperty(value="体温3")
    private String tw3;
    @ApiModelProperty(value="体温4")
    private String tw4;
    @ApiModelProperty(value="体温5")
    private String tw5;
    @ApiModelProperty(value="意识1")
    private String ys1;
    @ApiModelProperty(value="意识2")
    private String ys2;
    @ApiModelProperty(value="意识3")
    private String ys3;
    @ApiModelProperty(value="意识4")
    private String ys4;
    @ApiModelProperty(value="排尿1")
    private String pl1;
    @ApiModelProperty(value="排尿2")
    private String pl2;
    @ApiModelProperty(value="氧饱和度1")
    private String yphd1;
    @ApiModelProperty(value="氧饱和度2")
    private String yphd2;
    @ApiModelProperty(value="氧饱和度3")
    private String yphd3;
    @ApiModelProperty(value="氧饱和度4")
    private String yphd4;
    @ApiModelProperty(value="血糖1")
    private String xt1;
    @ApiModelProperty(value="血糖2")
    private String xt2;
    @ApiModelProperty(value="血糖3")
    private String xt3;
    @ApiModelProperty(value="血糖4")
    private String xt4;
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
	public String getXl3() {
		return xl3;
	}
	public void setXl3(String xl3) {
		this.xl3 = xl3;
	}
	public String getXl4() {
		return xl4;
	}
	public void setXl4(String xl4) {
		this.xl4 = xl4;
	}
	public String getXl5() {
		return xl5;
	}
	public void setXl5(String xl5) {
		this.xl5 = xl5;
	}
	public String getXl6() {
		return xl6;
	}
	public void setXl6(String xl6) {
		this.xl6 = xl6;
	}
	public String getSsy1() {
		return ssy1;
	}
	public void setSsy1(String ssy1) {
		this.ssy1 = ssy1;
	}
	public String getSsy2() {
		return ssy2;
	}
	public void setSsy2(String ssy2) {
		this.ssy2 = ssy2;
	}
	public String getSsy3() {
		return ssy3;
	}
	public void setSsy3(String ssy3) {
		this.ssy3 = ssy3;
	}
	public String getSsy4() {
		return ssy4;
	}
	public void setSsy4(String ssy4) {
		this.ssy4 = ssy4;
	}
	public String getSsy5() {
		return ssy5;
	}
	public void setSsy5(String ssy5) {
		this.ssy5 = ssy5;
	}
	public String getHx1() {
		return hx1;
	}
	public void setHx1(String hx1) {
		this.hx1 = hx1;
	}
	public String getHx2() {
		return hx2;
	}
	public void setHx2(String hx2) {
		this.hx2 = hx2;
	}
	public String getHx3() {
		return hx3;
	}
	public void setHx3(String hx3) {
		this.hx3 = hx3;
	}
	public String getHx4() {
		return hx4;
	}
	public void setHx4(String hx4) {
		this.hx4 = hx4;
	}
	public String getHx5() {
		return hx5;
	}
	public void setHx5(String hx5) {
		this.hx5 = hx5;
	}
	public String getTw1() {
		return tw1;
	}
	public void setTw1(String tw1) {
		this.tw1 = tw1;
	}
	public String getTw2() {
		return tw2;
	}
	public void setTw2(String tw2) {
		this.tw2 = tw2;
	}
	public String getTw3() {
		return tw3;
	}
	public void setTw3(String tw3) {
		this.tw3 = tw3;
	}
	public String getTw4() {
		return tw4;
	}
	public void setTw4(String tw4) {
		this.tw4 = tw4;
	}
	public String getTw5() {
		return tw5;
	}
	public void setTw5(String tw5) {
		this.tw5 = tw5;
	}
	public String getYs1() {
		return ys1;
	}
	public void setYs1(String ys1) {
		this.ys1 = ys1;
	}
	public String getYs2() {
		return ys2;
	}
	public void setYs2(String ys2) {
		this.ys2 = ys2;
	}
	public String getYs3() {
		return ys3;
	}
	public void setYs3(String ys3) {
		this.ys3 = ys3;
	}
	public String getYs4() {
		return ys4;
	}
	public void setYs4(String ys4) {
		this.ys4 = ys4;
	}
	public String getPl1() {
		return pl1;
	}
	public void setPl1(String pl1) {
		this.pl1 = pl1;
	}
	public String getPl2() {
		return pl2;
	}
	public void setPl2(String pl2) {
		this.pl2 = pl2;
	}
	public String getYphd1() {
		return yphd1;
	}
	public void setYphd1(String yphd1) {
		this.yphd1 = yphd1;
	}
	public String getYphd2() {
		return yphd2;
	}
	public void setYphd2(String yphd2) {
		this.yphd2 = yphd2;
	}
	public String getYphd3() {
		return yphd3;
	}
	public void setYphd3(String yphd3) {
		this.yphd3 = yphd3;
	}
	public String getYphd4() {
		return yphd4;
	}
	public void setYphd4(String yphd4) {
		this.yphd4 = yphd4;
	}
	public String getXt1() {
		return xt1;
	}
	public void setXt1(String xt1) {
		this.xt1 = xt1;
	}
	public String getXt2() {
		return xt2;
	}
	public void setXt2(String xt2) {
		this.xt2 = xt2;
	}
	public String getXt3() {
		return xt3;
	}
	public void setXt3(String xt3) {
		this.xt3 = xt3;
	}
	public String getXt4() {
		return xt4;
	}
	public void setXt4(String xt4) {
		this.xt4 = xt4;
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