   
package com.buit.cis.ims.response;

import java.sql.Date;
import java.sql.Timestamp;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


/**
 * 类名称：ImHzryLeaveHospitalResp<br> 
 * 类描述：住院_病人出院<br>
 * @author GONGFANGZHOU
 */
@ApiModel(value="住院_病人出院_leavehospitalResp")
public class ImHzryLeaveHospitalResp {
    
    @ApiModelProperty(value="住院号")
	private Integer zyh;
	
	@ApiModelProperty(value="住院号码")
	private String zyhm;
	
	@ApiModelProperty(value="病人姓名")
    private String brxm;
	
    @ApiModelProperty(value="病人床号")
	private String brch;
	
	@ApiModelProperty(value="病人性别(字典:DIC_GBSJ01:PD0000000269)")
	private Integer brxb;
	
	@ApiModelProperty(value="工作单位")
	private String gzdw;
	
	@ApiModelProperty(value="入院情况(字典:sys_flag_data:RY000002)")
	private Integer ryqk;
	
	@ApiModelProperty(value="病人科室(字典:sys_dict_config:4)")
	private Integer brks;
	
	@ApiModelProperty(value="出院判别(字典:sys_flag_data:CY000001)")
	private Integer cypb;
	
	@ApiModelProperty(value="入院日期")
	private Timestamp ryrq;
	
	@ApiModelProperty(value="出院日期")
	private Timestamp cyrq;
	
	@ApiModelProperty(value="出院方式(字典:sys_flag_data:JG000001)")
	private Integer cyfs;
	
	@ApiModelProperty(value="备注信息")
	private String bzxx;
	
	@ApiModelProperty(value="记录日期")
	private Timestamp jlrq;
	
	@ApiModelProperty(value="病人病区")
	private Integer brbq;
	
	@ApiModelProperty(value="出生日期")
	private Date csrq;
	
	@ApiModelProperty(value="入院年龄")
	private String rynl;
	
	@ApiModelProperty(value="随访信息")
	private String sf;

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

	public String getBrch() {
		return brch;
	}

	public void setBrch(String brch) {
		this.brch = brch;
	}

	public Integer getBrxb() {
		return brxb;
	}

	public void setBrxb(Integer brxb) {
		this.brxb = brxb;
	}

	public String getGzdw() {
		return gzdw;
	}

	public void setGzdw(String gzdw) {
		this.gzdw = gzdw;
	}

	public Integer getRyqk() {
		return ryqk;
	}

	public void setRyqk(Integer ryqk) {
		this.ryqk = ryqk;
	}

	public Integer getBrks() {
		return brks;
	}

	public void setBrks(Integer brks) {
		this.brks = brks;
	}

	public Integer getCypb() {
		return cypb;
	}

	public void setCypb(Integer cypb) {
		this.cypb = cypb;
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

	public Integer getCyfs() {
		return cyfs;
	}

	public void setCyfs(Integer cyfs) {
		this.cyfs = cyfs;
	}

	public String getBzxx() {
		return bzxx;
	}

	public void setBzxx(String bzxx) {
		this.bzxx = bzxx;
	}

	public Timestamp getJlrq() {
		return jlrq;
	}

	public void setJlrq(Timestamp jlrq) {
		this.jlrq = jlrq;
	}

	public Integer getBrbq() {
		return brbq;
	}

	public void setBrbq(Integer brbq) {
		this.brbq = brbq;
	}

	public Date getCsrq() {
		return csrq;
	}

	public void setCsrq(Date csrq) {
		this.csrq = csrq;
	}

	public String getRynl() {
		return rynl;
	}

	public void setRynl(String rynl) {
		this.rynl = rynl;
	}

	public String getSf() {
		return sf;
	}

	public void setSf(String sf) {
		this.sf = sf;
	}
	
	
	
}
