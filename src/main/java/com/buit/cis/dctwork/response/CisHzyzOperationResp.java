   
package com.buit.cis.dctwork.response;

import java.sql.Timestamp;

import com.buit.commons.PageQuery;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


/**
 * 类名称：CisHzyzOperationResp<br> 
 * 类描述：住院_病区医嘱<br>
 * @author GONGFANGZHOU
 */
@ApiModel(value="住院_病区医嘱_OperationResp")
public class CisHzyzOperationResp extends PageQuery {

    @ApiModelProperty(value="开嘱病区")
    private Integer srks;
    @ApiModelProperty(value="状态(0:未安排 1:已安排 )")
    private Integer zt;
    @ApiModelProperty(value="手术科室")
    private Integer ssks;
    @ApiModelProperty(value="医嘱名称")
    private String yzmc;
    @ApiModelProperty(value="开嘱时间")
    private Timestamp kssj;
    @ApiModelProperty(value="手术日期")
    private Timestamp ssrq;
    @ApiModelProperty(value="手术级别")
    private Integer ssjb;
    @ApiModelProperty(value="手术备注")
    private Integer ssbz;
	public Integer getSrks() {
		return srks;
	}
	public void setSrks(Integer srks) {
		this.srks = srks;
	}
	public Integer getZt() {
		return zt;
	}
	public void setZt(Integer zt) {
		this.zt = zt;
	}
	public Integer getSsks() {
		return ssks;
	}
	public void setSsks(Integer ssks) {
		this.ssks = ssks;
	}
	public String getYzmc() {
		return yzmc;
	}
	public void setYzmc(String yzmc) {
		this.yzmc = yzmc;
	}
	public Timestamp getKssj() {
		return kssj;
	}
	public void setKssj(Timestamp kssj) {
		this.kssj = kssj;
	}
	public Timestamp getSsrq() {
		return ssrq;
	}
	public void setSsrq(Timestamp ssrq) {
		this.ssrq = ssrq;
	}
	public Integer getSsjb() {
		return ssjb;
	}
	public void setSsjb(Integer ssjb) {
		this.ssjb = ssjb;
	}
	public Integer getSsbz() {
		return ssbz;
	}
	public void setSsbz(Integer ssbz) {
		this.ssbz = ssbz;
	}

}
