package com.buit.cis.nurse.response;

import java.io.Serializable;

import com.buit.commons.PageQuery;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 类名称：NisDdzcpgb<br>
 * 类描述：住院患者跌倒、坠床危险因素评估表
 *
 * @author GONGFANGZHOU
 */
@ApiModel(value = "住院患者跌倒、坠床危险因素评估表_Resp")
public class NisDdzcpgbResp extends PageQuery implements Serializable {
    @ApiModelProperty(value = "主键")
    /** 主键 */
    private Integer jlxh;
    @ApiModelProperty(value = "住院号")
    /** 住院号 */
    private Integer zyh;
    @ApiModelProperty(value = "模板类型")
    /** 模板类型 */
    private String mblx;
    @ApiModelProperty(value = "跌倒病史")
    /** 跌倒病史 */
    private String dds;
    @ApiModelProperty(value = "年龄")
    /** 年龄 */
    private String nl;
    @ApiModelProperty(value = "诊断")
    /** 诊断 */
    private String zd;
    @ApiModelProperty(value = "行走辅助")
    /** 行走辅助 */
    private String xzfz;
    @ApiModelProperty(value = "照顾者")
    /** 照顾者 */
    private String zgz;
    @ApiModelProperty(value = "步态")
    /** 步态 */
    private String bt;
    @ApiModelProperty(value = "精神状态")
    /** 精神状态 */
    private String jszt;
    @ApiModelProperty(value = "使用药物")
    /** 使用药物 */
    private String syjmyw;
    @ApiModelProperty(value = "行为")
    /** 行为 */
    private String xw;
    @ApiModelProperty(value = "听/视觉")
    /** 听/视觉 */
    private String tsj;
    @ApiModelProperty(value = "沟通能力")
    /** 沟通能力 */
    private String gtnl;
    @ApiModelProperty(value = "眩晕")
    /** 眩晕 */
    private String xy;
    @ApiModelProperty(value = "排泄")
    /** 排泄 */
    private String px;
    @ApiModelProperty(value = "单项高危")
    /** 单项高危 */
    private String dxgw;
    @ApiModelProperty(value = "护士签名")
    /** 护士签名 */
    private String hsqm;
    @ApiModelProperty(value = "护理措施")
    /** 护理措施 */
    private String hlcs;
    @ApiModelProperty(value = "总分")
    /** 总分 */
    private Integer zf;
    @ApiModelProperty(value = "日期")
    /** 日期 */
    private String rq;
    @ApiModelProperty(value = "机构")
    /** 机构 */
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
     * 设置:跌倒病史
     */
    public void setDds(String value) {
        this.dds = value;
    }

    /**
     * 获取:跌倒病史
     */
    public String getDds() {
        return dds;
    }

    /**
     * 设置:年龄
     */
    public void setNl(String value) {
        this.nl = value;
    }

    /**
     * 获取:年龄
     */
    public String getNl() {
        return nl;
    }

    /**
     * 设置:诊断
     */
    public void setZd(String value) {
        this.zd = value;
    }

    /**
     * 获取:诊断
     */
    public String getZd() {
        return zd;
    }

    /**
     * 设置:行走辅助
     */
    public void setXzfz(String value) {
        this.xzfz = value;
    }

    /**
     * 获取:行走辅助
     */
    public String getXzfz() {
        return xzfz;
    }

    /**
     * 设置:照顾者
     */
    public void setZgz(String value) {
        this.zgz = value;
    }

    /**
     * 获取:照顾者
     */
    public String getZgz() {
        return zgz;
    }

    /**
     * 设置:步态
     */
    public void setBt(String value) {
        this.bt = value;
    }

    /**
     * 获取:步态
     */
    public String getBt() {
        return bt;
    }

    /**
     * 设置:精神状态
     */
    public void setJszt(String value) {
        this.jszt = value;
    }

    /**
     * 获取:精神状态
     */
    public String getJszt() {
        return jszt;
    }

    /**
     * 设置:使用药物
     */
    public void setSyjmyw(String value) {
        this.syjmyw = value;
    }

    /**
     * 获取:使用药物
     */
    public String getSyjmyw() {
        return syjmyw;
    }

    /**
     * 设置:行为
     */
    public void setXw(String value) {
        this.xw = value;
    }

    /**
     * 获取:行为
     */
    public String getXw() {
        return xw;
    }

    /**
     * 设置:听/视觉
     */
    public void setTsj(String value) {
        this.tsj = value;
    }

    /**
     * 获取:听/视觉
     */
    public String getTsj() {
        return tsj;
    }

    /**
     * 设置:沟通能力
     */
    public void setGtnl(String value) {
        this.gtnl = value;
    }

    /**
     * 获取:沟通能力
     */
    public String getGtnl() {
        return gtnl;
    }

    /**
     * 设置:眩晕
     */
    public void setXy(String value) {
        this.xy = value;
    }

    /**
     * 获取:眩晕
     */
    public String getXy() {
        return xy;
    }

    /**
     * 设置:排泄
     */
    public void setPx(String value) {
        this.px = value;
    }

    /**
     * 获取:排泄
     */
    public String getPx() {
        return px;
    }

    /**
     * 设置:单项高危
     */
    public void setDxgw(String value) {
        this.dxgw = value;
    }

    /**
     * 获取:单项高危
     */
    public String getDxgw() {
        return dxgw;
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
     * 设置:机构
     */
    public void setJgid(Integer value) {
        this.jgid = value;
    }

    /**
     * 获取:机构
     */
    public Integer getJgid() {
        return jgid;
    }


}