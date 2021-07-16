package com.buit.cis.bedresev.request;

import com.buit.commons.PageQuery;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 床位预约查询请求参数
 * zhouhaisheng
 */
@ApiModel(value = "床位预约查询请求参数")
public class BedresevQueryReq extends PageQuery {

    @ApiModelProperty(value = "医疗机构id",hidden = true)
    private  Integer jgid;


    @ApiModelProperty(value = "卡号")
    private  String cardNo;

    @ApiModelProperty(value ="病人姓名")
    private String brxm;

    @ApiModelProperty(value = "联系电话")
    private String lxdh;

    public Integer getJgid() {
        return jgid;
    }

    public void setJgid(Integer jgid) {
        this.jgid = jgid;
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
}
