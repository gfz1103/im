   
package com.buit.cis.dctwork.request;

import com.buit.commons.PageQuery;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


/**
 * 类名称：CisJbzmd<br> 
 * 类描述：疾病证明单<br>
 * @author GONGFANGZHOU
 */
@ApiModel(value="疾病证明单_QueryReq")
public class CisJbzmdQueryReq  extends  PageQuery{
    @ApiModelProperty(value="住院号")
    private Integer zyh;
    @ApiModelProperty(value="门诊号码")
    private String mzhm;
    @ApiModelProperty(value="开始日期")
    private String ksrq;
    @ApiModelProperty(value="结束日期")
    private String jsrq;
    @ApiModelProperty(value="机构id", hidden = true)
    private Integer jgid;
    
	public Integer getZyh() {
		return zyh;
	}
	public void setZyh(Integer zyh) {
		this.zyh = zyh;
	}
	public String getMzhm() {
		return mzhm;
	}
	public void setMzhm(String mzhm) {
		this.mzhm = mzhm;
	}
	public String getKsrq() {
		return ksrq;
	}
	public void setKsrq(String ksrq) {
		this.ksrq = ksrq;
	}
	public String getJsrq() {
		return jsrq;
	}
	public void setJsrq(String jsrq) {
		this.jsrq = jsrq;
	}
	public Integer getJgid() {
		return jgid;
	}
	public void setJgid(Integer jgid) {
		this.jgid = jgid;
	}
 
}