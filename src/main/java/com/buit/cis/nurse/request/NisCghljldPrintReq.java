   
package com.buit.cis.nurse.request;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

import com.buit.commons.PageQuery;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


/**
 * 类名称：NisCghljldReq<br> 
 * 类描述：常规护理记录单<br>
 * @author GONGFANGZHOU
 */
@ApiModel(value="常规护理记录单_PrintReq")
public class NisCghljldPrintReq  extends  PageQuery{

    @ApiModelProperty(value="体温")
    private BigDecimal tw;
    @ApiModelProperty(value="脉搏")
    private Integer mb;
    @ApiModelProperty(value="呼吸")
    private Integer hx;
    @ApiModelProperty(value="BP")
    private BigDecimal bp;
    @ApiModelProperty(value="疼痛评分")
    private Integer ttpf;
    @ApiModelProperty(value="自理能力评分")
    private Integer zlnlpf;
    @ApiModelProperty(value="导管评分")
    private Integer dgpf;
    @ApiModelProperty(value="压力性损伤")
    private Integer ylxss;
    @ApiModelProperty(value="跌倒坠床评分")
    private Integer ddzcpf;
    @ApiModelProperty(value="基础护理")
    private String jchl;
    @ApiModelProperty(value="护理宣教")
    private String hlxj;
    @ApiModelProperty(value="营养")
    private String yy;
    @ApiModelProperty(value="精神心理")
    private String jsxl;
    @ApiModelProperty(value="自定义列1")
    private String zdyl1;
    @ApiModelProperty(value="自定义列2")
    private String zdyl2;
    @ApiModelProperty(value="自定义列3")
    private String zdyl3;
    @ApiModelProperty(value="自定义列4")
    private String zdyl4;
    @ApiModelProperty(value="护理记录")
    private String hljl;
    @ApiModelProperty(value="护士签名")
    private String hsqm;
    @ApiModelProperty(value="日期")
    private String rq;
    @ApiModelProperty(value="机构id", hidden = true)
    private Integer jgid;
	public BigDecimal getTw() {
		return tw;
	}
	public void setTw(BigDecimal tw) {
		this.tw = tw;
	}
	public Integer getMb() {
		return mb;
	}
	public void setMb(Integer mb) {
		this.mb = mb;
	}
	public Integer getHx() {
		return hx;
	}
	public void setHx(Integer hx) {
		this.hx = hx;
	}
	public BigDecimal getBp() {
		return bp;
	}
	public void setBp(BigDecimal bp) {
		this.bp = bp;
	}
	public Integer getTtpf() {
		return ttpf;
	}
	public void setTtpf(Integer ttpf) {
		this.ttpf = ttpf;
	}
	public Integer getZlnlpf() {
		return zlnlpf;
	}
	public void setZlnlpf(Integer zlnlpf) {
		this.zlnlpf = zlnlpf;
	}
	public Integer getDgpf() {
		return dgpf;
	}
	public void setDgpf(Integer dgpf) {
		this.dgpf = dgpf;
	}
	public Integer getYlxss() {
		return ylxss;
	}
	public void setYlxss(Integer ylxss) {
		this.ylxss = ylxss;
	}
	public Integer getDdzcpf() {
		return ddzcpf;
	}
	public void setDdzcpf(Integer ddzcpf) {
		this.ddzcpf = ddzcpf;
	}
	public String getJchl() {
		return jchl;
	}
	public void setJchl(String jchl) {
		this.jchl = jchl;
	}
	public String getHlxj() {
		return hlxj;
	}
	public void setHlxj(String hlxj) {
		this.hlxj = hlxj;
	}
	public String getYy() {
		return yy;
	}
	public void setYy(String yy) {
		this.yy = yy;
	}
	public String getJsxl() {
		return jsxl;
	}
	public void setJsxl(String jsxl) {
		this.jsxl = jsxl;
	}
	public String getZdyl1() {
		return zdyl1;
	}
	public void setZdyl1(String zdyl1) {
		this.zdyl1 = zdyl1;
	}
	public String getZdyl2() {
		return zdyl2;
	}
	public void setZdyl2(String zdyl2) {
		this.zdyl2 = zdyl2;
	}
	public String getZdyl3() {
		return zdyl3;
	}
	public void setZdyl3(String zdyl3) {
		this.zdyl3 = zdyl3;
	}
	public String getZdyl4() {
		return zdyl4;
	}
	public void setZdyl4(String zdyl4) {
		this.zdyl4 = zdyl4;
	}
	public String getHljl() {
		return hljl;
	}
	public void setHljl(String hljl) {
		this.hljl = hljl;
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

    
    
}