package com.buit.cis.ims.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * 结算管理-病人结算基本信息
 */
@ApiModel(value="结算管理-病人结算基本信息")
public class PatientBalanceAccountsBaseInfoResp {
    @ApiModelProperty("住院号")
    private Integer zyh;

    @ApiModelProperty("住院号码")
    private String zyhm;

    @ApiModelProperty("发票号码")
    private String  fphm;

    @ApiModelProperty("病人床号")
    private Integer brch;

    @ApiModelProperty("病人姓名")
    private String brxm;

    @ApiModelProperty("病人性质")
    private String brxz;

    @ApiModelProperty("病人科室")
    private Integer brks;

    @ApiModelProperty("入院日期")
    private Timestamp ryrq;

    @ApiModelProperty("总费用")
    private BigDecimal zyf = BigDecimal.ZERO;

    @ApiModelProperty("自负金额")
    private BigDecimal zfje = BigDecimal.ZERO;

    @ApiModelProperty("缴款金额")
    private BigDecimal yjhj = BigDecimal.ZERO;

    @ApiModelProperty("余款金额")
    private BigDecimal ykje = BigDecimal.ZERO;
    @ApiModelProperty(value = "折扣金额",hidden = true)
    private BigDecimal zkje = BigDecimal.ZERO;

    @ApiModelProperty("住院天数")
    private BigDecimal zyts;

    @ApiModelProperty("结算日期")
    private Timestamp jsrq;

    public Integer getZyh() {
        return zyh;
    }

    public void setZyh(Integer zyh) {
        this.zyh = zyh;
    }

    public String getFphm() {
        return fphm;
    }

    public void setFphm(String fphm) {
        this.fphm = fphm;
    }

    public Integer getBrch() {
        return brch;
    }

    public void setBrch(Integer brch) {
        this.brch = brch;
    }

    public String getBrxm() {
        return brxm;
    }

    public void setBrxm(String brxm) {
        this.brxm = brxm;
    }

    public String getBrxz() {
        return brxz;
    }

    public void setBrxz(String brxz) {
        this.brxz = brxz;
    }

    public Integer getBrks() {
        return brks;
    }

    public void setBrks(Integer brks) {
        this.brks = brks;
    }

    public Timestamp getRyrq() {
        return ryrq;
    }

    public void setRyrq(Timestamp ryrq) {
        this.ryrq = ryrq;
    }

    public BigDecimal getZyf() {
        return zyf;
    }

    public void setZyf(BigDecimal zyf) {
        this.zyf = zyf;
    }

    public BigDecimal getZfje() {
        return zfje;
    }

    public void setZfje(BigDecimal zfje) {
        this.zfje = zfje;
    }

    public BigDecimal getYjhj() {
        return yjhj;
    }

    public void setYjhj(BigDecimal yjhj) {
        this.yjhj = yjhj;
    }

    public BigDecimal getYkje() {
        return ykje;
    }

    public void setYkje(BigDecimal ykje) {
        this.ykje = ykje;
    }

    public BigDecimal getZyts() {
        return zyts;
    }

    public void setZyts(BigDecimal zyts) {
        this.zyts = zyts;
    }

    public Timestamp getJsrq() {
        return jsrq;
    }

    public void setJsrq(Timestamp jsrq) {
        this.jsrq = jsrq;
    }

    public String getZyhm() {
        return zyhm;
    }

    public void setZyhm(String zyhm) {
        this.zyhm = zyhm;
    }

    public BigDecimal getZkje() {
        return zkje;
    }

    public void setZkje(BigDecimal zkje) {
        this.zkje = zkje;
    }
}
