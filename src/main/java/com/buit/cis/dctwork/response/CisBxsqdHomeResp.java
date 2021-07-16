   
package com.buit.cis.dctwork.response;

import java.sql.Timestamp;

import com.buit.commons.PageQuery;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


/**
 * 类名称：CisBxsqdHomeResp<br> 
 * 类描述：备血申请单<br>
 * @author GONGFANGZHOU
 */
@ApiModel(value="备血申请单_HomeResp")
public class CisBxsqdHomeResp  {

	@ApiModelProperty(value="申请单号")
    private String sqdh;
	
	@ApiModelProperty(value="病人床号")
    private String brch;
	
	@ApiModelProperty(value="病人姓名")
    private String brxm;
	
	@ApiModelProperty(value="科室代码")
    private String ksdm;
	
	@ApiModelProperty(value="临床信息")
    private String lcxx;
	
	@ApiModelProperty(value="输血原因")
    private String sxyy;
	
	@ApiModelProperty(value="申请人代码")
    private String sqrdm;
	
	@ApiModelProperty(value="申请时间")
    private Timestamp sqsj;
	
	@ApiModelProperty(value="状态(0 申请/1 已发血/2 作废)")
    private String zt;

	public String getSqdh() {
		return sqdh;
	}

	public void setSqdh(String sqdh) {
		this.sqdh = sqdh;
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

	public String getKsdm() {
		return ksdm;
	}

	public void setKsdm(String ksdm) {
		this.ksdm = ksdm;
	}

	public String getLcxx() {
		return lcxx;
	}

	public void setLcxx(String lcxx) {
		this.lcxx = lcxx;
	}

	public String getSxyy() {
		return sxyy;
	}

	public void setSxyy(String sxyy) {
		this.sxyy = sxyy;
	}

	public String getSqrdm() {
		return sqrdm;
	}

	public void setSqrdm(String sqrdm) {
		this.sqrdm = sqrdm;
	}

	public Timestamp getSqsj() {
		return sqsj;
	}

	public void setSqsj(Timestamp sqsj) {
		this.sqsj = sqsj;
	}

	public String getZt() {
		return zt;
	}

	public void setZt(String zt) {
		this.zt = zt;
	}
    
}
