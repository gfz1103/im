package com.buit.cis.dctwork.model;

import java.sql.Timestamp;
import com.buit.commons.PageQuery;

/**
 * 类名称：EnrJl02<br> 
 * 类描述：
 * @author GONGFANGZHOU 
 * @ApiModel(value="")
 */
public class EnrJl02  extends  PageQuery{
	//@ApiModelProperty(value="明细编号")
    /** 明细编号 */
    private Integer mxbh;
	//@ApiModelProperty(value="记录编号")
    /** 记录编号 */
    private Integer jlbh;
	//@ApiModelProperty(value="项目编号")
    /** 项目编号 */
    private Integer xmbh;
	//@ApiModelProperty(value="项目名称")
    /** 项目名称 */
    private String xmmc;
	//@ApiModelProperty(value="显示名称")
    /** 显示名称 */
    private String xsmc;
	//@ApiModelProperty(value="对象取值:Oracle中为varchar2(4000)")
    /** 对象取值:Oracle中为varchar2(4000) */
    private String xmqz;
	//@ApiModelProperty(value="开始列号:表达列的关系,用于显示时识别数据窗口中的列号,若相同则为活动列")
    /** 开始列号:表达列的关系,用于显示时识别数据窗口中的列号,若相同则为活动列 */
    private Integer kslh;
	//@ApiModelProperty(value="结束列号:表达列的关系,")
    /** 结束列号:表达列的关系, */
    private Integer jslh;
	//@ApiModelProperty(value="活动标志:预先自动计算是否是一个活动列,由程序控制。")
    /** 活动标志:预先自动计算是否是一个活动列,由程序控制。 */
    private Integer hdbz;
	//@ApiModelProperty(value="页面处理方式:用于页眉项目 0不处理 1标记过程 2标记初始 3标记最新 DICT104")
    /** 页面处理方式:用于页眉项目 0不处理 1标记过程 2标记初始 3标记最新 DICT104 */
    private Integer ymclfs;
	//@ApiModelProperty(value="换行间隔:即每n行文字算一个数据行，0单行 1单行换行 2每2行换行...DICT116")
    /** 换行间隔:即每n行文字算一个数据行，0单行 1单行换行 2每2行换行...DICT116 */
    private Integer hhjg;
	//@ApiModelProperty(value="修改标志")
    /** 修改标志 */
    private Integer xgbz;
	//@ApiModelProperty(value="jgid")
    /** jgid */
    private Integer jgid;
	//@ApiModelProperty(value="lymxlx")
    /** lymxlx */
    private Integer lymxlx;
	//@ApiModelProperty(value="lymx")
    /** lymx */
    private Integer lymx;
	//@ApiModelProperty(value="lybh")
    /** lybh */
    private Integer lybh;
	//@ApiModelProperty(value="lybd")
    /** lybd */
    private Integer lybd;

    /** 设置:明细编号  */
    public void setMxbh(Integer value) {
        this.mxbh = value;
    }
    /** 获取:明细编号 */
    public Integer getMxbh() {
        return mxbh;
    }

    /** 设置:记录编号  */
    public void setJlbh(Integer value) {
        this.jlbh = value;
    }
    /** 获取:记录编号 */
    public Integer getJlbh() {
        return jlbh;
    }

    /** 设置:项目编号  */
    public void setXmbh(Integer value) {
        this.xmbh = value;
    }
    /** 获取:项目编号 */
    public Integer getXmbh() {
        return xmbh;
    }

    /** 设置:项目名称  */
    public void setXmmc(String value) {
        this.xmmc = value;
    }
    /** 获取:项目名称 */
    public String getXmmc() {
        return xmmc;
    }

    /** 设置:显示名称  */
    public void setXsmc(String value) {
        this.xsmc = value;
    }
    /** 获取:显示名称 */
    public String getXsmc() {
        return xsmc;
    }

    /** 设置:对象取值:Oracle中为varchar2(4000)  */
    public void setXmqz(String value) {
        this.xmqz = value;
    }
    /** 获取:对象取值:Oracle中为varchar2(4000) */
    public String getXmqz() {
        return xmqz;
    }

    /** 设置:开始列号:表达列的关系,用于显示时识别数据窗口中的列号,若相同则为活动列  */
    public void setKslh(Integer value) {
        this.kslh = value;
    }
    /** 获取:开始列号:表达列的关系,用于显示时识别数据窗口中的列号,若相同则为活动列 */
    public Integer getKslh() {
        return kslh;
    }

    /** 设置:结束列号:表达列的关系,  */
    public void setJslh(Integer value) {
        this.jslh = value;
    }
    /** 获取:结束列号:表达列的关系, */
    public Integer getJslh() {
        return jslh;
    }

    /** 设置:活动标志:预先自动计算是否是一个活动列,由程序控制。  */
    public void setHdbz(Integer value) {
        this.hdbz = value;
    }
    /** 获取:活动标志:预先自动计算是否是一个活动列,由程序控制。 */
    public Integer getHdbz() {
        return hdbz;
    }

    /** 设置:页面处理方式:用于页眉项目 0不处理 1标记过程 2标记初始 3标记最新 DICT104  */
    public void setYmclfs(Integer value) {
        this.ymclfs = value;
    }
    /** 获取:页面处理方式:用于页眉项目 0不处理 1标记过程 2标记初始 3标记最新 DICT104 */
    public Integer getYmclfs() {
        return ymclfs;
    }

    /** 设置:换行间隔:即每n行文字算一个数据行，0单行 1单行换行 2每2行换行...DICT116  */
    public void setHhjg(Integer value) {
        this.hhjg = value;
    }
    /** 获取:换行间隔:即每n行文字算一个数据行，0单行 1单行换行 2每2行换行...DICT116 */
    public Integer getHhjg() {
        return hhjg;
    }

    /** 设置:修改标志  */
    public void setXgbz(Integer value) {
        this.xgbz = value;
    }
    /** 获取:修改标志 */
    public Integer getXgbz() {
        return xgbz;
    }

    /** 设置:jgid  */
    public void setJgid(Integer value) {
        this.jgid = value;
    }
    /** 获取:jgid */
    public Integer getJgid() {
        return jgid;
    }

    /** 设置:lymxlx  */
    public void setLymxlx(Integer value) {
        this.lymxlx = value;
    }
    /** 获取:lymxlx */
    public Integer getLymxlx() {
        return lymxlx;
    }

    /** 设置:lymx  */
    public void setLymx(Integer value) {
        this.lymx = value;
    }
    /** 获取:lymx */
    public Integer getLymx() {
        return lymx;
    }

    /** 设置:lybh  */
    public void setLybh(Integer value) {
        this.lybh = value;
    }
    /** 获取:lybh */
    public Integer getLybh() {
        return lybh;
    }

    /** 设置:lybd  */
    public void setLybd(Integer value) {
        this.lybd = value;
    }
    /** 获取:lybd */
    public Integer getLybd() {
        return lybd;
    }


}