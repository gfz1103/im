package com.buit.cis.ims.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * 批量修改折扣比例入参
 * zhouhaisheng
 */
@ApiModel(value = "批量修改折扣比例入参")
public class UpdateZkblListReq {
    @ApiModelProperty(value = "修改折扣比例入参数组")
    private List<UpdateZkblReq> updateZkblReqs;

    public List<UpdateZkblReq> getUpdateZkblReqs() {
        return updateZkblReqs;
    }

    public void setUpdateZkblReqs(List<UpdateZkblReq> updateZkblReqs) {
        this.updateZkblReqs = updateZkblReqs;
    }
}
