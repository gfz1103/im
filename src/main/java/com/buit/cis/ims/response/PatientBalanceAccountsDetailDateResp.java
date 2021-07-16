package com.buit.cis.ims.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;
import java.util.List;

/**
 * 结算管理-明细项目(按日期)
 * zhouhaisheng
 */
@ApiModel(value = "结算管理-明细项目(按日期)出参")
public class PatientBalanceAccountsDetailDateResp {

    @ApiModelProperty(value="住院号")
    private Integer zyh;
    @ApiModelProperty(value="医疗机构id")
    private Integer jgid;
    @ApiModelProperty(value="fyxm")
    private Integer fyxm;



    @ApiModelProperty(value="费用日期")
    private String fyrq;

    @ApiModelProperty(value="费用名称")
    private String fymc;

    @ApiModelProperty(value="总计金额")
    private BigDecimal zjje;
    @ApiModelProperty(value="自负金额")
    private BigDecimal zfje;

    @ApiModelProperty(value="费用科室 | 费用输入的科室(记帐,按输入科室核算时要用)")
    private Integer fyks;

    @ApiModelProperty("执行科室")
    private Integer zxks;

    @ApiModelProperty("工号")
    private Integer qrgh;

    @ApiModelProperty("每天的项目明细")
    private List<PatientBalanceAccountsDetailDateMxResp> detailDateMxResps;

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

    public Integer getQrgh() {
        return qrgh;
    }

    public void setQrgh(Integer qrgh) {
        this.qrgh = qrgh;
    }

    public List<PatientBalanceAccountsDetailDateMxResp> getDetailDateMxResps() {
        return detailDateMxResps;
    }

    public void setDetailDateMxResps(List<PatientBalanceAccountsDetailDateMxResp> detailDateMxResps) {
        this.detailDateMxResps = detailDateMxResps;
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
}
