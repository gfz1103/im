package com.buit.cis.ims.dao;

import com.buit.commons.EntityDao;
import com.buit.cis.ims.model.ImJzhz;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * 住院_收入结帐汇总<br>
 * @author ZHOUHAISHENG
 */
@Mapper
public interface ImJzhzDao extends EntityDao<ImJzhz,Integer> {
    /**
     * 查询时间内结账汇总的数量
     * @param parameters
     * @return
     */
    Integer queryHzCount(Map<String, Object> parameters);

    /**
     * 查询最大的汇总日期
     * @param parameters
     * @return
     */
    Map<String, Object> queryMaxHzrq(Map<String, Object> parameters);

    /**
     * 住院管理-日终汇总（取消汇总）
     * @param parameters
     */
    void doCancelCollectCommit(Map<String, Object> parameters);

    /**
     *  查询上期结存
     * @param parametersbqye
     * @return
     */
    Map<String, Object> queryBqye(Map<String, Object> parametersbqye);

    /**
     * 按病人性质日终汇总
     * @param parameters
     * @return
     */
    List<Map<String, Object>> queryHzIdsBrxz(Map<String, Object> parameters);

    /**
     * 按付款方式汇总
     * @param parameters
     * @return
     */
    List<Map<String, Object>> queryHzIdsFkfs(Map<String, Object> parameters);

    /**
     * 按病人性质汇总作废
     * @param parametershzbd
     * @return
     */
    List<Map<String, Object>> queryHzIdsBrxzZf(Map<String, Object> parametershzbd);

    /**
     * 按付款方式汇总作废
     * @param parametershzbd
     * @return
     */
    List<Map<String, Object>> queryHzIdsFkfsZf(Map<String, Object> parametershzbd);

    /**
     * 日终汇总表（二）list数据
     * @param parameters
     * @return
     */
    List<Map<String, Object>> queryHzListTwo(Map<String, Object> parameters);

    /**
     * 日终汇总表（二）住院结算list数据
     * @param parameters
     * @return
     */
    List<Map<String, Object>> queryHzIdsZyjsTwo(Map<String, Object> parameters);
    /**
     * 日终汇总表（二）按病人性质统计应收金额
     * @param parameters
     * @return
     */
    List<Map<String, Object>> queryHzIdsBrxzTwo(Map<String, Object> parameters);

    /**
     * 日终汇总表（二）统计预缴款
     * @param parameters
     * @return
     */
    List<Map<String, Object>> queryHzIdsYjkTwo(Map<String, Object> parameters);

    /**
     *日终汇总表（二）出院总结汇总住院结算金额
     * @param parameters
     * @return
     */
    List<Map<String, Object>> queryHzIdsZyjsTwoCyzj(Map<String, Object> parameters);

    /**
     * 日终汇总表（二）出院总结 汇总病人性质金额
     * @param parameters
     * @return
     */
    List<Map<String, Object>> queryHzIdsbrxzTwoCyzj(Map<String, Object> parameters);

    /**
     * 日终汇总表（四）出院总结 汇总住院结算信息
     * @param parameter
     * @return
     */
    List<Map<String, Object>> queryHzIdsZyjsFour(Map<String, Object> parameter);

    /**
     *日终汇总表（四）按病人性质汇总
     * @param parameter
     * @return
     */
    List<Map<String, Object>> queryHzbrxzFour(Map<String, Object> parameter);
}
