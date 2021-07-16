package com.buit.cis.ims.request;

import com.buit.commons.PageQuery;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 床位管理-转床（分页列表信息）入参
 * zhouhaisheng
 */
@ApiModel(value = "床位管理-转床（分页列表信息）入参")
public class ZcPatientPageInfoReq extends PageQuery {
    @JsonIgnore
    @ApiModelProperty(value = "机构id",hidden = true)
    private Integer jgid;

    @ApiModelProperty(value = "病人床号")
    private String brch;
    @ApiModelProperty(value = "病人性质")
    private Integer brxz;
    @ApiModelProperty(value = "病人病区")
    private Integer brbq;
    @ApiModelProperty(value = "床位性别")
    private Integer cwxb;
    @ApiModelProperty(value = "查询床位号码")
    private String brchSearch;


    public String getBrch() {
        return brch;
    }

    public void setBrch(String brch) {
        this.brch = brch;
    }

    public Integer getBrxz() {
        return brxz;
    }

    public void setBrxz(Integer brxz) {
        this.brxz = brxz;
    }

    public Integer getBrbq() {
        return brbq;
    }

    public void setBrbq(Integer brbq) {
        this.brbq = brbq;
    }

    public Integer getJgid() {
        return jgid;
    }

    public void setJgid(Integer jgid) {
        this.jgid = jgid;
    }

    public Integer getCwxb() {
        return cwxb;
    }

    public void setCwxb(Integer cwxb) {
        this.cwxb = cwxb;
    }

    public String getBrchSearch() {
        return brchSearch;
    }

    public void setBrchSearch(String brchSearch) {
        this.brchSearch = brchSearch;
    }
}
