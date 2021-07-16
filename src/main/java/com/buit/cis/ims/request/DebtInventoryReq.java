package com.buit.cis.ims.request;

import com.buit.commons.PageQuery;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;

/**
 * 催款管理-欠费清单(入参)
 * zhouhaisheng
 */
@ApiModel(value = "催款管理-欠费清单(入参)")
public class DebtInventoryReq extends PageQuery {
    @ApiModelProperty(value = "机构id",hidden = true)
    private Integer jgid;
    @ApiModelProperty(value = "病人科室 sys_dict_config:15")
    private Integer brks;
    @ApiModelProperty(value = "病人病区 sys_dict_config:18")
    private Integer brbq;
    @ApiModelProperty(value = "催款金额")
    private BigDecimal ckje;
    @ApiModelProperty(value = "住院欠费提醒金额",hidden = true)
    private BigDecimal txje;
    @ApiModelProperty(value = "住院号码")
    private String zyhm;
    @ApiModelProperty(value = "患者姓名")
    private String brxm;
    @ApiModelProperty(value = "患者床号")
    private String brch;
    @ApiModelProperty(value = "病人性质")
    private String brxz;
    @ApiModelProperty(value = "业务类型  1：住院 2：留观")
    private String ywlx;

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

    public BigDecimal getCkje() {
        return ckje;
    }

    public void setCkje(BigDecimal ckje) {
        this.ckje = ckje;
    }

    public Integer getJgid() {
        return jgid;
    }

    public void setJgid(Integer jgid) {
        this.jgid = jgid;
    }

    public BigDecimal getTxje() {
        return txje;
    }

    public void setTxje(BigDecimal txje) {
        this.txje = txje;
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

    public String getBrch() {
        return brch;
    }

    public void setBrch(String brch) {
        this.brch = brch;
    }

    public String getYwlx() {
        return ywlx;
    }

    public void setYwlx(String ywlx) {
        this.ywlx = ywlx;
    }

    public String getBrxz() {
        return brxz;
    }

    public void setBrxz(String brxz) {
        this.brxz = brxz;
    }
}
