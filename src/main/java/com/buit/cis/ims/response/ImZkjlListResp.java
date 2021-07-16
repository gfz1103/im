package com.buit.cis.ims.response;


import java.util.List;

import com.buit.cis.dctwork.model.CisHzyz;
import com.buit.cis.dctwork.model.NisTfmx;
import com.buit.cis.dctwork.response.NisTymxResp;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 类名称：ImZkjl<br> 
 * 类描述：住院_转科记录集合
 * @author GONGFANGZHOU 
 * @ApiModel(value="住院_转科记录")
 */
@ApiModel(value="住院_转科记录_listResp")
public class ImZkjlListResp {
	@ApiModelProperty(value="病区医嘱未提交未执行集合")
	private List<CisHzyz> cisHzyzlist;
	
	@ApiModelProperty(value="退药医嘱未提交或未确认集合")
	private List<NisTymxResp> nisTymxRespList;
	
	@ApiModelProperty(value="退费单未确认集合")
	private List<NisTfmx> nisTfmxList;

	public List<CisHzyz> getCisHzyzlist() {
		return cisHzyzlist;
	}

	public void setCisHzyzlist(List<CisHzyz> cisHzyzlist) {
		this.cisHzyzlist = cisHzyzlist;
	}

	public List<NisTymxResp> getNisTymxRespList() {
		return nisTymxRespList;
	}

	public void setNisTymxRespList(List<NisTymxResp> nisTymxRespList) {
		this.nisTymxRespList = nisTymxRespList;
	}

	public List<NisTfmx> getNisTfmxList() {
		return nisTfmxList;
	}

	public void setNisTfmxList(List<NisTfmx> nisTfmxList) {
		this.nisTfmxList = nisTfmxList;
	}

}
