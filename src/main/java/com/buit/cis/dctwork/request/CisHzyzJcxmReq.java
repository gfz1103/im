   
package com.buit.cis.dctwork.request;

import java.sql.Timestamp;
import java.util.List;

import com.buit.apply.request.DicZlxmSqdReqYsJx01;
import com.buit.apply.request.DicZlxmSqdReqYsJx02;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


/**
 * 类名称：CisHzyzJcxmReq<br> 
 * 类描述：住院_病区医嘱_检查申请<br>
 * @author GONGFANGZHOU
 */
@ApiModel(value="住院_病区医嘱_jcxmReq")
public class CisHzyzJcxmReq {

	@ApiModelProperty(value="申请id")
    private Integer sqid;
	
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
    
    @ApiModelProperty(value="病人姓名")
    private Integer brxz;
    
    @ApiModelProperty(value="方式")
    private String op;
    
    @ApiModelProperty(value="检查时间")
    private Timestamp jcsj;
    
    @ApiModelProperty(value="申请对象")
    DicZlxmSqdReqYsJx01 ysJx01;
    
    @ApiModelProperty(value="费用明细")
    List<DicZlxmSqdReqYsJx02> ysJx02List;

	public Integer getSqid() {
		return sqid;
	}

	public void setSqid(Integer sqid) {
		this.sqid = sqid;
	}

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

	public Integer getBrxz() {
		return brxz;
	}

	public void setBrxz(Integer brxz) {
		this.brxz = brxz;
	}

	public String getOp() {
		return op;
	}

	public void setOp(String op) {
		this.op = op;
	}

	public Timestamp getJcsj() {
		return jcsj;
	}

	public void setJcsj(Timestamp jcsj) {
		this.jcsj = jcsj;
	}

	public DicZlxmSqdReqYsJx01 getYsJx01() {
		return ysJx01;
	}

	public void setYsJx01(DicZlxmSqdReqYsJx01 ysJx01) {
		this.ysJx01 = ysJx01;
	}

	public List<DicZlxmSqdReqYsJx02> getYsJx02List() {
		return ysJx02List;
	}

	public void setYsJx02List(List<DicZlxmSqdReqYsJx02> ysJx02List) {
		this.ysJx02List = ysJx02List;
	}
	
	
}
