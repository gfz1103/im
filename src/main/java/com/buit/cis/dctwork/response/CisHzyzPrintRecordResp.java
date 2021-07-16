   
package com.buit.cis.dctwork.response;

import java.sql.Timestamp;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


/**
 * 类名称：CisHzyzBookPrintResp<br> 
 * 类描述：医嘱本打印<br>
 * @author GONGFANGZHOU
 */
@ApiModel(value="住院_病区医嘱_bookprintResp")
public class CisHzyzPrintRecordResp {

	@ApiModelProperty(value="开始时间")
    private Timestamp kssj;
	
	@ApiModelProperty(value="医嘱名称")
    private String yzmc;
	
	@ApiModelProperty(value="开嘱医生")
    private String ysghmc;
	
	@ApiModelProperty(value="护士")
    private String fhghmc;
	
    @ApiModelProperty(value="停嘱时间")
    private Timestamp tzsj;
    
    @ApiModelProperty(value="停嘱医生")
    private String tzysmc;
    
    @ApiModelProperty(value="复核时间")
    private Timestamp zxsj;
    
    @ApiModelProperty(value="执行护士")
    private String zxfhghmc;
    
    @ApiModelProperty(value="页码")
    private Integer dyym;
    
    @ApiModelProperty(value="行号")
    private Integer dyhh;
    
    @ApiModelProperty(value="住院号")
    private Integer zyh;
    
    @ApiModelProperty(value="医嘱本序号")
    private Integer yzbxh;

	public Timestamp getKssj() {
		return kssj;
	}

	public void setKssj(Timestamp kssj) {
		this.kssj = kssj;
	}

	public String getYzmc() {
		return yzmc;
	}

	public void setYzmc(String yzmc) {
		this.yzmc = yzmc;
	}

	public String getYsghmc() {
		return ysghmc;
	}

	public void setYsghmc(String ysghmc) {
		this.ysghmc = ysghmc;
	}

	public String getFhghmc() {
		return fhghmc;
	}

	public void setFhghmc(String fhghmc) {
		this.fhghmc = fhghmc;
	}

	public Timestamp getTzsj() {
		return tzsj;
	}

	public void setTzsj(Timestamp tzsj) {
		this.tzsj = tzsj;
	}

	public String getTzysmc() {
		return tzysmc;
	}

	public void setTzysmc(String tzysmc) {
		this.tzysmc = tzysmc;
	}

	public Timestamp getZxsj() {
		return zxsj;
	}

	public void setZxsj(Timestamp zxsj) {
		this.zxsj = zxsj;
	}

	public String getZxfhghmc() {
		return zxfhghmc;
	}

	public void setZxfhghmc(String zxfhghmc) {
		this.zxfhghmc = zxfhghmc;
	}

	public Integer getDyym() {
		return dyym;
	}

	public void setDyym(Integer dyym) {
		this.dyym = dyym;
	}

	public Integer getDyhh() {
		return dyhh;
	}

	public void setDyhh(Integer dyhh) {
		this.dyhh = dyhh;
	}

	public Integer getZyh() {
		return zyh;
	}

	public void setZyh(Integer zyh) {
		this.zyh = zyh;
	}

	public Integer getYzbxh() {
		return yzbxh;
	}

	public void setYzbxh(Integer yzbxh) {
		this.yzbxh = yzbxh;
	}

    
	
}
