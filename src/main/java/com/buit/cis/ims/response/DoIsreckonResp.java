package com.buit.cis.ims.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 判断本日是否已做过结帐返回参数
 * zhouhaisheng
 */
@ApiModel(value = "判断本日是否已做过结帐返回参数")
public class DoIsreckonResp {
    @ApiModelProperty(value = "是否做过结账")
    private boolean isRreckon;
    @ApiModelProperty(value = "上次结账日期")
    private String jzrq;

    public boolean isRreckon() {
        return isRreckon;
    }

    public void setRreckon(boolean rreckon) {
        isRreckon = rreckon;
    }

    public String getJzrq() {
        return jzrq;
    }

    public void setJzrq(String jzrq) {
        this.jzrq = jzrq;
    }
}
