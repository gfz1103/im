   
package com.buit.cis.nurse.response;

import java.sql.Timestamp;

import com.buit.commons.PageQuery;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


/**
 * 类名称：NisHljbdsjb<br> 
 * 类描述：护理交班单记录数据表<br>
 * @author GONGFANGZHOU
 */
@ApiModel(value="护理交班单记录数据表_Resp")
public class NisHljbdsjbResp {
    @ApiModelProperty(value="出院数")
    private Integer cys;
    @ApiModelProperty(value="入院数")
    private Integer rys;
    @ApiModelProperty(value="预手术数")
    private Integer yssy;
    @ApiModelProperty(value="转出数")
    private Integer zcs;
    @ApiModelProperty(value="转入数")
    private Integer zrs;
    @ApiModelProperty(value="病危病重数")
    private Integer bwbzs;
    @ApiModelProperty(value="死亡数")
    private Integer sws;
    @ApiModelProperty(value="手术数")
    private Integer sss;
    @ApiModelProperty(value="一级护理数")
    private Integer yjhls;

    
    /**
     * 设置:出院数
     */
    public void setCys(Integer value) {
        this.cys = value;
    }
    /**
     * 获取:出院数
     */
    public Integer getCys() {
        return cys;
    }
    /**
     * 设置:入院数
     */
    public void setRys(Integer value) {
        this.rys = value;
    }
    /**
     * 获取:入院数
     */
    public Integer getRys() {
        return rys;
    }
    /**
     * 设置:预手术数
     */
    public void setYssy(Integer value) {
        this.yssy = value;
    }
    /**
     * 获取:预手术数
     */
    public Integer getYssy() {
        return yssy;
    }
    /**
     * 设置:转出数
     */
    public void setZcs(Integer value) {
        this.zcs = value;
    }
    /**
     * 获取:转出数
     */
    public Integer getZcs() {
        return zcs;
    }
    /**
     * 设置:转入数
     */
    public void setZrs(Integer value) {
        this.zrs = value;
    }
    /**
     * 获取:转入数
     */
    public Integer getZrs() {
        return zrs;
    }
    /**
     * 设置:病危病重数
     */
    public void setBwbzs(Integer value) {
        this.bwbzs = value;
    }
    /**
     * 获取:病危病重数
     */
    public Integer getBwbzs() {
        return bwbzs;
    }
    /**
     * 设置:死亡数
     */
    public void setSws(Integer value) {
        this.sws = value;
    }
    /**
     * 获取:死亡数
     */
    public Integer getSws() {
        return sws;
    }
    /**
     * 设置:手术数
     */
    public void setSss(Integer value) {
        this.sss = value;
    }
    /**
     * 获取:手术数
     */
    public Integer getSss() {
        return sss;
    }
    /**
     * 设置:一级护理数
     */
    public void setYjhls(Integer value) {
        this.yjhls = value;
    }
    /**
     * 获取:一级护理数
     */
    public Integer getYjhls() {
        return yjhls;
    }
   
}