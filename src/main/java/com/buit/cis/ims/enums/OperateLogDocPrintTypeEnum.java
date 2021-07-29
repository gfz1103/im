package com.buit.cis.ims.enums;

/**
 * @author jiangwei
 * @Description 患者住院操作日志医技申请对象枚举
 * @Date 2021-07-26
 * 关联im_operate_log.type
 */
public enum OperateLogDocPrintTypeEnum {
    ORALLY_CARD(0, "口服卡"),
    INJECTION_CARD(1, "注射卡"),
    INTRAVENOUS_DRIP_CARD(2, "静滴卡"),
    NOURISHMENT_CARD(3, "营养卡"),
    TREATMENT_PAPER(4, "治疗单"),
    TREATMENT_SUMMARY_PAPER(5, "汇总治疗单"),
    TRANSFUSION_PATROL_CARD(6, "输液巡视卡"),
    ADMINISTRATION_RECORD_PAPER(7, "给药记录单"),
    DIETARY_PAPER(8, "饮食单"),
    OTHER(9, "其他"),
    EXAMINATION_ASSAY_PAPER(10, "检查/检验申请单");

    private int value;
    private String text;

    OperateLogDocPrintTypeEnum(int value, String text) {
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
