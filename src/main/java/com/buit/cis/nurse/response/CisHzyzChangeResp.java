package com.buit.cis.nurse.response;

import java.math.BigDecimal;
import java.sql.Timestamp;

import com.buit.commons.PageQuery;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 类名称：CisHzyzChangeResp<br> 
 * 类描述：住院_病区变动医嘱<br>
 * @author GONGFANGZHOU
 */
@ApiModel(value="住院_病区变动医嘱_ChangeResp")
public class CisHzyzChangeResp extends PageQuery{
	
	/**
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
	*/
	private static final long serialVersionUID = 1L;
	@ApiModelProperty(value="病人床号")
	private String brch;
	
	@ApiModelProperty(value="病人姓名")
	private String brxm;
	
	@ApiModelProperty(value="开嘱时间")
	private Timestamp kssj;
	
	@ApiModelProperty(value="开嘱医生")
	private String ysgh;
	
	@ApiModelProperty(value="医嘱项目名称")
	private String yzmc;
	
	@ApiModelProperty(value="一次数量")
	private BigDecimal ycsl;
	
	@ApiModelProperty(value="使用频次")
	private String sypc;
	
	@ApiModelProperty(value="药品用法")
	private Integer ypyf;
	
	@ApiModelProperty(value="停嘱时间")
	private Timestamp tzsj;
	
	@ApiModelProperty(value="停嘱医生")
	private String tzys;
	
	@ApiModelProperty(value="发药方式")
	private Integer fyfs;
	
	@ApiModelProperty(value="开嘱医生名称")
	private String ysghmc;
	
	@ApiModelProperty(value="使用频次名称")
	private String sypcmc;
	
	@ApiModelProperty(value="药品用法名称")
	private String ypyfmc;
	
	@ApiModelProperty(value="发药方式名称")
	private String fyfsmc;
	
	@ApiModelProperty(value="停嘱医生名称")
	private String tzysmc;

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

	public Timestamp getKssj() {
		return kssj;
	}

	public void setKssj(Timestamp kssj) {
		this.kssj = kssj;
	}

	public String getYsgh() {
		return ysgh;
	}

	public void setYsgh(String ysgh) {
		this.ysgh = ysgh;
	}

	public String getYzmc() {
		return yzmc;
	}

	public void setYzmc(String yzmc) {
		this.yzmc = yzmc;
	}

	public BigDecimal getYcsl() {
		return ycsl;
	}

	public void setYcsl(BigDecimal ycsl) {
		this.ycsl = ycsl;
	}

	public String getSypc() {
		return sypc;
	}

	public void setSypc(String sypc) {
		this.sypc = sypc;
	}

	public Integer getYpyf() {
		return ypyf;
	}

	public void setYpyf(Integer ypyf) {
		this.ypyf = ypyf;
	}

	public Timestamp getTzsj() {
		return tzsj;
	}

	public void setTzsj(Timestamp tzsj) {
		this.tzsj = tzsj;
	}

	public String getTzys() {
		return tzys;
	}

	public void setTzys(String tzys) {
		this.tzys = tzys;
	}

	public Integer getFyfs() {
		return fyfs;
	}

	public void setFyfs(Integer fyfs) {
		this.fyfs = fyfs;
	}

	public String getYsghmc() {
		return ysghmc;
	}

	public void setYsghmc(String ysghmc) {
		this.ysghmc = ysghmc;
	}

	public String getSypcmc() {
		return sypcmc;
	}

	public void setSypcmc(String sypcmc) {
		this.sypcmc = sypcmc;
	}

	public String getYpyfmc() {
		return ypyfmc;
	}

	public void setYpyfmc(String ypyfmc) {
		this.ypyfmc = ypyfmc;
	}

	public String getFyfsmc() {
		return fyfsmc;
	}

	public void setFyfsmc(String fyfsmc) {
		this.fyfsmc = fyfsmc;
	}

	public String getTzysmc() {
		return tzysmc;
	}

	public void setTzysmc(String tzysmc) {
		this.tzysmc = tzysmc;
	}

}
