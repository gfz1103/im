package com.buit.cis.ims.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;

/**
 * 缴费管理-缴费处理(住院病人预缴费医药费收据)出参
 * zhouhaisheng
  */
@ApiModel(value="缴费管理-缴费处理(住院病人预缴费医药费收据)出参")
public class CostPatientCollectResp {
    @ApiModelProperty(value="住院号")
    private Integer zyh;
    @ApiModelProperty(value="住院号码")
    private String zyhm;
    @ApiModelProperty(value="病人床号")
    private String brch;
    @ApiModelProperty(value="病人姓名")
    private String brxm;
    @ApiModelProperty(value="就诊卡号")
    private String jzkh;
    @ApiModelProperty(value="预付卡余额")
    private BigDecimal fykye;
    @ApiModelProperty(value="(票)卡号码")
    private String pkhm;
    @ApiModelProperty(value="预缴合计")
    private BigDecimal yjhj;
    @ApiModelProperty(value="自费合计")
    private BigDecimal zfhj;
    @ApiModelProperty(value="剩余合计")
    private BigDecimal syhj;
    @ApiModelProperty(value="输入工号")
    private String srgh;

    public Integer getZyh() {
        return zyh;
    }

    public void setZyh(Integer zyh) {
        this.zyh = zyh;
    }

    public String getBrch() {
        return brch;
    }

    public void setBrch(String brch) {
        this.brch = brch;
    }

    public String getBrxm() {
        return brxm;
    }

    public void setBrxm(String brxm) {
        this.brxm = brxm;
    }

    public String getJzkh() {
        return jzkh;
    }

    public void setJzkh(String jzkh) {
        this.jzkh = jzkh;
    }

    public BigDecimal getFykye() {
        return fykye;
    }

    public void setFykye(BigDecimal fykye) {
        this.fykye = fykye;
    }

    public String getPkhm() {
        return pkhm;
    }

    public void setPkhm(String pkhm) {
        this.pkhm = pkhm;
    }

    public BigDecimal getYjhj() {
        return yjhj;
    }

    public void setYjhj(BigDecimal yjhj) {
        this.yjhj = yjhj;
    }

    public BigDecimal getZfhj() {
        return zfhj;
    }

    public void setZfhj(BigDecimal zfhj) {
        this.zfhj = zfhj;
    }

    public BigDecimal getSyhj() {
        return syhj;
    }

    public void setSyhj(BigDecimal syhj) {
        this.syhj = syhj;
    }

    public String getSrgh() {
        return srgh;
    }

    public void setSrgh(String srgh) {
        this.srgh = srgh;
    }

    public String getZyhm() {
        return zyhm;
    }

    public void setZyhm(String zyhm) {
        this.zyhm = zyhm;
    }
}
