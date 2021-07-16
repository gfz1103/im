package com.buit.cis.ims.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;

/**
 * @Author jiangwei
 * @Date 2021-04-22
 */
@ApiModel(value = "费用清单打印入参")
public class ExpensesPrintReq {
    @NotNull
    @ApiModelProperty(value = "模板格式 | 1：明细格式，2：项目汇总，3：医嘱格式，4：按日汇总")
    private Integer template;
    @NotNull
    @ApiModelProperty(value = "住院号")
    private Integer zyh;
    @NotNull
    @ApiModelProperty(value = "查询类型 0：全部 1:医疗 2：药品")
    private Integer queryType;
    @NotNull
    @ApiModelProperty(value = "开始日期")
    private String startDate;
    @NotNull
    @ApiModelProperty(value = "结束日期")
    private String endDate;

    public Integer getTemplate() {
        return template;
    }

    public void setTemplate(Integer template) {
        this.template = template;
    }

    public Integer getZyh() {
        return zyh;
    }

    public void setZyh(Integer zyh) {
        this.zyh = zyh;
    }

    public Integer getQueryType() {
        return queryType;
    }

    public void setQueryType(Integer queryType) {
        this.queryType = queryType;
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
}
