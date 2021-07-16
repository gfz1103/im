package com.buit.cis.dctwork.model;

import java.sql.Timestamp;
import com.buit.commons.PageQuery;

/**
 * 类名称：CisHzyjmb<br> 
 * 类描述：会诊意见模板维护表
 * @author GONGFANGZHOU 
 * @ApiModel(value="会诊意见模板维护表")
 */
public class CisHzyjmb  extends  PageQuery{
	//@ApiModelProperty(value="记录序号(主键)")
    /** 记录序号(主键) */
    private Integer jlxh;
	//@ApiModelProperty(value="模板名称")
    /** 模板名称 */
    private String mbmc;
	//@ApiModelProperty(value="会诊意见")
    /** 会诊意见 */
    private String hzyj;
	//@ApiModelProperty(value="个人工号(个人必填)")
    /** 个人工号(个人必填) */
    private Integer ysgh;
	//@ApiModelProperty(value="科室代码(全院为空)")
    /** 科室代码(全院为空) */
    private Integer ksdm;
	//@ApiModelProperty(value="作废判别(1是0否)")
    /** 作废判别(1是0否) */
    private Integer zfpb;
	//@ApiModelProperty(value="名称拼音代码")
    /** 名称拼音代码 */
    private String pydm;
	//@ApiModelProperty(value="机构id")
    /** 机构id */
    private Integer jgid;

    /** 设置:记录序号(主键)  */
    public void setJlxh(Integer value) {
        this.jlxh = value;
    }
    /** 获取:记录序号(主键) */
    public Integer getJlxh() {
        return jlxh;
    }

    /** 设置:模板名称  */
    public void setMbmc(String value) {
        this.mbmc = value;
    }
    /** 获取:模板名称 */
    public String getMbmc() {
        return mbmc;
    }

    /** 设置:会诊意见  */
    public void setHzyj(String value) {
        this.hzyj = value;
    }
    /** 获取:会诊意见 */
    public String getHzyj() {
        return hzyj;
    }

    /** 设置:个人工号(个人必填)  */
    public void setYsgh(Integer value) {
        this.ysgh = value;
    }
    /** 获取:个人工号(个人必填) */
    public Integer getYsgh() {
        return ysgh;
    }

    /** 设置:科室代码(全院为空)  */
    public void setKsdm(Integer value) {
        this.ksdm = value;
    }
    /** 获取:科室代码(全院为空) */
    public Integer getKsdm() {
        return ksdm;
    }

    /** 设置:作废判别(1是0否)  */
    public void setZfpb(Integer value) {
        this.zfpb = value;
    }
    /** 获取:作废判别(1是0否) */
    public Integer getZfpb() {
        return zfpb;
    }

    /** 设置:名称拼音代码  */
    public void setPydm(String value) {
        this.pydm = value;
    }
    /** 获取:名称拼音代码 */
    public String getPydm() {
        return pydm;
    }

    /** 设置:机构id  */
    public void setJgid(Integer value) {
        this.jgid = value;
    }
    /** 获取:机构id */
    public Integer getJgid() {
        return jgid;
    }


}