package com.buit.cis.dctwork.response;

import com.buit.commons.PageQuery;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


/**
 * 类名称：NisFyyf<br>
 * 类描述：病区_发药药房<br>
 * @author LAPTOP-6GUR25CC
 */
@ApiModel(value="病区_发药药房")
public class NisFyyfResp  extends PageQuery {
    @ApiModelProperty(value="记录序号")
    private Integer jlxh;
    @ApiModelProperty(value="机构代码")
    private Integer jgid;
    @ApiModelProperty(value="病区代码")
    private String bqdm;
    @ApiModelProperty(value="功能分类")
    private Integer gnfl;
    @ApiModelProperty(value="医嘱类型")
    private Integer type;
    @ApiModelProperty(value="药房识别")
    private String yfsb;
    @ApiModelProperty(value="代码识别")
    private String dmsb;
    @ApiModelProperty(value="注销判别")
    private Boolean zxpb;
    
    @ApiModelProperty(value="药房名称")
    private String yfmc;

    public Integer getJlxh() {
        return jlxh;
    }

    public void setJlxh(Integer jlxh) {
        this.jlxh = jlxh;
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
     * 设置:病区代码
     */
    public void setBqdm(String value) {
        this.bqdm = value;
    }
    /**
     * 获取:病区代码
     */
    public String getBqdm() {
        return bqdm;
    }
    /**
     * 设置:功能分类
     */
    public void setGnfl(Integer value) {
        this.gnfl = value;
    }
    /**
     * 获取:功能分类
     */
    public Integer getGnfl() {
        return gnfl;
    }
    /**
     * 设置:医嘱类型
     */
    public void setType(Integer value) {
        this.type = value;
    }
    /**
     * 获取:医嘱类型
     */
    public Integer getType() {
        return type;
    }
    /**
     * 设置:药房识别
     */
    public void setYfsb(String value) {
        this.yfsb = value;
    }
    /**
     * 获取:药房识别
     */
    public String getYfsb() {
        return yfsb;
    }
    /**
     * 设置:代码识别
     */
    public void setDmsb(String value) {
        this.dmsb = value;
    }
    /**
     * 获取:代码识别
     */
    public String getDmsb() {
        return dmsb;
    }
    /**
     * 设置:注销判别
     */
    public void setZxpb(Boolean value) {
        this.zxpb = value;
    }
    /**
     * 获取:注销判别
     */
    public Boolean getZxpb() {
        return zxpb;
    }

	public String getYfmc() {
		return yfmc;
	}

	public void setYfmc(String yfmc) {
		this.yfmc = yfmc;
	}
    
    
}
