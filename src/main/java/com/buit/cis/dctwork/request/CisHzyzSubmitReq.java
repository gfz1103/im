   
package com.buit.cis.dctwork.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


/**
 * 类名称：CisHzyz<br> 
 * 类描述：住院_病区医嘱<br>
 * @author GONGFANGZHOU
 */
@ApiModel(value="住院_病区医嘱_submitReq")
public class CisHzyzSubmitReq {

    @ApiModelProperty(value="住院号")
    private Integer zyh;

	@ApiModelProperty(value="临时医嘱")
	private Integer lsyz;
	
	@ApiModelProperty(value="病人病区")
    private Integer brbq;
	
	@ApiModelProperty(value="病人姓名")
    private String brxm;
	
	@ApiModelProperty(value="病人床号")
    private String brch;
	
	@ApiModelProperty(value="机构id", hidden = true)
	private Integer jgid;
	
	@ApiModelProperty(value="项目类型(急诊2,出院3)")
	private Integer xmlx;
	

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

	public Integer getBrbq() {
		return brbq;
	}

	public void setBrbq(Integer brbq) {
		this.brbq = brbq;
	}

	public String getBrxm() {
		return brxm;
	}

	public void setBrxm(String brxm) {
		this.brxm = brxm;
	}

	public String getBrch() {
		return brch;
	}

	public void setBrch(String brch) {
		this.brch = brch;
	}

	public Integer getJgid() {
		return jgid;
	}

	public void setJgid(Integer jgid) {
		this.jgid = jgid;
	}

	public Integer getXmlx() {
		return xmlx;
	}

	public void setXmlx(Integer xmlx) {
		this.xmlx = xmlx;
	}
	
	
}
