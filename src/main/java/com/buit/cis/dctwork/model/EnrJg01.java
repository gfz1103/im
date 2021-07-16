package com.buit.cis.dctwork.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import com.buit.commons.PageQuery;

/**
 * 类名称：EnrJg01<br> 
 * 类描述：
 * @author GONGFANGZHOU 
 * @ApiModel(value="")
 */
public class EnrJg01  extends  PageQuery{
	//@ApiModelProperty(value="类型编号")
    /** 类型编号 */
    private Integer jgbh;
	//@ApiModelProperty(value="病历名称")
    /** 病历名称 */
    private String jgmc;
	//@ApiModelProperty(value="病历类别:EMR_KBM_BLLB关联,展现病历夹,控制续页。")
    /** 病历类别:EMR_KBM_BLLB关联,展现病历夹,控制续页。 */
    private Integer bllb;
	//@ApiModelProperty(value="模板类别:EMR_KBM_BLLB关联,控制医疗权限。")
    /** 模板类别:EMR_KBM_BLLB关联,控制医疗权限。 */
    private Integer mblb;
	//@ApiModelProperty(value="所属科室:多科室逗号分隔 0表示全院")
    /** 所属科室:多科室逗号分隔 0表示全院 */
    private String ssks;
	//@ApiModelProperty(value="排序次序")
    /** 排序次序 */
    private Integer plcx;
	//@ApiModelProperty(value="拼音代码")
    /** 拼音代码 */
    private String pydm;
	//@ApiModelProperty(value="五笔代码")
    /** 五笔代码 */
    private String wbdm;
	//@ApiModelProperty(value="关联模板:护理病历填写关联模板，护理记录填写关联数据窗口名")
    /** 关联模板:护理病历填写关联模板，护理记录填写关联数据窗口名 */
    private String glmb;
	//@ApiModelProperty(value="纸张宽度(mm)")
    /** 纸张宽度(mm) */
    private Integer zzkd;
	//@ApiModelProperty(value="纸张宽度(mm)")
    /** 纸张宽度(mm) */
    private Integer zzgd;
	//@ApiModelProperty(value="每页行数:用户输入，不能高于理论行数")
    /** 每页行数:用户输入，不能高于理论行数 */
    private Integer myhs;
	//@ApiModelProperty(value="注销标志:0使用 1注销DICT103")
    /** 注销标志:0使用 1注销DICT103 */
    private Integer zxbz;
	//@ApiModelProperty(value="备注信息")
    /** 备注信息 */
    private String bzxx;
	//@ApiModelProperty(value="独立换行项目")
    /** 独立换行项目 */
    private Integer dlhhxm;
	//@ApiModelProperty(value="独立页码")
    /** 独立页码 */
    private Integer dlym;
	//@ApiModelProperty(value="纸张方向")
    /** 纸张方向 */
    private Integer zzfx;
	//@ApiModelProperty(value="纸张大小")
    /** 纸张大小 */
    private Integer zzdx;
	//@ApiModelProperty(value="tbtz")
    /** tbtz */
    private BigDecimal tbtz;

    /** 设置:类型编号  */
    public void setJgbh(Integer value) {
        this.jgbh = value;
    }
    /** 获取:类型编号 */
    public Integer getJgbh() {
        return jgbh;
    }

    /** 设置:病历名称  */
    public void setJgmc(String value) {
        this.jgmc = value;
    }
    /** 获取:病历名称 */
    public String getJgmc() {
        return jgmc;
    }

    /** 设置:病历类别:EMR_KBM_BLLB关联,展现病历夹,控制续页。  */
    public void setBllb(Integer value) {
        this.bllb = value;
    }
    /** 获取:病历类别:EMR_KBM_BLLB关联,展现病历夹,控制续页。 */
    public Integer getBllb() {
        return bllb;
    }

    /** 设置:模板类别:EMR_KBM_BLLB关联,控制医疗权限。  */
    public void setMblb(Integer value) {
        this.mblb = value;
    }
    /** 获取:模板类别:EMR_KBM_BLLB关联,控制医疗权限。 */
    public Integer getMblb() {
        return mblb;
    }

    /** 设置:所属科室:多科室逗号分隔 0表示全院  */
    public void setSsks(String value) {
        this.ssks = value;
    }
    /** 获取:所属科室:多科室逗号分隔 0表示全院 */
    public String getSsks() {
        return ssks;
    }

    /** 设置:排序次序  */
    public void setPlcx(Integer value) {
        this.plcx = value;
    }
    /** 获取:排序次序 */
    public Integer getPlcx() {
        return plcx;
    }

    /** 设置:拼音代码  */
    public void setPydm(String value) {
        this.pydm = value;
    }
    /** 获取:拼音代码 */
    public String getPydm() {
        return pydm;
    }

    /** 设置:五笔代码  */
    public void setWbdm(String value) {
        this.wbdm = value;
    }
    /** 获取:五笔代码 */
    public String getWbdm() {
        return wbdm;
    }

    /** 设置:关联模板:护理病历填写关联模板，护理记录填写关联数据窗口名  */
    public void setGlmb(String value) {
        this.glmb = value;
    }
    /** 获取:关联模板:护理病历填写关联模板，护理记录填写关联数据窗口名 */
    public String getGlmb() {
        return glmb;
    }

    /** 设置:纸张宽度(mm)  */
    public void setZzkd(Integer value) {
        this.zzkd = value;
    }
    /** 获取:纸张宽度(mm) */
    public Integer getZzkd() {
        return zzkd;
    }

    /** 设置:纸张宽度(mm)  */
    public void setZzgd(Integer value) {
        this.zzgd = value;
    }
    /** 获取:纸张宽度(mm) */
    public Integer getZzgd() {
        return zzgd;
    }

    /** 设置:每页行数:用户输入，不能高于理论行数  */
    public void setMyhs(Integer value) {
        this.myhs = value;
    }
    /** 获取:每页行数:用户输入，不能高于理论行数 */
    public Integer getMyhs() {
        return myhs;
    }

    /** 设置:注销标志:0使用 1注销DICT103  */
    public void setZxbz(Integer value) {
        this.zxbz = value;
    }
    /** 获取:注销标志:0使用 1注销DICT103 */
    public Integer getZxbz() {
        return zxbz;
    }

    /** 设置:备注信息  */
    public void setBzxx(String value) {
        this.bzxx = value;
    }
    /** 获取:备注信息 */
    public String getBzxx() {
        return bzxx;
    }

    /** 设置:独立换行项目  */
    public void setDlhhxm(Integer value) {
        this.dlhhxm = value;
    }
    /** 获取:独立换行项目 */
    public Integer getDlhhxm() {
        return dlhhxm;
    }

    /** 设置:独立页码  */
    public void setDlym(Integer value) {
        this.dlym = value;
    }
    /** 获取:独立页码 */
    public Integer getDlym() {
        return dlym;
    }

    /** 设置:纸张方向  */
    public void setZzfx(Integer value) {
        this.zzfx = value;
    }
    /** 获取:纸张方向 */
    public Integer getZzfx() {
        return zzfx;
    }

    /** 设置:纸张大小  */
    public void setZzdx(Integer value) {
        this.zzdx = value;
    }
    /** 获取:纸张大小 */
    public Integer getZzdx() {
        return zzdx;
    }

    /** 设置:tbtz  */
    public void setTbtz(BigDecimal value) {
        this.tbtz = value;
    }
    /** 获取:tbtz */
    public BigDecimal getTbtz() {
        return tbtz;
    }


}