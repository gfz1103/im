   
package com.buit.cis.ims.response;

import java.sql.Timestamp;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


/**
 * 类名称：ImHzryConsultedResp<br> 
 * 类描述：住院_手术首页<br>
 * @author GONGFANGZHOU
 */
@ApiModel(value="住院_待会诊_ConsultedResp")
public class ImHzryConsultedResp {

	@ApiModelProperty(value="申请序号")
    private Integer sqxh;
	
	@ApiModelProperty(value="住院号")
    private Integer zyh;
	
	@ApiModelProperty(value="住院号码")
    private String zyhm;
	
	@ApiModelProperty(value="紧急标志")
    private Integer jjbz;
	
	@ApiModelProperty(value="紧急标志")
    private Timestamp sqsj;
	
	@ApiModelProperty(value="病人姓名")
	private String brxm;
	
    @ApiModelProperty(value="病人床号")
	private String brch;

	@ApiModelProperty(value="病人科室")
	private Integer brks;
	
	@ApiModelProperty(value="申请医生")
	private Integer sqys;
	
	@ApiModelProperty(value="会诊医生")
	private String hzys;
	
	@ApiModelProperty(value="类型(1:普通会诊2:特殊抗菌药物会诊)")
	private Integer lx;
	
	@ApiModelProperty(value="结算日期")
	private Timestamp jsrq;

	public Integer getSqxh() {
		return sqxh;
	}

	public void setSqxh(Integer sqxh) {
		this.sqxh = sqxh;
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

	public Integer getJjbz() {
		return jjbz;
	}

	public void setJjbz(Integer jjbz) {
		this.jjbz = jjbz;
	}

	public Timestamp getSqsj() {
		return sqsj;
	}

	public void setSqsj(Timestamp sqsj) {
		this.sqsj = sqsj;
	}

	public String getBrxm() {
		return brxm;
	}

	public void setBrxm(String brxm) {
		this.brxm = brxm;
	}

	public String getBrch() {
		return brch;
	}

	public void setBrch(String brch) {
		this.brch = brch;
	}

	public Integer getBrks() {
		return brks;
	}

	public void setBrks(Integer brks) {
		this.brks = brks;
	}

	public Integer getSqys() {
		return sqys;
	}

	public void setSqys(Integer sqys) {
		this.sqys = sqys;
	}

	public String getHzys() {
		return hzys;
	}

	public void setHzys(String hzys) {
		this.hzys = hzys;
	}
	
	public Integer getLx() {
		return lx;
	}

	public void setLx(Integer lx) {
		this.lx = lx;
	}

	public Timestamp getJsrq() {
		return jsrq;
	}

	public void setJsrq(Timestamp jsrq) {
		this.jsrq = jsrq;
	}

	
}
