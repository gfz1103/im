package com.buit.cis.ims.enums;

/**
 * @Description 预缴金缴款类型枚举
 * @Author jiangwei
 * @Date 2021/1/8
 */
public enum JklxEnum {
    RECHARGE(0, "正常预缴"),
    Deduction(1, "结算抵扣"),
    SETTLEMENT_CANCEL(2, "取消结算"),
    REFUND_UNDO(3, "注销找退"),
    REFUND_DISCHARGE(4, "出院找退");

    private int value;
    private String text;

    JklxEnum(int value, String text) {
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
