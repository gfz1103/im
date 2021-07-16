   
package com.buit.cis.ims.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


/**
 * 类名称：ImZkjl<br> 
 * 类描述：住院_转科记录_保存<br>
 * @author GONGFANGZHOU
 */
@ApiModel(value="住院_转科记录_req")
public class ImZkjlReq {
	
    @ApiModelProperty(value="住院号")
    private Integer zyh;

	@ApiModelProperty(value="住院号码")	
	private String zyhm;
	
	@ApiModelProperty(value="病人床号")
	private String brch;
	
	@ApiModelProperty(value="病人姓名")
	private String brxm;
	
	@ApiModelProperty(value="病人科室")
	private Integer brks;
	
	@ApiModelProperty(value="病人病区")
	private Integer brbq;
	
	@ApiModelProperty(value="转入医生")
	private Integer zyys;
	
	@ApiModelProperty(value="转后科室")
	private Integer hhks;
	
	@ApiModelProperty(value="转后病区")
	private Integer hhbq;
	
	@ApiModelProperty(value="转后医生")
	private Integer hhys;

	public Integer getZyh() {
		return zyh;
	}

	public void setZyh(Integer zyh) {
		this.zyh = zyh;
	}

	public String getZyhm() {
		return zyhm;
	}

	public void setZyhm(String zyhm) {
		this.zyhm = zyhm;
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

	public Integer getBrbq() {
		return brbq;
	}

	public void setBrbq(Integer brbq) {
		this.brbq = brbq;
	}

	public Integer getZyys() {
		return zyys;
	}

	public void setZyys(Integer zyys) {
		this.zyys = zyys;
	}

	public Integer getHhks() {
		return hhks;
	}

	public void setHhks(Integer hhks) {
		this.hhks = hhks;
	}

	public Integer getHhbq() {
		return hhbq;
	}

	public void setHhbq(Integer hhbq) {
		this.hhbq = hhbq;
	}

	public Integer getHhys() {
		return hhys;
	}

	public void setHhys(Integer hhys) {
		this.hhys = hhys;
	}
	
	
   
}
