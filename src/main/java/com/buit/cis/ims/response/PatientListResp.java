package com.buit.cis.ims.response;

import com.buit.commons.PageQuery;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.sql.Timestamp;

/**
 *
 * 类描述：<br>
 *     病人管理列表出参
 * @author zhouhaisheng
 */
@ApiModel(value="病人管理列表出参")
public class PatientListResp extends PageQuery {
    @ApiModelProperty(value = "姓名")
    private String brxm;
    @ApiModelProperty(value = "病区")
    private Integer brbq;
    @ApiModelProperty(value = "住院号码")
    private String zyhm;
    @ApiModelProperty(value = "备注描述")
    private String cypbText;
    @ApiModelProperty(value = "病人性质描述")
    private String brxzText;
    @ApiModelProperty(value = "入院诊断描述")
    private String ryzdText;
    @ApiModelProperty(value = "床号")
    private String brch;
    @ApiModelProperty(value = "住院号")
    private Integer zyh;
    @ApiModelProperty(value = "未结算天数")
    private Integer wjsts;
    @ApiModelProperty(value = "病人性别描述")
    private String brxbText;
    @ApiModelProperty(value = "工作单位")
    private String gzdw2;
    @ApiModelProperty(value = "出生年月")
    private String csny;
    @ApiModelProperty(value = "床号")
    private Integer cwfp;
    @ApiModelProperty(value = "科室")
    private Integer brks;
    @ApiModelProperty(value = "性质")
    private Integer brxz;
    @ApiModelProperty(value = "病人科室")
    private String brksText;
    @ApiModelProperty(value = "身份证号")
    private String sfzh;
    @ApiModelProperty(value = "性别")
    private Integer brxb;
    @ApiModelProperty(value = "病人病区")
    private String brbqText;
    @ApiModelProperty(value = "入院诊断")
    private String ryzd;
    @ApiModelProperty(value = "备注 出院判别 | 0：在院病人 1：出院证明 2：预结出院 8：正常出院 9：终结出院 99 注销出院  字典DIC_GBSJ01:PD0000000728")
    private Integer cypb;
    @ApiModelProperty(value = "入院日期")
    private Timestamp ryrq;
    @ApiModelProperty(value = "结算次数")
    private Integer jscs = 0;
    @ApiModelProperty(value = "出院日期", hidden = true)
    private Timestamp cyrq;
    @ApiModelProperty(value = "住院天数")
    private Double zyts;
    @ApiModelProperty(value = "结算日期")
    private Timestamp jsrq;
    @ApiModelProperty(value = "就诊卡号")
    private String jzkh;
    @ApiModelProperty(value = "是否医保")
    private String isyb;


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

    public String getZyhm() {
        return zyhm;
    }

    public void setZyhm(String zyhm) {
        this.zyhm = zyhm;
    }

    public String getCypbText() {
        return cypbText;
    }

    public void setCypbText(String cypbText) {
        this.cypbText = cypbText;
    }

    public String getBrxzText() {
        return brxzText;
    }

    public void setBrxzText(String brxzText) {
        this.brxzText = brxzText;
    }

    public String getRyzdText() {
        return ryzdText;
    }

    public void setRyzdText(String ryzdText) {
        this.ryzdText = ryzdText;
    }

    public String getBrch() {
        return brch;
    }

    public void setBrch(String brch) {
        this.brch = brch;
    }

    public Integer getZyh() {
        return zyh;
    }

    public void setZyh(Integer zyh) {
        this.zyh = zyh;
    }

    public Integer getWjsts() {
        return wjsts;
    }

    public void setWjsts(Integer wjsts) {
        this.wjsts = wjsts;
    }

    public String getBrxbText() {
        return brxbText;
    }

    public void setBrxbText(String brxbText) {
        this.brxbText = brxbText;
    }

    public String getGzdw2() {
        return gzdw2;
    }

    public void setGzdw2(String gzdw2) {
        this.gzdw2 = gzdw2;
    }

    public String getCsny() {
        return csny;
    }

    public void setCsny(String csny) {
        this.csny = csny;
    }

    public Integer getCwfp() {
        return cwfp;
    }

    public void setCwfp(Integer cwfp) {
        this.cwfp = cwfp;
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

    public String getBrksText() {
        return brksText;
    }

    public void setBrksText(String brksText) {
        this.brksText = brksText;
    }

    public String getSfzh() {
        return sfzh;
    }

    public void setSfzh(String sfzh) {
        this.sfzh = sfzh;
    }

    public Integer getBrxb() {
        return brxb;
    }

    public void setBrxb(Integer brxb) {
        this.brxb = brxb;
    }

    public String getBrbqText() {
        return brbqText;
    }

    public void setBrbqText(String brbqText) {
        this.brbqText = brbqText;
    }

    public String getRyzd() {
        return ryzd;
    }

    public void setRyzd(String ryzd) {
        this.ryzd = ryzd;
    }

    public Integer getCypb() {
        return cypb;
    }

    public void setCypb(Integer cypb) {
        this.cypb = cypb;
    }

    public Timestamp getRyrq() {
        return ryrq;
    }

    public void setRyrq(Timestamp ryrq) {
        this.ryrq = ryrq;
    }

    public Integer getJscs() {
        return jscs;
    }

    public void setJscs(Integer jscs) {
        this.jscs = jscs;
    }

    public Timestamp getCyrq() {
        return cyrq;
    }

    public void setCyrq(Timestamp cyrq) {
        this.cyrq = cyrq;
    }

    public Timestamp getJsrq() {
        return jsrq;
    }

    public void setJsrq(Timestamp jsrq) {
        this.jsrq = jsrq;
    }

    public Double getZyts() {
        return zyts;
    }

    public void setZyts(Double zyts) {
        this.zyts = zyts;
    }

    public String getJzkh() {
        return jzkh;
    }

    public void setJzkh(String jzkh) {
        this.jzkh = jzkh;
    }

    public String getIsyb() {
        return isyb;
    }

    public void setIsyb(String isyb) {
        this.isyb = isyb;
    }
}
