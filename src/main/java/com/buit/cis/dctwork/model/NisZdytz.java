package com.buit.cis.dctwork.model;

import com.buit.commons.PageQuery;

import io.swagger.annotations.ApiModelProperty;

/**
 * 类名称：NisZdytz<br> 
 * 类描述：体温单自定义表
 * @author GONGFANGZHOU 
 * @ApiModel(value="体温单自定义表")
 */
public class NisZdytz  extends  PageQuery{
	/**
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
	*/
	private static final long serialVersionUID = 1L;
	@ApiModelProperty(value="自定义id")
    /** 自定义id */
    private Integer zdyid;
	@ApiModelProperty(value="住院号")
    /** 住院号 */
    private Integer zyh;
	@ApiModelProperty(value="自定义名称")
    /** 自定义名称 */
    private String zdymc;
	@ApiModelProperty(value="当前周数")
    /** 当前周数 */
    private Integer dqzs;
	@ApiModelProperty(value="机构id")
    /** 机构id */
    private Integer jgid;
	@ApiModelProperty(value="拼音代码")
    /** 拼音代码 */
    private String pydm;
	@ApiModelProperty(value="项目代码")
    /** 项目代码 */
    private Integer xmdm;
	

    /** 设置:自定义id  */
    public void setZdyid(Integer value) {
        this.zdyid = value;
    }
    /** 获取:自定义id */
    public Integer getZdyid() {
        return zdyid;
    }

    /** 设置:住院号  */
    public void setZyh(Integer value) {
        this.zyh = value;
    }
    /** 获取:住院号 */
    public Integer getZyh() {
        return zyh;
    }

    /** 设置:自定义名称  */
    public void setZdymc(String value) {
        this.zdymc = value;
    }
    /** 获取:自定义名称 */
    public String getZdymc() {
        return zdymc;
    }

    /** 设置:当前周数  */
    public void setDqzs(Integer value) {
        this.dqzs = value;
    }
    /** 获取:当前周数 */
    public Integer getDqzs() {
        return dqzs;
    }

    /** 设置:机构id  */
    public void setJgid(Integer value) {
        this.jgid = value;
    }
    /** 获取:机构id */
    public Integer getJgid() {
        return jgid;
    }

    /** 设置:拼音代码  */
    public void setPydm(String value) {
        this.pydm = value;
    }
    /** 获取:拼音代码 */
    public String getPydm() {
        return pydm;
    }
    /** 设置:项目代码  */
	public Integer getXmdm() {
		return xmdm;
	}
	/** 设置:项目代码  */
	public void setXmdm(Integer xmdm) {
		this.xmdm = xmdm;
	}


}