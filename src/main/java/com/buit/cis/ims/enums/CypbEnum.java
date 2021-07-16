package com.buit.cis.ims.enums;

/**
 * @Description 出院判别枚举
 * @Author jiangwei
 * @Date 2020/12/25
 */
public enum CypbEnum {
    IN_HOSPITAL(0, "在院病人"),
    CER_DISCHARGE(1, "出院证明"),
    PRE_DISCHARGE(2, "预结出院"),
    NORMAL_DISCHARGE(8, "正常出院"),
    FINISH_DISCHARGE(9, "终结出院"),
    CANCEL_DISCHARGE(99, "注销出院");

    private int value;
    private String text;

    CypbEnum(int value, String text) {
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
