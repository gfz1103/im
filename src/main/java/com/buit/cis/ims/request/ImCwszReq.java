package com.buit.cis.ims.request;

import com.buit.commons.PageQuery;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


/**
 * 类名称：ImCwsz<br>
 * 类描述：住院_床位设置<br>
 * @author zhouhaisheng
 */
@ApiModel(value="住院_床位设置")
public class ImCwszReq  extends PageQuery {
    @ApiModelProperty(value="业务类型 | 1:住院 2：留观")
    private String ywlx;

    @ApiModelProperty(value="机构id")
    private Integer jgid;

    @ApiModelProperty(value="病人床号")
    private String brch;

    @ApiModelProperty(value="床位病区 字典:sys_dict_config:18")
    private Integer ksdm;
    
    @ApiModelProperty(value="床位组号")
    private Integer cwzh;

    public String getBrch() {
        return brch;
    }

    public void setBrch(String brch) {
        this.brch = brch;
    }

    public Integer getKsdm() {
        return ksdm;
    }

    public void setKsdm(Integer ksdm) {
        this.ksdm = ksdm;
    }

	public Integer getCwzh() {
		return cwzh;
	}

	public void setCwzh(Integer cwzh) {
		this.cwzh = cwzh;
	}

    public String getYwlx() {
        return ywlx;
    }

    public void setYwlx(String ywlx) {
        this.ywlx = ywlx;
    }

    public Integer getJgid() {
        return jgid;
    }

    public void setJgid(Integer jgid) {
        this.jgid = jgid;
    }
}
