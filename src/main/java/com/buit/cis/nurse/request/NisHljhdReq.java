   
package com.buit.cis.nurse.request;

import java.sql.Timestamp;
import java.util.List;

import com.buit.cis.nurse.model.NisHljhdcs;
import com.buit.commons.PageQuery;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


/**
 * 类名称：NisHljhd<br> 
 * 类描述：护理计划单主表<br>
 * @author GONGFANGZHOU
 */
@ApiModel(value="护理计划单主表_Req")
public class NisHljhdReq  extends  PageQuery{
    @ApiModelProperty(value="主键记录序号")
    private Integer jlxh;
    @ApiModelProperty(value="住院号")
    private Integer zyh;
    @ApiModelProperty(value="日期")
    private Timestamp rq;
    @ApiModelProperty(value="护理问题")
    private String hlwt;
    @ApiModelProperty(value="护理目标")
    private String hlmb;
    @ApiModelProperty(value="机构id")
    private Integer jgid;
    @ApiModelProperty(value="效果评价")
    private String xgpj;
    @ApiModelProperty(value="护士签名")
    private String hsqm;
    @ApiModelProperty(value="护理措施集合")
    private List<NisHljhdcs> hlcsList ;
    /**
     * 设置:主键记录序号
     */
    public void setJlxh(Integer value) {
        this.jlxh = value;
    }
    /**
     * 获取:主键记录序号
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
     * 设置:护理问题
     */
    public void setHlwt(String value) {
        this.hlwt = value;
    }
    /**
     * 获取:护理问题
     */
    public String getHlwt() {
        return hlwt;
    }
    /**
     * 设置:护理目标
     */
    public void setHlmb(String value) {
        this.hlmb = value;
    }
    /**
     * 获取:护理目标
     */
    public String getHlmb() {
        return hlmb;
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
    /**
     * 设置:效果评价
     */
    public void setXgpj(String value) {
        this.xgpj = value;
    }
    /**
     * 获取:效果评价
     */
    public String getXgpj() {
        return xgpj;
    }
    /**
     * 设置:护士签名
     */
    public void setHsqm(String value) {
        this.hsqm = value;
    }
    /**
     * 获取:护士签名
     */
    public String getHsqm() {
        return hsqm;
    }
	public List<NisHljhdcs> getHlcsList() {
		return hlcsList;
	}
	public void setHlcsList(List<NisHljhdcs> hlcsList) {
		this.hlcsList = hlcsList;
	}
    
    
}