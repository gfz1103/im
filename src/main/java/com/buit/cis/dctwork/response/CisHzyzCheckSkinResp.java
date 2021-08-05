   
package com.buit.cis.dctwork.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


/**
 * 类名称：CisHzyz<br> 
 * 类描述：住院_病区医嘱<br>
 * @author GONGFANGZHOU
 */
@ApiModel(value="住院_病区医嘱_CheckSkinResp")
public class CisHzyzCheckSkinResp {
	
	@ApiModelProperty(value="是否能使用(1:是0:否)")
	private Integer isUse;
	
	@ApiModelProperty(value="不能使用提示信息")
	private String message;

	public Integer getIsUse() {
		return isUse;
	}

	public void setIsUse(Integer isUse) {
		this.isUse = isUse;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
