   
package com.buit.cis.dctwork.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


/**
 * 类名称：CisHzyzXmImportReq<br> 
 * 类描述：住院_病区医嘱_项目组套调入<br>
 * @author GONGFANGZHOU
 */
@ApiModel(value="住院_病区医嘱_xmimportReq")
public class CisHzyzXmImportReq {

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
    
    @ApiModelProperty(value="组套记录")
    private ZtBody ztBody;
	
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

	public ZtBody getZtBody() {
		return ztBody;
	}

	public void setZtBody(ZtBody ztBody) {
		this.ztBody = ztBody;
	}

	public static class ZtBody {
		
		@ApiModelProperty(value="机构id")
	    private Integer jgid;
		
	    @ApiModelProperty(value="科室代码")
	    private Integer ksdm;
	    
	    @ApiModelProperty(value="是否启用")
	    private Integer sfqy;
	    
	    @ApiModelProperty(value="所属类别")
	    private Integer sslb;
	    
	    @ApiModelProperty(value="项目选取")
	    private Integer xmxq;
	    
	    @ApiModelProperty(value="员工代码")
	    private Integer ygdm;
	    
	    @ApiModelProperty(value="组套编号")
	    private Integer ztbh;
	    
	    @ApiModelProperty(value="组套类别")
	    private Integer ztlb;
	    
	    @ApiModelProperty(value="组套名称")
	    private String ztmc;

		public Integer getJgid() {
			return jgid;
		}

		public void setJgid(Integer jgid) {
			this.jgid = jgid;
		}

		public Integer getKsdm() {
			return ksdm;
		}

		public void setKsdm(Integer ksdm) {
			this.ksdm = ksdm;
		}

		public Integer getSfqy() {
			return sfqy;
		}

		public void setSfqy(Integer sfqy) {
			this.sfqy = sfqy;
		}

		public Integer getSslb() {
			return sslb;
		}

		public void setSslb(Integer sslb) {
			this.sslb = sslb;
		}

		public Integer getXmxq() {
			return xmxq;
		}

		public void setXmxq(Integer xmxq) {
			this.xmxq = xmxq;
		}

		public Integer getYgdm() {
			return ygdm;
		}

		public void setYgdm(Integer ygdm) {
			this.ygdm = ygdm;
		}

		public Integer getZtbh() {
			return ztbh;
		}

		public void setZtbh(Integer ztbh) {
			this.ztbh = ztbh;
		}

		public Integer getZtlb() {
			return ztlb;
		}

		public void setZtlb(Integer ztlb) {
			this.ztlb = ztlb;
		}

		public String getZtmc() {
			return ztmc;
		}

		public void setZtmc(String ztmc) {
			this.ztmc = ztmc;
		}

		
	    
	}
	
	
}
