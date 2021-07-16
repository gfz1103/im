package com.buit.cis.nurse.model;

import java.sql.Timestamp;
import com.buit.commons.PageQuery;

/**
 * 类名称：NisTypc<br> 
 * 类描述：
 * @author GONGFANGZHOU 
 * @ApiModel(value="")
 */
public class NisTypc  extends  PageQuery{
	//@ApiModelProperty(value="主键")
    /** 主键 */
    private Long id;
	//@ApiModelProperty(value="退药序号，nis_tymx表主键")
    /** 退药序号，nis_tymx表主键 */
    private Integer tyxh;
	//@ApiModelProperty(value="提交序号，nis_tj02表主键")
    /** 提交序号，nis_tj02表主键 */
    private Integer tjxh;
	//@ApiModelProperty(value="执行时间")
    /** 执行时间 */
    private Timestamp zxsj;

    /** 设置:主键  */
    public void setId(Long value) {
        this.id = value;
    }
    /** 获取:主键 */
    public Long getId() {
        return id;
    }

    /** 设置:退药序号，nis_tymx表主键  */
    public void setTyxh(Integer value) {
        this.tyxh = value;
    }
    /** 获取:退药序号，nis_tymx表主键 */
    public Integer getTyxh() {
        return tyxh;
    }

    /** 设置:提交序号，nis_tj02表主键  */
    public void setTjxh(Integer value) {
        this.tjxh = value;
    }
    /** 获取:提交序号，nis_tj02表主键 */
    public Integer getTjxh() {
        return tjxh;
    }

    /** 设置:执行时间  */
    public void setZxsj(Timestamp value) {
        this.zxsj = value;
    }
    /** 获取:执行时间 */
    public Timestamp getZxsj() {
        return zxsj;
    }


}