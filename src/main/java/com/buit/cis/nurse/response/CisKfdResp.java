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
// * 类名称：CisKfd<br> 
// * 类描述：口服单<br>
// * @author GONGFANGZHOU
// */
//@ApiModel(value="口服单")
//public class CisKfdResp  extends  PageQuery{
//    @ApiModelProperty(value="口服单号")
//    private Integer kfdh;
//    @ApiModelProperty(value="住院号")
//    private Integer zyh;
//    @ApiModelProperty(value="病人病区")
//    private Integer brbq;
//    @ApiModelProperty(value="病人床号")
//    private String brch;
//    @ApiModelProperty(value="药品用法")
//    private Integer ypyf;
//    @ApiModelProperty(value="口服日期")
//    private Timestamp kfrq;
//    @ApiModelProperty(value="口服时间")
//    private Timestamp kfsj;
//    @ApiModelProperty(value="口服时点")
//    private Integer kfsd;
//    @ApiModelProperty(value="包药包数")
//    private Integer bybs;
//    @ApiModelProperty(value="包药时间")
//    private Timestamp bysj;
//    @ApiModelProperty(value="包药工号")
//    private Integer bygh;
//    @ApiModelProperty(value="包药来源")
//    private Integer byly;
//    @ApiModelProperty(value="来源标识")
//    private String lybs;
//    @ApiModelProperty(value="机构代码")
//    private Integer jgid;
//    @ApiModelProperty(value="住院号码")
//    private String zyhm;
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
//     * 设置:病人病区
//     */
//    public void setBrbq(Integer value) {
//        this.brbq = value;
//    }
//    /**
//     * 获取:病人病区
//     */
//    public Integer getBrbq() {
//        return brbq;
//    }
//    /**
//     * 设置:病人床号
//     */
//    public void setBrch(String value) {
//        this.brch = value;
//    }
//    /**
//     * 获取:病人床号
//     */
//    public String getBrch() {
//        return brch;
//    }
//    /**
//     * 设置:药品用法
//     */
//    public void setYpyf(Integer value) {
//        this.ypyf = value;
//    }
//    /**
//     * 获取:药品用法
//     */
//    public Integer getYpyf() {
//        return ypyf;
//    }
//    /**
//     * 设置:口服日期
//     */
//    public void setKfrq(Timestamp value) {
//        this.kfrq = value;
//    }
//    /**
//     * 获取:口服日期
//     */
//    public Timestamp getKfrq() {
//        return kfrq;
//    }
//    /**
//     * 设置:口服时间
//     */
//    public void setKfsj(Timestamp value) {
//        this.kfsj = value;
//    }
//    /**
//     * 获取:口服时间
//     */
//    public Timestamp getKfsj() {
//        return kfsj;
//    }
//    /**
//     * 设置:口服时点
//     */
//    public void setKfsd(Integer value) {
//        this.kfsd = value;
//    }
//    /**
//     * 获取:口服时点
//     */
//    public Integer getKfsd() {
//        return kfsd;
//    }
//    /**
//     * 设置:包药包数
//     */
//    public void setBybs(Integer value) {
//        this.bybs = value;
//    }
//    /**
//     * 获取:包药包数
//     */
//    public Integer getBybs() {
//        return bybs;
//    }
//    /**
//     * 设置:包药时间
//     */
//    public void setBysj(Timestamp value) {
//        this.bysj = value;
//    }
//    /**
//     * 获取:包药时间
//     */
//    public Timestamp getBysj() {
//        return bysj;
//    }
//    /**
//     * 设置:包药工号
//     */
//    public void setBygh(Integer value) {
//        this.bygh = value;
//    }
//    /**
//     * 获取:包药工号
//     */
//    public Integer getBygh() {
//        return bygh;
//    }
//    /**
//     * 设置:包药来源
//     */
//    public void setByly(Integer value) {
//        this.byly = value;
//    }
//    /**
//     * 获取:包药来源
//     */
//    public Integer getByly() {
//        return byly;
//    }
//    /**
//     * 设置:来源标识
//     */
//    public void setLybs(String value) {
//        this.lybs = value;
//    }
//    /**
//     * 获取:来源标识
//     */
//    public String getLybs() {
//        return lybs;
//    }
//    /**
//     * 设置:机构代码
//     */
//    public void setJgid(Integer value) {
//        this.jgid = value;
//    }
//    /**
//     * 获取:机构代码
//     */
//    public Integer getJgid() {
//        return jgid;
//    }
//    /**
//     * 设置:住院号码
//     */
//    public void setZyhm(String value) {
//        this.zyhm = value;
//    }
//    /**
//     * 获取:住院号码
//     */
//    public String getZyhm() {
//        return zyhm;
//    }
//}