package com.buit.cis.ims.model;

import com.buit.commons.PageQuery;

import java.sql.Timestamp;

/**
 * 类名称：MpiDemographicinfo<br> 
 * 类描述：EMPI个人基本信息
 * @author ZHOUHAISHENG 
 * @ApiModel(value="EMPI个人基本信息")
 */
public class MpiDemographicinfo  extends PageQuery {
	//@ApiModelProperty(value="EMPI")
    /** EMPI */
    private String empiid;
	//@ApiModelProperty(value="姓名")
    /** 姓名 */
    private String personname;
	//@ApiModelProperty(value="性别 | GB/T 2261.1-2003 与gender.xml字典关联")
    /** 性别 | GB/T 2261.1-2003 与gender.xml字典关联 */
    private String sexcode;
	//@ApiModelProperty(value="照片")
    /** 照片 */
    private String photo;
	//@ApiModelProperty(value="卡号")
    /** 卡号 */
    private String cardno;
	//@ApiModelProperty(value="身份证")
    /** 身份证 */
    private String idcard;
	//@ApiModelProperty(value="户籍标志 | 11=户籍（本区）")
    /** 户籍标志 | 11=户籍（本区） */
    private String registeredpermanent;
	//@ApiModelProperty(value="血型 | GA 324.6-2001 与blood.xml字典关联")
    /** 血型 | GA 324.6-2001 与blood.xml字典关联 */
    private String bloodtypecode;
	//@ApiModelProperty(value="出生地")
    /** 出生地 */
    private String homeplace;
	//@ApiModelProperty(value="姓名拼音")
    /** 姓名拼音 */
    private String namepycode;
	//@ApiModelProperty(value="联系地址")
    /** 联系地址 */
    private String address;
	//@ApiModelProperty(value="邮政编码")
    /** 邮政编码 */
    private String zipcode;
	//@ApiModelProperty(value="家庭电话")
    /** 家庭电话 */
    private String phonenumber;
	//@ApiModelProperty(value="本人电话")
    /** 本人电话 */
    private String mobilenumber;
	//@ApiModelProperty(value="电子邮件")
    /** 电子邮件 */
    private String email;
	//@ApiModelProperty(value="出生日期")
    /** 出生日期 */
    private Timestamp birthday;
	//@ApiModelProperty(value="国籍 | GB/T 2659-2000  与nationality.xml字典关联")
    /** 国籍 | GB/T 2659-2000  与nationality.xml字典关联 */
    private String nationalitycode;
	//@ApiModelProperty(value="民族 | GB3304-91 与ethnic.xml字典关联")
    /** 民族 | GB3304-91 与ethnic.xml字典关联 */
    private String nationcode;
	//@ApiModelProperty(value="RH血型 | 2=RH阳性 1=RH阴性 3=不详")
    /** RH血型 | 2=RH阳性 1=RH阴性 3=不详 */
    private String rhbloodcode;
	//@ApiModelProperty(value="婚姻状况 |  GB/T 2261.2-2003 与maritals.xml字典关联")
    /** 婚姻状况 |  GB/T 2261.2-2003 与maritals.xml字典关联 */
    private String maritalstatuscode;
	//@ApiModelProperty(value="参加工作日期")
    /** 参加工作日期 */
    private Timestamp startworkdate;
	//@ApiModelProperty(value="职业类别 | GB/T 6565-2009 与jobtitle.xml字典关联")
    /** 职业类别 | GB/T 6565-2009 与jobtitle.xml字典关联 */
    private String workcode;
	//@ApiModelProperty(value="文化程度 | GBT4658-198410 与education.xml字典关联")
    /** 文化程度 | GBT4658-198410 与education.xml字典关联 */
    private String educationcode;
	//@ApiModelProperty(value="保险类别 | 不使用")
    /** 保险类别 | 不使用 */
    private String insurancecode;
	//@ApiModelProperty(value="建档机构")
    /** 建档机构 */
    private String createunit;
	//@ApiModelProperty(value="建档人")
    /** 建档人 */
    private String createuser;
	//@ApiModelProperty(value="建档时间")
    /** 建档时间 */
    private Timestamp createtime;
	//@ApiModelProperty(value="最后修改机构")
    /** 最后修改机构 */
    private String lastmodifyunit;
	//@ApiModelProperty(value="最后修改时间")
    /** 最后修改时间 */
    private Timestamp lastmodifytime;
	//@ApiModelProperty(value="最后修改人")
    /** 最后修改人 */
    private String lastmodifyuser;
	//@ApiModelProperty(value="状态")
    /** 状态 */
    private String status;
	//@ApiModelProperty(value="工作单位")
    /** 工作单位 */
    private String workplace;
	//@ApiModelProperty(value="联系人")
    /** 联系人 */
    private String contact;
	//@ApiModelProperty(value="联系人电话")
    /** 联系人电话 */
    private String contactphone;
	//@ApiModelProperty(value="医保类别 | 不使用")
    /** 医保类别 | 不使用 */
    private String insurancetype;
	//@ApiModelProperty(value="版本号")
    /** 版本号 */
    private String versionnumber;
	//@ApiModelProperty(value="当insurancetype值为99，才有值")
    /** 当insurancetype值为99，才有值 */
    private String insurancetext;
	//@ApiModelProperty(value="scbz")
    /** scbz */
    private String scbz;
	//@ApiModelProperty(value="isnh")
    /** isnh */
    private Integer isnh;
	//@ApiModelProperty(value="ylzh")
    /** ylzh */
    private String ylzh;
	//@ApiModelProperty(value="????????")
    /** ???????? */
    private String brxz;
	//@ApiModelProperty(value="未输入身份证原因")
    /** 未输入身份证原因 */
    private Integer sfzyy;
	//@ApiModelProperty(value="保险卡号")
    /** 保险卡号 */
    private String bxcardno;
	//@ApiModelProperty(value="保险有效期起")
    /** 保险有效期起 */
    private Timestamp bxstart;
	//@ApiModelProperty(value="保险有效期止")
    /** 保险有效期止 */
    private Timestamp bxend;
	//@ApiModelProperty(value="保险公司")
    /** 保险公司 */
    private String bxcompany;
	//@ApiModelProperty(value="护照号")
    /** 护照号 */
    private String passportno;

    /** 设置:EMPI  */
    public void setEmpiid(String value) {
        this.empiid = value;
    }
    /** 获取:EMPI */
    public String getEmpiid() {
        return empiid;
    }

    /** 设置:姓名  */
    public void setPersonname(String value) {
        this.personname = value;
    }
    /** 获取:姓名 */
    public String getPersonname() {
        return personname;
    }

    /** 设置:性别 | GB/T 2261.1-2003 与gender.xml字典关联  */
    public void setSexcode(String value) {
        this.sexcode = value;
    }
    /** 获取:性别 | GB/T 2261.1-2003 与gender.xml字典关联 */
    public String getSexcode() {
        return sexcode;
    }

    /** 设置:照片  */
    public void setPhoto(String value) {
        this.photo = value;
    }
    /** 获取:照片 */
    public String getPhoto() {
        return photo;
    }

    /** 设置:卡号  */
    public void setCardno(String value) {
        this.cardno = value;
    }
    /** 获取:卡号 */
    public String getCardno() {
        return cardno;
    }

    /** 设置:身份证  */
    public void setIdcard(String value) {
        this.idcard = value;
    }
    /** 获取:身份证 */
    public String getIdcard() {
        return idcard;
    }

    /** 设置:户籍标志 | 11=户籍（本区）  */
    public void setRegisteredpermanent(String value) {
        this.registeredpermanent = value;
    }
    /** 获取:户籍标志 | 11=户籍（本区） */
    public String getRegisteredpermanent() {
        return registeredpermanent;
    }

    /** 设置:血型 | GA 324.6-2001 与blood.xml字典关联  */
    public void setBloodtypecode(String value) {
        this.bloodtypecode = value;
    }
    /** 获取:血型 | GA 324.6-2001 与blood.xml字典关联 */
    public String getBloodtypecode() {
        return bloodtypecode;
    }

    /** 设置:出生地  */
    public void setHomeplace(String value) {
        this.homeplace = value;
    }
    /** 获取:出生地 */
    public String getHomeplace() {
        return homeplace;
    }

    /** 设置:姓名拼音  */
    public void setNamepycode(String value) {
        this.namepycode = value;
    }
    /** 获取:姓名拼音 */
    public String getNamepycode() {
        return namepycode;
    }

    /** 设置:联系地址  */
    public void setAddress(String value) {
        this.address = value;
    }
    /** 获取:联系地址 */
    public String getAddress() {
        return address;
    }

    /** 设置:邮政编码  */
    public void setZipcode(String value) {
        this.zipcode = value;
    }
    /** 获取:邮政编码 */
    public String getZipcode() {
        return zipcode;
    }

    /** 设置:家庭电话  */
    public void setPhonenumber(String value) {
        this.phonenumber = value;
    }
    /** 获取:家庭电话 */
    public String getPhonenumber() {
        return phonenumber;
    }

    /** 设置:本人电话  */
    public void setMobilenumber(String value) {
        this.mobilenumber = value;
    }
    /** 获取:本人电话 */
    public String getMobilenumber() {
        return mobilenumber;
    }

    /** 设置:电子邮件  */
    public void setEmail(String value) {
        this.email = value;
    }
    /** 获取:电子邮件 */
    public String getEmail() {
        return email;
    }

    /** 设置:出生日期  */
    public void setBirthday(Timestamp value) {
        this.birthday = value;
    }
    /** 获取:出生日期 */
    public Timestamp getBirthday() {
        return birthday;
    }

    /** 设置:国籍 | GB/T 2659-2000  与nationality.xml字典关联  */
    public void setNationalitycode(String value) {
        this.nationalitycode = value;
    }
    /** 获取:国籍 | GB/T 2659-2000  与nationality.xml字典关联 */
    public String getNationalitycode() {
        return nationalitycode;
    }

    /** 设置:民族 | GB3304-91 与ethnic.xml字典关联  */
    public void setNationcode(String value) {
        this.nationcode = value;
    }
    /** 获取:民族 | GB3304-91 与ethnic.xml字典关联 */
    public String getNationcode() {
        return nationcode;
    }

    /** 设置:RH血型 | 2=RH阳性 1=RH阴性 3=不详  */
    public void setRhbloodcode(String value) {
        this.rhbloodcode = value;
    }
    /** 获取:RH血型 | 2=RH阳性 1=RH阴性 3=不详 */
    public String getRhbloodcode() {
        return rhbloodcode;
    }

    /** 设置:婚姻状况 |  GB/T 2261.2-2003 与maritals.xml字典关联  */
    public void setMaritalstatuscode(String value) {
        this.maritalstatuscode = value;
    }
    /** 获取:婚姻状况 |  GB/T 2261.2-2003 与maritals.xml字典关联 */
    public String getMaritalstatuscode() {
        return maritalstatuscode;
    }

    /** 设置:参加工作日期  */
    public void setStartworkdate(Timestamp value) {
        this.startworkdate = value;
    }
    /** 获取:参加工作日期 */
    public Timestamp getStartworkdate() {
        return startworkdate;
    }

    /** 设置:职业类别 | GB/T 6565-2009 与jobtitle.xml字典关联  */
    public void setWorkcode(String value) {
        this.workcode = value;
    }
    /** 获取:职业类别 | GB/T 6565-2009 与jobtitle.xml字典关联 */
    public String getWorkcode() {
        return workcode;
    }

    /** 设置:文化程度 | GBT4658-198410 与education.xml字典关联  */
    public void setEducationcode(String value) {
        this.educationcode = value;
    }
    /** 获取:文化程度 | GBT4658-198410 与education.xml字典关联 */
    public String getEducationcode() {
        return educationcode;
    }

    /** 设置:保险类别 | 不使用  */
    public void setInsurancecode(String value) {
        this.insurancecode = value;
    }
    /** 获取:保险类别 | 不使用 */
    public String getInsurancecode() {
        return insurancecode;
    }

    /** 设置:建档机构  */
    public void setCreateunit(String value) {
        this.createunit = value;
    }
    /** 获取:建档机构 */
    public String getCreateunit() {
        return createunit;
    }

    /** 设置:建档人  */
    public void setCreateuser(String value) {
        this.createuser = value;
    }
    /** 获取:建档人 */
    public String getCreateuser() {
        return createuser;
    }

    /** 设置:建档时间  */
    public void setCreatetime(Timestamp value) {
        this.createtime = value;
    }
    /** 获取:建档时间 */
    public Timestamp getCreatetime() {
        return createtime;
    }

    /** 设置:最后修改机构  */
    public void setLastmodifyunit(String value) {
        this.lastmodifyunit = value;
    }
    /** 获取:最后修改机构 */
    public String getLastmodifyunit() {
        return lastmodifyunit;
    }

    /** 设置:最后修改时间  */
    public void setLastmodifytime(Timestamp value) {
        this.lastmodifytime = value;
    }
    /** 获取:最后修改时间 */
    public Timestamp getLastmodifytime() {
        return lastmodifytime;
    }

    /** 设置:最后修改人  */
    public void setLastmodifyuser(String value) {
        this.lastmodifyuser = value;
    }
    /** 获取:最后修改人 */
    public String getLastmodifyuser() {
        return lastmodifyuser;
    }

    /** 设置:状态  */
    public void setStatus(String value) {
        this.status = value;
    }
    /** 获取:状态 */
    public String getStatus() {
        return status;
    }

    /** 设置:工作单位  */
    public void setWorkplace(String value) {
        this.workplace = value;
    }
    /** 获取:工作单位 */
    public String getWorkplace() {
        return workplace;
    }

    /** 设置:联系人  */
    public void setContact(String value) {
        this.contact = value;
    }
    /** 获取:联系人 */
    public String getContact() {
        return contact;
    }

    /** 设置:联系人电话  */
    public void setContactphone(String value) {
        this.contactphone = value;
    }
    /** 获取:联系人电话 */
    public String getContactphone() {
        return contactphone;
    }

    /** 设置:医保类别 | 不使用  */
    public void setInsurancetype(String value) {
        this.insurancetype = value;
    }
    /** 获取:医保类别 | 不使用 */
    public String getInsurancetype() {
        return insurancetype;
    }

    /** 设置:版本号  */
    public void setVersionnumber(String value) {
        this.versionnumber = value;
    }
    /** 获取:版本号 */
    public String getVersionnumber() {
        return versionnumber;
    }

    /** 设置:当insurancetype值为99，才有值  */
    public void setInsurancetext(String value) {
        this.insurancetext = value;
    }
    /** 获取:当insurancetype值为99，才有值 */
    public String getInsurancetext() {
        return insurancetext;
    }

    /** 设置:scbz  */
    public void setScbz(String value) {
        this.scbz = value;
    }
    /** 获取:scbz */
    public String getScbz() {
        return scbz;
    }

    /** 设置:isnh  */
    public void setIsnh(Integer value) {
        this.isnh = value;
    }
    /** 获取:isnh */
    public Integer getIsnh() {
        return isnh;
    }

    /** 设置:ylzh  */
    public void setYlzh(String value) {
        this.ylzh = value;
    }
    /** 获取:ylzh */
    public String getYlzh() {
        return ylzh;
    }

    /** 设置:????????  */
    public void setBrxz(String value) {
        this.brxz = value;
    }
    /** 获取:???????? */
    public String getBrxz() {
        return brxz;
    }

    /** 设置:未输入身份证原因  */
    public void setSfzyy(Integer value) {
        this.sfzyy = value;
    }
    /** 获取:未输入身份证原因 */
    public Integer getSfzyy() {
        return sfzyy;
    }

    /** 设置:保险卡号  */
    public void setBxcardno(String value) {
        this.bxcardno = value;
    }
    /** 获取:保险卡号 */
    public String getBxcardno() {
        return bxcardno;
    }

    /** 设置:保险有效期起  */
    public void setBxstart(Timestamp value) {
        this.bxstart = value;
    }
    /** 获取:保险有效期起 */
    public Timestamp getBxstart() {
        return bxstart;
    }

    /** 设置:保险有效期止  */
    public void setBxend(Timestamp value) {
        this.bxend = value;
    }
    /** 获取:保险有效期止 */
    public Timestamp getBxend() {
        return bxend;
    }

    /** 设置:保险公司  */
    public void setBxcompany(String value) {
        this.bxcompany = value;
    }
    /** 获取:保险公司 */
    public String getBxcompany() {
        return bxcompany;
    }

    /** 设置:护照号  */
    public void setPassportno(String value) {
        this.passportno = value;
    }
    /** 获取:护照号 */
    public String getPassportno() {
        return passportno;
    }


}