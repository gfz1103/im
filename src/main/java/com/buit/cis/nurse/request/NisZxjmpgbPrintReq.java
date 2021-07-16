   
package com.buit.cis.nurse.request;

import java.sql.Timestamp;

import com.buit.commons.PageQuery;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


/**
 * 类名称：NisZxjmpgb<br> 
 * 类描述：中心静脉导管相关性感染风险因素评估表<br>
 * @author GONGFANGZHOU
 */
@ApiModel(value="中心静脉导管相关性感染风险因素评估表_PrintReq")
public class NisZxjmpgbPrintReq  extends  PageQuery{
	@ApiModelProperty(value="年龄")
    private String nl;
	@ApiModelProperty(value="基础疾病1")
    private String jcjb1;
	@ApiModelProperty(value="基础疾病2")
    private String jcjb2;
	@ApiModelProperty(value="基础疾病3")
    private String jcjb3;
	@ApiModelProperty(value="基础疾病4")
    private String jcjb4;
	@ApiModelProperty(value="置管部位1")
    private String zgbw1;
	@ApiModelProperty(value="置管部位2")
    private String zgbw2;
	@ApiModelProperty(value="置管部位3")
    private String zgbw3;
	@ApiModelProperty(value="置管部位4")
    private String zgbw4;
	@ApiModelProperty(value="置管状态1")
    private String zgzt1;
	@ApiModelProperty(value="置管状态2")
    private String zgzt2;
	@ApiModelProperty(value="置留时间1")
    private String zlsj1;
	@ApiModelProperty(value="置留时间2")
    private String zlsj2;
	@ApiModelProperty(value="置留时间3")
    private String zlsj3;
	@ApiModelProperty(value="导管材质1")
    private String dgcz1;
	@ApiModelProperty(value="导管材质2")
    private String dgcz2;
	@ApiModelProperty(value="导管腔数1")
    private String dgqs1;
	@ApiModelProperty(value="导管腔数2")
    private String dgqs2;
	@ApiModelProperty(value="导管腔数3")
    private String dgqs3;
	@ApiModelProperty(value="输液接口1")
    private String syjk1;
	@ApiModelProperty(value="输液接口2")
    private String syjk2;
	@ApiModelProperty(value="输注液体1")
    private String szyt1;
	@ApiModelProperty(value="输注液体2")
    private String szyt2;
	@ApiModelProperty(value="输注液体3")
    private String szyt3;
    @ApiModelProperty(value="护理措施")
    private String hlcs;
    @ApiModelProperty(value="护士签名")
    private String hsqm;
    @ApiModelProperty(value="日期")
    private String rq;
    @ApiModelProperty(value="总分")
    private Integer zf;
    @ApiModelProperty(value="机构id", hidden = true)
    private Integer jgid;
	public String getNl() {
		return nl;
	}
	public void setNl(String nl) {
		this.nl = nl;
	}
	public String getJcjb1() {
		return jcjb1;
	}
	public void setJcjb1(String jcjb1) {
		this.jcjb1 = jcjb1;
	}
	public String getJcjb2() {
		return jcjb2;
	}
	public void setJcjb2(String jcjb2) {
		this.jcjb2 = jcjb2;
	}
	public String getJcjb3() {
		return jcjb3;
	}
	public void setJcjb3(String jcjb3) {
		this.jcjb3 = jcjb3;
	}
	public String getJcjb4() {
		return jcjb4;
	}
	public void setJcjb4(String jcjb4) {
		this.jcjb4 = jcjb4;
	}
	public String getZgbw1() {
		return zgbw1;
	}
	public void setZgbw1(String zgbw1) {
		this.zgbw1 = zgbw1;
	}
	public String getZgbw2() {
		return zgbw2;
	}
	public void setZgbw2(String zgbw2) {
		this.zgbw2 = zgbw2;
	}
	public String getZgbw3() {
		return zgbw3;
	}
	public void setZgbw3(String zgbw3) {
		this.zgbw3 = zgbw3;
	}
	public String getZgbw4() {
		return zgbw4;
	}
	public void setZgbw4(String zgbw4) {
		this.zgbw4 = zgbw4;
	}
	public String getZgzt1() {
		return zgzt1;
	}
	public void setZgzt1(String zgzt1) {
		this.zgzt1 = zgzt1;
	}
	public String getZgzt2() {
		return zgzt2;
	}
	public void setZgzt2(String zgzt2) {
		this.zgzt2 = zgzt2;
	}
	public String getZlsj1() {
		return zlsj1;
	}
	public void setZlsj1(String zlsj1) {
		this.zlsj1 = zlsj1;
	}
	public String getZlsj2() {
		return zlsj2;
	}
	public void setZlsj2(String zlsj2) {
		this.zlsj2 = zlsj2;
	}
	public String getZlsj3() {
		return zlsj3;
	}
	public void setZlsj3(String zlsj3) {
		this.zlsj3 = zlsj3;
	}
	public String getDgcz1() {
		return dgcz1;
	}
	public void setDgcz1(String dgcz1) {
		this.dgcz1 = dgcz1;
	}
	public String getDgcz2() {
		return dgcz2;
	}
	public void setDgcz2(String dgcz2) {
		this.dgcz2 = dgcz2;
	}
	public String getDgqs1() {
		return dgqs1;
	}
	public void setDgqs1(String dgqs1) {
		this.dgqs1 = dgqs1;
	}
	public String getDgqs2() {
		return dgqs2;
	}
	public void setDgqs2(String dgqs2) {
		this.dgqs2 = dgqs2;
	}
	public String getDgqs3() {
		return dgqs3;
	}
	public void setDgqs3(String dgqs3) {
		this.dgqs3 = dgqs3;
	}
	public String getSyjk1() {
		return syjk1;
	}
	public void setSyjk1(String syjk1) {
		this.syjk1 = syjk1;
	}
	public String getSyjk2() {
		return syjk2;
	}
	public void setSyjk2(String syjk2) {
		this.syjk2 = syjk2;
	}
	public String getSzyt1() {
		return szyt1;
	}
	public void setSzyt1(String szyt1) {
		this.szyt1 = szyt1;
	}
	public String getSzyt2() {
		return szyt2;
	}
	public void setSzyt2(String szyt2) {
		this.szyt2 = szyt2;
	}
	public String getSzyt3() {
		return szyt3;
	}
	public void setSzyt3(String szyt3) {
		this.szyt3 = szyt3;
	}
	public String getHlcs() {
		return hlcs;
	}
	public void setHlcs(String hlcs) {
		this.hlcs = hlcs;
	}
	public String getHsqm() {
		return hsqm;
	}
	public void setHsqm(String hsqm) {
		this.hsqm = hsqm;
	}
	public String getRq() {
		return rq;
	}
	public void setRq(String rq) {
		this.rq = rq;
	}
	public Integer getZf() {
		return zf;
	}
	public void setZf(Integer zf) {
		this.zf = zf;
	}
	public Integer getJgid() {
		return jgid;
	}
	public void setJgid(Integer jgid) {
		this.jgid = jgid;
	}
  
    
}