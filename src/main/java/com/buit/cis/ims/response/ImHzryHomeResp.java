   
package com.buit.cis.ims.response;

import java.sql.Timestamp;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


/**
 * 类名称：ImHzryHomeResp<br> 
 * 类描述：住院_首页<br>
 * @author GONGFANGZHOU
 */
@ApiModel(value="住院_首页_HomeResp")
public class ImHzryHomeResp {

	@ApiModelProperty(value="住院号")
    private Integer zyh;
	
	@ApiModelProperty(value="床号")
    private String brch;
	
	@ApiModelProperty(value="病人姓名")
    private String brxm;
	
	@ApiModelProperty(value="状态")
	private Integer cyzt;
	
    @ApiModelProperty(value="性别")
	private Integer brxb;

	@ApiModelProperty(value="年龄")
	private String rynl;
	
	@ApiModelProperty(value="病人科室")
	private Integer brks;
	
	@ApiModelProperty(value="住院医师")
	private Integer zyys;
	
	@ApiModelProperty(value="入院情况")
	private Integer ryqk;
	
	@ApiModelProperty(value="时间")
	private Timestamp sj;
	
	@ApiModelProperty(value="出院判别")
	private Integer cypb;

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

	public Integer getCyzt() {
		return cyzt;
	}

	public void setCyzt(Integer cyzt) {
		this.cyzt = cyzt;
	}

	public Integer getBrxb() {
		return brxb;
	}

	public void setBrxb(Integer brxb) {
		this.brxb = brxb;
	}

	public String getRynl() {
		return rynl;
	}

	public void setRynl(String rynl) {
		this.rynl = rynl;
	}

	public Integer getBrks() {
		return brks;
	}

	public void setBrks(Integer brks) {
		this.brks = brks;
	}

	public Integer getZyys() {
		return zyys;
	}

	public void setZyys(Integer zyys) {
		this.zyys = zyys;
	}

	public Integer getRyqk() {
		return ryqk;
	}

	public void setRyqk(Integer ryqk) {
		this.ryqk = ryqk;
	}

	public Timestamp getSj() {
		return sj;
	}

	public void setSj(Timestamp sj) {
		this.sj = sj;
	}

	public Integer getCypb() {
		return cypb;
	}

	public void setCypb(Integer cypb) {
		this.cypb = cypb;
	}

}
