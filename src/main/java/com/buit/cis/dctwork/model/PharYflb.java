package com.buit.cis.dctwork.model;

import com.buit.commons.PageQuery;

/**
 * 类名称：PharYflb<br> 
 * 类描述：药房_药房列表
 * @author yueyu
 * @ApiModel(value="药房_药房列表")
 */
public class PharYflb extends  PageQuery{
    //@ApiModelProperty(value="机构代码")
    /** 机构代码 */
    private Integer jgid;
    //@ApiModelProperty(value="药房识别")
    /** 药房识别 */
    private Integer yfsb;
    //@ApiModelProperty(value="药房名称")
    /** 药房名称 */
    private String yfmc;
    //@ApiModelProperty(value="包装类别 | 1.门诊 2。住院")
    /** 包装类别 | 1.门诊 2。住院 */
    private Integer bzlb;
    //@ApiModelProperty(value="西药权限")
    /** 西药权限 */
    private Integer xyqx;
    //@ApiModelProperty(value="中药权限")
    /** 中药权限 */
    private Integer zyqx;
    //@ApiModelProperty(value="草药权限")
    /** 草药权限 */
    private Integer cyqx;
    //@ApiModelProperty(value="三级管理标志")
    /** 三级管理标志 */
    private Integer sjglbz;
    //@ApiModelProperty(value="三级库房 | 用于标志本药房是否是三级库房，>0时，表示对应三级库房的科室代码。")
    /** 三级库房 | 用于标志本药房是否是三级库房，>0时，表示对应三级库房的科室代码。 */
    private Integer sjkf;
    //@ApiModelProperty(value="领药科室 | 保存在本药房领药的科室代码列表，中间用“，”隔开。")
    /** 领药科室 | 保存在本药房领药的科室代码列表，中间用“，”隔开。 */
    private String lyks;
    //@ApiModelProperty(value="注销标志 | 0.正常 1.注销")
    /** 注销标志 | 0.正常 1.注销 */
    private Integer zxbz;
    //@ApiModelProperty(value="上级发药机构")
    /** 上级发药机构 */
    private String sjjg;
    //@ApiModelProperty(value="上级发药药房")
    /** 上级发药药房 */
    private Integer sjyf;
    //@ApiModelProperty(value="医院区平台代码")
    /** 医院区平台代码 */
    private String yydmGyl;
    //@ApiModelProperty(value="dyyk")
    /** dyyk */
    private Integer dyyk;

    /** 设置:机构代码  */
    public void setJgid(Integer value) {
        this.jgid = value;
    }
    /** 获取:机构代码 */
    public Integer getJgid() {
        return jgid;
    }

    /** 设置:药房识别  */
    public void setYfsb(Integer value) {
        this.yfsb = value;
    }
    /** 获取:药房识别 */
    public Integer getYfsb() {
        return yfsb;
    }

    /** 设置:药房名称  */
    public void setYfmc(String value) {
        this.yfmc = value;
    }
    /** 获取:药房名称 */
    public String getYfmc() {
        return yfmc;
    }

    /** 设置:包装类别 | 1.门诊 2。住院  */
    public void setBzlb(Integer value) {
        this.bzlb = value;
    }
    /** 获取:包装类别 | 1.门诊 2。住院 */
    public Integer getBzlb() {
        return bzlb;
    }

    /** 设置:西药权限  */
    public void setXyqx(Integer value) {
        this.xyqx = value;
    }
    /** 获取:西药权限 */
    public Integer getXyqx() {
        return xyqx;
    }

    /** 设置:中药权限  */
    public void setZyqx(Integer value) {
        this.zyqx = value;
    }
    /** 获取:中药权限 */
    public Integer getZyqx() {
        return zyqx;
    }

    /** 设置:草药权限  */
    public void setCyqx(Integer value) {
        this.cyqx = value;
    }
    /** 获取:草药权限 */
    public Integer getCyqx() {
        return cyqx;
    }

    /** 设置:三级管理标志  */
    public void setSjglbz(Integer value) {
        this.sjglbz = value;
    }
    /** 获取:三级管理标志 */
    public Integer getSjglbz() {
        return sjglbz;
    }

    /** 设置:三级库房 | 用于标志本药房是否是三级库房，>0时，表示对应三级库房的科室代码。  */
    public void setSjkf(Integer value) {
        this.sjkf = value;
    }
    /** 获取:三级库房 | 用于标志本药房是否是三级库房，>0时，表示对应三级库房的科室代码。 */
    public Integer getSjkf() {
        return sjkf;
    }

    /** 设置:领药科室 | 保存在本药房领药的科室代码列表，中间用“，”隔开。  */
    public void setLyks(String value) {
        this.lyks = value;
    }
    /** 获取:领药科室 | 保存在本药房领药的科室代码列表，中间用“，”隔开。 */
    public String getLyks() {
        return lyks;
    }

    /** 设置:注销标志 | 0.正常 1.注销  */
    public void setZxbz(Integer value) {
        this.zxbz = value;
    }
    /** 获取:注销标志 | 0.正常 1.注销 */
    public Integer getZxbz() {
        return zxbz;
    }

    /** 设置:上级发药机构  */
    public void setSjjg(String value) {
        this.sjjg = value;
    }
    /** 获取:上级发药机构 */
    public String getSjjg() {
        return sjjg;
    }

    /** 设置:上级发药药房  */
    public void setSjyf(Integer value) {
        this.sjyf = value;
    }
    /** 获取:上级发药药房 */
    public Integer getSjyf() {
        return sjyf;
    }

    /** 设置:医院区平台代码  */
    public void setYydmGyl(String value) {
        this.yydmGyl = value;
    }
    /** 获取:医院区平台代码 */
    public String getYydmGyl() {
        return yydmGyl;
    }

    /** 设置:dyyk  */
    public void setDyyk(Integer value) {
        this.dyyk = value;
    }
    /** 获取:dyyk */
    public Integer getDyyk() {
        return dyyk;
    }


}
