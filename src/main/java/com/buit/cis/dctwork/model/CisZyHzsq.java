package com.buit.cis.dctwork.model;

import java.sql.Timestamp;
import com.buit.commons.PageQuery;

import io.swagger.annotations.ApiModelProperty;

/**
 * 类名称：CisZyHzsq<br> 
 * 类描述：
 * @author GONGFANGZHOU 
 * @ApiModel(value="")
 */
public class CisZyHzsq  extends  PageQuery{
	@ApiModelProperty(value="申请序号")
    /** 申请序号 */
    private Integer sqxh;
	@ApiModelProperty(value="住院号")
    /** 住院号 */
    private String jzhm;
	@ApiModelProperty(value="申请科室")
    /** 申请科室 */
    private Integer sqks;
	@ApiModelProperty(value="申请医生")
    /** 申请医生 */
    private String sqys;
	@ApiModelProperty(value="申请时间")
    /** 申请时间 */
    private Timestamp sqsj;
	@ApiModelProperty(value="会诊目的")
    /** 会诊目的 */
    private String hzmd;
	@ApiModelProperty(value="会诊目的2")
    /** 会诊目的2 */
    private String hzmd2;
	@ApiModelProperty(value="会诊时间")
    /** 会诊时间 */
    private Timestamp hzsj;
	@ApiModelProperty(value="会诊邀请对象")
    /** 会诊邀请对象 */
    private String yqdx;
	@ApiModelProperty(value="紧急标志")
    /** 紧急标志 */
    private Integer jjbz;
	@ApiModelProperty(value="提交标志")
    /** 提交标志 */
    private Integer tjbz;
	@ApiModelProperty(value="提交医生")
    /** 提交医生 */
    private String tjys;
	@ApiModelProperty(value="提交时间")
    /** 提交时间 */
    private Timestamp tjsj;
	@ApiModelProperty(value="作废标志")
    /** 作废标志 */
    private Integer zfbz;
	@ApiModelProperty(value="结束标志")
    /** 结束标志 */
    private Integer jsbz;
	@ApiModelProperty(value="结束时间")
    /** 结束时间 */
    private Timestamp jssj;
	@ApiModelProperty(value="填写人员")
    /** 填写人员 */
    private String txry;
	@ApiModelProperty(value="机构ID")
    /** 机构ID */
    private Integer jgid;
	@ApiModelProperty(value="病情治疗")
    /** 病情治疗 */
    private String bqzl;
	@ApiModelProperty(value="拟邀请会诊医院  1:本院，2:外院")
    /** 拟邀请会诊医院  1:本院，2:外院 */
    private String nhzyy;
	@ApiModelProperty(value="拟邀请会诊医院名称")
    /** 拟邀请会诊医院名称 */
    private String nyqhzyy;
	@ApiModelProperty(value="拟邀会诊科室（本院）")
    /** 拟邀会诊科室（本院） */
    private String nyqhzks;
	@ApiModelProperty(value="拟邀会诊科室（外院）")
    /** 拟邀会诊科室（外院） */
    private String nyqhzks2;
	@ApiModelProperty(value="拟邀会诊医生（外院）")
    /** 拟邀会诊医生（外院） */
    private String nyqhzys2;
	@ApiModelProperty(value="主治以上医师/科主任医师签名")
    /** 主治以上医师/科主任医师签名 */
    private String kzrys;

    private String messid;

    /** 设置:申请序号  */
    public void setSqxh(Integer value) {
        this.sqxh = value;
    }
    /** 获取:申请序号 */
    public Integer getSqxh() {
        return sqxh;
    }

    /** 设置:住院号  */
    public void setJzhm(String value) {
        this.jzhm = value;
    }
    /** 获取:住院号 */
    public String getJzhm() {
        return jzhm;
    }

    /** 设置:申请科室  */
    public void setSqks(Integer value) {
        this.sqks = value;
    }
    /** 获取:申请科室 */
    public Integer getSqks() {
        return sqks;
    }

    /** 设置:申请医生  */
    public void setSqys(String value) {
        this.sqys = value;
    }
    /** 获取:申请医生 */
    public String getSqys() {
        return sqys;
    }

    /** 设置:申请时间  */
    public void setSqsj(Timestamp value) {
        this.sqsj = value;
    }
    /** 获取:申请时间 */
    public Timestamp getSqsj() {
        return sqsj;
    }

    /** 设置:会诊目的  */
    public void setHzmd(String value) {
        this.hzmd = value;
    }
    /** 获取:会诊目的 */
    public String getHzmd() {
        return hzmd;
    }

    /** 设置:会诊目的2  */
    public void setHzmd2(String value) {
        this.hzmd2 = value;
    }
    /** 获取:会诊目的2 */
    public String getHzmd2() {
        return hzmd2;
    }

    /** 设置:会诊时间  */
    public void setHzsj(Timestamp value) {
        this.hzsj = value;
    }
    /** 获取:会诊时间 */
    public Timestamp getHzsj() {
        return hzsj;
    }

    /** 设置:会诊邀请对象  */
    public void setYqdx(String value) {
        this.yqdx = value;
    }
    /** 获取:会诊邀请对象 */
    public String getYqdx() {
        return yqdx;
    }

    /** 设置:紧急标志  */
    public void setJjbz(Integer value) {
        this.jjbz = value;
    }
    /** 获取:紧急标志 */
    public Integer getJjbz() {
        return jjbz;
    }

    /** 设置:提交标志  */
    public void setTjbz(Integer value) {
        this.tjbz = value;
    }
    /** 获取:提交标志 */
    public Integer getTjbz() {
        return tjbz;
    }

    /** 设置:提交医生  */
    public void setTjys(String value) {
        this.tjys = value;
    }
    /** 获取:提交医生 */
    public String getTjys() {
        return tjys;
    }

    /** 设置:提交时间  */
    public void setTjsj(Timestamp value) {
        this.tjsj = value;
    }
    /** 获取:提交时间 */
    public Timestamp getTjsj() {
        return tjsj;
    }

    /** 设置:作废标志  */
    public void setZfbz(Integer value) {
        this.zfbz = value;
    }
    /** 获取:作废标志 */
    public Integer getZfbz() {
        return zfbz;
    }

    /** 设置:结束标志  */
    public void setJsbz(Integer value) {
        this.jsbz = value;
    }
    /** 获取:结束标志 */
    public Integer getJsbz() {
        return jsbz;
    }

    /** 设置:结束时间  */
    public void setJssj(Timestamp value) {
        this.jssj = value;
    }
    /** 获取:结束时间 */
    public Timestamp getJssj() {
        return jssj;
    }

    /** 设置:填写人员  */
    public void setTxry(String value) {
        this.txry = value;
    }
    /** 获取:填写人员 */
    public String getTxry() {
        return txry;
    }

    /** 设置:机构ID  */
    public void setJgid(Integer value) {
        this.jgid = value;
    }
    /** 获取:机构ID */
    public Integer getJgid() {
        return jgid;
    }

    /** 设置:病情治疗  */
    public void setBqzl(String value) {
        this.bqzl = value;
    }
    /** 获取:病情治疗 */
    public String getBqzl() {
        return bqzl;
    }

    /** 设置:拟邀请会诊医院  1:本院，2:外院  */
    public void setNhzyy(String value) {
        this.nhzyy = value;
    }
    /** 获取:拟邀请会诊医院  1:本院，2:外院 */
    public String getNhzyy() {
        return nhzyy;
    }

    /** 设置:拟邀请会诊医院名称  */
    public void setNyqhzyy(String value) {
        this.nyqhzyy = value;
    }
    /** 获取:拟邀请会诊医院名称 */
    public String getNyqhzyy() {
        return nyqhzyy;
    }

    /** 设置:拟邀会诊科室（本院）  */
    public void setNyqhzks(String value) {
        this.nyqhzks = value;
    }
    /** 获取:拟邀会诊科室（本院） */
    public String getNyqhzks() {
        return nyqhzks;
    }

    /** 设置:拟邀会诊科室（外院）  */
    public void setNyqhzks2(String value) {
        this.nyqhzks2 = value;
    }
    /** 获取:拟邀会诊科室（外院） */
    public String getNyqhzks2() {
        return nyqhzks2;
    }

    /** 设置:拟邀会诊医生（外院）  */
    public void setNyqhzys2(String value) {
        this.nyqhzys2 = value;
    }
    /** 获取:拟邀会诊医生（外院） */
    public String getNyqhzys2() {
        return nyqhzys2;
    }

    /** 设置:主治以上医师/科主任医师签名  */
    public void setKzrys(String value) {
        this.kzrys = value;
    }
    /** 获取:主治以上医师/科主任医师签名 */
    public String getKzrys() {
        return kzrys;
    }

    public String getMessid() {
        return messid;
    }

    public void setMessid(String messid) {
        this.messid = messid;
    }
}
