package com.buit.cis.dctwork.model;

import java.sql.Timestamp;
import com.buit.commons.PageQuery;

/**
 * 类名称：NisTj01<br> 
 * 类描述：病区_提交记录 | 记录病区医嘱提交的记录，与提交明细表BQ_TJMX通过TJXH关联
 * @author GONGFANGZHOU 
 * @ApiModel(value="病区_提交记录 | 记录病区医嘱提交的记录，与提交明细表BQ_TJMX通过TJXH关联")
 */
public class NisTj01  extends  PageQuery{
	//@ApiModelProperty(value="提交序号")
    /** 提交序号 */
    private Integer tjxh;
	//@ApiModelProperty(value="机构代码")
    /** 机构代码 */
    private Integer jgid;
	//@ApiModelProperty(value="提交药房")
    /** 提交药房 */
    private Integer tjyf;
	//@ApiModelProperty(value="医嘱类型")
    /** 医嘱类型 */
    private Integer yzlx;
	//@ApiModelProperty(value="发药方式")
    /** 发药方式 */
    private Integer fyfs;
	//@ApiModelProperty(value="项目类型")
    /** 项目类型 */
    private Integer xmlx;
	//@ApiModelProperty(value="提交时间")
    /** 提交时间 */
    private Timestamp tjsj;
	//@ApiModelProperty(value="提交病区")
    /** 提交病区 */
    private Integer tjbq;
	//@ApiModelProperty(value="提交工号")
    /** 提交工号 */
    private String tjgh;
	//@ApiModelProperty(value="发药标志")
    /** 发药标志 */
    private Integer fybz;

    /** 设置:提交序号  */
    public void setTjxh(Integer value) {
        this.tjxh = value;
    }
    /** 获取:提交序号 */
    public Integer getTjxh() {
        return tjxh;
    }

    /** 设置:机构代码  */
    public void setJgid(Integer value) {
        this.jgid = value;
    }
    /** 获取:机构代码 */
    public Integer getJgid() {
        return jgid;
    }

    /** 设置:提交药房  */
    public void setTjyf(Integer value) {
        this.tjyf = value;
    }
    /** 获取:提交药房 */
    public Integer getTjyf() {
        return tjyf;
    }

    /** 设置:医嘱类型  */
    public void setYzlx(Integer value) {
        this.yzlx = value;
    }
    /** 获取:医嘱类型 */
    public Integer getYzlx() {
        return yzlx;
    }

    /** 设置:发药方式  */
    public void setFyfs(Integer value) {
        this.fyfs = value;
    }
    /** 获取:发药方式 */
    public Integer getFyfs() {
        return fyfs;
    }

    /** 设置:项目类型  */
    public void setXmlx(Integer value) {
        this.xmlx = value;
    }
    /** 获取:项目类型 */
    public Integer getXmlx() {
        return xmlx;
    }

    /** 设置:提交时间  */
    public void setTjsj(Timestamp value) {
        this.tjsj = value;
    }
    /** 获取:提交时间 */
    public Timestamp getTjsj() {
        return tjsj;
    }

    /** 设置:提交病区  */
    public void setTjbq(Integer value) {
        this.tjbq = value;
    }
    /** 获取:提交病区 */
    public Integer getTjbq() {
        return tjbq;
    }

    /** 设置:提交工号  */
    public void setTjgh(String value) {
        this.tjgh = value;
    }
    /** 获取:提交工号 */
    public String getTjgh() {
        return tjgh;
    }

    /** 设置:发药标志  */
    public void setFybz(Integer value) {
        this.fybz = value;
    }
    /** 获取:发药标志 */
    public Integer getFybz() {
        return fybz;
    }


}
