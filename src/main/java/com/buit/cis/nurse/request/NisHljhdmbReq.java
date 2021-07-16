   
package com.buit.cis.nurse.request;

import com.buit.commons.PageQuery;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


/**
 * 类名称：NisHljhdmb<br> 
 * 类描述：护理执行单模板<br>
 * @author GONGFANGZHOU
 */
@ApiModel(value="护理计划单模板_Req")
public class NisHljhdmbReq  extends  PageQuery{
    @ApiModelProperty(value="主键记录序号")
    private Integer jlxh;
    @ApiModelProperty(value="护理问题")
    private String hlwt;
    @ApiModelProperty(value="护理目标")
    private String hlmb;
    @ApiModelProperty(value="护理措施")
    private String hlcs;
    @ApiModelProperty(value="机构id")
    private Integer jgid;
    @ApiModelProperty(value="科室代码")
    private Integer ksdm;
    @ApiModelProperty(value="病区代码")
    private Integer bqdm;
    @ApiModelProperty(value="备注信息")
    private String bzxx;
    @ApiModelProperty(value="作废判别")
    private Integer zfpb;
    /**
     * 设置:主键记录序号
     */
    public void setJlxh(Integer value) {
        this.jlxh = value;
    }
    /**
     * 获取:主键记录序号
     */
    public Integer getJlxh() {
        return jlxh;
    }
    /**
     * 设置:护理问题
     */
    public void setHlwt(String value) {
        this.hlwt = value;
    }
    /**
     * 获取:护理问题
     */
    public String getHlwt() {
        return hlwt;
    }
    /**
     * 设置:护理目标
     */
    public void setHlmb(String value) {
        this.hlmb = value;
    }
    /**
     * 获取:护理目标
     */
    public String getHlmb() {
        return hlmb;
    }
    /**
     * 设置:护理措施
     */
    public void setHlcs(String value) {
        this.hlcs = value;
    }
    /**
     * 获取:护理措施
     */
    public String getHlcs() {
        return hlcs;
    }
    /**
     * 设置:机构id
     */
    public void setJgid(Integer value) {
        this.jgid = value;
    }
    /**
     * 获取:机构id
     */
    public Integer getJgid() {
        return jgid;
    }
    /**
     * 设置:科室代码
     */
    public void setKsdm(Integer value) {
        this.ksdm = value;
    }
    /**
     * 获取:科室代码
     */
    public Integer getKsdm() {
        return ksdm;
    }
    /**
     * 设置:病区代码
     */
    public void setBqdm(Integer value) {
        this.bqdm = value;
    }
    /**
     * 获取:病区代码
     */
    public Integer getBqdm() {
        return bqdm;
    }
    /**
     * 设置:备注信息
     */
    public void setBzxx(String value) {
        this.bzxx = value;
    }
    /**
     * 获取:备注信息
     */
    public String getBzxx() {
        return bzxx;
    }
    /**
     * 设置:作废判别
     */
    public void setZfpb(Integer value) {
        this.zfpb = value;
    }
    /**
     * 获取:作废判别
     */
    public Integer getZfpb() {
        return zfpb;
    }
}