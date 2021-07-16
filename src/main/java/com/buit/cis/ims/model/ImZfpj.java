package com.buit.cis.ims.model;

import com.buit.commons.PageQuery;

import java.sql.Timestamp;

/**
 * 类名称：ImZfpj<br> 
 * 类描述：住院_作废票据
 * @author ZHOUHAISHENG 
 * @ApiModel(value="住院_作废票据")
 */
public class ImZfpj  extends PageQuery {
	//@ApiModelProperty(value="结帐日期")
    /** 结帐日期 */
    private Timestamp jzrq;
	//@ApiModelProperty(value="操作工号")
    /** 操作工号 */
    private String czgh;
	//@ApiModelProperty(value="票据类别 | 1.发票  2.收据 3.退票")
    /** 票据类别 | 1.发票  2.收据 3.退票 */
    private Integer pjlb;
	//@ApiModelProperty(value="票据号码")
    /** 票据号码 */
    private String pjhm;
	//@ApiModelProperty(value="机构ID")
    /** 机构ID */
    private Integer jgid;

    /** 设置:结帐日期  */
    public void setJzrq(Timestamp value) {
        this.jzrq = value;
    }
    /** 获取:结帐日期 */
    public Timestamp getJzrq() {
        return jzrq;
    }

    /** 设置:操作工号  */
    public void setCzgh(String value) {
        this.czgh = value;
    }
    /** 获取:操作工号 */
    public String getCzgh() {
        return czgh;
    }

    /** 设置:票据类别 | 1.发票  2.收据 3.退票  */
    public void setPjlb(Integer value) {
        this.pjlb = value;
    }
    /** 获取:票据类别 | 1.发票  2.收据 3.退票 */
    public Integer getPjlb() {
        return pjlb;
    }

    /** 设置:票据号码  */
    public void setPjhm(String value) {
        this.pjhm = value;
    }
    /** 获取:票据号码 */
    public String getPjhm() {
        return pjhm;
    }

    /** 设置:机构ID  */
    public void setJgid(Integer value) {
        this.jgid = value;
    }
    /** 获取:机构ID */
    public Integer getJgid() {
        return jgid;
    }


}
