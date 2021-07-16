package com.buit.cis.ims.dao;

import com.buit.commons.EntityDao;
import com.buit.cis.ims.model.ImFeeFyhz;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * 费用汇总<br>
 * @author ZHOUHAISHENG
 */
@Mapper
public interface ImFeeFyhzDao extends EntityDao<ImFeeFyhz,Integer> {
    /**
     * 住院管理-日终汇总（取消汇总）
     * @param parameters
     */
    void doCancelCollectCommit(Map<String, Object> parameters);

    /**
     * 查询上期汇总日期
     * @param parametershzrq
     * @return
     */
    Map<String, Object> queryMaxHzrq(Map<String, Object> parametershzrq);

    /**
     * 查询上期结存
     * @param parameterslds_sqjc
     * @return
     */
    List<Map<String, Object>> queryHzSqjc(Map<String, Object> parameterslds_sqjc);
}
