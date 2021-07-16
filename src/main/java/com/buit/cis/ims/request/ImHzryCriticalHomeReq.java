package com.buit.cis.ims.request;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;



/**
 * 类名称：ImHzry<br>
 * 类描述：住院_病人入院<br>
 * @author ZHOUHAISHENG
 */
@ApiModel(value="住院_首页危急值_CriticalHomeReq")
public class ImHzryCriticalHomeReq {
   
    @ApiModelProperty(value="机构代码", hidden = true)
    private Integer jgid;
    
    @ApiModelProperty(value="开始时间")	
	private String beginDate;
	
	@ApiModelProperty(value="结束时间")
	private String endDate;
	
	@ApiModelProperty(value="状态(1:未处理,2:已处理)")
	private Integer zt;
	
	@ApiModelProperty(value="病区代码")
    private Integer bqdm;
    
    @ApiModelProperty(value="科室代码")
    private Integer ksdm;

	public Integer getJgid() {
		return jgid;
	}

	public void setJgid(Integer jgid) {
		this.jgid = jgid;
	}

	public String getBeginDate() {
		return beginDate;
	}

	public void setBeginDate(String beginDate) {
		this.beginDate = beginDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public Integer getZt() {
		return zt;
	}

	public void setZt(Integer zt) {
		this.zt = zt;
	}
	
	public Integer getBqdm() {
		return bqdm;
	}

	public void setBqdm(Integer bqdm) {
		this.bqdm = bqdm;
	}

	public Integer getKsdm() {
		return ksdm;
	}

	public void setKsdm(Integer ksdm) {
		this.ksdm = ksdm;
	}
   
	
}
