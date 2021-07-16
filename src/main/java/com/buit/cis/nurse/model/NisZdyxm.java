package com.buit.cis.nurse.model;

import java.sql.Timestamp;
import com.buit.commons.PageQuery;

/**
 * 类名称：NisZdyxm<br> 
 * 类描述：护理记录单自定义项目
 * @author GONGFANGZHOU 
 * @ApiModel(value="护理记录单自定义项目")
 */
public class NisZdyxm  extends  PageQuery{
	//@ApiModelProperty(value="项目代码(主键)")
    /** 项目代码(主键) */
    private Integer xmdm;
	//@ApiModelProperty(value="项目名称")
    /** 项目名称 */
    private String xmmc;
	//@ApiModelProperty(value="作废判别")
    /** 作废判别 */
    private Integer zfpb;
	//@ApiModelProperty(value="拼音代码")
    /** 拼音代码 */
    private String pydm;
	//@ApiModelProperty(value="机构id")
    /** 机构id */
    private Integer jgid;

    /** 设置:项目代码(主键)  */
    public void setXmdm(Integer value) {
        this.xmdm = value;
    }
    /** 获取:项目代码(主键) */
    public Integer getXmdm() {
        return xmdm;
    }

    /** 设置:项目名称  */
    public void setXmmc(String value) {
        this.xmmc = value;
    }
    /** 获取:项目名称 */
    public String getXmmc() {
        return xmmc;
    }

    /** 设置:作废判别  */
    public void setZfpb(Integer value) {
        this.zfpb = value;
    }
    /** 获取:作废判别 */
    public Integer getZfpb() {
        return zfpb;
    }

    /** 设置:拼音代码  */
    public void setPydm(String value) {
        this.pydm = value;
    }
    /** 获取:拼音代码 */
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