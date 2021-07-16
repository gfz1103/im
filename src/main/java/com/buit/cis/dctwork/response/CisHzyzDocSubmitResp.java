   
package com.buit.cis.dctwork.response;


import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


/**
 * 类名称：CisHzyzDocSubmitResp<br> 
 * 类描述：住院_病区医嘱_药品提交返回数据集<br>
 * @author GONGFANGZHOU
 */
@ApiModel(value="住院_病区医嘱_docsubmitResp")
public class CisHzyzDocSubmitResp {
	
	@ApiModelProperty(value="抗菌药物使用最大天数")
    private Integer kjywzdts;

	@ApiModelProperty(value="抗菌药物越级申请方式")
    private Integer sqlx;
	
    @ApiModelProperty(value="返回消息")
    private String message;
    
    @ApiModelProperty(value="库存消息")
    private String kcMessage;
    
    @ApiModelProperty(value="返回医嘱信息集合")
    private List<DocSubmitBody> yzxxList;

	public Integer getKjywzdts() {
		return kjywzdts;
	}

	public void setKjywzdts(Integer kjywzdts) {
		this.kjywzdts = kjywzdts;
	}

	public Integer getSqlx() {
		return sqlx;
	}

	public void setSqlx(Integer sqlx) {
		this.sqlx = sqlx;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}	
    
    public String getKcMessage() {
		return kcMessage;
	}

	public void setKcMessage(String kcMessage) {
		this.kcMessage = kcMessage;
	}

	public List<DocSubmitBody> getYzxxList() {
		return yzxxList;
	}

	public void setYzxxList(List<DocSubmitBody> yzxxList) {
		this.yzxxList = yzxxList;
	}

	public static class DocSubmitBody {
    	
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
