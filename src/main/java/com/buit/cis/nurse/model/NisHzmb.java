package com.buit.cis.nurse.model;

import com.buit.commons.PageQuery;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 类名称：NisHzmb<br> 
 * 类描述：患者护理记录模板表
 * @author GONGFANGZHOU 
 * 
 */
@ApiModel(value="患者护理记录模板表")
public class NisHzmb  extends  PageQuery{
	@ApiModelProperty(value="主键")
    /** 主键 */
    private Integer jlxh;
	@ApiModelProperty(value="住院号")
    /** 住院号 */
    private Integer zyh;
	@ApiModelProperty(value="模板类型")
    /** 模板类型 */
    private String mblx;
	@ApiModelProperty(value="模板名称")
    /** 模板名称 */
    private String mbmc;
	@ApiModelProperty(value="机构id")
    /** 机构id */
    private Integer jgid;

    /** 设置:主键  */
    public void setJlxh(Integer value) {
        this.jlxh = value;
    }
    /** 获取:主键 */
    public Integer getJlxh() {
        return jlxh;
    }

    /** 设置:住院号  */
    public void setZyh(Integer value) {
        this.zyh = value;
    }
    /** 获取:住院号 */
    public Integer getZyh() {
        return zyh;
    }

    /** 设置:模板类型  */
    public void setMblx(String value) {
        this.mblx = value;
    }
    /** 获取:模板类型 */
    public String getMblx() {
        return mblx;
    }

    /** 设置:模板名称  */
    public void setMbmc(String value) {
        this.mbmc = value;
    }
    /** 获取:模板名称 */
    public String getMbmc() {
        return mbmc;
    }

    /** 设置:机构id  */
    public void setJgid(Integer value) {
        this.jgid = value;
    }
    /** 获取:机构id */
    public Integer getJgid() {
        return jgid;
    }


}