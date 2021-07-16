package com.buit.cis.ims.response;

import java.math.BigDecimal;

/**
 * 费用总金额
 */
public class CardPatientCostBaseInfoJe {
    //费用合计
    private BigDecimal fyhj;
    //自负合计
    private BigDecimal zfhj;

    //折扣金额
    private BigDecimal zkje;

    public BigDecimal getFyhj() {
        return fyhj;
    }

    public void setFyhj(BigDecimal fyhj) {
        this.fyhj = fyhj;
    }

    public BigDecimal getZfhj() {
        return zfhj;
    }

    public void setZfhj(BigDecimal zfhj) {
        this.zfhj = zfhj;
    }

    public BigDecimal getZkje() {
        return zkje;
    }

    public void setZkje(BigDecimal zkje) {
        this.zkje = zkje;
    }
}
