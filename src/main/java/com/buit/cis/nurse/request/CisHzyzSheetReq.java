package com.buit.cis.nurse.request;

import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 类名称：CisHzyz<br> 
 * 类描述：住院_病区医嘱_附加计价单<br>
 * @author GONGFANGZHOU
 */
@ApiModel(value="住院_病区医嘱_sheetReq")
public class CisHzyzSheetReq {
	
	@ApiModelProperty(value="住院号")
	private Integer zyh;
	
	@ApiModelProperty(value="医嘱序号集合")
	private List<Integer> yzxhList;
	
	@ApiModelProperty(value="病人病区")
	private Integer brbq;

	public Integer getZyh() {
		return zyh;
	}

	public void setZyh(Integer zyh) {
		this.zyh = zyh;
	}

	public List<Integer> getYzxhList() {
		return yzxhList;
	}

	public void setYzxhList(List<Integer> yzxhList) {
		this.yzxhList = yzxhList;
	}

	public Integer getBrbq() {
		return brbq;
	}

	public void setBrbq(Integer brbq) {
		this.brbq = brbq;
	}
	
	
}
