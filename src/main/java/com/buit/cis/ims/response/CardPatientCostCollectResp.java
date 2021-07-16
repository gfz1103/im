package com.buit.cis.ims.response;

import com.buit.commons.PageQuery;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * 病人管理-帐卡-费用帐卡(费用清单-汇总格式)出参
 * 出参
 */
@ApiModel(value="病人管理-帐卡-费用帐卡(费用清单-汇总格式)出参")
public class CardPatientCostCollectResp extends PageQuery {

    @ApiModelProperty(value="费用日期")
    private Timestamp fyrq;

    @ApiModelProperty(value="费用名称")
    private String fymc;

    @ApiModelProperty(value="费用数量")
    private BigDecimal fysl;
    @ApiModelProperty(value="费用单价")
    private BigDecimal fydj;
    @ApiModelProperty(value="总计金额")
    private BigDecimal zjje;
    @ApiModelProperty(value="自负金额")
    private BigDecimal zfje;

    @ApiModelProperty(value="费用科室 | 费用输入的科室(记帐,按输入科室核算时要用)")
    private Integer fyks;
    @ApiModelProperty(value="自负比例 | 同FEE_SFDLZFBL或 FEE_YPXZ,PUB_FYXZ表中自负比例对应")
    private BigDecimal zfbl;

    public Timestamp getFyrq() {
        return fyrq;
    }

    public void setFyrq(Timestamp fyrq) {
        this.fyrq = fyrq;
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

    public BigDecimal getZfje() {
        return zfje;
    }

    public void setZfje(BigDecimal zfje) {
        this.zfje = zfje;
    }
}
