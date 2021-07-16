package com.buit.cis.ims.request;

import com.buit.commons.PageQuery;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 住院员工票据分页查询入参
 * zhouhaisheng
 */
@ApiModel(value = "住院员工票据分页查询入参")
public class ImYgpjPageReq extends PageQuery {
 @ApiModelProperty(value = "票据类型 1：发票号码维护  2：缴款收据维护")
  private Integer pjlx;

 public Integer getPjlx() {
  return pjlx;
 }

 public void setPjlx(Integer pjlx) {
  this.pjlx = pjlx;
 }
}
