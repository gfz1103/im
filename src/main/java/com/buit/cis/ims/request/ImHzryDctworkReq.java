package com.buit.cis.ims.request;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


/**
 * 
* @ClassName: ImHzryDctworkReq
* @Description: TODO 住院病人入院传入参数
* @author 龚方舟
* @date 2020年5月25日 下午3:00:43
*
 */
@ApiModel(value="住院_病人入院_workReq")
public class ImHzryDctworkReq {

    @ApiModelProperty(value="住院号 | 该住院号为内码")
    private Integer zyh;
    
    @ApiModelProperty(value="机构代码",hidden =  true)
    private Integer jgid;
    
    @ApiModelProperty(value="病人姓名")
    private String brxm;
    
    @ApiModelProperty(value="科室代码(字典:sys_dict_config:15)")
    private Integer ksdm;
    
    @ApiModelProperty(value="拟邀会诊科室",hidden =  true)
    private Integer nyqhzks;
    
    @ApiModelProperty(value="打开方式")
    private String openBy;
    
    @ApiModelProperty(value="住院状态")
    private Integer zyzt;
    
    @ApiModelProperty(value="病人情况(数组:1:病危,2:病重)")
    private Integer[] brqk;
    
    @ApiModelProperty(value="护理级别(数组:0:特级护理,1:一级护理,2:二级护理,3:三级护理)")
    private Integer[] hljb;
    
    @ApiModelProperty(value="住院医生",hidden =  true)
    private Integer zyys;
    
    @ApiModelProperty(value="病人科室")
    private Integer brks;
    
    @ApiModelProperty(value="病人病区")
    private Integer brbq;
    
    @ApiModelProperty(value="病人姓名或住院号码或病人床号")
    private String comprehensive; 
    
    @ApiModelProperty(value="床位组号")
    private Integer cwzh; 

	public Integer getZyh() {
		return zyh;
	}

	public void setZyh(Integer zyh) {
		this.zyh = zyh;
	}

	public Integer getJgid() {
		return jgid;
	}

	public void setJgid(Integer jgid) {
		this.jgid = jgid;
	}

	public String getBrxm() {
		return brxm;
	}

	public void setBrxm(String brxm) {
		this.brxm = brxm;
	}

	public Integer getKsdm() {
		return ksdm;
	}

	public void setKsdm(Integer ksdm) {
		this.ksdm = ksdm;
	}

	public Integer getNyqhzks() {
		return nyqhzks;
	}

	public void setNyqhzks(Integer nyqhzks) {
		this.nyqhzks = nyqhzks;
	}

	public String getOpenBy() {
		return openBy;
	}

	public void setOpenBy(String openBy) {
		this.openBy = openBy;
	}

	public Integer getZyzt() {
		return zyzt;
	}

	public void setZyzt(Integer zyzt) {
		this.zyzt = zyzt;
	}

	public Integer[] getBrqk() {
		return brqk;
	}

	public void setBrqk(Integer[] brqk) {
		this.brqk = brqk;
	}

	public Integer[] getHljb() {
		return hljb;
	}

	public void setHljb(Integer[] hljb) {
		this.hljb = hljb;
	}

	public Integer getZyys() {
		return zyys;
	}

	public void setZyys(Integer zyys) {
		this.zyys = zyys;
	}

	public Integer getBrks() {
		return brks;
	}

	public void setBrks(Integer brks) {
		this.brks = brks;
	}

	public Integer getBrbq() {
		return brbq;
	}

	public void setBrbq(Integer brbq) {
		this.brbq = brbq;
	}

	public String getComprehensive() {
		return comprehensive;
	}

	public void setComprehensive(String comprehensive) {
		this.comprehensive = comprehensive;
	}

	public Integer getCwzh() {
		return cwzh;
	}

	public void setCwzh(Integer cwzh) {
		this.cwzh = cwzh;
	}
	
	
	
}
