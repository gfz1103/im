package com.buit.cis.ims.model;

import com.buit.commons.PageQuery;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * 类名称：ImCwsz<br> 
 * 类描述：住院_床位设置
 * @author LAPTOP-6GUR25CC 
 * @ApiModel(value="住院_床位设置")
 */
public class ImCwsz  extends PageQuery {
	@ApiModelProperty(value="机构编码")
    private Integer jgid;
	@ApiModelProperty(value="病人床号")
    private String brch;
	@ApiModelProperty(value="房间号码")
    private String fjhm;
	@ApiModelProperty(value="床位科室")
    private Integer cwks;
	@ApiModelProperty(value="床位病区")
    private Integer ksdm;
	@ApiModelProperty(value="床位性别")
    private Integer cwxb;
	@ApiModelProperty(value="床位费用")
    private BigDecimal cwfy;
	@ApiModelProperty(value="ICU费用")
    private BigDecimal icu;
	@ApiModelProperty(value="加床判别 | 0.普通床 1.加床 2.虚床")
    private Integer jcpb;
	@ApiModelProperty(value="住院号")
    private Integer zyh;
    @ApiModelProperty(value = "婴儿唯一号 | 和BQ_XSEDJ.YEWYH的取值一致")
    private Integer yewyh;
    @ApiModelProperty(value = "自定义床位 | 自定义床位： 主要针对医保患者，费用清单中需要详细列出具体的床位信息； 0：累加床位费——就累加床位设置维护好的单个床位费用 1：累加床位费——累加自定义维护好的床位费用")
    private Integer zdycw;
    @ApiModelProperty(value = "jcrq")
    private Timestamp jcrq;
    @ApiModelProperty(value = "scbz")
    private String scbz;
    @ApiModelProperty(value = "vipcwfy")
    private BigDecimal vipcwfy;
    @ApiModelProperty(value = "差额床位费用")
    private BigDecimal cecwfy;
    @ApiModelProperty(value = "商保病人床位费用")
    private BigDecimal sbbrcwfy;
    @ApiModelProperty(value = "床位组号")
    private Integer cwzh;
    @ApiModelProperty(value = "床位费用序号")
    private Integer cwfyxh;
    @ApiModelProperty(value = "icu费用序号")
    private Integer icufyxh;
    @ApiModelProperty(value = "vip费用序号")
    private Integer vipfyxh;
    @ApiModelProperty(value = "商保费用序号")
    private Integer sbfyxh;
    @ApiModelProperty(value = "差额费用序号")
    private Integer cefyxh;

    public Integer getJgid() {
        return jgid;
    }

    public void setJgid(Integer jgid) {
        this.jgid = jgid;
    }

    public String getBrch() {
        return brch;
    }

    public void setBrch(String brch) {
        this.brch = brch;
    }

    public String getFjhm() {
        return fjhm;
    }

    public void setFjhm(String fjhm) {
        this.fjhm = fjhm;
    }

    public Integer getCwks() {
        return cwks;
    }

    public void setCwks(Integer cwks) {
        this.cwks = cwks;
    }

    public Integer getKsdm() {
        return ksdm;
    }

    public void setKsdm(Integer ksdm) {
        this.ksdm = ksdm;
    }

    public Integer getCwxb() {
        return cwxb;
    }

    public void setCwxb(Integer cwxb) {
        this.cwxb = cwxb;
    }

    public BigDecimal getCwfy() {
        return cwfy;
    }

    public void setCwfy(BigDecimal cwfy) {
        this.cwfy = cwfy;
    }

    public BigDecimal getIcu() {
        return icu;
    }

    public void setIcu(BigDecimal icu) {
        this.icu = icu;
    }

    public Integer getJcpb() {
        return jcpb;
    }

    public void setJcpb(Integer jcpb) {
        this.jcpb = jcpb;
    }

    public Integer getZyh() {
        return zyh;
    }

    public void setZyh(Integer zyh) {
        this.zyh = zyh;
    }

    public Integer getYewyh() {
        return yewyh;
    }

    public void setYewyh(Integer yewyh) {
        this.yewyh = yewyh;
    }

    public Integer getZdycw() {
        return zdycw;
    }

    public void setZdycw(Integer zdycw) {
        this.zdycw = zdycw;
    }

    public Timestamp getJcrq() {
        return jcrq;
    }

    public void setJcrq(Timestamp jcrq) {
        this.jcrq = jcrq;
    }

    public String getScbz() {
        return scbz;
    }

    public void setScbz(String scbz) {
        this.scbz = scbz;
    }

    public BigDecimal getVipcwfy() {
        return vipcwfy;
    }

    public void setVipcwfy(BigDecimal vipcwfy) {
        this.vipcwfy = vipcwfy;
    }

    public BigDecimal getSbbrcwfy() {
        return sbbrcwfy;
    }

    public void setSbbrcwfy(BigDecimal sbbrcwfy) {
        this.sbbrcwfy = sbbrcwfy;
    }

	public Integer getCwzh() {
		return cwzh;
	}

	public void setCwzh(Integer cwzh) {
		this.cwzh = cwzh;
	}

	public Integer getCwfyxh() {
		return cwfyxh;
	}

	public void setCwfyxh(Integer cwfyxh) {
		this.cwfyxh = cwfyxh;
	}

	public Integer getIcufyxh() {
		return icufyxh;
	}

	public void setIcufyxh(Integer icufyxh) {
		this.icufyxh = icufyxh;
	}

	public Integer getVipfyxh() {
		return vipfyxh;
	}

	public void setVipfyxh(Integer vipfyxh) {
        this.vipfyxh = vipfyxh;
    }

    public Integer getSbfyxh() {
        return sbfyxh;
    }

    public void setSbfyxh(Integer sbfyxh) {
        this.sbfyxh = sbfyxh;
    }

    public BigDecimal getCecwfy() {
        return cecwfy;
    }

    public void setCecwfy(BigDecimal cecwfy) {
        this.cecwfy = cecwfy;
    }

    public Integer getCefyxh() {
        return cefyxh;
    }

    public void setCefyxh(Integer cefyxh) {
        this.cefyxh = cefyxh;
    }
}
