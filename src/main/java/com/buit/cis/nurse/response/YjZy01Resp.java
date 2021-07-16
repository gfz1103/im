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
// * 类名称：YjZy01<br> 
// * 类描述：<br>
// * @author GONGFANGZHOU
// */
//@ApiModel(value="")
//public class YjZy01Resp  extends  PageQuery{
//    @ApiModelProperty(value="和YJ_ZY02.YJXH主外键关系")
//    private Integer yjxh;
//    @ApiModelProperty(value="jgid")
//    private Integer jgid;
//    @ApiModelProperty(value="tjhm")
//    private String tjhm;
//    @ApiModelProperty(value="zyh")
//    private Integer zyh;
//    @ApiModelProperty(value="zyhm")
//    private String zyhm;
//    @ApiModelProperty(value="brxm")
//    private String brxm;
//    @ApiModelProperty(value="kdrq")
//    private Timestamp kdrq;
//    @ApiModelProperty(value="ksdm")
//    private Integer ksdm;
//    @ApiModelProperty(value="ysdm")
//    private String ysdm;
//    @ApiModelProperty(value="zxrq")
//    private Timestamp zxrq;
//    @ApiModelProperty(value="zxks")
//    private Integer zxks;
//    @ApiModelProperty(value="zxys")
//    private String zxys;
//    @ApiModelProperty(value="医技单执行判别,与同表的ZXKS、ZXYS、ZXRQ同时被赋值 0：未执行     1：已执行")
//    private Integer zxpb;
//    @ApiModelProperty(value="hjgh")
//    private String hjgh;
//    @ApiModelProperty(value="与YJ_BBBM.BBBM 列相关联")
//    private String bbbm;
//    @ApiModelProperty(value="zysx")
//    private String zysx;
//    @ApiModelProperty(value="zfpb")
//    private Integer zfpb;
//    @ApiModelProperty(value="hymx")
//    private String hymx;
//    @ApiModelProperty(value="yjph")
//    private String yjph;
//    @ApiModelProperty(value="sqdh")
//    private Integer sqdh;
//    @ApiModelProperty(value="bwid")
//    private Integer bwid;
//    @ApiModelProperty(value="jbid")
//    private Integer jbid;
//    @ApiModelProperty(value="djzt")
//    private Integer djzt;
//    @ApiModelProperty(value="sqwh")
//    private Integer sqwh;
//    @ApiModelProperty(value="fybq")
//    private Integer fybq;
//    @ApiModelProperty(value="提交时间")
//    private Timestamp tjsj;
//    /**
//     * 设置:和YJ_ZY02.YJXH主外键关系
//     */
//    public void setYjxh(Integer value) {
//        this.yjxh = value;
//    }
//    /**
//     * 获取:和YJ_ZY02.YJXH主外键关系
//     */
//    public Integer getYjxh() {
//        return yjxh;
//    }
//    /**
//     * 设置:jgid
//     */
//    public void setJgid(Integer value) {
//        this.jgid = value;
//    }
//    /**
//     * 获取:jgid
//     */
//    public Integer getJgid() {
//        return jgid;
//    }
//    /**
//     * 设置:tjhm
//     */
//    public void setTjhm(String value) {
//        this.tjhm = value;
//    }
//    /**
//     * 获取:tjhm
//     */
//    public String getTjhm() {
//        return tjhm;
//    }
//    /**
//     * 设置:zyh
//     */
//    public void setZyh(Integer value) {
//        this.zyh = value;
//    }
//    /**
//     * 获取:zyh
//     */
//    public Integer getZyh() {
//        return zyh;
//    }
//    /**
//     * 设置:zyhm
//     */
//    public void setZyhm(String value) {
//        this.zyhm = value;
//    }
//    /**
//     * 获取:zyhm
//     */
//    public String getZyhm() {
//        return zyhm;
//    }
//    /**
//     * 设置:brxm
//     */
//    public void setBrxm(String value) {
//        this.brxm = value;
//    }
//    /**
//     * 获取:brxm
//     */
//    public String getBrxm() {
//        return brxm;
//    }
//    /**
//     * 设置:kdrq
//     */
//    public void setKdrq(Timestamp value) {
//        this.kdrq = value;
//    }
//    /**
//     * 获取:kdrq
//     */
//    public Timestamp getKdrq() {
//        return kdrq;
//    }
//    /**
//     * 设置:ksdm
//     */
//    public void setKsdm(Integer value) {
//        this.ksdm = value;
//    }
//    /**
//     * 获取:ksdm
//     */
//    public Integer getKsdm() {
//        return ksdm;
//    }
//    /**
//     * 设置:ysdm
//     */
//    public void setYsdm(String value) {
//        this.ysdm = value;
//    }
//    /**
//     * 获取:ysdm
//     */
//    public String getYsdm() {
//        return ysdm;
//    }
//    /**
//     * 设置:zxrq
//     */
//    public void setZxrq(Timestamp value) {
//        this.zxrq = value;
//    }
//    /**
//     * 获取:zxrq
//     */
//    public Timestamp getZxrq() {
//        return zxrq;
//    }
//    /**
//     * 设置:zxks
//     */
//    public void setZxks(Integer value) {
//        this.zxks = value;
//    }
//    /**
//     * 获取:zxks
//     */
//    public Integer getZxks() {
//        return zxks;
//    }
//    /**
//     * 设置:zxys
//     */
//    public void setZxys(String value) {
//        this.zxys = value;
//    }
//    /**
//     * 获取:zxys
//     */
//    public String getZxys() {
//        return zxys;
//    }
//    /**
//     * 设置:医技单执行判别,与同表的ZXKS、ZXYS、ZXRQ同时被赋值 0：未执行     1：已执行
//     */
//    public void setZxpb(Integer value) {
//        this.zxpb = value;
//    }
//    /**
//     * 获取:医技单执行判别,与同表的ZXKS、ZXYS、ZXRQ同时被赋值 0：未执行     1：已执行
//     */
//    public Integer getZxpb() {
//        return zxpb;
//    }
//    /**
//     * 设置:hjgh
//     */
//    public void setHjgh(String value) {
//        this.hjgh = value;
//    }
//    /**
//     * 获取:hjgh
//     */
//    public String getHjgh() {
//        return hjgh;
//    }
//    /**
//     * 设置:与YJ_BBBM.BBBM 列相关联
//     */
//    public void setBbbm(String value) {
//        this.bbbm = value;
//    }
//    /**
//     * 获取:与YJ_BBBM.BBBM 列相关联
//     */
//    public String getBbbm() {
//        return bbbm;
//    }
//    /**
//     * 设置:zysx
//     */
//    public void setZysx(String value) {
//        this.zysx = value;
//    }
//    /**
//     * 获取:zysx
//     */
//    public String getZysx() {
//        return zysx;
//    }
//    /**
//     * 设置:zfpb
//     */
//    public void setZfpb(Integer value) {
//        this.zfpb = value;
//    }
//    /**
//     * 获取:zfpb
//     */
//    public Integer getZfpb() {
//        return zfpb;
//    }
//    /**
//     * 设置:hymx
//     */
//    public void setHymx(String value) {
//        this.hymx = value;
//    }
//    /**
//     * 获取:hymx
//     */
//    public String getHymx() {
//        return hymx;
//    }
//    /**
//     * 设置:yjph
//     */
//    public void setYjph(String value) {
//        this.yjph = value;
//    }
//    /**
//     * 获取:yjph
//     */
//    public String getYjph() {
//        return yjph;
//    }
//    /**
//     * 设置:sqdh
//     */
//    public void setSqdh(Integer value) {
//        this.sqdh = value;
//    }
//    /**
//     * 获取:sqdh
//     */
//    public Integer getSqdh() {
//        return sqdh;
//    }
//    /**
//     * 设置:bwid
//     */
//    public void setBwid(Integer value) {
//        this.bwid = value;
//    }
//    /**
//     * 获取:bwid
//     */
//    public Integer getBwid() {
//        return bwid;
//    }
//    /**
//     * 设置:jbid
//     */
//    public void setJbid(Integer value) {
//        this.jbid = value;
//    }
//    /**
//     * 获取:jbid
//     */
//    public Integer getJbid() {
//        return jbid;
//    }
//    /**
//     * 设置:djzt
//     */
//    public void setDjzt(Integer value) {
//        this.djzt = value;
//    }
//    /**
//     * 获取:djzt
//     */
//    public Integer getDjzt() {
//        return djzt;
//    }
//    /**
//     * 设置:sqwh
//     */
//    public void setSqwh(Integer value) {
//        this.sqwh = value;
//    }
//    /**
//     * 获取:sqwh
//     */
//    public Integer getSqwh() {
//        return sqwh;
//    }
//    /**
//     * 设置:fybq
//     */
//    public void setFybq(Integer value) {
//        this.fybq = value;
//    }
//    /**
//     * 获取:fybq
//     */
//    public Integer getFybq() {
//        return fybq;
//    }
//    /**
//     * 设置:提交时间
//     */
//    public void setTjsj(Timestamp value) {
//        this.tjsj = value;
//    }
//    /**
//     * 获取:提交时间
//     */
//    public Timestamp getTjsj() {
//        return tjsj;
//    }
//}