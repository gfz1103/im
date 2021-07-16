package com.buit.cis.ims.response;

import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;

/**
 * @Description 费用清单打印- 病人信息返回结果
 * @Author jiangwei
 * @Date 2021-04-22
 */
public class ExpensesPrintPatientResp {
    @ApiModelProperty(value = "病人姓名")
    private String brxm;
    @ApiModelProperty(value = "病人性质")
    private String brxz;
    @ApiModelProperty(value = "住院天数")
    private Double zyts;
    @ApiModelProperty(value = "结算天数")
    private Double jsts;

    @ApiModelProperty(value = "开始日期")
    private String ksrq;
    @ApiModelProperty(value = "终止日期")
    private String zzrq;

    @ApiModelProperty(value = "住院号码")
    private String zyhm;
    @ApiModelProperty(value = "病人科室")
    private String brks;
    @ApiModelProperty(value = "病人床号")
    private String brch;

    @ApiModelProperty(value = "自负累计")
    private BigDecimal zflj = BigDecimal.ZERO;
    @ApiModelProperty(value = "自负合计")
    private BigDecimal zfhj = BigDecimal.ZERO;
    @ApiModelProperty(value = "费用累计")
    private BigDecimal fylj = BigDecimal.ZERO;
    @ApiModelProperty(value = "费用合计")
    private BigDecimal fyhj = BigDecimal.ZERO;

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

    public Double getZyts() {
        return zyts;
    }

    public void setZyts(Double zyts) {
        this.zyts = zyts;
    }

    public String getKsrq() {
        return ksrq;
    }

    public void setKsrq(String ksrq) {
        this.ksrq = ksrq;
    }

    public String getZzrq() {
        return zzrq;
    }

    public void setZzrq(String zzrq) {
        this.zzrq = zzrq;
    }

    public String getZyhm() {
        return zyhm;
    }

    public void setZyhm(String zyhm) {
        this.zyhm = zyhm;
    }

    public String getBrks() {
        return brks;
    }

    public void setBrks(String brks) {
        this.brks = brks;
    }

    public String getBrch() {
        return brch;
    }

    public void setBrch(String brch) {
        this.brch = brch;
    }

    public BigDecimal getZflj() {
        return zflj;
    }

    public void setZflj(BigDecimal zflj) {
        this.zflj = zflj;
    }

    public BigDecimal getZfhj() {
        return zfhj;
    }

    public void setZfhj(BigDecimal zfhj) {
        this.zfhj = zfhj;
    }

    public BigDecimal getFylj() {
        return fylj;
    }

    public void setFylj(BigDecimal fylj) {
        this.fylj = fylj;
    }

    public BigDecimal getFyhj() {
        return fyhj;
    }

    public void setFyhj(BigDecimal fyhj) {
        this.fyhj = fyhj;
    }

    public Double getJsts() {
        return jsts;
    }

    public void setJsts(Double jsts) {
        this.jsts = jsts;
    }
}
