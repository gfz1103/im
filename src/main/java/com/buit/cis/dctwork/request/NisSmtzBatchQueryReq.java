   
package com.buit.cis.dctwork.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


/**
 * 类名称：NisSmtzBatchSaveReq<br> 
 * 类描述：生命体征<br>
 * @author GONGFANGZHOU
 */
@ApiModel(value="生命体征_BatchQueryReq")
public class NisSmtzBatchQueryReq {
	
	@ApiModelProperty(value="查询日期")
    private String queryDate;
	
	@ApiModelProperty(value="查询时间段")
    private Integer hour;
	
	@ApiModelProperty(value="病区代码")
	private Integer bqdm;	
	
	@ApiModelProperty(value="是否为全部")
	private boolean qbFlag;	
	
	@ApiModelProperty(value="是否为新入患者")
	private boolean xrFlag;	
	
	@ApiModelProperty(value="是否为手术患者")
	private boolean ssFlag;	
	
	@ApiModelProperty(value="是否为一级护理患者")
	private boolean yjhlFlag;	
	
	@ApiModelProperty(value="是否为发热患者")
	private boolean frFlag;	
	
	public String getQueryDate() {
		return queryDate;
	}

	public void setQueryDate(String queryDate) {
		this.queryDate = queryDate;
	}

	public Integer getHour() {
		return hour;
	}

	public void setHour(Integer hour) {
		this.hour = hour;
	}

	public Integer getBqdm() {
		return bqdm;
	}

	public void setBqdm(Integer bqdm) {
		this.bqdm = bqdm;
	}

	public boolean isQbFlag() {
		return qbFlag;
	}

	public void setQbFlag(boolean qbFlag) {
		this.qbFlag = qbFlag;
	}

	public boolean isXrFlag() {
		return xrFlag;
	}

	public void setXrFlag(boolean xrFlag) {
		this.xrFlag = xrFlag;
	}

	public boolean isSsFlag() {
		return ssFlag;
	}

	public void setSsFlag(boolean ssFlag) {
		this.ssFlag = ssFlag;
	}

	public boolean isYjhlFlag() {
		return yjhlFlag;
	}

	public void setYjhlFlag(boolean yjhlFlag) {
		this.yjhlFlag = yjhlFlag;
	}

	public boolean isFrFlag() {
		return frFlag;
	}

	public void setFrFlag(boolean frFlag) {
		this.frFlag = frFlag;
	}
	
}
