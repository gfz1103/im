package com.buit.cis.ims.response;

import com.buit.commons.PageQuery;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;
import java.sql.Timestamp;


/**
 * 类名称：ImTbkk<br>
 * 类描述：住院_退补缴款<br>
 * @author ZHOUHAISHENG
 */
@ApiModel(value="住院_退补缴款返回")
public class ImTbkkResp  extends PageQuery {
    @ApiModelProperty(value="缴款序号")
    private Integer jkxh;
    @ApiModelProperty(value="机构代码")
    private Integer jgid;
    @ApiModelProperty(value="住院号")
    private Integer zyh;
    @ApiModelProperty(value="缴款日期")
    private Timestamp jkrq;
    @ApiModelProperty(value="缴款金额")
    private BigDecimal jkje;
    @ApiModelProperty(value="缴款方式 | 字典sys_dict_config:20")
    private Integer jkfs;
    @ApiModelProperty(value="收据号码 | 病人预缴款收据号码")
    private String sjhm;
    @ApiModelProperty(value="支票号码 | 病人缴款为支票时的支票号码")
    private String zphm;
    @ApiModelProperty(value="结算次数 | 同IM_HZRY,IM_ZYJS,IM_FEE_FYMX表中JSCS JSCS=0 则病人尚未办理结算.")
    private Integer jscs;
    @ApiModelProperty(value="操作工号 接口字典/sysuser/findGroupUserDic")
    private String czgh;
    @ApiModelProperty(value="结账日期")
    private Timestamp jzrq;
    @ApiModelProperty(value="汇总日期")
    private Timestamp hzrq;
    @ApiModelProperty(value="作废日期")
    private Timestamp zfrq;
    @ApiModelProperty(value="作废工号 ")
    private String zfgh;
    @ApiModelProperty(value="作废判别 | 注销预缴款或发票作废时填写")
    private Integer zfpb;
    @ApiModelProperty(value="转存判别 | 0.正常缴款;    1.中结转存 字典flagdata_code:38")
    private Integer zcpb;
    @ApiModelProperty(value="scbz")
    private String scbz;
    @ApiModelProperty(value="门诊类别")
    private Integer mzlb;
    @ApiModelProperty(value="操作员")
    private String czy;

    public Integer getJkxh() {
        return jkxh;
    }

    public void setJkxh(Integer jkxh) {
        this.jkxh = jkxh;
    }

    public Integer getJgid() {
        return jgid;
    }

    public void setJgid(Integer jgid) {
        this.jgid = jgid;
    }

    public Integer getZyh() {
        return zyh;
    }

    public void setZyh(Integer zyh) {
        this.zyh = zyh;
    }

    public Timestamp getJkrq() {
        return jkrq;
    }

    public void setJkrq(Timestamp jkrq) {
        this.jkrq = jkrq;
    }

    public BigDecimal getJkje() {
        return jkje;
    }

    public void setJkje(BigDecimal jkje) {
        this.jkje = jkje;
    }

    public Integer getJkfs() {
        return jkfs;
    }

    public void setJkfs(Integer jkfs) {
        this.jkfs = jkfs;
    }

    public String getSjhm() {
        return sjhm;
    }

    public void setSjhm(String sjhm) {
        this.sjhm = sjhm;
    }

    public String getZphm() {
        return zphm;
    }

    public void setZphm(String zphm) {
        this.zphm = zphm;
    }

    public Integer getJscs() {
        return jscs;
    }

    public void setJscs(Integer jscs) {
        this.jscs = jscs;
    }

    public String getCzgh() {
        return czgh;
    }

    public void setCzgh(String czgh) {
        this.czgh = czgh;
    }

    public Timestamp getJzrq() {
        return jzrq;
    }

    public void setJzrq(Timestamp jzrq) {
        this.jzrq = jzrq;
    }

    public Timestamp getHzrq() {
        return hzrq;
    }

    public void setHzrq(Timestamp hzrq) {
        this.hzrq = hzrq;
    }

    public Timestamp getZfrq() {
        return zfrq;
    }

    public void setZfrq(Timestamp zfrq) {
        this.zfrq = zfrq;
    }

    public String getZfgh() {
        return zfgh;
    }

    public void setZfgh(String zfgh) {
        this.zfgh = zfgh;
    }

    public Integer getZfpb() {
        return zfpb;
    }

    public void setZfpb(Integer zfpb) {
        this.zfpb = zfpb;
    }

    public Integer getZcpb() {
        return zcpb;
    }

    public void setZcpb(Integer zcpb) {
        this.zcpb = zcpb;
    }

    public String getScbz() {
        return scbz;
    }

    public void setScbz(String scbz) {
        this.scbz = scbz;
    }

    public Integer getMzlb() {
        return mzlb;
    }

    public void setMzlb(Integer mzlb) {
        this.mzlb = mzlb;
    }

    public String getCzy() {
        return czy;
    }

    public void setCzy(String czy) {
        this.czy = czy;
    }
}
