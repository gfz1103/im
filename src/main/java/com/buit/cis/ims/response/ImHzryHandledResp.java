package com.buit.cis.ims.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 患者入院首页
 * @ClassName: ImHzryHandledResp
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author 龚方舟
 * @date 2020年11月2日 下午5:02:02
 *
 */
@ApiModel(value="患者入院首页_HandledResp")
public class ImHzryHandledResp {

	@ApiModelProperty(value="未处理数量")
	private Integer wclCount;
	
	@ApiModelProperty(value="已处理数量")
	private Integer yclCount;

	public Integer getWclCount() {
		return wclCount;
	}

	public void setWclCount(Integer wclCount) {
		this.wclCount = wclCount;
	}

	public Integer getYclCount() {
		return yclCount;
	}

	public void setYclCount(Integer yclCount) {
		this.yclCount = yclCount;
	}
	
	
}
