   
package com.buit.cis.nurse.response;

import java.math.BigDecimal;
import java.sql.Timestamp;

import com.buit.commons.PageQuery;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


/**
 * 类名称：NisTymxRecordResp<br> 
 * 类描述：病区_退药明细_退药记录<br>
 * @author GONGFANGZHOU
 */
@ApiModel(value="病区_退药明细_BackResp")
public class NisTymxBackResp {
	
	@ApiModelProperty(value="可退药数量")
    private BigDecimal ktysl;
	
	@ApiModelProperty(value="一次数量")
    private BigDecimal ycsl;

	public BigDecimal getKtysl() {
		return ktysl;
	}

	public void setKtysl(BigDecimal ktysl) {
		this.ktysl = ktysl;
	}

	public BigDecimal getYcsl() {
		return ycsl;
	}

	public void setYcsl(BigDecimal ycsl) {
		this.ycsl = ycsl;
	}
	
}
