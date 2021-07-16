package com.buit.cis.ims.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.sql.Timestamp;

import javax.validation.constraints.NotNull;


/**
 * 类名称：ImRcjl<br> 
 * 类描述：住院_临床病人入出记录_出院<br>
 * @author GONGFANGZHOU
 */
@ApiModel(value="住院_临床病人入出记录_cyReq")
public class ImRcjlCyReq {
    @ApiModelProperty(value="出院日期")
    private Timestamp xcyrq;
    @NotNull(message = "住院号不能为空")
    @ApiModelProperty(value="住院号")
    private Integer zyh;
    @ApiModelProperty(value="出院方式 | 出院方式在CZLX=-1时使用，对应GY_DMZD中的代码（DMLB = 23）")
    private Integer cyfs;
    @ApiModelProperty(value="备注信息")
    private String bzxx;
    @ApiModelProperty(value="随访信息")
    private String sf;
    
	public Timestamp getXcyrq() {
		return xcyrq;
	}
	public void setXcyrq(Timestamp xcyrq) {
		this.xcyrq = xcyrq;
	}
	public Integer getZyh() {
		return zyh;
	}
	public void setZyh(Integer zyh) {
		this.zyh = zyh;
	}
	public Integer getCyfs() {
		return cyfs;
	}
	public void setCyfs(Integer cyfs) {
		this.cyfs = cyfs;
	}
	public String getBzxx() {
		return bzxx;
	}
	public void setBzxx(String bzxx) {
		this.bzxx = bzxx;
	}
	public String getSf() {
		return sf;
	}
	public void setSf(String sf) {
		this.sf = sf;
	}

}
