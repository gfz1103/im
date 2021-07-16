package com.buit.cis.ims.enums;


/**
 * @author All
 */
public enum RKPBEnum {
    //实物验收 0：实物未验收入库 1：已实物验收入库
    code_0(0),
    code_1(1);

    private Integer code;

    RKPBEnum(Integer code) {
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}
