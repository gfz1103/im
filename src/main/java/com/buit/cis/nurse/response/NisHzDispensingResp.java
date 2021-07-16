   
package com.buit.cis.nurse.response;

import java.math.BigDecimal;
import java.sql.Timestamp;

import com.buit.commons.PageQuery;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


/**
 * 类名称：NisHzDispensingResp<br> 
 * 类描述：病区发药<br>
 * @author GONGFANGZHOU
 */
@ApiModel(value="病区发药_HzHzDispensingResp")
public class NisHzDispensingResp  extends  PageQuery{
    /**
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
	*/
	private static final long serialVersionUID = 1L;
	@ApiModelProperty(value="住院号码")
    private String zyhm;
	@ApiModelProperty(value="病人姓名")
    private String brxm;
	@ApiModelProperty(value="领药病区")
    private String lybq;
	@ApiModelProperty(value="病人床号")
    private String brch;
	@ApiModelProperty(value="日期")
    private Timestamp fyrq;
	@ApiModelProperty(value="药品名称")
    private String ypmc;
	@ApiModelProperty(value="药品规格")
    private String ypgg;
    @ApiModelProperty(value="药房单位")
    private String yfdw;
    @ApiModelProperty(value="数量")
    private BigDecimal ypsl;
    @ApiModelProperty(value="零售金额")
    private BigDecimal lsje;
    @ApiModelProperty(value="产地名称")
    private String cdmc;
    @ApiModelProperty(value="操作人")
    private String personname;
    @ApiModelProperty(value="药品单价")
    private BigDecimal ypdj; 
    @ApiModelProperty(value="药品序号")
    private Integer ypxh;
    @ApiModelProperty(value="药品属性")
    private Integer ypsx;
    @ApiModelProperty(value="发药方式")
    private Integer fyfs;
    @ApiModelProperty(value="计费日期")
    private Timestamp jfrq;
    @ApiModelProperty(value="药品产地")
    private Integer ypcd;
    @ApiModelProperty(value="药房识别")
    private Integer yfsb;
    @ApiModelProperty(value="发药类型")
    private Integer fylx;
	public String getZyhm() {
		return zyhm;
	}
	public void setZyhm(String zyhm) {
		this.zyhm = zyhm;
	}
	public String getBrxm() {
		return brxm;
	}
	public void setBrxm(String brxm) {
		this.brxm = brxm;
	}
	public String getLybq() {
		return lybq;
	}
	public void setLybq(String lybq) {
		this.lybq = lybq;
	}
	public String getBrch() {
		return brch;
	}
	public void setBrch(String brch) {
		this.brch = brch;
	}
	public Timestamp getFyrq() {
		return fyrq;
	}
	public void setFyrq(Timestamp fyrq) {
		this.fyrq = fyrq;
	}
	public String getYpmc() {
		return ypmc;
	}
	public void setYpmc(String ypmc) {
		this.ypmc = ypmc;
	}
	public String getYpgg() {
		return ypgg;
	}
	public void setYpgg(String ypgg) {
		this.ypgg = ypgg;
	}
	public String getYfdw() {
		return yfdw;
	}
	public void setYfdw(String yfdw) {
		this.yfdw = yfdw;
	}
	public BigDecimal getYpsl() {
		return ypsl;
	}
	public void setYpsl(BigDecimal ypsl) {
		this.ypsl = ypsl;
	}
	public BigDecimal getLsje() {
		return lsje;
	}
	public void setLsje(BigDecimal lsje) {
		this.lsje = lsje;
	}
	public String getCdmc() {
		return cdmc;
	}
	public void setCdmc(String cdmc) {
		this.cdmc = cdmc;
	}
	public String getPersonname() {
		return personname;
	}
	public void setPersonname(String personname) {
		this.personname = personname;
	}
	public BigDecimal getYpdj() {
		return ypdj;
	}
	public void setYpdj(BigDecimal ypdj) {
		this.ypdj = ypdj;
	}
	public Integer getYpxh() {
		return ypxh;
	}
	public void setYpxh(Integer ypxh) {
		this.ypxh = ypxh;
	}
	public Integer getYpsx() {
		return ypsx;
	}
	public void setYpsx(Integer ypsx) {
		this.ypsx = ypsx;
	}
	public Integer getFyfs() {
		return fyfs;
	}
	public void setFyfs(Integer fyfs) {
		this.fyfs = fyfs;
	}
	public Timestamp getJfrq() {
		return jfrq;
	}
	public void setJfrq(Timestamp jfrq) {
		this.jfrq = jfrq;
	}
	public Integer getYpcd() {
		return ypcd;
	}
	public void setYpcd(Integer ypcd) {
		this.ypcd = ypcd;
	}
	public Integer getYfsb() {
		return yfsb;
	}
	public void setYfsb(Integer yfsb) {
		this.yfsb = yfsb;
	}
	public Integer getFylx() {
		return fylx;
	}
	public void setFylx(Integer fylx) {
		this.fylx = fylx;
	}

	
    
   
}