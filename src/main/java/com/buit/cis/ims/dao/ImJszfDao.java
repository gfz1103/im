package com.buit.cis.ims.dao;

import com.buit.commons.EntityDao;
import com.buit.cis.ims.model.ImJszf;
import org.apache.ibatis.annotations.Mapper;

import java.util.Map;

/**
 * 住院_结算作废<br>
 * @author ZHOUHAISHENG
 */
@Mapper
public interface ImJszfDao extends EntityDao<ImJszf,Integer> {
    /**
     * 查询是否有结算作废记录
     * @param map
     * @return
     */
    Integer checkCount(Map<String, Object> map);

    /**
     * 取消日报
     * @param parameters
     */
    void doCancelCommit(Map<String, Object> parameters);

    /**
     * 查询结账时间内 结账作废的记录 数量
     * @param parameters
     * @return
     */
    Integer queryJzrqCoount(Map<String, Object> parameters);

    /**
     *  修改结账作废的结账日期
     * @param parameters
     */
    void updateJzrq(Map<String, Object> parameters);

    /**
     * 住院管理-日终汇总（取消汇总）
     * @param parameters
     */
    void doCancelCollectCommit(Map<String, Object> parameters);

    /**
     * 查询汇总次数
     * @param parametersll_count
     * @return
     */
    Integer queryHzCount(Map<String, Object> parametersll_count);

    /**
     * 修改汇总日期
     * @param parametersupd
     */
    void updateHzrq(Map<String, Object> parametersupd);

    /**
     * 汇总计算作废的费用合计
     * @param parametersje
     * @return
     */
    Map<String, Object> queryHzFyhjJszf(Map<String, Object> parametersje);

    /**
     * 查询汇总现金作废的自负金额
     * @param parametersje
     * @return
     */
    Map<String, Object> queryHzZfhjXjzp(Map<String, Object> parametersje);

    /**
     * 查询汇总现金支票缴款合计作废
     * @param parametersje
     * @return
     */
    Map<String, Object> queryHzJkhjZfXjzp(Map<String, Object> parametersje);
}
