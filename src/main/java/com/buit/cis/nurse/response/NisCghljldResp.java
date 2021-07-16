   
package com.buit.cis.nurse.response;

import java.math.BigDecimal;
import java.sql.Timestamp;

import com.buit.commons.PageQuery;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


/**
 * 类名称：NisCghljld<br> 
 * 类描述：常规护理记录单<br>
 * @author GONGFANGZHOU
 */
@ApiModel(value="常规护理记录单_Resp")
public class NisCghljldResp  extends  PageQuery{
    @ApiModelProperty(value="主键")
    private Integer jlxh;
    @ApiModelProperty(value="住院号")
    private Integer zyh;
    @ApiModelProperty(value="模板类型")
    private String mblx;
    @ApiModelProperty(value="体温")
    private BigDecimal tw;
    @ApiModelProperty(value="脉搏")
    private Integer mb;
    @ApiModelProperty(value="呼吸")
    private Integer hx;
    @ApiModelProperty(value="BP")
    private String bp;
    @ApiModelProperty(value="疼痛评分")
    private Integer ttpf;
    @ApiModelProperty(value="自理能力评分")
    private Integer zlnlpf;
    @ApiModelProperty(value="导管评分")
    private Integer dgpf;
    @ApiModelProperty(value="压力性损伤")
    private Integer ylxss;
    @ApiModelProperty(value="跌倒坠床评分")
    private Integer ddzcpf;
    @ApiModelProperty(value="基础护理")
    private String jchl;
    @ApiModelProperty(value="护理宣教")
    private String hlxj;
    @ApiModelProperty(value="营养")
    private String yy;
    @ApiModelProperty(value="精神心理")
    private String jsxl;
    @ApiModelProperty(value="自定义列1")
    private Integer zdylone;
    @ApiModelProperty(value="自定义列2")
    private Integer zdyltwo;
    @ApiModelProperty(value="自定义列3")
    private Integer zdylthree;
    @ApiModelProperty(value="自定义列4")
    private Integer zdylfour;
    @ApiModelProperty(value="自定义列5")
    private Integer zdylfive;
    @ApiModelProperty(value="自定义列6")
    private Integer zdylsix;
    @ApiModelProperty(value="护理记录")
    private String hljl;
    @ApiModelProperty(value="护士签名")
    private String hsqm;
    @ApiModelProperty(value="日期")
    private String rq;
    @ApiModelProperty(value="机构id")
    private Integer jgid;
    @ApiModelProperty(value="生命体征关联id")
    private Integer smtzid;
    @ApiModelProperty(value="自理能力关联id")
    private Integer zlnlid;
    @ApiModelProperty(value="导管关联id")
    private Integer dgid;
    @ApiModelProperty(value="压力性损伤关联id")
    private Integer ylxssid;
    @ApiModelProperty(value="跌倒坠床关联id")
    private Integer ddzcid;
    @ApiModelProperty(value="自定义内容1")
    private String zdynr1;
    @ApiModelProperty(value="自定义内容2")
    private String zdynr2;
    @ApiModelProperty(value="自定义内容3")
    private String zdynr3;
    @ApiModelProperty(value="自定义内容4")
    private String zdynr4;
    @ApiModelProperty(value="自定义内容5")
    private String zdynr5;
    @ApiModelProperty(value="自定义内容6")
    private String zdynr6;
    @ApiModelProperty(value="意识")
    private String ys;
    @ApiModelProperty(value="SpO2")
    private BigDecimal spo2;
    @ApiModelProperty(value="皮肤")
    private String pf;
    @ApiModelProperty(value="导管类型")
    private String dglx;
    @ApiModelProperty(value="体位")
    private String stw;
    @ApiModelProperty(value="吸氧方式")
    private String xyfs;
    @ApiModelProperty(value="排痰")
    private String pt;
    @ApiModelProperty(value="其他导管")
    private String qtdg;
    @ApiModelProperty(value="血糖")
    private String xt;
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
     * 设置:体温
     */
    public void setTw(BigDecimal value) {
        this.tw = value;
    }
    /**
     * 获取:体温
     */
    public BigDecimal getTw() {
        return tw;
    }
    /**
     * 设置:脉搏
     */
    public void setMb(Integer value) {
        this.mb = value;
    }
    /**
     * 获取:脉搏
     */
    public Integer getMb() {
        return mb;
    }
    /**
     * 设置:呼吸
     */
    public void setHx(Integer value) {
        this.hx = value;
    }
    /**
     * 获取:呼吸
     */
    public Integer getHx() {
        return hx;
    }
    /**
     * 设置:BP
     */
    public void setBp(String value) {
        this.bp = value;
    }
    /**
     * 获取:BP
     */
    public String getBp() {
        return bp;
    }
    /**
     * 设置:疼痛评分
     */
    public void setTtpf(Integer value) {
        this.ttpf = value;
    }
    /**
     * 获取:疼痛评分
     */
    public Integer getTtpf() {
        return ttpf;
    }
    /**
     * 设置:自理能力评分
     */
    public void setZlnlpf(Integer value) {
        this.zlnlpf = value;
    }
    /**
     * 获取:自理能力评分
     */
    public Integer getZlnlpf() {
        return zlnlpf;
    }
    /**
     * 设置:导管评分
     */
    public void setDgpf(Integer value) {
        this.dgpf = value;
    }
    /**
     * 获取:导管评分
     */
    public Integer getDgpf() {
        return dgpf;
    }
    /**
     * 设置:压力性损伤
     */
    public void setYlxss(Integer value) {
        this.ylxss = value;
    }
    /**
     * 获取:压力性损伤
     */
    public Integer getYlxss() {
        return ylxss;
    }
    /**
     * 设置:跌倒坠床评分
     */
    public void setDdzcpf(Integer value) {
        this.ddzcpf = value;
    }
    /**
     * 获取:跌倒坠床评分
     */
    public Integer getDdzcpf() {
        return ddzcpf;
    }
    /**
     * 设置:基础护理
     */
    public void setJchl(String value) {
        this.jchl = value;
    }
    /**
     * 获取:基础护理
     */
    public String getJchl() {
        return jchl;
    }
    /**
     * 设置:护理宣教
     */
    public void setHlxj(String value) {
        this.hlxj = value;
    }
    /**
     * 获取:护理宣教
     */
    public String getHlxj() {
        return hlxj;
    }
    /**
     * 设置:营养
     */
    public void setYy(String yy) {
		this.yy = yy;
	}
    /**
     * 获取:营养
     */
    public String getYy() {
		return yy;
	}
    /**
     * 设置:精神心理
     */
    public void setJsxl(String jsxl) {
		this.jsxl = jsxl;
	}
    /**
     * 获取:精神心理
     */
	public String getJsxl() {
		return jsxl;
	}
	/**
     * 设置:自定义列1
     */
    public void setZdylone(Integer value) {
        this.zdylone = value;
    }
    /**
     * 获取:自定义列1
     */
    public Integer getZdylone() {
        return zdylone;
    }
    /**
     * 设置:自定义列2
     */
    public void setZdyltwo(Integer value) {
        this.zdyltwo = value;
    }
    /**
     * 获取:自定义列2
     */
    public Integer getZdyltwo() {
        return zdyltwo;
    }
    /**
     * 设置:自定义列3
     */
    public void setZdylthree(Integer value) {
        this.zdylthree = value;
    }
    /**
     * 获取:自定义列3
     */
    public Integer getZdylthree() {
        return zdylthree;
    }
    /**
     * 设置:自定义列4
     */
    public void setZdylfour(Integer value) {
        this.zdylfour = value;
    }
    /**
     * 获取:自定义列4
     */
    public Integer getZdylfour() {
        return zdylfour;
    }
    /**
     * 设置:自定义列5
     */
    public void setZdylfive(Integer value) {
        this.zdylfive = value;
    }
    /**
     * 获取:自定义列5
     */
    public Integer getZdylfive() {
        return zdylfive;
    }
    /**
     * 设置:自定义列6
     */
    public void setZdylsix(Integer value) {
        this.zdylsix = value;
    }
    /**
     * 获取:自定义列6
     */
    public Integer getZdylsix() {
        return zdylsix;
    }
    /**
     * 设置:护理记录
     */
    public void setHljl(String value) {
        this.hljl = value;
    }
    /**
     * 获取:护理记录
     */
    public String getHljl() {
        return hljl;
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
    public void setRq(String value) {
        this.rq = value;
    }
    /**
     * 获取:日期
     */
    public String getRq() {
        return rq;
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
    /**
     * 设置:生命体征关联id
     */
    public void setSmtzid(Integer value) {
        this.smtzid = value;
    }
    /**
     * 获取:生命体征关联id
     */
    public Integer getSmtzid() {
        return smtzid;
    }
    /**
     * 设置:自理能力关联id
     */
    public void setZlnlid(Integer value) {
        this.zlnlid = value;
    }
    /**
     * 获取:自理能力关联id
     */
    public Integer getZlnlid() {
        return zlnlid;
    }
    /**
     * 设置:导管关联id
     */
    public void setDgid(Integer value) {
        this.dgid = value;
    }
    /**
     * 获取:导管关联id
     */
    public Integer getDgid() {
        return dgid;
    }
    /**
     * 设置:压力性损伤关联id
     */
    public void setYlxssid(Integer value) {
        this.ylxssid = value;
    }
    /**
     * 获取:压力性损伤关联id
     */
    public Integer getYlxssid() {
        return ylxssid;
    }
    /**
     * 设置:跌倒坠床关联id
     */
    public void setDdzcid(Integer value) {
        this.ddzcid = value;
    }
    /**
     * 获取:跌倒坠床关联id
     */
    public Integer getDdzcid() {
        return ddzcid;
    }
	public String getZdynr1() {
		return zdynr1;
	}
	public void setZdynr1(String zdynr1) {
		this.zdynr1 = zdynr1;
	}
	public String getZdynr2() {
		return zdynr2;
	}
	public void setZdynr2(String zdynr2) {
		this.zdynr2 = zdynr2;
	}
	public String getZdynr3() {
		return zdynr3;
	}
	public void setZdynr3(String zdynr3) {
		this.zdynr3 = zdynr3;
	}
	public String getZdynr4() {
		return zdynr4;
	}
	public void setZdynr4(String zdynr4) {
		this.zdynr4 = zdynr4;
	}
	public String getZdynr5() {
		return zdynr5;
	}
	public void setZdynr5(String zdynr5) {
		this.zdynr5 = zdynr5;
	}
	public String getZdynr6() {
		return zdynr6;
	}
	public void setZdynr6(String zdynr6) {
		this.zdynr6 = zdynr6;
	}
	public String getYs() {
		return ys;
	}
	public void setYs(String ys) {
		this.ys = ys;
	}
	public BigDecimal getSpo2() {
		return spo2;
	}
	public void setSpo2(BigDecimal spo2) {
		this.spo2 = spo2;
	}
	public String getPf() {
		return pf;
	}
	public void setPf(String pf) {
		this.pf = pf;
	}
	public String getDglx() {
		return dglx;
	}
	public void setDglx(String dglx) {
		this.dglx = dglx;
	}
	public String getStw() {
		return stw;
	}
	public void setStw(String stw) {
		this.stw = stw;
	}
	public String getXyfs() {
		return xyfs;
	}
	public void setXyfs(String xyfs) {
		this.xyfs = xyfs;
	}
	public String getPt() {
		return pt;
	}
	public void setPt(String pt) {
		this.pt = pt;
	}
	public String getQtdg() {
		return qtdg;
	}
	public void setQtdg(String qtdg) {
		this.qtdg = qtdg;
	}
	public String getXt() {
		return xt;
	}
	public void setXt(String xt) {
		this.xt = xt;
	}
    
    
}