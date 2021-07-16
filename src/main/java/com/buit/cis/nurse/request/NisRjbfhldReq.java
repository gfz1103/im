   
package com.buit.cis.nurse.request;

import java.sql.Timestamp;

import com.buit.commons.PageQuery;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


/**
 * 类名称：NisRjbfhld<br> 
 * 类描述：日间病房护理记录单<br>
 * @author GONGFANGZHOU
 */
@ApiModel(value="日间病房护理记录单_Req")
public class NisRjbfhldReq  extends  PageQuery{
    @ApiModelProperty(value="主键")
    private Integer jlxh;
    @ApiModelProperty(value="住院号")
    private Integer zyh;
    @ApiModelProperty(value="模板类型")
    private String mblx;
    @ApiModelProperty(value="入院方式")
    private String ryfs;
    @ApiModelProperty(value="入院方式其他")
    private String ryfsqt;
    @ApiModelProperty(value="意识")
    private String ys;
    @ApiModelProperty(value="皮肤情况")
    private String pfqk;
    @ApiModelProperty(value="皮肤情况其他")
    private String pfqkqt;
    @ApiModelProperty(value="资料来源")
    private String zlly;
    @ApiModelProperty(value="资料来源其他")
    private String zllyqt;
    @ApiModelProperty(value="过敏史")
    private String gms;
    @ApiModelProperty(value="过敏史其他")
    private String gmsqt;
    @ApiModelProperty(value="文化程度")
    private String whcd;
    @ApiModelProperty(value="民族")
    private String mz;
    @ApiModelProperty(value="民族其他")
    private String mzqt;
    @ApiModelProperty(value="费用支出")
    private String fyzf;
    @ApiModelProperty(value="费用支付其他")
    private String fyzfqt;
    @ApiModelProperty(value="职业")
    private String zy;
    @ApiModelProperty(value="职业其他")
    private String zyqt;
    @ApiModelProperty(value="宗教信仰")
    private String zjxy;
    @ApiModelProperty(value="宗教信仰其他")
    private String zjxyqt;
    @ApiModelProperty(value="生命体征")
    private String smtz;
    @ApiModelProperty(value="心率")
    private String xl;
    @ApiModelProperty(value="呼吸")
    private String hx;
    @ApiModelProperty(value="收缩压")
    private String ssy;
    @ApiModelProperty(value="舒张压")
    private String szy;
    @ApiModelProperty(value="身高")
    private String sg;
    @ApiModelProperty(value="体重")
    private String tz;
    @ApiModelProperty(value="体重指数BMI")
    private String bmi;
    @ApiModelProperty(value="疼痛评分")
    private String ttpf;
    @ApiModelProperty(value="其他专科评估")
    private String qtzkpf;
    @ApiModelProperty(value="入院宣教")
    private String ryxj;
    @ApiModelProperty(value="术前指导")
    private String sqzd;
    @ApiModelProperty(value="患者今日")
    private String hzjr;
    @ApiModelProperty(value="返回病房")
    private String fhbf;
    @ApiModelProperty(value="神志")
    private String sz;
    @ApiModelProperty(value="护理体温")
    private String hltw;
    @ApiModelProperty(value="护理心率")
    private String hlxl;
    @ApiModelProperty(value="护理呼吸")
    private String hlhx;
    @ApiModelProperty(value="护理收缩压")
    private String hlssy;
    @ApiModelProperty(value="护理舒张压")
    private String hlszy;
    @ApiModelProperty(value="护理疼痛评分")
    private String hlttpf;
    @ApiModelProperty(value="饮食")
    private String ysqk;
    @ApiModelProperty(value="体位")
    private String tw;
    @ApiModelProperty(value="护理意识")
    private String hlys;
    @ApiModelProperty(value="护理皮肤情况")
    private String hlpfqk;
    @ApiModelProperty(value="护理皮肤情况其他")
    private String hlpfqkqt;
    @ApiModelProperty(value="伤口情况")
    private String skqk;
    @ApiModelProperty(value="伤口情况外观")
    private String skqkwg;
    @ApiModelProperty(value="伤口情况其他")
    private String skqkqt;
    @ApiModelProperty(value="置管情况")
    private String zgqk;
    @ApiModelProperty(value="患者转归出院")
    private String hzzg;
    @ApiModelProperty(value="患者转归转科")
    private String hzzgzk;
    @ApiModelProperty(value="术后指导")
    private String sszd;
    @ApiModelProperty(value="术后指导其他")
    private String sszdqt;
    @ApiModelProperty(value="出院指导")
    private String cyzd;
    @ApiModelProperty(value="出院指导其他")
    private String cyzdzt;
    @ApiModelProperty(value="护士签名")
    private String hsqm;
    @ApiModelProperty(value="护士签名日期")
    private Timestamp hsqmrq;
    @ApiModelProperty(value="医师签名")
    private String ysqm;
    @ApiModelProperty(value="医师签名日期")
    private Timestamp ysqmrq;
    @ApiModelProperty(value="日期")
    private Timestamp rq;
    @ApiModelProperty(value="机构id", hidden = true)
    private Integer jgid;
    /**
     * 设置:主键
     */
    public void setJlxh(Integer value) {
        this.jlxh = value;
    }
    /**
     * 获取:主键
     */
    public Integer getJlxh() {
        return jlxh;
    }
    /**
     * 设置:住院号
     */
    public void setZyh(Integer value) {
        this.zyh = value;
    }
    /**
     * 获取:住院号
     */
    public Integer getZyh() {
        return zyh;
    }
    /**
     * 设置:模板类型
     */
    public void setMblx(String value) {
        this.mblx = value;
    }
    /**
     * 获取:模板类型
     */
    public String getMblx() {
        return mblx;
    }
    /**
     * 设置:入院方式
     */
    public void setRyfs(String value) {
        this.ryfs = value;
    }
    /**
     * 获取:入院方式
     */
    public String getRyfs() {
        return ryfs;
    }
    /**
     * 设置:入院方式其他
     */
    public void setRyfsqt(String value) {
        this.ryfsqt = value;
    }
    /**
     * 获取:入院方式其他
     */
    public String getRyfsqt() {
        return ryfsqt;
    }
    /**
     * 设置:意识
     */
    public void setYs(String value) {
        this.ys = value;
    }
    /**
     * 获取:意识
     */
    public String getYs() {
        return ys;
    }
    /**
     * 设置:皮肤情况
     */
    public void setPfqk(String value) {
        this.pfqk = value;
    }
    /**
     * 获取:皮肤情况
     */
    public String getPfqk() {
        return pfqk;
    }
    /**
     * 设置:皮肤情况其他
     */
    public void setPfqkqt(String value) {
        this.pfqkqt = value;
    }
    /**
     * 获取:皮肤情况其他
     */
    public String getPfqkqt() {
        return pfqkqt;
    }
    /**
     * 设置:资料来源
     */
    public void setZlly(String value) {
        this.zlly = value;
    }
    /**
     * 获取:资料来源
     */
    public String getZlly() {
        return zlly;
    }
    /**
     * 设置:资料来源其他
     */
    public void setZllyqt(String value) {
        this.zllyqt = value;
    }
    /**
     * 获取:资料来源其他
     */
    public String getZllyqt() {
        return zllyqt;
    }
    /**
     * 设置:过敏史
     */
    public void setGms(String value) {
        this.gms = value;
    }
    /**
     * 获取:过敏史
     */
    public String getGms() {
        return gms;
    }
    /**
     * 设置:过敏史其他
     */
    public void setGmsqt(String value) {
        this.gmsqt = value;
    }
    /**
     * 获取:过敏史其他
     */
    public String getGmsqt() {
        return gmsqt;
    }
    /**
     * 设置:文化程度
     */
    public void setWhcd(String value) {
        this.whcd = value;
    }
    /**
     * 获取:文化程度
     */
    public String getWhcd() {
        return whcd;
    }
    /**
     * 设置:民族
     */
    public void setMz(String value) {
        this.mz = value;
    }
    /**
     * 获取:民族
     */
    public String getMz() {
        return mz;
    }
    /**
     * 设置:民族其他
     */
    public void setMzqt(String value) {
        this.mzqt = value;
    }
    /**
     * 获取:民族其他
     */
    public String getMzqt() {
        return mzqt;
    }
    /**
     * 设置:费用支出
     */
    public void setFyzf(String value) {
        this.fyzf = value;
    }
    /**
     * 获取:费用支出
     */
    public String getFyzf() {
        return fyzf;
    }
    /**
     * 设置:费用支付其他
     */
    public void setFyzfqt(String value) {
        this.fyzfqt = value;
    }
    /**
     * 获取:费用支付其他
     */
    public String getFyzfqt() {
        return fyzfqt;
    }
    /**
     * 设置:职业
     */
    public void setZy(String value) {
        this.zy = value;
    }
    /**
     * 获取:职业
     */
    public String getZy() {
        return zy;
    }
    /**
     * 设置:职业其他
     */
    public void setZyqt(String value) {
        this.zyqt = value;
    }
    /**
     * 获取:职业其他
     */
    public String getZyqt() {
        return zyqt;
    }
    /**
     * 设置:宗教信仰
     */
    public void setZjxy(String value) {
        this.zjxy = value;
    }
    /**
     * 获取:宗教信仰
     */
    public String getZjxy() {
        return zjxy;
    }
    /**
     * 设置:宗教信仰其他
     */
    public void setZjxyqt(String value) {
        this.zjxyqt = value;
    }
    /**
     * 获取:宗教信仰其他
     */
    public String getZjxyqt() {
        return zjxyqt;
    }
    /**
     * 设置:生命体征
     */
    public void setSmtz(String value) {
        this.smtz = value;
    }
    /**
     * 获取:生命体征
     */
    public String getSmtz() {
        return smtz;
    }
    /**
     * 设置:心率
     */
    public void setXl(String value) {
        this.xl = value;
    }
    /**
     * 获取:心率
     */
    public String getXl() {
        return xl;
    }
    /**
     * 设置:呼吸
     */
    public void setHx(String value) {
        this.hx = value;
    }
    /**
     * 获取:呼吸
     */
    public String getHx() {
        return hx;
    }
    /**
     * 设置:收缩压
     */
    public void setSsy(String value) {
        this.ssy = value;
    }
    /**
     * 获取:收缩压
     */
    public String getSsy() {
        return ssy;
    }
    /**
     * 设置:舒张压
     */
    public void setSzy(String value) {
        this.szy = value;
    }
    /**
     * 获取:舒张压
     */
    public String getSzy() {
        return szy;
    }
    /**
     * 设置:身高
     */
    public void setSg(String value) {
        this.sg = value;
    }
    /**
     * 获取:身高
     */
    public String getSg() {
        return sg;
    }
    /**
     * 设置:体重
     */
    public void setTz(String value) {
        this.tz = value;
    }
    /**
     * 获取:体重
     */
    public String getTz() {
        return tz;
    }
    /**
     * 设置:体重指数BMI
     */
    public void setBmi(String value) {
        this.bmi = value;
    }
    /**
     * 获取:体重指数BMI
     */
    public String getBmi() {
        return bmi;
    }
    /**
     * 设置:疼痛评分
     */
    public void setTtpf(String value) {
        this.ttpf = value;
    }
    /**
     * 获取:疼痛评分
     */
    public String getTtpf() {
        return ttpf;
    }
    /**
     * 设置:其他专科评估
     */
    public void setQtzkpf(String value) {
        this.qtzkpf = value;
    }
    /**
     * 获取:其他专科评估
     */
    public String getQtzkpf() {
        return qtzkpf;
    }
    /**
     * 设置:入院宣教
     */
    public void setRyxj(String value) {
        this.ryxj = value;
    }
    /**
     * 获取:入院宣教
     */
    public String getRyxj() {
        return ryxj;
    }
    /**
     * 设置:术前指导
     */
    public void setSqzd(String value) {
        this.sqzd = value;
    }
    /**
     * 获取:术前指导
     */
    public String getSqzd() {
        return sqzd;
    }
    /**
     * 设置:患者今日
     */
    public void setHzjr(String value) {
        this.hzjr = value;
    }
    /**
     * 获取:患者今日
     */
    public String getHzjr() {
        return hzjr;
    }
    /**
     * 设置:返回病房
     */
    public void setFhbf(String value) {
        this.fhbf = value;
    }
    /**
     * 获取:返回病房
     */
    public String getFhbf() {
        return fhbf;
    }
    /**
     * 设置:神志
     */
    public void setSz(String value) {
        this.sz = value;
    }
    /**
     * 获取:神志
     */
    public String getSz() {
        return sz;
    }
    /**
     * 设置:护理体温
     */
    public void setHltw(String value) {
        this.hltw = value;
    }
    /**
     * 获取:护理体温
     */
    public String getHltw() {
        return hltw;
    }
    /**
     * 设置:护理心率
     */
    public void setHlxl(String value) {
        this.hlxl = value;
    }
    /**
     * 获取:护理心率
     */
    public String getHlxl() {
        return hlxl;
    }
    /**
     * 设置:护理呼吸
     */
    public void setHlhx(String value) {
        this.hlhx = value;
    }
    /**
     * 获取:护理呼吸
     */
    public String getHlhx() {
        return hlhx;
    }
    /**
     * 设置:护理收缩压
     */
    public void setHlssy(String value) {
        this.hlssy = value;
    }
    /**
     * 获取:护理收缩压
     */
    public String getHlssy() {
        return hlssy;
    }
    /**
     * 设置:护理舒张压
     */
    public void setHlszy(String value) {
        this.hlszy = value;
    }
    /**
     * 获取:护理舒张压
     */
    public String getHlszy() {
        return hlszy;
    }
    /**
     * 设置:护理疼痛评分
     */
    public void setHlttpf(String value) {
        this.hlttpf = value;
    }
    /**
     * 获取:护理疼痛评分
     */
    public String getHlttpf() {
        return hlttpf;
    }
    /**
     * 设置:饮食
     */
    public void setYsqk(String value) {
        this.ysqk = value;
    }
    /**
     * 获取:饮食
     */
    public String getYsqk() {
        return ysqk;
    }
    /**
     * 设置:体位
     */
    public void setTw(String value) {
        this.tw = value;
    }
    /**
     * 获取:体位
     */
    public String getTw() {
        return tw;
    }
    /**
     * 设置:护理意识
     */
    public void setHlys(String value) {
        this.hlys = value;
    }
    /**
     * 获取:护理意识
     */
    public String getHlys() {
        return hlys;
    }
    /**
     * 设置:护理皮肤情况
     */
    public void setHlpfqk(String value) {
        this.hlpfqk = value;
    }
    /**
     * 获取:护理皮肤情况
     */
    public String getHlpfqk() {
        return hlpfqk;
    }
    /**
     * 设置:护理皮肤情况其他
     */
    public void setHlpfqkqt(String value) {
        this.hlpfqkqt = value;
    }
    /**
     * 获取:护理皮肤情况其他
     */
    public String getHlpfqkqt() {
        return hlpfqkqt;
    }
    /**
     * 设置:伤口情况
     */
    public void setSkqk(String value) {
        this.skqk = value;
    }
    /**
     * 获取:伤口情况
     */
    public String getSkqk() {
        return skqk;
    }
    /**
     * 设置:伤口情况外观
     */
    public void setSkqkwg(String value) {
        this.skqkwg = value;
    }
    /**
     * 获取:伤口情况外观
     */
    public String getSkqkwg() {
        return skqkwg;
    }
    /**
     * 设置:伤口情况其他
     */
    public void setSkqkqt(String value) {
        this.skqkqt = value;
    }
    /**
     * 获取:伤口情况其他
     */
    public String getSkqkqt() {
        return skqkqt;
    }
    /**
     * 设置:置管情况
     */
    public void setZgqk(String value) {
        this.zgqk = value;
    }
    /**
     * 获取:置管情况
     */
    public String getZgqk() {
        return zgqk;
    }
    /**
     * 设置:患者转归出院
     */
    public void setHzzg(String value) {
        this.hzzg = value;
    }
    /**
     * 获取:患者转归出院
     */
    public String getHzzg() {
        return hzzg;
    }
    /**
     * 设置:患者转归转科
     */
    public void setHzzgzk(String value) {
        this.hzzgzk = value;
    }
    /**
     * 获取:患者转归转科
     */
    public String getHzzgzk() {
        return hzzgzk;
    }
    /**
     * 设置:术后指导
     */
    public void setSszd(String value) {
        this.sszd = value;
    }
    /**
     * 获取:术后指导
     */
    public String getSszd() {
        return sszd;
    }
    /**
     * 设置:术后指导其他
     */
    public void setSszdqt(String value) {
        this.sszdqt = value;
    }
    /**
     * 获取:术后指导其他
     */
    public String getSszdqt() {
        return sszdqt;
    }
    /**
     * 设置:出院指导
     */
    public void setCyzd(String value) {
        this.cyzd = value;
    }
    /**
     * 获取:出院指导
     */
    public String getCyzd() {
        return cyzd;
    }
    /**
     * 设置:出院指导其他
     */
    public void setCyzdzt(String value) {
        this.cyzdzt = value;
    }
    /**
     * 获取:出院指导其他
     */
    public String getCyzdzt() {
        return cyzdzt;
    }
    /**
     * 设置:护士签名
     */
    public void setHsqm(String value) {
        this.hsqm = value;
    }
    /**
     * 获取:护士签名
     */
    public String getHsqm() {
        return hsqm;
    }
    /**
     * 设置:护士签名日期
     */
    public void setHsqmrq(Timestamp value) {
        this.hsqmrq = value;
    }
    /**
     * 获取:护士签名日期
     */
    public Timestamp getHsqmrq() {
        return hsqmrq;
    }
    /**
     * 设置:医师签名
     */
    public void setYsqm(String value) {
        this.ysqm = value;
    }
    /**
     * 获取:医师签名
     */
    public String getYsqm() {
        return ysqm;
    }
    /**
     * 设置:医师签名日期
     */
    public void setYsqmrq(Timestamp value) {
        this.ysqmrq = value;
    }
    /**
     * 获取:医师签名日期
     */
    public Timestamp getYsqmrq() {
        return ysqmrq;
    }
    /**
     * 设置:日期
     */
    public void setRq(Timestamp value) {
        this.rq = value;
    }
    /**
     * 获取:日期
     */
    public Timestamp getRq() {
        return rq;
    }
    /**
     * 设置:机构id
     */
    public void setJgid(Integer value) {
        this.jgid = value;
    }
    /**
     * 获取:机构id
     */
    public Integer getJgid() {
        return jgid;
    }
}