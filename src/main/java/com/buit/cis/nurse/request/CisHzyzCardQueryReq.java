
package com.buit.cis.nurse.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiParam;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.web.bind.annotation.RequestParam;


/**
 * 类名称：CisHzyzCardQueryReq<br>
 * 类描述：患者医嘱卡片<br>
 * @author GONGFANGZHOU
 */
@ApiModel(value="患者医嘱卡片_CardQueryReq")
public class CisHzyzCardQueryReq {
	
	@ApiModelProperty(value="病区代码")
    private Integer ksdm;
	
	@ApiModelProperty(value="床号住院号码姓名")
    private String comprehensive;
	
	@ApiModelProperty(value="科室代码")
    private Integer brks;

    @ApiModelProperty(value="床位组号")
    private Integer cwzh;
    
    @ApiModelProperty(value="机构id", hidden = true)
    private Integer jgid;

	public Integer getKsdm() {
		return ksdm;
	}

	public void setKsdm(Integer ksdm) {
		this.ksdm = ksdm;
	}

	public String getComprehensive() {
		return comprehensive;
	}

	public void setComprehensive(String comprehensive) {
		this.comprehensive = comprehensive;
	}

	public Integer getBrks() {
		return brks;
	}

	public void setBrks(Integer brks) {
		this.brks = brks;
	}

	public Integer getCwzh() {
		return cwzh;
	}

	public void setCwzh(Integer cwzh) {
		this.cwzh = cwzh;
	}

	public Integer getJgid() {
		return jgid;
	}

	public void setJgid(Integer jgid) {
		this.jgid = jgid;
	}
    
    
}
