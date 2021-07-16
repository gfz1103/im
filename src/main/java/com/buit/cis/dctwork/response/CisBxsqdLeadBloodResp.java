   
package com.buit.cis.dctwork.response;

import java.sql.Date;
import java.sql.Timestamp;

import com.buit.commons.PageQuery;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


/**
 * 类名称：CisBxsqdHomeResp<br> 
 * 类描述：备血申请单<br>
 * @author GONGFANGZHOU
 */
@ApiModel(value="备血申请单_LeadBloodResp")
public class CisBxsqdLeadBloodResp extends PageQuery {

	/**
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
	*/
	private static final long serialVersionUID = 1L;
	@ApiModelProperty(value="申请单号")
    private String sqdh;
	
	@ApiModelProperty(value="病人病区")
    private Integer brbq;
	
	@ApiModelProperty(value="病人科室")
    private Integer brks;
	
	@ApiModelProperty(value="病人床号")
    private String brch;
	
	@ApiModelProperty(value="住院号码")
    private String zyhm;
	
	@ApiModelProperty(value="病人姓名")
    private String brxm;
	
	@ApiModelProperty(value="病人性别")
    private Integer brxb;
	
	@ApiModelProperty(value="出生日期")
    private Date csny;
	
	@ApiModelProperty(value="开单时间")
    private Timestamp sqsj;
	
	@ApiModelProperty(value="开单医生")
	private String sqrdm;
	
	@ApiModelProperty(value="输血品种")
    private String sxpzmc;
	
	@ApiModelProperty(value="领血时间")
    private Timestamp lxsj;
	
	@ApiModelProperty(value="领血人")
	private String lxrdm;
	
	@ApiModelProperty(value="状态(0 申请/1 已发血/2 作废)")
    private String zt;

	public String getSqdh() {
		return sqdh;
	}

	public void setSqdh(String sqdh) {
		this.sqdh = sqdh;
	}

	public Integer getBrbq() {
		return brbq;
	}

	public void setBrbq(Integer brbq) {
		this.brbq = brbq;
	}

	public Integer getBrks() {
		return brks;
	}

	public void setBrks(Integer brks) {
		this.brks = brks;
	}

	public String getBrch() {
		return brch;
	}

	public void setBrch(String brch) {
		this.brch = brch;
	}

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

	public Integer getBrxb() {
		return brxb;
	}

	public void setBrxb(Integer brxb) {
		this.brxb = brxb;
	}

	public Date getCsny() {
		return csny;
	}

	public void setCsny(Date csny) {
		this.csny = csny;
	}

	public Timestamp getSqsj() {
		return sqsj;
	}

	public void setSqsj(Timestamp sqsj) {
		this.sqsj = sqsj;
	}

	public String getSqrdm() {
		return sqrdm;
	}

	public void setSqrdm(String sqrdm) {
		this.sqrdm = sqrdm;
	}

	public String getSxpzmc() {
		return sxpzmc;
	}

	public void setSxpzmc(String sxpzmc) {
		this.sxpzmc = sxpzmc;
	}

	public Timestamp getLxsj() {
		return lxsj;
	}

	public void setLxsj(Timestamp lxsj) {
		this.lxsj = lxsj;
	}

	public String getLxrdm() {
		return lxrdm;
	}

	public void setLxrdm(String lxrdm) {
		this.lxrdm = lxrdm;
	}

	public String getZt() {
		return zt;
	}

	public void setZt(String zt) {
		this.zt = zt;
	}

	
}
