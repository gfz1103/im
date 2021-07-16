package com.buit.cis.ims.response;

import com.buit.commons.PageQuery;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;


/**
 * 类名称：ImFeeFymxAccountingResp<br>
 * 类描述：费用明细表<br>
 * @author GONGFANGZHOU
 */
@ApiModel(value="费用明细表_accountingResp")
public class ImFeeFymxAccountingResp  extends PageQuery {
	
    @ApiModelProperty(value="住院号码")
	private String zyhm;
	
	@ApiModelProperty(value="病人姓名")
	private String brxm;
	
	@ApiModelProperty(value="记账日期")
	private Date jfrq;
	
	@ApiModelProperty(value="费用日期")
	private Date fyrq;
    
	@ApiModelProperty(value="药品类型")
	private Integer yplx;
	
	@ApiModelProperty(value="费用序号")
	private Integer fyxh;
	
	@ApiModelProperty(value="费用名称")
	private String fymc;
	
	@ApiModelProperty(value="费用数量")
	private BigDecimal fysl;
	
	@ApiModelProperty(value="费用单价")
	private BigDecimal fydj;
	
	@ApiModelProperty(value="自负比例")
	private BigDecimal zfbl;
	
	@ApiModelProperty(value="费用科室")
	private Integer fyks;	
	
	@ApiModelProperty(value="医生姓名")
	private String ygxm;
	
	@ApiModelProperty(value="机构id")
    private Integer jgid;

    @ApiModelProperty(value = "总计金额")
    private BigDecimal zjje;

    @ApiModelProperty(value = "性质名称")
    private String xzmc;

    @ApiModelProperty(value = "自负金额")
    private BigDecimal zfje;

    @ApiModelProperty(value = "作废判别 | 0：正常记账    1：已作废    2：作废抵账")
    private int zfpb;

    @ApiModelProperty(value = "作废日期")
    private Timestamp zfrq;

    public String getZyhm() {
        return zyhm;
    }

    public void setZyhm(String zyhm) {
        this.zyhm = zyhm;
    }

    public String getBrxm() {
		return brxm;
	}

	public void setBrxm(String brxm) {
		this.brxm = brxm;
	}

	public Date getJfrq() {
		return jfrq;
	}

	public void setJfrq(Date jfrq) {
		this.jfrq = jfrq;
	}

	public Date getFyrq() {
		return fyrq;
	}

	public void setFyrq(Date fyrq) {
		this.fyrq = fyrq;
	}

	public Integer getYplx() {
		return yplx;
	}

	public void setYplx(Integer yplx) {
		this.yplx = yplx;
	}

	public Integer getFyxh() {
		return fyxh;
	}

	public void setFyxh(Integer fyxh) {
		this.fyxh = fyxh;
	}

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

	public BigDecimal getZfbl() {
		return zfbl;
	}

	public void setZfbl(BigDecimal zfbl) {
		this.zfbl = zfbl;
	}

	public Integer getFyks() {
		return fyks;
	}

	public void setFyks(Integer fyks) {
		this.fyks = fyks;
	}

	public String getYgxm() {
		return ygxm;
	}

	public void setYgxm(String ygxm) {
		this.ygxm = ygxm;
	}

	public Integer getJgid() {
		return jgid;
	}

	public void setJgid(Integer jgid) {
		this.jgid = jgid;
	}

	public BigDecimal getZjje() {
		return zjje;
	}

	public void setZjje(BigDecimal zjje) {
		this.zjje = zjje;
	}

	public String getXzmc() {
		return xzmc;
	}

	public void setXzmc(String xzmc) {
		this.xzmc = xzmc;
    }

    public BigDecimal getZfje() {
        return zfje;
    }

    public void setZfje(BigDecimal zfje) {
        this.zfje = zfje;
    }

    public int getZfpb() {
        return zfpb;
    }

    public void setZfpb(int zfpb) {
        this.zfpb = zfpb;
    }

    public Timestamp getZfrq() {
        return zfrq;
    }

    public void setZfrq(Timestamp zfrq) {
        this.zfrq = zfrq;
    }
}
