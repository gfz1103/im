//   
//package com.buit.cis.nurse.response;
//
//import java.sql.Timestamp;
//
//import com.buit.commons.PageQuery;
//import io.swagger.annotations.ApiModel;
//import io.swagger.annotations.ApiModelProperty;
//
//
///**
// * 类名称：NisRyhld<br> 
// * 类描述：入院护理评估单<br>
// * @author GONGFANGZHOU
// */
//@ApiModel(value="入院护理评估单")
//public class NisRyhldResp  extends  PageQuery{
//    @ApiModelProperty(value="主键")
//    private Integer jlxh;
//    @ApiModelProperty(value="住院号")
//    private Integer zyh;
//    @ApiModelProperty(value="模板类型")
//    private String mblx;
//    @ApiModelProperty(value="就诊方式")
//    private String jzfs;
//    @ApiModelProperty(value="入院方式")
//    private String ryfs;
//    @ApiModelProperty(value="资料来源")
//    private String zlly;
//    @ApiModelProperty(value="过敏史")
//    private String gms;
//    @ApiModelProperty(value="过敏史药物")
//    private String gmyw;
//    @ApiModelProperty(value="文化程度")
//    private String whcd;
//    @ApiModelProperty(value="民族")
//    private String mz;
//    @ApiModelProperty(value="费用支出")
//    private String fyzf;
//    @ApiModelProperty(value="职业")
//    private String zy;
//    @ApiModelProperty(value="居住")
//    private String jz;
//    @ApiModelProperty(value="宗教信仰")
//    private String zjxy;
//    @ApiModelProperty(value="生命体征")
//    private String smtz;
//    @ApiModelProperty(value="心率")
//    private String xl;
//    @ApiModelProperty(value="呼吸")
//    private String hx;
//    @ApiModelProperty(value="血压")
//    private String xy;
//    @ApiModelProperty(value="疼痛评分")
//    private String ttpf;
//    @ApiModelProperty(value="评估工具")
//    private String pggj;
//    @ApiModelProperty(value="视力")
//    private String sl;
//    @ApiModelProperty(value="视力其他")
//    private String slqt;
//    @ApiModelProperty(value="听力")
//    private String tl;
//    @ApiModelProperty(value="听力其他")
//    private String tlqt;
//    @ApiModelProperty(value="口腔情况")
//    private String kqqk;
//    @ApiModelProperty(value="口腔情况其他")
//    private String kqqkqt;
//    @ApiModelProperty(value="语言")
//    private String yy;
//    @ApiModelProperty(value="语言其他")
//    private String yyqt;
//    @ApiModelProperty(value="皮肤")
//    private String pf;
//    @ApiModelProperty(value="皮肤其他")
//    private String pfqt;
//    @ApiModelProperty(value="导管")
//    private String dg;
//    @ApiModelProperty(value="导管其他")
//    private String dgqt;
//    @ApiModelProperty(value="基本膳食")
//    private String jbss;
//    @ApiModelProperty(value="禁忌")
//    private String jj;
//    @ApiModelProperty(value="偏好")
//    private String ph;
//    @ApiModelProperty(value="治疗饮食")
//    private String zlys;
//    @ApiModelProperty(value="食欲")
//    private String sy;
//    @ApiModelProperty(value="食欲其他")
//    private String syqt;
//    @ApiModelProperty(value="睡眠")
//    private String sm;
//    @ApiModelProperty(value="睡眠其他")
//    private String smqt;
//    @ApiModelProperty(value="排便")
//    private String pb;
//    @ApiModelProperty(value="排便其他")
//    private String pbqt;
//    @ApiModelProperty(value="排尿")
//    private String pn;
//    @ApiModelProperty(value="排尿其他")
//    private String pnqt;
//    @ApiModelProperty(value="嗜好")
//    private String sh;
//    @ApiModelProperty(value="嗜好烟")
//    private String shy;
//    @ApiModelProperty(value="嗜好酒")
//    private String shj;
//    @ApiModelProperty(value="情绪状态")
//    private String qxzt;
//    @ApiModelProperty(value="情绪状态其他")
//    private String qxztqt;
//    @ApiModelProperty(value="疾病认知")
//    private String jbrz;
//    @ApiModelProperty(value="住院顾虑")
//    private String zygl;
//    @ApiModelProperty(value="住院顾虑其他")
//    private String zyglqt;
//    @ApiModelProperty(value="婚姻状态")
//    private String hyzt;
//    @ApiModelProperty(value="身高")
//    private String sg;
//    @ApiModelProperty(value="体重")
//    private String tz;
//    @ApiModelProperty(value="体重指数")
//    private String tzzs;
//    @ApiModelProperty(value="BMI")
//    private String bmi;
//    @ApiModelProperty(value="体重丢失")
//    private String tzds;
//    @ApiModelProperty(value="膳食摄入")
//    private String sssr;
//    @ApiModelProperty(value="教育对象")
//    private String jydx;
//    @ApiModelProperty(value="学习意愿")
//    private String xxyy;
//    @ApiModelProperty(value="患者语种")
//    private String hzyz;
//    @ApiModelProperty(value="方言")
//    private String fy;
//    @ApiModelProperty(value="外语")
//    private String wy;
//    @ApiModelProperty(value="学习能力")
//    private String xxnl;
//    @ApiModelProperty(value="学习方式")
//    private String xxfs;
//    @ApiModelProperty(value="学习方式其他")
//    private String xxfsqt;
//    @ApiModelProperty(value="特殊需求")
//    private String tsxq;
//    @ApiModelProperty(value="特殊人群")
//    private String tsrq;
//    @ApiModelProperty(value="出院去向")
//    private String cyqx;
//    @ApiModelProperty(value="出院去向其他")
//    private String cyqxqt;
//    @ApiModelProperty(value="出院后照顾者")
//    private String cyhzgz;
//    @ApiModelProperty(value="交通工具需求")
//    private String jtgjxq;
//    @ApiModelProperty(value="交通工具需求其他")
//    private String jtgjxqqt;
//    @ApiModelProperty(value="护士签名")
//    private String hsqm;
//    @ApiModelProperty(value="护士签名日期")
//    private Timestamp hsqmrq;
//    @ApiModelProperty(value="护士长签名")
//    private String hszqm;
//    @ApiModelProperty(value="护士长签名日期")
//    private Timestamp hszqmrq;
//    @ApiModelProperty(value="医师签名")
//    private String ysqm;
//    @ApiModelProperty(value="医师签名日期")
//    private Timestamp ysqmrq;
//    @ApiModelProperty(value="日期")
//    private Timestamp rq;
//    @ApiModelProperty(value="机构id")
//    private Integer jgid;
//    /**
//     * 设置:主键
//     */
//    public void setJlxh(Integer value) {
//        this.jlxh = value;
//    }
//    /**
//     * 获取:主键
//     */
//    public Integer getJlxh() {
//        return jlxh;
//    }
//    /**
//     * 设置:住院号
//     */
//    public void setZyh(Integer value) {
//        this.zyh = value;
//    }
//    /**
//     * 获取:住院号
//     */
//    public Integer getZyh() {
//        return zyh;
//    }
//    /**
//     * 设置:模板类型
//     */
//    public void setMblx(String value) {
//        this.mblx = value;
//    }
//    /**
//     * 获取:模板类型
//     */
//    public String getMblx() {
//        return mblx;
//    }
//    /**
//     * 设置:就诊方式
//     */
//    public void setJzfs(String value) {
//        this.jzfs = value;
//    }
//    /**
//     * 获取:就诊方式
//     */
//    public String getJzfs() {
//        return jzfs;
//    }
//    /**
//     * 设置:入院方式
//     */
//    public void setRyfs(String value) {
//        this.ryfs = value;
//    }
//    /**
//     * 获取:入院方式
//     */
//    public String getRyfs() {
//        return ryfs;
//    }
//    /**
//     * 设置:资料来源
//     */
//    public void setZlly(String value) {
//        this.zlly = value;
//    }
//    /**
//     * 获取:资料来源
//     */
//    public String getZlly() {
//        return zlly;
//    }
//    /**
//     * 设置:过敏史
//     */
//    public void setGms(String value) {
//        this.gms = value;
//    }
//    /**
//     * 获取:过敏史
//     */
//    public String getGms() {
//        return gms;
//    }
//    /**
//     * 设置:过敏史药物
//     */
//    public void setGmyw(String value) {
//        this.gmyw = value;
//    }
//    /**
//     * 获取:过敏史药物
//     */
//    public String getGmyw() {
//        return gmyw;
//    }
//    /**
//     * 设置:文化程度
//     */
//    public void setWhcd(String value) {
//        this.whcd = value;
//    }
//    /**
//     * 获取:文化程度
//     */
//    public String getWhcd() {
//        return whcd;
//    }
//    /**
//     * 设置:民族
//     */
//    public void setMz(String value) {
//        this.mz = value;
//    }
//    /**
//     * 获取:民族
//     */
//    public String getMz() {
//        return mz;
//    }
//    /**
//     * 设置:费用支出
//     */
//    public void setFyzf(String value) {
//        this.fyzf = value;
//    }
//    /**
//     * 获取:费用支出
//     */
//    public String getFyzf() {
//        return fyzf;
//    }
//    /**
//     * 设置:职业
//     */
//    public void setZy(String value) {
//        this.zy = value;
//    }
//    /**
//     * 获取:职业
//     */
//    public String getZy() {
//        return zy;
//    }
//    /**
//     * 设置:居住
//     */
//    public void setJz(String value) {
//        this.jz = value;
//    }
//    /**
//     * 获取:居住
//     */
//    public String getJz() {
//        return jz;
//    }
//    /**
//     * 设置:宗教信仰
//     */
//    public void setZjxy(String value) {
//        this.zjxy = value;
//    }
//    /**
//     * 获取:宗教信仰
//     */
//    public String getZjxy() {
//        return zjxy;
//    }
//    /**
//     * 设置:生命体征
//     */
//    public void setSmtz(String value) {
//        this.smtz = value;
//    }
//    /**
//     * 获取:生命体征
//     */
//    public String getSmtz() {
//        return smtz;
//    }
//    /**
//     * 设置:心率
//     */
//    public void setXl(String value) {
//        this.xl = value;
//    }
//    /**
//     * 获取:心率
//     */
//    public String getXl() {
//        return xl;
//    }
//    /**
//     * 设置:呼吸
//     */
//    public void setHx(String value) {
//        this.hx = value;
//    }
//    /**
//     * 获取:呼吸
//     */
//    public String getHx() {
//        return hx;
//    }
//    /**
//     * 设置:血压
//     */
//    public void setXy(String value) {
//        this.xy = value;
//    }
//    /**
//     * 获取:血压
//     */
//    public String getXy() {
//        return xy;
//    }
//    /**
//     * 设置:疼痛评分
//     */
//    public void setTtpf(String value) {
//        this.ttpf = value;
//    }
//    /**
//     * 获取:疼痛评分
//     */
//    public String getTtpf() {
//        return ttpf;
//    }
//    /**
//     * 设置:评估工具
//     */
//    public void setPggj(String value) {
//        this.pggj = value;
//    }
//    /**
//     * 获取:评估工具
//     */
//    public String getPggj() {
//        return pggj;
//    }
//    /**
//     * 设置:视力
//     */
//    public void setSl(String value) {
//        this.sl = value;
//    }
//    /**
//     * 获取:视力
//     */
//    public String getSl() {
//        return sl;
//    }
//    /**
//     * 设置:视力其他
//     */
//    public void setSlqt(String value) {
//        this.slqt = value;
//    }
//    /**
//     * 获取:视力其他
//     */
//    public String getSlqt() {
//        return slqt;
//    }
//    /**
//     * 设置:听力
//     */
//    public void setTl(String value) {
//        this.tl = value;
//    }
//    /**
//     * 获取:听力
//     */
//    public String getTl() {
//        return tl;
//    }
//    /**
//     * 设置:听力其他
//     */
//    public void setTlqt(String value) {
//        this.tlqt = value;
//    }
//    /**
//     * 获取:听力其他
//     */
//    public String getTlqt() {
//        return tlqt;
//    }
//    /**
//     * 设置:口腔情况
//     */
//    public void setKqqk(String value) {
//        this.kqqk = value;
//    }
//    /**
//     * 获取:口腔情况
//     */
//    public String getKqqk() {
//        return kqqk;
//    }
//    /**
//     * 设置:口腔情况其他
//     */
//    public void setKqqkqt(String value) {
//        this.kqqkqt = value;
//    }
//    /**
//     * 获取:口腔情况其他
//     */
//    public String getKqqkqt() {
//        return kqqkqt;
//    }
//    /**
//     * 设置:语言
//     */
//    public void setYy(String value) {
//        this.yy = value;
//    }
//    /**
//     * 获取:语言
//     */
//    public String getYy() {
//        return yy;
//    }
//    /**
//     * 设置:语言其他
//     */
//    public void setYyqt(String value) {
//        this.yyqt = value;
//    }
//    /**
//     * 获取:语言其他
//     */
//    public String getYyqt() {
//        return yyqt;
//    }
//    /**
//     * 设置:皮肤
//     */
//    public void setPf(String value) {
//        this.pf = value;
//    }
//    /**
//     * 获取:皮肤
//     */
//    public String getPf() {
//        return pf;
//    }
//    /**
//     * 设置:皮肤其他
//     */
//    public void setPfqt(String value) {
//        this.pfqt = value;
//    }
//    /**
//     * 获取:皮肤其他
//     */
//    public String getPfqt() {
//        return pfqt;
//    }
//    /**
//     * 设置:导管
//     */
//    public void setDg(String value) {
//        this.dg = value;
//    }
//    /**
//     * 获取:导管
//     */
//    public String getDg() {
//        return dg;
//    }
//    /**
//     * 设置:导管其他
//     */
//    public void setDgqt(String value) {
//        this.dgqt = value;
//    }
//    /**
//     * 获取:导管其他
//     */
//    public String getDgqt() {
//        return dgqt;
//    }
//    /**
//     * 设置:基本膳食
//     */
//    public void setJbss(String value) {
//        this.jbss = value;
//    }
//    /**
//     * 获取:基本膳食
//     */
//    public String getJbss() {
//        return jbss;
//    }
//    /**
//     * 设置:禁忌
//     */
//    public void setJj(String value) {
//        this.jj = value;
//    }
//    /**
//     * 获取:禁忌
//     */
//    public String getJj() {
//        return jj;
//    }
//    /**
//     * 设置:偏好
//     */
//    public void setPh(String value) {
//        this.ph = value;
//    }
//    /**
//     * 获取:偏好
//     */
//    public String getPh() {
//        return ph;
//    }
//    /**
//     * 设置:治疗饮食
//     */
//    public void setZlys(String value) {
//        this.zlys = value;
//    }
//    /**
//     * 获取:治疗饮食
//     */
//    public String getZlys() {
//        return zlys;
//    }
//    /**
//     * 设置:食欲
//     */
//    public void setSy(String value) {
//        this.sy = value;
//    }
//    /**
//     * 获取:食欲
//     */
//    public String getSy() {
//        return sy;
//    }
//    /**
//     * 设置:食欲其他
//     */
//    public void setSyqt(String value) {
//        this.syqt = value;
//    }
//    /**
//     * 获取:食欲其他
//     */
//    public String getSyqt() {
//        return syqt;
//    }
//    /**
//     * 设置:睡眠
//     */
//    public void setSm(String value) {
//        this.sm = value;
//    }
//    /**
//     * 获取:睡眠
//     */
//    public String getSm() {
//        return sm;
//    }
//    /**
//     * 设置:睡眠其他
//     */
//    public void setSmqt(String value) {
//        this.smqt = value;
//    }
//    /**
//     * 获取:睡眠其他
//     */
//    public String getSmqt() {
//        return smqt;
//    }
//    /**
//     * 设置:排便
//     */
//    public void setPb(String value) {
//        this.pb = value;
//    }
//    /**
//     * 获取:排便
//     */
//    public String getPb() {
//        return pb;
//    }
//    /**
//     * 设置:排便其他
//     */
//    public void setPbqt(String value) {
//        this.pbqt = value;
//    }
//    /**
//     * 获取:排便其他
//     */
//    public String getPbqt() {
//        return pbqt;
//    }
//    /**
//     * 设置:排尿
//     */
//    public void setPn(String value) {
//        this.pn = value;
//    }
//    /**
//     * 获取:排尿
//     */
//    public String getPn() {
//        return pn;
//    }
//    /**
//     * 设置:排尿其他
//     */
//    public void setPnqt(String value) {
//        this.pnqt = value;
//    }
//    /**
//     * 获取:排尿其他
//     */
//    public String getPnqt() {
//        return pnqt;
//    }
//    /**
//     * 设置:嗜好
//     */
//    public void setSh(String value) {
//        this.sh = value;
//    }
//    /**
//     * 获取:嗜好
//     */
//    public String getSh() {
//        return sh;
//    }
//    /**
//     * 设置:嗜好烟
//     */
//    public void setShy(String value) {
//        this.shy = value;
//    }
//    /**
//     * 获取:嗜好烟
//     */
//    public String getShy() {
//        return shy;
//    }
//    /**
//     * 设置:嗜好酒
//     */
//    public void setShj(String value) {
//        this.shj = value;
//    }
//    /**
//     * 获取:嗜好酒
//     */
//    public String getShj() {
//        return shj;
//    }
//    /**
//     * 设置:情绪状态
//     */
//    public void setQxzt(String value) {
//        this.qxzt = value;
//    }
//    /**
//     * 获取:情绪状态
//     */
//    public String getQxzt() {
//        return qxzt;
//    }
//    /**
//     * 设置:情绪状态其他
//     */
//    public void setQxztqt(String value) {
//        this.qxztqt = value;
//    }
//    /**
//     * 获取:情绪状态其他
//     */
//    public String getQxztqt() {
//        return qxztqt;
//    }
//    /**
//     * 设置:疾病认知
//     */
//    public void setJbrz(String value) {
//        this.jbrz = value;
//    }
//    /**
//     * 获取:疾病认知
//     */
//    public String getJbrz() {
//        return jbrz;
//    }
//    /**
//     * 设置:住院顾虑
//     */
//    public void setZygl(String value) {
//        this.zygl = value;
//    }
//    /**
//     * 获取:住院顾虑
//     */
//    public String getZygl() {
//        return zygl;
//    }
//    /**
//     * 设置:住院顾虑其他
//     */
//    public void setZyglqt(String value) {
//        this.zyglqt = value;
//    }
//    /**
//     * 获取:住院顾虑其他
//     */
//    public String getZyglqt() {
//        return zyglqt;
//    }
//    /**
//     * 设置:婚姻状态
//     */
//    public void setHyzt(String value) {
//        this.hyzt = value;
//    }
//    /**
//     * 获取:婚姻状态
//     */
//    public String getHyzt() {
//        return hyzt;
//    }
//    /**
//     * 设置:身高
//     */
//    public void setSg(String value) {
//        this.sg = value;
//    }
//    /**
//     * 获取:身高
//     */
//    public String getSg() {
//        return sg;
//    }
//    /**
//     * 设置:体重
//     */
//    public void setTz(String value) {
//        this.tz = value;
//    }
//    /**
//     * 获取:体重
//     */
//    public String getTz() {
//        return tz;
//    }
//    /**
//     * 设置:体重指数
//     */
//    public void setTzzs(String value) {
//        this.tzzs = value;
//    }
//    /**
//     * 获取:体重指数
//     */
//    public String getTzzs() {
//        return tzzs;
//    }
//    /**
//     * 设置:BMI
//     */
//    public void setBmi(String value) {
//        this.bmi = value;
//    }
//    /**
//     * 获取:BMI
//     */
//    public String getBmi() {
//        return bmi;
//    }
//    /**
//     * 设置:体重丢失
//     */
//    public void setTzds(String value) {
//        this.tzds = value;
//    }
//    /**
//     * 获取:体重丢失
//     */
//    public String getTzds() {
//        return tzds;
//    }
//    /**
//     * 设置:膳食摄入
//     */
//    public void setSssr(String value) {
//        this.sssr = value;
//    }
//    /**
//     * 获取:膳食摄入
//     */
//    public String getSssr() {
//        return sssr;
//    }
//    /**
//     * 设置:教育对象
//     */
//    public void setJydx(String value) {
//        this.jydx = value;
//    }
//    /**
//     * 获取:教育对象
//     */
//    public String getJydx() {
//        return jydx;
//    }
//    /**
//     * 设置:学习意愿
//     */
//    public void setXxyy(String value) {
//        this.xxyy = value;
//    }
//    /**
//     * 获取:学习意愿
//     */
//    public String getXxyy() {
//        return xxyy;
//    }
//    /**
//     * 设置:患者语种
//     */
//    public void setHzyz(String value) {
//        this.hzyz = value;
//    }
//    /**
//     * 获取:患者语种
//     */
//    public String getHzyz() {
//        return hzyz;
//    }
//    /**
//     * 设置:方言
//     */
//    public void setFy(String value) {
//        this.fy = value;
//    }
//    /**
//     * 获取:方言
//     */
//    public String getFy() {
//        return fy;
//    }
//    /**
//     * 设置:外语
//     */
//    public void setWy(String value) {
//        this.wy = value;
//    }
//    /**
//     * 获取:外语
//     */
//    public String getWy() {
//        return wy;
//    }
//    /**
//     * 设置:学习能力
//     */
//    public void setXxnl(String value) {
//        this.xxnl = value;
//    }
//    /**
//     * 获取:学习能力
//     */
//    public String getXxnl() {
//        return xxnl;
//    }
//    /**
//     * 设置:学习方式
//     */
//    public void setXxfs(String value) {
//        this.xxfs = value;
//    }
//    /**
//     * 获取:学习方式
//     */
//    public String getXxfs() {
//        return xxfs;
//    }
//    /**
//     * 设置:学习方式其他
//     */
//    public void setXxfsqt(String value) {
//        this.xxfsqt = value;
//    }
//    /**
//     * 获取:学习方式其他
//     */
//    public String getXxfsqt() {
//        return xxfsqt;
//    }
//    /**
//     * 设置:特殊需求
//     */
//    public void setTsxq(String value) {
//        this.tsxq = value;
//    }
//    /**
//     * 获取:特殊需求
//     */
//    public String getTsxq() {
//        return tsxq;
//    }
//    /**
//     * 设置:特殊人群
//     */
//    public void setTsrq(String value) {
//        this.tsrq = value;
//    }
//    /**
//     * 获取:特殊人群
//     */
//    public String getTsrq() {
//        return tsrq;
//    }
//    /**
//     * 设置:出院去向
//     */
//    public void setCyqx(String value) {
//        this.cyqx = value;
//    }
//    /**
//     * 获取:出院去向
//     */
//    public String getCyqx() {
//        return cyqx;
//    }
//    /**
//     * 设置:出院去向其他
//     */
//    public void setCyqxqt(String value) {
//        this.cyqxqt = value;
//    }
//    /**
//     * 获取:出院去向其他
//     */
//    public String getCyqxqt() {
//        return cyqxqt;
//    }
//    /**
//     * 设置:出院后照顾者
//     */
//    public void setCyhzgz(String value) {
//        this.cyhzgz = value;
//    }
//    /**
//     * 获取:出院后照顾者
//     */
//    public String getCyhzgz() {
//        return cyhzgz;
//    }
//    /**
//     * 设置:交通工具需求
//     */
//    public void setJtgjxq(String value) {
//        this.jtgjxq = value;
//    }
//    /**
//     * 获取:交通工具需求
//     */
//    public String getJtgjxq() {
//        return jtgjxq;
//    }
//    /**
//     * 设置:交通工具需求其他
//     */
//    public void setJtgjxqqt(String value) {
//        this.jtgjxqqt = value;
//    }
//    /**
//     * 获取:交通工具需求其他
//     */
//    public String getJtgjxqqt() {
//        return jtgjxqqt;
//    }
//    /**
//     * 设置:护士签名
//     */
//    public void setHsqm(String value) {
//        this.hsqm = value;
//    }
//    /**
//     * 获取:护士签名
//     */
//    public String getHsqm() {
//        return hsqm;
//    }
//    /**
//     * 设置:护士签名日期
//     */
//    public void setHsqmrq(Timestamp value) {
//        this.hsqmrq = value;
//    }
//    /**
//     * 获取:护士签名日期
//     */
//    public Timestamp getHsqmrq() {
//        return hsqmrq;
//    }
//    /**
//     * 设置:护士长签名
//     */
//    public void setHszqm(String value) {
//        this.hszqm = value;
//    }
//    /**
//     * 获取:护士长签名
//     */
//    public String getHszqm() {
//        return hszqm;
//    }
//    /**
//     * 设置:护士长签名日期
//     */
//    public void setHszqmrq(Timestamp value) {
//        this.hszqmrq = value;
//    }
//    /**
//     * 获取:护士长签名日期
//     */
//    public Timestamp getHszqmrq() {
//        return hszqmrq;
//    }
//    /**
//     * 设置:医师签名
//     */
//    public void setYsqm(String value) {
//        this.ysqm = value;
//    }
//    /**
//     * 获取:医师签名
//     */
//    public String getYsqm() {
//        return ysqm;
//    }
//    /**
//     * 设置:医师签名日期
//     */
//    public void setYsqmrq(Timestamp value) {
//        this.ysqmrq = value;
//    }
//    /**
//     * 获取:医师签名日期
//     */
//    public Timestamp getYsqmrq() {
//        return ysqmrq;
//    }
//    /**
//     * 设置:日期
//     */
//    public void setRq(Timestamp value) {
//        this.rq = value;
//    }
//    /**
//     * 获取:日期
//     */
//    public Timestamp getRq() {
//        return rq;
//    }
//    /**
//     * 设置:机构id
//     */
//    public void setJgid(Integer value) {
//        this.jgid = value;
//    }
//    /**
//     * 获取:机构id
//     */
//    public Integer getJgid() {
//        return jgid;
//    }
//}