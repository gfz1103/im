package com.buit.cis.nurse.response;

import java.io.Serializable;

import com.buit.commons.PageQuery;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 类名称：NisLzdngpgb<br>
 * 类描述：留置导尿管感染风险因素评估表
 *
 * @author GONGFANGZHOU
 */
@ApiModel(value="留置导尿管感染风险因素评估表_Resp")
public class NisLzdngpgbResp extends PageQuery implements Serializable {
    @ApiModelProperty(value = "主键")
    /** 主键 */
    private Integer jlxh;
    @ApiModelProperty(value = "住院号")
    /** 住院号 */
    private Integer zyh;
    @ApiModelProperty(value = "模板类型")
    /** 模板类型 */
    private String mblx;
    @ApiModelProperty(value = "一般情况")
    /** 一般情况 */
    private String ybqk;
    @ApiModelProperty(value = "导尿管类型")
    /** 导尿管类型 */
    private String dnglx;
    @ApiModelProperty(value = "留置时间")
    /** 留置时间 */
    private String lzsj;
    @ApiModelProperty(value = "尿液性状")
    /** 尿液性状 */
    private String nyxz;
    @ApiModelProperty(value = "膀胱冲洗")
    /** 膀胱冲洗 */
    private String pgcx;
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
     * 设置:一般情况
     */
    public void setYbqk(String value) {
        this.ybqk = value;
    }

    /**
     * 获取:一般情况
     */
    public String getYbqk() {
        return ybqk;
    }

    /**
     * 设置:导尿管类型
     */
    public void setDnglx(String value) {
        this.dnglx = value;
    }

    /**
     * 获取:导尿管类型
     */
    public String getDnglx() {
        return dnglx;
    }

    /**
     * 设置:留置时间
     */
    public void setLzsj(String value) {
        this.lzsj = value;
    }

    /**
     * 获取:留置时间
     */
    public String getLzsj() {
        return lzsj;
    }

    /**
     * 设置:尿液性状
     */
    public void setNyxz(String value) {
        this.nyxz = value;
    }

    /**
     * 获取:尿液性状
     */
    public String getNyxz() {
        return nyxz;
    }

    /**
     * 设置:膀胱冲洗
     */
    public void setPgcx(String value) {
        this.pgcx = value;
    }

    /**
     * 获取:膀胱冲洗
     */
    public String getPgcx() {
        return pgcx;
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