package com.buit.cis.ims.response;

import com.buit.commons.PageQuery;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * 费用帐卡汇总对账出参
 * 出参
 */
@ApiModel(value="用帐卡汇总对账出参")
public class VerifyRecordResp extends PageQuery {

    @ApiModelProperty(value="费用")
    private String sfmc;
    @ApiModelProperty(value="费用名称")
    private String fymc;
    @ApiModelProperty(value="数量*天数")
    private BigDecimal fysl;
    @ApiModelProperty(value="费用单价")
    private BigDecimal fydj;
    @ApiModelProperty(value="总计金额")
    private BigDecimal zjje;
    @ApiModelProperty(value="自负金额")
    private BigDecimal zfje;
    @ApiModelProperty(value="自负比例 | 同FEE_SFDLZFBL或 FEE_YPXZ,PUB_FYXZ表中自负比例对应")
    private BigDecimal zfbl;
    @ApiModelProperty(value="药嘱序号")
    private Long yzxh;
    @ApiModelProperty(value="药嘱名称")
    private String yzmc;
    @ApiModelProperty(value="开嘱时间")
    private Timestamp kssj;
    @ApiModelProperty(value="停嘱时间")
    private Timestamp tzsj;
    @ApiModelProperty(value="开嘱医生")
    private String ysgh;
    @ApiModelProperty(value="停嘱医生")
    private String tzys;

    public String getSfmc() {
        return sfmc;
    }

    public void setSfmc(String sfmc) {
        this.sfmc = sfmc;
    }

    public String getFymc() {
        return fymc;
    }

    public void setFymc(String fymc) {
        this.fymc = fymc;
    }

    public BigDecimal getFysl() {
        return fysl;
    }

    public void setFysl(BigDecimal fysl) {
        this.fysl = fysl;
    }

    public BigDecimal getFydj() {
        return fydj;
    }

    public void setFydj(BigDecimal fydj) {
        this.fydj = fydj;
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

    public BigDecimal getZfbl() {
        return zfbl;
    }

    public void setZfbl(BigDecimal zfbl) {
        this.zfbl = zfbl;
    }

    public Long getYzxh() {
        return yzxh;
    }

    public void setYzxh(Long yzxh) {
        this.yzxh = yzxh;
    }

    public String getYzmc() {
        return yzmc;
    }

    public void setYzmc(String yzmc) {
        this.yzmc = yzmc;
    }

    public Timestamp getKssj() {
        return kssj;
    }

    public void setKssj(Timestamp kssj) {
        this.kssj = kssj;
    }

    public Timestamp getTzsj() {
        return tzsj;
    }

    public void setTzsj(Timestamp tzsj) {
        this.tzsj = tzsj;
    }

    public String getYsgh() {
        return ysgh;
    }

    public void setYsgh(String ysgh) {
        this.ysgh = ysgh;
    }

    public String getTzys() {
        return tzys;
    }

    public void setTzys(String tzys) {
        this.tzys = tzys;
    }
}
