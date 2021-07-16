package com.buit.cis.ims.response;

import java.sql.Date;
import java.sql.Timestamp;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 类名称：ImZkjl<br> 
 * 类描述：住院_转科记录
 * @author GONGFANGZHOU 
 * @ApiModel(value="住院_转科记录")
 */
@ApiModel(value="住院_转科记录_Resp")
public class ImZkjlResp {
	@ApiModelProperty(value="记录序号")
    /** 记录序号 */
    private Integer jlxh;
	@ApiModelProperty(value="住院号")
    /** 住院号 */
    private Integer zyh;
	@ApiModelProperty(value="换床类型 | 1:护士站转科 2:护士站转床 3:医生站转科 4:医生站换医生 5:护士站分配床位")
    /** 换床类型 | 1:护士站转科 2:护士站转床 3:医生站转科 4:医生站换医生 5:护士站分配床位 */
    private Boolean hclx;
	@ApiModelProperty(value="医生申请日期")
    /** 医生申请日期 */
    private Date yssqrq;
	@ApiModelProperty(value="医生申请工号")
    /** 医生申请工号 */
    private String yssqgh;
	@ApiModelProperty(value="病区申请日期")
    /** 病区申请日期 */
    private Date bqsqrq;
	@ApiModelProperty(value="病区申请工号")
    /** 病区申请工号 */
    private String bqsqgh;
	@ApiModelProperty(value="病区执行日期")
    /** 病区执行日期 */
    private Timestamp bqzxrq;
	@ApiModelProperty(value="病区执行工号")
    /** 病区执行工号 */
    private String bqzxgh;
	@ApiModelProperty(value="医生执行日期")
    /** 医生执行日期 */
    private Timestamp yszxrq;
	@ApiModelProperty(value="医生执行工号")
    /** 医生执行工号 */
    private String yszxgh;
	@ApiModelProperty(value="执行标志(字典:sys_flag_data:QR000001)")
    /** 执行标志 | 1:转前医生 2:转前病区 3:转后病区 4:转后医生 */
    private Integer zxbz;
	@ApiModelProperty(value="换前床号")
    /** 换前床号 */
    private String hqch;
	@ApiModelProperty(value="换后床号")
    /** 换后床号 */
    private String hhch;
	@ApiModelProperty(value="换前病区(字典:sys_dict_config:15)")
    /** 换前病区 */
    private Integer hqbq;
	@ApiModelProperty(value="换后病区(字典:sys_dict_config:15)")
    /** 换后病区 */
    private Integer hhbq;
	@ApiModelProperty(value="换前科室(字典:sys_dict_config:15)")
    /** 换前科室 */
    private Integer hqks;
	@ApiModelProperty(value="换后科室(字典:sys_dict_config:15)")
    /** 换后科室 */
    private Integer hhks;
	@ApiModelProperty(value="换前医生(字典:sys_dict_config:19)")
    /** 换前医生 */
    private Integer hqys;
	@ApiModelProperty(value="换后医生(字典:sys_dict_config:19)")
    /** 换后医生 */
    private Integer hhys;
	@ApiModelProperty(value="机构代码")
    /** 机构代码 */
    private Integer jgid;
	@ApiModelProperty(value="转科记录数量")
	private long countZk;
	
	@ApiModelProperty(value="病人姓名")
	private String brxm;
	
	@ApiModelProperty(value="病人床号")
	private String brch;

    /** 设置:记录序号  */
    public void setJlxh(Integer value) {
        this.jlxh = value;
    }
    /** 获取:记录序号 */
    public Integer getJlxh() {
        return jlxh;
    }

    /** 设置:住院号  */
    public void setZyh(Integer value) {
        this.zyh = value;
    }
    /** 获取:住院号 */
    public Integer getZyh() {
        return zyh;
    }

    /** 设置:换床类型 | 1:护士站转科 2:护士站转床 3:医生站转科 4:医生站换医生 5:护士站分配床位  */
    public void setHclx(Boolean value) {
        this.hclx = value;
    }
    /** 获取:换床类型 | 1:护士站转科 2:护士站转床 3:医生站转科 4:医生站换医生 5:护士站分配床位 */
    public Boolean getHclx() {
        return hclx;
    }

    /** 设置:医生申请日期  */
    public void setYssqrq(Date value) {
        this.yssqrq = value;
    }
    /** 获取:医生申请日期 */
    public Date getYssqrq() {
        return yssqrq;
    }

    /** 设置:医生申请工号  */
    public void setYssqgh(String value) {
        this.yssqgh = value;
    }
    /** 获取:医生申请工号 */
    public String getYssqgh() {
        return yssqgh;
    }

    /** 设置:病区申请日期  */
    public void setBqsqrq(Date value) {
        this.bqsqrq = value;
    }
    /** 获取:病区申请日期 */
    public Date getBqsqrq() {
        return bqsqrq;
    }

    /** 设置:病区申请工号  */
    public void setBqsqgh(String value) {
        this.bqsqgh = value;
    }
    /** 获取:病区申请工号 */
    public String getBqsqgh() {
        return bqsqgh;
    }

    /** 设置:病区执行日期  */
    public void setBqzxrq(Timestamp value) {
        this.bqzxrq = value;
    }
    /** 获取:病区执行日期 */
    public Timestamp getBqzxrq() {
        return bqzxrq;
    }

    /** 设置:病区执行工号  */
    public void setBqzxgh(String value) {
        this.bqzxgh = value;
    }
    /** 获取:病区执行工号 */
    public String getBqzxgh() {
        return bqzxgh;
    }

    /** 设置:医生执行日期  */
    public void setYszxrq(Timestamp value) {
        this.yszxrq = value;
    }
    /** 获取:医生执行日期 */
    public Timestamp getYszxrq() {
        return yszxrq;
    }

    /** 设置:医生执行工号  */
    public void setYszxgh(String value) {
        this.yszxgh = value;
    }
    /** 获取:医生执行工号 */
    public String getYszxgh() {
        return yszxgh;
    }

    /** 设置:执行标志 | 1:转前医生 2:转前病区 3:转后病区 4:转后医生  */
    public void setZxbz(Integer value) {
        this.zxbz = value;
    }
    /** 获取:执行标志 | 1:转前医生 2:转前病区 3:转后病区 4:转后医生 */
    public Integer getZxbz() {
        return zxbz;
    }

    /** 设置:换前床号  */
    public void setHqch(String value) {
        this.hqch = value;
    }
    /** 获取:换前床号 */
    public String getHqch() {
        return hqch;
    }

    /** 设置:换后床号  */
    public void setHhch(String value) {
        this.hhch = value;
    }
    /** 获取:换后床号 */
    public String getHhch() {
        return hhch;
    }

    /** 设置:换前病区  */
    public void setHqbq(Integer value) {
        this.hqbq = value;
    }
    /** 获取:换前病区 */
    public Integer getHqbq() {
        return hqbq;
    }

    /** 设置:换后病区  */
    public void setHhbq(Integer value) {
        this.hhbq = value;
    }
    /** 获取:换后病区 */
    public Integer getHhbq() {
        return hhbq;
    }

    /** 设置:换前科室  */
    public void setHqks(Integer value) {
        this.hqks = value;
    }
    /** 获取:换前科室 */
    public Integer getHqks() {
        return hqks;
    }

    /** 设置:换后科室  */
    public void setHhks(Integer value) {
        this.hhks = value;
    }
    /** 获取:换后科室 */
    public Integer getHhks() {
        return hhks;
    }

    /** 设置:换前医生  */
    public void setHqys(Integer value) {
        this.hqys = value;
    }
    /** 获取:换前医生 */
    public Integer getHqys() {
        return hqys;
    }

    /** 设置:换后医生  */
    public void setHhys(Integer value) {
        this.hhys = value;
    }
    /** 获取:换后医生 */
    public Integer getHhys() {
        return hhys;
    }

    /** 设置:机构代码  */
    public void setJgid(Integer value) {
        this.jgid = value;
    }
    /** 获取:机构代码 */
    public Integer getJgid() {
        return jgid;
    }
    
	public long getCountZk() {
		return countZk;
	}
	
	public void setCountZk(long countZk) {
		this.countZk = countZk;
	}
	
	public String getBrxm() {
		return brxm;
	}
	
	public void setBrxm(String brxm) {
		this.brxm = brxm;
	}
	
	public String getBrch() {
		return brch;
	}
	
	public void setBrch(String brch) {
		this.brch = brch;
	}


}
