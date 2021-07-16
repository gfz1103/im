   
package com.buit.cis.nurse.response;

import java.util.List;

import com.buit.cis.nurse.model.NisHljbdsjb;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


/**
 * 类名称：NisHljbd<br> 
 * 类描述：护理交班单主表<br>
 * @author GONGFANGZHOU
 */
@ApiModel(value="护理交班单主表_Resp")
public class NisHljbdResp {
    @ApiModelProperty(value="交班单出入院等数据")
    private NisHljbdsjb nisHljbdsjb;
   
    @ApiModelProperty(value="交班单明细集合")
    private List<NisHljbdmxbResp> mxList;

	public NisHljbdsjb getNisHljbdsjb() {
		return nisHljbdsjb;
	}

	public void setNisHljbdsjb(NisHljbdsjb nisHljbdsjb) {
		this.nisHljbdsjb = nisHljbdsjb;
	}

	public List<NisHljbdmxbResp> getMxList() {
		return mxList;
	}

	public void setMxList(List<NisHljbdmxbResp> mxList) {
		this.mxList = mxList;
	}
    
    
   
}