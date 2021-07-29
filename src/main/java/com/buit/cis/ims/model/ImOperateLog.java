package com.buit.cis.ims.model;

import com.buit.commons.PageQuery;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.sql.Timestamp;

/**
 * @author jiangwei
 * @Description 患者住院操作日志
 * @Date 2021-07-26
 */
@ApiModel(value = "患者住院操作日志")
public class ImOperateLog extends PageQuery {
    @ApiModelProperty(value = "记录序号")
    private Integer jlxh;
    @ApiModelProperty(value = "操作业务类型 | 0：患者状态变更，1：医嘱状态变更，2：医技申请状态变更，3：单据打印状态变更")
    private Integer category;
    @ApiModelProperty(value = "操作对象类别： 医技申请 | 0：会诊申请，1：手术申请，2：备血申请，3：检查申请，4：检验申请，5：抗菌药物使用申请 单据打印 | 0：口服卡，1：注射卡，2：静滴卡，3：营养卡，4：治疗单，5：汇总治疗单，6：输液巡视卡，7：给药记录单，8：饮食单，9：其他，10：检验申请单，11：检查申请单，12：检查指引单，13：检验指引单")
    private Integer type;
    @ApiModelProperty(value = "操作类型： 患者信息变更 | 0：入院登记，1：床位分配，2：转床，3：性质转换，4：出院通知，5：结算出院，6：转科 医嘱信息变更 | 0：新开，1：提交，2：复核，3：取消复核，4：执行，5：取消执行，6：停嘱，7：停嘱复核，8：停嘱取消复核，9：作废（医生）/退回（护士退回到医生），10：删除 医技申请 | 0：新开/草稿/修改，1：提交，2：确认/复核，3：取消确认/复核，4：删除 单据打印 | 0：打印/重打，1：取消打印")
    private Integer status;
    @ApiModelProperty(value = "住院号")
    private Integer zyh;
    @ApiModelProperty(value = "关联数据表名")
    private String dataTable;
    @ApiModelProperty(value = "关联数据ID（逗号分隔）")
    private String dataId;
    @ApiModelProperty(value = "操作人")
    private Integer operateUser;
    @ApiModelProperty(value = "操作时间")
    private Timestamp operateTime;


    public void setJlxh(Integer value) {
        this.jlxh = value;
    }

    public Integer getJlxh() {
        return jlxh;
    }


    public void setCategory(Integer value) {
        this.category = value;
    }

    public Integer getCategory() {
        return category;
    }


    public void setType(Integer value) {
        this.type = value;
    }

    public Integer getType() {
        return type;
    }


    public void setStatus(Integer value) {
        this.status = value;
    }

    public Integer getStatus() {
        return status;
    }


    public void setZyh(Integer value) {
        this.zyh = value;
    }

    public Integer getZyh() {
        return zyh;
    }


    public void setDataTable(String value) {
        this.dataTable = value;
    }

    public String getDataTable() {
        return dataTable;
    }


    public void setDataId(String value) {
        this.dataId = value;
    }

    public String getDataId() {
        return dataId;
    }


    public void setOperateUser(Integer value) {
        this.operateUser = value;
    }

    public Integer getOperateUser() {
        return operateUser;
    }


    public void setOperateTime(Timestamp value) {
        this.operateTime = value;
    }

    public Timestamp getOperateTime() {
        return operateTime;
    }


}