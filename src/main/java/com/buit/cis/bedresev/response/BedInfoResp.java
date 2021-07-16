package com.buit.cis.bedresev.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.sql.Timestamp;

@ApiModel(value = "床位信息预约信息返回结果封装")
public class BedInfoResp {
    @ApiModelProperty(value = "机构编码")
    private Integer jgid;
    @ApiModelProperty(value = "病人床号")
    private String brch;
    @ApiModelProperty(value = "房间号码")
    private String fjhm;
    @ApiModelProperty(value = "床位科室")
    private Integer cwks;
    @ApiModelProperty(value = "床位病区")
    private Integer ksdm;
    @ApiModelProperty(value = "床位性别")
    private Integer cwxb;
    @ApiModelProperty(value = "住院号")
    private Integer zyh;
    @ApiModelProperty(value = "病人姓名")
    private String brxm;
    @ApiModelProperty(value = "出院日期 | 病区办理出院证明的日期(可以提前或推后)")
    private Timestamp cyrq;
    @ApiModelProperty(value = "床位状态 0：空床，1：非空床")
    private String cwzt;
    @ApiModelProperty(value = "预约状态 0:未待床 1:已待床")
    private String dczt;

    public Integer getJgid() {
        return jgid;
    }

    public void setJgid(Integer jgid) {
        this.jgid = jgid;
    }

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

    public Integer getCwks() {
        return cwks;
    }

    public void setCwks(Integer cwks) {
        this.cwks = cwks;
    }

    public Integer getKsdm() {
        return ksdm;
    }

    public void setKsdm(Integer ksdm) {
        this.ksdm = ksdm;
    }

    public Integer getCwxb() {
        return cwxb;
    }

    public void setCwxb(Integer cwxb) {
        this.cwxb = cwxb;
    }

    public Integer getZyh() {
        return zyh;
    }

    public void setZyh(Integer zyh) {
        this.zyh = zyh;
    }

    public String getBrxm() {
        return brxm;
    }

    public void setBrxm(String brxm) {
        this.brxm = brxm;
    }

    public Timestamp getCyrq() {
        return cyrq;
    }

    public void setCyrq(Timestamp cyrq) {
        this.cyrq = cyrq;
    }

    public String getCwzt() {
        return cwzt;
    }

    public void setCwzt(String cwzt) {
        this.cwzt = cwzt;
    }

    public String getDczt() {
        return dczt;
    }

    public void setDczt(String dczt) {
        this.dczt = dczt;
    }
}
