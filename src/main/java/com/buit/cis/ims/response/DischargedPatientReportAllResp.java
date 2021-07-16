package com.buit.cis.ims.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * 出院病人费用报表出参
 * zhouhaisheng
 */
@ApiModel(value = "出院病人费用报表出参")
public class DischargedPatientReportAllResp {

    @ApiModelProperty(value = "list信息")
    private List<DischargedPatientReportResp> dischargedPatientReportRespList;

    @ApiModelProperty(value = "费用汇总信息")
    private List<DischargedPatientReportHjResp> dischargedPatientReportHjRespList;

    public List<DischargedPatientReportResp> getDischargedPatientReportRespList() {
        return dischargedPatientReportRespList;
    }

    public void setDischargedPatientReportRespList(List<DischargedPatientReportResp> dischargedPatientReportRespList) {
        this.dischargedPatientReportRespList = dischargedPatientReportRespList;
    }

    public List<DischargedPatientReportHjResp> getDischargedPatientReportHjRespList() {
        return dischargedPatientReportHjRespList;
    }

    public void setDischargedPatientReportHjRespList(List<DischargedPatientReportHjResp> dischargedPatientReportHjRespList) {
        this.dischargedPatientReportHjRespList = dischargedPatientReportHjRespList;
    }
}
