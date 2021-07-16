package com.buit.cis.nurse.model;

import java.sql.Timestamp;

import com.buit.commons.PageQuery;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 类名称：NisHlmbwhb<br> 
 * 类描述：护理单模板维护表
 * @author GONGFANGZHOU 
 */
@ApiModel(value="护理单模板维护表")
public class NisHlmbwhb  extends  PageQuery{
	@ApiModelProperty(value="主键id")
    /** 主键id */
    private Integer id;
	@ApiModelProperty(value="护理模板名称")
    /** 护理模板名称 */
    private String hlmbmc;
	@ApiModelProperty(value="护理内容")
    /** 护理内容 */
    private String hlnr;
	@ApiModelProperty(value="作废判别(1:是0:否)")
    /** 作废判别(1:是0:否) */
    private Integer zfpb;
	@ApiModelProperty(value="机构id")
    /** 机构id */
    private Integer jgid;
	@ApiModelProperty(value="科室代码(0:全院)")
    /** 科室代码(0:全院) */
    private Integer ksdm;
	@ApiModelProperty(value="个人")
    /** 个人 */
    private Integer userid;
	@ApiModelProperty(value="操作工号")
    /** 操作工号 */
    private Integer czgh;
	@ApiModelProperty(value="操作时间")
    /** 操作时间 */
    private Timestamp czsj;
	@ApiModelProperty(value="备注信息")
    /** 备注信息 */
    private String bzxx;
	@ApiModelProperty(value="拼音代码")
    /** 拼音代码 */
    private String pydm;
	@ApiModelProperty(value="病区代码")
    /** 病区代码 */
    private Integer bqdm;

    /** 设置:主键id  */
    public void setId(Integer value) {
        this.id = value;
    }
    /** 获取:主键id */
    public Integer getId() {
        return id;
    }

    /** 设置:护理模板名称  */
    public void setHlmbmc(String value) {
        this.hlmbmc = value;
    }
    /** 获取:护理模板名称 */
    public String getHlmbmc() {
        return hlmbmc;
    }

    /** 设置:护理内容  */
    public void setHlnr(String value) {
        this.hlnr = value;
    }
    /** 获取:护理内容 */
    public String getHlnr() {
        return hlnr;
    }

    /** 设置:作废判别(1:是0:否)  */
    public void setZfpb(Integer value) {
        this.zfpb = value;
    }
    /** 获取:作废判别(1:是0:否) */
    public Integer getZfpb() {
        return zfpb;
    }

    /** 设置:机构id  */
    public void setJgid(Integer value) {
        this.jgid = value;
    }
    /** 获取:机构id */
    public Integer getJgid() {
        return jgid;
    }

    /** 设置:科室代码(0:全院)  */
    public void setKsdm(Integer value) {
        this.ksdm = value;
    }
    /** 获取:科室代码(0:全院) */
    public Integer getKsdm() {
        return ksdm;
    }

    /** 设置:个人  */
    public void setUserid(Integer value) {
        this.userid = value;
    }
    /** 获取:个人 */
    public Integer getUserid() {
        return userid;
    }

    /** 设置:操作工号  */
    public void setCzgh(Integer value) {
        this.czgh = value;
    }
    /** 获取:操作工号 */
    public Integer getCzgh() {
        return czgh;
    }

    /** 设置:操作时间  */
    public void setCzsj(Timestamp value) {
        this.czsj = value;
    }
    /** 获取:操作时间 */
    public Timestamp getCzsj() {
        return czsj;
    }

    /** 设置:备注信息  */
    public void setBzxx(String value) {
        this.bzxx = value;
    }
    /** 获取:备注信息 */
    public String getBzxx() {
        return bzxx;
    }
    
    /** 设置:拼音代码 */
	public void setPydm(String pydm) {
		this.pydm = pydm;
	}
	/** 获取:拼音代码 */
	public String getPydm() {
		return pydm;
	}
	
	/** 设置:病区代码 */
	public void setBqdm(Integer bqdm) {
		this.bqdm = bqdm;
	}
	
	/** 获取:病区代码 */
	public Integer getBqdm() {
		return bqdm;
	}
	
}