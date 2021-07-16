package com.buit.cis.ims.enums;

/**
 * @Description 付款方式枚举
 * @Author jiangwei
 * @Date 2021/1/19
 */
public enum FkfsEnum {
    CASH(11, "现金"),
    CHECK(12, "支票"),
    WECHAT(13, "微信"),
    ALIPAY(14, "支付宝"),
    POS(15, "POS机"),
    REMITTANCE(16,"汇款"),
    DISCOUNT(17, "减免"),
    RECHARGE_CARD(19, "充值卡"),
    CURRENCY_ERROR(20, "货币误差"),
    QRCODE(21, "二维码");


    private int value;
    private String text;

    FkfsEnum(int value, String text) {
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
