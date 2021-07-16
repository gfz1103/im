package com.buit.cis.ims.request;

import com.buit.commons.PageQuery;
import io.swagger.annotations.ApiModelProperty;

/**
 * 结算发票查询 条件封装类
 */
public class InvoiceReportReq extends PageQuery {
    @ApiModelProperty(value = "业务类型 |  1：住院 2：留观")
    private String ywlx;
    @ApiModelProperty(value="发票号码")
    private String fphm;
    @ApiModelProperty(value="住院号码")
    private String zyhm;
    @ApiModelProperty(value="患者姓名")
    private String brxm;
    @ApiModelProperty(value="作废判别 | 0：正常 1：已作废")
    private Integer zfpb;
    @ApiModelProperty(value="结算开始日期")
    private String ksrq;
    @ApiModelProperty(value="结算结束日期")
    private String zzrq;

    public String getYwlx() {
        return ywlx;
    }

    public void setYwlx(String ywlx) {
        this.ywlx = ywlx;
    }

    public String getFphm() {
        return fphm;
    }

    public void setFphm(String fphm) {
        this.fphm = fphm;
    }

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

    public Integer getZfpb() {
        return zfpb;
    }

    public void setZfpb(Integer zfpb) {
        this.zfpb = zfpb;
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
}
