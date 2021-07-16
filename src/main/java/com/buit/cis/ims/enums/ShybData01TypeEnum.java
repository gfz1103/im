package com.buit.cis.ims.enums;


/**
 * @author 老花生
 */
public enum ShybData01TypeEnum {
    //医保字典ID 1：区县代码表、2：科室编码表、3：大病项目代码表、4：门诊大病登记疾病诊断分类、5：医保门诊项目类别、6：医保住院项目类别
    code_1(1),
    code_2(2),
    code_3(3),
    code_4(4),
    code_5(5),
    code_6(6);

    private Integer code;

    ShybData01TypeEnum(Integer code) {
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}
