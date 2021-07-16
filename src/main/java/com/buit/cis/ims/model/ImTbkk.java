package com.buit.cis.ims.model;

import com.buit.commons.PageQuery;

import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * 类名称：ImTbkk<br> 
 * 类描述：住院_退补缴款
 * @author ZHOUHAISHENG 
 * @ApiModel(value="住院_退补缴款")
 */
public class ImTbkk  extends PageQuery {
	//@ApiModelProperty(value="缴款序号")
    /** 缴款序号 */
    private Integer jkxh;
	//@ApiModelProperty(value="机构代码")
    /** 机构代码 */
    private Integer jgid;
	//@ApiModelProperty(value="住院号")
    /** 住院号 */
    private Integer zyh;
	//@ApiModelProperty(value="缴款日期")
    /** 缴款日期 */
    private Timestamp jkrq;
	//@ApiModelProperty(value="缴款金额")
    /** 缴款金额 */
    private BigDecimal jkje;
	//@ApiModelProperty(value="缴款方式 | 1:现金,2:支票(与ZY_JKFS表中JKFS字段对应")
    /** 缴款方式 | 1:现金,2:支票(与ZY_JKFS表中JKFS字段对应 */
    private Integer jkfs;
	//@ApiModelProperty(value="收据号码 | 病人预缴款收据号码")
    /** 收据号码 | 病人预缴款收据号码 */
    private String sjhm;
	//@ApiModelProperty(value="支票号码 | 病人缴款为支票时的支票号码")
    /** 支票号码 | 病人缴款为支票时的支票号码 */
    private String zphm;
    /** 发票号码 | 病人结算发票号码 */
    private String fphm;
	//@ApiModelProperty(value="结算次数 | 同IM_HZRY,IM_ZYJS,IM_FEE_FYMX表中JSCS JSCS=0 则病人尚未办理结算.")
    /** 结算次数 | 同IM_HZRY,IM_ZYJS,IM_FEE_FYMX表中JSCS JSCS=0 则病人尚未办理结算. */
    private Integer jscs;
	//@ApiModelProperty(value="操作工号")
    /** 操作工号 */
    private String czgh;
	//@ApiModelProperty(value="结账日期")
    /** 结账日期 */
    private Timestamp jzrq;
	//@ApiModelProperty(value="汇总日期")
    /** 汇总日期 */
    private Timestamp hzrq;
	//@ApiModelProperty(value="作废日期")
    /** 作废日期 */
    private Timestamp zfrq;
	//@ApiModelProperty(value="作废工号")
    /** 作废工号 */
    private Integer zfgh;
	//@ApiModelProperty(value="作废判别 | 注销预缴款或发票作废时填写")
//    /** 作废判别 | 注销预缴款或发票作废时填写 */
//    private Integer zfpb;
	//@ApiModelProperty(value="转存判别 | 0.正常缴款;    1.中结转存")
    /** 转存判别 | 0.正常缴款;    1.中结转存 */
    private Integer zcpb;
	//@ApiModelProperty(value="scbz")
    /** scbz */
    private String scbz;
	//@ApiModelProperty(value="门诊类别")
    /** 门诊类别 */
    private Integer mzlb;
    /** 缴款类型 | 0：正常预缴   1：结算抵扣   2：取消结算   3：注销找退   4：出院找退 */
    private Integer jklx;

    /** 设置:缴款序号  */
    public void setJkxh(Integer value) {
        this.jkxh = value;
    }
    /** 获取:缴款序号 */
    public Integer getJkxh() {
        return jkxh;
    }

    /** 设置:机构代码  */
    public void setJgid(Integer value) {
        this.jgid = value;
    }
    /** 获取:机构代码 */
    public Integer getJgid() {
        return jgid;
    }

    /** 设置:住院号  */
    public void setZyh(Integer value) {
        this.zyh = value;
    }
    /** 获取:住院号 */
    public Integer getZyh() {
        return zyh;
    }

    /** 设置:缴款日期  */
    public void setJkrq(Timestamp value) {
        this.jkrq = value;
    }
    /** 获取:缴款日期 */
    public Timestamp getJkrq() {
        return jkrq;
    }

    /** 设置:缴款金额  */
    public void setJkje(BigDecimal value) {
        this.jkje = value;
    }
    /** 获取:缴款金额 */
    public BigDecimal getJkje() {
        return jkje;
    }

    /** 设置:缴款方式 | 1:现金,2:支票(与ZY_JKFS表中JKFS字段对应  */
    public void setJkfs(Integer value) {
        this.jkfs = value;
    }
    /** 获取:缴款方式 | 1:现金,2:支票(与ZY_JKFS表中JKFS字段对应 */
    public Integer getJkfs() {
        return jkfs;
    }

    /** 设置:收据号码 | 病人预缴款收据号码  */
    public void setSjhm(String value) {
        this.sjhm = value;
    }
    /** 获取:收据号码 | 病人预缴款收据号码 */
    public String getSjhm() {
        return sjhm;
    }

    /** 设置:支票号码 | 病人缴款为支票时的支票号码  */
    public void setZphm(String value) {
        this.zphm = value;
    }
    /** 获取:支票号码 | 病人缴款为支票时的支票号码 */
    public String getZphm() {
        return zphm;
    }

    /** 设置:结算次数 | 同IM_HZRY,IM_ZYJS,IM_FEE_FYMX表中JSCS JSCS=0 则病人尚未办理结算.  */
    public void setJscs(Integer value) {
        this.jscs = value;
    }
    /** 获取:结算次数 | 同IM_HZRY,IM_ZYJS,IM_FEE_FYMX表中JSCS JSCS=0 则病人尚未办理结算. */
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

    /** 设置:结账日期  */
    public void setJzrq(Timestamp value) {
        this.jzrq = value;
    }
    /** 获取:结账日期 */
    public Timestamp getJzrq() {
        return jzrq;
    }

    /** 设置:汇总日期  */
    public void setHzrq(Timestamp value) {
        this.hzrq = value;
    }
    /** 获取:汇总日期 */
    public Timestamp getHzrq() {
        return hzrq;
    }

    /** 设置:作废日期  */
    public void setZfrq(Timestamp value) {
        this.zfrq = value;
    }
    /** 获取:作废日期 */
    public Timestamp getZfrq() {
        return zfrq;
    }

    /** 设置:作废工号  */
    public void setZfgh(Integer value) {
        this.zfgh = value;
    }
    /** 获取:作废工号 */
    public Integer getZfgh() {
        return zfgh;
    }

//    /** 设置:作废判别 | 注销预缴款或发票作废时填写  */
//    public void setZfpb(Integer value) {
//        this.zfpb = value;
//    }
//    /** 获取:作废判别 | 注销预缴款或发票作废时填写 */
//    public Integer getZfpb() {
//        return zfpb;
//    }

    /** 设置:转存判别 | 0.正常缴款;    1.中结转存  */
    public void setZcpb(Integer value) {
        this.zcpb = value;
    }
    /** 获取:转存判别 | 0.正常缴款;    1.中结转存 */
    public Integer getZcpb() {
        return zcpb;
    }

    /** 设置:scbz  */
    public void setScbz(String value) {
        this.scbz = value;
    }
    /** 获取:scbz */
    public String getScbz() {
        return scbz;
    }

    /** 设置:门诊类别  */
    public void setMzlb(Integer value) {
        this.mzlb = value;
    }
    /** 获取:门诊类别 */
    public Integer getMzlb() {
        return mzlb;
    }

    public String getFphm() {
        return fphm;
    }

    public void setFphm(String fphm) {
        this.fphm = fphm;
    }

    public Integer getJklx() {
        return jklx;
    }

    public void setJklx(Integer jklx) {
        this.jklx = jklx;
    }
}
