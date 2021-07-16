package com.buit.cis.ims.request;

import com.buit.commons.PageQuery;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 类名称：住院病人床位设置<br>
 * 类描述：住院_病人入院<br>
 * @author ZHOUHAISHENG
 */
@ApiModel(value="住院病人床位设置入参")
public class ZybrCwszReq  extends PageQuery {
    @ApiModelProperty(value="病人床号")
    private String brch;
    @ApiModelProperty(value="床位科室 字典sys_dict_config:15")
    private Integer cwks;
    @ApiModelProperty(value="床位病区 字典:sys_dict_config:18")
    private Integer cwbq;
    @ApiModelProperty(value="住院号码")
    private String zyhm;
    @ApiModelProperty(value="机构id" ,hidden = true)
    private Integer jgid;
    @ApiModelProperty(value="1:住院 2：留观")
    private String ywlx;

    public String getBrch() {
        return brch;
    }

    public void setBrch(String brch) {
        this.brch = brch;
    }

    public Integer getCwks() {
        return cwks;
    }

    public void setCwks(Integer cwks) {
        this.cwks = cwks;
    }

    public Integer getCwbq() {
        return cwbq;
    }

    public void setCwbq(Integer cwbq) {
        this.cwbq = cwbq;
    }

    public String getZyhm() {
        return zyhm;
    }

    public void setZyhm(String zyhm) {
        this.zyhm = zyhm;
    }

    public Integer getJgid() {
        return jgid;
    }

    public void setJgid(Integer jgid) {
        this.jgid = jgid;
    }

    public String getYwlx() {
        return ywlx;
    }

    public void setYwlx(String ywlx) {
        this.ywlx = ywlx;
    }
}
