   
package com.buit.cis.dctwork.request;

import java.util.List;

import com.buit.drug.response.CisHzyzYpcdDetailResp;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


/**
 * 类名称：CisHzyzCheckKcReq<br> 
 * 类描述：住院_病区医嘱药品组套调入校验库存<br>
 * @author GONGFANGZHOU
 */
@ApiModel(value="住院_病区医嘱_checkkcReq")
public class CisHzyzCheckKcReq {
	
	@ApiModelProperty(value="病区代码")
	private Integer ksdm;
	
	@ApiModelProperty(value="tab类型(长期:cq,临时:ls,急诊:jz,出院:cy)")
	private String adviceType;
	
	@ApiModelProperty(value="药品集合")
    private List<CisHzyzYpcdDetailResp> ypmxList;

	public Integer getKsdm() {
		return ksdm;
	}

	public void setKsdm(Integer ksdm) {
		this.ksdm = ksdm;
	}

	public String getAdviceType() {
		return adviceType;
	}

	public void setAdviceType(String adviceType) {
		this.adviceType = adviceType;
	}

	public List<CisHzyzYpcdDetailResp> getYpmxList() {
		return ypmxList;
	}

	public void setYpmxList(List<CisHzyzYpcdDetailResp> ypmxList) {
		this.ypmxList = ypmxList;
	}
	
	
	
}
