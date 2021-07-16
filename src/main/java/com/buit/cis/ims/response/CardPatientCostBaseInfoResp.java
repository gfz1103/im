package com.buit.cis.ims.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;

/**
 * 病人管理-帐卡-费用帐卡(费用清单-病人费用统计信息)出参
 * zhouahsiheng
 */

@ApiModel(value="病人管理-帐卡-费用帐卡(费用清单-病人费用统计信息)出参)")
public class CardPatientCostBaseInfoResp {
    @ApiModelProperty(value="病人姓名")
    private String brxm;
    @ApiModelProperty(value="病人性质")
    private Integer brxz;
    @ApiModelProperty(value="住院天数")
    private Double zyts;
    @ApiModelProperty(value="开始日期")
    private String startDate;

    @ApiModelProperty(value="终止日期")
    private String endDate;

    @ApiModelProperty(value="住院号")
    private Integer Zyh;
    @ApiModelProperty(value="住院号码")
    private String Zyhm;

    @ApiModelProperty(value="病人科室")
    private Integer brks;
    @ApiModelProperty(value="病人床号")
    private String brch;
    @ApiModelProperty(value="自负累计")
    private BigDecimal zflj = BigDecimal.ZERO;

    @ApiModelProperty(value="自负合计")
    private BigDecimal zfhj = BigDecimal.ZERO;
    @ApiModelProperty(value="费用累计")
    private BigDecimal fylj = BigDecimal.ZERO;
    @ApiModelProperty(value="费用合计")
    private BigDecimal fyhj = BigDecimal.ZERO;

    public String getBrxm() {
        return brxm;
    }

    public void setBrxm(String brxm) {
        this.brxm = brxm;
    }

    public Integer getBrxz() {
        return brxz;
    }

    public void setBrxz(Integer brxz) {
        this.brxz = brxz;
    }

    public Double getZyts() {
        return zyts;
    }

    public void setZyts(Double zyts) {
        this.zyts = zyts;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public Integer getZyh() {
        return Zyh;
    }

    public void setZyh(Integer zyh) {
        Zyh = zyh;
    }

    public Integer getBrks() {
        return brks;
    }

    public void setBrks(Integer brks) {
        this.brks = brks;
    }

    public String getBrch() {
        return brch;
    }

    public void setBrch(String brch) {
        this.brch = brch;
    }

    public BigDecimal getZflj() {
        return zflj;
    }

    public void setZflj(BigDecimal zflj) {
        this.zflj = zflj;
    }

    public BigDecimal getZfhj() {
        return zfhj;
    }

    public void setZfhj(BigDecimal zfhj) {
        this.zfhj = zfhj;
    }

    public BigDecimal getFylj() {
        return fylj;
    }

    public void setFylj(BigDecimal fylj) {
        this.fylj = fylj;
    }

    public BigDecimal getFyhj() {
        return fyhj;
    }

    public void setFyhj(BigDecimal fyhj) {
        this.fyhj = fyhj;
    }

    public String getZyhm() {
        return Zyhm;
    }

    public void setZyhm(String zyhm) {
        Zyhm = zyhm;
    }
}
