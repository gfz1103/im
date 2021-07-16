package com.buit.cis.ims.response;

import com.buit.commons.PageQuery;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;
import java.sql.Timestamp;


/**
 * 类名称：ImTbkk<br>
 * 类描述：住院管理-缴费管理(缴费处理-分页查询)出参<br>
 * @author ZHOUHAISHENG
 */
@ApiModel(value="住院管理-缴费管理(缴费处理-分页查询)出参")
public class PatientPayPageResp extends PageQuery {
    @ApiModelProperty(value="主键id")
    private Integer jkxh;

    @ApiModelProperty(value="病人姓名")
    private String brxm;
    @ApiModelProperty(value="住院号")
    private Integer zyh;
    @ApiModelProperty(value="缴款日期")
    private Timestamp jkrq;
    @ApiModelProperty(value="缴款金额")
    private BigDecimal jkje;
    @ApiModelProperty(value="缴款方式 | 1:现金,2:支票(与ZY_JKFS表中JKFS字段对应")
    private Integer jkfs;
    @ApiModelProperty(value="收据号码 | 病人预缴款收据号码")
    private String sjhm;
    @ApiModelProperty(value="支票号码 | 病人缴款为支票时的支票号码")
    private String zphm;
    @ApiModelProperty(value="住院号码")
    private String zyhm;
//    @ApiModelProperty(value="作废标识 0未作废 1已作废")
//    private String zfpb;
    @ApiModelProperty(value="作废日期")
    private Timestamp zfrq;
    @ApiModelProperty(value = "缴款类型 | 0：正常预缴   1：结算抵扣   2：取消结算   3：注销找退   4：出院找退")
    private Integer jklx;
    @ApiModelProperty(value = "发票号码 | 病人结算发票号码")
    private String fphm;

    public String getBrxm() {
        return brxm;
    }

    public void setBrxm(String brxm) {
        this.brxm = brxm;
    }


    public Integer getZyh() {
        return zyh;
    }

    public void setZyh(Integer zyh) {
        this.zyh = zyh;
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

    public String getZyhm() {
        return zyhm;
    }

    public void setZyhm(String zyhm) {
        this.zyhm = zyhm;
    }

    public Timestamp getZfrq() {
        return zfrq;
    }

    public void setZfrq(Timestamp zfrq) {
        this.zfrq = zfrq;
    }

//    public String getZfpb() {
//        return zfpb;
//    }
//
//    public void setZfpb(String zfpb) {
//        this.zfpb = zfpb;
//    }

    public Integer getJkxh() {
        return jkxh;
    }

    public void setJkxh(Integer jkxh) {
        this.jkxh = jkxh;
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
