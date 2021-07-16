   
package com.buit.cis.nurse.response;

import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


/**
 * 类名称：NisHzmb<br> 
 * 类描述：患者护理记录模板表<br>
 * @author GONGFANGZHOU
 */
@ApiModel(value="患者护理记录模板表_Resp")
public class NisHzmbResp {
    @ApiModelProperty(value="模板名称")
    private String label;
    
    @ApiModelProperty(value="模板类型")
    private String name;
    
    @ApiModelProperty(value="模板子节点")
    private List<NisHzmbResp> children;
    
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<NisHzmbResp> getChildren() {
		return children;
	}
	public void setChildren(List<NisHzmbResp> children) {
		this.children = children;
	}
    
    
    
}