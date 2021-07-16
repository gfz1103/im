   
package com.buit.cis.nurse.request;

import java.sql.Timestamp;

import com.buit.commons.PageQuery;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


/**
 * 类名称：NisJbdmb<br> 
 * 类描述：护理交班单模板<br>
 * @author GONGFANGZHOU
 */
@ApiModel(value="护理交班单模板_Req")
public class NisJbdmbReq  extends  PageQuery{
    @ApiModelProperty(value="主键id")
    private Integer id;
    @ApiModelProperty(value="患者类型(系统标识字典:JBD000001)")
    private Integer hzlx;
    @ApiModelProperty(value="SBAR类型(系统标识字典:JBD000002)")
    private Integer sbarlx;
    @ApiModelProperty(value="班次类型(系统标识字典:JBD000003)")
    private Integer bclx;
    @ApiModelProperty(value="模板内容")
    private String mbnr;
    @ApiModelProperty(value="作废判别(1:是0:否)")
    private Integer zfpb;
    @ApiModelProperty(value="机构id", hidden = true)
    private Integer jgid;
    /**
     * 设置:主键id
     */
    public void setId(Integer value) {
        this.id = value;
    }
    /**
     * 获取:主键id
     */
    public Integer getId() {
        return id;
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
     * 设置:班次类型(系统标识字典:JBD000003)
     */
    public void setBclx(Integer value) {
        this.bclx = value;
    }
    /**
     * 获取:班次类型(系统标识字典:JBD000003)
     */
    public Integer getBclx() {
        return bclx;
    }
    /**
     * 设置:模板内容
     */
    public void setMbnr(String value) {
        this.mbnr = value;
    }
    /**
     * 获取:模板内容
     */
    public String getMbnr() {
        return mbnr;
    }
    /**
     * 设置:作废判别(1:是0:否)
     */
    public void setZfpb(Integer value) {
        this.zfpb = value;
    }
    /**
     * 获取:作废判别(1:是0:否)
     */
    public Integer getZfpb() {
        return zfpb;
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