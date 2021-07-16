   
package com.buit.cis.nurse.request;

import com.buit.commons.PageQuery;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


/**
 * 类名称：NisCghljldReq<br> 
 * 类描述：常规护理记录单<br>
 * @author GONGFANGZHOU
 */
@ApiModel(value="常规护理记录单_ColumnHeadReq")
public class NisCghljldColumnHeadReq  extends  PageQuery{

    @ApiModelProperty(value="自定义列名称1")
    private String zdylmc1;
    @ApiModelProperty(value="自定义列名称2")
    private String zdylmc2;
    @ApiModelProperty(value="自定义列名称3")
    private String zdylmc3;
    @ApiModelProperty(value="自定义列名称4")
    private String zdylmc4;
	public String getZdylmc1() {
		return zdylmc1;
	}
	public void setZdylmc1(String zdylmc1) {
		this.zdylmc1 = zdylmc1;
	}
	public String getZdylmc2() {
		return zdylmc2;
	}
	public void setZdylmc2(String zdylmc2) {
		this.zdylmc2 = zdylmc2;
	}
	public String getZdylmc3() {
		return zdylmc3;
	}
	public void setZdylmc3(String zdylmc3) {
		this.zdylmc3 = zdylmc3;
	}
	public String getZdylmc4() {
		return zdylmc4;
	}
	public void setZdylmc4(String zdylmc4) {
		this.zdylmc4 = zdylmc4;
	}
    
    
}