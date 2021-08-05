   
package com.buit.cis.dctwork.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.constraints.NotNull;
import java.sql.Timestamp;


/**
 * 类名称：CisHzyzDataReq<br> 
 * 类描述：住院_病区医嘱_数据盒<br>
 * @author GONGFANGZHOU
 */
@ApiModel(value="住院_病区医嘱_SkinReq")
public class CisHzyzSkinReq {

	@NotNull(message = "记录序号不能为空")
	@ApiModelProperty(value="记录序号")
    private Integer jlxh;

	@NotNull(message = "病人id不能为空")
	@ApiModelProperty(value="病人id")
    private Integer brid;

	@NotNull(message = "皮试结果不能为空")
	@ApiModelProperty(value="皮试结果")
    private Integer psjg;

	@NotNull(message = "药品序号不能为空")
	@ApiModelProperty(value="药品序号")
    private Integer ypxh;

	public Integer getJlxh() {
		return jlxh;
	}

	public void setJlxh(Integer jlxh) {
		this.jlxh = jlxh;
	}

	public Integer getBrid() {
		return brid;
	}

	public void setBrid(Integer brid) {
		this.brid = brid;
	}

	public Integer getPsjg() {
		return psjg;
	}

	public void setPsjg(Integer psjg) {
		this.psjg = psjg;
	}

	public Integer getYpxh() {
		return ypxh;
	}

	public void setYpxh(Integer ypxh) {
		this.ypxh = ypxh;
	}
}
