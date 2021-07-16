   
package com.buit.cis.dctwork.response;

import java.sql.Timestamp;

import com.buit.commons.PageQuery;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


/**
 * 类名称：CisJbzmd<br> 
 * 类描述：疾病证明单<br>
 * @author GONGFANGZHOU
 */
@ApiModel(value="疾病证明单_Resp")
public class CisJbzmdResp  extends  PageQuery{
    @ApiModelProperty(value="记录序号(主键)")
    private Integer jlxh;
    @ApiModelProperty(value="住院号")
    private Integer zyh;
    @ApiModelProperty(value="门诊号码")
    private String mzhm;
    @ApiModelProperty(value="工作单位/家庭地址")
    private String jtdz;
    @ApiModelProperty(value="建议")
    private String jy;
    @ApiModelProperty(value="联系电话")
    private String lxdh;
    @ApiModelProperty(value="诊治医生")
    private Integer zzys;
    @ApiModelProperty(value="开具日期")
    private Timestamp kjrq;
    @ApiModelProperty(value="病人姓名")
    private String brxm;
    @ApiModelProperty(value="病人性别")
    private Integer brxb;
    @ApiModelProperty(value="身份证号")
    private String sfzh;
    @ApiModelProperty(value="入院日期")
    private Timestamp ryrq;
    @ApiModelProperty(value="就诊卡号")
    private String jzkh;
    @ApiModelProperty(value="诊断名称")
    private String zdmc;
	public Integer getJlxh() {
		return jlxh;
	}
	public void setJlxh(Integer jlxh) {
		this.jlxh = jlxh;
	}
	public Integer getZyh() {
		return zyh;
	}
	public void setZyh(Integer zyh) {
		this.zyh = zyh;
	}
	public String getMzhm() {
		return mzhm;
	}
	public void setMzhm(String mzhm) {
		this.mzhm = mzhm;
	}
	public String getJtdz() {
		return jtdz;
	}
	public void setJtdz(String jtdz) {
		this.jtdz = jtdz;
	}
	public String getJy() {
		return jy;
	}
	public void setJy(String jy) {
		this.jy = jy;
	}
	public String getLxdh() {
		return lxdh;
	}
	public void setLxdh(String lxdh) {
		this.lxdh = lxdh;
	}
	public Integer getZzys() {
		return zzys;
	}
	public void setZzys(Integer zzys) {
		this.zzys = zzys;
	}
	public Timestamp getKjrq() {
		return kjrq;
	}
	public void setKjrq(Timestamp kjrq) {
		this.kjrq = kjrq;
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
	public String getSfzh() {
		return sfzh;
	}
	public void setSfzh(String sfzh) {
		this.sfzh = sfzh;
	}
	public Timestamp getRyrq() {
		return ryrq;
	}
	public void setRyrq(Timestamp ryrq) {
		this.ryrq = ryrq;
	}
	public String getJzkh() {
		return jzkh;
	}
	public void setJzkh(String jzkh) {
		this.jzkh = jzkh;
	}
	public String getZdmc() {
		return zdmc;
	}
	public void setZdmc(String zdmc) {
		this.zdmc = zdmc;
	}
   
    
}