package com.buit.cis.ims.request;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


/**
 * 类名称：ImFeeFymxYj<br>
 * 类描述：医技费用明细表<br>
 * @author ZHOUHAISHENG
 */
@ApiModel(value="医技费用明细表_PrintReq")
public class ImFeeFymxYjPrintReq {

    @ApiModelProperty(value="开始时间")
    private String beginDate;
	@ApiModelProperty(value="结束时间")
    private String endDate;
    @ApiModelProperty(value="病区")
    private Integer brbq;
    @ApiModelProperty(value="科室")
    private Integer brks;
    @ApiModelProperty(value="住院号码、姓名、床号")
    private String comprehensive;
    @ApiModelProperty(value="记录序号集合")
    private List<Integer> jlxhList;
    @ApiModelProperty(value="住院号集合")
    private List<Integer> zyhList;
    @JsonIgnore
    @ApiModelProperty(value="机构id")
    private Integer jgid;
    @ApiModelProperty(value="医嘱类型0:检查1:检验")
    private Integer yzlx;
	public String getBeginDate() {
		return beginDate;
	}
	public void setBeginDate(String beginDate) {
		this.beginDate = beginDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public Integer getBrbq() {
		return brbq;
	}
	public void setBrbq(Integer brbq) {
		this.brbq = brbq;
	}
	public Integer getBrks() {
		return brks;
	}
	public void setBrks(Integer brks) {
		this.brks = brks;
	}
	public String getComprehensive() {
		return comprehensive;
	}
	public void setComprehensive(String comprehensive) {
		this.comprehensive = comprehensive;
	}
	public List<Integer> getJlxhList() {
		return jlxhList;
	}
	public void setJlxhList(List<Integer> jlxhList) {
		this.jlxhList = jlxhList;
	}
	public List<Integer> getZyhList() {
		return zyhList;
	}
	public void setZyhList(List<Integer> zyhList) {
		this.zyhList = zyhList;
	}
	public Integer getJgid() {
		return jgid;
	}
	public void setJgid(Integer jgid) {
		this.jgid = jgid;
	}
	public Integer getYzlx() {
		return yzlx;
	}
	public void setYzlx(Integer yzlx) {
		this.yzlx = yzlx;
	}
	
}
