   
package com.buit.cis.nurse.request;

import java.sql.Timestamp;
import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


/**
 * 类名称：NisYzCostReq<br> 
 * 类描述：一日清单医嘱<br>
 * @author GONGFANGZHOU
 */
@ApiModel(value="一日清单医嘱_YzCostReq")
public class NisYzCostReq {
	
	@ApiModelProperty(value="开始时间")
	private Timestamp beginDate;
	
	@ApiModelProperty(value="结束时间")
	private Timestamp endDate;

	@ApiModelProperty(value="住院号")
	private Integer zyh;
	
	@ApiModelProperty(value="住院号码")
	private String zyhm;
	
	@ApiModelProperty(value="病人姓名")
	private String brxm;
	
	@ApiModelProperty(value="病人性质")
	private Integer brxz;
	
	@ApiModelProperty(value="科室名称")
	private String ksmc;
	
	@ApiModelProperty(value="入院日期")
	private Timestamp ryrq;
	
	@ApiModelProperty(value="出院日期")
	private Timestamp cyrq;
	
	@ApiModelProperty(value="病人床号")
	private String brch;

	public Timestamp getBeginDate() {
		return beginDate;
	}

	public void setBeginDate(Timestamp beginDate) {
		this.beginDate = beginDate;
	}

	public Timestamp getEndDate() {
		return endDate;
	}

	public void setEndDate(Timestamp endDate) {
		this.endDate = endDate;
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

	public Integer getBrxz() {
		return brxz;
	}

	public void setBrxz(Integer brxz) {
		this.brxz = brxz;
	}

	public String getKsmc() {
		return ksmc;
	}

	public void setKsmc(String ksmc) {
		this.ksmc = ksmc;
	}

	public Timestamp getRyrq() {
		return ryrq;
	}

	public void setRyrq(Timestamp ryrq) {
		this.ryrq = ryrq;
	}

	public Timestamp getCyrq() {
		return cyrq;
	}

	public void setCyrq(Timestamp cyrq) {
		this.cyrq = cyrq;
	}

	public String getBrch() {
		return brch;
	}

	public void setBrch(String brch) {
		this.brch = brch;
	}

    
}
