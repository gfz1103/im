package com.buit.cis.ims.response;

import com.buit.commons.PageQuery;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;


/**
 * 
* @ClassName: ImHzryDctworkResp
* @Description: TODO 住院病人入院返回参数
* @author 龚方舟
* @date 2020年5月25日 下午7:26:01
*
 */
@ApiModel(value="住院_病人入院_workResp")
public class ImHzryDctworkResp  extends PageQuery {
	
	@ApiModelProperty(value="结算次数 | 最后一次结算次数（包括作废） 下一次结算次数为当前值加1")
    private Integer jscs;
	
	@ApiModelProperty(value="婴儿唯一号 | 和BQ_XSEDJ.YEWYH的取值一致")
    private Integer yewyh;
	
	@ApiModelProperty(value="住院号码 | 该住院号码是在院病人的识别号，在院病人唯一")
    private String zyhm;
	
	@ApiModelProperty(value="门诊号码")
    private String mzhm;
	
    @ApiModelProperty(value="住院号 | 该住院号为内码")
    private Integer zyh;
    
    @ApiModelProperty(value="房间号码")
    private String fjhm;
    
    @ApiModelProperty(value="病人情况 | 与GY_DMZD（DMLB=10）对应")
    private Integer brqk;
    
    @ApiModelProperty(value="病人皮试记录")
    private Integer gmywSign;
    
    @ApiModelProperty(value="护理级别 | 0：特级护理 1：一级护理 2：二级护理 3：三级护理(字典:sys_flag_data:HL000001)")
    private Integer hljb;
    
    @ApiModelProperty(value="出院日期 | 病区办理出院证明的日期(可以提前或推后)")
    private Timestamp cyrq;
    
    @ApiModelProperty(value="出院方式")
    private Integer cyfs;
    
    @ApiModelProperty(value="住院医生 | 对应GY_YGDM中的YGDM的代码")
    private String zyys;
    
    @ApiModelProperty(value="病人姓名")
    private String brxm;
    
    @ApiModelProperty(value="出院判别 | 0：在院病人 1：出院证明 2：预结出院 8：正常出院 9：终结出院 99 注销出院(字典:sys_flag_data:CY000001)")
    private Integer cypb;
    
    @ApiModelProperty(value="病人ID")
    private Integer brid;
    
    @ApiModelProperty(value="饮食代码 | 与GY_DMZD（DMLB=20）对应")
    private Integer ysdm;
    
    @ApiModelProperty(value="科室代码")
    private Integer ksdm;
    
    @ApiModelProperty(value="病人性质 | 对应PUB_BRXZ中代码")
    private Integer brxz;
    
    @ApiModelProperty(value="入院开始日期")
    private String ryrqs;
    
    @ApiModelProperty(value="借床科室")
    private Integer jcks;

    @ApiModelProperty(value="加床判别 | 0.普通床 1.加床 2.虚床(字典:sys_flag_data:JC000001)")
    private Integer jcpb;
    
    @ApiModelProperty(value="主治医生 | 对应GY_YGDM中的YGDM的代码")
    private String zzys;
    
    @ApiModelProperty(value="病人病区 | 病人所在病区，对应GY_KSDM的代码")
    private Integer brbq;
    
    @ApiModelProperty(value="疾病名称")
    private String jbmc;
    
    @ApiModelProperty(value="临床病人入出记录")
    private Integer cyz;
    
    @ApiModelProperty(value="出生年月")
    private Date csny;
    
    @ApiModelProperty(value="入院年龄")
    private String rynl;
    
    @ApiModelProperty(value="床位性别")
    private Integer cwxb;
    
    @ApiModelProperty(value="病人科室 | 病人所在科室，对应GY_KSDM的代码")
    private Integer brks;
    
    @ApiModelProperty(value="系统医嘱")
    private Integer xtyz;
    
    @ApiModelProperty(value="转科状态")
    private Integer zkzt;
    
    @ApiModelProperty(value="病人性别")
    private Integer brxb;
    
    @ApiModelProperty(value="重点患者判别 | 默认0 :不是重点患者   1:危重病人   2:手术后病人   3:新生儿病人,其高危险程度比较明显   4:疾病晚期的临终病人   5:急诊病人   6:社会上有影响有地位的人物   7:与医疗纠纷或司法案例有关的病人等")
    private Integer zdhzpb;
    
    @ApiModelProperty(value="入院日期 | 入院登记时填写的入院时间")
    private Date ryrq;
    
    @ApiModelProperty(value="住院次数，用于病案归档")
    private Integer zycs;
    
    @ApiModelProperty(value="目前诊断")
    private String mqzd;
    
    @ApiModelProperty(value="病人床号")
    private String brch;
    
    @ApiModelProperty(value="病人显示床号")
    private String brchShow;
    
    @ApiModelProperty(value="年龄")
    private String age;
    
    @ApiModelProperty(value="床位费用")
    private Integer cwfy;
    
    @ApiModelProperty(value="床位费用")
    private Integer xkyz;
    
    @ApiModelProperty(value="ICU")
    private Integer icu;
    
    @ApiModelProperty(value="床位科室")
    private Integer cwks;
    
    @ApiModelProperty(value="预交款")
    private BigDecimal yjk;
    
    @ApiModelProperty(value="自费金额")
    private BigDecimal zfje;
    
    @ApiModelProperty(value="剩余金额")
    private BigDecimal syje;
    
    @ApiModelProperty(value="医保卡号")
    private String ybkh;
    
    @ApiModelProperty(value="身份证号")
    private String sfzh;
    
    @ApiModelProperty(value="ICD10BM")
    private String icd10bm;
    
    @ApiModelProperty(value="病人血型")
    private Integer brxx;
    
    @ApiModelProperty(value="过敏药物")
    private String gmyw;
    
    @ApiModelProperty(value="入院主诊断名称")
    private String zzdmc;
    
    @ApiModelProperty(value="结算日期")
    private Timestamp jsrq;
    
    @ApiModelProperty(value="住院天数")
    private BigDecimal zyts;
    
    @ApiModelProperty(value="副主任医师")
    private String fzrys;
    
    @ApiModelProperty(value="责任护士")
    private String zrhs;
    
    @ApiModelProperty(value="主管护士")
    private String zghs;

	public Integer getJscs() {
		return jscs;
	}

	public void setJscs(Integer jscs) {
		this.jscs = jscs;
	}

	public Integer getYewyh() {
		return yewyh;
	}

	public void setYewyh(Integer yewyh) {
		this.yewyh = yewyh;
	}

	public String getZyhm() {
		return zyhm;
	}

	public void setZyhm(String zyhm) {
		this.zyhm = zyhm;
	}

	public String getMzhm() {
		return mzhm;
	}

	public void setMzhm(String mzhm) {
		this.mzhm = mzhm;
	}

	public Integer getZyh() {
		return zyh;
	}

	public void setZyh(Integer zyh) {
		this.zyh = zyh;
	}

	public String getFjhm() {
		return fjhm;
	}

	public void setFjhm(String fjhm) {
		this.fjhm = fjhm;
	}

	public Integer getBrqk() {
		return brqk;
	}

	public void setBrqk(Integer brqk) {
		this.brqk = brqk;
	}

	public Integer getGmywSign() {
		return gmywSign;
	}

	public void setGmywSign(Integer gmywSign) {
		this.gmywSign = gmywSign;
	}

	public Integer getHljb() {
		return hljb;
	}

	public void setHljb(Integer hljb) {
		this.hljb = hljb;
	}

	public Timestamp getCyrq() {
		return cyrq;
	}

	public void setCyrq(Timestamp cyrq) {
		this.cyrq = cyrq;
	}

	public Integer getCyfs() {
		return cyfs;
	}

	public void setCyfs(Integer cyfs) {
		this.cyfs = cyfs;
	}

	public String getZyys() {
		return zyys;
	}

	public void setZyys(String zyys) {
		this.zyys = zyys;
	}

	public String getBrxm() {
		return brxm;
	}

	public void setBrxm(String brxm) {
		this.brxm = brxm;
	}

	public Integer getCypb() {
		return cypb;
	}

	public void setCypb(Integer cypb) {
		this.cypb = cypb;
	}

	public Integer getBrid() {
		return brid;
	}

	public void setBrid(Integer brid) {
		this.brid = brid;
	}

	public Integer getYsdm() {
		return ysdm;
	}

	public void setYsdm(Integer ysdm) {
		this.ysdm = ysdm;
	}

	public Integer getKsdm() {
		return ksdm;
	}

	public void setKsdm(Integer ksdm) {
		this.ksdm = ksdm;
	}

	public Integer getBrxz() {
		return brxz;
	}

	public void setBrxz(Integer brxz) {
		this.brxz = brxz;
	}

	public String getRyrqs() {
		return ryrqs;
	}

	public void setRyrqs(String ryrqs) {
		this.ryrqs = ryrqs;
	}

	public Integer getJcks() {
		return jcks;
	}

	public void setJcks(Integer jcks) {
		this.jcks = jcks;
	}

	public Integer getJcpb() {
		return jcpb;
	}

	public void setJcpb(Integer jcpb) {
		this.jcpb = jcpb;
	}

	public String getZzys() {
		return zzys;
	}

	public void setZzys(String zzys) {
		this.zzys = zzys;
	}

	public Integer getBrbq() {
		return brbq;
	}

	public void setBrbq(Integer brbq) {
		this.brbq = brbq;
	}

	public String getJbmc() {
		return jbmc;
	}

	public void setJbmc(String jbmc) {
		this.jbmc = jbmc;
	}

	public Integer getCyz() {
		return cyz;
	}

	public void setCyz(Integer cyz) {
		this.cyz = cyz;
	}

	public Date getCsny() {
		return csny;
	}

	public void setCsny(Date csny) {
		this.csny = csny;
	}

	public String getRynl() {
		return rynl;
	}

	public void setRynl(String rynl) {
		this.rynl = rynl;
	}

	public Integer getCwxb() {
		return cwxb;
	}

	public void setCwxb(Integer cwxb) {
		this.cwxb = cwxb;
	}

	public Integer getBrks() {
		return brks;
	}

	public void setBrks(Integer brks) {
		this.brks = brks;
	}

	public Integer getXtyz() {
		return xtyz;
	}

	public void setXtyz(Integer xtyz) {
		this.xtyz = xtyz;
	}

	public Integer getZkzt() {
		return zkzt;
	}

	public void setZkzt(Integer zkzt) {
		this.zkzt = zkzt;
	}

	public Integer getBrxb() {
		return brxb;
	}

	public void setBrxb(Integer brxb) {
		this.brxb = brxb;
	}

	public Integer getZdhzpb() {
		return zdhzpb;
	}

	public void setZdhzpb(Integer zdhzpb) {
		this.zdhzpb = zdhzpb;
	}

	public Date getRyrq() {
		return ryrq;
	}

	public void setRyrq(Date ryrq) {
		this.ryrq = ryrq;
	}

	public Integer getZycs() {
		return zycs;
	}

	public void setZycs(Integer zycs) {
		this.zycs = zycs;
	}

	public String getMqzd() {
		return mqzd;
	}

	public void setMqzd(String mqzd) {
		this.mqzd = mqzd;
	}

	public String getBrch() {
		return brch;
	}

	public void setBrch(String brch) {
		this.brch = brch;
	}

	public String getBrchShow() {
		return brchShow;
	}

	public void setBrchShow(String brchShow) {
		this.brchShow = brchShow;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public Integer getCwfy() {
		return cwfy;
	}

	public void setCwfy(Integer cwfy) {
		this.cwfy = cwfy;
	}

	public Integer getXkyz() {
		return xkyz;
	}

	public void setXkyz(Integer xkyz) {
		this.xkyz = xkyz;
	}

	public Integer getIcu() {
		return icu;
	}

	public void setIcu(Integer icu) {
		this.icu = icu;
	}

	public Integer getCwks() {
		return cwks;
	}

	public void setCwks(Integer cwks) {
		this.cwks = cwks;
	}

	public BigDecimal getYjk() {
		return yjk;
	}

	public void setYjk(BigDecimal yjk) {
		this.yjk = yjk;
	}

	public BigDecimal getZfje() {
		return zfje;
	}

	public void setZfje(BigDecimal zfje) {
		this.zfje = zfje;
	}

	public BigDecimal getSyje() {
		return syje;
	}

	public void setSyje(BigDecimal syje) {
		this.syje = syje;
	}

	public String getYbkh() {
		return ybkh;
	}

	public void setYbkh(String ybkh) {
		this.ybkh = ybkh;
	}

	public String getSfzh() {
		return sfzh;
	}

	public void setSfzh(String sfzh) {
		this.sfzh = sfzh;
	}

	public String getIcd10bm() {
		return icd10bm;
	}

	public void setIcd10bm(String icd10bm) {
		this.icd10bm = icd10bm;
	}

	public Integer getBrxx() {
		return brxx;
	}

	public void setBrxx(Integer brxx) {
		this.brxx = brxx;
	}

	public String getGmyw() {
		return gmyw;
	}

	public void setGmyw(String gmyw) {
		this.gmyw = gmyw;
	}

	public String getZzdmc() {
		return zzdmc;
	}

	public void setZzdmc(String zzdmc) {
		this.zzdmc = zzdmc;
	}

	public Timestamp getJsrq() {
		return jsrq;
	}

	public void setJsrq(Timestamp jsrq) {
		this.jsrq = jsrq;
	}

	public BigDecimal getZyts() {
		return zyts;
	}

	public void setZyts(BigDecimal zyts) {
		this.zyts = zyts;
	}

	public String getFzrys() {
		return fzrys;
	}

	public void setFzrys(String fzrys) {
		this.fzrys = fzrys;
	}

	public String getZrhs() {
		return zrhs;
	}

	public void setZrhs(String zrhs) {
		this.zrhs = zrhs;
	}

	public String getZghs() {
		return zghs;
	}

	public void setZghs(String zghs) {
		this.zghs = zghs;
	}
    
	
}
