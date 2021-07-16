package com.buit.cis.ims.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;

/**
 * @Author jiangwei
 * @Date 2021-04-27
 */
@ApiModel(value = "住院结算付款信息入参")
public class ImFkxxAddReq {
    @ApiModelProperty(value = "付款方式")
    private Integer fkfs;
    @ApiModelProperty(value = "付款金额")
    private BigDecimal fkje;

    public Integer getFkfs() {
        return fkfs;
    }

    public void setFkfs(Integer fkfs) {
        this.fkfs = fkfs;
    }

    public BigDecimal getFkje() {
        return fkje;
    }

    public void setFkje(BigDecimal fkje) {
        this.fkje = fkje;
    }

}
