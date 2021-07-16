package com.buit.cis.ims.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 *根据病人性别查询病人可分配的床位信息入参
 *zhouhasiheng
 */
@ApiModel(value = "根据病人性别查询病人可分配的床位信息入参")
public class QueryCwListByBrksAndBrxbReq {

 @ApiModelProperty("病人科室")
 private Integer brks;
@ApiModelProperty("病人性别")
 private Integer brxb;

 public Integer getBrks() {
  return brks;
 }

 public void setBrks(Integer brks) {
  this.brks = brks;
 }

 public Integer getBrxb() {
  return brxb;
 }

 public void setBrxb(Integer brxb) {
  this.brxb = brxb;
 }
}
