package com.buit.cis.ims.model;

import com.buit.commons.PageQuery;

import java.sql.Timestamp;

/**
 * 类名称：ImCwtj<br> 
 * 类描述：住院_床位统计
 * @author ZHOUHAISHENG 
 * @ApiModel(value="住院_床位统计")
 */
public class ImCwtj  extends PageQuery {
	//@ApiModelProperty(value="记录序号")
    /** 记录序号 */
    private Integer jlxh;
	//@ApiModelProperty(value="机构代码")
    /** 机构代码 */
    private Integer jgid;
	//@ApiModelProperty(value="操作日期")
    /** 操作日期 */
    private Timestamp czrq;
	//@ApiModelProperty(value="操作类型 | 1分配 2 转入 3 包床 -4 退床-3退包 -2转出 -1 出院")
    /** 操作类型 | 1分配 2 转入 3 包床 -4 退床-3退包 -2转出 -1 出院 */
    private Integer czlx;
	//@ApiModelProperty(value="住院号")
    /** 住院号 */
    private Integer zyh;
	//@ApiModelProperty(value="病人科室")
    /** 病人科室 */
    private Integer brks;
	//@ApiModelProperty(value="原使用数")
    /** 原使用数 */
    private Integer ysys;
	//@ApiModelProperty(value="现使用数")
    /** 现使用数 */
    private Integer xsys;
	//@ApiModelProperty(value="病区判别 | 0：科室          1：病区")
    /** 病区判别 | 0：科室          1：病区 */
    private Integer bqpb;

    /** 设置:记录序号  */
    public void setJlxh(Integer value) {
        this.jlxh = value;
    }
    /** 获取:记录序号 */
    public Integer getJlxh() {
        return jlxh;
    }

    /** 设置:机构代码  */
    public void setJgid(Integer value) {
        this.jgid = value;
    }
    /** 获取:机构代码 */
    public Integer getJgid() {
        return jgid;
    }

    /** 设置:操作日期  */
    public void setCzrq(Timestamp value) {
        this.czrq = value;
    }
    /** 获取:操作日期 */
    public Timestamp getCzrq() {
        return czrq;
    }

    /** 设置:操作类型 | 1分配 2 转入 3 包床 -4 退床-3退包 -2转出 -1 出院  */
    public void setCzlx(Integer value) {
        this.czlx = value;
    }
    /** 获取:操作类型 | 1分配 2 转入 3 包床 -4 退床-3退包 -2转出 -1 出院 */
    public Integer getCzlx() {
        return czlx;
    }

    /** 设置:住院号  */
    public void setZyh(Integer value) {
        this.zyh = value;
    }
    /** 获取:住院号 */
    public Integer getZyh() {
        return zyh;
    }

    /** 设置:病人科室  */
    public void setBrks(Integer value) {
        this.brks = value;
    }
    /** 获取:病人科室 */
    public Integer getBrks() {
        return brks;
    }

    /** 设置:原使用数  */
    public void setYsys(Integer value) {
        this.ysys = value;
    }
    /** 获取:原使用数 */
    public Integer getYsys() {
        return ysys;
    }

    /** 设置:现使用数  */
    public void setXsys(Integer value) {
        this.xsys = value;
    }
    /** 获取:现使用数 */
    public Integer getXsys() {
        return xsys;
    }

    /** 设置:病区判别 | 0：科室          1：病区  */
    public void setBqpb(Integer value) {
        this.bqpb = value;
    }
    /** 获取:病区判别 | 0：科室          1：病区 */
    public Integer getBqpb() {
        return bqpb;
    }


}
