package com.buit.cis.dctwork.request;
//   
//package com.buit.cis.dctwork.request;
//
//import java.sql.Timestamp;
//
//import com.buit.commons.PageQuery;
//import io.swagger.annotations.ApiModel;
//import io.swagger.annotations.ApiModelProperty;
//
//
///**
// * 类名称：NisTzxm<br> 
// * 类描述：<br>
// * @author GONGFANGZHOU
// */
//@ApiModel(value="")
//public class NisTzxmReq  extends  PageQuery{
//    @ApiModelProperty(value="项目号")
//    private Long xmh;
//    @ApiModelProperty(value="项目名称")
//    private String xmmc;
//    @ApiModelProperty(value="拼音代码")
//    private String pydm;
//    @ApiModelProperty(value="状态标志（0：正常；1：注销）")
//    private Boolean ztbz;
//    @ApiModelProperty(value="系统标志（0：自定义；1：系统（不能修改））")
//    private Boolean xtbz;
//    @ApiModelProperty(value="体征类型（1.基本体征 2.入量 3.出量 4.皮试）")
//    private Boolean tzlx;
//    @ApiModelProperty(value="xmdw")
//    private Integer xmdw;
//    @ApiModelProperty(value="体温单显示标志：1表示默认显示到体温单中 0否（用户可再选择）")
//    private Boolean twdxs;
//    /**
//     * 设置:项目号
//     */
//    public void setXmh(Long value) {
//        this.xmh = value;
//    }
//    /**
//     * 获取:项目号
//     */
//    public Long getXmh() {
//        return xmh;
//    }
//    /**
//     * 设置:项目名称
//     */
//    public void setXmmc(String value) {
//        this.xmmc = value;
//    }
//    /**
//     * 获取:项目名称
//     */
//    public String getXmmc() {
//        return xmmc;
//    }
//    /**
//     * 设置:拼音代码
//     */
//    public void setPydm(String value) {
//        this.pydm = value;
//    }
//    /**
//     * 获取:拼音代码
//     */
//    public String getPydm() {
//        return pydm;
//    }
//    /**
//     * 设置:状态标志（0：正常；1：注销）
//     */
//    public void setZtbz(Boolean value) {
//        this.ztbz = value;
//    }
//    /**
//     * 获取:状态标志（0：正常；1：注销）
//     */
//    public Boolean getZtbz() {
//        return ztbz;
//    }
//    /**
//     * 设置:系统标志（0：自定义；1：系统（不能修改））
//     */
//    public void setXtbz(Boolean value) {
//        this.xtbz = value;
//    }
//    /**
//     * 获取:系统标志（0：自定义；1：系统（不能修改））
//     */
//    public Boolean getXtbz() {
//        return xtbz;
//    }
//    /**
//     * 设置:体征类型（1.基本体征 2.入量 3.出量 4.皮试）
//     */
//    public void setTzlx(Boolean value) {
//        this.tzlx = value;
//    }
//    /**
//     * 获取:体征类型（1.基本体征 2.入量 3.出量 4.皮试）
//     */
//    public Boolean getTzlx() {
//        return tzlx;
//    }
//    /**
//     * 设置:xmdw
//     */
//    public void setXmdw(Integer value) {
//        this.xmdw = value;
//    }
//    /**
//     * 获取:xmdw
//     */
//    public Integer getXmdw() {
//        return xmdw;
//    }
//    /**
//     * 设置:体温单显示标志：1表示默认显示到体温单中 0否（用户可再选择）
//     */
//    public void setTwdxs(Boolean value) {
//        this.twdxs = value;
//    }
//    /**
//     * 获取:体温单显示标志：1表示默认显示到体温单中 0否（用户可再选择）
//     */
//    public Boolean getTwdxs() {
//        return twdxs;
//    }
//}
