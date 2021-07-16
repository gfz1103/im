package com.buit.cis.ims.enums;


/**
 * @author 老花生
 */
public enum ShybFileNameEnum {
    //导入文件名称
    YPPSQY("YPPSQY"),
    YPJGGZ("YPJGGZ"),
    YPJCXX("YPJCXX"),
    YPCGGZ("YPCGGZ"),
    YBKBXGZ("YBKBXGZ"),
    YBBLGZ("YBBLGZ");

    private String code;

    ShybFileNameEnum(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
