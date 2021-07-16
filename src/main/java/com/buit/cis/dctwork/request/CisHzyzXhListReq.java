   
package com.buit.cis.dctwork.request;

import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


/**
 * 类名称：CisHzyzXhListReq<br> 
 * 类描述：住院_病区医嘱<br>
 * @author GONGFANGZHOU
 */
@ApiModel(value="住院_病区医嘱_xhlistReq")
public class CisHzyzXhListReq {
	
	@ApiModelProperty(value="记录序号集合")
	private List<Integer> jlxhList;
	
	@ApiModelProperty(value="组套记录序号集合")
	private List<Integer> ztyzjlxhList;
	
	@ApiModelProperty(value="组套标志(该条医嘱存是组套信息) 0非组套标志，1组套标志")
    private Integer ztbz;

	public List<Integer> getJlxhList() {
		return jlxhList;
	}

	public void setJlxhList(List<Integer> jlxhList) {
		this.jlxhList = jlxhList;
	}

	public List<Integer> getZtyzjlxhList() {
		return ztyzjlxhList;
	}

	public void setZtyzjlxhList(List<Integer> ztyzjlxhList) {
		this.ztyzjlxhList = ztyzjlxhList;
	}

	public Integer getZtbz() {
		return ztbz;
	}

	public void setZtbz(Integer ztbz) {
		this.ztbz = ztbz;
	}
	
}
