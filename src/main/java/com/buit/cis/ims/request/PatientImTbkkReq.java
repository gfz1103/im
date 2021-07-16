package com.buit.cis.ims.request;

import com.buit.commons.PageQuery;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 住院病人预交金统计(分页查询入参)
 * zhouhaisheng
 */
@ApiModel(value = "住院病人预交金统计(分页查询入参)")
public class PatientImTbkkReq extends PageQuery {
    @ApiModelProperty(value = "医疗机构Id",hidden = true)
    private Integer jgid;
    @ApiModelProperty(value = "用户Id",hidden = true)
    private Integer czgh;
    @ApiModelProperty(value = "住院号码")
    private String zyhm;
    @ApiModelProperty(value = "病人姓名")
    private String brxm;
    @ApiModelProperty(value = "病人床号")
    private String brch;
    @ApiModelProperty(value = "病人科室")
    private Integer brks;
    @ApiModelProperty(value = "病人病区")
    private Integer brbq;
    @ApiModelProperty(value = "付款方式")
    private Integer jkfs;
    @ApiModelProperty(value = "开始时间")
    private String startDate;
    @ApiModelProperty(value = "结束时间")
    private String endDate;
    @ApiModelProperty(value = "1：住院 2：留观")
    private String ywlx;
    @ApiModelProperty(value = "缴款类型 | 0：正常预缴   1：结算抵扣   2：取消结算   3：注销找退   4：出院找退")
    private Integer jklx;

    public String getBrxm() {
        return brxm;
    }

    public void setBrxm(String brxm) {
        this.brxm = brxm;
    }

    public String getBrch() {
        return brch;
    }

    public void setBrch(String brch) {
        this.brch = brch;
    }

    public Integer getBrks() {
        return brks;
    }

    public void setBrks(Integer brks) {
        this.brks = brks;
    }

    public Integer getBrbq() {
        return brbq;
    }

    public void setBrbq(Integer brbq) {
        this.brbq = brbq;
    }

    public Integer getJkfs() {
        return jkfs;
    }

    public void setJkfs(Integer jkfs) {
        this.jkfs = jkfs;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public Integer getJgid() {
        return jgid;
    }

    public void setJgid(Integer jgid) {
        this.jgid = jgid;
    }

    public Integer getCzgh() {
        return czgh;
    }

    public void setCzgh(Integer czgh) {
        this.czgh = czgh;
    }

    public String getZyhm() {
        return zyhm;
    }

    public void setZyhm(String zyhm) {
        this.zyhm = zyhm;
    }

    public String getYwlx() {
        return ywlx;
    }

    public void setYwlx(String ywlx) {
        this.ywlx = ywlx;
    }

    public Integer getJklx() {
        return jklx;
    }

    public void setJklx(Integer jklx) {
        this.jklx = jklx;
    }
}
