   
package com.buit.cis.dctwork.request;

import java.sql.Timestamp;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;


/**
 * 类名称：CisHzyz<br> 
 * 类描述：住院_病区医嘱<br>
 * @author GONGFANGZHOU
 */
@ApiModel(value="住院_病区医嘱_StopCheckReq")
public class CisHzyzStopCheckReq {

    @ApiModelProperty(value="记录序号")
	@NotNull(message = "记录序号不能为空")
    private Integer jlxh;
   
    @ApiModelProperty(value="开嘱医生")
    private String ysgh;
    
    @ApiModelProperty(value="停嘱时间")
    @NotNull(message = "停嘱时间不能为空")
    private Timestamp tzsj;
    
    @ApiModelProperty(value="医嘱类型")
    private Integer yzlx;
    
    @NotNull(message = "长期临时医嘱类型不能为空")
    @ApiModelProperty(value="临时医嘱")
    private Integer lsyz;
    
    @ApiModelProperty(value="皮试判别")
    private Integer pspb;
    
    @ApiModelProperty(value="过敏药物类别")
    private Integer gmywlb;
    
    @ApiModelProperty(value="病人id")
    private Integer brid;
    
	public Integer getJlxh() {
		return jlxh;
	}

	public void setJlxh(Integer jlxh) {
		this.jlxh = jlxh;
	}

	public String getYsgh() {
		return ysgh;
	}

	public void setYsgh(String ysgh) {
		this.ysgh = ysgh;
	}

	public Timestamp getTzsj() {
		return tzsj;
	}

	public void setTzsj(Timestamp tzsj) {
		this.tzsj = tzsj;
	}

	public Integer getYzlx() {
		return yzlx;
	}

	public void setYzlx(Integer yzlx) {
		this.yzlx = yzlx;
	}

	public Integer getLsyz() {
		return lsyz;
	}

	public void setLsyz(Integer lsyz) {
		this.lsyz = lsyz;
	}

	public Integer getPspb() {
		return pspb;
	}

	public void setPspb(Integer pspb) {
		this.pspb = pspb;
	}

	public Integer getGmywlb() {
		return gmywlb;
	}

	public void setGmywlb(Integer gmywlb) {
		this.gmywlb = gmywlb;
	}

	public Integer getBrid() {
		return brid;
	}

	public void setBrid(Integer brid) {
		this.brid = brid;
	}
    
    
}
