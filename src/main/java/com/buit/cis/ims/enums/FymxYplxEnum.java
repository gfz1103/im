package com.buit.cis.ims.enums;

/**
 * 住院费用明细/手术记账/退费项目 药品类型枚举
 * im_fee_fymx.YPLX/ss_ssjzdmx.YPLX/ss_zytfsqmx.YPLX
 */
public enum FymxYplxEnum {
    NOT_MEDICINE(0, "项目"),
    WESTERN_MEDICINE(1, "西药"),
    CHINESE_PATENT_MEDICINE(2, "中成药"),
    CHINESE_HERBAL_MEDICINE(3, "中草药");

    private int value;
    private String text;

    FymxYplxEnum(int value, String text) {
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
