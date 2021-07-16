   
package com.buit.cis.dctwork.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


/**
 * 类名称：CisHzyzProjectReq<br> 
 * 类描述：住院_病区医嘱常用项目或全部调入<br>
 * @author GONGFANGZHOU
 */
@ApiModel(value="住院_病区医嘱_projectReq")
public class CisHzyzProjectReq {
	
	@ApiModelProperty(value="组套助手tab名称")
	private String tabId;
	
	@ApiModelProperty(value="费用序号")
	private Integer fyxh;
	
	@ApiModelProperty(value="组套记录编号")
	private Integer jlbh;
	
	@ApiModelProperty(value="病人性质")
	private Integer brxz;

	public String getTabId() {
		return tabId;
	}

	public void setTabId(String tabId) {
		this.tabId = tabId;
	}

	public Integer getFyxh() {
		return fyxh;
	}

	public void setFyxh(Integer fyxh) {
		this.fyxh = fyxh;
	}

	public Integer getJlbh() {
		return jlbh;
	}

	public void setJlbh(Integer jlbh) {
		this.jlbh = jlbh;
	}

	public Integer getBrxz() {
		return brxz;
	}

	public void setBrxz(Integer brxz) {
		this.brxz = brxz;
	}	
	
}
