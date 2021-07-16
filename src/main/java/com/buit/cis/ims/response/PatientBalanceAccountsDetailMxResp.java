package com.buit.cis.ims.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;
import java.util.List;

/**
 * 结算管理-明细项目(按明细)
 * zhouhaisheng
 */
@ApiModel(value = "结算管理-明细项目(按明细)出参")
public class PatientBalanceAccountsDetailMxResp {

    @ApiModelProperty(value="住院号")
    private Integer zyh;
    @ApiModelProperty(value="医疗机构id")
    private Integer jgid;
    @ApiModelProperty(value="fyxm")
    private Integer fyxm;

    @ApiModelProperty(value="费用序号")
    private Integer fyxh;

    @ApiModelProperty(value="数量")
    private BigDecimal fysl;
    @ApiModelProperty(value="费用日期")
    private String fyrq;

    @ApiModelProperty(value="费用名称")
    private String fymc;

    @ApiModelProperty(value="总计金额")
    private BigDecimal zjje;
    @ApiModelProperty(value="自负金额")
    private BigDecimal zfje;
    @ApiModelProperty(value="折扣比例")
    private BigDecimal zkbl;
    @ApiModelProperty(value="折后金额")
    private BigDecimal zhje;
    @ApiModelProperty(value="折扣金额")
    private BigDecimal zkje;

    @ApiModelProperty("每个收费项目的项目明细")
    private List<PatientBalanceAccountsDetailMxDateResp> detailMxDeteResps;


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

    public String getFyrq() {
        return fyrq;
    }

    public void setFyrq(String fyrq) {
        this.fyrq = fyrq;
    }

    public String getFymc() {
        return fymc;
    }

    public void setFymc(String fymc) {
        this.fymc = fymc;
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

    public BigDecimal getZkje() {
        return zkje;
    }

    public void setZkje(BigDecimal zkje) {
        this.zkje = zkje;
    }

    public List<PatientBalanceAccountsDetailMxDateResp> getDetailMxDeteResps() {
        return detailMxDeteResps;
    }

    public void setDetailMxDeteResps(List<PatientBalanceAccountsDetailMxDateResp> detailMxDeteResps) {
        this.detailMxDeteResps = detailMxDeteResps;
    }

    public BigDecimal getFysl() {
        return fysl;
    }

    public void setFysl(BigDecimal fysl) {
        this.fysl = fysl;
    }

    public Integer getFyxh() {
        return fyxh;
    }

    public void setFyxh(Integer fyxh) {
        this.fyxh = fyxh;
    }

}
