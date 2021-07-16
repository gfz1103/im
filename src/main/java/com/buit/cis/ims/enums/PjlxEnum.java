package com.buit.cis.ims.enums;

/**
 * 票据类型枚举
 * @author zhouhaisheng
 */
public enum PjlxEnum {
    //票据类型 | 1.发票        2.收据
    code_1(1),
    code_2(2);

    private Integer code;

    PjlxEnum(Integer code) {
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}
