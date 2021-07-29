package com.buit.cis.ims.enums;

/**
 * @author jiangwei
 * @Description 患者住院操作日志业务类型枚举
 * @Date 2021-07-26
 * 关联im_operate_log.category
 */
public enum OperateLogCategoryEnum {
    PATIENT(0, "患者状态变更"),
    ADVICE(1, "医嘱状态变更"),
    MEDICAL_APPLICATION(2, "医技申请状态变更"),
    DOCUMENT_PRINT(3, "单据打印状态变更");

    private int value;
    private String text;

    OperateLogCategoryEnum(int value, String text) {
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
