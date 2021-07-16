   
package com.buit.cis.dctwork.request;

import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


/**
 * 类名称：CisHzyzChooseProjectReq<br> 
 * 类描述：住院_病区项目选择多条<br>
 * @author GONGFANGZHOU
 */
@ApiModel(value="住院_病区医嘱_chooseprojectReq")
public class CisHzyzChooseProjectReq {
	
	@ApiModelProperty(value="住院号")
	private Integer zyh;
	
	@ApiModelProperty(value="病人性质")
	private Integer brxz;
	
	@ApiModelProperty(value="记录编号集合")
	private List<Integer> jlbhs;

	public Integer getZyh() {
		return zyh;
	}

	public void setZyh(Integer zyh) {
		this.zyh = zyh;
	}

	public Integer getBrxz() {
		return brxz;
	}

	public void setBrxz(Integer brxz) {
		this.brxz = brxz;
	}

	public List<Integer> getJlbhs() {
		return jlbhs;
	}

	public void setJlbhs(List<Integer> jlbhs) {
		this.jlbhs = jlbhs;
	}

	
	
}
