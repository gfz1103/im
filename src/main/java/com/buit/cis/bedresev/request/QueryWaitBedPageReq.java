package com.buit.cis.bedresev.request;

import com.buit.commons.PageQuery;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 *预约待床分页查询
 * zhouhaisheng
 */
@ApiModel(value = "预约待床分页查询")
public class QueryWaitBedPageReq extends PageQuery {
    @ApiModelProperty(value = "预约科室")
    private Integer yyks;
    @ApiModelProperty(value = "卡号")
    private String cardNo;
    @ApiModelProperty(value = "病人姓名")
    private String brxm;
    @ApiModelProperty(value = "性别限制 | 1：男    2：女    3：不限")
    private Integer cwxb;
    @ApiModelProperty(value = "联系电话")
    private String lxdh;

    @ApiModelProperty(value = "待床状态 0:未待床 1:已待床 2:逾期")
    private String dczt;

    public Integer getYyks() {
        return yyks;
    }

    public void setYyks(Integer yyks) {
        this.yyks = yyks;
    }

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

    public String getBrxm() {
        return brxm;
    }

    public void setBrxm(String brxm) {
        this.brxm = brxm;
    }

    public String getLxdh() {
        return lxdh;
    }

    public void setLxdh(String lxdh) {
        this.lxdh = lxdh;
    }

    public String getDczt() {
        return dczt;
    }

    public void setDczt(String dczt) {
        this.dczt = dczt;
    }

    public Integer getCwxb() {
        return cwxb;
    }

    public void setCwxb(Integer cwxb) {
        this.cwxb = cwxb;
    }
}
