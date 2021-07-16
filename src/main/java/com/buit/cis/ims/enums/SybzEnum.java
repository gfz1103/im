package com.buit.cis.ims.enums;

/**
 * 住院员工票据使用标志
 */
public enum SybzEnum {
       //使用标志 | 0.可用        1.用完
        code_0(0),
        code_1(1);

private Integer code;

        SybzEnum(Integer code) {
        this.code = code;
        }

public Integer getCode() {
        return code;
        }

public void setCode(Integer code) {
        this.code = code;
        }
}
