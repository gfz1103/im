package com.buit.cis.dctwork.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="领血申请打印_Req")
public class ClinicalLeadBloodReq {
	
	@ApiModelProperty(value="申请单号")
	private Integer sqdh;

    public Integer getSqdh() {
        return sqdh;
    }

    public void setSqdh(Integer sqdh) {
        this.sqdh = sqdh;
    }
}
