package com.buit.cis.ims.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;

/**
 * 住院管理-结算信息
 */
@ApiModel(value = "住院管理-结算信息")
public class ZyJsxxReq {
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
    @ApiModelProperty(value="医保结算开始日期")
    private String ybjsksrq;
    @ApiModelProperty(value="医保住院天数")
    private Integer ybzyts;
    @ApiModelProperty(value="病人全名")
    private Object brqm;
    @ApiModelProperty(value="结算日期")
    private String jsrq;
    @ApiModelProperty(value="出院日期")
    private String cyrq;
    @ApiModelProperty(value="入院日期")
    private String ryrq;
    @ApiModelProperty(value="出院判别")
    private Integer cypb;
    @ApiModelProperty(value="开始日期")
    private String ksrq;
    @ApiModelProperty(value="结算类型")
    private Integer jslx;
    @ApiModelProperty(value="")
    private String tcje;
    @ApiModelProperty(value="病人姓名")
    private String brxm;
    @ApiModelProperty(value="费用合计")
    private String fyhj;
    @ApiModelProperty(value="")
    private BigDecimal yl_fyhj;
    @ApiModelProperty(value="")
    private String jkhj;
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
    private Integer jmje;
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
    private String zfhj;
    @ApiModelProperty(value="结算标识")
    private Integer jsbs;
    @ApiModelProperty(value="")
    private Integer fffs;
    @ApiModelProperty(value="发票号码")
    private String fphm;
    @ApiModelProperty(value="")
    private String fkjd;
    @ApiModelProperty(value="付款方式")
    private Integer fkfs;
    @ApiModelProperty(value="误差方式")
    private Integer wcfs;
    @ApiModelProperty(value="")
    private String wcjd;
    @ApiModelProperty(value="结算金额")
    private String jsje;
    @ApiModelProperty(value="")
    private BigDecimal ysje;
    @ApiModelProperty(value="误差金额")
    private String wcje;
    @ApiModelProperty(value="理赔金额")
    private Integer lpje;
    @ApiModelProperty(value="免赔金额")
    private Integer mpje;
    @ApiModelProperty(value="缴款金额")
    private String jkje;
    @ApiModelProperty(value="退找金额")
    private String tzje;

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

    public Integer getYbzyts() {
        return ybzyts;
    }

    public void setYbzyts(Integer ybzyts) {
        this.ybzyts = ybzyts;
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

    public String getTcje() {
        return tcje;
    }

    public void setTcje(String tcje) {
        this.tcje = tcje;
    }

    public String getBrxm() {
        return brxm;
    }

    public void setBrxm(String brxm) {
        this.brxm = brxm;
    }

    public String getFyhj() {
        return fyhj;
    }

    public void setFyhj(String fyhj) {
        this.fyhj = fyhj;
    }

    public BigDecimal getYl_fyhj() {
        return yl_fyhj;
    }

    public void setYl_fyhj(BigDecimal yl_fyhj) {
        this.yl_fyhj = yl_fyhj;
    }

    public String getJkhj() {
        return jkhj;
    }

    public void setJkhj(String jkhj) {
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

    public Integer getJmje() {
        return jmje;
    }

    public void setJmje(Integer jmje) {
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

    public String getZfhj() {
        return zfhj;
    }

    public void setZfhj(String zfhj) {
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

    public Integer getFkfs() {
        return fkfs;
    }

    public void setFkfs(Integer fkfs) {
        this.fkfs = fkfs;
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

    public String getJsje() {
        return jsje;
    }

    public void setJsje(String jsje) {
        this.jsje = jsje;
    }

    public BigDecimal getYsje() {
        return ysje;
    }

    public void setYsje(BigDecimal ysje) {
        this.ysje = ysje;
    }

    public String getWcje() {
        return wcje;
    }

    public void setWcje(String wcje) {
        this.wcje = wcje;
    }

    public Integer getLpje() {
        return lpje;
    }

    public void setLpje(Integer lpje) {
        this.lpje = lpje;
    }

    public Integer getMpje() {
        return mpje;
    }

    public void setMpje(Integer mpje) {
        this.mpje = mpje;
    }

    public String getJkje() {
        return jkje;
    }

    public void setJkje(String jkje) {
        this.jkje = jkje;
    }

    public String getTzje() {
        return tzje;
    }

    public void setTzje(String tzje) {
        this.tzje = tzje;
    }
}
