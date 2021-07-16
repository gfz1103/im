package com.buit.cis.ims.response;

import com.buit.commons.PageQuery;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * 类名称：住院病人床位设置分页查询<br>
 * 类描述：住院_病人入院<br>
 * @author ZHOUHAISHENG
 */
@ApiModel(value="住院病人床位设置分页查询出参")
public class ZybrCwszResp extends PageQuery {
    @ApiModelProperty(value="机构id")
    private Integer jgid;
    @ApiModelProperty(value="病人床号")
    private String brch;
    @ApiModelProperty(value="房间号码")
    private String fjhm;
    @ApiModelProperty(value="床位科室 字典sys_dict_config:15")
    private Integer cwks;
    @ApiModelProperty(value="科室代码 字典sys_dict_config:15")
    private Integer ksdm;
    @ApiModelProperty(value="床位性别 DIC_GBSJ01:PD0000000269")
    private Integer cwxb;
    @ApiModelProperty(value="床位费用")
    private BigDecimal cwfy;
    @ApiModelProperty(value="vip床位费用")
    private BigDecimal vipcwfy;
    @ApiModelProperty(value="加床判别 | 0.普通床 1.加床 2.虚床")
    private Integer jcpb;
    @ApiModelProperty(value="住院号")
    private Integer zyh;
    @ApiModelProperty(value="住院号码")
    private String zyhm;
    @ApiModelProperty(value="病人姓名")
    private String brxm;
    @ApiModelProperty(value="病人性别 DIC_GBSJ01:PD0000000269")
    private Integer brxb;
    @ApiModelProperty(value="病人性质 sys_dict_config:14")
    private Integer brxz;
    @ApiModelProperty(value="病人科室 字典sys_dict_config:15")
    private Integer brks;
    @ApiModelProperty(value="病人病区 字典:sys_dict_config:18")
    private Integer brbq;
    @ApiModelProperty(value="入院日期")
    private Timestamp ryrq;
    @ApiModelProperty(value="出院判别 | 0：在院病人 1：出院证明 2：预结出院 8：正常出院 9：终结出院 99 注销出院  字典DIC_GBSJ01:PD0000000728")
    private Integer cypb;

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

    public BigDecimal getCwfy() {
        return cwfy;
    }

    public void setCwfy(BigDecimal cwfy) {
        this.cwfy = cwfy;
    }

    public BigDecimal getVipcwfy() {
        return vipcwfy;
    }

    public void setVipcwfy(BigDecimal vipcwfy) {
        this.vipcwfy = vipcwfy;
    }

    public Integer getJcpb() {
        return jcpb;
    }

    public void setJcpb(Integer jcpb) {
        this.jcpb = jcpb;
    }

    public Integer getZyh() {
        return zyh;
    }

    public void setZyh(Integer zyh) {
        this.zyh = zyh;
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

    public Integer getBrxb() {
        return brxb;
    }

    public void setBrxb(Integer brxb) {
        this.brxb = brxb;
    }

    public Integer getBrxz() {
        return brxz;
    }

    public void setBrxz(Integer brxz) {
        this.brxz = brxz;
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

    public Timestamp getRyrq() {
        return ryrq;
    }

    public void setRyrq(Timestamp ryrq) {
        this.ryrq = ryrq;
    }

    public Integer getCypb() {
        return cypb;
    }

    public void setCypb(Integer cypb) {
        this.cypb = cypb;
    }
}
