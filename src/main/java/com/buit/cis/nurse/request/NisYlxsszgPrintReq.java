   
package com.buit.cis.nurse.request;

import com.buit.commons.PageQuery;
import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


/**
 * 类名称：NisYlxsszg<br> 
 * 类描述：压力性损伤预报、传报单(转归情况)<br>
 * @author GONGFANGZHOU
 */
@ApiModel(value="压力性损伤预报、传报单(转归情况)_PrintReq")
public class NisYlxsszgPrintReq  extends  PageQuery{
    /**
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
	*/
	private static final long serialVersionUID = 1L;
    @ApiModelProperty(value="未发生")
    private String wfs;
    @ApiModelProperty(value="发生1")
    private String fs1;
    @ApiModelProperty(value="发生2")
    private String fs2;
    @ApiModelProperty(value="疾病转归1")
    private String jbzg1;
    @ApiModelProperty(value="疾病转归2")
    private String jbzg2;
    @ApiModelProperty(value="疾病转归3")
    private String jbzg3;
    @ApiModelProperty(value="疾病转归4")
    private String jbzg4;
    @ApiModelProperty(value="压力性损伤转归1")
    private String ylxsszg1;
    @ApiModelProperty(value="压力性损伤转归2")
    private String ylxsszg2;
    @ApiModelProperty(value="压力性损伤转归3")
    private String ylxssz3;
    @ApiModelProperty(value="压力性损伤转归4")
    private String ylxsszg4;
    @ApiModelProperty(value="签名")
    private String qm;
    @ApiModelProperty(value="日期")
    private String rq;
    @JsonIgnore
    @ApiModelProperty(value="机构id")
    private Integer jgid;
	public String getWfs() {
		return wfs;
	}
	public void setWfs(String wfs) {
		this.wfs = wfs;
	}
	public String getFs1() {
		return fs1;
	}
	public void setFs1(String fs1) {
		this.fs1 = fs1;
	}
	public String getFs2() {
		return fs2;
	}
	public void setFs2(String fs2) {
		this.fs2 = fs2;
	}
	public String getJbzg1() {
		return jbzg1;
	}
	public void setJbzg1(String jbzg1) {
		this.jbzg1 = jbzg1;
	}
	public String getJbzg2() {
		return jbzg2;
	}
	public void setJbzg2(String jbzg2) {
		this.jbzg2 = jbzg2;
	}
	public String getJbzg3() {
		return jbzg3;
	}
	public void setJbzg3(String jbzg3) {
		this.jbzg3 = jbzg3;
	}
	public String getJbzg4() {
		return jbzg4;
	}
	public void setJbzg4(String jbzg4) {
		this.jbzg4 = jbzg4;
	}
	public String getYlxsszg1() {
		return ylxsszg1;
	}
	public void setYlxsszg1(String ylxsszg1) {
		this.ylxsszg1 = ylxsszg1;
	}
	public String getYlxsszg2() {
		return ylxsszg2;
	}
	public void setYlxsszg2(String ylxsszg2) {
		this.ylxsszg2 = ylxsszg2;
	}
	public String getYlxssz3() {
		return ylxssz3;
	}
	public void setYlxssz3(String ylxssz3) {
		this.ylxssz3 = ylxssz3;
	}
	public String getYlxsszg4() {
		return ylxsszg4;
	}
	public void setYlxsszg4(String ylxsszg4) {
		this.ylxsszg4 = ylxsszg4;
	}
	public String getQm() {
		return qm;
	}
	public void setQm(String qm) {
		this.qm = qm;
	}
	public String getRq() {
		return rq;
	}
	public void setRq(String rq) {
		this.rq = rq;
	}
	public Integer getJgid() {
		return jgid;
	}
	public void setJgid(Integer jgid) {
		this.jgid = jgid;
	}

    
}