package com.buit.cis.ims.request;

import java.sql.Timestamp;
import java.util.List;

import com.buit.cis.dctwork.request.NisZdytzReq;
import com.buit.cis.dctwork.request.NistzxmSaveReq;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
* @ClassName: ZyTwdReq
* @Description: TODO 体温单保存Req
* @author 龚方舟
* @date 2020年6月3日 上午10:34:47
*/
@ApiModel(value="体温单保存_Req")
public class ImTwdReq {
	
	@ApiModelProperty(value="采集号")
	private Integer cjh;
	
	@ApiModelProperty(value="住院号")
	private Integer zyh;
	
	@ApiModelProperty(value="申请序号")
	private Integer sqxh;
	
	@ApiModelProperty(value="操作时间")
	private Timestamp cjsj;
	
	@ApiModelProperty(value="项目下标")
	private String xmxb;
	
	@ApiModelProperty(value="病区体征项目")
	private NistzxmSaveReq nistzxmSaveReq;
	
	@ApiModelProperty(value="当前周数")
	private Integer currentWeek;
	
	@ApiModelProperty(value="自定义集合")
	private List<NisZdytzReq> nisZdytzReqList;
	
	public Integer getCjh() {
		return cjh;
	}

	public void setCjh(Integer cjh) {
		this.cjh = cjh;
	}

	public Integer getZyh() {
		return zyh;
	}

	public void setZyh(Integer zyh) {
		this.zyh = zyh;
	}

	public Integer getSqxh() {
		return sqxh;
	}

	public void setSqxh(Integer sqxh) {
		this.sqxh = sqxh;
	}

	public Timestamp getCjsj() {
		return cjsj;
	}

	public void setCjsj(Timestamp cjsj) {
		this.cjsj = cjsj;
	}
	
	public String getXmxb() {
		return xmxb;
	}

	public void setXmxb(String xmxb) {
		this.xmxb = xmxb;
	}

	public NistzxmSaveReq getNistzxmSaveReq() {
		return nistzxmSaveReq;
	}

	public void setNistzxmSaveReq(NistzxmSaveReq nistzxmSaveReq) {
		this.nistzxmSaveReq = nistzxmSaveReq;
	}

	public Integer getCurrentWeek() {
		return currentWeek;
	}

	public void setCurrentWeek(Integer currentWeek) {
		this.currentWeek = currentWeek;
	}

	public List<NisZdytzReq> getNisZdytzReqList() {
		return nisZdytzReqList;
	}

	public void setNisZdytzReqList(List<NisZdytzReq> nisZdytzReqList) {
		this.nisZdytzReqList = nisZdytzReqList;
	}

}
