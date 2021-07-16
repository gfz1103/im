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
// * 类名称：EnrJl01<br> 
// * 类描述：<br>
// * @author GONGFANGZHOU
// */
//@ApiModel(value="")
//public class EnrJl01Resp  extends  PageQuery{
//    @ApiModelProperty(value="病历编号")
//    private Integer jlbh;
//    @ApiModelProperty(value="住院流水号:IM_HZRY.ZYH")
//    private Integer zyh;
//    @ApiModelProperty(value="结构编号")
//    private Integer jgbh;
//    @ApiModelProperty(value="记录名称")
//    private String jlmc;
//    @ApiModelProperty(value="病历类型:0病历 1病程 2表格")
//    private Integer bllx;
//    @ApiModelProperty(value="病历类别:EMR_KBM_BLLB关联,展现病历夹,控制续页。")
//    private Integer bllb;
//    @ApiModelProperty(value="模板类别:EMR_KBM_BLLB关联,控制医疗权限。")
//    private Integer mblb;
//    @ApiModelProperty(value="关联模板:护理病历填写关联模板，护理记录填写关联数据窗口名")
//    private Integer mbbh;
//    @ApiModelProperty(value="段落类别:用于护理病历")
//    private Integer dllb;
//    @ApiModelProperty(value="段落键:用于护理病历")
//    private String dlj;
//    @ApiModelProperty(value="记录行数:用于表格式病历")
//    private Integer jlhs;
//    @ApiModelProperty(value="换页标志:用于表格式病历")
//    private Integer hybz;
//    @ApiModelProperty(value="记录时间:用户填写的时间，可不填")
//    private Timestamp jlsj;
//    @ApiModelProperty(value="书写时间:用于续打，支持插入操作")
//    private Timestamp sxsj;
//    @ApiModelProperty(value="系统时间:服务器时间")
//    private Timestamp xtsj;
//    @ApiModelProperty(value="书写病区")
//    private Integer sxbq;
//    @ApiModelProperty(value="书写护士")
//    private String sxhs;
//    @ApiModelProperty(value="完成签名:完成护士的签名流水号")
//    private Integer wcqm;
//    @ApiModelProperty(value="完成时间")
//    private Timestamp wcsj;
//    @ApiModelProperty(value="审阅标志:0否 1是")
//    private Integer sybz;
//    @ApiModelProperty(value="审阅时间")
//    private Timestamp sysj;
//    @ApiModelProperty(value="审阅护士")
//    private String syhs;
//    @ApiModelProperty(value="审阅签名:审阅护士的签名流水号")
//    private Integer syqm;
//    @ApiModelProperty(value="打印标志:0否 1是")
//    private Integer dybz;
//    @ApiModelProperty(value="记录状态:0书写 1完成 2封存(归档) 9删除 DICT108")
//    private Integer jlzt;
//    @ApiModelProperty(value="总结编号")
//    private Integer zjbh;
//    @ApiModelProperty(value="总结名称")
//    private String zjmc;
//    @ApiModelProperty(value="总结类型(1小结 2总结 Dict117)")
//    private Integer zjlx;
//    @ApiModelProperty(value="开始时间")
//    private Timestamp kssj;
//    @ApiModelProperty(value="结束时间")
//    private Timestamp jssj;
//    @ApiModelProperty(value="独立换行标志")
//    private Integer dlhhbz;
//    @ApiModelProperty(value="wcdllj")
//    private String wcdllj;
//    @ApiModelProperty(value="sydllj")
//    private String sydllj;
//    @ApiModelProperty(value="监控分类（三控单）")
//    private String jkfl;
//    @ApiModelProperty(value="监控评分（三控单）")
//    private Integer jkpf;
//    @ApiModelProperty(value="监控转归（三控单）")
//    private String jkzg;
//    @ApiModelProperty(value="jgid")
//    private Integer jgid;
//    /**
//     * 设置:病历编号
//     */
//    public void setJlbh(Integer value) {
//        this.jlbh = value;
//    }
//    /**
//     * 获取:病历编号
//     */
//    public Integer getJlbh() {
//        return jlbh;
//    }
//    /**
//     * 设置:住院流水号:IM_HZRY.ZYH
//     */
//    public void setZyh(Integer value) {
//        this.zyh = value;
//    }
//    /**
//     * 获取:住院流水号:IM_HZRY.ZYH
//     */
//    public Integer getZyh() {
//        return zyh;
//    }
//    /**
//     * 设置:结构编号
//     */
//    public void setJgbh(Integer value) {
//        this.jgbh = value;
//    }
//    /**
//     * 获取:结构编号
//     */
//    public Integer getJgbh() {
//        return jgbh;
//    }
//    /**
//     * 设置:记录名称
//     */
//    public void setJlmc(String value) {
//        this.jlmc = value;
//    }
//    /**
//     * 获取:记录名称
//     */
//    public String getJlmc() {
//        return jlmc;
//    }
//    /**
//     * 设置:病历类型:0病历 1病程 2表格
//     */
//    public void setBllx(Integer value) {
//        this.bllx = value;
//    }
//    /**
//     * 获取:病历类型:0病历 1病程 2表格
//     */
//    public Integer getBllx() {
//        return bllx;
//    }
//    /**
//     * 设置:病历类别:EMR_KBM_BLLB关联,展现病历夹,控制续页。
//     */
//    public void setBllb(Integer value) {
//        this.bllb = value;
//    }
//    /**
//     * 获取:病历类别:EMR_KBM_BLLB关联,展现病历夹,控制续页。
//     */
//    public Integer getBllb() {
//        return bllb;
//    }
//    /**
//     * 设置:模板类别:EMR_KBM_BLLB关联,控制医疗权限。
//     */
//    public void setMblb(Integer value) {
//        this.mblb = value;
//    }
//    /**
//     * 获取:模板类别:EMR_KBM_BLLB关联,控制医疗权限。
//     */
//    public Integer getMblb() {
//        return mblb;
//    }
//    /**
//     * 设置:关联模板:护理病历填写关联模板，护理记录填写关联数据窗口名
//     */
//    public void setMbbh(Integer value) {
//        this.mbbh = value;
//    }
//    /**
//     * 获取:关联模板:护理病历填写关联模板，护理记录填写关联数据窗口名
//     */
//    public Integer getMbbh() {
//        return mbbh;
//    }
//    /**
//     * 设置:段落类别:用于护理病历
//     */
//    public void setDllb(Integer value) {
//        this.dllb = value;
//    }
//    /**
//     * 获取:段落类别:用于护理病历
//     */
//    public Integer getDllb() {
//        return dllb;
//    }
//    /**
//     * 设置:段落键:用于护理病历
//     */
//    public void setDlj(String value) {
//        this.dlj = value;
//    }
//    /**
//     * 获取:段落键:用于护理病历
//     */
//    public String getDlj() {
//        return dlj;
//    }
//    /**
//     * 设置:记录行数:用于表格式病历
//     */
//    public void setJlhs(Integer value) {
//        this.jlhs = value;
//    }
//    /**
//     * 获取:记录行数:用于表格式病历
//     */
//    public Integer getJlhs() {
//        return jlhs;
//    }
//    /**
//     * 设置:换页标志:用于表格式病历
//     */
//    public void setHybz(Integer value) {
//        this.hybz = value;
//    }
//    /**
//     * 获取:换页标志:用于表格式病历
//     */
//    public Integer getHybz() {
//        return hybz;
//    }
//    /**
//     * 设置:记录时间:用户填写的时间，可不填
//     */
//    public void setJlsj(Timestamp value) {
//        this.jlsj = value;
//    }
//    /**
//     * 获取:记录时间:用户填写的时间，可不填
//     */
//    public Timestamp getJlsj() {
//        return jlsj;
//    }
//    /**
//     * 设置:书写时间:用于续打，支持插入操作
//     */
//    public void setSxsj(Timestamp value) {
//        this.sxsj = value;
//    }
//    /**
//     * 获取:书写时间:用于续打，支持插入操作
//     */
//    public Timestamp getSxsj() {
//        return sxsj;
//    }
//    /**
//     * 设置:系统时间:服务器时间
//     */
//    public void setXtsj(Timestamp value) {
//        this.xtsj = value;
//    }
//    /**
//     * 获取:系统时间:服务器时间
//     */
//    public Timestamp getXtsj() {
//        return xtsj;
//    }
//    /**
//     * 设置:书写病区
//     */
//    public void setSxbq(Integer value) {
//        this.sxbq = value;
//    }
//    /**
//     * 获取:书写病区
//     */
//    public Integer getSxbq() {
//        return sxbq;
//    }
//    /**
//     * 设置:书写护士
//     */
//    public void setSxhs(String value) {
//        this.sxhs = value;
//    }
//    /**
//     * 获取:书写护士
//     */
//    public String getSxhs() {
//        return sxhs;
//    }
//    /**
//     * 设置:完成签名:完成护士的签名流水号
//     */
//    public void setWcqm(Integer value) {
//        this.wcqm = value;
//    }
//    /**
//     * 获取:完成签名:完成护士的签名流水号
//     */
//    public Integer getWcqm() {
//        return wcqm;
//    }
//    /**
//     * 设置:完成时间
//     */
//    public void setWcsj(Timestamp value) {
//        this.wcsj = value;
//    }
//    /**
//     * 获取:完成时间
//     */
//    public Timestamp getWcsj() {
//        return wcsj;
//    }
//    /**
//     * 设置:审阅标志:0否 1是
//     */
//    public void setSybz(Integer value) {
//        this.sybz = value;
//    }
//    /**
//     * 获取:审阅标志:0否 1是
//     */
//    public Integer getSybz() {
//        return sybz;
//    }
//    /**
//     * 设置:审阅时间
//     */
//    public void setSysj(Timestamp value) {
//        this.sysj = value;
//    }
//    /**
//     * 获取:审阅时间
//     */
//    public Timestamp getSysj() {
//        return sysj;
//    }
//    /**
//     * 设置:审阅护士
//     */
//    public void setSyhs(String value) {
//        this.syhs = value;
//    }
//    /**
//     * 获取:审阅护士
//     */
//    public String getSyhs() {
//        return syhs;
//    }
//    /**
//     * 设置:审阅签名:审阅护士的签名流水号
//     */
//    public void setSyqm(Integer value) {
//        this.syqm = value;
//    }
//    /**
//     * 获取:审阅签名:审阅护士的签名流水号
//     */
//    public Integer getSyqm() {
//        return syqm;
//    }
//    /**
//     * 设置:打印标志:0否 1是
//     */
//    public void setDybz(Integer value) {
//        this.dybz = value;
//    }
//    /**
//     * 获取:打印标志:0否 1是
//     */
//    public Integer getDybz() {
//        return dybz;
//    }
//    /**
//     * 设置:记录状态:0书写 1完成 2封存(归档) 9删除 DICT108
//     */
//    public void setJlzt(Integer value) {
//        this.jlzt = value;
//    }
//    /**
//     * 获取:记录状态:0书写 1完成 2封存(归档) 9删除 DICT108
//     */
//    public Integer getJlzt() {
//        return jlzt;
//    }
//    /**
//     * 设置:总结编号
//     */
//    public void setZjbh(Integer value) {
//        this.zjbh = value;
//    }
//    /**
//     * 获取:总结编号
//     */
//    public Integer getZjbh() {
//        return zjbh;
//    }
//    /**
//     * 设置:总结名称
//     */
//    public void setZjmc(String value) {
//        this.zjmc = value;
//    }
//    /**
//     * 获取:总结名称
//     */
//    public String getZjmc() {
//        return zjmc;
//    }
//    /**
//     * 设置:总结类型(1小结 2总结 Dict117)
//     */
//    public void setZjlx(Integer value) {
//        this.zjlx = value;
//    }
//    /**
//     * 获取:总结类型(1小结 2总结 Dict117)
//     */
//    public Integer getZjlx() {
//        return zjlx;
//    }
//    /**
//     * 设置:开始时间
//     */
//    public void setKssj(Timestamp value) {
//        this.kssj = value;
//    }
//    /**
//     * 获取:开始时间
//     */
//    public Timestamp getKssj() {
//        return kssj;
//    }
//    /**
//     * 设置:结束时间
//     */
//    public void setJssj(Timestamp value) {
//        this.jssj = value;
//    }
//    /**
//     * 获取:结束时间
//     */
//    public Timestamp getJssj() {
//        return jssj;
//    }
//    /**
//     * 设置:独立换行标志
//     */
//    public void setDlhhbz(Integer value) {
//        this.dlhhbz = value;
//    }
//    /**
//     * 获取:独立换行标志
//     */
//    public Integer getDlhhbz() {
//        return dlhhbz;
//    }
//    /**
//     * 设置:wcdllj
//     */
//    public void setWcdllj(String value) {
//        this.wcdllj = value;
//    }
//    /**
//     * 获取:wcdllj
//     */
//    public String getWcdllj() {
//        return wcdllj;
//    }
//    /**
//     * 设置:sydllj
//     */
//    public void setSydllj(String value) {
//        this.sydllj = value;
//    }
//    /**
//     * 获取:sydllj
//     */
//    public String getSydllj() {
//        return sydllj;
//    }
//    /**
//     * 设置:监控分类（三控单）
//     */
//    public void setJkfl(String value) {
//        this.jkfl = value;
//    }
//    /**
//     * 获取:监控分类（三控单）
//     */
//    public String getJkfl() {
//        return jkfl;
//    }
//    /**
//     * 设置:监控评分（三控单）
//     */
//    public void setJkpf(Integer value) {
//        this.jkpf = value;
//    }
//    /**
//     * 获取:监控评分（三控单）
//     */
//    public Integer getJkpf() {
//        return jkpf;
//    }
//    /**
//     * 设置:监控转归（三控单）
//     */
//    public void setJkzg(String value) {
//        this.jkzg = value;
//    }
//    /**
//     * 获取:监控转归（三控单）
//     */
//    public String getJkzg() {
//        return jkzg;
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
//}
