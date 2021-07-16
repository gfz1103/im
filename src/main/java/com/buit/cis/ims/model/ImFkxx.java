package com.buit.cis.ims.model;

import com.buit.commons.PageQuery;

import java.math.BigDecimal;

/**
 * 类名称：ImFkxx<br>
 * 类描述：住院_结算付款信息<br>
 * @author zhouhaisheng
 */
public class ImFkxx  extends PageQuery {
    /** 记录序号 */
    private Integer jlxh;
    /** 住院号 */
    private Integer zyh;
    /** 结算次数 */
    private Integer jscs;
    /** 机构代码 */
    private Integer jgid;
    /** 付款方式 */
    private Integer fkfs;
    /** 付款金额 */
    private BigDecimal fkje;
    /** 付款号码 */
    private String fkhm;


    public void setJlxh(Integer value) {
        this.jlxh = value;
    }

    public Integer getJlxh() {
        return jlxh;
    }


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


    public void setJgid(Integer value) {
        this.jgid = value;
    }

    public Integer getJgid() {
        return jgid;
    }


    public void setFkfs(Integer value) {
        this.fkfs = value;
    }

    public Integer getFkfs() {
        return fkfs;
    }


    public void setFkje(BigDecimal value) {
        this.fkje = value;
    }

    public BigDecimal getFkje() {
        return fkje;
    }


    public void setFkhm(String value) {
        this.fkhm = value;
    }

    public String getFkhm() {
        return fkhm;
    }


}
