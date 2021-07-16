package com.buit.cis.ims.response;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 类名称：ImZkjl<br> 
 * 类描述：住院_转科记录
 * @author GONGFANGZHOU 
 * @ApiModel(value="住院_转科记录")
 */
@ApiModel(value="住院_转科记录_ZkZqHcjlResp")
public class ImZkZqHcjlResp {
	
	@ApiModelProperty(value="科室集合")
	private List<String> ksList;
	
	@ApiModelProperty(value="病区集合")
	private List<String> bqList;
	
	@ApiModelProperty(value="床号集合")
	private List<String> cwList;

	public List<String> getKsList() {
		return ksList;
	}

	public void setKsList(List<String> ksList) {
		this.ksList = ksList;
	}

	public List<String> getBqList() {
		return bqList;
	}

	public void setBqList(List<String> bqList) {
		this.bqList = bqList;
	}

	public List<String> getCwList() {
		return cwList;
	}

	public void setCwList(List<String> cwList) {
		this.cwList = cwList;
	}
	
	
}
