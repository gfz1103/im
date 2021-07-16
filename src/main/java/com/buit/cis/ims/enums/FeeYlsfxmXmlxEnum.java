package com.buit.cis.ims.enums;

/**
 * @Description 医疗收费项目 项目类型枚举
 * @Author jiangwei
 * @Date 2021-06-08
 * @Table fee_ylsfxm.xmlx
 */
public enum FeeYlsfxmXmlxEnum {
    ASSAY(4, "检验"),
    EXAMINE(5, "检查"),
    SURGERY(6, "手术"),
    TREATMENT(7, "治疗"),
    NURSE(8, "护理"),
    FOOD(9, "饮食"),
    MATERIALS(10, "卫材"),
    OTHER(99, "其他");


    private int value;
    private String text;

    FeeYlsfxmXmlxEnum(int value, String text) {
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
