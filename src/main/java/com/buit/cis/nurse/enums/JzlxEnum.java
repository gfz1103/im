package com.buit.cis.nurse.enums;

/**
 * 费用明细 记账类型
 * im_fee_fymx.xmlx
 */
public enum JzlxEnum {

    INPATIENT_AREA(1, "病区系统记帐"),
    PHARMACY(2, "药房系统记帐"),
    MEDICAL_TECHNOLOGY(3, "医技系统记帐"),
    IN_HOSPITAL(4, "住院系统记帐(补记帐)"),
    SURGERY(5, "手术系统记帐"),
    ANESTHESIA(6, "麻醉系统记帐"),
    RECOVERY(7, "康复系统记账"),
    AUTO_INCREASE(9, "自动累加费用");

    private int value;
    private String text;

    JzlxEnum(int value, String text) {
        this.value = value;
        this.text = text;
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

    //参数类型不能改为int 否则Integer类型入参会调用enum类equals(Object o)方法，而非本方法
    public boolean equals(Integer value) {
        return this.value == value;
    }
}
