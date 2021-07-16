   
package com.buit.cis.nurse.request;

import java.sql.Timestamp;
import java.util.List;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


/**
 * 类名称：CisHzyzReviewReq<br> 
 * 类描述：住院_病区医嘱复核<br>
 * @author GONGFANGZHOU
 */
@ApiModel(value="住院_病区医嘱_ReviewUpdateReq")
public class CisHzyzReviewUpdateReq {
	
	@ApiModelProperty(value="复核工号")
    private String fhgh;
	
	@ApiModelProperty(value="复核时间")
    private Timestamp fhsj;
	
	@ApiModelProperty(value="机构id" , hidden = true)
    private Integer jgid;
	
	@ApiModelProperty(value="记录序号集合")
    private List<Integer> jlxhList;
	
	@ApiModelProperty(value="组套记录序号集合")
    private List<Integer> ztjlxhList;

	public String getFhgh() {
		return fhgh;
	}

	public void setFhgh(String fhgh) {
		this.fhgh = fhgh;
	}

	public Timestamp getFhsj() {
		return fhsj;
	}

	public void setFhsj(Timestamp fhsj) {
		this.fhsj = fhsj;
	}

	public Integer getJgid() {
		return jgid;
	}

	public void setJgid(Integer jgid) {
		this.jgid = jgid;
	}

	public List<Integer> getJlxhList() {
		return jlxhList;
	}

	public void setJlxhList(List<Integer> jlxhList) {
		this.jlxhList = jlxhList;
	}

	public List<Integer> getZtjlxhList() {
		return ztjlxhList;
	}

	public void setZtjlxhList(List<Integer> ztjlxhList) {
		this.ztjlxhList = ztjlxhList;
	}
	
}
