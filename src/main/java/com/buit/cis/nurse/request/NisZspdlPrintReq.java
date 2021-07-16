   
package com.buit.cis.nurse.request;

import java.sql.Timestamp;

import com.buit.commons.PageQuery;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


/**
 * 类名称：NisZspdl<br> 
 * 类描述：Barthel指数平定量表<br>
 * @author GONGFANGZHOU
 */
@ApiModel(value="Barthel指数平定量表_Req")
public class NisZspdlPrintReq  extends  PageQuery{
    @ApiModelProperty(value="进食1")
    private String js1;
    @ApiModelProperty(value="进食2")
    private String js2;
    @ApiModelProperty(value="进食3")
    private String js3;
    @ApiModelProperty(value="洗澡1")
    private String xz1;
    @ApiModelProperty(value="洗澡2")
    private String xz2;
    @ApiModelProperty(value="修饰1")
    private String xs1;
    @ApiModelProperty(value="修饰2")
    private String xs2;
    @ApiModelProperty(value="穿衣1")
    private String cy1;
    @ApiModelProperty(value="穿衣2")
    private String cy2;
    @ApiModelProperty(value="穿衣3")
    private String cy3;
    @ApiModelProperty(value="控制大便1")
    private String kzdb1;
    @ApiModelProperty(value="控制大便2")
    private String kzdb2;
    @ApiModelProperty(value="控制大便3")
    private String kzdb3;
    @ApiModelProperty(value="控制小便1")
    private String kzxb1;
    @ApiModelProperty(value="控制小便2")
    private String kzxb2;
    @ApiModelProperty(value="控制小便3")
    private String kzxb3;
    @ApiModelProperty(value="如厕1")
    private String rc1;
    @ApiModelProperty(value="如厕2")
    private String rc2;
    @ApiModelProperty(value="如厕3")
    private String rc3;
    @ApiModelProperty(value="床椅转移1")
    private String cyzy1;
    @ApiModelProperty(value="床椅转移2")
    private String cyzy2;
    @ApiModelProperty(value="床椅转移3")
    private String cyzy3;
    @ApiModelProperty(value="床椅转移4")
    private String cyzy4;
    @ApiModelProperty(value="平地行走1")
    private String pdxz1;
    @ApiModelProperty(value="平地行走2")
    private String pdxz2;
    @ApiModelProperty(value="平地行走3")
    private String pdxz3;
    @ApiModelProperty(value="平地行走4")
    private String pdxz4;
    @ApiModelProperty(value="上下楼梯1")
    private String sxlt1;
    @ApiModelProperty(value="上下楼梯2")
    private String sxlt2;
    @ApiModelProperty(value="上下楼梯3")
    private String sxlt3;
    @ApiModelProperty(value="护士签名")
    private String hsqm;
    @ApiModelProperty(value="总分")
    private Integer zf;
    @ApiModelProperty(value="自立能力等级")
    private String dj;
    @ApiModelProperty(value="日期")
    private String rq;
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
	public String getJs3() {
		return js3;
	}
	public void setJs3(String js3) {
		this.js3 = js3;
	}
	public String getXz1() {
		return xz1;
	}
	public void setXz1(String xz1) {
		this.xz1 = xz1;
	}
	public String getXz2() {
		return xz2;
	}
	public void setXz2(String xz2) {
		this.xz2 = xz2;
	}
	public String getXs1() {
		return xs1;
	}
	public void setXs1(String xs1) {
		this.xs1 = xs1;
	}
	public String getXs2() {
		return xs2;
	}
	public void setXs2(String xs2) {
		this.xs2 = xs2;
	}
	public String getCy1() {
		return cy1;
	}
	public void setCy1(String cy1) {
		this.cy1 = cy1;
	}
	public String getCy2() {
		return cy2;
	}
	public void setCy2(String cy2) {
		this.cy2 = cy2;
	}
	public String getCy3() {
		return cy3;
	}
	public void setCy3(String cy3) {
		this.cy3 = cy3;
	}
	public String getKzdb1() {
		return kzdb1;
	}
	public void setKzdb1(String kzdb1) {
		this.kzdb1 = kzdb1;
	}
	public String getKzdb2() {
		return kzdb2;
	}
	public void setKzdb2(String kzdb2) {
		this.kzdb2 = kzdb2;
	}
	public String getKzdb3() {
		return kzdb3;
	}
	public void setKzdb3(String kzdb3) {
		this.kzdb3 = kzdb3;
	}
	public String getKzxb1() {
		return kzxb1;
	}
	public void setKzxb1(String kzxb1) {
		this.kzxb1 = kzxb1;
	}
	public String getKzxb2() {
		return kzxb2;
	}
	public void setKzxb2(String kzxb2) {
		this.kzxb2 = kzxb2;
	}
	public String getKzxb3() {
		return kzxb3;
	}
	public void setKzxb3(String kzxb3) {
		this.kzxb3 = kzxb3;
	}
	public String getRc1() {
		return rc1;
	}
	public void setRc1(String rc1) {
		this.rc1 = rc1;
	}
	public String getRc2() {
		return rc2;
	}
	public void setRc2(String rc2) {
		this.rc2 = rc2;
	}
	public String getRc3() {
		return rc3;
	}
	public void setRc3(String rc3) {
		this.rc3 = rc3;
	}
	public String getCyzy1() {
		return cyzy1;
	}
	public void setCyzy1(String cyzy1) {
		this.cyzy1 = cyzy1;
	}
	public String getCyzy2() {
		return cyzy2;
	}
	public void setCyzy2(String cyzy2) {
		this.cyzy2 = cyzy2;
	}
	public String getCyzy3() {
		return cyzy3;
	}
	public void setCyzy3(String cyzy3) {
		this.cyzy3 = cyzy3;
	}
	public String getCyzy4() {
		return cyzy4;
	}
	public void setCyzy4(String cyzy4) {
		this.cyzy4 = cyzy4;
	}
	public String getPdxz1() {
		return pdxz1;
	}
	public void setPdxz1(String pdxz1) {
		this.pdxz1 = pdxz1;
	}
	public String getPdxz2() {
		return pdxz2;
	}
	public void setPdxz2(String pdxz2) {
		this.pdxz2 = pdxz2;
	}
	public String getPdxz3() {
		return pdxz3;
	}
	public void setPdxz3(String pdxz3) {
		this.pdxz3 = pdxz3;
	}
	public String getPdxz4() {
		return pdxz4;
	}
	public void setPdxz4(String pdxz4) {
		this.pdxz4 = pdxz4;
	}
	public String getSxlt1() {
		return sxlt1;
	}
	public void setSxlt1(String sxlt1) {
		this.sxlt1 = sxlt1;
	}
	public String getSxlt2() {
		return sxlt2;
	}
	public void setSxlt2(String sxlt2) {
		this.sxlt2 = sxlt2;
	}
	public String getSxlt3() {
		return sxlt3;
	}
	public void setSxlt3(String sxlt3) {
		this.sxlt3 = sxlt3;
	}
	public String getHsqm() {
		return hsqm;
	}
	public void setHsqm(String hsqm) {
		this.hsqm = hsqm;
	}
	public Integer getZf() {
		return zf;
	}
	public void setZf(Integer zf) {
		this.zf = zf;
	}
	public String getDj() {
		return dj;
	}
	public void setDj(String dj) {
		this.dj = dj;
	}
	public String getRq() {
		return rq;
	}
	public void setRq(String rq) {
		this.rq = rq;
	}
   
    
}