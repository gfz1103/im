   
package com.buit.cis.nurse.response;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


/**
 * 类名称：ErrorResp<br> 
 * 类描述：判断错误返回<br>
 * @author GONGFANGZHOU
 */
@ApiModel(value="判断错误返回_Resp")
public class ErrorResp {
	
	@ApiModelProperty(value="出院证判断")
	private boolean hasLeaveHosProve;
	
	@ApiModelProperty(value="药品医嘱判断")
	private boolean hasErrorMeds;
	
	@ApiModelProperty(value="项目医嘱判断")
	private boolean hasErrorPros;
	
	@ApiModelProperty(value="退药单判断")
	private boolean hasErrorRetMed;

	public boolean isHasLeaveHosProve() {
		return hasLeaveHosProve;
	}

	public void setHasLeaveHosProve(boolean hasLeaveHosProve) {
		this.hasLeaveHosProve = hasLeaveHosProve;
	}

	public boolean isHasErrorMeds() {
		return hasErrorMeds;
	}

	public void setHasErrorMeds(boolean hasErrorMeds) {
		this.hasErrorMeds = hasErrorMeds;
	}

	public boolean isHasErrorPros() {
		return hasErrorPros;
	}

	public void setHasErrorPros(boolean hasErrorPros) {
		this.hasErrorPros = hasErrorPros;
	}

	public boolean isHasErrorRetMed() {
		return hasErrorRetMed;
	}

	public void setHasErrorRetMed(boolean hasErrorRetMed) {
		this.hasErrorRetMed = hasErrorRetMed;
	}
	
   
}