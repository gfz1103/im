
package com.buit.cis.nurse.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;


/**
 * 类名称：CisHzyzJySqdReq<br>
 * 类描述：住院检验申请单保存<br>
 * @author GONGFANGZHOU
 */
@ApiModel(value="住院检验申请单_jysqdReq")
public class CisHzyzJySqdReq {
	
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
    
    @ApiModelProperty(value="病人性质")
    private Integer brxz;
    
    @ApiModelProperty(value="申请时间")
    private Timestamp sqsj;

	@ApiModelProperty(value="加急标志0普通1加急")
	private Integer jjbz;

	@ApiModelProperty(value="备注信息")
	private String bzxx;

	@ApiModelProperty(value="组套集合")
    private List<Jyzt> jyzts;
    
    @ApiModelProperty(value="组套明细集合")
    private List<JyXmDetail> xmDetails;
    
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

	public Timestamp getSqsj() {
		return sqsj;
	}

	public void setSqsj(Timestamp sqsj) {
		this.sqsj = sqsj;
	}

	public List<Jyzt> getJyzts() {
		return jyzts;
	}

	public void setJyzts(List<Jyzt> jyzts) {
		this.jyzts = jyzts;
	}

	public List<JyXmDetail> getXmDetails() {
		return xmDetails;
	}

	public void setXmDetails(List<JyXmDetail> xmDetails) {
		this.xmDetails = xmDetails;
	}

	public Integer getJjbz() {
		return jjbz;
	}

	public void setJjbz(Integer jjbz) {
		this.jjbz = jjbz;
	}

	public String getBzxx() {
		return bzxx;
	}

	public void setBzxx(String bzxx) {
		this.bzxx = bzxx;
	}

	public static class Jyzt {
	    @ApiModelProperty(value="组套编号")
	    private Integer ztbh;
    
	    @ApiModelProperty(value="组套名称")
	    private String ztmc;
	
	    public Integer getZtbh() {
	        return ztbh;
	    }
	
	    public void setZtbh(Integer ztbh) {
	        this.ztbh = ztbh;
	    }
	
	    public String getZtmc() {
	        return ztmc;
	    }
	
	    public void setZtmc(String ztmc) {
	        this.ztmc = ztmc;
	    }
    }

	public static class JyXmDetail{
	    @ApiModelProperty(value="费用单价")
	    private BigDecimal fydj;
	    
	    @ApiModelProperty(value="医嘱名称")
	    private String yzmc;
	    
	    @ApiModelProperty(value="项目编号")
	    private Integer xmbh;
	    
	    @ApiModelProperty(value="记录编号")
	    private Integer jlbh;
	    
	    @ApiModelProperty(value="组套编号")
	    private Integer ztbh;
	    
	    @ApiModelProperty(value="费用数量")
	    private BigDecimal fysl;
	
		public BigDecimal getFydj() {
			return fydj;
		}
	
		public void setFydj(BigDecimal fydj) {
			this.fydj = fydj;
		}
	
		public String getYzmc() {
			return yzmc;
		}
	
		public void setYzmc(String yzmc) {
			this.yzmc = yzmc;
		}
	
		public Integer getXmbh() {
			return xmbh;
		}
	
		public void setXmbh(Integer xmbh) {
			this.xmbh = xmbh;
		}
	
		public Integer getJlbh() {
			return jlbh;
		}
	
		public void setJlbh(Integer jlbh) {
			this.jlbh = jlbh;
		}
	
		public Integer getZtbh() {
			return ztbh;
		}
	
		public void setZtbh(Integer ztbh) {
			this.ztbh = ztbh;
		}
	
		public BigDecimal getFysl() {
			return fysl;
		}
	
		public void setFysl(BigDecimal fysl) {
			this.fysl = fysl;
		}
	}
    
}
