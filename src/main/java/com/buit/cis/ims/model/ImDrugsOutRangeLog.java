package com.buit.cis.ims.model;

import com.buit.commons.PageQuery;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.sql.Timestamp;

/**
 * @author jiangwei
 * @Description 药品费用超出结算时间记录表
 * @Date 2021-05-03
 */
@ApiModel(value = "药品费用超出结算时间记录表")
public class ImDrugsOutRangeLog extends PageQuery {
    @ApiModelProperty(value = "记录序号")
    private Integer jlxh;
    @ApiModelProperty(value = "住院号")
    private Integer zyh;
    @ApiModelProperty(value = "原费用明细的主键 | 关联IM_FEE_FYMX.JLXH,")
    private Integer fymxJlxh;
    @ApiModelProperty(value = "实际费用日期 | 调换之前原费用明细的费用日期")
    private Timestamp sjfyrq;


    public void setJlxh(Integer value) {
        this.jlxh = value;
    }

    public Integer getJlxh() {
        return jlxh;
    }


    public void setZyh(Integer value) {
        this.zyh = value;
    }

    public Integer getZyh() {
        return zyh;
    }


    public void setFymxJlxh(Integer value) {
        this.fymxJlxh = value;
    }

    public Integer getFymxJlxh() {
        return fymxJlxh;
    }


    public void setSjfyrq(Timestamp value) {
        this.sjfyrq = value;
    }

    public Timestamp getSjfyrq() {
        return sjfyrq;
    }


}