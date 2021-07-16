package com.buit.cis.ims.model;

import com.buit.commons.PageQuery;

/**
 * 类名称：YjZdjg<br> 
 * 类描述：
 * @author ZHOUHAISHENG 
 * @ApiModel(value="")
 */
public class YjZdjg  extends PageQuery {
	//@ApiModelProperty(value="zdid")
    /** zdid */
    private Integer zdid;
	//@ApiModelProperty(value="jgid")
    /** jgid */
    private Integer jgid;
	//@ApiModelProperty(value="zddm")
    /** zddm */
    private String zddm;
	//@ApiModelProperty(value="zdmc")
    /** zdmc */
    private String zdmc;
	//@ApiModelProperty(value="ksdm")
    /** ksdm */
    private Integer ksdm;

    public Integer getZdid() {
        return zdid;
    }

    public void setZdid(Integer zdid) {
        this.zdid = zdid;
    }

    public Integer getJgid() {
        return jgid;
    }

    public void setJgid(Integer jgid) {
        this.jgid = jgid;
    }

    /** 设置:zddm  */
    public void setZddm(String value) {
        this.zddm = value;
    }
    /** 获取:zddm */
    public String getZddm() {
        return zddm;
    }

    /** 设置:zdmc  */
    public void setZdmc(String value) {
        this.zdmc = value;
    }
    /** 获取:zdmc */
    public String getZdmc() {
        return zdmc;
    }

    /** 设置:ksdm  */
    public void setKsdm(Integer value) {
        this.ksdm = value;
    }
    /** 获取:ksdm */
    public Integer getKsdm() {
        return ksdm;
    }


}