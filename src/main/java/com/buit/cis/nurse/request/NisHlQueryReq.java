   
package com.buit.cis.nurse.request;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


/**
 * 类名称：NisHlQueryReq<br> 
 * 类描述：护理表单<br>
 * @author GONGFANGZHOU
 */
@ApiModel(value="护理表单_QueryReq")
public class NisHlQueryReq{

	@NotNull(message = "住院号不能为空")
    @ApiModelProperty(value="住院号")
    private Integer zyh;
    
    @ApiModelProperty(value="查询时间(M-d)")
    private String queryDate;
   
    @ApiModelProperty(value="查询时间(Y-m)")
    private String yearMonth;
    
    @ApiModelProperty(value="模板类型")
    private String mblx;

    @ApiModelProperty(value="机构id", hidden = true)
    private Integer jgid;

	public Integer getZyh() {
		return zyh;
	}

	public void setZyh(Integer zyh) {
		this.zyh = zyh;
	}

	public String getQueryDate() {
		return queryDate;
	}

	public void setQueryDate(String queryDate) {
		this.queryDate = queryDate;
	}

	public String getYearMonth() {
		return yearMonth;
	}

	public void setYearMonth(String yearMonth) {
		this.yearMonth = yearMonth;
	}

	public String getMblx() {
		return mblx;
	}

	public void setMblx(String mblx) {
		this.mblx = mblx;
	}

	public Integer getJgid() {
		return jgid;
	}

	public void setJgid(Integer jgid) {
		this.jgid = jgid;
	}

}