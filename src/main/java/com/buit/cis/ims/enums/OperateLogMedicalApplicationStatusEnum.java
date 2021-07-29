package com.buit.cis.ims.enums;

/**
 * @author jiangwei
 * @Description 患者住院操作日志医技操作状态枚举
 * @Date 2021-07-27
 * 关联im_operate_log.status
 */
public enum OperateLogMedicalApplicationStatusEnum {
    EDIT(0, "新开或编辑"),
    APPROVE_PASS(1, "审批通过"),
    APPROVE_FAIL(2, "审批不通过"),
    APPOINTMENT(3, "预约"),
    EXECUTE(4, "执行"),
    EXECUTE_CANCEL(5, "取消执行"),
    DELETE(6, "删除");

    private int value;
    private String text;

    OperateLogMedicalApplicationStatusEnum(int value, String text) {
        this.value = value;
        this.text = text;
    }

    //参数类型不能改为int 否则Integer类型入参会调用enum类equals(Object o)方法，而非本方法
    public boolean equals(Integer value) {
        return this.value == value;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
