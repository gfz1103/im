package com.buit.cis.ims.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


/**
 * UseResp
 * @ClassName: ImCwszUseResp
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author 龚方舟
 * @date 2021年4月28日 下午1:24:59
 *
 */
@ApiModel(value = "住院_床位设置_UseResp")
public class ImCwszUseResp {

    @ApiModelProperty(value = "病区代码")
    private Integer ksdm;
    
    @ApiModelProperty(value = "已使用")
    private Integer ysy;
    
    @ApiModelProperty(value = "未使用")
    private Integer wsy;

	public Integer getKsdm() {
		return ksdm;
	}

	public void setKsdm(Integer ksdm) {
		this.ksdm = ksdm;
	}

	public Integer getYsy() {
		return ysy;
	}

	public void setYsy(Integer ysy) {
		this.ysy = ysy;
	}

	public Integer getWsy() {
		return wsy;
	}

	public void setWsy(Integer wsy) {
		this.wsy = wsy;
	}
    
}
