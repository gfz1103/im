package com.buit.cis.ims.request;

import io.swagger.annotations.ApiModelProperty;

public class HospitalChargesCollectReq {
    @ApiModelProperty(value = "查询-结账开始时间")
    private String hzksrq;
    @ApiModelProperty(value = "查询-结账结束时间")
    private String hzjsrq;
    @ApiModelProperty(value = "类型(查询ty=1;产生ty=2)")
    private Integer ty;
    @ApiModelProperty(value = "产生-结账日期")
    private String hzrq;


    public String getHzksrq() {
        return hzksrq;
    }

    public void setHzksrq(String hzksrq) {
        this.hzksrq = hzksrq;
    }

    public String getHzjsrq() {
        return hzjsrq;
    }

    public void setHzjsrq(String hzjsrq) {
        this.hzjsrq = hzjsrq;
    }

    public Integer getTy() {
        return ty;
    }

    public void setTy(Integer ty) {
        this.ty = ty;
    }

    public String getHzrq() {
        return hzrq;
    }

    public void setHzrq(String jzrq) {
        this.hzrq = jzrq;
    }


}
