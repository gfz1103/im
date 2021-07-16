   
package com.buit.cis.nurse.request;

import java.sql.Timestamp;
import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


/**
 * 类名称：CisHzyz<br> 
 * 类描述：住院_病区医嘱卡片打印<br>
 * @author GONGFANGZHOU
 */
@ApiModel(value="住院_病区医嘱_CardCancelReq")
public class CisHzyzCardCancelReq {
	
	@ApiModelProperty(value="医嘱组号")
	private Integer yzzh;
	
	@ApiModelProperty(value="时间")
	private Timestamp yysj;

	public Integer getYzzh() {
		return yzzh;
	}

	public void setYzzh(Integer yzzh) {
		this.yzzh = yzzh;
	}

	public Timestamp getYysj() {
		return yysj;
	}

	public void setYysj(Timestamp yysj) {
		this.yysj = yysj;
	}

    
}
