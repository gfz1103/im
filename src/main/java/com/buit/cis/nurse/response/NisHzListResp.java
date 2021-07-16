package com.buit.cis.nurse.response;

import java.sql.Date;
import java.sql.Timestamp;

import com.buit.commons.PageQuery;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 类名称：NisHzListResp<br> 
 * 类描述：病区一日清单<br>
 * @author GONGFANGZHOU
 */
@ApiModel(value="病区一日清单_HzListResp")
public class NisHzListResp extends PageQuery{
	
	/**
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
	*/
	private static final long serialVersionUID = 1L;
    
    @ApiModelProperty(value="入院诊断icd10")
	private String ryzd;
	
	@ApiModelProperty(value="住院号")
	private Integer zyh;

	@ApiModelProperty(value="住院号码")
	private String zyhm;
	
	@ApiModelProperty(value="病人姓名")
	private String brxm;
	
	@ApiModelProperty(value="出生日期")
	private Date csny;
	
	@ApiModelProperty(value="病人性别")
	private Integer brxb;
	
	@ApiModelProperty(value="身份证号")
	private String sfzh;
	
	@ApiModelProperty(value="病人性质")
	private Integer brxz;
	
	@ApiModelProperty(value="病人科室")
	private Integer brks;
	
	@ApiModelProperty(value="病人病区")
	private Integer brbq;
	
	@ApiModelProperty(value="病人床号")
	private String brch;
	
	@ApiModelProperty(value="工作单位")
	private String gzdw;
	
	@ApiModelProperty(value="入院日期")
	private Timestamp ryrq;
	
	@ApiModelProperty(value="出院判别")
	private Integer cypb;
	
	@ApiModelProperty(value="出院日期")
	private Timestamp cyrq;
	
	@ApiModelProperty(value="床位发票")
	private Integer cwfp;
	
	@ApiModelProperty(value="未结算天数")
	private Integer wjsts;
	
	public String getRyzd() {
		return ryzd;
	}

	public void setRyzd(String ryzd) {
		this.ryzd = ryzd;
	}

	public Integer getZyh() {
		return zyh;
	}

	public void setZyh(Integer zyh) {
		this.zyh = zyh;
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

	public Date getCsny() {
		return csny;
	}

	public void setCsny(Date csny) {
		this.csny = csny;
	}

	public Integer getBrxb() {
		return brxb;
	}

	public void setBrxb(Integer brxb) {
		this.brxb = brxb;
	}

	public String getSfzh() {
		return sfzh;
	}

	public void setSfzh(String sfzh) {
		this.sfzh = sfzh;
	}

	public Integer getBrxz() {
		return brxz;
	}

	public void setBrxz(Integer brxz) {
		this.brxz = brxz;
	}

	public Integer getBrks() {
		return brks;
	}

	public void setBrks(Integer brks) {
		this.brks = brks;
	}

	public Integer getBrbq() {
		return brbq;
	}

	public void setBrbq(Integer brbq) {
		this.brbq = brbq;
	}

	public String getBrch() {
		return brch;
	}

	public void setBrch(String brch) {
		this.brch = brch;
	}

	public String getGzdw() {
		return gzdw;
	}

	public void setGzdw(String gzdw) {
		this.gzdw = gzdw;
	}

	public Timestamp getRyrq() {
		return ryrq;
	}

	public void setRyrq(Timestamp ryrq) {
		this.ryrq = ryrq;
	}

	public Integer getCypb() {
		return cypb;
	}

	public void setCypb(Integer cypb) {
		this.cypb = cypb;
	}

	public Integer getCwfp() {
		return cwfp;
	}

	public void setCwfp(Integer cwfp) {
		this.cwfp = cwfp;
	}

	public Timestamp getCyrq() {
		return cyrq;
	}

	public void setCyrq(Timestamp cyrq) {
		this.cyrq = cyrq;
	}

	public Integer getWjsts() {
		return wjsts;
	}

	public void setWjsts(Integer wjsts) {
		this.wjsts = wjsts;
	}
	
	
}
