   
package com.buit.cis.nurse.request;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


/**
 * 类名称：CisHzyzNurseExReq<br> 
 * 类描述：住院_病区医嘱_护士执行<br>
 * @author GONGFANGZHOU
 */
@ApiModel(value="住院_病区医嘱_nurseexReq")
public class CisHzyzNurseExReq {
	
	@ApiModelProperty(value="病人病区")
	private Integer brbq;
	
	@ApiModelProperty(value="病人病区权限名称")
	private String qxmc;
	
	@ApiModelProperty(value="医嘱集合")
    private List<CisHzyzBody> cisHzyzBody;
    
    @ApiModelProperty(value="医嘱确认执行集合")
    private List<CisHzyzBodyZx> cisHzyzBodyZx;
    
    @ApiModelProperty(value="临时医嘱(0:长期1:临时2:全部)")
	private Integer lsyz;
    
	public List<CisHzyzBody> getCisHzyzBody() {
		return cisHzyzBody;
	}

	public void setCisHzyzBody(List<CisHzyzBody> cisHzyzBody) {
		this.cisHzyzBody = cisHzyzBody;
	}

	public List<CisHzyzBodyZx> getCisHzyzBodyZx() {
		return cisHzyzBodyZx;
	}

	public void setCisHzyzBodyZx(List<CisHzyzBodyZx> cisHzyzBodyZx) {
		this.cisHzyzBodyZx = cisHzyzBodyZx;
	}

	public Integer getBrbq() {
		return brbq;
	}

	public void setBrbq(Integer brbq) {
		this.brbq = brbq;
	}

	public String getQxmc() {
		return qxmc;
	}

	public void setQxmc(String qxmc) {
		this.qxmc = qxmc;
	}

	public Integer getLsyz() {
		return lsyz;
	}

	public void setLsyz(Integer lsyz) {
		this.lsyz = lsyz;
	}

	public static class CisHzyzBody{
		
		@ApiModelProperty(value="费用名称")
		private String fymc;
		
		@ApiModelProperty(value="费用数量")
		private BigDecimal fysl;
		
		@ApiModelProperty(value="费用单价")
		private BigDecimal fydj;
		
		@ApiModelProperty(value="总计金额")
		private BigDecimal zjje;
		
		@ApiModelProperty(value="医生工号")
		private Integer ysgh;
		
		@ApiModelProperty(value="输入工号")
		private Integer srgh;
		
		@ApiModelProperty(value="费用科室")
		private Integer fyks;
		
		@ApiModelProperty(value="记录序号")
		private Integer jlxh;
		
		@ApiModelProperty(value="住院号")
		private Integer zyh;
		
		@ApiModelProperty(value="费用序号")
		private Integer fyxh;
		
		@ApiModelProperty(value="药品产地")
		private Integer ypcd;
		
		@ApiModelProperty(value="项目类型")
		private Integer xmlx;
		
		@ApiModelProperty(value="药品类型")
		private Integer yplx;
		
		@ApiModelProperty(value="病人性质")
		private Integer brxz;
		
		@ApiModelProperty(value="执行科室")
		private Integer zxks;
		
		@ApiModelProperty(value="医嘱序号")
		private Integer yzxh;
		
		@ApiModelProperty(value="是否ok")
		private String ok;
		
		@ApiModelProperty(value="总计金额")
		private BigDecimal je;
		
		@ApiModelProperty(value="药品序号")
		private Integer ypxh;
		
		@ApiModelProperty(value="一次数量")
		private BigDecimal ycsl;

		public String getFymc() {
			return fymc;
		}

		public void setFymc(String fymc) {
			this.fymc = fymc;
		}

		public BigDecimal getFysl() {
			return fysl;
		}

		public void setFysl(BigDecimal fysl) {
			this.fysl = fysl;
		}

		public BigDecimal getFydj() {
			return fydj;
		}

		public void setFydj(BigDecimal fydj) {
			this.fydj = fydj;
		}

		public BigDecimal getZjje() {
			return zjje;
		}

		public void setZjje(BigDecimal zjje) {
			this.zjje = zjje;
		}

		public Integer getYsgh() {
			return ysgh;
		}

		public void setYsgh(Integer ysgh) {
			this.ysgh = ysgh;
		}

		public Integer getSrgh() {
			return srgh;
		}

		public void setSrgh(Integer srgh) {
			this.srgh = srgh;
		}

		public Integer getFyks() {
			return fyks;
		}

		public void setFyks(Integer fyks) {
			this.fyks = fyks;
		}

		public Integer getJlxh() {
			return jlxh;
		}

		public void setJlxh(Integer jlxh) {
			this.jlxh = jlxh;
		}

		public Integer getZyh() {
			return zyh;
		}

		public void setZyh(Integer zyh) {
			this.zyh = zyh;
		}

		public Integer getFyxh() {
			return fyxh;
		}

		public void setFyxh(Integer fyxh) {
			this.fyxh = fyxh;
		}

		public Integer getYpcd() {
			return ypcd;
		}

		public void setYpcd(Integer ypcd) {
			this.ypcd = ypcd;
		}

		public Integer getXmlx() {
			return xmlx;
		}

		public void setXmlx(Integer xmlx) {
			this.xmlx = xmlx;
		}

		public Integer getYplx() {
			return yplx;
		}

		public void setYplx(Integer yplx) {
			this.yplx = yplx;
		}

		public Integer getBrxz() {
			return brxz;
		}

		public void setBrxz(Integer brxz) {
			this.brxz = brxz;
		}

		public Integer getZxks() {
			return zxks;
		}

		public void setZxks(Integer zxks) {
			this.zxks = zxks;
		}

		public Integer getYzxh() {
			return yzxh;
		}

		public void setYzxh(Integer yzxh) {
			this.yzxh = yzxh;
		}

		public String getOk() {
			return ok;
		}

		public void setOk(String ok) {
			this.ok = ok;
		}

		public BigDecimal getJe() {
			return je;
		}

		public void setJe(BigDecimal je) {
			this.je = je;
		}

		public Integer getYpxh() {
			return ypxh;
		}

		public void setYpxh(Integer ypxh) {
			this.ypxh = ypxh;
		}

		public BigDecimal getYcsl() {
			return ycsl;
		}

		public void setYcsl(BigDecimal ycsl) {
			this.ycsl = ycsl;
		}
		
		
		
	}
	
	public static class CisHzyzBodyZx{
		
		@ApiModelProperty(value="费用名称")
		private String fymc;
		
		@ApiModelProperty(value="费用数量")
		private BigDecimal fysl;
		
		@ApiModelProperty(value="费用单价")
		private BigDecimal fydj;
		
		@ApiModelProperty(value="总计金额")
		private BigDecimal zjje;
		
		@ApiModelProperty(value="医生工号")
		private Integer ysgh;
		
		@ApiModelProperty(value="输入工号")
		private Integer srgh;
		
		@ApiModelProperty(value="费用科室")
		private Integer fyks;
		
		@ApiModelProperty(value="记录序号")
		private Integer jlxh;
		
		@ApiModelProperty(value="住院号")
		private Integer zyh;
		
		@ApiModelProperty(value="费用序号")
		private Integer fyxh;
		
		@ApiModelProperty(value="药品产地")
		private Integer ypcd;
		
		@ApiModelProperty(value="项目类型")
		private Integer xmlx;
		
		@ApiModelProperty(value="药品类型")
		private Integer yplx;
		
		@ApiModelProperty(value="病人性质")
		private Integer brxz;
		
		@ApiModelProperty(value="执行科室")
		private Integer zxks;
		
		@ApiModelProperty(value="医嘱序号")
		private Integer yzxh;
		
		@ApiModelProperty(value="确认时间")
		private Timestamp qrsj;
		
		@ApiModelProperty(value="历史标志")
		private Integer lsbz;
		
		@ApiModelProperty(value="总计金额")
		private BigDecimal je;
		
		@ApiModelProperty(value="费用序号")
		private Integer ypxh;
		
		@ApiModelProperty(value="一次数量")
		private BigDecimal ycsl;

		public String getFymc() {
			return fymc;
		}

		public void setFymc(String fymc) {
			this.fymc = fymc;
		}

		public BigDecimal getFysl() {
			return fysl;
		}

		public void setFysl(BigDecimal fysl) {
			this.fysl = fysl;
		}

		public BigDecimal getFydj() {
			return fydj;
		}

		public void setFydj(BigDecimal fydj) {
			this.fydj = fydj;
		}

		public BigDecimal getZjje() {
			return zjje;
		}

		public void setZjje(BigDecimal zjje) {
			this.zjje = zjje;
		}

		public Integer getYsgh() {
			return ysgh;
		}

		public void setYsgh(Integer ysgh) {
			this.ysgh = ysgh;
		}

		public Integer getSrgh() {
			return srgh;
		}

		public void setSrgh(Integer srgh) {
			this.srgh = srgh;
		}

		public Integer getFyks() {
			return fyks;
		}

		public void setFyks(Integer fyks) {
			this.fyks = fyks;
		}

		public Integer getJlxh() {
			return jlxh;
		}

		public void setJlxh(Integer jlxh) {
			this.jlxh = jlxh;
		}

		public Integer getZyh() {
			return zyh;
		}

		public void setZyh(Integer zyh) {
			this.zyh = zyh;
		}

		public Integer getFyxh() {
			return fyxh;
		}

		public void setFyxh(Integer fyxh) {
			this.fyxh = fyxh;
		}

		public Integer getYpcd() {
			return ypcd;
		}

		public void setYpcd(Integer ypcd) {
			this.ypcd = ypcd;
		}

		public Integer getXmlx() {
			return xmlx;
		}

		public void setXmlx(Integer xmlx) {
			this.xmlx = xmlx;
		}

		public Integer getYplx() {
			return yplx;
		}

		public void setYplx(Integer yplx) {
			this.yplx = yplx;
		}

		public Integer getBrxz() {
			return brxz;
		}

		public void setBrxz(Integer brxz) {
			this.brxz = brxz;
		}

		public Integer getZxks() {
			return zxks;
		}

		public void setZxks(Integer zxks) {
			this.zxks = zxks;
		}

		public Integer getYzxh() {
			return yzxh;
		}

		public void setYzxh(Integer yzxh) {
			this.yzxh = yzxh;
		}

		public Timestamp getQrsj() {
			return qrsj;
		}

		public void setQrsj(Timestamp qrsj) {
			this.qrsj = qrsj;
		}

		public Integer getLsbz() {
			return lsbz;
		}

		public void setLsbz(Integer lsbz) {
			this.lsbz = lsbz;
		}

		public BigDecimal getJe() {
			return je;
		}

		public void setJe(BigDecimal je) {
			this.je = je;
		}

		public Integer getYpxh() {
			return ypxh;
		}

		public void setYpxh(Integer ypxh) {
			this.ypxh = ypxh;
		}

		public BigDecimal getYcsl() {
			return ycsl;
		}

		public void setYcsl(BigDecimal ycsl) {
			this.ycsl = ycsl;
		}
		
		
	}
	
}
