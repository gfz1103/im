   
package com.buit.cis.dctwork.response;

import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


/**
 * 类名称：BqTjSureReq<br> 
 * 类描述：病区医嘱提交错误信息返回
 * @author GONGFANGZHOU
 */
@ApiModel(value="病区医嘱提交_msgRep")
public class NisTjMsgResp  {
	@ApiModelProperty(value="错误信息")
	private String resErrorMsg;
	
	@ApiModelProperty(value="错误病人姓名")
	private String resBrxmMsg;
	
	@ApiModelProperty(value="错误住院号")
	private String resZyhMsg;
	
	@ApiModelProperty(value="警告信息集合")
	private List<String> warnMsg;

	@ApiModelProperty(value="费用明细ID")
	private List<Integer> intList;

	public String getResErrorMsg() {
		return resErrorMsg;
	}

	public void setResErrorMsg(String resErrorMsg) {
		this.resErrorMsg = resErrorMsg;
	}

	public String getResBrxmMsg() {
		return resBrxmMsg;
	}

	public void setResBrxmMsg(String resBrxmMsg) {
		this.resBrxmMsg = resBrxmMsg;
	}

	public String getResZyhMsg() {
		return resZyhMsg;
	}

	public void setResZyhMsg(String resZyhMsg) {
		this.resZyhMsg = resZyhMsg;
	}

	public List<String> getWarnMsg() {
		return warnMsg;
	}

	public void setWarnMsg(List<String> warnMsg) {
		this.warnMsg = warnMsg;
	}

	public List<Integer> getIntList() {
		return intList;
	}

	public void setIntList(List<Integer> intList) {
		this.intList = intList;
	}
}