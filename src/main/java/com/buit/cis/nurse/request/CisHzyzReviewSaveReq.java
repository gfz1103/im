   
package com.buit.cis.nurse.request;


import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


/**
 * 类名称：CisHzyzReviewBatchResp<br> 
 * 类描述：住院_病区医嘱_批量复核返回<br>
 * @author GONGFANGZHOU
 */
@ApiModel(value="住院_病区医嘱_ReviewSaveReq")
public class CisHzyzReviewSaveReq {
	
    @ApiModelProperty(value="医嘱标志(1:开,2:停)")
    private Integer yzbz;
    
    @ApiModelProperty(value="当前病区")
    private Integer srks;
    
    @ApiModelProperty(value="医嘱(0:长期1:临时2:全部)")
    private Integer lsyz;
    
    @ApiModelProperty(value="机构id", hidden = true)
    private Integer jgid;
    
    @ApiModelProperty(value="是否全部勾选")
    private boolean checkAll;
    
    @ApiModelProperty(value="住院号码")
    private String zyhm;
    
    @ApiModelProperty(value="批量复核集合")
    private List<BatchBody> batchBodyList;
    
	public Integer getYzbz() {
		return yzbz;
	}

	public void setYzbz(Integer yzbz) {
		this.yzbz = yzbz;
	}

	public Integer getSrks() {
		return srks;
	}

	public void setSrks(Integer srks) {
		this.srks = srks;
	}

	public Integer getLsyz() {
		return lsyz;
	}

	public void setLsyz(Integer lsyz) {
		this.lsyz = lsyz;
	}

	public Integer getJgid() {
		return jgid;
	}

	public void setJgid(Integer jgid) {
		this.jgid = jgid;
	}
	
	public boolean isCheckAll() {
		return checkAll;
	}

	public void setCheckAll(boolean checkAll) {
		this.checkAll = checkAll;
	}

	public String getZyhm() {
		return zyhm;
	}

	public void setZyhm(String zyhm) {
		this.zyhm = zyhm;
	}

	public List<BatchBody> getBatchBodyList() {
		return batchBodyList;
	}

	public void setBatchBodyList(List<BatchBody> batchBodyList) {
		this.batchBodyList = batchBodyList;
	}

	public static class BatchBody {
		
		@ApiModelProperty(value="记录序号")
	    private Integer jlxh;
		
		@ApiModelProperty(value="组套标志")
	    private Integer ztbz;

		@ApiModelProperty(value="操作工号")
	    private String czgh;
		
		public BatchBody(Integer jlxh, Integer ztbz, String czgh) {
			this.jlxh = jlxh;
			this.ztbz = ztbz;
			this.czgh = czgh;
		}

		public Integer getJlxh() {
			return jlxh;
		}

		public void setJlxh(Integer jlxh) {
			this.jlxh = jlxh;
		}

		public Integer getZtbz() {
			return ztbz;
		}

		public void setZtbz(Integer ztbz) {
			this.ztbz = ztbz;
		}

		public String getCzgh() {
			return czgh;
		}

		public void setCzgh(String czgh) {
			this.czgh = czgh;
		}
		
		
	}
}
