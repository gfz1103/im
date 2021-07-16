package com.buit.cis.ims.request;


import com.buit.commons.PageQuery;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;
import java.sql.Timestamp;


/**
 * 类名称：ImFeeFyhz<br>
 * 类描述：费用汇总<br>
 * @author ZHOUHAISHENG
 */
@ApiModel(value="费用汇总")
public class ImFeeFyhzReq  extends PageQuery {
    @ApiModelProperty(value="机构ID")
    private Integer jgid;
    @ApiModelProperty(value="汇总日期")
    private Timestamp hzrq;
    @ApiModelProperty(value="费用项目")
    private Integer fyxm;
    @ApiModelProperty(value="上期结存")
    private BigDecimal sqjc;
    @ApiModelProperty(value="本期发生")
    private BigDecimal bqfs;
    @ApiModelProperty(value="本期结算")
    private BigDecimal bqjs;
    @ApiModelProperty(value="本期结存")
    private BigDecimal bqjc;
    @ApiModelProperty(value="实际结存")
    private BigDecimal sjjc;
    /**
     * 设置:机构ID
     */
    public void setJgid(Integer value) {
        this.jgid = value;
    }
    /**
     * 获取:机构ID
     */
    public Integer getJgid() {
        return jgid;
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
     * 设置:费用项目
     */
    public void setFyxm(Integer value) {
        this.fyxm = value;
    }
    /**
     * 获取:费用项目
     */
    public Integer getFyxm() {
        return fyxm;
    }
    /**
     * 设置:上期结存
     */
    public void setSqjc(BigDecimal value) {
        this.sqjc = value;
    }
    /**
     * 获取:上期结存
     */
    public BigDecimal getSqjc() {
        return sqjc;
    }
    /**
     * 设置:本期发生
     */
    public void setBqfs(BigDecimal value) {
        this.bqfs = value;
    }
    /**
     * 获取:本期发生
     */
    public BigDecimal getBqfs() {
        return bqfs;
    }
    /**
     * 设置:本期结算
     */
    public void setBqjs(BigDecimal value) {
        this.bqjs = value;
    }
    /**
     * 获取:本期结算
     */
    public BigDecimal getBqjs() {
        return bqjs;
    }
    /**
     * 设置:本期结存
     */
    public void setBqjc(BigDecimal value) {
        this.bqjc = value;
    }
    /**
     * 获取:本期结存
     */
    public BigDecimal getBqjc() {
        return bqjc;
    }
    /**
     * 设置:实际结存
     */
    public void setSjjc(BigDecimal value) {
        this.sjjc = value;
    }
    /**
     * 获取:实际结存
     */
    public BigDecimal getSjjc() {
        return sjjc;
    }
}
