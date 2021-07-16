package com.buit.cis.nurse.response;

import java.io.Serializable;

import com.buit.commons.PageQuery;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 类名称：NisFssrisk<br>
 * 类描述：肺栓塞风险因素评估表
 *
 * @author GONGFANGZHOU
 */
@ApiModel(value = "肺栓塞风险因素评估表_Resp")
public class NisFssriskResp extends PageQuery implements Serializable {
    @ApiModelProperty(value = "主键")
    /** 主键 */
    private Integer jlxh;
    @ApiModelProperty(value = "住院号")
    /** 住院号 */
    private Integer zyh;
    @ApiModelProperty(value = "模板类型")
    /** 模板类型 */
    private String mblx;
    @ApiModelProperty(value = "PTE或DVT病史")
    /** PTE或DVT病史 */
    private String bs;
    @ApiModelProperty(value = "1月内手术或骨折")
    /** 1月内手术或骨折 */
    private String ssgz;
    @ApiModelProperty(value = "活动性肿瘤")
    /** 活动性肿瘤 */
    private String hdxzl;
    @ApiModelProperty(value = "心率（次/min）")
    /** 心率（次/min） */
    private String xl;
    @ApiModelProperty(value = "咯血")
    /** 咯血 */
    private String kx;
    @ApiModelProperty(value = "单侧下肢疼痛")
    /** 单侧下肢疼痛 */
    private String xztt;
    @ApiModelProperty(value = "下肢深静脉触痛及单侧下肢水肿")
    /** 下肢深静脉触痛及单侧下肢水肿 */
    private String xzsz;
    @ApiModelProperty(value = "年龄＞65岁")
    /** 年龄＞65岁 */
    private String nldy;
    @ApiModelProperty(value = "血液高凝")
    /** 血液高凝 */
    private String xygn;
    @ApiModelProperty(value = "心脏病")
    /** 心脏病 */
    private String xzb;
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
     * 设置:PTE或DVT病史
     */
    public void setBs(String value) {
        this.bs = value;
    }

    /**
     * 获取:PTE或DVT病史
     */
    public String getBs() {
        return bs;
    }

    /**
     * 设置:1月内手术或骨折
     */
    public void setSsgz(String value) {
        this.ssgz = value;
    }

    /**
     * 获取:1月内手术或骨折
     */
    public String getSsgz() {
        return ssgz;
    }

    /**
     * 设置:活动性肿瘤
     */
    public void setHdxzl(String value) {
        this.hdxzl = value;
    }

    /**
     * 获取:活动性肿瘤
     */
    public String getHdxzl() {
        return hdxzl;
    }

    /**
     * 设置:心率（次/min）
     */
    public void setXl(String value) {
        this.xl = value;
    }

    /**
     * 获取:心率（次/min）
     */
    public String getXl() {
        return xl;
    }

    /**
     * 设置:咯血
     */
    public void setKx(String value) {
        this.kx = value;
    }

    /**
     * 获取:咯血
     */
    public String getKx() {
        return kx;
    }

    /**
     * 设置:单侧下肢疼痛
     */
    public void setXztt(String value) {
        this.xztt = value;
    }

    /**
     * 获取:单侧下肢疼痛
     */
    public String getXztt() {
        return xztt;
    }

    /**
     * 设置:下肢深静脉触痛及单侧下肢水肿
     */
    public void setXzsz(String value) {
        this.xzsz = value;
    }

    /**
     * 获取:下肢深静脉触痛及单侧下肢水肿
     */
    public String getXzsz() {
        return xzsz;
    }

    /**
     * 设置:年龄＞65岁
     */
    public void setNldy(String value) {
        this.nldy = value;
    }

    /**
     * 获取:年龄＞65岁
     */
    public String getNldy() {
        return nldy;
    }

    /**
     * 设置:血液高凝
     */
    public void setXygn(String value) {
        this.xygn = value;
    }

    /**
     * 获取:血液高凝
     */
    public String getXygn() {
        return xygn;
    }

    /**
     * 设置:心脏病
     */
    public void setXzb(String value) {
        this.xzb = value;
    }

    /**
     * 获取:心脏病
     */
    public String getXzb() {
        return xzb;
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