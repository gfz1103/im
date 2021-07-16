   
package com.buit.cis.dctwork.request;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


/**
 * 类名称：CisHzyzBookPrintReq<br> 
 * 类描述：医嘱本打印<br>
 * @author GONGFANGZHOU
 */
@ApiModel(value="住院_病区医嘱_bookprintReq")
public class CisHzyzBookPrintReq {
	
	@ApiModelProperty(value="打印类型")
	private String type;
	
	@ApiModelProperty(value="住院号")
	private Integer zyh;
	
	@ApiModelProperty(value="医嘱状态(1:开嘱2:停嘱)")
	private Integer yzzt;
	
	@ApiModelProperty(value="医嘱类型(0:长期1:临时)")
	private Integer yzlx;
	
	@ApiModelProperty(value="起始页")
	private Integer pageFrom;
	
	@ApiModelProperty(value="结束页")
	private Integer pageTo;
	
	@ApiModelProperty(value="是否重整(1:是0:否)")
	private Integer sfcz;
	
	@ApiModelProperty(value="重整医生")
	private Integer czys;
	
	@ApiModelProperty(value="打印医嘱数")
    private String dyyzs;
    
    @ApiModelProperty(value="停嘱医嘱数")
    private String tzyzs;
    
    @ApiModelProperty(value="打印页码")
    private Integer dyym;
    
    @ApiModelProperty(value="打印行号")
    private Integer dyhh;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Integer getZyh() {
		return zyh;
	}

	public void setZyh(Integer zyh) {
		this.zyh = zyh;
	}

	public Integer getYzzt() {
		return yzzt;
	}

	public void setYzzt(Integer yzzt) {
		this.yzzt = yzzt;
	}

	public Integer getYzlx() {
		return yzlx;
	}

	public void setYzlx(Integer yzlx) {
		this.yzlx = yzlx;
	}

	public Integer getPageFrom() {
		return pageFrom;
	}

	public void setPageFrom(Integer pageFrom) {
		this.pageFrom = pageFrom;
	}

	public Integer getPageTo() {
		return pageTo;
	}

	public void setPageTo(Integer pageTo) {
		this.pageTo = pageTo;
	}

	public Integer getSfcz() {
		return sfcz;
	}

	public void setSfcz(Integer sfcz) {
		this.sfcz = sfcz;
	}

	public Integer getCzys() {
		return czys;
	}

	public void setCzys(Integer czys) {
		this.czys = czys;
	}

	public String getDyyzs() {
		return dyyzs;
	}

	public void setDyyzs(String dyyzs) {
		this.dyyzs = dyyzs;
	}

	public String getTzyzs() {
		return tzyzs;
	}

	public void setTzyzs(String tzyzs) {
		this.tzyzs = tzyzs;
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

	
	
}
