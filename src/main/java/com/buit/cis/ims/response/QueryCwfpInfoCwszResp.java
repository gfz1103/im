package com.buit.cis.ims.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;

/**
 *  床位管理-床位分配(床位信息)出参
 */
@ApiModel(value = "床位管理-床位分配(床位信息)出参")
public class QueryCwfpInfoCwszResp {
    @ApiModelProperty(value="床位号码")
    private String brch;
    @ApiModelProperty(value="房间号码")
    private String fjhm;
    @ApiModelProperty(value="床位科室")
    private Integer cwks;
    @ApiModelProperty(value="床位病区")
    private Integer ksdm;
    @ApiModelProperty(value="床位性别")
    private Integer cwxb;
    @ApiModelProperty(value="床位费用")
    private BigDecimal cwfy;
    @ApiModelProperty(value="加床判别 | 0.普通床 1.加床 2.虚床")
    private Integer jcpb;

    public String getBrch() {
        return brch;
    }

    public void setBrch(String brch) {
        this.brch = brch;
    }

    public String getFjhm() {
        return fjhm;
    }

    public void setFjhm(String fjhm) {
        this.fjhm = fjhm;
    }

    public Integer getCwks() {
        return cwks;
    }

    public void setCwks(Integer cwks) {
        this.cwks = cwks;
    }

    public Integer getKsdm() {
        return ksdm;
    }

    public void setKsdm(Integer ksdm) {
        this.ksdm = ksdm;
    }


    public Integer getCwxb() {
        return cwxb;
    }

    public void setCwxb(Integer cwxb) {
        this.cwxb = cwxb;
    }

    public BigDecimal getCwfy() {
        return cwfy;
    }

    public void setCwfy(BigDecimal cwfy) {
        this.cwfy = cwfy;
    }

    public Integer getJcpb() {
        return jcpb;
    }

    public void setJcpb(Integer jcpb) {
        this.jcpb = jcpb;
    }
}
