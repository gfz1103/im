package com.buit.cis.ims.enums;

/**
 * @Description
 * @Author yueyu
 * @Date 2020/5/15 10:44
 */
public enum CflxEnum {
    xy(1,"西药处方"),
    zy(2,"中药处方"),
    cy(3,"草药处方"),
    ;

    private Integer intValue;
    private String strValue;

    CflxEnum(Integer intValue, String strValue) {
        this.intValue = intValue;
        this.strValue = strValue;
    }

    public Integer getIntValue() {
        return intValue;
    }

    public String getStrValue() {
        return strValue;
    }
}
