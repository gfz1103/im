package com.buit.cis.ims.dao;

import com.buit.commons.EntityDao;
import com.buit.cis.ims.model.ImSrhz;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * 住院收入汇总<br>
 * @author ZHOUHAISHENG
 */
@Mapper
public interface ImSrhzDao extends EntityDao<ImSrhz,Integer> {
    /**
     * 住院管理-日终汇总（取消汇总）
     * @param parameters
     */
    void doCancelCollectCommit(Map<String, Object> parameters);

    /**
     * 日终汇总（三）总费用
     * @param parameter
     * @return
     */
    Map<String, Object> queryHzThree(Map<String, Object> parameter);

    /**
     * 日终汇总（三）费用项目汇总
     * @param parameters
     * @return
     */
    List<Map<String, Object>> qyeryHzFymxThree(Map<String, Object> parameters);
}
