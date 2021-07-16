package com.buit.cis.ims.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;

/**
 * 结算处理页面的结算金额和补退金额汇总
 */
@ApiModel(value = "结算处理页面的结算金额和补退金额汇总")
public class BalanceAccountsSettleResp {
    @ApiModelProperty("结算类型")
    public String jslx;

    @ApiModelProperty("住院号")
    public Integer zyh;

    @ApiModelProperty("病人姓名")
    public String brxm;

    @ApiModelProperty("病人性质")
    public Integer brxz;

    @ApiModelProperty("总费用")
    public BigDecimal zfy=new BigDecimal(0.00);


    @ApiModelProperty("自负金额")
    public BigDecimal zfje=new BigDecimal(0.00);

    @ApiModelProperty("账户支付")
    public BigDecimal zhzf=new BigDecimal(0.00);

    @ApiModelProperty("预缴款")
    public BigDecimal yjk=new BigDecimal(0.00);

    @ApiModelProperty("减免金额")
    public BigDecimal jmje=new BigDecimal(0.00);

    @ApiModelProperty("免赔金额")
    public BigDecimal mpje=new BigDecimal(0.00);

    @ApiModelProperty("理赔金额")
    public BigDecimal lpje=new BigDecimal(0.00);

    @ApiModelProperty("应找金额")
    public BigDecimal yzje=new BigDecimal(0.00);

    @ApiModelProperty("卡内余额")
    public BigDecimal knye=new BigDecimal(0.00);

    @ApiModelProperty("就诊单元号")
    public String jzdyh;

    @ApiModelProperty("计算申请序号")
    public String jssqxh;


    @ApiModelProperty("医保结算范围费用总额")
    public BigDecimal ybjsfwfyze=new BigDecimal(0.00);
    @ApiModelProperty("非医保结算范围费用总额")
    public BigDecimal fybjsfwfyze=new BigDecimal(0.00);
    @ApiModelProperty("分类自负")
    public BigDecimal flzf=new BigDecimal(0.00);
    @ApiModelProperty("自负金额")
    public BigDecimal ybzf=new BigDecimal(0.00);
    @ApiModelProperty("个人账户支付")
    public BigDecimal grzhzf=new BigDecimal(0.00);
    @ApiModelProperty("统筹支付")
    public BigDecimal tczf=new BigDecimal(0.00);
    @ApiModelProperty("附加支付")
    public BigDecimal fjzf=new BigDecimal(0.00);
    @ApiModelProperty("当年账户余额")
    public BigDecimal curaccountamt=new BigDecimal(0.00);
    @ApiModelProperty("历年账户余额")
    public BigDecimal hisaccountamt=new BigDecimal(0.00);


    public String getJslx() {
        return jslx;
    }

    public void setJslx(String jslx) {
        this.jslx = jslx;
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

    public BigDecimal getZfy() {
        return zfy;
    }

    public void setZfy(BigDecimal zfy) {
        this.zfy = zfy;
    }

    public BigDecimal getZfje() {
        return zfje;
    }

    public void setZfje(BigDecimal zfje) {
        this.zfje = zfje;
    }

    public BigDecimal getZhzf() {
        return zhzf;
    }

    public void setZhzf(BigDecimal zhzf) {
        this.zhzf = zhzf;
    }

    public BigDecimal getYjk() {
        return yjk;
    }

    public void setYjk(BigDecimal yjk) {
        this.yjk = yjk;
    }

    public BigDecimal getJmje() {
        return jmje;
    }

    public void setJmje(BigDecimal jmje) {
        this.jmje = jmje;
    }

    public BigDecimal getMpje() {
        return mpje;
    }

    public void setMpje(BigDecimal mpje) {
        this.mpje = mpje;
    }

    public BigDecimal getLpje() {
        return lpje;
    }

    public void setLpje(BigDecimal lpje) {
        this.lpje = lpje;
    }

    public BigDecimal getYzje() {
        return yzje;
    }

    public void setYzje(BigDecimal yzje) {
        this.yzje = yzje;
    }

    public BigDecimal getKnye() {
        return knye;
    }

    public void setKnye(BigDecimal knye) {
        this.knye = knye;
    }

    public String getJzdyh() {
        return jzdyh;
    }

    public void setJzdyh(String jzdyh) {
        this.jzdyh = jzdyh;
    }

    public String getJssqxh() {
        return jssqxh;
    }

    public void setJssqxh(String jssqxh) {
        this.jssqxh = jssqxh;
    }

    public BigDecimal getYbjsfwfyze() {
        return ybjsfwfyze;
    }

    public void setYbjsfwfyze(BigDecimal ybjsfwfyze) {
        this.ybjsfwfyze = ybjsfwfyze;
    }

    public BigDecimal getFybjsfwfyze() {
        return fybjsfwfyze;
    }

    public void setFybjsfwfyze(BigDecimal fybjsfwfyze) {
        this.fybjsfwfyze = fybjsfwfyze;
    }

    public BigDecimal getFlzf() {
        return flzf;
    }

    public void setFlzf(BigDecimal flzf) {
        this.flzf = flzf;
    }

    public BigDecimal getYbzf() {
        return ybzf;
    }

    public void setYbzf(BigDecimal ybzf) {
        this.ybzf = ybzf;
    }

    public BigDecimal getGrzhzf() {
        return grzhzf;
    }

    public void setGrzhzf(BigDecimal grzhzf) {
        this.grzhzf = grzhzf;
    }

    public BigDecimal getTczf() {
        return tczf;
    }

    public void setTczf(BigDecimal tczf) {
        this.tczf = tczf;
    }

    public BigDecimal getFjzf() {
        return fjzf;
    }

    public void setFjzf(BigDecimal fjzf) {
        this.fjzf = fjzf;
    }

    public BigDecimal getCuraccountamt() {
        return curaccountamt;
    }

    public void setCuraccountamt(BigDecimal curaccountamt) {
        this.curaccountamt = curaccountamt;
    }

    public BigDecimal getHisaccountamt() {
        return hisaccountamt;
    }

    public void setHisaccountamt(BigDecimal hisaccountamt) {
        this.hisaccountamt = hisaccountamt;
    }
}
