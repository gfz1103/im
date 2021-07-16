package com.buit.cis.ims.response;

import com.buit.commons.PageQuery;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * @Author jiangwei
 * @Date 2021/04/19
 */
@ApiModel(value = "床位清单报表打印结果封装")
public class ReportBedListResp extends PageQuery {
    @ApiModelProperty(value = "病人床号")
    private String brch;
    @ApiModelProperty(value = "房间号码")
    private String fjhm;
    @ApiModelProperty(value = "床位科室")
    private String cwks;
    @ApiModelProperty(value = "床位病区")
    private String ksdm;
    @ApiModelProperty(value = "床位性别")
    private String cwxb;
    @ApiModelProperty(value = "床位费用")
    private BigDecimal cwfy;
    @ApiModelProperty(value = "住院号码")
    private String zyhm;
    @ApiModelProperty(value = "病人姓名")
    private String brxm;
    @ApiModelProperty(value = "病人性别")
    private String brxb;
    @ApiModelProperty(value = "病人性质")
    private String brxz;
    @ApiModelProperty(value = "病人科室")
    private String brks;
    @ApiModelProperty(value = "病人病区")
    private String brbq;
    @ApiModelProperty(value = "入院日期")
    private Timestamp ryrq;
    @ApiModelProperty(value = "备注")
    private String bzxx;
    @ApiModelProperty(value = "是否有人")
    private String status;


    public String getBrch() {
        return brch;
    }

    public void setBrch(String brch) {
        this.brch = brch;
    }

    public String getFjhm() {
        return fjhm;
    }

    public void setFjhm(String fjhm) {
        this.fjhm = fjhm;
    }

    public String getCwks() {
        return cwks;
    }

    public void setCwks(String cwks) {
        this.cwks = cwks;
    }

    public String getKsdm() {
        return ksdm;
    }

    public void setKsdm(String ksdm) {
        this.ksdm = ksdm;
    }

    public String getCwxb() {
        return cwxb;
    }

    public void setCwxb(String cwxb) {
        this.cwxb = cwxb;
    }

    public BigDecimal getCwfy() {
        return cwfy;
    }

    public void setCwfy(BigDecimal cwfy) {
        this.cwfy = cwfy;
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

    public String getBrxb() {
        return brxb;
    }

    public void setBrxb(String brxb) {
        this.brxb = brxb;
    }

    public String getBrxz() {
        return brxz;
    }

    public void setBrxz(String brxz) {
        this.brxz = brxz;
    }

    public String getBrks() {
        return brks;
    }

    public void setBrks(String brks) {
        this.brks = brks;
    }

    public String getBrbq() {
        return brbq;
    }

    public void setBrbq(String brbq) {
        this.brbq = brbq;
    }

    public Timestamp getRyrq() {
        return ryrq;
    }

    public void setRyrq(Timestamp ryrq) {
        this.ryrq = ryrq;
    }

    public String getBzxx() {
        return bzxx;
    }

    public void setBzxx(String bzxx) {
        this.bzxx = bzxx;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
