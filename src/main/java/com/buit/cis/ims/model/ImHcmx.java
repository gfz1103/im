package com.buit.cis.ims.model;

import com.buit.commons.PageQuery;

import java.sql.Timestamp;

/**
 * 类名称：ImHcmx<br> 
 * 类描述：住院_换床明细
 * @author ZHOUHAISHENG 
 * @ApiModel(value="住院_换床明细")
 */
public class ImHcmx  extends PageQuery {
	//@ApiModelProperty(value="住院号")
    /** 住院号 */
    private Integer zyh;
	//@ApiModelProperty(value="换床日期")
    /** 换床日期 */
    private Timestamp hcrq;
	//@ApiModelProperty(value="机构代码")
    /** 机构代码 */
    private Integer jgid;
	//@ApiModelProperty(value="终止日期")
    /** 终止日期 */
    private Timestamp zzrq;
	//@ApiModelProperty(value="换床类型 | 0.分床1.换床 2.换科 3.借床 4.包床 5.退床 6.包房 7.退房")
    /** 换床类型 | 0.分床1.换床 2.换科 3.借床 4.包床 5.退床 6.包房 7.退房 */
    private Integer hclx;
	//@ApiModelProperty(value="换前床号 | 与IM_CWSZ中的BRCH对应")
    /** 换前床号 | 与IM_CWSZ中的BRCH对应 */
    private String hqch;
	//@ApiModelProperty(value="换后床号 | 与IM_CWSZ中的BRCH对应")
    /** 换后床号 | 与IM_CWSZ中的BRCH对应 */
    private String hhch;
	//@ApiModelProperty(value="换前科室 | 与GY_KSDM中科室对应")
    /** 换前科室 | 与GY_KSDM中科室对应 */
    private Integer hqks;
	//@ApiModelProperty(value="换后科室 | 与GY_KSDM中科室对应")
    /** 换后科室 | 与GY_KSDM中科室对应 */
    private Integer hhks;
	//@ApiModelProperty(value="换前病区")
    /** 换前病区 */
    private Integer hqbq;
	//@ApiModelProperty(value="换后病区")
    /** 换后病区 */
    private Integer hhbq;
	//@ApiModelProperty(value="结算次数 | 同IM_HZRY,IM_ZYJS,IM_TBKK表中的JSCS 对应")
    /** 结算次数 | 同IM_HZRY,IM_ZYJS,IM_TBKK表中的JSCS 对应 */
    private Integer jscs;
	//@ApiModelProperty(value="操作工号")
    /** 操作工号 */
    private String czgh;
	//@ApiModelProperty(value="scbz")
    /** scbz */
    private String scbz;

    /** 设置:住院号  */
    public void setZyh(Integer value) {
        this.zyh = value;
    }
    /** 获取:住院号 */
    public Integer getZyh() {
        return zyh;
    }

    /** 设置:换床日期  */
    public void setHcrq(Timestamp value) {
        this.hcrq = value;
    }
    /** 获取:换床日期 */
    public Timestamp getHcrq() {
        return hcrq;
    }

    /** 设置:机构代码  */
    public void setJgid(Integer value) {
        this.jgid = value;
    }
    /** 获取:机构代码 */
    public Integer getJgid() {
        return jgid;
    }

    /** 设置:终止日期  */
    public void setZzrq(Timestamp value) {
        this.zzrq = value;
    }
    /** 获取:终止日期 */
    public Timestamp getZzrq() {
        return zzrq;
    }

    /** 设置:换床类型 | 0.分床1.换床 2.换科 3.借床 4.包床 5.退床 6.包房 7.退房  */
    public void setHclx(Integer value) {
        this.hclx = value;
    }
    /** 获取:换床类型 | 0.分床1.换床 2.换科 3.借床 4.包床 5.退床 6.包房 7.退房 */
    public Integer getHclx() {
        return hclx;
    }

    /** 设置:换前床号 | 与IM_CWSZ中的BRCH对应  */
    public void setHqch(String value) {
        this.hqch = value;
    }
    /** 获取:换前床号 | 与IM_CWSZ中的BRCH对应 */
    public String getHqch() {
        return hqch;
    }

    /** 设置:换后床号 | 与IM_CWSZ中的BRCH对应  */
    public void setHhch(String value) {
        this.hhch = value;
    }
    /** 获取:换后床号 | 与IM_CWSZ中的BRCH对应 */
    public String getHhch() {
        return hhch;
    }

    /** 设置:换前科室 | 与GY_KSDM中科室对应  */
    public void setHqks(Integer value) {
        this.hqks = value;
    }
    /** 获取:换前科室 | 与GY_KSDM中科室对应 */
    public Integer getHqks() {
        return hqks;
    }

    /** 设置:换后科室 | 与GY_KSDM中科室对应  */
    public void setHhks(Integer value) {
        this.hhks = value;
    }
    /** 获取:换后科室 | 与GY_KSDM中科室对应 */
    public Integer getHhks() {
        return hhks;
    }

    /** 设置:换前病区  */
    public void setHqbq(Integer value) {
        this.hqbq = value;
    }
    /** 获取:换前病区 */
    public Integer getHqbq() {
        return hqbq;
    }

    /** 设置:换后病区  */
    public void setHhbq(Integer value) {
        this.hhbq = value;
    }
    /** 获取:换后病区 */
    public Integer getHhbq() {
        return hhbq;
    }

    /** 设置:结算次数 | 同IM_HZRY,IM_ZYJS,IM_TBKK表中的JSCS 对应  */
    public void setJscs(Integer value) {
        this.jscs = value;
    }
    /** 获取:结算次数 | 同IM_HZRY,IM_ZYJS,IM_TBKK表中的JSCS 对应 */
    public Integer getJscs() {
        return jscs;
    }

    /** 设置:操作工号  */
    public void setCzgh(String value) {
        this.czgh = value;
    }
    /** 获取:操作工号 */
    public String getCzgh() {
        return czgh;
    }

    /** 设置:scbz  */
    public void setScbz(String value) {
        this.scbz = value;
    }
    /** 获取:scbz */
    public String getScbz() {
        return scbz;
    }


}
