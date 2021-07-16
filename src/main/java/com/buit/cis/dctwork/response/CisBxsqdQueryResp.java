   
package com.buit.cis.dctwork.response;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

import com.buit.cis.dctwork.model.CisBxxypz;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


/**
 * 类名称：CisBxsqdQueryResp<br> 
 * 类描述：备血申请单<br>
 * @author GONGFANGZHOU
 */
@ApiModel(value="备血申请单_queryResp")
public class CisBxsqdQueryResp {
	@ApiModelProperty(value="医疗机构代码")
    private String yljgd;
    @ApiModelProperty(value="申请单号")
    private String sqdh;
    @ApiModelProperty(value="病人类型(1 门诊/2 住院)")
    private String brlx;
    @ApiModelProperty(value="门诊号")
    private String mzh;
    @ApiModelProperty(value="住院号")
    private String zyh;
    @ApiModelProperty(value="病区代码")
    private String bqdm;
    @ApiModelProperty(value="科室代码")
    private String ksdm;
    @ApiModelProperty(value="临床信息")
    private String lcxx;
    @ApiModelProperty(value="输血目的")
    private String sxyy;
    @ApiModelProperty(value="输血性质(备血/常规/紧急)")
    private String sxxz;
    @ApiModelProperty(value="申请ABO血型(A/B/AB/O)")
    private String sqaboxx;
    @ApiModelProperty(value="申请RH血型(阴性/阳性/不详/未查)")
    private String sqrhxx;
    @ApiModelProperty(value="继往输血史(无/有/未说明)")
    private String sxsbs;
    @ApiModelProperty(value="孕产史")
    private String ycs;
    @ApiModelProperty(value="移植史")
    private String yzs;
    @ApiModelProperty(value="其他")
    private String qt;
    @ApiModelProperty(value="预定输血总量")
    private BigDecimal ydsxzl;
    @ApiModelProperty(value="受血者输血前检查(1:是,0:否)")
    private String sxqjc;
    @ApiModelProperty(value="受血者属地(1:本市,2:外埠)")
    private String sxzsd;
    @ApiModelProperty(value="HCT")
    private BigDecimal hct;
    @ApiModelProperty(value="Alt")
    private BigDecimal alt;
    @ApiModelProperty(value="HBsAg(阴性/阳性)")
    private String hbsag;
    @ApiModelProperty(value="Anti-HCV(阴性/阳性)")
    private String hcv;
    @ApiModelProperty(value="Anti_HIV(阴性/阳性)")
    private String hiv;
    @ApiModelProperty(value="梅毒抗体(阴性/阳性)")
    private String mdkt;
    @ApiModelProperty(value="血红蛋白")
    private BigDecimal xhdb;
    @ApiModelProperty(value="血小板")
    private BigDecimal xxb;
    @ApiModelProperty(value="白细胞")
    private BigDecimal bxb;
    @ApiModelProperty(value="预定输血日期")
    private Timestamp ydsxrq;
    @ApiModelProperty(value="输血同意书")
    private String sxtys;
    @ApiModelProperty(value="用血证")
    private String yxz;
    @ApiModelProperty(value="主治医师")
    private String zzys;
    @ApiModelProperty(value="上级医师")
    private String sjys;
    @ApiModelProperty(value="科主任")
    private String kzr;
    @ApiModelProperty(value="院医务科")
    private String yywk;
    @ApiModelProperty(value="输血日期时间")
    private Timestamp sxrqsj;
    @ApiModelProperty(value="注意事项")
    private String zysx;
    @ApiModelProperty(value="申请时间")
    private Timestamp sqsj;
    @ApiModelProperty(value="申请人代码")
    private String sqrdm;
    @ApiModelProperty(value="状态(0 申请/1 已发血/2 作废)")
    private String zt;
    @ApiModelProperty(value="发血时间")
    private Timestamp fxsj;
    @ApiModelProperty(value="发血人代码")
    private String fxrdm;
    @ApiModelProperty(value="作废时间")
    private Timestamp zfsj;
    @ApiModelProperty(value="作废人代码")
    private String zfrdm;
    @ApiModelProperty(value="病人床号")
    private String brch;
    
    @ApiModelProperty(value="血液品种集合")
    private List<CisBxxypz> cisBxxypzList;

	public String getYljgd() {
		return yljgd;
	}

	public void setYljgd(String yljgd) {
		this.yljgd = yljgd;
	}

	public String getSqdh() {
		return sqdh;
	}

	public void setSqdh(String sqdh) {
		this.sqdh = sqdh;
	}

	public String getBrlx() {
		return brlx;
	}

	public void setBrlx(String brlx) {
		this.brlx = brlx;
	}

	public String getMzh() {
		return mzh;
	}

	public void setMzh(String mzh) {
		this.mzh = mzh;
	}

	public String getZyh() {
		return zyh;
	}

	public void setZyh(String zyh) {
		this.zyh = zyh;
	}

	public String getBqdm() {
		return bqdm;
	}

	public void setBqdm(String bqdm) {
		this.bqdm = bqdm;
	}

	public String getKsdm() {
		return ksdm;
	}

	public void setKsdm(String ksdm) {
		this.ksdm = ksdm;
	}

	public String getLcxx() {
		return lcxx;
	}

	public void setLcxx(String lcxx) {
		this.lcxx = lcxx;
	}

	public String getSxyy() {
		return sxyy;
	}

	public void setSxyy(String sxyy) {
		this.sxyy = sxyy;
	}

	public String getSxxz() {
		return sxxz;
	}

	public void setSxxz(String sxxz) {
		this.sxxz = sxxz;
	}

	public String getSqaboxx() {
		return sqaboxx;
	}

	public void setSqaboxx(String sqaboxx) {
		this.sqaboxx = sqaboxx;
	}

	public String getSqrhxx() {
		return sqrhxx;
	}

	public void setSqrhxx(String sqrhxx) {
		this.sqrhxx = sqrhxx;
	}

	public String getSxsbs() {
		return sxsbs;
	}

	public void setSxsbs(String sxsbs) {
		this.sxsbs = sxsbs;
	}

	public String getYcs() {
		return ycs;
	}

	public void setYcs(String ycs) {
		this.ycs = ycs;
	}

	public String getYzs() {
		return yzs;
	}

	public void setYzs(String yzs) {
		this.yzs = yzs;
	}

	public String getQt() {
		return qt;
	}

	public void setQt(String qt) {
		this.qt = qt;
	}

	public BigDecimal getYdsxzl() {
		return ydsxzl;
	}

	public void setYdsxzl(BigDecimal ydsxzl) {
		this.ydsxzl = ydsxzl;
	}

	public String getSxqjc() {
		return sxqjc;
	}

	public void setSxqjc(String sxqjc) {
		this.sxqjc = sxqjc;
	}

	public String getSxzsd() {
		return sxzsd;
	}

	public void setSxzsd(String sxzsd) {
		this.sxzsd = sxzsd;
	}

	public BigDecimal getHct() {
		return hct;
	}

	public void setHct(BigDecimal hct) {
		this.hct = hct;
	}

	public BigDecimal getAlt() {
		return alt;
	}

	public void setAlt(BigDecimal alt) {
		this.alt = alt;
	}

	public String getHbsag() {
		return hbsag;
	}

	public void setHbsag(String hbsag) {
		this.hbsag = hbsag;
	}

	public String getHcv() {
		return hcv;
	}

	public void setHcv(String hcv) {
		this.hcv = hcv;
	}

	public String getHiv() {
		return hiv;
	}

	public void setHiv(String hiv) {
		this.hiv = hiv;
	}

	public String getMdkt() {
		return mdkt;
	}

	public void setMdkt(String mdkt) {
		this.mdkt = mdkt;
	}

	public BigDecimal getXhdb() {
		return xhdb;
	}

	public void setXhdb(BigDecimal xhdb) {
		this.xhdb = xhdb;
	}

	public BigDecimal getXxb() {
		return xxb;
	}

	public void setXxb(BigDecimal xxb) {
		this.xxb = xxb;
	}

	public BigDecimal getBxb() {
		return bxb;
	}

	public void setBxb(BigDecimal bxb) {
		this.bxb = bxb;
	}

	public Timestamp getYdsxrq() {
		return ydsxrq;
	}

	public void setYdsxrq(Timestamp ydsxrq) {
		this.ydsxrq = ydsxrq;
	}

	public String getSxtys() {
		return sxtys;
	}

	public void setSxtys(String sxtys) {
		this.sxtys = sxtys;
	}

	public String getYxz() {
		return yxz;
	}

	public void setYxz(String yxz) {
		this.yxz = yxz;
	}

	public String getZzys() {
		return zzys;
	}

	public void setZzys(String zzys) {
		this.zzys = zzys;
	}

	public String getSjys() {
		return sjys;
	}

	public void setSjys(String sjys) {
		this.sjys = sjys;
	}

	public String getKzr() {
		return kzr;
	}

	public void setKzr(String kzr) {
		this.kzr = kzr;
	}

	public String getYywk() {
		return yywk;
	}

	public void setYywk(String yywk) {
		this.yywk = yywk;
	}

	public Timestamp getSxrqsj() {
		return sxrqsj;
	}

	public void setSxrqsj(Timestamp sxrqsj) {
		this.sxrqsj = sxrqsj;
	}

	public String getZysx() {
		return zysx;
	}

	public void setZysx(String zysx) {
		this.zysx = zysx;
	}

	public Timestamp getSqsj() {
		return sqsj;
	}

	public void setSqsj(Timestamp sqsj) {
		this.sqsj = sqsj;
	}

	public String getSqrdm() {
		return sqrdm;
	}

	public void setSqrdm(String sqrdm) {
		this.sqrdm = sqrdm;
	}

	public String getZt() {
		return zt;
	}

	public void setZt(String zt) {
		this.zt = zt;
	}

	public Timestamp getFxsj() {
		return fxsj;
	}

	public void setFxsj(Timestamp fxsj) {
		this.fxsj = fxsj;
	}

	public String getFxrdm() {
		return fxrdm;
	}

	public void setFxrdm(String fxrdm) {
		this.fxrdm = fxrdm;
	}

	public Timestamp getZfsj() {
		return zfsj;
	}

	public void setZfsj(Timestamp zfsj) {
		this.zfsj = zfsj;
	}

	public String getZfrdm() {
		return zfrdm;
	}

	public void setZfrdm(String zfrdm) {
		this.zfrdm = zfrdm;
	}

	public String getBrch() {
		return brch;
	}

	public void setBrch(String brch) {
		this.brch = brch;
	}

	public List<CisBxxypz> getCisBxxypzList() {
		return cisBxxypzList;
	}

	public void setCisBxxypzList(List<CisBxxypz> cisBxxypzList) {
		this.cisBxxypzList = cisBxxypzList;
	}
  
    
}
