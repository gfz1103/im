   
package com.buit.cis.nurse.request;

import java.util.List;

import com.buit.commons.PageQuery;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


/**
 * 类名称：CisHzyz<br> 
 * 类描述：住院_病区医嘱卡片打印<br>
 * @author GONGFANGZHOU
 */
@ApiModel(value="住院_病区医嘱_MenuReq")
public class CisHzyzMenuReq{

    /**
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
	*/
	private static final long serialVersionUID = 1L;

	
	@ApiModelProperty(value="病区代码")
	private Integer bqdm;

    @ApiModelProperty(value="住院号集合")
    private List<Integer> zyhList;
    
    @ApiModelProperty(value="类型")
	private Integer type;

	public Integer getBqdm() {
		return bqdm;
	}

	public void setBqdm(Integer bqdm) {
		this.bqdm = bqdm;
	}

	public List<Integer> getZyhList() {
		return zyhList;
	}

	public void setZyhList(List<Integer> zyhList) {
		this.zyhList = zyhList;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}
    
    
    
}
