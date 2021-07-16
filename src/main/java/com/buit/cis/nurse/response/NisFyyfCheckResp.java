package com.buit.cis.nurse.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;



/**
 * 类名称：NisFyyf<br>
 * 类描述：病区_发药药房<br>
 * @author LAPTOP-6GUR25CC
 */
@ApiModel(value="病区_发药药房_checkResp")
public class NisFyyfCheckResp  {
   
	@ApiModelProperty(value="是否存在急诊药房(0:否1:是)")
    private Integer jzyf;
	
	@ApiModelProperty(value="是否存在出院带药药房(0:否1:是)")
	private Integer cydy;

	public Integer getJzyf() {
		return jzyf;
	}

	public void setJzyf(Integer jzyf) {
		this.jzyf = jzyf;
	}

	public Integer getCydy() {
		return cydy;
	}

	public void setCydy(Integer cydy) {
		this.cydy = cydy;
	}
	
	
}
