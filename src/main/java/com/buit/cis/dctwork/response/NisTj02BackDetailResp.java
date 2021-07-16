   
package com.buit.cis.dctwork.response;

import java.math.BigDecimal;
import java.sql.Timestamp;

import com.buit.commons.PageQuery;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


/**
 * 类名称：NisTj02Resp<br> 
 * 类描述：<br>
 * @author GONGFANGZHOU
 */
@ApiModel(value="病区_提交明细退回_BackDetailResp")
public class NisTj02BackDetailResp {
	
    @ApiModelProperty(value="记录序号")
    private Integer jlxh;
	
	@ApiModelProperty(value="执行时间")
    private Timestamp qrrq;
	
	@ApiModelProperty(value="药房识别")
    private Integer yfsb;
	
	@ApiModelProperty(value="发药数量")
    private BigDecimal ypsl;
	
	@ApiModelProperty(value="药房单位")
    private String yfdw;
	
	@ApiModelProperty(value="药品单价")
    private BigDecimal ypdj;
	
	@ApiModelProperty(value="发药日期")
    private Timestamp fyrq;
	
	@ApiModelProperty(value="提交序号")
    private Integer tjxh;
	
	@ApiModelProperty(value="提交标志")
    private Integer tjbz;

	public Integer getJlxh() {
		return jlxh;
	}

	public void setJlxh(Integer jlxh) {
		this.jlxh = jlxh;
	}

	public Timestamp getQrrq() {
		return qrrq;
	}

	public void setQrrq(Timestamp qrrq) {
		this.qrrq = qrrq;
	}

	public Integer getYfsb() {
		return yfsb;
	}

	public void setYfsb(Integer yfsb) {
		this.yfsb = yfsb;
	}

	public BigDecimal getYpsl() {
		return ypsl;
	}

	public void setYpsl(BigDecimal ypsl) {
		this.ypsl = ypsl;
	}

	public String getYfdw() {
		return yfdw;
	}

	public void setYfdw(String yfdw) {
		this.yfdw = yfdw;
	}

	public BigDecimal getYpdj() {
		return ypdj;
	}

	public void setYpdj(BigDecimal ypdj) {
		this.ypdj = ypdj;
	}

	public Timestamp getFyrq() {
		return fyrq;
	}

	public void setFyrq(Timestamp fyrq) {
		this.fyrq = fyrq;
	}

	public Integer getTjxh() {
		return tjxh;
	}

	public void setTjxh(Integer tjxh) {
		this.tjxh = tjxh;
	}

	public Integer getTjbz() {
		return tjbz;
	}

	public void setTjbz(Integer tjbz) {
		this.tjbz = tjbz;
	}
	
}
