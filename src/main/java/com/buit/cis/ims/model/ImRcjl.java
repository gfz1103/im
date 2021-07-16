package com.buit.cis.ims.model;

import com.buit.commons.PageQuery;

import java.sql.Timestamp;

/**
 * 类名称：ImRcjl<br> 
 * 类描述：住院_临床病人入出记录
 * @author ZHOUHAISHENG 
 * @ApiModel(value="住院_临床病人入出记录")
 */
public class ImRcjl  extends PageQuery {
	//@ApiModelProperty(value="记录序号")
    /** 记录序号 */
    private Integer jlxh;
	//@ApiModelProperty(value="机构代码")
    /** 机构代码 */
    private Integer jgid;
	//@ApiModelProperty(value="操作日期")
    /** 操作日期 */
    private Timestamp czrq;
	//@ApiModelProperty(value="临床日期")
    /** 临床日期 */
    private Timestamp lcrq;
	//@ApiModelProperty(value="操作类型 | 0:月结   1:入院   2 : 转入    -1: 出院   -2:转出")
    /** 操作类型 | 0:月结   1:入院   2 : 转入    -1: 出院   -2:转出 */
    private Integer czlx;
	//@ApiModelProperty(value="住院号")
    /** 住院号 */
    private Integer zyh;
	//@ApiModelProperty(value="病人科室")
    /** 病人科室 */
    private Integer brks;
	//@ApiModelProperty(value="月结在院人数")
    /** 月结在院人数 */
    private Integer yjzyrs;
	//@ApiModelProperty(value="病区判别 | 0：科室          1：病区")
    /** 病区判别 | 0：科室          1：病区 */
    private Integer bqpb;
	//@ApiModelProperty(value="出院方式 | 出院方式在CZLX=-1时使用，对应GY_DMZD中的代码（DMLB = 23）")
    /** 出院方式 | 出院方式在CZLX=-1时使用，对应GY_DMZD中的代码（DMLB = 23） */
    private Integer cyfs;
	//@ApiModelProperty(value="备注信息")
    /** 备注信息 */
    private String bzxx;
	//@ApiModelProperty(value="scbz")
    /** scbz */
    private String scbz;
    
    //@ApiModelProperty(value="随访信息")
    /** sf */
    private String sf;

    /** 设置:记录序号  */
    public void setJlxh(Integer value) {
        this.jlxh = value;
    }
    /** 获取:记录序号 */
    public Integer getJlxh() {
        return jlxh;
    }

    /** 设置:机构代码  */
    public void setJgid(Integer value) {
        this.jgid = value;
    }
    /** 获取:机构代码 */
    public Integer getJgid() {
        return jgid;
    }

    /** 设置:操作日期  */
    public void setCzrq(Timestamp value) {
        this.czrq = value;
    }
    /** 获取:操作日期 */
    public Timestamp getCzrq() {
        return czrq;
    }

    /** 设置:临床日期  */
    public void setLcrq(Timestamp value) {
        this.lcrq = value;
    }
    /** 获取:临床日期 */
    public Timestamp getLcrq() {
        return lcrq;
    }

    /** 设置:操作类型 | 0:月结   1:入院   2 : 转入    -1: 出院   -2:转出  */
    public void setCzlx(Integer value) {
        this.czlx = value;
    }
    /** 获取:操作类型 | 0:月结   1:入院   2 : 转入    -1: 出院   -2:转出 */
    public Integer getCzlx() {
        return czlx;
    }

    /** 设置:住院号  */
    public void setZyh(Integer value) {
        this.zyh = value;
    }
    /** 获取:住院号 */
    public Integer getZyh() {
        return zyh;
    }

    /** 设置:病人科室  */
    public void setBrks(Integer value) {
        this.brks = value;
    }
    /** 获取:病人科室 */
    public Integer getBrks() {
        return brks;
    }

    /** 设置:月结在院人数  */
    public void setYjzyrs(Integer value) {
        this.yjzyrs = value;
    }
    /** 获取:月结在院人数 */
    public Integer getYjzyrs() {
        return yjzyrs;
    }

    /** 设置:病区判别 | 0：科室          1：病区  */
    public void setBqpb(Integer value) {
        this.bqpb = value;
    }
    /** 获取:病区判别 | 0：科室          1：病区 */
    public Integer getBqpb() {
        return bqpb;
    }

    /** 设置:出院方式 | 出院方式在CZLX=-1时使用，对应GY_DMZD中的代码（DMLB = 23）  */
    public void setCyfs(Integer value) {
        this.cyfs = value;
    }
    /** 获取:出院方式 | 出院方式在CZLX=-1时使用，对应GY_DMZD中的代码（DMLB = 23） */
    public Integer getCyfs() {
        return cyfs;
    }

    /** 设置:备注信息  */
    public void setBzxx(String value) {
        this.bzxx = value;
    }
    /** 获取:备注信息 */
    public String getBzxx() {
        return bzxx;
    }

    /** 设置:scbz  */
    public void setScbz(String value) {
        this.scbz = value;
    }
    /** 获取:scbz */
    public String getScbz() {
        return scbz;
    }
    
	public String getSf() {
		return sf;
	}
	
	public void setSf(String sf) {
		this.sf = sf;
	}

    

}
