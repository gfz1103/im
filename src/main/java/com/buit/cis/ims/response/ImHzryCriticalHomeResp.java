   
package com.buit.cis.ims.response;

import java.sql.Timestamp;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


/**
 * 类名称：ImHzryCriticalHomeResp<br> 
 * 类描述：住院_首页<br>
 * @author GONGFANGZHOU
 */
@ApiModel(value="住院_危急值_CriticalHomeResp")
public class ImHzryCriticalHomeResp {

	@ApiModelProperty(value="住院号")
    private Integer zyh;
	
	@ApiModelProperty(value="危急值单号")
    private Integer wjzdh;
	
	@ApiModelProperty(value="床号")
    private String brch;
	
	@ApiModelProperty(value="病人姓名")
    private String brxm;

	@ApiModelProperty(value="病人科室")
	private Integer brks;
	
	@ApiModelProperty(value="危急值内容")
    private String wjzxmmc;
	
	@ApiModelProperty(value="报告医生")
    private Integer fsrdm;
	
	@ApiModelProperty(value="报告时间")
    private Timestamp fssj;
	
	@ApiModelProperty(value="状态")
    private Integer zt;
	
	@ApiModelProperty(value="确认医生")
    private Integer czrdm;
	
	@ApiModelProperty(value="确认时间")
    private Timestamp czsj;
	
	@ApiModelProperty(value="处理意见")
    private String czcs;

	public Integer getZyh() {
		return zyh;
	}

	public void setZyh(Integer zyh) {
		this.zyh = zyh;
	}

	public Integer getWjzdh() {
		return wjzdh;
	}

	public void setWjzdh(Integer wjzdh) {
		this.wjzdh = wjzdh;
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

	public String getWjzxmmc() {
		return wjzxmmc;
	}

	public void setWjzxmmc(String wjzxmmc) {
		this.wjzxmmc = wjzxmmc;
	}

	public Integer getFsrdm() {
		return fsrdm;
	}

	public void setFsrdm(Integer fsrdm) {
		this.fsrdm = fsrdm;
	}

	public Timestamp getFssj() {
		return fssj;
	}

	public void setFssj(Timestamp fssj) {
		this.fssj = fssj;
	}

	public Integer getZt() {
		return zt;
	}

	public void setZt(Integer zt) {
		this.zt = zt;
	}

	public Integer getCzrdm() {
		return czrdm;
	}

	public void setCzrdm(Integer czrdm) {
		this.czrdm = czrdm;
	}

	public Timestamp getCzsj() {
		return czsj;
	}

	public void setCzsj(Timestamp czsj) {
		this.czsj = czsj;
	}

	public String getCzcs() {
		return czcs;
	}

	public void setCzcs(String czcs) {
		this.czcs = czcs;
	}

}
