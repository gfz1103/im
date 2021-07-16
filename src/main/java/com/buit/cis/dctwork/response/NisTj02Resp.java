   
package com.buit.cis.dctwork.response;

import java.math.BigDecimal;
import java.sql.Timestamp;

import com.buit.commons.PageQuery;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


/**
 * 类名称：NisTj02Resp<br> 
 * 类描述：<br>
 * @author GONGFANGZHOU
 */
@ApiModel(value="病区_提交明细退回_Resp")
public class NisTj02Resp  extends  PageQuery{

    @ApiModelProperty(value="记录序号")
    private Integer jlxh;
	
	@ApiModelProperty(value="住院号码")
    private String zyhm;
	
	@ApiModelProperty(value="病人床号")
    private String brch;
	
	@ApiModelProperty(value="病人姓名")
    private String brxm;
	
	@ApiModelProperty(value="药品名称")
    private String ypmc;
	
	@ApiModelProperty(value="产地名称")
    private String cdmc;
	
	@ApiModelProperty(value="药房规格")
    private String yfgg;
	
	@ApiModelProperty(value="药房单位")
    private String yfdw;
	
	@ApiModelProperty(value="药品单价")
    private BigDecimal ypdj;
	
	@ApiModelProperty(value="一次数量")
    private BigDecimal ycsl;
	
	@ApiModelProperty(value="发药标志")
    private Integer fybz;
	
	@ApiModelProperty(value="确认标志")
    private Integer qrbz;
	
	@ApiModelProperty(value="确认时间")
    private Timestamp qrsj;
	
	@ApiModelProperty(value="确认工号")
    private String qrgh;
	
	@ApiModelProperty(value="IM_FEE_FYMX表的主键JLXH，只有手术记的药品才会存")
    private Integer fymxjlxh;

	public Integer getJlxh() {
		return jlxh;
	}

	public void setJlxh(Integer jlxh) {
		this.jlxh = jlxh;
	}

	public String getZyhm() {
		return zyhm;
	}

	public void setZyhm(String zyhm) {
		this.zyhm = zyhm;
	}

	public String getBrch() {
		return brch;
	}

	public void setBrch(String brch) {
		this.brch = brch;
	}

	public String getBrxm() {
		return brxm;
	}

	public void setBrxm(String brxm) {
		this.brxm = brxm;
	}

	public String getYpmc() {
		return ypmc;
	}

	public void setYpmc(String ypmc) {
		this.ypmc = ypmc;
	}

	public String getCdmc() {
		return cdmc;
	}

	public void setCdmc(String cdmc) {
		this.cdmc = cdmc;
	}

	public String getYfgg() {
		return yfgg;
	}

	public void setYfgg(String yfgg) {
		this.yfgg = yfgg;
	}

	public String getYfdw() {
		return yfdw;
	}

	public void setYfdw(String yfdw) {
		this.yfdw = yfdw;
	}

	public BigDecimal getYpdj() {
		return ypdj;
	}

	public void setYpdj(BigDecimal ypdj) {
		this.ypdj = ypdj;
	}

	public BigDecimal getYcsl() {
		return ycsl;
	}

	public void setYcsl(BigDecimal ycsl) {
		this.ycsl = ycsl;
	}

	public Integer getFybz() {
		return fybz;
	}

	public void setFybz(Integer fybz) {
		this.fybz = fybz;
	}

	public Integer getQrbz() {
		return qrbz;
	}

	public void setQrbz(Integer qrbz) {
		this.qrbz = qrbz;
	}

	public Timestamp getQrsj() {
		return qrsj;
	}

	public void setQrsj(Timestamp qrsj) {
		this.qrsj = qrsj;
	}

	public String getQrgh() {
		return qrgh;
	}

	public void setQrgh(String qrgh) {
		this.qrgh = qrgh;
	}

	public Integer getFymxjlxh() {
		return fymxjlxh;
	}

	public void setFymxjlxh(Integer fymxjlxh) {
		this.fymxjlxh = fymxjlxh;
	}

	
}
