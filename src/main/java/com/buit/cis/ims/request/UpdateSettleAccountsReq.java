package com.buit.cis.ims.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;

/**
 * zhouhaisheng
 * 住院结算作废保存入参
 */
@ApiModel(value = "住院结算作废保存入参")
public class UpdateSettleAccountsReq {
    @ApiModelProperty(value = "病人病区")
     private  Integer brbq;

     @ApiModelProperty(value = "登记id")
     private Integer djid;

     @ApiModelProperty(value = "开始日期")
     private String ksrq;

     @ApiModelProperty(value = "自负合计")
     private BigDecimal zfhj;

     @ApiModelProperty(value = "")
     private String tcje;

     @ApiModelProperty(value = "病人性质")
     private Integer brxz;
     @ApiModelProperty(value = "")
     private String xtrq;

     @ApiModelProperty(value = "病案号码")
     private String bahm;

     @ApiModelProperty(value = "病人姓名")
     private String brxm;

     @ApiModelProperty(value = "结算次数")
     private Integer jscs;

     @ApiModelProperty(value = "备注")
     private String remark;

     @ApiModelProperty(value = "")
     private String ljrq;

     @ApiModelProperty(value = "入院日期")
     private String ryrq;

     @ApiModelProperty(value = "缴款合计")
     private BigDecimal jkhj;

     @ApiModelProperty(value = "结算金额")
     private double jsje;

     @ApiModelProperty(value = "病人科室")
     private Integer brks;

     @ApiModelProperty(value = "住院号")
     private Integer zyh;

     @ApiModelProperty(value = "结算日期")
     private String jsrq;

     @ApiModelProperty(value = "出院日期")
     private String cyrq;

     @ApiModelProperty(value = "病人床号")
     private String brch;

     @ApiModelProperty(value = "住院号码")
     private String zyhm;

     @ApiModelProperty(value = "")
     private String sjzy;

     @ApiModelProperty(value = "")
     private String zhzf;

     @ApiModelProperty(value = "")
     private String hzrq;

     @ApiModelProperty(value = "")
     private String brqm;

     @ApiModelProperty(value = "")
     private double fyhj;

     @ApiModelProperty(value = "出院判别")
     private Integer cypb;

     @ApiModelProperty(value = "结算类型")
     private Integer jslx;

     @ApiModelProperty(value = "作废判别")
     private Integer zfpb;

     @ApiModelProperty(value = "结算标识")
     private Integer jsbs;

    @ApiModelProperty(value = "卡类型")
    private String cardtype;
    @ApiModelProperty(value = "卡号")
    private String carddata;

    public Integer getBrbq() {
        return brbq;
    }

    public void setBrbq(Integer brbq) {
        this.brbq = brbq;
    }

    public Integer getDjid() {
        return djid;
    }

    public void setDjid(Integer djid) {
        this.djid = djid;
    }

    public String getKsrq() {
        return ksrq;
    }

    public void setKsrq(String ksrq) {
        this.ksrq = ksrq;
    }

    public BigDecimal getZfhj() {
        return zfhj;
    }

    public void setZfhj(BigDecimal zfhj) {
        this.zfhj = zfhj;
    }

    public String getTcje() {
        return tcje;
    }

    public void setTcje(String tcje) {
        this.tcje = tcje;
    }

    public Integer getBrxz() {
        return brxz;
    }

    public void setBrxz(Integer brxz) {
        this.brxz = brxz;
    }

    public String getXtrq() {
        return xtrq;
    }

    public void setXtrq(String xtrq) {
        this.xtrq = xtrq;
    }

    public String getBahm() {
        return bahm;
    }

    public void setBahm(String bahm) {
        this.bahm = bahm;
    }

    public String getBrxm() {
        return brxm;
    }

    public void setBrxm(String brxm) {
        this.brxm = brxm;
    }

    public Integer getJscs() {
        return jscs;
    }

    public void setJscs(Integer jscs) {
        this.jscs = jscs;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getLjrq() {
        return ljrq;
    }

    public void setLjrq(String ljrq) {
        this.ljrq = ljrq;
    }

    public String getRyrq() {
        return ryrq;
    }

    public void setRyrq(String ryrq) {
        this.ryrq = ryrq;
    }

    public BigDecimal getJkhj() {
        return jkhj;
    }

    public void setJkhj(BigDecimal jkhj) {
        this.jkhj = jkhj;
    }

    public double getJsje() {
        return jsje;
    }

    public void setJsje(double jsje) {
        this.jsje = jsje;
    }

    public Integer getBrks() {
        return brks;
    }

    public void setBrks(Integer brks) {
        this.brks = brks;
    }

    public Integer getZyh() {
        return zyh;
    }

    public void setZyh(Integer zyh) {
        this.zyh = zyh;
    }

    public String getJsrq() {
        return jsrq;
    }

    public void setJsrq(String jsrq) {
        this.jsrq = jsrq;
    }

    public String getCyrq() {
        return cyrq;
    }

    public void setCyrq(String cyrq) {
        this.cyrq = cyrq;
    }

    public String getBrch() {
        return brch;
    }

    public void setBrch(String brch) {
        this.brch = brch;
    }

    public String getZyhm() {
        return zyhm;
    }

    public void setZyhm(String zyhm) {
        this.zyhm = zyhm;
    }

    public String getSjzy() {
        return sjzy;
    }

    public void setSjzy(String sjzy) {
        this.sjzy = sjzy;
    }

    public String getZhzf() {
        return zhzf;
    }

    public void setZhzf(String zhzf) {
        this.zhzf = zhzf;
    }

    public String getHzrq() {
        return hzrq;
    }

    public void setHzrq(String hzrq) {
        this.hzrq = hzrq;
    }

    public String getBrqm() {
        return brqm;
    }

    public void setBrqm(String brqm) {
        this.brqm = brqm;
    }

    public double getFyhj() {
        return fyhj;
    }

    public void setFyhj(double fyhj) {
        this.fyhj = fyhj;
    }

    public Integer getCypb() {
        return cypb;
    }

    public void setCypb(Integer cypb) {
        this.cypb = cypb;
    }

    public Integer getJslx() {
        return jslx;
    }

    public void setJslx(Integer jslx) {
        this.jslx = jslx;
    }

    public Integer getZfpb() {
        return zfpb;
    }

    public void setZfpb(Integer zfpb) {
        this.zfpb = zfpb;
    }

    public Integer getJsbs() {
        return jsbs;
    }

    public void setJsbs(Integer jsbs) {
        this.jsbs = jsbs;
    }

    public String getCardtype() {
        return cardtype;
    }

    public void setCardtype(String cardtype) {
        this.cardtype = cardtype;
    }

    public String getCarddata() {
        return carddata;
    }

    public void setCarddata(String carddata) {
        this.carddata = carddata;
    }
}
