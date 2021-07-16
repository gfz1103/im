   
package com.buit.cis.nurse.response;

import com.buit.commons.PageQuery;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


/**
 * 类名称：NisHljldzdy<br> 
 * 类描述：护理记录单自定义内容<br>
 * @author GONGFANGZHOU
 */
@ApiModel(value="护理记录单自定义内容_Resp")
public class NisHljldzdyResp  extends  PageQuery{
    @ApiModelProperty(value="自定义id")
    private Integer zdyid;
    @ApiModelProperty(value="住院号")
    private Integer zyh;
    @ApiModelProperty(value="自定义项目代码")
    private Integer xmdm;
    @ApiModelProperty(value="自定义名称")
    private String zdymc;
    @ApiModelProperty(value="自定义内容")
    private String zdynr;
    @ApiModelProperty(value="护理记录单JLXH")
    private Integer jlxh;
    @ApiModelProperty(value="机构id")
    private Integer jgid;
    /**
     * 设置:自定义id
     */
    public void setZdyid(Integer value) {
        this.zdyid = value;
    }
    /**
     * 获取:自定义id
     */
    public Integer getZdyid() {
        return zdyid;
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
     * 设置:自定义项目代码
     */
    public void setXmdm(Integer value) {
        this.xmdm = value;
    }
    /**
     * 获取:自定义项目代码
     */
    public Integer getXmdm() {
        return xmdm;
    }
    /**
     * 设置:自定义名称
     */
    public void setZdymc(String value) {
        this.zdymc = value;
    }
    /**
     * 获取:自定义名称
     */
    public String getZdymc() {
        return zdymc;
    }
    /**
     * 设置:自定义内容
     */
    public void setZdynr(String value) {
        this.zdynr = value;
    }
    /**
     * 获取:自定义内容
     */
    public String getZdynr() {
        return zdynr;
    }
    /**
     * 设置:护理记录单JLXH
     */
    public void setJlxh(Integer value) {
        this.jlxh = value;
    }
    /**
     * 获取:护理记录单JLXH
     */
    public Integer getJlxh() {
        return jlxh;
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