package com.buit.cis.nurse.model;

import java.sql.Timestamp;
import com.buit.commons.PageQuery;

/**
 * 类名称：NisYzdy<br> 
 * 类描述：
 * @author GONGFANGZHOU 
 * @ApiModel(value="")
 */
public class NisYzdy  extends  PageQuery{
	//@ApiModelProperty(value="打印序号")
    /** 打印序号 */
    private Integer dyxh;
	//@ApiModelProperty(value="医嘱本序号,对应CIS_HZYZ的JLXH")
    /** 医嘱本序号,对应CIS_HZYZ的JLXH */
    private Integer yzbxh;
	//@ApiModelProperty(value="住院号")
    /** 住院号 */
    private Integer zyh;
	//@ApiModelProperty(value="医嘱期效（1 长期 2 临时）")
    /** 医嘱期效（1 长期 2 临时） */
    private Integer yzqx;
	//@ApiModelProperty(value="打印日期")
    /** 打印日期 */
    private Timestamp dyrq;
	//@ApiModelProperty(value="打印内容,由YZMC＋YCJL＋JLDW＋SYPC＋YPYF组成")
    /** 打印内容,由YZMC＋YCJL＋JLDW＋SYPC＋YPYF组成 */
    private String dynr;
	//@ApiModelProperty(value="打印页码")
    /** 打印页码 */
    private Integer dyym;
	//@ApiModelProperty(value="打印行号")
    /** 打印行号 */
    private Integer dyhh;
	//@ApiModelProperty(value="重整标志（ 0正常医嘱 1重整打印激流 2重整打印转抄医嘱）")
    /** 重整标志（ 0正常医嘱 1重整打印激流 2重整打印转抄医嘱） */
    private Integer czbz;
	//@ApiModelProperty(value="机构ID")
    /** 机构ID */
    private Integer jgid;
	//@ApiModelProperty(value="重整医生")
    /** 重整医生 */
    private String czys;
	//@ApiModelProperty(value="重整护士")
    /** 重整护士 */
    private String czhs;

    /** 设置:打印序号  */
    public void setDyxh(Integer value) {
        this.dyxh = value;
    }
    /** 获取:打印序号 */
    public Integer getDyxh() {
        return dyxh;
    }

    /** 设置:医嘱本序号,对应CIS_HZYZ的JLXH  */
    public void setYzbxh(Integer value) {
        this.yzbxh = value;
    }
    /** 获取:医嘱本序号,对应CIS_HZYZ的JLXH */
    public Integer getYzbxh() {
        return yzbxh;
    }

    /** 设置:住院号  */
    public void setZyh(Integer value) {
        this.zyh = value;
    }
    /** 获取:住院号 */
    public Integer getZyh() {
        return zyh;
    }

    /** 设置:医嘱期效（1 长期 2 临时）  */
    public void setYzqx(Integer value) {
        this.yzqx = value;
    }
    /** 获取:医嘱期效（1 长期 2 临时） */
    public Integer getYzqx() {
        return yzqx;
    }

    /** 设置:打印日期  */
    public void setDyrq(Timestamp value) {
        this.dyrq = value;
    }
    /** 获取:打印日期 */
    public Timestamp getDyrq() {
        return dyrq;
    }

    /** 设置:打印内容,由YZMC＋YCJL＋JLDW＋SYPC＋YPYF组成  */
    public void setDynr(String value) {
        this.dynr = value;
    }
    /** 获取:打印内容,由YZMC＋YCJL＋JLDW＋SYPC＋YPYF组成 */
    public String getDynr() {
        return dynr;
    }

    /** 设置:打印页码  */
    public void setDyym(Integer value) {
        this.dyym = value;
    }
    /** 获取:打印页码 */
    public Integer getDyym() {
        return dyym;
    }

    /** 设置:打印行号  */
    public void setDyhh(Integer value) {
        this.dyhh = value;
    }
    /** 获取:打印行号 */
    public Integer getDyhh() {
        return dyhh;
    }

    /** 设置:重整标志（ 0正常医嘱 1重整打印激流 2重整打印转抄医嘱）  */
    public void setCzbz(Integer value) {
        this.czbz = value;
    }
    /** 获取:重整标志（ 0正常医嘱 1重整打印激流 2重整打印转抄医嘱） */
    public Integer getCzbz() {
        return czbz;
    }

    /** 设置:机构ID  */
    public void setJgid(Integer value) {
        this.jgid = value;
    }
    /** 获取:机构ID */
    public Integer getJgid() {
        return jgid;
    }

    /** 设置:重整医生  */
    public void setCzys(String value) {
        this.czys = value;
    }
    /** 获取:重整医生 */
    public String getCzys() {
        return czys;
    }

    /** 设置:重整护士  */
    public void setCzhs(String value) {
        this.czhs = value;
    }
    /** 获取:重整护士 */
    public String getCzhs() {
        return czhs;
    }


}
