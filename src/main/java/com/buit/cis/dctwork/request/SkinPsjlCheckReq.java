   
package com.buit.cis.dctwork.request;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


/**
 * 类名称：SkinPsjl<br> 
 * 类描述：公用_病人皮试记录校验<br>
 * @author GONGFANGZHOU
 */
@ApiModel(value="公用_病人皮试记录_checkReq")
public class SkinPsjlCheckReq {
	
	@ApiModelProperty(value="住院号")
	private Integer zyh;

	@ApiModelProperty(value="病人id")
	private Integer brid;
	
	@ApiModelProperty(value="药品类型")
	private Integer yplx;
	
	@ApiModelProperty(value="药品序号")
	private Integer ypxh;
	
	@ApiModelProperty(value="病人性质")
	private Integer brxz;

	public Integer getZyh() {
		return zyh;
	}

	public void setZyh(Integer zyh) {
		this.zyh = zyh;
	}

	public Integer getBrid() {
		return brid;
	}

	public void setBrid(Integer brid) {
		this.brid = brid;
	}

	public Integer getYplx() {
		return yplx;
	}

	public void setYplx(Integer yplx) {
		this.yplx = yplx;
	}

	public Integer getYpxh() {
		return ypxh;
	}

	public void setYpxh(Integer ypxh) {
		this.ypxh = ypxh;
	}

	public Integer getBrxz() {
		return brxz;
	}

	public void setBrxz(Integer brxz) {
		this.brxz = brxz;
	}    
	
}
