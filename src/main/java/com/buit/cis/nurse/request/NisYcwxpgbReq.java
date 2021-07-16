   
package com.buit.cis.nurse.request;

import java.sql.Timestamp;

import com.buit.commons.PageQuery;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


/**
 * 类名称：NisYcwxpgb<br> 
 * 类描述：住院患者压疮危险因素评估表<br>
 * @author GONGFANGZHOU
 */
@ApiModel(value="住院患者压疮危险因素评估表_Req")
public class NisYcwxpgbReq  extends  PageQuery{
    @ApiModelProperty(value="主键")
    private Integer jlxh;
    @ApiModelProperty(value="住院号")
    private Integer zyh;
    @ApiModelProperty(value="模板类型")
    private String mblx;
    @ApiModelProperty(value="感知")
    private String gz;
    @ApiModelProperty(value="潮湿")
    private String cs;
    @ApiModelProperty(value="活动")
    private String hd;
    @ApiModelProperty(value="移动")
    private String yd;
    @ApiModelProperty(value="营养")
    private String yy;
    @ApiModelProperty(value="摩擦力和剪切力")
    private String mcjql;
    @ApiModelProperty(value="是否院外带入")
    private Integer ywdr;
    @ApiModelProperty(value="总分")
    private Integer zf;
    @ApiModelProperty(value="护士签名")
    private String hsqm;
    @ApiModelProperty(value="日期")
    private Timestamp rq;
    @ApiModelProperty(value="护理措施")
    private String hlcs;
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
     * 设置:感知
     */
    public void setGz(String value) {
        this.gz = value;
    }
    /**
     * 获取:感知
     */
    public String getGz() {
        return gz;
    }
    /**
     * 设置:潮湿
     */
    public void setCs(String value) {
        this.cs = value;
    }
    /**
     * 获取:潮湿
     */
    public String getCs() {
        return cs;
    }
    /**
     * 设置:活动
     */
    public void setHd(String value) {
        this.hd = value;
    }
    /**
     * 获取:活动
     */
    public String getHd() {
        return hd;
    }
    /**
     * 设置:移动
     */
    public void setYd(String value) {
        this.yd = value;
    }
    /**
     * 获取:移动
     */
    public String getYd() {
        return yd;
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
     * 设置:摩擦力和剪切力
     */
    public void setMcjql(String value) {
        this.mcjql = value;
    }
    /**
     * 获取:摩擦力和剪切力
     */
    public String getMcjql() {
        return mcjql;
    }
    /**
     * 设置:是否院外带入
     */
    public void setYwdr(Integer value) {
        this.ywdr = value;
    }
    /**
     * 获取:是否院外带入
     */
    public Integer getYwdr() {
        return ywdr;
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
     * 设置:护理措施
     */
    public void setHlcs(String value) {
        this.hlcs = value;
    }
    /**
     * 获取:护理措施
     */
    public String getHlcs() {
        return hlcs;
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