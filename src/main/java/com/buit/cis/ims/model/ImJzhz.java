package com.buit.cis.ims.model;

import com.buit.commons.PageQuery;

import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * 类名称：ImJzhz<br> 
 * 类描述：住院_收入结帐汇总
 * @author ZHOUHAISHENG 
 * @ApiModel(value="住院_收入结帐汇总")
 */
public class ImJzhz  extends PageQuery {
	//@ApiModelProperty(value="机构ID")
    /** 机构ID */
    private Integer jgid;
	//@ApiModelProperty(value="汇总日期")
    /** 汇总日期 */
    private Timestamp hzrq;
	//@ApiModelProperty(value="项目编号 | 1.在院病人结算  2.预缴金  3.出院待结算")
    /** 项目编号 | 1.在院病人结算  2.预缴金  3.出院待结算 */
    private Integer xmbh;
	//@ApiModelProperty(value="上期结存")
    /** 上期结存 */
    private BigDecimal sqjc;
	//@ApiModelProperty(value="本期发生")
    /** 本期发生 */
    private BigDecimal bqfs;
	//@ApiModelProperty(value="本期结算")
    /** 本期结算 */
    private BigDecimal bqjs;
	//@ApiModelProperty(value="现金支票")
    /** 现金支票 */
    private BigDecimal xjzp;
	//@ApiModelProperty(value="优惠金额")
    /** 优惠金额 */
    private BigDecimal yhje;
	//@ApiModelProperty(value="出院待结")
    /** 出院待结 */
    private BigDecimal cydj;
	//@ApiModelProperty(value="欠费金额")
    /** 欠费金额 */
    private BigDecimal qfje;
	//@ApiModelProperty(value="参保应收")
    /** 参保应收 */
    private BigDecimal cbje;
	//@ApiModelProperty(value="其他应收")
    /** 其他应收 */
    private BigDecimal qtje;
	//@ApiModelProperty(value="本期余额")
    /** 本期余额 */
    private BigDecimal bqye;

    /** 设置:机构ID  */
    public void setJgid(Integer value) {
        this.jgid = value;
    }
    /** 获取:机构ID */
    public Integer getJgid() {
        return jgid;
    }

    /** 设置:汇总日期  */
    public void setHzrq(Timestamp value) {
        this.hzrq = value;
    }
    /** 获取:汇总日期 */
    public Timestamp getHzrq() {
        return hzrq;
    }

    /** 设置:项目编号 | 1.在院病人结算  2.预缴金  3.出院待结算  */
    public void setXmbh(Integer value) {
        this.xmbh = value;
    }
    /** 获取:项目编号 | 1.在院病人结算  2.预缴金  3.出院待结算 */
    public Integer getXmbh() {
        return xmbh;
    }

    /** 设置:上期结存  */
    public void setSqjc(BigDecimal value) {
        this.sqjc = value;
    }
    /** 获取:上期结存 */
    public BigDecimal getSqjc() {
        return sqjc;
    }

    /** 设置:本期发生  */
    public void setBqfs(BigDecimal value) {
        this.bqfs = value;
    }
    /** 获取:本期发生 */
    public BigDecimal getBqfs() {
        return bqfs;
    }

    /** 设置:本期结算  */
    public void setBqjs(BigDecimal value) {
        this.bqjs = value;
    }
    /** 获取:本期结算 */
    public BigDecimal getBqjs() {
        return bqjs;
    }

    /** 设置:现金支票  */
    public void setXjzp(BigDecimal value) {
        this.xjzp = value;
    }
    /** 获取:现金支票 */
    public BigDecimal getXjzp() {
        return xjzp;
    }

    /** 设置:优惠金额  */
    public void setYhje(BigDecimal value) {
        this.yhje = value;
    }
    /** 获取:优惠金额 */
    public BigDecimal getYhje() {
        return yhje;
    }

    /** 设置:出院待结  */
    public void setCydj(BigDecimal value) {
        this.cydj = value;
    }
    /** 获取:出院待结 */
    public BigDecimal getCydj() {
        return cydj;
    }

    /** 设置:欠费金额  */
    public void setQfje(BigDecimal value) {
        this.qfje = value;
    }
    /** 获取:欠费金额 */
    public BigDecimal getQfje() {
        return qfje;
    }

    /** 设置:参保应收  */
    public void setCbje(BigDecimal value) {
        this.cbje = value;
    }
    /** 获取:参保应收 */
    public BigDecimal getCbje() {
        return cbje;
    }

    /** 设置:其他应收  */
    public void setQtje(BigDecimal value) {
        this.qtje = value;
    }
    /** 获取:其他应收 */
    public BigDecimal getQtje() {
        return qtje;
    }

    /** 设置:本期余额  */
    public void setBqye(BigDecimal value) {
        this.bqye = value;
    }
    /** 获取:本期余额 */
    public BigDecimal getBqye() {
        return bqye;
    }


}
