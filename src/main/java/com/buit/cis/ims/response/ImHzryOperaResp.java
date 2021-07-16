   
package com.buit.cis.ims.response;

import java.sql.Timestamp;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


/**
 * 类名称：ImHzryOperaResp<br> 
 * 类描述：住院_手术首页<br>
 * @author GONGFANGZHOU
 */
@ApiModel(value="住院_手术首页_OperaResp")
public class ImHzryOperaResp {
	
	@ApiModelProperty(value="申请单号")
    private Integer sqdh;
	
	@ApiModelProperty(value="住院号")
    private Integer zyh;
	
    @ApiModelProperty(value="病人床号")
	private String brch;
	
	@ApiModelProperty(value="病人姓名")
	private String brxm;
	
	@ApiModelProperty(value="病人科室")
	private Integer brks;
	
	@ApiModelProperty(value="手术名称")
	private String ssmc;
	
	@ApiModelProperty(value="手术科室")
	private Integer ssks;
	
	@ApiModelProperty(value="手术医生")
	private Integer ssys;
	
	@ApiModelProperty(value="手术日期")
	private Timestamp ssrq;

	public Integer getSqdh() {
		return sqdh;
	}

	public void setSqdh(Integer sqdh) {
		this.sqdh = sqdh;
	}

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

	public String getSsmc() {
		return ssmc;
	}

	public void setSsmc(String ssmc) {
		this.ssmc = ssmc;
	}

	public Integer getSsks() {
		return ssks;
	}

	public void setSsks(Integer ssks) {
		this.ssks = ssks;
	}

	public Integer getSsys() {
		return ssys;
	}

	public void setSsys(Integer ssys) {
		this.ssys = ssys;
	}

	public Timestamp getSsrq() {
		return ssrq;
	}

	public void setSsrq(Timestamp ssrq) {
		this.ssrq = ssrq;
	}
	
	
	
}
