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
// * 类名称：CisKpbzmx<br> 
// * 类描述：包装明细<br>
// * @author GONGFANGZHOU
// */
//@ApiModel(value="包装明细")
//public class CisKpbzmxResp  extends  PageQuery{
//    @ApiModelProperty(value="主键")
//    private Integer bzmxid;
//    @ApiModelProperty(value="口服明细")
//    private Integer kfmx;
//    @ApiModelProperty(value="口服单号")
//    private Integer kfdh;
//    @ApiModelProperty(value="医嘱序号")
//    private Integer yzxh;
//    @ApiModelProperty(value="药品序号")
//    private Integer ypxh;
//    @ApiModelProperty(value="包装剂量")
//    private BigDecimal bzjl;
//    @ApiModelProperty(value="剂量单位")
//    private String jldw;
//    @ApiModelProperty(value="包装数量")
//    private BigDecimal bzsl;
//    @ApiModelProperty(value="数量单位")
//    private String sldw;
//    @ApiModelProperty(value="时间名称")
//    private String sjmc;
//    @ApiModelProperty(value="时间编号")
//    private Integer sjbh;
//    /**
//     * 设置:主键
//     */
//    public void setBzmxid(Integer value) {
//        this.bzmxid = value;
//    }
//    /**
//     * 获取:主键
//     */
//    public Integer getBzmxid() {
//        return bzmxid;
//    }
//    /**
//     * 设置:口服明细
//     */
//    public void setKfmx(Integer value) {
//        this.kfmx = value;
//    }
//    /**
//     * 获取:口服明细
//     */
//    public Integer getKfmx() {
//        return kfmx;
//    }
//    /**
//     * 设置:口服单号
//     */
//    public void setKfdh(Integer value) {
//        this.kfdh = value;
//    }
//    /**
//     * 获取:口服单号
//     */
//    public Integer getKfdh() {
//        return kfdh;
//    }
//    /**
//     * 设置:医嘱序号
//     */
//    public void setYzxh(Integer value) {
//        this.yzxh = value;
//    }
//    /**
//     * 获取:医嘱序号
//     */
//    public Integer getYzxh() {
//        return yzxh;
//    }
//    /**
//     * 设置:药品序号
//     */
//    public void setYpxh(Integer value) {
//        this.ypxh = value;
//    }
//    /**
//     * 获取:药品序号
//     */
//    public Integer getYpxh() {
//        return ypxh;
//    }
//    /**
//     * 设置:包装剂量
//     */
//    public void setBzjl(BigDecimal value) {
//        this.bzjl = value;
//    }
//    /**
//     * 获取:包装剂量
//     */
//    public BigDecimal getBzjl() {
//        return bzjl;
//    }
//    /**
//     * 设置:剂量单位
//     */
//    public void setJldw(String value) {
//        this.jldw = value;
//    }
//    /**
//     * 获取:剂量单位
//     */
//    public String getJldw() {
//        return jldw;
//    }
//    /**
//     * 设置:包装数量
//     */
//    public void setBzsl(BigDecimal value) {
//        this.bzsl = value;
//    }
//    /**
//     * 获取:包装数量
//     */
//    public BigDecimal getBzsl() {
//        return bzsl;
//    }
//    /**
//     * 设置:数量单位
//     */
//    public void setSldw(String value) {
//        this.sldw = value;
//    }
//    /**
//     * 获取:数量单位
//     */
//    public String getSldw() {
//        return sldw;
//    }
//    /**
//     * 设置:时间名称
//     */
//    public void setSjmc(String value) {
//        this.sjmc = value;
//    }
//    /**
//     * 获取:时间名称
//     */
//    public String getSjmc() {
//        return sjmc;
//    }
//    /**
//     * 设置:时间编号
//     */
//    public void setSjbh(Integer value) {
//        this.sjbh = value;
//    }
//    /**
//     * 获取:时间编号
//     */
//    public Integer getSjbh() {
//        return sjbh;
//    }
//}