   
package com.buit.cis.dctwork.request;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

import javax.validation.constraints.NotNull;

import com.buit.commons.PageQuery;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


/**
 * 类名称：CisHzyz<br> 
 * 类描述：住院_病区医嘱<br>
 * @author GONGFANGZHOU
 */
@ApiModel(value="住院_病区医嘱_HerbalReq")
public class CisHzyzHerbalReq {
	@ApiModelProperty(value="医嘱集合")
    private List<CisHzyzHerbalBody> cisHzyzReqList;
    
    @ApiModelProperty(value="附加项目集合")
    private List<CisHzyzHerbalBody> cisHzyzReqFjList;
	
	@ApiModelProperty(value="病人科室")
    private Integer brks;
	
    @ApiModelProperty(value="病人病区")
    private Integer brbq;
    
    @ApiModelProperty(value="病人床号")
    private String brch;
    
    @ApiModelProperty(value="住院号")
    @NotNull(message = "住院号不能为空")
    private Integer zyh;
    
	@ApiModelProperty(value="使用频次")
	@NotNull(message = "使用频次不能为空")
    private String sypc;
	
	@ApiModelProperty(value="医嘱执行时间")
	@NotNull(message = "医嘱执行时间不能为空")
    private String yzzxsj;
	
	@ApiModelProperty(value="药品用法")
	@NotNull(message = "药品用法不能为空")
    private Integer ypyf;

	@ApiModelProperty(value="处方帖数")
	@NotNull(message = "处方帖数不能为空")
    private Integer cfts;
	
	@ApiModelProperty(value="开嘱时间")
	@NotNull(message = "开嘱时间不能为空")
    private Timestamp kssj;

	@ApiModelProperty(value="备注信息")
    private String bzxx;
	
	@ApiModelProperty(value="组套记录序号")
    private Integer ztjlxh;

	@ApiModelProperty(value="一次剂量")
	@NotNull(message = "一次剂量不能为空")
	private BigDecimal ycjl;

	@ApiModelProperty(value="每日次数")
	@NotNull(message = "每日次数不能为空")
	private Integer mrcs;

	@ApiModelProperty(value="一次数量")
	@NotNull(message = "一次数量不能为空")
	private BigDecimal ycsl;
	
	public List<CisHzyzHerbalBody> getCisHzyzReqList() {
		return cisHzyzReqList;
	}

	public void setCisHzyzReqList(List<CisHzyzHerbalBody> cisHzyzReqList) {
		this.cisHzyzReqList = cisHzyzReqList;
	}

	public List<CisHzyzHerbalBody> getCisHzyzReqFjList() {
		return cisHzyzReqFjList;
	}

	public void setCisHzyzReqFjList(List<CisHzyzHerbalBody> cisHzyzReqFjList) {
		this.cisHzyzReqFjList = cisHzyzReqFjList;
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

	public String getSypc() {
		return sypc;
	}

	public void setSypc(String sypc) {
		this.sypc = sypc;
	}

	public String getYzzxsj() {
		return yzzxsj;
	}

	public void setYzzxsj(String yzzxsj) {
		this.yzzxsj = yzzxsj;
	}

	public Integer getYpyf() {
		return ypyf;
	}

	public void setYpyf(Integer ypyf) {
		this.ypyf = ypyf;
	}

	public Integer getCfts() {
		return cfts;
	}

	public void setCfts(Integer cfts) {
		this.cfts = cfts;
	}

	public Timestamp getKssj() {
		return kssj;
	}

	public void setKssj(Timestamp kssj) {
		this.kssj = kssj;
	}

	public String getBzxx() {
		return bzxx;
	}

	public void setBzxx(String bzxx) {
		this.bzxx = bzxx;
	}
	
	public Integer getZtjlxh() {
		return ztjlxh;
	}

	public void setZtjlxh(Integer ztjlxh) {
		this.ztjlxh = ztjlxh;
	}

	public BigDecimal getYcjl() {
		return ycjl;
	}

	public void setYcjl(BigDecimal ycjl) {
		this.ycjl = ycjl;
	}

	public Integer getMrcs() {
		return mrcs;
	}

	public void setMrcs(Integer mrcs) {
		this.mrcs = mrcs;
	}

	public BigDecimal getYcsl() {
		return ycsl;
	}

	public void setYcsl(BigDecimal ycsl) {
		this.ycsl = ycsl;
	}

	public static class CisHzyzHerbalBody {

		@ApiModelProperty(value="操作工号")
	    private String czgh;
		
		@ApiModelProperty(value="滴速")
	    private String ds;
		
		@ApiModelProperty(value="复核标志")
	    private Integer fhbz;
		
		@ApiModelProperty(value="复核工号")
	    private String fhgh;
		
		@ApiModelProperty(value="复核时间")
	    private Timestamp fhsj;
		
		@ApiModelProperty(value="发药方式")
	    private Integer fyfs;
		
		@ApiModelProperty(value="发药属性")
	    private Integer fysx;
		
		@ApiModelProperty(value="计费标志")
	    private Integer jfbz;
		
		@ApiModelProperty(value="机构id", hidden = true)
	    private Integer jgid;
		
		@ApiModelProperty(value="剂量单位")
	    private String jldw;
		
		@ApiModelProperty(value="记录序号")
	    private Integer jlxh;
		
		@ApiModelProperty(value="脚注")
	    private Integer jz;
		
		@ApiModelProperty(value="抗菌药物是否审批")
	    private Integer kjywsp;
		
		@ApiModelProperty(value="历史标志")
	    private Integer lsbz;
		
		@ApiModelProperty(value="临时医嘱")
	    private Integer lsyz;
		
		@ApiModelProperty(value="每日次数")
	    private Integer mrcs;
		
		@ApiModelProperty(value="每周次数")
	    private Integer mzcs;
		
		@ApiModelProperty(value="操作状态")
	    private String opStatus;
		
		@ApiModelProperty(value="皮试判别")
	    private Integer pspb;
		
		@ApiModelProperty(value="审方结果")
	    private Integer sfjg;
		
		@ApiModelProperty(value="首日次数")
	    private Integer srcs;
		
		@ApiModelProperty(value="使用标志")
	    private Integer sybz;
		
		@ApiModelProperty(value="tpn")
	    private Integer tpn;
		
		@ApiModelProperty(value="停嘱复核标志")
	    private Integer tzfhbz;
		
		@ApiModelProperty(value="停嘱时间")
	    private Timestamp tzsj;
		
		@ApiModelProperty(value="停嘱医生")
	    private String tzys;
		
		@ApiModelProperty(value="项目类型")
	    private Integer xmlx;
		
		@ApiModelProperty(value="一次剂量")
	    private BigDecimal ycjl;
		
		@ApiModelProperty(value="一次数量")
	    private BigDecimal ycsl;
		
		@ApiModelProperty(value="婴儿判别")
	    private Integer yepb;
		
		@ApiModelProperty(value="药房包装")
	    private Integer yfbz;
		
		@ApiModelProperty(value="药房单位")
	    private String yfdw;
		
		@ApiModelProperty(value="药房规格")
	    private String yfgg;
		
		@ApiModelProperty(value="药房识别")
	    private Integer yfsb;
		
		@ApiModelProperty(value="医技序号")
	    private Integer yjxh;
		
		@ApiModelProperty(value="医技主项")
	    private Integer yjzx;
		
		@ApiModelProperty(value="药品产地")
	    private Integer ypcd;	
		
		@ApiModelProperty(value="药品单价")
	    private BigDecimal ypdj;
		
		@ApiModelProperty(value="药品类型")
	    private Integer yplx;
		
		@ApiModelProperty(value="药品序号")
	    private Integer ypxh;
		
		@ApiModelProperty(value="煎法")
		private Integer ypzs;
		
		@ApiModelProperty(value="医生标志")
	    private Integer ysbz;
		
		@ApiModelProperty(value="医技序号")
	    private String ysgh;
		
		@ApiModelProperty(value="医生提交")
	    private Integer ystj;
		
		@ApiModelProperty(value="医嘱名称")
	    private String yzmc;
		
		@ApiModelProperty(value="医嘱判别")
	    private Integer yzpb;
		
		@ApiModelProperty(value="医嘱排序")
	    private Integer yzpx;
		
		@ApiModelProperty(value="医嘱作废")
	    private Integer yzzf;
		
		@ApiModelProperty(value="医嘱组号")
	    private Integer yzzh;
		
		@ApiModelProperty(value="自备药")
	    private Integer zby;
		
		@ApiModelProperty(value="作废标志")
	    private Integer zfbz;
		
		@ApiModelProperty(value="自负判别")
	    private Integer zfpb;
		
		@ApiModelProperty(value="自备药标识")
	    private Integer zfyp;
		
		@ApiModelProperty(value="组套标志")
	    private Integer ztbz;
		
		@ApiModelProperty(value="组套医嘱序号")
	    private Integer ztyzjlxh;

		public String getCzgh() {
			return czgh;
		}

		public void setCzgh(String czgh) {
			this.czgh = czgh;
		}

		public String getDs() {
			return ds;
		}

		public void setDs(String ds) {
			this.ds = ds;
		}

		public Integer getFhbz() {
			return fhbz;
		}

		public void setFhbz(Integer fhbz) {
			this.fhbz = fhbz;
		}

		public String getFhgh() {
			return fhgh;
		}

		public void setFhgh(String fhgh) {
			this.fhgh = fhgh;
		}

		public Timestamp getFhsj() {
			return fhsj;
		}

		public void setFhsj(Timestamp fhsj) {
			this.fhsj = fhsj;
		}

		public Integer getFyfs() {
			return fyfs;
		}

		public void setFyfs(Integer fyfs) {
			this.fyfs = fyfs;
		}

		public Integer getFysx() {
			return fysx;
		}

		public void setFysx(Integer fysx) {
			this.fysx = fysx;
		}

		public Integer getJfbz() {
			return jfbz;
		}

		public void setJfbz(Integer jfbz) {
			this.jfbz = jfbz;
		}

		public Integer getJgid() {
			return jgid;
		}

		public void setJgid(Integer jgid) {
			this.jgid = jgid;
		}

		public String getJldw() {
			return jldw;
		}

		public void setJldw(String jldw) {
			this.jldw = jldw;
		}

		public Integer getJlxh() {
			return jlxh;
		}

		public void setJlxh(Integer jlxh) {
			this.jlxh = jlxh;
		}

		public Integer getJz() {
			return jz;
		}

		public void setJz(Integer jz) {
			this.jz = jz;
		}

		public Integer getKjywsp() {
			return kjywsp;
		}

		public void setKjywsp(Integer kjywsp) {
			this.kjywsp = kjywsp;
		}

		public Integer getLsbz() {
			return lsbz;
		}

		public void setLsbz(Integer lsbz) {
			this.lsbz = lsbz;
		}

		public Integer getLsyz() {
			return lsyz;
		}

		public void setLsyz(Integer lsyz) {
			this.lsyz = lsyz;
		}

		public Integer getMrcs() {
			return mrcs;
		}

		public void setMrcs(Integer mrcs) {
			this.mrcs = mrcs;
		}

		public Integer getMzcs() {
			return mzcs;
		}

		public void setMzcs(Integer mzcs) {
			this.mzcs = mzcs;
		}

		public String getOpStatus() {
			return opStatus;
		}

		public void setOpStatus(String opStatus) {
			this.opStatus = opStatus;
		}

		public Integer getPspb() {
			return pspb;
		}

		public void setPspb(Integer pspb) {
			this.pspb = pspb;
		}

		public Integer getSfjg() {
			return sfjg;
		}

		public void setSfjg(Integer sfjg) {
			this.sfjg = sfjg;
		}

		public Integer getSrcs() {
			return srcs;
		}

		public void setSrcs(Integer srcs) {
			this.srcs = srcs;
		}

		public Integer getSybz() {
			return sybz;
		}

		public void setSybz(Integer sybz) {
			this.sybz = sybz;
		}

		public Integer getTpn() {
			return tpn;
		}

		public void setTpn(Integer tpn) {
			this.tpn = tpn;
		}

		public Integer getTzfhbz() {
			return tzfhbz;
		}

		public void setTzfhbz(Integer tzfhbz) {
			this.tzfhbz = tzfhbz;
		}

		public Timestamp getTzsj() {
			return tzsj;
		}

		public void setTzsj(Timestamp tzsj) {
			this.tzsj = tzsj;
		}

		public String getTzys() {
			return tzys;
		}

		public void setTzys(String tzys) {
			this.tzys = tzys;
		}

		public Integer getXmlx() {
			return xmlx;
		}

		public void setXmlx(Integer xmlx) {
			this.xmlx = xmlx;
		}

		public BigDecimal getYcjl() {
			return ycjl;
		}

		public void setYcjl(BigDecimal ycjl) {
			this.ycjl = ycjl;
		}

		public BigDecimal getYcsl() {
			return ycsl;
		}

		public void setYcsl(BigDecimal ycsl) {
			this.ycsl = ycsl;
		}

		public Integer getYepb() {
			return yepb;
		}

		public void setYepb(Integer yepb) {
			this.yepb = yepb;
		}

		public Integer getYfbz() {
			return yfbz;
		}

		public void setYfbz(Integer yfbz) {
			this.yfbz = yfbz;
		}

		public String getYfdw() {
			return yfdw;
		}

		public void setYfdw(String yfdw) {
			this.yfdw = yfdw;
		}

		public String getYfgg() {
			return yfgg;
		}

		public void setYfgg(String yfgg) {
			this.yfgg = yfgg;
		}

		public Integer getYfsb() {
			return yfsb;
		}

		public void setYfsb(Integer yfsb) {
			this.yfsb = yfsb;
		}

		public Integer getYjxh() {
			return yjxh;
		}

		public void setYjxh(Integer yjxh) {
			this.yjxh = yjxh;
		}

		public Integer getYjzx() {
			return yjzx;
		}

		public void setYjzx(Integer yjzx) {
			this.yjzx = yjzx;
		}

		public Integer getYpcd() {
			return ypcd;
		}

		public void setYpcd(Integer ypcd) {
			this.ypcd = ypcd;
		}

		public BigDecimal getYpdj() {
			return ypdj;
		}

		public void setYpdj(BigDecimal ypdj) {
			this.ypdj = ypdj;
		}

		public Integer getYplx() {
			return yplx;
		}

		public void setYplx(Integer yplx) {
			this.yplx = yplx;
		}

		public Integer getYpxh() {
			return ypxh;
		}

		public void setYpxh(Integer ypxh) {
			this.ypxh = ypxh;
		}

		public Integer getYpzs() {
			return ypzs;
		}

		public void setYpzs(Integer ypzs) {
			this.ypzs = ypzs;
		}

		public Integer getYsbz() {
			return ysbz;
		}

		public void setYsbz(Integer ysbz) {
			this.ysbz = ysbz;
		}

		public String getYsgh() {
			return ysgh;
		}

		public void setYsgh(String ysgh) {
			this.ysgh = ysgh;
		}

		public Integer getYstj() {
			return ystj;
		}

		public void setYstj(Integer ystj) {
			this.ystj = ystj;
		}

		public String getYzmc() {
			return yzmc;
		}

		public void setYzmc(String yzmc) {
			this.yzmc = yzmc;
		}

		public Integer getYzpb() {
			return yzpb;
		}

		public void setYzpb(Integer yzpb) {
			this.yzpb = yzpb;
		}

		public Integer getYzpx() {
			return yzpx;
		}

		public void setYzpx(Integer yzpx) {
			this.yzpx = yzpx;
		}

		public Integer getYzzf() {
			return yzzf;
		}

		public void setYzzf(Integer yzzf) {
			this.yzzf = yzzf;
		}

		public Integer getYzzh() {
			return yzzh;
		}

		public void setYzzh(Integer yzzh) {
			this.yzzh = yzzh;
		}

		public Integer getZby() {
			return zby;
		}

		public void setZby(Integer zby) {
			this.zby = zby;
		}

		public Integer getZfbz() {
			return zfbz;
		}

		public void setZfbz(Integer zfbz) {
			this.zfbz = zfbz;
		}

		public Integer getZfpb() {
			return zfpb;
		}

		public void setZfpb(Integer zfpb) {
			this.zfpb = zfpb;
		}

		public Integer getZfyp() {
			return zfyp;
		}

		public void setZfyp(Integer zfyp) {
			this.zfyp = zfyp;
		}

		public Integer getZtbz() {
			return ztbz;
		}

		public void setZtbz(Integer ztbz) {
			this.ztbz = ztbz;
		}

		public Integer getZtyzjlxh() {
			return ztyzjlxh;
		}

		public void setZtyzjlxh(Integer ztyzjlxh) {
			this.ztyzjlxh = ztyzjlxh;
		}

	}
	
}
