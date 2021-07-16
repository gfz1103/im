package com.buit.cis.ims.request;

import com.buit.commons.PageQuery;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 *结算管理-根据结算类型查询病人结算列表入参
 * zhouhaisheng
 */
@ApiModel(value = "结算管理-根据结算类型查询病人结算列表入参")
public class QueryBalanceAccountsPageReq extends PageQuery {
    @ApiModelProperty("结算类型 5:出院结算;1:中途结算;10:发票作废")
    private Integer jslx;

    @ApiModelProperty("住院号码")
    private String  zyhm;

    @ApiModelProperty(value = "1:住院 2：留观")
    private String ywlx;

    @ApiModelProperty(value = "结算结束日期")
    private String zzrq;

    @ApiModelProperty("床号")
    private String  brch;

    public Integer getJslx() {
        return jslx;
    }

    public void setJslx(Integer jslx) {
        this.jslx = jslx;
    }

    public String getZyhm() {
        return zyhm;
    }

    public void setZyhm(String zyhm) {
        this.zyhm = zyhm;
    }

    public String getYwlx() {
        return ywlx;
    }

    public void setYwlx(String ywlx) {
        this.ywlx = ywlx;
    }

    public String getZzrq() {
        return zzrq;
    }

    public void setZzrq(String zzrq) {
        this.zzrq = zzrq;
    }

    public String getBrch() {
        return brch;
    }

    public void setBrch(String brch) {
        this.brch = brch;
    }
}
