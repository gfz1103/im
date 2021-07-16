package com.buit.cis.ims.response;

import com.buit.commons.PageQuery;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;
import java.sql.Timestamp;


/**
 * 类名称：ImFeeFymx<br>
 * 类描述：费用明细表<br>
 * @author ZHOUHAISHENG
 */
@ApiModel(value="费用明细表")
public class ImFeeFymxResp  extends PageQuery {
    @ApiModelProperty(value="记录序号")
    private Long jlxh;
    @ApiModelProperty(value="机构ID")
    private String jgid;
    @ApiModelProperty(value="住院号")
    private Long zyh;
    @ApiModelProperty(value="费用日期")
    private Timestamp fyrq;
    @ApiModelProperty(value="费用序号")
    private Long fyxh;
    @ApiModelProperty(value="费用名称")
    private String fymc;
    @ApiModelProperty(value="药品产地")
    private Long ypcd;
    @ApiModelProperty(value="费用数量")
    private BigDecimal fysl;
    @ApiModelProperty(value="费用单价")
    private BigDecimal fydj;
    @ApiModelProperty(value="总计金额")
    private BigDecimal zjje;
    @ApiModelProperty(value="自负金额")
    private BigDecimal zfje;
    @ApiModelProperty(value="医生工号")
    private String ysgh;
    @ApiModelProperty(value="输入工号")
    private String srgh;
    @ApiModelProperty(value="确认工号")
    private String qrgh;
    @ApiModelProperty(value="费用病区 | 费用发生的病区")
    private Long fybq;
    @ApiModelProperty(value="费用科室 | 费用输入的科室(记帐,按输入科室核算时要用)")
    private Long fyks;
    @ApiModelProperty(value="执行科室 | 费用记帐科室(记帐,按执行科室核算时使用)")
    private Long zxks;
    @ApiModelProperty(value="记费日期 | 实际记费日期 写IM_FEE_FYMX时服务器时间")
    private Timestamp jfrq;
    @ApiModelProperty(value="项目类型 | 1：病区系统记帐 2：药房系统记帐 3：医技系统记帐 4：住院系统记帐 5：手术麻醉记帐 9：自动累加费用")
    private Integer xmlx;
    @ApiModelProperty(value="药品类型 | 0：费用 1：西药 2：中成药 3：中草药")
    private Boolean yplx;
    @ApiModelProperty(value="费用项目 | 指定FYXH归并的项目(同GY_SFMX表中SFXM对应)")
    private Long fyxm;
    @ApiModelProperty(value="结算次数")
    private Integer jscs;
    @ApiModelProperty(value="自负比例 | 同FEE_SFDLZFBL或 FEE_YPXZ,PUB_FYXZ表中自负比例对应")
    private BigDecimal zfbl;
    @ApiModelProperty(value="医嘱序号 | 同CIS_HZYZ表中的医嘱序号对应")
    private Long yzxh;
    @ApiModelProperty(value="汇总日期")
    private Timestamp hzrq;
    @ApiModelProperty(value="月结日期")
    private String yjrq;
    @ApiModelProperty(value="自理金额")
    private BigDecimal zlje;
    @ApiModelProperty(value="诊疗小组")
    private Integer zlxz;
    @ApiModelProperty(value="婴儿判别 | 0,大人  1  小孩")
    private Boolean yepb;
    @ApiModelProperty(value="打折比例")
    private BigDecimal dzbl;
    @ApiModelProperty(value="上传标志")
    private String scbz;
    @ApiModelProperty(value="xflsh")
    private String xflsh;
    @ApiModelProperty(value="折扣比例")
    private BigDecimal zkbl;
    @ApiModelProperty(value="折扣金额")
    private BigDecimal zkje;
    @ApiModelProperty(value="折后金额")
    private BigDecimal zhje;
    @ApiModelProperty(value="高价药标志,1:高价药中途结算完时标记位。15.高价药出院结算完成时标记位")
    private String gjybz;
    @ApiModelProperty(value = "补记账 作废工号")
    private String zfgh;
    @ApiModelProperty(value="补记账 作废关联序号,作废前的记录序号")
    private String zfglxh;
    @ApiModelProperty(value="大病减负标志 1:尿毒症透析医疗费用,2:肾移植减负,3：精神病减负  其他：不减负")
    private String dbjfbz;
    @ApiModelProperty(value="材料项目分组合并 ")
    private String cldym;
    /**
     * 设置:记录序号
     */
    public void setJlxh(Long value) {
        this.jlxh = value;
    }
    /**
     * 获取:记录序号
     */
    public Long getJlxh() {
        return jlxh;
    }
    /**
     * 设置:机构ID
     */
    public void setJgid(String value) {
        this.jgid = value;
    }
    /**
     * 获取:机构ID
     */
    public String getJgid() {
        return jgid;
    }
    /**
     * 设置:住院号
     */
    public void setZyh(Long value) {
        this.zyh = value;
    }
    /**
     * 获取:住院号
     */
    public Long getZyh() {
        return zyh;
    }
    /**
     * 设置:费用日期
     */
    public void setFyrq(Timestamp value) {
        this.fyrq = value;
    }
    /**
     * 获取:费用日期
     */
    public Timestamp getFyrq() {
        return fyrq;
    }
    /**
     * 设置:费用序号
     */
    public void setFyxh(Long value) {
        this.fyxh = value;
    }
    /**
     * 获取:费用序号
     */
    public Long getFyxh() {
        return fyxh;
    }
    /**
     * 设置:费用名称
     */
    public void setFymc(String value) {
        this.fymc = value;
    }
    /**
     * 获取:费用名称
     */
    public String getFymc() {
        return fymc;
    }
    /**
     * 设置:药品产地
     */
    public void setYpcd(Long value) {
        this.ypcd = value;
    }
    /**
     * 获取:药品产地
     */
    public Long getYpcd() {
        return ypcd;
    }
    /**
     * 设置:费用数量
     */
    public void setFysl(BigDecimal value) {
        this.fysl = value;
    }
    /**
     * 获取:费用数量
     */
    public BigDecimal getFysl() {
        return fysl;
    }
    /**
     * 设置:费用单价
     */
    public void setFydj(BigDecimal value) {
        this.fydj = value;
    }
    /**
     * 获取:费用单价
     */
    public BigDecimal getFydj() {
        return fydj;
    }
    /**
     * 设置:总计金额
     */
    public void setZjje(BigDecimal value) {
        this.zjje = value;
    }
    /**
     * 获取:总计金额
     */
    public BigDecimal getZjje() {
        return zjje;
    }
    /**
     * 设置:自负金额
     */
    public void setZfje(BigDecimal value) {
        this.zfje = value;
    }
    /**
     * 获取:自负金额
     */
    public BigDecimal getZfje() {
        return zfje;
    }
    /**
     * 设置:医生工号
     */
    public void setYsgh(String value) {
        this.ysgh = value;
    }
    /**
     * 获取:医生工号
     */
    public String getYsgh() {
        return ysgh;
    }
    /**
     * 设置:输入工号
     */
    public void setSrgh(String value) {
        this.srgh = value;
    }
    /**
     * 获取:输入工号
     */
    public String getSrgh() {
        return srgh;
    }
    /**
     * 设置:确认工号
     */
    public void setQrgh(String value) {
        this.qrgh = value;
    }
    /**
     * 获取:确认工号
     */
    public String getQrgh() {
        return qrgh;
    }
    /**
     * 设置:费用病区 | 费用发生的病区
     */
    public void setFybq(Long value) {
        this.fybq = value;
    }
    /**
     * 获取:费用病区 | 费用发生的病区
     */
    public Long getFybq() {
        return fybq;
    }
    /**
     * 设置:费用科室 | 费用输入的科室(记帐,按输入科室核算时要用)
     */
    public void setFyks(Long value) {
        this.fyks = value;
    }
    /**
     * 获取:费用科室 | 费用输入的科室(记帐,按输入科室核算时要用)
     */
    public Long getFyks() {
        return fyks;
    }
    /**
     * 设置:执行科室 | 费用记帐科室(记帐,按执行科室核算时使用)
     */
    public void setZxks(Long value) {
        this.zxks = value;
    }
    /**
     * 获取:执行科室 | 费用记帐科室(记帐,按执行科室核算时使用)
     */
    public Long getZxks() {
        return zxks;
    }
    /**
     * 设置:记费日期 | 实际记费日期 写IM_FEE_FYMX时服务器时间
     */
    public void setJfrq(Timestamp value) {
        this.jfrq = value;
    }
    /**
     * 获取:记费日期 | 实际记费日期 写IM_FEE_FYMX时服务器时间
     */
    public Timestamp getJfrq() {
        return jfrq;
    }
    /**
     * 设置:项目类型 | 1：病区系统记帐 2：药房系统记帐 3：医技系统记帐 4：住院系统记帐 5：手术麻醉记帐 9：自动累加费用
     */
    public void setXmlx(Integer value) {
        this.xmlx = value;
    }
    /**
     * 获取:项目类型 | 1：病区系统记帐 2：药房系统记帐 3：医技系统记帐 4：住院系统记帐 5：手术麻醉记帐 9：自动累加费用
     */
    public Integer getXmlx() {
        return xmlx;
    }
    /**
     * 设置:药品类型 | 0：费用 1：西药 2：中成药 3：中草药
     */
    public void setYplx(Boolean value) {
        this.yplx = value;
    }
    /**
     * 获取:药品类型 | 0：费用 1：西药 2：中成药 3：中草药
     */
    public Boolean getYplx() {
        return yplx;
    }
    /**
     * 设置:费用项目 | 指定FYXH归并的项目(同GY_SFMX表中SFXM对应)
     */
    public void setFyxm(Long value) {
        this.fyxm = value;
    }
    /**
     * 获取:费用项目 | 指定FYXH归并的项目(同GY_SFMX表中SFXM对应)
     */
    public Long getFyxm() {
        return fyxm;
    }
    /**
     * 设置:结算次数
     */
    public void setJscs(Integer value) {
        this.jscs = value;
    }
    /**
     * 获取:结算次数
     */
    public Integer getJscs() {
        return jscs;
    }
    /**
     * 设置:自负比例 | 同FEE_SFDLZFBL或 FEE_YPXZ,PUB_FYXZ表中自负比例对应
     */
    public void setZfbl(BigDecimal value) {
        this.zfbl = value;
    }
    /**
     * 获取:自负比例 | 同FEE_SFDLZFBL或 FEE_YPXZ,PUB_FYXZ表中自负比例对应
     */
    public BigDecimal getZfbl() {
        return zfbl;
    }
    /**
     * 设置:医嘱序号 | 同CIS_HZYZ表中的医嘱序号对应
     */
    public void setYzxh(Long value) {
        this.yzxh = value;
    }
    /**
     * 获取:医嘱序号 | 同CIS_HZYZ表中的医嘱序号对应
     */
    public Long getYzxh() {
        return yzxh;
    }
    /**
     * 设置:汇总日期
     */
    public void setHzrq(Timestamp value) {
        this.hzrq = value;
    }
    /**
     * 获取:汇总日期
     */
    public Timestamp getHzrq() {
        return hzrq;
    }
    /**
     * 设置:月结日期
     */
    public void setYjrq(String value) {
        this.yjrq = value;
    }
    /**
     * 获取:月结日期
     */
    public String getYjrq() {
        return yjrq;
    }
    /**
     * 设置:自理金额
     */
    public void setZlje(BigDecimal value) {
        this.zlje = value;
    }
    /**
     * 获取:自理金额
     */
    public BigDecimal getZlje() {
        return zlje;
    }
    /**
     * 设置:诊疗小组
     */
    public void setZlxz(Integer value) {
        this.zlxz = value;
    }
    /**
     * 获取:诊疗小组
     */
    public Integer getZlxz() {
        return zlxz;
    }
    /**
     * 设置:婴儿判别 | 0,大人  1  小孩
     */
    public void setYepb(Boolean value) {
        this.yepb = value;
    }
    /**
     * 获取:婴儿判别 | 0,大人  1  小孩
     */
    public Boolean getYepb() {
        return yepb;
    }
    /**
     * 设置:打折比例
     */
    public void setDzbl(BigDecimal value) {
        this.dzbl = value;
    }
    /**
     * 获取:打折比例
     */
    public BigDecimal getDzbl() {
        return dzbl;
    }
    /**
     * 设置:上传标志
     */
    public void setScbz(String value) {
        this.scbz = value;
    }
    /**
     * 获取:上传标志
     */
    public String getScbz() {
        return scbz;
    }
    /**
     * 设置:xflsh
     */
    public void setXflsh(String value) {
        this.xflsh = value;
    }
    /**
     * 获取:xflsh
     */
    public String getXflsh() {
        return xflsh;
    }
    /**
     * 设置:折扣比例
     */
    public void setZkbl(BigDecimal value) {
        this.zkbl = value;
    }
    /**
     * 获取:折扣比例
     */
    public BigDecimal getZkbl() {
        return zkbl;
    }
    /**
     * 设置:折扣金额
     */
    public void setZkje(BigDecimal value) {
        this.zkje = value;
    }
    /**
     * 获取:折扣金额
     */
    public BigDecimal getZkje() {
        return zkje;
    }
    /**
     * 设置:折后金额
     */
    public void setZhje(BigDecimal value) {
        this.zhje = value;
    }
    /**
     * 获取:折后金额
     */
    public BigDecimal getZhje() {
        return zhje;
    }
    /**
     * 设置:高价药标志,1:高价药中途结算完时标记位。15.高价药出院结算完成时标记位
     */
    public void setGjybz(String value) {
        this.gjybz = value;
    }
    /**
     * 获取:高价药标志,1:高价药中途结算完时标记位。15.高价药出院结算完成时标记位
     */
    public String getGjybz() {
        return gjybz;
    }

    /**
     * 设置:补记账 作废工号
     */
    public void setZfgh(String value) {
        this.zfgh = value;
    }

    /**
     * 获取:补记账 作废工号
     */
    public String getZfgh() {
        return zfgh;
    }
    /**
     * 设置:补记账 作废关联序号,作废前的记录序号
     */
    public void setZfglxh(String value) {
        this.zfglxh = value;
    }
    /**
     * 获取:补记账 作废关联序号,作废前的记录序号
     */
    public String getZfglxh() {
        return zfglxh;
    }
    /**
     * 设置:大病减负标志 1:尿毒症透析医疗费用,2:肾移植减负,3：精神病减负  其他：不减负
     */
    public void setDbjfbz(String value) {
        this.dbjfbz = value;
    }
    /**
     * 获取:大病减负标志 1:尿毒症透析医疗费用,2:肾移植减负,3：精神病减负  其他：不减负
     */
    public String getDbjfbz() {
        return dbjfbz;
    }
    /**
     * 设置:材料项目分组合并
     */
    public void setCldym(String value) {
        this.cldym = value;
    }
    /**
     * 获取:材料项目分组合并
     */
    public String getCldym() {
        return cldym;
    }
}
