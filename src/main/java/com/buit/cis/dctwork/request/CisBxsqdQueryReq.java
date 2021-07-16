   
package com.buit.cis.dctwork.request;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


/**
 * 类名称：CisBxsqdReq<br> 
 * 类描述：备血申请单<br>
 * @author GONGFANGZHOU
 */
@ApiModel(value="备血申请单_Req")
public class CisBxsqdQueryReq {
	
	@ApiModelProperty(value="开始时间")
	private String beginDate;
	
	@ApiModelProperty(value="结束时间")
	private String endDate;
	
    @ApiModelProperty(value="医疗机构代码", hidden = true)
    private String yljgd;

    @ApiModelProperty(value="科室代码")
    private String ksdm;
    
    @ApiModelProperty(value="病人病区")
    private Integer brbq;
    
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

	public String getYljgd() {
		return yljgd;
	}

	public void setYljgd(String yljgd) {
		this.yljgd = yljgd;
	}

	public String getKsdm() {
		return ksdm;
	}

	public void setKsdm(String ksdm) {
		this.ksdm = ksdm;
	}

	public Integer getBrbq() {
		return brbq;
	}

	public void setBrbq(Integer brbq) {
		this.brbq = brbq;
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
