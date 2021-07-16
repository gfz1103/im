package com.buit.cis.ims.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


/**
 * 类名称：ImYgpj<br>
 * 类描述：住院_员工票据<br>
 * @author zhouhaisheng
 */
@ApiModel(value="住院_员工票据")
public class ImYgpjReq   {
    @ApiModelProperty(value="员工代码  字典接口/sysuser/findGroupUserDic")
    private Integer ygdm;
    @ApiModelProperty(value="领取日期")
    private String lyrq;
    @ApiModelProperty(value="票据类型 | 1.发票        2.收据")
    private Integer pjlx;
    @ApiModelProperty(value="起始号码")
    private String qshm;
    @ApiModelProperty(value="终止号码")
    private String zzhm;
    @ApiModelProperty(value="使用号码")
    private String dqhm;


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
    public void setLyrq(String value) {
        this.lyrq = value;
    }
    /**
     * 获取:领取日期
     */
    public String getLyrq() {
        return lyrq;
    }

    public Integer getPjlx() {
        return pjlx;
    }

    public void setPjlx(Integer pjlx) {
        this.pjlx = pjlx;
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

}
