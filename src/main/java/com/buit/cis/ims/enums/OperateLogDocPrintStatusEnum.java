package com.buit.cis.ims.enums;

/**
 * @author jiangwei
 * @Description 患者住院操作日志患者操作状态枚举
 * @Date 2021-07-27
 * 关联im_operate_log.status
 */
public enum OperateLogDocPrintStatusEnum {
    FIRST_PRINT(0, "打印"),
    PRINT_CANCEL(1, "取消打印"),
    PRINT_AGAIN(2, "重打");

    private int value;
    private String text;

    OperateLogDocPrintStatusEnum(int value, String text) {
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
