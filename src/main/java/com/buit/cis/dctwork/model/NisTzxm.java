package com.buit.cis.dctwork.model;

import java.sql.Timestamp;
import com.buit.commons.PageQuery;

/**
 * 类名称：NisTzxm<br> 
 * 类描述：
 * @author GONGFANGZHOU 
 * @ApiModel(value="")
 */
public class NisTzxm  extends  PageQuery{
	//@ApiModelProperty(value="项目号")
    /** 项目号 */
    private Long xmh;
	//@ApiModelProperty(value="项目名称")
    /** 项目名称 */
    private String xmmc;
	//@ApiModelProperty(value="拼音代码")
    /** 拼音代码 */
    private String pydm;
	//@ApiModelProperty(value="状态标志（0：正常；1：注销）")
    /** 状态标志（0：正常；1：注销） */
    private Integer ztbz;
	//@ApiModelProperty(value="系统标志（0：自定义；1：系统（不能修改））")
    /** 系统标志（0：自定义；1：系统（不能修改）） */
    private Integer xtbz;
	//@ApiModelProperty(value="体征类型（1.基本体征 2.入量 3.出量 4.皮试）")
    /** 体征类型（1.基本体征 2.入量 3.出量 4.皮试） */
    private Integer tzlx;
	//@ApiModelProperty(value="xmdw")
    /** xmdw */
    private Integer xmdw;
	//@ApiModelProperty(value="体温单显示标志：1表示默认显示到体温单中 0否（用户可再选择）")
    /** 体温单显示标志：1表示默认显示到体温单中 0否（用户可再选择） */
    private Integer twdxs;

    /** 设置:项目号  */
    public void setXmh(Long value) {
        this.xmh = value;
    }
    /** 获取:项目号 */
    public Long getXmh() {
        return xmh;
    }

    /** 设置:项目名称  */
    public void setXmmc(String value) {
        this.xmmc = value;
    }
    /** 获取:项目名称 */
    public String getXmmc() {
        return xmmc;
    }

    /** 设置:拼音代码  */
    public void setPydm(String value) {
        this.pydm = value;
    }
    /** 获取:拼音代码 */
    public String getPydm() {
        return pydm;
    }

    /** 设置:状态标志（0：正常；1：注销）  */
    public void setZtbz(Integer value) {
        this.ztbz = value;
    }
    /** 获取:状态标志（0：正常；1：注销） */
    public Integer getZtbz() {
        return ztbz;
    }

    /** 设置:系统标志（0：自定义；1：系统（不能修改））  */
    public void setXtbz(Integer value) {
        this.xtbz = value;
    }
    /** 获取:系统标志（0：自定义；1：系统（不能修改）） */
    public Integer getXtbz() {
        return xtbz;
    }

    /** 设置:体征类型（1.基本体征 2.入量 3.出量 4.皮试）  */
    public void setTzlx(Integer value) {
        this.tzlx = value;
    }
    /** 获取:体征类型（1.基本体征 2.入量 3.出量 4.皮试） */
    public Integer getTzlx() {
        return tzlx;
    }

    /** 设置:xmdw  */
    public void setXmdw(Integer value) {
        this.xmdw = value;
    }
    /** 获取:xmdw */
    public Integer getXmdw() {
        return xmdw;
    }

    /** 设置:体温单显示标志：1表示默认显示到体温单中 0否（用户可再选择）  */
    public void setTwdxs(Integer value) {
        this.twdxs = value;
    }
    /** 获取:体温单显示标志：1表示默认显示到体温单中 0否（用户可再选择） */
    public Integer getTwdxs() {
        return twdxs;
    }


}
