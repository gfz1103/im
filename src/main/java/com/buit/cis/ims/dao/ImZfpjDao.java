package com.buit.cis.ims.dao;

import com.buit.commons.EntityDao;
import com.buit.cis.ims.model.ImZfpj;
import org.apache.ibatis.annotations.Mapper;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

/**
 * 住院_作废票据<br>
 * @author ZHOUHAISHENG
 */
@Mapper
public interface ImZfpjDao extends EntityDao<ImZfpj, Timestamp> {
    /**
     * 取消日报
     * @param parameters
     */
    void doCancelCommit(Map<String, Object> parameters);

    /**
     * 查询结账发票作废信息(结账日期不为空)
     * @param parameters
     * @return
     */
    List<Map<String, Object>> queryJzxxByJzrq(Map<String, Object> parameters);

    /**
     * 询结账发票作废信息
     * @param parameters
     * @return
     */
    List<Map<String, Object>> queryJzxx(Map<String, Object> parameters);
}
