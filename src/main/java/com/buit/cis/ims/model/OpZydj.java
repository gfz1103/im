package com.buit.cis.ims.model;

import com.buit.commons.PageQuery;

import java.sql.Date;

/**
 * 类名称：OpZydj<br> 
 * 类描述：住院病人入院登记
 * @author zhouhaisheng
 * @ApiModel(value="病人住院登记")
 */
public class OpZydj  extends PageQuery {
	//@ApiModelProperty(value="主键ID")
    /** 主键ID */
    private Integer djid;
	//@ApiModelProperty(value="卡号")
    /** 卡号 */
    private String cardno;
	//@ApiModelProperty(value="病人ID")
    /** 病人ID */
    private Integer brid;
	//@ApiModelProperty(value="联系人姓名")
    /** 联系人姓名 */
    private String lxrm;
	//@ApiModelProperty(value="联系人地址")
    /** 联系人地址 */
    private String lxdz;
	//@ApiModelProperty(value="联系电话")
    /** 联系电话 */
    private String lxdh;
	//@ApiModelProperty(value="门诊医生")
    /** 门诊医生 */
    private String mzys;
	//@ApiModelProperty(value="门诊科室")
    /** 门诊科室 */
    private String mzks;
	//@ApiModelProperty(value="门诊诊断")
    /** 门诊诊断 */
    private String brzd;
	//@ApiModelProperty(value="入院目的")
    /** 入院目的 */
    private String rymd;
	//@ApiModelProperty(value="登记类型")
    /** 登记类型 */
    private String djlx;
	//@ApiModelProperty(value="拟收治病区")
    /** 拟收治病区 */
    private String nszbq;
	//@ApiModelProperty(value="特需标志")
    /** 特需标志 */
    private Integer txbz;
	//@ApiModelProperty(value="开单日期")
    /** 开单日期 */
    private Date kdrq;
	//@ApiModelProperty(value="申请状态")
    /** 申请状态 */
    private String sqzt;
	//@ApiModelProperty(value="主诊断")
    /** 主诊断 */
    private String zzd;
	//@ApiModelProperty(value="主诊断ICD10")
    /** 主诊断ICD10 */
    private String zzdicd10;
	//@ApiModelProperty(value="大病减负标志 1:尿毒症透析医疗费用,2:肾移植减负,3：精神病减负  其他：不减负")
    /** 大病减负标志 1:尿毒症透析医疗费用,2:肾移植减负,3：精神病减负  其他：不减负 */
    private String dbjfbz;
    //@ApiModelProperty(value="就诊流水号")
    /** 就诊流水号 */
    private String jzlsh;

    public Integer getDjid() {
        return djid;
    }

    public void setDjid(Integer djid) {
        this.djid = djid;
    }

    /** 设置:卡号  */
    public void setCardno(String value) {
        this.cardno = value;
    }
    /** 获取:卡号 */
    public String getCardno() {
        return cardno;
    }

    /** 设置:病人ID  */
    public void setBrid(Integer value) {
        this.brid = value;
    }
    /** 获取:病人ID */
    public Integer getBrid() {
        return brid;
    }

    /** 设置:联系人姓名  */
    public void setLxrm(String value) {
        this.lxrm = value;
    }
    /** 获取:联系人姓名 */
    public String getLxrm() {
        return lxrm;
    }

    /** 设置:联系人地址  */
    public void setLxdz(String value) {
        this.lxdz = value;
    }
    /** 获取:联系人地址 */
    public String getLxdz() {
        return lxdz;
    }

    /** 设置:联系电话  */
    public void setLxdh(String value) {
        this.lxdh = value;
    }
    /** 获取:联系电话 */
    public String getLxdh() {
        return lxdh;
    }

    /** 设置:门诊医生  */
    public void setMzys(String value) {
        this.mzys = value;
    }
    /** 获取:门诊医生 */
    public String getMzys() {
        return mzys;
    }

    /** 设置:门诊科室  */
    public void setMzks(String value) {
        this.mzks = value;
    }
    /** 获取:门诊科室 */
    public String getMzks() {
        return mzks;
    }

    /** 设置:门诊诊断  */
    public void setBrzd(String value) {
        this.brzd = value;
    }
    /** 获取:门诊诊断 */
    public String getBrzd() {
        return brzd;
    }

    /** 设置:入院目的  */
    public void setRymd(String value) {
        this.rymd = value;
    }
    /** 获取:入院目的 */
    public String getRymd() {
        return rymd;
    }

    /** 设置:登记类型  */
    public void setDjlx(String value) {
        this.djlx = value;
    }
    /** 获取:登记类型 */
    public String getDjlx() {
        return djlx;
    }

    /** 设置:拟收治病区  */
    public void setNszbq(String value) {
        this.nszbq = value;
    }
    /** 获取:拟收治病区 */
    public String getNszbq() {
        return nszbq;
    }

    /** 设置:特需标志  */
    public void setTxbz(Integer value) {
        this.txbz = value;
    }
    /** 获取:特需标志 */
    public Integer getTxbz() {
        return txbz;
    }

    /** 设置:开单日期  */
    public void setKdrq(Date value) {
        this.kdrq = value;
    }
    /** 获取:开单日期 */
    public Date getKdrq() {
        return kdrq;
    }

    /** 设置:申请状态  */
    public void setSqzt(String value) {
        this.sqzt = value;
    }
    /** 获取:申请状态 */
    public String getSqzt() {
        return sqzt;
    }

    /** 设置:主诊断  */
    public void setZzd(String value) {
        this.zzd = value;
    }
    /** 获取:主诊断 */
    public String getZzd() {
        return zzd;
    }

    /** 设置:主诊断ICD10  */
    public void setZzdicd10(String value) {
        this.zzdicd10 = value;
    }
    /** 获取:主诊断ICD10 */
    public String getZzdicd10() {
        return zzdicd10;
    }

    /** 设置:大病减负标志 1:尿毒症透析医疗费用,2:肾移植减负,3：精神病减负  其他：不减负  */
    public void setDbjfbz(String value) {
        this.dbjfbz = value;
    }
    /** 获取:大病减负标志 1:尿毒症透析医疗费用,2:肾移植减负,3：精神病减负  其他：不减负 */
    public String getDbjfbz() {
        return dbjfbz;
    }

    public String getJzlsh() {
        return jzlsh;
    }

    public void setJzlsh(String jzlsh) {
        this.jzlsh = jzlsh;
    }
}
