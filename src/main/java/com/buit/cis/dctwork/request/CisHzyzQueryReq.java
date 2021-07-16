   
package com.buit.cis.dctwork.request;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


/**
 * 类名称：CisHzyzQueryReq<br> 
 * 类描述：住院_病区医嘱列表查询<br>
 * @author GONGFANGZHOU
 */
@ApiModel(value="住院_病区医嘱_queryReq")
public class CisHzyzQueryReq {
	
	@ApiModelProperty(value="住院号")
	private Integer zyh;
	
	@ApiModelProperty(value="是否为临时医嘱(0:长期,1:临时,急诊,出院)")
	private Integer lsyz;
	
	@ApiModelProperty(value="打开方式(doctor,nurse)")
	private String openBy;
	
	@ApiModelProperty(value="项目类型(急诊出院分别传2,3)")
	private Integer xmlx;
	
	@ApiModelProperty(value="机构id", hidden = true)
	private Integer jgid;
	
	@ApiModelProperty(value="查询类型(不传)", hidden = true)
	private Integer type;
	
	@ApiModelProperty(value="药品类型(1,2,3)", hidden = true)
	private Integer yplx;
	
	@ApiModelProperty(value="急诊标志")
	private String jzbz;
	
	@ApiModelProperty(value="带药标志")
	private String dybz;
	
	@ApiModelProperty(value="长期标志")
	private String cqbz;
	
	@ApiModelProperty(value="临时标志")
	private String lsbz;
	
	@ApiModelProperty(value="药品标志")
	private String ypbz;
	
	@ApiModelProperty(value="项目标志")
	private String xmbz;
	
	@ApiModelProperty(value="是否显示停嘱医嘱(1:是,0:否)", hidden = true)
	private Integer sfxs;
	
	@ApiModelProperty(value="是否勾选已停")
	private String isStop;
	
	@ApiModelProperty(value="是否勾选已作废")
	private String isYzzf;

	public Integer getZyh() {
		return zyh;
	}

	public void setZyh(Integer zyh) {
		this.zyh = zyh;
	}

	public Integer getLsyz() {
		return lsyz;
	}

	public void setLsyz(Integer lsyz) {
		this.lsyz = lsyz;
	}

	public String getOpenBy() {
		return openBy;
	}

	public void setOpenBy(String openBy) {
		this.openBy = openBy;
	}

	public Integer getXmlx() {
		return xmlx;
	}

	public void setXmlx(Integer xmlx) {
		this.xmlx = xmlx;
	}

	public Integer getJgid() {
		return jgid;
	}

	public void setJgid(Integer jgid) {
		this.jgid = jgid;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getJzbz() {
		return jzbz;
	}

	public void setJzbz(String jzbz) {
		this.jzbz = jzbz;
	}

	public String getDybz() {
		return dybz;
	}

	public void setDybz(String dybz) {
		this.dybz = dybz;
	}

	public String getCqbz() {
		return cqbz;
	}

	public void setCqbz(String cqbz) {
		this.cqbz = cqbz;
	}

	public String getLsbz() {
		return lsbz;
	}

	public void setLsbz(String lsbz) {
		this.lsbz = lsbz;
	}

	public String getYpbz() {
		return ypbz;
	}

	public void setYpbz(String ypbz) {
		this.ypbz = ypbz;
	}

	public String getXmbz() {
		return xmbz;
	}

	public void setXmbz(String xmbz) {
		this.xmbz = xmbz;
	}

	public Integer getYplx() {
		return yplx;
	}

	public void setYplx(Integer yplx) {
		this.yplx = yplx;
	}

	public Integer getSfxs() {
		return sfxs;
	}

	public void setSfxs(Integer sfxs) {
		this.sfxs = sfxs;
	}

	public String getIsStop() {
		return isStop;
	}

	public void setIsStop(String isStop) {
		this.isStop = isStop;
	}

	public String getIsYzzf() {
		return isYzzf;
	}

	public void setIsYzzf(String isYzzf) {
		this.isYzzf = isYzzf;
	}	
	
	
	
}
