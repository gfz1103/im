package com.buit.cis.ims.response;

import com.buit.commons.PageQuery;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * 在院病人预缴款统计(分页出参)
 */
@ApiModel(value = "在院病人预缴款统计(分页出参)")
public class PatientImTbkkResp extends PageQuery {

    @ApiModelProperty(value = "住院号码")
    private String zyhm;

    @ApiModelProperty(value = "病人姓名")
    private String brxm;

    @ApiModelProperty(value = "床号")
    private String brch;

    @ApiModelProperty(value = "病人科室")
    private Integer brks;

    @ApiModelProperty(value = "病人病区")
    private Integer brbq;

    @ApiModelProperty(value = "缴款日期")
    private Timestamp jkrq;

    @ApiModelProperty(value = "缴款金额")
    private BigDecimal jkje;

    @ApiModelProperty(value = "缴款类型 | 0：正常预缴   1：结算抵扣   2：取消结算   3：注销找退   4：出院找退")
    private Integer jklx;

    @ApiModelProperty(value = "缴款方式 | 1:现金, 2:支票")
    private Integer jkfs;

    @ApiModelProperty(value = "收据号码 | 病人预缴款收据号码")
    private String sjhm;

    @ApiModelProperty(value = "支票号码 | 病人缴款为支票时的支票号码")
    private String zphm;

    @ApiModelProperty(value = "发票号码 | 病人结算发票号码")
    private String fphm;

    @ApiModelProperty(value = "操作工号")
    private String czgh;

//    @ApiModelProperty(value = "作废判别 | 注销预缴款或发票作废时填写 0:正常 1：作废")
//    private Integer zfpb;

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

    public String getBrch() {
        return brch;
    }

    public void setBrch(String brch) {
        this.brch = brch;
    }

    public Integer getBrks() {
        return brks;
    }

    public void setBrks(Integer brks) {
        this.brks = brks;
    }

    public Timestamp getJkrq() {
        return jkrq;
    }

    public void setJkrq(Timestamp jkrq) {
        this.jkrq = jkrq;
    }

    public BigDecimal getJkje() {
        return jkje;
    }

    public void setJkje(BigDecimal jkje) {
        this.jkje = jkje;
    }

    public Integer getJkfs() {
        return jkfs;
    }

    public void setJkfs(Integer jkfs) {
        this.jkfs = jkfs;
    }

    public String getSjhm() {
        return sjhm;
    }

    public void setSjhm(String sjhm) {
        this.sjhm = sjhm;
    }

    public String getZphm() {
        return zphm;
    }

    public void setZphm(String zphm) {
        this.zphm = zphm;
    }

    public String getCzgh() {
        return czgh;
    }

    public void setCzgh(String czgh) {
        this.czgh = czgh;
    }

//    public Integer getZfpb() {
//        return zfpb;
//    }
//
//    public void setZfpb(Integer zfpb) {
//        this.zfpb = zfpb;
//    }

    public Integer getBrbq() {
        return brbq;
    }

    public void setBrbq(Integer brbq) {
        this.brbq = brbq;
    }

    public Integer getJklx() {
        return jklx;
    }

    public void setJklx(Integer jklx) {
        this.jklx = jklx;
    }

    public String getFphm() {
        return fphm;
    }

    public void setFphm(String fphm) {
        this.fphm = fphm;
    }
}
