package com.buit.cis.ims.model;

import com.buit.commons.PageQuery;

import java.math.BigDecimal;

/**
 * 类名称：ImJsmx<br>
 * 类描述：住院_结算明细<br>
 * @author zhouhaisheng
 */
public class ImJsmx  extends PageQuery {
    /** 住院号 */
    private Integer zyh;
    /** 结算次数 */
    private Integer jscs;
    /** 科室代码 */
    private Integer ksdm;
    /** 费用项目 */
    private Integer fyxm;
    /** 机构代码 */
    private Integer jgid;
    /** 总结金额 */
    private BigDecimal zjje;
    /** 自负金额 */
    private BigDecimal zfje;
    /** 自理金额 */
    private BigDecimal zlje;
    /** scbz */
    private String scbz;
    /** 分类自负金额 */
    private BigDecimal flzf;
    /** 纯自负金额 */
    private BigDecimal czf;


    public void setZyh(Integer value) {
        this.zyh = value;
    }

    public Integer getZyh() {
        return zyh;
    }


    public void setJscs(Integer value) {
        this.jscs = value;
    }

    public Integer getJscs() {
        return jscs;
    }


    public void setKsdm(Integer value) {
        this.ksdm = value;
    }

    public Integer getKsdm() {
        return ksdm;
    }


    public void setFyxm(Integer value) {
        this.fyxm = value;
    }

    public Integer getFyxm() {
        return fyxm;
    }


    public void setJgid(Integer value) {
        this.jgid = value;
    }

    public Integer getJgid() {
        return jgid;
    }


    public void setZjje(BigDecimal value) {
        this.zjje = value;
    }

    public BigDecimal getZjje() {
        return zjje;
    }


    public void setZfje(BigDecimal value) {
        this.zfje = value;
    }

    public BigDecimal getZfje() {
        return zfje;
    }


    public void setZlje(BigDecimal value) {
        this.zlje = value;
    }

    public BigDecimal getZlje() {
        return zlje;
    }


    public void setScbz(String value) {
        this.scbz = value;
    }

    public String getScbz() {
        return scbz;
    }


    public void setFlzf(BigDecimal value) {
        this.flzf = value;
    }

    public BigDecimal getFlzf() {
        return flzf;
    }


    public void setCzf(BigDecimal value) {
        this.czf = value;
    }

    public BigDecimal getCzf() {
        return czf;
    }


}
