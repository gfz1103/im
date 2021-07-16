   
package com.buit.cis.nurse.response;

import java.math.BigDecimal;
import java.sql.Timestamp;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


/**
 * 类名称：CisHzyzZt<br> 
 * 类描述：住院_病区医嘱或组套医嘱<br>
 * @author GONGFANGZHOU
 */
@ApiModel(value="住院_病区组套医嘱_yzorztResp")
public class CisHzyzOrztResp {

    @ApiModelProperty(value="开嘱医生")
    private String ysgh;
	
	@ApiModelProperty(value="药嘱名称")
    private String yzmc;
	
	@ApiModelProperty(value="药品单价")
    private BigDecimal ypdj;
	
	@ApiModelProperty(value="每日次数")
    private Integer mrcs;
	
	@ApiModelProperty(value="一次数量")
    private BigDecimal ycsl;
	
	@ApiModelProperty(value="金额")
    private BigDecimal je;
	
	@ApiModelProperty(value="是否ok")
    private String ok;
	
	@ApiModelProperty(value="使用标志")
    private Integer sybz;
	
	@ApiModelProperty(value="药品产地")
    private Integer ypcd;
	
	@ApiModelProperty(value="输入科室")
    private Integer srks;
	
	@ApiModelProperty(value="确认时间")
    private Timestamp qrsj;
	
	@ApiModelProperty(value="药品序号")
    private Integer ypxh;
	
	@ApiModelProperty(value="药品类型")
    private Integer yplx;
	
	@ApiModelProperty(value="住院号")
    private Integer zyh;
	
	@ApiModelProperty(value="记录序号")
    private Integer jlxh;
	
	@ApiModelProperty(value="开始时间")
    private Timestamp kssj;
	
	@ApiModelProperty(value="医嘱序号(同组号)")
    private Integer yzxh;
	
	@ApiModelProperty(value="医嘱组号")
    private Integer yzzh;
	
	@ApiModelProperty(value="停止时间")
    private Timestamp tzsj;
	
	@ApiModelProperty(value="每周次数")
    private Integer mzcs;

	@ApiModelProperty(value="项目类型")
    private Integer xmlx;
	
	@ApiModelProperty(value="操作工号")
    private String czgh;
	
	@ApiModelProperty(value="执行科室")
    private Integer zxks;
	
	@ApiModelProperty(value="婴儿判别")
    private Integer yepb;
	
	@ApiModelProperty(value="历史标志")
    private Integer lsbz;
	
	@ApiModelProperty(value="临时医嘱")
    private Integer lsyz;
	
	@ApiModelProperty(value="自负判别")
    private Integer zfpb;
	
	@ApiModelProperty(value="病人床号")
    private String brch;
	
	@ApiModelProperty(value="病人姓名")
    private String brxm;
	
	@ApiModelProperty(value="病人性质 | 对应PUB_BRXZ中代码")
    private Integer brxz;
	
	@ApiModelProperty(value="工作单位")
    private String gzdw;
	
	@ApiModelProperty(value="病人科室")
    private Integer brks;
	
	@ApiModelProperty(value="使用频次")
    private String sypc;
	
	@ApiModelProperty(value="诊疗小组 | 指负责病人治疗的医疗小组，对应ZY_ZLXZ.XZXH的代码")
    private Integer zlxz;
	
	@ApiModelProperty(value="天数")
    private Integer ts;
	
	@ApiModelProperty(value="费用次数")
    private Integer fycs;
	
	@ApiModelProperty(value="冻结ID号 | 对应ZY_ZHDJ.DJID的代码")
    private Integer djid;
    
	@ApiModelProperty(value="医生提交标志")
    private Integer ystj;
    
	@ApiModelProperty(value="首日次数")
    private Integer srcs;
	
	@ApiModelProperty(value="医嘱执行时间")
    private String yzzxsj;
	
	@ApiModelProperty(value="住院号码")
    private String zyhm;
	
	@ApiModelProperty(value="组套标志(该条医嘱存是组套信息) 0非组套标志，1组套标志")
    private Integer ztbz;
	
	@ApiModelProperty(value="组套医嘱记录序号")
    private Integer ztyzjlxh;
	
	@ApiModelProperty(value="药品用法")
    private Integer ypyf;
    
	@ApiModelProperty(value="备注信息")
    private String bzxx;
	
	@ApiModelProperty(value="医嘱判别")
    private Integer yzpb;
	
	@ApiModelProperty(value="发药方式")
    private Integer fyfs;
	
	@ApiModelProperty(value="医技序号")
	private Integer yjxh;
	
	@ApiModelProperty(value="发药属性")
	private Integer fysx;

	@ApiModelProperty(value="费用序号")
	private Integer fyxh;
	
	@ApiModelProperty(value="医嘱类型")
	private Integer yzlx;
	
	public String getYsgh() {
		return ysgh;
	}

	public void setYsgh(String ysgh) {
		this.ysgh = ysgh;
	}

	public String getYzmc() {
		return yzmc;
	}

	public void setYzmc(String yzmc) {
		this.yzmc = yzmc;
	}

	public BigDecimal getYpdj() {
		return ypdj;
	}

	public void setYpdj(BigDecimal ypdj) {
		this.ypdj = ypdj;
	}

	public Integer getMrcs() {
		return mrcs;
	}

	public void setMrcs(Integer mrcs) {
		this.mrcs = mrcs;
	}

	public BigDecimal getYcsl() {
		return ycsl;
	}

	public void setYcsl(BigDecimal ycsl) {
		this.ycsl = ycsl;
	}

	public BigDecimal getJe() {
		return je;
	}

	public void setJe(BigDecimal je) {
		this.je = je;
	}

	public String getOk() {
		return ok;
	}

	public void setOk(String ok) {
		this.ok = ok;
	}

	public Integer getSybz() {
		return sybz;
	}

	public void setSybz(Integer sybz) {
		this.sybz = sybz;
	}

	public Integer getYpcd() {
		return ypcd;
	}

	public void setYpcd(Integer ypcd) {
		this.ypcd = ypcd;
	}

	public Integer getSrks() {
		return srks;
	}

	public void setSrks(Integer srks) {
		this.srks = srks;
	}

	public Timestamp getQrsj() {
		return qrsj;
	}

	public void setQrsj(Timestamp qrsj) {
		this.qrsj = qrsj;
	}

	public Integer getYpxh() {
		return ypxh;
	}

	public void setYpxh(Integer ypxh) {
		this.ypxh = ypxh;
	}

	public Integer getYplx() {
		return yplx;
	}

	public void setYplx(Integer yplx) {
		this.yplx = yplx;
	}

	public Integer getZyh() {
		return zyh;
	}

	public void setZyh(Integer zyh) {
		this.zyh = zyh;
	}

	public Integer getJlxh() {
		return jlxh;
	}

	public void setJlxh(Integer jlxh) {
		this.jlxh = jlxh;
	}

	public Timestamp getKssj() {
		return kssj;
	}

	public void setKssj(Timestamp kssj) {
		this.kssj = kssj;
	}
	
	public Integer getYzxh() {
		return yzxh;
	}

	public void setYzxh(Integer yzxh) {
		this.yzxh = yzxh;
	}

	public Integer getYzzh() {
		return yzzh;
	}

	public void setYzzh(Integer yzzh) {
		this.yzzh = yzzh;
	}

	public Timestamp getTzsj() {
		return tzsj;
	}

	public void setTzsj(Timestamp tzsj) {
		this.tzsj = tzsj;
	}

	public Integer getMzcs() {
		return mzcs;
	}

	public void setMzcs(Integer mzcs) {
		this.mzcs = mzcs;
	}

	public Integer getXmlx() {
		return xmlx;
	}

	public void setXmlx(Integer xmlx) {
		this.xmlx = xmlx;
	}

	public String getCzgh() {
		return czgh;
	}

	public void setCzgh(String czgh) {
		this.czgh = czgh;
	}

	public Integer getZxks() {
		return zxks;
	}

	public void setZxks(Integer zxks) {
		this.zxks = zxks;
	}

	public Integer getYepb() {
		return yepb;
	}

	public void setYepb(Integer yepb) {
		this.yepb = yepb;
	}

	public Integer getLsbz() {
		return lsbz;
	}

	public void setLsbz(Integer lsbz) {
		this.lsbz = lsbz;
	}

	public Integer getLsyz() {
		return lsyz;
	}

	public void setLsyz(Integer lsyz) {
		this.lsyz = lsyz;
	}

	public Integer getZfpb() {
		return zfpb;
	}

	public void setZfpb(Integer zfpb) {
		this.zfpb = zfpb;
	}

	public String getBrch() {
		return brch;
	}

	public void setBrch(String brch) {
		this.brch = brch;
	}

	public String getBrxm() {
		return brxm;
	}

	public void setBrxm(String brxm) {
		this.brxm = brxm;
	}

	public Integer getBrxz() {
		return brxz;
	}

	public void setBrxz(Integer brxz) {
		this.brxz = brxz;
	}

	public String getGzdw() {
		return gzdw;
	}

	public void setGzdw(String gzdw) {
		this.gzdw = gzdw;
	}

	public Integer getBrks() {
		return brks;
	}

	public void setBrks(Integer brks) {
		this.brks = brks;
	}

	public String getSypc() {
		return sypc;
	}

	public void setSypc(String sypc) {
		this.sypc = sypc;
	}

	public Integer getZlxz() {
		return zlxz;
	}

	public void setZlxz(Integer zlxz) {
		this.zlxz = zlxz;
	}

	public Integer getTs() {
		return ts;
	}

	public void setTs(Integer ts) {
		this.ts = ts;
	}

	public Integer getFycs() {
		return fycs;
	}

	public void setFycs(Integer fycs) {
		this.fycs = fycs;
	}

	public Integer getDjid() {
		return djid;
	}

	public void setDjid(Integer djid) {
		this.djid = djid;
	}

	public Integer getYstj() {
		return ystj;
	}

	public void setYstj(Integer ystj) {
		this.ystj = ystj;
	}

	public Integer getSrcs() {
		return srcs;
	}

	public void setSrcs(Integer srcs) {
		this.srcs = srcs;
	}

	public String getYzzxsj() {
		return yzzxsj;
	}

	public void setYzzxsj(String yzzxsj) {
		this.yzzxsj = yzzxsj;
	}

	public String getZyhm() {
		return zyhm;
	}

	public void setZyhm(String zyhm) {
		this.zyhm = zyhm;
	}

	public Integer getZtbz() {
		return ztbz;
	}

	public void setZtbz(Integer ztbz) {
		this.ztbz = ztbz;
	}

	public Integer getZtyzjlxh() {
		return ztyzjlxh;
	}

	public void setZtyzjlxh(Integer ztyzjlxh) {
		this.ztyzjlxh = ztyzjlxh;
	}

	public Integer getYpyf() {
		return ypyf;
	}

	public void setYpyf(Integer ypyf) {
		this.ypyf = ypyf;
	}

	public String getBzxx() {
		return bzxx;
	}

	public void setBzxx(String bzxx) {
		this.bzxx = bzxx;
	}

	public Integer getYzpb() {
		return yzpb;
	}

	public void setYzpb(Integer yzpb) {
		this.yzpb = yzpb;
	}

	public Integer getFyfs() {
		return fyfs;
	}

	public void setFyfs(Integer fyfs) {
		this.fyfs = fyfs;
	}

	public Integer getYjxh() {
		return yjxh;
	}

	public void setYjxh(Integer yjxh) {
		this.yjxh = yjxh;
	}

	public Integer getFysx() {
		return fysx;
	}

	public void setFysx(Integer fysx) {
		this.fysx = fysx;
	}

	public Integer getFyxh() {
		return fyxh;
	}

	public void setFyxh(Integer fyxh) {
		this.fyxh = fyxh;
	}

	public Integer getYzlx() {
		return yzlx;
	}

	public void setYzlx(Integer yzlx) {
		this.yzlx = yzlx;
	}
	
    
}
