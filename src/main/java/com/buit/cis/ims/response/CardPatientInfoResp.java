package com.buit.cis.ims.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 病人管理-费用张卡信息(出参)
 * zhouhaisheng
 */
@ApiModel(value = "病人管理-费用张卡信息(出参)")
public class CardPatientInfoResp {
 @ApiModelProperty(value="结算类型")
 private String jslx;

 @ApiModelProperty(value="住院号")
 private Integer zyh;

 @ApiModelProperty(value="住院号码")
 private String zyhm;

 @ApiModelProperty(value="病人床号")
 private String brch;

 @ApiModelProperty(value="病人姓名")
 private String brxm;

 @ApiModelProperty(value="病人性质")
 private Integer brxz;

 @ApiModelProperty(value="病人科室")
 private Integer brks;
 @ApiModelProperty(value="病人病区")
 private Integer brbq;

 @ApiModelProperty(value="开始日期(废弃)")
 private String startDate;

 @ApiModelProperty(value="费用合计(总费用)")
 private BigDecimal fyhj;

 @ApiModelProperty(value="自负合计(自负)")
 private BigDecimal zfhj;

 @ApiModelProperty(value="总缴款")
 private BigDecimal jkje;

 @ApiModelProperty(value="余款")
 private BigDecimal yk;

 @ApiModelProperty(value="入院日期")
 private Date ryrq;

 @ApiModelProperty(value="出院日期")
 private Date cyrq;

 @ApiModelProperty(value="结算开始日期")
 private Date ksrq;

 @ApiModelProperty(value="结算终止日期")
 private Date zzrq;

 @ApiModelProperty(value="住院天数")
 private Double zyts;

 @ApiModelProperty(value="结算天数")
 private Double jsts;

 @ApiModelProperty(value="终止日期(废弃)")
 private String endDate;

 @ApiModelProperty(value="合计金额")
 private BigDecimal hjje;

 @ApiModelProperty(value="合计金额(人民币大写)")
 private String hjjedx;

 @ApiModelProperty(value="结算日期")
 private Date jsrq;

 @ApiModelProperty(value="结算次数")
 private Integer jscs;

 @ApiModelProperty(value="医保结算开始日期")
 private String ybjsksrq;

 @ApiModelProperty(value="工伤认定号")
 private String gsrdh;

 @ApiModelProperty(value="是否医保-用于预结算显示医保费用")
 private String isyb;

 public String getZyhm() {
  return zyhm;
 }

 public void setZyhm(String zyhm) {
  this.zyhm = zyhm;
 }

 public String getBrch() {
  return brch;
 }

 public void setBrch(String brch) {
  this.brch = brch;
 }

 public String getBrxm() {
  return brxm;
 }

 public void setBrxm(String brxm) {
  this.brxm = brxm;
 }

 public Integer getBrxz() {
  return brxz;
 }

 public void setBrxz(Integer brxz) {
  this.brxz = brxz;
 }

 public Integer getBrks() {
  return brks;
 }

 public void setBrks(Integer brks) {
  this.brks = brks;
 }

 public String getStartDate() {
  return startDate;
 }

 public void setStartDate(String startDate) {
  this.startDate = startDate;
 }

 public BigDecimal getFyhj() {
  return fyhj;
 }

 public void setFyhj(BigDecimal fyhj) {
  this.fyhj = fyhj;
 }

 public BigDecimal getZfhj() {
  return zfhj;
 }

 public void setZfhj(BigDecimal zfhj) {
  this.zfhj = zfhj;
 }

 public BigDecimal getJkje() {
  return jkje;
 }

 public void setJkje(BigDecimal jkje) {
  this.jkje = jkje;
 }

 public BigDecimal getYk() {
  return yk;
 }

 public void setYk(BigDecimal yk) {
  this.yk = yk;
 }

 public Double getZyts() {
  return zyts;
 }

 public void setZyts(Double zyts) {
  this.zyts = zyts;
 }

 public String getEndDate() {
  return endDate;
 }

 public void setEndDate(String endDate) {
  this.endDate = endDate;
 }

 public BigDecimal getHjje() {
  return hjje;
 }

 public void setHjje(BigDecimal hjje) {
  this.hjje = hjje;
 }

 public String getHjjedx() {
  return hjjedx;
 }

 public void setHjjedx(String hjjedx) {
  this.hjjedx = hjjedx;
 }

 public String getJslx() {
  return jslx;
 }

 public void setJslx(String jslx) {
  this.jslx = jslx;
 }

/* public ZyJsxxReq getZyJsxxReq() {
  return zyJsxxReq;
 }

 public void setZyJsxxReq(ZyJsxxReq zyJsxxReq) {
  this.zyJsxxReq = zyJsxxReq;
 }*/

 public Integer getZyh() {
  return zyh;
 }

 public void setZyh(Integer zyh) {
  this.zyh = zyh;
 }

 public Integer getBrbq() {
  return brbq;
 }

 public void setBrbq(Integer brbq) {
  this.brbq = brbq;
 }

 public Double getJsts() {
  return jsts;
 }

 public void setJsts(Double jsts) {
  this.jsts = jsts;
 }

 public Date getRyrq() {
  return ryrq;
 }

 public void setRyrq(Date ryrq) {
  this.ryrq = ryrq;
 }

 public Date getCyrq() {
  return cyrq;
 }

 public void setCyrq(Date cyrq) {
  this.cyrq = cyrq;
 }

 public Date getKsrq() {
  return ksrq;
 }

 public void setKsrq(Date ksrq) {
  this.ksrq = ksrq;
 }

 public Date getZzrq() {
  return zzrq;
 }

 public void setZzrq(Date zzrq) {
  this.zzrq = zzrq;
 }

 public Date getJsrq() {
  return jsrq;
 }

 public void setJsrq(Date jsrq) {
  this.jsrq = jsrq;
 }

 public Integer getJscs() {
  return jscs;
 }

 public void setJscs(Integer jscs) {
  this.jscs = jscs;
 }

 public String getYbjsksrq() {
  return ybjsksrq;
 }

 public void setYbjsksrq(String ybjsksrq) {
  this.ybjsksrq = ybjsksrq;
 }

 public String getGsrdh() {
  return gsrdh;
 }

 public void setGsrdh(String gsrdh) {
  this.gsrdh = gsrdh;
 }

 public String getIsyb() {
  return isyb;
 }

 public void setIsyb(String isyb) {
  this.isyb = isyb;
 }
}
