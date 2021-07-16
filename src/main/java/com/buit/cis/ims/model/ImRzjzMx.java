package com.buit.cis.ims.model;

import com.buit.commons.PageQuery;

import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * 类名称：ImRzjzMx<br>
 * 类描述：住院_日账终结项目明细信息
 * @author songyu
 * @ApiModel(value="住院_日账终结信息")
 */
public class ImRzjzMx extends PageQuery {
    //@ApiModelProperty(value="结账日期 ")
    /** 结账日期 */
    private Timestamp jzrq;
    //@ApiModelProperty(value="操作工号")
    /** 操作工号 */
    private String czgh;
    //@ApiModelProperty(value="机构代码")
    /** 机构代码 */
    private String jgid;
    //@ApiModelProperty(value="项目名称")
    /** 项目名称 */
    private String xmmc;
    //@ApiModelProperty(value="项目类型")
    /** 项目类型 */
    private Integer xmlx;
    //@ApiModelProperty(value="收入合计")
    /** 收入合计 */
    private BigDecimal srhj;
    //@ApiModelProperty(value="优惠金额")
    /** 优惠金额 */
    private BigDecimal yhje;
    //@ApiModelProperty(value="实际收入")
    /** 实际收入 */
    private BigDecimal sjsr;


    /** 设置:结算日期 | 权责发生制所有收款员日报汇总日期  */
    public void setJzrq(Timestamp value) {
        this.jzrq = value;
    }
    /** 获取:结算日期 | 权责发生制所有收款员日报汇总日期 */
    public Timestamp getJzrq() {
        return jzrq;
    }

    /** 设置:操作工号  */
    public void setCzgh(String value) {
        this.czgh = value;
    }
    /** 获取:操作工号 */
    public String getCzgh() {
        return czgh;
    }

    /** 设置:机构代码  */
    public void setJgid(String value) {
        this.jgid = value;
    }
    /** 获取:机构代码 */
    public String getJgid() {
        return jgid;
    }

    /** 设置: 项目名称  */
    public void setXmmc(String value) {
        this.xmmc = value;
    }
    /** 获取: 项目名称 */
    public String getXmmc() {
        return xmmc;
    }

    /** 设置: 项目类型  */
    public void setXmlx(Integer value) {
        this.xmlx = value;
    }
    /** 获取: 项目类型 */
    public Integer getXmlx() {
        return xmlx;
    }

    /** 设置:收入合计  */
    public void setSrhj(BigDecimal value) {
        this.srhj = value;
    }
    /** 获取:收入合计 */
    public BigDecimal getSrhj() {
        return srhj;
    }

    /** 设置:优惠金额  */
    public void setYhje(BigDecimal value) {
        this.yhje = value;
    }
    /** 获取:优惠金额 */
    public BigDecimal getYhje() {
        return yhje;
    }

    /** 设置:实际收入  */
    public void setSjsr(BigDecimal value) {
        this.sjsr = value;
    }
    /** 获取:实际收入 */
    public BigDecimal getSjsr() {
        return sjsr;
    }

}
