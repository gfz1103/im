   
package com.buit.cis.dctwork.request;

import java.sql.Timestamp;

import com.buit.commons.PageQuery;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


/**
 * 类名称：CisJbzmd<br> 
 * 类描述：疾病证明单<br>
 * @author GONGFANGZHOU
 */
@ApiModel(value="疾病证明单_Req")
public class CisJbzmdReq  extends  PageQuery{
    @ApiModelProperty(value="记录序号(主键)")
    private Integer jlxh;
    @ApiModelProperty(value="病人类型(1:门诊2:住院)")
    private Integer brlx;
    @ApiModelProperty(value="住院号")
    private Integer zyh;
    @ApiModelProperty(value="门诊号码")
    private String mzhm;
    @ApiModelProperty(value="工作单位/家庭地址")
    private String jtdz;
    @ApiModelProperty(value="建议")
    private String jy;
    @ApiModelProperty(value="联系电话")
    private String lxdh;
    @ApiModelProperty(value="诊治医生")
    private Integer zzys;
    @ApiModelProperty(value="开具日期")
    private Timestamp kjrq;
    @ApiModelProperty(value="录入者代码", hidden = true)
    private Integer lrzdm;
    @ApiModelProperty(value="机构id", hidden = true)
    private Integer jgid;
    /**
     * 设置:记录序号(主键)
     */
    public void setJlxh(Integer value) {
        this.jlxh = value;
    }
    /**
     * 获取:记录序号(主键)
     */
    public Integer getJlxh() {
        return jlxh;
    }
    /**
     * 设置:病人类型(1:门诊2:住院)
     */
    public void setBrlx(Integer value) {
        this.brlx = value;
    }
    /**
     * 获取:病人类型(1:门诊2:住院)
     */
    public Integer getBrlx() {
        return brlx;
    }
    /**
     * 设置:住院号
     */
    public void setZyh(Integer value) {
        this.zyh = value;
    }
    /**
     * 获取:住院号
     */
    public Integer getZyh() {
        return zyh;
    }
    /**
     * 设置:门诊号码
     */
    public void setMzhm(String value) {
        this.mzhm = value;
    }
    /**
     * 获取:门诊号码
     */
    public String getMzhm() {
        return mzhm;
    }
    /**
     * 设置:工作单位/家庭地址
     */
    public void setJtdz(String value) {
        this.jtdz = value;
    }
    /**
     * 获取:工作单位/家庭地址
     */
    public String getJtdz() {
        return jtdz;
    }
    /**
     * 设置:建议
     */
    public void setJy(String value) {
        this.jy = value;
    }
    /**
     * 获取:建议
     */
    public String getJy() {
        return jy;
    }
    /**
     * 设置:联系电话
     */
    public void setLxdh(String value) {
        this.lxdh = value;
    }
    /**
     * 获取:联系电话
     */
    public String getLxdh() {
        return lxdh;
    }
    /**
     * 设置:诊治医生
     */
    public void setZzys(Integer value) {
        this.zzys = value;
    }
    /**
     * 获取:诊治医生
     */
    public Integer getZzys() {
        return zzys;
    }
    /**
     * 设置:开具日期
     */
    public void setKjrq(Timestamp value) {
        this.kjrq = value;
    }
    /**
     * 获取:开具日期
     */
    public Timestamp getKjrq() {
        return kjrq;
    }
    /**
     * 设置:录入者代码
     */
    public void setLrzdm(Integer value) {
        this.lrzdm = value;
    }
    /**
     * 获取:录入者代码
     */
    public Integer getLrzdm() {
        return lrzdm;
    }
    /**
     * 设置:机构id
     */
    public void setJgid(Integer value) {
        this.jgid = value;
    }
    /**
     * 获取:机构id
     */
    public Integer getJgid() {
        return jgid;
    }
}