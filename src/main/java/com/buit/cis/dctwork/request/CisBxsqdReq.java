   
package com.buit.cis.dctwork.request;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


/**
 * 类名称：CisBxsqd<br> 
 * 类描述：备血申请单<br>
 * @author GONGFANGZHOU
 */
@ApiModel(value="备血申请单_Req")
public class CisBxsqdReq {
    @ApiModelProperty(value="医疗机构代码")
    private String yljgd;
    @NotNull(message = "申请单号不能为空")
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
    List<CisBxxypzReq> cisBxxypzReqList;
    /**
     * 设置:医疗机构代码
     */
    public void setYljgd(String value) {
        this.yljgd = value;
    }
    /**
     * 获取:医疗机构代码
     */
    public String getYljgd() {
        return yljgd;
    }
    /**
     * 设置:申请单号
     */
    public void setSqdh(String value) {
        this.sqdh = value;
    }
    /**
     * 获取:申请单号
     */
    public String getSqdh() {
        return sqdh;
    }
    /**
     * 设置:病人类型(1 门诊/2 住院)
     */
    public void setBrlx(String value) {
        this.brlx = value;
    }
    /**
     * 获取:病人类型(1 门诊/2 住院)
     */
    public String getBrlx() {
        return brlx;
    }
    /**
     * 设置:门诊号
     */
    public void setMzh(String value) {
        this.mzh = value;
    }
    /**
     * 获取:门诊号
     */
    public String getMzh() {
        return mzh;
    }
    /**
     * 设置:住院号
     */
    public void setZyh(String value) {
        this.zyh = value;
    }
    /**
     * 获取:住院号
     */
    public String getZyh() {
        return zyh;
    }
    /**
     * 设置:病区代码
     */
    public void setBqdm(String value) {
        this.bqdm = value;
    }
    /**
     * 获取:病区代码
     */
    public String getBqdm() {
        return bqdm;
    }
    /**
     * 设置:科室代码
     */
    public void setKsdm(String value) {
        this.ksdm = value;
    }
    /**
     * 获取:科室代码
     */
    public String getKsdm() {
        return ksdm;
    }
    /**
     * 设置:临床信息
     */
    public void setLcxx(String value) {
        this.lcxx = value;
    }
    /**
     * 获取:临床信息
     */
    public String getLcxx() {
        return lcxx;
    }
    /**
     * 设置:输血目的
     */
    public void setSxyy(String value) {
        this.sxyy = value;
    }
    /**
     * 获取:输血目的
     */
    public String getSxyy() {
        return sxyy;
    }
    /**
     * 设置:输血性质(备血/常规/紧急)
     */
    public void setSxxz(String value) {
        this.sxxz = value;
    }
    /**
     * 获取:输血性质(备血/常规/紧急)
     */
    public String getSxxz() {
        return sxxz;
    }
    /**
     * 设置:申请ABO血型(A/B/AB/O)
     */
    public void setSqaboxx(String value) {
        this.sqaboxx = value;
    }
    /**
     * 获取:申请ABO血型(A/B/AB/O)
     */
    public String getSqaboxx() {
        return sqaboxx;
    }
    /**
     * 设置:申请RH血型(阴性/阳性/不详/未查)
     */
    public void setSqrhxx(String value) {
        this.sqrhxx = value;
    }
    /**
     * 获取:申请RH血型(阴性/阳性/不详/未查)
     */
    public String getSqrhxx() {
        return sqrhxx;
    }
    /**
     * 设置:继往输血史(无/有/未说明)
     */
    public void setSxsbs(String value) {
        this.sxsbs = value;
    }
    /**
     * 获取:继往输血史(无/有/未说明)
     */
    public String getSxsbs() {
        return sxsbs;
    }
    /**
     * 设置:孕产史
     */
    public void setYcs(String value) {
        this.ycs = value;
    }
    /**
     * 获取:孕产史
     */
    public String getYcs() {
        return ycs;
    }
    /**
     * 设置:移植史
     */
    public void setYzs(String value) {
        this.yzs = value;
    }
    /**
     * 获取:移植史
     */
    public String getYzs() {
        return yzs;
    }
    /**
     * 设置:其他
     */
    public void setQt(String value) {
        this.qt = value;
    }
    /**
     * 获取:其他
     */
    public String getQt() {
        return qt;
    }
    /**
     * 设置:预定输血总量
     */
    public void setYdsxzl(BigDecimal value) {
        this.ydsxzl = value;
    }
    /**
     * 获取:预定输血总量
     */
    public BigDecimal getYdsxzl() {
        return ydsxzl;
    }
    /**
     * 设置:受血者输血前检查(1:是,0:否)
     */
    public void setSxqjc(String value) {
        this.sxqjc = value;
    }
    /**
     * 获取:受血者输血前检查(1:是,0:否)
     */
    public String getSxqjc() {
        return sxqjc;
    }
    /**
     * 设置:受血者属地(1:本市,2:外埠)
     */
    public void setSxzsd(String value) {
        this.sxzsd = value;
    }
    /**
     * 获取:受血者属地(1:本市,2:外埠)
     */
    public String getSxzsd() {
        return sxzsd;
    }
    /**
     * 设置:HCT
     */
    public void setHct(BigDecimal value) {
        this.hct = value;
    }
    /**
     * 获取:HCT
     */
    public BigDecimal getHct() {
        return hct;
    }
    /**
     * 设置:Alt
     */
    public void setAlt(BigDecimal value) {
        this.alt = value;
    }
    /**
     * 获取:Alt
     */
    public BigDecimal getAlt() {
        return alt;
    }
    /**
     * 设置:HBsAg(阴性/阳性)
     */
    public void setHbsag(String value) {
        this.hbsag = value;
    }
    /**
     * 获取:HBsAg(阴性/阳性)
     */
    public String getHbsag() {
        return hbsag;
    }
    /**
     * 设置:Anti-HCV(阴性/阳性)
     */
    public void setHcv(String value) {
        this.hcv = value;
    }
    /**
     * 获取:Anti-HCV(阴性/阳性)
     */
    public String getHcv() {
        return hcv;
    }
    /**
     * 设置:Anti_HIV(阴性/阳性)
     */
    public void setHiv(String value) {
        this.hiv = value;
    }
    /**
     * 获取:Anti_HIV(阴性/阳性)
     */
    public String getHiv() {
        return hiv;
    }
    /**
     * 设置:梅毒抗体(阴性/阳性)
     */
    public void setMdkt(String value) {
        this.mdkt = value;
    }
    /**
     * 获取:梅毒抗体(阴性/阳性)
     */
    public String getMdkt() {
        return mdkt;
    }
    /**
     * 设置:血红蛋白
     */
    public void setXhdb(BigDecimal value) {
        this.xhdb = value;
    }
    /**
     * 获取:血红蛋白
     */
    public BigDecimal getXhdb() {
        return xhdb;
    }
    /**
     * 设置:血小板
     */
    public void setXxb(BigDecimal value) {
        this.xxb = value;
    }
    /**
     * 获取:血小板
     */
    public BigDecimal getXxb() {
        return xxb;
    }
    /**
     * 设置:白细胞
     */
    public void setBxb(BigDecimal value) {
        this.bxb = value;
    }
    /**
     * 获取:白细胞
     */
    public BigDecimal getBxb() {
        return bxb;
    }
    /**
     * 设置:预定输血日期
     */
    public void setYdsxrq(Timestamp value) {
        this.ydsxrq = value;
    }
    /**
     * 获取:预定输血日期
     */
    public Timestamp getYdsxrq() {
        return ydsxrq;
    }
    /**
     * 设置:输血同意书
     */
    public void setSxtys(String value) {
        this.sxtys = value;
    }
    /**
     * 获取:输血同意书
     */
    public String getSxtys() {
        return sxtys;
    }
    /**
     * 设置:用血证
     */
    public void setYxz(String value) {
        this.yxz = value;
    }
    /**
     * 获取:用血证
     */
    public String getYxz() {
        return yxz;
    }
    /**
     * 设置:主治医师
     */
    public void setZzys(String value) {
        this.zzys = value;
    }
    /**
     * 获取:主治医师
     */
    public String getZzys() {
        return zzys;
    }
    /**
     * 设置:上级医师
     */
    public void setSjys(String value) {
        this.sjys = value;
    }
    /**
     * 获取:上级医师
     */
    public String getSjys() {
        return sjys;
    }
    /**
     * 设置:科主任
     */
    public void setKzr(String value) {
        this.kzr = value;
    }
    /**
     * 获取:科主任
     */
    public String getKzr() {
        return kzr;
    }
    /**
     * 设置:院医务科
     */
    public void setYywk(String value) {
        this.yywk = value;
    }
    /**
     * 获取:院医务科
     */
    public String getYywk() {
        return yywk;
    }
    /**
     * 设置:输血日期时间
     */
    public void setSxrqsj(Timestamp value) {
        this.sxrqsj = value;
    }
    /**
     * 获取:输血日期时间
     */
    public Timestamp getSxrqsj() {
        return sxrqsj;
    }
    /**
     * 设置:注意事项
     */
    public void setZysx(String value) {
        this.zysx = value;
    }
    /**
     * 获取:注意事项
     */
    public String getZysx() {
        return zysx;
    }
    /**
     * 设置:申请时间
     */
    public void setSqsj(Timestamp value) {
        this.sqsj = value;
    }
    /**
     * 获取:申请时间
     */
    public Timestamp getSqsj() {
        return sqsj;
    }
    /**
     * 设置:申请人代码
     */
    public void setSqrdm(String value) {
        this.sqrdm = value;
    }
    /**
     * 获取:申请人代码
     */
    public String getSqrdm() {
        return sqrdm;
    }
    /**
     * 设置:状态(0 申请/1 已发血/2 作废)
     */
    public void setZt(String value) {
        this.zt = value;
    }
    /**
     * 获取:状态(0 申请/1 已发血/2 作废)
     */
    public String getZt() {
        return zt;
    }
    /**
     * 设置:发血时间
     */
    public void setFxsj(Timestamp value) {
        this.fxsj = value;
    }
    /**
     * 获取:发血时间
     */
    public Timestamp getFxsj() {
        return fxsj;
    }
    /**
     * 设置:发血人代码
     */
    public void setFxrdm(String value) {
        this.fxrdm = value;
    }
    /**
     * 获取:发血人代码
     */
    public String getFxrdm() {
        return fxrdm;
    }
    /**
     * 设置:作废时间
     */
    public void setZfsj(Timestamp value) {
        this.zfsj = value;
    }
    /**
     * 获取:作废时间
     */
    public Timestamp getZfsj() {
        return zfsj;
    }
    /**
     * 设置:作废人代码
     */
    public void setZfrdm(String value) {
        this.zfrdm = value;
    }
    /**
     * 获取:作废人代码
     */
    public String getZfrdm() {
        return zfrdm;
    }

	public String getBrch() {
		return brch;
	}
	
	public void setBrch(String brch) {
		this.brch = brch;
	}
	
	public List<CisBxxypzReq> getCisBxxypzReqList() {
		return cisBxxypzReqList;
	}
	
	public void setCisBxxypzReqList(List<CisBxxypzReq> cisBxxypzReqList) {
		this.cisBxxypzReqList = cisBxxypzReqList;
	}
    
    
}