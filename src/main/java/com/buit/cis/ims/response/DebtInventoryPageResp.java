package com.buit.cis.ims.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;
import java.util.List;

/**
 *缴款管理-欠费清单(出参)
 * zhouhaisheng
 */
@ApiModel(value = "缴款管理-欠费清单(出参)")
public class DebtInventoryPageResp {
    @ApiModelProperty(value = "页面list数据")
    private List<DebtInventoryResp> debtInventoryRespList;
    @ApiModelProperty(value = "总人数")
    private Integer allCount;
    @ApiModelProperty(value = "欠款人数")
    private Integer debtCount;
    @ApiModelProperty(value = "自负总金额")
    private BigDecimal zfjeSum;
    @ApiModelProperty(value = "缴款总金额")
    private BigDecimal jkjeSum;
    @ApiModelProperty(value = "欠款总金额")
    private BigDecimal qkjeSum;


    public List<DebtInventoryResp> getDebtInventoryRespList() {
        return debtInventoryRespList;
    }

    public void setDebtInventoryRespList(List<DebtInventoryResp> debtInventoryRespList) {
        this.debtInventoryRespList = debtInventoryRespList;
    }

    public Integer getAllCount() {
        return allCount;
    }

    public void setAllCount(Integer allCount) {
        this.allCount = allCount;
    }

    public Integer getDebtCount() {
        return debtCount;
    }

    public void setDebtCount(Integer debtCount) {
        this.debtCount = debtCount;
    }

    public BigDecimal getZfjeSum() {
        return zfjeSum;
    }

    public void setZfjeSum(BigDecimal zfjeSum) {
        this.zfjeSum = zfjeSum;
    }

    public BigDecimal getJkjeSum() {
        return jkjeSum;
    }

    public void setJkjeSum(BigDecimal jkjeSum) {
        this.jkjeSum = jkjeSum;
    }

    public BigDecimal getQkjeSum() {
        return qkjeSum;
    }

    public void setQkjeSum(BigDecimal qkjeSum) {
        this.qkjeSum = qkjeSum;
    }


}
