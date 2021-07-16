package com.buit.cis.ims.response;

import com.buit.commons.PageQuery;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;
import java.sql.Timestamp;


/**
 * 类名称：DbxxResp<br>
 * 类描述：大病登记打印<br>
 * @author  卑军华
 */
@ApiModel(value="大病打印")
public class DbxxResp extends PageQuery {
    @JsonIgnore
    @ApiModelProperty(value="机构编码")
    private Integer jgid;
    @ApiModelProperty(value="姓名")
    private String personname;
    @ApiModelProperty(value="卡号")
    private String cardid;
    @ApiModelProperty(value="身份证号码")
    private String sfzh;
    @ApiModelProperty(value="联系电话")
    private String lxdh;
    @ApiModelProperty(value="联系地址")
    private String lxdz;
    @ApiModelProperty(value="委托人姓名")
    private String wtrxm;
    @ApiModelProperty(value="委托人身份证")
    private String wtrsfzh;
    @ApiModelProperty(value="人员属性")
    private String rysx;
    @ApiModelProperty(value="工作情况")
    private String gzqk;
    @ApiModelProperty(value="转出医院名称")
    private String zcyymc;
    @ApiModelProperty(value="登记有效期")
    private String djyxq;
    @ApiModelProperty(value="当前时间")
    private String dqsj;

    public Integer getJgid() {
        return jgid;
    }

    public void setJgid(Integer jgid) {
        this.jgid = jgid;
    }

    public String getPersonname() {
        return personname;
    }

    public void setPersonname(String personname) {
        this.personname = personname;
    }

    public String getCardid() {
        return cardid;
    }

    public void setCardid(String cardid) {
        this.cardid = cardid;
    }

    public String getSfzh() {
        return sfzh;
    }

    public void setSfzh(String sfzh) {
        this.sfzh = sfzh;
    }

    public String getLxdh() {
        return lxdh;
    }

    public void setLxdh(String lxdh) {
        this.lxdh = lxdh;
    }

    public String getLxdz() {
        return lxdz;
    }

    public void setLxdz(String lxdz) {
        this.lxdz = lxdz;
    }

    public String getWtrxm() {
        return wtrxm;
    }

    public void setWtrxm(String wtrxm) {
        this.wtrxm = wtrxm;
    }

    public String getWtrsfzh() {
        return wtrsfzh;
    }

    public void setWtrsfzh(String wtrsfzh) {
        this.wtrsfzh = wtrsfzh;
    }

    public String getRysx() {
        return rysx;
    }

    public void setRysx(String rysx) {
        this.rysx = rysx;
    }

    public String getGzqk() {
        return gzqk;
    }

    public void setGzqk(String gzqk) {
        this.gzqk = gzqk;
    }

    public String getZcyymc() {
        return zcyymc;
    }

    public void setZcyymc(String zcyymc) {
        this.zcyymc = zcyymc;
    }

    public String getDjyxq() {
        return djyxq;
    }

    public void setDjyxq(String djyxq) {
        this.djyxq = djyxq;
    }

    public String getDqsj() {
        return dqsj;
    }

    public void setDqsj(String dqsj) {
        this.dqsj = dqsj;
    }
}
