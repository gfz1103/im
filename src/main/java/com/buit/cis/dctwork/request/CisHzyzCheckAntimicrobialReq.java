   
package com.buit.cis.dctwork.request;



import java.util.List;

import org.springframework.web.bind.annotation.RequestParam;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiParam;



/**
 * 类名称：CisHzyzAntibioticsReq<br> 
 * 类描述：住院_病区医嘱_抗菌药物<br>
 * @author GONGFANGZHOU
 */
@ApiModel(value="住院_病区医嘱_CheckAntimicrobialReq")
public class CisHzyzCheckAntimicrobialReq  {
   
	@ApiModelProperty(value = "登录用户名")
	private String loginName;
	
	@ApiModelProperty(value = "密码")
	private String passwd;
	
	@ApiModelProperty(value = "医嘱信息集合")
	private List<YzxxBody> yzxxList;

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getPasswd() {
		return passwd;
	}

	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}

	public List<YzxxBody> getYzxxList() {
		return yzxxList;
	}

	public void setYzxxList(List<YzxxBody> yzxxList) {
		this.yzxxList = yzxxList;
	}

	public static class YzxxBody {
    	
    	@ApiModelProperty(value="抗生素等级(1:非限制,2:限制,3:特殊)")
        private Integer kssdj;
    	
    	@ApiModelProperty(value="记录序号")
	    private Integer jlxh;
    	    
	    @ApiModelProperty(value="医嘱组号")
	    private Integer yzzh;

		public Integer getKssdj() {
			return kssdj;
		}

		public void setKssdj(Integer kssdj) {
			this.kssdj = kssdj;
		}

		public Integer getJlxh() {
			return jlxh;
		}

		public void setJlxh(Integer jlxh) {
			this.jlxh = jlxh;
		}

		public Integer getYzzh() {
			return yzzh;
		}

		public void setYzzh(Integer yzzh) {
			this.yzzh = yzzh;
		}	    
	    
    }
}
