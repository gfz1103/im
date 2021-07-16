package com.buit.cis.ims.model;

import com.buit.commons.PageQuery;

import java.sql.Timestamp;

/**
 * 类名称：ImJkzf<br> 
 * 类描述：住院_缴款作废
 * @author ZHOUHAISHENG 
 * @ApiModel(value="住院_缴款作废")
 */
public class ImJkzf  extends PageQuery {
	//@ApiModelProperty(value="缴款序号")
    /** 缴款序号 */
    private Integer jkxh;
	//@ApiModelProperty(value="机构代码")
    /** 机构代码 */
    private Integer jgid;
	//@ApiModelProperty(value="作废工号")
    /** 作废工号 */
    private String zfgh;
	//@ApiModelProperty(value="作废日期")
    /** 作废日期 */
    private Timestamp zfrq;
	//@ApiModelProperty(value="结账日期")
    /** 结账日期 */
    private Timestamp jzrq;
	//@ApiModelProperty(value="汇总日期")
    /** 汇总日期 */
    private Timestamp hzrq;
	//@ApiModelProperty(value="门诊类别，默认0")
    /** 门诊类别，默认0 */
    private Integer mzlb;

    /** 设置:缴款序号  */
    public void setJkxh(Integer value) {
        this.jkxh = value;
    }
    /** 获取:缴款序号 */
    public Integer getJkxh() {
        return jkxh;
    }

    /** 设置:机构代码  */
    public void setJgid(Integer value) {
        this.jgid = value;
    }
    /** 获取:机构代码 */
    public Integer getJgid() {
        return jgid;
    }

    /** 设置:作废工号  */
    public void setZfgh(String value) {
        this.zfgh = value;
    }
    /** 获取:作废工号 */
    public String getZfgh() {
        return zfgh;
    }

    /** 设置:作废日期  */
    public void setZfrq(Timestamp value) {
        this.zfrq = value;
    }
    /** 获取:作废日期 */
    public Timestamp getZfrq() {
        return zfrq;
    }

    /** 设置:结账日期  */
    public void setJzrq(Timestamp value) {
        this.jzrq = value;
    }
    /** 获取:结账日期 */
    public Timestamp getJzrq() {
        return jzrq;
    }

    /** 设置:汇总日期  */
    public void setHzrq(Timestamp value) {
        this.hzrq = value;
    }
    /** 获取:汇总日期 */
    public Timestamp getHzrq() {
        return hzrq;
    }

    /** 设置:门诊类别，默认0  */
    public void setMzlb(Integer value) {
        this.mzlb = value;
    }
    /** 获取:门诊类别，默认0 */
    public Integer getMzlb() {
        return mzlb;
    }


}
