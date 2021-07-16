package com.buit.cis.ims.response;

import com.buit.commons.PageQuery;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.sql.Timestamp;


/**
 * 类名称：OpZydjPageResp<br>
 * 类描述：门诊住院登记分页查询返回<br>
 * @author zhouhaisheng
 */
@ApiModel(value="门诊住院登记分页查询返回")
public class OpZydjPageResp extends PageQuery {

    @ApiModelProperty(value="拟收治病区")
    private String nszbq;
    @ApiModelProperty(value="病人姓名")
    private String brxm;
    @ApiModelProperty(value="门诊医生")
    private String mzys;
    @ApiModelProperty(value="联系电话")
    private String lxdh;
    @ApiModelProperty(value="病人ID")
    private String brid;
    @ApiModelProperty(value="入院目的")
    private String rymd;
    @ApiModelProperty(value="登记类型")
    private String djlx;
    @ApiModelProperty(value="联系人姓名")
    private String lxrm;
    @ApiModelProperty(value="卡号")
    private String cardno;
    @ApiModelProperty(value="主键ID")
    private Integer djid;
    @ApiModelProperty(value="联系人地址")
    private String lxdz;
    @ApiModelProperty(value="开单日期")
    private Timestamp kdrq;
    @ApiModelProperty(value="门诊科室")
    private String mzks;
    @ApiModelProperty(value="身份证号")
    private String sfzh;
    @ApiModelProperty(value="病人性别")
    private int brxb;
    @ApiModelProperty(value="申请状态")
    private String sqzt;
    @ApiModelProperty(value="特需标志")
    private Integer txbz;
    @ApiModelProperty(value="病人年龄")
    private int brnl;
    @ApiModelProperty(value="门诊诊断")
    private String brzd;
    @ApiModelProperty(value="大病减负标志 1:尿毒症透析医疗费用,2:肾移植减负,3：精神病减负  其他：不减负")
    private String dbjfbz;

    public String getNszbq() {
        return nszbq;
    }

    public void setNszbq(String nszbq) {
        this.nszbq = nszbq;
    }

    public String getBrxm() {
        return brxm;
    }

    public void setBrxm(String brxm) {
        this.brxm = brxm;
    }

    public String getMzys() {
        return mzys;
    }

    public void setMzys(String mzys) {
        this.mzys = mzys;
    }

    public String getLxdh() {
        return lxdh;
    }

    public void setLxdh(String lxdh) {
        this.lxdh = lxdh;
    }

    public String getBrid() {
        return brid;
    }

    public void setBrid(String brid) {
        this.brid = brid;
    }

    public String getRymd() {
        return rymd;
    }

    public void setRymd(String rymd) {
        this.rymd = rymd;
    }

    public String getDjlx() {
        return djlx;
    }

    public void setDjlx(String djlx) {
        this.djlx = djlx;
    }

    public String getLxrm() {
        return lxrm;
    }

    public void setLxrm(String lxrm) {
        this.lxrm = lxrm;
    }

    public String getCardno() {
        return cardno;
    }

    public void setCardno(String cardno) {
        this.cardno = cardno;
    }

    public Integer getDjid() {
        return djid;
    }

    public void setDjid(Integer djid) {
        this.djid = djid;
    }

    public String getLxdz() {
        return lxdz;
    }

    public void setLxdz(String lxdz) {
        this.lxdz = lxdz;
    }

    public Timestamp getKdrq() {
        return kdrq;
    }

    public void setKdrq(Timestamp kdrq) {
        this.kdrq = kdrq;
    }

    public String getMzks() {
        return mzks;
    }

    public void setMzks(String mzks) {
        this.mzks = mzks;
    }

    public String getSfzh() {
        return sfzh;
    }

    public void setSfzh(String sfzh) {
        this.sfzh = sfzh;
    }

    public int getBrxb() {
        return brxb;
    }

    public void setBrxb(int brxb) {
        this.brxb = brxb;
    }

    public String getSqzt() {
        return sqzt;
    }

    public void setSqzt(String sqzt) {
        this.sqzt = sqzt;
    }

    public Integer getTxbz() {
        return txbz;
    }

    public void setTxbz(Integer txbz) {
        this.txbz = txbz;
    }

    public int getBrnl() {
        return brnl;
    }

    public void setBrnl(int brnl) {
        this.brnl = brnl;
    }

    public String getBrzd() {
        return brzd;
    }

    public void setBrzd(String brzd) {
        this.brzd = brzd;
    }

    public String getDbjfbz() {
        return dbjfbz;
    }

    public void setDbjfbz(String dbjfbz) {
        this.dbjfbz = dbjfbz;
    }
}
