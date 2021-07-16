package com.buit.cis.ims.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * zhouhaisheng
 * 床位转换保存入参
 */
@ApiModel(value = "床位转换保存入参")
public class DoGetToBedInfoReq {
  @ApiModelProperty("住院号")
  private Integer zyh;
 @ApiModelProperty("旧床位号")
  private String oldCwhm;
 @ApiModelProperty("病人科室")
  private Integer brks;
 @ApiModelProperty("新床位号")
 private String newCwhm;
 @ApiModelProperty("新床位病区")
 private Integer newBrbq;

 public Integer getZyh() {
  return zyh;
 }

 public void setZyh(Integer zyh) {
  this.zyh = zyh;
 }

 public String getOldCwhm() {
  return oldCwhm;
 }

 public void setOldCwhm(String oldCwhm) {
  this.oldCwhm = oldCwhm;
 }

 public Integer getBrks() {
  return brks;
 }

 public void setBrks(Integer brks) {
  this.brks = brks;
 }

 public String getNewCwhm() {
  return newCwhm;
 }

 public void setNewCwhm(String newCwhm) {
  this.newCwhm = newCwhm;
 }

 public Integer getNewBrbq() {
  return newBrbq;
 }

 public void setNewBrbq(Integer newBrbq) {
  this.newBrbq = newBrbq;
 }
}
