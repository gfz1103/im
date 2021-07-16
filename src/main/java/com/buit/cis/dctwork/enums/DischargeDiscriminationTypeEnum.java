package com.buit.cis.dctwork.enums;


/**
 * @ClassName: DischargeDiscriminationTypeEnum
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author 龚方舟
 * @date 2020年6月7日 下午12:29:12
 *
 */
public enum DischargeDiscriminationTypeEnum {
	/**
	 * 出院判别 | 0：在院病人
	 * 1：出院证明
	 * 2：预结出院
	 * 8：正常出院
	 * 9：终结出院
	 * 99 注销出院
	 */
	ZERO(0),
	ONE(1),
	TWO(2),
	EIGHT(8),
	NINE(9),
	NINETY_NINE(99);

	private Integer code;

	DischargeDiscriminationTypeEnum(Integer code) {
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    
}
