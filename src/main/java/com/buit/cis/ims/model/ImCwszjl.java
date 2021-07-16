package com.buit.cis.ims.model;

import java.sql.Timestamp;
import com.buit.commons.PageQuery;

/**
 * 类名称：ImCwszjl<br> 
 * 类描述：床位使用记录表
 * @author GONGFANGZHOU 
 * @ApiModel(value="床位使用记录表")
 */
public class ImCwszjl  extends  PageQuery{
	//@ApiModelProperty(value="记录序号(主键)")
    /** 记录序号(主键) */
    private Integer jlxh;
	//@ApiModelProperty(value="记录日期")
    /** 记录日期 */
    private Timestamp jlrq;
	//@ApiModelProperty(value="病区代码")
    /** 病区代码 */
    private Integer bqdm;
	//@ApiModelProperty(value="床位使用数")
    /** 床位使用数 */
    private Integer cwsys;
	//@ApiModelProperty(value="床位未使用数")
    /** 床位未使用数 */
    private Integer cwwsy;
	//@ApiModelProperty(value="机构id")
    /** 机构id */
    private Integer jgid;

    /** 设置:记录序号(主键)  */
    public void setJlxh(Integer value) {
        this.jlxh = value;
    }
    /** 获取:记录序号(主键) */
    public Integer getJlxh() {
        return jlxh;
    }

    /** 设置:记录日期  */
    public void setJlrq(Timestamp value) {
        this.jlrq = value;
    }
    /** 获取:记录日期 */
    public Timestamp getJlrq() {
        return jlrq;
    }

    /** 设置:病区代码  */
    public void setBqdm(Integer value) {
        this.bqdm = value;
    }
    /** 获取:病区代码 */
    public Integer getBqdm() {
        return bqdm;
    }

    /** 设置:床位使用数  */
    public void setCwsys(Integer value) {
        this.cwsys = value;
    }
    /** 获取:床位使用数 */
    public Integer getCwsys() {
        return cwsys;
    }

    /** 设置:床位未使用数  */
    public void setCwwsy(Integer value) {
        this.cwwsy = value;
    }
    /** 获取:床位未使用数 */
    public Integer getCwwsy() {
        return cwwsy;
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