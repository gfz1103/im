   
package com.buit.cis.dctwork.response;

import java.math.BigDecimal;
import java.sql.Timestamp;

import com.buit.commons.PageQuery;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


/**
 * 类名称：AmqcKjywsqd<br> 
 * 类描述：<br>
 * @author GONGFANGZHOU
 */
@ApiModel(value="抗菌药物申请_Resp")
public class AmqcKjywsqdResp  extends  PageQuery{
    @ApiModelProperty(value="sqdh")
    private Integer sqdh;
    @ApiModelProperty(value="就诊类型:Dict9 1住院 2新生儿 3门诊 4急诊 5留观")
    private Integer jzlx;
    @ApiModelProperty(value="就诊序号:就诊类型匹配的唯一识别号，比如门诊就诊序号、住院流水号")
    private String jzxh;
    @ApiModelProperty(value="brks")
    private Integer brks;
    @ApiModelProperty(value="病人病区")
    private Integer brbq;
    @ApiModelProperty(value="brch")
    private String brch;
    @ApiModelProperty(value="jbbh")
    private Integer jbbh;
    @ApiModelProperty(value="ysykjy")
    private String ysykjy;
    @ApiModelProperty(value="mqbq")
    private String mqbq;
    @ApiModelProperty(value="ypxh")
    private Integer ypxh;
    @ApiModelProperty(value="rzyl")
    private BigDecimal rzyl;
    @ApiModelProperty(value="yylc")
    private Integer yylc;
    @ApiModelProperty(value="grbqyzz")
    private Integer grbqyzz;
    @ApiModelProperty(value="mygndx")
    private Integer mygndx;
    @ApiModelProperty(value="sqywmg")
    private Integer sqywmg;
    @ApiModelProperty(value="jybgh")
    private String jybgh;
    @ApiModelProperty(value="hzks")
    private Integer hzks;
    @ApiModelProperty(value="qtyy")
    private Integer qtyy;
    @ApiModelProperty(value="qtyyxs")
    private String qtyyxs;
    @ApiModelProperty(value="sqys")
    private String sqys;
    @ApiModelProperty(value="医生填写申请单的时间")
    private Timestamp djrq;
    @ApiModelProperty(value="医生提交申请单的时间")
    private Timestamp sqrq;
    @ApiModelProperty(value="sqkzr")
    private String sqkzr;
    @ApiModelProperty(value="spyl")
    private BigDecimal spyl;
    @ApiModelProperty(value="抗菌药物专家组会诊意见")
    private String zjyj;
    @ApiModelProperty(value="zjqm")
    private String zjqm;
    @ApiModelProperty(value="zjsprq")
    private Timestamp zjsprq;
    @ApiModelProperty(value="ywkshys")
    private String ywkshys;
    @ApiModelProperty(value="ywkshrq")
    private Timestamp ywkshrq;
    @ApiModelProperty(value="0-新增 1-提交 2-科主任审批完成 3-专家组审批完成 4-医务科审批完成")
    private Integer djzt;
    @ApiModelProperty(value="0-未审批 1-同意 2-自动 9-不同意")
    private Integer spjg;
    @ApiModelProperty(value="zfbz")
    private Integer zfbz;
    @ApiModelProperty(value="jgid")
    private Integer jgid;
    /**
     * 设置:sqdh
     */
    public void setSqdh(Integer value) {
        this.sqdh = value;
    }
    /**
     * 获取:sqdh
     */
    public Integer getSqdh() {
        return sqdh;
    }
    /**
     * 设置:就诊类型:Dict9 1住院 2新生儿 3门诊 4急诊 5留观
     */
    public void setJzlx(Integer value) {
        this.jzlx = value;
    }
    /**
     * 获取:就诊类型:Dict9 1住院 2新生儿 3门诊 4急诊 5留观
     */
    public Integer getJzlx() {
        return jzlx;
    }
    /**
     * 设置:就诊序号:就诊类型匹配的唯一识别号，比如门诊就诊序号、住院流水号
     */
    public void setJzxh(String value) {
        this.jzxh = value;
    }
    /**
     * 获取:就诊序号:就诊类型匹配的唯一识别号，比如门诊就诊序号、住院流水号
     */
    public String getJzxh() {
        return jzxh;
    }
    /**
     * 设置:brks
     */
    public void setBrks(Integer value) {
        this.brks = value;
    }
    /**
     * 获取:brks
     */
    public Integer getBrks() {
        return brks;
    }
    /**
     * 设置:病人病区
     */
    public void setBrbq(Integer value) {
        this.brbq = value;
    }
    /**
     * 获取:病人病区
     */
    public Integer getBrbq() {
        return brbq;
    }
    /**
     * 设置:brch
     */
    public void setBrch(String value) {
        this.brch = value;
    }
    /**
     * 获取:brch
     */
    public String getBrch() {
        return brch;
    }
    /**
     * 设置:jbbh
     */
    public void setJbbh(Integer value) {
        this.jbbh = value;
    }
    /**
     * 获取:jbbh
     */
    public Integer getJbbh() {
        return jbbh;
    }
    /**
     * 设置:ysykjy
     */
    public void setYsykjy(String value) {
        this.ysykjy = value;
    }
    /**
     * 获取:ysykjy
     */
    public String getYsykjy() {
        return ysykjy;
    }
    /**
     * 设置:mqbq
     */
    public void setMqbq(String value) {
        this.mqbq = value;
    }
    /**
     * 获取:mqbq
     */
    public String getMqbq() {
        return mqbq;
    }
    /**
     * 设置:ypxh
     */
    public void setYpxh(Integer value) {
        this.ypxh = value;
    }
    /**
     * 获取:ypxh
     */
    public Integer getYpxh() {
        return ypxh;
    }
    /**
     * 设置:rzyl
     */
    public void setRzyl(BigDecimal value) {
        this.rzyl = value;
    }
    /**
     * 获取:rzyl
     */
    public BigDecimal getRzyl() {
        return rzyl;
    }
    /**
     * 设置:yylc
     */
    public void setYylc(Integer value) {
        this.yylc = value;
    }
    /**
     * 获取:yylc
     */
    public Integer getYylc() {
        return yylc;
    }
    /**
     * 设置:grbqyzz
     */
    public void setGrbqyzz(Integer value) {
        this.grbqyzz = value;
    }
    /**
     * 获取:grbqyzz
     */
    public Integer getGrbqyzz() {
        return grbqyzz;
    }
    /**
     * 设置:mygndx
     */
    public void setMygndx(Integer value) {
        this.mygndx = value;
    }
    /**
     * 获取:mygndx
     */
    public Integer getMygndx() {
        return mygndx;
    }
    /**
     * 设置:sqywmg
     */
    public void setSqywmg(Integer value) {
        this.sqywmg = value;
    }
    /**
     * 获取:sqywmg
     */
    public Integer getSqywmg() {
        return sqywmg;
    }
    /**
     * 设置:jybgh
     */
    public void setJybgh(String value) {
        this.jybgh = value;
    }
    /**
     * 获取:jybgh
     */
    public String getJybgh() {
        return jybgh;
    }
    /**
     * 设置:hzks
     */
    public void setHzks(Integer value) {
        this.hzks = value;
    }
    /**
     * 获取:hzks
     */
    public Integer getHzks() {
        return hzks;
    }
    /**
     * 设置:qtyy
     */
    public void setQtyy(Integer value) {
        this.qtyy = value;
    }
    /**
     * 获取:qtyy
     */
    public Integer getQtyy() {
        return qtyy;
    }
    /**
     * 设置:qtyyxs
     */
    public void setQtyyxs(String value) {
        this.qtyyxs = value;
    }
    /**
     * 获取:qtyyxs
     */
    public String getQtyyxs() {
        return qtyyxs;
    }
    /**
     * 设置:sqys
     */
    public void setSqys(String value) {
        this.sqys = value;
    }
    /**
     * 获取:sqys
     */
    public String getSqys() {
        return sqys;
    }
    /**
     * 设置:医生填写申请单的时间
     */
    public void setDjrq(Timestamp value) {
        this.djrq = value;
    }
    /**
     * 获取:医生填写申请单的时间
     */
    public Timestamp getDjrq() {
        return djrq;
    }
    /**
     * 设置:医生提交申请单的时间
     */
    public void setSqrq(Timestamp value) {
        this.sqrq = value;
    }
    /**
     * 获取:医生提交申请单的时间
     */
    public Timestamp getSqrq() {
        return sqrq;
    }
    /**
     * 设置:sqkzr
     */
    public void setSqkzr(String value) {
        this.sqkzr = value;
    }
    /**
     * 获取:sqkzr
     */
    public String getSqkzr() {
        return sqkzr;
    }
    /**
     * 设置:spyl
     */
    public void setSpyl(BigDecimal value) {
        this.spyl = value;
    }
    /**
     * 获取:spyl
     */
    public BigDecimal getSpyl() {
        return spyl;
    }
    /**
     * 设置:抗菌药物专家组会诊意见
     */
    public void setZjyj(String value) {
        this.zjyj = value;
    }
    /**
     * 获取:抗菌药物专家组会诊意见
     */
    public String getZjyj() {
        return zjyj;
    }
    /**
     * 设置:zjqm
     */
    public void setZjqm(String value) {
        this.zjqm = value;
    }
    /**
     * 获取:zjqm
     */
    public String getZjqm() {
        return zjqm;
    }
    /**
     * 设置:zjsprq
     */
    public void setZjsprq(Timestamp value) {
        this.zjsprq = value;
    }
    /**
     * 获取:zjsprq
     */
    public Timestamp getZjsprq() {
        return zjsprq;
    }
    /**
     * 设置:ywkshys
     */
    public void setYwkshys(String value) {
        this.ywkshys = value;
    }
    /**
     * 获取:ywkshys
     */
    public String getYwkshys() {
        return ywkshys;
    }
    /**
     * 设置:ywkshrq
     */
    public void setYwkshrq(Timestamp value) {
        this.ywkshrq = value;
    }
    /**
     * 获取:ywkshrq
     */
    public Timestamp getYwkshrq() {
        return ywkshrq;
    }
    /**
     * 设置:0-新增 1-提交 2-科主任审批完成 3-专家组审批完成 4-医务科审批完成
     */
    public void setDjzt(Integer value) {
        this.djzt = value;
    }
    /**
     * 获取:0-新增 1-提交 2-科主任审批完成 3-专家组审批完成 4-医务科审批完成
     */
    public Integer getDjzt() {
        return djzt;
    }
    /**
     * 设置:0-未审批 1-同意 2-自动 9-不同意
     */
    public void setSpjg(Integer value) {
        this.spjg = value;
    }
    /**
     * 获取:0-未审批 1-同意 2-自动 9-不同意
     */
    public Integer getSpjg() {
        return spjg;
    }
    /**
     * 设置:zfbz
     */
    public void setZfbz(Integer value) {
        this.zfbz = value;
    }
    /**
     * 获取:zfbz
     */
    public Integer getZfbz() {
        return zfbz;
    }
    /**
     * 设置:jgid
     */
    public void setJgid(Integer value) {
        this.jgid = value;
    }
    /**
     * 获取:jgid
     */
    public Integer getJgid() {
        return jgid;
    }
}
