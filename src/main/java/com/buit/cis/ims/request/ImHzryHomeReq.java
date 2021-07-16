package com.buit.cis.ims.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;



/**
 * 类名称：ImHzry<br>
 * 类描述：住院_病人入院<br>
 * @author ZHOUHAISHENG
 */
@ApiModel(value="住院_首页危急值_HomeReq")
public class ImHzryHomeReq {
    @ApiModelProperty(value="机构代码", hidden = true)
    private Integer jgid;
	
	@ApiModelProperty(value="状态(0:全部,1:出院,2:待出院,3:入院,4:待入院)")
	private Integer zt;
	
	@ApiModelProperty(value="今日出入院(1:入,2:出)")
	private Integer type;
	
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

	public Integer getZt() {
		return zt;
	}

	public void setZt(Integer zt) {
		this.zt = zt;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
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
