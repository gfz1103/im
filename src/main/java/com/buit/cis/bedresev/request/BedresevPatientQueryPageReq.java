package com.buit.cis.bedresev.request;

import com.buit.commons.PageQuery;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 住院预约病人查询入参
 * zhouhaisheng
 */
@ApiModel(value = "住院预约病人查询入参")
public class BedresevPatientQueryPageReq extends PageQuery {

    @ApiModelProperty(value="卡号")
    private String cardNo;
    @ApiModelProperty(value="病人姓名")
    private String brxm;
    @ApiModelProperty(value="联系电话")
    private String lxdh;

    @ApiModelProperty(value="待床状态 0:未待床 1:已待床 2:逾期")
    private String dczt;

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
}
