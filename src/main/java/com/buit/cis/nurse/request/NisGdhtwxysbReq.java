   
package com.buit.cis.nurse.request;

import java.sql.Timestamp;

import com.buit.commons.PageQuery;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


/**
 * 类名称：NisGdhtwxysb<br> 
 * 类描述：住院患者管道滑脱危险因素评估表<br>
 * @author GONGFANGZHOU
 */
@ApiModel(value="住院患者管道滑脱危险因素评估表_Req")
public class NisGdhtwxysbReq  extends  PageQuery{
    @ApiModelProperty(value="主键")
    private Integer jlxh;
    @ApiModelProperty(value="住院号")
    private Integer zyh;
    @ApiModelProperty(value="模板类型")
    private String mblx;
    @ApiModelProperty(value="高危风险管道")
    private String gwfxgd;
    @ApiModelProperty(value="中危风险管道")
    private String zwfxgd;
    @ApiModelProperty(value="低危风险管道")
    private String dwfxgd;
    @ApiModelProperty(value="其他")
    private String qt;
    @ApiModelProperty(value="护理措施")
    private String hlcs;
    @ApiModelProperty(value="护士签名")
    private String hsqm;
    @ApiModelProperty(value="日期")
    private Timestamp rq;
    @ApiModelProperty(value="机构id")
    private Integer jgid;
    @ApiModelProperty(value="总分")
    private Integer zf;
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
     * 设置:高危风险管道
     */
    public void setGwfxgd(String value) {
        this.gwfxgd = value;
    }
    /**
     * 获取:高危风险管道
     */
    public String getGwfxgd() {
        return gwfxgd;
    }
    /**
     * 设置:中危风险管道
     */
    public void setZwfxgd(String value) {
        this.zwfxgd = value;
    }
    /**
     * 获取:中危风险管道
     */
    public String getZwfxgd() {
        return zwfxgd;
    }
    /**
     * 设置:低危风险管道
     */
    public void setDwfxgd(String value) {
        this.dwfxgd = value;
    }
    /**
     * 获取:低危风险管道
     */
    public String getDwfxgd() {
        return dwfxgd;
    }
    /**
     * 设置:其他
     */
    public void setQt(String value) {
        this.qt = value;
    }
    /**
     * 获取:其他
     */
    public String getQt() {
        return qt;
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
}