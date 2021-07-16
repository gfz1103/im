package com.buit.cis.dctwork.enums;


/**
* 护理级别
* @ClassName: CareLevelEnum
* @Description: TODO(这里用一句话描述这个类的作用)
* @author 龚方舟
* @date 2020年6月7日 上午9:36:50
*
 */
public enum CareLevelEnum {
    /**
     * 一级护理,二级护理,三级护理
     */
	levelZero("特级护理"),
	levelOne("一级护理"),
	levelTwo("二级护理"),
	levelThree("三级护理");

	private String code;

	CareLevelEnum(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    
}
