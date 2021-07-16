package com.buit.cis.ims.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

/**
 * 出院费用统计报表出参
 * zhouhaisheng
 */
@ApiModel(value = "出院病人费用统计报表出参单条记录")
public class DischargedPatientReportResp {

    @ApiModelProperty(value = "病人姓名")
    private String brxm;
    @ApiModelProperty(value = "住院号码")
    private String zyhm;
    @ApiModelProperty(value = "病人床号")
    private String brch;
    @ApiModelProperty(value = "入院日期")
    private Timestamp ryrq;
    @ApiModelProperty(value = "出院日期")
    private Timestamp cyrq;
    @ApiModelProperty(value = "合计费用")
    private BigDecimal hjfy;

    @ApiModelProperty(value = "住院天数")
    private String zyts;
    @ApiModelProperty(value = "住院医师")
    private String zyys;
    @ApiModelProperty(value = "病人性质代码")
    private Integer brxz;
    @ApiModelProperty(value = "病人性质")
    private String brxzStr;
    @ApiModelProperty(value = "费用合计")
    private BigDecimal fyhj;
    @ApiModelProperty(value = "自负合计")
    private BigDecimal zfhj;
    @ApiModelProperty(value = "医保合计")
    private BigDecimal ybhj;
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

    public Timestamp getCyrq() {
        return cyrq;
    }

    public void setCyrq(Timestamp cyrq) {
        this.cyrq = cyrq;
    }

    public BigDecimal getHjfy() {
        return hjfy;
    }

    public void setHjfy(BigDecimal hjfy) {
        this.hjfy = hjfy;
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

    public Integer getBrxz() {
        return brxz;
    }

    public void setBrxz(Integer brxz) {
        this.brxz = brxz;
    }

    public String getBrxzStr() {
        return brxzStr;
    }

    public void setBrxzStr(String brxzStr) {
        this.brxzStr = brxzStr;
    }

    public BigDecimal getFyhj() {
        return fyhj;
    }

    public void setFyhj(BigDecimal fyhj) {
        this.fyhj = fyhj;
    }

    public BigDecimal getZfhj() {
        return zfhj;
    }

    public void setZfhj(BigDecimal zfhj) {
        this.zfhj = zfhj;
    }

    public BigDecimal getYbhj() {
        return ybhj;
    }

    public void setYbhj(BigDecimal ybhj) {
        this.ybhj = ybhj;
    }

    public List<InHospitalPatientReportSfxmResp> getInHospitalPatientReportSfxmRespList() {
        return inHospitalPatientReportSfxmRespList;
    }

    public void setInHospitalPatientReportSfxmRespList(List<InHospitalPatientReportSfxmResp> inHospitalPatientReportSfxmRespList) {
        this.inHospitalPatientReportSfxmRespList = inHospitalPatientReportSfxmRespList;
    }

}
