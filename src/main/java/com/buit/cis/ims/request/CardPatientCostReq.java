package com.buit.cis.ims.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 类名称：病人管理-帐卡-费用帐卡(费用清单)入参<br>
 * 类描述：病人管理-帐卡-费用帐卡(费用清单)入参<br>
 * @author ZHOUHAISHENG
 */
@ApiModel(value="病人管理-帐卡-费用帐卡(费用清单)入参")
public class CardPatientCostReq {
/*    @ApiModelProperty(value="医疗机构id",hidden = true)
    private Integer jgid;*/
    @ApiModelProperty(value="住院号")
    private Integer zyh;

    @ApiModelProperty(value="查询类型 0：全部 1:医疗 2：药品")
    private Integer queryType;
    @ApiModelProperty(value="开始日期")
    private String startDate;
    @ApiModelProperty(value="结束日期")
    private String endDate;
    @ApiModelProperty(value="结算类型")
    private String jslx;
/*    @ApiModelProperty(value="结算次数")
    private Integer jscs=0;*/


    public Integer getZyh() {
        return zyh;
    }

    public void setZyh(Integer zyh) {
        this.zyh = zyh;
    }

    public Integer getQueryType() {
        return queryType;
    }

    public void setQueryType(Integer queryType) {
        this.queryType = queryType;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getJslx() {
        return jslx;
    }

    public void setJslx(String jslx) {
        this.jslx = jslx;
    }

    /*    public Integer getJgid() {
        return jgid;
    }

    public void setJgid(Integer jgid) {
        this.jgid = jgid;
    }

    public Integer getJscs() {
        return jscs;
    }

    public void setJscs(Integer jscs) {
        this.jscs = jscs;
    }
*/

}
