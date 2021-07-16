   
package com.buit.cis.dctwork.request;

import java.util.List;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


/**
 * 类描述：病区医嘱药品输入法查询
 * @author GONGFANGZHOU
 */
@ApiModel(value="病区医嘱药品输入法查询_bqyzypsrfPageReq")
public class DrugsTypkBqyzYpSrfReq {	
    
    @NotNull(message = "搜索条件不能为空")
    @ApiModelProperty(value="搜索条件(可以为中文代码)",required = true)
    private String pydm;
    
    @ApiModelProperty(value="病区代码")
    private Integer bqdm;

    @ApiModelProperty(value="字段类型",hidden =  true)
    private String zdlx; 
    
    @ApiModelProperty(value="药房设置")
    private Integer yfsz;
    
    @ApiModelProperty(value="机构id",hidden =  true)
    private Integer jgid;
    
    @ApiModelProperty(value="药房识别集合",hidden =  true)
    private List<Integer> yfsbList;
    
    @ApiModelProperty(value="护士是否启用药品输入")
    private String hssfqyyp; 
    
    @ApiModelProperty(value="药品类型")
    private Integer dmsb; 

	public String getPydm() {
		return pydm;
	}

	public void setPydm(String pydm) {
		this.pydm = pydm;
	}

	public Integer getBqdm() {
		return bqdm;
	}

	public void setBqdm(Integer bqdm) {
		this.bqdm = bqdm;
	}

	public String getZdlx() {
		return zdlx;
	}

	public void setZdlx(String zdlx) {
		this.zdlx = zdlx;
	}

	public Integer getYfsz() {
		return yfsz;
	}

	public void setYfsz(Integer yfsz) {
		this.yfsz = yfsz;
	}

	public Integer getJgid() {
		return jgid;
	}

	public void setJgid(Integer jgid) {
		this.jgid = jgid;
	}

	public List<Integer> getYfsbList() {
		return yfsbList;
	}

	public void setYfsbList(List<Integer> yfsbList) {
		this.yfsbList = yfsbList;
	}

	public String getHssfqyyp() {
		return hssfqyyp;
	}

	public void setHssfqyyp(String hssfqyyp) {
		this.hssfqyyp = hssfqyyp;
	}

	public Integer getDmsb() {
		return dmsb;
	}

	public void setDmsb(Integer dmsb) {
		this.dmsb = dmsb;
	}
	
	

}
