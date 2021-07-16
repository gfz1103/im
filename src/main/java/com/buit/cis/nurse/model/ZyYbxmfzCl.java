package com.buit.cis.nurse.model;

import java.sql.Timestamp;
import com.buit.commons.PageQuery;

/**
 * 类名称：ZyYbxmfzCl<br> 
 * 类描述：医保项目分组(材料)
 * @author GONGFANGZHOU 
 * @ApiModel(value="医保项目分组(材料)")
 */
public class ZyYbxmfzCl  extends  PageQuery{
	//@ApiModelProperty(value="ID")
    /** ID */
    private Integer id;
	//@ApiModelProperty(value="分组编号")
    /** 分组编号 */
    private String fzbh;
	//@ApiModelProperty(value="分组名称")
    /** 分组名称 */
    private String fzmc;
	//@ApiModelProperty(value="分组限价")
    /** 分组限价 */
    private String fzxj;
	//@ApiModelProperty(value="费用类别")
    /** 费用类别 */
    private String fylb;
	//@ApiModelProperty(value="物价编码")
    /** 物价编码 */
    private String wjbm;
	//@ApiModelProperty(value="医保编码")
    /** 医保编码 */
    private String ybbm;
	//@ApiModelProperty(value="费用归并")
    /** 费用归并 */
    private Integer fygb;

    /** 设置:ID  */
    public void setId(Integer value) {
        this.id = value;
    }
    /** 获取:ID */
    public Integer getId() {
        return id;
    }

    /** 设置:分组编号  */
    public void setFzbh(String value) {
        this.fzbh = value;
    }
    /** 获取:分组编号 */
    public String getFzbh() {
        return fzbh;
    }

    /** 设置:分组名称  */
    public void setFzmc(String value) {
        this.fzmc = value;
    }
    /** 获取:分组名称 */
    public String getFzmc() {
        return fzmc;
    }

    /** 设置:分组限价  */
    public void setFzxj(String value) {
        this.fzxj = value;
    }
    /** 获取:分组限价 */
    public String getFzxj() {
        return fzxj;
    }

    /** 设置:费用类别  */
    public void setFylb(String value) {
        this.fylb = value;
    }
    /** 获取:费用类别 */
    public String getFylb() {
        return fylb;
    }

    /** 设置:物价编码  */
    public void setWjbm(String value) {
        this.wjbm = value;
    }
    /** 获取:物价编码 */
    public String getWjbm() {
        return wjbm;
    }

    /** 设置:医保编码  */
    public void setYbbm(String value) {
        this.ybbm = value;
    }
    /** 获取:医保编码 */
    public String getYbbm() {
        return ybbm;
    }

    /** 设置:费用归并  */
    public void setFygb(Integer value) {
        this.fygb = value;
    }
    /** 获取:费用归并 */
    public Integer getFygb() {
        return fygb;
    }


}