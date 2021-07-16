package com.buit.cis.dctwork.model;

import java.math.BigDecimal;
import com.buit.commons.PageQuery;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 类名称：DicXypz<br> 
 * 类描述：血液品种表
 * @author GONGFANGZHOU 
 * 
 */
@ApiModel(value="血液品种表")
public class DicXypz  extends  PageQuery{
	@ApiModelProperty(value="医疗机构代码")
    /** 医疗机构代码 */
    private String yljgd;
	@ApiModelProperty(value="输血品种代码")
    /** 输血品种代码 */
    private String sxpzdm;
	@ApiModelProperty(value="输血品种名称")
    /** 输血品种名称 */
    private String sxpzmc;
	@ApiModelProperty(value="单位")
    /** 单位 */
    private String dw;
	@ApiModelProperty(value="单价")
    /** 单价 */
    private BigDecimal dj;
	@ApiModelProperty(value="状态(0 停用/1 启用)")
    /** 状态(0 停用/1 启用) */
    private String zt;

    /** 设置:医疗机构代码  */
    public void setYljgd(String value) {
        this.yljgd = value;
    }
    /** 获取:医疗机构代码 */
    public String getYljgd() {
        return yljgd;
    }

    /** 设置:输血品种代码  */
    public void setSxpzdm(String value) {
        this.sxpzdm = value;
    }
    /** 获取:输血品种代码 */
    public String getSxpzdm() {
        return sxpzdm;
    }

    /** 设置:输血品种名称  */
    public void setSxpzmc(String value) {
        this.sxpzmc = value;
    }
    /** 获取:输血品种名称 */
    public String getSxpzmc() {
        return sxpzmc;
    }

    /** 设置:单位  */
    public void setDw(String value) {
        this.dw = value;
    }
    /** 获取:单位 */
    public String getDw() {
        return dw;
    }

    /** 设置:单价  */
    public void setDj(BigDecimal value) {
        this.dj = value;
    }
    /** 获取:单价 */
    public BigDecimal getDj() {
        return dj;
    }

    /** 设置:状态(0 停用/1 启用)  */
    public void setZt(String value) {
        this.zt = value;
    }
    /** 获取:状态(0 停用/1 启用) */
    public String getZt() {
        return zt;
    }


}
