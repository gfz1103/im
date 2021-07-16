   
package com.buit.cis.dctwork.response;



import java.util.List;

import com.buit.cis.dctwork.model.PharYflb;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


/**
 * 类名称：CisHzyzYflbResp<br> 
 * 类描述：病区发药药房<br>
 * @author GONGFANGZHOU
 */
@ApiModel(value="病区发药药房_yflbResp")
public class CisHzyzYflbResp {
  
	@ApiModelProperty(value="机构所有药房识别")
	private List<PharYflb> pharYflbList;
	
	@ApiModelProperty(value="机构病区发药药房识别")
	private List<NisFyyfResp> nisFyyfRespList;

	public List<PharYflb> getPharYflbList() {
		return pharYflbList;
	}

	public void setPharYflbList(List<PharYflb> pharYflbList) {
		this.pharYflbList = pharYflbList;
	}

	public List<NisFyyfResp> getNisFyyfRespList() {
		return nisFyyfRespList;
	}

	public void setNisFyyfRespList(List<NisFyyfResp> nisFyyfRespList) {
		this.nisFyyfRespList = nisFyyfRespList;
	}
	
	
}
