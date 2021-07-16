package com.buit.cis.nurse.enums;

/**
 * 费用记账 作废判别
 * im_fee_fymx.zfpb
 */
public enum FymxZfpbEnum {

    NORMAL(0, "正常记账"),
    CANCELED(1, "已作废"),
    REFUND(2, "作废抵账");

    private int value;
    private String text;

    FymxZfpbEnum(int value, String text) {
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
