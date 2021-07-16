package com.buit.cis.dctwork.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import com.buit.commons.PageQuery;

/**
 * 类名称：EnrJbys<br> 
 * 类描述：
 * @author GONGFANGZHOU 
 * @ApiModel(value="")
 */
public class EnrJbys  extends  PageQuery{
	//@ApiModelProperty(value="元素编号:流水号")
    /** 元素编号:流水号 */
    private Integer ysbh;
	//@ApiModelProperty(value="目录编号,关联EMR_MLLB.MLBH")
    /** 目录编号,关联EMR_MLLB.MLBH */
    private Integer mlbh;
	//@ApiModelProperty(value="元素名称")
    /** 元素名称 */
    private String ysmc;
	//@ApiModelProperty(value="元素类型:1输入型 2列表型 3引用型 4特殊型 5字典型 6体征型 DICT100")
    /** 元素类型:1输入型 2列表型 3引用型 4特殊型 5字典型 6体征型 DICT100 */
    private Integer yslx;
	//@ApiModelProperty(value="类型扩展，Oracle中为varchar(4000)")
    /** 类型扩展，Oracle中为varchar(4000) */
    private String lxkz;
	//@ApiModelProperty(value="数据类型:1文本 2数字 3日期 4图片 DICT101")
    /** 数据类型:1文本 2数字 3日期 4图片 DICT101 */
    private Integer sjlx;
	//@ApiModelProperty(value="数据格式:")
    /** 数据格式: */
    private String sjgs;
	//@ApiModelProperty(value="正常值上限")
    /** 正常值上限 */
    private BigDecimal zczsx;
	//@ApiModelProperty(value="正常值下限")
    /** 正常值下限 */
    private BigDecimal zczxx;
	//@ApiModelProperty(value="有效值上限")
    /** 有效值上限 */
    private BigDecimal yxzsx;
	//@ApiModelProperty(value="有效值下限")
    /** 有效值下限 */
    private BigDecimal yxzxx;
	//@ApiModelProperty(value="是否必填:0否 1是")
    /** 是否必填:0否 1是 */
    private Integer sfbt;
	//@ApiModelProperty(value="禁止编辑:0否 1是")
    /** 禁止编辑:0否 1是 */
    private Integer jzbj;
	//@ApiModelProperty(value="复制原态:0否 1是")
    /** 复制原态:0否 1是 */
    private Integer fzyt;
	//@ApiModelProperty(value="拼音码")
    /** 拼音码 */
    private String pydm;
	//@ApiModelProperty(value="五笔码")
    /** 五笔码 */
    private String wbdm;
	//@ApiModelProperty(value="备注信息")
    /** 备注信息 */
    private String bzxx;
	//@ApiModelProperty(value="使用标志:0使用 1注销 DICT102")
    /** 使用标志:0使用 1注销 DICT102 */
    private Integer zxbz;

    /** 设置:元素编号:流水号  */
    public void setYsbh(Integer value) {
        this.ysbh = value;
    }
    /** 获取:元素编号:流水号 */
    public Integer getYsbh() {
        return ysbh;
    }

    /** 设置:目录编号,关联EMR_MLLB.MLBH  */
    public void setMlbh(Integer value) {
        this.mlbh = value;
    }
    /** 获取:目录编号,关联EMR_MLLB.MLBH */
    public Integer getMlbh() {
        return mlbh;
    }

    /** 设置:元素名称  */
    public void setYsmc(String value) {
        this.ysmc = value;
    }
    /** 获取:元素名称 */
    public String getYsmc() {
        return ysmc;
    }

    /** 设置:元素类型:1输入型 2列表型 3引用型 4特殊型 5字典型 6体征型 DICT100  */
    public void setYslx(Integer value) {
        this.yslx = value;
    }
    /** 获取:元素类型:1输入型 2列表型 3引用型 4特殊型 5字典型 6体征型 DICT100 */
    public Integer getYslx() {
        return yslx;
    }

    /** 设置:类型扩展，Oracle中为varchar(4000)  */
    public void setLxkz(String value) {
        this.lxkz = value;
    }
    /** 获取:类型扩展，Oracle中为varchar(4000) */
    public String getLxkz() {
        return lxkz;
    }

    /** 设置:数据类型:1文本 2数字 3日期 4图片 DICT101  */
    public void setSjlx(Integer value) {
        this.sjlx = value;
    }
    /** 获取:数据类型:1文本 2数字 3日期 4图片 DICT101 */
    public Integer getSjlx() {
        return sjlx;
    }

    /** 设置:数据格式:  */
    public void setSjgs(String value) {
        this.sjgs = value;
    }
    /** 获取:数据格式: */
    public String getSjgs() {
        return sjgs;
    }

    /** 设置:正常值上限  */
    public void setZczsx(BigDecimal value) {
        this.zczsx = value;
    }
    /** 获取:正常值上限 */
    public BigDecimal getZczsx() {
        return zczsx;
    }

    /** 设置:正常值下限  */
    public void setZczxx(BigDecimal value) {
        this.zczxx = value;
    }
    /** 获取:正常值下限 */
    public BigDecimal getZczxx() {
        return zczxx;
    }

    /** 设置:有效值上限  */
    public void setYxzsx(BigDecimal value) {
        this.yxzsx = value;
    }
    /** 获取:有效值上限 */
    public BigDecimal getYxzsx() {
        return yxzsx;
    }

    /** 设置:有效值下限  */
    public void setYxzxx(BigDecimal value) {
        this.yxzxx = value;
    }
    /** 获取:有效值下限 */
    public BigDecimal getYxzxx() {
        return yxzxx;
    }

    /** 设置:是否必填:0否 1是  */
    public void setSfbt(Integer value) {
        this.sfbt = value;
    }
    /** 获取:是否必填:0否 1是 */
    public Integer getSfbt() {
        return sfbt;
    }

    /** 设置:禁止编辑:0否 1是  */
    public void setJzbj(Integer value) {
        this.jzbj = value;
    }
    /** 获取:禁止编辑:0否 1是 */
    public Integer getJzbj() {
        return jzbj;
    }

    /** 设置:复制原态:0否 1是  */
    public void setFzyt(Integer value) {
        this.fzyt = value;
    }
    /** 获取:复制原态:0否 1是 */
    public Integer getFzyt() {
        return fzyt;
    }

    /** 设置:拼音码  */
    public void setPydm(String value) {
        this.pydm = value;
    }
    /** 获取:拼音码 */
    public String getPydm() {
        return pydm;
    }

    /** 设置:五笔码  */
    public void setWbdm(String value) {
        this.wbdm = value;
    }
    /** 获取:五笔码 */
    public String getWbdm() {
        return wbdm;
    }

    /** 设置:备注信息  */
    public void setBzxx(String value) {
        this.bzxx = value;
    }
    /** 获取:备注信息 */
    public String getBzxx() {
        return bzxx;
    }

    /** 设置:使用标志:0使用 1注销 DICT102  */
    public void setZxbz(Integer value) {
        this.zxbz = value;
    }
    /** 获取:使用标志:0使用 1注销 DICT102 */
    public Integer getZxbz() {
        return zxbz;
    }


}