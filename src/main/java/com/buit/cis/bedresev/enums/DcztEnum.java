package com.buit.cis.bedresev.enums;

/**
 * @Description 床位预约待床状态枚举
 * @Author jiangwei
 * @Date 2021/04/07
 * ImBedResev.DCZT
 */
public enum DcztEnum {
    NOT_WAITED(0, "未待床"),
    WAITED(1, "已待床"),
    OVERDUE(2, "已逾期"),
    IN_HOSPITAL(3, "已入院");

    private int value;
    private String text;

    DcztEnum(int value, String text) {
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
