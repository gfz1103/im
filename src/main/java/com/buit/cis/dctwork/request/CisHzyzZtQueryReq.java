   
package com.buit.cis.dctwork.request;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


/**
 * 类名称：CisHzyzZtQueryReq<br> 
 * 类描述：住院_检验申请单查询<br>
 * @author GONGFANGZHOU
 */
@ApiModel(value="住院_检验申请单_queryReq")
public class CisHzyzZtQueryReq  {
	
	@ApiModelProperty(value="开始时间")
	private String beginDate;
	
	@ApiModelProperty(value="结束时间")
	private String endDate;
	
	@ApiModelProperty(value="执行科室")
	private Integer zxks;
	
	@ApiModelProperty(value="病人病区")
	private Integer brbq;
	
	@ApiModelProperty(value="医嘱类型", hidden = true)
	private Integer yzlx;
	
	@ApiModelProperty(value="住院号")
	private Integer zyh;
	
	@ApiModelProperty(value="是否作废(1:是0:否)")
    private Integer stop;

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

	public Integer getZxks() {
		return zxks;
	}

	public void setZxks(Integer zxks) {
		this.zxks = zxks;
	}

	public Integer getBrbq() {
		return brbq;
	}

	public void setBrbq(Integer brbq) {
		this.brbq = brbq;
	}

	public Integer getYzlx() {
		return yzlx;
	}

	public void setYzlx(Integer yzlx) {
		this.yzlx = yzlx;
	}

	public Integer getZyh() {
		return zyh;
	}

	public void setZyh(Integer zyh) {
		this.zyh = zyh;
	}

	public Integer getStop() {
		return stop;
	}

	public void setStop(Integer stop) {
		this.stop = stop;
	}

}
