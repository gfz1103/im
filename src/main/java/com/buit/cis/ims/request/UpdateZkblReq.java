package com.buit.cis.ims.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;

/**
 * 修改折扣比例入参
 * zhouhaisheng
 */
@ApiModel(value = "修改折扣比例入参")
public class UpdateZkblReq {
    @ApiModelProperty(value = "住院号")
    public  Integer zyh;
    @ApiModelProperty(value = "折扣比例")
    public BigDecimal zkbl;
    @ApiModelProperty(value = "结算次数")
    public Integer jscs;
    @ApiModelProperty(value = "费用序号")
    public Integer fyxh;

 public Integer getZyh() {
  return zyh;
 }

 public void setZyh(Integer zyh) {
  this.zyh = zyh;
 }

 public BigDecimal getZkbl() {
  return zkbl;
 }

 public void setZkbl(BigDecimal zkbl) {
  this.zkbl = zkbl;
 }

 public Integer getJscs() {
  return jscs;
 }

 public void setJscs(Integer jscs) {
  this.jscs = jscs;
 }

 public Integer getFyxh() {
  return fyxh;
 }

 public void setFyxh(Integer fyxh) {
  this.fyxh = fyxh;
 }
}
