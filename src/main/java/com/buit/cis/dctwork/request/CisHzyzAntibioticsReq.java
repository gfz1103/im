   
package com.buit.cis.dctwork.request;



import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;



/**
 * 类名称：CisHzyzAntibioticsReq<br> 
 * 类描述：住院_病区医嘱_抗菌药物<br>
 * @author GONGFANGZHOU
 */
@ApiModel(value="住院_病区医嘱_antibioticsReq")
public class CisHzyzAntibioticsReq  {
   
	@ApiModelProperty(value="记录序号")
    private Integer jlxh;
  
    @ApiModelProperty(value="申请类型")
    private Integer sqlx;
    
    @ApiModelProperty(value="使用理由")
    private String syly;

	public Integer getJlxh() {
		return jlxh;
	}

	public void setJlxh(Integer jlxh) {
		this.jlxh = jlxh;
	}

	public Integer getSqlx() {
		return sqlx;
	}

	public void setSqlx(Integer sqlx) {
		this.sqlx = sqlx;
	}

	public String getSyly() {
		return syly;
	}

	public void setSyly(String syly) {
		this.syly = syly;
	}
    
    

}
