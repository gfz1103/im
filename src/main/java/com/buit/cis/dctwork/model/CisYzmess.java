package com.buit.cis.dctwork.model;

import java.sql.Timestamp;
import com.buit.commons.PageQuery;

/**
 * 类名称：CisYzmess<br> 
 * 类描述：医嘱变动消息发送
 * @author GONGFANGZHOU 
 * @ApiModel(value="医嘱变动消息发送")
 */
public class CisYzmess  extends  PageQuery{
	//@ApiModelProperty(value="记录序号(主键)")
    /** 记录序号(主键) */
    private Integer jlxh;
	//@ApiModelProperty(value="住院号")
    /** 住院号 */
    private Integer zyh;
	//@ApiModelProperty(value="发送时间")
    /** 发送时间 */
    private Timestamp fssj;
	//@ApiModelProperty(value="状态(0:未处理1:处理中2:已处理)")
    /** 状态(0:未处理1:处理中2:已处理) */
    private Integer zt;
	//@ApiModelProperty(value="机构id")
    /** 机构id */
    private Integer jgid;
	//@ApiModelProperty(value="消息返回id")
    /** 消息返回id */
    private String messid;
	//@ApiModelProperty(value="发送人代码")
    /** 发送人代码 */
    private Integer fsrdm;
	//@ApiModelProperty(value="处理人代码")
    /** 处理人代码 */
    private Integer clrdm;

    /** 设置:记录序号(主键)  */
    public void setJlxh(Integer value) {
        this.jlxh = value;
    }
    /** 获取:记录序号(主键) */
    public Integer getJlxh() {
        return jlxh;
    }

    /** 设置:住院号  */
    public void setZyh(Integer value) {
        this.zyh = value;
    }
    /** 获取:住院号 */
    public Integer getZyh() {
        return zyh;
    }

    /** 设置:发送时间  */
    public void setFssj(Timestamp value) {
        this.fssj = value;
    }
    /** 获取:发送时间 */
    public Timestamp getFssj() {
        return fssj;
    }

    /** 设置:状态(0:未处理1:处理中2:已处理)  */
    public void setZt(Integer value) {
        this.zt = value;
    }
    /** 获取:状态(0:未处理1:处理中2:已处理) */
    public Integer getZt() {
        return zt;
    }

    /** 设置:机构id  */
    public void setJgid(Integer value) {
        this.jgid = value;
    }
    /** 获取:机构id */
    public Integer getJgid() {
        return jgid;
    }

    /** 设置:消息返回id  */
    public void setMessid(String value) {
        this.messid = value;
    }
    /** 获取:消息返回id */
    public String getMessid() {
        return messid;
    }

    /** 设置:发送人代码  */
    public void setFsrdm(Integer value) {
        this.fsrdm = value;
    }
    /** 获取:发送人代码 */
    public Integer getFsrdm() {
        return fsrdm;
    }

    /** 设置:处理人代码  */
    public void setClrdm(Integer value) {
        this.clrdm = value;
    }
    /** 获取:处理人代码 */
    public Integer getClrdm() {
        return clrdm;
    }


}