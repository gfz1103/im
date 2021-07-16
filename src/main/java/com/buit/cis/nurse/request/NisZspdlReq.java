   
package com.buit.cis.nurse.request;

import java.sql.Timestamp;

import com.buit.commons.PageQuery;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


/**
 * 类名称：NisZspdl<br> 
 * 类描述：Barthel指数平定量表<br>
 * @author GONGFANGZHOU
 */
@ApiModel(value="Barthel指数平定量表_Req")
public class NisZspdlReq  extends  PageQuery{
    @ApiModelProperty(value="主键")
    private Integer jlxh;
    @ApiModelProperty(value="住院号")
    private Integer zyh;
    @ApiModelProperty(value="模板类型")
    private String mblx;
    @ApiModelProperty(value="进食")
    private String js;
    @ApiModelProperty(value="洗澡")
    private String xz;
    @ApiModelProperty(value="修饰")
    private String xs;
    @ApiModelProperty(value="穿衣")
    private String cy;
    @ApiModelProperty(value="控制大便")
    private String kzdb;
    @ApiModelProperty(value="控制小便")
    private String kzxb;
    @ApiModelProperty(value="如厕")
    private String rc;
    @ApiModelProperty(value="床椅转移")
    private String cyzy;
    @ApiModelProperty(value="平地行走")
    private String pdxz;
    @ApiModelProperty(value="上下楼梯")
    private String sxlt;
    @ApiModelProperty(value="护士签名")
    private String hsqm;
    @ApiModelProperty(value="总分")
    private Integer zf;
    @ApiModelProperty(value="自立能力等级")
    private String dj;
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
     * 设置:进食
     */
    public void setJs(String value) {
        this.js = value;
    }
    /**
     * 获取:进食
     */
    public String getJs() {
        return js;
    }
    /**
     * 设置:洗澡
     */
    public void setXz(String value) {
        this.xz = value;
    }
    /**
     * 获取:洗澡
     */
    public String getXz() {
        return xz;
    }
    /**
     * 设置:修饰
     */
    public void setXs(String value) {
        this.xs = value;
    }
    /**
     * 获取:修饰
     */
    public String getXs() {
        return xs;
    }
    /**
     * 设置:穿衣
     */
    public void setCy(String value) {
        this.cy = value;
    }
    /**
     * 获取:穿衣
     */
    public String getCy() {
        return cy;
    }
    /**
     * 设置:控制大便
     */
    public void setKzdb(String value) {
        this.kzdb = value;
    }
    /**
     * 获取:控制大便
     */
    public String getKzdb() {
        return kzdb;
    }
    /**
     * 设置:控制小便
     */
    public void setKzxb(String value) {
        this.kzxb = value;
    }
    /**
     * 获取:控制小便
     */
    public String getKzxb() {
        return kzxb;
    }
    /**
     * 设置:如厕
     */
    public void setRc(String value) {
        this.rc = value;
    }
    /**
     * 获取:如厕
     */
    public String getRc() {
        return rc;
    }
    /**
     * 设置:床椅转移
     */
    public void setCyzy(String value) {
        this.cyzy = value;
    }
    /**
     * 获取:床椅转移
     */
    public String getCyzy() {
        return cyzy;
    }
    /**
     * 设置:平地行走
     */
    public void setPdxz(String value) {
        this.pdxz = value;
    }
    /**
     * 获取:平地行走
     */
    public String getPdxz() {
        return pdxz;
    }
    /**
     * 设置:上下楼梯
     */
    public void setSxlt(String value) {
        this.sxlt = value;
    }
    /**
     * 获取:上下楼梯
     */
    public String getSxlt() {
        return sxlt;
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
     * 设置:总分
     */
    public void setZf(Integer value) {
        this.zf = value;
    }
    /**
     * 获取:总分
     */
    public Integer getZf() {
        return zf;
    }
    /**
     * 设置:自立能力等级
     */
    public void setDj(String value) {
        this.dj = value;
    }
    /**
     * 获取:自立能力等级
     */
    public String getDj() {
        return dj;
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