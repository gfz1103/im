package com.buit.cis.dctwork.response;

import com.buit.commons.PageQuery;
import com.buit.system.response.HrPersonnelModel;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;


/**
 * 类名称：NisFyyf<br>
 * 类描述：病区_发药药房<br>
 * @author GONGFANGZHOU
 */
@ApiModel(value="病区_发药药房_qxResp")
public class NisFyyfQxResp  extends PageQuery {
	
	@ApiModelProperty(value="住院药房集合")
	private List<NisFyyfResp> nisFyyfRespList;
	
	@ApiModelProperty(value="处方权限对象")
	private HrPersonnelModel mzysqx;

	public List<NisFyyfResp> getNisFyyfRespList() {
		return nisFyyfRespList;
	}

	public void setNisFyyfRespList(List<NisFyyfResp> nisFyyfRespList) {
		this.nisFyyfRespList = nisFyyfRespList;
	}

	public HrPersonnelModel getMzysqx() {
		return mzysqx;
	}

	public void setMzysqx(HrPersonnelModel mzysqx) {
		this.mzysqx = mzysqx;
	}
    
}
