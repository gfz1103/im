package com.buit.cis.ims.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 病人管理-帐卡-费用帐卡(帐卡费用列表)入参
 *@author ZHOUHAISHENG
 *
 */
@ApiModel(value="病人管理-帐卡-费用帐卡(帐卡费用列表)入参")
public class CardPatientInfoCostReq {

    @ApiModelProperty(value="住院号 | 该住院号为内码")
    private Integer zyh;
    @ApiModelProperty(value="结算次数")
    private Integer jscs=0;
    @ApiModelProperty(value="结算类型 | 1：中途结算 2：预结（不写IM_ZYJS）3：预结后出院结算 4：终结处理(异常出院) 5：正常出院结算 6：合并结算 9：退费")
    private String jslx;
    @ApiModelProperty(value="结算开始日期")
    private String ksrq;
    @ApiModelProperty(value="结算终止日期")
    private String zzrq;
    @ApiModelProperty(value="结算日期")
    private String jsrq;

    @ApiModelProperty(value="病人性质")
    private String brxz;
    @ApiModelProperty(value="住院号码")
    private String zyhm;
    @ApiModelProperty(value="病人床号")
    private String brch;
    @ApiModelProperty(value="发票号码")
    private String fphm;

    @ApiModelProperty(value="机构id",hidden = true)
    private Integer jgid;

    public Integer getZyh() {
        return zyh;
    }

    public void setZyh(Integer zyh) {
        this.zyh = zyh;
    }

    public Integer getJscs() {
        return jscs;
    }

    public void setJscs(Integer jscs) {
        this.jscs = jscs;
    }

    public String getJslx() {
        return jslx;
    }

    public void setJslx(String jslx) {
        this.jslx = jslx;
    }

    public String getJsrq() {
        return jsrq;
    }

    public void setJsrq(String jsrq) {
        this.jsrq = jsrq;
    }

    public Integer getJgid() {
        return jgid;
    }

    public void setJgid(Integer jgid) {
        this.jgid = jgid;
    }

    public String getBrxz() {
        return brxz;
    }

    public void setBrxz(String brxz) {
        this.brxz = brxz;
    }

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

    public String getFphm() {
        return fphm;
    }

    public void setFphm(String fphm) {
        this.fphm = fphm;
    }

    public String getKsrq() {
        return ksrq;
    }

    public void setKsrq(String ksrq) {
        this.ksrq = ksrq;
    }

    public String getZzrq() {
        return zzrq;
    }

    public void setZzrq(String zzrq) {
        this.zzrq = zzrq;
    }

}
