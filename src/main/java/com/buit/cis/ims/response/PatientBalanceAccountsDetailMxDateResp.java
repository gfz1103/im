package com.buit.cis.ims.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;

/**
 * 结算管理-明细项目(按明细)明细出参
 *  zhouhaisheng
 */
@ApiModel(value = "结算管理-明细项目(按明细)明细出参")
public class PatientBalanceAccountsDetailMxDateResp {

    @ApiModelProperty(value="住院号")
    private Integer zyh;
    @ApiModelProperty(value="医疗机构id")
    private Integer jgid;
    @ApiModelProperty(value="fyxm")
    private Integer fyxm;
    @ApiModelProperty(value="工号")
    private String qrgh;
    @ApiModelProperty(value="费用名称")
    private String fymc;
    @ApiModelProperty(value="费用日期")
    private String fyrq;

    @ApiModelProperty(value="费用序号")
    private Integer fyxh;
    @ApiModelProperty(value="数量")
    private BigDecimal fysl;
    @ApiModelProperty(value="费用单价")
    private BigDecimal fydj;
    @ApiModelProperty(value="总计金额")
    private BigDecimal zjje;
    @ApiModelProperty(value="自负金额")
    private BigDecimal zfje;
    @ApiModelProperty(value="自负比例 | 同FEE_SFDLZFBL或 FEE_YPXZ,PUB_FYXZ表中自负比例对应")
    private BigDecimal zfbl;
    @ApiModelProperty(value="费用科室 | 费用输入的科室(记帐,按输入科室核算时要用)")
    private Integer fyks;
    @ApiModelProperty("折扣金额")
    private BigDecimal zkje;
    @ApiModelProperty("执行科室")
    private Integer zxks;
    @ApiModelProperty("折扣比例")
    private BigDecimal zkbl;

    @ApiModelProperty("折后金额")
    private BigDecimal zhje;




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

    public Integer getZyh() {
        return zyh;
    }

    public void setZyh(Integer zyh) {
        this.zyh = zyh;
    }

    public Integer getJgid() {
        return jgid;
    }

    public void setJgid(Integer jgid) {
        this.jgid = jgid;
    }

    public Integer getFyxm() {
        return fyxm;
    }

    public void setFyxm(Integer fyxm) {
        this.fyxm = fyxm;
    }

    public String getQrgh() {
        return qrgh;
    }

    public void setQrgh(String qrgh) {
        this.qrgh = qrgh;
    }


    public Integer getFyks() {
        return fyks;
    }

    public void setFyks(Integer fyks) {
        this.fyks = fyks;
    }

    public Integer getZxks() {
        return zxks;
    }

    public void setZxks(Integer zxks) {
        this.zxks = zxks;
    }

    public BigDecimal getZkbl() {
        return zkbl;
    }

    public void setZkbl(BigDecimal zkbl) {
        this.zkbl = zkbl;
    }

    public BigDecimal getZhje() {
        return zhje;
    }

    public void setZhje(BigDecimal zhje) {
        this.zhje = zhje;
    }

    public Integer getFyxh() {
        return fyxh;
    }

    public void setFyxh(Integer fyxh) {
        this.fyxh = fyxh;
    }

    public String getFyrq() {
        return fyrq;
    }

    public void setFyrq(String fyrq) {
        this.fyrq = fyrq;
    }

    public BigDecimal getZkje() {
        return zkje;
    }

    public void setZkje(BigDecimal zkje) {
        this.zkje = zkje;
    }
}
