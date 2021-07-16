   
package com.buit.cis.dctwork.request;

import java.math.BigDecimal;
import java.sql.Timestamp;

import com.buit.commons.PageQuery;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


/**
 * 类名称：CisHzyzDataReq<br> 
 * 类描述：住院_病区医嘱_数据盒<br>
 * @author GONGFANGZHOU
 */
@ApiModel(value="住院_病区医嘱_Req")
public class CisHzyzDataReq {
	
	@ApiModelProperty(value="住院号")
    private Integer zyh;
	
	@ApiModelProperty(value="开始时间")
    private Timestamp kssj;
	
	@ApiModelProperty(value="结束时间")
    private Timestamp jssj;
	
	@ApiModelProperty(value="医嘱标识(0:长期1:临时2:变更)")
    private Integer lsyz;

	public Integer getZyh() {
		return zyh;
	}

	public void setZyh(Integer zyh) {
		this.zyh = zyh;
	}

	public Timestamp getKssj() {
		return kssj;
	}

	public void setKssj(Timestamp kssj) {
		this.kssj = kssj;
	}

	public Timestamp getJssj() {
		return jssj;
	}

	public void setJssj(Timestamp jssj) {
		this.jssj = jssj;
	}

	public Integer getLsyz() {
		return lsyz;
	}

	public void setLsyz(Integer lsyz) {
		this.lsyz = lsyz;
	}
	
	
	
}
