package com.buit.cis.dctwork.response;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @Author jiangwei
 * @Date 2021-07-19
 */
@ApiModel(value = "检查指引单明细返回结果封装")
public class CisJcsq01PrintResp {
    @ApiModelProperty(value = "检查序号")
    private Integer jcxh;
    @ApiModelProperty(value = "执行科室")
    private String zxks;
    @ApiModelProperty(value = "申请时间")
    private String sqsj;
    @ApiModelProperty(value = "检查项目")
    private String jcxm;
    @ApiModelProperty(value = "是否加急")
    private String sfjj;
    @ApiModelProperty(value = "申请医生")
    private String sqys;
    @ApiModelProperty(value = "检查项目")
    private String sqks;

    public Integer getJcxh() {
        return jcxh;
    }

    public void setJcxh(Integer jcxh) {
        this.jcxh = jcxh;
    }

    public String getZxks() {
        return zxks;
    }

    public void setZxks(String zxks) {
        this.zxks = zxks;
    }

    public String getSqsj() {
        return sqsj;
    }

    public void setSqsj(String sqsj) {
        this.sqsj = sqsj;
    }

    public String getJcxm() {
        return jcxm;
    }

    public void setJcxm(String jcxm) {
        this.jcxm = jcxm;
    }

    public String getSfjj() {
        return sfjj;
    }

    public void setSfjj(String sfjj) {
        this.sfjj = sfjj;
    }

    public String getSqys() {
        return sqys;
    }

    public void setSqys(String sqys) {
        this.sqys = sqys;
    }

    public String getSqks() {
        return sqks;
    }

    public void setSqks(String sqks) {
        this.sqks = sqks;
    }
}
