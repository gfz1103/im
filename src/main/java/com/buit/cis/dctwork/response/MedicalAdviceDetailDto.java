package com.buit.cis.dctwork.response;

import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * @Description
 * @Author yueyu
 * @Date 2020/7/29 10:12
 */
public class MedicalAdviceDetailDto {

    @ApiModelProperty(hidden = true)
    private Integer zyh;
    @ApiModelProperty("医嘱序号")
    private Integer yzxh;
    @ApiModelProperty("医嘱类型，0：长期，1：临时医嘱")
    private Integer yzlx;
    @ApiModelProperty("药品名称")
    private String ypmc;
    @ApiModelProperty("药品规格")
    private String yfgg;
    @ApiModelProperty("产地名称")
    private String cdmc;
    @ApiModelProperty("一次计量")
    private BigDecimal ycjl;
    @ApiModelProperty("一次数量")
    private BigDecimal ycsl;
    @ApiModelProperty("给药途径")
    private String gytj;
    @ApiModelProperty("药房单位")
    private String yfdw;
    @ApiModelProperty("执行时间")
    private String yzzxsj;
    @ApiModelProperty("使用频次")
    private String sypc;
    @ApiModelProperty("备注信息")
    private String bzxx;
    @ApiModelProperty("开嘱时间")
    private Timestamp kssj;
    @ApiModelProperty("开嘱医生")
    private String ysgh;
    @ApiModelProperty("医嘱组号")
    private Integer yzzh;
    @ApiModelProperty("静配方式，1：配置，2：打包")
    private Integer jpfs;
    @ApiModelProperty("医嘱状态，1：正常，0：停嘱")
    private Integer yzzt;
    @ApiModelProperty("计量单位")
    private String jldw;

    public String getJldw() {
        return jldw;
    }

    public void setJldw(String jldw) {
        this.jldw = jldw;
    }

    public String getYfdw() {
        return yfdw;
    }

    public void setYfdw(String yfdw) {
        this.yfdw = yfdw;
    }

    public Integer getJpfs() {
        return jpfs;
    }

    public void setJpfs(Integer jpfs) {
        this.jpfs = jpfs;
    }

    public Integer getYzzt() {
        return yzzt;
    }

    public void setYzzt(Integer yzzt) {
        this.yzzt = yzzt;
    }

    public Integer getZyh() {
        return zyh;
    }

    public void setZyh(Integer zyh) {
        this.zyh = zyh;
    }

    public Integer getYzxh() {
        return yzxh;
    }

    public void setYzxh(Integer yzxh) {
        this.yzxh = yzxh;
    }

    public Integer getYzlx() {
        return yzlx;
    }

    public void setYzlx(Integer yzlx) {
        this.yzlx = yzlx;
    }

    public String getYpmc() {
        return ypmc;
    }

    public void setYpmc(String ypmc) {
        this.ypmc = ypmc;
    }

    public String getYfgg() {
        return yfgg;
    }

    public void setYfgg(String yfgg) {
        this.yfgg = yfgg;
    }

    public String getCdmc() {
        return cdmc;
    }

    public void setCdmc(String cdmc) {
        this.cdmc = cdmc;
    }

    public BigDecimal getYcjl() {
        return ycjl;
    }

    public void setYcjl(BigDecimal ycjl) {
        this.ycjl = ycjl;
    }

    public BigDecimal getYcsl() {
        return ycsl;
    }

    public void setYcsl(BigDecimal ycsl) {
        this.ycsl = ycsl;
    }

    public String getGytj() {
        return gytj;
    }

    public void setGytj(String gytj) {
        this.gytj = gytj;
    }

    public String getYzzxsj() {
        return yzzxsj;
    }

    public void setYzzxsj(String yzzxsj) {
        this.yzzxsj = yzzxsj;
    }

    public String getSypc() {
        return sypc;
    }

    public void setSypc(String sypc) {
        this.sypc = sypc;
    }

    public String getBzxx() {
        return bzxx;
    }

    public void setBzxx(String bzxx) {
        this.bzxx = bzxx;
    }

    public Timestamp getKssj() {
        return kssj;
    }

    public void setKssj(Timestamp kssj) {
        this.kssj = kssj;
    }

    public String getYsgh() {
        return ysgh;
    }

    public void setYsgh(String ysgh) {
        this.ysgh = ysgh;
    }

    public Integer getYzzh() {
        return yzzh;
    }

    public void setYzzh(Integer yzzh) {
        this.yzzh = yzzh;
    }
}
