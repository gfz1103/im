   
package com.buit.cis.dctwork.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


/**
 * 类名称：CisHzyzCommonReq<br> 
 * 类描述：住院_病区医嘱常用药或全部调入<br>
 * @author GONGFANGZHOU
 */
@ApiModel(value="住院_病区医嘱_commonReq")
public class CisHzyzCommonReq {
	
	@ApiModelProperty(value="组套助手tab名称")
	private String tabId;
	
	@ApiModelProperty(value="药品序号")
	private Integer ypxh;
	
	@ApiModelProperty(value="组套记录编号")
	private Integer jlbh;
	
	@ApiModelProperty(value="病区代码")
	private Integer	wardId;
	
	@ApiModelProperty(value="tab类型(长期:cq,临时:ls,急诊:jz,出院:cy)")
	private String adviceType;

	public String getTabId() {
		return tabId;
	}

	public void setTabId(String tabId) {
		this.tabId = tabId;
	}

	public Integer getYpxh() {
		return ypxh;
	}

	public void setYpxh(Integer ypxh) {
		this.ypxh = ypxh;
	}

	public Integer getJlbh() {
		return jlbh;
	}

	public void setJlbh(Integer jlbh) {
		this.jlbh = jlbh;
	}

	public Integer getWardId() {
		return wardId;
	}

	public void setWardId(Integer wardId) {
		this.wardId = wardId;
	}

	public String getAdviceType() {
		return adviceType;
	}

	public void setAdviceType(String adviceType) {
		this.adviceType = adviceType;
	}
	
	
}
