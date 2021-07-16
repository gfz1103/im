package com.buit.cis.ims.response;

import com.buit.commons.PageQuery;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.sql.Timestamp;


/**
 * 类名称：ImRyzd<br> 
 * 类描述：住院_入院诊断<br>
 * @author ZHOUHAISHENG
 */
@ApiModel(value="住院_入院诊断")
public class ImRyzdResp  extends PageQuery {
    @ApiModelProperty(value="住院号")
    private Integer zyh;
    @ApiModelProperty(value="诊断序号 | 对应GY_JBZD的代码")
    private Integer zdxh;
    @ApiModelProperty(value="诊断类别 (字典:sys_flag_data:ZD000001) | 与GY_DMZD（DMLB= 22）对应 1：门诊诊断 2：入院诊断 3：出院主诊断 4：出院次诊断 5：院内感染")
    private Integer zdlb;
    @ApiModelProperty(value="机构代码")
    private Integer jgid;
    @ApiModelProperty(value="转归情况 (字典:sys_flag_data:JG000001) | 与GY_DMZD（DMLB= 23）对应 1：治愈 2：好转 3：未愈 4：死亡 5：其他")
    private Integer zgqk;
    @ApiModelProperty(value="提醒标志")
    private Integer txbz;
    @ApiModelProperty(value="scbz")
    private String scbz;
    @ApiModelProperty(value="诊断日期")
    private Timestamp zdrq;
    @ApiModelProperty(value="入院病情代码(字典:sys_flag_data:RY000001):1.有明确诊断 2.临床未确定 3.情况不明 4.无法确定")
    private Integer rybqdm;
    @ApiModelProperty(value="中西类别(字典:sys_flag_data:ZX000001):1.西医 2.中医")
    private Integer zxlb;
    
    @ApiModelProperty(value="诊断名称")
    private String jbmc;
    
    @ApiModelProperty(value="ICD10 ")
    private String icd10;
    
    @ApiModelProperty(value="ICD9 | ICD9，ICD码")
    private String icd9;

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
     * 设置:诊断序号 | 对应GY_JBZD的代码
     */
    public void setZdxh(Integer value) {
        this.zdxh = value;
    }
    /**
     * 获取:诊断序号 | 对应GY_JBZD的代码
     */
    public Integer getZdxh() {
        return zdxh;
    }
    /**
     * 设置:诊断类别 | 与GY_DMZD（DMLB= 22）对应 1：门诊诊断 2：入院诊断 3：出院主诊断 4：出院次诊断 5：院内感染
     */
    public void setZdlb(Integer value) {
        this.zdlb = value;
    }
    /**
     * 获取:诊断类别 | 与GY_DMZD（DMLB= 22）对应 1：门诊诊断 2：入院诊断 3：出院主诊断 4：出院次诊断 5：院内感染
     */
    public Integer getZdlb() {
        return zdlb;
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
     * 设置:转归情况 | 与GY_DMZD（DMLB= 23）对应 1：治愈 2：好转 3：未愈 4：死亡 5：其他
     */
    public void setZgqk(Integer value) {
        this.zgqk = value;
    }
    /**
     * 获取:转归情况 | 与GY_DMZD（DMLB= 23）对应 1：治愈 2：好转 3：未愈 4：死亡 5：其他
     */
    public Integer getZgqk() {
        return zgqk;
    }
    /**
     * 设置:提醒标志
     */
    public void setTxbz(Integer value) {
        this.txbz = value;
    }
    /**
     * 获取:提醒标志
     */
    public Integer getTxbz() {
        return txbz;
    }
    /**
     * 设置:scbz
     */
    public void setScbz(String value) {
        this.scbz = value;
    }
    /**
     * 获取:scbz
     */
    public String getScbz() {
        return scbz;
    }
    /**
     * 设置:zdrq
     */
    public void setZdrq(Timestamp value) {
        this.zdrq = value;
    }
    /**
     * 获取:zdrq
     */
    public Timestamp getZdrq() {
        return zdrq;
    }
    /**
     * 设置:入院病情代码：1.有明确诊断 2.临床未确定 3.情况不明 4.无法确定
     */
    public void setRybqdm(Integer value) {
        this.rybqdm = value;
    }
    /**
     * 获取:入院病情代码：1.有明确诊断 2.临床未确定 3.情况不明 4.无法确定
     */
    public Integer getRybqdm() {
        return rybqdm;
    }
    /**
     * 设置:中西类别：1.西医 2.中医
     */
    public void setZxlb(Integer value) {
        this.zxlb = value;
    }
    /**
     * 获取:中西类别：1.西医 2.中医
     */
    public Integer getZxlb() {
        return zxlb;
    }
    
	public String getJbmc() {
		return jbmc;
	}
	
	public void setJbmc(String jbmc) {
		this.jbmc = jbmc;
	}
	
	public String getIcd10() {
		return icd10;
	}
	
	public void setIcd10(String icd10) {
		this.icd10 = icd10;
	}
	
	public String getIcd9() {
		return icd9;
	}
	
	public void setIcd9(String icd9) {
		this.icd9 = icd9;
	}
    
}
