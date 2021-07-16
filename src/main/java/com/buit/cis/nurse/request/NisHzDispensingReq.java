   
package com.buit.cis.nurse.request;

import java.math.BigDecimal;
import java.sql.Timestamp;

import com.buit.commons.PageQuery;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


/**
 * 类名称：NisHzDispensingReq<br> 
 * 类描述：病区发药<br>
 * @author GONGFANGZHOU
 */
@ApiModel(value="病区发药_HzDispensingReq")
public class NisHzDispensingReq {

	@ApiModelProperty(value="开始时间")
	private Timestamp beginDate;
	
	@ApiModelProperty(value="结束时间")
	private Timestamp endDate;
	
	@ApiModelProperty(value="病区代码")
    private Integer bqdm;
	
    @ApiModelProperty(value="操作工号")
    private Integer qrgh;

    @ApiModelProperty(value="药品序号")
    private Integer ypxh;
    
    @ApiModelProperty(value="药品产地")
    private Integer ypcd;
    
    @ApiModelProperty(value="药品单价")
    private BigDecimal ypdj;

	public Timestamp getBeginDate() {
		return beginDate;
	}

	public void setBeginDate(Timestamp beginDate) {
		this.beginDate = beginDate;
	}

	public Timestamp getEndDate() {
		return endDate;
	}

	public void setEndDate(Timestamp endDate) {
		this.endDate = endDate;
	}

	public Integer getBqdm() {
		return bqdm;
	}

	public void setBqdm(Integer bqdm) {
		this.bqdm = bqdm;
	}

	public Integer getQrgh() {
		return qrgh;
	}

	public void setQrgh(Integer qrgh) {
		this.qrgh = qrgh;
	}

	public Integer getYpxh() {
		return ypxh;
	}

	public void setYpxh(Integer ypxh) {
		this.ypxh = ypxh;
	}

	public Integer getYpcd() {
		return ypcd;
	}

	public void setYpcd(Integer ypcd) {
		this.ypcd = ypcd;
	}

	public BigDecimal getYpdj() {
		return ypdj;
	}

	public void setYpdj(BigDecimal ypdj) {
		this.ypdj = ypdj;
	}

}