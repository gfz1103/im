   
package com.buit.cis.ims.request;

import com.buit.commons.PageQuery;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


/**
 * 类名称：ImCwzh<br> 
 * 类描述：<br>
 * @author GONGFANGZHOU
 */
@ApiModel(value="床位组号_Req")
public class ImCwzhReq  extends  PageQuery{
    @ApiModelProperty(value="床位组号(主键)")
    private Integer cwzh;
    @ApiModelProperty(value="床位组号名称")
    private String cwzhmc;
    @ApiModelProperty(value="作废判别(1:是0:否)")
    private Integer zfpb;
    @ApiModelProperty(value="拼音代码")
    private String pydm;
    @ApiModelProperty(value="机构id")
    private Integer jgid;
    @ApiModelProperty(value="病区代码")
    private Integer bqdm;
    /**
     * 设置:床位组号(主键)
     */
    public void setCwzh(Integer value) {
        this.cwzh = value;
    }
    /**
     * 获取:床位组号(主键)
     */
    public Integer getCwzh() {
        return cwzh;
    }
    /**
     * 设置:床位组号名称
     */
    public void setCwzhmc(String value) {
        this.cwzhmc = value;
    }
    /**
     * 获取:床位组号名称
     */
    public String getCwzhmc() {
        return cwzhmc;
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
     * 设置:拼音代码
     */
    public void setPydm(String value) {
        this.pydm = value;
    }
    /**
     * 获取:拼音代码
     */
    public String getPydm() {
        return pydm;
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
	public Integer getBqdm() {
		return bqdm;
	}
	public void setBqdm(Integer bqdm) {
		this.bqdm = bqdm;
	}
    
}