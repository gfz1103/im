package com.buit.cis.ims.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;

/**
 * 病人管理-帐卡-费用帐卡(帐卡费用列表)出参
 *@author ZHOUHAISHENG
 *
 */
@ApiModel(value="病人管理-帐卡-费用帐卡(帐卡费用列表)出参")
public class CardPatientInfoCostResp {
    @ApiModelProperty(value = "住院号")
    private Integer zyh;
    @ApiModelProperty(value = "结算次数")
    private String jscs;
    @ApiModelProperty(value = "住院归并")
    private String zygb;
    @ApiModelProperty(value = "收费名称")
    private String sfmc;
    @ApiModelProperty(value = "总计金额")
    private BigDecimal zjje;
    @ApiModelProperty(value = "自负金额")
    private BigDecimal zfje;
    @ApiModelProperty(value = "自理金额")
    private BigDecimal zlje;

    public Integer getZyh() {
        return zyh;
    }

    public void setZyh(Integer zyh) {
        this.zyh = zyh;
    }

    public String getJscs() {
        return jscs;
    }

    public void setJscs(String jscs) {
        this.jscs = jscs;
    }

    public String getZygb() {
        return zygb;
    }

    public void setZygb(String zygb) {
        this.zygb = zygb;
    }

    public String getSfmc() {
        return sfmc;
    }

    public void setSfmc(String sfmc) {
        this.sfmc = sfmc;
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

    public BigDecimal getZlje() {
        return zlje;
    }

    public void setZlje(BigDecimal zlje) {
        this.zlje = zlje;
    }
}
