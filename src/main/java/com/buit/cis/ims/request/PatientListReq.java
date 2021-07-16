package com.buit.cis.ims.request;

import com.buit.commons.PageQuery;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 类名称：
 * 病人管理列表入参
 * 类描述：<br>
 * @author zhouhaisheng
 */
@ApiModel(value="病人管理列表入参")
public class PatientListReq extends PageQuery {
    @JsonIgnore
    @ApiModelProperty(value = "医疗机构id", hidden = true)
    private Integer jgid;
    @ApiModelProperty(value = "就诊卡号")
    private String jzkh;

    @ApiModelProperty(value = "住院号码")
    private String zyhm;
    @ApiModelProperty(value = "姓名")
    private String brxm;
    @ApiModelProperty(value = "病区 字典:sys_dict_config:18")
    private Integer brbq;
    @ApiModelProperty(value = "床号")
    private String brch;
    @ApiModelProperty(value = "科室 字典sys_dict_config:15")
    private Integer brks;
    @ApiModelProperty(value = "性质 sys_dict_config:14")
    private Integer brxz;
    @ApiModelProperty(value = "性别 DIC_GBSJ01:PD0000000269")
    private Integer brxb;
    @ApiModelProperty(value = "入院日期")
    private String ryrq;
    @ApiModelProperty(value = "1:住院 2：留观")
    private String ywlx;
    @ApiModelProperty(value = "出院判别 | 0：在院病人 1：出院证明 2：预结出院 8：正常出院 9：终结出院 99 注销出院")
    private Integer cypb;
    @ApiModelProperty(value = "开始日期 | 入院日期范围")
    private String ksrq;
    @ApiModelProperty(value = "结束日期 | 入院日期范围")
    private String jsrq;

    public PatientListReq() {
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

    public Integer getBrbq() {
        return brbq;
    }

    public void setBrbq(Integer brbq) {
        this.brbq = brbq;
    }

    public String getBrch() {
        return brch;
    }

    public void setBrch(String brch) {
        this.brch = brch;
    }

    public Integer getBrks() {
        return brks;
    }

    public void setBrks(Integer brks) {
        this.brks = brks;
    }

    public Integer getBrxz() {
        return brxz;
    }

    public void setBrxz(Integer brxz) {
        this.brxz = brxz;
    }

    public Integer getBrxb() {
        return brxb;
    }

    public void setBrxb(Integer brxb) {
        this.brxb = brxb;
    }

    public String getRyrq() {
        return ryrq;
    }

    public void setRyrq(String ryrq) {
        this.ryrq = ryrq;
    }

    public Integer getJgid() {
        return jgid;
    }

    public void setJgid(Integer jgid) {
        this.jgid = jgid;
    }

    public String getYwlx() {
        return ywlx;
    }

    public void setYwlx(String ywlx) {
        this.ywlx = ywlx;
    }

    public Integer getCypb() {
        return cypb;
    }

    public void setCypb(Integer cypb) {
        this.cypb = cypb;
    }

    public String getJzkh() {
        return jzkh;
    }

    public void setJzkh(String jzkh) {
        this.jzkh = jzkh;
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
}
