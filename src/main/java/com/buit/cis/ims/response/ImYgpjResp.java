package com.buit.cis.ims.response;

import com.buit.commons.PageQuery;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.sql.Timestamp;


/**
 * 类名称：ImYgpj<br>
 * 类描述：住院_员工票据<br>
 * @author LAPTOP-6GUR25CC
 */
@ApiModel(value="住院_员工票据")
public class ImYgpjResp  extends PageQuery {

    @ApiModelProperty(value="记录序号")
    private Integer jlxh;
    @ApiModelProperty(value="机构编号")
    private Integer jgid;
    @ApiModelProperty(value="员工代码")
    private Integer ygdm;
    @ApiModelProperty(value="员工姓名")
    private String ygxm;
    @ApiModelProperty(value="领取日期")
    private Timestamp lyrq;
    @ApiModelProperty(value="票据类型 | 1.发票        2.收据")
    private Integer pjlx;
    @ApiModelProperty(value="起始号码")
    private String qshm;
    @ApiModelProperty(value="终止号码")
    private String zzhm;
    @ApiModelProperty(value="当前号码")
    private String dqhm;
    @ApiModelProperty(value="使用标志 | 0.启用        1.停用")
    private Integer sybz;

    public Integer getJlxh() {
        return jlxh;
    }

    public void setJlxh(Integer jlxh) {
        this.jlxh = jlxh;
    }

    /**
     * 设置:机构编号
     */
    public void setJgid(Integer value) {
        this.jgid = value;
    }
    /**
     * 获取:机构编号
     */
    public Integer getJgid() {
        return jgid;
    }
    /**
     * 设置:员工代码
     */
    public void setYgdm(Integer value) {
        this.ygdm = value;
    }
    /**
     * 获取:员工代码
     */
    public Integer getYgdm() {
        return ygdm;
    }
    /**
     * 设置:领取日期
     */
    public void setLyrq(Timestamp value) {
        this.lyrq = value;
    }
    /**
     * 获取:领取日期
     */
    public Timestamp getLyrq() {
        return lyrq;
    }
    /**
     * 设置:票据类型 | 1.发票        2.收据
     */
    public Integer getPjlx() {
        return pjlx;
    }

    public void setPjlx(Integer pjlx) {
        this.pjlx = pjlx;
    }

    public void setSybz(Integer sybz) {
        this.sybz = sybz;
    }

    /**
     * 设置:起始号码
     */
    public void setQshm(String value) {
        this.qshm = value;
    }
    /**
     * 获取:起始号码
     */
    public String getQshm() {
        return qshm;
    }
    /**
     * 设置:终止号码
     */
    public void setZzhm(String value) {
        this.zzhm = value;
    }
    /**
     * 获取:终止号码
     */
    public String getZzhm() {
        return zzhm;
    }
    /**
     * 设置:当前号码
     */
    public void setDqhm(String value) {
        this.dqhm = value;
    }
    /**
     * 获取:当前号码
     */
    public String getDqhm() {
        return dqhm;
    }

    public Integer getSybz() {
        return sybz;
    }

    public String getYgxm() {
        return ygxm;
    }

    public void setYgxm(String ygxm) {
        this.ygxm = ygxm;
    }
}
