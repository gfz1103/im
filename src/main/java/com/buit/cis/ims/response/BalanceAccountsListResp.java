package com.buit.cis.ims.response;

import com.buit.commons.PageQuery;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
 * 结算管理-病人结算列表显示
 * zhouhaisheng
 */
@ApiModel(value="结算管理-病人结算列表显示")
public class BalanceAccountsListResp extends PageQuery {
    @ApiModelProperty("住院号")
    private Integer zyh;
    @ApiModelProperty("住院号码")
    private String zyhm;
    @ApiModelProperty("发票号码")
    private String fphm;
    @ApiModelProperty("医保卡号")
    private String ybkh;

    @ApiModelProperty("病人姓名")
    private String brxm;

    @ApiModelProperty("病人床号")
    private String brch;

    @ApiModelProperty("病人病区")
    private String brbq;

    @ApiModelProperty("病人性质")
    private String brxz;

    @ApiModelProperty("上级病人性质")
    private String sjxz;

    @ApiModelProperty("入院日期")
    private Date ryrq;

    @ApiModelProperty("出院日期")
    private Date cyrq;

    @ApiModelProperty("住院天数")
    private String zyts;

    @ApiModelProperty("结算开始日期")
    private Date ksrq;

    @ApiModelProperty("结算终止日期")
    private Date zzrq;

    @ApiModelProperty("结算天数")
    private String jsts;

    @ApiModelProperty("备注")
    private String remarks;

    @ApiModelProperty("行政区划代码")
    private String xzqhdm;

    @ApiModelProperty("高价药标志 0:不是高价药 1:是高价药")
    private Integer gjybz;

    @ApiModelProperty("结算类型")
    private Integer jslx;

    @ApiModelProperty("结算次数")
    private Integer jscs;

    public Integer getZyh() {
        return zyh;
    }

    public void setZyh(Integer zyh) {
        this.zyh = zyh;
    }

    public String getFphm() {
        return fphm;
    }

    public void setFphm(String fphm) {
        this.fphm = fphm;
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

    public String getBrxz() {
        return brxz;
    }

    public void setBrxz(String brxz) {
        this.brxz = brxz;
    }

    public Date getRyrq() {
        return ryrq;
    }

    public void setRyrq(Date ryrq) {
        this.ryrq = ryrq;
    }

    public Date getCyrq() {
        return cyrq;
    }

    public void setCyrq(Date cyrq) {
        this.cyrq = cyrq;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getXzqhdm() {
        return xzqhdm;
    }

    public void setXzqhdm(String xzqhdm) {
        this.xzqhdm = xzqhdm;
    }

    public Integer getGjybz() {
        return gjybz;
    }

    public void setGjybz(Integer gjybz) {
        this.gjybz = gjybz;
    }

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

    public Integer getJscs() {
        return jscs;
    }

    public void setJscs(Integer jscs) {
        this.jscs = jscs;
    }

    public String getYbkh() {
        return ybkh;
    }

    public void setYbkh(String ybkh) {
        this.ybkh = ybkh;
    }

    public String getSjxz() {
        return sjxz;
    }

    public void setSjxz(String sjxz) {
        this.sjxz = sjxz;
    }

    public String getZyts() {
        return zyts;
    }

    public void setZyts(String zyts) {
        this.zyts = zyts;
    }

    public Date getKsrq() {
        return ksrq;
    }

    public void setKsrq(Date ksrq) {
        this.ksrq = ksrq;
    }

    public Date getZzrq() {
        return zzrq;
    }

    public void setZzrq(Date zzrq) {
        this.zzrq = zzrq;
    }

    public String getJsts() {
        return jsts;
    }

    public void setJsts(String jsts) {
        this.jsts = jsts;
    }

    public String getBrbq() {
        return brbq;
    }

    public void setBrbq(String brbq) {
        this.brbq = brbq;
    }
}
