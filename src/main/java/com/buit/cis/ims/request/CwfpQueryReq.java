package com.buit.cis.ims.request;

import com.buit.commons.PageQuery;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 床位管理-床位分配 查询未分配床位病人分页信息入参
 */
@ApiModel(value = "床位管理-床位分配 查询未分配床位病人分页信息入参")
public class CwfpQueryReq extends PageQuery {

    @ApiModelProperty(value = "机构id", hidden = true)
    private Integer jgid;
    @ApiModelProperty(value = "床位性别")
    private Integer cwxb;
    @ApiModelProperty(value = "床位病区")
    private Integer ksdm;


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

    public Integer getKsdm() {
        return ksdm;
    }

    public void setKsdm(Integer ksdm) {
        this.ksdm = ksdm;
    }
}
