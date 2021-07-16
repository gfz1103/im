package com.buit.cis.bedresev.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 住院预约统计分析出参
 * zhouhaisheng
 */
@ApiModel(value = "住院预约统计分析出参")
public class QueryImBedresevCollectResp {

    @ApiModelProperty(value = "预约科室")
    private Integer yyks;

    @ApiModelProperty(value = "预约总人数")
    private Integer allCollectNum;

    @ApiModelProperty(value = "等床人次")
    private Integer waitCollectNum;

    @ApiModelProperty(value = "待床人次")
    private Integer dcCollectNum;

    @ApiModelProperty(value = "逾期人次")
    private Integer yqCollectNum;

    @ApiModelProperty(value = "入院人次")
    private Integer ryCollectNum;

    public Integer getYyks() {
        return yyks;
    }

    public void setYyks(Integer yyks) {
        this.yyks = yyks;
    }

    public Integer getAllCollectNum() {
        return allCollectNum;
    }

    public void setAllCollectNum(Integer allCollectNum) {
        this.allCollectNum = allCollectNum;
    }

    public Integer getWaitCollectNum() {
        return waitCollectNum;
    }

    public void setWaitCollectNum(Integer waitCollectNum) {
        this.waitCollectNum = waitCollectNum;
    }

    public Integer getDcCollectNum() {
        return dcCollectNum;
    }

    public void setDcCollectNum(Integer dcCollectNum) {
        this.dcCollectNum = dcCollectNum;
    }

    public Integer getYqCollectNum() {
        return yqCollectNum;
    }

    public void setYqCollectNum(Integer yqCollectNum) {
        this.yqCollectNum = yqCollectNum;
    }

    public Integer getRyCollectNum() {
        return ryCollectNum;
    }

    public void setRyCollectNum(Integer ryCollectNum) {
        this.ryCollectNum = ryCollectNum;
    }
}
