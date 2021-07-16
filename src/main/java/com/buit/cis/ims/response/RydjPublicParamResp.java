package com.buit.cis.ims.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 *
 * 类描述：<br>
 *         获取住院号码和病案号码，收据号码
 * @author zhouhaisheng
 */
@ApiModel(value="获取住院号码和病案号码，收据号码")
public class RydjPublicParamResp {

    @ApiModelProperty(value="住院号码")
    private String zyhm;
    @ApiModelProperty(value="病案号码")
    private String bahm;
    @ApiModelProperty(value="收据号码")
    private String sjhm;
    @ApiModelProperty(value = "默认付款方式")
    private Integer fkfs;

    public String getZyhm() {
        return zyhm;
    }

    public void setZyhm(String zyhm) {
        this.zyhm = zyhm;
    }

    public String getBahm() {
        return bahm;
    }

    public void setBahm(String bahm) {
        this.bahm = bahm;
    }

    public String getSjhm() {
        return sjhm;
    }

    public void setSjhm(String sjhm) {
        this.sjhm = sjhm;
    }

    public Integer getFkfs() {
        return fkfs;
    }

    public void setFkfs(Integer fkfs) {
        this.fkfs = fkfs;
    }
}
