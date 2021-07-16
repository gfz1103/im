package com.buit.cis.dctwork.model;

import java.sql.Timestamp;
import com.buit.commons.PageQuery;

/**
 * 类名称：NisSmtz<br> 
 * 类描述：生命体征
 * @author GONGFANGZHOU 
 * @ApiModel(value="生命体征")
 */
public class NisSmtz  extends  PageQuery{
	//@ApiModelProperty(value="采集号")
    /** 采集号 */
    private Integer cjh;
	//@ApiModelProperty(value="采集组号（仅为方便程序处理）")
    /** 采集组号（仅为方便程序处理） */
    private Integer cjzh;
	//@ApiModelProperty(value="项目号（体征项目）")
    /** 项目号（体征项目） */
    private Integer xmh;
	//@ApiModelProperty(value="计划标志（0：临时；1：计划）")
    /** 计划标志（0：临时；1：计划） */
    private Integer jhbz;
	//@ApiModelProperty(value="采集时间（测量时间）")
    /** 采集时间（测量时间） */
    private Timestamp cjsj;
	//@ApiModelProperty(value="住院号")
    /** 住院号 */
    private Integer zyh;
	//@ApiModelProperty(value="病人科室")
    /** 病人科室 */
    private Integer brks;
	//@ApiModelProperty(value="病人病区")
    /** 病人病区 */
    private Integer brbq;
	//@ApiModelProperty(value="病人床号")
    /** 病人床号 */
    private String brch;
	//@ApiModelProperty(value="体征内容")
    /** 体征内容 */
    private String tznr;
	//@ApiModelProperty(value="项目下标（体温类型等）")
    /** 项目下标（体温类型等） */
    private String xmxb;
	//@ApiModelProperty(value="复测标志（0：首测  1：复测）")
    /** 复测标志（0：首测  1：复测） */
    private Integer fcbz;
	//@ApiModelProperty(value="复测关联（FCBZ=1，记录需要复测记录的采集号）")
    /** 复测关联（FCBZ=1，记录需要复测记录的采集号） */
    private Integer fcgl;
	//@ApiModelProperty(value="体温单显示（0：不显示 1：显示，显示在体温单的‘空白栏’或‘其他’栏内）")
    /** 体温单显示（0：不显示 1：显示，显示在体温单的‘空白栏’或‘其他’栏内） */
    private Integer twdxs;
	//@ApiModelProperty(value="记录时间（实际录入系统的时间）")
    /** 记录时间（实际录入系统的时间） */
    private Timestamp jlsj;
	//@ApiModelProperty(value="记录人员")
    /** 记录人员 */
    private String jlgh;
	//@ApiModelProperty(value="作废标志（0：有效 1：已作废）")
    /** 作废标志（0：有效 1：已作废） */
    private Integer zfbz;
	//@ApiModelProperty(value="备注信息（复测方法、辅助呼吸等）")
    /** 备注信息（复测方法、辅助呼吸等） */
    private String bzxx;
	//@ApiModelProperty(value="异常标志（-2：低  -1：偏低  0：正常  1：偏高  2：高）")
    /** 异常标志（-2：低  -1：偏低  0：正常  1：偏高  2：高） */
    private Integer ycbz;
	//@ApiModelProperty(value="jgid")
    /** jgid */
    private Integer jgid;
    //@ApiModelProperty(value="自定义序号")
    /** 自定义序号 */
    private Integer zdyxh;
    //@ApiModelProperty(value="时间段")
    /** 时间段 */
    private Integer sjd;

    /** 设置:采集号  */
    public void setCjh(Integer value) {
        this.cjh = value;
    }
    /** 获取:采集号 */
    public Integer getCjh() {
        return cjh;
    }

    /** 设置:采集组号（仅为方便程序处理）  */
    public void setCjzh(Integer value) {
        this.cjzh = value;
    }
    /** 获取:采集组号（仅为方便程序处理） */
    public Integer getCjzh() {
        return cjzh;
    }

    /** 设置:项目号（体征项目）  */
    public void setXmh(Integer value) {
        this.xmh = value;
    }
    /** 获取:项目号（体征项目） */
    public Integer getXmh() {
        return xmh;
    }

    /** 设置:计划标志（0：临时；1：计划）  */
    public void setJhbz(Integer value) {
        this.jhbz = value;
    }
    /** 获取:计划标志（0：临时；1：计划） */
    public Integer getJhbz() {
        return jhbz;
    }

    /** 设置:采集时间（测量时间）  */
    public void setCjsj(Timestamp value) {
        this.cjsj = value;
    }
    /** 获取:采集时间（测量时间） */
    public Timestamp getCjsj() {
        return cjsj;
    }

    /** 设置:住院号  */
    public void setZyh(Integer value) {
        this.zyh = value;
    }
    /** 获取:住院号 */
    public Integer getZyh() {
        return zyh;
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

    /** 设置:体征内容  */
    public void setTznr(String value) {
        this.tznr = value;
    }
    /** 获取:体征内容 */
    public String getTznr() {
        return tznr;
    }

    /** 设置:项目下标（体温类型等）  */
    public void setXmxb(String value) {
        this.xmxb = value;
    }
    /** 获取:项目下标（体温类型等） */
    public String getXmxb() {
        return xmxb;
    }

    /** 设置:复测标志（0：首测  1：复测）  */
    public void setFcbz(Integer value) {
        this.fcbz = value;
    }
    /** 获取:复测标志（0：首测  1：复测） */
    public Integer getFcbz() {
        return fcbz;
    }

    /** 设置:复测关联（FCBZ=1，记录需要复测记录的采集号）  */
    public void setFcgl(Integer value) {
        this.fcgl = value;
    }
    /** 获取:复测关联（FCBZ=1，记录需要复测记录的采集号） */
    public Integer getFcgl() {
        return fcgl;
    }

    /** 设置:体温单显示（0：不显示 1：显示，显示在体温单的‘空白栏’或‘其他’栏内）  */
    public void setTwdxs(Integer value) {
        this.twdxs = value;
    }
    /** 获取:体温单显示（0：不显示 1：显示，显示在体温单的‘空白栏’或‘其他’栏内） */
    public Integer getTwdxs() {
        return twdxs;
    }

    /** 设置:记录时间（实际录入系统的时间）  */
    public void setJlsj(Timestamp value) {
        this.jlsj = value;
    }
    /** 获取:记录时间（实际录入系统的时间） */
    public Timestamp getJlsj() {
        return jlsj;
    }

    /** 设置:记录人员  */
    public void setJlgh(String value) {
        this.jlgh = value;
    }
    /** 获取:记录人员 */
    public String getJlgh() {
        return jlgh;
    }

    /** 设置:作废标志（0：有效 1：已作废）  */
    public void setZfbz(Integer value) {
        this.zfbz = value;
    }
    /** 获取:作废标志（0：有效 1：已作废） */
    public Integer getZfbz() {
        return zfbz;
    }

    /** 设置:备注信息（复测方法、辅助呼吸等）  */
    public void setBzxx(String value) {
        this.bzxx = value;
    }
    /** 获取:备注信息（复测方法、辅助呼吸等） */
    public String getBzxx() {
        return bzxx;
    }

    /** 设置:异常标志（-2：低  -1：偏低  0：正常  1：偏高  2：高）  */
    public void setYcbz(Integer value) {
        this.ycbz = value;
    }
    /** 获取:异常标志（-2：低  -1：偏低  0：正常  1：偏高  2：高） */
    public Integer getYcbz() {
        return ycbz;
    }

    /** 设置:jgid  */
    public void setJgid(Integer value) {
        this.jgid = value;
    }
    /** 获取:jgid */
    public Integer getJgid() {
        return jgid;
    }
	public Integer getZdyxh() {
		return zdyxh;
	}
	public void setZdyxh(Integer zdyxh) {
		this.zdyxh = zdyxh;
	}
	public Integer getSjd() {
		return sjd;
	}
	public void setSjd(Integer sjd) {
		this.sjd = sjd;
	}
	
	

}
