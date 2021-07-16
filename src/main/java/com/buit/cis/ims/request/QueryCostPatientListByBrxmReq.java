package com.buit.cis.ims.request;


import com.buit.commons.PageQuery;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 *缴费管理-缴费处理-病人姓名模糊查询住院病人列表入参
 * zhouhaisheng
 */
@ApiModel(value = "缴费管理-缴费处理-病人姓名模糊查询住院病人列表入参")
public class QueryCostPatientListByBrxmReq extends PageQuery {
    @ApiModelProperty(value = "机构id",hidden = true)
    private Integer  jgid;

    @ApiModelProperty("病人姓名")
    private String  brxm;

    public String getBrxm() {
        return brxm;
    }

    public void setBrxm(String brxm) {
        this.brxm = brxm;
    }

    public Integer getJgid() {
        return jgid;
    }

    public void setJgid(Integer jgid) {
        this.jgid = jgid;
    }
}
