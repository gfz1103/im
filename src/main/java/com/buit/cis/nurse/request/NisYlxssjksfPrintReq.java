   
package com.buit.cis.nurse.request;

import com.buit.commons.PageQuery;
import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


/**
 * 类名称：NisYlxssjksf<br> 
 * 类描述：压力性损伤预报、传报单(监控随访)<br>
 * @author GONGFANGZHOU
 */
@ApiModel(value="压力性损伤预报、传报单(监控随访)_PrintReq")
public class NisYlxssjksfPrintReq  extends  PageQuery{
    /**
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
	*/
    @ApiModelProperty(value="Braden评分")
    private String pf;
    @ApiModelProperty(value="预报≤12分")
    private String yb;
    @ApiModelProperty(value="院内发生")
    private String ynfs;
    @ApiModelProperty(value="院外带入")
    private String ywdr;
    @ApiModelProperty(value="部位")
    private String bw;
    @ApiModelProperty(value="大小")
    private String dx;
    @ApiModelProperty(value="伤口分期")
    private String skfq;
    @ApiModelProperty(value="基部颜色")
    private String jbys;
    @ApiModelProperty(value="渗出")
    private String sc;
    @ApiModelProperty(value="疮面处理")
    private String cmcl;
    @ApiModelProperty(value="护理措施")
    private String hlcs;
    @ApiModelProperty(value="护士长签名")
    private String hszqm;
    @ApiModelProperty(value="护理部督导")
    private String hlbdd;
    @ApiModelProperty(value="日期")
    private String rq;
    @JsonIgnore
    @ApiModelProperty(value="机构id")
    private Integer jgid;
    /**
     * 设置:Braden评分
     */
    public void setPf(String value) {
        this.pf = value;
    }
    /**
     * 获取:Braden评分
     */
    public String getPf() {
        return pf;
    }
    /**
     * 设置:预报≤12分
     */
    public void setYb(String value) {
        this.yb = value;
    }
    /**
     * 获取:预报≤12分
     */
    public String getYb() {
        return yb;
    }
    /**
     * 设置:院内发生
     */
    public void setYnfs(String value) {
        this.ynfs = value;
    }
    /**
     * 获取:院内发生
     */
    public String getYnfs() {
        return ynfs;
    }
    /**
     * 设置:院外带入
     */
    public void setYwdr(String value) {
        this.ywdr = value;
    }
    /**
     * 获取:院外带入
     */
    public String getYwdr() {
        return ywdr;
    }
    /**
     * 设置:部位
     */
    public void setBw(String value) {
        this.bw = value;
    }
    /**
     * 获取:部位
     */
    public String getBw() {
        return bw;
    }
    /**
     * 设置:大小
     */
    public void setDx(String value) {
        this.dx = value;
    }
    /**
     * 获取:大小
     */
    public String getDx() {
        return dx;
    }
    /**
     * 设置:伤口分期
     */
    public void setSkfq(String value) {
        this.skfq = value;
    }
    /**
     * 获取:伤口分期
     */
    public String getSkfq() {
        return skfq;
    }
    /**
     * 设置:基部颜色
     */
    public void setJbys(String value) {
        this.jbys = value;
    }
    /**
     * 获取:基部颜色
     */
    public String getJbys() {
        return jbys;
    }
    /**
     * 设置:渗出
     */
    public void setSc(String value) {
        this.sc = value;
    }
    /**
     * 获取:渗出
     */
    public String getSc() {
        return sc;
    }
    /**
     * 设置:疮面处理
     */
    public void setCmcl(String value) {
        this.cmcl = value;
    }
    /**
     * 获取:疮面处理
     */
    public String getCmcl() {
        return cmcl;
    }
    /**
     * 设置:护理措施
     */
    public void setHlcs(String value) {
        this.hlcs = value;
    }
    /**
     * 获取:护理措施
     */
    public String getHlcs() {
        return hlcs;
    }
    /**
     * 设置:护士长签名
     */
    public void setHszqm(String value) {
        this.hszqm = value;
    }
    /**
     * 获取:护士长签名
     */
    public String getHszqm() {
        return hszqm;
    }
    /**
     * 设置:护理部督导
     */
    public void setHlbdd(String value) {
        this.hlbdd = value;
    }
    /**
     * 获取:护理部督导
     */
    public String getHlbdd() {
        return hlbdd;
    }
    /**
     * 设置:日期
     */
    public void setRq(String rq) {
		this.rq = rq;
	}
    /**
     * 获取:日期
     */
    public String getRq() {
		return rq;
	}

    /**
     * 设置:机构id
     */
    public void setJgid(Integer value) {
        this.jgid = value;
    }	
	/**
     * 获取:机构id
     */
    public Integer getJgid() {
        return jgid;
    }
	
}