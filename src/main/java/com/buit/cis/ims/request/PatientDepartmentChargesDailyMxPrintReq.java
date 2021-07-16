package com.buit.cis.ims.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "日终结账明细查询入参")
public class PatientDepartmentChargesDailyMxPrintReq {

    @ApiModelProperty(value = "类型(查询ty=1;产生ty=2;打印ty=3)")
    private Integer ty;

    @ApiModelProperty(value = "结账日期")
    private String jzrq;

    @ApiModelProperty(value = "明细类型1:结算明细 2预交款明细")
    private Integer mxType;

    public Integer getTy() {
        return ty;
    }

    public void setTy(Integer type) {
        this.ty = type;
    }

    public String getJzrq() {
        return jzrq;
    }

    public void setJzrq(String type) {
        this.jzrq = type;
    }

    public Integer getMxType() {
        return mxType;
    }

    public void setMxType(Integer type) {
        this.mxType = type;
    }

}
