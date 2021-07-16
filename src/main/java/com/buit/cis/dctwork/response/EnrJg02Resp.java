   
package com.buit.cis.dctwork.response;

import java.math.BigDecimal;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


/**
 * 类名称：EnrJg02<br> 
 * 类描述：<br>
 * @author GONGFANGZHOU
 */
@ApiModel(value="结构表_Resp")
public class EnrJg02Resp {
    @ApiModelProperty(value="项目编号")
    private Integer xmbh;
    @ApiModelProperty(value="结构编号")
    private Integer jgbh;
    @ApiModelProperty(value="元素编号")
    private Integer ysbh;
    @ApiModelProperty(value="项目名称")
    private String xmmc;
    @ApiModelProperty(value="显示名称")
    private String xsmc;
    @ApiModelProperty(value="项目取值:可填写默认值")
    private String xmqz;
    @ApiModelProperty(value="开始列号:表达列的关系,用于显示时识别数据窗口中的列号,若相同则为活动列")
    private Integer kslh;
    @ApiModelProperty(value="结束列号:表达列的关系,")
    private Integer jslh;
    @ApiModelProperty(value="活动标志:预先自动计算是否是一个活动列,由程序控制。")
    private Integer hdbz;
    @ApiModelProperty(value="元素扩展:Oracle中为varchar2(4000)")
    private String yskz;
    @ApiModelProperty(value="数据格式:数值型或日期型元素有效")
    private String sjgs;
    @ApiModelProperty(value="正常值上限")
    private BigDecimal zczsx;
    @ApiModelProperty(value="正常值下限")
    private BigDecimal zczxx;
    @ApiModelProperty(value="有效值上限")
    private BigDecimal yxzsx;
    @ApiModelProperty(value="有效值下限")
    private BigDecimal yxzxx;
    @ApiModelProperty(value="是否必填:0否 1是")
    private Integer sfbt;
    @ApiModelProperty(value="禁止编辑:0否 1是")
    private Integer jzbj;
    @ApiModelProperty(value="复制原态:0否 1是")
    private Integer fzyt;
    @ApiModelProperty(value="项目导出:引用功能使用")
    private Integer xmdc;
    @ApiModelProperty(value="页面处理方式:用于页眉项目 0不处理 1标记过程 2标记初始 3标记最新 DICT104")
    private Integer ymclfs;
    @ApiModelProperty(value="换行间隔:即每n行文字算一个数据行，0单行 1单行换行 2每2行换行...DICT116")
    private Integer hhjg;
    @ApiModelProperty(value="项目宽度:字节数标识项目宽度,1个汉字占2个字节")
    private Integer xmkd;
    @ApiModelProperty(value="自定义项目:1用户自定义的活动列项目")
    private Integer zdyxm;
    
    @ApiModelProperty(value="yskzBnt")
    private String yskzBnt;
    
    @ApiModelProperty(value="元素类型:1输入型 2列表型 3引用型 4特殊型 5字典型 6体征型 DICT100")
    private Integer yslx;
    
    @ApiModelProperty(value="数据类型:1文本 2数字 3日期 4图片 DICT101")
    private Integer sjlx;

    /**
     * 设置:项目编号
     */
    public void setXmbh(Integer value) {
        this.xmbh = value;
    }
    /**
     * 获取:项目编号
     */
    public Integer getXmbh() {
        return xmbh;
    }
    /**
     * 设置:结构编号
     */
    public void setJgbh(Integer value) {
        this.jgbh = value;
    }
    /**
     * 获取:结构编号
     */
    public Integer getJgbh() {
        return jgbh;
    }
    /**
     * 设置:元素编号
     */
    public void setYsbh(Integer value) {
        this.ysbh = value;
    }
    /**
     * 获取:元素编号
     */
    public Integer getYsbh() {
        return ysbh;
    }
    /**
     * 设置:项目名称
     */
    public void setXmmc(String value) {
        this.xmmc = value;
    }
    /**
     * 获取:项目名称
     */
    public String getXmmc() {
        return xmmc;
    }
    /**
     * 设置:显示名称
     */
    public void setXsmc(String value) {
        this.xsmc = value;
    }
    /**
     * 获取:显示名称
     */
    public String getXsmc() {
        return xsmc;
    }
    /**
     * 设置:项目取值:可填写默认值
     */
    public void setXmqz(String value) {
        this.xmqz = value;
    }
    /**
     * 获取:项目取值:可填写默认值
     */
    public String getXmqz() {
        return xmqz;
    }
    /**
     * 设置:开始列号:表达列的关系,用于显示时识别数据窗口中的列号,若相同则为活动列
     */
    public void setKslh(Integer value) {
        this.kslh = value;
    }
    /**
     * 获取:开始列号:表达列的关系,用于显示时识别数据窗口中的列号,若相同则为活动列
     */
    public Integer getKslh() {
        return kslh;
    }
    /**
     * 设置:结束列号:表达列的关系,
     */
    public void setJslh(Integer value) {
        this.jslh = value;
    }
    /**
     * 获取:结束列号:表达列的关系,
     */
    public Integer getJslh() {
        return jslh;
    }
    /**
     * 设置:活动标志:预先自动计算是否是一个活动列,由程序控制。
     */
    public void setHdbz(Integer value) {
        this.hdbz = value;
    }
    /**
     * 获取:活动标志:预先自动计算是否是一个活动列,由程序控制。
     */
    public Integer getHdbz() {
        return hdbz;
    }
    /**
     * 设置:元素扩展:Oracle中为varchar2(4000)
     */
    public void setYskz(String value) {
        this.yskz = value;
    }
    /**
     * 获取:元素扩展:Oracle中为varchar2(4000)
     */
    public String getYskz() {
        return yskz;
    }
    /**
     * 设置:数据格式:数值型或日期型元素有效
     */
    public void setSjgs(String value) {
        this.sjgs = value;
    }
    /**
     * 获取:数据格式:数值型或日期型元素有效
     */
    public String getSjgs() {
        return sjgs;
    }
    /**
     * 设置:正常值上限
     */
    public void setZczsx(BigDecimal value) {
        this.zczsx = value;
    }
    /**
     * 获取:正常值上限
     */
    public BigDecimal getZczsx() {
        return zczsx;
    }
    /**
     * 设置:正常值下限
     */
    public void setZczxx(BigDecimal value) {
        this.zczxx = value;
    }
    /**
     * 获取:正常值下限
     */
    public BigDecimal getZczxx() {
        return zczxx;
    }
    /**
     * 设置:有效值上限
     */
    public void setYxzsx(BigDecimal value) {
        this.yxzsx = value;
    }
    /**
     * 获取:有效值上限
     */
    public BigDecimal getYxzsx() {
        return yxzsx;
    }
    /**
     * 设置:有效值下限
     */
    public void setYxzxx(BigDecimal value) {
        this.yxzxx = value;
    }
    /**
     * 获取:有效值下限
     */
    public BigDecimal getYxzxx() {
        return yxzxx;
    }
    /**
     * 设置:是否必填:0否 1是
     */
    public void setSfbt(Integer value) {
        this.sfbt = value;
    }
    /**
     * 获取:是否必填:0否 1是
     */
    public Integer getSfbt() {
        return sfbt;
    }
    /**
     * 设置:禁止编辑:0否 1是
     */
    public void setJzbj(Integer value) {
        this.jzbj = value;
    }
    /**
     * 获取:禁止编辑:0否 1是
     */
    public Integer getJzbj() {
        return jzbj;
    }
    /**
     * 设置:复制原态:0否 1是
     */
    public void setFzyt(Integer value) {
        this.fzyt = value;
    }
    /**
     * 获取:复制原态:0否 1是
     */
    public Integer getFzyt() {
        return fzyt;
    }
    /**
     * 设置:项目导出:引用功能使用
     */
    public void setXmdc(Integer value) {
        this.xmdc = value;
    }
    /**
     * 获取:项目导出:引用功能使用
     */
    public Integer getXmdc() {
        return xmdc;
    }
    /**
     * 设置:页面处理方式:用于页眉项目 0不处理 1标记过程 2标记初始 3标记最新 DICT104
     */
    public void setYmclfs(Integer value) {
        this.ymclfs = value;
    }
    /**
     * 获取:页面处理方式:用于页眉项目 0不处理 1标记过程 2标记初始 3标记最新 DICT104
     */
    public Integer getYmclfs() {
        return ymclfs;
    }
    /**
     * 设置:换行间隔:即每n行文字算一个数据行，0单行 1单行换行 2每2行换行...DICT116
     */
    public void setHhjg(Integer value) {
        this.hhjg = value;
    }
    /**
     * 获取:换行间隔:即每n行文字算一个数据行，0单行 1单行换行 2每2行换行...DICT116
     */
    public Integer getHhjg() {
        return hhjg;
    }
    /**
     * 设置:项目宽度:字节数标识项目宽度,1个汉字占2个字节
     */
    public void setXmkd(Integer value) {
        this.xmkd = value;
    }
    /**
     * 获取:项目宽度:字节数标识项目宽度,1个汉字占2个字节
     */
    public Integer getXmkd() {
        return xmkd;
    }
    /**
     * 设置:自定义项目:1用户自定义的活动列项目
     */
    public void setZdyxm(Integer value) {
        this.zdyxm = value;
    }
    /**
     * 获取:自定义项目:1用户自定义的活动列项目
     */
    public Integer getZdyxm() {
        return zdyxm;
    }
	
	public String getYskzBnt() {
		return yskzBnt;
	}
	public void setYskzBnt(String yskzBnt) {
		this.yskzBnt = yskzBnt;
	}
	public Integer getYslx() {
		return yslx;
	}
	
	public void setYslx(Integer yslx) {
		this.yslx = yslx;
	}
	
	public Integer getSjlx() {
		return sjlx;
	}
	
	public void setSjlx(Integer sjlx) {
		this.sjlx = sjlx;
	}
    
    
}