package com.buit.cis.nurse.response;
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
// * 类名称：NisChhld<br> 
// * 类描述：产后护理记录单<br>
// * @author GONGFANGZHOU
// */
//@ApiModel(value="产后护理记录单")
//public class NisChhldResp  extends  PageQuery{
//    @ApiModelProperty(value="主键")
//    private Integer jlxh;
//    @ApiModelProperty(value="住院号")
//    private Integer zyh;
//    @ApiModelProperty(value="模板类型")
//    private String mblx;
//    @ApiModelProperty(value="体温")
//    private BigDecimal tw;
//    @ApiModelProperty(value="脉搏")
//    private Integer mb;
//    @ApiModelProperty(value="呼吸")
//    private Integer hx;
//    @ApiModelProperty(value="血压")
//    private Integer xy;
//    @ApiModelProperty(value="SpO2")
//    private BigDecimal spo2;
//    @ApiModelProperty(value="氧疗")
//    private BigDecimal yl;
//    @ApiModelProperty(value="伤口情况")
//    private String skqk;
//    @ApiModelProperty(value="泌乳")
//    private String mr;
//    @ApiModelProperty(value="宫底")
//    private String gd;
//    @ApiModelProperty(value="质地")
//    private BigDecimal zd;
//    @ApiModelProperty(value="流血量")
//    private BigDecimal lxl;
//    @ApiModelProperty(value="性质")
//    private String xz;
//    @ApiModelProperty(value="肛门排气")
//    private String gmpq;
//    @ApiModelProperty(value="排尿情况")
//    private String pnqk;
//    @ApiModelProperty(value="尿量")
//    private BigDecimal nl;
//    @ApiModelProperty(value="皮肤评估")
//    private String pfpg;
//    @ApiModelProperty(value="基础护理")
//    private String jchl;
//    @ApiModelProperty(value="健康教育")
//    private String jkjy;
//    @ApiModelProperty(value="护士签名")
//    private String hsqm;
//    @ApiModelProperty(value="日期")
//    private Timestamp rq;
//    @ApiModelProperty(value="其他")
//    private String bzxx;
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
//     * 设置:体温
//     */
//    public void setTw(BigDecimal value) {
//        this.tw = value;
//    }
//    /**
//     * 获取:体温
//     */
//    public BigDecimal getTw() {
//        return tw;
//    }
//    /**
//     * 设置:脉搏
//     */
//    public void setMb(Integer value) {
//        this.mb = value;
//    }
//    /**
//     * 获取:脉搏
//     */
//    public Integer getMb() {
//        return mb;
//    }
//    /**
//     * 设置:呼吸
//     */
//    public void setHx(Integer value) {
//        this.hx = value;
//    }
//    /**
//     * 获取:呼吸
//     */
//    public Integer getHx() {
//        return hx;
//    }
//    /**
//     * 设置:血压
//     */
//    public void setXy(Integer value) {
//        this.xy = value;
//    }
//    /**
//     * 获取:血压
//     */
//    public Integer getXy() {
//        return xy;
//    }
//    /**
//     * 设置:SpO2
//     */
//    public void setSpo2(BigDecimal value) {
//        this.spo2 = value;
//    }
//    /**
//     * 获取:SpO2
//     */
//    public BigDecimal getSpo2() {
//        return spo2;
//    }
//    /**
//     * 设置:氧疗
//     */
//    public void setYl(BigDecimal value) {
//        this.yl = value;
//    }
//    /**
//     * 获取:氧疗
//     */
//    public BigDecimal getYl() {
//        return yl;
//    }
//    /**
//     * 设置:伤口情况
//     */
//    public void setSkqk(String value) {
//        this.skqk = value;
//    }
//    /**
//     * 获取:伤口情况
//     */
//    public String getSkqk() {
//        return skqk;
//    }
//    /**
//     * 设置:泌乳
//     */
//    public void setMr(String value) {
//        this.mr = value;
//    }
//    /**
//     * 获取:泌乳
//     */
//    public String getMr() {
//        return mr;
//    }
//    /**
//     * 设置:宫底
//     */
//    public void setGd(String value) {
//        this.gd = value;
//    }
//    /**
//     * 获取:宫底
//     */
//    public String getGd() {
//        return gd;
//    }
//    /**
//     * 设置:质地
//     */
//    public void setZd(BigDecimal value) {
//        this.zd = value;
//    }
//    /**
//     * 获取:质地
//     */
//    public BigDecimal getZd() {
//        return zd;
//    }
//    /**
//     * 设置:流血量
//     */
//    public void setLxl(BigDecimal value) {
//        this.lxl = value;
//    }
//    /**
//     * 获取:流血量
//     */
//    public BigDecimal getLxl() {
//        return lxl;
//    }
//    /**
//     * 设置:性质
//     */
//    public void setXz(String value) {
//        this.xz = value;
//    }
//    /**
//     * 获取:性质
//     */
//    public String getXz() {
//        return xz;
//    }
//    /**
//     * 设置:肛门排气
//     */
//    public void setGmpq(String value) {
//        this.gmpq = value;
//    }
//    /**
//     * 获取:肛门排气
//     */
//    public String getGmpq() {
//        return gmpq;
//    }
//    /**
//     * 设置:排尿情况
//     */
//    public void setPnqk(String value) {
//        this.pnqk = value;
//    }
//    /**
//     * 获取:排尿情况
//     */
//    public String getPnqk() {
//        return pnqk;
//    }
//    /**
//     * 设置:尿量
//     */
//    public void setNl(BigDecimal value) {
//        this.nl = value;
//    }
//    /**
//     * 获取:尿量
//     */
//    public BigDecimal getNl() {
//        return nl;
//    }
//    /**
//     * 设置:皮肤评估
//     */
//    public void setPfpg(String value) {
//        this.pfpg = value;
//    }
//    /**
//     * 获取:皮肤评估
//     */
//    public String getPfpg() {
//        return pfpg;
//    }
//    /**
//     * 设置:基础护理
//     */
//    public void setJchl(String value) {
//        this.jchl = value;
//    }
//    /**
//     * 获取:基础护理
//     */
//    public String getJchl() {
//        return jchl;
//    }
//    /**
//     * 设置:健康教育
//     */
//    public void setJkjy(String value) {
//        this.jkjy = value;
//    }
//    /**
//     * 获取:健康教育
//     */
//    public String getJkjy() {
//        return jkjy;
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
//     * 设置:其他
//     */
//    public void setBzxx(String value) {
//        this.bzxx = value;
//    }
//    /**
//     * 获取:其他
//     */
//    public String getBzxx() {
//        return bzxx;
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