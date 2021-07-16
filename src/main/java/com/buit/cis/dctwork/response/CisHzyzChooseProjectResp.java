   
package com.buit.cis.dctwork.response;

import java.math.BigDecimal;
import java.sql.Timestamp;

import com.buit.commons.PageQuery;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


/**
 * 类名称：CisHzyzChooseProjectResp<br> 
 * 类描述：住院_病区项目组套选择单条<br>
 * @author GONGFANGZHOU
 */
@ApiModel(value="住院_病区项目医嘱_chooseprojectResp")
public class CisHzyzChooseProjectResp {

    @ApiModelProperty(value="费用序号")
	private Integer fyxh;
    
    @ApiModelProperty(value="项目名称")
	private String yzmc;
    
    @ApiModelProperty(value="项目数量")
	private Integer xmsl;
    
    @ApiModelProperty(value="药品组号")
	private Integer ypzh;
    
    @ApiModelProperty(value="费用单价")
	private BigDecimal fydj;
    
    @ApiModelProperty(value="费用归并")
	private Integer fygb;
    
    @ApiModelProperty(value="费用单位")
	private String fydw;
    
    @ApiModelProperty(value="项目类型")
	private Integer xmlx;
    
    @ApiModelProperty(value="费用科室")
	private Integer fyks;
    
    @ApiModelProperty(value="使用频次")
	private String sypc;
	
    @ApiModelProperty(value="住院使用")
	private Integer zysy;
    
    @ApiModelProperty(value="医技使用")
	private Integer yjsy;
	
	@ApiModelProperty(value="作废判别")
	private Integer zfpb;
	
	@ApiModelProperty(value="自负比例")
    private BigDecimal zfbl;
    
    @ApiModelProperty(value="是否过敏")
    private boolean isAllergy;
    
    @ApiModelProperty(value="不良反映")
    private String blfy;

	public Integer getFyxh() {
		return fyxh;
	}

	public void setFyxh(Integer fyxh) {
		this.fyxh = fyxh;
	}

	public String getYzmc() {
		return yzmc;
	}

	public void setYzmc(String yzmc) {
		this.yzmc = yzmc;
	}

	public Integer getXmsl() {
		return xmsl;
	}

	public void setXmsl(Integer xmsl) {
		this.xmsl = xmsl;
	}

	public Integer getYpzh() {
		return ypzh;
	}

	public void setYpzh(Integer ypzh) {
		this.ypzh = ypzh;
	}

	public BigDecimal getFydj() {
		return fydj;
	}

	public void setFydj(BigDecimal fydj) {
		this.fydj = fydj;
	}

	public Integer getFygb() {
		return fygb;
	}

	public void setFygb(Integer fygb) {
		this.fygb = fygb;
	}

	public String getFydw() {
		return fydw;
	}

	public void setFydw(String fydw) {
		this.fydw = fydw;
	}

	public Integer getXmlx() {
		return xmlx;
	}

	public void setXmlx(Integer xmlx) {
		this.xmlx = xmlx;
	}

	public Integer getFyks() {
		return fyks;
	}

	public void setFyks(Integer fyks) {
		this.fyks = fyks;
	}

	public String getSypc() {
		return sypc;
	}

	public void setSypc(String sypc) {
		this.sypc = sypc;
	}

	public Integer getZysy() {
		return zysy;
	}

	public void setZysy(Integer zysy) {
		this.zysy = zysy;
	}

	public Integer getYjsy() {
		return yjsy;
	}

	public void setYjsy(Integer yjsy) {
		this.yjsy = yjsy;
	}

	public Integer getZfpb() {
		return zfpb;
	}

	public void setZfpb(Integer zfpb) {
		this.zfpb = zfpb;
	}

	public BigDecimal getZfbl() {
		return zfbl;
	}

	public void setZfbl(BigDecimal zfbl) {
		this.zfbl = zfbl;
	}

	public boolean isAllergy() {
		return isAllergy;
	}

	public void setAllergy(boolean isAllergy) {
		this.isAllergy = isAllergy;
	}

	public String getBlfy() {
		return blfy;
	}

	public void setBlfy(String blfy) {
		this.blfy = blfy;
	}
	
	
}
