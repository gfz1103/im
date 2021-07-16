package com.buit.cis.ims.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;

/**
 * 出院报表费用合计
 * zhouhaisheng
 */
@ApiModel(value ="出院报表费用合计" )
public class DischargedPatientReportHjResp {
    @ApiModelProperty(value = "收费项目")
    private Integer sfxm;
    @ApiModelProperty(value = "费用合计")
    private BigDecimal fyhj;

    public Integer getSfxm() {
        return sfxm;
    }

    public void setSfxm(Integer sfxm) {
        this.sfxm = sfxm;
    }

    public BigDecimal getFyhj() {
        return fyhj;
    }

    public void setFyhj(BigDecimal fyhj) {
        this.fyhj = fyhj;
    }
}
