   
package com.buit.cis.nurse.request;

import java.sql.Timestamp;

import com.buit.commons.PageQuery;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


/**
 * 类名称：NisYlxsszg<br> 
 * 类描述：压力性损伤预报、传报单(转归情况)<br>
 * @author GONGFANGZHOU
 */
@ApiModel(value="压力性损伤预报、传报单(转归情况)_Req")
public class NisYlxsszgReq  extends  PageQuery{
    /**
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
	*/
	private static final long serialVersionUID = 1L;
	@ApiModelProperty(value="主键")
    private Integer jlxh;
    @ApiModelProperty(value="主表记录序号")
    private Integer cbdjlxh;
    @ApiModelProperty(value="未发生")
    private String wfs;
    @ApiModelProperty(value="发生")
    private String fs;
    @ApiModelProperty(value="疾病转归")
    private String jbzg;
    @ApiModelProperty(value="压力性损伤转归")
    private String ylxsszg;
    @ApiModelProperty(value="签名")
    private String qm;
    @ApiModelProperty(value="日期")
    private Timestamp rq;
    @ApiModelProperty(value="机构id")
    private Integer jgid;
    @ApiModelProperty(value="数据状态")
    private String opStatus;
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
    public void setRq(Timestamp value) {
        this.rq = value;
    }
    /**
     * 获取:日期
     */
    public Timestamp getRq() {
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
	public String getOpStatus() {
		return opStatus;
	}
	public void setOpStatus(String opStatus) {
		this.opStatus = opStatus;
	}
    
}