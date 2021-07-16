package com.buit.cis.ims.response;


import com.buit.commons.PageQuery;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


/**
 * 医技信息维护 -诊断结果维护出参
 * 类名称：YjZdjg<br>
 * 类描述：<br>
 * @author ZHOUHAISHENG
 */
@ApiModel(value="医技信息维护 -诊断结果维护出参")
public class YjZdjgResp  extends PageQuery {
    @ApiModelProperty(value="zdid")
    private Long zdid;
    @ApiModelProperty(value="jgid")
    private Integer jgid;
    @ApiModelProperty(value="zddm")
    private String zddm;
    @ApiModelProperty(value="zdmc")
    private String zdmc;
    @ApiModelProperty(value="ksdm")
    private Long ksdm;
    /**
     * 设置:zdid
     */
    public void setZdid(Long value) {
        this.zdid = value;
    }
    /**
     * 获取:zdid
     */
    public Long getZdid() {
        return zdid;
    }
    /**
     * 设置:jgid
     */
    public void setJgid(Integer value) {
        this.jgid = value;
    }
    /**
     * 获取:jgid
     */
    public Integer getJgid() {
        return jgid;
    }
    /**
     * 设置:zddm
     */
    public void setZddm(String value) {
        this.zddm = value;
    }
    /**
     * 获取:zddm
     */
    public String getZddm() {
        return zddm;
    }
    /**
     * 设置:zdmc
     */
    public void setZdmc(String value) {
        this.zdmc = value;
    }
    /**
     * 获取:zdmc
     */
    public String getZdmc() {
        return zdmc;
    }
    /**
     * 设置:ksdm
     */
    public void setKsdm(Long value) {
        this.ksdm = value;
    }
    /**
     * 获取:ksdm
     */
    public Long getKsdm() {
        return ksdm;
    }
}