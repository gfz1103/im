   
package com.buit.cis.nurse.request;

import java.math.BigDecimal;
import java.sql.Timestamp;

import com.buit.commons.PageQuery;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


/**
 * 类名称：NisHljl<br> 
 * 类描述：一般护理记录单<br>
 * @author GONGFANGZHOU
 */
@ApiModel(value="一般护理记录单_Req")
public class NisHljlReq  extends  PageQuery{
    @ApiModelProperty(value="主键")
    private Integer jlxh;
    @ApiModelProperty(value="住院号")
    private Integer zyh;
    @ApiModelProperty(value="模板类型")
    private String mblx;
    @ApiModelProperty(value="意识")
    private String ys;
    @ApiModelProperty(value="体温")
    private BigDecimal tw;
    @ApiModelProperty(value="脉搏")
    private Integer mb;
    @ApiModelProperty(value="呼吸")
    private Integer hx;
    @ApiModelProperty(value="SBP")
    private BigDecimal sbp;
    @ApiModelProperty(value="DBP")
    private BigDecimal dbp;
    @ApiModelProperty(value="SpO2")
    private BigDecimal spo2;
    @ApiModelProperty(value="血糖")
    private BigDecimal xt;
    @ApiModelProperty(value="营养")
    private String yy;
    @ApiModelProperty(value="精神心理")
    private String jsxl;
    @ApiModelProperty(value="导管护理")
    private String dghl;
    @ApiModelProperty(value="其他导管")
    private String qtdg;
    @ApiModelProperty(value="护理导管")
    private String hlzd;
    @ApiModelProperty(value="病情措施")
    private String bqcs;
    @ApiModelProperty(value="护士签名")
    private String hsqm;
    @ApiModelProperty(value="日期")
    private Timestamp rq;
    @ApiModelProperty(value="机构id", hidden = true)
    private Integer jgid;
    /**
     * 设置:主键
     */
    public void setJlxh(Integer value) {
        this.jlxh = value;
    }
    /**
     * 获取:主键
     */
    public Integer getJlxh() {
        return jlxh;
    }
    /**
     * 设置:住院号
     */
    public void setZyh(Integer value) {
        this.zyh = value;
    }
    /**
     * 获取:住院号
     */
    public Integer getZyh() {
        return zyh;
    }
    /**
     * 设置:模板类型
     */
    public void setMblx(String value) {
        this.mblx = value;
    }
    /**
     * 获取:模板类型
     */
    public String getMblx() {
        return mblx;
    }
    /**
     * 设置:意识
     */
    public void setYs(String value) {
        this.ys = value;
    }
    /**
     * 获取:意识
     */
    public String getYs() {
        return ys;
    }
    /**
     * 设置:体温
     */
    public void setTw(BigDecimal value) {
        this.tw = value;
    }
    /**
     * 获取:体温
     */
    public BigDecimal getTw() {
        return tw;
    }
    /**
     * 设置:脉搏
     */
    public void setMb(Integer value) {
        this.mb = value;
    }
    /**
     * 获取:脉搏
     */
    public Integer getMb() {
        return mb;
    }
    /**
     * 设置:呼吸
     */
    public void setHx(Integer value) {
        this.hx = value;
    }
    /**
     * 获取:呼吸
     */
    public Integer getHx() {
        return hx;
    }
    /**
     * 设置:SBP
     */
    public void setSbp(BigDecimal value) {
        this.sbp = value;
    }
    /**
     * 获取:SBP
     */
    public BigDecimal getSbp() {
        return sbp;
    }
    /**
     * 设置:DBP
     */
    public void setDbp(BigDecimal value) {
        this.dbp = value;
    }
    /**
     * 获取:DBP
     */
    public BigDecimal getDbp() {
        return dbp;
    }
    /**
     * 设置:SpO2
     */
    public void setSpo2(BigDecimal value) {
        this.spo2 = value;
    }
    /**
     * 获取:SpO2
     */
    public BigDecimal getSpo2() {
        return spo2;
    }
    /**
     * 设置:血糖
     */
    public void setXt(BigDecimal value) {
        this.xt = value;
    }
    /**
     * 获取:血糖
     */
    public BigDecimal getXt() {
        return xt;
    }
    /**
     * 设置:营养
     */
    public void setYy(String value) {
        this.yy = value;
    }
    /**
     * 获取:营养
     */
    public String getYy() {
        return yy;
    }
    /**
     * 设置:精神心理
     */
    public void setJsxl(String value) {
        this.jsxl = value;
    }
    /**
     * 获取:精神心理
     */
    public String getJsxl() {
        return jsxl;
    }
    /**
     * 设置:导管护理
     */
    public void setDghl(String value) {
        this.dghl = value;
    }
    /**
     * 获取:导管护理
     */
    public String getDghl() {
        return dghl;
    }
    /**
     * 设置:其他导管
     */
    public void setQtdg(String value) {
        this.qtdg = value;
    }
    /**
     * 获取:其他导管
     */
    public String getQtdg() {
        return qtdg;
    }
    /**
     * 设置:护理导管
     */
    public void setHlzd(String value) {
        this.hlzd = value;
    }
    /**
     * 获取:护理导管
     */
    public String getHlzd() {
        return hlzd;
    }
    /**
     * 设置:病情措施
     */
    public void setBqcs(String value) {
        this.bqcs = value;
    }
    /**
     * 获取:病情措施
     */
    public String getBqcs() {
        return bqcs;
    }
    /**
     * 设置:护士签名
     */
    public void setHsqm(String value) {
        this.hsqm = value;
    }
    /**
     * 获取:护士签名
     */
    public String getHsqm() {
        return hsqm;
    }
    /**
     * 设置:日期
     */
    public void setRq(Timestamp value) {
        this.rq = value;
    }
    /**
     * 获取:日期
     */
    public Timestamp getRq() {
        return rq;
    }
    /**
     * 设置:机构id
     */
    public void setJgid(Integer value) {
        this.jgid = value;
    }
    /**
     * 获取:机构id
     */
    public Integer getJgid() {
        return jgid;
    }
}