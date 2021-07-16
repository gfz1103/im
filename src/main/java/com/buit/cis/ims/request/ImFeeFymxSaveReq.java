package com.buit.cis.ims.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;


/**
 * 类名称：ImFeeFymx<br> 
 * 类描述：费用明细表_费用记账保存<br>
 * @author GONGFANGZHOU
 */
@ApiModel(value="费用明细表_saveReq")
public class ImFeeFymxSaveReq  {

	private List<FymxBody> fymxBody;

	public List<FymxBody> getFymxBody() {
		return fymxBody;
	}

	public void setFymxBody(List<FymxBody> fymxBody) {
		this.fymxBody = fymxBody;
	}

	@ApiModel(value = "费用明细")
	public static class FymxBody {

		@ApiModelProperty(value = "病人床号")
		private String brch;

		@ApiModelProperty(value = "病人科室")
		private Integer brks;

		@ApiModelProperty(value = "病人性别")
		private Integer brxb;

		@ApiModelProperty(value="病人姓名")
		private String brxm;

		@ApiModelProperty(value = "病人性质")
		private Integer brxz;

		@ApiModelProperty(value = "出院日期")
		private Timestamp cyrq;

		@ApiModelProperty(value = "费用病区")
		private Integer fybq;

		@NotNull
		@ApiModelProperty(value = "费用单价")
		private BigDecimal fydj;

		@ApiModelProperty(value = "费用科室 | 费用输入的科室(记帐,按输入科室核算时要用)")
		private Integer fyks;

		@NotNull
		@ApiModelProperty(value = "费用名称")
		private String fymc;

		@NotNull
		@ApiModelProperty(value = "费用日期")
		private Timestamp fyrq;

		@NotNull
		@ApiModelProperty(value = "费用数量")
		private BigDecimal fysl;

		@NotNull
		@ApiModelProperty(value = "费用序号")
		private Integer fyxh;

		@ApiModelProperty(value = "结算次数")
		private Integer jscs;

		@ApiModelProperty(value = "入院日期")
		private Timestamp ryrq;

		@ApiModelProperty(value = "费用单位")
		private String fydw;

		@ApiModelProperty(value="药房规格")
		private String yfgg;

		@ApiModelProperty(value = "药品产地")
		private Integer ypcd;

		@ApiModelProperty(value = "药品类型 | 0：费用 1：西药 2：中成药 3：中草药")
		private Integer yplx;

		@ApiModelProperty(value = "医生工号")
		private String ysgh;

		@NotNull
		@ApiModelProperty(value = "自负比例 | 同FEE_SFDLZFBL或 FEE_YPXZ,PUB_FYXZ表中自负比例对应")
		private BigDecimal zfbl;

		@NotNull
		@ApiModelProperty(value = "自负金额")
		private BigDecimal zfje;

		@NotNull
		@ApiModelProperty(value = "总计金额")
		private BigDecimal zjje;

		@ApiModelProperty(value = "执行科室 | 费用记帐科室(记帐,按执行科室核算时使用)")
		private Integer zxks;

		@NotNull
		@ApiModelProperty(value = "住院号")
		private Integer zyh;

		@ApiModelProperty(value = "住院号码")
		private String zyhm;
		
		@ApiModelProperty(value = "费用归并")
		private Integer fygb;

		public String getBrch() {
			return brch;
		}

		public void setBrch(String brch) {
			this.brch = brch;
		}

		public Integer getBrks() {
			return brks;
		}

		public void setBrks(Integer brks) {
			this.brks = brks;
		}

		public Integer getBrxb() {
			return brxb;
		}

		public void setBrxb(Integer brxb) {
			this.brxb = brxb;
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

		public Timestamp getCyrq() {
			return cyrq;
		}

		public void setCyrq(Timestamp cyrq) {
			this.cyrq = cyrq;
		}

		public Integer getFybq() {
			return fybq;
		}

		public void setFybq(Integer fybq) {
			this.fybq = fybq;
		}

		public BigDecimal getFydj() {
			return fydj;
		}

		public void setFydj(BigDecimal fydj) {
			this.fydj = fydj;
		}

		public Integer getFyks() {
			return fyks;
		}

		public void setFyks(Integer fyks) {
			this.fyks = fyks;
		}

		public String getFymc() {
			return fymc;
		}

		public void setFymc(String fymc) {
			this.fymc = fymc;
		}

		public Timestamp getFyrq() {
			return fyrq;
		}

		public void setFyrq(Timestamp fyrq) {
			this.fyrq = fyrq;
		}

		public BigDecimal getFysl() {
			return fysl;
		}

		public void setFysl(BigDecimal fysl) {
			this.fysl = fysl;
		}

		public Integer getFyxh() {
			return fyxh;
		}

		public void setFyxh(Integer fyxh) {
			this.fyxh = fyxh;
		}

		public Integer getJscs() {
			return jscs;
		}

		public void setJscs(Integer jscs) {
			this.jscs = jscs;
		}

		public Timestamp getRyrq() {
			return ryrq;
		}

		public void setRyrq(Timestamp ryrq) {
			this.ryrq = ryrq;
		}

		public String getFydw() {
			return fydw;
		}

		public void setFydw(String fydw) {
			this.fydw = fydw;
		}

		public String getYfgg() {
			return yfgg;
		}

		public void setYfgg(String yfgg) {
			this.yfgg = yfgg;
		}

		public Integer getYpcd() {
			return ypcd;
		}

		public void setYpcd(Integer ypcd) {
			this.ypcd = ypcd;
		}

		public Integer getYplx() {
			return yplx;
		}

		public void setYplx(Integer yplx) {
			this.yplx = yplx;
		}

		public String getYsgh() {
			return ysgh;
		}

		public void setYsgh(String ysgh) {
			this.ysgh = ysgh;
		}

		public BigDecimal getZfbl() {
			return zfbl;
		}

		public void setZfbl(BigDecimal zfbl) {
			this.zfbl = zfbl;
		}

		public BigDecimal getZfje() {
			return zfje;
		}

		public void setZfje(BigDecimal zfje) {
			this.zfje = zfje;
		}

		public BigDecimal getZjje() {
			return zjje;
		}

		public void setZjje(BigDecimal zjje) {
			this.zjje = zjje;
		}

		public Integer getZxks() {
			return zxks;
		}

		public void setZxks(Integer zxks) {
			this.zxks = zxks;
		}

		public Integer getZyh() {
			return zyh;
		}

		public void setZyh(Integer zyh) {
			this.zyh = zyh;
		}

		public String getZyhm() {
			return zyhm;
		}

		public void setZyhm(String zyhm) {
			this.zyhm = zyhm;
		}

		public Integer getFygb() {
			return fygb;
		}

		public void setFygb(Integer fygb) {
			this.fygb = fygb;
		}

	}
    
}
