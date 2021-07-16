   
package com.buit.cis.dctwork.response;

import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


/**
 * 类名称：SkinPsjl<br> 
 * 类描述：公用_病人皮试记录<br>
 * @author GONGFANGZHOU
 */
@ApiModel(value="过敏药物查询_Resp")
public class SkinPsjlQueryResp {
    
	@ApiModelProperty(value="cyz")
	private Long cyz; 
	
	@ApiModelProperty(value="过敏药物集合")
	private List<SkinPsjlResp> gmywList;

	public Long getCyz() {
		return cyz;
	}

	public void setCyz(Long cyz) {
		this.cyz = cyz;
	}

	public List<SkinPsjlResp> getGmywList() {
		return gmywList;
	}

	public void setGmywList(List<SkinPsjlResp> gmywList) {
		this.gmywList = gmywList;
	}
    
}
