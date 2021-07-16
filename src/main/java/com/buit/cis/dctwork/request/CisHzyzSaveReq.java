   
package com.buit.cis.dctwork.request;

import java.util.List;

import com.buit.commons.PageQuery;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


/**
 * 类名称：CisHzyz<br> 
 * 类描述：住院_病区医嘱<br>
 * @author GONGFANGZHOU
 */
@ApiModel(value="住院_病区医嘱_saveReq")
public class CisHzyzSaveReq {
	@ApiModelProperty(value="医嘱集合")
    private List<CisHzyzReq> cisHzyzReqList;
    
    @ApiModelProperty(value="附加项目集合")
    private List<CisHzyzReq> cisHzyzReqFjList;
	
	@ApiModelProperty(value="病人科室")
    private Integer brks;
	
    @ApiModelProperty(value="病人病区")
    private Integer brbq;
    
    @ApiModelProperty(value="病人床号")
    private String brch;
    
    @ApiModelProperty(value="住院号")
    private Integer zyh;
    
    @ApiModelProperty(value="病人姓名")
    private String brxm;
    
    

	public Integer getBrks() {
		return brks;
	}

	public void setBrks(Integer brks) {
		this.brks = brks;
	}

	public Integer getBrbq() {
		return brbq;
	}

	public void setBrbq(Integer brbq) {
		this.brbq = brbq;
	}

	public String getBrch() {
		return brch;
	}

	public void setBrch(String brch) {
		this.brch = brch;
	}

	public Integer getZyh() {
		return zyh;
	}

	public void setZyh(Integer zyh) {
		this.zyh = zyh;
	}

	public String getBrxm() {
		return brxm;
	}

	public void setBrxm(String brxm) {
		this.brxm = brxm;
	}

	public List<CisHzyzReq> getCisHzyzReqList() {
		return cisHzyzReqList;
	}

	public void setCisHzyzReqList(List<CisHzyzReq> cisHzyzReqList) {
		this.cisHzyzReqList = cisHzyzReqList;
	}

	public List<CisHzyzReq> getCisHzyzReqFjList() {
		return cisHzyzReqFjList;
	}

	public void setCisHzyzReqFjList(List<CisHzyzReq> cisHzyzReqFjList) {
		this.cisHzyzReqFjList = cisHzyzReqFjList;
	}
    
	
}
