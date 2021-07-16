   
package com.buit.cis.ims.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


/**
 * 类名称：ImZkjlHomeReq<br> 
 * 类描述：住院_转科记录<br>
 * @author GONGFANGZHOU
 */
@ApiModel(value="住院_转科记录_HomeReq")
public class ImZkjlHomeReq {
	
	@ApiModelProperty(value="转出状态(0:全部,1:待转出,2:待转入,3:已转出,4:已转入)")
	private Integer zt;
	
	@ApiModelProperty(value="机构id", hidden = true)
	private Integer jgid;
	
	@ApiModelProperty(value="病区代码")	
	private Integer bqdm;
	
	@ApiModelProperty(value="科室代码")	
	private Integer ksdm;

	public Integer getZt() {
		return zt;
	}

	public void setZt(Integer zt) {
		this.zt = zt;
	}

	public Integer getJgid() {
		return jgid;
	}

	public void setJgid(Integer jgid) {
		this.jgid = jgid;
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
