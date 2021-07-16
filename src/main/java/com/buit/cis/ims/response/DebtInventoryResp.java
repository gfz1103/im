package com.buit.cis.ims.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * 催款管理-欠费清单(列表数据)
 */
@ApiModel(value = "催款管理-欠费清单(列表数据)")
public class DebtInventoryResp {
    @ApiModelProperty(value = "住院号", hidden = true)
    private Integer zyh;
    @ApiModelProperty(value = "住院号码")
    private String zyhm;
    @ApiModelProperty(value = "病人床号")
    private String brch;
    @ApiModelProperty(value = "病人性质 sys_dict_config:14")
    private Integer brxz;
    @ApiModelProperty(value = "病人姓名")
    private String brxm;
    @ApiModelProperty(value = "病人性别 DIC_GBSJ01:PD0000000269")
    private Integer brxb;
    @ApiModelProperty(value = "年龄")
    private String age;
    @ApiModelProperty(value = "出生年月")
    private Timestamp csny;
    @ApiModelProperty(value = "入院日期")
    private Timestamp ryrq;
    @ApiModelProperty(value = "住院天数")
    private double zyts;
    @ApiModelProperty(value = "缴款金额")
    private BigDecimal jkje;
    @ApiModelProperty(value = "总计金额")
    private BigDecimal zjje;
    @ApiModelProperty(value = "自负金额")
    private BigDecimal zfje;
    @ApiModelProperty(value = "欠费金额")
    private BigDecimal qfje;
    @ApiModelProperty(value = "可报金额")
    private BigDecimal kbje;
    @ApiModelProperty(value ="科室名称",hidden = true)
    private String ksmc;
    @ApiModelProperty(value ="病区名称",hidden = true)
    private String bqmc;
    @ApiModelProperty(value = "补缴金额")
    private BigDecimal bjje;
    @ApiModelProperty(value = "1:科室 2：病区")
    private Integer type;

    public String getZyhm() {
        return zyhm;
    }

    public void setZyhm(String zyhm) {
        this.zyhm = zyhm;
    }

    public String getBrch() {
        return brch;
    }

    public void setBrch(String brch) {
        this.brch = brch;
    }

    public Integer getBrxz() {
        return brxz;
    }

    public void setBrxz(Integer brxz) {
        this.brxz = brxz;
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

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public double getZyts() {
        return zyts;
    }

    public void setZyts(double zyts) {
        this.zyts = zyts;
    }

    public BigDecimal getJkje() {
        return jkje;
    }

    public void setJkje(BigDecimal jkje) {
        this.jkje = jkje;
    }

    public BigDecimal getZfje() {
        return zfje;
    }

    public void setZfje(BigDecimal zfje) {
        this.zfje = zfje;
    }

    public BigDecimal getQfje() {
        return qfje;
    }

    public void setQfje(BigDecimal qfje) {
        this.qfje = qfje;
    }

    public Integer getZyh() {
        return zyh;
    }

    public void setZyh(Integer zyh) {
        this.zyh = zyh;
    }

    public Timestamp getCsny() {
        return csny;
    }

    public void setCsny(Timestamp csny) {
        this.csny = csny;
    }

    public Timestamp getRyrq() {
        return ryrq;
    }

    public void setRyrq(Timestamp ryrq) {
        this.ryrq = ryrq;
    }

    public BigDecimal getBjje() {
        return bjje;
    }

    public void setBjje(BigDecimal bjje) {
        this.bjje = bjje;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getKsmc() {
        return ksmc;
    }

    public void setKsmc(String ksmc) {
        this.ksmc = ksmc;
    }

    public String getBqmc() {
        return bqmc;
    }

    public void setBqmc(String bqmc) {
        this.bqmc = bqmc;
    }

    public BigDecimal getKbje() {
        return kbje;
    }

    public void setKbje(BigDecimal kbje) {
        this.kbje = kbje;
    }

    public BigDecimal getZjje() {
        return zjje;
    }

    public void setZjje(BigDecimal zjje) {
        this.zjje = zjje;
    }
}
