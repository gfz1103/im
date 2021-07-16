   
package com.buit.cis.nurse.request;

import java.sql.Timestamp;

import javax.validation.constraints.NotNull;

import com.buit.commons.PageQuery;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


/**
 * 类名称：NisHljbd<br> 
 * 类描述：护理交班单主表<br>
 * @author GONGFANGZHOU
 */
@ApiModel(value="护理交班单主表_QueryReq")
public class NisHljbdQueryReq  extends  PageQuery{
	@ApiModelProperty(value="开始时间")
    private String kssj;
	@ApiModelProperty(value="结束时间")
    private String jssj;
    @ApiModelProperty(value="班次类型(系统标识字典:JBD000003)")
    private Integer bclx;
    @ApiModelProperty(value="护理组号")
    private Integer hlzh;
    @NotNull(message = "病区代码不能为空")
    @ApiModelProperty(value="病区代码")
    private Integer bqdm;
    @ApiModelProperty(value="机构id", hidden = true)
    private Integer jgid;
   
    public String getKssj() {
		return kssj;
	}
	public void setKssj(String kssj) {
		this.kssj = kssj;
	}
	public String getJssj() {
		return jssj;
	}
	public void setJssj(String jssj) {
		this.jssj = jssj;
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
     * 设置:护理组号
     */
    public void setHlzh(Integer value) {
        this.hlzh = value;
    }
    /**
     * 获取:护理组号
     */
    public Integer getHlzh() {
        return hlzh;
    }
    /**
     * 设置:病区代码
     */
    public void setBqdm(Integer value) {
        this.bqdm = value;
    }
    /**
     * 获取:病区代码
     */
    public Integer getBqdm() {
        return bqdm;
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