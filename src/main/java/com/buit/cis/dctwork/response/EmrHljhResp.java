package com.buit.cis.dctwork.response;
//   
//package com.buit.cis.dctwork.response;
//
//import java.sql.Timestamp;
//
//import com.buit.commons.PageQuery;
//import io.swagger.annotations.ApiModel;
//import io.swagger.annotations.ApiModelProperty;
//
//
///**
// * 类名称：EmrHljh<br> 
// * 类描述：护理计划<br>
// * @author GONGFANGZHOU
// */
//@ApiModel(value="护理计划")
//public class EmrHljhResp  extends  PageQuery{
//    @ApiModelProperty(value="计划编号")
//    private Integer jlbh;
//    @ApiModelProperty(value="开始日期")
//    private Timestamp ksrq;
//    @ApiModelProperty(value="护理诊断")
//    private String hlzd;
//    @ApiModelProperty(value="护理目标")
//    private String hlmb;
//    @ApiModelProperty(value="护理措施")
//    private String hlcs;
//    @ApiModelProperty(value="护理评价")
//    private String hlpj;
//    @ApiModelProperty(value="停止日期")
//    private Timestamp tzrq;
//    @ApiModelProperty(value="住院号")
//    private Integer zyh;
//    @ApiModelProperty(value="机构id")
//    private Integer jgid;
//    /**
//     * 设置:计划编号
//     */
//    public void setJlbh(Integer value) {
//        this.jlbh = value;
//    }
//    /**
//     * 获取:计划编号
//     */
//    public Integer getJlbh() {
//        return jlbh;
//    }
//    /**
//     * 设置:开始日期
//     */
//    public void setKsrq(Timestamp value) {
//        this.ksrq = value;
//    }
//    /**
//     * 获取:开始日期
//     */
//    public Timestamp getKsrq() {
//        return ksrq;
//    }
//    /**
//     * 设置:护理诊断
//     */
//    public void setHlzd(String value) {
//        this.hlzd = value;
//    }
//    /**
//     * 获取:护理诊断
//     */
//    public String getHlzd() {
//        return hlzd;
//    }
//    /**
//     * 设置:护理目标
//     */
//    public void setHlmb(String value) {
//        this.hlmb = value;
//    }
//    /**
//     * 获取:护理目标
//     */
//    public String getHlmb() {
//        return hlmb;
//    }
//    /**
//     * 设置:护理措施
//     */
//    public void setHlcs(String value) {
//        this.hlcs = value;
//    }
//    /**
//     * 获取:护理措施
//     */
//    public String getHlcs() {
//        return hlcs;
//    }
//    /**
//     * 设置:护理评价
//     */
//    public void setHlpj(String value) {
//        this.hlpj = value;
//    }
//    /**
//     * 获取:护理评价
//     */
//    public String getHlpj() {
//        return hlpj;
//    }
//    /**
//     * 设置:停止日期
//     */
//    public void setTzrq(Timestamp value) {
//        this.tzrq = value;
//    }
//    /**
//     * 获取:停止日期
//     */
//    public Timestamp getTzrq() {
//        return tzrq;
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