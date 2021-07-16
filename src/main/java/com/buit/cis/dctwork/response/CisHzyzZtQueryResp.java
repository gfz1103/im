   
package com.buit.cis.dctwork.response;

import java.sql.Timestamp;

import com.buit.commons.PageQuery;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


/**
 * 类名称：CisHzyzZtQueryResp<br> 
 * 类描述：住院_检验申请单<br>
 * @author GONGFANGZHOU
 */
@ApiModel(value="住院_检验申请单_queryResp")
public class CisHzyzZtQueryResp extends PageQuery{	
  
	@ApiModelProperty(value="执行科室")
    private Integer zxks;
	
	@ApiModelProperty(value="病人床号")
    private String brch;
	
	@ApiModelProperty(value = "病人姓名")
    private String brxm;
	
	@ApiModelProperty(value="住院号码")
    private String zyhm;

	@ApiModelProperty(value="开单时间")
    private Timestamp kssj;
	
	@ApiModelProperty(value="申请医师")
    private String ysgh;
	
	@ApiModelProperty(value="检查项目")
    private String yzmc;
    
	@ApiModelProperty(value="报告时间")
    private Timestamp bgsj;
	
	@ApiModelProperty(value="作废标志(1:是0:否)")
    private Integer zfbz;

	public Integer getZxks() {
		return zxks;
	}

	public void setZxks(Integer zxks) {
		this.zxks = zxks;
	}

	public String getBrch() {
		return brch;
	}

	public void setBrch(String brch) {
		this.brch = brch;
	}

	public String getBrxm() {
		return brxm;
	}

	public void setBrxm(String brxm) {
		this.brxm = brxm;
	}

	public String getZyhm() {
		return zyhm;
	}

	public void setZyhm(String zyhm) {
		this.zyhm = zyhm;
	}

	public Timestamp getKssj() {
		return kssj;
	}

	public void setKssj(Timestamp kssj) {
		this.kssj = kssj;
	}

	public String getYsgh() {
		return ysgh;
	}

	public void setYsgh(String ysgh) {
		this.ysgh = ysgh;
	}

	public String getYzmc() {
		return yzmc;
	}

	public void setYzmc(String yzmc) {
		this.yzmc = yzmc;
	}

	public Timestamp getBgsj() {
		return bgsj;
	}

	public void setBgsj(Timestamp bgsj) {
		this.bgsj = bgsj;
	}

	public Integer getZfbz() {
		return zfbz;
	}

	public void setZfbz(Integer zfbz) {
		this.zfbz = zfbz;
	}
	
	
}
