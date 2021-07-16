package com.buit.cis.ims.model;

import com.buit.commons.PageQuery;

import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * 类名称：ImFeeFymxJs<br>
 * 类描述：住院_结算费用明细 | 在住院结算后产生该表<br>
 * @author zhouhaisheng
 */
public class ImFeeFymxJs  extends PageQuery {
    /** 记录序号 */
    private Integer jlxh;
    /** 机构代码 */
    private Integer jgid;
    /** 住院号 */
    private Integer zyh;
    /** 费用日期 */
    private Timestamp fyrq;
    /** 费用序号 */
    private Integer fyxh;
    /** 费用名称 */
    private String fymc;
    /** 药品产地 */
    private Integer ypcd;
    /** 费用数量 */
    private BigDecimal fysl;
    /** 费用单价 */
    private BigDecimal fydj;
    /** 总计金额 */
    private BigDecimal zjje;
    /** 自负金额 */
    private BigDecimal zfje;
    /** 医生工号 */
    private String ysgh;
    /** 输入工号 */
    private String srgh;
    /** 确认工号 */
    private String qrgh;
    /** 费用病区 */
    private Integer fybq;
    /** 费用科室 */
    private Integer fyks;
    /** 执行科室 */
    private Integer zxks;
    /** 计费日期 */
    private Timestamp jfrq;
    /** 项目类型 | 1：病区系统记帐 2：药房系统记帐 3：医技系统记帐 4：住院系统记帐 5：手术麻醉记帐 9：自动累加费用 */
    private Integer xmlx;
    /** 药品类型 | 0：费用 1：西药 2：中成药 3：中草药 */
    private Integer yplx;
    /** 费用项目 | 指定FYXH归并的项目(同GY_SFMX表中SFXM对应) */
    private Integer fyxm;
    /** 结算次数 */
    private Integer jscs;
    /** 自负比例 | 同FEE_SFDLZFBL或 FEE_YPXZ,PUB_FYXZ表中自负比例对应 */
    private BigDecimal zfbl;
    /** 医嘱序号 */
    private Integer yzxh;
    /** 汇总日期 */
    private Timestamp hzrq;
    /** 月结日期 */
    private String yjrq;
    /** 自理金额 */
    private BigDecimal zlje;
    /** 医疗小组 */
    private Integer zlxz;
    /** 婴儿判别 | 0 大人  1 小孩  */
    private Integer yepb;
    /** 打折比例 */
    private BigDecimal dzbl;
    /** 上传标志 */
    private String scbz;
    /** xflsh */
    private String xflsh;
    /** 折扣比例 */
    private BigDecimal zkbl;
    /** 折扣金额 */
    private BigDecimal zkje;
    /** 折后金额 */
    private BigDecimal zhje;
    /** 高价药标志,1:高价药中途结算完时标记位。15.高价药出院结算完成时标记位 */
    private String gjybz;
    /**
     * 补记账 作废工号
     */
    private String zfgh;
    /** 补记账 作废关联序号,作废前的记录序号 */
    private String zfglxh;
    /** 大病减负标志 1:尿毒症透析医疗费用,2:肾移植减负,3：精神病减负  其他：不减负 */
    private String dbjfbz;
    /** 材料项目分组合并  */
    private String cldym;


    public void setJlxh(Integer value) {
        this.jlxh = value;
    }

    public Integer getJlxh() {
        return jlxh;
    }


    public void setJgid(Integer value) {
        this.jgid = value;
    }

    public Integer getJgid() {
        return jgid;
    }


    public void setZyh(Integer value) {
        this.zyh = value;
    }

    public Integer getZyh() {
        return zyh;
    }


    public void setFyrq(Timestamp value) {
        this.fyrq = value;
    }

    public Timestamp getFyrq() {
        return fyrq;
    }


    public void setFyxh(Integer value) {
        this.fyxh = value;
    }

    public Integer getFyxh() {
        return fyxh;
    }


    public void setFymc(String value) {
        this.fymc = value;
    }

    public String getFymc() {
        return fymc;
    }


    public void setYpcd(Integer value) {
        this.ypcd = value;
    }

    public Integer getYpcd() {
        return ypcd;
    }


    public void setFysl(BigDecimal value) {
        this.fysl = value;
    }

    public BigDecimal getFysl() {
        return fysl;
    }


    public void setFydj(BigDecimal value) {
        this.fydj = value;
    }

    public BigDecimal getFydj() {
        return fydj;
    }


    public void setZjje(BigDecimal value) {
        this.zjje = value;
    }

    public BigDecimal getZjje() {
        return zjje;
    }


    public void setZfje(BigDecimal value) {
        this.zfje = value;
    }

    public BigDecimal getZfje() {
        return zfje;
    }


    public void setYsgh(String value) {
        this.ysgh = value;
    }

    public String getYsgh() {
        return ysgh;
    }


    public void setSrgh(String value) {
        this.srgh = value;
    }

    public String getSrgh() {
        return srgh;
    }


    public void setQrgh(String value) {
        this.qrgh = value;
    }

    public String getQrgh() {
        return qrgh;
    }


    public void setFybq(Integer value) {
        this.fybq = value;
    }

    public Integer getFybq() {
        return fybq;
    }


    public void setFyks(Integer value) {
        this.fyks = value;
    }

    public Integer getFyks() {
        return fyks;
    }


    public void setZxks(Integer value) {
        this.zxks = value;
    }

    public Integer getZxks() {
        return zxks;
    }


    public void setJfrq(Timestamp value) {
        this.jfrq = value;
    }

    public Timestamp getJfrq() {
        return jfrq;
    }


    public void setXmlx(Integer value) {
        this.xmlx = value;
    }

    public Integer getXmlx() {
        return xmlx;
    }


    public void setYplx(Integer value) {
        this.yplx = value;
    }

    public Integer getYplx() {
        return yplx;
    }


    public void setFyxm(Integer value) {
        this.fyxm = value;
    }

    public Integer getFyxm() {
        return fyxm;
    }


    public void setJscs(Integer value) {
        this.jscs = value;
    }

    public Integer getJscs() {
        return jscs;
    }


    public void setZfbl(BigDecimal value) {
        this.zfbl = value;
    }

    public BigDecimal getZfbl() {
        return zfbl;
    }


    public void setYzxh(Integer value) {
        this.yzxh = value;
    }

    public Integer getYzxh() {
        return yzxh;
    }


    public void setHzrq(Timestamp value) {
        this.hzrq = value;
    }

    public Timestamp getHzrq() {
        return hzrq;
    }


    public void setYjrq(String value) {
        this.yjrq = value;
    }

    public String getYjrq() {
        return yjrq;
    }


    public void setZlje(BigDecimal value) {
        this.zlje = value;
    }

    public BigDecimal getZlje() {
        return zlje;
    }


    public void setZlxz(Integer value) {
        this.zlxz = value;
    }

    public Integer getZlxz() {
        return zlxz;
    }


    public void setYepb(Integer value) {
        this.yepb = value;
    }

    public Integer getYepb() {
        return yepb;
    }


    public void setDzbl(BigDecimal value) {
        this.dzbl = value;
    }

    public BigDecimal getDzbl() {
        return dzbl;
    }


    public void setScbz(String value) {
        this.scbz = value;
    }

    public String getScbz() {
        return scbz;
    }


    public void setXflsh(String value) {
        this.xflsh = value;
    }

    public String getXflsh() {
        return xflsh;
    }


    public void setZkbl(BigDecimal value) {
        this.zkbl = value;
    }

    public BigDecimal getZkbl() {
        return zkbl;
    }


    public void setZkje(BigDecimal value) {
        this.zkje = value;
    }

    public BigDecimal getZkje() {
        return zkje;
    }


    public void setZhje(BigDecimal value) {
        this.zhje = value;
    }

    public BigDecimal getZhje() {
        return zhje;
    }


    public void setGjybz(String value) {
        this.gjybz = value;
    }

    public String getGjybz() {
        return gjybz;
    }


    public void setZfgh(String value) {
        this.zfgh = value;
    }

    public String getZfgh() {
        return zfgh;
    }


    public void setZfglxh(String value) {
        this.zfglxh = value;
    }

    public String getZfglxh() {
        return zfglxh;
    }


    public void setDbjfbz(String value) {
        this.dbjfbz = value;
    }

    public String getDbjfbz() {
        return dbjfbz;
    }


    public void setCldym(String value) {
        this.cldym = value;
    }

    public String getCldym() {
        return cldym;
    }


}
