   
package com.buit.cis.nurse.request;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

import com.buit.commons.PageQuery;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


/**
 * 类名称：NisSjkjld<br> 
 * 类描述：神经科记录单<br>
 * @author GONGFANGZHOU
 */
@ApiModel(value="神经科记录单_PrintReq")
public class NisSjkjldPrintReq  extends  PageQuery{
    /**
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
	*/
	private static final long serialVersionUID = 1L;

    @ApiModelProperty(value="神志")
    private String sz;
    @ApiModelProperty(value="格拉斯评分")
    private String glspf;
    @ApiModelProperty(value="瞳孔左")
    private String tkz;
    @ApiModelProperty(value="瞳孔右")
    private String tky;
    @ApiModelProperty(value="瞳孔左对光放射")
    private String tkzdgfs;
    @ApiModelProperty(value="瞳孔右对光放射")
    private String tkydgfs;
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
    @ApiModelProperty(value="护理记录")
    private String hljl;
    @ApiModelProperty(value="护士签名")
    private String hsqm;
    @ApiModelProperty(value="日期")
    private String rq;
    @ApiModelProperty(value="机构id", hidden = true)
    private Integer jgid;
	public String getSz() {
		return sz;
	}
	public void setSz(String sz) {
		this.sz = sz;
	}
	public String getGlspf() {
		return glspf;
	}
	public void setGlspf(String glspf) {
		this.glspf = glspf;
	}
	public String getTkz() {
		return tkz;
	}
	public void setTkz(String tkz) {
		this.tkz = tkz;
	}
	public String getTky() {
		return tky;
	}
	public void setTky(String tky) {
		this.tky = tky;
	}
	public String getTkzdgfs() {
		return tkzdgfs;
	}
	public void setTkzdgfs(String tkzdgfs) {
		this.tkzdgfs = tkzdgfs;
	}
	public String getTkydgfs() {
		return tkydgfs;
	}
	public void setTkydgfs(String tkydgfs) {
		this.tkydgfs = tkydgfs;
	}
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