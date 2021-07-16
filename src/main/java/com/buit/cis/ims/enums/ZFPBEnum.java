package com.buit.cis.ims.enums;


/**
 * @author All
 */
public enum ZFPBEnum {
    //作废判别 | 0 未作废 1作废
    code_0(0),
    code_1(1);

    private Integer code;

    ZFPBEnum(Integer code) {
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}
