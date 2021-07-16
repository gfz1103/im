   
package com.buit.cis.nurse.request;

import java.sql.Timestamp;

import com.buit.commons.PageQuery;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


/**
 * 类名称：NisFsspgb<br> 
 * 类描述：深静脉血栓风险评估表<br>
 * @author GONGFANGZHOU
 */
@ApiModel(value="深静脉血栓风险评估表_Req")
public class NisFsspgbPrintReq  extends  PageQuery{

    @ApiModelProperty(value="一般情况1")
    private String ybqk1;
    @ApiModelProperty(value="一般情况2")
    private String ybqk2;
    @ApiModelProperty(value="一般情况3")
    private String ybqk3;
    @ApiModelProperty(value="一般情况4")
    private String ybqk4;
    @ApiModelProperty(value="一般情况5")
    private String ybqk5;
    @ApiModelProperty(value="一般情况6")
    private String ybqk6;
    @ApiModelProperty(value="一般情况7")
    private String ybqk7;
    @ApiModelProperty(value="特殊疾病1")
    private String tsjb1;
    @ApiModelProperty(value="特殊疾病2")
    private String tsjb2;
    @ApiModelProperty(value="特殊疾病3")
    private String tsjb3;
    @ApiModelProperty(value="特殊疾病4")
    private String tsjb4;
    @ApiModelProperty(value="特殊疾病5")
    private String tsjb5;
    @ApiModelProperty(value="特殊疾病6")
    private String tsjb6;
    @ApiModelProperty(value="特殊疾病7")
    private String tsjb7;
    @ApiModelProperty(value="特殊疾病8")
    private String tsjb8;
    @ApiModelProperty(value="特殊疾病9")
    private String tsjb9;
    @ApiModelProperty(value="手术和治疗1")
    private String sshzl1;
    @ApiModelProperty(value="手术和治疗2")
    private String sshzl2;
    @ApiModelProperty(value="手术和治疗3")
    private String sshzl3;
    @ApiModelProperty(value="手术和治疗4")
    private String sshzl4;
    @ApiModelProperty(value="手术和治疗5")
    private String sshzl5;
    @ApiModelProperty(value="手术和治疗6")
    private String sshzl6;
    @ApiModelProperty(value="护理措施")
    private String hlcs;
    @ApiModelProperty(value="护士签名")
    private String hsqm;
    @ApiModelProperty(value="日期")
    private String rq;
    @ApiModelProperty(value="总分")
    private Integer zf;
    @ApiModelProperty(value="机构id", hidden = true)
    private Integer jgid;
	public String getYbqk1() {
		return ybqk1;
	}
	public void setYbqk1(String ybqk1) {
		this.ybqk1 = ybqk1;
	}
	public String getYbqk2() {
		return ybqk2;
	}
	public void setYbqk2(String ybqk2) {
		this.ybqk2 = ybqk2;
	}
	public String getYbqk3() {
		return ybqk3;
	}
	public void setYbqk3(String ybqk3) {
		this.ybqk3 = ybqk3;
	}
	public String getYbqk4() {
		return ybqk4;
	}
	public void setYbqk4(String ybqk4) {
		this.ybqk4 = ybqk4;
	}
	public String getYbqk5() {
		return ybqk5;
	}
	public void setYbqk5(String ybqk5) {
		this.ybqk5 = ybqk5;
	}
	public String getYbqk6() {
		return ybqk6;
	}
	public void setYbqk6(String ybqk6) {
		this.ybqk6 = ybqk6;
	}
	public String getYbqk7() {
		return ybqk7;
	}
	public void setYbqk7(String ybqk7) {
		this.ybqk7 = ybqk7;
	}
	public String getTsjb1() {
		return tsjb1;
	}
	public void setTsjb1(String tsjb1) {
		this.tsjb1 = tsjb1;
	}
	public String getTsjb2() {
		return tsjb2;
	}
	public void setTsjb2(String tsjb2) {
		this.tsjb2 = tsjb2;
	}
	public String getTsjb3() {
		return tsjb3;
	}
	public void setTsjb3(String tsjb3) {
		this.tsjb3 = tsjb3;
	}
	public String getTsjb4() {
		return tsjb4;
	}
	public void setTsjb4(String tsjb4) {
		this.tsjb4 = tsjb4;
	}
	public String getTsjb5() {
		return tsjb5;
	}
	public void setTsjb5(String tsjb5) {
		this.tsjb5 = tsjb5;
	}
	public String getTsjb6() {
		return tsjb6;
	}
	public void setTsjb6(String tsjb6) {
		this.tsjb6 = tsjb6;
	}
	public String getTsjb7() {
		return tsjb7;
	}
	public void setTsjb7(String tsjb7) {
		this.tsjb7 = tsjb7;
	}
	public String getTsjb8() {
		return tsjb8;
	}
	public void setTsjb8(String tsjb8) {
		this.tsjb8 = tsjb8;
	}
	public String getTsjb9() {
		return tsjb9;
	}
	public void setTsjb9(String tsjb9) {
		this.tsjb9 = tsjb9;
	}
	public String getSshzl1() {
		return sshzl1;
	}
	public void setSshzl1(String sshzl1) {
		this.sshzl1 = sshzl1;
	}
	public String getSshzl2() {
		return sshzl2;
	}
	public void setSshzl2(String sshzl2) {
		this.sshzl2 = sshzl2;
	}
	public String getSshzl3() {
		return sshzl3;
	}
	public void setSshzl3(String sshzl3) {
		this.sshzl3 = sshzl3;
	}
	public String getSshzl4() {
		return sshzl4;
	}
	public void setSshzl4(String sshzl4) {
		this.sshzl4 = sshzl4;
	}
	public String getSshzl5() {
		return sshzl5;
	}
	public void setSshzl5(String sshzl5) {
		this.sshzl5 = sshzl5;
	}
	public String getSshzl6() {
		return sshzl6;
	}
	public void setSshzl6(String sshzl6) {
		this.sshzl6 = sshzl6;
	}
	public String getHlcs() {
		return hlcs;
	}
	public void setHlcs(String hlcs) {
		this.hlcs = hlcs;
	}
	public String getHsqm() {
		return hsqm;
	}
	public void setHsqm(String hsqm) {
		this.hsqm = hsqm;
	}
	public String getRq() {
		return rq;
	}
	public void setRq(String rq) {
		this.rq = rq;
	}
	public Integer getZf() {
		return zf;
	}
	public void setZf(Integer zf) {
		this.zf = zf;
	}
	public Integer getJgid() {
		return jgid;
	}
	public void setJgid(Integer jgid) {
		this.jgid = jgid;
	}
   
    
}