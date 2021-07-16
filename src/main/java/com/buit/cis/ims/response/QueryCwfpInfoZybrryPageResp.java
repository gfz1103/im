package com.buit.cis.ims.response;

import com.buit.commons.PageQuery;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.sql.Timestamp;

/**
 * zhouhaisheng
 * 住院病人床位分配住院病人列表信息分页显示
 */
@ApiModel(value = "住院病人床位分配住院病人列表信息分页显示")
public class QueryCwfpInfoZybrryPageResp extends PageQuery {
    @ApiModelProperty(value = "住院号")
    private Integer zyh;
    @ApiModelProperty(value = "住院号码")
    private String zyhm;
    @ApiModelProperty(value = "病人姓名")
    private String brxm;
    @ApiModelProperty(value = "病人性别 DIC_GBSJ01:PD0000000260")
    private Integer brxb;
    @ApiModelProperty(value = "病人性质 sys_dict_config:14")
    private Integer brxz;
    @ApiModelProperty(value = "病人科室 sys_dict_config:15")
    private Integer brks;
    @ApiModelProperty(value = "入院日期")
    private Timestamp ryrq;
    @ApiModelProperty(value = "出院判别 0：在院病人\\n1：出院证明\\n2：预结出院\\n8：正常出院\\n9：终结出院\\n99 注销出院")
    private Integer cypb;

     public String getZyhm() {
      return zyhm;
     }

     public void setZyhm(String zyhm) {
      this.zyhm = zyhm;
     }

     public String getBrxm() {
      return brxm;
     }

     public void setBrxm(String brxm) {
      this.brxm = brxm;
     }

     public Integer getBrxb() {
      return brxb;
     }

     public void setBrxb(Integer brxb) {
      this.brxb = brxb;
     }

     public Integer getBrxz() {
      return brxz;
     }

     public void setBrxz(Integer brxz) {
      this.brxz = brxz;
     }

     public Integer getBrks() {
      return brks;
     }

     public void setBrks(Integer brks) {
      this.brks = brks;
     }

     public Timestamp getRyrq() {
      return ryrq;
     }

     public void setRyrq(Timestamp ryrq) {
      this.ryrq = ryrq;
     }

     public Integer getCypb() {
      return cypb;
     }

     public void setCypb(Integer cypb) {
      this.cypb = cypb;
     }

    public Integer getZyh() {
        return zyh;
    }

    public void setZyh(Integer zyh) {
        this.zyh = zyh;
    }
}
