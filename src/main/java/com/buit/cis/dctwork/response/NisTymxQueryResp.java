   
package com.buit.cis.dctwork.response;

import java.math.BigDecimal;
import java.sql.Timestamp;

import com.buit.commons.PageQuery;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


/**
 * 类名称：NisTymxQueryResp<br> 
 * 类描述：病区_退药明细<br>
 * @author GONGFANGZHOU
 */
@ApiModel(value="病区_退药明细_queryResp")
public class NisTymxQueryResp  extends  PageQuery{
    @ApiModelProperty(value="记录序号")
    private Integer jlxh;
    @ApiModelProperty(value="住院号")
    private Integer zyh;
    @ApiModelProperty(value="药品序号")
    private Integer ypxh;
    @ApiModelProperty(value="药品产地")
    private Integer ypcd;
    @ApiModelProperty(value="申请日期")
    private Timestamp sqrq;
    @ApiModelProperty(value="药品价格")
    private BigDecimal ypdj;
    @ApiModelProperty(value="机构代码")
    private Integer jgid;
    @ApiModelProperty(value="退费日期")
    private Timestamp tyrq;
    @ApiModelProperty(value="药品规格")
    private String ypgg;
    @ApiModelProperty(value="药房单位")
    private String yfdw;
    @ApiModelProperty(value="药房包装")
    private Integer yfbz;
    @ApiModelProperty(value="药品数量")
    private BigDecimal ypsl;
    @ApiModelProperty(value="自负比例")
    private BigDecimal zfbl;
    @ApiModelProperty(value="婴儿判别")
    private Integer yepb;
    @ApiModelProperty(value="药房识别")
    private Integer yfsb;
    @ApiModelProperty(value="退药病区")
    private Integer tybq;
    @ApiModelProperty(value="操作工号")
    private String czgh;
    @ApiModelProperty(value="自费判别")
    private Integer zfpb;
    @ApiModelProperty(value="提交标志")
    private Integer tjbz;
    @ApiModelProperty(value="医嘱ID")
    private Integer yzid;
    @ApiModelProperty(value="退药类型")
    private Integer tylx;
    @ApiModelProperty(value="退回标志")
    private Integer thbz;
    @ApiModelProperty(value="退回时间")
    private Timestamp thsj;
    @ApiModelProperty(value="退回人")
    private String thr;
    @ApiModelProperty(value="记录ID")
    private Integer jlid;
    
    @ApiModelProperty(value="药品名称")
    private String ypmc;
    
    @ApiModelProperty(value="产地名称")
    private String cdmc;
    /**
     * 设置:记录序号
     */
    public void setJlxh(Integer value) {
        this.jlxh = value;
    }
    /**
     * 获取:记录序号
     */
    public Integer getJlxh() {
        return jlxh;
    }
    /**
     * 设置:住院号
     */
    public void setZyh(Integer value) {
        this.zyh = value;
    }
    /**
     * 获取:住院号
     */
    public Integer getZyh() {
        return zyh;
    }
    /**
     * 设置:药品序号
     */
    public void setYpxh(Integer value) {
        this.ypxh = value;
    }
    /**
     * 获取:药品序号
     */
    public Integer getYpxh() {
        return ypxh;
    }
    /**
     * 设置:药品产地
     */
    public void setYpcd(Integer value) {
        this.ypcd = value;
    }
    /**
     * 获取:药品产地
     */
    public Integer getYpcd() {
        return ypcd;
    }
    /**
     * 设置:申请日期
     */
    public void setSqrq(Timestamp value) {
        this.sqrq = value;
    }
    /**
     * 获取:申请日期
     */
    public Timestamp getSqrq() {
        return sqrq;
    }

    public BigDecimal getYpdj() {
		return ypdj;
	}
    
	public void setYpdj(BigDecimal ypdj) {
		this.ypdj = ypdj;
	}
	
	/**
     * 设置:机构代码
     */
    public void setJgid(Integer value) {
        this.jgid = value;
    }
    /**
     * 获取:机构代码
     */
    public Integer getJgid() {
        return jgid;
    }
    /**
     * 设置:退费日期
     */
    public void setTyrq(Timestamp value) {
        this.tyrq = value;
    }
    /**
     * 获取:退费日期
     */
    public Timestamp getTyrq() {
        return tyrq;
    }
    /**
     * 设置:药品规格
     */
    public void setYpgg(String value) {
        this.ypgg = value;
    }
    /**
     * 获取:药品规格
     */
    public String getYpgg() {
        return ypgg;
    }
    /**
     * 设置:药房单位
     */
    public void setYfdw(String value) {
        this.yfdw = value;
    }
    /**
     * 获取:药房单位
     */
    public String getYfdw() {
        return yfdw;
    }
    /**
     * 设置:药房包装
     */
    public void setYfbz(Integer value) {
        this.yfbz = value;
    }
    /**
     * 获取:药房包装
     */
    public Integer getYfbz() {
        return yfbz;
    }
    /**
     * 设置:药品数量
     */
    public void setYpsl(BigDecimal value) {
        this.ypsl = value;
    }
    /**
     * 获取:药品数量
     */
    public BigDecimal getYpsl() {
        return ypsl;
    }
    /**
     * 设置:自负比例
     */
    public void setZfbl(BigDecimal value) {
        this.zfbl = value;
    }
    /**
     * 获取:自负比例
     */
    public BigDecimal getZfbl() {
        return zfbl;
    }
    /**
     * 设置:婴儿判别
     */
    public void setYepb(Integer value) {
        this.yepb = value;
    }
    /**
     * 获取:婴儿判别
     */
    public Integer getYepb() {
        return yepb;
    }
    /**
     * 设置:药房识别
     */
    public void setYfsb(Integer value) {
        this.yfsb = value;
    }
    /**
     * 获取:药房识别
     */
    public Integer getYfsb() {
        return yfsb;
    }
    /**
     * 设置:退药病区
     */
    public void setTybq(Integer value) {
        this.tybq = value;
    }
    /**
     * 获取:退药病区
     */
    public Integer getTybq() {
        return tybq;
    }
    /**
     * 设置:操作工号
     */
    public void setCzgh(String value) {
        this.czgh = value;
    }
    /**
     * 获取:操作工号
     */
    public String getCzgh() {
        return czgh;
    }
    /**
     * 设置:自费判别
     */
    public void setZfpb(Integer value) {
        this.zfpb = value;
    }
    /**
     * 获取:自费判别
     */
    public Integer getZfpb() {
        return zfpb;
    }
    /**
     * 设置:提交标志
     */
    public void setTjbz(Integer value) {
        this.tjbz = value;
    }
    /**
     * 获取:提交标志
     */
    public Integer getTjbz() {
        return tjbz;
    }
    /**
     * 设置:医嘱ID
     */
    public void setYzid(Integer value) {
        this.yzid = value;
    }
    /**
     * 获取:医嘱ID
     */
    public Integer getYzid() {
        return yzid;
    }
    /**
     * 设置:退药类型
     */
    public void setTylx(Integer value) {
        this.tylx = value;
    }
    /**
     * 获取:退药类型
     */
    public Integer getTylx() {
        return tylx;
    }
    /**
     * 设置:退回标志
     */
    public void setThbz(Integer value) {
        this.thbz = value;
    }
    /**
     * 获取:退回标志
     */
    public Integer getThbz() {
        return thbz;
    }
    /**
     * 设置:退回时间
     */
    public void setThsj(Timestamp value) {
        this.thsj = value;
    }
    /**
     * 获取:退回时间
     */
    public Timestamp getThsj() {
        return thsj;
    }
    /**
     * 设置:退回人
     */
    public void setThr(String value) {
        this.thr = value;
    }
    /**
     * 获取:退回人
     */
    public String getThr() {
        return thr;
    }
    /**
     * 设置:记录ID
     */
    public void setJlid(Integer value) {
        this.jlid = value;
    }
    /**
     * 获取:记录ID
     */
    public Integer getJlid() {
        return jlid;
    }
    
	public String getYpmc() {
		return ypmc;
	}
	
	public void setYpmc(String ypmc) {
		this.ypmc = ypmc;
	}
	
	public String getCdmc() {
		return cdmc;
	}
	
	public void setCdmc(String cdmc) {
		this.cdmc = cdmc;
	}
    
    
}
