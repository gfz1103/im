   
package com.buit.cis.dctwork.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


/**
 * 类名称：CisHzyzFyjlResp<br> 
 * 类描述：住院医嘱_发药记录<br>
 * @author GONGFANGZHOU
 */
@ApiModel(value="住院医嘱_发药记录_FyjlResp")
public class CisHzyzFyjlResp {
	
	@ApiModelProperty(value="机构id")
    private Integer jgid;
    @ApiModelProperty(value="医嘱序号")
    private Integer yzzh;
	public Integer getJgid() {
		return jgid;
	}
	public void setJgid(Integer jgid) {
		this.jgid = jgid;
	}
	public Integer getYzzh() {
		return yzzh;
	}
	public void setYzzh(Integer yzzh) {
		this.yzzh = yzzh;
	}
	
	
}