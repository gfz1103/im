package com.buit.cis.ims.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;
import java.util.List;

/**
 * 结算管理-住院结算（结算保存入参）
 * zhouhaisheng
 */
@ApiModel(value = "结算管理-住院结算(结算保存入参)")
public class SaveSettleAccountsReq {
    @ApiModelProperty(value="医疗总费合计")
    private BigDecimal yl_zfhj;
    @ApiModelProperty(value="病人病区")
    private Integer brbq;
    @ApiModelProperty(value="结算次数")
    private Integer jscs;
    @ApiModelProperty(value="住院号码")
    private String zyhm;
    @ApiModelProperty(value="备注")
    private String remark;
    @ApiModelProperty(value="住院号")
    private Integer zyh;
    @ApiModelProperty(value="数据转移 | 0 未转移 1 已转移")
    private Integer sjzy;
    @ApiModelProperty(value="病案号码")
    private String bahm;
    @ApiModelProperty(value="汇总日期 | 汇总日报结束时填写，病人费用、医嘱转到出院表中，根据该字段可判断病人费用、医嘱在哪张表中")
    private String hzrq;
    @ApiModelProperty(value="冻结ID号 | 对应ZY_ZHDJ.DJID的代码")
    private Integer djid;
    @ApiModelProperty(value="医保住院天数")
    private BigDecimal ybzyts;
    @ApiModelProperty(value="病人全名")
    private Object brqm;
    @ApiModelProperty(value="结算日期")
    private String jsrq;
    @ApiModelProperty(value="出院判别")
    private Integer cypb;
    @ApiModelProperty(value="入院日期")
    private String ryrq;
    @ApiModelProperty(value="出院日期")
    private String cyrq;
    @ApiModelProperty(value = "住院天数")
    private BigDecimal zyts;
    @ApiModelProperty(value="结算开始日期")
    private String ksrq;
    @ApiModelProperty(value="结算终止日期")
    private String zzrq;
    @ApiModelProperty(value = "结算天数")
    private Double jsts;
    @ApiModelProperty(value="结算类型")
    private Integer jslx;
    @ApiModelProperty(value="")
    private BigDecimal tcje;
    @ApiModelProperty(value="病人姓名")
    private String brxm;
    @ApiModelProperty(value="费用合计")
    private BigDecimal fyhj;
    @ApiModelProperty(value="")
    private BigDecimal yl_fyhj;
    @ApiModelProperty(value="")
    private BigDecimal jkhj;
    @ApiModelProperty(value="病人床号")
    private  String brch;
    @ApiModelProperty(value="药品自负合计")
    private BigDecimal yp_zfhj;
    @ApiModelProperty(value="")
    private String xtrq;
    @ApiModelProperty(value="药品费用合计")
    private BigDecimal yp_fyhj;
    @ApiModelProperty(value="")
    private BigDecimal jsday;
    @ApiModelProperty(value="减免金额")
    private BigDecimal jmje;
    @ApiModelProperty(value="病人科室")
    private Integer brks;
    @ApiModelProperty(value="病人性质")
    private Integer brxz;
    @ApiModelProperty(value="")
    private String ljrq;
    @ApiModelProperty(value="作废判别")
    private Integer zfpb;
    @ApiModelProperty(value="")
    private String zhzf;
    @ApiModelProperty(value="自负合计")
    private BigDecimal zfhj;
    @ApiModelProperty(value="")
    private Integer jsbs;
    @ApiModelProperty(value="")
    private Integer fffs;
    @ApiModelProperty(value="发票号码")
    private String fphm;
    @ApiModelProperty(value="")
    private String fkjd;
    @ApiModelProperty(value="")
    private Integer wcfs;
    @ApiModelProperty(value="")
    private String wcjd;
    @ApiModelProperty(value="结算金额")
    private BigDecimal jsje;
    @ApiModelProperty(value = "应收金额 | 自负金额经过四舍五入后计算后的实际收款金额 eg: 自负-100.01，应收-100")
    private BigDecimal ysje;
    @ApiModelProperty(value = "误差金额 | 四舍五入的部分")
    private BigDecimal wcje;
    @ApiModelProperty(value="理赔金额")
    private BigDecimal lpje;
    @ApiModelProperty(value="免赔金额")
    private BigDecimal mpje;
    @ApiModelProperty(value="缴款金额")
    private BigDecimal jkje;
    @ApiModelProperty(value="")
    private BigDecimal tzje;
    @ApiModelProperty(value = "")
    private  String jssqxh;
    @ApiModelProperty(value = "交易费用总额")
    private  String totalexpense;
    @ApiModelProperty(value = "凭证类型")
    private  String cardtype;
    @ApiModelProperty(value = "凭证码")
    private  String carddata;
    @ApiModelProperty(value = "账户标志")
    private  String accountattr;
    @ApiModelProperty(value = "医疗类别")
    private  String yllb;
    @ApiModelProperty(value = "病人类型")
    private  String persontype;
    @ApiModelProperty(value = "工伤认定号")
    private  String gsrdh;
    @ApiModelProperty(value = "出院结算标识")
    private  String cyjsbz;
//    @ApiModelProperty(value = "结算开始日期")
//    private  String jsksrq;
//    @ApiModelProperty(value = "结算结束日期")
//    private  String jsjsrq;
    @ApiModelProperty(value = "就诊单元号")
    private  String jzdyh;
    @ApiModelProperty(value = "线上业务类型")
    private String xsywlx;
    @ApiModelProperty(value = "明细账单行数")
    private String mxzdhs;
    @ApiModelProperty(value = "ip")
    private String ip;
    @ApiModelProperty(value = "医保结算开始日期")
    private String ybjsksrq;
    @ApiModelProperty(value = "医保结算结束日期")
    private String ybjsjsrq;
    @ApiModelProperty(value = "付款信息")
    private List<ImFkxxAddReq> fkxxList;
    @ApiModelProperty(value = "操作时间 | 进入预处理界面的时间")
    private String czsj;

    public BigDecimal getYl_zfhj() {
        return yl_zfhj;
    }

    public void setYl_zfhj(BigDecimal yl_zfhj) {
        this.yl_zfhj = yl_zfhj;
    }

    public Integer getBrbq() {
        return brbq;
    }

    public void setBrbq(Integer brbq) {
        this.brbq = brbq;
    }

    public Integer getJscs() {
        return jscs;
    }

    public void setJscs(Integer jscs) {
        this.jscs = jscs;
    }

    public String getZyhm() {
        return zyhm;
    }

    public void setZyhm(String zyhm) {
        this.zyhm = zyhm;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getZyh() {
        return zyh;
    }

    public void setZyh(Integer zyh) {
        this.zyh = zyh;
    }

    public Integer getSjzy() {
        return sjzy;
    }

    public void setSjzy(Integer sjzy) {
        this.sjzy = sjzy;
    }

    public String getBahm() {
        return bahm;
    }

    public void setBahm(String bahm) {
        this.bahm = bahm;
    }

    public String getHzrq() {
        return hzrq;
    }

    public void setHzrq(String hzrq) {
        this.hzrq = hzrq;
    }

    public Integer getDjid() {
        return djid;
    }

    public void setDjid(Integer djid) {
        this.djid = djid;
    }

    public String getYbjsksrq() {
        return ybjsksrq;
    }

    public void setYbjsksrq(String ybjsksrq) {
        this.ybjsksrq = ybjsksrq;
    }


    public BigDecimal getYbzyts() {
        return ybzyts;
    }

    public void setYbzyts(BigDecimal ybzyts) {
        this.ybzyts = ybzyts;
    }

    public BigDecimal getZyts() {
        return zyts;
    }

    public void setZyts(BigDecimal zyts) {
        this.zyts = zyts;
    }

    public Object getBrqm() {
        return brqm;
    }

    public void setBrqm(Object brqm) {
        this.brqm = brqm;
    }

    public String getJsrq() {
        return jsrq;
    }

    public void setJsrq(String jsrq) {
        this.jsrq = jsrq;
    }

    public String getCyrq() {
        return cyrq;
    }

    public void setCyrq(String cyrq) {
        this.cyrq = cyrq;
    }

    public String getRyrq() {
        return ryrq;
    }

    public void setRyrq(String ryrq) {
        this.ryrq = ryrq;
    }

    public Integer getCypb() {
        return cypb;
    }

    public void setCypb(Integer cypb) {
        this.cypb = cypb;
    }

    public String getKsrq() {
        return ksrq;
    }

    public void setKsrq(String ksrq) {
        this.ksrq = ksrq;
    }

    public Integer getJslx() {
        return jslx;
    }

    public void setJslx(Integer jslx) {
        this.jslx = jslx;
    }

    public BigDecimal getTcje() {
        return tcje;
    }

    public void setTcje(BigDecimal tcje) {
        this.tcje = tcje;
    }

    public String getBrxm() {
        return brxm;
    }

    public void setBrxm(String brxm) {
        this.brxm = brxm;
    }

    public BigDecimal getFyhj() {
        return fyhj;
    }

    public void setFyhj(BigDecimal fyhj) {
        this.fyhj = fyhj;
    }

    public BigDecimal getYl_fyhj() {
        return yl_fyhj;
    }

    public void setYl_fyhj(BigDecimal yl_fyhj) {
        this.yl_fyhj = yl_fyhj;
    }

    public BigDecimal getJkhj() {
        return jkhj;
    }

    public void setJkhj(BigDecimal jkhj) {
        this.jkhj = jkhj;
    }

    public String getBrch() {
        return brch;
    }

    public void setBrch(String brch) {
        this.brch = brch;
    }

    public BigDecimal getYp_zfhj() {
        return yp_zfhj;
    }

    public void setYp_zfhj(BigDecimal yp_zfhj) {
        this.yp_zfhj = yp_zfhj;
    }

    public String getXtrq() {
        return xtrq;
    }

    public void setXtrq(String xtrq) {
        this.xtrq = xtrq;
    }

    public BigDecimal getYp_fyhj() {
        return yp_fyhj;
    }

    public void setYp_fyhj(BigDecimal yp_fyhj) {
        this.yp_fyhj = yp_fyhj;
    }

    public BigDecimal getJsday() {
        return jsday;
    }

    public void setJsday(BigDecimal jsday) {
        this.jsday = jsday;
    }

    public BigDecimal getJmje() {
        return jmje;
    }

    public void setJmje(BigDecimal jmje) {
        this.jmje = jmje;
    }

    public Integer getBrks() {
        return brks;
    }

    public void setBrks(Integer brks) {
        this.brks = brks;
    }

    public Integer getBrxz() {
        return brxz;
    }

    public void setBrxz(Integer brxz) {
        this.brxz = brxz;
    }

    public String getLjrq() {
        return ljrq;
    }

    public void setLjrq(String ljrq) {
        this.ljrq = ljrq;
    }

    public Integer getZfpb() {
        return zfpb;
    }

    public void setZfpb(Integer zfpb) {
        this.zfpb = zfpb;
    }

    public String getZhzf() {
        return zhzf;
    }

    public void setZhzf(String zhzf) {
        this.zhzf = zhzf;
    }

    public BigDecimal getZfhj() {
        return zfhj;
    }

    public void setZfhj(BigDecimal zfhj) {
        this.zfhj = zfhj;
    }

    public Integer getJsbs() {
        return jsbs;
    }

    public void setJsbs(Integer jsbs) {
        this.jsbs = jsbs;
    }

    public Integer getFffs() {
        return fffs;
    }

    public void setFffs(Integer fffs) {
        this.fffs = fffs;
    }

    public String getFphm() {
        return fphm;
    }

    public void setFphm(String fphm) {
        this.fphm = fphm;
    }

    public String getFkjd() {
        return fkjd;
    }

    public void setFkjd(String fkjd) {
        this.fkjd = fkjd;
    }

    public Integer getWcfs() {
        return wcfs;
    }

    public void setWcfs(Integer wcfs) {
        this.wcfs = wcfs;
    }

    public String getWcjd() {
        return wcjd;
    }

    public void setWcjd(String wcjd) {
        this.wcjd = wcjd;
    }

    public BigDecimal getJsje() {
        return jsje;
    }

    public void setJsje(BigDecimal jsje) {
        this.jsje = jsje;
    }

    public BigDecimal getYsje() {
        return ysje;
    }

    public void setYsje(BigDecimal ysje) {
        this.ysje = ysje;
    }

    public BigDecimal getWcje() {
        return wcje;
    }

    public void setWcje(BigDecimal wcje) {
        this.wcje = wcje;
    }

    public BigDecimal getLpje() {
        return lpje;
    }

    public void setLpje(BigDecimal lpje) {
        this.lpje = lpje;
    }

    public BigDecimal getMpje() {
        return mpje;
    }

    public void setMpje(BigDecimal mpje) {
        this.mpje = mpje;
    }

    public BigDecimal getJkje() {
        return jkje;
    }

    public void setJkje(BigDecimal jkje) {
        this.jkje = jkje;
    }

    public BigDecimal getTzje() {
        return tzje;
    }

    public void setTzje(BigDecimal tzje) {
        this.tzje = tzje;
    }


    public String getJssqxh() {
        return jssqxh;
    }
    public void setJssqxh(String jssqxh) {
        this.jssqxh = jssqxh;
    }

    public String getTotalexpense() {
        return totalexpense;
    }

    public void setTotalexpense(String totalexpense) {
        this.totalexpense = totalexpense;
    }

    public String getCardtype() {
        return cardtype;
    }

    public void setCardtype(String cardtype) {
        this.cardtype = cardtype;
    }

    public String getCarddata() {
        return carddata;
    }

    public void setCarddata(String carddata) {
        this.carddata = carddata;
    }

    public String getAccountattr() {
        return accountattr;
    }

    public void setAccountattr(String accountattr) {
        this.accountattr = accountattr;
    }

    public String getYllb() {
        return yllb;
    }

    public void setYllb(String yllb) {
        this.yllb = yllb;
    }

    public String getPersontype() {
        return persontype;
    }

    public void setPersontype(String persontype) {
        this.persontype = persontype;
    }

    public String getGsrdh() {
        return gsrdh;
    }

    public void setGsrdh(String gsrdh) {
        this.gsrdh = gsrdh;
    }

    public String getCyjsbz() {
        return cyjsbz;
    }

    public void setCyjsbz(String cyjsbz) {
        this.cyjsbz = cyjsbz;
    }

//    public String getJsksrq() {
//        return jsksrq;
//    }
//
//    public void setJsksrq(String jsksrq) {
//        this.jsksrq = jsksrq;
//    }
//
//    public String getJsjsrq() {
//        return jsjsrq;
//    }
//
//    public void setJsjsrq(String jsjsrq) {
//        this.jsjsrq = jsjsrq;
//    }

    public String getJzdyh() {
        return jzdyh;
    }

    public void setJzdyh(String jzdyh) {
        this.jzdyh = jzdyh;
    }

    public String getXsywlx() {
        return xsywlx;
    }

    public void setXsywlx(String xsywlx) {
        this.xsywlx = xsywlx;
    }

    public String getMxzdhs() {
        return mxzdhs;
    }

    public void setMxzdhs(String mxzdhs) {
        this.mxzdhs = mxzdhs;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getZzrq() {
        return zzrq;
    }

    public void setZzrq(String zzrq) {
        this.zzrq = zzrq;
    }

    public Double getJsts() {
        return jsts;
    }

    public void setJsts(Double jsts) {
        this.jsts = jsts;
    }

    public String getYbjsjsrq() {
        return ybjsjsrq;
    }

    public void setYbjsjsrq(String ybjsjsrq) {
        this.ybjsjsrq = ybjsjsrq;
    }

    public List<ImFkxxAddReq> getFkxxList() {
        return fkxxList;
    }

    public void setFkxxList(List<ImFkxxAddReq> fkxxList) {
        this.fkxxList = fkxxList;
    }

    public String getCzsj() {
        return czsj;
    }

    public void setCzsj(String czsj) {
        this.czsj = czsj;
    }
}
