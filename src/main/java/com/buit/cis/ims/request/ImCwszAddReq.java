package com.buit.cis.ims.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;

/**
 * 类名称：ImCwsz<br>
 * 类描述：住院_床位设置新增<br>
 * @author zhouhaisheng
 */
@ApiModel(value="住院_床位设置新增")
public class ImCwszAddReq {

    @ApiModelProperty(value="床位号码")
    private String brch;
    @ApiModelProperty(value="房间号码")
    private String fjhm;
    @ApiModelProperty(value="床位科室")
    private Integer cwks;
    @ApiModelProperty(value="床位病区 字典:sys_dict_config:18")
    private Integer ksdm;
    @ApiModelProperty(value = "床位性别")
    private Integer cwxb;
    @ApiModelProperty(value = "床位费用")
    private BigDecimal cwfy;
    @ApiModelProperty(value = "ICU费用")
    private BigDecimal icu;
    @ApiModelProperty(value = "加床判别 | 0.普通床 1.加床 2.虚床")
    private Integer jcpb;
    @ApiModelProperty(value = "Vip床位费用")
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

    public void setBrch(String brch) {
        this.brch = brch;
    }

    public void setFjhm(String fjhm) {
        this.fjhm = fjhm;
    }

    public void setCwks(Integer cwks) {
        this.cwks = cwks;
    }

    public void setKsdm(Integer ksdm) {
        this.ksdm = ksdm;
    }

    public void setCwxb(Integer cwxb) {
        this.cwxb = cwxb;
    }

    public void setCwfy(BigDecimal cwfy) {
        this.cwfy = cwfy;
    }

    public void setIcu(BigDecimal icu) {
        this.icu = icu;
    }

    public void setJcpb(Integer jcpb) {
        this.jcpb = jcpb;
    }

    public String getBrch() {
        return brch;
    }

    public String getFjhm() {
        return fjhm;
    }

    public Integer getCwks() {
        return cwks;
    }

    public Integer getKsdm() {
        return ksdm;
    }

    public Integer getCwxb() {
        return cwxb;
    }

    public BigDecimal getCwfy() {
        return cwfy;
    }

    public BigDecimal getIcu() {
        return icu;
    }

    public Integer getJcpb() {
        return jcpb;
    }

    public BigDecimal getSbbrcwfy() {
        return sbbrcwfy;
    }

    public void setSbbrcwfy(BigDecimal sbbrcwfy) {
        this.sbbrcwfy = sbbrcwfy;
    }

    public BigDecimal getVipcwfy() {
        return vipcwfy;
    }

    public void setVipcwfy(BigDecimal vipcwfy) {
        this.vipcwfy = vipcwfy;
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
