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
// * 类名称：NisHljlmb<br> 
// * 类描述：护理记录模板<br>
// * @author GONGFANGZHOU
// */
//@ApiModel(value="护理记录模板")
//public class NisHljlmbResp  extends  PageQuery{
//    @ApiModelProperty(value="模板编号")
//    private Integer mbbh;
//    @ApiModelProperty(value="模板名称")
//    private String mbmc;
//    @ApiModelProperty(value="模板类型")
//    private String mblx;
//    @ApiModelProperty(value="作废判别(1:是0:否)")
//    private Integer zfpb;
//    @ApiModelProperty(value="备注信息")
//    private String bzxx;
//    @ApiModelProperty(value="拼音代码")
//    private String pydm;
//    @ApiModelProperty(value="机构id")
//    private Integer jgid;
//    /**
//     * 设置:模板编号
//     */
//    public void setMbbh(Integer value) {
//        this.mbbh = value;
//    }
//    /**
//     * 获取:模板编号
//     */
//    public Integer getMbbh() {
//        return mbbh;
//    }
//    /**
//     * 设置:模板名称
//     */
//    public void setMbmc(String value) {
//        this.mbmc = value;
//    }
//    /**
//     * 获取:模板名称
//     */
//    public String getMbmc() {
//        return mbmc;
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
//     * 设置:作废判别(1:是0:否)
//     */
//    public void setZfpb(Integer value) {
//        this.zfpb = value;
//    }
//    /**
//     * 获取:作废判别(1:是0:否)
//     */
//    public Integer getZfpb() {
//        return zfpb;
//    }
//    /**
//     * 设置:备注信息
//     */
//    public void setBzxx(String value) {
//        this.bzxx = value;
//    }
//    /**
//     * 获取:备注信息
//     */
//    public String getBzxx() {
//        return bzxx;
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