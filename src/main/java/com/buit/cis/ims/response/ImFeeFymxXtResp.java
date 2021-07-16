package com.buit.cis.ims.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;
import java.sql.Timestamp;


/**
 * 类名称：ImFeeFymx<br>
 * 类描述：费用明细表(血透减负)<br>
 * @author GONGFANGZHOU
 */
@ApiModel(value="费用明细表_xtResp")
public class ImFeeFymxXtResp {
    @ApiModelProperty(value="记录序号")
    private Long jlxh;
    @ApiModelProperty(value="住院号")
    private Long zyh;
    @ApiModelProperty(value="费用日期")
    private Timestamp fyrq;
    @ApiModelProperty(value="费用序号")
    private Long fyxh;
    @ApiModelProperty(value="费用名称")
    private String fymc;
    @ApiModelProperty(value="药品产地")
    private Long ypcd;
    @ApiModelProperty(value="费用数量")
    private BigDecimal fysl;
    @ApiModelProperty(value="费用单价")
    private BigDecimal fydj;
    @ApiModelProperty(value="总计金额")
    private BigDecimal zjje;
    @ApiModelProperty(value="自负金额")
    private BigDecimal zfje;
    @ApiModelProperty(value="医生工号")
    private String ysgh;
    @ApiModelProperty(value="记费日期 | 实际记费日期 写IM_FEE_FYMX时服务器时间")
    private Timestamp jfrq;
    @ApiModelProperty(value="项目类型 | 1：病区系统记帐 2：药房系统记帐 3：医技系统记帐 4：住院系统记帐 5：手术麻醉记帐 9：自动累加费用")
    private Integer xmlx;
    @ApiModelProperty(value="药品类型 | 0：费用 1：西药 2：中成药 3：中草药")
    private Boolean yplx;
    @ApiModelProperty(value="费用项目 | 指定FYXH归并的项目(同GY_SFMX表中SFXM对应)")
    private Long fyxm;
    @ApiModelProperty(value="结算次数")
    private Integer jscs;
    @ApiModelProperty(value="自负比例 | 同FEE_SFDLZFBL或 FEE_YPXZ,PUB_FYXZ表中自负比例对应")
    private BigDecimal zfbl;
    @ApiModelProperty(value="医嘱序号 | 同CIS_HZYZ表中的医嘱序号对应")
    private Long yzxh;
    @ApiModelProperty(value="大病减负标志 1:尿毒症透析医疗费用,2:肾移植减负,3：精神病减负  其他：不减负")
    private String dbjfbz;
    @ApiModelProperty(value="费用科室 | 费用输入的科室(记帐,按输入科室核算时要用)")
    private Long fyks;
    @ApiModelProperty(value="医生姓名")
    private String ysxm;
    @ApiModelProperty(value="项目名称")
    private String xmmc;
    @ApiModelProperty(value="是否为大病")
    private String dbjfbzText;
	public Long getJlxh() {
		return jlxh;
	}
	public void setJlxh(Long jlxh) {
		this.jlxh = jlxh;
	}
	public Long getZyh() {
		return zyh;
	}
	public void setZyh(Long zyh) {
		this.zyh = zyh;
	}
	public Timestamp getFyrq() {
		return fyrq;
	}
	public void setFyrq(Timestamp fyrq) {
		this.fyrq = fyrq;
	}
	public Long getFyxh() {
		return fyxh;
	}
	public void setFyxh(Long fyxh) {
		this.fyxh = fyxh;
	}
	public String getFymc() {
		return fymc;
	}
	public void setFymc(String fymc) {
		this.fymc = fymc;
	}
	public Long getYpcd() {
		return ypcd;
	}
	public void setYpcd(Long ypcd) {
		this.ypcd = ypcd;
	}
	public BigDecimal getFysl() {
		return fysl;
	}
	public void setFysl(BigDecimal fysl) {
		this.fysl = fysl;
	}
	public BigDecimal getFydj() {
		return fydj;
	}
	public void setFydj(BigDecimal fydj) {
		this.fydj = fydj;
	}
	public BigDecimal getZjje() {
		return zjje;
	}
	public void setZjje(BigDecimal zjje) {
		this.zjje = zjje;
	}
	public BigDecimal getZfje() {
		return zfje;
	}
	public void setZfje(BigDecimal zfje) {
		this.zfje = zfje;
	}
	public String getYsgh() {
		return ysgh;
	}
	public void setYsgh(String ysgh) {
		this.ysgh = ysgh;
	}
	public Timestamp getJfrq() {
		return jfrq;
	}
	public void setJfrq(Timestamp jfrq) {
		this.jfrq = jfrq;
	}
	public Integer getXmlx() {
		return xmlx;
	}
	public void setXmlx(Integer xmlx) {
		this.xmlx = xmlx;
	}
	public Boolean getYplx() {
		return yplx;
	}
	public void setYplx(Boolean yplx) {
		this.yplx = yplx;
	}
	public Long getFyxm() {
		return fyxm;
	}
	public void setFyxm(Long fyxm) {
		this.fyxm = fyxm;
	}
	public Integer getJscs() {
		return jscs;
	}
	public void setJscs(Integer jscs) {
		this.jscs = jscs;
	}
	public BigDecimal getZfbl() {
		return zfbl;
	}
	public void setZfbl(BigDecimal zfbl) {
		this.zfbl = zfbl;
	}
	public Long getYzxh() {
		return yzxh;
	}
	public void setYzxh(Long yzxh) {
		this.yzxh = yzxh;
	}
	public String getDbjfbz() {
		return dbjfbz;
	}
	public void setDbjfbz(String dbjfbz) {
		this.dbjfbz = dbjfbz;
	}
	public Long getFyks() {
		return fyks;
	}
	public void setFyks(Long fyks) {
		this.fyks = fyks;
	}
	public String getYsxm() {
		return ysxm;
	}
	public void setYsxm(String ysxm) {
		this.ysxm = ysxm;
	}
	public String getXmmc() {
		return xmmc;
	}
	public void setXmmc(String xmmc) {
		this.xmmc = xmmc;
	}
	public String getDbjfbzText() {
		return dbjfbzText;
	}
	public void setDbjfbzText(String dbjfbzText) {
		this.dbjfbzText = dbjfbzText;
	}

    
}
