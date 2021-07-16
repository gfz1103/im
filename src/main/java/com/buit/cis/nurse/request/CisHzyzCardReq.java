   
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
@ApiModel(value="住院_病区医嘱_cardReq")
public class CisHzyzCardReq extends PageQuery{

    /**
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
	*/
	private static final long serialVersionUID = 1L;

	@ApiModelProperty(value="日期方式")
    private Integer typeDate;
    
    @ApiModelProperty(value="打印标志")
    private Integer dybz;
    
	@ApiModelProperty(value="医嘱类别")
	private Integer yzlb;
	
	@ApiModelProperty(value="临时医嘱")
	private Integer lsyz;
	
	@ApiModelProperty(value="病区代码")
	private Integer bqdm;
	
	@ApiModelProperty(value="病区名称")
	private String bqdmName;
	
	@ApiModelProperty(value="科室名称")
	private String ksdmName;

    @ApiModelProperty(value="住院号集合")
    private List<Integer> zyhList;
    
    @ApiModelProperty(value="是否营养卡(1:是0:否)")
	private Integer sfyyk;
    
	public Integer getTypeDate() {
		return typeDate;
	}

	public void setTypeDate(Integer typeDate) {
		this.typeDate = typeDate;
	}

	public Integer getDybz() {
		return dybz;
	}

	public void setDybz(Integer dybz) {
		this.dybz = dybz;
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

	public String getBqdmName() {
		return bqdmName;
	}

	public void setBqdmName(String bqdmName) {
		this.bqdmName = bqdmName;
	}
	
	public String getKsdmName() {
		return ksdmName;
	}

	public void setKsdmName(String ksdmName) {
		this.ksdmName = ksdmName;
	}

	public List<Integer> getZyhList() {
		return zyhList;
	}

	public void setZyhList(List<Integer> zyhList) {
		this.zyhList = zyhList;
	}

	public Integer getSfyyk() {
		return sfyyk;
	}

	public void setSfyyk(Integer sfyyk) {
		this.sfyyk = sfyyk;
	}
    
}
