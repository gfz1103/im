package com.buit.cis.ims.request;

import com.buit.cis.ims.model.ImFeeFymx;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;


/**
 * 类名称：ImFeeFymx<br> 
 * 类描述：费用明细表(大病)<br>
 * @author GONGFANGZHOU
 */
@ApiModel(value="费用明细表_dbReq")
public class ImFeeFymxDbReq {

    @ApiModelProperty(value="住院号")
    private Integer zyh;
    
    @ApiModelProperty(value="住院病人集合")
    private List<ImFeeFymx> imFeeFymxList;

	public Integer getZyh() {
		return zyh;
	}

	public void setZyh(Integer zyh) {
		this.zyh = zyh;
	}

	public List<ImFeeFymx> getImFeeFymxList() {
		return imFeeFymxList;
	}

	public void setImFeeFymxList(List<ImFeeFymx> imFeeFymxList) {
		this.imFeeFymxList = imFeeFymxList;
	}
  
}
