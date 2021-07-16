package com.buit.cis.bedresev.model;

import com.buit.commons.PageQuery;
import io.swagger.annotations.ApiModelProperty;

import java.sql.Timestamp;


/**
 * 类名称：ImBedresev<br> 
 * 类描述：
 * @author zhouhaisheng
 * @ApiModel(value="")
 */
public class ImBedresev  extends  PageQuery{
	@ApiModelProperty(value="主键id")
    private Integer jlxh;
	@ApiModelProperty(value="机构id")
    private Integer jgid;
	@ApiModelProperty(value="卡号")
    private String cardNo;
	@ApiModelProperty(value="病人姓名")
    private String brxm;
	@ApiModelProperty(value="身份证号码")
    private String idCard;
	@ApiModelProperty(value="出生年月")
    private Timestamp csny;
	@ApiModelProperty(value="病人性质")
    private Integer brxz;
	@ApiModelProperty(value="联系电话")
    private String lxdh;
	@ApiModelProperty(value="现住址_市")
    private Integer xzzS;
	@ApiModelProperty(value="现住址_省区市")
    private Integer xzzSqs;
	@ApiModelProperty(value="现住址_县")
    private Integer xzzX;
	@ApiModelProperty(value="联系人姓名")
    private String lxrm;
	@ApiModelProperty(value="预约科室")
    private Integer yyks;
	@ApiModelProperty(value="预约住院日期")
    private Timestamp yyzyrq;
	@ApiModelProperty(value="入院情况")
    private Integer ryqk;
	@ApiModelProperty(value="门诊诊断")
    private String mzzd;
	@ApiModelProperty(value="备注")
    private String bz;
	@ApiModelProperty(value="病人病区")
    private Integer brbq;
	@ApiModelProperty(value="病人床号")
    private String brch;
	@ApiModelProperty(value="通知入院时间")
    private Timestamp rytzsj;
	@ApiModelProperty(value="病人通知内容")
    private String notice;
	@ApiModelProperty(value="待床状态 0:未待床 1:已待床 2:逾期 3:已入院")
    private String dczt;
    @ApiModelProperty(value="联系人关系")
    private Integer lxgx;
    @ApiModelProperty(value="联系人电话")
    private String lxrdh;
    @ApiModelProperty(value="病人性别")
    private Integer brxb;
    @ApiModelProperty(value="操作人")
    private Integer djr;
    @ApiModelProperty(value="待床操作人")
    private Integer dcczr;
    @ApiModelProperty(value="登记时间")
    private Timestamp djsj;

    @ApiModelProperty(value="入院登记时间")
    private Timestamp rydjsj;
    @ApiModelProperty(value="入院登记操作人")
    private Integer rydjczr;
    @ApiModelProperty(value="病人id")
    private Integer brid;

    /** 设置:主键id  */
    public void setJlxh(Integer value) {
        this.jlxh = value;
    }
    /** 获取:主键id */
    public Integer getJlxh() {
        return jlxh;
    }

    /** 设置:机构id  */
    public void setJgid(Integer value) {
        this.jgid = value;
    }
    /** 获取:机构id */
    public Integer getJgid() {
        return jgid;
    }

    /** 设置:卡号  */
    public void setCardNo(String value) {
        this.cardNo = value;
    }
    /** 获取:卡号 */
    public String getCardNo() {
        return cardNo;
    }

    /** 设置:病人姓名  */
    public void setBrxm(String value) {
        this.brxm = value;
    }
    /** 获取:病人姓名 */
    public String getBrxm() {
        return brxm;
    }

    /** 设置:身份证号码  */
    public void setIdCard(String value) {
        this.idCard = value;
    }
    /** 获取:身份证号码 */
    public String getIdCard() {
        return idCard;
    }

    /** 设置:出生年月  */
    public void setCsny(Timestamp value) {
        this.csny = value;
    }
    /** 获取:出生年月 */
    public Timestamp getCsny() {
        return csny;
    }

    /** 设置:病人性质  */
    public void setBrxz(Integer value) {
        this.brxz = value;
    }
    /** 获取:病人性质 */
    public Integer getBrxz() {
        return brxz;
    }

    /** 设置:联系电话  */
    public void setLxdh(String value) {
        this.lxdh = value;
    }
    /** 获取:联系电话 */
    public String getLxdh() {
        return lxdh;
    }

    public Integer getXzzS() {
        return xzzS;
    }

    public void setXzzS(Integer xzzS) {
        this.xzzS = xzzS;
    }

    public Integer getXzzSqs() {
        return xzzSqs;
    }

    public void setXzzSqs(Integer xzzSqs) {
        this.xzzSqs = xzzSqs;
    }

    public Integer getXzzX() {
        return xzzX;
    }

    public void setXzzX(Integer xzzX) {
        this.xzzX = xzzX;
    }

    /** 设置:联系人姓名  */
    public void setLxrm(String value) {
        this.lxrm = value;
    }
    /** 获取:联系人姓名 */
    public String getLxrm() {
        return lxrm;
    }

    /** 设置:预约科室  */
    public void setYyks(Integer value) {
        this.yyks = value;
    }
    /** 获取:预约科室 */
    public Integer getYyks() {
        return yyks;
    }

    /** 设置:预约住院日期  */
    public void setYyzyrq(Timestamp value) {
        this.yyzyrq = value;
    }
    /** 获取:预约住院日期 */
    public Timestamp getYyzyrq() {
        return yyzyrq;
    }

    /** 设置:入院情况  */
    public void setRyqk(Integer value) {
        this.ryqk = value;
    }
    /** 获取:入院情况 */
    public Integer getRyqk() {
        return ryqk;
    }

    /** 设置:门诊诊断  */
    public void setMzzd(String value) {
        this.mzzd = value;
    }
    /** 获取:门诊诊断 */
    public String getMzzd() {
        return mzzd;
    }

    /** 设置:备注  */
    public void setBz(String value) {
        this.bz = value;
    }
    /** 获取:备注 */
    public String getBz() {
        return bz;
    }

    /** 设置:病人病区  */
    public void setBrbq(Integer value) {
        this.brbq = value;
    }
    /** 获取:病人病区 */
    public Integer getBrbq() {
        return brbq;
    }

    /** 设置:病人床号  */
    public void setBrch(String value) {
        this.brch = value;
    }
    /** 获取:病人床号 */
    public String getBrch() {
        return brch;
    }

    /** 设置:通知入院时间  */
    public void setRytzsj(Timestamp value) {
        this.rytzsj = value;
    }
    /** 获取:通知入院时间 */
    public Timestamp getRytzsj() {
        return rytzsj;
    }

    /** 设置:病人通知内容  */
    public void setNotice(String value) {
        this.notice = value;
    }
    /** 获取:病人通知内容 */
    public String getNotice() {
        return notice;
    }

    /** 设置:待床状态 0:未待床 1:已待床 2:逾期  */
    public void setDczt(String value) {
        this.dczt = value;
    }
    /** 获取:待床状态 0:未待床 1:已待床 2:逾期 */
    public String getDczt() {
        return dczt;
    }

    public Integer getLxgx() {
        return lxgx;
    }

    public void setLxgx(Integer lxgx) {
        this.lxgx = lxgx;
    }

    public String getLxrdh() {
        return lxrdh;
    }

    public void setLxrdh(String lxrdh) {
        this.lxrdh = lxrdh;
    }

    public Integer getBrxb() {
        return brxb;
    }

    public void setBrxb(Integer brxb) {
        this.brxb = brxb;
    }

    public Integer getDjr() {
        return djr;
    }

    public void setDjr(Integer djr) {
        this.djr = djr;
    }

    public Integer getDcczr() {
        return dcczr;
    }

    public void setDcczr(Integer dcczr) {
        this.dcczr = dcczr;
    }

    public Timestamp getDjsj() {
        return djsj;
    }

    public void setDjsj(Timestamp djsj) {
        this.djsj = djsj;
    }

    public Timestamp getRydjsj() {
        return rydjsj;
    }

    public void setRydjsj(Timestamp rydjsj) {
        this.rydjsj = rydjsj;
    }

    public Integer getRydjczr() {
        return rydjczr;
    }

    public void setRydjczr(Integer rydjczr) {
        this.rydjczr = rydjczr;
    }

    public Integer getBrid() {
        return brid;
    }

    public void setBrid(Integer brid) {
        this.brid = brid;
    }
}