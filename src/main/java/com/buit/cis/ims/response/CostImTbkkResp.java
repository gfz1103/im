package com.buit.cis.ims.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * 病人管理-账卡-缴费记录列表
 * zhouhaisheng
 */
@ApiModel(value = "病人管理-账卡-缴费记录列表")
public class CostImTbkkResp {

 @ApiModelProperty(value="缴款日期")
 private Timestamp jkrq;
 @ApiModelProperty(value="缴款金额")
 private BigDecimal jkje;
 @ApiModelProperty(value="缴款方式 | 字典sys_dict_config:20")
 private Integer jkfs;
 @ApiModelProperty(value="收据号码 | 病人预缴款收据号码")
 private String sjhm;
 @ApiModelProperty(value="发票号码 | 病人结算发票号码")
 private String fphm;

 @ApiModelProperty(value="备注 | 0.正常缴款; 1.中结转存 转存判别字典sys_flag_data:ZY000001")
 private Integer zcpb;

 @ApiModelProperty(value="缴款类型 | 0：正常预缴   1：结算抵扣   2：取消结算   3：注销找退   4：出院找退")
 private Integer jklx;

// @ApiModelProperty(value="备注 | 0.正常缴款; 1.作废")
// private Integer zfpb;

 @ApiModelProperty(value="操作员")
 private String czy;

 public Timestamp getJkrq() {
  return jkrq;
 }

 public void setJkrq(Timestamp jkrq) {
  this.jkrq = jkrq;
 }

 public BigDecimal getJkje() {
  return jkje;
 }

 public void setJkje(BigDecimal jkje) {
  this.jkje = jkje;
 }

 public Integer getJkfs() {
  return jkfs;
 }

 public void setJkfs(Integer jkfs) {
  this.jkfs = jkfs;
 }

 public String getSjhm() {
  return sjhm;
 }

 public void setSjhm(String sjhm) {
  this.sjhm = sjhm;
 }

 public Integer getZcpb() {
  return zcpb;
 }

 public void setZcpb(Integer zcpb) {
  this.zcpb = zcpb;
 }

 public String getCzy() {
  return czy;
 }

 public void setCzy(String czy) {
  this.czy = czy;
 }

// public Integer getZfpb() {
//  return zfpb;
// }
//
// public void setZfpb(Integer zfpb) {
//  this.zfpb = zfpb;
// }

 public Integer getJklx() {
  return jklx;
 }

 public void setJklx(Integer jklx) {
  this.jklx = jklx;
 }

 public String getFphm() {
  return fphm;
 }

 public void setFphm(String fphm) {
  this.fphm = fphm;
 }
}
