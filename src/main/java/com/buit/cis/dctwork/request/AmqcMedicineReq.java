package com.buit.cis.dctwork.request;

import java.math.BigDecimal;
import java.sql.Timestamp;
import com.buit.commons.PageQuery;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 类名称：AmqcMedicineResp<br> 
 * 类描述：
 * @author GONGFANGZHOU 
 * 
 */
@ApiModel(value="抗菌药物审批列表_Resp")
public class AmqcMedicineReq  extends  PageQuery{

	@ApiModelProperty(value="病人科室")
    /** brks */
    private Integer brks;
	
	@ApiModelProperty(value="0-新增 1-提交 2-科主任审批完成 3-专家组审批完成 4-医务科审批完成")
    /** 0-新增 1-提交 2-科主任审批完成 3-专家组审批完成 4-医务科审批完成 */
    private Integer djzt;
	
	@ApiModelProperty(value="0-未审批 1-同意 2-自动 9-不同意")
    /** 0-未审批 1-同意 2-自动 9-不同意 */
    private Integer spjg;
	
	@ApiModelProperty(value="申请开始日期")
	private Timestamp sqksrq;
	
	@ApiModelProperty(value="申请结束日期")
	private Timestamp sqjsrq;
	
	@ApiModelProperty(value="住院号码")
	private Integer zyhm;
	
	@ApiModelProperty(value="病人姓名")
	private String brxm;
	
	@ApiModelProperty(value="病人性别")
	private Integer brxb;
	
	@ApiModelProperty(value="病人床号")
	/** brch */
    private String brch;
	
	@ApiModelProperty(value="就诊类型:Dict9 1住院 2新生儿 3门诊 4急诊 5留观")
    /** 就诊类型:Dict9 1住院 2新生儿 3门诊 4急诊 5留观 */
    private Integer jzlx;
	
	@ApiModelProperty(value="作废判别")
    /** zfbz */
    private Integer zfbz;
	
	@ApiModelProperty(value="机构id")
    /** jgid */
    private Integer jgid;

	public Integer getBrks() {
		return brks;
	}

	public void setBrks(Integer brks) {
		this.brks = brks;
	}

	public Integer getDjzt() {
		return djzt;
	}

	public void setDjzt(Integer djzt) {
		this.djzt = djzt;
	}

	public Integer getSpjg() {
		return spjg;
	}

	public void setSpjg(Integer spjg) {
		this.spjg = spjg;
	}

	public Timestamp getSqksrq() {
		return sqksrq;
	}

	public void setSqksrq(Timestamp sqksrq) {
		this.sqksrq = sqksrq;
	}

	public Timestamp getSqjsrq() {
		return sqjsrq;
	}

	public void setSqjsrq(Timestamp sqjsrq) {
		this.sqjsrq = sqjsrq;
	}

	public Integer getZyhm() {
		return zyhm;
	}

	public void setZyhm(Integer zyhm) {
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

	public String getBrch() {
		return brch;
	}

	public void setBrch(String brch) {
		this.brch = brch;
	}

	public Integer getJzlx() {
		return jzlx;
	}

	public void setJzlx(Integer jzlx) {
		this.jzlx = jzlx;
	}

	public Integer getZfbz() {
		return zfbz;
	}

	public void setZfbz(Integer zfbz) {
		this.zfbz = zfbz;
	}

	public Integer getJgid() {
		return jgid;
	}

	public void setJgid(Integer jgid) {
		this.jgid = jgid;
	}
	
}