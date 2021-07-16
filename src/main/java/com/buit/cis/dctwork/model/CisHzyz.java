package com.buit.cis.dctwork.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import com.buit.commons.PageQuery;

import io.swagger.annotations.ApiModelProperty;

/**
 * 类名称：CisHzyz<br> 
 * 类描述：住院_病区医嘱
 * @author GONGFANGZHOU 
 * @ApiModel(value="住院_病区医嘱")
 */
public class CisHzyz  extends  PageQuery{
	@ApiModelProperty(value="记录序号")
    /** 记录序号 */
    private Integer jlxh;
	@ApiModelProperty(value="机构代码")
    /** 机构代码 */
    private Integer jgid;
	@ApiModelProperty(value="住院号")
    /** 住院号 */
    private Integer zyh;
	@ApiModelProperty(value="药嘱名称")
    /** 药嘱名称 */
    private String yzmc;
	@ApiModelProperty(value="药品序号")
    /** 药品序号 */
    private Integer ypxh;
	@ApiModelProperty(value="药品产地")
    /** 药品产地 */
    private Integer ypcd;
	@ApiModelProperty(value="项目类型")
    /** 项目类型 */
    private Integer xmlx;
	@ApiModelProperty(value="药品类型")
    /** 药品类型 */
    private Integer yplx;
	@ApiModelProperty(value="每日次数")
    /** 每日次数 */
    private Integer mrcs;
	@ApiModelProperty(value="一次剂量")
    /** 一次剂量 */
    private BigDecimal ycjl;
	@ApiModelProperty(value="一次数量")
    /** 一次数量 */
    private BigDecimal ycsl;
	@ApiModelProperty(value="每周次数")
    /** 每周次数 */
    private Integer mzcs;
	@ApiModelProperty(value="开始时间")
    /** 开始时间 */
    private Timestamp kssj;
	@ApiModelProperty(value="确认时间")
    /** 确认时间 */
    private Timestamp qrsj;
	@ApiModelProperty(value="停止时间")
    /** 停止时间 */
    private Timestamp tzsj;
	@ApiModelProperty(value="药品单价")
    /** 药品单价 */
    private BigDecimal ypdj;
	@ApiModelProperty(value="药品用法(字典:sys_dict_config:25)")
    /** 药品用法 */
    private Integer ypyf;
	@ApiModelProperty(value="开嘱医生(字典:sys_dict_config:19)")
    /** 开嘱医生 */
    private String ysgh;
	@ApiModelProperty(value="停嘱医生")
    /** 停嘱医生 */
    private String tzys;
	@ApiModelProperty(value="操作工号")
    /** 操作工号 */
    private String czgh;
	@ApiModelProperty(value="复核工号")
    /** 复核工号 */
    private String fhgh;
	@ApiModelProperty(value="使用标志(字典:sys_flag_data:TJ000002)")
    /** 使用标志 */
    private Integer sybz;
	@ApiModelProperty(value="输入科室(字典:sys_dict_config:15)")
    /** 输入科室 */
    private Integer srks;
	@ApiModelProperty(value="自负判别")
    /** 自负判别 */
    private Integer zfpb;
	@ApiModelProperty(value="医技主项")
    /** 医技主项 */
    private Integer yjzx;
	@ApiModelProperty(value="医技序号")
    /** 医技序号 */
    private Integer yjxh;
	@ApiModelProperty(value="特检号码")
    /** 特检号码 */
    private String tjhm;
	@ApiModelProperty(value="执行科室")
    /** 执行科室 */
    private Integer zxks;
	@ApiModelProperty(value="安排日期")
    /** 安排日期 */
    private Timestamp aprq;
	@ApiModelProperty(value="医嘱组号")
    /** 医嘱组号 */
    private Integer yzzh;
	@ApiModelProperty(value="使用频次(字典:sys_dict_config:26)")
    /** 使用频次 */
    private String sypc;
	@ApiModelProperty(value="发药属性")
    /** 发药属性 */
    private Integer fysx;
	@ApiModelProperty(value="婴儿判别")
    /** 婴儿判别 */
    private Integer yepb;
	@ApiModelProperty(value="药房识别(字典:sys_dict_config:24)")
    /** 药房识别 */
    private Integer yfsb;
	@ApiModelProperty(value="临时医嘱")
    /** 临时医嘱 */
    private Integer lsyz;
	@ApiModelProperty(value="历史标志")
    /** 历史标志 */
    private Integer lsbz;
	@ApiModelProperty(value="医嘱判别")
    /** 医嘱判别 */
    private Integer yzpb;
	@ApiModelProperty(value="计费标志")
    /** 计费标志 */
    private Integer jfbz;
	@ApiModelProperty(value="备注")
    /** 备注 */
    private String bzxx;
	@ApiModelProperty(value="化验项目")
    /** 化验项目 */
    private String hyxm;
	@ApiModelProperty(value="发药方式")
    /** 发药方式 */
    private Integer fyfs;
	@ApiModelProperty(value="TPN")
    /** TPN */
    private Integer tpn;
	@ApiModelProperty(value="医生医嘱标志")
    /** 医生医嘱标志 */
    private Integer ysbz;
	@ApiModelProperty(value="医生提交标志")
    /** 医生提交标志 */
    private Integer ystj;
	@ApiModelProperty(value="发药提醒")
    /** 发药提醒 */
    private Timestamp fytx;
	@ApiModelProperty(value="医嘱排序")
    /** 医嘱排序 */
    private Integer yzpx;
	@ApiModelProperty(value="申请文号")
    /** 申请文号 */
    private Integer sqwh;
	@ApiModelProperty(value="医生医嘱编号")
    /** 医生医嘱编号 */
    private Integer ysyzbh;
	@ApiModelProperty(value="申请ID")
    /** 申请ID */
    private Integer sqid;
	@ApiModelProperty(value="作废标志")
    /** 作废标志 */
    private Integer zfbz;
	@ApiModelProperty(value="申请单XML")
    /** 申请单XML */
    private String xml;
	@ApiModelProperty(value="申请单名称")
    /** 申请单名称 */
    private String sqdmc;
	@ApiModelProperty(value="手术编号")
    /** 手术编号 */
    private Integer ssbh;
	@ApiModelProperty(value="婴儿唯一号")
    /** 婴儿唯一号 */
    private Integer yewyh;
	@ApiModelProperty(value="首日次数")
    /** 首日次数 */
    private Integer srcs;
	@ApiModelProperty(value="配置批次")
    /** 配置批次 */
    private String pzpc;
	@ApiModelProperty(value="审方结果")
    /** 审方结果 */
    private Integer sfjg;
	@ApiModelProperty(value="用药天数")
    /** 用药天数 */
    private Integer yyts;
	@ApiModelProperty(value="药房规格")
    /** 药房规格 */
    private String yfgg;
	@ApiModelProperty(value="药房单位")
    /** 药房单位 */
    private String yfdw;
	@ApiModelProperty(value="药房包装")
    /** 药房包装 */
    private Integer yfbz;
	@ApiModelProperty(value="病人科室")
    /** 病人科室 */
    private Integer brks;
	@ApiModelProperty(value="病人病区")
    /** 病人病区 */
    private Integer brbq;
	@ApiModelProperty(value="病人床号")
    /** 病人床号 */
    private String brch;
	@ApiModelProperty(value="医嘱执行时间")
    /** 医嘱执行时间 */
    private String yzzxsj;
	@ApiModelProperty(value="复核标志")
    /** 复核标志 */
    private Integer fhbz;
	@ApiModelProperty(value="复核时间")
    /** 复核时间 */
    private Timestamp fhsj;
	@ApiModelProperty(value="停嘱复核标志")
    /** 停嘱复核标志 */
    private Integer tzfhbz;
	@ApiModelProperty(value="停嘱复核人")
    /** 停嘱复核人 */
    private String tzfhr;
	@ApiModelProperty(value="停嘱复核时间")
    /** 停嘱复核时间 */
    private Timestamp tzfhsj;
	@ApiModelProperty(value="审方工号")
    /** 审方工号 */
    private String sfgh;
	@ApiModelProperty(value="审方意见")
    /** 审方意见 */
    private String sfyj;
	@ApiModelProperty(value="皮试判别")
    /** 皮试判别 */
    private Integer pspb;
	@ApiModelProperty(value="皮试结果")
    /** 皮试结果 */
    private Integer psjg;
	@ApiModelProperty(value="皮试时间")
    /** 皮试时间 */
    private Timestamp pssj;
	@ApiModelProperty(value="皮试工号")
    /** 皮试工号 */
    private String psgh;
	@ApiModelProperty(value="原液皮试")
    /** 原液皮试 */
    private Integer yyps;
	@ApiModelProperty(value="皮试关联")
    /** 皮试关联 */
    private Integer psgl;
	@ApiModelProperty(value="皮试复核")
    /** 皮试复核 */
    private String psfh;
	@ApiModelProperty(value="贴数")
    /** 贴数 */
    private Integer cfts;
	@ApiModelProperty(value="煎法")
    /** 煎法 */
    private Integer ypzs;
	@ApiModelProperty(value="脚注")
    /** 脚注 */
    private Integer jz;
	@ApiModelProperty(value="越权使用标志")
    /** 越权使用标志 */
    private Integer yqsy;
	@ApiModelProperty(value="打印状态,0未打印,1医嘱打印,2停嘱打印")
    /** 打印状态,0未打印,1医嘱打印,2停嘱打印 */
    private Integer dyzt;
	@ApiModelProperty(value="约定类别")
    /** 约定类别 */
    private String ydlb;
	@ApiModelProperty(value="补充业务ID")
    /** 补充业务ID */
    private Integer ywid;
	@ApiModelProperty(value="自备药标识")
    /** 自备药标识 */
    private Integer zfyp;
	@ApiModelProperty(value="scbz")
    /** scbz */
    private String scbz;
	@ApiModelProperty(value="医嘱作废,暂时只有医嘱本打印有用")
    /** 医嘱作废,暂时只有医嘱本打印有用 */
    private Integer yzzf;
	@ApiModelProperty(value="作废工号")
    /** 作废工号 */
    private String zfgh;
	@ApiModelProperty(value="执行判别")
    /** 执行判别 */
    private Integer zxpb;
	@ApiModelProperty(value="组套标志(该条医嘱存是组套信息) 0非组套标志，1组套标志")
    /** 组套标志(该条医嘱存是组套信息) 0非组套标志，1组套标志 */
    private Integer ztbz;
	@ApiModelProperty(value="组套医嘱记录序号")
    /** 组套医嘱记录序号 */
    private Integer ztyzjlxh;
	@ApiModelProperty(value="医嘱类型(0:检查1:检验2:备血3:手术4:会诊5:住院处方6:理疗)")
    /** 医嘱类型(0:检查1:检验2:备血3:手术4:会诊5:住院处方6:理疗) */
    private Integer yzlx;
	@ApiModelProperty(value="抗菌药物审批结果(0:申请,1:同意,2不同意)")
    /** 抗菌药物审批结果(0:申请,1:同意,2不同意) */
    private Integer kjywsp;
	@ApiModelProperty(value="通知状态(1:已通知,0:未通知)")
    /** 通知状态(1:已通知,0:未通知) */
    private Integer tzzt;
	@ApiModelProperty(value="通知时间")
    /** 通知时间 */
    private Timestamp tztime;
	@ApiModelProperty(value="通知工号")
    /** 通知工号 */
    private Integer tzgh;
	@ApiModelProperty(value="医嘱作废时间")
    /** 医嘱作废时间 */
    private Timestamp zfsj;
	@ApiModelProperty(value="滴速")
    /** 滴速 */
    private String ds;
    @ApiModelProperty(value="组套一次剂量")
    /** 组套一次剂量 */
    private BigDecimal ztycjl;
    @ApiModelProperty(value="组套每日次数")
    /** 组套每日次数 */
    private Integer ztmrcs;
    @ApiModelProperty(value="组套一次数量")
    /** 组套一次数量 */
    private BigDecimal ztycsl;
	
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

    /** 设置:住院号  */
    public void setZyh(Integer value) {
        this.zyh = value;
    }
    /** 获取:住院号 */
    public Integer getZyh() {
        return zyh;
    }

    /** 设置:药嘱名称  */
    public void setYzmc(String value) {
        this.yzmc = value;
    }
    /** 获取:药嘱名称 */
    public String getYzmc() {
        return yzmc;
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

    /** 设置:项目类型  */
    public void setXmlx(Integer value) {
        this.xmlx = value;
    }
    /** 获取:项目类型 */
    public Integer getXmlx() {
        return xmlx;
    }

    /** 设置:药品类型  */
    public void setYplx(Integer value) {
        this.yplx = value;
    }
    /** 获取:药品类型 */
    public Integer getYplx() {
        return yplx;
    }

    /** 设置:每日次数  */
    public void setMrcs(Integer value) {
        this.mrcs = value;
    }
    /** 获取:每日次数 */
    public Integer getMrcs() {
        return mrcs;
    }

    /** 设置:一次剂量  */
    public void setYcjl(BigDecimal value) {
        this.ycjl = value;
    }
    /** 获取:一次剂量 */
    public BigDecimal getYcjl() {
        return ycjl;
    }

    /** 设置:一次数量  */
    public void setYcsl(BigDecimal value) {
        this.ycsl = value;
    }
    /** 获取:一次数量 */
    public BigDecimal getYcsl() {
        return ycsl;
    }

    /** 设置:每周次数  */
    public void setMzcs(Integer value) {
        this.mzcs = value;
    }
    /** 获取:每周次数 */
    public Integer getMzcs() {
        return mzcs;
    }

    /** 设置:开始时间  */
    public void setKssj(Timestamp value) {
        this.kssj = value;
    }
    /** 获取:开始时间 */
    public Timestamp getKssj() {
        return kssj;
    }

    /** 设置:确认时间  */
    public void setQrsj(Timestamp value) {
        this.qrsj = value;
    }
    /** 获取:确认时间 */
    public Timestamp getQrsj() {
        return qrsj;
    }

    /** 设置:停止时间  */
    public void setTzsj(Timestamp value) {
        this.tzsj = value;
    }
    /** 获取:停止时间 */
    public Timestamp getTzsj() {
        return tzsj;
    }

    /** 设置:药品单价  */
    public void setYpdj(BigDecimal value) {
        this.ypdj = value;
    }
    /** 获取:药品单价 */
    public BigDecimal getYpdj() {
        return ypdj;
    }

    /** 设置:药品用法  */
    public void setYpyf(Integer value) {
        this.ypyf = value;
    }
    /** 获取:药品用法 */
    public Integer getYpyf() {
        return ypyf;
    }

    /** 设置:开嘱医生  */
    public void setYsgh(String value) {
        this.ysgh = value;
    }
    /** 获取:开嘱医生 */
    public String getYsgh() {
        return ysgh;
    }

    /** 设置:停嘱医生  */
    public void setTzys(String value) {
        this.tzys = value;
    }
    /** 获取:停嘱医生 */
    public String getTzys() {
        return tzys;
    }

    /** 设置:操作工号  */
    public void setCzgh(String value) {
        this.czgh = value;
    }
    /** 获取:操作工号 */
    public String getCzgh() {
        return czgh;
    }

    /** 设置:复核工号  */
    public void setFhgh(String value) {
        this.fhgh = value;
    }
    /** 获取:复核工号 */
    public String getFhgh() {
        return fhgh;
    }

    /** 设置:使用标志  */
    public void setSybz(Integer value) {
        this.sybz = value;
    }
    /** 获取:使用标志 */
    public Integer getSybz() {
        return sybz;
    }

    /** 设置:输入科室  */
    public void setSrks(Integer value) {
        this.srks = value;
    }
    /** 获取:输入科室 */
    public Integer getSrks() {
        return srks;
    }

    /** 设置:自负判别  */
    public void setZfpb(Integer value) {
        this.zfpb = value;
    }
    /** 获取:自负判别 */
    public Integer getZfpb() {
        return zfpb;
    }

    /** 设置:医技主项  */
    public void setYjzx(Integer value) {
        this.yjzx = value;
    }
    /** 获取:医技主项 */
    public Integer getYjzx() {
        return yjzx;
    }

    /** 设置:医技序号  */
    public void setYjxh(Integer value) {
        this.yjxh = value;
    }
    /** 获取:医技序号 */
    public Integer getYjxh() {
        return yjxh;
    }

    /** 设置:特检号码  */
    public void setTjhm(String value) {
        this.tjhm = value;
    }
    /** 获取:特检号码 */
    public String getTjhm() {
        return tjhm;
    }

    /** 设置:执行科室  */
    public void setZxks(Integer value) {
        this.zxks = value;
    }
    /** 获取:执行科室 */
    public Integer getZxks() {
        return zxks;
    }

    /** 设置:安排日期  */
    public void setAprq(Timestamp value) {
        this.aprq = value;
    }
    /** 获取:安排日期 */
    public Timestamp getAprq() {
        return aprq;
    }

    /** 设置:医嘱组号  */
    public void setYzzh(Integer value) {
        this.yzzh = value;
    }
    /** 获取:医嘱组号 */
    public Integer getYzzh() {
        return yzzh;
    }

    /** 设置:使用频次  */
    public void setSypc(String value) {
        this.sypc = value;
    }
    /** 获取:使用频次 */
    public String getSypc() {
        return sypc;
    }

    /** 设置:发药属性  */
    public void setFysx(Integer value) {
        this.fysx = value;
    }
    /** 获取:发药属性 */
    public Integer getFysx() {
        return fysx;
    }

    /** 设置:婴儿判别  */
    public void setYepb(Integer value) {
        this.yepb = value;
    }
    /** 获取:婴儿判别 */
    public Integer getYepb() {
        return yepb;
    }

    /** 设置:药房识别  */
    public void setYfsb(Integer value) {
        this.yfsb = value;
    }
    /** 获取:药房识别 */
    public Integer getYfsb() {
        return yfsb;
    }

    /** 设置:临时医嘱  */
    public void setLsyz(Integer value) {
        this.lsyz = value;
    }
    /** 获取:临时医嘱 */
    public Integer getLsyz() {
        return lsyz;
    }

    /** 设置:历史标志  */
    public void setLsbz(Integer value) {
        this.lsbz = value;
    }
    /** 获取:历史标志 */
    public Integer getLsbz() {
        return lsbz;
    }

    /** 设置:医嘱判别  */
    public void setYzpb(Integer value) {
        this.yzpb = value;
    }
    /** 获取:医嘱判别 */
    public Integer getYzpb() {
        return yzpb;
    }

    /** 设置:计费标志  */
    public void setJfbz(Integer value) {
        this.jfbz = value;
    }
    /** 获取:计费标志 */
    public Integer getJfbz() {
        return jfbz;
    }

    /** 设置:备注  */
    public void setBzxx(String value) {
        this.bzxx = value;
    }
    /** 获取:备注 */
    public String getBzxx() {
        return bzxx;
    }

    /** 设置:化验项目  */
    public void setHyxm(String value) {
        this.hyxm = value;
    }
    /** 获取:化验项目 */
    public String getHyxm() {
        return hyxm;
    }

    /** 设置:发药方式  */
    public void setFyfs(Integer value) {
        this.fyfs = value;
    }
    /** 获取:发药方式 */
    public Integer getFyfs() {
        return fyfs;
    }

    /** 设置:TPN  */
    public void setTpn(Integer value) {
        this.tpn = value;
    }
    /** 获取:TPN */
    public Integer getTpn() {
        return tpn;
    }

    /** 设置:医生医嘱标志  */
    public void setYsbz(Integer value) {
        this.ysbz = value;
    }
    /** 获取:医生医嘱标志 */
    public Integer getYsbz() {
        return ysbz;
    }

    /** 设置:医生提交标志  */
    public void setYstj(Integer value) {
        this.ystj = value;
    }
    /** 获取:医生提交标志 */
    public Integer getYstj() {
        return ystj;
    }

    /** 设置:发药提醒  */
    public void setFytx(Timestamp value) {
        this.fytx = value;
    }
    /** 获取:发药提醒 */
    public Timestamp getFytx() {
        return fytx;
    }

    /** 设置:医嘱排序  */
    public void setYzpx(Integer value) {
        this.yzpx = value;
    }
    /** 获取:医嘱排序 */
    public Integer getYzpx() {
        return yzpx;
    }

    /** 设置:申请文号  */
    public void setSqwh(Integer value) {
        this.sqwh = value;
    }
    /** 获取:申请文号 */
    public Integer getSqwh() {
        return sqwh;
    }

    /** 设置:医生医嘱编号  */
    public void setYsyzbh(Integer value) {
        this.ysyzbh = value;
    }
    /** 获取:医生医嘱编号 */
    public Integer getYsyzbh() {
        return ysyzbh;
    }

    /** 设置:申请ID  */
    public void setSqid(Integer value) {
        this.sqid = value;
    }
    /** 获取:申请ID */
    public Integer getSqid() {
        return sqid;
    }

    /** 设置:作废标志  */
    public void setZfbz(Integer value) {
        this.zfbz = value;
    }
    /** 获取:作废标志 */
    public Integer getZfbz() {
        return zfbz;
    }

    /** 设置:申请单XML  */
    public void setXml(String value) {
        this.xml = value;
    }
    /** 获取:申请单XML */
    public String getXml() {
        return xml;
    }

    /** 设置:申请单名称  */
    public void setSqdmc(String value) {
        this.sqdmc = value;
    }
    /** 获取:申请单名称 */
    public String getSqdmc() {
        return sqdmc;
    }

    /** 设置:手术编号  */
    public void setSsbh(Integer value) {
        this.ssbh = value;
    }
    /** 获取:手术编号 */
    public Integer getSsbh() {
        return ssbh;
    }

    /** 设置:婴儿唯一号  */
    public void setYewyh(Integer value) {
        this.yewyh = value;
    }
    /** 获取:婴儿唯一号 */
    public Integer getYewyh() {
        return yewyh;
    }

    /** 设置:首日次数  */
    public void setSrcs(Integer value) {
        this.srcs = value;
    }
    /** 获取:首日次数 */
    public Integer getSrcs() {
        return srcs;
    }

    /** 设置:配置批次  */
    public void setPzpc(String value) {
        this.pzpc = value;
    }
    /** 获取:配置批次 */
    public String getPzpc() {
        return pzpc;
    }

    /** 设置:审方结果  */
    public void setSfjg(Integer value) {
        this.sfjg = value;
    }
    /** 获取:审方结果 */
    public Integer getSfjg() {
        return sfjg;
    }

    /** 设置:用药天数  */
    public void setYyts(Integer value) {
        this.yyts = value;
    }
    /** 获取:用药天数 */
    public Integer getYyts() {
        return yyts;
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

    /** 设置:病人科室  */
    public void setBrks(Integer value) {
        this.brks = value;
    }
    /** 获取:病人科室 */
    public Integer getBrks() {
        return brks;
    }

    /** 设置:病人病区  */
    public void setBrbq(Integer value) {
        this.brbq = value;
    }
    /** 获取:病人病区 */
    public Integer getBrbq() {
        return brbq;
    }

    /** 设置:病人床号  */
    public void setBrch(String value) {
        this.brch = value;
    }
    /** 获取:病人床号 */
    public String getBrch() {
        return brch;
    }

    /** 设置:医嘱执行时间  */
    public void setYzzxsj(String value) {
        this.yzzxsj = value;
    }
    /** 获取:医嘱执行时间 */
    public String getYzzxsj() {
        return yzzxsj;
    }

    /** 设置:复核标志  */
    public void setFhbz(Integer value) {
        this.fhbz = value;
    }
    /** 获取:复核标志 */
    public Integer getFhbz() {
        return fhbz;
    }

    /** 设置:复核时间  */
    public void setFhsj(Timestamp value) {
        this.fhsj = value;
    }
    /** 获取:复核时间 */
    public Timestamp getFhsj() {
        return fhsj;
    }

    /** 设置:停嘱复核标志  */
    public void setTzfhbz(Integer value) {
        this.tzfhbz = value;
    }
    /** 获取:停嘱复核标志 */
    public Integer getTzfhbz() {
        return tzfhbz;
    }

    /** 设置:停嘱复核人  */
    public void setTzfhr(String value) {
        this.tzfhr = value;
    }
    /** 获取:停嘱复核人 */
    public String getTzfhr() {
        return tzfhr;
    }

    /** 设置:停嘱复核时间  */
    public void setTzfhsj(Timestamp value) {
        this.tzfhsj = value;
    }
    /** 获取:停嘱复核时间 */
    public Timestamp getTzfhsj() {
        return tzfhsj;
    }

    /** 设置:审方工号  */
    public void setSfgh(String value) {
        this.sfgh = value;
    }
    /** 获取:审方工号 */
    public String getSfgh() {
        return sfgh;
    }

    /** 设置:审方意见  */
    public void setSfyj(String value) {
        this.sfyj = value;
    }
    /** 获取:审方意见 */
    public String getSfyj() {
        return sfyj;
    }

    /** 设置:皮试判别  */
    public void setPspb(Integer value) {
        this.pspb = value;
    }
    /** 获取:皮试判别 */
    public Integer getPspb() {
        return pspb;
    }

    /** 设置:皮试结果  */
    public void setPsjg(Integer value) {
        this.psjg = value;
    }
    /** 获取:皮试结果 */
    public Integer getPsjg() {
        return psjg;
    }

    /** 设置:皮试时间  */
    public void setPssj(Timestamp value) {
        this.pssj = value;
    }
    /** 获取:皮试时间 */
    public Timestamp getPssj() {
        return pssj;
    }

    /** 设置:皮试工号  */
    public void setPsgh(String value) {
        this.psgh = value;
    }
    /** 获取:皮试工号 */
    public String getPsgh() {
        return psgh;
    }

    /** 设置:原液皮试  */
    public void setYyps(Integer value) {
        this.yyps = value;
    }
    /** 获取:原液皮试 */
    public Integer getYyps() {
        return yyps;
    }

    /** 设置:皮试关联  */
    public void setPsgl(Integer value) {
        this.psgl = value;
    }
    /** 获取:皮试关联 */
    public Integer getPsgl() {
        return psgl;
    }

    /** 设置:皮试复核  */
    public void setPsfh(String value) {
        this.psfh = value;
    }
    /** 获取:皮试复核 */
    public String getPsfh() {
        return psfh;
    }

    /** 设置:贴数  */
    public void setCfts(Integer value) {
        this.cfts = value;
    }
    /** 获取:贴数 */
    public Integer getCfts() {
        return cfts;
    }

    /** 设置:煎法  */
    public void setYpzs(Integer value) {
        this.ypzs = value;
    }
    /** 获取:煎法 */
    public Integer getYpzs() {
        return ypzs;
    }

    /** 设置:脚注  */
    public void setJz(Integer value) {
        this.jz = value;
    }
    /** 获取:脚注 */
    public Integer getJz() {
        return jz;
    }

    /** 设置:越权使用标志  */
    public void setYqsy(Integer value) {
        this.yqsy = value;
    }
    /** 获取:越权使用标志 */
    public Integer getYqsy() {
        return yqsy;
    }

    /** 设置:打印状态,0未打印,1医嘱打印,2停嘱打印  */
    public void setDyzt(Integer value) {
        this.dyzt = value;
    }
    /** 获取:打印状态,0未打印,1医嘱打印,2停嘱打印 */
    public Integer getDyzt() {
        return dyzt;
    }

    /** 设置:约定类别  */
    public void setYdlb(String value) {
        this.ydlb = value;
    }
    /** 获取:约定类别 */
    public String getYdlb() {
        return ydlb;
    }

    /** 设置:补充业务ID  */
    public void setYwid(Integer value) {
        this.ywid = value;
    }
    /** 获取:补充业务ID */
    public Integer getYwid() {
        return ywid;
    }

    /** 设置:自备药标识  */
    public void setZfyp(Integer value) {
        this.zfyp = value;
    }
    /** 获取:自备药标识 */
    public Integer getZfyp() {
        return zfyp;
    }

    /** 设置:scbz  */
    public void setScbz(String value) {
        this.scbz = value;
    }
    /** 获取:scbz */
    public String getScbz() {
        return scbz;
    }

    /** 设置:医嘱作废,暂时只有医嘱本打印有用  */
    public void setYzzf(Integer value) {
        this.yzzf = value;
    }
    /** 获取:医嘱作废,暂时只有医嘱本打印有用 */
    public Integer getYzzf() {
        return yzzf;
    }

    /** 设置:作废工号  */
    public void setZfgh(String value) {
        this.zfgh = value;
    }
    /** 获取:作废工号 */
    public String getZfgh() {
        return zfgh;
    }

    /** 设置:执行判别  */
    public void setZxpb(Integer value) {
        this.zxpb = value;
    }
    /** 获取:执行判别 */
    public Integer getZxpb() {
        return zxpb;
    }

    /** 设置:组套标志(该条医嘱存是组套信息) 0非组套标志，1组套标志  */
    public void setZtbz(Integer value) {
        this.ztbz = value;
    }
    /** 获取:组套标志(该条医嘱存是组套信息) 0非组套标志，1组套标志 */
    public Integer getZtbz() {
        return ztbz;
    }

    /** 设置:组套医嘱记录序号  */
    public void setZtyzjlxh(Integer value) {
        this.ztyzjlxh = value;
    }
    /** 获取:组套医嘱记录序号 */
    public Integer getZtyzjlxh() {
        return ztyzjlxh;
    }
    /** 医嘱类型(0:检查1:检验2:备血3:手术4:会诊5:住院处方6:理疗) */
	public Integer getYzlx() {
		return yzlx;
	}
	/** 医嘱类型(0:检查1:检验2:备血3:手术4:会诊5:住院处方6:理疗) */
	public void setYzlx(Integer yzlx) {
		this.yzlx = yzlx;
	}
	/**抗菌药物审批结果(0:申请,1:同意,2不同意) */
	public Integer getKjywsp() {
		return kjywsp;
	}
	/**抗菌药物审批结果(0:申请,1:同意,2不同意) */
	public void setKjywsp(Integer kjywsp) {
		this.kjywsp = kjywsp;
	}
	/** 通知状态(1:已通知,0:未通知) */
	public Integer getTzzt() {
		return tzzt;
	}
	/** 通知状态(1:已通知,0:未通知) */
	public void setTzzt(Integer tzzt) {
		this.tzzt = tzzt;
	}
	/** 通知时间 */
	public Timestamp getTztime() {
		return tztime;
	}
	/** 通知时间 */
	public void setTztime(Timestamp tztime) {
		this.tztime = tztime;
	}
	/** 通知工号 */
	public Integer getTzgh() {
		return tzgh;
	}
	/** 通知工号 */
	public void setTzgh(Integer tzgh) {
		this.tzgh = tzgh;
	}
	/** 医嘱作废时间 */
	public Timestamp getZfsj() {
		return zfsj;
	}
	/** 医嘱作废时间 */
	public void setZfsj(Timestamp zfsj) {
		this.zfsj = zfsj;
	}
	/** 滴速 */
	public String getDs() {
		return ds;
	}
	/** 滴速 */
	public void setDs(String ds) {
		this.ds = ds;
	}

    public BigDecimal getZtycjl() {
        return ztycjl;
    }

    public void setZtycjl(BigDecimal ztycjl) {
        this.ztycjl = ztycjl;
    }

    public Integer getZtmrcs() {
        return ztmrcs;
    }

    public void setZtmrcs(Integer ztmrcs) {
        this.ztmrcs = ztmrcs;
    }

    public BigDecimal getZtycsl() {
        return ztycsl;
    }

    public void setZtycsl(BigDecimal ztycsl) {
        this.ztycsl = ztycsl;
    }
}
