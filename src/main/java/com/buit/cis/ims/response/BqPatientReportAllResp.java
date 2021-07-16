package com.buit.cis.ims.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * 病区病人费用报表出参
 * zhouhaisheng
 */
@ApiModel(value = "病区病人费用报表出参")
public class BqPatientReportAllResp {

    @ApiModelProperty(value = "list信息")
    private List<BqPatientReportResp> bqPatientReportRespList;

    @ApiModelProperty(value = "费用汇总信息")
    private List<BqPatientReportHjResp> bqPatientReportHjRespList;

    public List<BqPatientReportResp> getBqPatientReportRespList() {
        return bqPatientReportRespList;
    }

    public void setBqPatientReportRespList(List<BqPatientReportResp> bqPatientReportRespList) {
        this.bqPatientReportRespList = bqPatientReportRespList;
    }

    public List<BqPatientReportHjResp> getBqPatientReportHjRespList() {
        return bqPatientReportHjRespList;
    }

    public void setBqPatientReportHjRespList(List<BqPatientReportHjResp> bqPatientReportHjRespList) {
        this.bqPatientReportHjRespList = bqPatientReportHjRespList;
    }
}
