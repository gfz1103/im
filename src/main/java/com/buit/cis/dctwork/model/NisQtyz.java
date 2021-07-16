package com.buit.cis.dctwork.model;

import com.buit.commons.PageQuery;

import io.swagger.annotations.ApiModelProperty;

/**
 * 类名称：NisQtyz<br> 
 * 类描述：
 * @author GONGFANGZHOU 
 * @ApiModel(value="")
 */
public class NisQtyz  extends  PageQuery{
	@ApiModelProperty(value="yzxh")
    /** yzxh */
    private Integer yzxh;
	@ApiModelProperty(value="yzmc")
    /** yzmc */
    private String yzmc;
	@ApiModelProperty(value="srdm")
    /** srdm */
    private String srdm;
	@ApiModelProperty(value="yzlb")
    /** yzlb */
    private Integer yzlb;
	@ApiModelProperty(value="tsbz")
    /** tsbz */
    private String tsbz;
	@ApiModelProperty(value="zfbz")
    /** zfbz */
    private Integer zfbz;
	@ApiModelProperty(value="yzpx")
    /** yzpx */
    private Integer yzpx;
	@ApiModelProperty(value="jgid")
    /** jgid */
    private Integer jgid;

    /** 设置:yzxh  */
    public void setYzxh(Integer value) {
        this.yzxh = value;
    }
    /** 获取:yzxh */
    public Integer getYzxh() {
        return yzxh;
    }

    /** 设置:yzmc  */
    public void setYzmc(String value) {
        this.yzmc = value;
    }
    /** 获取:yzmc */
    public String getYzmc() {
        return yzmc;
    }

    /** 设置:srdm  */
    public void setSrdm(String value) {
        this.srdm = value;
    }
    /** 获取:srdm */
    public String getSrdm() {
        return srdm;
    }

    /** 设置:yzlb  */
    public void setYzlb(Integer value) {
        this.yzlb = value;
    }
    /** 获取:yzlb */
    public Integer getYzlb() {
        return yzlb;
    }

    /** 设置:tsbz  */
    public void setTsbz(String value) {
        this.tsbz = value;
    }
    /** 获取:tsbz */
    public String getTsbz() {
        return tsbz;
    }

    /** 设置:zfbz  */
    public void setZfbz(Integer value) {
        this.zfbz = value;
    }
    /** 获取:zfbz */
    public Integer getZfbz() {
        return zfbz;
    }

    /** 设置:yzpx  */
    public void setYzpx(Integer value) {
        this.yzpx = value;
    }
    /** 获取:yzpx */
    public Integer getYzpx() {
        return yzpx;
    }

    /** 设置:jgid  */
    public void setJgid(Integer value) {
        this.jgid = value;
    }
    /** 获取:jgid */
    public Integer getJgid() {
        return jgid;
    }


}
