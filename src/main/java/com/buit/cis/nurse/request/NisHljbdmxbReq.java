   
package com.buit.cis.nurse.request;

import com.buit.commons.PageQuery;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


/**
 * 类名称：NisHljbdmxb<br> 
 * 类描述：护理交班单明细表<br>
 * @author GONGFANGZHOU
 */
@ApiModel(value="护理交班单明细表_Req")
public class NisHljbdmxbReq  extends  PageQuery{
    @ApiModelProperty(value="记录序号(主键id)")
    private Integer jlxh;
    @ApiModelProperty(value="交班单记录序号(nis_hljbd主键)")
    private Integer jbdjlxh;
    @ApiModelProperty(value="住院号")
    private Integer zyh;
    @ApiModelProperty(value="诊断名称(入院主诊断)")
    private String zdmc;
    @ApiModelProperty(value="患者类型(系统标识字典:JBD000001)")
    private Integer hzlx;
    @ApiModelProperty(value="SBAR类型(系统标识字典:JBD000002)")
    private Integer sbarlx;
    @ApiModelProperty(value="交班内容")
    private String jbnr;
    @ApiModelProperty(value="机构id")
    private Integer jgid;
    /**
     * 设置:记录序号(主键id)
     */
    public void setJlxh(Integer value) {
        this.jlxh = value;
    }
    /**
     * 获取:记录序号(主键id)
     */
    public Integer getJlxh() {
        return jlxh;
    }
    /**
     * 设置:交班单记录序号(nis_hljbd主键)
     */
    public void setJbdjlxh(Integer value) {
        this.jbdjlxh = value;
    }
    /**
     * 获取:交班单记录序号(nis_hljbd主键)
     */
    public Integer getJbdjlxh() {
        return jbdjlxh;
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
     * 设置:诊断名称(入院主诊断)
     */
    public void setZdmc(String value) {
        this.zdmc = value;
    }
    /**
     * 获取:诊断名称(入院主诊断)
     */
    public String getZdmc() {
        return zdmc;
    }
    /**
     * 设置:患者类型(系统标识字典:JBD000001)
     */
    public void setHzlx(Integer value) {
        this.hzlx = value;
    }
    /**
     * 获取:患者类型(系统标识字典:JBD000001)
     */
    public Integer getHzlx() {
        return hzlx;
    }
    /**
     * 设置:SBAR类型(系统标识字典:JBD000002)
     */
    public void setSbarlx(Integer value) {
        this.sbarlx = value;
    }
    /**
     * 获取:SBAR类型(系统标识字典:JBD000002)
     */
    public Integer getSbarlx() {
        return sbarlx;
    }
    /**
     * 设置:交班内容
     */
    public void setJbnr(String value) {
        this.jbnr = value;
    }
    /**
     * 获取:交班内容
     */
    public String getJbnr() {
        return jbnr;
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