   
package com.buit.cis.dctwork.request;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


/**
 * 类名称：NisSmtzBatchSaveReq<br> 
 * 类描述：生命体征<br>
 * @author GONGFANGZHOU
 */
@ApiModel(value="生命体征_BatchSaveReq")
public class NisSmtzBatchSaveReq {
	
	@ApiModelProperty(value="查询日期")
    private String queryDate;
	
	@ApiModelProperty(value="查询时间段")
    private Integer hour;
	
	@ApiModelProperty(value="批量保存集合")
	private List<NisSmtzBatch> nisSmtzBatchList;	
	
	public String getQueryDate() {
		return queryDate;
	}

	public void setQueryDate(String queryDate) {
		this.queryDate = queryDate;
	}

	public Integer getHour() {
		return hour;
	}

	public void setHour(Integer hour) {
		this.hour = hour;
	}

	public List<NisSmtzBatch> getNisSmtzBatchList() {
		return nisSmtzBatchList;
	}

	public void setNisSmtzBatchList(List<NisSmtzBatch> nisSmtzBatchList) {
		this.nisSmtzBatchList = nisSmtzBatchList;
	}

	public static class NisSmtzBatch {

		@ApiModelProperty(value="住院号")
	    private Integer zyh;
	    
	    @ApiModelProperty(value="采集时间")
	    private Timestamp cjsj;
	    
	    @ApiModelProperty(value="体温")
	    private String tw;
	    
	    @ApiModelProperty(value="项目下标")
	    private String xmxb;
	    
	    @ApiModelProperty(value="脉搏")
	    private Integer mb;
	    
	    @ApiModelProperty(value="呼吸")
	    private Integer hx;
	    
	    @ApiModelProperty(value="心率")
	    private Integer xl;
	    
	    @ApiModelProperty(value="收缩压")
	    private Integer ssy;
	    
	    @ApiModelProperty(value="舒张压")
	    private Integer szy;
	    
	    @ApiModelProperty(value="体重")
	    private String tz;
	    
	    @ApiModelProperty(value="身高")
	    private String sg;
	    
	    @ApiModelProperty(value="降温")
	    private BigDecimal jw;
	    
	    @ApiModelProperty(value="起搏器")
	    private Integer qbq;
	    
	    @ApiModelProperty(value="辅助呼吸")
	    private Integer fzhx;
	    
	    @ApiModelProperty(value="大便")
	    private String db;
	    
	    @ApiModelProperty(value="尿量")
	    private BigDecimal nl;
	    
	    @ApiModelProperty(value="出量")
	    private BigDecimal qtcl;
	    
	    @ApiModelProperty(value="过敏药物")
	    private String ps;
	    
	    @ApiModelProperty(value="疼痛评分")
	    private Integer ttpf;
	    
	    @ApiModelProperty(value="自定义名称1")
	    private String zdymc1;
	    
	    @ApiModelProperty(value="自定义内容1")
	    private String zdynr1;
	    
	    @ApiModelProperty(value="自定义名称2")
	    private String zdymc2;
	    
	    @ApiModelProperty(value="自定义内容2")
	    private String zdynr2;
	    
	    @ApiModelProperty(value="自定义名称3")
	    private String zdymc3;
	    
	    @ApiModelProperty(value="自定义内容3")
	    private String zdynr3;

	    @ApiModelProperty(value="自定义id1")
	    private Integer zdyid1;
	    
	    @ApiModelProperty(value="自定义id2")
	    private Integer zdyid2;
	    
	    @ApiModelProperty(value="自定义id3")
	    private Integer zdyid3;
	    
	    @ApiModelProperty(value="项目代码1")
	    private Integer xmdm1;
	    
	    @ApiModelProperty(value="项目代码2")
	    private Integer xmdm2;
	    
	    @ApiModelProperty(value="项目代码3")
	    private Integer xmdm3;
	    
	    @ApiModelProperty(value="更新状态")
	    private String opStatus;

		public Integer getZyh() {
			return zyh;
		}

		public void setZyh(Integer zyh) {
			this.zyh = zyh;
		}

		public Timestamp getCjsj() {
			return cjsj;
		}

		public void setCjsj(Timestamp cjsj) {
			this.cjsj = cjsj;
		}

		public String getTw() {
			return tw;
		}

		public void setTw(String tw) {
			this.tw = tw;
		}

		public String getXmxb() {
			return xmxb;
		}

		public void setXmxb(String xmxb) {
			this.xmxb = xmxb;
		}

		public Integer getMb() {
			return mb;
		}

		public void setMb(Integer mb) {
			this.mb = mb;
		}

		public Integer getHx() {
			return hx;
		}

		public void setHx(Integer hx) {
			this.hx = hx;
		}

		public Integer getXl() {
			return xl;
		}

		public void setXl(Integer xl) {
			this.xl = xl;
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

		public String getTz() {
			return tz;
		}

		public void setTz(String tz) {
			this.tz = tz;
		}

		public String getSg() {
			return sg;
		}

		public void setSg(String sg) {
			this.sg = sg;
		}

		public BigDecimal getJw() {
			return jw;
		}

		public void setJw(BigDecimal jw) {
			this.jw = jw;
		}

		public Integer getQbq() {
			return qbq;
		}

		public void setQbq(Integer qbq) {
			this.qbq = qbq;
		}

		public Integer getFzhx() {
			return fzhx;
		}

		public void setFzhx(Integer fzhx) {
			this.fzhx = fzhx;
		}

		public String getDb() {
			return db;
		}

		public void setDb(String db) {
			this.db = db;
		}

		public BigDecimal getNl() {
			return nl;
		}

		public void setNl(BigDecimal nl) {
			this.nl = nl;
		}

		public BigDecimal getQtcl() {
			return qtcl;
		}

		public void setQtcl(BigDecimal qtcl) {
			this.qtcl = qtcl;
		}

		public String getPs() {
			return ps;
		}

		public void setPs(String ps) {
			this.ps = ps;
		}

		public Integer getTtpf() {
			return ttpf;
		}

		public void setTtpf(Integer ttpf) {
			this.ttpf = ttpf;
		}

		public String getZdymc1() {
			return zdymc1;
		}

		public void setZdymc1(String zdymc1) {
			this.zdymc1 = zdymc1;
		}

		public String getZdynr1() {
			return zdynr1;
		}

		public void setZdynr1(String zdynr1) {
			this.zdynr1 = zdynr1;
		}

		public String getZdymc2() {
			return zdymc2;
		}

		public void setZdymc2(String zdymc2) {
			this.zdymc2 = zdymc2;
		}

		public String getZdynr2() {
			return zdynr2;
		}

		public void setZdynr2(String zdynr2) {
			this.zdynr2 = zdynr2;
		}

		public String getZdymc3() {
			return zdymc3;
		}

		public void setZdymc3(String zdymc3) {
			this.zdymc3 = zdymc3;
		}

		public String getZdynr3() {
			return zdynr3;
		}

		public void setZdynr3(String zdynr3) {
			this.zdynr3 = zdynr3;
		}

		public Integer getZdyid1() {
			return zdyid1;
		}

		public void setZdyid1(Integer zdyid1) {
			this.zdyid1 = zdyid1;
		}

		public Integer getZdyid2() {
			return zdyid2;
		}

		public void setZdyid2(Integer zdyid2) {
			this.zdyid2 = zdyid2;
		}

		public Integer getZdyid3() {
			return zdyid3;
		}

		public void setZdyid3(Integer zdyid3) {
			this.zdyid3 = zdyid3;
		}

		public Integer getXmdm1() {
			return xmdm1;
		}

		public void setXmdm1(Integer xmdm1) {
			this.xmdm1 = xmdm1;
		}

		public Integer getXmdm2() {
			return xmdm2;
		}

		public void setXmdm2(Integer xmdm2) {
			this.xmdm2 = xmdm2;
		}

		public Integer getXmdm3() {
			return xmdm3;
		}

		public void setXmdm3(Integer xmdm3) {
			this.xmdm3 = xmdm3;
		}

		public String getOpStatus() {
			return opStatus;
		}

		public void setOpStatus(String opStatus) {
			this.opStatus = opStatus;
		}
		    
	}

   
    
    
}
