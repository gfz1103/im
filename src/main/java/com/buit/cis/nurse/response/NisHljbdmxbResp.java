   
package com.buit.cis.nurse.response;

import java.sql.Date;
import java.sql.Timestamp;

import com.buit.commons.PageQuery;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


/**
 * 类名称：NisHljbdmxb<br> 
 * 类描述：护理交班单明细表<br>
 * @author GONGFANGZHOU
 */
@ApiModel(value="护理交班单明细表_Resp")
public class NisHljbdmxbResp {
    @ApiModelProperty(value="记录序号(主键id)")
    private Integer jlxh;
    @ApiModelProperty(value="交班单记录序号(nis_hljbd主键)")
    private Integer jbdjlxh;
    @ApiModelProperty(value="住院号")
    private Integer zyh;
    @ApiModelProperty(value="诊断名称(入院主诊断)")
    private String zdmc;
    @ApiModelProperty(value="患者类型(系统标识字典:JBD000001)")
    private Integer hzlx;
    @ApiModelProperty(value="SBAR类型(系统标识字典:JBD000002)")
    private Integer sbarlx;
    @ApiModelProperty(value="交班内容")
    private String jbnr;
    @ApiModelProperty(value="病人床号")
    private String brch;
    @ApiModelProperty(value="病人姓名")
    private String brxm;
    @ApiModelProperty(value="出生日期")
    private	Date csny;
    @ApiModelProperty(value="住院号码")
    private String zyhm;
    @ApiModelProperty(value="sbar类型名称")
    private String sbarmc;
    @ApiModelProperty(value="班次类型")
    private Integer bclx;
    
    /**
     * 设置:记录序号(主键id)
     */
    public void setJlxh(Integer value) {
        this.jlxh = value;
    }
    /**
     * 获取:记录序号(主键id)
     */
    public Integer getJlxh() {
        return jlxh;
    }
    /**
     * 设置:交班单记录序号(nis_hljbd主键)
     */
    public void setJbdjlxh(Integer value) {
        this.jbdjlxh = value;
    }
    /**
     * 获取:交班单记录序号(nis_hljbd主键)
     */
    public Integer getJbdjlxh() {
        return jbdjlxh;
    }
    /**
     * 设置:住院号
     */
    public void setZyh(Integer value) {
        this.zyh = value;
    }
    /**
     * 获取:住院号
     */
    public Integer getZyh() {
        return zyh;
    }
    /**
     * 设置:诊断名称(入院主诊断)
     */
    public void setZdmc(String value) {
        this.zdmc = value;
    }
    /**
     * 获取:诊断名称(入院主诊断)
     */
    public String getZdmc() {
        return zdmc;
    }
    /**
     * 设置:患者类型(系统标识字典:JBD000001)
     */
    public void setHzlx(Integer value) {
        this.hzlx = value;
    }
    /**
     * 获取:患者类型(系统标识字典:JBD000001)
     */
    public Integer getHzlx() {
        return hzlx;
    }
    /**
     * 设置:SBAR类型(系统标识字典:JBD000002)
     */
    public void setSbarlx(Integer value) {
        this.sbarlx = value;
    }
    /**
     * 获取:SBAR类型(系统标识字典:JBD000002)
     */
    public Integer getSbarlx() {
        return sbarlx;
    }
    /**
     * 设置:交班内容
     */
    public void setJbnr(String value) {
        this.jbnr = value;
    }
    /**
     * 获取:交班内容
     */
    public String getJbnr() {
        return jbnr;
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
	public Date getCsny() {
		return csny;
	}
	public void setCsny(Date csny) {
		this.csny = csny;
	}
	public String getZyhm() {
		return zyhm;
	}
	public void setZyhm(String zyhm) {
		this.zyhm = zyhm;
	}
	public String getSbarmc() {
		return sbarmc;
	}
	public void setSbarmc(String sbarmc) {
		this.sbarmc = sbarmc;
	}
	public Integer getBclx() {
		return bclx;
	}
	public void setBclx(Integer bclx) {
		this.bclx = bclx;
	}
	
   
}