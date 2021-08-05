package com.buit.cis.dctwork.response;

import java.math.BigDecimal;
import java.util.List;

import com.buit.drug.response.CisHzyzYpcdDetailResp;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 类名称：CisHzyzCommonResp<br> 
 * 类描述：住院_病区发药明细<br>
 * @author GONGFANGZHOU
 */
@ApiModel(value="住院_病区医嘱药品_COMMONResp")
public class CisHzyzCommonResp {
	
    @ApiModelProperty(value="记录编号")
    private Integer jlbh;
	
	@ApiModelProperty(value="组套编号")
    private Integer ztbh;
	
	@ApiModelProperty(value="药品组号")
    private Integer ypzh;
	
	@ApiModelProperty(value="医嘱名称")
	private String yzmc;
	
	@ApiModelProperty(value="药品名称")
	private String ypmc;
	
	@ApiModelProperty(value="药品规格")
	private String ypgg;
	
	@ApiModelProperty(value="项目数量")
	private BigDecimal xmsl;
	
	@ApiModelProperty(value="一次剂量")
	private BigDecimal ycjl;
	
	@ApiModelProperty(value="剂量单位")
	private String jldw;
	
	@ApiModelProperty(value="使用频次")
	private String sypc;
	
	@ApiModelProperty(value="每日次数")
	private Integer mrcs;
	
	@ApiModelProperty(value="用药天数")
    private Integer yyts;
	
	@ApiModelProperty(value="给药途径")
    private Integer gytj;
	
	@ApiModelProperty(value="药品数量")
    private BigDecimal ypsl;
	
	@ApiModelProperty(value="医保分类")
    private BigDecimal ybfl;
	
	@ApiModelProperty(value="脚注")
	private Integer jz;
	
	@ApiModelProperty(value="药品序号")
	private Integer ypxh;
	
	@ApiModelProperty(value="药房规格")
	private String yfgg;
	
	@ApiModelProperty(value="药房单位")
	private String yfdw;
	
	@ApiModelProperty(value="药房包装")
	private Integer yfbz;
	
	@ApiModelProperty(value="零售价格")
    private BigDecimal lsjg;
	
	@ApiModelProperty(value="皮试判别")
	private Integer pspb;
	
	@ApiModelProperty(value="药品剂量")
	private BigDecimal ypjl;
	
	@ApiModelProperty(value="给药方法")
	private Integer gyff;
	
	@ApiModelProperty(value="药品产地")
	private Integer ypcd;
	
	@ApiModelProperty(value="药品产地")
	private String cdmc;
	
	@ApiModelProperty(value="药品类型")
	private Integer yplx;
	
	@ApiModelProperty(value="药品类型")
	private Integer type;
	
	@ApiModelProperty(value="特殊药品")
	private Integer tsyp;
	
	@ApiModelProperty(value="基药类型 1.非基本药物,2国家基本药物,3.省基本药物,4.区自选")
	private Integer jylx;
	
	@ApiModelProperty(value="发药方式")
	private Integer fyfs;
	
	@ApiModelProperty(value="最小包装")
	private Integer zxbz;
	
	@ApiModelProperty(value="抗生素标志")
	private Integer ksbz;
	
	@ApiModelProperty(value="一次用量")
	private BigDecimal ycyl;
	
	@ApiModelProperty(value="抗生素等级 固定为一级抗生素 二级抗生素 三级抗生素")
	private Integer kssdj;

	@ApiModelProperty(value="抗菌药物越权使用方式 1：提醒 2禁止")
	private Integer yqsyfs;

	@ApiModelProperty(value="抗菌药物是否需要审批 1：需要 2不需要")
	private Integer sfsp;
	
	@ApiModelProperty(value="药房识别")
	private Integer yfsb;
	
	@ApiModelProperty(value="费用归并")
	private Integer fygb;
	
	@ApiModelProperty(value="药库作废")
	private Integer ykzf;
	
	@ApiModelProperty(value="警告信息")
	private String warnMsg;
	
	@ApiModelProperty(value="错误信息")
	private String errorMsg;
	
	@ApiModelProperty(value="自负比例")
    private BigDecimal zfbl;
    
    @ApiModelProperty(value="是否过敏")
    private boolean isAllergy;
    
    @ApiModelProperty(value="不良反映")
    private String blfy;
    
    @ApiModelProperty(value="作废判别")
    private Integer zfpb;
    
    @ApiModelProperty(value="过敏药物类别")
    private Integer gmywlb;
    
    @ApiModelProperty(value="产地集合")
    private List<CisHzyzYpcdDetailResp> ypcdList;

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

	public Integer getYpzh() {
		return ypzh;
	}

	public void setYpzh(Integer ypzh) {
		this.ypzh = ypzh;
	}

	public String getYzmc() {
		return yzmc;
	}

	public void setYzmc(String yzmc) {
		this.yzmc = yzmc;
	}

	public String getYpmc() {
		return ypmc;
	}

	public void setYpmc(String ypmc) {
		this.ypmc = ypmc;
	}

	public String getYpgg() {
		return ypgg;
	}

	public void setYpgg(String ypgg) {
		this.ypgg = ypgg;
	}

	public BigDecimal getXmsl() {
		return xmsl;
	}

	public void setXmsl(BigDecimal xmsl) {
		this.xmsl = xmsl;
	}

	public BigDecimal getYcjl() {
		return ycjl;
	}

	public void setYcjl(BigDecimal ycjl) {
		this.ycjl = ycjl;
	}

	public String getJldw() {
		return jldw;
	}

	public void setJldw(String jldw) {
		this.jldw = jldw;
	}

	public String getSypc() {
		return sypc;
	}

	public void setSypc(String sypc) {
		this.sypc = sypc;
	}

	public Integer getMrcs() {
		return mrcs;
	}

	public void setMrcs(Integer mrcs) {
		this.mrcs = mrcs;
	}

	public Integer getYyts() {
		return yyts;
	}

	public void setYyts(Integer yyts) {
		this.yyts = yyts;
	}

	public Integer getGytj() {
		return gytj;
	}

	public void setGytj(Integer gytj) {
		this.gytj = gytj;
	}

	public BigDecimal getYpsl() {
		return ypsl;
	}

	public void setYpsl(BigDecimal ypsl) {
		this.ypsl = ypsl;
	}

	public BigDecimal getYbfl() {
		return ybfl;
	}

	public void setYbfl(BigDecimal ybfl) {
		this.ybfl = ybfl;
	}

	public Integer getJz() {
		return jz;
	}

	public void setJz(Integer jz) {
		this.jz = jz;
	}

	public Integer getYpxh() {
		return ypxh;
	}

	public void setYpxh(Integer ypxh) {
		this.ypxh = ypxh;
	}

	public String getYfgg() {
		return yfgg;
	}

	public void setYfgg(String yfgg) {
		this.yfgg = yfgg;
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

	public BigDecimal getLsjg() {
		return lsjg;
	}

	public void setLsjg(BigDecimal lsjg) {
		this.lsjg = lsjg;
	}

	public Integer getPspb() {
		return pspb;
	}

	public void setPspb(Integer pspb) {
		this.pspb = pspb;
	}

	public BigDecimal getYpjl() {
		return ypjl;
	}

	public void setYpjl(BigDecimal ypjl) {
		this.ypjl = ypjl;
	}

	public Integer getGyff() {
		return gyff;
	}

	public void setGyff(Integer gyff) {
		this.gyff = gyff;
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

	public Integer getYplx() {
		return yplx;
	}

	public void setYplx(Integer yplx) {
		this.yplx = yplx;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getTsyp() {
		return tsyp;
	}

	public void setTsyp(Integer tsyp) {
		this.tsyp = tsyp;
	}

	public Integer getJylx() {
		return jylx;
	}

	public void setJylx(Integer jylx) {
		this.jylx = jylx;
	}

	public Integer getFyfs() {
		return fyfs;
	}

	public void setFyfs(Integer fyfs) {
		this.fyfs = fyfs;
	}

	public Integer getZxbz() {
		return zxbz;
	}

	public void setZxbz(Integer zxbz) {
		this.zxbz = zxbz;
	}

	public Integer getKsbz() {
		return ksbz;
	}

	public void setKsbz(Integer ksbz) {
		this.ksbz = ksbz;
	}

	public BigDecimal getYcyl() {
		return ycyl;
	}

	public void setYcyl(BigDecimal ycyl) {
		this.ycyl = ycyl;
	}

	public Integer getKssdj() {
		return kssdj;
	}

	public void setKssdj(Integer kssdj) {
		this.kssdj = kssdj;
	}

	public Integer getYqsyfs() {
		return yqsyfs;
	}

	public void setYqsyfs(Integer yqsyfs) {
		this.yqsyfs = yqsyfs;
	}

	public Integer getSfsp() {
		return sfsp;
	}

	public void setSfsp(Integer sfsp) {
		this.sfsp = sfsp;
	}

	public Integer getYfsb() {
		return yfsb;
	}

	public void setYfsb(Integer yfsb) {
		this.yfsb = yfsb;
	}

	public Integer getFygb() {
		return fygb;
	}

	public void setFygb(Integer fygb) {
		this.fygb = fygb;
	}

	public Integer getYkzf() {
		return ykzf;
	}

	public void setYkzf(Integer ykzf) {
		this.ykzf = ykzf;
	}

	public String getWarnMsg() {
		return warnMsg;
	}

	public void setWarnMsg(String warnMsg) {
		this.warnMsg = warnMsg;
	}

	public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}

	public BigDecimal getZfbl() {
		return zfbl;
	}

	public void setZfbl(BigDecimal zfbl) {
		this.zfbl = zfbl;
	}

	public boolean isAllergy() {
		return isAllergy;
	}

	public void setAllergy(boolean isAllergy) {
		this.isAllergy = isAllergy;
	}

	public String getBlfy() {
		return blfy;
	}

	public void setBlfy(String blfy) {
		this.blfy = blfy;
	}

	public Integer getZfpb() {
		return zfpb;
	}

	public void setZfpb(Integer zfpb) {
		this.zfpb = zfpb;
	}
		
	public Integer getGmywlb() {
		return gmywlb;
	}

	public void setGmywlb(Integer gmywlb) {
		this.gmywlb = gmywlb;
	}

	public List<CisHzyzYpcdDetailResp> getYpcdList() {
		return ypcdList;
	}

	public void setYpcdList(List<CisHzyzYpcdDetailResp> ypcdList) {
		this.ypcdList = ypcdList;
	}
}
