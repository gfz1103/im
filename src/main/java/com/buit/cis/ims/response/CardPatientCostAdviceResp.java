package com.buit.cis.ims.response;

import com.buit.commons.PageQuery;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * 病人管理-帐卡-费用帐卡(费用清单-明细格式)出参
 * 出参
 */
@ApiModel(value="病人管理-帐卡-费用帐卡(费用清单-医嘱格式)出参")
public class CardPatientCostAdviceResp extends PageQuery {

    @ApiModelProperty(value="开始日期")
    private Timestamp startDate;

    @ApiModelProperty(value="费用名称")
    private String fymc;

    @ApiModelProperty(value="终止日期")
    private Timestamp endDate;

    @ApiModelProperty(value="数量*天数")
    private BigDecimal fysl;
    @ApiModelProperty(value="费用单价")
    private BigDecimal fydj;
    @ApiModelProperty(value="总计金额")
    private BigDecimal zjje;


    @ApiModelProperty(value="费用科室 | 费用输入的科室(记帐,按输入科室核算时要用)")
    private Integer fyks;
    @ApiModelProperty(value="自负比例 | 同FEE_SFDLZFBL或 FEE_YPXZ,PUB_FYXZ表中自负比例对应")
    private BigDecimal zfbl;

    public Timestamp getStartDate() {
        return startDate;
    }

    public void setStartDate(Timestamp startDate) {
        this.startDate = startDate;
    }

    public String getFymc() {
        return fymc;
    }

    public void setFymc(String fymc) {
        this.fymc = fymc;
    }

    public Timestamp getEndDate() {
        return endDate;
    }

    public void setEndDate(Timestamp endDate) {
        this.endDate = endDate;
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

    public Integer getFyks() {
        return fyks;
    }

    public void setFyks(Integer fyks) {
        this.fyks = fyks;
    }

    public BigDecimal getZfbl() {
        return zfbl;
    }

    public void setZfbl(BigDecimal zfbl) {
        this.zfbl = zfbl;
    }
}
