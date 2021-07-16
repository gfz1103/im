   
package com.buit.cis.nurse.request;

import java.sql.Timestamp;

import com.buit.commons.PageQuery;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


/**
 * 类名称：NisZxjmpgb<br> 
 * 类描述：中心静脉导管相关性感染风险因素评估表<br>
 * @author GONGFANGZHOU
 */
@ApiModel(value="中心静脉导管相关性感染风险因素评估表_Req")
public class NisZxjmpgbReq  extends  PageQuery{
    @ApiModelProperty(value="主键")
    private Integer jlxh;
    @ApiModelProperty(value="住院号")
    private Integer zyh;
    @ApiModelProperty(value="模板类型")
    private String mblx;
    @ApiModelProperty(value="年龄")
    private String nl;
    @ApiModelProperty(value="基础疾病")
    private String jcjb;
    @ApiModelProperty(value="置管部位")
    private String zgbw;
    @ApiModelProperty(value="置管状态")
    private String zgzt;
    @ApiModelProperty(value="留置时间")
    private String lzsj;
    @ApiModelProperty(value="导管材质")
    private String dgcz;
    @ApiModelProperty(value="导管腔数")
    private String dgqs;
    @ApiModelProperty(value="输液接口")
    private String syjk;
    @ApiModelProperty(value="输注液体")
    private String szyt;
    @ApiModelProperty(value="护理措施")
    private String hlcs;
    @ApiModelProperty(value="护士签名")
    private String hsqm;
    @ApiModelProperty(value="日期")
    private Timestamp rq;
    @ApiModelProperty(value="总分")
    private Integer zf;
    @ApiModelProperty(value="机构id")
    private Integer jgid;
    /**
     * 设置:主键
     */
    public void setJlxh(Integer value) {
        this.jlxh = value;
    }
    /**
     * 获取:主键
     */
    public Integer getJlxh() {
        return jlxh;
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
     * 设置:模板类型
     */
    public void setMblx(String value) {
        this.mblx = value;
    }
    /**
     * 获取:模板类型
     */
    public String getMblx() {
        return mblx;
    }
    /**
     * 设置:年龄
     */
    public void setNl(String value) {
        this.nl = value;
    }
    /**
     * 获取:年龄
     */
    public String getNl() {
        return nl;
    }
    /**
     * 设置:基础疾病
     */
    public void setJcjb(String value) {
        this.jcjb = value;
    }
    /**
     * 获取:基础疾病
     */
    public String getJcjb() {
        return jcjb;
    }
    /**
     * 设置:置管部位
     */
    public void setZgbw(String value) {
        this.zgbw = value;
    }
    /**
     * 获取:置管部位
     */
    public String getZgbw() {
        return zgbw;
    }
    /**
     * 设置:置管状态
     */
    public void setZgzt(String value) {
        this.zgzt = value;
    }
    /**
     * 获取:置管状态
     */
    public String getZgzt() {
        return zgzt;
    }
    /**
     * 设置:留置时间
     */
    public void setLzsj(String value) {
        this.lzsj = value;
    }
    /**
     * 获取:留置时间
     */
    public String getLzsj() {
        return lzsj;
    }
    /**
     * 设置:导管材质
     */
    public void setDgcz(String value) {
        this.dgcz = value;
    }
    /**
     * 获取:导管材质
     */
    public String getDgcz() {
        return dgcz;
    }
    /**
     * 设置:导管腔数
     */
    public void setDgqs(String value) {
        this.dgqs = value;
    }
    /**
     * 获取:导管腔数
     */
    public String getDgqs() {
        return dgqs;
    }
    /**
     * 设置:输液接口
     */
    public void setSyjk(String value) {
        this.syjk = value;
    }
    /**
     * 获取:输液接口
     */
    public String getSyjk() {
        return syjk;
    }
    /**
     * 设置:输注液体
     */
    public void setSzyt(String value) {
        this.szyt = value;
    }
    /**
     * 获取:输注液体
     */
    public String getSzyt() {
        return szyt;
    }
    /**
     * 设置:护理措施
     */
    public void setHlcs(String value) {
        this.hlcs = value;
    }
    /**
     * 获取:护理措施
     */
    public String getHlcs() {
        return hlcs;
    }
    /**
     * 设置:护士签名
     */
    public void setHsqm(String value) {
        this.hsqm = value;
    }
    /**
     * 获取:护士签名
     */
    public String getHsqm() {
        return hsqm;
    }
    /**
     * 设置:日期
     */
    public void setRq(Timestamp value) {
        this.rq = value;
    }
    /**
     * 获取:日期
     */
    public Timestamp getRq() {
        return rq;
    }
    /**
     * 设置:总分
     */
    public void setZf(Integer value) {
        this.zf = value;
    }
    /**
     * 获取:总分
     */
    public Integer getZf() {
        return zf;
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