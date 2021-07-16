   
package com.buit.cis.dctwork.request;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


/**
 * 类名称：CisBxsqdHomeReq<br> 
 * 类描述：备血申请单<br>
 * @author GONGFANGZHOU
 */
@ApiModel(value="备血申请单_HomeReq")
public class CisBxsqdHomeReq {
	
	@ApiModelProperty(value="开始时间")
	private String beginDate;
	
	@ApiModelProperty(value="结束时间")
	private String endDate;
	
    @ApiModelProperty(value="医疗机构代码", hidden = true)
    private String yljgd;

    @ApiModelProperty(value="状态(0:未审批,1:已审批)")
    private String zt;
    
    @ApiModelProperty(value="病区代码")
    private Integer bqdm;
    
    @ApiModelProperty(value="科室代码")
    private Integer ksdm;
    

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

	public String getZt() {
		return zt;
	}

	public void setZt(String zt) {
		this.zt = zt;
	}

	public Integer getBqdm() {
		return bqdm;
	}

	public void setBqdm(Integer bqdm) {
		this.bqdm = bqdm;
	}

	public Integer getKsdm() {
		return ksdm;
	}

	public void setKsdm(Integer ksdm) {
		this.ksdm = ksdm;
	}
	
	

}
