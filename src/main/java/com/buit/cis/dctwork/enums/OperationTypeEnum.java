package com.buit.cis.dctwork.enums;


/**
 * 数据库操作方式
 * @ClassName: OperationTypeEnum
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author 龚方舟
 * @date 2020年6月7日 下午12:29:36
 *
 */
public enum OperationTypeEnum {
    /**
     * create:新增,update:修改更新,remove:删除,expired:过期,deal:处理
     */
	create("create"),
	update("update"),
	remove("remove"),
	expired("expired"),
	deal("deal");

	private String code;

	OperationTypeEnum(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    
}
