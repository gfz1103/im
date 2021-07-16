   
package com.buit.cis.nurse.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;
import java.sql.Timestamp;


/**
 * 类名称：CisHzyzReviewInfoResp<br> 
 * 类描述：住院_病区医嘱复核列表<br>
 * @author GONGFANGZHOU
 */
@ApiModel(value="住院_病区组套医嘱_ReviewPatientResp")
public class CisHzyzReviewPatientResp {

	@ApiModelProperty(value="住院号")
	private Integer zyh;

	@ApiModelProperty(value="病人姓名")
    private String brxm;
	
	@ApiModelProperty(value="病人床号")
    private String brch;

	public Integer getZyh() {
		return zyh;
	}

	public void setZyh(Integer zyh) {
		this.zyh = zyh;
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
}
