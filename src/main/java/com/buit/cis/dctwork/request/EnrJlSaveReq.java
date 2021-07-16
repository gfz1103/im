   
package com.buit.cis.dctwork.request;

import java.sql.Timestamp;
import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


/**
 * 类名称：EnrJl01<br> 
 * 类描述：<br>
 * @author GONGFANGZHOU
 */
@ApiModel(value="护理记录保存请求_saveReq")
public class EnrJlSaveReq {
	
	@ApiModelProperty(value="住院号")
	private Integer zyh;
	
	@ApiModelProperty(value="当前时间")
	private Timestamp time;
	
	@ApiModelProperty(value="病人病区代码")
	private Integer brbqdm;
	
	@ApiModelProperty(value="记录编号")
	private Integer jlbh;
	
	@ApiModelProperty(value="护理记录结构集合")
	private List<EnrJbysSaveReq> enrJbysSaveReqList;

	public Integer getZyh() {
		return zyh;
	}

	public void setZyh(Integer zyh) {
		this.zyh = zyh;
	}

	public Timestamp getTime() {
		return time;
	}

	public void setTime(Timestamp time) {
		this.time = time;
	}

	public Integer getBrbqdm() {
		return brbqdm;
	}

	public void setBrbqdm(Integer brbqdm) {
		this.brbqdm = brbqdm;
	}
	
	public Integer getJlbh() {
		return jlbh;
	}

	public void setJlbh(Integer jlbh) {
		this.jlbh = jlbh;
	}

	public List<EnrJbysSaveReq> getEnrJbysSaveReqList() {
		return enrJbysSaveReqList;
	}

	public void setEnrJbysSaveReqList(List<EnrJbysSaveReq> enrJbysSaveReqList) {
		this.enrJbysSaveReqList = enrJbysSaveReqList;
	}
	
}