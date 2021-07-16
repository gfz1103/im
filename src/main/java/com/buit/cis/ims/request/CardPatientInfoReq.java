package com.buit.cis.ims.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 类名称：查询住院病人帐卡信息入参<br>
 * 类描述：查询住院病人帐卡信息入参<br>
 *
 * @author ZHOUHAISHENG
 */
@ApiModel(value = "查询住院病人帐卡信息入参")
public class CardPatientInfoReq {
    @ApiModelProperty(value = "住院号 | 该住院号为内码")
    private Integer zyh;
    @JsonIgnore
    @ApiModelProperty(value = "机构代码", hidden = true)
    private Integer jgid;
    @ApiModelProperty(value = "结算类型 1:中途结算 5:出院结算 10:发票作废(取消结算)")
    private String jslx;
    @ApiModelProperty(value = "费用开始日期")
    private String startDate;
    @ApiModelProperty(value = "费用结束日期")
    private String endDate;
    @ApiModelProperty(value = "结算次数")
    private Integer jscs = 0;
    @ApiModelProperty(value = "住院号码")
    private String zyhm;
    @ApiModelProperty(value = "发票号码")
    private String fphm;

    public Integer getZyh() {
        return zyh;
    }

    public void setZyh(Integer zyh) {
        this.zyh = zyh;
    }

    public String getJslx() {
        return jslx;
    }

    public void setJslx(String jslx) {
        this.jslx = jslx;
    }

    public Integer getJgid() {
        return jgid;
    }

    public void setJgid(Integer jgid) {
        this.jgid = jgid;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public Integer getJscs() {
        return jscs;
    }

    public void setJscs(Integer jscs) {
        this.jscs = jscs;
    }

    public String getZyhm() {
        return zyhm;
    }

    public void setZyhm(String zyhm) {
        this.zyhm = zyhm;
    }

    public String getFphm() {
        return fphm;
    }

    public void setFphm(String fphm) {
        this.fphm = fphm;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }
}
