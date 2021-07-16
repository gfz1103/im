   
package com.buit.cis.dctwork.request;

import java.math.BigDecimal;
import java.sql.Timestamp;

import com.buit.commons.PageQuery;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


/**
 * 类名称：EnrJbys<br> 
 * 类描述：<br>
 * @author GONGFANGZHOU
 */
@ApiModel(value="护理记录结构保存_saveReq")
public class EnrJbysSaveReq {

	@ApiModelProperty(value="复制原态:0否 1是")
    private Integer fzyt;
	
	@ApiModelProperty(value="活动标志")
	private Integer hdbz;
	
	@ApiModelProperty(value="换行间隔")
	private Integer hhjg;
	
	@ApiModelProperty(value="结构编号")
	private Integer jgbh;
	
	@ApiModelProperty(value="结束列号")
	private Integer jslh;
	
	@ApiModelProperty(value="禁止编辑:0否 1是")
    private Integer jzbj;
	
	@ApiModelProperty(value="开始列号")
	private Integer kslh;
	
	@ApiModelProperty(value="是否必填:0否 1是")
    private Integer sfbt;
	
	@ApiModelProperty(value="数据格式:")
    private String sjgs;
	
	@ApiModelProperty(value="数据类型:1文本 2数字 3日期 4图片 DICT101")
    private Integer sjlx;
	
	@ApiModelProperty(value="项目编号")
	private Integer xmbh;
	
	@ApiModelProperty(value="项目导出")
	private Integer xmdc; 
	
	@ApiModelProperty(value="项目宽度")
	private Integer xmkd; 
	
	@ApiModelProperty(value="项目名称")
	private String xmmc;
	
	@ApiModelProperty(value="项目取值")
	private String xmqz;
	
	@ApiModelProperty(value="显示名称")
	private String xsmc;

	@ApiModelProperty(value="页面处理方式")
	private Integer ymclfs;

	@ApiModelProperty(value="元素编号:流水号")
	private Integer ysbh;
	
	@ApiModelProperty(value="元素扩展")
	private String yskz;
	
	@ApiModelProperty(value="元素类型:1输入型 2列表型 3引用型 4特殊型 5字典型 6体征型 DICT100")
	private Integer yslx;
	
    @ApiModelProperty(value="有效值上限")
    private BigDecimal yxzsx;
	
    @ApiModelProperty(value="有效值下限")
    private BigDecimal yxzxx;    
    
    @ApiModelProperty(value="正常值上限")
    private BigDecimal zczsx;
    
    @ApiModelProperty(value="正常值下限")
    private BigDecimal zczxx;
    
    @ApiModelProperty(value="自定义项目")
    private Integer zdyxm;
    
    @ApiModelProperty(value="yskzbnt")
	private String yskzbnt;

	public Integer getFzyt() {
		return fzyt;
	}

	public void setFzyt(Integer fzyt) {
		this.fzyt = fzyt;
	}

	public Integer getHdbz() {
		return hdbz;
	}

	public void setHdbz(Integer hdbz) {
		this.hdbz = hdbz;
	}

	public Integer getHhjg() {
		return hhjg;
	}

	public void setHhjg(Integer hhjg) {
		this.hhjg = hhjg;
	}

	public Integer getJgbh() {
		return jgbh;
	}

	public void setJgbh(Integer jgbh) {
		this.jgbh = jgbh;
	}

	public Integer getJslh() {
		return jslh;
	}

	public void setJslh(Integer jslh) {
		this.jslh = jslh;
	}

	public Integer getJzbj() {
		return jzbj;
	}

	public void setJzbj(Integer jzbj) {
		this.jzbj = jzbj;
	}

	public Integer getKslh() {
		return kslh;
	}

	public void setKslh(Integer kslh) {
		this.kslh = kslh;
	}

	public Integer getSfbt() {
		return sfbt;
	}

	public void setSfbt(Integer sfbt) {
		this.sfbt = sfbt;
	}

	public String getSjgs() {
		return sjgs;
	}

	public void setSjgs(String sjgs) {
		this.sjgs = sjgs;
	}

	public Integer getSjlx() {
		return sjlx;
	}

	public void setSjlx(Integer sjlx) {
		this.sjlx = sjlx;
	}

	public Integer getXmbh() {
		return xmbh;
	}

	public void setXmbh(Integer xmbh) {
		this.xmbh = xmbh;
	}

	public Integer getXmdc() {
		return xmdc;
	}

	public void setXmdc(Integer xmdc) {
		this.xmdc = xmdc;
	}

	public Integer getXmkd() {
		return xmkd;
	}

	public void setXmkd(Integer xmkd) {
		this.xmkd = xmkd;
	}

	public String getXmmc() {
		return xmmc;
	}

	public void setXmmc(String xmmc) {
		this.xmmc = xmmc;
	}

	public String getXmqz() {
		return xmqz;
	}

	public void setXmqz(String xmqz) {
		this.xmqz = xmqz;
	}

	public String getXsmc() {
		return xsmc;
	}

	public void setXsmc(String xsmc) {
		this.xsmc = xsmc;
	}

	public Integer getYmclfs() {
		return ymclfs;
	}

	public void setYmclfs(Integer ymclfs) {
		this.ymclfs = ymclfs;
	}

	public Integer getYsbh() {
		return ysbh;
	}

	public void setYsbh(Integer ysbh) {
		this.ysbh = ysbh;
	}

	public String getYskz() {
		return yskz;
	}

	public void setYskz(String yskz) {
		this.yskz = yskz;
	}

	public Integer getYslx() {
		return yslx;
	}

	public void setYslx(Integer yslx) {
		this.yslx = yslx;
	}

	public BigDecimal getYxzsx() {
		return yxzsx;
	}

	public void setYxzsx(BigDecimal yxzsx) {
		this.yxzsx = yxzsx;
	}

	public BigDecimal getYxzxx() {
		return yxzxx;
	}

	public void setYxzxx(BigDecimal yxzxx) {
		this.yxzxx = yxzxx;
	}

	public BigDecimal getZczsx() {
		return zczsx;
	}

	public void setZczsx(BigDecimal zczsx) {
		this.zczsx = zczsx;
	}

	public BigDecimal getZczxx() {
		return zczxx;
	}

	public void setZczxx(BigDecimal zczxx) {
		this.zczxx = zczxx;
	}

	public Integer getZdyxm() {
		return zdyxm;
	}

	public void setZdyxm(Integer zdyxm) {
		this.zdyxm = zdyxm;
	}

	public String getYskzbnt() {
		return yskzbnt;
	}

	public void setYskzbnt(String yskzbnt) {
		this.yskzbnt = yskzbnt;
	}

}