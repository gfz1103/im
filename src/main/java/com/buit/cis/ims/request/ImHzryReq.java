package com.buit.cis.ims.request;

import com.buit.commons.PageQuery;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;
import java.sql.Timestamp;


/**
 * 类名称：ImHzry<br>
 * 类描述：住院_病人入院<br>
 * @author ZHOUHAISHENG
 */
@ApiModel(value="住院_病人入院")
public class ImHzryReq  extends PageQuery {
    @ApiModelProperty(value="住院号 | 该住院号为内码")
    private Integer zyh;
    @ApiModelProperty(value="机构代码")
    private Integer jgid;
    @ApiModelProperty(value="病人ID")
    private Integer brid;
    @ApiModelProperty(value="住院号码 | 该住院号码是在院病人的识别号，在院病人唯一")
    private String zyhm;
    @ApiModelProperty(value="病案号码 | 住院病人的病历号，病案号码唯一")
    private String bahm;
    @ApiModelProperty(value="门诊号码")
    private String mzhm;
    @ApiModelProperty(value="病人性质 | 对应PUB_BRXZ中代码")
    private Integer brxz;
    @ApiModelProperty(value="公费证号 | 若ZY_BRXZ中ZHPB为1则需填写该证号")
    private String gfzh;
    @ApiModelProperty(value="病人姓名")
    private String brxm;
    @ApiModelProperty(value="病人性别")
    private Integer brxb;
    @ApiModelProperty(value="出生年月")
    private Timestamp csny;
    @ApiModelProperty(value="身份证号")
    private String sfzh;
    @ApiModelProperty(value="婚姻状况")
    private Integer hyzk;
    @ApiModelProperty(value="身份代码 | 与GY_DMZD（DMLB=14）对应")
    private String zydm;
    @ApiModelProperty(value="籍贯代码")
    private Integer sfdm;
    @ApiModelProperty(value="民族代码")
    private Integer jgdm;
    @ApiModelProperty(value="民族代码")
    private String mzdm;
    @ApiModelProperty(value="籍贯代码")
    private String gjdm;
    @ApiModelProperty(value="单位编号 | 对应与gy_cydw")
    private Integer dwbh;
    @ApiModelProperty(value="工作单位")
    private String gzdw;
    @ApiModelProperty(value="单位电话")
    private String dwdh;
    @ApiModelProperty(value="单位邮编")
    private String dwyb;
    @ApiModelProperty(value="户口地址")
    private String hkdz;
    @ApiModelProperty(value="户口邮编")
    private String hkyb;
    @ApiModelProperty(value="联系人名")
    private String lxrm;
    @ApiModelProperty(value="联系人关系 | 与GY_DMZD（DMLB=4）对应")
    private Integer lxgx;
    @ApiModelProperty(value="联系地址")
    private String lxdz;
    @ApiModelProperty(value="联系电话")
    private String lxdh;
    @ApiModelProperty(value="凭证号码")
    private String pzhm;
    @ApiModelProperty(value="社保号码 | 社会保险病人社保号码")
    private String sbhm;
    @ApiModelProperty(value="担保人名")
    private String dbrm;
    @ApiModelProperty(value="担保关系")
    private Integer dbgx;
    @ApiModelProperty(value="在职退休 | 与GY_DMZD（DMLB=9）对应")
    private Integer zztx;
    @ApiModelProperty(value="大保病种")
    private Integer dbbz;
    @ApiModelProperty(value="病人科室 | 病人所在科室，对应GY_KSDM的代码")
    private Integer brks;
    @ApiModelProperty(value="病人病区 | 病人所在病区，对应GY_KSDM的代码")
    private Integer brbq;
    @ApiModelProperty(value="病人床号")
    private String brch;
    @ApiModelProperty(value="登记日期 | 填写入院登记表的时间(系统自动填写)")
    private Timestamp djrq;
    @ApiModelProperty(value="入院日期 | 入院登记时填写的入院时间")
    private Timestamp ryrq;
    @ApiModelProperty(value="出院日期 | 病区办理出院证明的日期(可以提前或推后)")
    private Timestamp cyrq;
    @ApiModelProperty(value="出院判别 | 0：在院病人 1：出院证明 2：预结出院 8：正常出院 9：终结出院 99 注销出院")
    private Integer cypb;
    @ApiModelProperty(value="出院方式 |与GY_DMZD（DMLB= 23）对应 1：治愈 2：好转 3：未愈 5：死亡 ：其他")
    private Integer cyfs;
    @ApiModelProperty(value="操作工号")
    private String czgh;
    @ApiModelProperty(value="入院情况 | 与GY_DMZD（DMLB=10）对应")
    private Integer ryqk;
    @ApiModelProperty(value="病人情况 | 与GY_DMZD（DMLB=10）对应")
    private Integer brqk;
    @ApiModelProperty(value="护理级别 | 0：特级护理 1：一级护理 2：二级护理 3：三级护理")
    private Integer hljb;
    @ApiModelProperty(value="饮食代码 | 与GY_DMZD（DMLB=20）对应")
    private Integer ysdm;
    @ApiModelProperty(value="病人血型 | 与GY_DMZD（DMLB=21）对应")
    private Integer brxx;
    @ApiModelProperty(value="会诊科室")
    private Integer hzks;
    @ApiModelProperty(value="借床科室")
    private Integer jcks;
    @ApiModelProperty(value="门诊医生 | 对应GY_YGDM中的YGDM的代码")
    private String mzys;
    @ApiModelProperty(value="住院医生 | 对应GY_YGDM中的YGDM的代码")
    private String zyys;
    @ApiModelProperty(value="主任医生 | 对应GY_YGDM中的YGDM的代码")
    private String zsys;
    @ApiModelProperty(value="主治医生 | 对应GY_YGDM中的YGDM的代码")
    private String zzys;
    @ApiModelProperty(value="确诊日期 | 病人确诊日期 ")
    private Timestamp qzrq;
    @ApiModelProperty(value="开始日期 | 床位费累加的起始日期，第一次自动累加费用后修改该时间")
    private Timestamp ksrq;
    @ApiModelProperty(value="结算日期 | 最后一次结算日期（包括作废）")
    private Timestamp jsrq;
    @ApiModelProperty(value="结算次数 | 最后一次结算次数（包括作废） 下一次结算次数为当前值加1")
    private Integer jscs;
    @ApiModelProperty(value="结账日期 | 汇总日报结束时填写，若该病人已做预结则写该字段")
    private Timestamp jzrq;
    @ApiModelProperty(value="汇总日期 | 汇总日报结束时填写，病人费用、医嘱转到出院表中，根据该字段可判断病人费用、医嘱在哪张表中")
    private Timestamp hzrq;
    @ApiModelProperty(value="修改判别 | 0：没有对病人操作 1：正在对该病人费用记帐 2：正在对该病人性质转换 9：正在对该病人结算处理 该字段已经作废不使用，开始使用的版本为bshrp4.3.01.3000")
    private Integer xgpb=0;
    @ApiModelProperty(value="病案判别 | 0：病人信息未转到病案系统 1：病人信息已转到病案系统")
    private Integer bapb=0;
    @ApiModelProperty(value="病人关联")
    private Integer brgl;
    @ApiModelProperty(value="病人口令")
    private String brkl;
    @ApiModelProperty(value="病人全名")
    private String brqm;
    @ApiModelProperty(value="家庭电话")
    private String jtdh;
    @ApiModelProperty(value="医保卡号")
    private String ybkh;
    @ApiModelProperty(value="就诊卡号")
    private String jzkh;
    @ApiModelProperty(value="主治医生 | 指接收病人的医生，对应GY_YGDM中的YGDM的代码")
    private String szys;
    @ApiModelProperty(value="诊疗小组 | 指负责病人治疗的医疗小组，对应ZY_ZLXZ.XZXH的代码")
    private Integer zlxz;
    @ApiModelProperty(value="审批金额 | 允许病人欠款的最大金额")
    private BigDecimal spje;
    @ApiModelProperty(value="数据转移 | 0 未转移 1 已转移")
    private Integer sjzy;
    @ApiModelProperty(value="冻结标志 | 0：未冻结      1：已冻结")
    private Integer djbz;
    @ApiModelProperty(value="冻结ID号 | 对应ZY_ZHDJ.DJID的代码")
    private Integer djid;
    @ApiModelProperty(value="冻结金额 | 达到冻结账户的金额值")
    private BigDecimal djje;
    @ApiModelProperty(value="YSJS")
    private Integer ysjs;
    @ApiModelProperty(value="转科状态")
    private Integer zkzt;
    @ApiModelProperty(value="重点患者判别 | 默认0 :不是重点患者   1:危重病人   2:手术后病人   3:新生儿病人,其高危险程度比较明显   4:疾病晚期的临终病人   5:急诊病人   6:社会上有影响有地位的人物   7:与医疗纠纷或司法案例有关的病人等")
    private Integer zdhzpb;
    @ApiModelProperty(value="备注")
    private String bz;
    @ApiModelProperty(value="出生地_省市区")
    private Integer csdSqs;
    @ApiModelProperty(value="出生地_市")
    private Integer csdS;
    @ApiModelProperty(value="出生地_县")
    private Integer csdX;
    @ApiModelProperty(value="籍贯代码_省区市")
    private Integer jgdmSqs;
    @ApiModelProperty(value="籍贯代码_市")
    private Integer jgdmS;
    @ApiModelProperty(value="籍贯代码_市")
    private Integer xzzSqs;
    @ApiModelProperty(value="现住址_市")
    private Integer xzzS;
    @ApiModelProperty(value="现住址_县")
    private Integer xzzX;
    @ApiModelProperty(value="现住址_邮编")
    private String xzzYb;
    @ApiModelProperty(value="现住址_电话")
    private String xzzDh;
    @ApiModelProperty(value="户口地址_省区市")
    private Integer hkdzSqs;
    @ApiModelProperty(value="户口地址_市")
    private Integer hkdzS;
    @ApiModelProperty(value="户口地址_县")
    private Integer hkdzX;
    @ApiModelProperty(value="现住址_其他地址")
    private String xzzQtdz;
    @ApiModelProperty(value="户口地址_其他地址")
    private String hkdzQtdz;
    @ApiModelProperty(value="调入标志 | 客户关系系统调入住院病人使用标志，1表示该病人已经导入，0则表示未导入客户关系")
    private Integer drbz;
    @ApiModelProperty(value="入院年龄")
    private String rynl;
    @ApiModelProperty(value="住院次数，用于病案归档")
    private Integer zycs;
    @ApiModelProperty(value="入院诊断")
    private String ryzd;
    @ApiModelProperty(value="目前诊断")
    private String mqzd;
    @ApiModelProperty(value="主要诊断名称")
    private String zyzd;
    @ApiModelProperty(value="门诊诊断编码")
    private Integer mzzd;
    @ApiModelProperty(value="zhbz")
    private String zhbz;
    @ApiModelProperty(value="scbz")
    private String scbz;
    @ApiModelProperty(value="农合住院登记ID")
    private String nhdjid;
    @ApiModelProperty(value="门诊类别")
    private Integer mzlb;
    @ApiModelProperty(value="xzqhdm")
    private String xzqhdm;
    @ApiModelProperty(value="大病减负标志 1:尿毒症透析医疗费用,2:肾移植减负,3：精神病减负  其他：不减负")
    private String dbjfbz;

    @ApiModelProperty(value="证件类型")
    private String zjlx;
    @ApiModelProperty(value="业务类型")
    private String ywlx;

    /**
     * 设置:住院号 | 该住院号为内码
     */
    public void setZyh(Integer value) {
        this.zyh = value;
    }
    /**
     * 获取:住院号 | 该住院号为内码
     */
    public Integer getZyh() {
        return zyh;
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
     * 设置:病人ID
     */
    public void setBrid(Integer value) {
        this.brid = value;
    }
    /**
     * 获取:病人ID
     */
    public Integer getBrid() {
        return brid;
    }
    /**
     * 设置:住院号码 | 该住院号码是在院病人的识别号，在院病人唯一
     */
    public void setZyhm(String value) {
        this.zyhm = value;
    }
    /**
     * 获取:住院号码 | 该住院号码是在院病人的识别号，在院病人唯一
     */
    public String getZyhm() {
        return zyhm;
    }
    /**
     * 设置:病案号码 | 住院病人的病历号，病案号码唯一
     */
    public void setBahm(String value) {
        this.bahm = value;
    }
    /**
     * 获取:病案号码 | 住院病人的病历号，病案号码唯一
     */
    public String getBahm() {
        return bahm;
    }
    /**
     * 设置:门诊号码
     */
    public void setMzhm(String value) {
        this.mzhm = value;
    }
    /**
     * 获取:门诊号码
     */
    public String getMzhm() {
        return mzhm;
    }
    /**
     * 设置:病人性质 | 对应PUB_BRXZ中代码
     */
    public void setBrxz(Integer value) {
        this.brxz = value;
    }
    /**
     * 获取:病人性质 | 对应PUB_BRXZ中代码
     */
    public Integer getBrxz() {
        return brxz;
    }
    /**
     * 设置:公费证号 | 若ZY_BRXZ中ZHPB为1则需填写该证号
     */
    public void setGfzh(String value) {
        this.gfzh = value;
    }
    /**
     * 获取:公费证号 | 若ZY_BRXZ中ZHPB为1则需填写该证号
     */
    public String getGfzh() {
        return gfzh;
    }
    /**
     * 设置:病人姓名
     */
    public void setBrxm(String value) {
        this.brxm = value;
    }
    /**
     * 获取:病人姓名
     */
    public String getBrxm() {
        return brxm;
    }
    /**
     * 设置:病人性别
     */
    public void setBrxb(Integer value) {
        this.brxb = value;
    }
    /**
     * 获取:病人性别
     */
    public Integer getBrxb() {
        return brxb;
    }
    /**
     * 设置:出生年月
     */
    public void setCsny(Timestamp value) {
        this.csny = value;
    }
    /**
     * 获取:出生年月
     */
    public Timestamp getCsny() {
        return csny;
    }
    /**
     * 设置:身份证号
     */
    public void setSfzh(String value) {
        this.sfzh = value;
    }
    /**
     * 获取:身份证号
     */
    public String getSfzh() {
        return sfzh;
    }
    /**
     * 设置:婚姻状况
     */
    public void setHyzk(Integer value) {
        this.hyzk = value;
    }
    /**
     * 获取:婚姻状况
     */
    public Integer getHyzk() {
        return hyzk;
    }
    /**
     * 设置:身份代码 | 与GY_DMZD（DMLB=14）对应
     */
    public void setZydm(String value) {
        this.zydm = value;
    }
    /**
     * 获取:身份代码 | 与GY_DMZD（DMLB=14）对应
     */
    public String getZydm() {
        return zydm;
    }
    /**
     * 设置:籍贯代码
     */
    public void setSfdm(Integer value) {
        this.sfdm = value;
    }
    /**
     * 获取:籍贯代码
     */
    public Integer getSfdm() {
        return sfdm;
    }
    /**
     * 设置:民族代码
     */
    public void setJgdm(Integer value) {
        this.jgdm = value;
    }
    /**
     * 获取:民族代码
     */
    public Integer getJgdm() {
        return jgdm;
    }
    /**
     * 设置:民族代码
     */
    public void setMzdm(String value) {
        this.mzdm = value;
    }
    /**
     * 获取:民族代码
     */
    public String getMzdm() {
        return mzdm;
    }
    /**
     * 设置:籍贯代码
     */
    public void setGjdm(String value) {
        this.gjdm = value;
    }
    /**
     * 获取:籍贯代码
     */
    public String getGjdm() {
        return gjdm;
    }
    /**
     * 设置:单位编号 | 对应与gy_cydw
     */
    public void setDwbh(Integer value) {
        this.dwbh = value;
    }
    /**
     * 获取:单位编号 | 对应与gy_cydw
     */
    public Integer getDwbh() {
        return dwbh;
    }
    /**
     * 设置:工作单位
     */
    public void setGzdw(String value) {
        this.gzdw = value;
    }
    /**
     * 获取:工作单位
     */
    public String getGzdw() {
        return gzdw;
    }
    /**
     * 设置:单位电话
     */
    public void setDwdh(String value) {
        this.dwdh = value;
    }
    /**
     * 获取:单位电话
     */
    public String getDwdh() {
        return dwdh;
    }
    /**
     * 设置:单位邮编
     */
    public void setDwyb(String value) {
        this.dwyb = value;
    }
    /**
     * 获取:单位邮编
     */
    public String getDwyb() {
        return dwyb;
    }
    /**
     * 设置:户口地址
     */
    public void setHkdz(String value) {
        this.hkdz = value;
    }
    /**
     * 获取:户口地址
     */
    public String getHkdz() {
        return hkdz;
    }
    /**
     * 设置:户口邮编
     */
    public void setHkyb(String value) {
        this.hkyb = value;
    }
    /**
     * 获取:户口邮编
     */
    public String getHkyb() {
        return hkyb;
    }
    /**
     * 设置:联系人名
     */
    public void setLxrm(String value) {
        this.lxrm = value;
    }
    /**
     * 获取:联系人名
     */
    public String getLxrm() {
        return lxrm;
    }
    /**
     * 设置:联系人关系 | 与GY_DMZD（DMLB=4）对应
     */
    public void setLxgx(Integer value) {
        this.lxgx = value;
    }
    /**
     * 获取:联系人关系 | 与GY_DMZD（DMLB=4）对应
     */
    public Integer getLxgx() {
        return lxgx;
    }
    /**
     * 设置:联系地址
     */
    public void setLxdz(String value) {
        this.lxdz = value;
    }
    /**
     * 获取:联系地址
     */
    public String getLxdz() {
        return lxdz;
    }
    /**
     * 设置:联系电话
     */
    public void setLxdh(String value) {
        this.lxdh = value;
    }
    /**
     * 获取:联系电话
     */
    public String getLxdh() {
        return lxdh;
    }
    /**
     * 设置:凭证号码
     */
    public void setPzhm(String value) {
        this.pzhm = value;
    }
    /**
     * 获取:凭证号码
     */
    public String getPzhm() {
        return pzhm;
    }
    /**
     * 设置:社保号码 | 社会保险病人社保号码
     */
    public void setSbhm(String value) {
        this.sbhm = value;
    }
    /**
     * 获取:社保号码 | 社会保险病人社保号码
     */
    public String getSbhm() {
        return sbhm;
    }
    /**
     * 设置:担保人名
     */
    public void setDbrm(String value) {
        this.dbrm = value;
    }
    /**
     * 获取:担保人名
     */
    public String getDbrm() {
        return dbrm;
    }
    /**
     * 设置:担保关系
     */
    public void setDbgx(Integer value) {
        this.dbgx = value;
    }
    /**
     * 获取:担保关系
     */
    public Integer getDbgx() {
        return dbgx;
    }
    /**
     * 设置:在职退休 | 与GY_DMZD（DMLB=9）对应
     */
    public void setZztx(Integer value) {
        this.zztx = value;
    }
    /**
     * 获取:在职退休 | 与GY_DMZD（DMLB=9）对应
     */
    public Integer getZztx() {
        return zztx;
    }
    /**
     * 设置:大保病种
     */
    public void setDbbz(Integer value) {
        this.dbbz = value;
    }
    /**
     * 获取:大保病种
     */
    public Integer getDbbz() {
        return dbbz;
    }
    /**
     * 设置:病人科室 | 病人所在科室，对应GY_KSDM的代码
     */
    public void setBrks(Integer value) {
        this.brks = value;
    }
    /**
     * 获取:病人科室 | 病人所在科室，对应GY_KSDM的代码
     */
    public Integer getBrks() {
        return brks;
    }
    /**
     * 设置:病人病区 | 病人所在病区，对应GY_KSDM的代码
     */
    public void setBrbq(Integer value) {
        this.brbq = value;
    }
    /**
     * 获取:病人病区 | 病人所在病区，对应GY_KSDM的代码
     */
    public Integer getBrbq() {
        return brbq;
    }
    /**
     * 设置:病人床号
     */
    public void setBrch(String value) {
        this.brch = value;
    }
    /**
     * 获取:病人床号
     */
    public String getBrch() {
        return brch;
    }
    /**
     * 设置:登记日期 | 填写入院登记表的时间(系统自动填写)
     */
    public void setDjrq(Timestamp value) {
        this.djrq = value;
    }
    /**
     * 获取:登记日期 | 填写入院登记表的时间(系统自动填写)
     */
    public Timestamp getDjrq() {
        return djrq;
    }
    /**
     * 设置:入院日期 | 入院登记时填写的入院时间
     */
    public void setRyrq(Timestamp value) {
        this.ryrq = value;
    }
    /**
     * 获取:入院日期 | 入院登记时填写的入院时间
     */
    public Timestamp getRyrq() {
        return ryrq;
    }
    /**
     * 设置:出院日期 | 病区办理出院证明的日期(可以提前或推后)
     */
    public void setCyrq(Timestamp value) {
        this.cyrq = value;
    }
    /**
     * 获取:出院日期 | 病区办理出院证明的日期(可以提前或推后)
     */
    public Timestamp getCyrq() {
        return cyrq;
    }
    /**
     * 设置:出院判别 | 0：在院病人 1：出院证明 2：预结出院 8：正常出院 9：终结出院 99 注销出院
     */
    public void setCypb(Integer value) {
        this.cypb = value;
    }
    /**
     * 获取:出院判别 | 0：在院病人 1：出院证明 2：预结出院 8：正常出院 9：终结出院 99 注销出院
     */
    public Integer getCypb() {
        return cypb;
    }
    /**
     * 设置:出院方式 |与GY_DMZD（DMLB= 23）对应 1：治愈 2：好转 3：未愈 5：死亡 ：其他
     */
    public void setCyfs(Integer value) {
        this.cyfs = value;
    }
    /**
     * 获取:出院方式 |与GY_DMZD（DMLB= 23）对应 1：治愈 2：好转 3：未愈 5：死亡 ：其他
     */
    public Integer getCyfs() {
        return cyfs;
    }
    /**
     * 设置:操作工号
     */
    public void setCzgh(String value) {
        this.czgh = value;
    }
    /**
     * 获取:操作工号
     */
    public String getCzgh() {
        return czgh;
    }
    /**
     * 设置:入院情况 | 与GY_DMZD（DMLB=10）对应
     */
    public void setRyqk(Integer value) {
        this.ryqk = value;
    }
    /**
     * 获取:入院情况 | 与GY_DMZD（DMLB=10）对应
     */
    public Integer getRyqk() {
        return ryqk;
    }
    /**
     * 设置:病人情况 | 与GY_DMZD（DMLB=10）对应
     */
    public void setBrqk(Integer value) {
        this.brqk = value;
    }
    /**
     * 获取:病人情况 | 与GY_DMZD（DMLB=10）对应
     */
    public Integer getBrqk() {
        return brqk;
    }
    /**
     * 设置:护理级别 | 0：特级护理 1：一级护理 2：二级护理 3：三级护理
     */
    public void setHljb(Integer value) {
        this.hljb = value;
    }
    /**
     * 获取:护理级别 | 0：特级护理 1：一级护理 2：二级护理 3：三级护理
     */
    public Integer getHljb() {
        return hljb;
    }
    /**
     * 设置:饮食代码 | 与GY_DMZD（DMLB=20）对应
     */
    public void setYsdm(Integer value) {
        this.ysdm = value;
    }
    /**
     * 获取:饮食代码 | 与GY_DMZD（DMLB=20）对应
     */
    public Integer getYsdm() {
        return ysdm;
    }
    /**
     * 设置:病人血型 | 与GY_DMZD（DMLB=21）对应
     */
    public void setBrxx(Integer value) {
        this.brxx = value;
    }
    /**
     * 获取:病人血型 | 与GY_DMZD（DMLB=21）对应
     */
    public Integer getBrxx() {
        return brxx;
    }
    /**
     * 设置:会诊科室
     */
    public void setHzks(Integer value) {
        this.hzks = value;
    }
    /**
     * 获取:会诊科室
     */
    public Integer getHzks() {
        return hzks;
    }
    /**
     * 设置:借床科室
     */
    public void setJcks(Integer value) {
        this.jcks = value;
    }
    /**
     * 获取:借床科室
     */
    public Integer getJcks() {
        return jcks;
    }
    /**
     * 设置:门诊医生 | 对应GY_YGDM中的YGDM的代码
     */
    public void setMzys(String value) {
        this.mzys = value;
    }
    /**
     * 获取:门诊医生 | 对应GY_YGDM中的YGDM的代码
     */
    public String getMzys() {
        return mzys;
    }
    /**
     * 设置:住院医生 | 对应GY_YGDM中的YGDM的代码
     */
    public void setZyys(String value) {
        this.zyys = value;
    }
    /**
     * 获取:住院医生 | 对应GY_YGDM中的YGDM的代码
     */
    public String getZyys() {
        return zyys;
    }
    /**
     * 设置:主任医生 | 对应GY_YGDM中的YGDM的代码
     */
    public void setZsys(String value) {
        this.zsys = value;
    }
    /**
     * 获取:主任医生 | 对应GY_YGDM中的YGDM的代码
     */
    public String getZsys() {
        return zsys;
    }
    /**
     * 设置:主治医生 | 对应GY_YGDM中的YGDM的代码
     */
    public void setZzys(String value) {
        this.zzys = value;
    }
    /**
     * 获取:主治医生 | 对应GY_YGDM中的YGDM的代码
     */
    public String getZzys() {
        return zzys;
    }
    /**
     * 设置:确诊日期 | 病人确诊日期
     */
    public void setQzrq(Timestamp value) {
        this.qzrq = value;
    }
    /**
     * 获取:确诊日期 | 病人确诊日期
     */
    public Timestamp getQzrq() {
        return qzrq;
    }
    /**
     * 设置:开始日期 | 床位费累加的起始日期，第一次自动累加费用后修改该时间
     */
    public void setKsrq(Timestamp value) {
        this.ksrq = value;
    }
    /**
     * 获取:开始日期 | 床位费累加的起始日期，第一次自动累加费用后修改该时间
     */
    public Timestamp getKsrq() {
        return ksrq;
    }
    /**
     * 设置:结算日期 | 最后一次结算日期（包括作废）
     */
    public void setJsrq(Timestamp value) {
        this.jsrq = value;
    }
    /**
     * 获取:结算日期 | 最后一次结算日期（包括作废）
     */
    public Timestamp getJsrq() {
        return jsrq;
    }
    /**
     * 设置:结算次数 | 最后一次结算次数（包括作废） 下一次结算次数为当前值加1
     */
    public void setJscs(Integer value) {
        this.jscs = value;
    }
    /**
     * 获取:结算次数 | 最后一次结算次数（包括作废） 下一次结算次数为当前值加1
     */
    public Integer getJscs() {
        return jscs;
    }
    /**
     * 设置:结账日期 | 汇总日报结束时填写，若该病人已做预结则写该字段
     */
    public void setJzrq(Timestamp value) {
        this.jzrq = value;
    }
    /**
     * 获取:结账日期 | 汇总日报结束时填写，若该病人已做预结则写该字段
     */
    public Timestamp getJzrq() {
        return jzrq;
    }
    /**
     * 设置:汇总日期 | 汇总日报结束时填写，病人费用、医嘱转到出院表中，根据该字段可判断病人费用、医嘱在哪张表中
     */
    public void setHzrq(Timestamp value) {
        this.hzrq = value;
    }
    /**
     * 获取:汇总日期 | 汇总日报结束时填写，病人费用、医嘱转到出院表中，根据该字段可判断病人费用、医嘱在哪张表中
     */
    public Timestamp getHzrq() {
        return hzrq;
    }
    /**
     * 设置:修改判别 | 0：没有对病人操作 1：正在对该病人费用记帐 2：正在对该病人性质转换 9：正在对该病人结算处理 该字段已经作废不使用，开始使用的版本为bshrp4.3.01.3000
     */
    public void setXgpb(Integer value) {
        this.xgpb = value;
    }
    /**
     * 获取:修改判别 | 0：没有对病人操作 1：正在对该病人费用记帐 2：正在对该病人性质转换 9：正在对该病人结算处理 该字段已经作废不使用，开始使用的版本为bshrp4.3.01.3000
     */
    public Integer getXgpb() {
        return xgpb;
    }
    /**
     * 设置:病案判别 | 0：病人信息未转到病案系统 1：病人信息已转到病案系统
     */
    public void setBapb(Integer value) {
        this.bapb = value;
    }
    /**
     * 获取:病案判别 | 0：病人信息未转到病案系统 1：病人信息已转到病案系统
     */
    public Integer getBapb() {
        return bapb;
    }
    /**
     * 设置:病人关联
     */
    public void setBrgl(Integer value) {
        this.brgl = value;
    }
    /**
     * 获取:病人关联
     */
    public Integer getBrgl() {
        return brgl;
    }
    /**
     * 设置:病人口令
     */
    public void setBrkl(String value) {
        this.brkl = value;
    }
    /**
     * 获取:病人口令
     */
    public String getBrkl() {
        return brkl;
    }
    /**
     * 设置:病人全名
     */
    public void setBrqm(String value) {
        this.brqm = value;
    }
    /**
     * 获取:病人全名
     */
    public String getBrqm() {
        return brqm;
    }
    /**
     * 设置:家庭电话
     */
    public void setJtdh(String value) {
        this.jtdh = value;
    }
    /**
     * 获取:家庭电话
     */
    public String getJtdh() {
        return jtdh;
    }
    /**
     * 设置:医保卡号
     */
    public void setYbkh(String value) {
        this.ybkh = value;
    }
    /**
     * 获取:医保卡号
     */
    public String getYbkh() {
        return ybkh;
    }
    /**
     * 设置:就诊卡号
     */
    public void setJzkh(String value) {
        this.jzkh = value;
    }
    /**
     * 获取:就诊卡号
     */
    public String getJzkh() {
        return jzkh;
    }
    /**
     * 设置:主治医生 | 指接收病人的医生，对应GY_YGDM中的YGDM的代码
     */
    public void setSzys(String value) {
        this.szys = value;
    }
    /**
     * 获取:主治医生 | 指接收病人的医生，对应GY_YGDM中的YGDM的代码
     */
    public String getSzys() {
        return szys;
    }
    /**
     * 设置:诊疗小组 | 指负责病人治疗的医疗小组，对应ZY_ZLXZ.XZXH的代码
     */
    public void setZlxz(Integer value) {
        this.zlxz = value;
    }
    /**
     * 获取:诊疗小组 | 指负责病人治疗的医疗小组，对应ZY_ZLXZ.XZXH的代码
     */
    public Integer getZlxz() {
        return zlxz;
    }
    /**
     * 设置:审批金额 | 允许病人欠款的最大金额
     */
    public void setSpje(BigDecimal value) {
        this.spje = value;
    }
    /**
     * 获取:审批金额 | 允许病人欠款的最大金额
     */
    public BigDecimal getSpje() {
        return spje;
    }
    /**
     * 设置:数据转移 | 0 未转移 1 已转移
     */
    public void setSjzy(Integer value) {
        this.sjzy = value;
    }
    /**
     * 获取:数据转移 | 0 未转移 1 已转移
     */
    public Integer getSjzy() {
        return sjzy;
    }
    /**
     * 设置:冻结标志 | 0：未冻结      1：已冻结
     */
    public void setDjbz(Integer value) {
        this.djbz = value;
    }
    /**
     * 获取:冻结标志 | 0：未冻结      1：已冻结
     */
    public Integer getDjbz() {
        return djbz;
    }
    /**
     * 设置:冻结ID号 | 对应ZY_ZHDJ.DJID的代码
     */
    public void setDjid(Integer value) {
        this.djid = value;
    }
    /**
     * 获取:冻结ID号 | 对应ZY_ZHDJ.DJID的代码
     */
    public Integer getDjid() {
        return djid;
    }
    /**
     * 设置:冻结金额 | 达到冻结账户的金额值
     */
    public void setDjje(BigDecimal value) {
        this.djje = value;
    }
    /**
     * 获取:冻结金额 | 达到冻结账户的金额值
     */
    public BigDecimal getDjje() {
        return djje;
    }
    /**
     * 设置:YSJS
     */
    public void setYsjs(Integer value) {
        this.ysjs = value;
    }
    /**
     * 获取:YSJS
     */
    public Integer getYsjs() {
        return ysjs;
    }
    /**
     * 设置:转科状态
     */
    public void setZkzt(Integer value) {
        this.zkzt = value;
    }
    /**
     * 获取:转科状态
     */
    public Integer getZkzt() {
        return zkzt;
    }
    /**
     * 设置:重点患者判别 | 默认0 :不是重点患者   1:危重病人   2:手术后病人   3:新生儿病人,其高危险程度比较明显   4:疾病晚期的临终病人   5:急诊病人   6:社会上有影响有地位的人物   7:与医疗纠纷或司法案例有关的病人等
     */
    public void setZdhzpb(Integer value) {
        this.zdhzpb = value;
    }
    /**
     * 获取:重点患者判别 | 默认0 :不是重点患者   1:危重病人   2:手术后病人   3:新生儿病人,其高危险程度比较明显   4:疾病晚期的临终病人   5:急诊病人   6:社会上有影响有地位的人物   7:与医疗纠纷或司法案例有关的病人等
     */
    public Integer getZdhzpb() {
        return zdhzpb;
    }
    /**
     * 设置:备注
     */
    public void setBz(String value) {
        this.bz = value;
    }
    /**
     * 获取:备注
     */
    public String getBz() {
        return bz;
    }
    /**
     * 设置:出生地_省市区
     */
    public void setCsdSqs(Integer value) {
        this.csdSqs = value;
    }
    /**
     * 获取:出生地_省市区
     */
    public Integer getCsdSqs() {
        return csdSqs;
    }
    /**
     * 设置:出生地_市
     */
    public void setCsdS(Integer value) {
        this.csdS = value;
    }
    /**
     * 获取:出生地_市
     */
    public Integer getCsdS() {
        return csdS;
    }
    /**
     * 设置:出生地_县
     */
    public void setCsdX(Integer value) {
        this.csdX = value;
    }
    /**
     * 获取:出生地_县
     */
    public Integer getCsdX() {
        return csdX;
    }
    /**
     * 设置:籍贯代码_省区市
     */
    public void setJgdmSqs(Integer value) {
        this.jgdmSqs = value;
    }
    /**
     * 获取:籍贯代码_省区市
     */
    public Integer getJgdmSqs() {
        return jgdmSqs;
    }
    /**
     * 设置:籍贯代码_市
     */
    public void setJgdmS(Integer value) {
        this.jgdmS = value;
    }
    /**
     * 获取:籍贯代码_市
     */
    public Integer getJgdmS() {
        return jgdmS;
    }
    /**
     * 设置:籍贯代码_市
     */
    public void setXzzSqs(Integer value) {
        this.xzzSqs = value;
    }
    /**
     * 获取:籍贯代码_市
     */
    public Integer getXzzSqs() {
        return xzzSqs;
    }
    /**
     * 设置:现住址_市
     */
    public void setXzzS(Integer value) {
        this.xzzS = value;
    }
    /**
     * 获取:现住址_市
     */
    public Integer getXzzS() {
        return xzzS;
    }
    /**
     * 设置:现住址_县
     */
    public void setXzzX(Integer value) {
        this.xzzX = value;
    }
    /**
     * 获取:现住址_县
     */
    public Integer getXzzX() {
        return xzzX;
    }
    /**
     * 设置:现住址_邮编
     */
    public void setXzzYb(String value) {
        this.xzzYb = value;
    }
    /**
     * 获取:现住址_邮编
     */
    public String getXzzYb() {
        return xzzYb;
    }
    /**
     * 设置:现住址_电话
     */
    public void setXzzDh(String value) {
        this.xzzDh = value;
    }
    /**
     * 获取:现住址_电话
     */
    public String getXzzDh() {
        return xzzDh;
    }
    /**
     * 设置:户口地址_省区市
     */
    public void setHkdzSqs(Integer value) {
        this.hkdzSqs = value;
    }
    /**
     * 获取:户口地址_省区市
     */
    public Integer getHkdzSqs() {
        return hkdzSqs;
    }
    /**
     * 设置:户口地址_市
     */
    public void setHkdzS(Integer value) {
        this.hkdzS = value;
    }
    /**
     * 获取:户口地址_市
     */
    public Integer getHkdzS() {
        return hkdzS;
    }
    /**
     * 设置:户口地址_县
     */
    public void setHkdzX(Integer value) {
        this.hkdzX = value;
    }
    /**
     * 获取:户口地址_县
     */
    public Integer getHkdzX() {
        return hkdzX;
    }
    /**
     * 设置:现住址_其他地址
     */
    public void setXzzQtdz(String value) {
        this.xzzQtdz = value;
    }
    /**
     * 获取:现住址_其他地址
     */
    public String getXzzQtdz() {
        return xzzQtdz;
    }
    /**
     * 设置:户口地址_其他地址
     */
    public void setHkdzQtdz(String value) {
        this.hkdzQtdz = value;
    }
    /**
     * 获取:户口地址_其他地址
     */
    public String getHkdzQtdz() {
        return hkdzQtdz;
    }
    /**
     * 设置:调入标志 | 客户关系系统调入住院病人使用标志，1表示该病人已经导入，0则表示未导入客户关系
     */
    public void setDrbz(Integer value) {
        this.drbz = value;
    }
    /**
     * 获取:调入标志 | 客户关系系统调入住院病人使用标志，1表示该病人已经导入，0则表示未导入客户关系
     */
    public Integer getDrbz() {
        return drbz;
    }
    /**
     * 设置:入院年龄
     */
    public void setRynl(String value) {
        this.rynl = value;
    }
    /**
     * 获取:入院年龄
     */
    public String getRynl() {
        return rynl;
    }
    /**
     * 设置:住院次数，用于病案归档
     */
    public void setZycs(Integer value) {
        this.zycs = value;
    }
    /**
     * 获取:住院次数，用于病案归档
     */
    public Integer getZycs() {
        return zycs;
    }
    /**
     * 设置:入院诊断
     */
    public void setRyzd(String value) {
        this.ryzd = value;
    }
    /**
     * 获取:入院诊断
     */
    public String getRyzd() {
        return ryzd;
    }
    /**
     * 设置:目前诊断
     */
    public void setMqzd(String value) {
        this.mqzd = value;
    }
    /**
     * 获取:目前诊断
     */
    public String getMqzd() {
        return mqzd;
    }
    /**
     * 设置:主要诊断名称
     */
    public void setZyzd(String value) {
        this.zyzd = value;
    }
    /**
     * 获取:主要诊断名称
     */
    public String getZyzd() {
        return zyzd;
    }
    /**
     * 设置:门诊诊断编码
     */
    public void setMzzd(Integer value) {
        this.mzzd = value;
    }
    /**
     * 获取:门诊诊断编码
     */
    public Integer getMzzd() {
        return mzzd;
    }
    /**
     * 设置:zhbz
     */
    public void setZhbz(String value) {
        this.zhbz = value;
    }
    /**
     * 获取:zhbz
     */
    public String getZhbz() {
        return zhbz;
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
     * 设置:农合住院登记ID
     */
    public void setNhdjid(String value) {
        this.nhdjid = value;
    }
    /**
     * 获取:农合住院登记ID
     */
    public String getNhdjid() {
        return nhdjid;
    }
    /**
     * 设置:门诊类别
     */
    public void setMzlb(Integer value) {
        this.mzlb = value;
    }
    /**
     * 获取:门诊类别
     */
    public Integer getMzlb() {
        return mzlb;
    }
    /**
     * 设置:xzqhdm
     */
    public void setXzqhdm(String value) {
        this.xzqhdm = value;
    }
    /**
     * 获取:xzqhdm
     */
    public String getXzqhdm() {
        return xzqhdm;
    }
    /**
     * 设置:大病减负标志 1:尿毒症透析医疗费用,2:肾移植减负,3：精神病减负  其他：不减负
     */
    public void setDbjfbz(String value) {
        this.dbjfbz = value;
    }
    /**
     * 获取:大病减负标志 1:尿毒症透析医疗费用,2:肾移植减负,3：精神病减负  其他：不减负
     */
    public String getDbjfbz() {
        return dbjfbz;
    }

    public String getZjlx() {
        return zjlx;
    }

    public void setZjlx(String zjlx) {
        this.zjlx = zjlx;
    }
	public String getYwlx() {
		return ywlx;
	}
	public void setYwlx(String ywlx) {
		this.ywlx = ywlx;
	}
    
    
}
