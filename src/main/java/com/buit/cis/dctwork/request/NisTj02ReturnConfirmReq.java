   
package com.buit.cis.dctwork.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


/**
 * 类名称：NisTj02Resp<br> 
 * 类描述：<br>
 * @author GONGFANGZHOU
 */
@ApiModel(value="病区_提交明细退回_HzInfoReq")
public class NisTj02ReturnConfirmReq {

	@ApiModelProperty(value="记录序号")
    private Integer jlxh;
	
	@ApiModelProperty(value="提交序号")
    private Integer tjxh;

	@ApiModelProperty(value="医嘱序号")
    private Integer yzxh;
	
	@ApiModelProperty(value="临时医嘱")
    private Integer lsyz;

	@ApiModelProperty(value="药品类型")
    private Integer yplx;
	
	@ApiModelProperty(value="组套标志")
    private Integer ztbz;
	
	@ApiModelProperty(value="组套记录序号")
    private Integer ztyzjlxh;

	public Integer getJlxh() {
		return jlxh;
	}

	public void setJlxh(Integer jlxh) {
		this.jlxh = jlxh;
	}

	public Integer getTjxh() {
		return tjxh;
	}

	public void setTjxh(Integer tjxh) {
		this.tjxh = tjxh;
	}

	public Integer getYzxh() {
		return yzxh;
	}

	public void setYzxh(Integer yzxh) {
		this.yzxh = yzxh;
	}

	public Integer getLsyz() {
		return lsyz;
	}

	public void setLsyz(Integer lsyz) {
		this.lsyz = lsyz;
	}

	public Integer getYplx() {
		return yplx;
	}

	public void setYplx(Integer yplx) {
		this.yplx = yplx;
	}

	public Integer getZtbz() {
		return ztbz;
	}

	public void setZtbz(Integer ztbz) {
		this.ztbz = ztbz;
	}

	public Integer getZtyzjlxh() {
		return ztyzjlxh;
	}

	public void setZtyzjlxh(Integer ztyzjlxh) {
		this.ztyzjlxh = ztyzjlxh;
	}

}
