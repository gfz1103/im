package com.buit.cis.ims.enums;


/**
 * @author All
 */
public enum StopFlagEnum {
    //停用标识 0 停用 1启用
    code_0("0"),
    code_1("1");

    private String code;

    StopFlagEnum(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
