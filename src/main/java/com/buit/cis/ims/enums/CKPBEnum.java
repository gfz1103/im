package com.buit.cis.ims.enums;


/**
 * @author All
 */
public enum CKPBEnum {
    //实物验收 0：未出库 1：已出库
    code_0(0),
    code_1(1);

    private Integer code;

    CKPBEnum(Integer code) {
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}
