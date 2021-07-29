package com.buit.cis.ims.enums;

/**
 * @author jiangwei
 * @Description 患者住院操作日志医技申请对象枚举
 * @Date 2021-07-26
 * 关联im_operate_log.type
 */
public enum OperateLogAdviceStatusEnum {
    EDIT(0, "新开或编辑"),
    SUBMIT(1, "提交"),
    REVIEW(2, "复核"),
    REVIEW_CANCEL(3, "取消复核"),
    EXECUTE(4, "执行"),
    EXECUTE_CANCEL(5, "取消执行"),
    ADVICE_STOP(6, "停嘱"),
    STOP_REVIEW(7, "停嘱复核"),
    STOP_REVIEW_CANCEL(8, "停嘱取消复核"),
    DISABLE(9, "作废/退回"),
    DELETE(10, "删除");

    private int value;
    private String text;

    OperateLogAdviceStatusEnum(int value, String text) {
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
