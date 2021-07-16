   
package com.buit.cis.ims.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


/**
 * 类名称：ImHzryWardHomeResp<br> 
 * 类描述：住院_首页<br>
 * @author GONGFANGZHOU
 */
@ApiModel(value="住院_问题医嘱_WardHomeResp")
public class ImHzryWardHomeResp {

	@ApiModelProperty(value="住院号")
    private Integer zyh;
	
	@ApiModelProperty(value="床号")
    private String brch;
	
	@ApiModelProperty(value="病人姓名")
    private String brxm;

	@ApiModelProperty(value="病人科室")
	private Integer brks;
	
	@ApiModelProperty(value="问题医嘱")
    private String yzmc;

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

	public Integer getBrks() {
		return brks;
	}

	public void setBrks(Integer brks) {
		this.brks = brks;
	}

	public String getYzmc() {
		return yzmc;
	}

	public void setYzmc(String yzmc) {
		this.yzmc = yzmc;
	}
	
	
	
}
