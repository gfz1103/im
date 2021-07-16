package com.buit.cis.ims.dto;

import io.swagger.annotations.ApiModelProperty;

import java.sql.Timestamp;

public class InvoiceReportDto {
    @ApiModelProperty(value = "住院号码")
    private String zyhm;
    @ApiModelProperty(value = "患者姓名")
    private String brxm;
    @ApiModelProperty(value = "患者性别")
    private Integer brxb;
    @ApiModelProperty(value = "收费性质")
    private Integer brxz;
    @ApiModelProperty(value = "发票号码")
    private String fphm;
    @ApiModelProperty(value = "病人科室")
    private Integer brks;
    @ApiModelProperty(value = "病人病区")
    private Integer brbq;
    @ApiModelProperty(value = "入院日期")
    private Timestamp ryrq;
    @ApiModelProperty(value = "出院日期")
    private Timestamp cyrq;
    @ApiModelProperty(value = "结算开始日期")
    private Timestamp ksrq;
    @ApiModelProperty(value = "结算结束日期")
    private Timestamp zzrq;
    @ApiModelProperty(value = "结算日期")
    private Timestamp jsrq;
    @ApiModelProperty(value = "结算操作员工号")
    private String czgh;
    @ApiModelProperty(value = "作废日期")
    private Timestamp zfrq;
    @ApiModelProperty(value = "作废操作员工号")
    private String zfgh;
    @ApiModelProperty(value = "结算类型")
    private Integer jslx;
    @ApiModelProperty("行政区划代码")
    private String xzqhdm;
    @ApiModelProperty(value = "作废判别 | 0：正常 1：已作废")
    private Integer zfpb;

    public String getZyhm() {
        return zyhm;
    }

    public void setZyhm(String zyhm) {
        this.zyhm = zyhm;
    }

    public String getBrxm() {
        return brxm;
    }

    public void setBrxm(String brxm) {
        this.brxm = brxm;
    }

    public Integer getBrxb() {
        return brxb;
    }

    public void setBrxb(Integer brxb) {
        this.brxb = brxb;
    }

    public Integer getBrxz() {
        return brxz;
    }

    public void setBrxz(Integer brxz) {
        this.brxz = brxz;
    }

    public String getFphm() {
        return fphm;
    }

    public void setFphm(String fphm) {
        this.fphm = fphm;
    }

    public Integer getBrks() {
        return brks;
    }

    public void setBrks(Integer brks) {
        this.brks = brks;
    }

    public Integer getBrbq() {
        return brbq;
    }

    public void setBrbq(Integer brbq) {
        this.brbq = brbq;
    }

    public Timestamp getRyrq() {
        return ryrq;
    }

    public void setRyrq(Timestamp ryrq) {
        this.ryrq = ryrq;
    }

    public Timestamp getCyrq() {
        return cyrq;
    }

    public void setCyrq(Timestamp cyrq) {
        this.cyrq = cyrq;
    }

    public Timestamp getKsrq() {
        return ksrq;
    }

    public void setKsrq(Timestamp ksrq) {
        this.ksrq = ksrq;
    }

    public Timestamp getZzrq() {
        return zzrq;
    }

    public void setZzrq(Timestamp zzrq) {
        this.zzrq = zzrq;
    }

    public Timestamp getJsrq() {
        return jsrq;
    }

    public void setJsrq(Timestamp jsrq) {
        this.jsrq = jsrq;
    }

    public String getCzgh() {
        return czgh;
    }

    public void setCzgh(String czgh) {
        this.czgh = czgh;
    }

    public Timestamp getZfrq() {
        return zfrq;
    }

    public void setZfrq(Timestamp zfrq) {
        this.zfrq = zfrq;
    }

    public String getZfgh() {
        return zfgh;
    }

    public void setZfgh(String zfgh) {
        this.zfgh = zfgh;
    }

    public Integer getJslx() {
        return jslx;
    }

    public void setJslx(Integer jslx) {
        this.jslx = jslx;
    }

    public String getXzqhdm() {
        return xzqhdm;
    }

    public void setXzqhdm(String xzqhdm) {
        this.xzqhdm = xzqhdm;
    }

    public Integer getZfpb() {
        return zfpb;
    }

    public void setZfpb(Integer zfpb) {
        this.zfpb = zfpb;
    }
}
