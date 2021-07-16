   
package com.buit.cis.nurse.request;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


/**
 * 类名称：CisHzyzReviewReq<br> 
 * 类描述：住院_病区医嘱复核<br>
 * @author GONGFANGZHOU
 */
@ApiModel(value="住院_病区医嘱_ReviewReq")
public class CisHzyzReviewReq {
    
	@ApiModelProperty(value="组套标志(0:否,1:是)")
    private Integer ztbz;
	
	@ApiModelProperty(value="记录序号")
	@NotNull(message = "记录序号不能为空")
    private Integer jlxh;

	public Integer getZtbz() {
		return ztbz;
	}

	public void setZtbz(Integer ztbz) {
		this.ztbz = ztbz;
	}

	public Integer getJlxh() {
		return jlxh;
	}

	public void setJlxh(Integer jlxh) {
		this.jlxh = jlxh;
	}
	
	
}
