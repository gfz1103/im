package com.buit.cis.ims.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 日终结账查询入参
 * zhouhaisheng
 */
@ApiModel(value = "日终结账查询入参")
public class PatientDepartmentChargesDailyPrintReq {
    @ApiModelProperty(value = "")
    private String type;
    @ApiModelProperty(value = "查询-结账开始时间")
    private String adt_clrq_b;
    @ApiModelProperty(value = "查询-结账结束时间")
    private String adt_clrq_e;
    @ApiModelProperty(value = "类型(查询ty=1;产生ty=2)")
    private String ty;
    @ApiModelProperty(value = "产生-结账日期")
    private String jzrq;
    @ApiModelProperty(value = "0:显示空报表 1:显示正常报表")
    private String first="0";

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAdt_clrq_b() {
        return adt_clrq_b;
    }

    public void setAdt_clrq_b(String adt_clrq_b) {
        this.adt_clrq_b = adt_clrq_b;
    }

    public String getAdt_clrq_e() {
        return adt_clrq_e;
    }

    public void setAdt_clrq_e(String adt_clrq_e) {
        this.adt_clrq_e = adt_clrq_e;
    }

    public String getTy() {
        return ty;
    }

    public void setTy(String ty) {
        this.ty = ty;
    }

    public String getJzrq() {
        return jzrq;
    }

    public void setJzrq(String jzrq) {
        this.jzrq = jzrq;
    }

    public String getFirst() {
        return first;
    }

    public void setFirst(String first) {
        this.first = first;
    }
}
