package com.buit.cis.nurse.model;

import java.sql.Timestamp;
import com.buit.commons.PageQuery;

/**
 * 类名称：CisKpkfbz<br> 
 * 类描述：口服包装
 * @author GONGFANGZHOU 
 * @ApiModel(value="口服包装")
 */
public class CisKpkfbz  extends  PageQuery{
	//@ApiModelProperty(value="主键")
    /** 主键 */
    private Integer kfmxid;
	//@ApiModelProperty(value="口服单号")
    /** 口服单号 */
    private Integer kfdh;
	//@ApiModelProperty(value="条码编号")
    /** 条码编号 */
    private String tmbh;
	//@ApiModelProperty(value="药袋描述")
    /** 药袋描述 */
    private String ydms;
	//@ApiModelProperty(value="作废标志")
    /** 作废标志 */
    private Integer zfbz;
	//@ApiModelProperty(value="来源标识")
    /** 来源标识 */
    private String lybs;
	//@ApiModelProperty(value="服药状态")
    /** 服药状态 */
    private Integer kfzt;
	//@ApiModelProperty(value="执行时间")
    /** 执行时间 */
    private Timestamp zxsj;
	//@ApiModelProperty(value="执行工号")
    /** 执行工号 */
    private Integer zxgh;
	//@ApiModelProperty(value="核对时间")
    /** 核对时间 */
    private Timestamp hdsj;
	//@ApiModelProperty(value="核对工号")
    /** 核对工号 */
    private Integer hdgh;
	//@ApiModelProperty(value="打印次数")
    /** 打印次数 */
    private Integer dycs;
	//@ApiModelProperty(value="打印工号")
    /** 打印工号 */
    private Integer dygh;
	//@ApiModelProperty(value="打印时间")
    /** 打印时间 */
    private Timestamp dysj;

    /** 设置:主键  */
    public void setKfmxid(Integer value) {
        this.kfmxid = value;
    }
    /** 获取:主键 */
    public Integer getKfmxid() {
        return kfmxid;
    }

    /** 设置:口服单号  */
    public void setKfdh(Integer value) {
        this.kfdh = value;
    }
    /** 获取:口服单号 */
    public Integer getKfdh() {
        return kfdh;
    }

    /** 设置:条码编号  */
    public void setTmbh(String value) {
        this.tmbh = value;
    }
    /** 获取:条码编号 */
    public String getTmbh() {
        return tmbh;
    }

    /** 设置:药袋描述  */
    public void setYdms(String value) {
        this.ydms = value;
    }
    /** 获取:药袋描述 */
    public String getYdms() {
        return ydms;
    }

    /** 设置:作废标志  */
    public void setZfbz(Integer value) {
        this.zfbz = value;
    }
    /** 获取:作废标志 */
    public Integer getZfbz() {
        return zfbz;
    }

    /** 设置:来源标识  */
    public void setLybs(String value) {
        this.lybs = value;
    }
    /** 获取:来源标识 */
    public String getLybs() {
        return lybs;
    }

    /** 设置:服药状态  */
    public void setKfzt(Integer value) {
        this.kfzt = value;
    }
    /** 获取:服药状态 */
    public Integer getKfzt() {
        return kfzt;
    }

    /** 设置:执行时间  */
    public void setZxsj(Timestamp value) {
        this.zxsj = value;
    }
    /** 获取:执行时间 */
    public Timestamp getZxsj() {
        return zxsj;
    }

    /** 设置:执行工号  */
    public void setZxgh(Integer value) {
        this.zxgh = value;
    }
    /** 获取:执行工号 */
    public Integer getZxgh() {
        return zxgh;
    }

    /** 设置:核对时间  */
    public void setHdsj(Timestamp value) {
        this.hdsj = value;
    }
    /** 获取:核对时间 */
    public Timestamp getHdsj() {
        return hdsj;
    }

    /** 设置:核对工号  */
    public void setHdgh(Integer value) {
        this.hdgh = value;
    }
    /** 获取:核对工号 */
    public Integer getHdgh() {
        return hdgh;
    }

    /** 设置:打印次数  */
    public void setDycs(Integer value) {
        this.dycs = value;
    }
    /** 获取:打印次数 */
    public Integer getDycs() {
        return dycs;
    }

    /** 设置:打印工号  */
    public void setDygh(Integer value) {
        this.dygh = value;
    }
    /** 获取:打印工号 */
    public Integer getDygh() {
        return dygh;
    }

    /** 设置:打印时间  */
    public void setDysj(Timestamp value) {
        this.dysj = value;
    }
    /** 获取:打印时间 */
    public Timestamp getDysj() {
        return dysj;
    }


}