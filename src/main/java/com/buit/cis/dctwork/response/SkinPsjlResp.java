   
package com.buit.cis.dctwork.response;

import com.buit.commons.PageQuery;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


/**
 * 类名称：SkinPsjl<br> 
 * 类描述：公用_病人皮试记录<br>
 * @author GONGFANGZHOU
 */
@ApiModel(value="公用_病人皮试记录_Resp")
public class SkinPsjlResp  extends  PageQuery{
    @ApiModelProperty(value="记录序号")
    private Integer jlxh;
    @ApiModelProperty(value="病人编号")
    private Integer brid;
    @ApiModelProperty(value="药品序号")
    private Integer ypxh;
    @ApiModelProperty(value="机构代码")
    private Integer jgid;
    @ApiModelProperty(value="皮试结果")
    private Integer psjg;
    @ApiModelProperty(value="皮试来源 1:门诊 2:住院 3:家床")
    private Integer psly;
    @ApiModelProperty(value="过敏症状")
    private String gmzz;
    @ApiModelProperty(value="其他症状")
    private String qtzz;
    @ApiModelProperty(value="不良反映")
    private String blfy;
    
    @ApiModelProperty(value="药品名称")
    private String ypmc;
    
    @ApiModelProperty(value="用药标识  | 1.高血压、2.糖尿病、3.精神病、4.胰岛素")
    private Integer yybs;
    
    @ApiModelProperty(value="过敏药物类别  | 1.青霉素、2.磺胺、3.链霉素")
    private Integer gmywlb;
    
    /**
     * 设置:记录序号
     */
    public void setJlxh(Integer value) {
        this.jlxh = value;
    }
    /**
     * 获取:记录序号
     */
    public Integer getJlxh() {
        return jlxh;
    }
    /**
     * 设置:病人编号
     */
    public void setBrid(Integer value) {
        this.brid = value;
    }
    /**
     * 获取:病人编号
     */
    public Integer getBrid() {
        return brid;
    }
    /**
     * 设置:药品序号
     */
    public void setYpxh(Integer value) {
        this.ypxh = value;
    }
    /**
     * 获取:药品序号
     */
    public Integer getYpxh() {
        return ypxh;
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
     * 设置:皮试结果
     */
    public void setPsjg(Integer value) {
        this.psjg = value;
    }
    /**
     * 获取:皮试结果
     */
    public Integer getPsjg() {
        return psjg;
    }
    /**
     * 设置:皮试来源 1:门诊 2:住院 3:家床
     */
    public void setPsly(Integer value) {
        this.psly = value;
    }
    /**
     * 获取:皮试来源 1:门诊 2:住院 3:家床
     */
    public Integer getPsly() {
        return psly;
    }
    /**
     * 设置:过敏症状
     */
    public void setGmzz(String value) {
        this.gmzz = value;
    }
    /**
     * 获取:过敏症状
     */
    public String getGmzz() {
        return gmzz;
    }
    /**
     * 设置:其他症状
     */
    public void setQtzz(String value) {
        this.qtzz = value;
    }
    /**
     * 获取:其他症状
     */
    public String getQtzz() {
        return qtzz;
    }
    /**
     * 设置:不良反映
     */
    public void setBlfy(String value) {
        this.blfy = value;
    }
    /**
     * 获取:不良反映
     */
    
    public String getBlfy() {
        return blfy;
    }
    
	public String getYpmc() {
		return ypmc;
	}
	
	public void setYpmc(String ypmc) {
		this.ypmc = ypmc;
	}
	
	public Integer getYybs() {
		return yybs;
	}
	
	public void setYybs(Integer yybs) {
		this.yybs = yybs;
	}
	
	public Integer getGmywlb() {
		return gmywlb;
	}
	
	public void setGmywlb(Integer gmywlb) {
		this.gmywlb = gmywlb;
	}
    
    
}
