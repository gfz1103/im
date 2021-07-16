package com.buit.cis.ims.request;

import com.buit.commons.PageQuery;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 住院管理-缴费管理(缴费查询)入参
 */
@ApiModel(value="住院管理-缴费管理(缴费处理-缴费查询)入参")
public class QueryPatientCostReq extends PageQuery {
    @ApiModelProperty(value = "1：住院 2：留观")
    private String ywlx;
    @ApiModelProperty(value="开始时间")
    private String startDate;
    @ApiModelProperty(value="结束时间")
    private String endDate;

    @ApiModelProperty(value="缴款方式 | 1:现金,2:支票(与ZY_JKFS表中JKFS字段对应")
    private Integer jkfs;

    @ApiModelProperty(value="住院号码")
    private String zyhm;

    @ApiModelProperty(value="缴款员")
    private String czgh;

    @ApiModelProperty(value="机构代码",hidden = true)
    private Integer jgid;


    @ApiModelProperty(value="科室")
    private Integer brks;
//    @ApiModelProperty(value="作废判别",hidden = true)
//    private Integer zfpb;

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public Integer getJkfs() {
        return jkfs;
    }

    public void setJkfs(Integer jkfs) {
        this.jkfs = jkfs;
    }

    public String getZyhm() {
        return zyhm;
    }

    public void setZyhm(String zyhm) {
        this.zyhm = zyhm;
    }

    public String getCzgh() {
        return czgh;
    }

    public void setCzgh(String czgh) {
        this.czgh = czgh;
    }

    public Integer getJgid() {
        return jgid;
    }

    public void setJgid(Integer jgid) {
        this.jgid = jgid;
    }

    public Integer getBrks() {
        return brks;
    }

    public void setBrks(Integer brks) {
        this.brks = brks;
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
