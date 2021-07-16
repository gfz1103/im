package com.buit.cis.ims.request;

import com.buit.commons.PageQuery;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 住院管理-缴费管理(缴费处理-分页查询)入参
 */

@ApiModel(value="住院管理-缴费管理(缴费处理-分页查询)入参")
public class PatientPayPageReq extends PageQuery {
    @ApiModelProperty(value = "1：住院 2：留观")
    private String ywlx;
    @ApiModelProperty(value="收据号码 | 病人预缴款收据号码")
    private String sjhm;
    @ApiModelProperty(value="住院号")
    private Integer zyh;
    @ApiModelProperty(value="机构id",hidden = true)
    private Integer jgid;
//    @ApiModelProperty(value="作废判别",hidden = true)
//    private Integer zfpb;

    public String getSjhm() {
        return sjhm;
    }

    public void setSjhm(String sjhm) {
        this.sjhm = sjhm;
    }

    public Integer getJgid() {
        return jgid;
    }

    public void setJgid(Integer jgid) {
        this.jgid = jgid;
    }

    public Integer getZyh() {
        return zyh;
    }

    public void setZyh(Integer zyh) {
        this.zyh = zyh;
    }

//    public Integer getZfpb() {
//        return zfpb;
//    }
//
//    public void setZfpb(Integer zfpb) {
//        this.zfpb = zfpb;
//    }

    public String getYwlx() {
        return ywlx;
    }

    public void setYwlx(String ywlx) {
        this.ywlx = ywlx;
    }
}
