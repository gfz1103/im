package com.buit.cis.nurse.response;

import java.io.Serializable;

import com.buit.commons.PageQuery;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 类名称：NisYlxsszg<br>
 * 类描述：压力性损伤预报、传报单(转归情况)
 *
 * @author GONGFANGZHOU
 */
@ApiModel(value="压力性损伤预报、传报单(转归情况)_Resp")
public class NisYlxsszgResp extends PageQuery implements Serializable {
    @ApiModelProperty(value="主键")
    /**
     * 主键
     */
    private Integer jlxh;
    @ApiModelProperty(value="主表记录序号")
    /**
     * 主表记录序号
     */
    private Integer cbdjlxh;
    @ApiModelProperty(value="未发生")
    /**
     * 未发生
     */
    private String wfs;
    @ApiModelProperty(value="发生")
    /**
     * 发生
     */
    private String fs;
    @ApiModelProperty(value="疾病转归")
    /**
     * 疾病转归
     */
    private String jbzg;
    @ApiModelProperty(value="压力性损伤转归")
    /**
     * 压力性损伤转归
     */
    private String ylxsszg;
    @ApiModelProperty(value="签名")
    /**
     * 签名
     */
    private String qm;
    @ApiModelProperty(value="日期")
    /**
     * 日期
     */
    private String rq;
    @ApiModelProperty(value="机构id")
    /**
     * 机构id
     */
    private Integer jgid;

    /**
     * 设置:主键
     */
    public void setJlxh(Integer value) {
        this.jlxh = value;
    }

    /**
     * 获取:主键
     */
    public Integer getJlxh() {
        return jlxh;
    }

    /**
     * 设置:主表记录序号
     */
    public void setCbdjlxh(Integer value) {
        this.cbdjlxh = value;
    }

    /**
     * 获取:主表记录序号
     */
    public Integer getCbdjlxh() {
        return cbdjlxh;
    }

    /**
     * 设置:未发生
     */
    public void setWfs(String value) {
        this.wfs = value;
    }

    /**
     * 获取:未发生
     */
    public String getWfs() {
        return wfs;
    }

    /**
     * 设置:发生
     */
    public void setFs(String value) {
        this.fs = value;
    }

    /**
     * 获取:发生
     */
    public String getFs() {
        return fs;
    }

    /**
     * 设置:疾病转归
     */
    public void setJbzg(String value) {
        this.jbzg = value;
    }

    /**
     * 获取:疾病转归
     */
    public String getJbzg() {
        return jbzg;
    }

    /**
     * 设置:压力性损伤转归
     */
    public void setYlxsszg(String value) {
        this.ylxsszg = value;
    }

    /**
     * 获取:压力性损伤转归
     */
    public String getYlxsszg() {
        return ylxsszg;
    }

    /**
     * 设置:签名
     */
    public void setQm(String value) {
        this.qm = value;
    }

    /**
     * 获取:签名
     */
    public String getQm() {
        return qm;
    }

    /**
     * 设置:日期
     */
    public void setRq(String value) {
        this.rq = value;
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