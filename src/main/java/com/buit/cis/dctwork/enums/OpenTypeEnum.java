package com.buit.cis.dctwork.enums;


/**
 * 方式类别
 * @ClassName: OpenTypeEnum
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author 龚方舟
 * @date 2020年6月7日 下午12:29:24
 *
 */
public enum OpenTypeEnum {
    /**
     * doctor:医生工作站,nurse:护士工作站
     */
	doctor("doctor"),
	nurse("nurse");

	private String code;

	OpenTypeEnum(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    
}
