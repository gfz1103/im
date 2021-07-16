package com.buit.cis.ims.enums;

/**
 * 入院诊断类别枚举
 *
 * @Author jiangwei
 * @Date 2021-03-24
 */
public enum ImRyzdZdlbEnum {
    OUTPATIENT_DIAGNOSIS(1, "门诊诊断"),
    ADMITTING_DIAGNOSIS(2, "入院诊断"),
    DISCHARGE_MAIN_DIAGNOSIS(3, "出院主诊断"),
    DISCHARGE_SECONDARY_DIAGNOSIS(4, "出院次诊断"),
    INFECTION(5, "院内感染");

    private int value;
    private String text;

    ImRyzdZdlbEnum(int value, String text) {
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
