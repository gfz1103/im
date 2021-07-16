package com.buit.cis.ims.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * 在院病人收费项目汇总出参
 * zhouhaisheng
 */
@ApiModel(value = "在院病人收费项目汇总出参")
public class InHospitalPatientReportAllResp {
    @ApiModelProperty(value = "list信息")
    private List<InHospitalPatientReportResp> inHospitalPatientReportRespList;
    @ApiModelProperty(value = "汇总信息")
    private List<InHospitalPatientReportHjResp> inHospitalPatientReportHjRespList;

    public List<InHospitalPatientReportResp> getInHospitalPatientReportRespList() {
        return inHospitalPatientReportRespList;
    }

    public void setInHospitalPatientReportRespList(List<InHospitalPatientReportResp> inHospitalPatientReportRespList) {
        this.inHospitalPatientReportRespList = inHospitalPatientReportRespList;
    }

    public List<InHospitalPatientReportHjResp> getInHospitalPatientReportHjRespList() {
        return inHospitalPatientReportHjRespList;
    }

    public void setInHospitalPatientReportHjRespList(List<InHospitalPatientReportHjResp> inHospitalPatientReportHjRespList) {
        this.inHospitalPatientReportHjRespList = inHospitalPatientReportHjRespList;
    }
}
