package com.buit.cis.ims.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 
* @ClassName: ImHzryMedicalBookReq
* @Description: TODO 医嘱本打印病人信息
* @author 龚方舟
* @date 2020年5月25日 下午3:00:43
*
 */
@ApiModel(value="住院_病人入院_medicalbookReq")
public class ImHzryMedicalBookReq {
	
	@ApiModelProperty(value="机构id", hidden = true)
    private Integer jgid;

    @ApiModelProperty(value="出院状态")
    private Integer cyzt;
    
    @ApiModelProperty(value="是否转科")
    private Integer sfzk;
    
    @ApiModelProperty(value="病人病区")
    private Integer brbq;
    
    @ApiModelProperty(value="住院号码或病人床号")
    private String comprehensive;

	public Integer getJgid() {
		return jgid;
	}

	public void setJgid(Integer jgid) {
		this.jgid = jgid;
	}

	public Integer getCyzt() {
		return cyzt;
	}

	public void setCyzt(Integer cyzt) {
		this.cyzt = cyzt;
	}

	public Integer getSfzk() {
		return sfzk;
	}

	public void setSfzk(Integer sfzk) {
		this.sfzk = sfzk;
	}

	public Integer getBrbq() {
		return brbq;
	}

	public void setBrbq(Integer brbq) {
		this.brbq = brbq;
	}

	public String getComprehensive() {
		return comprehensive;
	}

	public void setComprehensive(String comprehensive) {
		this.comprehensive = comprehensive;
	} 

}
