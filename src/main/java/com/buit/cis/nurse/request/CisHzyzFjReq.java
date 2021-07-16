   
package com.buit.cis.nurse.request;

import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


/**
 * 类名称：CisHzyz<br> 
 * 类描述：住院_病区医嘱附加信息<br>
 * @author GONGFANGZHOU
 */
@ApiModel(value="住院_病区医嘱_fjReq")
public class CisHzyzFjReq {

	@ApiModelProperty(value="医嘱序号集合")
	private List<FjBody> fjBodyList;
	   
	public List<FjBody> getFjBodyList() {
		return fjBodyList;
	}

	public void setFjBodyList(List<FjBody> fjBodyList) {
		this.fjBodyList = fjBodyList;
	}

	public static class FjBody{
		
		@ApiModelProperty(value="科室代码")
		private Integer ksdm;
		
		@ApiModelProperty(value="医嘱序号")
		private Integer yzxh;
		
		@ApiModelProperty(value="是否门诊使用(1:是,0:否)")
		private String mzsy;

		public Integer getKsdm() {
			return ksdm;
		}

		public void setKsdm(Integer ksdm) {
			this.ksdm = ksdm;
		}

		public Integer getYzxh() {
			return yzxh;
		}

		public void setYzxh(Integer yzxh) {
			this.yzxh = yzxh;
		}

		public String getMzsy() {
			return mzsy;
		}

		public void setMzsy(String mzsy) {
			this.mzsy = mzsy;
		}
		
	}
    
}
