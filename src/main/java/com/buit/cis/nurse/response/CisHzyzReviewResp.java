   
package com.buit.cis.nurse.response;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


/**
 * 类名称：CisHzyz<br> 
 * 类描述：住院_病区医嘱_复核返回<br>
 * @author GONGFANGZHOU
 */
@ApiModel(value="住院_病区医嘱_reviewResp")
public class CisHzyzReviewResp {
	
    @ApiModelProperty(value="复核标识")
    private Integer messageCount;
    
    @ApiModelProperty(value="更新数量")
    private Long updateCount;
    
    @ApiModelProperty(value="警告提示")
    private String warningMessage;
    
    @ApiModelProperty(value="抗菌药物警告提示")
    private String ywwarningMessage;

	public Integer getMessageCount() {
		return messageCount;
	}

	public void setMessageCount(Integer messageCount) {
		this.messageCount = messageCount;
	}

	public Long getUpdateCount() {
		return updateCount;
	}

	public void setUpdateCount(Long updateCount) {
		this.updateCount = updateCount;
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
