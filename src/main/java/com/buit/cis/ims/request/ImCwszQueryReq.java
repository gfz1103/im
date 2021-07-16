package com.buit.cis.ims.request;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


/**
 * 类名称：ImCwsz<br>
 * 类描述：住院_床位设置<br>
 * @author GONGFANGZHOU
 */
@ApiModel(value="住院_床位设置")
public class ImCwszQueryReq {

    @ApiModelProperty(value="病人床号")
    private String brch;
    
    @ApiModelProperty(value="病人床号(查询)")
    private String brchSearch;
    
    @ApiModelProperty(value="病人性别")
    private Integer brxb;

    @ApiModelProperty(value="科室代码")
    private Integer ksdm;
    
    @ApiModelProperty(value="机构id", hidden = true)
    private Integer jgid;

	public String getBrch() {
		return brch;
	}

	public void setBrch(String brch) {
		this.brch = brch;
	}

	public String getBrchSearch() {
		return brchSearch;
	}

	public void setBrchSearch(String brchSearch) {
		this.brchSearch = brchSearch;
	}

	public Integer getBrxb() {
		return brxb;
	}

	public void setBrxb(Integer brxb) {
		this.brxb = brxb;
	}

	public Integer getKsdm() {
		return ksdm;
	}

	public void setKsdm(Integer ksdm) {
		this.ksdm = ksdm;
	}

	public Integer getJgid() {
		return jgid;
	}

	public void setJgid(Integer jgid) {
		this.jgid = jgid;
	}

}
