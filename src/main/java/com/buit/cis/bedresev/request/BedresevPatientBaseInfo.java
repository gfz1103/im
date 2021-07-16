package com.buit.cis.bedresev.request;

import com.buit.commons.PageQuery;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.sql.Timestamp;

/**
 * 床位预约病人基本信息
 * zhouhaisheng
 */
@ApiModel(value = "床位预约病人基本信息")
public class BedresevPatientBaseInfo extends PageQuery {

    @ApiModelProperty(value = "主键id")
    private Integer jlxh;

    @ApiModelProperty(value = "机构id",hidden = true)
    private Integer jgid;

    @ApiModelProperty(value = "卡号")
    private String cardNo;

    @ApiModelProperty(value = "病人姓名")
    private String brxm;

    @ApiModelProperty(value = "病人性别")
    private Integer brxb;
    @ApiModelProperty(value = "身份证号")
    private String idCard;
    @ApiModelProperty(value = "出生年月")
    private Timestamp csny;
    @ApiModelProperty(value = "病人性质")
    private Integer brxz;
    @ApiModelProperty(value = "联系电话")
    private String lxdh;

    /** 现住址_省区市 */
    @ApiModelProperty(value = "现住址_省区市")
    private Integer xzzSqs;
    /** 现住址_市 */
     @ApiModelProperty(value="现住址_市")
     private Integer xzzS;
     @ApiModelProperty(value="现住址_县")
    private Integer xzzX;

    @ApiModelProperty(value = "联系人姓名")
    private String lxrm;
    @ApiModelProperty(value = "预约科室")
    private Integer yyks;
    @ApiModelProperty(value = "预约住院日期")
    private Timestamp yyzyrq;
    @ApiModelProperty(value = "入院情况")
    private Integer ryqk;
    @ApiModelProperty(value = "门诊诊断")
    private String mzzd;
    @ApiModelProperty(value = "备注")
    private String bz;
    @ApiModelProperty(value = "病人病区")
    private Integer brbq;
    @ApiModelProperty(value = "病人床号")
    private String brch;
    @ApiModelProperty(value = "通知入院时间")
    private Timestamp rytzsj;
    @ApiModelProperty(value = "病人通知内容")
    private String notice;

    @ApiModelProperty(value = "待床状态 0:未待床 1:已待床 2:逾期")
    private String dczt;


    public Integer getJgid() {
        return jgid;
    }

    public void setJgid(Integer jgid) {
        this.jgid = jgid;
    }

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
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

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public Timestamp getCsny() {
        return csny;
    }

    public void setCsny(Timestamp csny) {
        this.csny = csny;
    }

    public Integer getBrxz() {
        return brxz;
    }

    public void setBrxz(Integer brxz) {
        this.brxz = brxz;
    }

    public String getLxdh() {
        return lxdh;
    }

    public void setLxdh(String lxdh) {
        this.lxdh = lxdh;
    }

    public Integer getXzzSqs() {
        return xzzSqs;
    }

    public void setXzzSqs(Integer xzzSqs) {
        this.xzzSqs = xzzSqs;
    }

    public Integer getXzzS() {
        return xzzS;
    }

    public void setXzzS(Integer xzzS) {
        this.xzzS = xzzS;
    }

    public Integer getXzzX() {
        return xzzX;
    }

    public void setXzzX(Integer xzzX) {
        this.xzzX = xzzX;
    }

    public String getLxrm() {
        return lxrm;
    }

    public void setLxrm(String lxrm) {
        this.lxrm = lxrm;
    }

    public Integer getYyks() {
        return yyks;
    }

    public void setYyks(Integer yyks) {
        this.yyks = yyks;
    }

    public Timestamp getYyzyrq() {
        return yyzyrq;
    }

    public void setYyzyrq(Timestamp yyzyrq) {
        this.yyzyrq = yyzyrq;
    }

    public Integer getRyqk() {
        return ryqk;
    }

    public void setRyqk(Integer ryqk) {
        this.ryqk = ryqk;
    }

    public String getMzzd() {
        return mzzd;
    }

    public void setMzzd(String mzzd) {
        this.mzzd = mzzd;
    }

    public String getBz() {
        return bz;
    }

    public void setBz(String bz) {
        this.bz = bz;
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

    public Timestamp getRytzsj() {
        return rytzsj;
    }

    public void setRytzsj(Timestamp rytzsj) {
        this.rytzsj = rytzsj;
    }

    public String getNotice() {
        return notice;
    }

    public void setNotice(String notice) {
        this.notice = notice;
    }

    public String getDczt() {
        return dczt;
    }

    public void setDczt(String dczt) {
        this.dczt = dczt;
    }

    public Integer getJlxh() {
        return jlxh;
    }

    public void setJlxh(Integer jlxh) {
        this.jlxh = jlxh;
    }
}
