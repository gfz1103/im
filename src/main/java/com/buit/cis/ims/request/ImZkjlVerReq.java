   
package com.buit.cis.ims.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


/**
 * 类名称：ImZkjl<br> 
 * 类描述：住院_转科记录_校验<br>
 * @author GONGFANGZHOU
 */
@ApiModel(value="住院_转科记录_verReq")
public class ImZkjlVerReq{
    @ApiModelProperty(value="住院号")
    private Integer zyh;
    
    @ApiModelProperty(value="校验类型")
    private Integer type;
    
    @ApiModelProperty(value="病人科室")
    private Integer brks;

	public Integer getZyh() {
		return zyh;
	}

	public void setZyh(Integer zyh) {
		this.zyh = zyh;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getBrks() {
		return brks;
	}

	public void setBrks(Integer brks) {
		this.brks = brks;
	}
 
}
