   
package com.buit.cis.nurse.request;

import java.math.BigDecimal;
import java.sql.Timestamp;

import com.buit.commons.PageQuery;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


/**
 * 类名称：NisCqhld<br> 
 * 类描述：产前护理记录单<br>
 * @author GONGFANGZHOU
 */
@ApiModel(value="产前护理记录单_printReq")
public class NisCqhldPrintReq  extends  PageQuery{
    @ApiModelProperty(value="主键")
    private Integer jlxh;
    @ApiModelProperty(value="住院号")
    private Integer zyh;
    @ApiModelProperty(value="模板类型")
    private String mblx;
    @ApiModelProperty(value="体温")
    private BigDecimal tw;
    @ApiModelProperty(value="脉搏")
    private Integer mb;
    @ApiModelProperty(value="呼吸")
    private Integer hx;
    @ApiModelProperty(value="血压")
    private String xy;
    @ApiModelProperty(value="SpO2")
    private BigDecimal spo2;
    @ApiModelProperty(value="氧疗")
    private BigDecimal yl;
    @ApiModelProperty(value="胎心")
    private Integer tx;
    @ApiModelProperty(value="胎动")
    private Integer td;
    @ApiModelProperty(value="宫缩")
    private String gs;
    @ApiModelProperty(value="膝反射")
    private String xfs;
    @ApiModelProperty(value="尿量")
    private BigDecimal nl;
    @ApiModelProperty(value="水肿")
    private String sz;
    @ApiModelProperty(value="流水性质")
    private String lsxz;
    @ApiModelProperty(value="流水量")
    private BigDecimal lsl;
    @ApiModelProperty(value="流血性质")
    private String lxxz;
    @ApiModelProperty(value="流血量")
    private BigDecimal lxl;
    @ApiModelProperty(value="跌倒评分")
    private String ddpf;
    @ApiModelProperty(value="皮肤评估")
    private String pfpg;
    @ApiModelProperty(value="基础护理")
    private String jchl;
    @ApiModelProperty(value="健康教育")
    private String jkjy;
    @ApiModelProperty(value="护士签名")
    private String hsqm;
    @ApiModelProperty(value="日期")
    private String rq;
    @ApiModelProperty(value="其他")
    private String bzxx;
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
     * 设置:体温
     */
    public void setTw(BigDecimal value) {
        this.tw = value;
    }
    /**
     * 获取:体温
     */
    public BigDecimal getTw() {
        return tw;
    }
    /**
     * 设置:脉搏
     */
    public void setMb(Integer value) {
        this.mb = value;
    }
    /**
     * 获取:脉搏
     */
    public Integer getMb() {
        return mb;
    }
    /**
     * 设置:呼吸
     */
    public void setHx(Integer value) {
        this.hx = value;
    }
    /**
     * 获取:呼吸
     */
    public Integer getHx() {
        return hx;
    }
    /**
     * 设置:血压
     */
    public void setXy(String value) {
        this.xy = value;
    }
    /**
     * 获取:血压
     */
    public String getXy() {
        return xy;
    }
    /**
     * 设置:SpO2
     */
    public void setSpo2(BigDecimal value) {
        this.spo2 = value;
    }
    /**
     * 获取:SpO2
     */
    public BigDecimal getSpo2() {
        return spo2;
    }
    /**
     * 设置:氧疗
     */
    public void setYl(BigDecimal value) {
        this.yl = value;
    }
    /**
     * 获取:氧疗
     */
    public BigDecimal getYl() {
        return yl;
    }
    /**
     * 设置:胎心
     */
    public void setTx(Integer value) {
        this.tx = value;
    }
    /**
     * 获取:胎心
     */
    public Integer getTx() {
        return tx;
    }
    /**
     * 设置:胎动
     */
    public void setTd(Integer value) {
        this.td = value;
    }
    /**
     * 获取:胎动
     */
    public Integer getTd() {
        return td;
    }
    /**
     * 设置:宫缩
     */
    public void setGs(String value) {
        this.gs = value;
    }
    /**
     * 获取:宫缩
     */
    public String getGs() {
        return gs;
    }
    /**
     * 设置:膝反射
     */
    public void setXfs(String value) {
        this.xfs = value;
    }
    /**
     * 获取:膝反射
     */
    public String getXfs() {
        return xfs;
    }
    /**
     * 设置:尿量
     */
    public void setNl(BigDecimal value) {
        this.nl = value;
    }
    /**
     * 获取:尿量
     */
    public BigDecimal getNl() {
        return nl;
    }
    /**
     * 设置:水肿
     */
    public void setSz(String value) {
        this.sz = value;
    }
    /**
     * 获取:水肿
     */
    public String getSz() {
        return sz;
    }
    /**
     * 设置:流水性质
     */
    public void setLsxz(String value) {
        this.lsxz = value;
    }
    /**
     * 获取:流水性质
     */
    public String getLsxz() {
        return lsxz;
    }
    /**
     * 设置:流水量
     */
    public void setLsl(BigDecimal value) {
        this.lsl = value;
    }
    /**
     * 获取:流水量
     */
    public BigDecimal getLsl() {
        return lsl;
    }
    /**
     * 设置:流血性质
     */
    public void setLxxz(String value) {
        this.lxxz = value;
    }
    /**
     * 获取:流血性质
     */
    public String getLxxz() {
        return lxxz;
    }
    /**
     * 设置:流血量
     */
    public void setLxl(BigDecimal value) {
        this.lxl = value;
    }
    /**
     * 获取:流血量
     */
    public BigDecimal getLxl() {
        return lxl;
    }
    /**
     * 设置:跌倒评分
     */
    public void setDdpf(String value) {
        this.ddpf = value;
    }
    /**
     * 获取:跌倒评分
     */
    public String getDdpf() {
        return ddpf;
    }
    /**
     * 设置:皮肤评估
     */
    public void setPfpg(String value) {
        this.pfpg = value;
    }
    /**
     * 获取:皮肤评估
     */
    public String getPfpg() {
        return pfpg;
    }
    /**
     * 设置:基础护理
     */
    public void setJchl(String value) {
        this.jchl = value;
    }
    /**
     * 获取:基础护理
     */
    public String getJchl() {
        return jchl;
    }
    /**
     * 设置:健康教育
     */
    public void setJkjy(String value) {
        this.jkjy = value;
    }
    /**
     * 获取:健康教育
     */
    public String getJkjy() {
        return jkjy;
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
     * 设置:日期
     */
    public void setRq(String value) {
        this.rq = value;
    }
    /**
     * 获取:日期
     */
    public String getRq() {
        return rq;
    }
    /**
     * 设置:其他
     */
    public void setBzxx(String value) {
        this.bzxx = value;
    }
    /**
     * 获取:其他
     */
    public String getBzxx() {
        return bzxx;
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