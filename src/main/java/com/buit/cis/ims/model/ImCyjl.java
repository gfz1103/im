package com.buit.cis.ims.model;

import java.sql.Timestamp;
import com.buit.commons.PageQuery;

/**
 * 类名称：ImCyjl<br> 
 * 类描述：住院_住院记录
 * @author GONGFANGZHOU 
 * @ApiModel(value="住院_住院记录")
 */
public class ImCyjl  extends  PageQuery{
	//@ApiModelProperty(value="记录序号")
    /** 记录序号 */
    private Integer jlxh;
	//@ApiModelProperty(value="机构代码")
    /** 机构代码 */
    private Integer jgid;
	//@ApiModelProperty(value="住院号")
    /** 住院号 */
    private Integer zyh;
	//@ApiModelProperty(value="出院时间")
    /** 出院时间 */
    private Timestamp cysj;
	//@ApiModelProperty(value="出院方式 | 与GY_DMZD（DMLB= 23）对应 1：治愈 2：好转 3：未愈 4：死亡 5：其他")
    /** 出院方式 | 与GY_DMZD（DMLB= 23）对应 1：治愈 2：好转 3：未愈 4：死亡 5：其他 */
    private Integer cyfs;
	//@ApiModelProperty(value="操作类型 | 1出院证、2取消出院证、3通知出院、4取消通知出院")
    /** 操作类型 | 1出院证、2取消出院证、3通知出院、4取消通知出院 */
    private Integer czlx;
	//@ApiModelProperty(value="操作人")
    /** 操作人 */
    private Integer czr;
	//@ApiModelProperty(value="操作日期")
    /** 操作日期 */
    private Timestamp czrq;

    /** 设置:记录序号  */
    public void setJlxh(Integer value) {
        this.jlxh = value;
    }
    /** 获取:记录序号 */
    public Integer getJlxh() {
        return jlxh;
    }

    /** 设置:机构代码  */
    public void setJgid(Integer value) {
        this.jgid = value;
    }
    /** 获取:机构代码 */
    public Integer getJgid() {
        return jgid;
    }

    /** 设置:住院号  */
    public void setZyh(Integer value) {
        this.zyh = value;
    }
    /** 获取:住院号 */
    public Integer getZyh() {
        return zyh;
    }

    /** 设置:出院时间  */
    public void setCysj(Timestamp value) {
        this.cysj = value;
    }
    /** 获取:出院时间 */
    public Timestamp getCysj() {
        return cysj;
    }

    /** 设置:出院方式 | 与GY_DMZD（DMLB= 23）对应 1：治愈 2：好转 3：未愈 4：死亡 5：其他  */
    public void setCyfs(Integer value) {
        this.cyfs = value;
    }
    /** 获取:出院方式 | 与GY_DMZD（DMLB= 23）对应 1：治愈 2：好转 3：未愈 4：死亡 5：其他 */
    public Integer getCyfs() {
        return cyfs;
    }

    /** 设置:操作类型 | 1出院证、2取消出院证、3通知出院、4取消通知出院  */
    public void setCzlx(Integer value) {
        this.czlx = value;
    }
    /** 获取:操作类型 | 1出院证、2取消出院证、3通知出院、4取消通知出院 */
    public Integer getCzlx() {
        return czlx;
    }

    /** 设置:操作人  */
    public void setCzr(Integer value) {
        this.czr = value;
    }
    /** 获取:操作人 */
    public Integer getCzr() {
        return czr;
    }

    /** 设置:操作日期  */
    public void setCzrq(Timestamp value) {
        this.czrq = value;
    }
    /** 获取:操作日期 */
    public Timestamp getCzrq() {
        return czrq;
    }


}
