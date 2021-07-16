package com.buit.cis.ims.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * zhouhaisheng
 * 床位管理-床位分配保存入参
 */
@ApiModel(value = "床位管理-床位分配保存入参")
public class CwfpReq {

   @ApiModelProperty(value = "病人床号")
   private String brch;

   @ApiModelProperty(value = "住院号")
   private Integer zyh;

   @ApiModelProperty(value = "病人科室")
   private Integer brks;

    @ApiModelProperty(value = "病人病区")
    private Integer ksdm;

   @ApiModelProperty(value = "入院日期")
   private String ryrq;


 public String getBrch() {
  return brch;
 }

 public void setBrch(String brch) {
  this.brch = brch;
 }

 public Integer getZyh() {
  return zyh;
 }

 public void setZyh(Integer zyh) {
  this.zyh = zyh;
 }

 public Integer getBrks() {
  return brks;
 }

 public void setBrks(Integer brks) {
  this.brks = brks;
 }

 public String getRyrq() {
  return ryrq;
 }

 public void setRyrq(String ryrq) {
  this.ryrq = ryrq;
 }

    public Integer getKsdm() {
        return ksdm;
    }

    public void setKsdm(Integer ksdm) {
        this.ksdm = ksdm;
    }


}
