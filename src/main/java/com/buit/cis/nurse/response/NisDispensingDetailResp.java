
package com.buit.cis.nurse.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;
import java.sql.Timestamp;


/**
 * @Author jiangwei
 * @Date 2021/6/9 14:41
 */
@ApiModel(value = "病区发药明细查询封装")
public class NisDispensingDetailResp {
    @ApiModelProperty(value = "记录序号")
    private Integer jlxh;
    @ApiModelProperty(value = "住院号")
    private Integer zyh;
    @ApiModelProperty(value = "病人床号")
    private String brch;
    @ApiModelProperty(value = "病人姓名")
    private String brxm;
    @ApiModelProperty(value = "发药日期")
    private Timestamp jfrq;
    @ApiModelProperty(value = "数量")
    private BigDecimal ypsl;
    @ApiModelProperty(value = "药品名称")
    private String ypmc;
    @ApiModelProperty(value = "单价")
    private BigDecimal ypdj;
    @ApiModelProperty(value = "金额")
    private BigDecimal fyje;
    @ApiModelProperty(value = "单位")
    private String yfdw;
    @ApiModelProperty(value = "产地")
    private String cdmc;

    public Integer getJlxh() {
        return jlxh;
    }

    public void setJlxh(Integer jlxh) {
        this.jlxh = jlxh;
    }

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

    public Timestamp getJfrq() {
        return jfrq;
    }

    public void setJfrq(Timestamp jfrq) {
        this.jfrq = jfrq;
    }

    public BigDecimal getYpsl() {
        return ypsl;
    }

    public void setYpsl(BigDecimal ypsl) {
        this.ypsl = ypsl;
    }

    public String getYpmc() {
        return ypmc;
    }

    public void setYpmc(String ypmc) {
        this.ypmc = ypmc;
    }

    public BigDecimal getYpdj() {
        return ypdj;
    }

    public void setYpdj(BigDecimal ypdj) {
        this.ypdj = ypdj;
    }

    public BigDecimal getFyje() {
        return fyje;
    }

    public void setFyje(BigDecimal fyje) {
        this.fyje = fyje;
    }

    public String getYfdw() {
        return yfdw;
    }

    public void setYfdw(String yfdw) {
        this.yfdw = yfdw;
    }

    public String getCdmc() {
        return cdmc;
    }

    public void setCdmc(String cdmc) {
        this.cdmc = cdmc;
    }
}