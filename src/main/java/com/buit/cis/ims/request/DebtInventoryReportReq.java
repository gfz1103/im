package com.buit.cis.ims.request;

import com.buit.commons.PageQuery;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;

/**
 * @Author zhouhaisheng
 *  催款清单入参
 **/
@ApiModel(value = "催款清单入参")
public class DebtInventoryReportReq extends PageQuery {
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
    @ApiModelProperty(value = "病人姓名")
    private String brxm;
    @ApiModelProperty(value = "(住院号码)&(补缴金额)&(病人科室)&(病人病区) 生成报表集合逗号分隔")
    private String zyhmStr;
    @ApiModelProperty(value = "1:搜索打印 2:切换Tab页打印")
    private String queryType;

    public Integer getJgid() {
        return jgid;
    }

    public void setJgid(Integer jgid) {
        this.jgid = jgid;
    }

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

    public String getZyhmStr() {
        return zyhmStr;
    }

    public void setZyhmStr(String zyhmStr) {
        this.zyhmStr = zyhmStr;
    }

    public String getQueryType() {
        return queryType;
    }

    public void setQueryType(String queryType) {
        this.queryType = queryType;
    }
}
