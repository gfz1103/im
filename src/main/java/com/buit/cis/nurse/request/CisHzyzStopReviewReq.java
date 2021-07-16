   
package com.buit.cis.nurse.request;

import java.util.List;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


/**
 * 类名称：CisHzyzReviewReq<br> 
 * 类描述：住院_病区医嘱复核<br>
 * @author GONGFANGZHOU
 */
@ApiModel(value="住院_病区医嘱_StopReviewReq")
public class CisHzyzStopReviewReq {
	
	@NotNull(message = "复核方式不能为空")
	@ApiModelProperty(value="是否停嘱复核(1:停嘱复核0:取消停嘱复核)")
    private Integer sftzfh;
	
	@ApiModelProperty(value="停嘱复核集合")
    private List<CisHzyzStopReviewBody> stopReviewBodyList;
	
	public Integer getSftzfh() {
		return sftzfh;
	}

	public void setSftzfh(Integer sftzfh) {
		this.sftzfh = sftzfh;
	}

	public List<CisHzyzStopReviewBody> getStopReviewBodyList() {
		return stopReviewBodyList;
	}

	public void setStopReviewBodyList(List<CisHzyzStopReviewBody> stopReviewBodyList) {
		this.stopReviewBodyList = stopReviewBodyList;
	}

	public static class CisHzyzStopReviewBody {
		@ApiModelProperty(value="组套标志(0:否,1:是)")
	    private Integer ztbz;
		
		@ApiModelProperty(value="记录序号")
		@NotNull(message = "记录序号不能为空")
	    private Integer jlxh;
	
		public Integer getZtbz() {
			return ztbz;
		}
	
		public void setZtbz(Integer ztbz) {
			this.ztbz = ztbz;
		}
	
		public Integer getJlxh() {
			return jlxh;
		}
	
		public void setJlxh(Integer jlxh) {
			this.jlxh = jlxh;
		}
	}
	
}
