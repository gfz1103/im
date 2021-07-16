package com.buit.cis.dctwork.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

/**
 * 类名称：CisHzyz<br>
 * 类描述：住院_病区医嘱<br>
 * @author GONGFANGZHOU
 */
@ApiModel(value="住院_病区医嘱_DeteleReq")
public class CisHzyzDeteleReq {

    @ApiModelProperty(value="记录序号")
    @NotNull(message = "记录序号不能为空")
    private Integer jlxh;

    @ApiModelProperty(value="医嘱类型(0:检查1:检验2:备血3:手术4:会诊5:住院处方6:理疗)")
    private Integer yzlx;

    @ApiModelProperty(value="组套标志")
    private Integer ztbz;

    @ApiModelProperty(value="申请id")
    private Integer sqid;

    @ApiModelProperty(value="抗菌药物")
    private Integer kjywsp;

    public Integer getJlxh() {
        return jlxh;
    }

    public void setJlxh(Integer jlxh) {
        this.jlxh = jlxh;
    }

    public Integer getYzlx() {
        return yzlx;
    }

    public void setYzlx(Integer yzlx) {
        this.yzlx = yzlx;
    }

    public Integer getZtbz() {
        return ztbz;
    }

    public void setZtbz(Integer ztbz) {
        this.ztbz = ztbz;
    }

    public Integer getSqid() {
        return sqid;
    }

    public void setSqid(Integer sqid) {
        this.sqid = sqid;
    }

    public Integer getKjywsp() {
        return kjywsp;
    }

    public void setKjywsp(Integer kjywsp) {
        this.kjywsp = kjywsp;
    }
}
