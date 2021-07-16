   
package com.buit.cis.dctwork.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


/**
 * 类名称：NisTj02Req<br> 
 * 类描述：病区_提交明细退回请求<br>
 * @author GONGFANGZHOU
 */
@ApiModel(value="病区_提交明细退回_Req")
public class NisTj02Req {
	
	@ApiModelProperty(value="住院号码,病人床号,病人姓名")
    private String comprehensive;
	
	@ApiModelProperty(value="确认标志")
    private Integer qrbz;

	public String getComprehensive() {
		return comprehensive;
	}

	public void setComprehensive(String comprehensive) {
		this.comprehensive = comprehensive;
	}

	public Integer getQrbz() {
		return qrbz;
	}

	public void setQrbz(Integer qrbz) {
		this.qrbz = qrbz;
	}
	
   
}
