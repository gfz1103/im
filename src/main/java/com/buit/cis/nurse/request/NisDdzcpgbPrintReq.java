   
package com.buit.cis.nurse.request;

import java.sql.Timestamp;

import com.buit.commons.PageQuery;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


/**
 * 类名称：NisDdzcpgb<br> 
 * 类描述：住院患者跌倒、坠床危险因素评估表<br>
 * @author GONGFANGZHOU
 */
@ApiModel(value="住院患者跌倒、坠床危险因素评估表_PrintReq")
public class NisDdzcpgbPrintReq  extends  PageQuery{

	@ApiModelProperty(value="年龄1")
    private String nl1;
	@ApiModelProperty(value="年龄2")
    private String nl2;
	@ApiModelProperty(value="年龄3")
    private String nl3;
    @ApiModelProperty(value="跌倒病史1")
    private String dds1;
    @ApiModelProperty(value="跌倒病史2")
    private String dds2;
    @ApiModelProperty(value="诊断")
    private String zd;
    @ApiModelProperty(value="行走辅助1")
    private String xzfz1;
    @ApiModelProperty(value="行走辅助2")
    private String xzfz2;
    @ApiModelProperty(value="行走辅助3")
    private String xzfz3;
    @ApiModelProperty(value="行走辅助4")
    private String xzfz4;
    @ApiModelProperty(value="照顾者1")
    private String zgz1;
    @ApiModelProperty(value="照顾者2")
    private String zgz2;
    @ApiModelProperty(value="步态1")
    private String bt1;
    @ApiModelProperty(value="步态2")
    private String bt2;
    @ApiModelProperty(value="步态3")
    private String bt3;
    @ApiModelProperty(value="精神状态1")
    private String jszt1;
    @ApiModelProperty(value="精神状态2")
    private String jszt2;
    @ApiModelProperty(value="行为")
    private String xw;
    @ApiModelProperty(value="听/视觉")
    private String tsj;
    @ApiModelProperty(value="沟通能力")
    private String gtnl;
    @ApiModelProperty(value="眩晕1")
    private String xy1;
    @ApiModelProperty(value="眩晕2")
    private String xy2;
    @ApiModelProperty(value="眩晕3")
    private String xy3;
    @ApiModelProperty(value="排泄")
    private String px;
    @ApiModelProperty(value="使用药物1")
    private String syyw1;
    @ApiModelProperty(value="使用药物2")
    private String syyw2;
    @ApiModelProperty(value="使用药物3")
    private String syyw3;
    @ApiModelProperty(value="使用药物4")
    private String syyw4;
    @ApiModelProperty(value="单项高危")
    private String dxgw;
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
	public String getNl3() {
		return nl3;
	}
	public void setNl3(String nl3) {
		this.nl3 = nl3;
	}
	public String getDds1() {
		return dds1;
	}
	public void setDds1(String dds1) {
		this.dds1 = dds1;
	}
	public String getDds2() {
		return dds2;
	}
	public void setDds2(String dds2) {
		this.dds2 = dds2;
	}
	public String getZd() {
		return zd;
	}
	public void setZd(String zd) {
		this.zd = zd;
	}
	public String getXzfz1() {
		return xzfz1;
	}
	public void setXzfz1(String xzfz1) {
		this.xzfz1 = xzfz1;
	}
	public String getXzfz2() {
		return xzfz2;
	}
	public void setXzfz2(String xzfz2) {
		this.xzfz2 = xzfz2;
	}
	public String getXzfz3() {
		return xzfz3;
	}
	public void setXzfz3(String xzfz3) {
		this.xzfz3 = xzfz3;
	}
	public String getXzfz4() {
		return xzfz4;
	}
	public void setXzfz4(String xzfz4) {
		this.xzfz4 = xzfz4;
	}
	public String getZgz1() {
		return zgz1;
	}
	public void setZgz1(String zgz1) {
		this.zgz1 = zgz1;
	}
	public String getZgz2() {
		return zgz2;
	}
	public void setZgz2(String zgz2) {
		this.zgz2 = zgz2;
	}
	public String getBt1() {
		return bt1;
	}
	public void setBt1(String bt1) {
		this.bt1 = bt1;
	}
	public String getBt2() {
		return bt2;
	}
	public void setBt2(String bt2) {
		this.bt2 = bt2;
	}
	public String getBt3() {
		return bt3;
	}
	public void setBt3(String bt3) {
		this.bt3 = bt3;
	}
	public String getJszt1() {
		return jszt1;
	}
	public void setJszt1(String jszt1) {
		this.jszt1 = jszt1;
	}
	public String getJszt2() {
		return jszt2;
	}
	public void setJszt2(String jszt2) {
		this.jszt2 = jszt2;
	}
	public String getXw() {
		return xw;
	}
	public void setXw(String xw) {
		this.xw = xw;
	}
	public String getTsj() {
		return tsj;
	}
	public void setTsj(String tsj) {
		this.tsj = tsj;
	}
	public String getGtnl() {
		return gtnl;
	}
	public void setGtnl(String gtnl) {
		this.gtnl = gtnl;
	}
	public String getXy1() {
		return xy1;
	}
	public void setXy1(String xy1) {
		this.xy1 = xy1;
	}
	public String getXy2() {
		return xy2;
	}
	public void setXy2(String xy2) {
		this.xy2 = xy2;
	}
	public String getXy3() {
		return xy3;
	}
	public void setXy3(String xy3) {
		this.xy3 = xy3;
	}
	public String getPx() {
		return px;
	}
	public void setPx(String px) {
		this.px = px;
	}
	public String getSyyw1() {
		return syyw1;
	}
	public void setSyyw1(String syyw1) {
		this.syyw1 = syyw1;
	}
	public String getSyyw2() {
		return syyw2;
	}
	public void setSyyw2(String syyw2) {
		this.syyw2 = syyw2;
	}
	public String getSyyw3() {
		return syyw3;
	}
	public void setSyyw3(String syyw3) {
		this.syyw3 = syyw3;
	}
	public String getSyyw4() {
		return syyw4;
	}
	public void setSyyw4(String syyw4) {
		this.syyw4 = syyw4;
	}
	public String getDxgw() {
		return dxgw;
	}
	public void setDxgw(String dxgw) {
		this.dxgw = dxgw;
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