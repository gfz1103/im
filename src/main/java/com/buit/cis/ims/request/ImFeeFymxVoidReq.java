package com.buit.cis.ims.request;

import java.math.BigDecimal;
import java.sql.Timestamp;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


/**
 * 类名称：ImFeeFymx<br> 
 * 类描述：费用明细表_费用记账作废<br>
 * @author GONGFANGZHOU
 */
@ApiModel(value="费用明细表_voidReq")
public class ImFeeFymxVoidReq  {

	@ApiModelProperty(value="材料项目分组合并 ")
    private String cldym;
		
	@ApiModelProperty(value="打折比例")
	private BigDecimal dzbl;
	
	@ApiModelProperty(value="费用病区 | 费用发生的病区")
	private Integer fybq;
	
	@ApiModelProperty(value="费用单价")
	private BigDecimal fydj;
	
	@ApiModelProperty(value="费用科室 | 费用输入的科室(记帐,按输入科室核算时要用)")
	private Integer fyks;
	
	@ApiModelProperty(value="费用名称")
	private String fymc;
	
	@ApiModelProperty(value="费用日期")
	private Timestamp fyrq;
	
	@ApiModelProperty(value="费用数量")
	private BigDecimal fysl;
	
	@ApiModelProperty(value="费用序号")
	private Integer fyxh;
	
	@ApiModelProperty(value="费用项目 | 指定FYXH归并的项目(同GY_SFMX表中SFXM对应)")
	private Integer fyxm;
	
	@ApiModelProperty(value="汇总日期")
	private Timestamp hzrq;
	
	@ApiModelProperty(value="记费日期 | 实际记费日期 写IM_FEE_FYMX时服务器时间")
	private Timestamp jfrq;
	
	@ApiModelProperty(value="机构ID")
	private Integer jgid;

    @ApiModelProperty(value="记录序号")
    private Integer jlxh;
    
    @ApiModelProperty(value="结算次数")
    private Integer jscs;
    
    @ApiModelProperty(value="确认工号")
    private String qrgh;
    
    @ApiModelProperty(value="输入工号")
    private String srgh;
    
    @ApiModelProperty(value="项目类型 | 1：病区系统记帐 2：药房系统记帐 3：医技系统记帐 4：住院系统记帐 5：手术麻醉记帐 9：自动累加费用")
    private Integer xmlx;
    
    @ApiModelProperty(value="婴儿判别 | 0,大人  1  小孩")
    private Integer yepb;
    
    @ApiModelProperty(value="月结日期")
    private String yjrq;
    
    @ApiModelProperty(value="药品产地")
    private Integer ypcd;
    
    @ApiModelProperty(value="药品类型 | 0：费用 1：西药 2：中成药 3：中草药")
    private Integer yplx;
    
    @ApiModelProperty(value="医生工号")
    private String ysgh;
    
    @ApiModelProperty(value="医嘱序号 | 同CIS_HZYZ表中的医嘱序号对应")
    private Integer yzxh;
    
    @ApiModelProperty(value="自负比例 | 同FEE_SFDLZFBL或 FEE_YPXZ,PUB_FYXZ表中自负比例对应")
    private BigDecimal zfbl;
    
    @ApiModelProperty(value="自负金额")
    private BigDecimal zfje;
    
    @ApiModelProperty(value="作废数量")
    private BigDecimal zfsl;
    
    @ApiModelProperty(value="总计金额")
    private BigDecimal zjje;
    
    @ApiModelProperty(value="自理金额")
    private BigDecimal zlje;
    
    @ApiModelProperty(value="诊疗小组")
    private Integer zlxz;
    
    @ApiModelProperty(value="执行科室 | 费用记帐科室(记帐,按执行科室核算时使用)")
    private Integer zxks;
    
    @ApiModelProperty(value="住院号")
    private Integer zyh;
    
    @ApiModelProperty(value="病人信息")
    private BrxxBody brxxbody;
    
    public String getCldym() {
		return cldym;
	}

	public void setCldym(String cldym) {
		this.cldym = cldym;
	}

	public BigDecimal getDzbl() {
		return dzbl;
	}

	public void setDzbl(BigDecimal dzbl) {
		this.dzbl = dzbl;
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

	public Integer getFyxm() {
		return fyxm;
	}

	public void setFyxm(Integer fyxm) {
		this.fyxm = fyxm;
	}

	public Timestamp getHzrq() {
		return hzrq;
	}

	public void setHzrq(Timestamp hzrq) {
		this.hzrq = hzrq;
	}

	public Timestamp getJfrq() {
		return jfrq;
	}

	public void setJfrq(Timestamp jfrq) {
		this.jfrq = jfrq;
	}

	public Integer getJgid() {
		return jgid;
	}

	public void setJgid(Integer jgid) {
		this.jgid = jgid;
	}

	public Integer getJlxh() {
		return jlxh;
	}

	public void setJlxh(Integer jlxh) {
		this.jlxh = jlxh;
	}

	public Integer getJscs() {
		return jscs;
	}

	public void setJscs(Integer jscs) {
		this.jscs = jscs;
	}

	public String getQrgh() {
		return qrgh;
	}

	public void setQrgh(String qrgh) {
		this.qrgh = qrgh;
	}

	public String getSrgh() {
		return srgh;
	}

	public void setSrgh(String srgh) {
		this.srgh = srgh;
	}

	public Integer getXmlx() {
		return xmlx;
	}

	public void setXmlx(Integer xmlx) {
		this.xmlx = xmlx;
	}

	public Integer getYepb() {
		return yepb;
	}

	public void setYepb(Integer yepb) {
		this.yepb = yepb;
	}

	public String getYjrq() {
		return yjrq;
	}

	public void setYjrq(String yjrq) {
		this.yjrq = yjrq;
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

	public Integer getYzxh() {
		return yzxh;
	}

	public void setYzxh(Integer yzxh) {
		this.yzxh = yzxh;
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

	public BigDecimal getZfsl() {
		return zfsl;
	}

	public void setZfsl(BigDecimal zfsl) {
		this.zfsl = zfsl;
	}

	public BigDecimal getZjje() {
		return zjje;
	}

	public void setZjje(BigDecimal zjje) {
		this.zjje = zjje;
	}

	public BigDecimal getZlje() {
		return zlje;
	}

	public void setZlje(BigDecimal zlje) {
		this.zlje = zlje;
	}

	public Integer getZlxz() {
		return zlxz;
	}

	public void setZlxz(Integer zlxz) {
		this.zlxz = zlxz;
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

	public BrxxBody getBrxxbody() {
		return brxxbody;
	}

	public void setBrxxbody(BrxxBody brxxbody) {
		this.brxxbody = brxxbody;
	}

	public static class BrxxBody {
    	
    	@ApiModelProperty(value="病人病区")
    	private Integer brbq;
    	
    	@ApiModelProperty(value="病人床号")
    	private String brch;
    	
    	@ApiModelProperty(value="病人科室")
    	private Integer brks;
    	
    	@ApiModelProperty(value="病人性别")
    	private Integer brxb;
    	
    	@ApiModelProperty(value="病人姓名")
    	private String brxm;
    	
    	@ApiModelProperty(value="病人性质")
    	private Integer brxz;
    	
    	@ApiModelProperty(value="结算次数")
    	private Integer jscs;
    	
    	@ApiModelProperty(value="住院号")
    	private Integer zyh;
    	
    	@ApiModelProperty(value="住院号码")
    	private String zyhm;
    	
    	@ApiModelProperty(value="费用病区")
    	private Integer fybq;

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

		public Integer getJscs() {
			return jscs;
		}

		public void setJscs(Integer jscs) {
			this.jscs = jscs;
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

		public Integer getFybq() {
			return fybq;
		}

		public void setFybq(Integer fybq) {
			this.fybq = fybq;
		}

    }
    
}
