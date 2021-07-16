   
package com.buit.cis.nurse.request;

import java.util.List;

import com.buit.commons.PageQuery;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


/**
 * 类名称：NisZdyxm<br> 
 * 类描述：护理记录单自定义项目<br>
 * @author GONGFANGZHOU
 */
@ApiModel(value="护理记录单自定义项目_Req")
public class NisZdyxmReq  extends  PageQuery{
    @ApiModelProperty(value="项目代码(主键)")
    private Integer xmdm;
    @ApiModelProperty(value="项目名称")
    private String xmmc;
    @ApiModelProperty(value="作废判别")
    private Integer zfpb;
    @ApiModelProperty(value="拼音代码")
    private String pydm;
    @ApiModelProperty(value="机构id", hidden = true)
    private Integer jgid;
    @ApiModelProperty(value="自定义项目内容集合")
    private List<NisZdyxmnrReq> zdyxmnrReqList;
    
    /**
     * 设置:项目代码(主键)
     */
    public void setXmdm(Integer value) {
        this.xmdm = value;
    }
    /**
     * 获取:项目代码(主键)
     */
    public Integer getXmdm() {
        return xmdm;
    }
    /**
     * 设置:项目名称
     */
    public void setXmmc(String value) {
        this.xmmc = value;
    }
    /**
     * 获取:项目名称
     */
    public String getXmmc() {
        return xmmc;
    }
    /**
     * 设置:作废判别
     */
    public void setZfpb(Integer value) {
        this.zfpb = value;
    }
    /**
     * 获取:作废判别
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
    
	public List<NisZdyxmnrReq> getZdyxmnrReqList() {
		return zdyxmnrReqList;
	}
	
	public void setZdyxmnrReqList(List<NisZdyxmnrReq> zdyxmnrReqList) {
		this.zdyxmnrReqList = zdyxmnrReqList;
	}
    
    
}