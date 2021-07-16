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
// * 类名称：GyCsrz<br> 
// * 类描述：<br>
// * @author GONGFANGZHOU
// */
//@ApiModel(value="")
//public class GyCsrzResp  extends  PageQuery{
//    @ApiModelProperty(value="日志序号")
//    private Integer rzxh;
//    @ApiModelProperty(value="机构ID")
//    private Integer jgid;
//    @ApiModelProperty(value="日志时间")
//    private Timestamp rzsj;
//    @ApiModelProperty(value="日志类型 0:无数据   1:成功   2:失败")
//    private Integer rzlx;
//    @ApiModelProperty(value="日志信息")
//    private String rzxx;
//    /**
//     * 设置:日志序号
//     */
//    public void setRzxh(Integer value) {
//        this.rzxh = value;
//    }
//    /**
//     * 获取:日志序号
//     */
//    public Integer getRzxh() {
//        return rzxh;
//    }
//    /**
//     * 设置:机构ID
//     */
//    public void setJgid(Integer value) {
//        this.jgid = value;
//    }
//    /**
//     * 获取:机构ID
//     */
//    public Integer getJgid() {
//        return jgid;
//    }
//    /**
//     * 设置:日志时间
//     */
//    public void setRzsj(Timestamp value) {
//        this.rzsj = value;
//    }
//    /**
//     * 获取:日志时间
//     */
//    public Timestamp getRzsj() {
//        return rzsj;
//    }
//    /**
//     * 设置:日志类型 0:无数据   1:成功   2:失败
//     */
//    public void setRzlx(Integer value) {
//        this.rzlx = value;
//    }
//    /**
//     * 获取:日志类型 0:无数据   1:成功   2:失败
//     */
//    public Integer getRzlx() {
//        return rzlx;
//    }
//    /**
//     * 设置:日志信息
//     */
//    public void setRzxx(String value) {
//        this.rzxx = value;
//    }
//    /**
//     * 获取:日志信息
//     */
//    public String getRzxx() {
//        return rzxx;
//    }
//}