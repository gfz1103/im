package com.buit.cis.ims.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("住院收费项目")
public class InHospitalPatientSfxmResp {
    @ApiModelProperty(value = "收费项目")
    private Integer sfxm;
    @ApiModelProperty(value = "收费项目名称")
    private String sfmc;


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

}
