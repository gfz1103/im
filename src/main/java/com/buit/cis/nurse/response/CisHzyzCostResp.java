package com.buit.cis.nurse.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 类名称：ZybqyzCostResp<br> 
 * 类描述：住院_病区费用记账保存返回<br>
 * @author GONGFANGZHOU
 */
@ApiModel(value="住院_病区发药明细_costResp")
public class CisHzyzCostResp {
	
	@ApiModelProperty(value="费用序号")
	private Integer fyxh;
	
	@ApiModelProperty(value="记录序号")
	private Integer jlxh;

	public Integer getFyxh() {
		return fyxh;
	}

	public void setFyxh(Integer fyxh) {
		this.fyxh = fyxh;
	}

	public Integer getJlxh() {
		return jlxh;
	}

	public void setJlxh(Integer jlxh) {
		this.jlxh = jlxh;
	}
	
	

}
