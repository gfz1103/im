package com.buit.cis.ims.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

/**
 * 在院病人费用统计出参
 * zhouhaisheng
 */
@ApiModel(value = "在院病人费用统计出参")
public class InHospitalPatientReportResp {
    @ApiModelProperty(value = "病人姓名")
    private String brxm;
    @ApiModelProperty(value = "住院号码")
    private String zyhm;
    @ApiModelProperty(value = "病人床号")
    private String brch;
    @ApiModelProperty(value = "入院日期")
    private Timestamp ryrq;
    @ApiModelProperty(value = "合计费用")
    private BigDecimal hjfy;
    @ApiModelProperty(value = "药占比")
    private BigDecimal yzb;
    @ApiModelProperty(value = "药品费用合计")
    private BigDecimal yphj;
    @ApiModelProperty(value = "住院天数")
    private String zyts;
    @ApiModelProperty(value = "住院医师")
    private String zyys;
    @ApiModelProperty(value = "收费项目集合")
    private List<InHospitalPatientReportSfxmResp> inHospitalPatientReportSfxmRespList;

    public String getBrxm() {
        return brxm;
    }

    public void setBrxm(String brxm) {
        this.brxm = brxm;
    }

    public String getZyhm() {
        return zyhm;
    }

    public void setZyhm(String zyhm) {
        this.zyhm = zyhm;
    }

    public String getBrch() {
        return brch;
    }

    public void setBrch(String brch) {
        this.brch = brch;
    }

    public Timestamp getRyrq() {
        return ryrq;
    }

    public void setRyrq(Timestamp ryrq) {
        this.ryrq = ryrq;
    }

    public BigDecimal getHjfy() {
        return hjfy;
    }

    public void setHjfy(BigDecimal hjfy) {
        this.hjfy = hjfy;
    }

    public BigDecimal getYzb() {
        return yzb;
    }

    public void setYzb(BigDecimal yzb) {
        this.yzb = yzb;
    }

    public String getZyts() {
        return zyts;
    }

    public void setZyts(String zyts) {
        this.zyts = zyts;
    }

    public String getZyys() {
        return zyys;
    }

    public void setZyys(String zyys) {
        this.zyys = zyys;
    }

    public List<InHospitalPatientReportSfxmResp> getInHospitalPatientReportSfxmRespList() {
        return inHospitalPatientReportSfxmRespList;
    }

    public void setInHospitalPatientReportSfxmRespList(List<InHospitalPatientReportSfxmResp> inHospitalPatientReportSfxmRespList) {
        this.inHospitalPatientReportSfxmRespList = inHospitalPatientReportSfxmRespList;
    }

    public BigDecimal getYphj() {
        return yphj;
    }

    public void setYphj(BigDecimal yphj) {
        this.yphj = yphj;
    }
}
