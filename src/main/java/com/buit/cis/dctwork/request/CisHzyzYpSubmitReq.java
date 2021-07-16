   
package com.buit.cis.dctwork.request;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


/**
 * 类名称：CisHzyz<br> 
 * 类描述：住院_病区医嘱_药品提交<br>
 * @author GONGFANGZHOU
 */
@ApiModel(value="住院_病区医嘱_ypsubmitReq")
public class CisHzyzYpSubmitReq {

    @ApiModelProperty(value="住院号")
    private Integer zyh;
	
	@ApiModelProperty(value="领药日期")
	private Date lyrq;
	
	@ApiModelProperty(value="发药方式(对应IM_FYFS的FYFS,0:全部)")
	private Integer fyfs;
	
	@ApiModelProperty(value="医嘱类型(2:全部医嘱,0:长期医嘱,1:临时医嘱,3:急诊用药,4:出院带药)")
	private Integer lsyz;
	
	@ApiModelProperty(value="药房识别(对应PHAR_YFLB的YFSB,0:全部)")
	private Integer yfsb;
	
	@ApiModelProperty(value="住院号集合")
	private List<Integer> zyhs;
	
	@ApiModelProperty(value="记录序号集合")
	private List<Integer> jlxhs;
	
	@ApiModelProperty(value="病区科室代码")
	private Integer ksdm;

	public Integer getZyh() {
		return zyh;
	}

	public void setZyh(Integer zyh) {
		this.zyh = zyh;
	}

	public Date getLyrq() {
		return lyrq;
	}

	public void setLyrq(Date lyrq) {
		this.lyrq = lyrq;
	}

	public Integer getFyfs() {
		return fyfs;
	}

	public void setFyfs(Integer fyfs) {
		this.fyfs = fyfs;
	}

	public Integer getLsyz() {
		return lsyz;
	}

	public void setLsyz(Integer lsyz) {
		this.lsyz = lsyz;
	}

	public Integer getYfsb() {
		return yfsb;
	}

	public void setYfsb(Integer yfsb) {
		this.yfsb = yfsb;
	}

	public List<Integer> getZyhs() {
		return zyhs;
	}

	public void setZyhs(List<Integer> zyhs) {
		this.zyhs = zyhs;
	}

	public List<Integer> getJlxhs() {
		return jlxhs;
	}

	public void setJlxhs(List<Integer> jlxhs) {
		this.jlxhs = jlxhs;
	}

	public Integer getKsdm() {
		return ksdm;
	}

	public void setKsdm(Integer ksdm) {
		this.ksdm = ksdm;
	}

}
