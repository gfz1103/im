   
package com.buit.cis.dctwork.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


/**
 * 类名称：CisHzyz<br> 
 * 类描述：住院_病区医嘱<br>
 * @author GONGFANGZHOU
 */
@ApiModel(value="住院_病区医嘱_OrderSetReq")
public class CisHzyzOrderSetReq{
   
	@ApiModelProperty(value="住院号")
    private Integer zyh;
	
	@ApiModelProperty(value="机构id", hidden = true)
    private Integer jgid;
	
	@ApiModelProperty(value="医嘱(0:长期1:临时、出院、急诊)")
    private Integer lsyz;
	
	@ApiModelProperty(value="类型(2:急诊3:出院带药)")
    private Integer xmlx;
	
	@ApiModelProperty(value="药品类型(0:项目1:西药2:草药)")
    private Integer yplx;

	public Integer getZyh() {
		return zyh;
	}

	public void setZyh(Integer zyh) {
		this.zyh = zyh;
	}

	public Integer getJgid() {
		return jgid;
	}

	public void setJgid(Integer jgid) {
		this.jgid = jgid;
	}

	public Integer getLsyz() {
		return lsyz;
	}

	public void setLsyz(Integer lsyz) {
		this.lsyz = lsyz;
	}

	public Integer getXmlx() {
		return xmlx;
	}

	public void setXmlx(Integer xmlx) {
		this.xmlx = xmlx;
	}

	public Integer getYplx() {
		return yplx;
	}

	public void setYplx(Integer yplx) {
		this.yplx = yplx;
	}
	
	
}
