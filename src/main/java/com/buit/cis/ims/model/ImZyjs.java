package com.buit.cis.ims.model;

import com.buit.commons.PageQuery;

import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * 类名称：ImZyjs<br> 
 * 类描述：住院_住院结算
 * @author GONGFANGZHOU 
 * @ApiModel(value="住院_住院结算")
 */
public class ImZyjs  extends  PageQuery{
	//@ApiModelProperty(value="住院号")
    /** 住院号 */
    private Integer zyh;
	//@ApiModelProperty(value="结算次数")
    /** 结算次数 */
    private Integer jscs;
	//@ApiModelProperty(value="机构代码")
    /** 机构代码 */
    private Integer jgid;
	//@ApiModelProperty(value="结算类型 | 1：中途结算 2：预结（不写IM_ZYJS） 3：预结后出院结算 4：终结处理(异常出院) 5：正常出院结算 6：合并结算 9：退费")
    /** 结算类型 | 1：中途结算 2：预结（不写IM_ZYJS） 3：预结后出院结算 4：终结处理(异常出院) 5：正常出院结算 6：合并结算 9：退费 */
    private Integer jslx;
	//@ApiModelProperty(value="开始日期 | 结算费用的开始日期")
    /** 开始日期 | 结算费用的开始日期 */
    private Timestamp ksrq;
	//@ApiModelProperty(value="终止日期 | 结算费用的终止日期")
    /** 终止日期 | 结算费用的终止日期 */
    private Timestamp zzrq;
	//@ApiModelProperty(value="结算日期 | 结算时服务器时间")
    /** 结算日期 | 结算时服务器时间 */
    private Timestamp jsrq;
	//@ApiModelProperty(value="费用合计 | 本次结算费用合计(包含记帐部分费用)")
    /** 费用合计 | 本次结算费用合计(包含记帐部分费用) */
    private BigDecimal fyhj;
	//@ApiModelProperty(value="自负合计 | 本次结算费用中自负费用总计")
    /** 自负合计 | 本次结算费用中自负费用总计 */
    private BigDecimal zfhj;
	//@ApiModelProperty(value="缴款合计 | 从IM_TBKK表中统计")
    /** 缴款合计 | 从IM_TBKK表中统计 */
    private BigDecimal jkhj;
	//@ApiModelProperty(value="发票号码 | 病人结算时打印的结算发票号码")
    /** 发票号码 | 病人结算时打印的结算发票号码 */
    private String fphm;
	//@ApiModelProperty(value="操作工号")
    /** 操作工号 */
    private String czgh;
	//@ApiModelProperty(value="结账日期")
    /** 结账日期 */
    private Timestamp jzrq;
	//@ApiModelProperty(value="汇总日期 | 同IM_TBKK表中HZRQ住院处做汇总日期时填写")
    /** 汇总日期 | 同IM_TBKK表中HZRQ住院处做汇总日期时填写 */
    private Timestamp hzrq;
	//@ApiModelProperty(value="退票号码 | 病人办理补退费时所退的原结算发票号码")
    /** 退票号码 | 病人办理补退费时所退的原结算发票号码 */
    private String tphm;
	//@ApiModelProperty(value="作废日期")
    /** 作废日期 */
    private Timestamp zfrq;
	//@ApiModelProperty(value="作废工号")
    /** 作废工号 */
    private String zfgh;
	//@ApiModelProperty(value="作废判别 | 0：正常 1：已作废")
    /** 作废判别 */
    private Integer zfpb;
	//@ApiModelProperty(value="结算项目 | 中途结算时选择的费用项目")
    /** 结算项目 | 中途结算时选择的费用项目 */
    private String jsxm;
	//@ApiModelProperty(value="结算缴款 | 结算时被冲消的缴款序号")
    /** 结算缴款 | 结算时被冲消的缴款序号 */
    private String jsjk;
	//@ApiModelProperty(value="病人性质 | 病人结算时的性质")
    /** 病人性质 | 病人结算时的性质 */
    private Integer brxz;
	//@ApiModelProperty(value="scbz")
    /** scbz */
    private String scbz;
	//@ApiModelProperty(value="报销金额")
    /** 报销金额 */
    private String bxje;
	//@ApiModelProperty(value="农合医院编号")
    /** 农合医院编号 */
    private String nhbh;
	//@ApiModelProperty(value="农合报销ID")
    /** 农合报销ID */
    private String nhbxid;
	//@ApiModelProperty(value="农合报销日期")
    /** 农合报销日期 */
    private String nhbxrq;
	//@ApiModelProperty(value="住院天数")
    /** 住院天数 */
    private BigDecimal zyts;
	//@ApiModelProperty(value="门诊类别，默认0")
    /** 门诊类别，默认0 */
    private Integer mzlb;
	//@ApiModelProperty(value="减免金额")
    /** 减免金额 */
    private BigDecimal jmje;
	//@ApiModelProperty(value="免赔金额")
    /** 免赔金额 */
    private BigDecimal mpje;
	//@ApiModelProperty(value="理赔金额")
    /** 理赔金额 */
    private BigDecimal lpje;
	//@ApiModelProperty(value="汇率损益金额")
    /** 汇率损益金额 */
    private BigDecimal hlsyje;
	//@ApiModelProperty(value="超付金额")
    /** 超付金额 */
    private BigDecimal cfje;
	//@ApiModelProperty(value="已冲账金额")
    /** 已冲账金额 */
    private BigDecimal yczje;
	//@ApiModelProperty(value="已发生坏账金额")
    /** 已发生坏账金额 */
    private BigDecimal yhzje;
	//@ApiModelProperty(value="binvcode")
    /** binvcode */
    private String binvcode;
	//@ApiModelProperty(value="binvnr")
    /** binvnr */
    private String binvnr;
	//@ApiModelProperty(value="高价药标志,15表示该药品为高价药（医保定额支付）")
    /** 高价药标志,15表示该药品为高价药（医保定额支付） */
    private String gjybz;
	//@ApiModelProperty(value="挂账单位名称:限支付方式为挂账时，录入的备注信息")
    /** 挂账单位名称:限支付方式为挂账时，录入的备注信息 */
    private String gzdwmc;

    /** 设置:住院号  */
    public void setZyh(Integer value) {
        this.zyh = value;
    }
    /** 获取:住院号 */
    public Integer getZyh() {
        return zyh;
    }

    /** 设置:结算次数  */
    public void setJscs(Integer value) {
        this.jscs = value;
    }
    /** 获取:结算次数 */
    public Integer getJscs() {
        return jscs;
    }

    /** 设置:机构代码  */
    public void setJgid(Integer value) {
        this.jgid = value;
    }
    /** 获取:机构代码 */
    public Integer getJgid() {
        return jgid;
    }

    /** 设置:结算类型 | 1：中途结算 2：预结（不写IM_ZYJS） 3：预结后出院结算 4：终结处理(异常出院) 5：正常出院结算 6：合并结算 9：退费  */
    public void setJslx(Integer value) {
        this.jslx = value;
    }
    /** 获取:结算类型 | 1：中途结算 2：预结（不写IM_ZYJS） 3：预结后出院结算 4：终结处理(异常出院) 5：正常出院结算 6：合并结算 9：退费 */
    public Integer getJslx() {
        return jslx;
    }

    /** 设置:开始日期 | 结算费用的开始日期  */
    public void setKsrq(Timestamp value) {
        this.ksrq = value;
    }
    /** 获取:开始日期 | 结算费用的开始日期 */
    public Timestamp getKsrq() {
        return ksrq;
    }

    /** 设置:终止日期 | 结算费用的终止日期  */
    public void setZzrq(Timestamp value) {
        this.zzrq = value;
    }
    /** 获取:终止日期 | 结算费用的终止日期 */
    public Timestamp getZzrq() {
        return zzrq;
    }

    /** 设置:结算日期 | 结算时服务器时间  */
    public void setJsrq(Timestamp value) {
        this.jsrq = value;
    }
    /** 获取:结算日期 | 结算时服务器时间 */
    public Timestamp getJsrq() {
        return jsrq;
    }

    /** 设置:费用合计 | 本次结算费用合计(包含记帐部分费用)  */
    public void setFyhj(BigDecimal value) {
        this.fyhj = value;
    }
    /** 获取:费用合计 | 本次结算费用合计(包含记帐部分费用) */
    public BigDecimal getFyhj() {
        return fyhj;
    }

    /** 设置:自负合计 | 本次结算费用中自负费用总计  */
    public void setZfhj(BigDecimal value) {
        this.zfhj = value;
    }
    /** 获取:自负合计 | 本次结算费用中自负费用总计 */
    public BigDecimal getZfhj() {
        return zfhj;
    }

    /** 设置:缴款合计 | 从IM_TBKK表中统计  */
    public void setJkhj(BigDecimal value) {
        this.jkhj = value;
    }
    /** 获取:缴款合计 | 从IM_TBKK表中统计 */
    public BigDecimal getJkhj() {
        return jkhj;
    }

    /** 设置:发票号码 | 病人结算时打印的结算发票号码  */
    public void setFphm(String value) {
        this.fphm = value;
    }
    /** 获取:发票号码 | 病人结算时打印的结算发票号码 */
    public String getFphm() {
        return fphm;
    }

    /** 设置:操作工号  */
    public void setCzgh(String value) {
        this.czgh = value;
    }
    /** 获取:操作工号 */
    public String getCzgh() {
        return czgh;
    }

    /** 设置:结账日期  */
    public void setJzrq(Timestamp value) {
        this.jzrq = value;
    }
    /** 获取:结账日期 */
    public Timestamp getJzrq() {
        return jzrq;
    }

    /** 设置:汇总日期 | 同IM_TBKK表中HZRQ住院处做汇总日期时填写  */
    public void setHzrq(Timestamp value) {
        this.hzrq = value;
    }
    /** 获取:汇总日期 | 同IM_TBKK表中HZRQ住院处做汇总日期时填写 */
    public Timestamp getHzrq() {
        return hzrq;
    }

    /** 设置:退票号码 | 病人办理补退费时所退的原结算发票号码  */
    public void setTphm(String value) {
        this.tphm = value;
    }
    /** 获取:退票号码 | 病人办理补退费时所退的原结算发票号码 */
    public String getTphm() {
        return tphm;
    }

    /** 设置:作废日期  */
    public void setZfrq(Timestamp value) {
        this.zfrq = value;
    }
    /** 获取:作废日期 */
    public Timestamp getZfrq() {
        return zfrq;
    }

    /** 设置:作废工号  */
    public void setZfgh(String value) {
        this.zfgh = value;
    }
    /** 获取:作废工号 */
    public String getZfgh() {
        return zfgh;
    }

    /** 设置:作废判别  */
    public void setZfpb(Integer value) {
        this.zfpb = value;
    }
    /** 获取:作废判别 */
    public Integer getZfpb() {
        return zfpb;
    }

    /** 设置:结算项目 | 中途结算时选择的费用项目  */
    public void setJsxm(String value) {
        this.jsxm = value;
    }
    /** 获取:结算项目 | 中途结算时选择的费用项目 */
    public String getJsxm() {
        return jsxm;
    }

    /** 设置:结算缴款 | 结算时被冲消的缴款序号  */
    public void setJsjk(String value) {
        this.jsjk = value;
    }
    /** 获取:结算缴款 | 结算时被冲消的缴款序号 */
    public String getJsjk() {
        return jsjk;
    }

    /** 设置:病人性质 | 病人结算时的性质  */
    public void setBrxz(Integer value) {
        this.brxz = value;
    }
    /** 获取:病人性质 | 病人结算时的性质 */
    public Integer getBrxz() {
        return brxz;
    }

    /** 设置:scbz  */
    public void setScbz(String value) {
        this.scbz = value;
    }
    /** 获取:scbz */
    public String getScbz() {
        return scbz;
    }

    /** 设置:报销金额  */
    public void setBxje(String value) {
        this.bxje = value;
    }
    /** 获取:报销金额 */
    public String getBxje() {
        return bxje;
    }

    /** 设置:农合医院编号  */
    public void setNhbh(String value) {
        this.nhbh = value;
    }
    /** 获取:农合医院编号 */
    public String getNhbh() {
        return nhbh;
    }

    /** 设置:农合报销ID  */
    public void setNhbxid(String value) {
        this.nhbxid = value;
    }
    /** 获取:农合报销ID */
    public String getNhbxid() {
        return nhbxid;
    }

    /** 设置:农合报销日期  */
    public void setNhbxrq(String value) {
        this.nhbxrq = value;
    }
    /** 获取:农合报销日期 */
    public String getNhbxrq() {
        return nhbxrq;
    }

    /** 设置:住院天数  */
    public void setZyts(BigDecimal value) {
        this.zyts = value;
    }
    /** 获取:住院天数 */
    public BigDecimal getZyts() {
        return zyts;
    }

    /** 设置:门诊类别，默认0  */
    public void setMzlb(Integer value) {
        this.mzlb = value;
    }
    /** 获取:门诊类别，默认0 */
    public Integer getMzlb() {
        return mzlb;
    }

    /** 设置:减免金额  */
    public void setJmje(BigDecimal value) {
        this.jmje = value;
    }
    /** 获取:减免金额 */
    public BigDecimal getJmje() {
        return jmje;
    }

    /** 设置:免赔金额  */
    public void setMpje(BigDecimal value) {
        this.mpje = value;
    }
    /** 获取:免赔金额 */
    public BigDecimal getMpje() {
        return mpje;
    }

    /** 设置:理赔金额  */
    public void setLpje(BigDecimal value) {
        this.lpje = value;
    }
    /** 获取:理赔金额 */
    public BigDecimal getLpje() {
        return lpje;
    }

    /** 设置:汇率损益金额  */
    public void setHlsyje(BigDecimal value) {
        this.hlsyje = value;
    }
    /** 获取:汇率损益金额 */
    public BigDecimal getHlsyje() {
        return hlsyje;
    }

    /** 设置:超付金额  */
    public void setCfje(BigDecimal value) {
        this.cfje = value;
    }
    /** 获取:超付金额 */
    public BigDecimal getCfje() {
        return cfje;
    }

    /** 设置:已冲账金额  */
    public void setYczje(BigDecimal value) {
        this.yczje = value;
    }
    /** 获取:已冲账金额 */
    public BigDecimal getYczje() {
        return yczje;
    }

    /** 设置:已发生坏账金额  */
    public void setYhzje(BigDecimal value) {
        this.yhzje = value;
    }
    /** 获取:已发生坏账金额 */
    public BigDecimal getYhzje() {
        return yhzje;
    }

    /** 设置:binvcode  */
    public void setBinvcode(String value) {
        this.binvcode = value;
    }
    /** 获取:binvcode */
    public String getBinvcode() {
        return binvcode;
    }

    /** 设置:binvnr  */
    public void setBinvnr(String value) {
        this.binvnr = value;
    }
    /** 获取:binvnr */
    public String getBinvnr() {
        return binvnr;
    }

    /** 设置:高价药标志,15表示该药品为高价药（医保定额支付）  */
    public void setGjybz(String value) {
        this.gjybz = value;
    }
    /** 获取:高价药标志,15表示该药品为高价药（医保定额支付） */
    public String getGjybz() {
        return gjybz;
    }

    /** 设置:挂账单位名称:限支付方式为挂账时，录入的备注信息  */
    public void setGzdwmc(String value) {
        this.gzdwmc = value;
    }
    /** 获取:挂账单位名称:限支付方式为挂账时，录入的备注信息 */
    public String getGzdwmc() {
        return gzdwmc;
    }


}
