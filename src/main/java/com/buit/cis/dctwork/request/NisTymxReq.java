   
package com.buit.cis.dctwork.request;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

import com.buit.commons.PageQuery;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


/**
 * 类名称：NisTymx<br> 
 * 类描述：病区_退药明细<br>
 * @author GONGFANGZHOU
 */
@ApiModel(value="病区_退药明细_Req")
public class NisTymxReq {
  
	@ApiModelProperty(value="机构代码")
	private Integer jgid; 
	
	@ApiModelProperty(value="药房识别")
	private Integer yfsb;
	
	@ApiModelProperty(value="发药日期")
	private Timestamp fyrq;
	
	@ApiModelProperty(value="药品名称")
	private String ypmc;
	
	@ApiModelProperty(value="药品序号")
	private Integer ypxh;
	
	@ApiModelProperty(value="药品产地")
	private Integer ypcd;
	
	@ApiModelProperty(value="产地名称")
	private String cdmc;
	
	@ApiModelProperty(value="药品单价")
	private BigDecimal ypdj;
	
	@ApiModelProperty(value="药品数量")
    private BigDecimal ypsl;
	
	@ApiModelProperty(value="药房名称")
    private String yfmc;
	
	@ApiModelProperty(value="药品规格")
    private String ypgg;
	
	@ApiModelProperty(value="药房单位")
    private String yfdw;
	
	@ApiModelProperty(value="药房包装")
    private Integer yfbz;
	
	@ApiModelProperty(value="领用病区")
    private Integer lybq;
	
	@ApiModelProperty(value="婴儿判别")
    private Integer yepb;
	
	@ApiModelProperty(value="自负比例")
    private BigDecimal zfbl;
	
	@ApiModelProperty(value="自费判别")
    private Integer zfpb;
	
	@ApiModelProperty(value="医嘱序号")
    private Integer yzxh;
	
	@ApiModelProperty(value="住院号")
    private Integer zyh;
	
	@ApiModelProperty(value="退药病区")
    private Integer tybq;
	
	@ApiModelProperty(value="提交标志")
    private Integer tjbz;
	
	@ApiModelProperty(value="医嘱ID")
    private Integer yzid;
	
	@ApiModelProperty(value="药品价格")
    private BigDecimal ypjg;

	@ApiModelProperty(value="可退数量")
	private BigDecimal ktsl;
	
	@ApiModelProperty(value="一次数量")
	private BigDecimal ycsl;
	
	@ApiModelProperty(value="退药原因")
	private String tyyy;
	
	@ApiModelProperty(value="是否手动选择")
	private boolean sdflag;
	
	@ApiModelProperty(value="选择明细集合")
	private List<NisTymxDetail> detailList;

	public Integer getJgid() {
		return jgid;
	}

	public void setJgid(Integer jgid) {
		this.jgid = jgid;
	}

	public Integer getYfsb() {
		return yfsb;
	}

	public void setYfsb(Integer yfsb) {
		this.yfsb = yfsb;
	}

	public Timestamp getFyrq() {
		return fyrq;
	}

	public void setFyrq(Timestamp fyrq) {
		this.fyrq = fyrq;
	}

	public String getYpmc() {
		return ypmc;
	}

	public void setYpmc(String ypmc) {
		this.ypmc = ypmc;
	}

	public Integer getYpxh() {
		return ypxh;
	}

	public void setYpxh(Integer ypxh) {
		this.ypxh = ypxh;
	}

	public Integer getYpcd() {
		return ypcd;
	}

	public void setYpcd(Integer ypcd) {
		this.ypcd = ypcd;
	}

	public String getCdmc() {
		return cdmc;
	}

	public void setCdmc(String cdmc) {
		this.cdmc = cdmc;
	}

	public BigDecimal getYpdj() {
		return ypdj;
	}

	public void setYpdj(BigDecimal ypdj) {
		this.ypdj = ypdj;
	}

	public BigDecimal getYpsl() {
		return ypsl;
	}

	public void setYpsl(BigDecimal ypsl) {
		this.ypsl = ypsl;
	}

	public String getYfmc() {
		return yfmc;
	}

	public void setYfmc(String yfmc) {
		this.yfmc = yfmc;
	}

	public String getYpgg() {
		return ypgg;
	}

	public void setYpgg(String ypgg) {
		this.ypgg = ypgg;
	}

	public String getYfdw() {
		return yfdw;
	}

	public void setYfdw(String yfdw) {
		this.yfdw = yfdw;
	}

	public Integer getYfbz() {
		return yfbz;
	}

	public void setYfbz(Integer yfbz) {
		this.yfbz = yfbz;
	}

	public Integer getLybq() {
		return lybq;
	}

	public void setLybq(Integer lybq) {
		this.lybq = lybq;
	}

	public Integer getYepb() {
		return yepb;
	}

	public void setYepb(Integer yepb) {
		this.yepb = yepb;
	}

	public BigDecimal getZfbl() {
		return zfbl;
	}

	public void setZfbl(BigDecimal zfbl) {
		this.zfbl = zfbl;
	}

	public Integer getZfpb() {
		return zfpb;
	}

	public void setZfpb(Integer zfpb) {
		this.zfpb = zfpb;
	}

	public Integer getYzxh() {
		return yzxh;
	}

	public void setYzxh(Integer yzxh) {
		this.yzxh = yzxh;
	}

	public Integer getZyh() {
		return zyh;
	}

	public void setZyh(Integer zyh) {
		this.zyh = zyh;
	}

	public Integer getTybq() {
		return tybq;
	}

	public void setTybq(Integer tybq) {
		this.tybq = tybq;
	}

	public Integer getTjbz() {
		return tjbz;
	}

	public void setTjbz(Integer tjbz) {
		this.tjbz = tjbz;
	}

	public Integer getYzid() {
		return yzid;
	}

	public void setYzid(Integer yzid) {
		this.yzid = yzid;
	}

	public BigDecimal getYpjg() {
		return ypjg;
	}

	public void setYpjg(BigDecimal ypjg) {
		this.ypjg = ypjg;
	}

	public BigDecimal getKtsl() {
		return ktsl;
	}

	public void setKtsl(BigDecimal ktsl) {
		this.ktsl = ktsl;
	}
	
	public BigDecimal getYcsl() {
		return ycsl;
	}

	public void setYcsl(BigDecimal ycsl) {
		this.ycsl = ycsl;
	}

	public String getTyyy() {
		return tyyy;
	}

	public void setTyyy(String tyyy) {
		this.tyyy = tyyy;
	}

	public boolean isSdflag() {
		return sdflag;
	}

	public void setSdflag(boolean sdflag) {
		this.sdflag = sdflag;
	}
	
	public List<NisTymxDetail> getDetailList() {
		return detailList;
	}

	public void setDetailList(List<NisTymxDetail> detailList) {
		this.detailList = detailList;
	}

	public static class NisTymxDetail{
		@ApiModelProperty(value="记录序号")
		private Integer jlxh;
		
		@ApiModelProperty(value="执行时间")
		private Timestamp qrrq;

		public Integer getJlxh() {
			return jlxh;
		}

		public void setJlxh(Integer jlxh) {
			this.jlxh = jlxh;
		}

		public Timestamp getQrrq() {
			return qrrq;
		}

		public void setQrrq(Timestamp qrrq) {
			this.qrrq = qrrq;
		}
		
		
	} 
    
}
