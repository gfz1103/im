package com.buit.cis.ims.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;

/**
 * @Description
 * @Author jiangwei
 * @Date 2021-04-22
 */
@ApiModel(value = "费用清单打印-费用列表数据返回结果")
public class ExpensesPrintDataResp {
    @ApiModelProperty(value = "医保编号")
    private String ybbm;
    @ApiModelProperty(value = "费用归并")
    private String fygb;
    @ApiModelProperty(value = "费用日期")
    private String fyrq;
    @ApiModelProperty(value = "费用名称")
    private String fymc;
    @ApiModelProperty(value = "费用科室")
    private String fyks;
    @ApiModelProperty(value = "数量")
    private BigDecimal sl;
    @ApiModelProperty(value = "单位")
    private String dw;
    @ApiModelProperty(value = "单价")
    private BigDecimal dj;
    @ApiModelProperty(value = "金额")
    private BigDecimal je;
    @ApiModelProperty(value = "自负比例")
    private BigDecimal zfbl;
    @ApiModelProperty(value = "报销金额（医保范围）")
    private BigDecimal bxje;
    @ApiModelProperty(value = "自负金额（分类自负）")
    private BigDecimal zfje;
    @ApiModelProperty(value = "自费金额（不走医保的费用的总金额）")
    private BigDecimal zfje2;
    @ApiModelProperty(value = "开始日期")
    private String ksrq;
    @ApiModelProperty(value = "结束日期")
    private String jsrq;

    public String getYbbm() {
        return ybbm;
    }

    public void setYbbm(String ybbm) {
        this.ybbm = ybbm;
    }

    public String getFygb() {
        return fygb;
    }

    public void setFygb(String fygb) {
        this.fygb = fygb;
    }

    public String getFyrq() {
        return fyrq;
    }

    public void setFyrq(String fyrq) {
        this.fyrq = fyrq;
    }

    public String getFymc() {
        return fymc;
    }

    public void setFymc(String fymc) {
        this.fymc = fymc;
    }

    public String getFyks() {
        return fyks;
    }

    public void setFyks(String fyks) {
        this.fyks = fyks;
    }

    public BigDecimal getSl() {
        return sl == null ? BigDecimal.ZERO : sl;
    }

    public void setSl(BigDecimal sl) {
        this.sl = sl;
    }

    public String getDw() {
        return dw;
    }

    public void setDw(String dw) {
        this.dw = dw;
    }

    public BigDecimal getDj() {
        return dj == null ? BigDecimal.ZERO : dj;
    }

    public void setDj(BigDecimal dj) {
        this.dj = dj;
    }

    public BigDecimal getJe() {
        return je == null ? BigDecimal.ZERO : je;
    }

    public void setJe(BigDecimal je) {
        this.je = je;
    }

    public BigDecimal getZfbl() {
        return zfbl == null ? BigDecimal.ZERO : zfbl;
    }

    public void setZfbl(BigDecimal zfbl) {
        this.zfbl = zfbl;
    }

    public BigDecimal getZfje() {
        return zfje == null ? BigDecimal.ZERO : zfje;
    }

    public void setZfje(BigDecimal zfje) {
        this.zfje = zfje;
    }

    public String getKsrq() {
        return ksrq;
    }

    public void setKsrq(String ksrq) {
        this.ksrq = ksrq;
    }

    public String getJsrq() {
        return jsrq;
    }

    public void setJsrq(String jsrq) {
        this.jsrq = jsrq;
    }

    public BigDecimal getBxje() {
        return bxje == null ? BigDecimal.ZERO : bxje;
    }

    public void setBxje(BigDecimal bxje) {
        this.bxje = bxje;
    }

    public BigDecimal getZfje2() {
        return zfje2;
    }

    public void setZfje2(BigDecimal zfje2) {
        this.zfje2 = zfje2;
    }
}
