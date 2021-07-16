   
package com.buit.cis.dctwork.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


/**
 * 类名称：CisHzyzBookPrintResp<br> 
 * 类描述：医嘱本打印<br>
 * @author GONGFANGZHOU
 */
@ApiModel(value="住院_病区医嘱_bookprintResp")
public class CisHzyzBookPrintResp {

    @ApiModelProperty(value="打印医嘱数")
    private String dyyzs;
    
    @ApiModelProperty(value="停嘱医嘱数")
    private String tzyzs;
    
    @ApiModelProperty(value="返回内容")
    private String msg;

	public String getDyyzs() {
		return dyyzs;
	}

	public void setDyyzs(String dyyzs) {
		this.dyyzs = dyyzs;
	}

	public String getTzyzs() {
		return tzyzs;
	}

	public void setTzyzs(String tzyzs) {
		this.tzyzs = tzyzs;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
	

}
