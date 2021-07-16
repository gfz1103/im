package com.buit.cis.dctwork.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import com.buit.commons.PageQuery;

/**
 * 类名称：NisTj02<br> 
 * 类描述：病区_提交明细 | 记录病区医嘱提交明细的记录，与提交记录表通过TJXH关联
 * @author GONGFANGZHOU 
 * @ApiModel(value="病区_提交明细 | 记录病区医嘱提交明细的记录，与提交记录表通过TJXH关联")
 */
public class NisTj02  extends  PageQuery{
	//@ApiModelProperty(value="记录序号")
    /** 记录序号 */
    private Integer jlxh;
	//@ApiModelProperty(value="机构代码")
    /** 机构代码 */
    private Integer jgid;
	//@ApiModelProperty(value="提交序号")
    /** 提交序号 */
    private Integer tjxh;
	//@ApiModelProperty(value="医嘱序号")
    /** 医嘱序号 */
    private Integer yzxh;
	//@ApiModelProperty(value="住院号")
    /** 住院号 */
    private Integer zyh;
	//@ApiModelProperty(value="药品序号")
    /** 药品序号 */
    private Integer ypxh;
	//@ApiModelProperty(value="药品产地")
    /** 药品产地 */
    private Integer ypcd;
	//@ApiModelProperty(value="药房规格")
    /** 药房规格 */
    private String yfgg;
	//@ApiModelProperty(value="药房单位")
    /** 药房单位 */
    private String yfdw;
	//@ApiModelProperty(value="药房包装")
    /** 药房包装 */
    private Integer yfbz;
	//@ApiModelProperty(value="开始时间")
    /** 开始时间 */
    private Timestamp kssj;
	//@ApiModelProperty(value="一次数量")
    /** 一次数量 */
    private BigDecimal ycsl;
	//@ApiModelProperty(value="一天次数")
    /** 一天次数 */
    private Integer ytcs;
	//@ApiModelProperty(value="发药时间")
    /** 发药时间 */
    private BigDecimal fysl;
	//@ApiModelProperty(value="计费日期")
    /** 计费日期 */
    private Timestamp jfrq;
	//@ApiModelProperty(value="确认日期")
    /** 确认日期 */
    private Timestamp qrrq;
	//@ApiModelProperty(value="使用频次")
    /** 使用频次 */
    private String sypc;
	//@ApiModelProperty(value="发药金额")
    /** 发药金额 */
    private BigDecimal fyje;
	//@ApiModelProperty(value="药品单价")
    /** 药品单价 */
    private BigDecimal ypdj;
	//@ApiModelProperty(value="发药标志")
    /** 发药标志 */
    private Integer fybz;
	//@ApiModelProperty(value="发药工号")
    /** 发药工号 */
    private String fygh;
	//@ApiModelProperty(value="发药日期")
    /** 发药日期 */
    private Timestamp fyrq;
	//@ApiModelProperty(value="临时医嘱")
    /** 临时医嘱 */
    private Integer lsyz;
	//@ApiModelProperty(value="取整策略")
    /** 取整策略 */
    private BigDecimal qzcl;
	//@ApiModelProperty(value="婴儿判别")
    /** 婴儿判别 */
    private Integer yepb;
	//@ApiModelProperty(value="费用科室")
    /** 费用科室 */
    private Integer fyks;
	//@ApiModelProperty(value="实际发药标志")
    /** 实际发药标志 */
    private Integer sjfybz;
	//@ApiModelProperty(value="实际发药人")
    /** 实际发药人 */
    private String sjfyr;
	//@ApiModelProperty(value="实际发药时间")
    /** 实际发药时间 */
    private Timestamp sjfysj;
	//@ApiModelProperty(value="用药天数")
    /** 用药天数 */
    private Integer yyts;
	//@ApiModelProperty(value="是否病区医嘱，默认0  0：是 1：否  1代表是用费用记账记得")
    /** 是否病区医嘱，默认0  0：是 1：否  1代表是用费用记账记得 */
    private Integer bqyz;
	//@ApiModelProperty(value="确认标志")
    /** 确认标志 */
    private Integer qrbz;
	//@ApiModelProperty(value="确认时间")
    /** 确认时间 */
    private Timestamp qrsj;
	//@ApiModelProperty(value="确认工号")
    /** 确认工号 */
    private String qrgh;
	//@ApiModelProperty(value="IM_FEE_FYMX表的主键JLXH，只有手术记的药品才会存")
    /** IM_FEE_FYMX表的主键JLXH，只有手术记的药品才会存 */
    private Integer fymxjlxh;

    /** 设置:记录序号  */
    public void setJlxh(Integer value) {
        this.jlxh = value;
    }
    /** 获取:记录序号 */
    public Integer getJlxh() {
        return jlxh;
    }

    /** 设置:机构代码  */
    public void setJgid(Integer value) {
        this.jgid = value;
    }
    /** 获取:机构代码 */
    public Integer getJgid() {
        return jgid;
    }

    /** 设置:提交序号  */
    public void setTjxh(Integer value) {
        this.tjxh = value;
    }
    /** 获取:提交序号 */
    public Integer getTjxh() {
        return tjxh;
    }

    /** 设置:医嘱序号  */
    public void setYzxh(Integer value) {
        this.yzxh = value;
    }
    /** 获取:医嘱序号 */
    public Integer getYzxh() {
        return yzxh;
    }

    /** 设置:住院号  */
    public void setZyh(Integer value) {
        this.zyh = value;
    }
    /** 获取:住院号 */
    public Integer getZyh() {
        return zyh;
    }

    /** 设置:药品序号  */
    public void setYpxh(Integer value) {
        this.ypxh = value;
    }
    /** 获取:药品序号 */
    public Integer getYpxh() {
        return ypxh;
    }

    /** 设置:药品产地  */
    public void setYpcd(Integer value) {
        this.ypcd = value;
    }
    /** 获取:药品产地 */
    public Integer getYpcd() {
        return ypcd;
    }

    /** 设置:药房规格  */
    public void setYfgg(String value) {
        this.yfgg = value;
    }
    /** 获取:药房规格 */
    public String getYfgg() {
        return yfgg;
    }

    /** 设置:药房单位  */
    public void setYfdw(String value) {
        this.yfdw = value;
    }
    /** 获取:药房单位 */
    public String getYfdw() {
        return yfdw;
    }

    /** 设置:药房包装  */
    public void setYfbz(Integer value) {
        this.yfbz = value;
    }
    /** 获取:药房包装 */
    public Integer getYfbz() {
        return yfbz;
    }

    /** 设置:开始时间  */
    public void setKssj(Timestamp value) {
        this.kssj = value;
    }
    /** 获取:开始时间 */
    public Timestamp getKssj() {
        return kssj;
    }

    /** 设置:一次数量  */
    public void setYcsl(BigDecimal value) {
        this.ycsl = value;
    }
    /** 获取:一次数量 */
    public BigDecimal getYcsl() {
        return ycsl;
    }

    /** 设置:一天次数  */
    public void setYtcs(Integer value) {
        this.ytcs = value;
    }
    /** 获取:一天次数 */
    public Integer getYtcs() {
        return ytcs;
    }

    /** 设置:发药时间  */
    public void setFysl(BigDecimal value) {
        this.fysl = value;
    }
    /** 获取:发药时间 */
    public BigDecimal getFysl() {
        return fysl;
    }

    /** 设置:计费日期  */
    public void setJfrq(Timestamp value) {
        this.jfrq = value;
    }
    /** 获取:计费日期 */
    public Timestamp getJfrq() {
        return jfrq;
    }

    /** 设置:确认日期  */
    public void setQrrq(Timestamp value) {
        this.qrrq = value;
    }
    /** 获取:确认日期 */
    public Timestamp getQrrq() {
        return qrrq;
    }

    /** 设置:使用频次  */
    public void setSypc(String value) {
        this.sypc = value;
    }
    /** 获取:使用频次 */
    public String getSypc() {
        return sypc;
    }

    /** 设置:发药金额  */
    public void setFyje(BigDecimal value) {
        this.fyje = value;
    }
    /** 获取:发药金额 */
    public BigDecimal getFyje() {
        return fyje;
    }

    /** 设置:药品单价  */
    public void setYpdj(BigDecimal value) {
        this.ypdj = value;
    }
    /** 获取:药品单价 */
    public BigDecimal getYpdj() {
        return ypdj;
    }

    /** 设置:发药标志  */
    public void setFybz(Integer value) {
        this.fybz = value;
    }
    /** 获取:发药标志 */
    public Integer getFybz() {
        return fybz;
    }

    /** 设置:发药工号  */
    public void setFygh(String value) {
        this.fygh = value;
    }
    /** 获取:发药工号 */
    public String getFygh() {
        return fygh;
    }

    /** 设置:发药日期  */
    public void setFyrq(Timestamp value) {
        this.fyrq = value;
    }
    /** 获取:发药日期 */
    public Timestamp getFyrq() {
        return fyrq;
    }

    /** 设置:临时医嘱  */
    public void setLsyz(Integer value) {
        this.lsyz = value;
    }
    /** 获取:临时医嘱 */
    public Integer getLsyz() {
        return lsyz;
    }

    /** 设置:取整策略  */
    public void setQzcl(BigDecimal value) {
        this.qzcl = value;
    }
    /** 获取:取整策略 */
    public BigDecimal getQzcl() {
        return qzcl;
    }

    /** 设置:婴儿判别  */
    public void setYepb(Integer value) {
        this.yepb = value;
    }
    /** 获取:婴儿判别 */
    public Integer getYepb() {
        return yepb;
    }

    /** 设置:费用科室  */
    public void setFyks(Integer value) {
        this.fyks = value;
    }
    /** 获取:费用科室 */
    public Integer getFyks() {
        return fyks;
    }

    /** 设置:实际发药标志  */
    public void setSjfybz(Integer value) {
        this.sjfybz = value;
    }
    /** 获取:实际发药标志 */
    public Integer getSjfybz() {
        return sjfybz;
    }

    /** 设置:实际发药人  */
    public void setSjfyr(String value) {
        this.sjfyr = value;
    }
    /** 获取:实际发药人 */
    public String getSjfyr() {
        return sjfyr;
    }

    /** 设置:实际发药时间  */
    public void setSjfysj(Timestamp value) {
        this.sjfysj = value;
    }
    /** 获取:实际发药时间 */
    public Timestamp getSjfysj() {
        return sjfysj;
    }

    /** 设置:用药天数  */
    public void setYyts(Integer value) {
        this.yyts = value;
    }
    /** 获取:用药天数 */
    public Integer getYyts() {
        return yyts;
    }

    /** 设置:是否病区医嘱，默认0  0：是 1：否  1代表是用费用记账记得  */
    public void setBqyz(Integer value) {
        this.bqyz = value;
    }
    /** 获取:是否病区医嘱，默认0  0：是 1：否  1代表是用费用记账记得 */
    public Integer getBqyz() {
        return bqyz;
    }

    /** 设置:确认标志  */
    public void setQrbz(Integer value) {
        this.qrbz = value;
    }
    /** 获取:确认标志 */
    public Integer getQrbz() {
        return qrbz;
    }

    /** 设置:确认时间  */
    public void setQrsj(Timestamp value) {
        this.qrsj = value;
    }
    /** 获取:确认时间 */
    public Timestamp getQrsj() {
        return qrsj;
    }

    /** 设置:确认工号  */
    public void setQrgh(String value) {
        this.qrgh = value;
    }
    /** 获取:确认工号 */
    public String getQrgh() {
        return qrgh;
    }

    /** 设置:IM_FEE_FYMX表的主键JLXH，只有手术记的药品才会存  */
    public void setFymxjlxh(Integer value) {
        this.fymxjlxh = value;
    }
    /** 获取:IM_FEE_FYMX表的主键JLXH，只有手术记的药品才会存 */
    public Integer getFymxjlxh() {
        return fymxjlxh;
    }


}
