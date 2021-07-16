package com.buit.cis.ims.response;

import com.buit.commons.PageQuery;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;
import java.sql.Timestamp;


/**
 * 类名称：ImCwsz<br>
 * 类描述：住院_床位设置<br>
 *
 * @author zhouhaisheng
 */
@ApiModel(value = "住院_床位设置")
public class ImCwszResp extends PageQuery {
    @JsonIgnore
    @ApiModelProperty(value = "机构编码")
    private Integer jgid;
    @ApiModelProperty(value = "床位号码")
    private String brch;
    @ApiModelProperty(value = "房间号码")
    private String fjhm;
    @ApiModelProperty(value = "床位科室")
    private Long cwks;
    @ApiModelProperty(value = "床位病区")
    private Long ksdm;
    @ApiModelProperty(value = "床位性别")
    private Integer cwxb;
    @ApiModelProperty(value = "床位费用")
    private BigDecimal cwfy;
    @ApiModelProperty(value = "ICU费用")
    private BigDecimal icu;
    @ApiModelProperty(value = "加床判别 | 0.普通床 1.加床 2.虚床")
    private Integer jcpb;
    @JsonIgnore
    @ApiModelProperty(value = "住院号")
    private Long zyh;
    @JsonIgnore
    @ApiModelProperty(value = "婴儿唯一号 | 和BQ_XSEDJ.YEWYH的取值一致")
    private Long yewyh;
    @JsonIgnore
    @ApiModelProperty(value = "自定义床位 | 自定义床位： 主要针对医保患者，费用清单中需要详细列出具体的床位信息； 0：累加床位费——就累加床位设置维护好的单个床位费用 1：累加床位费——累加自定义维护好的床位费用")
    private Boolean zdycw;
    @JsonIgnore
    @ApiModelProperty(value = "jcrq")
    private Timestamp jcrq;
    @JsonIgnore
    @ApiModelProperty(value = "scbz")
    private String scbz;
    @ApiModelProperty(value = "Vip床位费用")
    private BigDecimal vipcwfy;
    @ApiModelProperty(value = "差额床位费用")
    private BigDecimal cecwfy;
    @ApiModelProperty(value = "商保病人床位费用")
    private BigDecimal sbbrcwfy;
    @ApiModelProperty(value = "床位组号")
    private Integer cwzh;
    @ApiModelProperty(value = "床位组号名称")
    private String cwzhmc;
    @ApiModelProperty(value = "床位费用序号")
    private Integer cwfyxh;
    @ApiModelProperty(value = "icu费用序号")
    private Integer icufyxh;
    @ApiModelProperty(value = "vip费用序号")
    private Integer vipfyxh;
    @ApiModelProperty(value = "差额费用序号")
    private Integer cefyxh;
    @ApiModelProperty(value = "商保费用序号")
    private Integer sbfyxh;
    @ApiModelProperty(value = "床位费用名称")
    private String cwfymc;
    @ApiModelProperty(value = "icu费用名称")
    private String icufymc;
    @ApiModelProperty(value = "vip费用名称")
    private String vipfymc;
    @ApiModelProperty(value = "差额费用名称")
    private String cefymc;
    @ApiModelProperty(value = "商保费用名称")
    private String sbfymc;

    /**
     * 设置:机构编码
     */
    public void setJgid(Integer value) {
        this.jgid = value;
    }

    /**
     * 获取:机构编码
     */
    public Integer getJgid() {
        return jgid;
    }

    /**
     * 设置:病人床号
     */
    public void setBrch(String value) {
        this.brch = value;
    }

    /**
     * 获取:病人床号
     */
    public String getBrch() {
        return brch;
    }

    /**
     * 设置:房间号码
     */
    public void setFjhm(String value) {
        this.fjhm = value;
    }

    /**
     * 获取:房间号码
     */
    public String getFjhm() {
        return fjhm;
    }

    /**
     * 设置:床位科室
     */
    public void setCwks(Long value) {
        this.cwks = value;
    }

    /**
     * 获取:床位科室
     */
    public Long getCwks() {
        return cwks;
    }

    /**
     * 设置:床位病区
     */
    public void setKsdm(Long value) {
        this.ksdm = value;
    }

    /**
     * 获取:床位病区
     */
    public Long getKsdm() {
        return ksdm;
    }

    /**
     * 设置:床位性别
     */
    public void setCwxb(Integer value) {
        this.cwxb = value;
    }

    /**
     * 获取:床位性别
     */
    public Integer getCwxb() {
        return cwxb;
    }

    /**
     * 设置:床位费用
     */
    public void setCwfy(BigDecimal value) {
        this.cwfy = value;
    }

    /**
     * 获取:床位费用
     */
    public BigDecimal getCwfy() {
        return cwfy;
    }

    /**
     * 设置:ICU费用
     */
    public void setIcu(BigDecimal value) {
        this.icu = value;
    }

    /**
     * 获取:ICU费用
     */
    public BigDecimal getIcu() {
        return icu;
    }

    /**
     * 设置:加床判别 | 0.普通床 1.加床 2.虚床
     */
    public Integer getJcpb() {
        return jcpb;
    }

    public void setJcpb(Integer jcpb) {
        this.jcpb = jcpb;
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
     * 设置:婴儿唯一号 | 和BQ_XSEDJ.YEWYH的取值一致
     */
    public void setYewyh(Long value) {
        this.yewyh = value;
    }

    /**
     * 获取:婴儿唯一号 | 和BQ_XSEDJ.YEWYH的取值一致
     */
    public Long getYewyh() {
        return yewyh;
    }

    /**
     * 设置:自定义床位 | 自定义床位： 主要针对医保患者，费用清单中需要详细列出具体的床位信息； 0：累加床位费——就累加床位设置维护好的单个床位费用 1：累加床位费——累加自定义维护好的床位费用
     */
    public void setZdycw(Boolean value) {
        this.zdycw = value;
    }

    /**
     * 获取:自定义床位 | 自定义床位： 主要针对医保患者，费用清单中需要详细列出具体的床位信息； 0：累加床位费——就累加床位设置维护好的单个床位费用 1：累加床位费——累加自定义维护好的床位费用
     */
    public Boolean getZdycw() {
        return zdycw;
    }

    /**
     * 设置:jcrq
     */
    public void setJcrq(Timestamp value) {
        this.jcrq = value;
    }

    /**
     * 获取:jcrq
     */
    public Timestamp getJcrq() {
        return jcrq;
    }

    /**
     * 设置:scbz
     */
    public void setScbz(String value) {
        this.scbz = value;
    }

    /**
     * 获取:scbz
     */
    public String getScbz() {
        return scbz;
    }

    /**
     * 设置:vipcwfy
     */
    public void setVipcwfy(BigDecimal value) {
        this.vipcwfy = value;
    }

    /**
     * 获取:vipcwfy
     */
    public BigDecimal getVipcwfy() {
        return vipcwfy;
    }

    /**
     * 设置:商保病人床位费用
     */
    public void setSbbrcwfy(BigDecimal value) {
        this.sbbrcwfy = value;
    }

    /**
     * 获取:商保病人床位费用
     */
    public BigDecimal getSbbrcwfy() {
        return sbbrcwfy;
    }

    public Integer getCwzh() {
		return cwzh;
	}

	public void setCwzh(Integer cwzh) {
		this.cwzh = cwzh;
	}

	public String getCwzhmc() {
        return cwzhmc;
    }

    public void setCwzhmc(String cwzhmc) {
        this.cwzhmc = cwzhmc;
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

    public String getCwfymc() {
        return cwfymc;
    }

    public void setCwfymc(String cwfymc) {
        this.cwfymc = cwfymc;
    }

    public String getIcufymc() {
        return icufymc;
    }

    public void setIcufymc(String icufymc) {
        this.icufymc = icufymc;
    }

    public String getVipfymc() {
        return vipfymc;
    }

    public void setVipfymc(String vipfymc) {
        this.vipfymc = vipfymc;
    }

    public String getSbfymc() {
        return sbfymc;
    }

    public void setSbfymc(String sbfymc) {
        this.sbfymc = sbfymc;
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

    public String getCefymc() {
        return cefymc;
    }

    public void setCefymc(String cefymc) {
        this.cefymc = cefymc;
    }
}
