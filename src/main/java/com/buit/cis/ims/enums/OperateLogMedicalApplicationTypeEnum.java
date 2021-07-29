package com.buit.cis.ims.enums;

/**
 * @author jiangwei
 * @Description 患者住院操作日志医技申请对象枚举
 * @Date 2021-07-26
 * 关联im_operate_log.type
 */
public enum OperateLogMedicalApplicationTypeEnum {
    CONSULTATION(0, "会诊申请"),
    SURGERY(1, "手术申请"),
    RESERVE_BLOOD(2, "备血申请"),
    EXAMINATION(3, "检查申请"),
    ASSAY(4, "检验申请"),
    ANTIBACTERIAL_AGENTS(5, "抗菌药物使用申请");

    private int value;
    private String text;

    OperateLogMedicalApplicationTypeEnum(int value, String text) {
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
