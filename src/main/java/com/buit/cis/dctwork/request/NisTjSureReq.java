   
package com.buit.cis.dctwork.request;

import java.math.BigDecimal;
import java.sql.Timestamp;

import com.buit.commons.PageQuery;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


/**
 * 类名称：BqTjSureReq<br> 
 * 类描述：病区医嘱提交确认请求
 * @author GONGFANGZHOU
 */
@ApiModel(value="病区医嘱提交_sureRep")
public class NisTjSureReq  {
	
	@ApiModelProperty(value="药品序号")
	private Integer medId;

	@ApiModelProperty(value="药品产地")
	private Integer medsource;
	
	@ApiModelProperty(value="总数量(一次数量*次数)")
	private BigDecimal quantity;
	
	@ApiModelProperty(value="药房识别")
	private Integer yfsb;
	
	@ApiModelProperty(value="药品名称")
	private String	ypmc;
	
	@ApiModelProperty(value="临时医嘱")
	private Integer	lsyz;
	
	public NisTjSureReq(Integer medId, Integer medsource, BigDecimal quantity, Integer yfsb, String	ypmc) {
		this.medId = medId;
		this.medsource = medsource;
		this.quantity = quantity;
		this.yfsb = yfsb;
		this.ypmc = ypmc;
	}

	public Integer getMedId() {
		return medId;
	}

	public void setMedId(Integer medId) {
		this.medId = medId;
	}

	public Integer getMedsource() {
		return medsource;
	}

	public void setMedsource(Integer medsource) {
		this.medsource = medsource;
	}

	public BigDecimal getQuantity() {
		return quantity;
	}

	public void setQuantity(BigDecimal quantity) {
		this.quantity = quantity;
	}

	public Integer getYfsb() {
		return yfsb;
	}

	public void setYfsb(Integer yfsb) {
		this.yfsb = yfsb;
	}

	public String getYpmc() {
		return ypmc;
	}

	public void setYpmc(String ypmc) {
		this.ypmc = ypmc;
	}

	public Integer getLsyz() {
		return lsyz;
	}

	public void setLsyz(Integer lsyz) {
		this.lsyz = lsyz;
	}
	
    
}