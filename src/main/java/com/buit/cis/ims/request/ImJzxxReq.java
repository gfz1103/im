package com.buit.cis.ims.request;

import com.buit.commons.PageQuery;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;
import java.sql.Timestamp;


/**
 * 类名称：ImJzxx<br>
 * 类描述：住院_收款结帐信息<br>
 * @author ZHOUHAISHENG
 */
@ApiModel(value="住院_收款结帐信息")
public class ImJzxxReq  extends PageQuery {
    @ApiModelProperty(value="结算日期 | 权责发生制所有收款员日报汇总日期")
    private Timestamp jzrq;
    @ApiModelProperty(value="操作工号")
    private String czgh;
    @ApiModelProperty(value="机构代码")
    private String jgid;
    @ApiModelProperty(value="出院收入")
    private BigDecimal cysr;
    @ApiModelProperty(value="预交金额")
    private BigDecimal yjje;
    @ApiModelProperty(value="预交现金")
    private BigDecimal yjxj;
    @ApiModelProperty(value="预交支票")
    private BigDecimal yjzp;
    @ApiModelProperty(value="退票金额")
    private BigDecimal tpje;
    @ApiModelProperty(value="发票张数")
    private Integer fpzs;
    @ApiModelProperty(value="收据张数")
    private Integer sjzs;
    @ApiModelProperty(value="应收金额")
    private BigDecimal ysje;
    @ApiModelProperty(value="应收现金")
    private BigDecimal ysxj;
    @ApiModelProperty(value="应收支票")
    private BigDecimal yszp;
    @ApiModelProperty(value="支票张数")
    private Integer zpzs;
    @ApiModelProperty(value="退预交金 | 同IM_JZXX.YSJE")
    private BigDecimal tyjj;
    @ApiModelProperty(value="退预缴款张数")
    private Integer tjks;
    @ApiModelProperty(value="空白支票金额")
    private BigDecimal kbje;
    @ApiModelProperty(value="空白支票张数")
    private Integer kbzp;
    @ApiModelProperty(value="汇总日期")
    private Timestamp hzrq;
    @ApiModelProperty(value="预缴其他")
    private BigDecimal yjqt;
    @ApiModelProperty(value="应收其他")
    private BigDecimal ysqt;
    @ApiModelProperty(value="其他票据张数")
    private Integer qtzs;
    @ApiModelProperty(value="舍入金额 | 同IM_ZYJS.SRJE")
    private BigDecimal srje;
    @ApiModelProperty(value="预缴银行卡")
    private BigDecimal yjyhk;
    @ApiModelProperty(value="应收银行卡")
    private BigDecimal ysyhk;
    @ApiModelProperty(value="应收优惠")
    private BigDecimal ysyh;
    @ApiModelProperty(value="起至票据")
    private String qzpj;
    @ApiModelProperty(value="起至收据")
    private String qzsj;
    @ApiModelProperty(value="zxjzfy")
    private Integer zxjzfy;
    @ApiModelProperty(value="grxjzf")
    private Integer grxjzf;
    @ApiModelProperty(value="bczhzf")
    private Integer bczhzf;
    @ApiModelProperty(value="tczc")
    private Integer tczc;
    @ApiModelProperty(value="dbzc")
    private Integer dbzc;
    @ApiModelProperty(value="azqgfy")
    private Integer azqgfy;
    @ApiModelProperty(value="收费类型统计")
    private String qtysfb;
    /**
     * 设置:结算日期 | 权责发生制所有收款员日报汇总日期
     */
    public void setJzrq(Timestamp value) {
        this.jzrq = value;
    }
    /**
     * 获取:结算日期 | 权责发生制所有收款员日报汇总日期
     */
    public Timestamp getJzrq() {
        return jzrq;
    }
    /**
     * 设置:操作工号
     */
    public void setCzgh(String value) {
        this.czgh = value;
    }
    /**
     * 获取:操作工号
     */
    public String getCzgh() {
        return czgh;
    }
    /**
     * 设置:机构代码
     */
    public void setJgid(String value) {
        this.jgid = value;
    }
    /**
     * 获取:机构代码
     */
    public String getJgid() {
        return jgid;
    }
    /**
     * 设置:出院收入
     */
    public void setCysr(BigDecimal value) {
        this.cysr = value;
    }
    /**
     * 获取:出院收入
     */
    public BigDecimal getCysr() {
        return cysr;
    }
    /**
     * 设置:预交金额
     */
    public void setYjje(BigDecimal value) {
        this.yjje = value;
    }
    /**
     * 获取:预交金额
     */
    public BigDecimal getYjje() {
        return yjje;
    }
    /**
     * 设置:预交现金
     */
    public void setYjxj(BigDecimal value) {
        this.yjxj = value;
    }
    /**
     * 获取:预交现金
     */
    public BigDecimal getYjxj() {
        return yjxj;
    }
    /**
     * 设置:预交支票
     */
    public void setYjzp(BigDecimal value) {
        this.yjzp = value;
    }
    /**
     * 获取:预交支票
     */
    public BigDecimal getYjzp() {
        return yjzp;
    }
    /**
     * 设置:退票金额
     */
    public void setTpje(BigDecimal value) {
        this.tpje = value;
    }
    /**
     * 获取:退票金额
     */
    public BigDecimal getTpje() {
        return tpje;
    }
    /**
     * 设置:发票张数
     */
    public void setFpzs(Integer value) {
        this.fpzs = value;
    }
    /**
     * 获取:发票张数
     */
    public Integer getFpzs() {
        return fpzs;
    }
    /**
     * 设置:收据张数
     */
    public void setSjzs(Integer value) {
        this.sjzs = value;
    }
    /**
     * 获取:收据张数
     */
    public Integer getSjzs() {
        return sjzs;
    }
    /**
     * 设置:应收金额
     */
    public void setYsje(BigDecimal value) {
        this.ysje = value;
    }
    /**
     * 获取:应收金额
     */
    public BigDecimal getYsje() {
        return ysje;
    }
    /**
     * 设置:应收现金
     */
    public void setYsxj(BigDecimal value) {
        this.ysxj = value;
    }
    /**
     * 获取:应收现金
     */
    public BigDecimal getYsxj() {
        return ysxj;
    }
    /**
     * 设置:应收支票
     */
    public void setYszp(BigDecimal value) {
        this.yszp = value;
    }
    /**
     * 获取:应收支票
     */
    public BigDecimal getYszp() {
        return yszp;
    }
    /**
     * 设置:支票张数
     */
    public void setZpzs(Integer value) {
        this.zpzs = value;
    }
    /**
     * 获取:支票张数
     */
    public Integer getZpzs() {
        return zpzs;
    }
    /**
     * 设置:退预交金 | 同IM_JZXX.YSJE
     */
    public void setTyjj(BigDecimal value) {
        this.tyjj = value;
    }
    /**
     * 获取:退预交金 | 同IM_JZXX.YSJE
     */
    public BigDecimal getTyjj() {
        return tyjj;
    }
    /**
     * 设置:退预缴款张数
     */
    public void setTjks(Integer value) {
        this.tjks = value;
    }
    /**
     * 获取:退预缴款张数
     */
    public Integer getTjks() {
        return tjks;
    }
    /**
     * 设置:空白支票金额
     */
    public void setKbje(BigDecimal value) {
        this.kbje = value;
    }
    /**
     * 获取:空白支票金额
     */
    public BigDecimal getKbje() {
        return kbje;
    }
    /**
     * 设置:空白支票张数
     */
    public void setKbzp(Integer value) {
        this.kbzp = value;
    }
    /**
     * 获取:空白支票张数
     */
    public Integer getKbzp() {
        return kbzp;
    }
    /**
     * 设置:汇总日期
     */
    public void setHzrq(Timestamp value) {
        this.hzrq = value;
    }
    /**
     * 获取:汇总日期
     */
    public Timestamp getHzrq() {
        return hzrq;
    }
    /**
     * 设置:预缴其他
     */
    public void setYjqt(BigDecimal value) {
        this.yjqt = value;
    }
    /**
     * 获取:预缴其他
     */
    public BigDecimal getYjqt() {
        return yjqt;
    }
    /**
     * 设置:应收其他
     */
    public void setYsqt(BigDecimal value) {
        this.ysqt = value;
    }
    /**
     * 获取:应收其他
     */
    public BigDecimal getYsqt() {
        return ysqt;
    }
    /**
     * 设置:其他票据张数
     */
    public void setQtzs(Integer value) {
        this.qtzs = value;
    }
    /**
     * 获取:其他票据张数
     */
    public Integer getQtzs() {
        return qtzs;
    }
    /**
     * 设置:舍入金额 | 同IM_ZYJS.SRJE
     */
    public void setSrje(BigDecimal value) {
        this.srje = value;
    }
    /**
     * 获取:舍入金额 | 同IM_ZYJS.SRJE
     */
    public BigDecimal getSrje() {
        return srje;
    }
    /**
     * 设置:预缴银行卡
     */
    public void setYjyhk(BigDecimal value) {
        this.yjyhk = value;
    }
    /**
     * 获取:预缴银行卡
     */
    public BigDecimal getYjyhk() {
        return yjyhk;
    }
    /**
     * 设置:应收银行卡
     */
    public void setYsyhk(BigDecimal value) {
        this.ysyhk = value;
    }
    /**
     * 获取:应收银行卡
     */
    public BigDecimal getYsyhk() {
        return ysyhk;
    }
    /**
     * 设置:应收优惠
     */
    public void setYsyh(BigDecimal value) {
        this.ysyh = value;
    }
    /**
     * 获取:应收优惠
     */
    public BigDecimal getYsyh() {
        return ysyh;
    }
    /**
     * 设置:起至票据
     */
    public void setQzpj(String value) {
        this.qzpj = value;
    }
    /**
     * 获取:起至票据
     */
    public String getQzpj() {
        return qzpj;
    }
    /**
     * 设置:起至收据
     */
    public void setQzsj(String value) {
        this.qzsj = value;
    }
    /**
     * 获取:起至收据
     */
    public String getQzsj() {
        return qzsj;
    }
    /**
     * 设置:zxjzfy
     */
    public void setZxjzfy(Integer value) {
        this.zxjzfy = value;
    }
    /**
     * 获取:zxjzfy
     */
    public Integer getZxjzfy() {
        return zxjzfy;
    }
    /**
     * 设置:grxjzf
     */
    public void setGrxjzf(Integer value) {
        this.grxjzf = value;
    }
    /**
     * 获取:grxjzf
     */
    public Integer getGrxjzf() {
        return grxjzf;
    }
    /**
     * 设置:bczhzf
     */
    public void setBczhzf(Integer value) {
        this.bczhzf = value;
    }
    /**
     * 获取:bczhzf
     */
    public Integer getBczhzf() {
        return bczhzf;
    }
    /**
     * 设置:tczc
     */
    public void setTczc(Integer value) {
        this.tczc = value;
    }
    /**
     * 获取:tczc
     */
    public Integer getTczc() {
        return tczc;
    }
    /**
     * 设置:dbzc
     */
    public void setDbzc(Integer value) {
        this.dbzc = value;
    }
    /**
     * 获取:dbzc
     */
    public Integer getDbzc() {
        return dbzc;
    }
    /**
     * 设置:azqgfy
     */
    public void setAzqgfy(Integer value) {
        this.azqgfy = value;
    }
    /**
     * 获取:azqgfy
     */
    public Integer getAzqgfy() {
        return azqgfy;
    }
    /**
     * 设置:收费类型统计
     */
    public void setQtysfb(String value) {
        this.qtysfb = value;
    }
    /**
     * 获取:收费类型统计
     */
    public String getQtysfb() {
        return qtysfb;
    }
}
