package com.buit.cis.ims.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


/**
 * 类名称：ImYgpjUpdateReq<br>
 * 类描述：住院_员工票据修改<br>
 * @author zhouhaisheng
 */
@ApiModel(value="住院_员工票据修改")
public class ImYgpjUpdateReq{
    @ApiModelProperty(value="主键id")
    private Integer jlxh;
    @ApiModelProperty(value="终止号码")
    private String zzhm;
    @ApiModelProperty(value="使用号码")
    private String dqhm;

    public Integer getJlxh() {
        return jlxh;
    }

    public void setJlxh(Integer jlxh) {
        this.jlxh = jlxh;
    }

    public String getDqhm() {
        return dqhm;
    }

    public void setDqhm(String dqhm) {
        this.dqhm = dqhm;
    }




    public String getZzhm() {
        return zzhm;
    }

    public void setZzhm(String zzhm) {
        this.zzhm = zzhm;
    }
}
