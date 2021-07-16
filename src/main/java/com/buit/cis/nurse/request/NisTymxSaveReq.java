   
package com.buit.cis.nurse.request;

import java.sql.Timestamp;
import java.util.List;

import com.buit.cis.dctwork.request.NisTymxReq;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


/**
 * 类名称：NisTymx<br> 
 * 类描述：病区_退药明细_保存<br>
 * @author GONGFANGZHOU
 */
@ApiModel(value="病区_退药明细_saveReq")
public class NisTymxSaveReq {
	
	@ApiModelProperty(value="住院号")
	private Integer zyh;
	
	@ApiModelProperty(value="开始时间")
	private String beginDate;
	
	@ApiModelProperty(value="结束时间")
	private String endDate;
	
	@ApiModelProperty(value="退药集合")
	private List<NisTymxReq> nisTymxReqList;

	public Integer getZyh() {
		return zyh;
	}

	public void setZyh(Integer zyh) {
		this.zyh = zyh;
	}

	public String getBeginDate() {
		return beginDate;
	}

	public void setBeginDate(String beginDate) {
		this.beginDate = beginDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public List<NisTymxReq> getNisTymxReqList() {
		return nisTymxReqList;
	}

	public void setNisTymxReqList(List<NisTymxReq> nisTymxReqList) {
		this.nisTymxReqList = nisTymxReqList;
	}

}
