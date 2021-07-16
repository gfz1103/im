   
package com.buit.cis.nurse.request;

import java.sql.Timestamp;

import com.buit.commons.PageQuery;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


/**
 * 类名称：NisAllzxd<br> 
 * 类描述：所有护理执行单<br>
 * @author GONGFANGZHOU
 */
@ApiModel(value="所有护理执行单_Req")
public class NisAllzxdReq  extends  PageQuery{
    @ApiModelProperty(value="主键")
    private Integer jlxh;
    @ApiModelProperty(value="住院号")
    private Integer zyh;
    @ApiModelProperty(value="模板类型")
    private String mblx;
    @ApiModelProperty(value="组号")
    private Integer zh;
    @ApiModelProperty(value="护理诊断")
    private String hlzd;
    @ApiModelProperty(value="护理诊断其他")
    private String qt;
    @ApiModelProperty(value="护理目标")
    private String hlmb;
    @ApiModelProperty(value="护理措施")
    private String hlcs;
    @ApiModelProperty(value="护理时间")
    private Timestamp hlsj;
    @ApiModelProperty(value="效果评价时间")
    private Timestamp xgpjsj;
    @ApiModelProperty(value="效果评价")
    private String xgpj;
    @ApiModelProperty(value="护士签名")
    private String hsqm;
    @ApiModelProperty(value="医生签名")
    private String ysqm;
    @ApiModelProperty(value="日期")
    private Timestamp rq;
    @ApiModelProperty(value="机构id")
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
     * 设置:组号
     */
    public void setZh(Integer value) {
        this.zh = value;
    }
    /**
     * 获取:组号
     */
    public Integer getZh() {
        return zh;
    }
    /**
     * 设置:护理诊断
     */
    public void setHlzd(String value) {
        this.hlzd = value;
    }
    /**
     * 获取:护理诊断
     */
    public String getHlzd() {
        return hlzd;
    }
    /**
     * 设置:护理诊断其他
     */
    public void setQt(String value) {
        this.qt = value;
    }
    /**
     * 获取:护理诊断其他
     */
    public String getQt() {
        return qt;
    }
    /**
     * 设置:护理目标
     */
    public void setHlmb(String value) {
        this.hlmb = value;
    }
    /**
     * 获取:护理目标
     */
    public String getHlmb() {
        return hlmb;
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
     * 设置:护理时间
     */
	public void setHlsj(Timestamp hlsj) {
		this.hlsj = hlsj;
	}
	/**
     * 获取:护理时间
     */
	public Timestamp getHlsj() {
		return hlsj;
	}
	/**
     * 设置:效果评价时间
     */
    public void setXgpjsj(Timestamp value) {
        this.xgpjsj = value;
    }
    /**
     * 获取:效果评价时间
     */
    public Timestamp getXgpjsj() {
        return xgpjsj;
    }
    /**
     * 设置:效果评价
     */
    public void setXgpj(String value) {
        this.xgpj = value;
    }
    /**
     * 获取:效果评价
     */
    public String getXgpj() {
        return xgpj;
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
     * 设置:医生签名
     */
    public void setYsqm(String value) {
        this.ysqm = value;
    }
    /**
     * 获取:医生签名
     */
    public String getYsqm() {
        return ysqm;
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