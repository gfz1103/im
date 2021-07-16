   
package com.buit.cis.dctwork.response;

import java.math.BigDecimal;
import java.sql.Timestamp;

import com.buit.commons.PageQuery;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


/**
 * 类名称：NisTjSureResp<br> 
 * 类描述：病区医嘱提交返回
 * @author GONGFANGZHOU
 */
@ApiModel(value="病区医嘱提交_sureResp")
public class NisTjSureResp  {

	@ApiModelProperty(value="药品序号")
	private Integer medId;

	@ApiModelProperty(value="药品产地")
	private Integer medsource;
	
	@ApiModelProperty(value="总数量(一次数量*次数)")
	private BigDecimal quantity;
	
	@ApiModelProperty(value="药品名称")
	private String	ypmc;
	
	@ApiModelProperty(value="库存数量")
	private BigDecimal kczl;

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

	public String getYpmc() {
		return ypmc;
	}

	public void setYpmc(String ypmc) {
		this.ypmc = ypmc;
	}

	public BigDecimal getKczl() {
		return kczl;
	}

	public void setKczl(BigDecimal kczl) {
		this.kczl = kczl;
	}
	
    
}