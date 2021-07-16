   
package com.buit.cis.nurse.response;

import java.math.BigDecimal;
import java.sql.Timestamp;

import com.buit.commons.PageQuery;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


/**
 * 类名称：CisHzyz<br> 
 * 类描述：住院_病区医嘱_按项目查询<br>
 * @author GONGFANGZHOU
 */
@ApiModel(value="住院_病区医嘱_xmResp")
public class CisHzyzXmResp {
    
	@ApiModelProperty(value="项目序号")
	private Integer ypxh;
	
	@ApiModelProperty(value="项目名称")
	private String yzmc;
	
	@ApiModelProperty(value="组套标志(0非组套标志,1组套标志)")
	private Integer ztbz;

	public Integer getYpxh() {
		return ypxh;
	}

	public void setYpxh(Integer ypxh) {
		this.ypxh = ypxh;
	}

	public String getYzmc() {
		return yzmc;
	}

	public void setYzmc(String yzmc) {
		this.yzmc = yzmc;
	}

	public Integer getZtbz() {
		return ztbz;
	}

	public void setZtbz(Integer ztbz) {
		this.ztbz = ztbz;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((ypxh == null) ? 0 : ypxh.hashCode());
		result = prime * result + ((yzmc == null) ? 0 : yzmc.hashCode());
		result = prime * result + ((ztbz == null) ? 0 : ztbz.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		CisHzyzXmResp other = (CisHzyzXmResp) obj;
		if (ypxh == null) {
			if (other.ypxh != null) {
				return false;
			}
		} else if (!ypxh.equals(other.ypxh)) {
			return false;
		}
		if (yzmc == null) {
			if (other.yzmc != null) {
				return false;
			}
		} else if (!yzmc.equals(other.yzmc)) {
			return false;
		}		
		if (ztbz == null) {
			if (other.ztbz != null) {
				return false;
			}
		} else if (!ztbz.equals(other.ztbz)) {
			return false;
		}
		return true;
	}
	
}
