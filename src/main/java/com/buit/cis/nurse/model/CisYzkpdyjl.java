package com.buit.cis.nurse.model;

import java.sql.Timestamp;
import com.buit.commons.PageQuery;

/**
 * 类名称：CisYzkpdyjl<br> 
 * 类描述：住院医嘱医嘱卡片打印记录
 * @author GONGFANGZHOU 
 * @ApiModel(value="住院医嘱医嘱卡片打印记录")
 */
public class CisYzkpdyjl  extends  PageQuery{
	//@ApiModelProperty(value="记录序号")
    /** 记录序号 */
    private Integer id;
	//@ApiModelProperty(value="住院号")
    /** 住院号 */
    private Integer zyh;
	//@ApiModelProperty(value="医嘱序号")
    /** 医嘱序号 */
    private Integer yzxh;
	//@ApiModelProperty(value="医嘱组号")
    /** 医嘱组号 */
    private Integer yzzh;
	//@ApiModelProperty(value="打印时间")
    /** 打印时间 */
    private Timestamp dysj;
	//@ApiModelProperty(value="用药时间")
    /** 用药时间 */
    private Timestamp yysj;
	//@ApiModelProperty(value="打印标志")
    /** 打印标志 */
    private Integer dybz;

    /** 设置:记录序号  */
    public void setId(Integer value) {
        this.id = value;
    }
    /** 获取:记录序号 */
    public Integer getId() {
        return id;
    }

    /** 设置:住院号  */
    public void setZyh(Integer value) {
        this.zyh = value;
    }
    /** 获取:住院号 */
    public Integer getZyh() {
        return zyh;
    }

    /** 设置:医嘱序号  */
    public void setYzxh(Integer value) {
        this.yzxh = value;
    }
    /** 获取:医嘱序号 */
    public Integer getYzxh() {
        return yzxh;
    }

    /** 设置:医嘱组号  */
    public void setYzzh(Integer value) {
        this.yzzh = value;
    }
    /** 获取:医嘱组号 */
    public Integer getYzzh() {
        return yzzh;
    }

    /** 设置:打印时间  */
    public void setDysj(Timestamp value) {
        this.dysj = value;
    }
    /** 获取:打印时间 */
    public Timestamp getDysj() {
        return dysj;
    }

    /** 设置:用药时间  */
    public void setYysj(Timestamp value) {
        this.yysj = value;
    }
    /** 获取:用药时间 */
    public Timestamp getYysj() {
        return yysj;
    }

    /** 设置:打印标志  */
    public void setDybz(Integer value) {
        this.dybz = value;
    }
    /** 获取:打印标志 */
    public Integer getDybz() {
        return dybz;
    }


}