package com.buit.cis.ims.enums;

/**
 * 
 * @author
 *
 */
public enum SysDataCodeEnum {
	/**
	 * 年
	 */
	YEAR("1");

	private String code;

	SysDataCodeEnum(String code) {
		this.code = code;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
}
