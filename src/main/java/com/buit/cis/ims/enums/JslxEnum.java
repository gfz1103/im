package com.buit.cis.ims.enums;

/**
 * 结算操作类型
 *
 * @Author jiangwei
 * @Date 2021-05-03
 */
public enum JslxEnum {
    MIDDLE(1, "中途结算"),
    OUT(5, "出院结算"),
    Cancel(10, "发票作废");

    private int value;
    private String text;

    JslxEnum(int value, String text) {
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
