   
package com.buit.cis.nurse.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


/**
 * 类名称：CisHzyz<br> 
 * 类描述：住院_病区医嘱卡片打印<br>
 * @author GONGFANGZHOU
 */
@ApiModel(value="住院_病区医嘱_CardKsResp")
public class CisHzyzCardKsResp {

    @ApiModelProperty(value="病人科室")
    private Integer brks;
    
    @ApiModelProperty(value="科室名称")
    private String brksname;

	public Integer getBrks() {
		return brks;
	}

	public void setBrks(Integer brks) {
		this.brks = brks;
	}

	public String getBrksname() {
		return brksname;
	}

	public void setBrksname(String brksname) {
		this.brksname = brksname;
	}

	
    
}
