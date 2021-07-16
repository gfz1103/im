package com.buit.cis.ims.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;

/**
 * zhouhaisheng
 * 入院登记保存入参
 */
@ApiModel("入院登记保存入参")
public class RydjSaveReq {
    @ApiModelProperty(value = "联系地址")
    private String lxdz;
    @ApiModelProperty(value = "冻结ID号 | 对应ZY_ZHDJ.DJID的代码")
    private Integer djid;
    @ApiModelProperty(value = "出生地_省市区")
    private Integer csdSqs;
    @ApiModelProperty(value = "入院情况 DIC_GBSJ01:PD0000000727")
    private Integer ryqk;
    @ApiModelProperty(value = "操作工号")
    private String czgh;
    @ApiModelProperty(value = "修改判别 | 0：没有对病人操作\\n1：正在对该病人费用记帐\\n2：正在对该病人性质转换\\n9：正在对该病人结算处理\\n该字段已经作废不使用，开始使用的版本为bshrp4.3.01.3000")
    private Integer xgpb;
    @ApiModelProperty(value = "国籍代码 DIC_GBSJ01:PD0000000271")
    private String gjdm;
    @ApiModelProperty(value = "现住址_其他地址")
    private String xzzQtdz;
    @ApiModelProperty(value = "医保卡号")
    private String ybkh;
    @ApiModelProperty(value = "就诊卡号")
    private String jzkh;
    @ApiModelProperty(value = "")
    private String zphm;
    @ApiModelProperty(value = "籍贯代码_市")
    private Integer jgdmS;
    @ApiModelProperty(value = "入院诊断")
    private String ryzd;
    @ApiModelProperty(value = "ICD10编码")
    private String icd10bm;
    @ApiModelProperty(value = "籍贯代码_省区市")
    private Integer jgdmSqs;
    @ApiModelProperty(value = "病案号码")
    private String bahm;
    @ApiModelProperty(value = "缴款金额")
    private String jkje;
    @ApiModelProperty(value = "诊疗小组 | 指负责病人治疗的医疗小组，对应ZY_ZLXZ.XZXH的代码")
    private Integer zlxz;
    @ApiModelProperty(value = "")
    private String xxdm;
    @ApiModelProperty(value = "婚姻状况 DIC_GBSJ01:PD0000000137")
    private Integer hyzk;
    @ApiModelProperty(value = "职业代码 DIC_GBSJ01:PD0000000432")
    private String zydm;
    @ApiModelProperty(value = "门诊医生 | 对应GY_YGDM中的YGDM的代码")
    private String mzys;
    @ApiModelProperty(value = "籍贯代码")
    private Integer jgdm;
    @ApiModelProperty(value = "出生年月")
    private String csny;
    @ApiModelProperty(value = "病人性别 DIC_GBSJ01:PD0000000260")
    private Integer brxb;
    @ApiModelProperty(value = "")
    private String gmyw;
    @ApiModelProperty(value = "异地医保")
    private String xzqhdm;
    @ApiModelProperty(value = "病人科室 | 病人所在科室，对应GY_KSDM的代码  sys_dict_config:15")
    private Integer brks;
    @ApiModelProperty(value = "病人床号")
    private String brch;
    @ApiModelProperty(value = "结账日期 | 汇总日报结束时填写，若该病人已做预结则写该字段")
    private String jzrq;
    @ApiModelProperty(value = "住院号码")
    private String zyhm;
    @ApiModelProperty(value = "修改时间")
    private String xgsj;
    @ApiModelProperty(value = "建档时间")
    private String jdsj;
    @ApiModelProperty(value = "联系电话")
    private String lxdh;
    @ApiModelProperty(value = "住院医生 | 对应GY_YGDM中的YGDM的代码 sys_dict_config:19")
    private String zyys;
    @ApiModelProperty(value = "门诊住院登记登记id")
    private Integer opZydjDjid;
    @ApiModelProperty(value = "主任医生 | 对应GY_YGDM中的YGDM的代码 sys_dict_config:19")
    private String zsys;
    @ApiModelProperty(value = "单位编号 | 对应与gy_cydw")
    private Integer dwbh;
    @ApiModelProperty(value = "省份代码")
    private Integer sfdm;
    @ApiModelProperty(value = "")
    private String sjhm;
    @ApiModelProperty(value = "机构代码")
    private Integer jgid;
    @ApiModelProperty(value = "")
    private String zxbz;
    @ApiModelProperty(value = "户口地址_省区市")
    private Integer hkdzSqs;
    @ApiModelProperty(value = "")
    private String zxsj;
    @ApiModelProperty(value = "现住址_电话")
    private String xzzDh;
    @ApiModelProperty(value = "单位名称")
    private String dwmc;
    @ApiModelProperty(value = "大保病种")
    private Integer dbbz;
    @ApiModelProperty(value = "户口地址_其他地址")
    private String hkdzQtdz;
    @ApiModelProperty(value = "病人性质 | 对应PUB_BRXZ中代码 sys_dict_config:14")
    private Integer brxz;
    @ApiModelProperty(value = "病人血型 | 与GY_DMZD（DMLB=21）对应")
    private String brxx;
    @ApiModelProperty(value = "入院次数")
    private String rycs;
    @ApiModelProperty(value = "民族代码 DIC_GBSJ01:PD0000000215")
    private String mzdm;
    @ApiModelProperty(value = "建档机构")
    private String jdjg;
    @ApiModelProperty(value = "")
    private String zxr;
    @ApiModelProperty(value = "pos付款")
    private Object pos;
    @ApiModelProperty(value = "门诊号码")
    private String mzhm;
    @ApiModelProperty(value = "籍贯代码_市")
    private Integer xzzSqs;
    @ApiModelProperty(value = "现住址_邮编")
    private String xzzYb;
    @ApiModelProperty(value = "")
    private String jdr;
    @ApiModelProperty(value = "病人姓名")
    private String brxm;
    @ApiModelProperty(value = "结算次数 | 最后一次结算次数（包括作废）\\n下一次结算次数为当前值加1\\n")
    private Integer jscs;
    @ApiModelProperty(value = "")
    private String empiid;
    @ApiModelProperty(value = "大病减负标志 0：否 1：是")
    private String dbjfbz;
    @ApiModelProperty(value = "联系人关系 DIC_GBSJ01:PD0000000143")
    private Integer lxgx;
    @ApiModelProperty(value = "")
    private String fyzh;
    @ApiModelProperty(value = "入院日期 | 入院登记时填写的入院时间")
    private String ryrq;
    @ApiModelProperty(value = "冻结金额 | 达到冻结账户的金额值")
    private BigDecimal djje;
    @ApiModelProperty(value = "就诊次数")
    private String jzcs;
    @ApiModelProperty(value = "")
    private String dwxh;
    @ApiModelProperty(value = "入院年龄")
    private String rynl;
    @ApiModelProperty(value = "联系人名")
    private String lxrm;
    @ApiModelProperty(value = "工作单位")
    private String gzdw;
    @ApiModelProperty(value="证件类型 DIC_GBSJ01:PD0000000260")
    private String zjlx;
    @ApiModelProperty(value = "身份证号")
    private String sfzh;
    @ApiModelProperty(value = "")
    private String czrq;
    @ApiModelProperty(value = "户口地址_县")
    private Integer hkdzX;
    @ApiModelProperty(value = "家庭电话")
    private String jtdh;
    @ApiModelProperty(value = "缴款方式 sys_dict_config:20")
    private Integer jkfs;
    @ApiModelProperty(value = "户口邮编")
    private String hkyb;
    @ApiModelProperty(value = "在职退休 | 与GY_DMZD（DMLB=9）对应")
    private Integer zztx;
    @ApiModelProperty(value = "籍贯代码_市")
    private Integer xzzS;
    @ApiModelProperty(value = "病案判别 | 0：病人信息未转到病案系统\\n1：病人信息已转到病案系统\\n")
    private Integer bapb;
    @ApiModelProperty(value = "担保关系")
    private Integer dbgx;
    @ApiModelProperty(value = "现住址_县")
    private Integer xzzX;
    @ApiModelProperty(value = "户口地址_市")
    private Integer hkdzS;
    @ApiModelProperty(value = "病人ID")
    private Integer brid;
    @ApiModelProperty(value = "单位电话")
    private String dwdh;
    @ApiModelProperty(value = "出院判别 | 0：在院病人\\n1：出院证明\\n2：预结出院\\n8：正常出院\\n9：终结出院\\n99 注销出院")
    private Integer cypb;
    @ApiModelProperty(value = "户口地址")
    private String hkdz;
    @ApiModelProperty(value = "")
    private String jmbz;
    @ApiModelProperty(value = "出生地_县")
    private Integer csdX;
    @ApiModelProperty(value = "单位邮编")
    private String dwyb;
    @ApiModelProperty(value = "出生地_市")
    private Integer csdS;
    @ApiModelProperty(value = "冻结标志 | 0：未冻结  1：已冻结")
    private Integer djbz;
    @ApiModelProperty(value = "收治医生")
    private String szys;
    @ApiModelProperty(value = "病人病区")
    private Integer brbq;
    @ApiModelProperty(value = "是否医保")
    private String isYb;
    @ApiModelProperty(value = "卡类型")
    private String cardtype;
    @ApiModelProperty(value = "医保卡号")
    private String carddata;
    @ApiModelProperty(value = "床位预约id")
    private Integer cwyyid;
    @ApiModelProperty(value = "业务类型 1：住院 2：留观")
    private String ywlx;
    @ApiModelProperty(value = "主治医生 | 对应GY_YGDM中的YGDM的代码 sys_dict_config:19")
    private String zzys;
    @ApiModelProperty(value = "大病项目")
    private String dbtype;
    @ApiModelProperty(value = "工伤认定号")
    private String gsrdh;
    @ApiModelProperty(value = "是否工伤")
    private String isGs;
    @ApiModelProperty(value = "是否大病")
    private String isDb;
    @ApiModelProperty(value = "入院途径 | 1：门诊转入    2：急诊转入    3：转院转入    4：其他")
    private Integer rytj;

    public String getLxdz() {
        return lxdz;
    }

    public void setLxdz(String lxdz) {
        this.lxdz = lxdz;
    }

    public Integer getDjid() {
        return djid;
    }

    public void setDjid(Integer djid) {
        this.djid = djid;
    }

    public Integer getCsdSqs() {
        return csdSqs;
    }

    public void setCsdSqs(Integer csdSqs) {
        this.csdSqs = csdSqs;
    }

    public Integer getRyqk() {
        return ryqk;
    }

    public void setRyqk(Integer ryqk) {
        this.ryqk = ryqk;
    }

    public String getCzgh() {
        return czgh;
    }

    public void setCzgh(String czgh) {
        this.czgh = czgh;
    }

    public Integer getXgpb() {
        return xgpb;
    }

    public void setXgpb(Integer xgpb) {
        this.xgpb = xgpb;
    }

    public String getGjdm() {
        return gjdm;
    }

    public void setGjdm(String gjdm) {
        this.gjdm = gjdm;
    }

    public String getXzzQtdz() {
        return xzzQtdz;
    }

    public void setXzzQtdz(String xzzQtdz) {
        this.xzzQtdz = xzzQtdz;
    }

    public String getYbkh() {
        return ybkh;
    }

    public void setYbkh(String ybkh) {
        this.ybkh = ybkh;
    }

    public String getJzkh() {
        return jzkh;
    }

    public void setJzkh(String jzkh) {
        this.jzkh = jzkh;
    }

    public String getZphm() {
        return zphm;
    }

    public void setZphm(String zphm) {
        this.zphm = zphm;
    }

    public Integer getJgdmS() {
        return jgdmS;
    }

    public void setJgdmS(Integer jgdmS) {
        this.jgdmS = jgdmS;
    }

    public String getRyzd() {
        return ryzd;
    }

    public void setRyzd(String ryzd) {
        this.ryzd = ryzd;
    }

    public Integer getJgdmSqs() {
        return jgdmSqs;
    }

    public void setJgdmSqs(Integer jgdmSqs) {
        this.jgdmSqs = jgdmSqs;
    }

    public String getBahm() {
        return bahm;
    }

    public void setBahm(String bahm) {
        this.bahm = bahm;
    }

    public String getJkje() {
        return jkje;
    }

    public void setJkje(String jkje) {
        this.jkje = jkje;
    }

    public Integer getZlxz() {
        return zlxz;
    }

    public void setZlxz(Integer zlxz) {
        this.zlxz = zlxz;
    }

    public String getXxdm() {
        return xxdm;
    }

    public void setXxdm(String xxdm) {
        this.xxdm = xxdm;
    }

    public Integer getHyzk() {
        return hyzk;
    }

    public void setHyzk(Integer hyzk) {
        this.hyzk = hyzk;
    }

    public String getZydm() {
        return zydm;
    }

    public void setZydm(String zydm) {
        this.zydm = zydm;
    }

    public String getMzys() {
        return mzys;
    }

    public void setMzys(String mzys) {
        this.mzys = mzys;
    }

    public Integer getJgdm() {
        return jgdm;
    }

    public void setJgdm(Integer jgdm) {
        this.jgdm = jgdm;
    }

    public String getCsny() {
        return csny;
    }

    public void setCsny(String csny) {
        this.csny = csny;
    }

    public Integer getBrxb() {
        return brxb;
    }

    public void setBrxb(Integer brxb) {
        this.brxb = brxb;
    }

    public String getGmyw() {
        return gmyw;
    }

    public void setGmyw(String gmyw) {
        this.gmyw = gmyw;
    }

    public String getXzqhdm() {
        return xzqhdm;
    }

    public void setXzqhdm(String xzqhdm) {
        this.xzqhdm = xzqhdm;
    }

    public Integer getBrks() {
        return brks;
    }

    public void setBrks(Integer brks) {
        this.brks = brks;
    }

    public String getBrch() {
        return brch;
    }

    public void setBrch(String brch) {
        this.brch = brch;
    }

    public String getJzrq() {
        return jzrq;
    }

    public void setJzrq(String jzrq) {
        this.jzrq = jzrq;
    }

    public String getZyhm() {
        return zyhm;
    }

    public void setZyhm(String zyhm) {
        this.zyhm = zyhm;
    }

    public String getXgsj() {
        return xgsj;
    }

    public void setXgsj(String xgsj) {
        this.xgsj = xgsj;
    }

    public String getJdsj() {
        return jdsj;
    }

    public void setJdsj(String jdsj) {
        this.jdsj = jdsj;
    }

    public String getLxdh() {
        return lxdh;
    }

    public void setLxdh(String lxdh) {
        this.lxdh = lxdh;
    }

    public String getZyys() {
        return zyys;
    }

    public void setZyys(String zyys) {
        this.zyys = zyys;
    }

    public Integer getOpZydjDjid() {
        return opZydjDjid;
    }

    public void setOpZydjDjid(Integer opZydjDjid) {
        this.opZydjDjid = opZydjDjid;
    }

    public String getZsys() {
        return zsys;
    }

    public void setZsys(String zsys) {
        this.zsys = zsys;
    }

    public Integer getDwbh() {
        return dwbh;
    }

    public void setDwbh(Integer dwbh) {
        this.dwbh = dwbh;
    }

    public Integer getSfdm() {
        return sfdm;
    }

    public void setSfdm(Integer sfdm) {
        this.sfdm = sfdm;
    }

    public String getSjhm() {
        return sjhm;
    }

    public void setSjhm(String sjhm) {
        this.sjhm = sjhm;
    }

    public Integer getJgid() {
        return jgid;
    }

    public void setJgid(Integer jgid) {
        this.jgid = jgid;
    }

    public String getZxbz() {
        return zxbz;
    }

    public void setZxbz(String zxbz) {
        this.zxbz = zxbz;
    }

    public Integer getHkdzSqs() {
        return hkdzSqs;
    }

    public void setHkdzSqs(Integer hkdzSqs) {
        this.hkdzSqs = hkdzSqs;
    }

    public String getZxsj() {
        return zxsj;
    }

    public void setZxsj(String zxsj) {
        this.zxsj = zxsj;
    }

    public String getXzzDh() {
        return xzzDh;
    }

    public void setXzzDh(String xzzDh) {
        this.xzzDh = xzzDh;
    }

    public String getDwmc() {
        return dwmc;
    }

    public void setDwmc(String dwmc) {
        this.dwmc = dwmc;
    }

    public Integer getDbbz() {
        return dbbz;
    }

    public void setDbbz(Integer dbbz) {
        this.dbbz = dbbz;
    }

    public String getHkdzQtdz() {
        return hkdzQtdz;
    }

    public void setHkdzQtdz(String hkdzQtdz) {
        this.hkdzQtdz = hkdzQtdz;
    }

    public Integer getBrxz() {
        return brxz;
    }

    public void setBrxz(Integer brxz) {
        this.brxz = brxz;
    }

    public String getBrxx() {
        return brxx;
    }

    public void setBrxx(String brxx) {
        this.brxx = brxx;
    }

    public String getRycs() {
        return rycs;
    }

    public void setRycs(String rycs) {
        this.rycs = rycs;
    }

    public String getMzdm() {
        return mzdm;
    }

    public void setMzdm(String mzdm) {
        this.mzdm = mzdm;
    }

    public String getJdjg() {
        return jdjg;
    }

    public void setJdjg(String jdjg) {
        this.jdjg = jdjg;
    }

    public String getZxr() {
        return zxr;
    }

    public void setZxr(String zxr) {
        this.zxr = zxr;
    }

    public Object getPos() {
        return pos;
    }

    public void setPos(Object pos) {
        this.pos = pos;
    }

    public String getMzhm() {
        return mzhm;
    }

    public void setMzhm(String mzhm) {
        this.mzhm = mzhm;
    }

    public Integer getXzzSqs() {
        return xzzSqs;
    }

    public void setXzzSqs(Integer xzzSqs) {
        this.xzzSqs = xzzSqs;
    }

    public String getXzzYb() {
        return xzzYb;
    }

    public void setXzzYb(String xzzYb) {
        this.xzzYb = xzzYb;
    }

    public String getJdr() {
        return jdr;
    }

    public void setJdr(String jdr) {
        this.jdr = jdr;
    }

    public String getBrxm() {
        return brxm;
    }

    public void setBrxm(String brxm) {
        this.brxm = brxm;
    }

    public Integer getJscs() {
        return jscs;
    }

    public void setJscs(Integer jscs) {
        this.jscs = jscs;
    }

    public String getEmpiid() {
        return empiid;
    }

    public void setEmpiid(String empiid) {
        this.empiid = empiid;
    }

    public String getDbjfbz() {
        return dbjfbz;
    }

    public void setDbjfbz(String dbjfbz) {
        this.dbjfbz = dbjfbz;
    }

    public Integer getLxgx() {
        return lxgx;
    }

    public void setLxgx(Integer lxgx) {
        this.lxgx = lxgx;
    }

    public String getFyzh() {
        return fyzh;
    }

    public void setFyzh(String fyzh) {
        this.fyzh = fyzh;
    }

    public String getRyrq() {
        return ryrq;
    }

    public void setRyrq(String ryrq) {
        this.ryrq = ryrq;
    }

    public BigDecimal getDjje() {
        return djje;
    }

    public void setDjje(BigDecimal djje) {
        this.djje = djje;
    }

    public String getJzcs() {
        return jzcs;
    }

    public void setJzcs(String jzcs) {
        this.jzcs = jzcs;
    }

    public String getDwxh() {
        return dwxh;
    }

    public void setDwxh(String dwxh) {
        this.dwxh = dwxh;
    }

    public String getRynl() {
        return rynl;
    }

    public void setRynl(String rynl) {
        this.rynl = rynl;
    }

    public String getLxrm() {
        return lxrm;
    }

    public void setLxrm(String lxrm) {
        this.lxrm = lxrm;
    }

    public String getGzdw() {
        return gzdw;
    }

    public void setGzdw(String gzdw) {
        this.gzdw = gzdw;
    }

    public String getZjlx() {
        return zjlx;
    }

    public void setZjlx(String zjlx) {
        this.zjlx = zjlx;
    }

    public String getSfzh() {
        return sfzh;
    }

    public void setSfzh(String sfzh) {
        this.sfzh = sfzh;
    }

    public String getCzrq() {
        return czrq;
    }

    public void setCzrq(String czrq) {
        this.czrq = czrq;
    }

    public Integer getHkdzX() {
        return hkdzX;
    }

    public void setHkdzX(Integer hkdzX) {
        this.hkdzX = hkdzX;
    }

    public String getJtdh() {
        return jtdh;
    }

    public void setJtdh(String jtdh) {
        this.jtdh = jtdh;
    }

    public Integer getJkfs() {
        return jkfs;
    }

    public void setJkfs(Integer jkfs) {
        this.jkfs = jkfs;
    }

    public String getHkyb() {
        return hkyb;
    }

    public void setHkyb(String hkyb) {
        this.hkyb = hkyb;
    }

    public Integer getZztx() {
        return zztx;
    }

    public void setZztx(Integer zztx) {
        this.zztx = zztx;
    }

    public Integer getXzzS() {
        return xzzS;
    }

    public void setXzzS(Integer xzzS) {
        this.xzzS = xzzS;
    }

    public Integer getBapb() {
        return bapb;
    }

    public void setBapb(Integer bapb) {
        this.bapb = bapb;
    }

    public Integer getDbgx() {
        return dbgx;
    }

    public void setDbgx(Integer dbgx) {
        this.dbgx = dbgx;
    }

    public Integer getXzzX() {
        return xzzX;
    }

    public void setXzzX(Integer xzzX) {
        this.xzzX = xzzX;
    }

    public Integer getHkdzS() {
        return hkdzS;
    }

    public void setHkdzS(Integer hkdzS) {
        this.hkdzS = hkdzS;
    }

    public Integer getBrid() {
        return brid;
    }

    public void setBrid(Integer brid) {
        this.brid = brid;
    }

    public String getDwdh() {
        return dwdh;
    }

    public void setDwdh(String dwdh) {
        this.dwdh = dwdh;
    }

    public Integer getCypb() {
        return cypb;
    }

    public void setCypb(Integer cypb) {
        this.cypb = cypb;
    }

    public String getHkdz() {
        return hkdz;
    }

    public void setHkdz(String hkdz) {
        this.hkdz = hkdz;
    }

    public String getJmbz() {
        return jmbz;
    }

    public void setJmbz(String jmbz) {
        this.jmbz = jmbz;
    }

    public Integer getCsdX() {
        return csdX;
    }

    public void setCsdX(Integer csdX) {
        this.csdX = csdX;
    }

    public String getDwyb() {
        return dwyb;
    }

    public void setDwyb(String dwyb) {
        this.dwyb = dwyb;
    }

    public Integer getCsdS() {
        return csdS;
    }

    public void setCsdS(Integer csdS) {
        this.csdS = csdS;
    }

    public Integer getDjbz() {
        return djbz;
    }

    public void setDjbz(Integer djbz) {
        this.djbz = djbz;
    }

    public String getSzys() {
        return szys;
    }

    public void setSzys(String szys) {
        this.szys = szys;
    }

    public Integer getBrbq() {
        return brbq;
    }

    public void setBrbq(Integer brbq) {
        this.brbq = brbq;
    }

    public String getIsYb() {
        return isYb;
    }

    public void setIsYb(String isYb) {
        this.isYb = isYb;
    }

    public String getCardtype() {
        return cardtype;
    }

    public void setCardtype(String cardtype) {
        this.cardtype = cardtype;
    }

    public Integer getCwyyid() {
        return cwyyid;
    }

    public void setCwyyid(Integer cwyyid) {
        this.cwyyid = cwyyid;
    }

    public String getYwlx() {
        return ywlx;
    }

    public void setYwlx(String ywlx) {
        this.ywlx = ywlx;
    }

    public String getCarddata() {
        return carddata;
    }

    public void setCarddata(String carddata) {
        this.carddata = carddata;
    }

	public String getZzys() {
		return zzys;
	}

	public void setZzys(String zzys) {
		this.zzys = zzys;
	}

    public String getIcd10bm() {
        return icd10bm;
    }

    public void setIcd10bm(String icd10bm) {
        this.icd10bm = icd10bm;
    }

    public String getDbtype() {
        return dbtype;
    }

    public void setDbtype(String dbtype) {
        this.dbtype = dbtype;
    }

    public String getGsrdh() {
        return gsrdh;
    }

    public void setGsrdh(String gsrdh) {
        this.gsrdh = gsrdh;
    }

    public String getIsGs() {
        return isGs;
    }

    public void setIsGs(String isGs) {
        this.isGs = isGs;
    }

    public String getIsDb() {
        return isDb;
    }

    public void setIsDb(String isDb) {
        this.isDb = isDb;
    }

    public Integer getRytj() {
        return rytj;
    }

    public void setRytj(Integer rytj) {
        this.rytj = rytj;
    }
}
