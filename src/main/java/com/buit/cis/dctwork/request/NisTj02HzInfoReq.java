   
package com.buit.cis.dctwork.request;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


/**
 * 类名称：NisTj02Resp<br> 
 * 类描述：<br>
 * @author GONGFANGZHOU
 */
@ApiModel(value="病区_提交明细退回_HzInfoReq")
public class NisTj02HzInfoReq {

	@ApiModelProperty(value="病人病区")
	@NotNull(message = "病区代码不能为空")
    private Integer brbq;
	
	@ApiModelProperty(value="住院号码、病人姓名、病人床号")
    private String comprehensive;

	@ApiModelProperty(value="机构id", hidden = true)
    private Integer jgid;

	public Integer getBrbq() {
		return brbq;
	}

	public void setBrbq(Integer brbq) {
		this.brbq = brbq;
	}

	public String getComprehensive() {
		return comprehensive;
	}

	public void setComprehensive(String comprehensive) {
		this.comprehensive = comprehensive;
	}

	public Integer getJgid() {
		return jgid;
	}

	public void setJgid(Integer jgid) {
		this.jgid = jgid;
	}
	
	
	
}
