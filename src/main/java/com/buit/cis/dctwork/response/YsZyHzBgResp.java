   
package com.buit.cis.dctwork.response;

import java.sql.Timestamp;

import com.buit.commons.PageQuery;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


/**
 * 类名称：YsZyHzBgResp<br> 
 * 类描述：<br>
 * @author GONGFANGZHOU
 */
@ApiModel(value="会诊报告_Resp")
public class YsZyHzBgResp  extends  PageQuery{

     @ApiModelProperty(value="selectflag")
	 private Integer selectflag;
    
     @ApiModelProperty(value="来源")
     private Integer hispatienttype;
     
     @ApiModelProperty(value="门诊病人号码")
     private String clinicpatientid;
     
     @ApiModelProperty(value="住院病人号码")
     private String infeepatientid; 
     
     @ApiModelProperty(value="检查类型")
     private String devicetype;
     
     @ApiModelProperty(value="设备名称")
     private String devicename;
     
     @ApiModelProperty(value="检查项目")
     private String studyscription;
     
     @ApiModelProperty(value="开单科室")
     private String reqdepartmentname;
     
     @ApiModelProperty(value="申请医生")
     private String sqys;
     
     @ApiModelProperty(value="检查时间")
     private Timestamp studytime;
     
     @ApiModelProperty(value="申请时间")
     private Timestamp sqsj;
     
     @ApiModelProperty(value="会诊医生")
     private String hzys;
     
     @ApiModelProperty(value="检查状态")
     private String studystatusname;
     
     @ApiModelProperty(value="报告结果")
     private String reportdescribe;
     
     @ApiModelProperty(value="临床诊断")
     private String reportdiagnose;
     
     @ApiModelProperty(value="开单医生")
     private String doctorcode;
	 
     @ApiModelProperty(value="阳性状态")
	 private String ifmasculinename;

     @ApiModelProperty(value="未知")
     private String diagid;

	public Integer getSelectflag() {
		return selectflag;
	}

	public void setSelectflag(Integer selectflag) {
		this.selectflag = selectflag;
	}

	public Integer getHispatienttype() {
		return hispatienttype;
	}

	public void setHispatienttype(Integer hispatienttype) {
		this.hispatienttype = hispatienttype;
	}

	public String getClinicpatientid() {
		return clinicpatientid;
	}

	public void setClinicpatientid(String clinicpatientid) {
		this.clinicpatientid = clinicpatientid;
	}

	public String getInfeepatientid() {
		return infeepatientid;
	}

	public void setInfeepatientid(String infeepatientid) {
		this.infeepatientid = infeepatientid;
	}

	public String getDevicetype() {
		return devicetype;
	}

	public void setDevicetype(String devicetype) {
		this.devicetype = devicetype;
	}

	public String getDevicename() {
		return devicename;
	}

	public void setDevicename(String devicename) {
		this.devicename = devicename;
	}

	public String getStudyscription() {
		return studyscription;
	}

	public void setStudyscription(String studyscription) {
		this.studyscription = studyscription;
	}

	public String getReqdepartmentname() {
		return reqdepartmentname;
	}

	public void setReqdepartmentname(String reqdepartmentname) {
		this.reqdepartmentname = reqdepartmentname;
	}

	public String getSqys() {
		return sqys;
	}

	public void setSqys(String sqys) {
		this.sqys = sqys;
	}

	public Timestamp getStudytime() {
		return studytime;
	}

	public void setStudytime(Timestamp studytime) {
		this.studytime = studytime;
	}

	public Timestamp getSqsj() {
		return sqsj;
	}

	public void setSqsj(Timestamp sqsj) {
		this.sqsj = sqsj;
	}

	public String getHzys() {
		return hzys;
	}

	public void setHzys(String hzys) {
		this.hzys = hzys;
	}

	public String getStudystatusname() {
		return studystatusname;
	}

	public void setStudystatusname(String studystatusname) {
		this.studystatusname = studystatusname;
	}

	public String getReportdescribe() {
		return reportdescribe;
	}

	public void setReportdescribe(String reportdescribe) {
		this.reportdescribe = reportdescribe;
	}

	public String getReportdiagnose() {
		return reportdiagnose;
	}

	public void setReportdiagnose(String reportdiagnose) {
		this.reportdiagnose = reportdiagnose;
	}

	public String getDoctorcode() {
		return doctorcode;
	}

	public void setDoctorcode(String doctorcode) {
		this.doctorcode = doctorcode;
	}

	public String getIfmasculinename() {
		return ifmasculinename;
	}

	public void setIfmasculinename(String ifmasculinename) {
		this.ifmasculinename = ifmasculinename;
	}

	public String getDiagid() {
		return diagid;
	}

	public void setDiagid(String diagid) {
		this.diagid = diagid;
	}
	
}