package com.buit.cis.bedresev.request;

import com.buit.commons.PageQuery;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "床位信息预约信息查询参数")
public class BedInfoReq extends PageQuery {
    @ApiModelProperty(value = "机构编码", hidden = true)
    private Integer jgid;
    @ApiModelProperty(value = "床位号码")
    private String brch;
    @ApiModelProperty(value = "床位科室")
    private Integer cwks;
    @ApiModelProperty(value = "床位病区")
    private Integer ksdm;

    public Integer getJgid() {
        return jgid;
    }

    public void setJgid(Integer jgid) {
        this.jgid = jgid;
    }

    public String getBrch() {
        return brch;
    }

    public void setBrch(String brch) {
        this.brch = brch;
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

}
