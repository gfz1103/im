package com.buit.cis.ims.request;

import com.buit.commons.PageQuery;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


/**
 * 门诊住院登记分页查询请求
 * 类名称：OpZydjPageReq<br>
 * 类描述：<br>
 * @author 老花生
 */
@ApiModel(value="门诊住院登记分页查询请求")
public class OpZydjPageReq  extends PageQuery {
  @ApiModelProperty(value="门诊号码")
  private String mzhm;

  public String getMzhm() {
    return mzhm;
  }

  public void setMzhm(String mzhm) {
    this.mzhm = mzhm;
  }
}
