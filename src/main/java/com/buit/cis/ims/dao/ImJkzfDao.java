package com.buit.cis.ims.dao;

import com.buit.commons.EntityDao;
import com.buit.cis.ims.model.ImJkzf;
import org.apache.ibatis.annotations.Mapper;

import java.util.Map;

/**
 * 住院_缴款作废<br>
 * @author ZHOUHAISHENG
 */
@Mapper
public interface ImJkzfDao extends EntityDao<ImJkzf,Integer> {
    /**
     * 查询是否有住院缴款作废记录
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
     *查询结账期内 缴款作废的记录
     * @param parameters
     * @return
     */
    Integer queryJzrqCount(Map<String, Object> parameters);

    /**
     * 修改缴款作废的结账日期
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
}
