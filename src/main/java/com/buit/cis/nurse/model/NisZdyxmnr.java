package com.buit.cis.nurse.model;

import com.buit.commons.PageQuery;

import io.swagger.annotations.ApiModelProperty;

/**
 * 类名称：NisZdyxmnr<br> 
 * 类描述：自定义专科护理内容
 * @author GONGFANGZHOU 
 * @ApiModel(value="自定义专科护理内容")
 */
public class NisZdyxmnr  extends  PageQuery{
	@ApiModelProperty(value="自定义项目内容代码(主键)")
    /** 自定义项目内容代码(主键) */
    private Integer xmnrdm;
    @ApiModelProperty(value="项目内容序号")
    /** 项目内容序号 */
    private String xmnrxh;
	@ApiModelProperty(value="项目内容名称")
    /** 项目内容名称 */
    private String xmnrmc;
	@ApiModelProperty(value="拼音代码")
    /** 拼音代码 */
    private String pydm;
	@ApiModelProperty(value="机构id")
    /** 机构id */
    private Integer jgid;
	@ApiModelProperty(value="nis_zdyxm(主键关联)")
    /** nis_zdyxm(主键关联) */
    private Integer xmdm;
	@ApiModelProperty(value="备注信息")
    /** 备注信息 */
    private String bzxx;
	@ApiModelProperty(value="作废判别(1:是0:否)")
    /** 作废判别(1:是0:否) */
    private Integer zfpb;

    /** 设置:自定义项目内容代码(主键)  */
    public void setXmnrdm(Integer value) {
        this.xmnrdm = value;
    }
    /** 获取:自定义项目内容代码(主键) */
    public Integer getXmnrdm() {
        return xmnrdm;
    }
    /**
     * 设置:项目内容序号
     */
	public void setXmnrxh(String xmnrxh) {
		this.xmnrxh = xmnrxh;
	}
	/**
     * 获取:项目内容序号
     */
	public String getXmnrxh() {
		return xmnrxh;
	}
    /** 设置:项目内容名称  */
    public void setXmnrmc(String value) {
        this.xmnrmc = value;
    }
    /** 获取:项目内容名称 */
    public String getXmnrmc() {
        return xmnrmc;
    }

    /** 设置:拼音代码  */
    public void setPydm(String value) {
        this.pydm = value;
    }
    /** 获取:拼音代码 */
    public String getPydm() {
        return pydm;
    }

    /** 设置:机构id  */
    public void setJgid(Integer value) {
        this.jgid = value;
    }
    /** 获取:机构id */
    public Integer getJgid() {
        return jgid;
    }

    /** 设置:nis_zdyxm(主键关联)  */
    public void setXmdm(Integer value) {
        this.xmdm = value;
    }
    /** 获取:nis_zdyxm(主键关联) */
    public Integer getXmdm() {
        return xmdm;
    }

    /** 设置:备注信息  */
    public void setBzxx(String value) {
        this.bzxx = value;
    }
    /** 获取:备注信息 */
    public String getBzxx() {
        return bzxx;
    }

    /** 设置:作废判别(1:是0:否)  */
    public void setZfpb(Integer value) {
        this.zfpb = value;
    }
    /** 获取:作废判别(1:是0:否) */
    public Integer getZfpb() {
        return zfpb;
    }


}