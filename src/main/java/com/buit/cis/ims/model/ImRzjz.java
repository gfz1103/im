package com.buit.cis.ims.model;

import com.buit.commons.PageQuery;

import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * 类名称：ImRzjz<br>
 * 类描述：住院_日终结账信息
 * @author songyu
 * @ApiModel(value="住院_日账终结信息")
 */
public class ImRzjz extends PageQuery {
    //@ApiModelProperty(value="结账日期 ")
    /** 结账日期 */
    private Timestamp jzrq;
    //@ApiModelProperty(value="操作工号")
    /** 操作工号 */
    private String czgh;
    //@ApiModelProperty(value="机构代码")
    /** 机构代码 */
    private String jgid;
    //@ApiModelProperty(value="总收预缴金额")
    /** 总收预缴金额 */
    private BigDecimal yjje;
    //@ApiModelProperty(value="实收预缴金额")
    /** 实收预缴金额 */
    private BigDecimal ssyjje;
    //@ApiModelProperty(value="预缴金找退")
    /** 预缴金找退 */
    private BigDecimal tkyjje;
    //@ApiModelProperty(value="结算收现金金额")
    /** 结算收现金金额 */
    private BigDecimal jsxjje;
    //@ApiModelProperty(value="结算收支票金额")
    /** 结算收支票金额 */
    private BigDecimal jszpje;
    //@ApiModelProperty(value="结算收POS金额")
    /** 结算收POS金额 */
    private BigDecimal jsposje;
    //@ApiModelProperty(value="舍入金额(货币误差)")
    /** 舍入金额(货币误差) */
    private BigDecimal srje;
    //@ApiModelProperty(value="应收金额")
    /** 应收金额 */
    private BigDecimal ysje;
    //@ApiModelProperty(value="欠费金额")
    /** 欠费金额 */
    private BigDecimal qfje;
    //@ApiModelProperty(value="优惠金额")
    /** 优惠金额 */
    private BigDecimal yhje;
    //@ApiModelProperty(value="记账总额")
    /** 记账总额 */
    private BigDecimal jzze;
    //@ApiModelProperty(value="医保记账")
    /** 医保记账 */
    private BigDecimal ybjz;
    //@ApiModelProperty(value="商保记账")
    /** 商保记账 */
    private BigDecimal sbjz;
    //@ApiModelProperty(value="其他记账")
    /** 其他记账 */
    private BigDecimal qtjz;
    //@ApiModelProperty(value="收预交金现金")
    /** 收预交金现金 */
    private BigDecimal syjjxj;
    //@ApiModelProperty(value="收预交金支票")
    /** 收预交金支票 */
    private BigDecimal syjjzp;
    //@ApiModelProperty(value="收预交金POS")
    /** 收预交金POS */
    private BigDecimal syjjpos;
    //@ApiModelProperty(value="退预交金现金")
    /** 退预交金现金 */
    private BigDecimal tyjjxj;
    //@ApiModelProperty(value="退预交金支票")
    /** 退预交金支票 */
    private BigDecimal tyjjzp;
    //@ApiModelProperty(value="退预交金POS")
    /** 退预交金POS */
    private BigDecimal tyjjpos;
    //@ApiModelProperty(value="汇总日期")
    /** 汇总日期 */
    private Timestamp hzrq;

    private BigDecimal jswxje;

    private BigDecimal jszfbje;

    private BigDecimal jsgzje;

    private BigDecimal jszzje;

    private BigDecimal syjjwx;

    private BigDecimal syjjzfb;

    private BigDecimal syjjewm;

    private BigDecimal syjjzz;

    private BigDecimal tyjjwx;

    private BigDecimal tyjjzfb;

    private BigDecimal tyjjewm;

    private BigDecimal tyjjzz;

    public BigDecimal getJswxje() {
        return jswxje;
    }

    public void setJswxje(BigDecimal jswxje) {
        this.jswxje = jswxje;
    }

    public BigDecimal getJszfbje() {
        return jszfbje;
    }

    public void setJszfbje(BigDecimal jszfbje) {
        this.jszfbje = jszfbje;
    }

    public BigDecimal getJsgzje() {
        return jsgzje;
    }

    public void setJsgzje(BigDecimal jsgzje) {
        this.jsgzje = jsgzje;
    }

    public BigDecimal getJszzje() {
        return jszzje;
    }

    public void setJszzje(BigDecimal jszzje) {
        this.jszzje = jszzje;
    }

    public BigDecimal getSyjjwx() {
        return syjjwx;
    }

    public void setSyjjwx(BigDecimal syjjwx) {
        this.syjjwx = syjjwx;
    }

    public BigDecimal getSyjjzfb() {
        return syjjzfb;
    }

    public void setSyjjzfb(BigDecimal syjjzfb) {
        this.syjjzfb = syjjzfb;
    }

    public BigDecimal getSyjjewm() {
        return syjjewm;
    }

    public void setSyjjewm(BigDecimal syjjewm) {
        this.syjjewm = syjjewm;
    }

    public BigDecimal getSyjjzz() {
        return syjjzz;
    }

    public void setSyjjzz(BigDecimal syjjzz) {
        this.syjjzz = syjjzz;
    }

    public BigDecimal getTyjjwx() {
        return tyjjwx;
    }

    public void setTyjjwx(BigDecimal tyjjwx) {
        this.tyjjwx = tyjjwx;
    }

    public BigDecimal getTyjjzfb() {
        return tyjjzfb;
    }

    public void setTyjjzfb(BigDecimal tyjjzfb) {
        this.tyjjzfb = tyjjzfb;
    }

    public BigDecimal getTyjjewm() {
        return tyjjewm;
    }

    public void setTyjjewm(BigDecimal tyjjewm) {
        this.tyjjewm = tyjjewm;
    }

    public BigDecimal getTyjjzz() {
        return tyjjzz;
    }

    public void setTyjjzz(BigDecimal tyjjzz) {
        this.tyjjzz = tyjjzz;
    }

    public BigDecimal getYsje() {
        return ysje;
    }

    public void setYsje(BigDecimal ysje) {
        this.ysje = ysje;
    }

    /** 设置:结算日期 | 权责发生制所有收款员日报汇总日期  */
    public void setJzrq(Timestamp value) {
        this.jzrq = value;
    }
    /** 获取:结算日期 | 权责发生制所有收款员日报汇总日期 */
    public Timestamp getJzrq() {
        return jzrq;
    }

    /** 设置:操作工号  */
    public void setCzgh(String value) {
        this.czgh = value;
    }
    /** 获取:操作工号 */
    public String getCzgh() {
        return czgh;
    }

    /** 设置:机构代码  */
    public void setJgid(String value) {
        this.jgid = value;
    }
    /** 获取:机构代码 */
    public String getJgid() {
        return jgid;
    }

    /** 设置: 总收预缴金额  */
    public void setYjje(BigDecimal value) {
        this.yjje = value;
    }
    /** 获取: 总收预缴金额 */
    public BigDecimal getYjje() {
        return yjje;
    }

    /** 设置: 实收预缴金额  */
    public void setSsyjje(BigDecimal value) {
        this.ssyjje = value;
    }
    /** 获取: 实收预缴金额 */
    public BigDecimal getSsyjje() {
        return ssyjje;
    }

    /** 设置:预缴金找退  */
    public void setTkyjje(BigDecimal value) {
        this.tkyjje = value;
    }
    /** 获取:预缴金找退 */
    public BigDecimal getTkyjje() {
        return tkyjje;
    }

    /** 设置:结算收现金金额  */
    public void setJsxjje(BigDecimal value) {
        this.jsxjje = value;
    }
    /** 获取:结算收现金金额 */
    public BigDecimal getJsxjje() {
        return jsxjje;
    }

    /** 设置:结算收支票金额  */
    public void setJszpje(BigDecimal value) {
        this.jszpje = value;
    }
    /** 获取:结算收支票金额 */
    public BigDecimal getJszpje() {
        return jszpje;
    }

    /** 设置:结算收POS金额  */
    public void setJsposje(BigDecimal value) {
        this.jsposje = value;
    }
    /** 获取:结算收POS金额 */
    public BigDecimal getJsposje() {
        return jsposje;
    }

    /** 设置:舍入金额(货币误差)  */
    public void setSrje(BigDecimal value) {
        this.srje = value;
    }
    /** 获取:舍入金额(货币误差) */
    public BigDecimal getSrje() {
        return srje;
    }

    /** 设置:欠费金额  */
    public void setQfje(BigDecimal value) {
        this.qfje = value;
    }
    /** 获取:欠费金额 */
    public BigDecimal getQfje() {
        return qfje;
    }

    /** 设置:优惠金额  */
    public void setYhje(BigDecimal value) {
        this.yhje = value;
    }
    /** 获取:优惠金额 */
    public BigDecimal getYhje() {
        return yhje;
    }

    /** 设置:记账总额  */
    public void setJzze(BigDecimal value) {
        this.jzze = value;
    }
    /** 获取:记账总额 */
    public BigDecimal getJzze() {
        return jzze;
    }

    /** 设置:医保记账  */
    public void setYbjz(BigDecimal value) {
        this.ybjz = value;
    }
    /** 获取:医保记账 */
    public BigDecimal getYbjz() {
        return ybjz;
    }

    /** 设置:商保记账 */
    public void setSbjz(BigDecimal value) {
        this.sbjz = value;
    }
    /** 获取:商保记账 */
    public BigDecimal getSbjz() {
        return sbjz;
    }

    /** 设置:其他记账  */
    public void setQtjz(BigDecimal value) {
        this.qtjz = value;
    }
    /** 获取:其他记账 */
    public BigDecimal getQtjz() {
        return qtjz;
    }

    /** 设置:收预交金现金  */
    public void setSyjjxj(BigDecimal value) {
        this.syjjxj = value;
    }
    /** 获取:收预交金现金 */
    public BigDecimal getSyjjxj() {
        return syjjxj;
    }

    /** 设置:收预交金支票  */
    public void setSyjjzp(BigDecimal value) {
        this.syjjzp = value;
    }
    /** 获取:收预交金支票 */
    public BigDecimal getSyjjzp() {
        return syjjzp;
    }

    /** 设置:收预交金POS  */
    public void setSyjjpos(BigDecimal value) {
        this.syjjpos = value;
    }
    /** 获取:收预交金POS */
    public BigDecimal getSyjjpos() {
        return syjjpos;
    }

    /** 设置:退预交金现金  */
    public void setTyjjxj(BigDecimal value) {
        this.tyjjxj = value;
    }
    /** 获取:退预交金现金 */
    public BigDecimal getTyjjxj() {
        return tyjjxj;
    }

    /** 设置:退预交金支票  */
    public void setTyjjzp(BigDecimal value) {
        this.tyjjzp = value;
    }
    /** 获取:退预交金支票 */
    public BigDecimal getTyjjzp() {
        return tyjjzp;
    }

    /** 设置:退预交金POS  */
    public void setTyjjpos(BigDecimal value) {
        this.tyjjpos = value;
    }
    /** 获取:退预交金POS */
    public BigDecimal getTyjjpos() {
        return tyjjpos;
    }

    /** 设置:其他记账  */
    public void setHzrq(Timestamp value) {
        this.hzrq = value;
    }
    /** 获取:其他记账 */
    public Timestamp getHzrq() {
        return hzrq;
    }



}
