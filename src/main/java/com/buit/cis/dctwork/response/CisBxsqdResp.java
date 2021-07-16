   
package com.buit.cis.dctwork.response;

import java.sql.Timestamp;

import com.buit.commons.PageQuery;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


/**
 * 类名称：CisBxsqdResp<br> 
 * 类描述：备血申请单<br>
 * @author GONGFANGZHOU
 */
@ApiModel(value="备血申请单_Resp")
public class CisBxsqdResp  extends  PageQuery{
	
	@ApiModelProperty(value="科室代码")
    private String ksdm;
	
	@ApiModelProperty(value="病人床号")
    private String brch;
	
	@ApiModelProperty(value="病人姓名")
    private String brxm;
	
	@ApiModelProperty(value="住院号码")
    private String zyhm;
	
	@ApiModelProperty(value="申请时间")
    private Timestamp sqsj;
	
	@ApiModelProperty(value="申请人代码")
    private String sqrdm;

    @ApiModelProperty(value="申请单号")
    private String sqdh;
    
    @ApiModelProperty(value="状态(0 申请/1 已发血/2 作废)")
    private String zt;

    @ApiModelProperty(value="输血品种")
    private String sxpzmc;
    
    @ApiModelProperty(value="住院号")
    private String zyh;
    
    @ApiModelProperty(value="输血时间")
    private Timestamp sxsj;
    
    @ApiModelProperty(value="作废标志(1:是0:否)")
    private Integer zfbz;

	public String getKsdm() {
		return ksdm;
	}

	public void setKsdm(String ksdm) {
		this.ksdm = ksdm;
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

	public String getZyhm() {
		return zyhm;
	}

	public void setZyhm(String zyhm) {
		this.zyhm = zyhm;
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

	public String getSqdh() {
		return sqdh;
	}

	public void setSqdh(String sqdh) {
		this.sqdh = sqdh;
	}

	public String getZt() {
		return zt;
	}

	public void setZt(String zt) {
		this.zt = zt;
	}

	public String getSxpzmc() {
		return sxpzmc;
	}

	public void setSxpzmc(String sxpzmc) {
		this.sxpzmc = sxpzmc;
	}

	public String getZyh() {
		return zyh;
	}

	public void setZyh(String zyh) {
		this.zyh = zyh;
	}

	public Timestamp getSxsj() {
		return sxsj;
	}

	public void setSxsj(Timestamp sxsj) {
		this.sxsj = sxsj;
	}

	public Integer getZfbz() {
		return zfbz;
	}

	public void setZfbz(Integer zfbz) {
		this.zfbz = zfbz;
	}
    
    
    
}
