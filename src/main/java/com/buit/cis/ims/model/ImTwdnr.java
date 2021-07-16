package com.buit.cis.ims.model;

import java.sql.Timestamp;
import com.buit.commons.PageQuery;

import io.swagger.annotations.ApiModelProperty;

/**
 * 类名称：ImTwdnr<br> 
 * 类描述：住院体温单内容
 * @author GONGFANGZHOU 
 * @ApiModel(value="住院体温单内容")
 */
public class ImTwdnr  extends  PageQuery{
	@ApiModelProperty(value="住院号")
    /** zyh */
    private Integer zyh;
	@ApiModelProperty(value="住院号码")
    /** zyhm */
    private String zyhm;
	@ApiModelProperty(value="操作内容")
    /** cznr */
    private String cznr;
	@ApiModelProperty(value="发生时间")
    /** czsj */
    private Timestamp czsj;
	@ApiModelProperty(value="主键")
    /** id */
    private Integer id;
	@ApiModelProperty(value="病人床号")
    /** brch */
    private String brch;
	@ApiModelProperty(value="病人姓名")
    /** brxm */
    private String brxm;
	
    @ApiModelProperty(value="作废标识字典:SYS_FLAG_DATA:ZF000001 0:未作废(默认0) 1:作废")
    private Integer zfbz;
    
    @ApiModelProperty(value="操作类型(1:手术 2:分娩 3:死亡 4:自定义)")
    private Integer czlx;
    
    @ApiModelProperty(value="操作人")
    private Integer czr;
    
    @ApiModelProperty(value="记录时间")
    private Timestamp jlsj;

    /** 设置:zyh  */
    public void setZyh(Integer value) {
        this.zyh = value;
    }
    /** 获取:zyh */
    public Integer getZyh() {
        return zyh;
    }

    /** 设置:zyhm  */
    public void setZyhm(String value) {
        this.zyhm = value;
    }
    /** 获取:zyhm */
    public String getZyhm() {
        return zyhm;
    }

    /** 设置:cznr  */
    public void setCznr(String value) {
        this.cznr = value;
    }
    /** 获取:cznr */
    public String getCznr() {
        return cznr;
    }

    /** 设置:czsj  */
    public void setCzsj(Timestamp value) {
        this.czsj = value;
    }
    /** 获取:czsj */
    public Timestamp getCzsj() {
        return czsj;
    }

    /** 设置:id  */
    public void setId(Integer value) {
        this.id = value;
    }
    /** 获取:id */
    public Integer getId() {
        return id;
    }

    /** 设置:brch  */
    public void setBrch(String value) {
        this.brch = value;
    }
    /** 获取:brch */
    public String getBrch() {
        return brch;
    }

    /** 设置:brxm  */
    public void setBrxm(String value) {
        this.brxm = value;
    }
    /** 获取:brxm */
    public String getBrxm() {
        return brxm;
    }

    /** 设置:1.作废  0.未作废  （默认值）  */
    public void setZfbz(Integer value) {
        this.zfbz = value;
    }
    /** 获取:1.作废  0.未作废  （默认值） */
    public Integer getZfbz() {
        return zfbz;
    }
    
	public Integer getCzlx() {
		return czlx;
	}
	
	public void setCzlx(Integer czlx) {
		this.czlx = czlx;
	}
	
	public Integer getCzr() {
		return czr;
	}
	
	public void setCzr(Integer czr) {
		this.czr = czr;
	}
	
	public Timestamp getJlsj() {
		return jlsj;
	}
	
	public void setJlsj(Timestamp jlsj) {
		this.jlsj = jlsj;
	}

    
}
