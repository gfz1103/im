package com.buit.cis.ims.enums;


/**
 * @author All
 */
public enum YSPBEnum {
    //实物验收 0：未验收 1：已验收
    code_0(0),
    code_1(1);

    private Integer code;

    YSPBEnum(Integer code) {
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}
