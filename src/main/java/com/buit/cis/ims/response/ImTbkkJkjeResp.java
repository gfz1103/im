package com.buit.cis.ims.response;


import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;

public class ImTbkkJkjeResp {
    @ApiModelProperty("总的缴款金额")
    private BigDecimal jkje;
    @ApiModelProperty("住院号")
    private Integer zyh;

    public BigDecimal getJkje() {
        return jkje;
    }

    public void setJkje(BigDecimal jkje) {
        this.jkje = jkje;
    }

    public Integer getZyh() {
        return zyh;
    }

    public void setZyh(Integer zyh) {
        this.zyh = zyh;
    }
}
