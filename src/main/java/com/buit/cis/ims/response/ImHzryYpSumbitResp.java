package com.buit.cis.ims.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


/**
 * 类名称：ImHzry<br>
 * 类描述：住院_病人入院药品医嘱提交返回列表<br>
 * @author ZHOUHAISHENG
 */
@ApiModel(value="住院_病人入院_ypsumbitResp")
public class ImHzryYpSumbitResp {
    @ApiModelProperty(value="住院号 | 该住院号为内码")
    private Integer zyh;
  
    @ApiModelProperty(value="病人床号")
    private String brch;
    
    @ApiModelProperty(value="病人姓名")
    private String brxm;

	public Integer getZyh() {
		return zyh;
	}

	public void setZyh(Integer zyh) {
		this.zyh = zyh;
	}

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
  
}
