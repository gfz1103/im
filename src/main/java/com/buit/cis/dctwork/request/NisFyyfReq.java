package com.buit.cis.dctwork.request;

import com.buit.commons.PageQuery;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


/**
 * 类名称：NisFyyf<br>
 * 类描述：病区_发药药房<br>
 * @author LAPTOP-6GUR25CC
 */
@ApiModel(value="病区_发药药房")
public class NisFyyfReq  extends PageQuery {
    @ApiModelProperty(value="机构代码")
    private Integer jgid;
    @ApiModelProperty(value="病区代码")
    private String bqdm;
    @ApiModelProperty(value="功能分类")
    private Integer gnfl;
    @ApiModelProperty(value="医嘱类型")
    private Integer type;
    @ApiModelProperty(value="药房识别")
    private String yfsb;
    @ApiModelProperty(value="代码识别")
    private String dmsb;
    @ApiModelProperty(value="注销判别")
    private Integer zxpb;


    public Integer getJgid() {
        return jgid;
    }

    public void setJgid(Integer jgid) {
        this.jgid = jgid;
    }

    public String getBqdm() {
        return bqdm;
    }

    public void setBqdm(String bqdm) {
        this.bqdm = bqdm;
    }

    public Integer getGnfl() {
        return gnfl;
    }

    public void setGnfl(Integer gnfl) {
        this.gnfl = gnfl;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getYfsb() {
        return yfsb;
    }

    public void setYfsb(String yfsb) {
        this.yfsb = yfsb;
    }

    public String getDmsb() {
        return dmsb;
    }

    public void setDmsb(String dmsb) {
        this.dmsb = dmsb;
    }

    public Integer getZxpb() {
        return zxpb;
    }

    public void setZxpb(Integer zxpb) {
        this.zxpb = zxpb;
    }
}
