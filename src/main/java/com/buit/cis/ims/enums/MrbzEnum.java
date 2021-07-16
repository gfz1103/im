package com.buit.cis.ims.enums;


/**
 * @author All
 */
public enum MrbzEnum {
    //设置:默认标志 | 1.默认科室   0.非默认科室
    code_0(0),
    code_1(1);

    private Integer code;

    MrbzEnum(Integer code) {
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}
