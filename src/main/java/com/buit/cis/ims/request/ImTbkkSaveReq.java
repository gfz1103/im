package com.buit.cis.ims.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Map;


/**
 * 类名称：ImTbkk<br>
 * 类描述：住院退补缴款保存入参<br>
 * @author zhouhaisheng
 */
@ApiModel(value="住院退补缴款保存入参")
public class ImTbkkSaveReq {

    @ApiModelProperty(value="缴款合计")
    private String jkhj;
    @ApiModelProperty(value="缴款日期")
    private String jkrq;
    @ApiModelProperty(value="自费合计")
    private String zfhj;
    @ApiModelProperty(value="操作工号")
    private Integer czgh;
    @ApiModelProperty(value="住院号")
    private Integer zyh;
    @ApiModelProperty(value="病人床号")
    private String brch;
    @ApiModelProperty(value="缴款方式")
    private Integer jkfs;
    @ApiModelProperty(value="预付卡余额")
    private String yfkye;
    @ApiModelProperty(value="住院号码")
    private String zyhm;
    @ApiModelProperty(value="就诊卡号")
    private String jzkh;
    @ApiModelProperty(value="剩余合计")
    private String syhj;
    @ApiModelProperty(value="支票号码 | 病人缴款为支票时的支票号码")
    private String zphm;
    @ApiModelProperty(value="收据号码 | 病人预缴款收据号码")
    private String sjhm;
    @ApiModelProperty(value="发票号码 | 病人结算发票号码")
    private String fphm;
    @ApiModelProperty(value="病人姓名")
    private String brxm;
    @ApiModelProperty(value="缴款金额")
    private String jkje;
    @ApiModelProperty(value="pos机缴款参数")
    private Map<String, Object> pos;
    @ApiModelProperty(value = "缴款类型 | 0：正常预缴   1：结算抵扣   2：取消结算   3：注销找退   4：出院找退")
    private Integer jklx;

    public String getJkhj() {
        return jkhj;
    }

    public void setJkhj(String jkhj) {
        this.jkhj = jkhj;
    }

    public String getJkrq() {
        return jkrq;
    }

    public void setJkrq(String jkrq) {
        this.jkrq = jkrq;
    }

    public String getZfhj() {
        return zfhj;
    }

    public void setZfhj(String zfhj) {
        this.zfhj = zfhj;
    }

    public Integer getCzgh() {
        return czgh;
    }

    public void setCzgh(Integer czgh) {
        this.czgh = czgh;
    }

    public Integer getZyh() {
        return zyh;
    }

    public void setZyh(Integer zyh) {
        this.zyh = zyh;
    }

    public String getBrch() {
        return brch;
    }

    public void setBrch(String brch) {
        this.brch = brch;
    }

    public Integer getJkfs() {
        return jkfs;
    }

    public void setJkfs(Integer jkfs) {
        this.jkfs = jkfs;
    }

    public String getYfkye() {
        return yfkye;
    }

    public void setYfkye(String yfkye) {
        this.yfkye = yfkye;
    }

    public String getZyhm() {
        return zyhm;
    }

    public void setZyhm(String zyhm) {
        this.zyhm = zyhm;
    }

    public String getJzkh() {
        return jzkh;
    }

    public void setJzkh(String jzkh) {
        this.jzkh = jzkh;
    }

    public String getSyhj() {
        return syhj;
    }

    public void setSyhj(String syhj) {
        this.syhj = syhj;
    }

    public String getZphm() {
        return zphm;
    }

    public void setZphm(String zphm) {
        this.zphm = zphm;
    }

    public String getSjhm() {
        return sjhm;
    }

    public void setSjhm(String sjhm) {
        this.sjhm = sjhm;
    }

    public String getBrxm() {
        return brxm;
    }

    public void setBrxm(String brxm) {
        this.brxm = brxm;
    }

    public String getJkje() {
        return jkje;
    }

    public void setJkje(String jkje) {
        this.jkje = jkje;
    }

    public Map<String, Object> getPos() {
        return pos;
    }

    public void setPos(Map<String, Object> pos) {
        this.pos = pos;
    }

    public String getFphm() {
        return fphm;
    }

    public void setFphm(String fphm) {
        this.fphm = fphm;
    }

    public Integer getJklx() {
        return jklx;
    }

    public void setJklx(Integer jklx) {
        this.jklx = jklx;
    }
}
