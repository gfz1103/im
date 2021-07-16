   
package com.buit.cis.dctwork.response;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


/**
 * 类名称：CisZyHzyj<br> 
 * 类描述：<br>
 * @author GONGFANGZHOU
 */
@ApiModel(value="会诊意见_Resp")
public class CisZyHzyjResp {
	@ApiModelProperty(value="会诊意见")
    private String hzyj;
	
	@ApiModelProperty(value="科室名称")
    private String officename;
	
	@ApiModelProperty(value="医生名称")
    private String personname;
	
	@ApiModelProperty(value="所属医生")
	private String ssys;
	
	@ApiModelProperty(value="所属科室")
	private Integer ksdm;

	public String getHzyj() {
		return hzyj;
	}

	public void setHzyj(String hzyj) {
		this.hzyj = hzyj;
	}

	public String getOfficename() {
		return officename;
	}

	public void setOfficename(String officename) {
		this.officename = officename;
	}

	public String getPersonname() {
		return personname;
	}

	public void setPersonname(String personname) {
		this.personname = personname;
	}

	public String getSsys() {
		return ssys;
	}

	public void setSsys(String ssys) {
		this.ssys = ssys;
	}

	public Integer getKsdm() {
		return ksdm;
	}

	public void setKsdm(Integer ksdm) {
		this.ksdm = ksdm;
	}
	
	
}
