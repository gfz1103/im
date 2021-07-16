package com.buit.cis.ims.request;

import com.buit.cis.dctwork.request.SkinPsjlReq;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;


/**
 * 
* @ClassName: ImHzryInfoReq
* @Description: TODO 住院病人入院传入参数
* @author 龚方舟
* @date 2020年5月25日 下午3:00:43
*
 */
@ApiModel(value="住院_病人入院_infoReq")
public class ImHzryInfoReq {
		
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
    @ApiModelProperty(value="病人性质 | 对应PUB_BRXZ中代码")
    private Integer brxz;
    @ApiModelProperty(value="病人姓名")
    private String brxm;
    @ApiModelProperty(value="病人性别")
    private Integer brxb;
    @ApiModelProperty(value="联系地址")
    private String lxdz;
    @ApiModelProperty(value="联系电话")
    private String lxdh;
    @ApiModelProperty(value="病人科室 | 病人所在科室，对应GY_KSDM的代码")
    private Integer brks;
    @ApiModelProperty(value="病人情况 | 与GY_DMZD（DMLB=10）对应")
    private Integer brqk;
    @ApiModelProperty(value="护理级别 | 0：特级护理 1：一级护理 2：二级护理 3：三级护理")
    private Integer hljb;
    @ApiModelProperty(value="饮食代码 | 与GY_DMZD（DMLB=20）对应")
    private Integer ysdm;
    @ApiModelProperty(value="病人血型 | 与GY_DMZD（DMLB=21）对应")
    private Integer brxx;
    @ApiModelProperty(value="门诊医生 | 对应GY_YGDM中的YGDM的代码")
    private String mzys;
    @ApiModelProperty(value="住院医生 | 对应GY_YGDM中的YGDM的代码")
    private String zyys;
    @ApiModelProperty(value="主任医生 | 对应GY_YGDM中的YGDM的代码")
    private String zsys;
    @ApiModelProperty(value="主治医生 | 对应GY_YGDM中的YGDM的代码")
    private String zzys;
    @ApiModelProperty(value="入院情况 | 与GY_DMZD（DMLB=10）对应")
    private Integer ryqk;
    @ApiModelProperty(value="入院诊断")
    private String ryzd;
    @ApiModelProperty(value="目前诊断")
    private String mqzd;
    @ApiModelProperty(value="副主任医师")
    private String fzrys;
    @ApiModelProperty(value="责任护士")
    private String zrhs;
    @ApiModelProperty(value="主管护士")
    private String zghs;

    @ApiModelProperty(value="诊断集合")
    private List<ImRyzdReq> imRyzdReqList;
    
    @ApiModelProperty(value="过敏药物集合")
    private List<SkinPsjlReq> skinPsjlReqList;
    
    @ApiModelProperty(value="打开方式")
    private String openBy;

	public Integer getZyh() {
		return zyh;
	}

	public void setZyh(Integer zyh) {
		this.zyh = zyh;
	}

	public Integer getJgid() {
		return jgid;
	}

	public void setJgid(Integer jgid) {
		this.jgid = jgid;
	}

	public Integer getBrid() {
		return brid;
	}

	public void setBrid(Integer brid) {
		this.brid = brid;
	}

	public String getZyhm() {
		return zyhm;
	}

	public void setZyhm(String zyhm) {
		this.zyhm = zyhm;
	}

	public String getBahm() {
		return bahm;
	}

	public void setBahm(String bahm) {
		this.bahm = bahm;
	}

	public Integer getBrxz() {
		return brxz;
	}

	public void setBrxz(Integer brxz) {
		this.brxz = brxz;
	}

	public String getBrxm() {
		return brxm;
	}

	public void setBrxm(String brxm) {
		this.brxm = brxm;
	}

	public Integer getBrxb() {
		return brxb;
	}

	public void setBrxb(Integer brxb) {
		this.brxb = brxb;
	}

	public String getLxdz() {
		return lxdz;
	}

	public void setLxdz(String lxdz) {
		this.lxdz = lxdz;
	}

	public String getLxdh() {
		return lxdh;
	}

	public void setLxdh(String lxdh) {
		this.lxdh = lxdh;
	}

	public Integer getBrks() {
		return brks;
	}

	public void setBrks(Integer brks) {
		this.brks = brks;
	}

	public Integer getBrqk() {
		return brqk;
	}

	public void setBrqk(Integer brqk) {
		this.brqk = brqk;
	}

	public Integer getHljb() {
		return hljb;
	}

	public void setHljb(Integer hljb) {
		this.hljb = hljb;
	}

	public Integer getYsdm() {
		return ysdm;
	}

	public void setYsdm(Integer ysdm) {
		this.ysdm = ysdm;
	}

	public Integer getBrxx() {
		return brxx;
	}

	public void setBrxx(Integer brxx) {
		this.brxx = brxx;
	}

	public String getMzys() {
		return mzys;
	}

	public void setMzys(String mzys) {
		this.mzys = mzys;
	}

	public String getZyys() {
		return zyys;
	}

	public void setZyys(String zyys) {
		this.zyys = zyys;
	}

	public String getZsys() {
		return zsys;
	}

	public void setZsys(String zsys) {
		this.zsys = zsys;
	}

	public String getZzys() {
		return zzys;
	}

	public void setZzys(String zzys) {
		this.zzys = zzys;
	}

	public Integer getRyqk() {
		return ryqk;
	}

	public void setRyqk(Integer ryqk) {
		this.ryqk = ryqk;
	}

	public String getRyzd() {
		return ryzd;
	}

	public void setRyzd(String ryzd) {
		this.ryzd = ryzd;
	}

	public String getMqzd() {
		return mqzd;
	}

	public void setMqzd(String mqzd) {
		this.mqzd = mqzd;
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

	public List<ImRyzdReq> getImRyzdReqList() {
		return imRyzdReqList;
	}

	public void setImRyzdReqList(List<ImRyzdReq> imRyzdReqList) {
		this.imRyzdReqList = imRyzdReqList;
	}

	public List<SkinPsjlReq> getSkinPsjlReqList() {
		return skinPsjlReqList;
	}

	public void setSkinPsjlReqList(List<SkinPsjlReq> skinPsjlReqList) {
		this.skinPsjlReqList = skinPsjlReqList;
	}

	public String getOpenBy() {
		return openBy;
	}

	public void setOpenBy(String openBy) {
		this.openBy = openBy;
	}
    
    
}
