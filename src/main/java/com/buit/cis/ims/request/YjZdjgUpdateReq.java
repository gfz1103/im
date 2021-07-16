package com.buit.cis.ims.request;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


/**
 * 医技信息维护 -诊断结果维护修改入参
 * 类名称：YjZdjg<br>
 * 类描述：<br>
 * @author ZHOUHAISHENG
 */
@ApiModel(value="医技信息维护 -诊断结果维护修改入参")
public class YjZdjgUpdateReq {

    @ApiModelProperty(value="诊断id")
    private Integer zdid;
    @ApiModelProperty(value="诊断代码")
    private String zddm;
    @ApiModelProperty(value="诊断名称")
    private String zdmc;

    public String getZddm() {
        return zddm;
    }

    public void setZddm(String zddm) {
        this.zddm = zddm;
    }

    public String getZdmc() {
        return zdmc;
    }

    public void setZdmc(String zdmc) {
        this.zdmc = zdmc;
    }

    public Integer getZdid() {
        return zdid;
    }

    public void setZdid(Integer zdid) {
        this.zdid = zdid;
    }
}