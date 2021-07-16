package com.buit.cis.ims.enums;

/**
 * 床位性别枚举
 */
public enum CwxbEnum {
    code_1("1", "男性"),
    code_2("2", "女性"),
    code_3("3", "不限"),
    ;

    private String code;
    private String value;

    CwxbEnum(String code, String value) {
        this.code = code;
        this.value = value;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public static String getCwxb(String code) {
        CwxbEnum[] values = CwxbEnum.values();
        for (CwxbEnum x : values) {
            if (x.getCode().equals(code)) {
                return x.getValue();
            }
        }
        return null;
    }
}
