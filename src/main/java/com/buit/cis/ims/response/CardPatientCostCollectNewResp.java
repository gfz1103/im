package com.buit.cis.ims.response;

import com.buit.commons.PageQuery;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;

/**
 * 病人管理-帐卡-费用帐卡(费用清单-汇总新格式)出参
 * 出参
 */
@ApiModel(value="病人管理-帐卡-费用帐卡(费用清单-汇总新格式)出参")
public class CardPatientCostCollectNewResp extends PageQuery {

    @ApiModelProperty(value="费用")
    private String sfmc;
    @ApiModelProperty(value="费用名称")
    private String fymc;

    @ApiModelProperty(value="数量*天数")
    private BigDecimal fysl;
    @ApiModelProperty(value="费用单价")
    private BigDecimal fydj;
    @ApiModelProperty(value="总计金额")
    private BigDecimal zjje;
    @ApiModelProperty(value="自负金额")
    private BigDecimal zfje;
    @ApiModelProperty(value="自负比例 | 同FEE_SFDLZFBL或 FEE_YPXZ,PUB_FYXZ表中自负比例对应")
    private BigDecimal zfbl;

    public String getSfmc() {
        return sfmc;
    }

    public void setSfmc(String sfmc) {
        this.sfmc = sfmc;
    }

    public String getFymc() {
        return fymc;
    }

    public void setFymc(String fymc) {
        this.fymc = fymc;
    }

    public BigDecimal getFysl() {
        return fysl;
    }

    public void setFysl(BigDecimal fysl) {
        this.fysl = fysl;
    }

    public BigDecimal getFydj() {
        return fydj;
    }

    public void setFydj(BigDecimal fydj) {
        this.fydj = fydj;
    }

    public BigDecimal getZjje() {
        return zjje;
    }

    public void setZjje(BigDecimal zjje) {
        this.zjje = zjje;
    }

    public BigDecimal getZfje() {
        return zfje;
    }

    public void setZfje(BigDecimal zfje) {
        this.zfje = zfje;
    }

    public BigDecimal getZfbl() {
        return zfbl;
    }

    public void setZfbl(BigDecimal zfbl) {
        this.zfbl = zfbl;
    }
}
