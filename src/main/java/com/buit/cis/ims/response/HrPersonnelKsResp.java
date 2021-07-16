package com.buit.cis.ims.response;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 类名称：HrPersonnelKsResp<br> 
 * 类描述：员工信息
 * @author 神算子 
 * 
 */
@ApiModel(value="员工信息_ksResp")
public class HrPersonnelKsResp {

	@ApiModelProperty(value = "人员id")
	private Integer personid;
	
	@ApiModelProperty(value = "人员姓名")
	private String personname;
	
	@ApiModelProperty(value = "科室id")
	private Integer id;
	
	@ApiModelProperty(value = "科室名称")
	private String officename;
	
	@ApiModelProperty(value = "医疗角色")
	private Integer medicalroles;

	public Integer getPersonid() {
		return personid;
	}

	public void setPersonid(Integer personid) {
		this.personid = personid;
	}

	public String getPersonname() {
		return personname;
	}

	public void setPersonname(String personname) {
		this.personname = personname;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getOfficename() {
		return officename;
	}

	public void setOfficename(String officename) {
		this.officename = officename;
	}

	public Integer getMedicalroles() {
		return medicalroles;
	}

	public void setMedicalroles(Integer medicalroles) {
		this.medicalroles = medicalroles;
	}
	
	
}
