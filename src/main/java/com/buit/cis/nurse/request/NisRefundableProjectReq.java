package com.buit.cis.nurse.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.sql.Date;

@ApiModel(value="查询可退项目请求")
public class NisRefundableProjectReq implements Serializable {

    @NotNull(message = "住院号不能为空")
    @ApiModelProperty(value="住院号", required = true)
    private Integer zyh;

    @ApiModelProperty(value="开始时间(yyyy-mm-dd)")
    private Date startTime;

    @ApiModelProperty(value="结束时间(yyyy-mm-dd)")
    private Date endTime;

    @ApiModelProperty(value="项目费用序号")
    private Integer fyxh;

    public Integer getZyh() {
        return zyh;
    }

    public void setZyh(Integer zyh) {
        this.zyh = zyh;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Integer getFyxh() {
        return fyxh;
    }

    public void setFyxh(Integer fyxh) {
        this.fyxh = fyxh;
    }
}
