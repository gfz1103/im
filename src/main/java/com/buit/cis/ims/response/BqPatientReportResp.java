package com.buit.cis.ims.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;

/**
 * 病区费用统计报表出参
 * zhouhaisheng
 */
@ApiModel(value = "病区费用统计报表出参")
public class BqPatientReportResp extends DischargedPatientReportResp{
    @ApiModelProperty(value = "药占比")
    private BigDecimal yzb;

    @ApiModelProperty(value = "药品费用合计")
    private  BigDecimal yphj;

    public BigDecimal getYzb() {
        return yzb;
    }

    public void setYzb(BigDecimal yzb) {
        this.yzb = yzb;
    }

    public BigDecimal getYphj() {
        return yphj;
    }

    public void setYphj(BigDecimal yphj) {
        this.yphj = yphj;
    }
}
