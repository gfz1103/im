package com.buit.cis.ims.request;

import com.buit.commons.PageQuery;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;
import java.sql.Timestamp;


/**
 * 类名称：ImFeeFymxYj<br>
 * 类描述：医技费用明细表<br>
 * @author ZHOUHAISHENG
 */
@ApiModel(value="医技费用明细表_JyReq")
public class ImFeeFymxYjJyReq  extends PageQuery {

    @ApiModelProperty(value="开始时间")
    private String beginDate;
	@ApiModelProperty(value="结束时间")
    private String endDate;
    @ApiModelProperty(value="病区")
    private Integer brbq;
    @ApiModelProperty(value="科室")
    private Integer brks;
    @ApiModelProperty(value="住院号码、姓名、床号")
    private String comprehensive;
    @ApiModelProperty(value="医嘱类型", hidden = true)
    private Integer yzlx;
    @ApiModelProperty(value="机构id", hidden = true)
    private Integer jgid;
    @ApiModelProperty(value="执行状态(0:未执行5:已执行)")
    private Integer zxzt;
    
	public String getBeginDate() {
		return beginDate;
	}
	public void setBeginDate(String beginDate) {
		this.beginDate = beginDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public Integer getBrbq() {
		return brbq;
	}
	public void setBrbq(Integer brbq) {
		this.brbq = brbq;
	}
	public Integer getBrks() {
		return brks;
	}
	public void setBrks(Integer brks) {
		this.brks = brks;
	}
	public String getComprehensive() {
		return comprehensive;
	}
	public void setComprehensive(String comprehensive) {
		this.comprehensive = comprehensive;
	}
	public Integer getYzlx() {
		return yzlx;
	}
	public void setYzlx(Integer yzlx) {
		this.yzlx = yzlx;
	}
	public Integer getJgid() {
		return jgid;
	}
	public void setJgid(Integer jgid) {
		this.jgid = jgid;
	}
	public Integer getZxzt() {
		return zxzt;
	}
	public void setZxzt(Integer zxzt) {
		this.zxzt = zxzt;
	}

	
}
