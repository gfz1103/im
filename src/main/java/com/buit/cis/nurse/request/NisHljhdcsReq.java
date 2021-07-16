//   
//package com.buit.cis.nurse.request;
//
//import java.sql.Timestamp;
//
//import com.buit.commons.PageQuery;
//import io.swagger.annotations.ApiModel;
//import io.swagger.annotations.ApiModelProperty;
//
//
///**
// * 类名称：NisHljhdcs<br> 
// * 类描述：护理执行单子表<br>
// * @author GONGFANGZHOU
// */
//@ApiModel(value="护理执行单子表")
//public class NisHljhdcsReq  extends  PageQuery{
//    @ApiModelProperty(value="主键记录序号")
//    private Integer jlxh;
//    @ApiModelProperty(value="执行单主表记录序号")
//    private Integer zxjlxh;
//    @ApiModelProperty(value="护理措施")
//    private String hlcs;
//    /**
//     * 设置:主键记录序号
//     */
//    public void setJlxh(Integer value) {
//        this.jlxh = value;
//    }
//    /**
//     * 获取:主键记录序号
//     */
//    public Integer getJlxh() {
//        return jlxh;
//    }
//    /**
//     * 设置:执行单主表记录序号
//     */
//    public void setZxjlxh(Integer value) {
//        this.zxjlxh = value;
//    }
//    /**
//     * 获取:执行单主表记录序号
//     */
//    public Integer getZxjlxh() {
//        return zxjlxh;
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
//}