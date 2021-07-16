   
package com.buit.cis.dctwork.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


/**
 * 类名称：NisTj02Resp<br> 
 * 类描述：<br>
 * @author GONGFANGZHOU
 */
@ApiModel(value="病区_提交明细退回_HzInfoResp")
public class NisTj02HzInfoResp {

	@ApiModelProperty(value="病人床号")
    private String brch;
	
	@ApiModelProperty(value="病人姓名")
    private String brxm;
	
	@ApiModelProperty(value="住院号")
    private Integer zyh;

	public String getBrch() {
		return brch;
	}

	public void setBrch(String brch) {
		this.brch = brch;
	}

	public String getBrxm() {
		return brxm;
	}

	public void setBrxm(String brxm) {
		this.brxm = brxm;
	}

	public Integer getZyh() {
		return zyh;
	}

	public void setZyh(Integer zyh) {
		this.zyh = zyh;
	}
	
	
}
