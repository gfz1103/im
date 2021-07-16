package com.buit.cis.bedresev.request;

import com.buit.commons.PageQuery;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 住院预约统计分析入参
 * zhouhaisheng
 */
@ApiModel(value = "住院预约统计分析入参")
public class QueryImBedresevCollectReq extends PageQuery {

    @ApiModelProperty(value = "机构id",hidden = true)
    private Integer jgid;

    @ApiModelProperty(value = "开始时间")
    private String startDate;

    @ApiModelProperty(value = "结束时间")
    private String endDate;

    @ApiModelProperty(value = "预约科室")
    private Integer yyks;

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

    public Integer getYyks() {
        return yyks;
    }

    public void setYyks(Integer yyks) {
        this.yyks = yyks;
    }

    public Integer getJgid() {
        return jgid;
    }

    public void setJgid(Integer jgid) {
        this.jgid = jgid;
    }
}
