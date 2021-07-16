package com.buit.cis.ims.response;


import com.buit.commons.PageQuery;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;
import java.sql.Timestamp;


/**
 * 类名称：ImSrhz<br>
 * 类描述：住院收入汇总<br>
 * @author ZHOUHAISHENG
 */
@ApiModel(value="住院收入汇总")
public class ImSrhzResp  extends PageQuery {
    @ApiModelProperty(value="机构ID")
    private Integer jgid;
    @ApiModelProperty(value="汇总日期")
    private Timestamp hzrq;
    @ApiModelProperty(value="科室类别 | 1.科室  2.病区")
    private Integer kslb;
    @ApiModelProperty(value="科室代码")
    private Integer ksdm;
    @ApiModelProperty(value="收费项目")
    private Integer sfxm;
    @ApiModelProperty(value="总计金额")
    private BigDecimal zjje;
    @ApiModelProperty(value="自负金额")
    private BigDecimal zfje;
    /**
     * 设置:机构ID
     */
    public void setJgid(Integer value) {
        this.jgid = value;
    }
    /**
     * 获取:机构ID
     */
    public Integer getJgid() {
        return jgid;
    }
    /**
     * 设置:汇总日期
     */
    public void setHzrq(Timestamp value) {
        this.hzrq = value;
    }
    /**
     * 获取:汇总日期
     */
    public Timestamp getHzrq() {
        return hzrq;
    }
    /**
     * 设置:科室类别 | 1.科室  2.病区
     */
    public void setKslb(Integer value) {
        this.kslb = value;
    }
    /**
     * 获取:科室类别 | 1.科室  2.病区
     */
    public Integer getKslb() {
        return kslb;
    }
    /**
     * 设置:科室代码
     */
    public void setKsdm(Integer value) {
        this.ksdm = value;
    }
    /**
     * 获取:科室代码
     */
    public Integer getKsdm() {
        return ksdm;
    }
    /**
     * 设置:收费项目
     */
    public void setSfxm(Integer value) {
        this.sfxm = value;
    }
    /**
     * 获取:收费项目
     */
    public Integer getSfxm() {
        return sfxm;
    }
    /**
     * 设置:总计金额
     */
    public void setZjje(BigDecimal value) {
        this.zjje = value;
    }
    /**
     * 获取:总计金额
     */
    public BigDecimal getZjje() {
        return zjje;
    }
    /**
     * 设置:自负金额
     */
    public void setZfje(BigDecimal value) {
        this.zfje = value;
    }
    /**
     * 获取:自负金额
     */
    public BigDecimal getZfje() {
        return zfje;
    }
}
