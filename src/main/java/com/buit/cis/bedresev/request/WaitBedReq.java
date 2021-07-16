package com.buit.cis.bedresev.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.sql.Timestamp;

/**
 * 待床保存入参
 * zhouhaisheng
 */
@ApiModel(value = "待床保存入参")
public class WaitBedReq {

    @ApiModelProperty(value = "主键id")
    private Integer jlxh;

    @ApiModelProperty(value = "病人病区")
    private Integer brbq;
    @ApiModelProperty(value = "病人床号")
    private String brch;
    @ApiModelProperty(value = "通知入院时间")
    private String rytzsj;
    @ApiModelProperty(value = "病人通知内容")
    private String notice;

    public Integer getJlxh() {
        return jlxh;
    }

    public void setJlxh(Integer jlxh) {
        this.jlxh = jlxh;
    }

    public Integer getBrbq() {
        return brbq;
    }

    public void setBrbq(Integer brbq) {
        this.brbq = brbq;
    }

    public String getBrch() {
        return brch;
    }

    public void setBrch(String brch) {
        this.brch = brch;
    }

    public String getRytzsj() {
        return rytzsj;
    }

    public void setRytzsj(String rytzsj) {
        this.rytzsj = rytzsj;
    }

    public String getNotice() {
        return notice;
    }

    public void setNotice(String notice) {
        this.notice = notice;
    }
}
