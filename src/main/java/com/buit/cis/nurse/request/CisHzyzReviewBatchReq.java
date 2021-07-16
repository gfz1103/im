   
package com.buit.cis.nurse.request;


import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


/**
 * 类名称：CisHzyzReviewBatchResp<br> 
 * 类描述：住院_病区医嘱_批量复核返回<br>
 * @author GONGFANGZHOU
 */
@ApiModel(value="住院_病区医嘱_ReviewBatchReq")
public class CisHzyzReviewBatchReq {
	
    @ApiModelProperty(value="医嘱标志(1:开,2:停)")
    private Integer yzbz;
    
    @ApiModelProperty(value="住院号码")
    private String zyhm;
    
    @ApiModelProperty(value="当前病区")
    private Integer srks;
    
    @ApiModelProperty(value="医嘱(0:长期1:临时2:全部)")
    private Integer lsyz;
    
    @ApiModelProperty(value="机构id", hidden = true)
    private Integer jgid;
    
    @ApiModelProperty(value="住院号集合")
    private List<Integer> zyhList;

	public Integer getYzbz() {
		return yzbz;
	}

	public void setYzbz(Integer yzbz) {
		this.yzbz = yzbz;
	}

	public String getZyhm() {
		return zyhm;
	}

	public void setZyhm(String zyhm) {
		this.zyhm = zyhm;
	}

	public Integer getSrks() {
		return srks;
	}

	public void setSrks(Integer srks) {
		this.srks = srks;
	}

	public Integer getLsyz() {
		return lsyz;
	}

	public void setLsyz(Integer lsyz) {
		this.lsyz = lsyz;
	}

	public Integer getJgid() {
		return jgid;
	}

	public void setJgid(Integer jgid) {
		this.jgid = jgid;
	}

	public List<Integer> getZyhList() {
		return zyhList;
	}

	public void setZyhList(List<Integer> zyhList) {
		this.zyhList = zyhList;
	}
    
}
