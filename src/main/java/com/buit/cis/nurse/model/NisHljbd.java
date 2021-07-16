package com.buit.cis.nurse.model;

import java.sql.Timestamp;

import com.buit.commons.PageQuery;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 类名称：NisHljbd<br> 
 * 类描述：护理交班单主表
 * @author GONGFANGZHOU 
 */
@ApiModel(value="护理交班单主表")
public class NisHljbd  extends  PageQuery{
	@ApiModelProperty(value="记录序号(主键id)")
    /** 记录序号(主键id) */
    private Integer jlxh;
	@ApiModelProperty(value="交班时间")
    /** 交班时间 */
    private Timestamp jbsj;
	@ApiModelProperty(value="班次类型(系统标识字典:JBD000003)")
    /** 班次类型(系统标识字典:JBD000003) */
    private Integer bclx;
	@ApiModelProperty(value="护理组号")
    /** 护理组号 */
    private Integer hlzh;
	@ApiModelProperty(value="病区代码")
    /** 病区代码 */
    private Integer bqdm;
	@ApiModelProperty(value="机构id")
    /** 机构id */
    private Integer jgid;

    /** 设置:记录序号(主键id)  */
    public void setJlxh(Integer value) {
        this.jlxh = value;
    }
    /** 获取:记录序号(主键id) */
    public Integer getJlxh() {
        return jlxh;
    }

    /** 设置:交班时间  */
    public void setJbsj(Timestamp value) {
        this.jbsj = value;
    }
    /** 获取:交班时间 */
    public Timestamp getJbsj() {
        return jbsj;
    }

    /** 设置:班次类型(系统标识字典:JBD000003)  */
    public void setBclx(Integer value) {
        this.bclx = value;
    }
    /** 获取:班次类型(系统标识字典:JBD000003) */
    public Integer getBclx() {
        return bclx;
    }

    /** 设置:护理组号  */
    public void setHlzh(Integer value) {
        this.hlzh = value;
    }
    /** 获取:护理组号 */
    public Integer getHlzh() {
        return hlzh;
    }

    /** 设置:病区代码  */
    public void setBqdm(Integer value) {
        this.bqdm = value;
    }
    /** 获取:病区代码 */
    public Integer getBqdm() {
        return bqdm;
    }

    /** 设置:机构id  */
    public void setJgid(Integer value) {
        this.jgid = value;
    }
    /** 获取:机构id */
    public Integer getJgid() {
        return jgid;
    }


}