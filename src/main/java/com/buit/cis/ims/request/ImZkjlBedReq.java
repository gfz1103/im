   
package com.buit.cis.ims.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


/**
 * 类名称：ImZkjl<br> 
 * 类描述：住院_转科记录_床位转入<br>
 * @author GONGFANGZHOU
 */
@ApiModel(value="住院_转科记录_bedreq")
public class ImZkjlBedReq {
	
    @ApiModelProperty(value="住院号")
    private Integer zyh;

    @ApiModelProperty(value="换前床位")
    private String cwhmOld;
    
    @ApiModelProperty(value="换后床位")
    private String cwhmNew;
    
    @ApiModelProperty(value="换前科室")
	private Integer hqks;
	
	@ApiModelProperty(value="换后科室")
	private Integer hhks;
	
	@ApiModelProperty(value="换前病区")
	private Integer hqbq;	
	
	@ApiModelProperty(value="换后病区")
	private Integer hhbq;

	public Integer getZyh() {
		return zyh;
	}

	public void setZyh(Integer zyh) {
		this.zyh = zyh;
	}

	public String getCwhmOld() {
		return cwhmOld;
	}

	public void setCwhmOld(String cwhmOld) {
		this.cwhmOld = cwhmOld;
	}

	public String getCwhmNew() {
		return cwhmNew;
	}

	public void setCwhmNew(String cwhmNew) {
		this.cwhmNew = cwhmNew;
	}

	public Integer getHqks() {
		return hqks;
	}

	public void setHqks(Integer hqks) {
		this.hqks = hqks;
	}

	public Integer getHhks() {
		return hhks;
	}

	public void setHhks(Integer hhks) {
		this.hhks = hhks;
	}

	public Integer getHqbq() {
		return hqbq;
	}

	public void setHqbq(Integer hqbq) {
		this.hqbq = hqbq;
	}

	public Integer getHhbq() {
		return hhbq;
	}

	public void setHhbq(Integer hhbq) {
		this.hhbq = hhbq;
	}
	
}
