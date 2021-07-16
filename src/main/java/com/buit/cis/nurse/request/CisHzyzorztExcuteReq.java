   
package com.buit.cis.nurse.request;

import java.sql.Timestamp;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


/**
 * 类名称：CisHzyz<br> 
 * 类描述：住院_病区医嘱或组套执行<br>
 * @author GONGFANGZHOU
 */
@ApiModel(value="住院_病区医嘱_excuteReq")
public class CisHzyzorztExcuteReq {

    @ApiModelProperty(value="住院号")
    private Integer zyh;
    
    @JsonIgnore
    @ApiModelProperty(value="复核标志")
    private Integer fhbz;
    
    @JsonIgnore
	@ApiModelProperty(value="机构id")
	private Integer jgid;
	
    @JsonIgnore
	@ApiModelProperty(value="确认时间")
	private Timestamp qrsj;
    
    @ApiModelProperty(value="住院号集合")
    private List<Integer> zyhs;
    
    @ApiModelProperty(value="项目序号集合")
    private List<Integer> xmxhs;
    
    @ApiModelProperty(value="记录序号集合(打印用)")
    private List<Integer> jlxhs;
    
    @ApiModelProperty(value="打印方式")
    private String printWay;
    
    @JsonIgnore
    @ApiModelProperty(value="排序列名字")
    private String orderBy;
    
    @ApiModelProperty(value="增加复核标志")
    private Integer addfhbz;
    
    @ApiModelProperty(value="组套标志")
    private List<Object> ztbzs;
    
    @ApiModelProperty(value="病人病区")
    private Integer brbq;
    
    @ApiModelProperty(value="临时医嘱(0:长期1:临时2:全部)")
	private Integer lsyz;

	public Integer getZyh() {
		return zyh;
	}

	public void setZyh(Integer zyh) {
		this.zyh = zyh;
	}
	
	public Integer getFhbz() {
		return fhbz;
	}

	public void setFhbz(Integer fhbz) {
		this.fhbz = fhbz;
	}

	public Integer getJgid() {
		return jgid;
	}

	public void setJgid(Integer jgid) {
		this.jgid = jgid;
	}

	public Timestamp getQrsj() {
		return qrsj;
	}

	public void setQrsj(Timestamp qrsj) {
		this.qrsj = qrsj;
	}

	public List<Integer> getZyhs() {
		return zyhs;
	}

	public void setZyhs(List<Integer> zyhs) {
		this.zyhs = zyhs;
	}

	public List<Integer> getXmxhs() {
		return xmxhs;
	}

	public void setXmxhs(List<Integer> xmxhs) {
		this.xmxhs = xmxhs;
	}

	public List<Integer> getJlxhs() {
		return jlxhs;
	}

	public void setJlxhs(List<Integer> jlxhs) {
		this.jlxhs = jlxhs;
	}

	public String getPrintWay() {
		return printWay;
	}

	public void setPrintWay(String printWay) {
		this.printWay = printWay;
	}

	public Integer getAddfhbz() {
		return addfhbz;
	}

	public void setAddfhbz(Integer addfhbz) {
		this.addfhbz = addfhbz;
	}

	public String getOrderBy() {
		return orderBy;
	}

	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}

	public List<Object> getZtbzs() {
		return ztbzs;
	}

	public void setZtbzs(List<Object> ztbzs) {
		this.ztbzs = ztbzs;
	}

	public Integer getBrbq() {
		return brbq;
	}

	public void setBrbq(Integer brbq) {
		this.brbq = brbq;
	}

	public Integer getLsyz() {
		return lsyz;
	}

	public void setLsyz(Integer lsyz) {
		this.lsyz = lsyz;
	}
	
	
}
