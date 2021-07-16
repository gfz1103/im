package com.buit.cis.ims.model;

import com.buit.commons.PageQuery;

import java.sql.Timestamp;

/**
 * 类名称：ImJszf<br> 
 * 类描述：住院_结算作废
 * @author ZHOUHAISHENG 
 * @ApiModel(value="住院_结算作废")
 */
public class ImJszf  extends PageQuery {
	//@ApiModelProperty(value="住院号")
    /** 住院号 */
    private Integer zyh;
	//@ApiModelProperty(value="结算次数")
    /** 结算次数 */
    private Integer jscs;
	//@ApiModelProperty(value="机构代码")
    /** 机构代码 */
    private Integer jgid;
	//@ApiModelProperty(value="作废工号")
    /** 作废工号 */
    private Integer zfgh;
	//@ApiModelProperty(value="作废日期")
    /** 作废日期 */
    private Timestamp zfrq;
	//@ApiModelProperty(value="结账日期")
    /** 结账日期 */
    private Timestamp jzrq;
	//@ApiModelProperty(value="汇总日期")
    /** 汇总日期 */
    private Timestamp hzrq;
	//@ApiModelProperty(value="mzlb")
    /** mzlb */
    private Integer mzlb;

    /** 设置:住院号  */
    public void setZyh(Integer value) {
        this.zyh = value;
    }
    /** 获取:住院号 */
    public Integer getZyh() {
        return zyh;
    }

    /** 设置:结算次数  */
    public void setJscs(Integer value) {
        this.jscs = value;
    }
    /** 获取:结算次数 */
    public Integer getJscs() {
        return jscs;
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
    public void setZfgh(Integer value) {
        this.zfgh = value;
    }
    /** 获取:作废工号 */
    public Integer getZfgh() {
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

    /** 设置:mzlb  */
    public void setMzlb(Integer value) {
        this.mzlb = value;
    }
    /** 获取:mzlb */
    public Integer getMzlb() {
        return mzlb;
    }


}
