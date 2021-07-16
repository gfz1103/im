package com.buit.cis.dctwork.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.util.List;

/**
 * 类名称：CisHzyz<br>
 * 类描述：住院_病区医嘱<br>
 * @author GONGFANGZHOU
 */
@ApiModel(value="住院_病区医嘱_CancelReq")
public class CisHzyzCancelReq {

	@NotNull(message = "住院号不能为空")
	@ApiModelProperty(value="住院号")
	private Integer zyh;

	@NotNull(message = "长期临时医嘱类型不能为空")
	@ApiModelProperty(value="临时医嘱(0:长期1:临时)")
	private Integer lsyz;
	
	@ApiModelProperty(value="取消医嘱数据集合")
	private List<CisHzyzCancelBody> cancelBodyList;
	
	public Integer getZyh() {
		return zyh;
	}

	public void setZyh(Integer zyh) {
		this.zyh = zyh;
	}

	public Integer getLsyz() {
		return lsyz;
	}

	public void setLsyz(Integer lsyz) {
		this.lsyz = lsyz;
	}

	public List<CisHzyzCancelBody> getCancelBodyList() {
		return cancelBodyList;
	}

	public void setCancelBodyList(List<CisHzyzCancelBody> cancelBodyList) {
		this.cancelBodyList = cancelBodyList;
	}

	public static class CisHzyzCancelBody {
		
		@NotNull(message = "记录序号不能为空")
		@ApiModelProperty(value="记录序号")
		private Integer jlxh;

		@ApiModelProperty(value="组套标志")
		private Integer ztbz;
		
		@ApiModelProperty(value="医嘱类型(0:检查1:检验2:备血3:手术4:会诊5:住院处方6:理疗)")
	    private Integer yzlx;
		
		@ApiModelProperty(value="申请id")
	    private Integer sqid;

	    @ApiModelProperty(value="抗菌药物")
	    private Integer kjywsp;
	    
	    @ApiModelProperty(value="药品类型")
	    private Integer yplx;

		public Integer getJlxh() {
			return jlxh;
		}

		public void setJlxh(Integer jlxh) {
			this.jlxh = jlxh;
		}

		public Integer getZtbz() {
			return ztbz;
		}

		public void setZtbz(Integer ztbz) {
			this.ztbz = ztbz;
		}

		public Integer getYzlx() {
			return yzlx;
		}

		public void setYzlx(Integer yzlx) {
			this.yzlx = yzlx;
		}

		public Integer getSqid() {
			return sqid;
		}

		public void setSqid(Integer sqid) {
			this.sqid = sqid;
		}

		public Integer getKjywsp() {
			return kjywsp;
		}

		public void setKjywsp(Integer kjywsp) {
			this.kjywsp = kjywsp;
		}

		public Integer getYplx() {
			return yplx;
		}

		public void setYplx(Integer yplx) {
			this.yplx = yplx;
		}
		
	}
	
}
