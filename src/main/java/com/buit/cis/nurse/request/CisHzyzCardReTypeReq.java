   
package com.buit.cis.nurse.request;

import java.util.List;

import com.buit.commons.PageQuery;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


/**
 * 类名称：CisHzyz<br> 
 * 类描述：住院_病区医嘱卡片打印<br>
 * @author GONGFANGZHOU
 */
@ApiModel(value="住院_病区医嘱_ReTypeReq")
public class CisHzyzCardReTypeReq{

	@ApiModelProperty(value="日期方式")
    private Integer typeDate;
    
	@ApiModelProperty(value="医嘱类别")
	private Integer yzlb;
	
	@ApiModelProperty(value="临时医嘱")
	private Integer lsyz;
	
	@ApiModelProperty(value="病区代码")
	private Integer bqdm;

    @ApiModelProperty(value="重打主键集合")
    private List<Integer> idList;
    
    @ApiModelProperty(value="是否营养卡(1:是0:否)")
	private Integer sfyyk;

	public Integer getTypeDate() {
		return typeDate;
	}

	public void setTypeDate(Integer typeDate) {
		this.typeDate = typeDate;
	}

	public Integer getYzlb() {
		return yzlb;
	}

	public void setYzlb(Integer yzlb) {
		this.yzlb = yzlb;
	}

	public Integer getLsyz() {
		return lsyz;
	}

	public void setLsyz(Integer lsyz) {
		this.lsyz = lsyz;
	}

	public Integer getBqdm() {
		return bqdm;
	}

	public void setBqdm(Integer bqdm) {
		this.bqdm = bqdm;
	}

	public List<Integer> getIdList() {
		return idList;
	}

	public void setIdList(List<Integer> idList) {
		this.idList = idList;
	}

	public Integer getSfyyk() {
		return sfyyk;
	}

	public void setSfyyk(Integer sfyyk) {
		this.sfyyk = sfyyk;
	}
    
}
