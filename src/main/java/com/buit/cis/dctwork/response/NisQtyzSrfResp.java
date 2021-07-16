package com.buit.cis.dctwork.response;

import com.buit.commons.PageQuery;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 类名称：NisQtyz<br> 
 * 类描述：
 * @author GONGFANGZHOU 
 */
@ApiModel(value="病区其他医嘱_srfResp")
public class NisQtyzSrfResp  extends  PageQuery{
	@ApiModelProperty(value="费用序号")
    private Integer fyxh;
	
	@ApiModelProperty(value="医嘱名称")
    private String yzmc;
	
	@ApiModelProperty(value="药品名称")
    private String ypmc;

	@ApiModelProperty(value="特殊医嘱")
    private Integer tsyz;

	public Integer getFyxh() {
		return fyxh;
	}

	public void setFyxh(Integer fyxh) {
		this.fyxh = fyxh;
	}

	public String getYzmc() {
		return yzmc;
	}

	public void setYzmc(String yzmc) {
		this.yzmc = yzmc;
	}

	public String getYpmc() {
		return ypmc;
	}

	public void setYpmc(String ypmc) {
		this.ypmc = ypmc;
	}

	public Integer getTsyz() {
		return tsyz;
	}

	public void setTsyz(Integer tsyz) {
		this.tsyz = tsyz;
	}
	
}
