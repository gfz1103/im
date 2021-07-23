package com.buit.cis.dctwork.request;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * add by songxx
 * 病历树-请求类
 */
public class ImHzryEmrTreeReq implements Serializable {
    /**
     * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
     */
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "门诊号码/住院号码")
    private String hm;
    @ApiModelProperty(value = "模式:1门诊医生,2住院医生,3住院护士")
    private String mode;
    @ApiModelProperty(value = "1级树，病历目录ID")
    private Integer ctlId;
    @ApiModelProperty(value = "病历类别,1:普陀病历；2:病程病历")
    private Integer emrType;
    @ApiModelProperty(value = "普通病历2级树，病历分类ID")
    private Integer tplClassId;
    @ApiModelProperty(value = "病程病历2级数，病历书写年月,格式yyyy-MM")
    private String tName;

    public String getHm() {
        return hm;
    }

    public void setHm(String hm) {
        this.hm = hm;
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public Integer getCtlId() {
        return ctlId;
    }

    public void setCtlId(Integer ctlId) {
        this.ctlId = ctlId;
    }

    public Integer getEmrType() {
        return emrType;
    }

    public void setEmrType(Integer emrType) {
        this.emrType = emrType;
    }

    public Integer getTplClassId() {
        return tplClassId;
    }

    public void setTplClassId(Integer tplClassId) {
        this.tplClassId = tplClassId;
    }

    public String gettName() {
        return tName;
    }

    public void settName(String tName) {
        this.tName = tName;
    }
}
