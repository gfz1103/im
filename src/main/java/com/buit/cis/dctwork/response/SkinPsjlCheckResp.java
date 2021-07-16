   
package com.buit.cis.dctwork.response;


import java.math.BigDecimal;

import com.buit.commons.PageQuery;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


/**
 * 类名称：SkinPsjl<br> 
 * 类描述：公用_病人皮试记录<br>
 * @author GONGFANGZHOU
 */
@ApiModel(value="公用_病人皮试记录")
public class SkinPsjlCheckResp {
    @ApiModelProperty(value="自负比例")
    private BigDecimal zfbl;
    
    @ApiModelProperty(value="是否过敏")
    private boolean isAllergy;
    
    @ApiModelProperty(value="不良反映")
    private String blfy;

	public BigDecimal getZfbl() {
		return zfbl;
	}

	public void setZfbl(BigDecimal zfbl) {
		this.zfbl = zfbl;
	}

	public boolean isAllergy() {
		return isAllergy;
	}

	public void setAllergy(boolean isAllergy) {
		this.isAllergy = isAllergy;
	}

	public String getBlfy() {
		return blfy;
	}

	public void setBlfy(String blfy) {
		this.blfy = blfy;
	}
    
    
}
