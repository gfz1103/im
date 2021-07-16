package com.buit.cis.nurse.response;

import java.io.Serializable;

import com.buit.commons.PageQuery;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 类名称：NisBqyjpgd<br>
 * 类描述：患者病情预警评估单
 *
 * @author GONGFANGZHOU
 */
@ApiModel(value = "患者病情预警评估单_Resp")
public class NisBqyjpgdResp extends PageQuery implements Serializable {
    @ApiModelProperty(value = "主键")
    /** 主键 */
    private Integer jlxh;
    @ApiModelProperty(value = "住院号")
    /** 住院号 */
    private Integer zyh;
    @ApiModelProperty(value = "模板类型")
    /** 模板类型 */
    private String mblx;
    @ApiModelProperty(value = "心率")
    /** 心率 */
    private String xl;
    @ApiModelProperty(value = "收缩压")
    /** 收缩压 */
    private String ssy;
    @ApiModelProperty(value = "呼吸频率")
    /** 呼吸频率 */
    private String hx;
    @ApiModelProperty(value = "体温")
    /** 体温 */
    private String tw;
    @ApiModelProperty(value = "意识")
    /** 意识 */
    private String ys;
    @ApiModelProperty(value = "排尿")
    /** 排尿 */
    private String pl;
    @ApiModelProperty(value = "氧饱和度")
    /** 氧饱和度 */
    private String yphd;
    @ApiModelProperty(value = "血糖")
    /** 血糖 */
    private String xt;
    @ApiModelProperty(value = "护理措施")
    /** 护理措施 */
    private String hlcs;
    @ApiModelProperty(value = "护士签名")
    /** 护士签名 */
    private String hsqm;
    @ApiModelProperty(value = "日期")
    /** 日期 */
    private String rq;
    @ApiModelProperty(value = "总分")
    /** 总分 */
    private Integer zf;
    @ApiModelProperty(value = "机构id")
    /** 机构id */
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
     * 设置:心率
     */
    public void setXl(String value) {
        this.xl = value;
    }

    /**
     * 获取:心率
     */
    public String getXl() {
        return xl;
    }

    /**
     * 设置:收缩压
     */
    public void setSsy(String value) {
        this.ssy = value;
    }

    /**
     * 获取:收缩压
     */
    public String getSsy() {
        return ssy;
    }

    /**
     * 设置:呼吸频率
     */
    public void setHx(String value) {
        this.hx = value;
    }

    /**
     * 获取:呼吸频率
     */
    public String getHx() {
        return hx;
    }

    /**
     * 设置:体温
     */
    public void setTw(String value) {
        this.tw = value;
    }

    /**
     * 获取:体温
     */
    public String getTw() {
        return tw;
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
     * 设置:排尿
     */
    public void setPl(String value) {
        this.pl = value;
    }

    /**
     * 获取:排尿
     */
    public String getPl() {
        return pl;
    }

    /**
     * 设置:氧饱和度
     */
    public void setYphd(String value) {
        this.yphd = value;
    }

    /**
     * 获取:氧饱和度
     */
    public String getYphd() {
        return yphd;
    }

    /**
     * 设置:血糖
     */
    public void setXt(String value) {
        this.xt = value;
    }

    /**
     * 获取:血糖
     */
    public String getXt() {
        return xt;
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
    public void setRq(String value) {
        this.rq = value;
    }

    /**
     * 获取:日期
     */
    public String getRq() {
        return rq;
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