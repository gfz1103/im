package com.buit.cis.nurse.response;

import java.io.Serializable;

import com.buit.commons.PageQuery;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 类名称：NisFsspgb<br>
 * 类描述：深静脉血栓风险评估表
 *
 * @author GONGFANGZHOU
 */
@ApiModel(value = "深静脉血栓风险评估表_Resp")
public class NisFsspgbResp extends PageQuery implements Serializable {
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
    @ApiModelProperty(value = "特殊疾病")
    /** 导尿管类型 */
    private String tsjb;
    @ApiModelProperty(value = "手术和治疗")
    /** 尿液性状 */
    private String sshzl;
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
     * 设置:特殊疾病
     */
    public void setTsjb(String value) {
        this.tsjb = value;
    }

    /**
     * 获取:特殊疾病
     */
    public String getTsjb() {
        return tsjb;
    }

    /**
     * 设置:手术和治疗
     */
    public void setSshzl(String value) {
        this.sshzl = value;
    }

    /**
     * 获取:手术和治疗
     */
    public String getSshzl() {
        return sshzl;
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