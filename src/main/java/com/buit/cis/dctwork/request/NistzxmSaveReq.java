package com.buit.cis.dctwork.request;

import java.math.BigDecimal;
import java.sql.Timestamp;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
/**
* @ClassName: NistzxmSaveReq
* @Description: TODO 生命体征项目_保存
* @author 龚方舟
* @date 2020年6月3日 上午11:29:54
*/
@ApiModel(value="生命体征项目_saveReq")
public class NistzxmSaveReq {
	@ApiModelProperty(value="测量时间")		
	private Timestamp cjsj;	
	
	@ApiModelProperty(value="大便(次/日)")
	private String db;
	
	@ApiModelProperty(value="呼吸(次/分)")
	private Integer hx;
	
	@ApiModelProperty(value="脉搏(次/分)")
	private Integer mb;
	
	@ApiModelProperty(value="过敏药物")
	private String ps;
	
	@ApiModelProperty(value="出量(ml)")
	private BigDecimal qtcl;
	
	@ApiModelProperty(value="入量(ml)")
	private BigDecimal rl;

	@ApiModelProperty(value="身高(cm)")
	private String sg;
	
	@ApiModelProperty(value="收缩压(mmHg)")
	private Integer ssy;
	
	@ApiModelProperty(value="舒张压(mmHg)")
	private Integer szy;
	
	@ApiModelProperty(value="体温(℃)")
	private String tw;
	
	@ApiModelProperty(value="体重(kg)")
	private String tz;
	
	@ApiModelProperty(value="尿量(ml)")
	private BigDecimal nl;
	
	@ApiModelProperty(value="心率(次/分)")
	private Integer xl;
	
	@ApiModelProperty(value="降温(℃)")
	private BigDecimal jw;
	
	@ApiModelProperty(value="辅助呼吸(次/分)")
	private Integer fzhx;
	
	@ApiModelProperty(value="起搏器(次/分)")
	private Integer qbq;
	
	@ApiModelProperty(value="疼痛评分")
	private Integer ttpf;

	public Timestamp getCjsj() {
		return cjsj;
	}

	public void setCjsj(Timestamp cjsj) {
		this.cjsj = cjsj;
	}

	public String getDb() {
		return db;
	}

	public void setDb(String db) {
		this.db = db;
	}

	public Integer getHx() {
		return hx;
	}

	public void setHx(Integer hx) {
		this.hx = hx;
	}

	public Integer getMb() {
		return mb;
	}

	public void setMb(Integer mb) {
		this.mb = mb;
	}

	public String getPs() {
		return ps;
	}

	public void setPs(String ps) {
		this.ps = ps;
	}

	public BigDecimal getQtcl() {
		return qtcl;
	}

	public void setQtcl(BigDecimal qtcl) {
		this.qtcl = qtcl;
	}

	public BigDecimal getRl() {
		return rl;
	}

	public void setRl(BigDecimal rl) {
		this.rl = rl;
	}

	public String getSg() {
		return sg;
	}

	public void setSg(String sg) {
		this.sg = sg;
	}

	public Integer getSsy() {
		return ssy;
	}

	public void setSsy(Integer ssy) {
		this.ssy = ssy;
	}

	public Integer getSzy() {
		return szy;
	}

	public void setSzy(Integer szy) {
		this.szy = szy;
	}

	public String getTw() {
		return tw;
	}

	public void setTw(String tw) {
		this.tw = tw;
	}

	public String getTz() {
		return tz;
	}

	public void setTz(String tz) {
		this.tz = tz;
	}

	public BigDecimal getNl() {
		return nl;
	}

	public void setNl(BigDecimal nl) {
		this.nl = nl;
	}

	public Integer getXl() {
		return xl;
	}

	public void setXl(Integer xl) {
		this.xl = xl;
	}

	public BigDecimal getJw() {
		return jw;
	}

	public void setJw(BigDecimal jw) {
		this.jw = jw;
	}

	public Integer getFzhx() {
		return fzhx;
	}

	public void setFzhx(Integer fzhx) {
		this.fzhx = fzhx;
	}

	public Integer getQbq() {
		return qbq;
	}

	public void setQbq(Integer qbq) {
		this.qbq = qbq;
	}

	public Integer getTtpf() {
		return ttpf;
	}

	public void setTtpf(Integer ttpf) {
		this.ttpf = ttpf;
	}
	
	
	
}
