   
package com.buit.cis.dctwork.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.sql.Timestamp;
import java.util.List;

import javax.validation.constraints.NotNull;


/**
 * 类名称：CisHzyz<br> 
 * 类描述：住院_病区医嘱<br>
 * @author GONGFANGZHOU
 */
@ApiModel(value="住院_病区医嘱_InvalidReq")
public class CisHzyzInvalidReq {

	@NotNull(message = "住院号不能为空")
	@ApiModelProperty(value="住院号")
	private Integer zyh;

	@ApiModelProperty(value="临时医嘱(0:长期1:临时)")
	private Integer lsyz;

	@ApiModelProperty(value="医嘱作废(1:作废,0:未作废)")
	private Integer yzzf;

	@ApiModelProperty(value="作废数据集合")
	private List<CisHzyzInvalidBody> invalidBodyList;

	public Integer getZyh() {
		return zyh;
	}

	public void setZyh(Integer zyh) {
		this.zyh = zyh;
	}

	public Integer getLsyz() {
		return lsyz;
	}

	public void setLsyz(Integer lsyz) {
		this.lsyz = lsyz;
	}

	public Integer getYzzf() {
		return yzzf;
	}

	public void setYzzf(Integer yzzf) {
		this.yzzf = yzzf;
	}

	public List<CisHzyzInvalidBody> getInvalidBodyList() {
		return invalidBodyList;
	}

	public void setInvalidBodyList(List<CisHzyzInvalidBody> invalidBodyList) {
		this.invalidBodyList = invalidBodyList;
	}

	public static class CisHzyzInvalidBody {
		
		@NotNull(message = "记录序号不能为空")
		@ApiModelProperty(value="记录序号")
		private Integer jlxh;

		@ApiModelProperty(value="组套标志")
		private Integer ztbz;

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

		public boolean equals(Object obj){
	        if (obj == null) 
	        	return false;
	        if (this == obj) {
	            return true;
	        }
	        if (obj instanceof Integer){
	            if(this.jlxh.equals(obj)){
	                return true;
	            }
	        }
	        return false;
	    }
	}
    
}
