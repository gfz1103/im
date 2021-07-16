   
package com.buit.cis.nurse.request;

import java.sql.Timestamp;

import com.buit.commons.PageQuery;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


/**
 * 类名称：NisFyfxpgd<br> 
 * 类描述：<br>医院获得性肺炎风险因素评估单
 * @author GONGFANGZHOU
 */
@ApiModel(value="医院获得性肺炎风险因素评估单_Req")
public class NisFyfxpgdReq  extends  PageQuery{
    @ApiModelProperty(value="主键")
    private Integer jlxh;
    @ApiModelProperty(value="住院号")
    private Integer zyh;
    @ApiModelProperty(value="模板类型")
    private String mblx;
    @ApiModelProperty(value="年龄")
    private String nl;
    @ApiModelProperty(value="神志")
    private String sz;
    @ApiModelProperty(value="特殊风险")
    private String tsfx;
    @ApiModelProperty(value="呼吸类型")
    private String hxlx;
    @ApiModelProperty(value="气道类型")
    private String qdlx;
    @ApiModelProperty(value="机械通气")
    private String jxtq;
    @ApiModelProperty(value="颅胸腹部手术")
    private Integer lxfb;
    @ApiModelProperty(value="插管全麻")
    private Integer cgqm;
    @ApiModelProperty(value="护士签名")
    private String hsqm;
    @ApiModelProperty(value="护理措施")
    private String hlcs;
    @ApiModelProperty(value="总分")
    private Integer zf;
    @ApiModelProperty(value="日期")
    private Timestamp rq;
    @ApiModelProperty(value="机构", hidden = true)
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
     * 设置:神志
     */
    public void setSz(String value) {
        this.sz = value;
    }
    /**
     * 获取:神志
     */
    public String getSz() {
        return sz;
    }
    /**
     * 设置:特殊风险
     */
    public void setTsfx(String value) {
        this.tsfx = value;
    }
    /**
     * 获取:特殊风险
     */
    public String getTsfx() {
        return tsfx;
    }
    /**
     * 设置:呼吸类型
     */
    public void setHxlx(String value) {
        this.hxlx = value;
    }
    /**
     * 获取:呼吸类型
     */
    public String getHxlx() {
        return hxlx;
    }
    /**
     * 设置:气道类型
     */
    public void setQdlx(String value) {
        this.qdlx = value;
    }
    /**
     * 获取:气道类型
     */
    public String getQdlx() {
        return qdlx;
    }
    /**
     * 设置:机械通气
     */
    public void setJxtq(String value) {
        this.jxtq = value;
    }
    /**
     * 获取:机械通气
     */
    public String getJxtq() {
        return jxtq;
    }
    /**
     * 设置:颅胸腹部手术
     */
    public void setLxfb(Integer value) {
        this.lxfb = value;
    }
    /**
     * 获取:颅胸腹部手术
     */
    public Integer getLxfb() {
        return lxfb;
    }
    /**
     * 设置:插管全麻
     */
    public void setCgqm(Integer value) {
        this.cgqm = value;
    }
    /**
     * 获取:插管全麻
     */
    public Integer getCgqm() {
        return cgqm;
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