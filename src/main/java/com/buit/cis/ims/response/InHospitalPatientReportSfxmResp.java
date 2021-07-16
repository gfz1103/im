package com.buit.cis.ims.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;

/**
 * 收费项目集合
 * zhouhaisheng
 */
@ApiModel("住院收费项目集合")
public class InHospitalPatientReportSfxmResp {
    @ApiModelProperty(value = "收费项目")
    private Integer sfxm;
    @ApiModelProperty(value = "收费项目名称")
    private String sfmc;
    @ApiModelProperty(value = "费用合计")
    private BigDecimal fyhj;


    public Integer getSfxm() {
        return sfxm;
    }

    public void setSfxm(Integer sfxm) {
        this.sfxm = sfxm;
    }

    public String getSfmc() {
        return sfmc;
    }

    public void setSfmc(String sfmc) {
        this.sfmc = sfmc;
    }

    public BigDecimal getFyhj() {
        return fyhj;
    }

    public void setFyhj(BigDecimal fyhj) {
        this.fyhj = fyhj;
    }
}
