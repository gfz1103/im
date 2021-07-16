package com.buit.cis.ims.enums;

/**
 * 住院 病区信息维护 发药药房设置 注销判别枚举
 */
public enum TjpbEnum {
    //使用标志 | 0.未执行调价       1.已执行调价
    code_0(0),
    code_1(1);

    private Integer code;

    TjpbEnum(Integer code) {
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}
