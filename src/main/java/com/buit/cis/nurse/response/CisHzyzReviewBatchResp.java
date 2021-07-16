   
package com.buit.cis.nurse.response;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


/**
 * 类名称：CisHzyzReviewBatchResp<br> 
 * 类描述：住院_病区医嘱_批量复核返回<br>
 * @author GONGFANGZHOU
 */
@ApiModel(value="住院_病区医嘱_reviewbatchResp")
public class CisHzyzReviewBatchResp {
	
    @ApiModelProperty(value="特殊标识")
    private boolean tsbz;
    
    @ApiModelProperty(value="警告提示")
    private String warningMessage;
    
    @ApiModelProperty(value="抗菌药物警告提示")
    private String ywwarningMessage;

	public boolean isTsbz() {
		return tsbz;
	}

	public void setTsbz(boolean tsbz) {
		this.tsbz = tsbz;
	}

	public String getWarningMessage() {
		return warningMessage;
	}

	public void setWarningMessage(String warningMessage) {
		this.warningMessage = warningMessage;
	}

	public String getYwwarningMessage() {
		return ywwarningMessage;
	}

	public void setYwwarningMessage(String ywwarningMessage) {
		this.ywwarningMessage = ywwarningMessage;
	}
    
	
    
}
