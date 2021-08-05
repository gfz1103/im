   
package com.buit.cis.dctwork.response;

import java.sql.Timestamp;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


/**
 * 类名称：SkinPsjl<br> 
 * 类描述：公用_病人皮试记录<br>
 * @author GONGFANGZHOU
 */
@ApiModel(value="公用_病人皮试记录_DrugsResp")
public class SkinPsjlDrugsResp {
    @ApiModelProperty(value="记录序号")
    private Integer jlxh;
    @ApiModelProperty(value="病人编号")
    private Integer brid;
    @ApiModelProperty(value="药品序号")
    private Integer ypxh;
    @ApiModelProperty(value="药品名称")
    private String ypmc;
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
    @ApiModelProperty(value="录入时间")
    private Timestamp czsj;
    @ApiModelProperty(value="皮试时间")
    private Timestamp pssj;
    @ApiModelProperty(value="过敏类别")
    private Integer gmlb;
    @ApiModelProperty(value="皮试有效期(天数)")
    private Integer psyxq;
    
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
     * 获取:药品名称
     */
    public Integer getYpxh() {
        return ypxh;
    }
    /**
     * 设置:药品名称
     */
    public void setYpmc(String ypmc) {
		this.ypmc = ypmc;
	}
    /**
     * 获取:药品序号
     */
    public String getYpmc() {
		return ypmc;
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
    
	public Timestamp getCzsj() {
		return czsj;
	}
	
	public void setCzsj(Timestamp czsj) {
		this.czsj = czsj;
	}
	
	public Timestamp getPssj() {
		return pssj;
	}
	public void setPssj(Timestamp pssj) {
		this.pssj = pssj;
	}
	public Integer getGmlb() {
		return gmlb;
	}
	
	public void setGmlb(Integer gmlb) {
		this.gmlb = gmlb;
	}
	
	public Integer getPsyxq() {
		return psyxq;
	}
	
	public void setPsyxq(Integer psyxq) {
		this.psyxq = psyxq;
	}
    
}
