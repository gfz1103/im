   
package com.buit.cis.dctwork.request;


import com.buit.commons.PageQuery;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


/**
 * 类名称：CisBxsqdHomeReq<br> 
 * 类描述：备血申请单<br>
 * @author GONGFANGZHOU
 */
@ApiModel(value="备血申请单_LeadBloodReq")
public class CisBxsqdLeadBloodReq extends PageQuery{
	
	/**
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
	*/
	private static final long serialVersionUID = 1L;

	@ApiModelProperty(value="领用状态(0:未领用,1:已领用)")
    private Integer lyzt;
	
	@ApiModelProperty(value="领用开始时间")
	private String beginDate;
	
	@ApiModelProperty(value="领用结束时间")
	private String endDate;
	
    @ApiModelProperty(value="医疗机构代码", hidden = true)
    private String yljgd;

    @ApiModelProperty(value="住院号码")
    private String zyhm;
    
    @ApiModelProperty(value="病人姓名")
    private String brxm;

	public Integer getLyzt() {
		return lyzt;
	}

	public void setLyzt(Integer lyzt) {
		this.lyzt = lyzt;
	}

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

	public String getZyhm() {
		return zyhm;
	}

	public void setZyhm(String zyhm) {
		this.zyhm = zyhm;
	}

	public String getBrxm() {
		return brxm;
	}

	public void setBrxm(String brxm) {
		this.brxm = brxm;
	}
    
}
