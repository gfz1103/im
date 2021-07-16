package com.buit.cis.ims.enums;

/**
 * @Description 出院判别枚举
 * @Author jiangwei
 * @Date 2020/12/25
 */
public enum YwlxEnum {
    OUTPATIENT(1, "门诊"),
    HOSPITALIZATION(2, "住院");

    private int value;
    private String text;

    YwlxEnum(int value, String text) {
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
