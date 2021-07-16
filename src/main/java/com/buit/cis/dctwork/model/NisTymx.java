package com.buit.cis.dctwork.model;

import java.math.BigDecimal;
import java.sql.Timestamp;

import com.buit.commons.PageQuery;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 类名称：NisTymx<br> 
 * 类描述：病区_退药明细
 * @author GONGFANGZHOU 
 */
@ApiModel(value="病区_退药明细")
public class NisTymx  extends  PageQuery{
	@ApiModelProperty(value="记录序号")
    /** 记录序号 */
    private Integer jlxh;
	@ApiModelProperty(value="住院号")
    /** 住院号 */
    private Integer zyh;
	@ApiModelProperty(value="药品序号")
    /** 药品序号 */
    private Integer ypxh;
	@ApiModelProperty(value="药品产地")
    /** 药品产地 */
    private Integer ypcd;
	@ApiModelProperty(value="申请日期")
    /** 申请日期 */
    private Timestamp sqrq;
	@ApiModelProperty(value="药品价格")
    /** 药品价格 */
    private BigDecimal ypjg;
	@ApiModelProperty(value="机构代码")
    /** 机构代码 */
    private Integer jgid;
	@ApiModelProperty(value="退费日期")
    /** 退费日期 */
    private Timestamp tyrq;
	@ApiModelProperty(value="药品规格")
    /** 药品规格 */
    private String ypgg;
	@ApiModelProperty(value="药房单位")
    /** 药房单位 */
    private String yfdw;
	@ApiModelProperty(value="药房包装")
    /** 药房包装 */
    private Integer yfbz;
	@ApiModelProperty(value="药品数量")
    /** 药品数量 */
    private BigDecimal ypsl;
	@ApiModelProperty(value="自负比例")
    /** 自负比例 */
    private BigDecimal zfbl;
	@ApiModelProperty(value="婴儿判别")
    /** 婴儿判别 */
    private Integer yepb;
	@ApiModelProperty(value="药房识别")
    /** 药房识别 */
    private Integer yfsb;
	@ApiModelProperty(value="退药病区")
    /** 退药病区 */
    private Integer tybq;
	@ApiModelProperty(value="操作工号")
    /** 操作工号 */
    private String czgh;
	@ApiModelProperty(value="自费判别")
    /** 自费判别 */
    private Integer zfpb;
	@ApiModelProperty(value="提交标志")
    /** 提交标志 */
    private Integer tjbz;
	@ApiModelProperty(value="医嘱ID")
    /** 医嘱ID */
    private Integer yzid;
	@ApiModelProperty(value="退药类型")
    /** 退药类型 */
    private Integer tylx;
	@ApiModelProperty(value="退回标志")
    /** 退回标志 */
    private Integer thbz;
	@ApiModelProperty(value="退回时间")
    /** 退回时间 */
    private Timestamp thsj;
	@ApiModelProperty(value="退回人")
    /** 退回人 */
    private String thr;
	@ApiModelProperty(value="记录ID")
    /** 记录ID */
    private Integer jlid;
    @ApiModelProperty(value="发药方式")
    /** 发药方式 */
    private Integer fyfs;
    @ApiModelProperty(value="退药原因")
    /** 退药原因 */
    private String tyyy;
    
	public Integer getJlxh() {
		return jlxh;
	}
	public void setJlxh(Integer jlxh) {
		this.jlxh = jlxh;
	}
	public Integer getZyh() {
		return zyh;
	}
	public void setZyh(Integer zyh) {
		this.zyh = zyh;
	}
	public Integer getYpxh() {
		return ypxh;
	}
	public void setYpxh(Integer ypxh) {
		this.ypxh = ypxh;
	}
	public Integer getYpcd() {
		return ypcd;
	}
	public void setYpcd(Integer ypcd) {
		this.ypcd = ypcd;
	}
	public Timestamp getSqrq() {
		return sqrq;
	}
	public void setSqrq(Timestamp sqrq) {
		this.sqrq = sqrq;
	}
	public BigDecimal getYpjg() {
		return ypjg;
	}
	public void setYpjg(BigDecimal ypjg) {
		this.ypjg = ypjg;
	}
	public Integer getJgid() {
		return jgid;
	}
	public void setJgid(Integer jgid) {
		this.jgid = jgid;
	}
	public Timestamp getTyrq() {
		return tyrq;
	}
	public void setTyrq(Timestamp tyrq) {
		this.tyrq = tyrq;
	}
	public String getYpgg() {
		return ypgg;
	}
	public void setYpgg(String ypgg) {
		this.ypgg = ypgg;
	}
	public String getYfdw() {
		return yfdw;
	}
	public void setYfdw(String yfdw) {
		this.yfdw = yfdw;
	}
	public Integer getYfbz() {
		return yfbz;
	}
	public void setYfbz(Integer yfbz) {
		this.yfbz = yfbz;
	}
	public BigDecimal getYpsl() {
		return ypsl;
	}
	public void setYpsl(BigDecimal ypsl) {
		this.ypsl = ypsl;
	}
	public BigDecimal getZfbl() {
		return zfbl;
	}
	public void setZfbl(BigDecimal zfbl) {
		this.zfbl = zfbl;
	}
	public Integer getYepb() {
		return yepb;
	}
	public void setYepb(Integer yepb) {
		this.yepb = yepb;
	}
	public Integer getYfsb() {
		return yfsb;
	}
	public void setYfsb(Integer yfsb) {
		this.yfsb = yfsb;
	}
	public Integer getTybq() {
		return tybq;
	}
	public void setTybq(Integer tybq) {
		this.tybq = tybq;
	}
	public String getCzgh() {
		return czgh;
	}
	public void setCzgh(String czgh) {
		this.czgh = czgh;
	}
	public Integer getZfpb() {
		return zfpb;
	}
	public void setZfpb(Integer zfpb) {
		this.zfpb = zfpb;
	}
	public Integer getTjbz() {
		return tjbz;
	}
	public void setTjbz(Integer tjbz) {
		this.tjbz = tjbz;
	}
	public Integer getYzid() {
		return yzid;
	}
	public void setYzid(Integer yzid) {
		this.yzid = yzid;
	}
	public Integer getTylx() {
		return tylx;
	}
	public void setTylx(Integer tylx) {
		this.tylx = tylx;
	}
	public Integer getThbz() {
		return thbz;
	}
	public void setThbz(Integer thbz) {
		this.thbz = thbz;
	}
	public Timestamp getThsj() {
		return thsj;
	}
	public void setThsj(Timestamp thsj) {
		this.thsj = thsj;
	}
	public String getThr() {
		return thr;
	}
	public void setThr(String thr) {
		this.thr = thr;
	}
	public Integer getJlid() {
		return jlid;
	}
	public void setJlid(Integer jlid) {
		this.jlid = jlid;
	}
	public Integer getFyfs() {
		return fyfs;
	}
	public void setFyfs(Integer fyfs) {
		this.fyfs = fyfs;
	}
	public String getTyyy() {
		return tyyy;
	}
	public void setTyyy(String tyyy) {
		this.tyyy = tyyy;
	}


}
